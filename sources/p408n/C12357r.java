package p408n;

import android.content.Context;
import p404l.C12291b;
import p408n.C12348l;

/* renamed from: n.r */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12357r extends C12359t {
    public C12357r(Context context, C12348l.C12349a c12349a) {
        super(context);
        m1805a(c12349a);
    }

    /* renamed from: a */
    public final void m1805a(C12348l.C12349a c12349a) {
        C12342f c12342f = (C12342f) c12349a.f24994c;
        setText(c12342f.f24954a);
        int i = c12342f.f24956c;
        if (i <= 0) {
            i = 60;
        }
        setTextSize(i);
        int i2 = c12342f.f24957d;
        if (i2 <= 0) {
            i2 = 170;
        }
        setHeight(i2);
        int i3 = c12342f.f24955b;
        if (i3 == 0) {
            i3 = -13421773;
        }
        setTextColor(i3);
        setBackgroundDrawable(new C12291b(c12349a));
    }
}
