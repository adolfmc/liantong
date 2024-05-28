package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

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
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetEntity;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FlowGetAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FlowGetEntity.PopularListBean> list;
    private onItemClistener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface onItemClistener {
        void OnItem(FlowGetEntity.PopularListBean popularListBean);

        void onFlowGet(FlowGetEntity.PopularListBean popularListBean);
    }

    public FlowGetAdapter(List<FlowGetEntity.PopularListBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    public void onItemClistener(onItemClistener onitemclistener) {
        this.listener = onitemclistener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493210, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull @NotNull final RecyclerView.ViewHolder viewHolder, final int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        GlideApp.with(this.context).load(this.list.get(i).getBackGroundImg()).into(viewHolder2.image_backGround);
        TextView textView = viewHolder2.tv_game_currentMinute;
        textView.setText(this.list.get(i).getCurrentMinute() + "/");
        viewHolder2.tv_game_name.setText(this.list.get(i).getName());
        UIUtils.logD("时间", this.list.get(i).getCurrentMinute() + "/" + this.list.get(i).getMinute());
        TextView textView2 = viewHolder2.tv_game_minute;
        textView2.setText(this.list.get(i).getMinute() + "分钟");
        TextView textView3 = viewHolder2.tv_game_currentMinute;
        textView3.setText(this.list.get(i).getCurrentMinute() + "/");
        TextView textView4 = viewHolder2.tv_game_gamePersonsTrue;
        textView4.setText(this.list.get(i).getPersonNum() + "万人在玩");
        if (this.list.get(i).getState().equals("1")) {
            viewHolder2.tv_game_copywriting.setText(this.list.get(i).getCopywriting());
            viewHolder2.tv_game_copywriting.setTextColor(this.context.getResources().getColor(2131100060));
            viewHolder2.tv_game_copywriting.setBackground(this.context.getDrawable(2131230962));
            viewHolder2.tv_game_copywriting.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$FlowGetAdapter$w5txTrp4bo-Q95nfYLvg0t91GsM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.listener.onFlowGet(FlowGetAdapter.this.list.get(i));
                }
            });
        }
        if (this.list.get(i).getState().equals("2")) {
            viewHolder2.tv_game_copywriting.setText("继续玩");
            viewHolder2.tv_game_copywriting.setTextColor(this.context.getResources().getColor(2131099953));
            viewHolder2.tv_game_copywriting.setBackground(this.context.getDrawable(2131230895));
            viewHolder2.tv_game_copywriting.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$FlowGetAdapter$3261okf9Ttoqd76p9VIyOVIOQY8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecyclerView.ViewHolder.this.itemView.performClick();
                }
            });
        }
        if (this.list.get(i).getState().equals("0")) {
            viewHolder2.tv_game_copywriting.setText(this.list.get(i).getCopywriting());
            viewHolder2.tv_game_copywriting.setTextColor(this.context.getResources().getColor(2131099953));
            viewHolder2.tv_game_copywriting.setBackground(this.context.getDrawable(2131230895));
            viewHolder2.tv_game_copywriting.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.-$$Lambda$FlowGetAdapter$SvXoUdi2783-hh9HiAgSVu1We9k
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RecyclerView.ViewHolder.this.itemView.performClick();
                }
            });
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.adapter.FlowGetAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (FlowGetAdapter.this.listener != null) {
                    FlowGetAdapter.this.listener.OnItem((FlowGetEntity.PopularListBean) FlowGetAdapter.this.list.get(viewHolder.getPosition()));
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
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_backGround;
        private TextView tv_game_copywriting;
        private TextView tv_game_currentMinute;
        private TextView tv_game_gamePersonsTrue;
        private TextView tv_game_minute;
        private TextView tv_game_name;

        public ViewHolder(View view) {
            super(view);
            this.image_backGround = (ImageView) view.findViewById(2131297264);
            this.tv_game_name = (TextView) view.findViewById(2131298955);
            this.tv_game_gamePersonsTrue = (TextView) view.findViewById(2131298953);
            this.tv_game_currentMinute = (TextView) view.findViewById(2131298952);
            this.tv_game_copywriting = (TextView) view.findViewById(2131298951);
            this.tv_game_minute = (TextView) view.findViewById(2131298954);
        }
    }
}
