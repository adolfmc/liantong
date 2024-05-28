package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ComfortEntity;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ComFortAdapter extends RecyclerView.Adapter<Adapter> {
    private Context context;
    private BaseVideoEntity<List<ComfortEntity>> data;

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public ComFortAdapter(Context context, BaseVideoEntity<List<ComfortEntity>> baseVideoEntity) {
        this.context = context;
        this.data = baseVideoEntity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    @NotNull
    public Adapter onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new Adapter(LayoutInflater.from(this.context).inflate(2131493246, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull @NotNull Adapter adapter, int i) {
        TextView textView = adapter.tv_name;
        textView.setText(this.data.getData().get(i % this.data.getData().size()).getName() + ":");
        adapter.tv_comfortName.setText(this.data.getData().get(i % this.data.getData().size()).getComfortName());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class Adapter extends RecyclerView.ViewHolder {
        TextView tv_comfortName;
        TextView tv_name;

        public Adapter(View view) {
            super(view);
            this.tv_name = (TextView) view.findViewById(2131299021);
            this.tv_comfortName = (TextView) view.findViewById(2131298914);
        }
    }
}
