package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchTagEntity;
import java.util.ArrayList;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduSearchwordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<SearchTagEntity> list;
    private Context mContext;
    private OnItemListClickListener mOnItemListClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemListClickListener {
        void onItemListClick(int i);
    }

    public BaiduSearchwordAdapter(Context context, ArrayList<SearchTagEntity> arrayList) {
        this.mContext = context;
        this.list = arrayList;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493022, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int screenWidth = UIUtils.getScreenWidth(this.mContext);
        MyHolder myHolder = (MyHolder) viewHolder;
        ViewGroup.LayoutParams layoutParams = myHolder.mName.getLayoutParams();
        layoutParams.width = screenWidth / 3;
        myHolder.mName.setLayoutParams(layoutParams);
        myHolder.mName.setText(this.list.get(i).gettABNAME());
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduSearchwordAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (BaiduSearchwordAdapter.this.mOnItemListClickListener != null) {
                    BaiduSearchwordAdapter.this.mOnItemListClickListener.onItemListClick(i);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class MyHolder extends RecyclerView.ViewHolder {
        private final TextView mName;

        public MyHolder(View view) {
            super(view);
            this.mName = (TextView) view.findViewById(2131298489);
        }
    }

    public void setOnItemListClickListener(OnItemListClickListener onItemListClickListener) {
        this.mOnItemListClickListener = onItemListClickListener;
    }
}
