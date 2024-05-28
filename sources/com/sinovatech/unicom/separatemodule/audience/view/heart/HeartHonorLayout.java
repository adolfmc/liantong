package com.sinovatech.unicom.separatemodule.audience.view.heart;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.p318ui.C9718R;
import com.sinovatech.unicom.separatemodule.audience.view.heart.AbstractPathAnimator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HeartHonorLayout extends RelativeLayout {
    private AbstractPathAnimator mAnimator;

    public HeartHonorLayout(Context context) {
        super(context);
        init(null, 0);
    }

    public HeartHonorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet, 0);
    }

    public HeartHonorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet, i);
    }

    private void init(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C9718R.styleable.HeartLayout, i, 0);
        this.mAnimator = new MyHoPathAnimator(AbstractPathAnimator.Config.fromTypeArray(obtainStyledAttributes));
        obtainStyledAttributes.recycle();
    }

    public AbstractPathAnimator getAnimator() {
        return this.mAnimator;
    }

    public void setAnimator(AbstractPathAnimator abstractPathAnimator) {
        clearAnimation();
        this.mAnimator = abstractPathAnimator;
    }

    @Override // android.view.View
    public void clearAnimation() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).clearAnimation();
        }
        removeAllViews();
    }

    public void addHeart(int i) {
        HonorHeartView honorHeartView = new HonorHeartView(getContext());
        honorHeartView.setColor(i);
        this.mAnimator.start(honorHeartView, this);
    }

    public void addHeart(int i, int i2, int i3) {
        HonorHeartView honorHeartView = new HonorHeartView(getContext());
        honorHeartView.setColorAndDrawables(i, i2, i3);
        this.mAnimator.start(honorHeartView, this);
    }
}
