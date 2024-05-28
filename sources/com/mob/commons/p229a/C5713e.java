package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5869r;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6044i;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6202d;
import com.mob.tools.utils.Data;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

/* renamed from: com.mob.commons.a.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5713e extends AbstractRunnableC5704c {
    public C5713e() {
        super(C5869r.m12200a("002DedFg"), 0L, C5869r.m12200a("0066ed g*eeVdjh"), 60L);
        m12757c();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (this.f14066a != null) {
            MobLog.getInstance().m11342d("[cl] paramObj not null", new Object[0]);
            HashMap<String, Object> b = m12760b(this.f14066a);
            if (b == null || b.isEmpty()) {
                return;
            }
            b.put("pt", 4);
            m12763a("O_LCMT", b);
            return;
        }
        m12736l();
        C5715a.m12735a();
    }

    /* renamed from: l */
    private void m12736l() {
        C6152DH.requester(MobSDK.getContext()).getPosComm(0, 0, true).getMbcdi().getMcdi().request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.e.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                HashMap<String, Object> b;
                if (dHResponse.getPosComm(new int[0]) == null || (b = C5713e.this.m12760b(dHResponse.getPosComm(new int[0]))) == null || b.isEmpty()) {
                    return;
                }
                String mcdi = dHResponse.getMcdi();
                String mbcdi = dHResponse.getMbcdi();
                if (!TextUtils.isEmpty(mbcdi)) {
                    b.put("cbsmt", mbcdi);
                }
                if (!TextUtils.isEmpty(mcdi)) {
                    b.put("cssmt", mcdi);
                }
                JSONObject jSONObject = new JSONObject(C5713e.this.m12767a(dHResponse.getPosComm(new int[0])));
                String MD5 = Data.MD5(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                String m12632b = C5741aa.m12650a().m12632b(C5741aa.f14140g, (String) null);
                long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14141h, 0L);
                long longValue = ((Long) C5713e.this.m12764a(C5869r.m12200a("0064ed(gXee<djg"), (String) 3600L)).longValue() * 1000;
                long currentTimeMillis = System.currentTimeMillis();
                if (TextUtils.isEmpty(m12632b) || !m12632b.equals(MD5) || currentTimeMillis - m12634b >= longValue) {
                    if (C5713e.this.m12754f()) {
                        b.put("pt", 1);
                    } else if (currentTimeMillis - m12634b >= longValue) {
                        b.put("pt", 2);
                    } else {
                        b.put("pt", 3);
                    }
                    C5713e.this.m12763a("O_LCMT", b);
                    C5741aa.m12650a().m12641a(C5741aa.f14140g, MD5);
                    C5741aa.m12650a().m12643a(C5741aa.f14141h, currentTimeMillis);
                }
            }
        });
    }

    /* renamed from: com.mob.commons.a.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5715a {

        /* renamed from: d */
        private static volatile C5715a f14090d;

        /* renamed from: a */
        private long f14091a;

        /* renamed from: b */
        private long f14092b;

        /* renamed from: c */
        private AtomicInteger f14093c = new AtomicInteger(0);

        /* renamed from: a */
        public static C5715a m12735a() {
            if (f14090d == null) {
                synchronized (C5715a.class) {
                    if (f14090d == null) {
                        f14090d = new C5715a();
                    }
                }
            }
            return f14090d;
        }

        private C5715a() {
            C6202d.m11087a().m11083a(new C6202d.InterfaceC6211a() { // from class: com.mob.commons.a.e.a.1
                @Override // com.mob.tools.utils.C6202d.InterfaceC6211a
                /* renamed from: a */
                public void mo11054a() {
                    if (C5747b.m12562c()) {
                        if (System.currentTimeMillis() - C5715a.this.f14091a >= ((Integer) C5747b.m12583a("gpdi", 120)).intValue() * 1000) {
                            MobLog.getInstance().m11342d("[cl] tme > ", new Object[0]);
                            C5715a.this.m12732b();
                            C5715a.this.f14091a = System.currentTimeMillis();
                        }
                        C5715a.this.m12730c();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m12732b() {
            this.f14093c.getAndSet(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m12730c() {
            if (this.f14093c.get() >= 3 || System.currentTimeMillis() - this.f14092b < ((Integer) C5747b.m12583a("gpdi", 120)).intValue() * 1000) {
                return;
            }
            m12728d();
        }

        /* renamed from: d */
        private void m12728d() {
            Object m11071c = C6202d.m11087a().m11071c();
            this.f14093c.getAndIncrement();
            this.f14092b = System.currentTimeMillis();
            Object m11076b = C6202d.m11087a().m11076b();
            float f = 0.0f;
            if (m11071c != null && m11076b != null) {
                try {
                    f = new C6044i.C6049a(m11071c).m11626a(m11076b);
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                    return;
                }
            }
            if (m11071c != null) {
                if (m11076b == null || f > ((Float) C5747b.m12583a("gped", Float.valueOf(10.0f))).floatValue()) {
                    MobLog.getInstance().m11342d("[cl] cur != las", new Object[0]);
                    C6202d.m11087a().m11080a(m11071c);
                    C5731l.m12681a().m12678a(0L, C5713e.class, new Object[]{-1, m11071c}, 0);
                }
            }
        }
    }
}
