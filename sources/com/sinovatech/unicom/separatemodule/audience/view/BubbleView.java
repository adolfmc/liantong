package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BubbleView extends RelativeLayout {
    private int bottomPadding;
    private List<Drawable> drawableList;
    private float maxScale;
    private float minScale;
    private int originsOffset;
    private int riseDuration;

    public BubbleView(Context context) {
        super(context);
        this.drawableList = new ArrayList();
        this.riseDuration = 3000;
        this.bottomPadding = 0;
        this.originsOffset = 20;
        this.maxScale = 1.0f;
        this.minScale = 1.0f;
    }

    public BubbleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawableList = new ArrayList();
        this.riseDuration = 3000;
        this.bottomPadding = 0;
        this.originsOffset = 20;
        this.maxScale = 1.0f;
        this.minScale = 1.0f;
    }

    public BubbleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.drawableList = new ArrayList();
        this.riseDuration = 3000;
        this.bottomPadding = 0;
        this.originsOffset = 20;
        this.maxScale = 1.0f;
        this.minScale = 1.0f;
    }

    public BubbleView setDrawableList(List<Drawable> list) {
        this.drawableList = list;
        return this;
    }

    public BubbleView setDefaultDrawableList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getResources().getDrawable(2131230970));
        arrayList.add(getResources().getDrawable(2131230971));
        arrayList.add(getResources().getDrawable(2131230972));
        arrayList.add(getResources().getDrawable(2131230973));
        arrayList.add(getResources().getDrawable(2131230974));
        arrayList.add(getResources().getDrawable(2131230975));
        arrayList.add(getResources().getDrawable(2131230976));
        arrayList.add(getResources().getDrawable(2131230977));
        arrayList.add(getResources().getDrawable(2131230978));
        setDrawableList(arrayList);
        return this;
    }

    public void startAnimation(int i, int i2) {
        bubbleAnimation(i, i2);
    }

    public void startAnimation(int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bubbleAnimation(i, i2);
        }
    }

    private void bubbleAnimation(int i, int i2) {
        int dip2px = i2 - dip2px(getContext(), this.bottomPadding);
        switch ((int) (Math.random() * 3.0d)) {
            case 0:
                i -= this.originsOffset;
                break;
            case 1:
                i += this.originsOffset;
                break;
            case 2:
                dip2px -= this.originsOffset;
                break;
        }
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(this.drawableList.get((int) (this.drawableList.size() * Math.random())));
        addView(imageView);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.0f);
        ofFloat.setDuration(this.riseDuration);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "scaleX", this.minScale, this.maxScale);
        ofFloat2.setDuration(this.riseDuration);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, "scaleY", this.minScale, this.maxScale);
        ofFloat3.setDuration(this.riseDuration);
        ValueAnimator besselAnimator = getBesselAnimator(imageView, i, dip2px);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(besselAnimator).with(ofFloat).with(ofFloat2).with(ofFloat3);
        animatorSet.start();
    }

    private ValueAnimator getBesselAnimator(final ImageView imageView, int i, int i2) {
        float f;
        float[] fArr;
        float[] fArr2 = {i / 2, i2};
        double d = i;
        double d2 = i2;
        ValueAnimator ofObject = ValueAnimator.ofObject(new BesselEvaluator(new float[]{((float) (0.1d * d)) + ((float) (Math.random() * d * 0.8d)), (float) (d2 - ((Math.random() * d2) * 0.5d))}, new float[]{(float) (Math.random() * d), (float) (Math.random() * (f - fArr[1]))}), fArr2, new float[]{(float) (Math.random() * d), 0.0f});
        ofObject.setDuration(this.riseDuration);
        ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.BubbleView.1
            @Override // com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float[] fArr3 = new float[2];
                float[] fArr4 = (float[]) valueAnimator.getAnimatedValue();
                imageView.setTranslationX(fArr4[0]);
                imageView.setTranslationY(fArr4[1]);
            }
        });
        ofObject.addListener(new Animator.AnimatorListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.BubbleView.2
            @Override // com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // com.nineoldandroids.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BubbleView.this.removeView(imageView);
                imageView.setImageDrawable(null);
            }
        });
        return ofObject;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BesselEvaluator implements TypeEvaluator<float[]> {
        private float[] point1;
        private float[] point2;

        public BesselEvaluator(float[] fArr, float[] fArr2) {
            this.point1 = new float[2];
            this.point2 = new float[2];
            this.point1 = fArr;
            this.point2 = fArr2;
        }

        @Override // com.nineoldandroids.animation.TypeEvaluator
        public float[] evaluate(float f, float[] fArr, float[] fArr2) {
            float f2 = 1.0f - f;
            float[] fArr3 = this.point1;
            float f3 = (fArr[0] * f2 * f2 * f2) + (fArr3[0] * 3.0f * f * f2 * f2);
            float[] fArr4 = this.point2;
            return new float[]{f3 + (fArr4[0] * 3.0f * f2 * f * f) + (fArr2[0] * f * f * f), (fArr[1] * f2 * f2 * f2) + (fArr3[1] * 3.0f * f * f2 * f2) + (fArr4[1] * 3.0f * f2 * f * f) + (fArr2[1] * f * f * f)};
        }
    }

    public int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
