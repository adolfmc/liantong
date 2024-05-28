package com.sinovatech.unicom.separatemodule.user.adapter;

import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CaseAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> list;

    public CaseAdapter(List<String> list) {
        this.list = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493205, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvContent.setText(this.list.get(i));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            this.tvContent = (TextView) view.findViewById(2131298974);
        }
    }
}
