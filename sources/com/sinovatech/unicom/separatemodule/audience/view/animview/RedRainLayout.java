package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.BaseInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceInnerRecylerView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.RedPacketView;
import java.util.Random;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedRainLayout extends FrameLayout {
    private static final int mInitY = 60;
    int amount;
    private ChoujiangListener choujiangListener;
    private int mCurrentAccount;
    private int mDelay;
    private int mDuration;
    private FrameLayout mFrameLayout;
    Handler mHandler;
    private TimeInterpolator[] mInterpolators;
    private int[] mSize;
    private int mTotalAccount;
    private int mTotalAmount;
    int number;
    Random random;
    Runnable runnable;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    interface ChoujiangListener {
        void requestChoujiang();
    }

    static /* synthetic */ int access$008(RedRainLayout redRainLayout) {
        int i = redRainLayout.mCurrentAccount;
        redRainLayout.mCurrentAccount = i + 1;
        return i;
    }

    static /* synthetic */ int access$316(RedRainLayout redRainLayout, float f) {
        int i = (int) (redRainLayout.mTotalAmount + f);
        redRainLayout.mTotalAmount = i;
        return i;
    }

    public RedRainLayout(Context context) {
        this(context, null);
    }

    public RedRainLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RedRainLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTotalAccount = 20;
        this.mDuration = 3000;
        this.mDelay = 500;
        this.random = new Random();
        this.amount = 10;
        this.mHandler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.RedRainLayout.1
            @Override // java.lang.Runnable
            public void run() {
                if (RedRainLayout.this.mCurrentAccount > RedRainLayout.this.mTotalAccount) {
                    Log.i("lln", "红包雨下完了");
                    AudienceInnerRecylerView.isInnerRecylerView = false;
                    if (RedRainLayout.this.choujiangListener == null || RedRainLayout.this.mTotalAmount < RedRainLayout.this.amount * RedRainLayout.this.number) {
                        return;
                    }
                    RedRainLayout.this.choujiangListener.requestChoujiang();
                    RedRainLayout.this.mHandler.removeCallbacks(RedRainLayout.this.runnable);
                    return;
                }
                RedRainLayout.access$008(RedRainLayout.this);
                final RedPacketView redPacketView = new RedPacketView(RedRainLayout.this.getContext());
                redPacketView.setAmount(RedRainLayout.this.amount);
                RedRainLayout.this.addView(redPacketView);
                float initialX = RedRainLayout.this.getInitialX();
                redPacketView.setX(initialX);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.play(ObjectAnimator.ofFloat(redPacketView, "translationY", -RedPacketView.dpToPixel(60.0f), RedRainLayout.this.mSize[1] + RedPacketView.dpToPixel(60.0f))).with(ObjectAnimator.ofFloat(redPacketView, "translationX", initialX, initialX - UIUtils.dip2px(250.0f)));
                animatorSet.setInterpolator(RedRainLayout.this.mInterpolators[0]);
                animatorSet.setDuration(RedRainLayout.this.mDuration);
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.RedRainLayout.1.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        RedRainLayout.this.removeView(redPacketView);
                    }
                });
                redPacketView.setRedPackClick(new RedPacketView.RedPackClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.RedRainLayout.1.2
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.animview.RedPacketView.RedPackClickListener
                    public void click() {
                        if (redPacketView.isClicked()) {
                            RedRainLayout.access$316(RedRainLayout.this, redPacketView.getAmount());
                            if (RedRainLayout.this.mTotalAmount >= RedRainLayout.this.amount * RedRainLayout.this.number) {
                                Log.i("requestChoujiang", "总金额==" + RedRainLayout.this.mTotalAmount);
                                RedRainLayout.this.mHandler.removeCallbacks(RedRainLayout.this.runnable);
                                Log.i("requestChoujiang", "点够了 去请求");
                                if (RedRainLayout.this.choujiangListener == null || !AudienceInnerRecylerView.isInnerRecylerView) {
                                    return;
                                }
                                Log.d("requestChoujiang", "跳去红包雨抽奖");
                                RedRainLayout.this.choujiangListener.requestChoujiang();
                                AudienceInnerRecylerView.isInnerRecylerView = false;
                            }
                        }
                    }
                });
                animatorSet.start();
                RedRainLayout.this.mHandler.postDelayed(this, RedRainLayout.this.mDelay);
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        this.mSize = RedPacketView.getWindowWidthAndHeight(context);
        initInterpolator();
        this.number = this.random.nextInt(3) + 3;
        Log.i("lln", "随机数==" + this.number);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float getInitialX() {
        return new Random().nextInt((int) (this.mSize[0] + RedPacketView.dpToPixel(150.0f)));
    }

    private void initInterpolator() {
        this.mInterpolators = new BaseInterpolator[]{new LinearInterpolator()};
    }

    public void setTotalAccount(int i) {
        this.mTotalAccount = i;
    }

    public void startRedRainAnimation() {
        AudienceInnerRecylerView.isInnerRecylerView = true;
        this.mCurrentAccount = 0;
        this.mTotalAmount = 0;
        this.number = this.random.nextInt(3) + 3;
        this.mHandler.postDelayed(this.runnable, this.mDelay);
    }

    public void stop() {
        this.mHandler.removeCallbacks(this.runnable);
    }

    public void setChoujiangListener(ChoujiangListener choujiangListener) {
        this.choujiangListener = choujiangListener;
    }
}
