package com.sinovatech.unicom.basic.p315ui.home.view;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.sinovatech.unicom.basic.p314po.HeaderChildEntity;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.view.HomeHeaderItemLayout;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeHuaFeiView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeHuaFeiView extends LinearLayout {
    private static final String TAG = "HomeHuaFeiView";
    private Activity activityContext;
    private HeaderEntity headerEntity;
    private HomeHeaderItemLayout[] hedaderLayuts;
    List<HeaderChildEntity> list;
    private View mView;
    private Typeface typeface;

    public HomeHuaFeiView(Activity activity, HeaderEntity headerEntity) {
        super(activity);
        this.list = new ArrayList();
        init(activity, headerEntity, this.typeface);
        this.typeface = Typeface.createFromAsset(activity.getAssets(), "bebas_regular.otf");
    }

    private void init(Activity activity, HeaderEntity headerEntity, Typeface typeface) {
        try {
            this.activityContext = activity;
            this.headerEntity = headerEntity;
            this.typeface = typeface;
            this.mView = LayoutInflater.from(this.activityContext).inflate(2131493171, this);
            this.hedaderLayuts = new HomeHeaderItemLayout[]{(HomeHeaderItemLayout) this.mView.findViewById(2131297120), (HomeHeaderItemLayout) this.mView.findViewById(2131297121), (HomeHeaderItemLayout) this.mView.findViewById(2131297122), (HomeHeaderItemLayout) this.mView.findViewById(2131297123)};
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化首页卡片话费信息异常" + e.getMessage());
        }
    }

    public void initData(HeaderEntity headerEntity) {
        this.headerEntity = headerEntity;
        setData();
    }

    private void initDefaultData() {
        HeaderChildEntity headerChildEntity = new HeaderChildEntity();
        headerChildEntity.setRemianTitle("剩余话费");
        headerChildEntity.setNumber("-");
        headerChildEntity.setUnit("");
        HeaderChildEntity headerChildEntity2 = new HeaderChildEntity();
        headerChildEntity2.setRemianTitle("剩余流量");
        headerChildEntity2.setNumber("-");
        headerChildEntity2.setUnit("");
        HeaderChildEntity headerChildEntity3 = new HeaderChildEntity();
        headerChildEntity3.setRemianTitle("剩余语音");
        headerChildEntity3.setNumber("-");
        headerChildEntity3.setUnit("");
        HeaderChildEntity headerChildEntity4 = new HeaderChildEntity();
        headerChildEntity4.setRemianTitle("可用积分");
        headerChildEntity4.setNumber("-");
        headerChildEntity4.setUnit("");
        this.list.add(headerChildEntity);
        this.list.add(headerChildEntity2);
        this.list.add(headerChildEntity3);
        this.list.add(headerChildEntity4);
    }

    public void setData() {
        try {
            if (this.headerEntity == null) {
                this.headerEntity = new HeaderEntity();
            }
            this.list = this.headerEntity.getHeaderEntityList();
            if (this.list == null || this.list.size() == 0) {
                initDefaultData();
            }
            ArrayList arrayList = new ArrayList();
            UnicomHomeLogUtils.getInstance().removeLog(UnicomHomeLogUtils.LOG_TYPE_HF);
            for (int i = 0; i < 4; i++) {
                if (i < this.list.size()) {
                    HeaderChildEntity headerChildEntity = this.list.get(i);
                    this.hedaderLayuts[i].setData(headerChildEntity, i, this.typeface);
                    this.hedaderLayuts[i].setVisibility(0);
                    arrayList.add(new HomeLogEntity(String.valueOf(1020100 + i + 1), headerChildEntity.getRemianTitle()));
                } else {
                    this.hedaderLayuts[i].setVisibility(8);
                }
            }
            UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_HF, arrayList);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "设置首页卡片话费信息异常" + e.getMessage());
        }
    }

    public void setHeaderEntity(HeaderEntity headerEntity) {
        this.headerEntity = headerEntity;
        setData();
    }
}
