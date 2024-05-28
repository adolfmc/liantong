package com.sinovatech.unicom.basic.p315ui.home.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeUnLoginView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeUnLoginView extends LinearLayout {
    private static final String TAG = "HomeUnLoginView";
    private Activity activityContext;
    private TextView mTvLogin;
    private View mView;

    public HomeUnLoginView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        try {
            this.activityContext = (Activity) context;
            this.mView = LayoutInflater.from(context).inflate(2131493187, this);
            this.mTvLogin = (TextView) this.mView.findViewById(2131297205);
            this.mView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.HomeUnLoginView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    HomeUnLoginView.this.activityContext.startActivity(new Intent(HomeUnLoginView.this.activityContext, LoginBindActivity.class));
                    try {
                        PvCurrencyLogUtils.pvLogMainDJ("1120101", "", "", "", "首页-背景-未登录点击");
                        UnicomHomeLogUtils.getInstance().clickLog("1120101", "首页-背景-未登录点击");
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_UNLOGIN, new HomeLogEntity("1120101", "首页未登录按钮"));
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化卡片未登录布局异常:" + e.getMessage());
        }
    }
}
