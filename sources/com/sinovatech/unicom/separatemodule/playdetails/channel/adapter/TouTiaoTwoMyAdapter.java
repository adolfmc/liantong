package com.sinovatech.unicom.separatemodule.playdetails.channel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.playdetails.channel.MyItemTouchHandler;
import com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.TouTiaoTwoMyAdapter;
import com.sinovatech.unicom.separatemodule.playdetails.channel.entity.ChannelEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TouTiaoTwoMyAdapter extends MyItemTouchHandler.ItemTouchAdapterImpl {
    private boolean isDelete;
    AdapterCallBack mAdapterCallBack;
    private Context mContext;
    private List<ChannelEntity.DataDTO> mData;
    private final LayoutInflater mLayoutInflater;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface AdapterCallBack {
        void onItemClickListener(ViewHolder viewHolder, int i);

        boolean onItemLongClickListener(ViewHolder viewHolder, int i);
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.channel.MyItemTouchHandler.ItemTouchAdapterImpl
    public boolean autoOpenSwipe() {
        return false;
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.channel.MyItemTouchHandler.ItemTouchAdapterImpl
    public void onItemRemove(int i) {
    }

    public void setmAdapterCallBack(AdapterCallBack adapterCallBack) {
        this.mAdapterCallBack = adapterCallBack;
    }

    public TouTiaoTwoMyAdapter(Context context, List<ChannelEntity.DataDTO> list) {
        this.mContext = context;
        this.mData = list;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override // com.sinovatech.unicom.separatemodule.playdetails.channel.MyItemTouchHandler.ItemTouchAdapterImpl
    public void onItemMove(int i, int i2) {
        Collections.swap(this.mData, i, i2);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this.mLayoutInflater.inflate(2131493241, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, @SuppressLint({"RecyclerView"}) int i) {
        if (viewHolder instanceof ViewHolder) {
            final ChannelEntity.DataDTO dataDTO = this.mData.get(i);
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            viewHolder2.tvTitle.setText(dataDTO.getPageName());
            if (this.isDelete) {
                if (!TextUtils.isEmpty(dataDTO.getSubCode())) {
                    viewHolder2.ivDelete.setVisibility(0);
                }
            } else {
                viewHolder2.ivDelete.setVisibility(8);
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.-$$Lambda$TouTiaoTwoMyAdapter$tLUUoIA5pxXfXu5Sb_9cbLFP9_4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.mAdapterCallBack.onItemClickListener((TouTiaoTwoMyAdapter.ViewHolder) viewHolder, TouTiaoTwoMyAdapter.this.mData.indexOf(dataDTO));
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.-$$Lambda$TouTiaoTwoMyAdapter$K1nKUp1D7LpWCQJ6BotXR99qwDs
                @Override // android.view.View.OnLongClickListener
                public final boolean onLongClick(View view) {
                    return TouTiaoTwoMyAdapter.lambda$onBindViewHolder$1(TouTiaoTwoMyAdapter.this, viewHolder, dataDTO, view);
                }
            });
            if (dataDTO.isUnMove()) {
                viewHolder2.tvTitle.setBackgroundResource(2131231088);
                viewHolder2.tvTitle.setTextColor(Color.parseColor("#ff999999"));
            }
        }
    }

    public static /* synthetic */ boolean lambda$onBindViewHolder$1(TouTiaoTwoMyAdapter touTiaoTwoMyAdapter, RecyclerView.ViewHolder viewHolder, ChannelEntity.DataDTO dataDTO, View view) {
        touTiaoTwoMyAdapter.mAdapterCallBack.onItemLongClickListener((ViewHolder) viewHolder, touTiaoTwoMyAdapter.mData.indexOf(dataDTO));
        return false;
    }

    public void showDeleteIcon(boolean z) {
        this.isDelete = z;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivDelete;
        TextView tvTitle;

        public ViewHolder(View view) {
            super(view);
            this.tvTitle = (TextView) view.findViewById(2131298980);
            this.ivDelete = (ImageView) view.findViewById(2131297406);
        }
    }
}
