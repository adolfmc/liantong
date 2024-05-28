package p408n;

import android.content.Context;
import android.widget.LinearLayout;
import p408n.C12348l;

/* renamed from: n.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12351n {

    /* renamed from: a */
    public Context f25005a;

    /* renamed from: b */
    public C12348l.C12349a f25006b;

    /* renamed from: c */
    public C12334a f25007c;

    /* renamed from: d */
    public long f25008d;

    public C12351n(Context context, C12348l.C12349a c12349a) {
        this.f25005a = context;
        this.f25006b = c12349a;
        C12334a c12334a = new C12334a(context);
        this.f25007c = c12334a;
        c12334a.setOrientation(1);
        C12334a c12334a2 = this.f25007c;
        c12349a.getClass();
        c12334a2.setAlpha(1.0f);
    }

    /* renamed from: a */
    public final void m1809a() {
        C12352o c12352o = new C12352o(this.f25005a, null);
        c12352o.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        this.f25007c.addView(c12352o);
        this.f25007c.addView(new C12353p(this.f25005a, this.f25006b));
    }
}
