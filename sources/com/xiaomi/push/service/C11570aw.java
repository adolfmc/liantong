package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.service.XMPushService;

/* renamed from: com.xiaomi.push.service.aw */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11570aw extends XMPushService.AbstractC11509j {

    /* renamed from: a */
    private C11339er f23627a;

    /* renamed from: a */
    private XMPushService f23628a;

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public String mo2375a() {
        return "send a message.";
    }

    public C11570aw(XMPushService xMPushService, C11339er c11339er) {
        super(4);
        this.f23628a = null;
        this.f23628a = xMPushService;
        this.f23627a = c11339er;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public void mo2374a() {
        try {
            if (this.f23627a != null) {
                if (AbstractC11590e.m2559a(this.f23627a)) {
                    this.f23627a.m3944c(System.currentTimeMillis() - this.f23627a.m3967a());
                }
                this.f23628a.m2890a(this.f23627a);
            }
        } catch (C11368fi e) {
            AbstractC11049b.m5276a(e);
            this.f23628a.m2894a(10, e);
        }
    }
}
