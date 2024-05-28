package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.XMPushService;
import java.util.Collection;

/* renamed from: com.xiaomi.push.service.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11619s extends XMPushService.AbstractC11509j {

    /* renamed from: a */
    private XMPushService f23747a;

    /* renamed from: a */
    private String f23748a;

    /* renamed from: a */
    private byte[] f23749a;

    /* renamed from: b */
    private String f23750b;

    /* renamed from: c */
    private String f23751c;

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public String mo2375a() {
        return "register app";
    }

    public C11619s(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.f23747a = xMPushService;
        this.f23748a = str;
        this.f23749a = bArr;
        this.f23750b = str2;
        this.f23751c = str3;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public void mo2374a() {
        C11545am.C11547b next;
        C11614p m2430a = C11615q.m2430a((Context) this.f23747a);
        if (m2430a == null) {
            try {
                m2430a = C11615q.m2424a(this.f23747a, this.f23748a, this.f23750b, this.f23751c);
            } catch (Exception e) {
                AbstractC11049b.m5268d("fail to register push account. " + e);
            }
        }
        if (m2430a == null) {
            AbstractC11049b.m5268d("no account for registration.");
            C11620t.m2407a(this.f23747a, 70000002, "no account.");
            return;
        }
        AbstractC11049b.m5282a("do registration now.");
        Collection<C11545am.C11547b> m2684a = C11545am.m2692a().m2684a("5");
        if (m2684a.isEmpty()) {
            next = m2430a.m2435a(this.f23747a);
            C11632w.m2356a(this.f23747a, next);
            C11545am.m2692a().m2686a(next);
        } else {
            next = m2684a.iterator().next();
        }
        if (this.f23747a.m2851c()) {
            try {
                if (next.f23527a == C11545am.EnumC11554c.binded) {
                    C11632w.m2354a(this.f23747a, this.f23748a, this.f23749a);
                } else if (next.f23527a == C11545am.EnumC11554c.unbind) {
                    C11620t.m2403a(this.f23748a, this.f23749a);
                    XMPushService xMPushService = this.f23747a;
                    XMPushService xMPushService2 = this.f23747a;
                    xMPushService2.getClass();
                    xMPushService.m2885a(new XMPushService.C11501b(next));
                }
                return;
            } catch (C11368fi e2) {
                AbstractC11049b.m5268d("meet error, disconnect connection. " + e2);
                this.f23747a.m2894a(10, e2);
                return;
            }
        }
        C11620t.m2403a(this.f23748a, this.f23749a);
        this.f23747a.m2865a(true);
    }
}
