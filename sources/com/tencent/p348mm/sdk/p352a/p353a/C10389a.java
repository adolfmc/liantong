package com.tencent.p348mm.sdk.p352a.p353a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.p348mm.sdk.p354b.C10393b;
import com.tencent.p348mm.sdk.p354b.C10398f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.a.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10389a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.sdk.a.a.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C10390a {

        /* renamed from: d */
        public String f19997d;

        /* renamed from: e */
        public Bundle f19998e;

        /* renamed from: f */
        public String f19999f;

        /* renamed from: g */
        public String f20000g;
    }

    /* renamed from: a */
    public static boolean m6200a(Context context, C10390a c10390a) {
        String str;
        String str2;
        if (context == null || c10390a == null) {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, invalid argument";
        } else if (!C10398f.m6184c(c10390a.f20000g)) {
            String str3 = null;
            if (!C10398f.m6184c(c10390a.f19999f)) {
                str3 = c10390a.f19999f + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(c10390a.f20000g);
            if (c10390a.f19998e != null) {
                intent.putExtras(c10390a.f19998e);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 570490883);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", c10390a.f19997d);
            intent.putExtra("_mmessage_checksum", C10391b.m6199a(c10390a.f19997d, 570490883, packageName));
            context.sendBroadcast(intent, str3);
            C10393b.m6191c("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str3);
            return true;
        } else {
            str = "MicroMsg.SDK.MMessage";
            str2 = "send fail, action is null";
        }
        C10393b.m6195a(str, str2);
        return false;
    }
}
