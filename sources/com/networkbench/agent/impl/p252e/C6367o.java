package com.networkbench.agent.impl.p252e;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.o */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6367o implements InterfaceC6355f {

    /* renamed from: a */
    private final InterfaceC6393e f16038a = C6394f.m10150a();

    /* renamed from: b */
    private List<AbstractC6364m> f16039b = new ArrayList();

    /* renamed from: c */
    private WeakReference<Activity> f16040c;

    /* renamed from: b */
    public List<AbstractC6364m> m10276b() {
        return this.f16039b;
    }

    @Override // com.networkbench.agent.impl.p252e.InterfaceC6355f
    /* renamed from: a */
    public void mo10277a(WeakReference<Activity> weakReference) {
        if (this.f16039b != null) {
            this.f16039b = null;
        }
        this.f16039b = C6363l.m10291a(weakReference.get(), this);
        this.f16040c = weakReference;
        m10275c();
    }

    @Override // com.networkbench.agent.impl.p252e.InterfaceC6355f
    /* renamed from: a */
    public void mo10278a() {
        m10274d();
    }

    /* renamed from: c */
    public void m10275c() {
        View decorView = this.f16040c.get().getWindow().getDecorView();
        for (AbstractC6364m abstractC6364m : this.f16039b) {
            if (decorView instanceof FrameLayout) {
                ((FrameLayout) decorView).addView(abstractC6364m, new ViewGroup.LayoutParams(-1, -1));
            }
            abstractC6364m.m10283i();
        }
    }

    /* renamed from: d */
    public void m10274d() {
        View decorView = this.f16040c.get().getWindow().getDecorView();
        for (AbstractC6364m abstractC6364m : this.f16039b) {
            abstractC6364m.m10282j();
            if (decorView instanceof FrameLayout) {
                ((FrameLayout) decorView).removeView(abstractC6364m);
            }
        }
    }
}
