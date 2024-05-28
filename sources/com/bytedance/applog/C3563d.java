package com.bytedance.applog;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

@NBSInstrumented
/* renamed from: com.bytedance.applog.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3563d extends AbstractC3579f {
    public C3563d(C3591h c3591h) {
        super(c3591h);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ae  */
    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean mo17162c() {
        /*
            r9 = this;
            com.bytedance.applog.h r0 = r9.f8439a
            com.bytedance.applog.x r1 = r0.f8465d
            com.bytedance.applog.y r0 = r0.f8469h
            org.json.JSONObject r2 = r0.m17017b()
            int r3 = r0.m17014c()
            r4 = 0
            if (r3 == 0) goto Led
            if (r2 == 0) goto Led
            long r2 = java.lang.System.currentTimeMillis()
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            org.json.JSONObject r6 = r0.m17017b()
            java.lang.String r7 = "header"
            r5.put(r7, r6)
            java.lang.String r6 = "magic_tag"
            java.lang.String r7 = "ss_app_log"
            r5.put(r6, r7)
            java.lang.String r6 = "_gen_time"
            r5.put(r6, r2)
            com.bytedance.applog.h r2 = r9.f8439a
            android.app.Application r2 = r2.f8464c
            org.json.JSONObject r3 = r0.m17017b()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            com.bytedance.applog.h r7 = r9.f8439a
            com.bytedance.applog.UriConfig r7 = r7.m17287c()
            java.lang.String r7 = r7.getAbUri()
            r6.<init>(r7)
            r7 = 1
            java.lang.String r2 = com.bytedance.applog.C3614k1.m17254a(r2, r3, r6, r7)
            java.lang.String[] r3 = com.bytedance.applog.C3607j1.f8525e
            java.lang.String r2 = com.bytedance.applog.C3607j1.m17262a(r2, r3)
            r3 = 0
            com.bytedance.applog.network.INetworkClient r6 = com.bytedance.applog.AppLog.getNetClient()     // Catch: java.lang.Exception -> L73
            boolean r8 = r5 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> L73
            if (r8 != 0) goto L62
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L73
            goto L68
        L62:
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch: java.lang.Exception -> L73
            java.lang.String r5 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r5)     // Catch: java.lang.Exception -> L73
        L68:
            byte[] r5 = com.bytedance.applog.C3710v0.m17082d(r5)     // Catch: java.lang.Exception -> L73
            java.lang.String r8 = "application/json; charset=utf-8"
            java.lang.String r2 = r6.post(r2, r5, r8)     // Catch: java.lang.Exception -> L73
            goto L78
        L73:
            r2 = move-exception
            r2.printStackTrace()
            r2 = r3
        L78:
            if (r2 == 0) goto L8b
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: org.json.JSONException -> L85
            r5.<init>(r2)     // Catch: org.json.JSONException -> L85
            com.bytedance.applog.C3607j1.m17260a(r5)     // Catch: org.json.JSONException -> L83
            goto L8c
        L83:
            r2 = move-exception
            goto L87
        L85:
            r2 = move-exception
            r5 = r3
        L87:
            r2.printStackTrace()
            goto L8c
        L8b:
            r5 = r3
        L8c:
            if (r5 == 0) goto La1
            java.lang.String r2 = "message"
            java.lang.String r6 = ""
            java.lang.String r2 = r5.optString(r2, r6)
            java.lang.String r6 = "success"
            boolean r2 = r6.equals(r2)
            if (r2 == 0) goto La1
            r2 = r7
            goto La2
        La1:
            r2 = r4
        La2:
            if (r2 == 0) goto Lab
            java.lang.String r2 = "data"
            org.json.JSONObject r2 = r5.optJSONObject(r2)
            goto Lac
        Lab:
            r2 = r3
        Lac:
            if (r2 == 0) goto Led
            org.json.JSONObject r1 = r1.m17055a()
            boolean r1 = com.bytedance.applog.C3712v2.m17072b(r1, r2)
            r1 = r1 ^ r7
            boolean r4 = com.bytedance.applog.C3704u2.f8845b
            if (r4 == 0) goto Lc9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getAbConfig "
            r4.append(r5)
            r4.append(r2)
            goto Ld6
        Lc9:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "getAbConfig "
            r4.append(r5)
            r4.append(r1)
        Ld6:
            java.lang.String r4 = r4.toString()
            com.bytedance.applog.C3704u2.m17108a(r4, r3)
            com.bytedance.applog.x r3 = r0.f8935c
            r3.m17051a(r2)
            r0.m17015b(r2)
            com.bytedance.applog.e2 r0 = com.bytedance.applog.C3577e2.m17309a()
            r0.onRemoteAbConfigGet(r1, r2)
            return r7
        Led:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3563d.mo17162c():boolean");
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: d */
    public String mo17161d() {
        return "abconfiger";
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: e */
    public long[] mo17160e() {
        return C3612k.f8531g;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: g */
    public boolean mo17159g() {
        return true;
    }

    @Override // com.bytedance.applog.AbstractC3579f
    /* renamed from: h */
    public long mo17158h() {
        long j = this.f8439a.f8465d.f8900e.getLong("abtest_fetch_interval", 0L);
        if (j < 600000) {
            return 600000L;
        }
        return j;
    }
}
