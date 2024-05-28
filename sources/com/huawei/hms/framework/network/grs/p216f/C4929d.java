package com.huawei.hms.framework.network.grs.p216f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.local.model.C4959a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.framework.network.grs.f.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4929d extends AbstractC4926a {
    public C4929d(Context context, String str, boolean z) {
        this.f11238e = z;
        if (m14982a(TextUtils.isEmpty(str) ? "grs_app_global_route_config.json" : str, context) == 0) {
            this.f11237d = true;
        }
    }

    public C4929d(boolean z, boolean z2) {
        this.f11238e = z2;
        this.f11237d = z;
    }

    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: a */
    public int mo14959a(String str) {
        this.f11234a = new C4959a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONArray("applications").getJSONObject(0);
            this.f11234a.m14842b(jSONObject.getString("name"));
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            if (jSONArray != null && jSONArray.length() != 0) {
                if (jSONObject.has("customservices")) {
                    m14977b(jSONObject.getJSONArray("customservices"));
                }
                return 0;
            }
            return -1;
        } catch (JSONException e) {
            Logger.m15043w("LocalManagerV2", "parse appbean failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0035 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0036 A[Catch: JSONException -> 0x00b7, TryCatch #0 {JSONException -> 0x00b7, blocks: (B:3:0x000b, B:6:0x001b, B:13:0x0036, B:16:0x003d, B:18:0x0043, B:21:0x0071, B:26:0x0089, B:28:0x0090, B:32:0x0098, B:34:0x009e, B:35:0x00aa, B:22:0x0076, B:25:0x0081, B:7:0x0020, B:10:0x002b), top: B:42:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009e A[Catch: JSONException -> 0x00b7, LOOP:1: B:32:0x0098->B:34:0x009e, LOOP_END, TryCatch #0 {JSONException -> 0x00b7, blocks: (B:3:0x000b, B:6:0x001b, B:13:0x0036, B:16:0x003d, B:18:0x0043, B:21:0x0071, B:26:0x0089, B:28:0x0090, B:32:0x0098, B:34:0x009e, B:35:0x00aa, B:22:0x0076, B:25:0x0081, B:7:0x0020, B:10:0x002b), top: B:42:0x000b }] */
    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo14958b(java.lang.String r11) {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            r10.f11235b = r0
            r0 = -1
            r2 = 0
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> Lb7
            r3.<init>(r11)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r11 = "countryOrAreaGroups"
            boolean r11 = r3.has(r11)     // Catch: org.json.JSONException -> Lb7
            r4 = 0
            if (r11 == 0) goto L20
            java.lang.String r11 = "countryOrAreaGroups"
        L1b:
            org.json.JSONArray r11 = r3.getJSONArray(r11)     // Catch: org.json.JSONException -> Lb7
            goto L33
        L20:
            java.lang.String r11 = "countryGroups"
            boolean r11 = r3.has(r11)     // Catch: org.json.JSONException -> Lb7
            if (r11 == 0) goto L2b
            java.lang.String r11 = "countryGroups"
            goto L1b
        L2b:
            java.lang.String r11 = "LocalManagerV2"
            java.lang.String r3 = "maybe local config json is wrong because the default countryOrAreaGroups isn't config."
            com.huawei.hms.framework.common.Logger.m15052e(r11, r3)     // Catch: org.json.JSONException -> Lb7
            r11 = r4
        L33:
            if (r11 != 0) goto L36
            return r0
        L36:
            int r3 = r11.length()     // Catch: org.json.JSONException -> Lb7
            if (r3 == 0) goto Lb6
            r3 = r2
        L3d:
            int r5 = r11.length()     // Catch: org.json.JSONException -> Lb7
            if (r3 >= r5) goto Lb6
            org.json.JSONObject r5 = r11.getJSONObject(r3)     // Catch: org.json.JSONException -> Lb7
            com.huawei.hms.framework.network.grs.local.model.b r6 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> Lb7
            r6.<init>()     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "id"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.m14837b(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "name"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.m14836c(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "description"
            java.lang.String r7 = r5.getString(r7)     // Catch: org.json.JSONException -> Lb7
            r6.m14840a(r7)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r7 = "countriesOrAreas"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb7
            if (r7 == 0) goto L76
            java.lang.String r7 = "countriesOrAreas"
        L71:
            org.json.JSONArray r5 = r5.getJSONArray(r7)     // Catch: org.json.JSONException -> Lb7
            goto L89
        L76:
            java.lang.String r7 = "countries"
            boolean r7 = r5.has(r7)     // Catch: org.json.JSONException -> Lb7
            if (r7 == 0) goto L81
            java.lang.String r7 = "countries"
            goto L71
        L81:
            java.lang.String r5 = "LocalManagerV2"
            java.lang.String r7 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.m15045w(r5, r7)     // Catch: org.json.JSONException -> Lb7
            r5 = r4
        L89:
            java.util.HashSet r7 = new java.util.HashSet     // Catch: org.json.JSONException -> Lb7
            r7.<init>(r1)     // Catch: org.json.JSONException -> Lb7
            if (r5 == 0) goto Lb5
            int r8 = r5.length()     // Catch: org.json.JSONException -> Lb7
            if (r8 != 0) goto L97
            goto Lb5
        L97:
            r8 = r2
        L98:
            int r9 = r5.length()     // Catch: org.json.JSONException -> Lb7
            if (r8 >= r9) goto Laa
            java.lang.Object r9 = r5.get(r8)     // Catch: org.json.JSONException -> Lb7
            java.lang.String r9 = (java.lang.String) r9     // Catch: org.json.JSONException -> Lb7
            r7.add(r9)     // Catch: org.json.JSONException -> Lb7
            int r8 = r8 + 1
            goto L98
        Laa:
            r6.m14839a(r7)     // Catch: org.json.JSONException -> Lb7
            java.util.List<com.huawei.hms.framework.network.grs.local.model.b> r5 = r10.f11235b     // Catch: org.json.JSONException -> Lb7
            r5.add(r6)     // Catch: org.json.JSONException -> Lb7
            int r3 = r3 + 1
            goto L3d
        Lb5:
            return r0
        Lb6:
            return r2
        Lb7:
            r11 = move-exception
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r11 = r11.getMessage()
            java.lang.String r11 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r11)
            r1[r2] = r11
            java.lang.String r11 = "LocalManagerV2"
            java.lang.String r2 = "parse countrygroup failed maybe json style is wrong. %s"
            com.huawei.hms.framework.common.Logger.m15043w(r11, r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.C4929d.mo14958b(java.lang.String):int");
    }

    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: e */
    public int mo14957e(String str) {
        return m14973d(str);
    }
}
