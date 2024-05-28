package com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter;

import android.app.Activity;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthManager;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthRecord;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YeWuInfoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private YeWuInfoAdapter adapter;
    private List<H5AuthRecord> list;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ToggleButton switch_toggle_btn;
        private TextView title;
        private TextView vice_title;

        public ViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(2131298785);
            this.vice_title = (TextView) view.findViewById(2131299504);
            this.switch_toggle_btn = (ToggleButton) view.findViewById(2131298723);
        }
    }

    public YeWuInfoAdapter(Activity activity, YeWuInfoAdapter yeWuInfoAdapter, List<H5AuthRecord> list) {
        this.list = list;
        this.adapter = yeWuInfoAdapter;
        this.activity = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493407, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(this.list.get(i).getAuthScopeHint());
        viewHolder.vice_title.setText(this.list.get(i).getAuthReson());
        viewHolder.switch_toggle_btn.setChecked(this.list.get(i).isAuthStatus());
        viewHolder.switch_toggle_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter.YeWuInfoAdapter.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                H5AuthManager.getInstance(YeWuInfoAdapter.this.activity).updateH5AuthStatus(((H5AuthRecord) YeWuInfoAdapter.this.list.get(i)).getAppId(), ((H5AuthRecord) YeWuInfoAdapter.this.list.get(i)).getAuthScopeCode(), z);
            }
        });
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }
}
