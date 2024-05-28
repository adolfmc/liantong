package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p315ui.entity.MainBean;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.GridAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.MainAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_LAST_ITEM = 1;
    private static final int VIEW_TYPE_NORMAL = 0;
    private Activity activity;
    private GridAdapter adapter;
    private List<MainBean> data;

    public MainAdapter(Activity activity, List<MainBean> list) {
        this.activity = activity;
        this.data = list;
    }

    public void setData(List<MainBean> list) {
        this.data = list;
        GridAdapter gridAdapter = this.adapter;
        if (gridAdapter != null) {
            gridAdapter.setData();
        }
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            return new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493343, viewGroup, false));
        }
        return new MemberItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493342, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == getItemCount() - 1 ? 1 : 0;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MemberItemViewHolder) {
            MainBean mainBean = this.data.get(i);
            MemberItemViewHolder memberItemViewHolder = (MemberItemViewHolder) viewHolder;
            memberItemViewHolder.titleTextView.setText(mainBean.getTitle());
            memberItemViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(this.activity, 5));
            String postion = PvCurrencyLogUtils.getPostion("20401", i);
            mainBean.setTransId(postion);
            memberItemViewHolder.recyclerView.setAdapter(new GridAdapter(this.activity, mainBean.getList(), postion, "服务页Tab签标题" + (i + 1)));
            memberItemViewHolder.recyclerView.clearOnScrollListeners();
            return;
        }
        ((FooterViewHolder) viewHolder).itemView.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, FuWuConstant.lastH));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size() + 1;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.MainAdapter$MemberItemViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class MemberItemViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public TextView titleTextView;

        public MemberItemViewHolder(View view) {
            super(view);
            this.titleTextView = (TextView) view.findViewById(2131297050);
            this.recyclerView = (RecyclerView) view.findViewById(2131297049);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.MainAdapter$FooterViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View view) {
            super(view);
        }
    }
}
