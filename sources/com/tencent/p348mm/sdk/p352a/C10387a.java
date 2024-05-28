package com.tencent.p348mm.sdk.p352a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.p348mm.sdk.p352a.p353a.C10391b;
import com.tencent.p348mm.sdk.p354b.C10393b;
import com.tencent.p348mm.sdk.p354b.C10398f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10387a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.a.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10388a {

        /* renamed from: b */
        public String f19993b;

        /* renamed from: c */
        public String f19994c;

        /* renamed from: d */
        public String f19995d;

        /* renamed from: e */
        public Bundle f19996e;
        public int flags = -1;
    }

    /* renamed from: a */
    public static boolean m6201a(Context context, C10388a c10388a) {
        if (context == null || c10388a == null) {
            C10393b.m6195a("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (C10398f.m6184c(c10388a.f19993b)) {
            C10393b.m6195a("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + c10388a.f19993b);
            return false;
        } else {
            if (C10398f.m6184c(c10388a.f19994c)) {
                c10388a.f19994c = c10388a.f19993b + ".wxapi.WXEntryActivity";
            }
            C10393b.m6191c("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + c10388a.f19993b + ", targetClassName = " + c10388a.f19994c);
            Intent intent = new Intent();
            intent.setClassName(c10388a.f19993b, c10388a.f19994c);
            if (c10388a.f19996e != null) {
                intent.putExtras(c10388a.f19996e);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 570490883);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", c10388a.f19995d);
            intent.putExtra("_mmessage_checksum", C10391b.m6199a(c10388a.f19995d, 570490883, packageName));
            if (c10388a.flags == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(c10388a.flags);
            }
            try {
                context.startActivity(intent);
                C10393b.m6191c("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                C10393b.m6194a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", e.getMessage());
                return false;
            }
        }
    }
}
