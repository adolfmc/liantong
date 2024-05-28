package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PcAnimView extends FrameLayout {
    Handler handler;
    private boolean isFinishAnima;
    private ImageView ivPc;
    Runnable runnable;
    private Animation translateAnimation;

    public PcAnimView(Context context) {
        this(context, null);
    }

    public PcAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PcAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.PcAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                if (PcAnimView.this.isFinishAnima) {
                    PcAnimView.this.ivPc.setVisibility(0);
                    PcAnimView.this.ivPc.startAnimation(PcAnimView.this.translateAnimation);
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        this.ivPc = (ImageView) LayoutInflater.from(context).inflate(2131493283, (ViewGroup) this, true).findViewById(2131297340);
        this.translateAnimation = AnimationUtils.loadAnimation(context, 2130771985);
        this.translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.PcAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                Log.i("lln", "跑车动画开始");
                PcAnimView.this.isFinishAnima = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Log.i("lln", "跑车动画结束");
                PcAnimView.this.ivPc.clearAnimation();
                PcAnimView.this.ivPc.setVisibility(8);
                PcAnimView.this.isFinishAnima = true;
            }
        });
    }

    public void startAnimPc() {
        this.handler.post(this.runnable);
    }

    public void stopAnimPc() {
        this.ivPc.clearAnimation();
        this.ivPc.setVisibility(8);
        this.isFinishAnima = true;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
