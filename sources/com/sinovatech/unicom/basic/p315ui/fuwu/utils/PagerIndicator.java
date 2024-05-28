package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p083v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.PagerIndicator */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PagerIndicator extends LinearLayout {
    private Context context;
    private int mCurrentIndex;
    private int mIndicatorHeightSize;
    private int mIndicatorMargin;
    private Drawable mIndicatorNormal;
    private Drawable mIndicatorSelected;
    private int mIndicatorWidthSize;
    private int mNumIndicators;

    public PagerIndicator(Context context) {
        super(context);
        init(context);
    }

    public PagerIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        setOrientation(0);
        setGravity(17);
        this.mIndicatorWidthSize = getResources().getDimensionPixelSize(2131165968);
        this.mIndicatorHeightSize = getResources().getDimensionPixelSize(2131165969);
        this.mIndicatorMargin = getResources().getDimensionPixelOffset(2131165966);
        this.mIndicatorNormal = ContextCompat.getDrawable(context, 2131231712);
        this.mIndicatorSelected = ContextCompat.getDrawable(context, 2131231714);
    }

    public void setNumIndicators(int i) {
        removeAllViews();
        this.mNumIndicators = i;
        this.mCurrentIndex = 0;
        this.mIndicatorNormal = ContextCompat.getDrawable(this.context, 2131231713);
        this.mIndicatorSelected = ContextCompat.getDrawable(this.context, 2131231715);
        if (i != 1) {
            if (i == 2) {
                this.mIndicatorWidthSize = getResources().getDimensionPixelSize(2131165968);
                this.mIndicatorHeightSize = getResources().getDimensionPixelSize(2131165969);
                this.mIndicatorMargin = getResources().getDimensionPixelSize(2131165967);
            } else if (i == 3) {
                this.mIndicatorWidthSize = getResources().getDimensionPixelSize(2131165968);
                this.mIndicatorHeightSize = getResources().getDimensionPixelSize(2131165969);
                this.mIndicatorMargin = getResources().getDimensionPixelSize(2131165966);
            } else {
                this.mIndicatorNormal = ContextCompat.getDrawable(this.context, 2131231712);
                this.mIndicatorSelected = ContextCompat.getDrawable(this.context, 2131231714);
                this.mIndicatorWidthSize = getResources().getDimensionPixelSize(2131165972);
                this.mIndicatorHeightSize = getResources().getDimensionPixelSize(2131165971);
                this.mIndicatorMargin = getResources().getDimensionPixelSize(2131165966);
            }
        }
        if (i == 2) {
            ImageView imageView = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.mIndicatorWidthSize, this.mIndicatorHeightSize);
            layoutParams.setMargins(0, 0, 0, 0);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(this.mIndicatorSelected);
            addView(imageView);
            ImageView imageView2 = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(this.mIndicatorWidthSize, this.mIndicatorHeightSize);
            layoutParams2.setMargins(0, 0, this.mIndicatorMargin, 0);
            imageView2.setLayoutParams(layoutParams2);
            imageView2.setImageDrawable(this.mIndicatorNormal);
            addView(imageView2);
            return;
        }
        for (int i2 = 0; i2 < this.mNumIndicators; i2++) {
            ImageView imageView3 = new ImageView(getContext());
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(this.mIndicatorWidthSize, this.mIndicatorHeightSize);
            int i3 = this.mIndicatorMargin;
            layoutParams3.setMargins(i3, 0, i3, 0);
            imageView3.setLayoutParams(layoutParams3);
            if (i2 == this.mCurrentIndex) {
                imageView3.setImageDrawable(this.mIndicatorSelected);
            } else {
                imageView3.setImageDrawable(this.mIndicatorNormal);
            }
            addView(imageView3);
        }
    }

    public void setCurrentIndex(int i) {
        MsLogUtil.m7979d("指示器：", "currentIndex = " + i);
        if (i < 0 || i >= this.mNumIndicators) {
            return;
        }
        MsLogUtil.m7979d("指示器：", "mNumIndicators = " + this.mNumIndicators);
        MsLogUtil.m7979d("指示器：", "mCurrentIndex = " + this.mCurrentIndex);
        ((ImageView) getChildAt(this.mCurrentIndex)).setImageDrawable(this.mIndicatorNormal);
        ((ImageView) getChildAt(i)).setImageDrawable(this.mIndicatorSelected);
        this.mCurrentIndex = i;
    }
}
