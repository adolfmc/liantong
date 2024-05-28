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
import com.sinovatech.unicom.common.UIUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CommonLiveAnimView extends FrameLayout {
    private AlphaAnimation alphaAnimation;
    Handler handler;
    private boolean isFinishAnima;
    private ImageView ivAnim01;
    private ImageView ivAnim02;
    Runnable runnable;
    private ScaleAnimation scaleAnimation;

    public CommonLiveAnimView(Context context) {
        this(context, null);
    }

    public CommonLiveAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonLiveAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CommonLiveAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                CommonLiveAnimView.this.ivAnim01.setVisibility(0);
                CommonLiveAnimView.this.ivAnim02.setVisibility(0);
                CommonLiveAnimView.this.ivAnim01.startAnimation(CommonLiveAnimView.this.scaleAnimation);
                CommonLiveAnimView.this.ivAnim02.startAnimation(CommonLiveAnimView.this.alphaAnimation);
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493279, (ViewGroup) this, true);
        this.ivAnim01 = (ImageView) inflate.findViewById(2131297338);
        this.ivAnim02 = (ImageView) inflate.findViewById(2131297339);
        this.scaleAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, 1, 1.0f, 1, 1.0f);
        this.scaleAnimation.setDuration(600L);
        this.alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        this.alphaAnimation.setDuration(800L);
        this.alphaAnimation.setRepeatCount(2);
        this.alphaAnimation.setRepeatMode(1);
        this.alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.CommonLiveAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                CommonLiveAnimView.this.isFinishAnima = false;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CommonLiveAnimView.this.ivAnim01.clearAnimation();
                CommonLiveAnimView.this.ivAnim02.clearAnimation();
                CommonLiveAnimView.this.ivAnim01.setVisibility(8);
                CommonLiveAnimView.this.ivAnim02.setVisibility(8);
                CommonLiveAnimView.this.isFinishAnima = true;
            }
        });
    }

    public void startAnimCommon(int i) {
        UIUtils.logD("startAnimCommon", "---->" + this.isFinishAnima);
        if (this.isFinishAnima) {
            if (i == 10001) {
                this.ivAnim01.setImageResource(2131231863);
            } else if (i == 10002) {
                this.ivAnim01.setImageResource(2131231547);
            } else if (i == 10005) {
                this.ivAnim01.setImageResource(2131231538);
            } else if (i == 10006) {
                this.ivAnim01.setImageResource(2131231546);
            }
            this.handler.post(this.runnable);
        }
    }

    public void stopAnimCommon() {
        this.ivAnim01.clearAnimation();
        this.ivAnim02.clearAnimation();
        this.ivAnim01.setVisibility(8);
        this.ivAnim02.setVisibility(8);
        this.isFinishAnima = true;
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }
}
