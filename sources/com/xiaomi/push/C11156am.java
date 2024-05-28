package com.xiaomi.push;

import android.content.Context;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.am */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11156am {

    /* renamed from: a */
    static int f21515a;

    /* renamed from: a */
    public static InterfaceC11150ai m4884a(Context context) {
        if (C11469j.m2974a()) {
            f21515a = 1;
            return new C11155al(context);
        } else if (C11145ah.m4908a(context)) {
            f21515a = 2;
            return new C11145ah(context);
        } else if (C11158ao.m4875a(context)) {
            f21515a = 4;
            return new C11158ao(context);
        } else if (C11165aq.m4861a(context)) {
            f21515a = 5;
            return new C11165aq(context);
        } else if (C11154ak.m4888a(context)) {
            f21515a = 3;
            return new C11151aj(context);
        } else {
            f21515a = 0;
            return new C11164ap();
        }
    }
}
