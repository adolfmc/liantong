package p001a;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* renamed from: a.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C0776c {

    /* renamed from: e */
    public int f2397e;

    /* renamed from: f */
    public int f2398f;

    /* renamed from: g */
    public int f2399g;

    /* renamed from: h */
    public int f2400h;

    /* renamed from: i */
    public int f2401i;

    /* renamed from: j */
    public float f2402j;

    /* renamed from: k */
    public float f2403k;

    /* renamed from: l */
    public int f2404l;

    /* renamed from: m */
    public int f2405m;

    /* renamed from: o */
    public int f2407o;

    /* renamed from: p */
    public int f2408p;

    /* renamed from: a */
    public int f2393a = Integer.MAX_VALUE;

    /* renamed from: b */
    public int f2394b = Integer.MAX_VALUE;

    /* renamed from: c */
    public int f2395c = Integer.MIN_VALUE;

    /* renamed from: d */
    public int f2396d = Integer.MIN_VALUE;

    /* renamed from: n */
    public List<Integer> f2406n = new ArrayList();

    /* renamed from: a */
    public final int m22230a() {
        return this.f2400h - this.f2401i;
    }

    /* renamed from: a */
    public final void m22229a(View view, int i, int i2, int i3, int i4) {
        InterfaceC0701b interfaceC0701b = (InterfaceC0701b) view.getLayoutParams();
        this.f2393a = Math.min(this.f2393a, (view.getLeft() - interfaceC0701b.mo15654g()) - i);
        this.f2394b = Math.min(this.f2394b, (view.getTop() - interfaceC0701b.mo15645p()) - i2);
        this.f2395c = Math.max(this.f2395c, interfaceC0701b.mo15657d() + view.getRight() + i3);
        this.f2396d = Math.max(this.f2396d, interfaceC0701b.mo15659b() + view.getBottom() + i4);
    }
}
