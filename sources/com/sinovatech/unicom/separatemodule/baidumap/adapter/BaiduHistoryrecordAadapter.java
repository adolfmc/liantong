package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduHistoryrecordAadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    boolean isHistoryDeleteStatus;
    private List<String> list;
    private Context mContext;
    private OnDeleteClickListener mOnDeleteClickListener;
    private OnItemListClickListener mOnItemListClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnDeleteClickListener {
        void onDeleteClick(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemListClickListener {
        void onItemListClick(int i);
    }

    public BaiduHistoryrecordAadapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }

    public void delete(boolean z) {
        this.isHistoryDeleteStatus = z;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493017, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.mName.setText(this.list.get(i));
        if (this.isHistoryDeleteStatus) {
            myHolder.mDelete.setVisibility(0);
            myHolder.mDelete.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduHistoryrecordAadapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (BaiduHistoryrecordAadapter.this.mOnDeleteClickListener != null) {
                        BaiduHistoryrecordAadapter.this.mOnDeleteClickListener.onDeleteClick(i);
                    }
                    BaiduHistoryrecordAadapter.this.list.remove(i);
                    BaiduHistoryrecordAadapter.this.notifyDataSetChanged();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            myHolder.mDelete.setVisibility(8);
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduHistoryrecordAadapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (BaiduHistoryrecordAadapter.this.mOnItemListClickListener != null) {
                    BaiduHistoryrecordAadapter.this.mOnItemListClickListener.onItemListClick(i);
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
        private final ImageButton mDelete;
        private final TextView mName;

        public MyHolder(View view) {
            super(view);
            this.mName = (TextView) view.findViewById(2131298489);
            this.mDelete = (ImageButton) view.findViewById(2131298488);
        }
    }

    public void setOnDeleteClickListener(OnDeleteClickListener onDeleteClickListener) {
        this.mOnDeleteClickListener = onDeleteClickListener;
    }

    public void setOnItemListClickListener(OnItemListClickListener onItemListClickListener) {
        this.mOnItemListClickListener = onItemListClickListener;
    }
}
