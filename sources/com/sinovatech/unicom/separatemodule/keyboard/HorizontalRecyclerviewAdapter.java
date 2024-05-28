package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HorizontalRecyclerviewAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<ImageModel> datas;
    private LayoutInflater mInflater;
    private OnClickItemListener onClickItemListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnClickItemListener {
        void onItemClick(View view, int i, List<ImageModel> list);

        void onItemLongClick(View view, int i, List<ImageModel> list);
    }

    public HorizontalRecyclerviewAdapter(Context context, List<ImageModel> list) {
        this.datas = list;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mInflater.inflate(2131493405, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        ImageModel imageModel = this.datas.get(i);
        if (this.onClickItemListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.HorizontalRecyclerviewAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    HorizontalRecyclerviewAdapter.this.onClickItemListener.onItemClick(viewHolder.itemView, viewHolder.getLayoutPosition(), HorizontalRecyclerviewAdapter.this.datas);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.HorizontalRecyclerviewAdapter.2
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    NBSActionInstrumentation.onLongClickEventEnter(view, this);
                    HorizontalRecyclerviewAdapter.this.onClickItemListener.onItemLongClick(viewHolder.itemView, viewHolder.getLayoutPosition(), HorizontalRecyclerviewAdapter.this.datas);
                    NBSActionInstrumentation.onLongClickEventExit();
                    return false;
                }
            });
        }
        viewHolder.imageBtn.getLayoutParams().width = UIUtils.getScreenWidth(this.context) / 6;
        viewHolder.imageBtn.setImageDrawable(imageModel.icon);
        if (imageModel.isSelected) {
            viewHolder.imageBtn.setBackgroundColor(this.context.getResources().getColor(2131099693));
        } else {
            viewHolder.imageBtn.setBackgroundColor(this.context.getResources().getColor(2131099692));
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.datas.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageBtn;

        public ViewHolder(View view) {
            super(view);
            this.imageBtn = (ImageView) view.findViewById(2131297265);
        }
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }
}
