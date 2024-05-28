package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduLiveListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private List<BusinessEntity.LivingListBean> list;

    public BaiduLiveListAdapter(Activity activity, List<BusinessEntity.LivingListBean> list) {
        this.context = activity;
        this.list = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.context).inflate(2131493018, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        myHolder.name.setText(this.list.get(i).getHallName());
        GlideApp.with(this.context).load(this.list.get(i).getHeadImg()).placeholder(2131231026).error(2131231026).into(myHolder.image);
        Glide.with(this.context).asGif().load((Integer) 2131231021).into(myHolder.livethe);
        if ("Y".equals(this.list.get(i).getLiving())) {
            myHolder.livethe.setVisibility(0);
        } else {
            myHolder.livethe.setVisibility(8);
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduLiveListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!TextUtils.isEmpty(((BusinessEntity.LivingListBean) BaiduLiveListAdapter.this.list.get(i)).getLiveUrl())) {
                    StatisticsUploadUtils.upload(BaiduLiveListAdapter.this.context, "FJ0012", "点击滑动列表直播头像", "按钮", "0", ((BusinessEntity.LivingListBean) BaiduLiveListAdapter.this.list.get(i)).getHallName(), ((BusinessEntity.LivingListBean) BaiduLiveListAdapter.this.list.get(i)).getLiveUrl());
                    IntentManager.generateIntentAndGo(BaiduLiveListAdapter.this.context, ((BusinessEntity.LivingListBean) BaiduLiveListAdapter.this.list.get(i)).getLiveUrl(), "", false, "get");
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
    public class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final ImageView livethe;
        private final TextView name;

        public MyHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(2131298096);
            this.image = (ImageView) view.findViewById(2131297258);
            this.livethe = (ImageView) view.findViewById(2131297681);
        }
    }
}
