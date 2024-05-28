package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaibuCoffeeVolumeEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduCoffeeVolumeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    List<BaibuCoffeeVolumeEntity.TitleListBean> mList;
    private OnItemClickListener mOnItemClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickListener {
        void onItemClick();
    }

    public BaiduCoffeeVolumeAdapter(Context context, List<BaibuCoffeeVolumeEntity.TitleListBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493011, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.mTitle.setText(this.mList.get(i).getTitle());
        GlideApp.with(this.mContext).load(this.mList.get(i).getIcon()).placeholder(2131231004).error(2131231004).into(myHolder.mIcon);
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (BaiduCoffeeVolumeAdapter.this.mOnItemClickListener != null) {
                    BaiduCoffeeVolumeAdapter.this.mOnItemClickListener.onItemClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView mIcon;
        private final TextView mTitle;

        public MyHolder(View view) {
            super(view);
            this.mIcon = (ImageView) view.findViewById(2131297242);
            this.mTitle = (TextView) view.findViewById(2131298785);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
