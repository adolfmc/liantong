package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.baidumap.entity.CouponsListEntity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduCouponsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private CouponsListEntity list;
    private Activity mContext;
    private OnItemListClickListener mOnItemListClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemListClickListener {
        void onItemListClick(int i, String str, String str2, String str3);
    }

    public BaiduCouponsListAdapter(Activity activity, CouponsListEntity couponsListEntity) {
        this.mContext = activity;
        this.list = couponsListEntity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493015, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        if (!this.list.getData().get(i).isMore()) {
            final CouponsListEntity.DataBean dataBean = this.list.getData().get(i);
            myHolder.layout.setVisibility(0);
            myHolder.mTitle.setText(dataBean.getTitle());
            myHolder.mTitles.setText(dataBean.getSubTitle());
            myHolder.mPrice.setText(dataBean.getCost());
            myHolder.isReceive.setText(dataBean.getStateDesc());
            if ("05".equals(dataBean.getState())) {
                myHolder.mTitle.setTextColor(Color.parseColor("#3b3632"));
                myHolder.mTitles.setTextColor(Color.parseColor("#dd775e"));
                myHolder.mPrice.setTextColor(Color.parseColor("#1d1814"));
                myHolder.isReceive.setTextColor(Color.parseColor("#dd775e"));
                GlideApp.with(this.mContext).load(this.list.getBackgroundColor()).placeholder(2131231011).error(2131231011).into(myHolder.image);
            } else {
                myHolder.mTitle.setTextColor(Color.parseColor("#9f9f9f"));
                myHolder.mTitles.setTextColor(Color.parseColor("#9f9f9f"));
                myHolder.mPrice.setTextColor(Color.parseColor("#9f9f9f"));
                myHolder.isReceive.setTextColor(Color.parseColor("#9f9f9f"));
                GlideApp.with(this.mContext).load(this.list.getBackgroundNoColor()).placeholder(2131231009).error(2131231009).into(myHolder.image);
            }
            myHolder.mLeftLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCouponsListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (BaiduCouponsListAdapter.this.mOnItemListClickListener != null) {
                        BaiduCouponsListAdapter.this.mOnItemListClickListener.onItemListClick(i, dataBean.getCouponId(), dataBean.getState(), dataBean.getPaymentType());
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return;
        }
        myHolder.layout.setVisibility(8);
        GlideApp.with(this.mContext).load(this.list.getDefaultImg()).placeholder(2131231010).error(2131231010).into(myHolder.image);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.list.getData().size() > 0) {
            return this.list.getData().size();
        }
        return 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView isReceive;
        private final LinearLayout layout;
        private final RelativeLayout mLeftLayout;
        private final TextView mPrice;
        private final TextView mTitle;
        private final TextView mTitles;

        public MyHolder(View view) {
            super(view);
            this.layout = (LinearLayout) view.findViewById(2131297574);
            this.mLeftLayout = (RelativeLayout) view.findViewById(2131297591);
            this.mTitle = (TextView) view.findViewById(2131298785);
            this.mTitles = (TextView) view.findViewById(2131298806);
            this.mPrice = (TextView) view.findViewById(2131298227);
            this.isReceive = (TextView) view.findViewById(2131297320);
            this.image = (ImageView) view.findViewById(2131297258);
        }
    }

    public void setOnItemListClickListener(OnItemListClickListener onItemListClickListener) {
        this.mOnItemListClickListener = onItemListClickListener;
    }
}
