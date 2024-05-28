package com.sinovatech.unicom.basic.p315ui.fuwu.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerBaiViewAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DragRecyclerBaiViewAdapter extends RecyclerView.Adapter<DragHolder> {
    private Activity context;

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return 8;
    }

    public DragRecyclerBaiViewAdapter(Activity activity) {
        this.context = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public DragHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new DragHolder(LayoutInflater.from(this.context).inflate(2131493140, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull DragHolder dragHolder, int i) {
        if (FuWuConstant.isBianJiState) {
            dragHolder.rl_item_add.setVisibility(0);
        } else {
            dragHolder.rl_item_add.setVisibility(8);
        }
        dragHolder.rl_item_bg.setImageResource(2131231308);
        if (FuWuConstant.topList == null || FuWuConstant.topList.size() <= 0 || i >= FuWuConstant.topList.size()) {
            return;
        }
        dragHolder.rl_item_bg.setImageResource(0);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.DragRecyclerBaiViewAdapter$DragHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class DragHolder extends RecyclerView.ViewHolder {
        private ImageView rl_item_add;
        private ImageView rl_item_bg;

        public DragHolder(View view) {
            super(view);
            this.rl_item_add = (ImageView) view.findViewById(2131298346);
            this.rl_item_bg = (ImageView) view.findViewById(2131298347);
        }
    }

    public void setContentList(Activity activity) {
        this.context = activity;
        notifyDataSetChanged();
    }
}
