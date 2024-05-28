package p479t0;

import com.unicom.pay.widget.ticker.C10727a;
import com.unicom.pay.widget.ticker.C10729b;
import com.unicom.pay.widget.ticker.C10730c;
import java.util.ArrayList;
import java.util.Set;

/* renamed from: t0.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14164b {

    /* renamed from: a */
    public final ArrayList<C10729b> f27688a = new ArrayList<>();

    /* renamed from: b */
    public final C10730c f27689b;

    /* renamed from: c */
    public C10727a[] f27690c;

    /* renamed from: d */
    public Set<Character> f27691d;

    public C14164b(C10730c c10730c) {
        this.f27689b = c10730c;
    }

    /* renamed from: a */
    public final float m92a() {
        int size = this.f27688a.size();
        float f = 0.0f;
        for (int i = 0; i < size; i++) {
            C10729b c10729b = this.f27688a.get(i);
            c10729b.m6027a();
            f += c10729b.f20560l;
        }
        return f;
    }

    /* renamed from: b */
    public final void m90b() {
        int size = this.f27688a.size();
        for (int i = 0; i < size; i++) {
            C10729b c10729b = this.f27688a.get(i);
            c10729b.m6027a();
            c10729b.f20562n = c10729b.f20560l;
        }
    }

    /* renamed from: a */
    public final void m91a(float f) {
        int size = this.f27688a.size();
        for (int i = 0; i < size; i++) {
            C10729b c10729b = this.f27688a.get(i);
            if (f == 1.0f) {
                c10729b.f20551c = c10729b.f20552d;
                c10729b.f20563o = 0.0f;
                c10729b.f20564p = 0.0f;
            }
            float f2 = c10729b.f20550b.f20568c;
            float abs = ((Math.abs(c10729b.f20555g - c10729b.f20554f) * f2) * f) / f2;
            int i2 = (int) abs;
            float f3 = (1.0f - f) * c10729b.f20564p;
            int i3 = c10729b.f20565q;
            c10729b.f20557i = ((abs - i2) * f2 * i3) + f3;
            c10729b.f20556h = (i2 * i3) + c10729b.f20554f;
            c10729b.f20558j = f2;
            float f4 = c10729b.f20559k;
            c10729b.f20560l = ((c10729b.f20561m - f4) * f) + f4;
        }
    }
}
