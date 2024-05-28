package com.networkbench.agent.impl.p252e;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.e.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6351d extends AbstractC6364m {

    /* renamed from: a */
    public static final String f15978a = "activity";

    /* renamed from: b */
    public static Bitmap f15979b = null;

    /* renamed from: c */
    public static final String f15980c = "viewid";

    /* renamed from: t */
    private static final InterfaceC6393e f15981t = C6394f.m10150a();

    /* renamed from: u */
    private static final int f15982u = 1193046;

    /* renamed from: o */
    private View f15983o;

    /* renamed from: p */
    private boolean f15984p;

    /* renamed from: q */
    private View f15985q;

    /* renamed from: r */
    private WindowManager.LayoutParams f15986r;

    /* renamed from: s */
    private FragmentC6378x f15987s;

    /* renamed from: v */
    private int f15988v;

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10266a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    public int getPosBeginX() {
        return 500;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    public int getPosBeginY() {
        return 1000;
    }

    @TargetApi(16)
    public C6351d(Activity activity, C6367o c6367o) {
        super(activity, c6367o);
        this.f15984p = false;
        this.f15985q = new View(activity);
        this.f15985q.setBackground(m10289a(2013200384, new RectShape()));
        this.f16032j = C6356g.m10303a(activity) + File.separator + "hand_enable_new.png";
        setId(1193046);
        setOnTouchListener(new View$OnTouchListenerC6350c(this));
        setOnClickListener(new View$OnClickListenerC6349b(this));
        this.f16034l = "点选";
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
    protected boolean mo10269a(AbstractC6364m abstractC6364m) {
        return abstractC6364m instanceof C6351d;
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    /* renamed from: a */
    public void mo10265a(MotionEvent motionEvent, int i, int i2) {
        m10290a(i, i2);
        this.f16027e.m10296b(this.f16029g, this.f16028f);
        View m10186a = C6388z.m10186a(this.f16026d.getWindow().getDecorView(), motionEvent);
        if (m10186a != null) {
            View view = this.f15983o;
            if (view == null || view != m10186a) {
                m10318d();
                Rect rect = new Rect();
                m10186a.getGlobalVisibleRect(rect);
                m10320a(this.f16026d, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
                this.f15984p = true;
                this.f15983o = m10186a;
                return;
            }
            return;
        }
        m10318d();
        this.f15984p = false;
        this.f15983o = null;
    }

    @Override // com.networkbench.agent.impl.p252e.AbstractC6364m
    @TargetApi(11)
    /* renamed from: c */
    public void mo10287c() {
        View view = this.f15983o;
        if (view == null || !this.f15984p) {
            return;
        }
        f15979b = C6375v.m10238a((View[]) null);
        if (f15979b == null) {
            return;
        }
        final FragmentManager fragmentManager = this.f16026d.getFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        FragmentC6378x fragmentC6378x = this.f15987s;
        if (fragmentC6378x != null) {
            beginTransaction.remove(fragmentC6378x);
        }
        this.f15987s = new FragmentC6378x();
        Bundle bundle = new Bundle();
        bundle.putString("viewid", C6255f.m10802c(view));
        bundle.putString("activity", m10316n());
        this.f15987s.setArguments(bundle);
        this.f15988v = fragmentManager.getBackStackEntryCount();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.networkbench.agent.impl.e.d.1
            @Override // android.app.FragmentManager.OnBackStackChangedListener
            public void onBackStackChanged() {
                if (C6351d.this.f15988v >= fragmentManager.getBackStackEntryCount()) {
                    C6351d.this.mo10263b();
                }
            }
        });
        try {
            beginTransaction.replace(1193046, this.f15987s).addToBackStack(null).commitAllowingStateLoss();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f15981t;
            interfaceC6393e.mo10115e("action floating view item add fragment failed:" + th.getMessage());
        }
        setVisible(4);
        m10317e();
    }

    /* renamed from: n */
    private String m10316n() {
        if (TextUtils.isEmpty(C6255f.f15552a)) {
            return Harvest.currentActivityName;
        }
        return C6255f.f15552a;
    }

    /* renamed from: a */
    public void m10320a(Context context, int i, int i2, int i3, int i4) {
        m10321a(i, i2, i3, i4);
    }

    /* renamed from: d */
    public void m10318d() {
        m10317e();
    }

    /* renamed from: a */
    public void m10321a(int i, int i2, int i3, int i4) {
        this.f15986r = m10281k();
        WindowManager.LayoutParams layoutParams = this.f15986r;
        layoutParams.flags = 327992;
        layoutParams.width = i3;
        layoutParams.height = i4;
        layoutParams.x = i;
        layoutParams.y = i2;
        this.f16027e.m10297a(this.f15985q, this.f15986r);
    }

    /* renamed from: e */
    public void m10317e() {
        this.f16027e.m10298a(this.f15985q);
    }
}
