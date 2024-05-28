package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.service.XMPushService;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.service.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11587c extends XMPushService.AbstractC11509j {

    /* renamed from: a */
    private XMPushService f23671a;

    /* renamed from: a */
    private C11339er[] f23672a;

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public String mo2375a() {
        return "batch send message.";
    }

    public C11587c(XMPushService xMPushService, C11339er[] c11339erArr) {
        super(4);
        this.f23671a = null;
        this.f23671a = xMPushService;
        this.f23672a = c11339erArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
    /* renamed from: a */
    public void mo2374a() {
        try {
            if (this.f23672a != null) {
                this.f23671a.m2863a(this.f23672a);
            }
        } catch (C11368fi e) {
            AbstractC11049b.m5276a(e);
            this.f23671a.m2894a(10, e);
        }
    }
}
