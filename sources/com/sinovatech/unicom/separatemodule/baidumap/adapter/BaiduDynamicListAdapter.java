package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduDynamicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity context;
    private List<BusinessEntity.DynamicListBean> list;

    public BaiduDynamicListAdapter(Activity activity, List<BusinessEntity.DynamicListBean> list) {
        this.context = activity;
        this.list = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.context).inflate(2131493016, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MyHolder myHolder = (MyHolder) viewHolder;
        GlideApp.with(this.context).load(this.list.get(i).getHorizontalPicture()).placeholder(2131231015).error(2131231015).into(myHolder.image);
        if (TextUtils.isEmpty(this.list.get(i).getViewsNum())) {
            myHolder.browse_layout.setVisibility(8);
        } else {
            myHolder.browse.setText(this.list.get(i).getViewsNum());
        }
        if (TextUtils.isEmpty(this.list.get(i).getCommentsAmount())) {
            myHolder.msg_layout.setVisibility(8);
        } else {
            myHolder.msg.setText(this.list.get(i).getCommentsAmount());
        }
        if (TextUtils.isEmpty(this.list.get(i).getLikesNum())) {
            myHolder.praise_layout.setVisibility(8);
        } else {
            myHolder.praise.setText(this.list.get(i).getLikesNum());
        }
        myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduDynamicListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!TextUtils.isEmpty(((BusinessEntity.DynamicListBean) BaiduDynamicListAdapter.this.list.get(i)).getDynamicAddress())) {
                    StatisticsUploadUtils.upload(BaiduDynamicListAdapter.this.context, "FJ0011", "点击滑动列表动态", "按钮", "0", ((BusinessEntity.DynamicListBean) BaiduDynamicListAdapter.this.list.get(i)).getTopic(), ((BusinessEntity.DynamicListBean) BaiduDynamicListAdapter.this.list.get(i)).getDynamicAddress());
                    IntentManager.generateIntentAndGo(BaiduDynamicListAdapter.this.context, ((BusinessEntity.DynamicListBean) BaiduDynamicListAdapter.this.list.get(i)).getDynamicAddress(), "", false, "get");
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
        private final TextView browse;
        private final LinearLayout browse_layout;
        private final ImageView image;
        private final TextView msg;
        private final LinearLayout msg_layout;
        private final TextView praise;
        private final LinearLayout praise_layout;

        public MyHolder(View view) {
            super(view);
            this.browse = (TextView) view.findViewById(2131296537);
            this.msg = (TextView) view.findViewById(2131298087);
            this.praise = (TextView) view.findViewById(2131298216);
            this.image = (ImageView) view.findViewById(2131297258);
            this.browse_layout = (LinearLayout) view.findViewById(2131296538);
            this.msg_layout = (LinearLayout) view.findViewById(2131298088);
            this.praise_layout = (LinearLayout) view.findViewById(2131298217);
        }
    }
}
