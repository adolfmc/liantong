package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import java.util.List;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ServiceTopMenuView extends LinearLayout {
    private AppCompatActivity activityContext;
    private LinearLayout contentLayout;
    private LayoutInflater inflate;

    public ServiceTopMenuView(@NonNull Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        init();
    }

    public ServiceTopMenuView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        init();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.contentLayout = (LinearLayout) findViewById(2131298749);
    }

    private void init() {
        this.inflate = LayoutInflater.from(this.activityContext);
    }

    public void setData(List<MenuEntity> list) {
        try {
            this.contentLayout.removeAllViews();
            int size = list.size();
            if (size > 4) {
                size = 4;
            }
            for (int i = 0; i < size; i++) {
                final MenuEntity menuEntity = list.get(i);
                LinearLayout linearLayout = (LinearLayout) this.inflate.inflate(2131493236, (ViewGroup) this.contentLayout, false);
                this.contentLayout.addView(linearLayout);
                linearLayout.setContentDescription(menuEntity.getMenuTitle());
                linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.ServiceTopMenuView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        IntentManager.generateIntentAndGo(ServiceTopMenuView.this.activityContext, menuEntity, "get");
                        StatisticsUploadUtils.upload(ServiceTopMenuView.this.activityContext, "22", "服务-智慧推荐", "按钮", "0", menuEntity.getMenuTitle(), menuEntity.getMenuURL());
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                GlideApp.with((FragmentActivity) this.activityContext).load(menuEntity.getMenuIconURL()).into((ImageView) linearLayout.findViewById(2131298751));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadLog(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("actId");
            String optString2 = jSONObject.optString("goodsId");
            String optString3 = jSONObject.optString("actType");
            StatisticsUploadUtils.upload(this.activityContext, "H122", "首页-今日主推", "广告", "今日主推", jSONObject.optString("title"), "", "", optString3, optString, optString2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
