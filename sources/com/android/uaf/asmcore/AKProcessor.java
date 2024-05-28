package com.android.uaf.asmcore;

import android.app.Activity;
import com.android.client.asm.core.MatcherParamsHelper;
import com.android.client.asm.core.uaf.AuthenticatorCore;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AuthenticatorDatabase;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AKProcessor {
    private static final String TAG = "AKProcessor";
    private IAuthenticatorKernel mAK;
    private AkAuthnrInfo mAKAuthenticatorInfo;
    private TLVCommandEncoder mEncoder;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AKResponseParams {
        public byte[] additionalAKInfoToBeStored;
        public String assertion;
        public short statusCode;
        public byte[] transactionConfirmationToken;
        public byte[] userVerifyToken;
        public List<AuthenticatorCore.Username> usernames = new ArrayList();
        public List<GetRegistrationsOut.AppRegistration> appRegistrations = new ArrayList();
        public List<Extension> exts = new ArrayList();
        public AkInfo info = new AkInfo();
        public AuthenticatorDatabase.RegistrationRecord regToBeStored = new AuthenticatorDatabase.RegistrationRecord();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AkAuthnrInfo {
        public AuthenticatorInfo generalInfo = new AuthenticatorInfo();
        public AuthnrAdditionalInfo additionalInfo = new AuthnrAdditionalInfo();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AkInfo {
        byte apiVersion;
        public List<AkAuthnrInfo> authenticators = new ArrayList();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AuthnrAdditionalInfo {
        public boolean keyHandleStoredInside = false;
        public boolean builtinEnrollUI = false;
        public boolean builtinSettingUI = false;
        public boolean expectAPPID = false;
        public byte maxKeyHandle = 0;
        public String akHashAlg = "";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AKRequestParams {
        public byte[] KHAccessToken;
        public byte[] aaid;
        public byte[] additionalAKArgument;
        public byte[] appID;
        public List<byte[]> attestationCerts;
        public short attestationType;
        public IAuthenticatorDescriptor authenticatorDescriptor;
        public byte authenticatorIndex;
        public short cmd;
        public List<IMatcher.Extension> extensions;
        public byte[] finalChallenge;
        public List<byte[]> keyHandles;
        public byte[] keyID;
        public IMatcher.MatcherInParams matcherInParams;
        public MatcherParamsHelper.AuthenticatorMatcherType matcherType;
        public byte matcherVersion;
        public byte[] transaction;
        public byte[] transactionConfirmationToken;
        public byte[] userName;
        public byte[] userVerifyToken;

        public AKRequestParams setCmd(short s) {
            this.cmd = s;
            return this;
        }

        public AKRequestParams setExtensions(List<IMatcher.Extension> list) {
            this.extensions = list;
            return this;
        }

        public AKRequestParams setAuthenticatorIndex(byte b) {
            this.authenticatorIndex = b;
            return this;
        }

        public AKRequestParams setAppID(byte[] bArr) {
            this.appID = bArr;
            return this;
        }

        public AKRequestParams setFinalChallenge(byte[] bArr) {
            this.finalChallenge = bArr;
            return this;
        }

        public AKRequestParams setTransaction(byte[] bArr) {
            this.transaction = bArr;
            return this;
        }

        public AKRequestParams setUserName(byte[] bArr) {
            this.userName = bArr;
            return this;
        }

        public AKRequestParams setAttestationType(short s) {
            this.attestationType = s;
            return this;
        }

        public AKRequestParams setKHAccessToken(byte[] bArr) {
            this.KHAccessToken = bArr;
            return this;
        }

        public AKRequestParams setUserVerifyToken(byte[] bArr) {
            this.userVerifyToken = bArr;
            return this;
        }

        public AKRequestParams setTransactionConfirmationToken(byte[] bArr) {
            this.transactionConfirmationToken = bArr;
            return this;
        }

        public AKRequestParams setKeyID(byte[] bArr) {
            this.keyID = bArr;
            return this;
        }

        public AKRequestParams setKeyHandles(List<byte[]> list) {
            this.keyHandles = list;
            return this;
        }

        public AKRequestParams setAdditionalAKArgument(byte[] bArr) {
            this.additionalAKArgument = bArr;
            return this;
        }

        public AKRequestParams setMatcherType(MatcherParamsHelper.AuthenticatorMatcherType authenticatorMatcherType) {
            this.matcherType = authenticatorMatcherType;
            return this;
        }

        public AKRequestParams setMatcherInParams(IMatcher.MatcherInParams matcherInParams) {
            this.matcherInParams = matcherInParams;
            return this;
        }

        public AKRequestParams setAuthenticatorDescriptor(IAuthenticatorDescriptor iAuthenticatorDescriptor) {
            this.authenticatorDescriptor = iAuthenticatorDescriptor;
            return this;
        }

        public AKRequestParams setAaid(byte[] bArr) {
            this.aaid = bArr;
            return this;
        }

        public AKRequestParams setAttestationCerts(List<byte[]> list) {
            this.attestationCerts = list;
            return this;
        }

        public AKRequestParams setMatcherVersion(byte b) {
            this.matcherVersion = b;
            return this;
        }
    }

    public AKProcessor(IAuthenticatorKernel iAuthenticatorKernel) {
        this.mAK = null;
        this.mEncoder = null;
        this.mAK = iAuthenticatorKernel;
        this.mEncoder = new TLVCommandEncoder();
    }

    public AkAuthnrInfo getAKInfo(String str) throws AsmException {
        getLowLevelAuth(str);
        return this.mAKAuthenticatorInfo;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x006f, code lost:
        r7.mAKAuthenticatorInfo = r3;
        r0 = java.lang.Boolean.TRUE.booleanValue();
        com.gmrz.android.client.utils.Logger.m15889i("AKProcessor", "Low Level Authenticator index is " + ((int) r3.generalInfo.authenticatorIndex));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getLowLevelAuth(java.lang.String r8) throws com.gmrz.android.client.asm.api.AsmException {
        /*
            r7 = this;
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r0 = r0.booleanValue()
            java.lang.String r1 = "AKProcessor"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Get low-level Authenticator for "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r2 = r2.toString()
            com.gmrz.android.client.utils.Logger.m15889i(r1, r2)
            com.android.uaf.asmcore.AKProcessor$AKRequestParams r1 = new com.android.uaf.asmcore.AKProcessor$AKRequestParams     // Catch: java.lang.Exception -> La2
            r1.<init>()     // Catch: java.lang.Exception -> La2
            r2 = 13313(0x3401, float:1.8655E-41)
            r1.setCmd(r2)     // Catch: java.lang.Exception -> La2
            com.android.uaf.asmcore.AKProcessor$AKResponseParams r1 = r7.processAK(r1)     // Catch: java.lang.Exception -> La2
            r2 = 0
        L2b:
            com.android.uaf.asmcore.AKProcessor$AkInfo r3 = r1.info     // Catch: java.lang.Exception -> La2
            java.util.List<com.android.uaf.asmcore.AKProcessor$AkAuthnrInfo> r3 = r3.authenticators     // Catch: java.lang.Exception -> La2
            int r3 = r3.size()     // Catch: java.lang.Exception -> La2
            if (r2 >= r3) goto L95
            com.android.uaf.asmcore.AKProcessor$AkInfo r3 = r1.info     // Catch: java.lang.Exception -> La2
            java.util.List<com.android.uaf.asmcore.AKProcessor$AkAuthnrInfo> r3 = r3.authenticators     // Catch: java.lang.Exception -> La2
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.Exception -> La2
            com.android.uaf.asmcore.AKProcessor$AkAuthnrInfo r3 = (com.android.uaf.asmcore.AKProcessor.AkAuthnrInfo) r3     // Catch: java.lang.Exception -> La2
            java.lang.String r4 = "AKProcessor"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La2
            r5.<init>()     // Catch: java.lang.Exception -> La2
            java.lang.String r6 = "AkAuthnrInfo["
            r5.append(r6)     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo r6 = r3.generalInfo     // Catch: java.lang.Exception -> La2
            short r6 = r6.authenticatorIndex     // Catch: java.lang.Exception -> La2
            r5.append(r6)     // Catch: java.lang.Exception -> La2
            java.lang.String r6 = "].aaid="
            r5.append(r6)     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo r6 = r3.generalInfo     // Catch: java.lang.Exception -> La2
            java.lang.String r6 = r6.aaid     // Catch: java.lang.Exception -> La2
            r5.append(r6)     // Catch: java.lang.Exception -> La2
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.utils.Logger.m15889i(r4, r5)     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo r4 = r3.generalInfo     // Catch: java.lang.Exception -> La2
            java.lang.String r4 = r4.aaid     // Catch: java.lang.Exception -> La2
            boolean r4 = r8.equalsIgnoreCase(r4)     // Catch: java.lang.Exception -> La2
            if (r4 == 0) goto L92
            r7.mAKAuthenticatorInfo = r3     // Catch: java.lang.Exception -> La2
            java.lang.Boolean r8 = java.lang.Boolean.TRUE     // Catch: java.lang.Exception -> La2
            boolean r0 = r8.booleanValue()     // Catch: java.lang.Exception -> La2
            java.lang.String r8 = "AKProcessor"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> La2
            r1.<init>()     // Catch: java.lang.Exception -> La2
            java.lang.String r2 = "Low Level Authenticator index is "
            r1.append(r2)     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo r2 = r3.generalInfo     // Catch: java.lang.Exception -> La2
            short r2 = r2.authenticatorIndex     // Catch: java.lang.Exception -> La2
            r1.append(r2)     // Catch: java.lang.Exception -> La2
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> La2
            com.gmrz.android.client.utils.Logger.m15889i(r8, r1)     // Catch: java.lang.Exception -> La2
            goto L95
        L92:
            int r2 = r2 + 1
            goto L2b
        L95:
            if (r0 == 0) goto L98
            return
        L98:
            com.gmrz.android.client.asm.api.AsmException r8 = new com.gmrz.android.client.asm.api.AsmException
            com.gmrz.android.client.asm.api.AsmError r0 = com.gmrz.android.client.asm.api.AsmError.FAILURE
            java.lang.String r1 = "Can not find requested Authenticator."
            r8.<init>(r0, r1)
            throw r8
        La2:
            r8 = move-exception
            com.gmrz.android.client.asm.api.AsmException r0 = new com.gmrz.android.client.asm.api.AsmException
            com.gmrz.android.client.asm.api.AsmError r1 = com.gmrz.android.client.asm.api.AsmError.FAILURE
            java.lang.String r2 = "AK GetInfo failed."
            r0.<init>(r1, r2, r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.uaf.asmcore.AKProcessor.getLowLevelAuth(java.lang.String):void");
    }

    public void prepareProcessAK(Activity activity) {
        this.mAK.setCallerActivity(activity);
    }

    public AKResponseParams processAK(AKRequestParams aKRequestParams) throws AsmException {
        try {
            byte[] encode = this.mEncoder.encode(aKRequestParams);
            Logger.m15892e("AKProcessor", "AK.process request: " + getHex(encode));
            HashMap hashMap = new HashMap();
            hashMap.put(IAuthenticatorKernel.AKDataKeys.APPID, aKRequestParams.appID);
            hashMap.put(IAuthenticatorKernel.AKDataKeys.DESCRIPTOR, aKRequestParams.authenticatorDescriptor);
            hashMap.put(IAuthenticatorKernel.AKDataKeys.MATCHER_IN_PARAMS, aKRequestParams.matcherInParams);
            byte[] processRequest = this.mAK.processRequest(encode, hashMap);
            if (processRequest == null) {
                throw new Exception("Response buffer is empty");
            }
            Logger.m15895d("AKProcessor", "AK.process response: " + getHex(processRequest));
            AKResponseParams decode = this.mEncoder.decode(aKRequestParams.cmd, processRequest);
            if (13319 != aKRequestParams.cmd) {
                Logger.m15895d("AKProcessor", "respParams.statusCode:" + ((int) decode.statusCode));
                short s = decode.statusCode;
                if (s == 9) {
                    Logger.m15892e("AKProcessor", "Error: key permanently disapeared.");
                    decode.statusCode = (short) 9;
                } else if (s != 14) {
                    switch (s) {
                        case 0:
                            decode.statusCode = (short) 0;
                            break;
                        case 1:
                            Logger.m15892e("AKProcessor", "Error: unknown.");
                            decode.statusCode = (short) 1;
                            break;
                        case 2:
                            Logger.m15892e("AKProcessor", "Error: access denied.");
                            decode.statusCode = (short) 2;
                            break;
                        case 3:
                            Logger.m15892e("AKProcessor", "Error: user not enrolled.");
                            decode.statusCode = (short) 2;
                            break;
                        case 4:
                            Logger.m15892e("AKProcessor", "Error: cannot render transaction content.");
                            decode.statusCode = (short) 1;
                            break;
                        case 5:
                            Logger.m15892e("AKProcessor", "Error: user cancelled.");
                            decode.statusCode = (short) 3;
                            break;
                        case 6:
                            Logger.m15892e("AKProcessor", "Error: command not supported.");
                            decode.statusCode = (short) 1;
                            break;
                        case 7:
                            Logger.m15892e("AKProcessor", "Error: attestation not supported.");
                            decode.statusCode = (short) 1;
                            break;
                        default:
                            switch (s) {
                                case 16:
                                    Logger.m15892e("AKProcessor", "Error: User Lockout.");
                                    decode.statusCode = (short) 16;
                                    break;
                                case 17:
                                    Logger.m15892e("AKProcessor", "Error: Authenticator already exists.");
                                    decode.statusCode = (short) 4;
                                    break;
                                case 18:
                                    Logger.m15892e("AKProcessor", "Error: gm need register.");
                                    decode.statusCode = (short) 18;
                                    break;
                                default:
                                    Logger.m15892e("AKProcessor", "Error: unknown status code.");
                                    decode.statusCode = (short) 1;
                                    break;
                            }
                    }
                } else {
                    Logger.m15892e("AKProcessor", "Error: Biometric User Preferred IRIS Auth.");
                    decode.statusCode = (short) 14;
                }
            }
            return decode;
        } catch (Exception e) {
            throw new AsmException(AsmError.FAILURE, "AK process failed", e);
        }
    }

    public boolean postProcessAK() {
        Logger.m15892e("AKProcessor", "AK.postProcess request: ");
        return this.mAK.postProcess();
    }

    public byte[] prepareFinalChallenge(String str) throws AsmException {
        return hashWithAuthenticatorFunction(str.getBytes(Charsets.utf8Charset));
    }

    private byte[] hashWithAuthenticatorFunction(byte[] bArr) throws AsmException {
        try {
            return this.mAK.getDigestMethod().digest(bArr);
        } catch (Exception e) {
            throw new AsmException(AsmError.FAILURE, "Couldn't calculate hash", e);
        }
    }

    static String getHex(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append("0123456789ABCDEF".charAt((b & 240) >> 4));
            sb.append("0123456789ABCDEF".charAt(b & 15));
        }
        return sb.toString();
    }

    public List<Extension> extExtract(AKResponseParams aKResponseParams) {
        IAuthenticatorKernel iAuthenticatorKernel = this.mAK;
        if (iAuthenticatorKernel instanceof IAuthenticatorKernel.IExtAuthenticatorKernel) {
            return ((IAuthenticatorKernel.IExtAuthenticatorKernel) iAuthenticatorKernel).extExtract(aKResponseParams);
        }
        return null;
    }

    public void extInit(AKRequestParams aKRequestParams) {
        IAuthenticatorKernel iAuthenticatorKernel = this.mAK;
        if (iAuthenticatorKernel instanceof IAuthenticatorKernel.IExtAuthenticatorKernel) {
            ((IAuthenticatorKernel.IExtAuthenticatorKernel) iAuthenticatorKernel).extInit(aKRequestParams);
        }
    }
}
