package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MsgAnimView extends FrameLayout {
    Animation animation1;
    private TranslateAnimation browseAnima;
    Runnable goodsDmRunnable;
    Handler handler;
    private ImageView ivGoodsDm_Level;
    private ImageView ivLevel;
    private ImageView ivMgr;
    private ImageView ivMgrG;
    Runnable joinRunnable;
    private LinearLayout llBrowse;
    private LinearLayout llJoin;
    private float screenWidth;
    private TextView tvGoodsDm_Msg;
    private TextView tvGoodsDm_Nick;
    private TextView tvMsg;
    private TextView tvNick;
    private View view;

    public MsgAnimView(Context context) {
        this(context, null);
    }

    public MsgAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MsgAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.joinRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView.3
            @Override // java.lang.Runnable
            public void run() {
                MsgAnimView.this.llJoin.setVisibility(0);
                MsgAnimView.this.llJoin.setBackgroundResource(2131231067);
                MsgAnimView.this.llJoin.startAnimation(MsgAnimView.this.animation1);
            }
        };
        this.goodsDmRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, -MsgAnimView.this.screenWidth, 0.0f, 0.0f);
                    translateAnimation.setDuration(1500L);
                    MsgAnimView.this.llBrowse.startAnimation(translateAnimation);
                    MsgAnimView.this.llBrowse.setVisibility(4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        initView(context);
    }

    private void initView(Context context) {
        this.view = LayoutInflater.from(context).inflate(2131493282, (ViewGroup) this, true);
        this.llJoin = (LinearLayout) this.view.findViewById(2131297739);
        this.llBrowse = (LinearLayout) this.view.findViewById(2131297738);
        this.llJoin.setVisibility(4);
        this.llBrowse.setVisibility(4);
        this.ivLevel = (ImageView) this.view.findViewById(2131297421);
        this.tvNick = (TextView) this.view.findViewById(2131299003);
        this.tvMsg = (TextView) this.view.findViewById(2131299002);
        this.ivMgr = (ImageView) this.view.findViewById(2131297440);
        this.ivMgr.setVisibility(8);
        this.ivGoodsDm_Level = (ImageView) this.view.findViewById(2131297422);
        this.tvGoodsDm_Nick = (TextView) this.view.findViewById(2131299006);
        this.tvGoodsDm_Msg = (TextView) this.view.findViewById(2131299005);
        this.ivMgrG = (ImageView) this.view.findViewById(2131297439);
        this.ivMgrG.setVisibility(8);
        this.animation1 = AnimationUtils.loadAnimation(context, 2130771984);
        this.animation1.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                MsgAnimView.this.llJoin.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MsgAnimView.this.llJoin.setVisibility(4);
            }
        });
        this.screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.browseAnima = new TranslateAnimation(this.screenWidth, 0.0f, 0.0f, 0.0f);
        this.browseAnima.setDuration(1000L);
        this.browseAnima.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                MsgAnimView.this.llBrowse.setVisibility(0);
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                MsgAnimView.this.handler.postDelayed(MsgAnimView.this.goodsDmRunnable, 2000L);
            }
        });
    }

    public void startAnimJoinOrFocus(String str, String str2, boolean z, boolean z2) {
        if (this.llJoin.getVisibility() == 4) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.tvNick.setText(str);
            if (z2) {
                this.tvMsg.setText("进入了直播间");
            } else {
                this.tvMsg.setText("关注了主播");
            }
            levelSet(str2, this.ivLevel, this.tvNick);
            this.ivMgr.setVisibility(z ? 0 : 8);
            this.handler.post(this.joinRunnable);
        } else if (TextUtils.isEmpty(str)) {
        } else {
            this.llJoin.setVisibility(4);
        }
    }

    public void stopAnimJoinOrFocus() {
        this.llJoin.clearAnimation();
        this.llJoin.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.joinRunnable);
        }
    }

    public void startAnimBrowseGoods(String str, String str2, boolean z, String str3) {
        if (this.llBrowse.getVisibility() != 4 || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        this.tvGoodsDm_Nick.setText(str);
        TextView textView = this.tvGoodsDm_Msg;
        textView.setText("正在去下单" + str3);
        levelSet(str2, this.ivGoodsDm_Level, this.tvGoodsDm_Nick);
        this.ivMgrG.setVisibility(z ? 0 : 8);
        this.llBrowse.clearAnimation();
        this.llBrowse.startAnimation(this.browseAnima);
    }

    public void stopAnimBrowseGoods() {
        this.llBrowse.clearAnimation();
        this.llBrowse.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.goodsDmRunnable);
        }
    }

    public void levelSet(String str, ImageView imageView, TextView textView) {
        String str2 = "#FFFFFF";
        if ("1".equals(str)) {
            imageView.setVisibility(8);
            str2 = "#72fffa";
        } else if ("2".equals(str)) {
            imageView.setImageResource(2131231620);
            str2 = "#72fffa";
        } else if ("3".equals(str)) {
            imageView.setImageResource(2131231575);
            str2 = "#ffe84a";
        } else if ("4".equals(str)) {
            imageView.setImageResource(2131231622);
            str2 = "#b5d8ff";
        } else if ("5".equals(str)) {
            imageView.setImageResource(2131231621);
            str2 = "#ff92f1";
        } else {
            imageView.setVisibility(8);
        }
        textView.setTextColor(Color.parseColor(str2));
    }
}
