package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11375fo;
import com.xiaomi.push.C11228cc;
import com.xiaomi.push.C11232cg;
import com.xiaomi.push.C11266db;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.C11336ep;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11358fb;
import com.xiaomi.push.C11371fl;
import com.xiaomi.push.C11372fm;
import com.xiaomi.push.C11374fn;
import com.xiaomi.push.C11392fz;
import com.xiaomi.push.EnumC11324ei;
import com.xiaomi.push.service.C11545am;
import java.util.Date;

/* renamed from: com.xiaomi.push.service.ak */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11542ak {

    /* renamed from: a */
    private XMPushService f23515a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11542ak(XMPushService xMPushService) {
        this.f23515a = xMPushService;
    }

    /* renamed from: a */
    public void m2698a(AbstractC11375fo abstractC11375fo) {
        if (!"5".equals(abstractC11375fo.m3789k())) {
            m2696b(abstractC11375fo);
        }
        String m3789k = abstractC11375fo.m3789k();
        if (TextUtils.isEmpty(m3789k)) {
            m3789k = "1";
            abstractC11375fo.m3786l("1");
        }
        if (m3789k.equals("0")) {
            AbstractC11049b.m5282a("Received wrong packet with chid = 0 : " + abstractC11375fo.mo3775a());
        }
        if (abstractC11375fo instanceof C11372fm) {
            C11371fl m3795a = abstractC11375fo.m3795a("kick");
            if (m3795a != null) {
                String m3787l = abstractC11375fo.m3787l();
                String m3829a = m3795a.m3829a("type");
                String m3829a2 = m3795a.m3829a("reason");
                AbstractC11049b.m5282a("kicked by server, chid=" + m3789k + " res=" + C11545am.C11547b.m2665a(m3787l) + " type=" + m3829a + " reason=" + m3829a2);
                if ("wait".equals(m3829a)) {
                    C11545am.C11547b m2681a = C11545am.m2692a().m2681a(m3789k, m3787l);
                    if (m2681a != null) {
                        this.f23515a.m2872a(m2681a);
                        m2681a.m2666a(C11545am.EnumC11554c.unbind, 3, 0, m3829a2, m3829a);
                        return;
                    }
                    return;
                }
                this.f23515a.m2867a(m3789k, m3787l, 3, m3829a2, m3829a);
                C11545am.m2692a().m2680a(m3789k, m3787l);
                return;
            }
        } else if (abstractC11375fo instanceof C11374fn) {
            C11374fn c11374fn = (C11374fn) abstractC11375fo;
            if ("redir".equals(c11374fn.m3816b())) {
                C11371fl a = c11374fn.m3819a("hosts");
                if (a != null) {
                    m2699a(a);
                    return;
                }
                return;
            }
        }
        this.f23515a.m2862b().m2530a(this.f23515a, m3789k, abstractC11375fo);
    }

    /* renamed from: a */
    public void m2700a(C11339er c11339er) {
        if (5 != c11339er.m3968a()) {
            m2695c(c11339er);
        }
        try {
            m2697b(c11339er);
        } catch (Exception e) {
            AbstractC11049b.m5279a("handle Blob chid = " + c11339er.m3968a() + " cmd = " + c11339er.m3966a() + " packetid = " + c11339er.m3941e() + " failure ", e);
        }
    }

    /* renamed from: b */
    public void m2697b(C11339er c11339er) {
        String m3966a = c11339er.m3966a();
        if (c11339er.m3968a() == 0) {
            if ("PING".equals(m3966a)) {
                byte[] m3963a = c11339er.m3963a();
                if (m3963a != null && m3963a.length > 0) {
                    C11289dp.C11299j m4156a = C11289dp.C11299j.m4156a(m3963a);
                    if (m4156a.m4155b()) {
                        C11571ax.m2625a().m2622a(m4156a.m4160a());
                    }
                }
                if (!"com.xiaomi.xmsf".equals(this.f23515a.getPackageName())) {
                    this.f23515a.m2899a();
                }
                if ("1".equals(c11339er.m3941e())) {
                    AbstractC11049b.m5282a("received a server ping");
                } else {
                    C11336ep.m3972b();
                }
                this.f23515a.m2860b();
                return;
            } else if ("SYNC".equals(m3966a)) {
                if ("CONF".equals(c11339er.m3950b())) {
                    C11571ax.m2625a().m2622a(C11289dp.C11291b.m4272a(c11339er.m3963a()));
                    return;
                } else if (TextUtils.equals("U", c11339er.m3950b())) {
                    C11289dp.C11300k m4147a = C11289dp.C11300k.m4147a(c11339er.m3963a());
                    C11266db.m4384a(this.f23515a).m4379a(m4147a.m4153a(), m4147a.m4145b(), new Date(m4147a.m4154a()), new Date(m4147a.m4146b()), m4147a.m4141c() * 1024, m4147a.m4138e());
                    C11339er c11339er2 = new C11339er();
                    c11339er2.m3962a(0);
                    c11339er2.m3956a(c11339er.m3966a(), "UCA");
                    c11339er2.m3958a(c11339er.m3941e());
                    XMPushService xMPushService = this.f23515a;
                    xMPushService.m2885a(new C11570aw(xMPushService, c11339er2));
                    return;
                } else if (TextUtils.equals("P", c11339er.m3950b())) {
                    C11289dp.C11298i m4162a = C11289dp.C11298i.m4162a(c11339er.m3963a());
                    C11339er c11339er3 = new C11339er();
                    c11339er3.m3962a(0);
                    c11339er3.m3956a(c11339er.m3966a(), "PCA");
                    c11339er3.m3958a(c11339er.m3941e());
                    C11289dp.C11298i c11298i = new C11289dp.C11298i();
                    if (m4162a.m4164a()) {
                        c11298i.m4163a(m4162a.m4165a());
                    }
                    c11339er3.m3953a(c11298i.mo4063a(), (String) null);
                    XMPushService xMPushService2 = this.f23515a;
                    xMPushService2.m2885a(new C11570aw(xMPushService2, c11339er3));
                    AbstractC11049b.m5282a("ACK msgP: id = " + c11339er.m3941e());
                    return;
                } else {
                    return;
                }
            } else if ("NOTIFY".equals(c11339er.m3966a())) {
                C11289dp.C11297h m4168a = C11289dp.C11297h.m4168a(c11339er.m3963a());
                AbstractC11049b.m5282a("notify by server err = " + m4168a.m4166c() + " desc = " + m4168a.m4172a());
                return;
            } else {
                return;
            }
        }
        String num = Integer.toString(c11339er.m3968a());
        if ("SECMSG".equals(c11339er.m3966a())) {
            if (!c11339er.m3964a()) {
                this.f23515a.m2862b().m2531a(this.f23515a, num, c11339er);
                return;
            }
            AbstractC11049b.m5282a("Recv SECMSG errCode = " + c11339er.m3952b() + " errStr = " + c11339er.m3945c());
        } else if ("BIND".equals(m3966a)) {
            C11289dp.C11293d m4240a = C11289dp.C11293d.m4240a(c11339er.m3963a());
            String m3939g = c11339er.m3939g();
            C11545am.C11547b m2681a = C11545am.m2692a().m2681a(num, m3939g);
            if (m2681a == null) {
                return;
            }
            if (m4240a.m4243a()) {
                AbstractC11049b.m5282a("SMACK: channel bind succeeded, chid=" + c11339er.m3968a());
                m2681a.m2666a(C11545am.EnumC11554c.binded, 1, 0, (String) null, (String) null);
                return;
            }
            String m4244a = m4240a.m4244a();
            if ("auth".equals(m4244a)) {
                if ("invalid-sig".equals(m4240a.m4239b())) {
                    AbstractC11049b.m5282a("SMACK: bind error invalid-sig token = " + m2681a.f23535c + " sec = " + m2681a.f23540h);
                    C11336ep.m3977a(0, EnumC11324ei.BIND_INVALID_SIG.m4043a(), 1, null, 0);
                }
                m2681a.m2666a(C11545am.EnumC11554c.unbind, 1, 5, m4240a.m4239b(), m4244a);
                C11545am.m2692a().m2680a(num, m3939g);
            } else if ("cancel".equals(m4244a)) {
                m2681a.m2666a(C11545am.EnumC11554c.unbind, 1, 7, m4240a.m4239b(), m4244a);
                C11545am.m2692a().m2680a(num, m3939g);
            } else if ("wait".equals(m4244a)) {
                this.f23515a.m2872a(m2681a);
                m2681a.m2666a(C11545am.EnumC11554c.unbind, 1, 7, m4240a.m4239b(), m4244a);
            }
            AbstractC11049b.m5282a("SMACK: channel bind failed, chid=" + num + " reason=" + m4240a.m4239b());
        } else if ("KICK".equals(m3966a)) {
            C11289dp.C11296g m4179a = C11289dp.C11296g.m4179a(c11339er.m3963a());
            String m3939g2 = c11339er.m3939g();
            String m4182a = m4179a.m4182a();
            String m4178b = m4179a.m4178b();
            AbstractC11049b.m5282a("kicked by server, chid=" + num + " res= " + C11545am.C11547b.m2665a(m3939g2) + " type=" + m4182a + " reason=" + m4178b);
            if ("wait".equals(m4182a)) {
                C11545am.C11547b m2681a2 = C11545am.m2692a().m2681a(num, m3939g2);
                if (m2681a2 != null) {
                    this.f23515a.m2872a(m2681a2);
                    m2681a2.m2666a(C11545am.EnumC11554c.unbind, 3, 0, m4178b, m4182a);
                    return;
                }
                return;
            }
            this.f23515a.m2867a(num, m3939g2, 3, m4178b, m4182a);
            C11545am.m2692a().m2680a(num, m3939g2);
        }
    }

    /* renamed from: b */
    private void m2696b(AbstractC11375fo abstractC11375fo) {
        C11545am.C11547b m2681a;
        String m3787l = abstractC11375fo.m3787l();
        String m3789k = abstractC11375fo.m3789k();
        if (TextUtils.isEmpty(m3787l) || TextUtils.isEmpty(m3789k) || (m2681a = C11545am.m2692a().m2681a(m3789k, m3787l)) == null) {
            return;
        }
        C11392fz.m3733a(this.f23515a, m2681a.f23529a, C11392fz.m3730a(abstractC11375fo.mo3775a()), true, true, System.currentTimeMillis());
    }

    /* renamed from: c */
    private void m2695c(C11339er c11339er) {
        C11545am.C11547b m2681a;
        String m3939g = c11339er.m3939g();
        String num = Integer.toString(c11339er.m3968a());
        if (TextUtils.isEmpty(m3939g) || TextUtils.isEmpty(num) || (m2681a = C11545am.m2692a().m2681a(num, m3939g)) == null) {
            return;
        }
        C11392fz.m3733a(this.f23515a, m2681a.f23529a, c11339er.mo3914c(), true, true, System.currentTimeMillis());
    }

    /* renamed from: a */
    private void m2699a(C11371fl c11371fl) {
        String m3824c = c11371fl.m3824c();
        if (TextUtils.isEmpty(m3824c)) {
            return;
        }
        String[] split = m3824c.split(";");
        C11228cc m4561a = C11232cg.m4574a().m4561a(C11358fb.m3870a(), false);
        if (m4561a == null || split.length <= 0) {
            return;
        }
        m4561a.m4591a(split);
        this.f23515a.m2894a(20, (Exception) null);
        this.f23515a.m2865a(true);
    }
}
