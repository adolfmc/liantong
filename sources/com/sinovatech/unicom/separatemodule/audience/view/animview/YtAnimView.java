package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YtAnimView extends FrameLayout {
    Handler handler;
    private boolean isFinishAnima;
    private ImageView ivYt;
    private RelativeLayout rlYt;
    Runnable runnable;
    private Animation translateAnimation;

    public YtAnimView(Context context) {
        this(context, null);
    }

    public YtAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YtAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.YtAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                if (YtAnimView.this.isFinishAnima) {
                    YtAnimView.this.rlYt.setVisibility(0);
                    YtAnimView.this.ivYt.startAnimation(YtAnimView.this.translateAnimation);
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493285, (ViewGroup) this, true);
        this.rlYt = (RelativeLayout) inflate.findViewById(2131298312);
        this.ivYt = (ImageView) inflate.findViewById(2131297341);
        this.translateAnimation = AnimationUtils.loadAnimation(context, 2130771985);
        this.translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.YtAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                YtAnimView.this.isFinishAnima = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                YtAnimView.this.ivYt.clearAnimation();
                YtAnimView.this.rlYt.setVisibility(8);
                YtAnimView.this.isFinishAnima = true;
            }
        });
    }

    public void startAnimYt() {
        this.handler.post(this.runnable);
    }

    public void stopAnimYt() {
        this.ivYt.clearAnimation();
        this.rlYt.setVisibility(8);
        this.isFinishAnima = true;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
