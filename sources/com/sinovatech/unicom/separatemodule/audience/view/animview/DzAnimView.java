package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DzAnimView extends FrameLayout {
    Handler handler;
    private boolean isFinishAnima;
    private ImageView ivDz;
    private RelativeLayout rlDz;
    private RotateAnimation rotateAnimation;
    Runnable runnable;

    public DzAnimView(Context context) {
        this(context, null);
    }

    public DzAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DzAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.DzAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                if (DzAnimView.this.isFinishAnima) {
                    DzAnimView.this.rlDz.setVisibility(0);
                    DzAnimView.this.ivDz.startAnimation(DzAnimView.this.rotateAnimation);
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493280, (ViewGroup) this, true);
        this.rlDz = (RelativeLayout) inflate.findViewById(2131298310);
        this.ivDz = (ImageView) inflate.findViewById(2131297334);
        this.rotateAnimation = new RotateAnimation(0.0f, 20.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnimation.setInterpolator(new CycleInterpolator(3.0f));
        this.rotateAnimation.setRepeatCount(1);
        this.rotateAnimation.setDuration(1000L);
        this.rotateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.DzAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                DzAnimView.this.isFinishAnima = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                DzAnimView.this.ivDz.clearAnimation();
                DzAnimView.this.rlDz.setVisibility(8);
                DzAnimView.this.isFinishAnima = true;
            }
        });
    }

    public void startAnimDz() {
        this.handler.post(this.runnable);
    }

    public void stopAnimDz() {
        this.ivDz.clearAnimation();
        this.rlDz.setVisibility(8);
        this.isFinishAnima = true;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
