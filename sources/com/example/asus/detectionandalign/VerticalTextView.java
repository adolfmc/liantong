package com.example.asus.detectionandalign;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.p086v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VerticalTextView extends AppCompatTextView {

    /* renamed from: a */
    Rect f9882a;

    /* renamed from: b */
    private int f9883b;

    public VerticalTextView(Context context) {
        super(context);
        this.f9882a = new Rect();
    }

    public VerticalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9882a = new Rect();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4243R.styleable.verticaltextview);
        this.f9883b = obtainStyledAttributes.getInt(C4243R.styleable.verticaltextview_direction, 0);
        obtainStyledAttributes.recycle();
        requestLayout();
        invalidate();
    }

    /* renamed from: a */
    private int m16246a(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int height = this.f9882a.height() + getPaddingTop() + getPaddingBottom();
        return mode == Integer.MIN_VALUE ? Math.min(height, size) : height;
    }

    /* renamed from: b */
    private int m16245b(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int width = this.f9882a.width() + getPaddingLeft() + getPaddingRight();
        return mode == Integer.MIN_VALUE ? Math.min(width, size) : width;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        int height;
        int width2;
        int height2;
        int height3;
        int height4;
        int height5;
        int i;
        canvas.save();
        Path path = new Path();
        int i2 = this.f9883b;
        if (i2 != 0) {
            if (i2 == 1) {
                width = (getWidth() + this.f9882a.height()) >> 1;
                height = (getHeight() + this.f9882a.width()) >> 1;
                width2 = (getWidth() + this.f9882a.height()) >> 1;
                height2 = getHeight();
                height3 = this.f9882a.width();
            } else if (i2 != 2) {
                if (i2 == 3) {
                    width = (getWidth() + this.f9882a.width()) >> 1;
                    height = (getHeight() - this.f9882a.height()) >> 1;
                    width2 = (getWidth() - this.f9882a.width()) >> 1;
                    height2 = getHeight();
                    height3 = this.f9882a.height();
                }
                getPaint().setColor(getCurrentTextColor());
                canvas.drawTextOnPath(getText().toString(), path, 0.0f, 0.0f, getPaint());
                canvas.restore();
            } else {
                width = (getWidth() - this.f9882a.width()) >> 1;
                height = (getHeight() + this.f9882a.height()) >> 1;
                width2 = (getWidth() + this.f9882a.width()) >> 1;
                height4 = getHeight();
                height5 = this.f9882a.height();
            }
            i = height2 - height3;
            path.moveTo(width, height);
            path.lineTo(width2, i >> 1);
            getPaint().setColor(getCurrentTextColor());
            canvas.drawTextOnPath(getText().toString(), path, 0.0f, 0.0f, getPaint());
            canvas.restore();
        }
        width = (getWidth() - this.f9882a.height()) >> 1;
        height = (getHeight() - this.f9882a.width()) >> 1;
        width2 = (getWidth() - this.f9882a.height()) >> 1;
        height4 = getHeight();
        height5 = this.f9882a.width();
        i = height4 + height5;
        path.moveTo(width, height);
        path.lineTo(width2, i >> 1);
        getPaint().setColor(getCurrentTextColor());
        canvas.drawTextOnPath(getText().toString(), path, 0.0f, 0.0f, getPaint());
        canvas.restore();
    }

    @Override // android.support.p086v7.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        int m16245b;
        int m16246a;
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), this.f9882a);
        int i3 = this.f9883b;
        if (i3 == 2 || i3 == 3) {
            m16245b = m16245b(i);
            m16246a = m16246a(i2);
        } else if (i3 != 0 && i3 != 1) {
            return;
        } else {
            m16245b = m16246a(i);
            m16246a = m16245b(i2);
        }
        setMeasuredDimension(m16245b, m16246a);
    }

    public void setDirection(int i) {
        this.f9883b = i;
        requestLayout();
        invalidate();
    }
}
