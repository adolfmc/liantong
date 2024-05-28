package com.sinovatech.unicom.separatemodule.audience.view.heart;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import com.sinovatech.unicom.separatemodule.audience.view.heart.AbstractPathAnimator;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyHoPathAnimator extends AbstractPathAnimator {
    private final AtomicInteger mCounter;
    private Handler mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    public static float scale(double d, double d2, double d3, double d4, double d5) {
        return (float) ((((d - d2) / (d3 - d2)) * (d5 - d4)) + d4);
    }

    public MyHoPathAnimator(AbstractPathAnimator.Config config) {
        super(config);
        this.mCounter = new AtomicInteger(0);
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.view.heart.AbstractPathAnimator
    public void start(final View view, final ViewGroup viewGroup) {
        viewGroup.addView(view, new ViewGroup.LayoutParams(this.mConfig.heartWidth, this.mConfig.heartHeight));
        FloatAnimation floatAnimation = new FloatAnimation(createPath(this.mCounter, viewGroup, 2), randomRotation(), viewGroup, view);
        floatAnimation.setDuration(this.mConfig.animDuration);
        floatAnimation.setInterpolator(new LinearInterpolator());
        floatAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.heart.MyHoPathAnimator.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MyHoPathAnimator.this.mHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.heart.MyHoPathAnimator.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        viewGroup.removeView(view);
                    }
                });
                MyHoPathAnimator.this.mCounter.decrementAndGet();
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                MyHoPathAnimator.this.mCounter.incrementAndGet();
            }
        });
        floatAnimation.setInterpolator(new LinearInterpolator());
        view.startAnimation(floatAnimation);
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class FloatAnimation extends Animation {
        private float mDistance;
        private PathMeasure mPm;
        private float mRotation;
        private View mView;

        public FloatAnimation(Path path, float f, View view, View view2) {
            this.mPm = new PathMeasure(path, false);
            this.mDistance = this.mPm.getLength();
            this.mView = view2;
            this.mRotation = f;
            view.setLayerType(2, null);
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            this.mPm.getMatrix(this.mDistance * f, transformation.getMatrix(), 1);
            this.mView.setRotation(this.mRotation * f);
            float f2 = 3000.0f * f;
            float scale = f2 < 200.0f ? MyHoPathAnimator.scale(f, 0.0d, 0.06666667014360428d, 0.20000000298023224d, 1.100000023841858d) : f2 < 300.0f ? MyHoPathAnimator.scale(f, 0.06666667014360428d, 0.10000000149011612d, 1.100000023841858d, 1.0d) : 1.0f;
            this.mView.setScaleX(scale);
            this.mView.setScaleY(scale);
            transformation.setAlpha(1.0f - f);
        }
    }
}
