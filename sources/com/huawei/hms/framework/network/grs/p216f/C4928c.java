package com.huawei.hms.framework.network.grs.p216f;

import android.content.Context;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.local.model.C4959a;
import com.huawei.hms.framework.network.grs.local.model.C4960b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.framework.network.grs.f.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4928c extends AbstractC4926a {
    public C4928c(Context context, boolean z) {
        this.f11238e = z;
        if (m14982a("grs_sdk_global_route_config.json", context) == 0) {
            this.f11237d = true;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006e A[Catch: JSONException -> 0x0088, LOOP:1: B:20:0x0068->B:22:0x006e, LOOP_END, TryCatch #0 {JSONException -> 0x0088, blocks: (B:3:0x0001, B:4:0x000c, B:6:0x0012, B:9:0x0041, B:14:0x0059, B:16:0x0060, B:20:0x0068, B:22:0x006e, B:23:0x007a, B:24:0x0081, B:10:0x0047, B:13:0x0052), top: B:30:0x0001 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.huawei.hms.framework.network.grs.local.model.C4960b> m14960a(org.json.JSONObject r10) {
        /*
            r9 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: org.json.JSONException -> L88
            r2 = 16
            r1.<init>(r2)     // Catch: org.json.JSONException -> L88
            java.util.Iterator r3 = r10.keys()     // Catch: org.json.JSONException -> L88
        Lc:
            boolean r4 = r3.hasNext()     // Catch: org.json.JSONException -> L88
            if (r4 == 0) goto L87
            java.lang.Object r4 = r3.next()     // Catch: org.json.JSONException -> L88
            java.lang.String r4 = (java.lang.String) r4     // Catch: org.json.JSONException -> L88
            com.huawei.hms.framework.network.grs.local.model.b r5 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> L88
            r5.<init>()     // Catch: org.json.JSONException -> L88
            r5.m14837b(r4)     // Catch: org.json.JSONException -> L88
            org.json.JSONObject r4 = r10.getJSONObject(r4)     // Catch: org.json.JSONException -> L88
            java.lang.String r6 = "name"
            java.lang.String r6 = r4.getString(r6)     // Catch: org.json.JSONException -> L88
            r5.m14836c(r6)     // Catch: org.json.JSONException -> L88
            java.lang.String r6 = "description"
            java.lang.String r6 = r4.getString(r6)     // Catch: org.json.JSONException -> L88
            r5.m14840a(r6)     // Catch: org.json.JSONException -> L88
            r6 = 0
            java.lang.String r7 = "countriesOrAreas"
            boolean r7 = r4.has(r7)     // Catch: org.json.JSONException -> L88
            if (r7 == 0) goto L47
            java.lang.String r6 = "countriesOrAreas"
        L41:
            org.json.JSONArray r4 = r4.getJSONArray(r6)     // Catch: org.json.JSONException -> L88
            r6 = r4
            goto L59
        L47:
            java.lang.String r7 = "countries"
            boolean r7 = r4.has(r7)     // Catch: org.json.JSONException -> L88
            if (r7 == 0) goto L52
            java.lang.String r6 = "countries"
            goto L41
        L52:
            java.lang.String r4 = "LocalManagerV1"
            java.lang.String r7 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.m15045w(r4, r7)     // Catch: org.json.JSONException -> L88
        L59:
            java.util.HashSet r4 = new java.util.HashSet     // Catch: org.json.JSONException -> L88
            r4.<init>(r2)     // Catch: org.json.JSONException -> L88
            if (r6 == 0) goto L81
            int r7 = r6.length()     // Catch: org.json.JSONException -> L88
            if (r7 != 0) goto L67
            goto L81
        L67:
            r7 = r0
        L68:
            int r8 = r6.length()     // Catch: org.json.JSONException -> L88
            if (r7 >= r8) goto L7a
            java.lang.Object r8 = r6.get(r7)     // Catch: org.json.JSONException -> L88
            java.lang.String r8 = (java.lang.String) r8     // Catch: org.json.JSONException -> L88
            r4.add(r8)     // Catch: org.json.JSONException -> L88
            int r7 = r7 + 1
            goto L68
        L7a:
            r5.m14839a(r4)     // Catch: org.json.JSONException -> L88
            r1.add(r5)     // Catch: org.json.JSONException -> L88
            goto Lc
        L81:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: org.json.JSONException -> L88
            r10.<init>()     // Catch: org.json.JSONException -> L88
            return r10
        L87:
            return r1
        L88:
            r10 = move-exception
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r10 = r10.getMessage()
            java.lang.String r10 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r10)
            r1[r0] = r10
            java.lang.String r10 = "LocalManagerV1"
            java.lang.String r0 = "parse countryGroups failed maybe json style is wrong. %s"
            com.huawei.hms.framework.common.Logger.m15043w(r10, r0, r1)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.C4928c.m14960a(org.json.JSONObject):java.util.List");
    }

    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: a */
    public int mo14959a(String str) {
        this.f11234a = new C4959a();
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("application");
            String string = jSONObject.getString("name");
            long j = jSONObject.getLong("cacheControl");
            JSONArray jSONArray = jSONObject.getJSONArray("services");
            this.f11234a.m14842b(string);
            this.f11234a.m14846a(j);
            if (jSONArray != null) {
                if (jSONArray.length() != 0) {
                    return 0;
                }
            }
            return -1;
        } catch (JSONException e) {
            Logger.m15043w("LocalManagerV1", "parse appbean failed maybe json style is wrong. %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    /* renamed from: a */
    public List<C4960b> m14961a(JSONArray jSONArray, JSONObject jSONObject) {
        return (jSONObject == null || jSONObject.length() == 0) ? new ArrayList() : m14960a(jSONObject);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: JSONException -> 0x0045, TryCatch #0 {JSONException -> 0x0045, blocks: (B:3:0x000b, B:6:0x001b, B:13:0x0035, B:15:0x003b, B:7:0x0020, B:10:0x002b), top: B:20:0x000b }] */
    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo14958b(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            r4.f11235b = r0
            r0 = -1
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L45
            r2.<init>(r5)     // Catch: org.json.JSONException -> L45
            r5 = 0
            java.lang.String r3 = "countryOrAreaGroups"
            boolean r3 = r2.has(r3)     // Catch: org.json.JSONException -> L45
            if (r3 == 0) goto L20
            java.lang.String r5 = "countryOrAreaGroups"
        L1b:
            org.json.JSONObject r5 = r2.getJSONObject(r5)     // Catch: org.json.JSONException -> L45
            goto L32
        L20:
            java.lang.String r3 = "countryGroups"
            boolean r3 = r2.has(r3)     // Catch: org.json.JSONException -> L45
            if (r3 == 0) goto L2b
            java.lang.String r5 = "countryGroups"
            goto L1b
        L2b:
            java.lang.String r2 = "LocalManagerV1"
            java.lang.String r3 = "maybe local config json is wrong because the default countryOrAreaGroups isn't config."
            com.huawei.hms.framework.common.Logger.m15052e(r2, r3)     // Catch: org.json.JSONException -> L45
        L32:
            if (r5 != 0) goto L35
            return r0
        L35:
            int r2 = r5.length()     // Catch: org.json.JSONException -> L45
            if (r2 == 0) goto L44
            java.util.List<com.huawei.hms.framework.network.grs.local.model.b> r2 = r4.f11235b     // Catch: org.json.JSONException -> L45
            java.util.List r5 = r4.m14960a(r5)     // Catch: org.json.JSONException -> L45
            r2.addAll(r5)     // Catch: org.json.JSONException -> L45
        L44:
            return r1
        L45:
            r5 = move-exception
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r5 = r5.getMessage()
            java.lang.String r5 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r5)
            r2[r1] = r5
            java.lang.String r5 = "LocalManagerV1"
            java.lang.String r1 = "parse countrygroup failed maybe json style is wrong. %s"
            com.huawei.hms.framework.common.Logger.m15043w(r5, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.C4928c.mo14958b(java.lang.String):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0097 A[Catch: JSONException -> 0x00ff, TryCatch #0 {JSONException -> 0x00ff, blocks: (B:3:0x0001, B:4:0x0010, B:6:0x0016, B:8:0x002c, B:10:0x0035, B:11:0x0049, B:13:0x004f, B:15:0x005c, B:18:0x0066, B:23:0x007d, B:24:0x0091, B:26:0x0097, B:28:0x00a7, B:30:0x00ad, B:31:0x00b5, B:19:0x006b, B:22:0x0076, B:32:0x00c2, B:35:0x00cd, B:39:0x00dd, B:41:0x00e9, B:43:0x00f0, B:44:0x00f7, B:36:0x00d2, B:40:0x00e2), top: B:50:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0 A[Catch: JSONException -> 0x00ff, TryCatch #0 {JSONException -> 0x00ff, blocks: (B:3:0x0001, B:4:0x0010, B:6:0x0016, B:8:0x002c, B:10:0x0035, B:11:0x0049, B:13:0x004f, B:15:0x005c, B:18:0x0066, B:23:0x007d, B:24:0x0091, B:26:0x0097, B:28:0x00a7, B:30:0x00ad, B:31:0x00b5, B:19:0x006b, B:22:0x0076, B:32:0x00c2, B:35:0x00cd, B:39:0x00dd, B:41:0x00e9, B:43:0x00f0, B:44:0x00f7, B:36:0x00d2, B:40:0x00e2), top: B:50:0x0001 }] */
    @Override // com.huawei.hms.framework.network.grs.p216f.AbstractC4926a
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo14957e(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.C4928c.mo14957e(java.lang.String):int");
    }
}
