package com.alipay.security.mobile.module.p113c;

import com.alipay.security.mobile.module.p110a.C2081a;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.c.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2091b {
    /* renamed from: a */
    public static String m20491a(String str) {
        String str2 = "";
        try {
            str2 = C2095f.m20485a(str);
        } catch (Throwable unused) {
        }
        if (C2081a.m20577a(str2)) {
            return C2092c.m20489a(".SystemConfig" + File.separator + str);
        }
        return str2;
    }
}
