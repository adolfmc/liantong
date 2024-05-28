package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.C11420gv;
import com.xiaomi.push.C11421gw;
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

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.bc */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11583bc {
    /* renamed from: a */
    public static InterfaceC11442hq m2569a(Context context, C11427hb c11427hb) {
        if (c11427hb.m3373b()) {
            return null;
        }
        byte[] m3384a = c11427hb.m3384a();
        InterfaceC11442hq m2568a = m2568a(c11427hb.m3389a(), c11427hb.f22982b);
        if (m2568a != null) {
            C11441hp.m3084a(m2568a, m3384a);
        }
        return m2568a;
    }

    /* renamed from: a */
    private static InterfaceC11442hq m2568a(EnumC11404gf enumC11404gf, boolean z) {
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
