package com.networkbench.agent.impl.p252e;

import android.app.Activity;
import android.view.MotionEvent;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.r */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6370r extends AbstractC6364m {

    /* renamed from: a */
    private static final float f16043a = 66.0f;

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    protected float getBtnRadius() {
        return 66.0f;
    }

    public C6370r(Activity activity, C6367o c6367o) {
        super(activity, c6367o);
        this.f16031i = true;
        setOnTouchListener(new View$OnTouchListenerC6350c(this));
        setOnClickListener(new View$OnClickListenerC6349b(this));
        this.f16032j = C6356g.m10303a(activity) + File.separator + "window.png";
        this.f16034l = "功能";
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    protected boolean mo10269a(AbstractC6364m abstractC6364m) {
        return (abstractC6364m instanceof C6370r) || (abstractC6364m instanceof C6351d);
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10266a() {
        m10286f();
        if (this.f16033k) {
            mo10263b();
            this.f16032j = C6356g.m10303a(this.f16026d) + File.separator + "window_click.png";
            m10285g();
            return;
        }
        m10284h();
        this.f16032j = C6356g.m10303a(this.f16026d) + File.separator + "window.png";
        m10285g();
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10265a(MotionEvent motionEvent, int i, int i2) {
        if (this.f16033k) {
            return;
        }
        m10290a(i, i2);
        this.f16027e.m10296b(this.f16029g, this.f16028f);
        f16023m = i;
        f16024n = i2;
        m10268d();
    }

    /* renamed from: d */
    private void m10268d() {
        for (AbstractC6364m abstractC6364m : this.f16030h.m10276b()) {
            if (!mo10269a(abstractC6364m)) {
                abstractC6364m.m10280m();
            }
        }
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: l */
    public boolean mo10267l() {
        return !this.f16033k;
    }
}
