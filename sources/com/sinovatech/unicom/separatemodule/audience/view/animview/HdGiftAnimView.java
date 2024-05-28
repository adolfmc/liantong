package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.p318ui.GlideApp;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HdGiftAnimView extends FrameLayout {
    private Context context;
    Handler handler;
    private TranslateAnimation hdGiftAnima;
    Runnable hdGiftRunnable;
    private boolean isFinishAnima;
    private ImageView ivGift;
    private ImageView levleImage;
    private RelativeLayout llDmHdGift;
    Runnable runnable;
    private TextView tvSender;

    public HdGiftAnimView(Context context) {
        this(context, null);
    }

    public HdGiftAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HdGiftAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isFinishAnima = true;
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.HdGiftAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                HdGiftAnimView.this.llDmHdGift.setVisibility(0);
                HdGiftAnimView.this.llDmHdGift.startAnimation(HdGiftAnimView.this.hdGiftAnima);
            }
        };
        this.hdGiftRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.HdGiftAnimView.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -HdGiftAnimView.this.getResources().getDisplayMetrics().widthPixels, 0.0f, 0.0f);
                    translateAnimation.setDuration(1500L);
                    HdGiftAnimView.this.llDmHdGift.startAnimation(translateAnimation);
                    HdGiftAnimView.this.llDmHdGift.setVisibility(4);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.HdGiftAnimView.3.1
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation) {
                            HdGiftAnimView.this.isFinishAnima = true;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2131493223, (ViewGroup) this, true);
        this.context = context;
        this.llDmHdGift = (RelativeLayout) inflate.findViewById(2131297736);
        this.tvSender = (TextView) inflate.findViewById(2131298966);
        this.ivGift = (ImageView) inflate.findViewById(2131297392);
        this.levleImage = (ImageView) inflate.findViewById(2131299851);
        this.hdGiftAnima = new TranslateAnimation(context.getResources().getDisplayMetrics().widthPixels, 0.0f, 0.0f, 0.0f);
        this.hdGiftAnima.setDuration(1500L);
        this.hdGiftAnima.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.HdGiftAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                HdGiftAnimView.this.llDmHdGift.setVisibility(0);
                HdGiftAnimView.this.isFinishAnima = false;
                Log.i("lln", "动画开始");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                Log.i("lln", "结束");
                HdGiftAnimView.this.handler.postDelayed(HdGiftAnimView.this.hdGiftRunnable, 2000L);
            }
        });
    }

    public void startAnimHdGift(String str, String str2, int i) {
        if (this.isFinishAnima) {
            if (i == 10005) {
                this.ivGift.setImageResource(2131230898);
            } else if (i == 10006) {
                this.ivGift.setImageResource(2131230899);
            } else if (i == 10007) {
                this.ivGift.setImageResource(2131230900);
            } else if (i == 10008) {
                this.ivGift.setImageResource(2131230901);
            }
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.tvSender.setText(str);
            levelSet(str2, this.levleImage);
            this.handler.post(this.runnable);
        }
    }

    public void startAnimHdGift(String str, String str2, String str3) {
        if (this.isFinishAnima) {
            GlideApp.with(this.context).load(str3).into(this.ivGift);
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.tvSender.setText(str);
            levelSet(str2, this.levleImage);
            this.handler.post(this.runnable);
        }
    }

    public void stopAnimHdGift() {
        this.llDmHdGift.clearAnimation();
        this.llDmHdGift.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.runnable);
        }
    }

    private void levelSet(String str, ImageView imageView) {
        imageView.setVisibility(0);
        if ("1".equals(str)) {
            imageView.setImageResource(2131231609);
        } else if ("2".equals(str)) {
            imageView.setImageResource(2131231620);
        } else if ("3".equals(str)) {
            imageView.setImageResource(2131231575);
        } else if ("4".equals(str)) {
            imageView.setImageResource(2131231622);
        } else if ("5".equals(str)) {
            imageView.setImageResource(2131231621);
        } else {
            imageView.setVisibility(8);
        }
    }
}
