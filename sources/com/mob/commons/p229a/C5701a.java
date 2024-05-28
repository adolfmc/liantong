package com.mob.commons.p229a;

import com.mob.commons.C5741aa;
import com.mob.commons.C5868q;
import com.mob.tools.MobLog;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.mob.commons.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5701a extends AbstractRunnableC5704c {

    /* renamed from: c */
    private static volatile long f14062c;

    /* renamed from: d */
    private static volatile HashMap<Long, Long> f14063d;

    public C5701a() {
        super(C5868q.m12203b("002ch"), 0L, C5868q.m12203b("005ch?ddTci"), 900L);
        m12757c();
        if (f14063d == null) {
            f14062c = System.currentTimeMillis();
            f14063d = C5741aa.m12650a().m12619f();
        }
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (f14063d == null) {
            f14063d = new HashMap<>();
        }
        for (Map.Entry<Long, Long> entry : f14063d.entrySet()) {
            if (entry != null && entry.getKey().longValue() != f14062c) {
                m12788l();
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - f14062c;
        f14063d.put(Long.valueOf(f14062c), Long.valueOf(currentTimeMillis));
        C5741aa.m12650a().m12638a(f14063d);
        long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14139f, 0L);
        long j = m12750j() * 1000;
        if (currentTimeMillis < j || System.currentTimeMillis() - m12634b <= j) {
            return;
        }
        m12788l();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: b */
    protected void mo12712b() {
        m12774a(((Long) m12764a(m12756d(), (String) 0L)).longValue());
    }

    /* renamed from: l */
    private void m12788l() {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            for (Map.Entry<Long, Long> entry : f14063d.entrySet()) {
                if (entry != null) {
                    hashMap.put(C5868q.m12203b("008fc cfVdbg=dkOh"), entry.getKey());
                    hashMap.put(C5868q.m12203b("0086cbcfciDchRchdc8d"), entry.getValue());
                }
            }
            m12763a("ARSTAMT", hashMap);
            C5741aa.m12650a().m12643a(C5741aa.f14139f, System.currentTimeMillis());
            if (f14063d != null) {
                f14063d.clear();
            }
            C5741aa.m12650a().m12638a((HashMap<Long, Long>) null);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
    }
}
