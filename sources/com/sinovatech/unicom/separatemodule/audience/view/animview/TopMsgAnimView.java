package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TopMsgAnimView extends FrameLayout {
    Handler handler;
    private HdGiftAnimView hdGiftAnimView;
    private LinearLayout llDmOrder;
    private TranslateAnimation orderAnima;
    Runnable orderDmRunnable;
    private float screenWidth;
    private TextView tvOrderNick;
    private View view;
    private ImageView xunzhangImage;

    public TopMsgAnimView(Context context) {
        this(context, null);
    }

    public TopMsgAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TopMsgAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.orderDmRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.TopMsgAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -TopMsgAnimView.this.screenWidth, 0.0f, 0.0f);
                    translateAnimation.setDuration(1500L);
                    TopMsgAnimView.this.llDmOrder.startAnimation(translateAnimation);
                    TopMsgAnimView.this.llDmOrder.setVisibility(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        this.view = LayoutInflater.from(context).inflate(2131493284, (ViewGroup) this, true);
        this.llDmOrder = (LinearLayout) this.view.findViewById(2131297737);
        this.tvOrderNick = (TextView) this.view.findViewById(2131299004);
        this.hdGiftAnimView = (HdGiftAnimView) this.view.findViewById(2131297578);
        this.xunzhangImage = (ImageView) this.view.findViewById(2131299852);
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.orderAnima = new TranslateAnimation(this.screenWidth, 0.0f, 0.0f, 0.0f);
        this.orderAnima.setDuration(1500L);
        this.orderAnima.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.TopMsgAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                TopMsgAnimView.this.llDmOrder.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                TopMsgAnimView.this.handler.postDelayed(TopMsgAnimView.this.orderDmRunnable, 2000L);
            }
        });
    }

    public void startAnimOrder(String str, String str2, String str3) {
        if (this.llDmOrder.getVisibility() == 4) {
            TextView textView = this.tvOrderNick;
            textView.setText(str + "购买了" + str3);
            levelSet(str2, this.xunzhangImage);
            this.llDmOrder.clearAnimation();
            this.llDmOrder.startAnimation(this.orderAnima);
        }
    }

    public void stopAnimOrder() {
        this.llDmOrder.clearAnimation();
        this.llDmOrder.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.orderDmRunnable);
        }
    }

    public void startAnimMsgGift(String str, String str2, int i) {
        HdGiftAnimView hdGiftAnimView = this.hdGiftAnimView;
        if (hdGiftAnimView != null) {
            hdGiftAnimView.startAnimHdGift(str, str2, i);
        }
    }

    public void startAnimMsgGift(String str, String str2, String str3) {
        HdGiftAnimView hdGiftAnimView = this.hdGiftAnimView;
        if (hdGiftAnimView != null) {
            hdGiftAnimView.startAnimHdGift(str, str2, str3);
        }
    }

    public void stopAnimMsgGift() {
        HdGiftAnimView hdGiftAnimView = this.hdGiftAnimView;
        if (hdGiftAnimView != null) {
            hdGiftAnimView.stopAnimHdGift();
        }
    }

    public void levelSet(String str, ImageView imageView) {
        imageView.setVisibility(0);
        if (str.equals("1")) {
            imageView.setImageResource(2131231609);
        } else if (str.equals("2")) {
            imageView.setImageResource(2131231620);
        } else if (str.equals("3")) {
            imageView.setImageResource(2131231575);
        } else if (str.equals("4")) {
            imageView.setImageResource(2131231622);
        } else if (str.equals("5")) {
            imageView.setImageResource(2131231621);
        } else {
            imageView.setVisibility(8);
        }
    }
}
