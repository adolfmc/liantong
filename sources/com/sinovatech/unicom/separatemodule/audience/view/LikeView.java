package com.sinovatech.unicom.separatemodule.audience.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.sinovatech.unicom.separatemodule.audience.util.AnimUtils;
import java.util.Random;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LikeView extends RelativeLayout {
    private int[] angles;
    private long doubleEnd;
    private GestureDetector gestureDetector;
    private int likeViewSize;
    private TouchCallBack listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface TouchCallBack {
        void doubleTapCallback();

        void onClick(long j);
    }

    public LikeView(Context context) {
        super(context);
        this.likeViewSize = 300;
        this.angles = new int[]{-30, 0, 30};
        init(context);
    }

    public LikeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.likeViewSize = 300;
        this.angles = new int[]{-30, 0, 30};
        init(context);
    }

    private void init(Context context) {
        this.gestureDetector = new GestureDetector(context, new MyGestureDetectorListener());
        setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$LikeView$Iq5aWd3Nrh-hPNnlgcyeXw3X8Ig
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return LikeView.lambda$init$0(LikeView.this, view, motionEvent);
            }
        });
    }

    public static /* synthetic */ boolean lambda$init$0(LikeView likeView, View view, MotionEvent motionEvent) {
        likeView.gestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyGestureDetectorListener extends GestureDetector.SimpleOnGestureListener {
        public MyGestureDetectorListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onDoubleTap(MotionEvent motionEvent) {
            LikeView.this.addImageView(motionEvent);
            if (LikeView.this.listener != null) {
                LikeView.this.doubleEnd = System.currentTimeMillis();
                LikeView.this.listener.doubleTapCallback();
                return true;
            }
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            if (LikeView.this.listener != null) {
                LikeView.this.listener.onClick(System.currentTimeMillis() - LikeView.this.doubleEnd);
                return true;
            }
            return true;
        }
    }

    public void setListener(TouchCallBack touchCallBack) {
        this.listener = touchCallBack;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addImageView(MotionEvent motionEvent) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(getResources().getDrawable(2131230888));
        addView(imageView);
        int i = this.likeViewSize;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
        layoutParams.leftMargin = ((int) motionEvent.getX()) - (this.likeViewSize / 2);
        layoutParams.topMargin = ((int) motionEvent.getY()) - (this.likeViewSize / 2);
        imageView.setLayoutParams(layoutParams);
        playAnim(imageView);
    }

    private void playAnim(final ImageView imageView) {
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(AnimUtils.rotateAnim(0L, 0, this.angles[new Random().nextInt(3)]));
        animationSet.addAnimation(AnimUtils.scaleAnim(100L, 2.0f, 1.0f, 0L));
        animationSet.addAnimation(AnimUtils.alphaAnim(0.0f, 1.0f, 100L, 0L));
        animationSet.addAnimation(AnimUtils.scaleAnim(500L, 1.0f, 1.8f, 300L));
        animationSet.addAnimation(AnimUtils.alphaAnim(1.0f, 0.0f, 500L, 300L));
        animationSet.addAnimation(AnimUtils.translationAnim(500L, 0.0f, 0.0f, 0.0f, -400.0f, 300L));
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LikeView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.LikeView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LikeView.this.removeView(imageView);
                    }
                });
            }
        });
        imageView.startAnimation(animationSet);
    }
}
