package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6219j;
import com.mob.tools.utils.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.mob.commons.a.o */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5738o extends AbstractRunnableC5704c {

    /* renamed from: c */
    private volatile long f14130c;

    /* renamed from: d */
    private volatile AtomicInteger f14131d;

    public C5738o() {
        super(C5731l.m12674a("002'ggRh"), 0L, C5731l.m12674a("004PggFh giek"), 300L);
        this.f14130c = 0L;
        this.f14131d = new AtomicInteger(0);
        m12757c();
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        if (this.f14066a != null && (this.f14066a instanceof Boolean) && ((Boolean) this.f14066a).booleanValue()) {
            this.f14131d.set(0);
        }
        m12652l();
        C6219j.m10995a().m10992a(getClass().getName(), new C6219j.InterfaceC6223a() { // from class: com.mob.commons.a.o.1
            @Override // com.mob.tools.utils.C6219j.InterfaceC6223a
            /* renamed from: a */
            public void mo10990a() {
                if (C5738o.this.mo12709e()) {
                    try {
                        long currentTimeMillis = System.currentTimeMillis() - C5738o.this.f14130c;
                        long intValue = ((Integer) C5747b.m12583a("wsct", 300)).intValue() * 1000;
                        if (currentTimeMillis >= intValue) {
                            C5738o.this.m12652l();
                        } else if (C5738o.this.f14131d.get() == 0) {
                            C5738o.this.f14131d.getAndSet(1);
                            C5731l.m12681a().m12678a((intValue - currentTimeMillis) / 1000, C5738o.class, new Object[]{-1, true}, 0);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().m11341d(th);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m12652l() {
        this.f14130c = System.currentTimeMillis();
        C5873u.m12183a(new AbstractC6201c<ArrayList<HashMap<String, Object>>>() { // from class: com.mob.commons.a.o.2
            @Override // com.mob.tools.utils.AbstractC6201c
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo11088a(ArrayList<HashMap<String, Object>> arrayList) {
                if (arrayList != null) {
                    try {
                        if (arrayList.isEmpty()) {
                            return;
                        }
                        ArrayList arrayList2 = new ArrayList();
                        Iterator<HashMap<String, Object>> it = arrayList.iterator();
                        while (it.hasNext()) {
                            Object obj = it.next().get(C5731l.m12674a("005@gjfkfkfjgl"));
                            if (obj != null) {
                                arrayList2.add(String.valueOf(obj));
                            }
                        }
                        Collections.sort(arrayList2);
                        String MD5 = Data.MD5(TextUtils.join("", arrayList2));
                        String m12632b = C5741aa.m12650a().m12632b(C5741aa.f14143j, (String) null);
                        long currentTimeMillis = System.currentTimeMillis();
                        long m12634b = C5741aa.m12650a().m12634b(C5741aa.f14144k, 0L);
                        long intValue = ((Integer) C5738o.this.m12764a(C5731l.m12674a("005Tgg,hDffVek"), (String) 7200)).intValue() * 1000;
                        if (m12632b == null || !m12632b.equals(MD5) || currentTimeMillis - intValue >= m12634b) {
                            C5738o.this.m12770a(0L, "WLMT", (Object) arrayList, true);
                            C5741aa.m12650a().m12641a(C5741aa.f14143j, MD5);
                            C5741aa.m12650a().m12643a(C5741aa.f14144k, currentTimeMillis);
                        }
                    } catch (Throwable th) {
                        MobLog.getInstance().m11325w(th);
                    }
                }
            }
        });
    }
}
