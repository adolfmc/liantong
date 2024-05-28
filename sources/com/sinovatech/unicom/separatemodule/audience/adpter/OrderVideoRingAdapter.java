package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class OrderVideoRingAdapter extends RecyclerView.Adapter<OrderVideoRingHolder> {
    private Context context;
    private List<VideoRingExplainEntity.DataEntity> dataEntities;
    private int num = 3;
    public SetOnItemVideoRingClick setOnItemVideoRingClick;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SetOnItemVideoRingClick {
        void setOnItemOrderVideoRing(int i);
    }

    public OrderVideoRingAdapter(Context context, List<VideoRingExplainEntity.DataEntity> list) {
        this.context = context;
        this.dataEntities = list;
    }

    public void UpVideoData(List<VideoRingExplainEntity.DataEntity> list) {
        this.dataEntities = list;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NotNull
    public OrderVideoRingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderVideoRingHolder(LayoutInflater.from(this.context).inflate(2131493394, (ViewGroup) null));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull OrderVideoRingHolder orderVideoRingHolder, final int i) {
        boolean isSelected = this.dataEntities.get(i).isSelected();
        int i2 = this.num;
        if (i % i2 == 0) {
            if (isSelected) {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232032));
            } else {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232033));
            }
            orderVideoRingHolder.tvOrderTitle.setTextColor(this.context.getColor(2131099938));
            orderVideoRingHolder.tvOrderDesc.setTextColor(this.context.getColor(2131099938));
        } else if (i % i2 == 1) {
            if (isSelected) {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232034));
            } else {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232035));
            }
            if (this.dataEntities.get(i).getRecommendedStatus().equals("1")) {
                orderVideoRingHolder.imageTuiJian.setVisibility(0);
            }
            orderVideoRingHolder.tvOrderTitle.setTextColor(this.context.getColor(2131099939));
            orderVideoRingHolder.tvOrderDesc.setTextColor(this.context.getColor(2131099939));
        } else if (i % i2 == 2) {
            if (isSelected) {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232036));
            } else {
                orderVideoRingHolder.llOrder.setBackground(this.context.getDrawable(2131232037));
            }
            orderVideoRingHolder.tvOrderTitle.setTextColor(this.context.getColor(2131099940));
            orderVideoRingHolder.tvOrderDesc.setTextColor(this.context.getColor(2131099940));
        }
        orderVideoRingHolder.tvOrderDesc.setText(this.dataEntities.get(i).getConfirmTips());
        orderVideoRingHolder.tvOrderTitle.setText(this.dataEntities.get(i).getProductName());
        orderVideoRingHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.adpter.OrderVideoRingAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                OrderVideoRingAdapter.this.setOnItemVideoRingClick.setOnItemOrderVideoRing(i);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataEntities.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class OrderVideoRingHolder extends RecyclerView.ViewHolder {
        ImageView imageTuiJian;
        LinearLayout llOrder;
        TextView tvOrderDesc;
        TextView tvOrderTitle;

        public OrderVideoRingHolder(View view) {
            super(view);
            this.llOrder = (LinearLayout) view.findViewById(2131297751);
            this.tvOrderTitle = (TextView) view.findViewById(2131298178);
            this.tvOrderDesc = (TextView) view.findViewById(2131298177);
            this.imageTuiJian = (ImageView) view.findViewById(2131297280);
        }
    }

    public void setSetOnItemVideoRingClick(SetOnItemVideoRingClick setOnItemVideoRingClick) {
        this.setOnItemVideoRingClick = setOnItemVideoRingClick;
    }
}
