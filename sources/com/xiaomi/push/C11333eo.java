package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11178ax;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.C11462ig;
import com.xiaomi.push.service.C11571ax;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/* renamed from: com.xiaomi.push.eo */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11333eo {

    /* renamed from: a */
    private int f22172a;

    /* renamed from: a */
    private long f22173a;

    /* renamed from: a */
    private C11332en f22175a;

    /* renamed from: a */
    private String f22176a;

    /* renamed from: a */
    private boolean f22177a = false;

    /* renamed from: a */
    private C11178ax f22174a = C11178ax.m4802a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.eo$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11335a {

        /* renamed from: a */
        static final C11333eo f22179a = new C11333eo();
    }

    /* renamed from: a */
    public static C11333eo m3990a() {
        return C11335a.f22179a;
    }

    /* renamed from: a */
    public static C11332en m3991a() {
        C11332en c11332en;
        synchronized (C11335a.f22179a) {
            c11332en = C11335a.f22179a.f22175a;
        }
        return c11332en;
    }

    /* renamed from: a */
    public synchronized void m3983a(XMPushService xMPushService) {
        this.f22175a = new C11332en(xMPushService);
        this.f22176a = "";
        C11571ax.m2625a().m2621a(new C11571ax.AbstractC11573a() { // from class: com.xiaomi.push.eo.1
            @Override // com.xiaomi.push.service.C11571ax.AbstractC11573a
            /* renamed from: a */
            public void mo2608a(C11289dp.C11291b c11291b) {
                if (c11291b.m4263e()) {
                    C11333eo.m3990a().m3986a(c11291b.m4264e());
                }
            }
        });
    }

    /* renamed from: a */
    public boolean m3988a() {
        return this.f22177a;
    }

    /* renamed from: a */
    public void m3986a(int i) {
        if (i > 0) {
            int i2 = i * 1000;
            if (i2 > 604800000) {
                i2 = 604800000;
            }
            if (this.f22172a == i2 && this.f22177a) {
                return;
            }
            this.f22177a = true;
            this.f22173a = System.currentTimeMillis();
            this.f22172a = i2;
            AbstractC11049b.m5270c("enable dot duration = " + i2 + " start = " + this.f22173a);
        }
    }

    /* renamed from: a */
    private void m3989a() {
        if (!this.f22177a || System.currentTimeMillis() - this.f22173a <= this.f22172a) {
            return;
        }
        this.f22177a = false;
        this.f22173a = 0L;
    }

    /* renamed from: b */
    boolean m3982b() {
        m3989a();
        return this.f22177a && this.f22174a.m4803a() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized C11326ek m3992a() {
        C11326ek c11326ek;
        c11326ek = null;
        if (m3982b()) {
            c11326ek = m3987a(C11169au.m4833d(this.f22175a.f22164a) ? 750 : 375);
        }
        return c11326ek;
    }

    /* renamed from: a */
    private C11326ek m3987a(int i) {
        ArrayList arrayList = new ArrayList();
        C11326ek c11326ek = new C11326ek(this.f22176a, arrayList);
        if (!C11169au.m4833d(this.f22175a.f22164a)) {
            c11326ek.m4009a(C11455i.m3029i(this.f22175a.f22164a));
        }
        C11465ii c11465ii = new C11465ii(i);
        AbstractC11456ia mo2988a = new C11462ig.C11463a().mo2988a(c11465ii);
        try {
            c11326ek.mo3082b(mo2988a);
        } catch (C11448hu unused) {
        }
        LinkedList<C11178ax.C11179a> m4801a = this.f22174a.m4801a();
        while (m4801a.size() > 0) {
            try {
                C11325ej m3985a = m3985a(m4801a.getLast());
                if (m3985a != null) {
                    m3985a.mo3082b(mo2988a);
                }
                if (c11465ii.m2987a() > i) {
                    break;
                }
                if (m3985a != null) {
                    arrayList.add(m3985a);
                }
                m4801a.removeLast();
            } catch (C11448hu | NoSuchElementException unused2) {
            }
        }
        return c11326ek;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized C11325ej m3993a() {
        C11325ej c11325ej;
        c11325ej = new C11325ej();
        c11325ej.m4035a(C11169au.m4850a((Context) this.f22175a.f22164a));
        c11325ej.f22133a = (byte) 0;
        c11325ej.f22137b = 1;
        c11325ej.m4024d((int) (System.currentTimeMillis() / 1000));
        return c11325ej;
    }

    /* renamed from: a */
    private C11325ej m3985a(C11178ax.C11179a c11179a) {
        if (c11179a.f21563a == 0) {
            if (c11179a.f21564a instanceof C11325ej) {
                return (C11325ej) c11179a.f21564a;
            }
            return null;
        }
        C11325ej m3993a = m3993a();
        m3993a.m4038a(EnumC11324ei.CHANNEL_STATS_COUNTER.m4043a());
        m3993a.m4028c(c11179a.f21563a);
        m3993a.m4027c(c11179a.f21565a);
        return m3993a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m3984a(C11325ej c11325ej) {
        this.f22174a.m4799a(c11325ej);
    }
}
