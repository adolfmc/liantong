package com.megvii.lv5.sdk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.megvii.lv5.C5490k3;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.sdk.C5559R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HorizontalStepsViewIndicator extends View {

    /* renamed from: a */
    public int f13612a;

    /* renamed from: b */
    public float f13613b;

    /* renamed from: c */
    public float f13614c;

    /* renamed from: d */
    public Drawable f13615d;

    /* renamed from: e */
    public Drawable f13616e;

    /* renamed from: f */
    public Drawable f13617f;

    /* renamed from: g */
    public Drawable f13618g;

    /* renamed from: h */
    public float f13619h;

    /* renamed from: i */
    public float f13620i;

    /* renamed from: j */
    public float f13621j;

    /* renamed from: k */
    public List<C5490k3> f13622k;

    /* renamed from: l */
    public int f13623l;

    /* renamed from: m */
    public float f13624m;

    /* renamed from: n */
    public List<Float> f13625n;

    /* renamed from: o */
    public Paint f13626o;

    /* renamed from: p */
    public Paint f13627p;

    /* renamed from: q */
    public int f13628q;

    /* renamed from: r */
    public int f13629r;

    /* renamed from: s */
    public int f13630s;

    /* renamed from: t */
    public InterfaceC5609a f13631t;

    /* renamed from: u */
    public int f13632u;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.HorizontalStepsViewIndicator$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5609a {
    }

    public HorizontalStepsViewIndicator(Context context) {
        this(context, null);
    }

    public HorizontalStepsViewIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalStepsViewIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13612a = (int) TypedValue.applyDimension(1, 28.0f, getResources().getDisplayMetrics());
        this.f13623l = 0;
        this.f13628q = getResources().getColor(C5667z2.m12879a(getContext()).m12878a(getResources().getString(C5559R.string.key_liveness_home_action_line_doing_color)));
        this.f13629r = getResources().getColor(C5667z2.m12879a(getContext()).m12878a(getResources().getString(C5559R.string.key_liveness_home_action_lined_done_color)));
        m13027a();
    }

    /* renamed from: a */
    public final void m13027a() {
        this.f13622k = new ArrayList();
        new Path();
        new DashPathEffect(new float[]{8.0f, 8.0f, 8.0f, 8.0f}, 1.0f);
        this.f13625n = new ArrayList();
        this.f13626o = new Paint();
        this.f13627p = new Paint();
        this.f13626o.setAntiAlias(true);
        this.f13626o.setColor(this.f13628q);
        this.f13626o.setStyle(Paint.Style.STROKE);
        this.f13626o.setStrokeWidth(2.0f);
        this.f13627p.setAntiAlias(true);
        this.f13627p.setColor(this.f13629r);
        this.f13627p.setStyle(Paint.Style.STROKE);
        this.f13627p.setStrokeWidth(2.0f);
        this.f13626o.setStyle(Paint.Style.FILL);
        this.f13627p.setStyle(Paint.Style.FILL);
        float f = this.f13612a;
        this.f13613b = 0.05f * f;
        this.f13614c = 0.28f * f;
        this.f13624m = f * 1.43f;
        try {
            this.f13615d = getResources().getDrawable(C5667z2.m12879a(getContext()).m12877b(getResources().getString(C5559R.string.key_liveness_home_action_line_icon2)));
            this.f13616e = getResources().getDrawable(C5667z2.m12879a(getContext()).m12877b(getResources().getString(C5559R.string.key_liveness_home_action_line_icon1)));
            this.f13617f = getResources().getDrawable(C5667z2.m12879a(getContext()).m12877b(getResources().getString(C5559R.string.key_liveness_home_action_line_icon4)));
            this.f13618g = getResources().getDrawable(C5667z2.m12879a(getContext()).m12877b(getResources().getString(C5559R.string.key_liveness_home_action_line_icon3)));
        } catch (Throwable unused) {
        }
    }

    public List<Float> getCircleCenterPointPositionList() {
        return this.f13625n;
    }

    public float getCircleRadius() {
        return this.f13614c;
    }

    @Override // android.view.View
    public synchronized void onDraw(Canvas canvas) {
        Drawable drawable;
        float f;
        float f2;
        float f3;
        float f4;
        Paint paint;
        super.onDraw(canvas);
        InterfaceC5609a interfaceC5609a = this.f13631t;
        if (interfaceC5609a != null) {
            HorizontalStepView horizontalStepView = (HorizontalStepView) interfaceC5609a;
        }
        this.f13626o.setColor(this.f13628q);
        this.f13627p.setColor(this.f13629r);
        int i = 0;
        while (i < this.f13625n.size() - 1) {
            float floatValue = this.f13625n.get(i).floatValue();
            int i2 = i + 1;
            float floatValue2 = this.f13625n.get(i2).floatValue();
            if (i >= this.f13630s || this.f13622k.get(0).f12842a == -1) {
                float f5 = this.f13614c;
                f = (floatValue + f5) - 10.0f;
                f2 = this.f13620i;
                f3 = (floatValue2 - f5) + 10.0f;
                f4 = this.f13621j;
                paint = this.f13626o;
            } else {
                float f6 = this.f13614c;
                f = (floatValue + f6) - 10.0f;
                f2 = this.f13620i;
                f3 = (floatValue2 - f6) + 10.0f;
                f4 = this.f13621j;
                paint = this.f13627p;
            }
            canvas.drawRect(f, f2, f3, f4, paint);
            i = i2;
        }
        for (int i3 = 0; i3 < this.f13625n.size(); i3++) {
            float floatValue3 = this.f13625n.get(i3).floatValue();
            float f7 = this.f13614c;
            float f8 = this.f13619h;
            Rect rect = new Rect((int) (floatValue3 - f7), (int) (f8 - f7), (int) (floatValue3 + f7), (int) (f8 + f7));
            int i4 = this.f13622k.get(i3).f12842a;
            if (i4 == -1) {
                this.f13627p.setColor(-1);
                canvas.drawCircle(floatValue3, this.f13619h, this.f13614c * 1.25f, this.f13627p);
                Drawable drawable2 = this.f13617f;
                if (drawable2 != null) {
                    drawable2.setBounds(rect);
                    drawable = this.f13617f;
                    drawable.draw(canvas);
                }
            } else if (i4 == 0) {
                this.f13627p.setColor(-1);
                canvas.drawCircle(floatValue3, this.f13619h, this.f13614c * 1.25f, this.f13627p);
                Drawable drawable3 = this.f13616e;
                if (drawable3 != null) {
                    drawable3.setBounds(rect);
                    drawable = this.f13616e;
                    drawable.draw(canvas);
                }
            } else if (i4 == 1) {
                this.f13627p.setColor(-1);
                canvas.drawCircle(floatValue3, this.f13619h, this.f13614c * 1.25f, this.f13627p);
                Drawable drawable4 = this.f13615d;
                if (drawable4 != null) {
                    drawable4.setBounds(rect);
                    drawable = this.f13615d;
                    drawable.draw(canvas);
                }
            } else {
                if (i4 == 2) {
                    this.f13627p.setColor(-1);
                    canvas.drawCircle(floatValue3, this.f13619h, this.f13614c * 1.25f, this.f13627p);
                    Drawable drawable5 = this.f13618g;
                    if (drawable5 != null) {
                        drawable5.setBounds(rect);
                        drawable = this.f13618g;
                        drawable.draw(canvas);
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public synchronized void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != 0) {
            this.f13632u = View.MeasureSpec.getSize(i);
        }
        int i3 = this.f13612a;
        if (View.MeasureSpec.getMode(i2) != 0) {
            i3 = Math.min(i3, View.MeasureSpec.getSize(i2));
        }
        int i4 = this.f13623l;
        setMeasuredDimension((int) (((i4 * this.f13614c) * 2.0f) - ((i4 - 1) * this.f13624m)), i3);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float height = getHeight() * 0.5f;
        this.f13619h = height;
        float f = this.f13613b / 2.0f;
        this.f13620i = height - f;
        this.f13621j = height + f;
        this.f13625n.clear();
        int i5 = 0;
        while (true) {
            int i6 = this.f13623l;
            if (i5 >= i6) {
                break;
            }
            float f2 = this.f13614c;
            float f3 = this.f13624m;
            float f4 = i5;
            this.f13625n.add(Float.valueOf((((this.f13632u - ((i6 * f2) * 2.0f)) - ((i6 - 1) * f3)) / 2.0f) + f2 + (f2 * f4 * 2.0f) + (f4 * f3)));
            i5++;
        }
        InterfaceC5609a interfaceC5609a = this.f13631t;
        if (interfaceC5609a != null) {
            HorizontalStepView horizontalStepView = (HorizontalStepView) interfaceC5609a;
        }
    }

    public void setAttentionIcon(Drawable drawable) {
        this.f13616e = drawable;
    }

    public void setCompleteIcon(Drawable drawable) {
        this.f13615d = drawable;
    }

    public void setCompletedLineColor(int i) {
        this.f13629r = i;
    }

    public void setDefaultIcon(Drawable drawable) {
        this.f13617f = drawable;
    }

    public void setOnDrawListener(InterfaceC5609a interfaceC5609a) {
        this.f13631t = interfaceC5609a;
    }

    public void setStepNum(List<C5490k3> list) {
        this.f13622k = list;
        this.f13623l = list.size();
        List<C5490k3> list2 = this.f13622k;
        if (list2 != null && list2.size() > 0) {
            for (int i = 0; i < this.f13623l; i++) {
                int i2 = this.f13622k.get(i).f12842a;
                if (i2 == 1 || i2 == 0 || i2 == 2) {
                    this.f13630s = i;
                }
            }
        }
        requestLayout();
    }

    public void setUnCompletedLineColor(int i) {
        this.f13628q = i;
    }
}
