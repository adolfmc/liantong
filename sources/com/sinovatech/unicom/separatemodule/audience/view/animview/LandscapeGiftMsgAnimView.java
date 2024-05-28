package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
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
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import java.util.Iterator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LandscapeGiftMsgAnimView extends FrameLayout {
    private Animation animation1;
    private Context context;
    Handler handler;
    private ImageView ivGiftIcon;
    private ImageView ivMedal;
    Runnable joinRunnable;
    private RelativeLayout llJoin;
    private TextView tvGiftNum;
    private TextView tvGiftNumGone;
    private TextView tvUserName;
    private TextView tvUserNameGone;
    private View view;

    public LandscapeGiftMsgAnimView(Context context) {
        this(context, null);
    }

    public LandscapeGiftMsgAnimView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LandscapeGiftMsgAnimView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.handler = new Handler();
        this.joinRunnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.LandscapeGiftMsgAnimView.2
            @Override // java.lang.Runnable
            public void run() {
                LandscapeGiftMsgAnimView.this.llJoin.setVisibility(0);
                LandscapeGiftMsgAnimView.this.llJoin.startAnimation(LandscapeGiftMsgAnimView.this.animation1);
            }
        };
        initView(context);
    }

    private void initView(final Context context) {
        this.view = LayoutInflater.from(context).inflate(2131493226, (ViewGroup) this, true);
        this.context = context;
        this.llJoin = (RelativeLayout) this.view.findViewById(2131298323);
        this.llJoin.setVisibility(4);
        this.ivMedal = (ImageView) this.view.findViewById(2131297436);
        this.tvUserName = (TextView) this.view.findViewById(2131299130);
        this.tvGiftNumGone = (TextView) this.view.findViewById(2131298963);
        this.tvUserNameGone = (TextView) this.view.findViewById(2131299131);
        this.ivGiftIcon = (ImageView) this.view.findViewById(2131297388);
        this.tvGiftNum = (TextView) this.view.findViewById(2131298961);
        this.animation1 = AnimationUtils.loadAnimation(context, 2130771983);
        this.animation1.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.animview.LandscapeGiftMsgAnimView.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                LandscapeGiftMsgAnimView.this.llJoin.setVisibility(0);
                LandscapeGiftMsgAnimView.this.tvGiftNum.setAnimation(AnimationUtils.loadAnimation(context, 2130772002));
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                LandscapeGiftMsgAnimView.this.llJoin.setVisibility(4);
            }
        });
    }

    public void startAnimHdGift(String str, String str2, String str3, String str4, String str5) {
        if (this.llJoin.getVisibility() == 4) {
            this.tvUserName.setText(str);
            this.tvUserNameGone.setText(str);
            levelSet(str2, this.ivMedal);
            this.tvGiftNum.setText(str4);
            this.tvGiftNumGone.setText(str4);
            if (TextUtils.isEmpty(str3)) {
                GlideApp.with(this.context).load(getGiftIcon(str5)).into(this.ivGiftIcon);
            } else {
                GlideApp.with(this.context).load(str3).into(this.ivGiftIcon);
            }
            this.handler.post(this.joinRunnable);
        }
    }

    public void stopAnimHdGift() {
        this.llJoin.clearAnimation();
        this.llJoin.setVisibility(4);
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacks(this.joinRunnable);
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

    private String getGiftIcon(String str) {
        GiftEntity giftEntity;
        Iterator<GiftEntity> it = CacheDataCenter.getInstance().getGiftList().iterator();
        while (true) {
            if (!it.hasNext()) {
                giftEntity = null;
                break;
            }
            giftEntity = it.next();
            if (giftEntity.getGiftCode().equals(str)) {
                break;
            }
        }
        if (giftEntity != null) {
            if (TextUtils.isEmpty(giftEntity.getImgFileChat())) {
                return giftEntity.getGiftImgSrc();
            }
            return giftEntity.getImgFileChat();
        }
        return str;
    }
}
