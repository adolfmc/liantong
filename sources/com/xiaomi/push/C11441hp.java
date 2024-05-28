package com.xiaomi.push;

import android.app.NotificationChannel;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11450hw;
import com.xiaomi.push.C11462ig;
import com.xiaomi.push.service.C11533af;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.hp */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11441hp {
    /* renamed from: a */
    public static <T extends InterfaceC11442hq<T, ?>> byte[] m3085a(T t) {
        if (t == null) {
            return null;
        }
        try {
            return new C11449hv(new C11450hw.C11451a()).m3061a(t);
        } catch (C11448hu e) {
            AbstractC11049b.m5279a("convertThriftObjectToBytes catch TException.", e);
            return null;
        }
    }

    /* renamed from: a */
    public static <T extends InterfaceC11442hq<T, ?>> void m3084a(T t, byte[] bArr) {
        if (bArr == null) {
            throw new C11448hu("the message byte is empty.");
        }
        new C11447ht(new C11462ig.C11463a(true, true, bArr.length)).m3062a(t, bArr);
    }

    /* renamed from: a */
    public static short m3089a(Context context, C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        return m3086a(context, c11427hb.f22981b, (m3388a == null || m3388a.m3558a() == null) ? null : m3388a.m3558a().get("channel_id"));
    }

    /* renamed from: a */
    public static short m3088a(Context context, String str) {
        return m3086a(context, str, (String) null);
    }

    /* renamed from: a */
    public static short m3086a(Context context, String str, String str2) {
        return (short) (C11395g.m3714a(context, str, false).m3705a() + 0 + (C11133ad.m4938b(context) ? 4 : 0) + (C11133ad.m4939a(context) ? 8 : 0) + (C11533af.m2761a(context) ? 16 : 0) + m3087a(context, str, str2));
    }

    /* renamed from: a */
    private static int m3087a(Context context, String str, String str2) {
        C11533af m2760a;
        NotificationChannel m2757a;
        if (Build.VERSION.SDK_INT < 26 || context == null || TextUtils.isEmpty(str) || (m2760a = C11533af.m2760a(context, str)) == null || (m2757a = m2760a.m2757a(m2760a.m2756a(str2))) == null) {
            return 0;
        }
        return m2757a.getImportance() != 0 ? 32 : 64;
    }
}
