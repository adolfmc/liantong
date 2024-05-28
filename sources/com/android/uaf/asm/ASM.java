package com.android.uaf.asm;

import android.app.Activity;
import android.content.Context;
import com.android.client.asm.core.uaf.AuthenticatorCore;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.IUafAsmApi;
import com.gmrz.android.client.asm.api.uaf.json.ASMRequest;
import com.gmrz.android.client.asm.api.uaf.json.ASMResponse;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn;
import com.gmrz.android.client.asm.api.uaf.json.DeregisterIn;
import com.gmrz.android.client.asm.api.uaf.json.GetInfoOut;
import com.gmrz.android.client.asm.api.uaf.json.RegisterIn;
import com.gmrz.android.client.utils.JsonObjectAdapter;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ASM implements IUafAsmApi {
    private static final String TAG = "ASM";
    private final Context mContext;
    private final List<AuthenticatorCore> mAuthenticators = new ArrayList();
    private final Gson mGson = JsonObjectAdapter.GsonBuilder().create();

    public ASM(Context context) {
        this.mContext = context;
    }

    private AuthenticatorCore.Response getInfo() {
        GetInfoOut getInfoOut = new GetInfoOut();
        AuthenticatorCore.Response response = new AuthenticatorCore.Response();
        try {
            for (AuthenticatorCore authenticatorCore : this.mAuthenticators) {
                getInfoOut.Authenticators.add(authenticatorCore.getInfo());
            }
            response.data = getInfoOut;
            response.statusCode = 0;
        } catch (Exception e) {
            Logger.m15891e("ASM", "Error getting info with available authenticators", e);
            response.statusCode = 1;
        }
        return response;
    }

    private AuthenticatorCore getAuthenticator(long j) {
        for (AuthenticatorCore authenticatorCore : this.mAuthenticators) {
            if (authenticatorCore.getReferenceID() == j) {
                return authenticatorCore;
            }
        }
        Logger.m15883w("ASM", "Waring! Incorrect authenticatorIndex");
        return null;
    }

    public void addAuthenticator(AuthenticatorCore authenticatorCore) {
        this.mAuthenticators.add(authenticatorCore);
    }

    @Override // com.gmrz.android.client.asm.api.uaf.IUafAsmApi
    public String process(String str, Activity activity) {
        AuthenticatorCore.Response info;
        ASMResponse aSMResponse = new ASMResponse();
        try {
            Gson gson = this.mGson;
            ASMRequest aSMRequest = (ASMRequest) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) ASMRequest.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) ASMRequest.class));
            if (aSMRequest.asmVersion != null && !aSMRequest.asmVersion.checkVersion(1, 0)) {
                throw new AsmException(AsmError.NOT_SUPPORTED);
            }
            switch (aSMRequest.requestType) {
                case GetInfo:
                    info = getInfo();
                    break;
                case Register:
                    AuthenticatorCore authenticator = getAuthenticator(aSMRequest.authenticatorIndex.shortValue());
                    Gson gson2 = this.mGson;
                    JsonObject jsonObject = aSMRequest.args;
                    info = authenticator.register((RegisterIn) (!(gson2 instanceof Gson) ? gson2.fromJson((JsonElement) jsonObject, (Class<Object>) RegisterIn.class) : NBSGsonInstrumentation.fromJson(gson2, (JsonElement) jsonObject, (Class<Object>) RegisterIn.class)), aSMRequest.exts, activity);
                    break;
                case Authenticate:
                    AuthenticatorCore authenticator2 = getAuthenticator(aSMRequest.authenticatorIndex.shortValue());
                    Gson gson3 = this.mGson;
                    JsonObject jsonObject2 = aSMRequest.args;
                    info = authenticator2.authenticate((AuthenticateIn) (!(gson3 instanceof Gson) ? gson3.fromJson((JsonElement) jsonObject2, (Class<Object>) AuthenticateIn.class) : NBSGsonInstrumentation.fromJson(gson3, (JsonElement) jsonObject2, (Class<Object>) AuthenticateIn.class)), aSMRequest.exts, activity);
                    break;
                case Deregister:
                    AuthenticatorCore authenticator3 = getAuthenticator(aSMRequest.authenticatorIndex.shortValue());
                    Gson gson4 = this.mGson;
                    JsonObject jsonObject3 = aSMRequest.args;
                    info = authenticator3.deregister((DeregisterIn) (!(gson4 instanceof Gson) ? gson4.fromJson((JsonElement) jsonObject3, (Class<Object>) DeregisterIn.class) : NBSGsonInstrumentation.fromJson(gson4, (JsonElement) jsonObject3, (Class<Object>) DeregisterIn.class)), aSMRequest.exts);
                    break;
                case GetRegistrations:
                    info = getAuthenticator(aSMRequest.authenticatorIndex.shortValue()).getRegistrations();
                    break;
                case OpenSettings:
                    info = getAuthenticator(aSMRequest.authenticatorIndex.shortValue()).openSettings();
                    break;
                default:
                    throw new AsmException(AsmError.FAILURE, "Unknown command");
            }
            aSMResponse.statusCode = (short) info.statusCode;
            if (info.data != null) {
                aSMResponse.responseData = (JsonObject) this.mGson.toJsonTree(info.data);
            }
            if (info.exts != null) {
                aSMResponse.exts = info.exts;
            }
        } catch (Exception e) {
            Logger.m15891e("ASM", "ASM.Process exception.", e);
            aSMResponse.statusCode = (short) 1;
        }
        Gson gson5 = this.mGson;
        String json = !(gson5 instanceof Gson) ? gson5.toJson(aSMResponse) : NBSGsonInstrumentation.toJson(gson5, aSMResponse);
        Logger.m15895d("AUTHENTICATOR_JSON_MSG_RESPONSE", json);
        return json;
    }
}
