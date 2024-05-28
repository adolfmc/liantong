package com.gmrz.android.uaf.framework.service;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import com.fido.android.framework.service.IAuthenticatorUIAdapter;
import com.fido.android.framework.service.Mfac;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.IUafAsmBinder;
import com.gmrz.android.client.asm.api.uaf.json.ASMRequest;
import com.gmrz.android.client.asm.api.uaf.json.ASMResponse;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticateIn;
import com.gmrz.android.client.asm.api.uaf.json.AuthenticatorInfo;
import com.gmrz.android.client.asm.api.uaf.json.DeregisterIn;
import com.gmrz.android.client.asm.api.uaf.json.GetInfoOut;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.asm.api.uaf.json.RegisterIn;
import com.gmrz.android.client.utils.JsonObjectAdapter;
import com.gmrz.android.client.utils.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Authenticator implements IAuthenticatorUIAdapter {
    private static final String TAG = "Authenticator";
    private short attestationType;
    private IUafAsmBinder mAsmBinder;
    private String mAsmDestination;
    final String mAuthnrID;
    final AuthenticatorInfo mAuthnrInfo;
    private GetRegistrationsOut mRegs;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    enum StateEnum {
        Activated,
        Setup,
        Enabled,
        RegCommitted
    }

    public short getAttestationType() {
        return this.attestationType;
    }

    public void setAttestationType(short s) {
        this.attestationType = s;
    }

    public Authenticator(AuthenticatorInfo authenticatorInfo, String str, IUafAsmBinder iUafAsmBinder) {
        this.mAsmDestination = null;
        this.mAuthnrID = str;
        this.mAuthnrInfo = authenticatorInfo;
        if (iUafAsmBinder != null) {
            this.mAsmBinder = iUafAsmBinder;
        }
    }

    public Authenticator(AuthenticatorInfo authenticatorInfo, String str, String str2) {
        this.mAsmDestination = null;
        this.mAuthnrID = str;
        this.mAuthnrInfo = authenticatorInfo;
        this.mAsmBinder = null;
        if (str2 != null) {
            this.mAsmDestination = str2;
        }
    }

    private String getID() {
        return this.mAuthnrID;
    }

    public IUafAsmBinder getAsmBinder() {
        return this.mAsmBinder;
    }

    public String getAsmDestination() {
        return this.mAsmDestination;
    }

    public AuthenticatorInfo getAuthnrInfo() {
        return this.mAuthnrInfo;
    }

    public GetInfoOut getinfo() throws AsmException {
        ASMResponse executeAsmRequest = AuthenticatorManager.executeAsmRequest(null, null, ASMRequest.RequestType.GetInfo, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
        Gson create = JsonObjectAdapter.GsonBuilder().create();
        JsonObject jsonObject = executeAsmRequest.responseData;
        return (GetInfoOut) (!(create instanceof Gson) ? create.fromJson((JsonElement) jsonObject, (Class<Object>) GetInfoOut.class) : NBSGsonInstrumentation.fromJson(create, (JsonElement) jsonObject, (Class<Object>) GetInfoOut.class));
    }

    public ASMResponse register(RegisterIn registerIn, String str) throws AsmException {
        this.mRegs = null;
        return AuthenticatorManager.executeAsmRequest(registerIn, str, ASMRequest.RequestType.Register, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
    }

    public ASMResponse authenticate(AuthenticateIn authenticateIn, String str) throws AsmException {
        return AuthenticatorManager.executeAsmRequest(authenticateIn, str, ASMRequest.RequestType.Authenticate, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
    }

    public void deregister(DeregisterIn deregisterIn) throws AsmException {
        this.mRegs = null;
        AuthenticatorManager.executeAsmRequest(deregisterIn, null, ASMRequest.RequestType.Deregister, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
    }

    public GetRegistrationsOut getRegistrations() throws AsmException {
        GetRegistrationsOut getRegistrationsOut = this.mRegs;
        if (getRegistrationsOut != null) {
            return getRegistrationsOut;
        }
        ASMResponse executeAsmRequest = AuthenticatorManager.executeAsmRequest(null, null, ASMRequest.RequestType.GetRegistrations, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
        Gson create = JsonObjectAdapter.GsonBuilder().create();
        JsonObject jsonObject = executeAsmRequest.responseData;
        this.mRegs = (GetRegistrationsOut) (!(create instanceof Gson) ? create.fromJson((JsonElement) jsonObject, (Class<Object>) GetRegistrationsOut.class) : NBSGsonInstrumentation.fromJson(create, (JsonElement) jsonObject, (Class<Object>) GetRegistrationsOut.class));
        return this.mRegs;
    }

    public boolean isRegistered(String str) {
        try {
            for (GetRegistrationsOut.AppRegistration appRegistration : getRegistrations().appRegs) {
                if (appRegistration.appID.equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (AsmException e) {
            Logger.m15891e(TAG, "isRegistered failed", e);
            return false;
        }
    }

    public boolean isKeyIDRegistered(String str) {
        try {
            GetRegistrationsOut registrations = getRegistrations();
            if (registrations != null) {
                for (GetRegistrationsOut.AppRegistration appRegistration : registrations.appRegs) {
                    if (appRegistration.keyIDs != null && appRegistration.keyIDs.size() > 0) {
                        for (String str2 : appRegistration.keyIDs) {
                            if (str2.equals(str)) {
                                return true;
                            }
                        }
                        continue;
                    }
                }
                return false;
            }
            return false;
        } catch (AsmException e) {
            Logger.m15891e(TAG, "Error while checking is keyID registered", e);
            return false;
        }
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public IAuthenticatorUIAdapter.GetUIOut getUI() {
        IAuthenticatorUIAdapter.GetUIOut getUIOut = new IAuthenticatorUIAdapter.GetUIOut();
        getUIOut.Title = this.mAuthnrInfo.title != null ? this.mAuthnrInfo.title : genAuthenticatorTitle();
        getUIOut.Icon = this.mAuthnrInfo.icon;
        getUIOut.Text = "You can set up " + getUIOut.Title + " on your Android device to sign in to websites that use gmrz authentication.It's secure and more convenient than signing in with your username and password.";
        getUIOut.hasSettings = this.mAuthnrInfo.hasSettings;
        return getUIOut;
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public String getId() {
        return getID();
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public boolean isTokenRegistered() {
        try {
            return !getRegistrations().appRegs.isEmpty();
        } catch (AsmException e) {
            Logger.m15891e(TAG, "Error while getting state", e);
            return false;
        }
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public void setEnabled(boolean z) {
        SharedPreferences.Editor edit = Mfac.Instance().getContext().getSharedPreferences("authnr_state", 0).edit();
        edit.putString(this.mAuthnrID + StateEnum.Enabled, z ? "yes" : "no");
        edit.apply();
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public boolean isEnabled() {
        SharedPreferences sharedPreferences = Mfac.Instance().getContext().getSharedPreferences("authnr_state", 0);
        return "yes".equalsIgnoreCase(sharedPreferences.getString(this.mAuthnrID + StateEnum.Enabled, "yes"));
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public Drawable getIcon() {
        return this.mAsmBinder.getIcon();
    }

    @Override // com.fido.android.framework.service.IAuthenticatorUIAdapter
    public void openSettings(String str) {
        String str2 = TAG;
        Logger.m15889i(str2, "Execute called with params: " + str);
        try {
            if ("manage".equals(str)) {
                AuthenticatorManager.executeAsmRequest(null, null, ASMRequest.RequestType.OpenSettings, this.mAuthnrInfo.authenticatorIndex, this.mAsmDestination);
                return;
            }
            if (!"clear".equals(str)) {
                return;
            }
            for (GetRegistrationsOut.AppRegistration appRegistration : getRegistrations().appRegs) {
                for (String str3 : appRegistration.keyIDs) {
                    DeregisterIn deregisterIn = new DeregisterIn();
                    deregisterIn.appID = appRegistration.appID;
                    deregisterIn.keyID = str3;
                    deregister(deregisterIn);
                }
            }
        } catch (Exception e) {
            Logger.m15891e(TAG, "Error while executing call", e);
        }
    }

    private String genAuthenticatorTitle() {
        StringBuilder sb = new StringBuilder();
        if ((this.mAuthnrInfo.userVerification & 1) != 0) {
            sb.append("User presence confirmation and ");
        }
        if ((this.mAuthnrInfo.userVerification & 2) != 0) {
            sb.append("Fingerprint and ");
        }
        if ((this.mAuthnrInfo.userVerification & 4) != 0) {
            sb.append("Passcode and ");
        }
        if ((this.mAuthnrInfo.userVerification & 8) != 0) {
            sb.append("Voiceprint and ");
        }
        if ((this.mAuthnrInfo.userVerification & 16) != 0) {
            sb.append("Face Recognition and ");
        }
        if ((this.mAuthnrInfo.userVerification & 32) != 0) {
            sb.append("Location sensor or measurement and ");
        }
        if ((this.mAuthnrInfo.userVerification & 64) != 0) {
            sb.append("Eye biometric and ");
        }
        if ((this.mAuthnrInfo.userVerification & 128) != 0) {
            sb.append("Drawn pattern and ");
        }
        if ((this.mAuthnrInfo.userVerification & 256) != 0) {
            sb.append("Full hand (palm-print, hand geometry or vein geometry) and ");
        }
        if ((this.mAuthnrInfo.userVerification & 512) != 0) {
            sb.append("No user interaction and ");
        }
        if (sb.length() == 0) {
            sb.append("Unknown type");
        } else {
            sb.delete(sb.length() - 5, sb.length());
        }
        sb.append(" Authenticator (");
        sb.append(this.mAuthnrInfo.aaid);
        sb.append(")");
        return sb.toString();
    }
}
