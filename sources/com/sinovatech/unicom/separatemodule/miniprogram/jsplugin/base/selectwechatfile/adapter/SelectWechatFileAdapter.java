package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity.WechatFileModel;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SelectWechatFileAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<WechatFileModel> dataList;
    private boolean sIsScrolling = false;

    public void setScrolling(boolean z) {
        this.sIsScrolling = z;
    }

    public boolean getScrolling() {
        return this.sIsScrolling;
    }

    public SelectWechatFileAdapter(Activity activity, List<WechatFileModel> list) {
        this.dataList = list;
        this.activity = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493553, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        List<WechatFileModel> list = this.dataList;
        if (list == null || list.size() <= 0) {
            return;
        }
        viewHolder.f18580tv.setText(this.dataList.get(i).getTime());
        viewHolder.f18579rl.setLayoutManager(new GridLayoutManager(this.activity, 4));
        viewHolder.f18579rl.setAdapter(new SelectWechatItemAdapter(this.activity, this.dataList.get(i).getFileModelList(), this.sIsScrolling));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<WechatFileModel> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: rl */
        private RecyclerView f18579rl;

        /* renamed from: tv */
        private TextView f18580tv;

        ViewHolder(@NonNull View view) {
            super(view);
            this.f18580tv = (TextView) view.findViewById(2131299038);
            this.f18579rl = (RecyclerView) view.findViewById(2131299039);
        }
    }
}
