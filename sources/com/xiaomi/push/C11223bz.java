package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import java.text.SimpleDateFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.bz */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11223bz {

    /* renamed from: a */
    private static SimpleDateFormat f21667a = new SimpleDateFormat("yyyy/MM/dd");

    /* renamed from: a */
    private static String f21666a = f21667a.format(Long.valueOf(System.currentTimeMillis()));

    /* renamed from: a */
    public static C11408gj m4659a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d("category_push_stat");
        c11408gj.m3665a("push_sdk_stat_channel");
        c11408gj.m3668a(1L);
        c11408gj.m3657b(str);
        c11408gj.m3662a(true);
        c11408gj.m3658b(System.currentTimeMillis());
        c11408gj.m3642g(C11201bn.m4703a(context).m4705a());
        c11408gj.m3646e("com.xiaomi.xmsf");
        c11408gj.m3644f("");
        c11408gj.m3653c("push_stat");
        return c11408gj;
    }
}
