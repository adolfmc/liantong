package p408n;

import android.content.Context;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;
import p397j.C12229b;

/* renamed from: n.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C12359t extends TextView {
    public C12359t(Context context) {
        this(context, null);
    }

    public C12359t(Context context, AttributeSet attributeSet) {
        this(context, null, 0);
    }

    public C12359t(Context context, AttributeSet attributeSet, int i) {
        super(context, null, 0);
        m1803a();
    }

    /* renamed from: a */
    public final void m1803a() {
        setGravity(17);
    }

    /* renamed from: a */
    public final void m1802a(int i, int i2, int i3, int i4) {
        super.setPadding(C12229b.m1925a(i), C12229b.m1925a(i2), C12229b.m1925a(i3), C12229b.m1925a(i4));
    }

    @Override // android.widget.TextView
    public void setHeight(int i) {
        super.setHeight(C12229b.m1925a(i));
    }

    @Override // android.widget.TextView
    public final void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(Html.fromHtml((String) charSequence), bufferType);
    }

    @Override // android.widget.TextView
    public void setTextSize(float f) {
        setTextSize(0, C12229b.m1925a((int) f));
    }
}
