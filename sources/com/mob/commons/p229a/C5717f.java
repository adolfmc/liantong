package com.mob.commons.p229a;

import android.text.TextUtils;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6212e;
import com.mob.tools.utils.ResHelper;
import java.util.Date;
import java.util.HashMap;

/* renamed from: com.mob.commons.a.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5717f extends AbstractRunnableC6217h {

    /* renamed from: a */
    private int f14095a = 0;

    /* renamed from: b */
    private final String f14096b;

    /* renamed from: c */
    private final int f14097c;

    /* renamed from: d */
    private long f14098d;

    /* renamed from: e */
    private final HashMap<String, Object> f14099e;

    public C5717f(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            this.f14099e = new HashMap<>();
            this.f14096b = null;
            this.f14097c = 0;
            return;
        }
        this.f14099e = hashMap;
        this.f14096b = m12726b();
        this.f14097c = ((Integer) ResHelper.forceCast(hashMap.get("dn"), 0)).intValue();
    }

    @Override // com.mob.tools.utils.AbstractRunnableC6217h
    /* renamed from: a */
    public void mo10991a() {
        MobLog.getInstance().m11342d("LPT owk", new Object[0]);
        if (m12723e()) {
            if (this.f14098d == 0) {
                this.f14098d = C6212e.m11053a().m11051a(new Date(), m12726b()).getTime();
            }
            int currentTimeMillis = this.f14098d > 0 ? (int) ((System.currentTimeMillis() - this.f14098d) / 60000) : 0;
            if (currentTimeMillis >= 2) {
                MobLog.getInstance().m11342d("LPT gpe " + currentTimeMillis, new Object[0]);
                while (currentTimeMillis > 0 && m12723e()) {
                    m12724d();
                    currentTimeMillis--;
                }
            } else {
                m12724d();
            }
            this.f14098d = System.currentTimeMillis();
            m12727a(60);
        }
    }

    /* renamed from: d */
    private void m12724d() {
        C6212e.m11053a().m11049a(this.f14099e, this.f14095a);
        this.f14095a++;
        MobLog.getInstance().m11342d("LPT nedlp " + this.f14096b + " cpt " + this.f14095a, new Object[0]);
    }

    /* renamed from: a */
    private void m12727a(int i) {
        C5731l.m12681a().m12667d(i, this);
    }

    /* renamed from: b */
    public String m12726b() {
        return (String) ResHelper.forceCast(this.f14099e.get("pit"), null);
    }

    /* renamed from: e */
    private boolean m12723e() {
        return this.f14095a < this.f14097c;
    }

    /* renamed from: c */
    public void m12725c() {
        long time;
        if (TextUtils.isEmpty(this.f14096b)) {
            return;
        }
        try {
            Date date = new Date();
            Date m11051a = C6212e.m11053a().m11051a(date, m12726b());
            if (date.after(m11051a)) {
                time = (m11051a.getTime() + 86400000) - date.getTime();
            } else {
                time = m11051a.getTime() - date.getTime();
            }
            m12727a((int) ((time / 1000) + (time % 1000 != 0 ? 1 : 0)));
        } catch (Throwable th) {
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d("LPT e " + th, new Object[0]);
        }
    }
}
