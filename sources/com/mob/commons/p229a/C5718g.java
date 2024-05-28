package com.mob.commons.p229a;

import com.mob.commons.C5741aa;
import com.mob.commons.C5855l;
import com.mob.commons.InterfaceC5854k;
import com.mob.tools.MobLog;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.commons.a.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5718g extends AbstractRunnableC5704c {

    /* renamed from: c */
    private static InterfaceC5854k f14100c;

    /* renamed from: d */
    private static final String f14101d = C5731l.m12674a("014Iem=gUeleiKedj1ejeeRg[ei(h+feff");

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: b */
    protected void mo12712b() {
    }

    public C5718g() {
        super(C5731l.m12674a("002Ggfej"), 0L, C5731l.m12674a("005Egfejff:ek"), 30L);
        m12757c();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        long longValue;
        if (m12754f()) {
            m12719m();
            return;
        }
        Long[] lArr = (Long[]) this.f14066a;
        long longValue2 = lArr[0].longValue();
        long longValue3 = lArr[1].longValue();
        int i = (longValue2 > 3L ? 1 : (longValue2 == 3L ? 0 : -1));
        if (i == 0 && lArr.length < 3) {
            longValue = System.currentTimeMillis();
        } else {
            longValue = lArr[2].longValue();
        }
        if (longValue2 == 0) {
            m12720l();
            m12722a(longValue3, longValue);
            m12721b(longValue3);
        } else if (longValue2 == 1 || i == 0) {
            m12722a(longValue3, longValue);
            m12721b(longValue3);
        } else if (longValue2 == 2) {
            m12722a(longValue3, longValue);
            m12720l();
        }
    }

    /* renamed from: b */
    private void m12721b(long j) {
        if (C5855l.m12246a().m12235b()) {
            return;
        }
        C5731l.m12681a().m12678a(m12750j(), C5718g.class, new Object[]{-1, new Long[]{3L, Long.valueOf(j)}}, 0);
    }

    /* renamed from: a */
    private void m12722a(long j, long j2) {
        try {
            HashMap hashMap = (HashMap) C5741aa.m12650a().m12627c(f14101d, null);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            hashMap.put(Long.valueOf(j), Long.valueOf(j2));
            C5741aa.m12650a().m12633b(f14101d, hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* renamed from: l */
    private void m12720l() {
        try {
            HashMap hashMap = (HashMap) C5741aa.m12650a().m12627c(f14101d, null);
            if (hashMap == null || hashMap.isEmpty()) {
                return;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                long longValue = ((Long) entry.getKey()).longValue();
                long longValue2 = ((Long) entry.getValue()).longValue();
                HashMap<String, Object> hashMap2 = new HashMap<>();
                hashMap2.put(C5731l.m12674a("005WehAfj!ej6h"), Long.valueOf(longValue2));
                hashMap2.put(C5731l.m12674a("008RekehJfj+ejegJg=gi"), Long.valueOf(longValue2 - longValue));
                m12763a("BKIOMT", hashMap2);
            }
            C5741aa.m12650a().m12636b(f14101d);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }

    /* renamed from: m */
    private static synchronized boolean m12719m() {
        synchronized (C5718g.class) {
            if (f14100c == null) {
                f14100c = new InterfaceC5854k() { // from class: com.mob.commons.a.g.1

                    /* renamed from: a */
                    private volatile long f14102a = 0;

                    @Override // com.mob.commons.InterfaceC5854k
                    /* renamed from: a */
                    public void mo12147a(boolean z, boolean z2, long j) {
                        if (z2) {
                            this.f14102a = System.currentTimeMillis();
                            C5731l.m12681a().m12678a(0L, C5718g.class, new Object[]{-1, new Long[]{0L, Long.valueOf(this.f14102a), Long.valueOf(System.currentTimeMillis())}}, 1);
                        }
                        if (!z) {
                            if (j > 0) {
                                C5731l.m12681a().m12678a(0L, C5718g.class, new Object[]{-1, new Long[]{2L, Long.valueOf(this.f14102a), Long.valueOf(System.currentTimeMillis())}}, 1);
                            }
                        } else if (z2) {
                        } else {
                            this.f14102a = System.currentTimeMillis();
                            C5731l.m12681a().m12678a(0L, C5718g.class, new Object[]{-1, new Long[]{1L, Long.valueOf(this.f14102a), Long.valueOf(System.currentTimeMillis())}}, 0);
                        }
                    }
                };
                C5855l.m12246a().m12244a(f14100c);
                return true;
            }
            return false;
        }
    }
}
