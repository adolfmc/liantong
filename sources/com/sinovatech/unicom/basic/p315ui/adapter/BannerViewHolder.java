package com.sinovatech.unicom.basic.p315ui.adapter;

import android.content.Context;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.eventbus.TecentEventBus;
import com.sinovatech.unicom.basic.p314po.AdvertiseEntity;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.zhpan.bannerview.holder.ViewHolder;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.BannerViewHolder */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BannerViewHolder implements ViewHolder<AdvertiseEntity> {
    private AppCompatActivity activityContext;
    private ImageView bannerImageView;
    private int type;

    public BannerViewHolder(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public BannerViewHolder(AppCompatActivity appCompatActivity, int i) {
        this.activityContext = appCompatActivity;
        this.type = i;
    }

    @Override // com.zhpan.bannerview.holder.ViewHolder
    public View createView(ViewGroup viewGroup, Context context, int i) {
        View inflate = LayoutInflater.from(context).inflate(2131493217, viewGroup, false);
        this.bannerImageView = (ImageView) inflate.findViewById(2131297160);
        ImageView imageView = (ImageView) inflate.findViewById(2131297161);
        ImageView imageView2 = (ImageView) inflate.findViewById(2131297162);
        if (this.type == 1) {
            imageView.setVisibility(0);
            imageView2.setVisibility(0);
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.BannerViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    long currentTimeMillis = System.currentTimeMillis();
                    ConfigManager configManager = new ConfigManager(BannerViewHolder.this.activityContext);
                    configManager.setLastClickTime(currentTimeMillis + "");
                    EventBusUtils.post(new TecentEventBus(EventBusUtils.EVENT_TMS_HomeBanner_Update));
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        return inflate;
    }

    @Override // com.zhpan.bannerview.holder.ViewHolder
    public void onBind(Context context, AdvertiseEntity advertiseEntity, int i, int i2) {
        if (advertiseEntity.getAdvertiseTitle() != null && !advertiseEntity.getAdvertiseTitle().isEmpty()) {
            this.bannerImageView.setContentDescription(advertiseEntity.getAdvertiseTitle());
        }
        if (TextUtils.isEmpty(advertiseEntity.getAdvertiseImageURL())) {
            return;
        }
        GlideApp.with(App.getInstance()).asDrawable().placeholder(2131231244).load(advertiseEntity.getAdvertiseImageURL()).into(this.bannerImageView);
        try {
            if (advertiseEntity.getAdvertiseTargetURL().contains("androidId")) {
                advertiseEntity.setAdvertiseTargetURL(advertiseEntity.getAdvertiseTargetURL() + "&pvType=banner");
                StatisticsUploadUtils.uploadRealTimeBeiDong(this.activityContext, "hzyl0001", "首页banner广告", "入口曝光", advertiseEntity.getAdvertiseId(), advertiseEntity.getAdvertiseTitle(), advertiseEntity.getAdvertiseTargetURL());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
