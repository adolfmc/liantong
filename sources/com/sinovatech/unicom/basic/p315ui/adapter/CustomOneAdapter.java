package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.p318ui.GlideApp;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.CustomOneAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomOneAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<MenuEntity> dataList;
    private onItemClickListener onItemClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.CustomOneAdapter$onItemClickListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface onItemClickListener {
        void onItemClickListener();
    }

    public void setOnItemClickListener(onItemClickListener onitemclicklistener) {
        this.onItemClickListener = onitemclicklistener;
    }

    public CustomOneAdapter(Activity activity, List<MenuEntity> list) {
        this.dataList = list;
        this.activity = activity;
    }

    public void setData(List<MenuEntity> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493135, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GlideApp.with(this.activity).asBitmap().placeholder(2131231795).error(2131231795).load(this.dataList.get(i).getMenuIconURL()).into(viewHolder.iv_img);
        viewHolder.lin_top_fuwu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.CustomOneAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (CustomOneAdapter.this.onItemClickListener != null) {
                    CustomOneAdapter.this.onItemClickListener.onItemClickListener();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.CustomOneAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_img;
        private LinearLayout lin_top_fuwu;

        ViewHolder(View view) {
            super(view);
            this.iv_img = (ImageView) view.findViewById(2131297403);
            this.lin_top_fuwu = (LinearLayout) view.findViewById(2131297617);
        }
    }
}
