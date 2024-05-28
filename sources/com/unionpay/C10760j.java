package com.unionpay;

/* renamed from: com.unionpay.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10760j implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20689a;

    C10760j(UPPayWapActivity uPPayWapActivity) {
        this.f20689a = uPPayWapActivity;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0073 A[Catch: Exception -> 0x0080, TRY_LEAVE, TryCatch #0 {Exception -> 0x0080, blocks: (B:3:0x0001, B:14:0x0032, B:16:0x0073, B:12:0x0024), top: B:22:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void mo5871a(java.lang.String r6, com.unionpay.InterfaceC10741ab r7) {
        /*
            r5 = this;
            r0 = 0
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L80
            r1.<init>(r6)     // Catch: java.lang.Exception -> L80
            java.lang.String r6 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = "url"
            java.lang.Object r3 = r1.get(r3)     // Catch: java.lang.Exception -> L1f
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L1f
            java.lang.String r6 = "title"
            java.lang.Object r6 = r1.get(r6)     // Catch: java.lang.Exception -> L1d
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.Exception -> L1d
            goto L32
        L1d:
            r6 = move-exception
            goto L22
        L1f:
            r1 = move-exception
            r3 = r6
            r6 = r1
        L22:
            if (r7 == 0) goto L31
            java.lang.String r1 = "1"
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Exception -> L80
            java.lang.String r6 = com.unionpay.UPPayWapActivity.m5973a(r1, r6, r0)     // Catch: java.lang.Exception -> L80
            r7.mo5826a(r6)     // Catch: java.lang.Exception -> L80
        L31:
            r6 = r2
        L32:
            android.os.Bundle r1 = new android.os.Bundle     // Catch: java.lang.Exception -> L80
            r1.<init>()     // Catch: java.lang.Exception -> L80
            java.lang.String r2 = "waptype"
            java.lang.String r4 = "new_page"
            r1.putString(r2, r4)     // Catch: java.lang.Exception -> L80
            java.lang.String r2 = "magic_data"
            java.lang.String r4 = "949A1CC"
            r1.putString(r2, r4)     // Catch: java.lang.Exception -> L80
            java.lang.String r2 = "wapurl"
            r1.putString(r2, r3)     // Catch: java.lang.Exception -> L80
            java.lang.String r2 = "waptitle"
            r1.putString(r2, r6)     // Catch: java.lang.Exception -> L80
            java.lang.String r6 = "actionType"
            com.unionpay.UPPayWapActivity r2 = r5.f20689a     // Catch: java.lang.Exception -> L80
            java.lang.String r2 = com.unionpay.UPPayWapActivity.m5968c(r2)     // Catch: java.lang.Exception -> L80
            r1.putString(r6, r2)     // Catch: java.lang.Exception -> L80
            android.content.Intent r6 = new android.content.Intent     // Catch: java.lang.Exception -> L80
            r6.<init>()     // Catch: java.lang.Exception -> L80
            r6.putExtras(r1)     // Catch: java.lang.Exception -> L80
            com.unionpay.UPPayWapActivity r1 = r5.f20689a     // Catch: java.lang.Exception -> L80
            java.lang.Class<com.unionpay.UPPayWapActivity> r2 = com.unionpay.UPPayWapActivity.class
            r6.setClass(r1, r2)     // Catch: java.lang.Exception -> L80
            com.unionpay.UPPayWapActivity r1 = r5.f20689a     // Catch: java.lang.Exception -> L80
            r1.startActivity(r6)     // Catch: java.lang.Exception -> L80
            if (r7 == 0) goto L7f
            java.lang.String r6 = "0"
            java.lang.String r1 = "success"
            java.lang.String r6 = com.unionpay.UPPayWapActivity.m5973a(r6, r1, r0)     // Catch: java.lang.Exception -> L80
            r7.mo5826a(r6)     // Catch: java.lang.Exception -> L80
        L7f:
            return
        L80:
            r6 = move-exception
            if (r7 == 0) goto L90
            java.lang.String r1 = "1"
            java.lang.String r6 = r6.getMessage()
            java.lang.String r6 = com.unionpay.UPPayWapActivity.m5973a(r1, r6, r0)
            r7.mo5826a(r6)
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.C10760j.mo5871a(java.lang.String, com.unionpay.ab):void");
    }
}
