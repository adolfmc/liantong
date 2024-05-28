package com.bytedance.applog;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.w */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3716w extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8872e;

    /* renamed from: f */
    public final C3726x f8873f;

    public C3716w(Context context, C3726x c3726x) {
        super(false, false);
        this.f8872e = context;
        this.f8873f = c3726x;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        jSONObject.put("sdk_version", 5030090);
        jSONObject.put("sdk_version_code", C3704u2.f8846c);
        jSONObject.put("sdk_version_name", "5.3.0");
        jSONObject.put("channel", this.f8873f.f8897b.getChannel());
        jSONObject.put("not_request_sender", this.f8873f.f8897b.getNotReuqestSender() ? 1 : 0);
        C3735y.m17019a(jSONObject, "aid", this.f8873f.f8897b.getAid());
        C3735y.m17019a(jSONObject, "release_build", this.f8873f.f8897b.getReleaseBuild());
        C3735y.m17019a(jSONObject, "user_agent", this.f8873f.f8900e.getString("user_agent", null));
        C3735y.m17019a(jSONObject, "ab_sdk_version", this.f8873f.f8898c.getString("ab_sdk_version", ""));
        String googleAid = this.f8873f.f8897b.getGoogleAid();
        if (TextUtils.isEmpty(googleAid)) {
            googleAid = C3644n2.m17195a(this.f8872e, this.f8873f);
        }
        C3735y.m17019a(jSONObject, "google_aid", googleAid);
        String language = this.f8873f.f8897b.getLanguage();
        if (TextUtils.isEmpty(language)) {
            language = this.f8873f.f8900e.getString("app_language", null);
        }
        C3735y.m17019a(jSONObject, "app_language", language);
        String region = this.f8873f.f8897b.getRegion();
        if (TextUtils.isEmpty(region)) {
            region = this.f8873f.f8900e.getString("app_region", null);
        }
        C3735y.m17019a(jSONObject, "app_region", region);
        String string = this.f8873f.f8898c.getString("app_track", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                jSONObject.put("app_track", new JSONObject(string));
            } catch (Throwable th) {
                C3704u2.m17108a("U SHALL NOT PASS!", th);
            }
        }
        String string2 = this.f8873f.f8898c.getString("header_custom_info", null);
        if (string2 != null && string2.length() > 0) {
            try {
                JSONObject jSONObject2 = new JSONObject(string2);
                jSONObject2.remove("_debug_flag");
                jSONObject.put("custom", jSONObject2);
            } catch (Throwable th2) {
                C3704u2.m17108a("U SHALL NOT PASS!", th2);
            }
        }
        C3735y.m17019a(jSONObject, "user_unique_id", this.f8873f.f8898c.getString("user_unique_id", null));
        return true;
    }
}
