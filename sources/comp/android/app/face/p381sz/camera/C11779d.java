package comp.android.app.face.p381sz.camera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/* renamed from: comp.android.app.face.sz.camera.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11779d extends View {

    /* renamed from: a */
    private int f23995a;

    /* renamed from: b */
    private int f23996b;

    /* renamed from: c */
    private float f23997c;

    /* renamed from: d */
    private float f23998d;

    /* renamed from: e */
    private float f23999e;

    /* renamed from: f */
    private Paint f24000f;

    /* renamed from: g */
    private Path f24001g;

    /* renamed from: h */
    private float f24002h;

    /* renamed from: i */
    private float f24003i;

    /* renamed from: j */
    private RectF f24004j;

    public C11779d(Context context, int i, int i2) {
        super(context);
        this.f23995a = i;
        this.f23996b = i2;
        float f = i2;
        float f2 = f / 2.0f;
        this.f23999e = f2;
        this.f23997c = f2;
        this.f23998d = f2;
        this.f24000f = new Paint();
        this.f24001g = new Path();
        this.f24002h = f / 50.0f;
        this.f24003i = this.f23996b / 12.0f;
        float f3 = this.f23997c;
        float f4 = this.f23998d;
        float f5 = this.f24003i;
        this.f24004j = new RectF(f3, f4 - f5, (2.0f * f5) + f3, f4 + f5);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f23995a == 1) {
            this.f24000f.setAntiAlias(true);
            this.f24000f.setColor(-287515428);
            this.f24000f.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.f23997c, this.f23998d, this.f23999e, this.f24000f);
            this.f24000f.setColor(-16777216);
            this.f24000f.setStyle(Paint.Style.STROKE);
            this.f24000f.setStrokeWidth(this.f24002h);
            Path path = this.f24001g;
            float f = this.f23997c;
            float f2 = this.f24003i;
            path.moveTo(f - (f2 / 7.0f), this.f23998d + f2);
            Path path2 = this.f24001g;
            float f3 = this.f23997c;
            float f4 = this.f24003i;
            path2.lineTo(f3 + f4, this.f23998d + f4);
            this.f24001g.arcTo(this.f24004j, 90.0f, -180.0f);
            Path path3 = this.f24001g;
            float f5 = this.f23997c;
            float f6 = this.f24003i;
            path3.lineTo(f5 - f6, this.f23998d - f6);
            canvas.drawPath(this.f24001g, this.f24000f);
            this.f24000f.setStyle(Paint.Style.FILL);
            this.f24001g.reset();
            Path path4 = this.f24001g;
            float f7 = this.f23997c;
            float f8 = this.f24003i;
            path4.moveTo(f7 - f8, (float) (this.f23998d - (f8 * 1.5d)));
            Path path5 = this.f24001g;
            float f9 = this.f23997c;
            float f10 = this.f24003i;
            path5.lineTo(f9 - f10, (float) (this.f23998d - (f10 / 2.3d)));
            Path path6 = this.f24001g;
            float f11 = this.f24003i;
            path6.lineTo((float) (this.f23997c - (f11 * 1.6d)), this.f23998d - f11);
            this.f24001g.close();
            canvas.drawPath(this.f24001g, this.f24000f);
        }
        if (this.f23995a == 2) {
            this.f24000f.setAntiAlias(true);
            this.f24000f.setColor(-1);
            this.f24000f.setStyle(Paint.Style.FILL);
            canvas.drawCircle(this.f23997c, this.f23998d, this.f23999e, this.f24000f);
            this.f24000f.setAntiAlias(true);
            this.f24000f.setStyle(Paint.Style.STROKE);
            this.f24000f.setColor(-16724992);
            this.f24000f.setStrokeWidth(this.f24002h);
            this.f24001g.moveTo(this.f23997c - (this.f23996b / 6.0f), this.f23998d);
            Path path7 = this.f24001g;
            float f12 = this.f23997c;
            int i = this.f23996b;
            path7.lineTo(f12 - (i / 21.2f), this.f23998d + (i / 7.7f));
            Path path8 = this.f24001g;
            float f13 = this.f23997c;
            int i2 = this.f23996b;
            path8.lineTo(f13 + (i2 / 4.0f), this.f23998d - (i2 / 8.5f));
            Path path9 = this.f24001g;
            float f14 = this.f23997c;
            int i3 = this.f23996b;
            path9.lineTo(f14 - (i3 / 21.2f), this.f23998d + (i3 / 9.4f));
            this.f24001g.close();
            canvas.drawPath(this.f24001g, this.f24000f);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int i3 = this.f23996b;
        setMeasuredDimension(i3, i3);
    }
}
