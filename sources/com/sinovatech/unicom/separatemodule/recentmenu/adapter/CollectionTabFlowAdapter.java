package com.sinovatech.unicom.separatemodule.recentmenu.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.viewholder.CollectionTabFlowViewHolder;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionTabFlowAdapter extends RecyclerView.Adapter<CollectionTabFlowViewHolder> {
    private static final String TAG = "CollectionTabFlowAdapte";
    private CollectionTabFlowItemClickListener itemClickListener;
    private List<CollectionTabEntity> mList;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CollectionTabFlowItemClickListener {
        void onItemClick(int i, CollectionTabEntity collectionTabEntity);
    }

    public CollectionTabFlowAdapter(List<CollectionTabEntity> list) {
        this.mList = list;
    }

    public void setItemClickListener(CollectionTabFlowItemClickListener collectionTabFlowItemClickListener) {
        this.itemClickListener = collectionTabFlowItemClickListener;
    }

    public void updateList(List<CollectionTabEntity> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public CollectionTabFlowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new CollectionTabFlowViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493042, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull CollectionTabFlowViewHolder collectionTabFlowViewHolder, final int i) {
        final CollectionTabEntity collectionTabEntity;
        if (collectionTabFlowViewHolder != null) {
            try {
                if (this.mList == null || (collectionTabEntity = this.mList.get(i)) == null) {
                    return;
                }
                collectionTabFlowViewHolder.mTvTabName.setText(collectionTabEntity.getCategoryName());
                if (collectionTabEntity.isSelect()) {
                    collectionTabFlowViewHolder.mTvTabName.setBackgroundResource(2131231102);
                    collectionTabFlowViewHolder.mTvTabName.setTextColor(Color.parseColor("#E60027"));
                } else {
                    collectionTabFlowViewHolder.mTvTabName.setBackgroundResource(2131231105);
                    collectionTabFlowViewHolder.mTvTabName.setTextColor(Color.parseColor("#333333"));
                }
                collectionTabFlowViewHolder.mTvTabName.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionTabFlowAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if (CollectionTabFlowAdapter.this.itemClickListener != null) {
                            CollectionTabFlowAdapter.this.itemClickListener.onItemClick(i, collectionTabEntity);
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "收藏分组数据类型填充数据异常:" + e.getMessage());
            }
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<CollectionTabEntity> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
