package com.p281qq.p282e.ads.nativ;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.nativ.NativeUnifiedADAppInfoImpl */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NativeUnifiedADAppInfoImpl implements NativeUnifiedADAppMiitInfo {

    /* renamed from: a */
    private final String f17850a;

    /* renamed from: b */
    private final String f17851b;

    /* renamed from: c */
    private final long f17852c;

    /* renamed from: d */
    private final String f17853d;

    /* renamed from: e */
    private final String f17854e;

    /* renamed from: f */
    private final String f17855f;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.nativ.NativeUnifiedADAppInfoImpl$Keys */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    interface Keys {
        public static final String APP_NAME = "app_name";
        public static final String AUTHOR_NAME = "author_name";
        public static final String PACKAGE_SIZE = "package_size";
        public static final String PERMISSION_URL = "permission_url";
        public static final String PRIVACY_AGREEMENT = "privacy_agreement";
        public static final String VERSION_NAME = "version_name";
    }

    public NativeUnifiedADAppInfoImpl(JSONObject jSONObject) {
        this.f17850a = jSONObject.optString("app_name");
        this.f17851b = jSONObject.optString("author_name");
        this.f17852c = jSONObject.optLong("package_size");
        this.f17853d = jSONObject.optString("permission_url");
        this.f17854e = jSONObject.optString("privacy_agreement");
        this.f17855f = jSONObject.optString("version_name");
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public String getAppName() {
        return this.f17850a;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public String getAuthorName() {
        return this.f17851b;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public long getPackageSizeBytes() {
        return this.f17852c;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public String getPermissionsUrl() {
        return this.f17853d;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public String getPrivacyAgreement() {
        return this.f17854e;
    }

    @Override // com.p281qq.p282e.ads.nativ.NativeUnifiedADAppMiitInfo
    public String getVersionName() {
        return this.f17855f;
    }
}
