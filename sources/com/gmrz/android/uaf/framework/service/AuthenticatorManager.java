package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import android.content.pm.PackageManager;
import com.fido.android.framework.service.IAuthenticatorUIAdapter;
import com.fido.android.framework.service.Mfac;
import com.fido.uaf.ver0100.types.DeregisterAuthenticator;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.ASMRequest;
import com.gmrz.android.client.asm.api.uaf.json.ASMResponse;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.DeregisterIn;
import com.gmrz.android.client.asm.api.uaf.json.GetInfoOut;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.asm.api.uaf.json.Version;
import com.gmrz.android.client.commlib.ICommunicationClientResponse;
import com.gmrz.android.client.mfac.ASMCommunicationClientRequest;
import com.gmrz.android.client.utils.JsonObjectAdapter;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.fidoalliance.uaf.client.AdditionData;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class AuthenticatorManager {
    private static final String TAG = "AuthenticatorManager";
    private static AuthenticatorManager mInstance;
    private Activity mCallerActivity;
    private String mCommClientError;
    private Object mCommClientResponse;
    private ASMCommunicationClientProxy mCommunicationLibProxy;
    private final Map<String, Authenticator> authnrsMap = new ConcurrentHashMap();
    private final Map<String, ArrayList<AuthenticatorInfo>> authenticatorInfoMap = new ConcurrentHashMap();

    public static synchronized AuthenticatorManager instance(Activity activity) {
        AuthenticatorManager authenticatorManager;
        synchronized (AuthenticatorManager.class) {
            if (mInstance == null) {
                mInstance = new AuthenticatorManager();
            }
            mInstance.mCallerActivity = activity;
            authenticatorManager = mInstance;
        }
        return authenticatorManager;
    }

    private AuthenticatorManager() {
        try {
            Mfac.Instance().getContext().getPackageManager().getPackageInfo(Mfac.Instance().getContext().getPackageName(), 0);
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            concurrentHashMap.put("LOCAL", new UAFLocalASMCommunicationClient());
            concurrentHashMap.put("INTENT", new UAFIntentASMCommunicationClient());
            this.mCommunicationLibProxy = new ASMCommunicationClientProxy(concurrentHashMap);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.m15891e(TAG, "Cannot find package info of Fido.", e);
        }
    }

    public final Map<String, Authenticator> getAuthenticatorMap() {
        return this.authnrsMap;
    }

    public final void setCommClientResponse(Object obj) {
        this.mCommClientResponse = obj;
    }

    public final void setCommClientError(String str) {
        this.mCommClientError = str;
    }

    public final List<Authenticator> getAuthenticators() {
        String[] serviceModuleList;
        AuthenticatorInfo next;
        ArrayList arrayList = new ArrayList();
        this.mCommunicationLibProxy.setUseService(Boolean.valueOf(this.mCallerActivity == null));
        for (String str : this.mCommunicationLibProxy.getServiceModuleList("ASM")) {
            ArrayList<AuthenticatorInfo> authenticatorsInfo = getAuthenticatorsInfo(str);
            if (authenticatorsInfo != null && authenticatorsInfo.size() != 0) {
                Iterator<AuthenticatorInfo> it = authenticatorsInfo.iterator();
                while (it.hasNext()) {
                    String str2 = str + next.aaid;
                    Authenticator authenticator = new Authenticator(it.next(), str2, str);
                    this.authnrsMap.put(str2, authenticator);
                    arrayList.add(authenticator);
                }
            }
        }
        return arrayList;
    }

    public final List<IAuthenticatorUIAdapter> getAuthenticatorUIAdapters(String str) {
        return new ArrayList(getAuthenticators());
    }

    private ArrayList<AuthenticatorInfo> getAuthenticatorsInfo(String str) {
        ArrayList<AuthenticatorInfo> arrayList;
        try {
        } catch (AsmException e) {
            e = e;
            arrayList = null;
        } catch (Exception e2) {
            e = e2;
            arrayList = null;
        }
        if (this.authenticatorInfoMap.containsKey(str)) {
            return this.authenticatorInfoMap.get(str);
        }
        ASMResponse executeAsmRequest = executeAsmRequest(null, null, ASMRequest.RequestType.GetInfo, (short) 0, str);
        Gson create = JsonObjectAdapter.GsonBuilder().create();
        JsonObject jsonObject = executeAsmRequest.responseData;
        arrayList = (ArrayList) ((GetInfoOut) (!(create instanceof Gson) ? create.fromJson((JsonElement) jsonObject, (Class<Object>) GetInfoOut.class) : NBSGsonInstrumentation.fromJson(create, (JsonElement) jsonObject, (Class<Object>) GetInfoOut.class))).Authenticators;
        if (arrayList != null) {
            try {
            } catch (AsmException e3) {
                e = e3;
                Logger.m15882w(TAG, "getAuthenticatorsInfo failed", e);
                return arrayList;
            } catch (Exception e4) {
                e = e4;
                Logger.m15882w(TAG, "getAuthenticatorsInfo failed", e);
                return arrayList;
            }
            if (arrayList.size() > 0) {
                this.authenticatorInfoMap.put(str, arrayList);
                return arrayList;
            }
        }
        Logger.m15892e(TAG, "getAuthenticatorsInfo failed to getInfo ");
        return arrayList;
    }

    public final void deregisterAuthenticator(DeregisterAuthenticator deregisterAuthenticator, String str) {
        List<Authenticator> authenticators = getAuthenticators();
        DeregisterIn deregisterIn = new DeregisterIn();
        deregisterIn.appID = str;
        deregisterIn.keyID = deregisterAuthenticator.keyID;
        boolean equals = deregisterAuthenticator.aaid.equals("");
        for (Authenticator authenticator : authenticators) {
            if (equals) {
                try {
                    Iterator<GetRegistrationsOut.AppRegistration> it = authenticator.getRegistrations().appRegs.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            GetRegistrationsOut.AppRegistration next = it.next();
                            if (next.appID.equals(deregisterIn.appID)) {
                                for (String str2 : next.keyIDs) {
                                    deregisterIn.keyID = str2;
                                    authenticator.deregister(deregisterIn);
                                }
                            }
                        }
                    }
                } catch (AsmException e) {
                    Logger.m15891e(TAG, "Deregistration failed", e);
                }
            } else if (authenticator.mAuthnrID.endsWith(deregisterAuthenticator.aaid)) {
                authenticator.deregister(deregisterIn);
                return;
            } else {
                continue;
            }
            Logger.m15891e(TAG, "Deregistration failed", e);
        }
    }

    public final Authenticator getAuthenticator(String str) throws AsmException {
        Authenticator authenticator = this.authnrsMap.get(str);
        if (authenticator != null) {
            return authenticator;
        }
        throw new AsmException(AsmError.BAD_TOKEN, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ASMCommunicationResponse implements ICommunicationClientResponse {
        @Override // com.gmrz.android.client.commlib.ICommunicationClientResponse
        public void onError(String str) {
        }

        @Override // com.gmrz.android.client.commlib.ICommunicationClientResponse
        public void onRemoveRequest() {
        }

        ASMCommunicationResponse() {
        }

        @Override // com.gmrz.android.client.commlib.ICommunicationClientResponse
        public void onResponse(String str, Object obj) {
            Logger.m15889i(AuthenticatorManager.TAG, "ASMCommunication Response Received");
            AuthenticatorManager.mInstance.mCommClientResponse = obj;
        }
    }

    public static ASMResponse executeAsmRequest(Object obj, String str, ASMRequest.RequestType requestType, short s, String str2) throws AsmException {
        Logger.startTimer(TAG, "executeAsmRequest");
        try {
            try {
                try {
                    Gson create = JsonObjectAdapter.GsonBuilder().create();
                    ASMRequest aSMRequest = new ASMRequest();
                    aSMRequest.requestType = requestType;
                    if (requestType != ASMRequest.RequestType.GetInfo) {
                        aSMRequest.authenticatorIndex = Short.valueOf(s);
                    }
                    aSMRequest.asmVersion = new Version(1, 0);
                    if (obj != null) {
                        aSMRequest.args = (JsonObject) create.toJsonTree(obj);
                    }
                    if (str != null) {
                        Logger.m15895d(TAG, "executeAsmRequest additionData:" + str);
                        List<AdditionData> parseAdditionData = AdditionHelper.parseAdditionData(str);
                        if (parseAdditionData != null) {
                            aSMRequest.exts = AdditionHelper.convert2Extensions(parseAdditionData);
                        }
                    }
                    String json = !(create instanceof Gson) ? create.toJson(aSMRequest) : NBSGsonInstrumentation.toJson(create, aSMRequest);
                    Logger.m15895d(TAG + "-###", "ASM Request:\n" + json);
                    ASMCommunicationClientRequest aSMCommunicationClientRequest = new ASMCommunicationClientRequest();
                    aSMCommunicationClientRequest.sRequest = json;
                    aSMCommunicationClientRequest.callerActivityContext = mInstance.mCallerActivity;
                    ASMCommunicationClientProxy aSMCommunicationClientProxy = mInstance.mCommunicationLibProxy;
                    mInstance.mCommClientResponse = null;
                    mInstance.mCommClientError = null;
                    aSMCommunicationClientProxy.waitForResponse(aSMCommunicationClientProxy.sendRequest(str2, "", aSMCommunicationClientRequest, new ASMCommunicationResponse()));
                    if (mInstance.mCommClientError != null) {
                        throw new AsmException(AsmError.FAILURE, "Commlib ASM SendRequest failed: " + mInstance.mCommClientError);
                    } else if (mInstance.mCommClientResponse == null) {
                        throw new AsmException(AsmError.FAILURE, "Commlib ASM SendRequest failed to receive response");
                    } else {
                        String str3 = (String) mInstance.mCommClientResponse;
                        Logger.m15895d(TAG + "-###", "ASM Response:\n" + str3);
                        ASMResponse aSMResponse = (ASMResponse) (!(create instanceof Gson) ? create.fromJson(str3, (Class<Object>) ASMResponse.class) : NBSGsonInstrumentation.fromJson(create, str3, (Class<Object>) ASMResponse.class));
                        if (aSMResponse.statusCode != 0) {
                            Logger.m15892e(TAG, "executeAsmRequest failed with error code " + Integer.valueOf(aSMResponse.statusCode).toString());
                            if (aSMResponse.statusCode == 3) {
                                throw new AsmException(AsmError.CANCELED);
                            }
                            if (aSMResponse.statusCode == 2) {
                                throw new AsmException(AsmError.NO_MATCH);
                            }
                            if (aSMResponse.statusCode == 9) {
                                throw new AsmException(AsmError.KEY_DISAPPEARED_PERMANENTLY);
                            }
                            if (aSMResponse.statusCode == 14) {
                                throw new AsmException(AsmError.BIOMETRIC_USER_PREFERRED_IRIS);
                            }
                            if (aSMResponse.statusCode == 18) {
                                throw new AsmException(AsmError.GM_NEED_REGISTER);
                            }
                            throw new AsmException(AsmError.FAILURE, "Failed to execute ASM request");
                        }
                        return aSMResponse;
                    }
                } catch (AsmException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw new AsmException(AsmError.FAILURE, "Failed to execute ASM request", e2);
            }
        } finally {
            Logger.endTimer(TAG, "executeAsmRequest");
        }
    }
}
