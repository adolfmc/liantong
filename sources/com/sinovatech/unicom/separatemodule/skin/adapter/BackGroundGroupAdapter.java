package com.sinovatech.unicom.separatemodule.skin.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.home.util.RecycleGridDivider;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity;
import com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean;
import com.sinovatech.unicom.separatemodule.skin.event.DeleteSkinEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackGroundGroupAdapter extends RecyclerView.Adapter<BackGroundGroupVH> {
    private static final String TAG = "BackGroundGroupAdapter";
    private BackgroundSkinBean currentBean;
    private List<BackgroundSkinEntity> list;
    private Activity mActivity;
    private BackgroundTongYongBean tongYongBean;

    public BackGroundGroupAdapter(Activity activity, List<BackgroundSkinEntity> list) {
        this.mActivity = activity;
        this.list = list;
    }

    public void setTongYongBean(BackgroundTongYongBean backgroundTongYongBean) {
        this.tongYongBean = backgroundTongYongBean;
    }

    public void setCurrentBean(BackgroundSkinBean backgroundSkinBean) {
        this.currentBean = backgroundSkinBean;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public BackGroundGroupVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BackGroundGroupVH(LayoutInflater.from(viewGroup.getContext()).inflate(2131492921, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull BackGroundGroupVH backGroundGroupVH, int i) {
        final BackgroundSkinEntity backgroundSkinEntity;
        if (backGroundGroupVH != null) {
            try {
                if (this.list == null || this.list.size() <= 0 || (backgroundSkinEntity = this.list.get(i)) == null) {
                    return;
                }
                final ArrayList arrayList = new ArrayList();
                arrayList.addAll(backgroundSkinEntity.getSkinList());
                int size = backgroundSkinEntity.getSkinList().size();
                if (TextUtils.equals("自定义皮肤", backgroundSkinEntity.getGroupName())) {
                    backGroundGroupVH.mTvMore.setVisibility(8);
                } else if (size < 4) {
                    BackgroundSkinBean backgroundSkinBean = new BackgroundSkinBean();
                    backgroundSkinBean.setProductImgUrl("pifu");
                    backgroundSkinBean.setProductName("更多皮肤正在设计中");
                    backgroundSkinBean.setProductSubtitle("APP用户专享");
                    arrayList.add(backgroundSkinBean);
                    backGroundGroupVH.mTvMore.setVisibility(8);
                } else {
                    backGroundGroupVH.mTvMore.setVisibility(0);
                }
                backGroundGroupVH.mTvTitle.setText(backgroundSkinEntity.getGroupName());
                backGroundGroupVH.mTvMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundGroupAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        PvCurrencyLogUtils.pvLogDJ("皮肤首页", "S2ndpage1178", "", "", "", backgroundSkinEntity.getGroupName() + "", "更多", "", "");
                        BackgroundMoreActivity.startNewIntent(BackGroundGroupAdapter.this.mActivity, arrayList, BackGroundGroupAdapter.this.currentBean, BackGroundGroupAdapter.this.tongYongBean, backgroundSkinEntity.getGroupName(), backgroundSkinEntity.getShowType());
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                BackGroundItemAdapter backGroundItemAdapter = new BackGroundItemAdapter(this.mActivity, arrayList, false, this.currentBean, this.tongYongBean, backgroundSkinEntity.getShowType());
                backGroundItemAdapter.setDeleteListener(new BackGroundItemAdapter.OnItemDeleteListener() { // from class: com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundGroupAdapter.2
                    @Override // com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter.OnItemDeleteListener
                    public void onDelete(BackgroundSkinBean backgroundSkinBean2) {
                        DeleteSkinEvent deleteSkinEvent = new DeleteSkinEvent(0);
                        deleteSkinEvent.setSkinBean(backgroundSkinBean2);
                        EventBusUtils.post(deleteSkinEvent);
                    }
                });
                backGroundGroupVH.mRV_Skin.addItemDecoration(new RecycleGridDivider(UIUtils.dip2px(5.0f), false));
                backGroundGroupVH.mRV_Skin.setLayoutManager(new GridLayoutManager(this.mActivity, 2));
                backGroundGroupVH.mRV_Skin.setAdapter(backGroundItemAdapter);
            } catch (Exception e) {
                String str = TAG;
                MsLogUtil.m7977e(str, "皮肤分组数据异常:" + e.getMessage());
            }
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BackgroundSkinEntity> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class BackGroundGroupVH extends RecyclerView.ViewHolder {
        private RecyclerView mRV_Skin;
        private TextView mTvMore;
        private TextView mTvTitle;

        public BackGroundGroupVH(View view) {
            super(view);
            this.mTvTitle = (TextView) view.findViewById(2131296477);
            this.mTvMore = (TextView) view.findViewById(2131296476);
            this.mRV_Skin = (RecyclerView) view.findViewById(2131296478);
        }
    }
}
