package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11180ay;
import com.xiaomi.push.C11419gu;
import com.xiaomi.push.C11420gv;
import com.xiaomi.push.C11421gw;
import com.xiaomi.push.C11425h;
import com.xiaomi.push.C11426ha;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11432hg;
import com.xiaomi.push.C11433hh;
import com.xiaomi.push.C11434hi;
import com.xiaomi.push.C11436hk;
import com.xiaomi.push.C11438hm;
import com.xiaomi.push.C11440ho;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.InterfaceC11442hq;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.r */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11113r {
    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static <T extends InterfaceC11442hq<T, ?>> C11427hb m5038a(Context context, T t, EnumC11404gf enumC11404gf) {
        return m5037a(context, t, enumC11404gf, !enumC11404gf.equals(EnumC11404gf.Registration), context.getPackageName(), C11087b.m5151a(context).m5156a());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static <T extends InterfaceC11442hq<T, ?>> C11427hb m5037a(Context context, T t, EnumC11404gf enumC11404gf, boolean z, String str, String str2) {
        return m5036a(context, t, enumC11404gf, z, str, str2, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static <T extends InterfaceC11442hq<T, ?>> C11427hb m5034b(Context context, T t, EnumC11404gf enumC11404gf, boolean z, String str, String str2) {
        return m5036a(context, t, enumC11404gf, z, str, str2, false);
    }

    /* renamed from: a */
    protected static <T extends InterfaceC11442hq<T, ?>> C11427hb m5036a(Context context, T t, EnumC11404gf enumC11404gf, boolean z, String str, String str2, boolean z2) {
        byte[] m3085a = C11441hp.m3085a(t);
        if (m3085a == null) {
            AbstractC11049b.m5282a("invoke convertThriftObjectToBytes method, return null.");
            return null;
        }
        C11427hb c11427hb = new C11427hb();
        if (z) {
            String m5135d = C11087b.m5151a(context).m5135d();
            if (TextUtils.isEmpty(m5135d)) {
                AbstractC11049b.m5282a("regSecret is empty, return null");
                return null;
            }
            try {
                m3085a = C11425h.m3409b(C11180ay.m4796a(m5135d), m3085a);
            } catch (Exception unused) {
                AbstractC11049b.m5268d("encryption error. ");
            }
        }
        C11419gu c11419gu = new C11419gu();
        c11419gu.f22840a = 5L;
        c11419gu.f22841a = "fakeid";
        c11427hb.m3381a(c11419gu);
        c11427hb.m3377a(ByteBuffer.wrap(m3085a));
        c11427hb.m3383a(enumC11404gf);
        c11427hb.m3371b(z2);
        c11427hb.m3372b(str);
        c11427hb.m3376a(z);
        c11427hb.m3378a(str2);
        return c11427hb;
    }

    /* renamed from: a */
    public static InterfaceC11442hq m5039a(Context context, C11427hb c11427hb) {
        byte[] m3384a;
        if (c11427hb.m3373b()) {
            byte[] m5093a = C11094f.m5093a(context, c11427hb, EnumC11090d.ASSEMBLE_PUSH_FCM);
            if (m5093a == null) {
                m5093a = C11180ay.m4796a(C11087b.m5151a(context).m5135d());
            }
            try {
                m3384a = C11425h.m3410a(m5093a, c11427hb.m3384a());
            } catch (Exception e) {
                throw new C11104l("the aes decrypt failed.", e);
            }
        } else {
            m3384a = c11427hb.m3384a();
        }
        InterfaceC11442hq m5035a = m5035a(c11427hb.m3389a(), c11427hb.f22982b);
        if (m5035a != null) {
            C11441hp.m3084a(m5035a, m3384a);
        }
        return m5035a;
    }

    /* renamed from: a */
    private static InterfaceC11442hq m5035a(EnumC11404gf enumC11404gf, boolean z) {
        switch (enumC11404gf) {
            case Registration:
                return new C11432hg();
            case UnRegistration:
                return new C11438hm();
            case Subscription:
                return new C11436hk();
            case UnSubscription:
                return new C11440ho();
            case SendMessage:
                return new C11434hi();
            case AckMessage:
                return new C11420gv();
            case SetConfig:
                return new C11426ha();
            case ReportFeedback:
                return new C11433hh();
            case Notification:
                if (z) {
                    return new C11430he();
                }
                C11421gw c11421gw = new C11421gw();
                c11421gw.m3463a(true);
                return c11421gw;
            case Command:
                return new C11426ha();
            default:
                return null;
        }
    }
}
