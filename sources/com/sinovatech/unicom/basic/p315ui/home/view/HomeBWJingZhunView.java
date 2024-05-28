package com.sinovatech.unicom.basic.p315ui.home.view;

import android.app.Activity;
import android.content.Context;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import com.sinovatech.unicom.basic.p315ui.home.adapter.HomeBwJingZhunAdapter;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBwJingZhunEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBwJingZhunEntity_;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeCardInfoManager;
import com.sinovatech.unicom.basic.p315ui.home.util.RecycleGridDivider;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.HomeBWJingZhunView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeBWJingZhunView extends LinearLayout {
    private static final String TAG = "HomeBWJingZhunView";
    public static String resumeId = "";
    private Activity activityContext;
    private Box<HomeBwJingZhunEntity> cacheBox;
    private HomeCardInfoManager cardInfoManager;
    private HomeBwJingZhunAdapter mAdapter;
    private List<HomeBwJingZhunEntity> mList;
    private RecyclerView recyclerView;

    public HomeBWJingZhunView(Activity activity, HomeCardInfoManager homeCardInfoManager) {
        super(activity);
        this.mList = new ArrayList();
        this.activityContext = activity;
        this.cardInfoManager = homeCardInfoManager;
        init();
    }

    private void init() {
        try {
            MsLogUtil.m7979d(TAG, "初始化本网精准营销业务控件");
            this.cacheBox = App.getBoxStore().boxFor(HomeBwJingZhunEntity.class);
            this.recyclerView = (RecyclerView) View.inflate(this.activityContext, 2131493159, this).findViewById(2131297129);
            this.recyclerView.addItemDecoration(new RecycleGridDivider(UIUtils.dip2px(5.0f), false));
            this.recyclerView.setLayoutManager(new GridLayoutManager(this.activityContext, 3));
            this.mAdapter = new HomeBwJingZhunAdapter(this.activityContext, this.mList);
            this.recyclerView.setAdapter(this.mAdapter);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化本网精准营销业务控件异常" + e.getMessage());
        }
    }

    public void initData() {
        notifyAdapter(getCacheList(), false);
    }

    public void notifyAdapter(List<HomeBwJingZhunEntity> list) {
        notifyAdapter(list, false);
    }

    public void notifyAdapter(List<HomeBwJingZhunEntity> list, boolean z) {
        HomeBwJingZhunEntity homeBwJingZhunEntity;
        try {
            this.mList.clear();
            resumeId = "";
            if (list != null && list.size() >= 3) {
                this.mList.addAll(list);
                this.recyclerView.setLayoutManager(new GridLayoutManager((Context) this.activityContext, 3, 1, false));
                this.mAdapter.notifyDataSetChanged();
                setVisibility(0);
                if (!z) {
                    for (int i = 0; i < list.size(); i++) {
                        if (i < 3) {
                            if (!TextUtils.isEmpty(list.get(i).getWxId())) {
                                resumeId += homeBwJingZhunEntity.getWxId() + ",";
                            }
                        }
                    }
                    try {
                        if (!TextUtils.isEmpty(resumeId)) {
                            resumeId = resumeId.substring(0, resumeId.length() - 1);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7977e(TAG, "去掉精准营销字符串最后一个逗号异常:" + e.getMessage());
                    }
                }
            } else {
                setVisibility(8);
            }
            MsLogUtil.m7979d(TAG, "刷新数据");
        } catch (Exception e2) {
            setVisibility(8);
            MsLogUtil.m7977e(TAG, "刷新本网精准营销数据异常" + e2.getMessage());
        }
    }

    private void updateData(List<HomeBwJingZhunEntity> list) {
        if (list == null || list.size() < 3) {
            notifyAdapter(new ArrayList());
        } else {
            notifyAdapter(list);
        }
    }

    private List<HomeBwJingZhunEntity> getCacheList() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(this.cacheBox.query().equal(HomeBwJingZhunEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find());
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取本网精准营销缓存数据异常:" + e.getMessage());
        }
        MsLogUtil.m7979d(TAG, "获取卡片本网精准营销缓存数据结果条数:" + arrayList.size());
        return new ArrayList();
    }

    private void setCache(List<HomeBwJingZhunEntity> list) {
        if (list == null) {
            try {
                list = new ArrayList<>();
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "设置本网精准营销存数据异常:" + e.getMessage());
                return;
            }
        }
        List<HomeBwJingZhunEntity> find = this.cacheBox.query().equal(HomeBwJingZhunEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find();
        if (find != null && find.size() > 0) {
            this.cacheBox.remove(find);
        }
        this.cacheBox.put(list);
    }

    public String getBaoGuangUrl() {
        HomeBwJingZhunEntity homeBwJingZhunEntity;
        String str = "";
        try {
            if (this.mList != null && this.mList.size() >= 3) {
                int i = 0;
                while (i < this.mList.size()) {
                    if (i < 3 && (homeBwJingZhunEntity = this.mList.get(i)) != null) {
                        String goodsUrl = homeBwJingZhunEntity.getGoodsUrl();
                        if (TextUtils.isEmpty(goodsUrl)) {
                            goodsUrl = "";
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append(goodsUrl);
                        sb.append(i == 2 ? "" : ",");
                        str = sb.toString();
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "获取本网精准营销url异常:" + e.getMessage());
        }
        return str;
    }
}
