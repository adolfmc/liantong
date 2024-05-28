package comp.android.app.face.p381sz.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/* renamed from: comp.android.app.face.sz.camera.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11778c extends View {

    /* renamed from: a */
    Path f23989a;

    /* renamed from: b */
    private int f23990b;

    /* renamed from: c */
    private int f23991c;

    /* renamed from: d */
    private int f23992d;

    /* renamed from: e */
    private float f23993e;

    /* renamed from: f */
    private Paint f23994f;

    public C11778c(Context context) {
        super(context);
    }

    public C11778c(Context context, int i) {
        this(context);
        this.f23990b = i;
        int i2 = i / 2;
        this.f23991c = i2;
        this.f23992d = i2;
        this.f23993e = i / 15.0f;
        this.f23994f = new Paint();
        this.f23994f.setAntiAlias(true);
        this.f23994f.setColor(-1);
        this.f23994f.setStyle(Paint.Style.STROKE);
        this.f23994f.setStrokeWidth(this.f23993e);
        this.f23989a = new Path();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.f23989a;
        float f = this.f23993e;
        path.moveTo(f, f / 2.0f);
        this.f23989a.lineTo(this.f23991c, this.f23992d - (this.f23993e / 2.0f));
        Path path2 = this.f23989a;
        float f2 = this.f23993e;
        path2.lineTo(this.f23990b - f2, f2 / 2.0f);
        canvas.drawPath(this.f23989a, this.f23994f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3 = this.f23990b;
        setMeasuredDimension(i3, i3 / 2);
    }
}
