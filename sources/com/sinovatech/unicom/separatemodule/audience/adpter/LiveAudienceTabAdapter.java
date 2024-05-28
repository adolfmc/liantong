package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveAudienceTabEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveAudienceTabAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context context;
    private List<LiveAudienceTabEntity> list;
    private TabItemClickedListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface TabItemClickedListener {
        void cliced(int i, LiveAudienceTabEntity liveAudienceTabEntity);
    }

    private int getItemLayout(int i) {
        switch (i) {
            case 3:
                return 2131493220;
            case 4:
                return 2131493222;
            default:
                return 2131493221;
        }
    }

    public LiveAudienceTabAdapter(Context context, List<LiveAudienceTabEntity> list) {
        this.context = context;
        this.list = list == null ? new ArrayList<>() : list;
    }

    public void setListener(TabItemClickedListener tabItemClickedListener) {
        this.listener = tabItemClickedListener;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493221, (ViewGroup) null));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.tvName.setText(this.list.get(i).getTitle());
        if (this.list.get(i).isSelected()) {
            Drawable drawable = this.context.getResources().getDrawable(2131232337);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            viewHolder.tvName.setCompoundDrawables(null, null, null, drawable);
            viewHolder.tvName.setTypeface(Typeface.defaultFromStyle(1));
            viewHolder.tvName.setTextColor(-1);
        } else {
            viewHolder.tvName.setCompoundDrawables(null, null, null, null);
            viewHolder.tvName.setTypeface(Typeface.defaultFromStyle(0));
            viewHolder.tvName.setTextColor(-1711276033);
        }
        viewHolder.tvName.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.adpter.-$$Lambda$LiveAudienceTabAdapter$Duepq_wiwkxQfwf2x3EggTVe8Sc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LiveAudienceTabAdapter.lambda$onBindViewHolder$0(LiveAudienceTabAdapter.this, i, view);
            }
        });
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(LiveAudienceTabAdapter liveAudienceTabAdapter, int i, View view) {
        TabItemClickedListener tabItemClickedListener = liveAudienceTabAdapter.listener;
        if (tabItemClickedListener != null) {
            tabItemClickedListener.cliced(i, liveAudienceTabAdapter.list.get(i));
        }
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
        private TextView tvName;

        public ViewHolder(View view) {
            super(view);
            this.tvName = (TextView) view.findViewById(2131299108);
        }
    }
}
