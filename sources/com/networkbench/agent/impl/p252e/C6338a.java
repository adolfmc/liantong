package com.networkbench.agent.impl.p252e;

import android.app.Activity;
import android.view.MotionEvent;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6338a extends AbstractC6364m {
    public C6338a(Activity activity, C6367o c6367o) {
        super(activity, c6367o);
        setOnClickListener(new View$OnClickListenerC6349b(this));
        this.f16032j = C6356g.m10303a(activity) + File.separator + "handle.png";
        this.f16034l = "点选";
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10266a() {
        m10284h();
        m10340d();
    }

    /* renamed from: d */
    private void m10340d() {
        for (AbstractC6364m abstractC6364m : this.f16030h.m10276b()) {
            if (abstractC6364m instanceof C6351d) {
                abstractC6364m.setVisible(0);
            }
        }
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10265a(MotionEvent motionEvent, int i, int i2) {
        throw new UnsupportedOperationException("OverviewFloatBtn not support this operation");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    public int getPosBeginX() {
        return super.getPosBeginX() - 150;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    public int getPosBeginY() {
        return super.getPosBeginY() - 150;
    }
}
