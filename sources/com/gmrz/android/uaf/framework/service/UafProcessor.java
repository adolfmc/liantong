package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import android.content.Context;
import com.fido.android.framework.service.Mfac;
import com.fido.uaf.ver0100.engine.UafEngine;
import com.fido.uaf.ver0100.message.AuthenticationRequest;
import com.fido.uaf.ver0100.message.AuthenticationResponse;
import com.fido.uaf.ver0100.message.DeregistrationRequest;
import com.fido.uaf.ver0100.message.RegistrationRequest;
import com.fido.uaf.ver0100.message.RegistrationResponse;
import com.fido.uaf.ver0100.message.UafMessage;
import com.fido.uaf.ver0100.types.AuthenticatorRegistrationAssertion;
import com.fido.uaf.ver0100.types.AuthenticatorSignAssertion;
import com.fido.uaf.ver0100.types.ChannelBinding;
import com.fido.uaf.ver0100.types.DeregisterAuthenticator;
import com.fido.uaf.ver0100.types.FinalChallengeParams;
import com.fido.uaf.ver0100.types.MatchCriteria;
import com.fido.uaf.ver0100.types.Policy;
import com.fido.uaf.ver0100.types.UafError;
import com.fido.uaf.ver0100.types.UafException;
import com.fidoalliance.uaf.app.api.Authenticator;
import com.fidoalliance.uaf.app.api.DiscoveryData;
import com.fidoalliance.uaf.app.api.Version;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.ASMResponse;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticateOut;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.DisplayPNGCharacteristicsDescriptor;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.asm.api.uaf.json.RegisterIn;
import com.gmrz.android.client.asm.api.uaf.json.RegisterOut;
import com.gmrz.android.client.utils.JsonObjectAdapter;
import com.gmrz.android.client.utils.Logger;
import com.gmrz.android.uaf.framework.service.UafPolicyProcessor;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.utils.AAID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.fidoalliance.uaf.client.UAFMessage;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafProcessor {
    private static final String TAG = "UafProcessor";
    private Activity mCallerActivity;
    private Context mCallerContext;
    private final UafEngine mUafEngine = new UafEngine();

    /* JADX WARN: Removed duplicated region for block: B:18:0x006a A[Catch: UafException -> 0x00dc, TryCatch #0 {UafException -> 0x00dc, blocks: (B:3:0x0010, B:5:0x001a, B:7:0x001e, B:9:0x002d, B:11:0x004a, B:14:0x0055, B:16:0x005b, B:17:0x0067, B:18:0x006a, B:35:0x00af, B:36:0x00bd, B:19:0x006d, B:21:0x0077, B:32:0x00a4, B:22:0x007c, B:23:0x0083, B:24:0x0084, B:26:0x008a, B:27:0x008e, B:28:0x0095, B:29:0x0096, B:31:0x00a0, B:33:0x00a7, B:34:0x00ae, B:37:0x00be, B:38:0x00cc, B:39:0x00cd, B:40:0x00db), top: B:45:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x006d A[Catch: UafException -> 0x00dc, TryCatch #0 {UafException -> 0x00dc, blocks: (B:3:0x0010, B:5:0x001a, B:7:0x001e, B:9:0x002d, B:11:0x004a, B:14:0x0055, B:16:0x005b, B:17:0x0067, B:18:0x006a, B:35:0x00af, B:36:0x00bd, B:19:0x006d, B:21:0x0077, B:32:0x00a4, B:22:0x007c, B:23:0x0083, B:24:0x0084, B:26:0x008a, B:27:0x008e, B:28:0x0095, B:29:0x0096, B:31:0x00a0, B:33:0x00a7, B:34:0x00ae, B:37:0x00be, B:38:0x00cc, B:39:0x00cd, B:40:0x00db), top: B:45:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0084 A[Catch: UafException -> 0x00dc, TryCatch #0 {UafException -> 0x00dc, blocks: (B:3:0x0010, B:5:0x001a, B:7:0x001e, B:9:0x002d, B:11:0x004a, B:14:0x0055, B:16:0x005b, B:17:0x0067, B:18:0x006a, B:35:0x00af, B:36:0x00bd, B:19:0x006d, B:21:0x0077, B:32:0x00a4, B:22:0x007c, B:23:0x0083, B:24:0x0084, B:26:0x008a, B:27:0x008e, B:28:0x0095, B:29:0x0096, B:31:0x00a0, B:33:0x00a7, B:34:0x00ae, B:37:0x00be, B:38:0x00cc, B:39:0x00cd, B:40:0x00db), top: B:45:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0096 A[Catch: UafException -> 0x00dc, TryCatch #0 {UafException -> 0x00dc, blocks: (B:3:0x0010, B:5:0x001a, B:7:0x001e, B:9:0x002d, B:11:0x004a, B:14:0x0055, B:16:0x005b, B:17:0x0067, B:18:0x006a, B:35:0x00af, B:36:0x00bd, B:19:0x006d, B:21:0x0077, B:32:0x00a4, B:22:0x007c, B:23:0x0083, B:24:0x0084, B:26:0x008a, B:27:0x008e, B:28:0x0095, B:29:0x0096, B:31:0x00a0, B:33:0x00a7, B:34:0x00ae, B:37:0x00be, B:38:0x00cc, B:39:0x00cd, B:40:0x00db), top: B:45:0x0010 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.gmrz.android.uaf.framework.service.UafProcessor.UafResponseTask processUafRequest(com.gmrz.android.uaf.framework.service.UafProcessor.UafRequestTask r7) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.uaf.framework.service.UafProcessor.processUafRequest(com.gmrz.android.uaf.framework.service.UafProcessor$UafRequestTask):com.gmrz.android.uaf.framework.service.UafProcessor$UafResponseTask");
    }

    private boolean isPolicyStructureValid(Policy policy) {
        for (List<MatchCriteria> list : policy.accepted) {
            for (MatchCriteria matchCriteria : list) {
                if (matchCriteria.aaid != null && (matchCriteria.vendorID != null || matchCriteria.userVerification != 0 || matchCriteria.keyProtection != 0 || matchCriteria.matcherProtection != 0 || matchCriteria.tcDisplay != 0 || matchCriteria.authenticationAlgorithms != null || matchCriteria.assertionSchemes != null || matchCriteria.attestationTypes != null)) {
                    return false;
                }
                if (matchCriteria.aaid == null && (matchCriteria.authenticationAlgorithms == null || matchCriteria.assertionSchemes == null)) {
                    return false;
                }
            }
        }
        return true;
    }

    public DiscoveryData getDiscoveryData(Activity activity) {
        Logger.m15889i(TAG, "getDiscoveryData...");
        DiscoveryData discoveryData = new DiscoveryData();
        discoveryData.clientVendor = "GMRZ, Inc";
        discoveryData.clientVersion = new Version(1, 0);
        this.mCallerActivity = activity;
        try {
            HashSet hashSet = new HashSet();
            for (Authenticator authenticator : AuthenticatorManager.instance(this.mCallerActivity).getAuthenticators()) {
                new com.fidoalliance.uaf.app.api.Authenticator();
                com.fidoalliance.uaf.app.api.Authenticator authenticator2 = new com.fidoalliance.uaf.app.api.Authenticator();
                authenticator2.title = authenticator.mAuthnrInfo.title;
                if (authenticator2.title == null || authenticator2.title.equals("")) {
                    authenticator2.title = "title";
                }
                authenticator2.aaid = authenticator.mAuthnrInfo.aaid;
                authenticator2.description = authenticator.mAuthnrInfo.description;
                if (authenticator2.description == null || authenticator2.description.equals("")) {
                    authenticator2.description = "description";
                }
                for (com.gmrz.android.client.asm.api.uaf.json.Version version : authenticator.mAuthnrInfo.asmVersions) {
                    authenticator2.supportedUAFVersions.add(new Version(version.major.intValue(), version.minor.intValue()));
                    hashSet.add(new Version(version.major.intValue(), version.minor.intValue()));
                }
                authenticator2.assertionScheme = authenticator.mAuthnrInfo.assertionScheme;
                authenticator2.authenticationAlgorithm = authenticator.mAuthnrInfo.authenticationAlgorithm;
                authenticator2.attestationTypes = authenticator.mAuthnrInfo.attestationTypes;
                authenticator2.userVerification = authenticator.mAuthnrInfo.userVerification;
                authenticator2.keyProtection = authenticator.mAuthnrInfo.keyProtection;
                authenticator2.matcherProtection = authenticator.mAuthnrInfo.matcherProtection;
                authenticator2.attachmentHint = authenticator.mAuthnrInfo.attachmentHint;
                authenticator2.isSecondFactorOnly = authenticator.mAuthnrInfo.isSecondFactorOnly;
                authenticator2.tcDisplay = authenticator.mAuthnrInfo.tcDisplay;
                authenticator2.tcDisplayContentType = authenticator.mAuthnrInfo.tcDisplayContentType;
                authenticator2.icon = authenticator.mAuthnrInfo.icon;
                authenticator2.supportedExtensionIDs = authenticator.mAuthnrInfo.supportedExtensionIDs;
                if ("image/png".equals(authenticator.mAuthnrInfo.tcDisplayContentType) && authenticator.mAuthnrInfo.tcDisplayPNGCharacteristics != null && authenticator.mAuthnrInfo.tcDisplayPNGCharacteristics.size() > 0) {
                    authenticator2.tcDisplayPNGCharacteristics = new ArrayList();
                    for (DisplayPNGCharacteristicsDescriptor displayPNGCharacteristicsDescriptor : authenticator.mAuthnrInfo.tcDisplayPNGCharacteristics) {
                        Authenticator.DisplayPNGCharacteristicsDescriptor displayPNGCharacteristicsDescriptor2 = new Authenticator.DisplayPNGCharacteristicsDescriptor();
                        displayPNGCharacteristicsDescriptor2.width = displayPNGCharacteristicsDescriptor.width;
                        displayPNGCharacteristicsDescriptor2.height = displayPNGCharacteristicsDescriptor.height;
                        displayPNGCharacteristicsDescriptor2.bitDepth = displayPNGCharacteristicsDescriptor.bitDepth;
                        displayPNGCharacteristicsDescriptor2.colorType = displayPNGCharacteristicsDescriptor.colorType;
                        displayPNGCharacteristicsDescriptor2.compression = displayPNGCharacteristicsDescriptor.compression;
                        displayPNGCharacteristicsDescriptor2.f27853filter = displayPNGCharacteristicsDescriptor.f27854filter;
                        displayPNGCharacteristicsDescriptor2.interlace = displayPNGCharacteristicsDescriptor.interlace;
                        if (displayPNGCharacteristicsDescriptor.plte != null && displayPNGCharacteristicsDescriptor.plte.size() > 0) {
                            displayPNGCharacteristicsDescriptor2.plte = new ArrayList();
                            for (DisplayPNGCharacteristicsDescriptor.rgbPalletteEntry rgbpalletteentry : displayPNGCharacteristicsDescriptor.plte) {
                                Authenticator.rgbPaletteEntry rgbpaletteentry = new Authenticator.rgbPaletteEntry();
                                rgbpaletteentry.f10126b = rgbpalletteentry.f10149b;
                                rgbpaletteentry.f10127g = rgbpalletteentry.f10150g;
                                rgbpaletteentry.f10128r = rgbpalletteentry.f10151r;
                                displayPNGCharacteristicsDescriptor2.plte.add(rgbpaletteentry);
                            }
                        } else {
                            displayPNGCharacteristicsDescriptor2.plte = null;
                        }
                        authenticator2.tcDisplayPNGCharacteristics.add(displayPNGCharacteristicsDescriptor2);
                    }
                }
                discoveryData.supportedUAFVersions.addAll(hashSet);
                if (authenticator2.tcDisplayContentType != null && !authenticator2.tcDisplayContentType.equals("image/png")) {
                    authenticator2.tcDisplayPNGCharacteristics = null;
                }
                discoveryData.availableAuthenticators.add(authenticator2);
            }
        } catch (Exception e) {
            Logger.m15891e(TAG, "Error while processing UAF discovery", e);
        }
        return discoveryData;
    }

    private UAFMessage processRegister(UafRequestTask uafRequestTask, RegistrationRequest registrationRequest, String str, String str2) throws UafException {
        Logger.startTimer(TAG, "processRegister");
        if (!uafRequestTask.mCheckPolicy) {
            try {
                Mfac.Instance().showEulaDialog();
            } catch (AsmException unused) {
                Logger.endTimer(TAG, "processRegister");
                throw new UafException(UafError.Error.USER_CANCELLED);
            }
        }
        if (!Mfac.Instance().isEnabled()) {
            Logger.endTimer(TAG, "processRegister");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) AuthenticatorManager.instance(this.mCallerActivity).getAuthenticators();
        if (arrayList2 == null || arrayList2.size() == 0) {
            Logger.m15892e(TAG, "processRegister () - Failed to get authenticators list");
            Logger.endTimer(TAG, "processRegister");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        }
        UafPolicyProcessor uafPolicyProcessor = new UafPolicyProcessor(registrationRequest.policy.accepted, registrationRequest.policy.disallowed, arrayList2, this.mCallerActivity);
        List<Authenticator> eligibleAuthnrs = uafPolicyProcessor.getEligibleAuthnrs();
        if (eligibleAuthnrs == null || eligibleAuthnrs.size() == 0) {
            Logger.m15889i(TAG, "localUafPolicyProcessor.getEligibleAuthnrs list size is 0. no suitable authenticators");
            Logger.endTimer(TAG, "processRegister");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        }
        List<List<UafPolicyProcessor.AcceptedAuthnr>> acceptedAuthnrs = uafPolicyProcessor.getAcceptedAuthnrs();
        if (acceptedAuthnrs == null || acceptedAuthnrs.size() == 0) {
            Logger.m15889i(TAG, "NO_SUITABLE_AUTHENTICATOR");
            Logger.endTimer(TAG, "processRegister");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        } else if (uafRequestTask.mCheckPolicy) {
            Logger.endTimer(TAG, "processRegister");
            return null;
        } else {
            String finalChallengeParams = getFinalChallengeParams(str2, registrationRequest.challenge, uafRequestTask.mChannelBindings, str);
            RegisterIn registerIn = new RegisterIn();
            registerIn.appID = str2;
            registerIn.username = registrationRequest.username;
            registerIn.finalChallenge = finalChallengeParams;
            Collections.sort(acceptedAuthnrs, new Comparator<List<UafPolicyProcessor.AcceptedAuthnr>>() { // from class: com.gmrz.android.uaf.framework.service.UafProcessor.1
                @Override // java.util.Comparator
                public int compare(List<UafPolicyProcessor.AcceptedAuthnr> list, List<UafPolicyProcessor.AcceptedAuthnr> list2) {
                    return list.get(0).index - list2.get(0).index;
                }
            });
            int i = 1;
            for (UafPolicyProcessor.AcceptedAuthnr acceptedAuthnr : acceptedAuthnrs.get(acceptedAuthnrs.size() - 1)) {
                Authenticator authenticator = eligibleAuthnrs.get(acceptedAuthnr.index);
                AuthenticatorInfo authnrInfo = authenticator.getAuthnrInfo();
                if (authnrInfo.aaid.equals(AAID.GESTURE_ECC.name) && acceptedAuthnrs.size() == 2) {
                    Logger.m15889i(TAG, "select 004A#01AC and select again to 004A#01AF");
                    authenticator = eligibleAuthnrs.get(0);
                    authnrInfo = authenticator.getAuthnrInfo();
                }
                registerIn.attestationType = authenticator.getAttestationType();
                if (registrationRequest.header.exts != null) {
                    Iterator<Extension> it = registrationRequest.header.exts.iterator();
                    while (true) {
                        Iterator<Extension> it2 = it;
                        if (it2.hasNext()) {
                            Extension next = it2.next();
                            if (next.f10152id.equals("fido.uaf.safetynet") && next.data.isEmpty()) {
                                registrationRequest.header.exts.remove(next);
                                break;
                            }
                        }
                    }
                }
                try {
                    ASMResponse register = authenticator.register(registerIn, uafRequestTask.mAdditionalData);
                    Gson create = JsonObjectAdapter.GsonBuilder().create();
                    JsonObject jsonObject = register.responseData;
                    RegisterOut registerOut = (RegisterOut) (!(create instanceof Gson) ? create.fromJson((JsonElement) jsonObject, (Class<Object>) RegisterOut.class) : NBSGsonInstrumentation.fromJson(create, (JsonElement) jsonObject, (Class<Object>) RegisterOut.class));
                    arrayList.add(new AuthenticatorRegistrationAssertion(registerOut.assertionScheme, registerOut.assertion, register.exts));
                    i = 1;
                } catch (AsmException e) {
                    Logger.endTimer(TAG, "processRegister");
                    if (e.error().equals(AsmError.CANCELED)) {
                        throw new UafException(UafError.Error.USER_CANCELLED);
                    }
                    if (e.error().equals(AsmError.NO_MATCH)) {
                        throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
                    }
                    if (e.error().equals(AsmError.KEY_DISAPPEARED_PERMANENTLY)) {
                        throw new UafException(UafError.Error.KEY_DISAPPEARED_PERMANENTLY);
                    }
                    throw new UafException(UafError.Error.UNKNOWN);
                } catch (Exception unused2) {
                    String str3 = TAG;
                    Logger.m15883w(str3, "Failed to register: " + authnrInfo.aaid);
                    Logger.endTimer(TAG, "processRegister");
                    throw new UafException(UafError.Error.UNKNOWN);
                }
            }
            UafMessage[] uafMessageArr = new RegistrationResponse[i];
            uafMessageArr[0] = new RegistrationResponse(str2);
            uafMessageArr[0].header = registrationRequest.header;
            uafMessageArr[0].fcParams = finalChallengeParams;
            uafMessageArr[0].assertions = arrayList;
            Logger.endTimer(TAG, "processRegister");
            return prepareResponse(uafMessageArr, null);
        }
    }

    private UAFMessage processAuthenticate(UafRequestTask uafRequestTask, AuthenticationRequest authenticationRequest, String str, String str2) throws UafException {
        List<List<UafPolicyProcessor.AcceptedAuthnr>> acceptedAuthnrs;
        Logger.startTimer(TAG, "processAuthenticate");
        if (!Mfac.Instance().isEnabled()) {
            Logger.endTimer(TAG, "processAuthenticate");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<Authenticator> arrayList2 = (ArrayList) AuthenticatorManager.instance(this.mCallerActivity).getAuthenticators();
        if (arrayList2 != null) {
            String str3 = TAG;
            Logger.m15889i(str3, "Available authenticators: " + arrayList2.size());
        }
        ArrayList<Authenticator> registeredAuthnrs = getRegisteredAuthnrs(arrayList2, str2);
        if (registeredAuthnrs != null) {
            String str4 = TAG;
            Logger.m15889i(str4, "Registered authenticators: " + registeredAuthnrs.size());
        }
        UafPolicyProcessor uafPolicyProcessor = new UafPolicyProcessor(authenticationRequest.policy.accepted, authenticationRequest.policy.disallowed, registeredAuthnrs, this.mCallerActivity);
        List<Authenticator> eligibleAuthnrs = uafPolicyProcessor.getEligibleAuthnrs();
        if (authenticationRequest.policy.accepted.size() == 0) {
            acceptedAuthnrs = uafPolicyProcessor.getAllAuthnrs();
        } else {
            acceptedAuthnrs = uafPolicyProcessor.getAcceptedAuthnrs();
        }
        if (acceptedAuthnrs == null || acceptedAuthnrs.size() == 0) {
            Logger.m15889i(TAG, "NO_MATCH");
            Logger.endTimer(TAG, "processAuthenticate");
            throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
        } else if (uafRequestTask.mCheckPolicy) {
            Logger.m15889i(TAG, "Check_Policy: Success");
            Logger.endTimer(TAG, "processAuthenticate");
            return null;
        } else {
            String finalChallengeParams = getFinalChallengeParams(str2, authenticationRequest.challenge, uafRequestTask.mChannelBindings, str);
            AuthenticateIn authenticateIn = new AuthenticateIn();
            authenticateIn.appID = str2;
            authenticateIn.finalChallenge = finalChallengeParams;
            authenticateIn.transaction = authenticationRequest.transaction;
            for (UafPolicyProcessor.AcceptedAuthnr acceptedAuthnr : acceptedAuthnrs.get(acceptedAuthnrs.size() - 1)) {
                Authenticator authenticator = eligibleAuthnrs.get(acceptedAuthnr.index);
                AuthenticatorInfo authnrInfo = authenticator.getAuthnrInfo();
                if (!acceptedAuthnr.keyIDList.isEmpty()) {
                    authenticateIn.keyIDs = acceptedAuthnr.keyIDList;
                }
                Mfac.Instance().useSafetyNet();
                if (authenticationRequest.header.exts != null) {
                    Iterator<Extension> it = authenticationRequest.header.exts.iterator();
                    while (true) {
                        Iterator<Extension> it2 = it;
                        if (it2.hasNext()) {
                            Extension next = it2.next();
                            if (next.f10152id.equals("fido.uaf.safetynet") && next.data.isEmpty()) {
                                authenticationRequest.header.exts.remove(next);
                                break;
                            }
                        }
                    }
                }
                try {
                    ASMResponse authenticate = authenticator.authenticate(authenticateIn, uafRequestTask.mAdditionalData);
                    Gson create = JsonObjectAdapter.GsonBuilder().create();
                    JsonObject jsonObject = authenticate.responseData;
                    arrayList.add(new AuthenticatorSignAssertion(authnrInfo.assertionScheme, ((AuthenticateOut) (!(create instanceof Gson) ? create.fromJson((JsonElement) jsonObject, (Class<Object>) AuthenticateOut.class) : NBSGsonInstrumentation.fromJson(create, (JsonElement) jsonObject, (Class<Object>) AuthenticateOut.class))).assertion, authenticate.exts));
                } catch (AsmException e) {
                    Logger.endTimer(TAG, "processAuthenticate");
                    if (e.error().equals(AsmError.CANCELED)) {
                        throw new UafException(UafError.Error.USER_CANCELLED);
                    }
                    if (e.error().equals(AsmError.NO_MATCH)) {
                        throw new UafException(UafError.Error.NO_SUITABLE_AUTHENTICATOR);
                    }
                    if (e.error().equals(AsmError.KEY_DISAPPEARED_PERMANENTLY)) {
                        throw new UafException(UafError.Error.KEY_DISAPPEARED_PERMANENTLY);
                    }
                    if (e.error().equals(AsmError.BIOMETRIC_USER_PREFERRED_IRIS)) {
                        throw new UafException(UafError.Error.BIOMETRIC_USER_PREFERRED_IRIS);
                    }
                    if (e.error().equals(AsmError.GM_NEED_REGISTER)) {
                        throw new UafException(UafError.Error.GM_NEED_REGISTER);
                    }
                    throw new UafException(UafError.Error.UNKNOWN);
                } catch (Exception unused) {
                    String str5 = TAG;
                    Logger.m15883w(str5, "Failed to authenticate: " + authnrInfo.aaid);
                }
            }
            UafMessage[] uafMessageArr = {new AuthenticationResponse(str2)};
            uafMessageArr[0].header = authenticationRequest.header;
            uafMessageArr[0].fcParams = finalChallengeParams;
            uafMessageArr[0].assertions = arrayList;
            Logger.endTimer(TAG, "processAuthenticate");
            return prepareResponse(uafMessageArr, null);
        }
    }

    private void processDeregister(DeregistrationRequest deregistrationRequest, String str) {
        Logger.startTimer(TAG, "processDeregister");
        for (DeregisterAuthenticator deregisterAuthenticator : deregistrationRequest.authenticators) {
            AuthenticatorManager.instance(this.mCallerActivity).deregisterAuthenticator(deregisterAuthenticator, str);
        }
        Logger.endTimer(TAG, "processDeregister");
    }

    private String getFinalChallengeParams(String str, String str2, ChannelBinding channelBinding, String str3) {
        return new FinalChallengeParams(str, str2, str3, channelBinding).getFinalChallenge();
    }

    private UAFMessage prepareResponse(UafMessage[] uafMessageArr, String str) {
        UAFMessage uAFMessage = new UAFMessage();
        uAFMessage.uafProtocolMessage = this.mUafEngine.buildResponse(uafMessageArr);
        uAFMessage.additionalData = str;
        return uAFMessage;
    }

    private ArrayList<Authenticator> getRegisteredAuthnrs(ArrayList<Authenticator> arrayList, String str) {
        ArrayList<Authenticator> arrayList2 = new ArrayList<>();
        Iterator<Authenticator> it = arrayList.iterator();
        while (it.hasNext()) {
            Authenticator next = it.next();
            if (next.isRegistered(str)) {
                arrayList2.add(next);
            }
        }
        return arrayList2;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class UafRequestTask {
        public final Activity mActivity;
        public final String mAdditionalData;
        public final String mCallerPkgName;
        public final int mCallingPid;
        public final int mCallingUid;
        public final ChannelBinding mChannelBindings;
        public final boolean mCheckPolicy;
        public final Context mContext;
        public final String mOrigin;
        public final String mUafMessage;

        public UafRequestTask(String str, ChannelBinding channelBinding, String str2, String str3, int i, int i2, Context context, String str4, boolean z, Activity activity) {
            this.mUafMessage = str;
            this.mChannelBindings = channelBinding;
            this.mAdditionalData = str4;
            this.mOrigin = str2;
            this.mCallingPid = i;
            this.mCallingUid = i2;
            this.mCheckPolicy = z;
            this.mActivity = activity;
            this.mContext = context;
            this.mCallerPkgName = str3;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.mCheckPolicy ? "CHECK_POLICY " : "");
            sb.append("from ");
            sb.append(this.mCallerPkgName);
            sb.append(", PID=");
            sb.append(this.mCallingPid);
            sb.append(", UID=");
            sb.append(this.mCallingUid);
            sb.append(",\n    Origin=");
            sb.append(this.mOrigin);
            sb.append(",\n    ChannelBinding=");
            sb.append(this.mChannelBindings);
            sb.append(",\n    AdditionalData=");
            sb.append(this.mAdditionalData);
            sb.append(",\n    Message=");
            sb.append(this.mUafMessage);
            return sb.toString();
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class UafResponseTask {
        public UafError.Error returnCode;
        public UAFMessage uafResponseMessage;

        public UafResponseTask(UAFMessage uAFMessage, UafError.Error error) {
            this.uafResponseMessage = null;
            this.returnCode = UafError.Error.UNKNOWN;
            this.uafResponseMessage = uAFMessage;
            this.returnCode = error;
        }

        public String toString() {
            String str = "Status:" + this.returnCode;
            if (this.uafResponseMessage != null) {
                return str + "\n    Message: " + this.uafResponseMessage.uafProtocolMessage;
            }
            return str;
        }
    }
}
