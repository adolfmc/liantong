package com.xiaomi.push;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.xiaomi.push.ez */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11352ez implements InterfaceC11369fj {

    /* renamed from: a */
    public static boolean f22222a;

    /* renamed from: a */
    private AbstractC11356fa f22224a;

    /* renamed from: a */
    private SimpleDateFormat f22227a = new SimpleDateFormat("hh:mm:ss aaa");

    /* renamed from: a */
    private C11354a f22223a = null;

    /* renamed from: b */
    private C11354a f22228b = null;

    /* renamed from: a */
    private InterfaceC11360fd f22225a = null;

    /* renamed from: a */
    private final String f22226a = "[Slim] ";

    public C11352ez(AbstractC11356fa abstractC11356fa) {
        this.f22224a = null;
        this.f22224a = abstractC11356fa;
        m3907a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.ez$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11354a implements InterfaceC11362ff, InterfaceC11370fk {

        /* renamed from: a */
        String f22231a;

        /* renamed from: a */
        private boolean f22232a;

        @Override // com.xiaomi.push.InterfaceC11370fk
        /* renamed from: a */
        public boolean mo2829a(AbstractC11375fo abstractC11375fo) {
            return true;
        }

        C11354a(boolean z) {
            this.f22232a = true;
            this.f22232a = z;
            this.f22231a = z ? " RCV " : " Sent ";
        }

        @Override // com.xiaomi.push.InterfaceC11362ff
        /* renamed from: a */
        public void mo2830a(AbstractC11375fo abstractC11375fo) {
            if (C11352ez.f22222a) {
                AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + this.f22231a + " PKT " + abstractC11375fo.mo3775a());
                return;
            }
            AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + this.f22231a + " PKT [" + abstractC11375fo.m3789k() + "," + abstractC11375fo.m3790j() + "]");
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0146  */
        /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
        @Override // com.xiaomi.push.InterfaceC11362ff
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo2831a(com.xiaomi.push.C11339er r6) {
            /*
                Method dump skipped, instructions count: 379
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11352ez.C11354a.mo2831a(com.xiaomi.push.er):void");
        }
    }

    /* renamed from: a */
    private void m3907a() {
        this.f22223a = new C11354a(true);
        this.f22228b = new C11354a(false);
        AbstractC11356fa abstractC11356fa = this.f22224a;
        C11354a c11354a = this.f22223a;
        abstractC11356fa.m3889a(c11354a, c11354a);
        AbstractC11356fa abstractC11356fa2 = this.f22224a;
        C11354a c11354a2 = this.f22228b;
        abstractC11356fa2.m3877b(c11354a2, c11354a2);
        this.f22225a = new InterfaceC11360fd() { // from class: com.xiaomi.push.ez.1
            @Override // com.xiaomi.push.InterfaceC11360fd
            /* renamed from: a */
            public void mo2888a(AbstractC11356fa abstractC11356fa3, int i, Exception exc) {
                AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + " Connection closed (" + C11352ez.this.f22224a.hashCode() + ")");
            }

            @Override // com.xiaomi.push.InterfaceC11360fd
            /* renamed from: a */
            public void mo2887a(AbstractC11356fa abstractC11356fa3, Exception exc) {
                AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + " Reconnection failed due to an exception (" + C11352ez.this.f22224a.hashCode() + ")");
                exc.printStackTrace();
            }

            @Override // com.xiaomi.push.InterfaceC11360fd
            /* renamed from: b */
            public void mo2857b(AbstractC11356fa abstractC11356fa3) {
                AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + " Connection reconnected (" + C11352ez.this.f22224a.hashCode() + ")");
            }

            @Override // com.xiaomi.push.InterfaceC11360fd
            /* renamed from: a */
            public void mo2889a(AbstractC11356fa abstractC11356fa3) {
                AbstractC11049b.m5270c("[Slim] " + C11352ez.this.f22227a.format(new Date()) + " Connection started (" + C11352ez.this.f22224a.hashCode() + ")");
            }
        };
    }
}
