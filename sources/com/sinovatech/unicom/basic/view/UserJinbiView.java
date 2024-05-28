package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.MyUnicomEntity;
import com.sinovatech.unicom.basic.p314po.UnicomBottomEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserJinbiView extends LinearLayout {
    private AppCompatActivity activityContext;
    private Typeface typeface;

    public UserJinbiView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        setOrientation(0);
        init();
    }

    public UserJinbiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        setOrientation(0);
        init();
    }

    private void init() {
        this.typeface = Typeface.createFromAsset(this.activityContext.getAssets(), "bebas_regular.otf");
        setOrientation(0);
        setPadding(UIUtils.dip2px(this.activityContext, 10.0f), 0, UIUtils.dip2px(this.activityContext, 5.0f), 0);
    }

    public void setData(MyUnicomEntity myUnicomEntity) {
        try {
            removeAllViews();
            List<UnicomBottomEntity> bottomEntityList = myUnicomEntity.getBottomEntityList();
            if (bottomEntityList.size() == 0) {
                if (UserManager.getInstance().isYiwang()) {
                    UnicomBottomEntity unicomBottomEntity = new UnicomBottomEntity();
                    UnicomBottomEntity unicomBottomEntity2 = new UnicomBottomEntity();
                    UnicomBottomEntity unicomBottomEntity3 = new UnicomBottomEntity();
                    new UnicomBottomEntity();
                    unicomBottomEntity.setTitle("我的礼包");
                    unicomBottomEntity2.setTitle("我的收益");
                    unicomBottomEntity3.setTitle("互联网积分");
                    unicomBottomEntity.setValue("-");
                    unicomBottomEntity2.setValue("-");
                    unicomBottomEntity3.setValue("-");
                    bottomEntityList.add(unicomBottomEntity);
                    bottomEntityList.add(unicomBottomEntity2);
                    bottomEntityList.add(unicomBottomEntity3);
                } else {
                    UnicomBottomEntity unicomBottomEntity4 = new UnicomBottomEntity();
                    UnicomBottomEntity unicomBottomEntity5 = new UnicomBottomEntity();
                    UnicomBottomEntity unicomBottomEntity6 = new UnicomBottomEntity();
                    UnicomBottomEntity unicomBottomEntity7 = new UnicomBottomEntity();
                    unicomBottomEntity4.setTitle("我的金币");
                    unicomBottomEntity5.setTitle("我的钱包");
                    unicomBottomEntity6.setTitle("我的礼包");
                    unicomBottomEntity7.setTitle("累计收益");
                    unicomBottomEntity4.setValue("-");
                    unicomBottomEntity5.setValue("-");
                    unicomBottomEntity6.setValue("-");
                    unicomBottomEntity7.setValue("-");
                    bottomEntityList.add(unicomBottomEntity4);
                    bottomEntityList.add(unicomBottomEntity5);
                    bottomEntityList.add(unicomBottomEntity6);
                    bottomEntityList.add(unicomBottomEntity7);
                }
            }
            for (int i = 0; i < bottomEntityList.size(); i++) {
                final UnicomBottomEntity unicomBottomEntity8 = bottomEntityList.get(i);
                LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.activityContext).inflate(2131493525, (ViewGroup) this, false);
                TextView textView = (TextView) linearLayout.findViewById(2131299394);
                TextView textView2 = (TextView) linearLayout.findViewById(2131299395);
                TextView textView3 = (TextView) linearLayout.findViewById(2131299398);
                ImageView imageView = (ImageView) linearLayout.findViewById(2131299396);
                ImageView imageView2 = (ImageView) linearLayout.findViewById(2131299397);
                if (!TextUtils.isEmpty(unicomBottomEntity8.getAddNum()) && !"0".equals(unicomBottomEntity8.getAddNum())) {
                    textView2.setText(unicomBottomEntity8.getAddNum());
                    textView2.setVisibility(0);
                } else {
                    textView2.setVisibility(8);
                }
                textView.setTypeface(this.typeface);
                textView.setText(unicomBottomEntity8.getValue());
                textView3.setText(LanguageUtil.getInstance().getText(unicomBottomEntity8.getTitle(), textView3));
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.UserJinbiView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if ("我的礼包".equals(unicomBottomEntity8.getTitle())) {
                            UserFragment.wodelibaoClick = true;
                        }
                        IntentManager.gotoWebViewActivity(UserJinbiView.this.activityContext, unicomBottomEntity8.getLinkurl(), unicomBottomEntity8.getTitle());
                        StatisticsUploadUtils.upload(UserJinbiView.this.activityContext, "51", "我的-头部", "按钮", "0", unicomBottomEntity8.getTitle(), "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                addView(linearLayout);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
