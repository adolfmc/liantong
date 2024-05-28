package p472q0;

import android.view.View;
import android.widget.TextView;
import p474r0.InterfaceC13715a;

/* renamed from: q0.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC13679d implements InterfaceC13715a {

    /* renamed from: a */
    public View f27517a;

    /* renamed from: b */
    public TextView f27518b;

    /* renamed from: c */
    public int f27519c;

    /* renamed from: d */
    public int f27520d;

    /* renamed from: e */
    public int f27521e;

    /* renamed from: f */
    public int f27522f;

    /* renamed from: g */
    public float f27523g;

    /* renamed from: h */
    public float f27524h;

    @Override // p474r0.InterfaceC13715a
    /* renamed from: a */
    public /* synthetic */ TextView mo125a(View view) {
        return InterfaceC13715a.CC.$default$a(this, view);
    }

    @Override // p474r0.InterfaceC13715a
    public final void setDuration(int i) {
        this.f27520d = i;
    }

    @Override // p474r0.InterfaceC13715a
    public final void setGravity(int i, int i2, int i3) {
        this.f27519c = i;
        this.f27521e = i2;
        this.f27522f = i3;
    }

    @Override // p474r0.InterfaceC13715a
    public final void setMargin(float f, float f2) {
        this.f27523g = f;
        this.f27524h = f2;
    }

    @Override // p474r0.InterfaceC13715a
    public final void setText(CharSequence charSequence) {
        TextView textView = this.f27518b;
        if (textView == null) {
            return;
        }
        textView.setText(charSequence);
    }

    @Override // p474r0.InterfaceC13715a
    public final void setView(View view) {
        this.f27517a = view;
        this.f27518b = view == null ? null : mo125a(view);
    }
}
