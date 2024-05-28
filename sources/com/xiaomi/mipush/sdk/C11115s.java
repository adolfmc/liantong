package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.mipush.sdk.C11097g;
import com.xiaomi.push.C11176aw;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.s */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11115s {
    /* renamed from: a */
    public static AbstractPushManager m5033a(Context context, EnumC11090d enumC11090d) {
        return m5032b(context, enumC11090d);
    }

    /* renamed from: b */
    private static AbstractPushManager m5032b(Context context, EnumC11090d enumC11090d) {
        C11097g.C11099a m5078a = C11097g.m5078a(enumC11090d);
        if (m5078a == null || TextUtils.isEmpty(m5078a.f21387a) || TextUtils.isEmpty(m5078a.f21388b)) {
            return null;
        }
        return (AbstractPushManager) C11176aw.m4810a(m5078a.f21387a, m5078a.f21388b, context);
    }
}
