package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11375fo;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11372fm;
import com.xiaomi.push.C11374fn;
import com.xiaomi.push.C11377fq;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.service.C11545am;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: com.xiaomi.push.service.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11598h {

    /* renamed from: a */
    private C11621u f23684a = new C11621u();

    @SuppressLint({"WrongConstant"})
    /* renamed from: a */
    public void m2538a(Context context) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.service_started");
            if (C11469j.m2960c()) {
                intent.addFlags(16777216);
            }
            AbstractC11049b.m5282a("[Bcst] send ***.push.service_started broadcast to inform push service has started.");
            intent.setPackage("com.android.mms");
            context.sendBroadcast(intent);
        }
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public void m2534a(Context context, C11545am.C11547b c11547b, boolean z, int i, String str) {
        if ("5".equalsIgnoreCase(c11547b.f23539g)) {
            this.f23684a.m2397a(context, c11547b, z, i, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(c11547b.f23529a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", c11547b.f23539g);
        intent.putExtra(AbstractC11555an.f23593s, c11547b.f23533b);
        intent.putExtra(AbstractC11555an.f23566J, c11547b.f23541i);
        AbstractC11049b.m5282a(String.format("[Bcst] notify channel open result. %s,%s,%b,%d", c11547b.f23539g, c11547b.f23529a, Boolean.valueOf(z), Integer.valueOf(i)));
        m2537a(context, intent, c11547b);
    }

    @SuppressLint({"DefaultLocale"})
    /* renamed from: a */
    public void m2536a(Context context, C11545am.C11547b c11547b, int i) {
        if ("5".equalsIgnoreCase(c11547b.f23539g)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_closed");
        intent.setPackage(c11547b.f23529a);
        intent.putExtra(AbstractC11555an.f23596v, c11547b.f23539g);
        intent.putExtra("ext_reason", i);
        intent.putExtra(AbstractC11555an.f23593s, c11547b.f23533b);
        intent.putExtra(AbstractC11555an.f23566J, c11547b.f23541i);
        if (c11547b.f23523a != null && "9".equals(c11547b.f23539g)) {
            try {
                c11547b.f23523a.send(Message.obtain(null, 17, intent));
                return;
            } catch (RemoteException unused) {
                c11547b.f23523a = null;
                AbstractC11049b.m5282a("peer may died: " + c11547b.f23533b.substring(c11547b.f23533b.lastIndexOf(64)));
                return;
            }
        }
        AbstractC11049b.m5282a(String.format("[Bcst] notify channel closed. %s,%s,%d", c11547b.f23539g, c11547b.f23529a, Integer.valueOf(i)));
        m2537a(context, intent, c11547b);
    }

    /* renamed from: a */
    public void m2530a(XMPushService xMPushService, String str, AbstractC11375fo abstractC11375fo) {
        String str2;
        C11545am.C11547b m2532a = m2532a(abstractC11375fo);
        if (m2532a == null) {
            AbstractC11049b.m5268d("error while notify channel closed! channel " + str + " not registered");
        } else if ("5".equalsIgnoreCase(str)) {
            this.f23684a.m2392a(xMPushService, abstractC11375fo, m2532a);
        } else {
            String str3 = m2532a.f23529a;
            if (abstractC11375fo instanceof C11374fn) {
                str2 = "com.xiaomi.push.new_msg";
            } else if (abstractC11375fo instanceof C11372fm) {
                str2 = "com.xiaomi.push.new_iq";
            } else if (!(abstractC11375fo instanceof C11377fq)) {
                AbstractC11049b.m5268d("unknown packet type, drop it");
                return;
            } else {
                str2 = "com.xiaomi.push.new_pres";
            }
            Intent intent = new Intent();
            intent.setAction(str2);
            intent.setPackage(str3);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", abstractC11375fo.mo3776a());
            intent.putExtra(AbstractC11555an.f23566J, m2532a.f23541i);
            intent.putExtra(AbstractC11555an.f23558B, m2532a.f23540h);
            AbstractC11049b.m5282a(String.format("[Bcst] notify packet arrival. %s,%s,%s", m2532a.f23539g, m2532a.f23529a, abstractC11375fo.m3790j()));
            if ("3".equalsIgnoreCase(str)) {
                intent.putExtra(AbstractC11555an.f23597w, abstractC11375fo.f22325a);
                intent.putExtra(AbstractC11555an.f23598x, System.currentTimeMillis());
            }
            m2537a(xMPushService, intent, m2532a);
        }
    }

    /* renamed from: a */
    public void m2531a(XMPushService xMPushService, String str, C11339er c11339er) {
        C11545am.C11547b m2533a = m2533a(c11339er);
        if (m2533a == null) {
            AbstractC11049b.m5268d("error while notify channel closed! channel " + str + " not registered");
        } else if ("5".equalsIgnoreCase(str)) {
            this.f23684a.m2393a(xMPushService, c11339er, m2533a);
        } else {
            String str2 = m2533a.f23529a;
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.new_msg");
            intent.setPackage(str2);
            intent.putExtra("ext_rcv_timestamp", SystemClock.elapsedRealtime());
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_raw_packet", c11339er.m3957a(m2533a.f23540h));
            intent.putExtra(AbstractC11555an.f23566J, m2533a.f23541i);
            intent.putExtra(AbstractC11555an.f23558B, m2533a.f23540h);
            if (AbstractC11590e.m2559a(c11339er)) {
                intent.putExtra("ext_downward_pkt_id", c11339er.m3941e());
            }
            if (m2533a.f23523a != null) {
                try {
                    m2533a.f23523a.send(Message.obtain(null, 17, intent));
                    AbstractC11049b.m5282a("message was sent by messenger for chid=" + str);
                    return;
                } catch (RemoteException unused) {
                    m2533a.f23523a = null;
                    AbstractC11049b.m5282a("peer may died: " + m2533a.f23533b.substring(m2533a.f23533b.lastIndexOf(64)));
                }
            }
            if ("com.xiaomi.xmsf".equals(str2)) {
                return;
            }
            AbstractC11049b.m5282a(String.format("[Bcst] notify packet(blob) arrival. %s,%s,%s", m2533a.f23539g, m2533a.f23529a, c11339er.m3941e()));
            if (AbstractC11590e.m2559a(c11339er)) {
                C11563at.m2639a().m2637a(c11339er.m3941e(), SystemClock.elapsedRealtime());
            }
            m2537a(xMPushService, intent, m2533a);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.xiaomi.push.service.C11545am.C11547b m2532a(com.xiaomi.push.AbstractC11375fo r6) {
        /*
            r5 = this;
            com.xiaomi.push.service.am r0 = com.xiaomi.push.service.C11545am.m2692a()
            java.lang.String r1 = r6.m3789k()
            java.util.Collection r0 = r0.m2684a(r1)
            boolean r1 = r0.isEmpty()
            r2 = 0
            if (r1 == 0) goto L14
            return r2
        L14:
            java.util.Iterator r1 = r0.iterator()
            int r0 = r0.size()
            r3 = 1
            if (r0 != r3) goto L26
            java.lang.Object r6 = r1.next()
            com.xiaomi.push.service.am$b r6 = (com.xiaomi.push.service.C11545am.C11547b) r6
            return r6
        L26:
            java.lang.String r0 = r6.m3785m()
            java.lang.String r6 = r6.m3787l()
        L2e:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r1.next()
            com.xiaomi.push.service.am$b r3 = (com.xiaomi.push.service.C11545am.C11547b) r3
            java.lang.String r4 = r3.f23533b
            boolean r4 = android.text.TextUtils.equals(r0, r4)
            if (r4 != 0) goto L4a
            java.lang.String r4 = r3.f23533b
            boolean r4 = android.text.TextUtils.equals(r6, r4)
            if (r4 == 0) goto L2e
        L4a:
            return r3
        L4b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11598h.m2532a(com.xiaomi.push.fo):com.xiaomi.push.service.am$b");
    }

    /* renamed from: a */
    C11545am.C11547b m2533a(C11339er c11339er) {
        Collection<C11545am.C11547b> m2684a = C11545am.m2692a().m2684a(Integer.toString(c11339er.m3968a()));
        if (m2684a.isEmpty()) {
            return null;
        }
        Iterator<C11545am.C11547b> it = m2684a.iterator();
        if (m2684a.size() == 1) {
            return it.next();
        }
        String m3939g = c11339er.m3939g();
        while (it.hasNext()) {
            C11545am.C11547b next = it.next();
            if (TextUtils.equals(m3939g, next.f23533b)) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m2535a(Context context, C11545am.C11547b c11547b, String str, String str2) {
        if (c11547b == null) {
            AbstractC11049b.m5268d("error while notify kick by server!");
        } else if ("5".equalsIgnoreCase(c11547b.f23539g)) {
            AbstractC11049b.m5268d("mipush kicked by server");
        } else {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.kicked");
            intent.setPackage(c11547b.f23529a);
            intent.putExtra("ext_kick_type", str);
            intent.putExtra("ext_kick_reason", str2);
            intent.putExtra("ext_chid", c11547b.f23539g);
            intent.putExtra(AbstractC11555an.f23593s, c11547b.f23533b);
            intent.putExtra(AbstractC11555an.f23566J, c11547b.f23541i);
            AbstractC11049b.m5282a(String.format("[Bcst] notify packet(blob) arrival. %s,%s,%s", c11547b.f23539g, c11547b.f23529a, str2));
            m2537a(context, intent, c11547b);
        }
    }

    /* renamed from: a */
    private static void m2537a(Context context, Intent intent, C11545am.C11547b c11547b) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, m2529a(c11547b));
        }
    }

    /* renamed from: a */
    public static String m2529a(C11545am.C11547b c11547b) {
        if (!"9".equals(c11547b.f23539g)) {
            return c11547b.f23529a + ".permission.MIPUSH_RECEIVE";
        }
        return c11547b.f23529a + ".permission.MIMC_RECEIVE";
    }
}
