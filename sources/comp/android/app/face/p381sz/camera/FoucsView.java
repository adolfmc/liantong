package comp.android.app.face.p381sz.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import comp.android.app.face.p381sz.camera.util.ScreenUtils;

/* renamed from: comp.android.app.face.sz.camera.FoucsView */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class FoucsView extends View {

    /* renamed from: a */
    private int f23861a;

    /* renamed from: b */
    private int f23862b;

    /* renamed from: c */
    private int f23863c;

    /* renamed from: d */
    private int f23864d;

    /* renamed from: e */
    private Paint f23865e;

    public FoucsView(Context context) {
        this(context, null);
    }

    public FoucsView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FoucsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f23861a = ScreenUtils.getScreenWidth(context) / 3;
        this.f23865e = new Paint();
        this.f23865e.setAntiAlias(true);
        this.f23865e.setDither(true);
        this.f23865e.setColor(-300503530);
        this.f23865e.setStrokeWidth(4.0f);
        this.f23865e.setStyle(Paint.Style.STROKE);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.f23862b;
        int i2 = this.f23864d;
        int i3 = this.f23863c;
        canvas.drawRect(i - i2, i3 - i2, i + i2, i3 + i2, this.f23865e);
        canvas.drawLine(2.0f, getHeight() / 2, this.f23861a / 10, getHeight() / 2, this.f23865e);
        canvas.drawLine(getWidth() - 2, getHeight() / 2, getWidth() - (this.f23861a / 10), getHeight() / 2, this.f23865e);
        canvas.drawLine(getWidth() / 2, 2.0f, getWidth() / 2, this.f23861a / 10, this.f23865e);
        canvas.drawLine(getWidth() / 2, getHeight() - 2, getWidth() / 2, getHeight() - (this.f23861a / 10), this.f23865e);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.f23861a;
        this.f23862b = (int) (i3 / 2.0d);
        this.f23863c = (int) (i3 / 2.0d);
        this.f23864d = ((int) (i3 / 2.0d)) - 2;
        setMeasuredDimension(i3, i3);
    }
}
