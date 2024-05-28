package cn.ctid.verification;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.anxin.teeidentify_lib.C2114R;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1618d implements InterfaceC1602A {

    /* renamed from: a */
    private C1610G f2672a;

    /* renamed from: b */
    private ImageView f2673b;

    /* renamed from: c */
    private TextView f2674c;

    /* renamed from: d */
    private Activity f2675d;

    /* renamed from: e */
    private ViewGroup f2676e;

    /* renamed from: f */
    private C1639x f2677f;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(223);
    }

    public C1618d(Activity activity, ViewGroup viewGroup) {
        this.f2675d = (Activity) C1611H.m22085b(activity);
        this.f2676e = (ViewGroup) C1611H.m22085b(viewGroup);
        this.f2672a = new C1610G(activity, C2114R.C2118layout.ctid_identify_layout_titlebar);
        this.f2673b = (ImageView) this.f2672a.m22088a(C2114R.C2117id.left_icon);
        this.f2674c = (TextView) this.f2672a.m22088a(C2114R.C2117id.title);
        if (C1640y.m22048a().f2714g != null) {
            this.f2677f = C1640y.m22048a().f2714g.mo22040a();
        }
    }

    /* renamed from: a */
    static native /* synthetic */ Activity m22072a(C1618d c1618d);

    @Override // cn.ctid.verification.InterfaceC1602A
    /* renamed from: a */
    public native void mo22073a();

    @Override // cn.ctid.verification.InterfaceC1602A
    /* renamed from: b */
    public native void mo22071b();

    /* renamed from: c */
    public native void m22070c();

    /* renamed from: d */
    public native void m22069d();
}
