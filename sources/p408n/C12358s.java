package p408n;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;
import p397j.C12229b;

/* renamed from: n.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12358s extends EditText {

    /* renamed from: a */
    public Paint f25020a;

    public C12358s(Context context) {
        this(context, null, 0);
    }

    public C12358s(Context context, AttributeSet attributeSet, int i) {
        super(context, null, 0);
        m1804a();
    }

    /* renamed from: a */
    public final void m1804a() {
        requestFocus();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setGravity(51);
        Paint paint = new Paint();
        this.f25020a = paint;
        paint.setColor(-16777216);
        this.f25020a.setStrokeWidth(1.0f);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.drawLine(0.0f, 0.0f, getWidth() - 1, 0.0f, this.f25020a);
        canvas.drawLine(0.0f, 0.0f, 0.0f, getHeight() - 1, this.f25020a);
        canvas.drawLine(getWidth() - 1, 0.0f, getWidth() - 1, getHeight() - 1, this.f25020a);
        canvas.drawLine(0.0f, getHeight() - 1, getWidth() - 1, getHeight() - 1, this.f25020a);
        super.onDraw(canvas);
    }

    @Override // android.widget.TextView
    public void setHeight(int i) {
        super.setHeight(C12229b.m1925a(i));
    }

    @Override // android.widget.TextView
    public void setTextSize(float f) {
        setTextSize(0, C12229b.m1925a((int) f));
    }
}
