package com.networkbench.agent.impl.p252e;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import com.networkbench.agent.impl.p243c.p247d.C6277i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.s */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6371s extends AbstractC6364m {

    /* renamed from: a */
    public static Bitmap f16044a = null;

    /* renamed from: c */
    private static final int f16045c = 1193048;

    /* renamed from: p */
    private static final InterfaceC6393e f16046p = C6394f.m10150a();

    /* renamed from: b */
    private FragmentC6383y f16047b;

    /* renamed from: o */
    private int f16048o;

    public C6371s(Activity activity, C6367o c6367o) {
        super(activity, c6367o);
        setOnClickListener(new View$OnClickListenerC6349b(this));
        setId(1193048);
        this.f16032j = C6356g.m10303a(activity) + File.separator + "page.png";
        this.f16034l = "页面";
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10266a() {
        m10284h();
        m10262d();
    }

    @TargetApi(11)
    /* renamed from: d */
    private void m10262d() {
        C6277i.m10671b();
        f16044a = C6375v.m10238a((View[]) null);
        if (f16044a == null) {
            return;
        }
        final FragmentManager fragmentManager = this.f16026d.getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        FragmentC6383y fragmentC6383y = this.f16047b;
        if (fragmentC6383y != null) {
            beginTransaction.remove(fragmentC6383y);
        }
        this.f16047b = new FragmentC6383y();
        this.f16048o = fragmentManager.getBackStackEntryCount();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.networkbench.agent.impl.e.s.1
            @Override // android.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
                if (C6371s.this.f16048o >= fragmentManager.getBackStackEntryCount()) {
                    C6371s.this.mo10263b();
                }
            }
        });
        try {
            beginTransaction.replace(1193048, this.f16047b).addToBackStack(null).commitAllowingStateLoss();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f16046p;
            interfaceC6393e.mo10115e("action floating view item add fragment failed:" + th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: b */
    public void mo10263b() {
        for (AbstractC6364m abstractC6364m : this.f16030h.m10276b()) {
            if (abstractC6364m instanceof C6370r) {
                abstractC6364m.setVisible(0);
                abstractC6364m.f16033k = false;
                abstractC6364m.f16032j = C6356g.m10303a(this.f16026d) + File.separator + "window.png";
                abstractC6364m.m10285g();
            } else {
                abstractC6364m.setVisible(4);
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
        return super.getPosBeginX();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    public int getPosBeginY() {
        return super.getPosBeginY() - 180;
    }
}
