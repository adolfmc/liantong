package com.megvii.livenesslib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.megvii.livenesslib.C5351R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AutoRatioImageview extends ImageView {
    private int mPrefer;
    private float mRatio;

    public AutoRatioImageview(Context context) {
        super(context);
        this.mRatio = -1.0f;
        this.mPrefer = 0;
    }

    public AutoRatioImageview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRatio = -1.0f;
        this.mPrefer = 0;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5351R.styleable.AutoRatioImageView);
        if (obtainStyledAttributes != null) {
            this.mRatio = obtainStyledAttributes.getFloat(C5351R.styleable.AutoRatioImageView_ratio, -1.0f);
            this.mPrefer = obtainStyledAttributes.getInt(C5351R.styleable.AutoRatioImageView_prefer, 0);
        }
    }

    public AutoRatioImageview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRatio = -1.0f;
        this.mPrefer = 0;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        float f = this.mRatio;
        if (f < 0.0f) {
            if (getDrawable() == null) {
                super.onMeasure(i, i2);
                return;
            }
            int intrinsicWidth = getDrawable().getIntrinsicWidth();
            int intrinsicHeight = getDrawable().getIntrinsicHeight();
            if (this.mPrefer == 0) {
                setMeasuredDimension(size, (intrinsicHeight * size) / intrinsicWidth);
            } else {
                setMeasuredDimension((intrinsicWidth * size2) / intrinsicHeight, size2);
            }
        } else if (this.mPrefer == 0) {
            setMeasuredDimension(size, (int) (size * f));
        } else {
            setMeasuredDimension((int) (size2 / f), size);
        }
    }
}
