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
import android.widget.TextView;
import com.sinovatech.unicom.p318ui.GlideApp;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WelcomFloatAnimView extends FrameLayout {
    private Animation animation;
    private ImageView close;
    Handler handler;
    private ImageView img;
    Runnable joinRunnable;
    private FloatLinkListener listener;
    private Context mContext;
    private RelativeLayout root;
    private TextView title;
    private View view;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface FloatLinkListener {
        void clicked();
    }

    public WelcomFloatAnimView(Context context) {
        this(context, null);
    }

    public WelcomFloatAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WelcomFloatAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.joinRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.WelcomFloatAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                WelcomFloatAnimView.this.root.startAnimation(WelcomFloatAnimView.this.animation);
            }
        };
        this.mContext = context;
        initView(context);
    }

    private void initView(Context context) {
        this.view = LayoutInflater.from(context).inflate(2131493009, (ViewGroup) this, true);
        this.img = (ImageView) this.view.findViewById(2131297525);
        this.close = (ImageView) this.view.findViewById(2131297524);
        this.title = (TextView) this.view.findViewById(2131299149);
        this.root = (RelativeLayout) this.view.findViewById(2131298377);
        this.root.setVisibility(4);
        this.animation = AnimationUtils.loadAnimation(context, 2130771987);
        this.animation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.WelcomFloatAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                WelcomFloatAnimView.this.root.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                WelcomFloatAnimView.this.root.setVisibility(4);
            }
        });
        this.close.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.-$$Lambda$WelcomFloatAnimView$av8ODDRzhfWRGbmc75KlumNMXp8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WelcomFloatAnimView.this.stopAnimWelcomFloat();
            }
        });
        this.root.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.-$$Lambda$WelcomFloatAnimView$2ZDwAgGThnr2ngdWVaf7vJ3vG_0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WelcomFloatAnimView.lambda$initView$1(WelcomFloatAnimView.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$1(WelcomFloatAnimView welcomFloatAnimView, View view) {
        FloatLinkListener floatLinkListener = welcomFloatAnimView.listener;
        if (floatLinkListener != null) {
            floatLinkListener.clicked();
        }
    }

    public void startAnimWelcomFloat(String str, String str2, FloatLinkListener floatLinkListener) {
        if (this.root.getVisibility() == 4) {
            this.title.setText(str);
            GlideApp.with(this.mContext).load(str2).into(this.img);
            this.listener = floatLinkListener;
            this.handler.post(this.joinRunnable);
            return;
        }
        stopAnimWelcomFloat();
        startAnimWelcomFloat(str, str2, floatLinkListener);
    }

    public void stopAnimWelcomFloat() {
        this.root.clearAnimation();
        this.root.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.joinRunnable);
        }
    }
}
