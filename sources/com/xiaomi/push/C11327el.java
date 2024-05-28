package com.xiaomi.push;

import com.xiaomi.push.C11330em;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.el */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11327el implements C11545am.C11547b.InterfaceC11549a {

    /* renamed from: a */
    private int f22151a;

    /* renamed from: a */
    private AbstractC11356fa f22152a;

    /* renamed from: a */
    private XMPushService f22153a;

    /* renamed from: a */
    private C11545am.C11547b f22154a;

    /* renamed from: a */
    private boolean f22156a = false;

    /* renamed from: a */
    private C11545am.EnumC11554c f22155a = C11545am.EnumC11554c.binding;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11327el(XMPushService xMPushService, C11545am.C11547b c11547b) {
        this.f22153a = xMPushService;
        this.f22154a = c11547b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m4006a() {
        this.f22154a.m2671a(this);
        this.f22152a = this.f22153a.m2902a();
    }

    @Override // com.xiaomi.push.service.C11545am.C11547b.InterfaceC11549a
    /* renamed from: a */
    public void mo2344a(C11545am.EnumC11554c enumC11554c, C11545am.EnumC11554c enumC11554c2, int i) {
        if (!this.f22156a && enumC11554c == C11545am.EnumC11554c.binding) {
            this.f22155a = enumC11554c2;
            this.f22151a = i;
            this.f22156a = true;
        }
        this.f22153a.m2885a(new XMPushService.AbstractC11509j(4) { // from class: com.xiaomi.push.el.1
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "Handling bind stats";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                C11327el.this.m4003c();
            }
        });
    }

    /* renamed from: b */
    private void m4004b() {
        this.f22154a.m2663b(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m4003c() {
        m4004b();
        if (this.f22156a && this.f22151a != 11) {
            C11325ej m3993a = C11333eo.m3990a().m3993a();
            switch (this.f22155a) {
                case unbind:
                    int i = this.f22151a;
                    if (i != 17) {
                        if (i == 21) {
                            m3993a.f22134a = EnumC11324ei.BIND_TIMEOUT.m4043a();
                            break;
                        } else {
                            try {
                                C11330em.C11331a m3999c = C11330em.m3999c(C11333eo.m3991a().m3997a());
                                m3993a.f22134a = m3999c.f22159a.m4043a();
                                m3993a.m4027c(m3999c.f22160a);
                                break;
                            } catch (NullPointerException unused) {
                                m3993a = null;
                                break;
                            }
                        }
                    } else {
                        m3993a.f22134a = EnumC11324ei.BIND_TCP_READ_TIMEOUT.m4043a();
                        break;
                    }
                case binded:
                    m3993a.f22134a = EnumC11324ei.BIND_SUCCESS.m4043a();
                    break;
            }
            if (m3993a != null) {
                m3993a.m4031b(this.f22152a.mo3853a());
                m3993a.m4023d(this.f22154a.f23533b);
                m3993a.f22137b = 1;
                try {
                    m3993a.m4039a((byte) Integer.parseInt(this.f22154a.f23539g));
                } catch (NumberFormatException unused2) {
                }
                C11333eo.m3990a().m3984a(m3993a);
            }
        }
    }
}
