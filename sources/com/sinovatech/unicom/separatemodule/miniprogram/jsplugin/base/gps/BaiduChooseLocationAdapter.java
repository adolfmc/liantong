package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiDuLoacationEntity;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduChooseLocationAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private int currentPosition = 0;
    private ArrayList<BaiDuLoacationEntity> datas;
    public setOnClick setOnClick;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface setOnClick {
        void setOnPosition(int i);
    }

    public BaiduChooseLocationAdapter(Context context, ArrayList<BaiDuLoacationEntity> arrayList, setOnClick setonclick) {
        this.context = context;
        this.datas = arrayList;
        this.setOnClick = setonclick;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    @NotNull
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(2131493206, (ViewGroup) null));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull @NotNull ViewHolder viewHolder, final int i) {
        viewHolder.address.setText(this.datas.get(i).getAddress());
        viewHolder.name.setText(this.datas.get(i).getName());
        if (!TextUtils.isEmpty(this.datas.get(i).getDistance())) {
            viewHolder.tvDistance.setVisibility(0);
            TextView textView = viewHolder.tvDistance;
            textView.setText(this.datas.get(i).getDistance() + "米之内 |");
        } else {
            viewHolder.tvDistance.setVisibility(8);
        }
        if (i == this.currentPosition) {
            viewHolder.name.setTextColor(this.context.getResources().getColor(2131100097));
        } else {
            viewHolder.name.setTextColor(this.context.getResources().getColor(2131099739));
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.gps.BaiduChooseLocationAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (BaiduChooseLocationAdapter.this.setOnClick != null) {
                    BaiduChooseLocationAdapter.this.currentPosition = i;
                    BaiduChooseLocationAdapter.this.setOnClick.setOnPosition(i);
                    BaiduChooseLocationAdapter.this.notifyDataSetChanged();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView name;
        TextView tvDistance;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(2131298096);
            this.address = (TextView) view.findViewById(2131296322);
            this.tvDistance = (TextView) view.findViewById(2131298930);
        }
    }
}
