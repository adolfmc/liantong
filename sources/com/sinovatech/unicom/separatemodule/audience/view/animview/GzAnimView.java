package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GzAnimView extends FrameLayout {
    private AlphaAnimation alphaAnimation;
    Handler handler;
    private boolean isFinishAnima;
    private ImageView ivDown;
    private ImageView ivEffect;
    private ImageView ivUp;
    private RelativeLayout rlAnim;
    Runnable runnable;
    private ScaleAnimation scaleAnimationDown;
    private ScaleAnimation scaleAnimationUp;

    public GzAnimView(Context context) {
        this(context, null);
    }

    public GzAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GzAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.GzAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                if (GzAnimView.this.isFinishAnima) {
                    GzAnimView.this.rlAnim.setVisibility(0);
                    GzAnimView.this.ivUp.startAnimation(GzAnimView.this.scaleAnimationUp);
                    GzAnimView.this.ivDown.startAnimation(GzAnimView.this.scaleAnimationDown);
                    GzAnimView.this.ivEffect.startAnimation(GzAnimView.this.alphaAnimation);
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493281, (ViewGroup) this, true);
        this.rlAnim = (RelativeLayout) inflate.findViewById(2131298311);
        this.ivUp = (ImageView) inflate.findViewById(2131297337);
        this.ivDown = (ImageView) inflate.findViewById(2131297335);
        this.ivEffect = (ImageView) inflate.findViewById(2131297336);
        this.scaleAnimationUp = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, 1, 0.5f, 1, 0.5f);
        this.scaleAnimationUp.setRepeatCount(3);
        this.scaleAnimationUp.setDuration(1000L);
        this.scaleAnimationDown = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f, 1, 0.5f, 1, 0.5f);
        this.scaleAnimationDown.setRepeatCount(3);
        this.scaleAnimationDown.setDuration(1000L);
        this.alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        this.alphaAnimation.setDuration(1000L);
        this.alphaAnimation.setRepeatCount(3);
        this.alphaAnimation.setRepeatMode(1);
        this.scaleAnimationDown.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.GzAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                GzAnimView.this.isFinishAnima = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                GzAnimView.this.ivUp.clearAnimation();
                GzAnimView.this.ivDown.clearAnimation();
                GzAnimView.this.ivEffect.clearAnimation();
                GzAnimView.this.rlAnim.setVisibility(8);
                GzAnimView.this.isFinishAnima = true;
            }
        });
    }

    public void startAnimGz() {
        this.handler.post(this.runnable);
    }

    public void stopAnimGz() {
        this.ivUp.clearAnimation();
        this.ivDown.clearAnimation();
        this.ivEffect.clearAnimation();
        this.rlAnim.setVisibility(8);
        this.isFinishAnima = true;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
