package com.gmrz.asm.p198fp.descriptor;

import android.content.Context;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IMatcher;
import com.gmrz.asm.p198fp.akselector.FpGmAttestationAkSelector;
import com.gmrz.asm.p198fp.authui.FpMatcher;
import com.gmrz.fpasm.C4439R;
import com.utils.AAID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.descriptor.FpGmAttestationAuthenticatorDescriptor */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpGmAttestationAuthenticatorDescriptor implements IAuthenticatorDescriptor {
    public static final String AUTHENTICATOR_LABEL = "keystore_GM_keystore_attestation_label";
    private final Map<String, IAuthenticatorDescriptor.AAIDInfo> AAIDMap = new HashMap(1);
    private final Context mContext;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.gmrz.asm.fp.descriptor.FpGmAttestationAuthenticatorDescriptor$UAFDescriptor */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class UAFDescriptor implements IAuthenticatorDescriptor.IUAFDescriptor {
        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public long getAttachmentHint() {
            return 1L;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public short getTcDisplay() {
            return (short) 1;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public String getTcDisplayContentType() {
            return "text/plain";
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public long getUserVerification() {
            return 2L;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public boolean isRoamingAuthenticator() {
            return false;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IUAFDescriptor
        public boolean isSecondFactorOnly() {
            return false;
        }
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public byte getMatcherVersion() {
        return (byte) 0;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public boolean hasSettings() {
        return false;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public boolean isAKManagedMatcher() {
        return true;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public boolean isTransactionShownByAuthUI() {
        return true;
    }

    public FpGmAttestationAuthenticatorDescriptor(Context context) {
        this.mContext = context;
        IAuthenticatorDescriptor.AAIDInfo aAIDInfo = new IAuthenticatorDescriptor.AAIDInfo();
        aAIDInfo.aaid = AAID.FINGER_KA_GM.name;
        aAIDInfo.label = "keystore_GM_keystore_attestation_label";
        aAIDInfo.certificateChain = null;
        aAIDInfo.autoConfigure = true;
        this.AAIDMap.put(aAIDInfo.label, aAIDInfo);
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public Map<String, IAuthenticatorDescriptor.AAIDInfo> getAAIDInfo() {
        return this.AAIDMap;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public Class<? extends IAKSelector> getAuthenticatorSelectorClass() {
        return FpGmAttestationAkSelector.class;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public int getDescription() {
        return C4439R.string.nativefps_description;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public int getIcon() {
        return C4439R.C4441drawable.icon_fingerprint;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public Class<? extends IMatcher> getMatcherClass() {
        return FpMatcher.class;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public IAuthenticatorDescriptor.IOSTPDescriptor getOSTPDescriptor() {
        return new OSTPDescriptor();
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public int getTitle() {
        return C4439R.string.fp_title_sm2;
    }

    @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor
    public IAuthenticatorDescriptor.IUAFDescriptor getUAFDescriptor() {
        return new UAFDescriptor();
    }

    /* renamed from: com.gmrz.asm.fp.descriptor.FpGmAttestationAuthenticatorDescriptor$OSTPDescriptor */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class OSTPDescriptor implements IAuthenticatorDescriptor.IOSTPDescriptor {
        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public String getAuthDBOldFileName() {
            return null;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public String getAuthFactor() {
            return "PIN";
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public String getAuthInstallMethod() {
            return "Embedded";
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public String getAuthSecType() {
            return "Software";
        }

        public OSTPDescriptor() {
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public ArrayList<String> getAuthSchemes() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("AUTH_JWS");
            return arrayList;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public ArrayList<String> getAuthSuites() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("ES256");
            arrayList.add("RS256");
            return arrayList;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public ArrayList<String> getRegModes() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Asym1pass");
            return arrayList;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public ArrayList<String> getRegSchemes() {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("REG_JWS");
            return arrayList;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public Boolean isSecureDisplaySupported() {
            return false;
        }

        @Override // com.android.client.asm.sdk.IAuthenticatorDescriptor.IOSTPDescriptor
        public String getVersion() {
            try {
                return FpGmAttestationAuthenticatorDescriptor.this.mContext.getPackageManager().getPackageInfo(FpGmAttestationAuthenticatorDescriptor.this.mContext.getPackageName(), 0).versionName;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
