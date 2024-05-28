package com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthRecord;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.activity.QuanXianInfoActivity;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YeWuAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<H5AuthRecord> h5AuthRecordList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private RelativeLayout rl_right_item;

        public ViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(2131298058);
            this.rl_right_item = (RelativeLayout) view.findViewById(2131298376);
        }
    }

    public YeWuAdapter(Activity activity, List<H5AuthRecord> list) {
        this.h5AuthRecordList = list;
        this.activity = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493408, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(this.h5AuthRecordList.get(i).getAppName());
        viewHolder.rl_right_item.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter.YeWuAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (YeWuAdapter.this.h5AuthRecordList != null && YeWuAdapter.this.h5AuthRecordList.size() > 0 && YeWuAdapter.this.h5AuthRecordList.get(i) != null && !TextUtils.isEmpty(((H5AuthRecord) YeWuAdapter.this.h5AuthRecordList.get(i)).getAppId())) {
                    Intent intent = new Intent(YeWuAdapter.this.activity, QuanXianInfoActivity.class);
                    String appName = ((H5AuthRecord) YeWuAdapter.this.h5AuthRecordList.get(i)).getAppName();
                    if (TextUtils.isEmpty(appName)) {
                        str = "业务授权";
                    } else {
                        str = appName + "业务授权";
                    }
                    intent.putExtra("title", str);
                    intent.putExtra("appId", String.valueOf(((H5AuthRecord) YeWuAdapter.this.h5AuthRecordList.get(i)).getAppId()));
                    YeWuAdapter.this.activity.startActivity(intent);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.h5AuthRecordList.size();
    }
}
