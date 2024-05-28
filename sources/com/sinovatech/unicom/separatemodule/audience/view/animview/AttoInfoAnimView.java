package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AttoInfoAnimView extends FrameLayout {
    Animation animation1;
    Handler handler;
    Runnable joinRunnable;
    private LinearLayout llComment;
    private TextView tvComment;
    private View view;

    public AttoInfoAnimView(Context context) {
        this(context, null);
    }

    public AttoInfoAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AttoInfoAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.joinRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.AttoInfoAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                AttoInfoAnimView.this.llComment.setVisibility(0);
                AttoInfoAnimView.this.llComment.startAnimation(AttoInfoAnimView.this.animation1);
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        this.view = LayoutInflater.from(context).inflate(2131492970, (ViewGroup) this, true);
        this.llComment = (LinearLayout) this.view.findViewById(2131297704);
        this.tvComment = (TextView) this.view.findViewById(2131298915);
        this.llComment.setVisibility(4);
        this.animation1 = AnimationUtils.loadAnimation(context, 2130771982);
        this.animation1.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.AttoInfoAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                AttoInfoAnimView.this.llComment.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AttoInfoAnimView.this.llComment.setVisibility(4);
            }
        });
    }

    public void startAnimAttoInfo(String str, String str2) {
        if (this.llComment.getVisibility() == 4) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(-6070), 0, str.length(), 33);
            this.tvComment.setText(new SpannableStringBuilder().append((CharSequence) spannableString).append((CharSequence) str2));
            this.handler.post(this.joinRunnable);
            return;
        }
        stopAnimAttoInfo();
        startAnimAttoInfo(str, str2);
    }

    public void stopAnimAttoInfo() {
        this.llComment.clearAnimation();
        this.llComment.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.joinRunnable);
        }
    }
}
