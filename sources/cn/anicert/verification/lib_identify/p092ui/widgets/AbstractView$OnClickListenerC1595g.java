package cn.anicert.verification.lib_identify.p092ui.widgets;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.ctid.verification.C1610G;
import cn.ctid.verification.C1611H;
import com.anxin.teeidentify_lib.C2114R;
import com.tfd.sdk.LF8bOvWP4;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.anicert.verification.lib_identify.ui.widgets.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractView$OnClickListenerC1595g<V extends View> extends PopupWindow implements View.OnClickListener {

    /* renamed from: a */
    private ArrayList<TextView> f2634a;

    /* renamed from: b */
    V f2635b;

    /* renamed from: c */
    private View.OnClickListener f2636c;

    /* renamed from: d */
    private View.OnClickListener f2637d;

    /* renamed from: e */
    private View.OnClickListener f2638e;

    /* renamed from: f */
    private InterfaceC1596a f2639f;

    /* renamed from: g */
    private View.OnLongClickListener f2640g;

    /* renamed from: h */
    private C1610G f2641h;

    /* renamed from: i */
    private View f2642i;

    /* renamed from: j */
    private View f2643j;

    /* renamed from: k */
    private View f2644k;

    /* renamed from: l */
    private ViewGroup f2645l;

    /* renamed from: m */
    private View f2646m;

    /* renamed from: n */
    private View f2647n;

    /* renamed from: o */
    int f2648o = 0;

    /* renamed from: p */
    private int f2649p;

    /* renamed from: q */
    private PopupWindow.OnDismissListener f2650q;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.anicert.verification.lib_identify.ui.widgets.g$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface InterfaceC1596a {
        /* renamed from: a */
        void mo22118a(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.anicert.verification.lib_identify.ui.widgets.g$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1597b {

        /* renamed from: a */
        int f2651a;

        /* renamed from: b */
        int f2652b;

        C1597b(int i, int i2) {
            this.f2651a = i;
            this.f2652b = i2;
        }
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(28);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractView$OnClickListenerC1595g(Context context) {
        this.f2649p = 0;
        this.f2641h = new C1610G(context, C2114R.C2118layout.ctid_identify_layout_keyboard_view);
        this.f2642i = this.f2641h.m22088a(C2114R.C2117id.arrow_down);
        this.f2643j = this.f2641h.m22088a(C2114R.C2117id.confirm);
        this.f2644k = this.f2641h.m22088a(C2114R.C2117id.del);
        this.f2645l = (ViewGroup) this.f2641h.m22088a(C2114R.C2117id.keys);
        m22124c();
        this.f2649p = C1611H.m22087a(context, 10.0f);
        m22122d();
        setContentView(this.f2641h.m22089a());
        setWidth(-1);
        setHeight(-2);
        setFocusable(true);
        setOutsideTouchable(true);
        setAnimationStyle(C2114R.C2119style.keyboard_anim);
    }

    /* renamed from: a */
    static native /* synthetic */ View.OnClickListener m22128a(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: b */
    static native /* synthetic */ View.OnClickListener m22125b(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: b */
    private native void m22127b();

    /* renamed from: c */
    static native /* synthetic */ View.OnClickListener m22123c(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: c */
    private native void m22124c();

    /* renamed from: d */
    static native /* synthetic */ View.OnLongClickListener m22121d(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: d */
    private native void m22122d();

    /* renamed from: e */
    static native /* synthetic */ PopupWindow.OnDismissListener m22120e(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: f */
    static native /* synthetic */ View m22119f(AbstractView$OnClickListenerC1595g abstractView$OnClickListenerC1595g);

    /* renamed from: a */
    native int m22135a();

    /* renamed from: a */
    native void m22134a(int i);

    /* renamed from: a */
    public native void m22133a(Activity activity);

    /* renamed from: a */
    public native void m22132a(View.OnClickListener onClickListener);

    /* renamed from: a */
    public native void m22131a(View.OnLongClickListener onLongClickListener);

    /* renamed from: a */
    public native void m22130a(V v);

    /* renamed from: a */
    public native void m22129a(InterfaceC1596a interfaceC1596a);

    /* renamed from: b */
    public native void m22126b(View.OnClickListener onClickListener);

    @Override // android.view.View.OnClickListener
    public native void onClick(View view);

    @Override // android.widget.PopupWindow
    public native void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener);
}
