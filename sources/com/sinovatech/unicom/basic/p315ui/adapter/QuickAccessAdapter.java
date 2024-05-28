package com.sinovatech.unicom.basic.p315ui.adapter;

import android.app.Activity;
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
import org.json.JSONArray;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.QuickAccessAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class QuickAccessAdapter extends RecyclerView.Adapter<QuickViewHolder> {
    private int adapter_type;
    private JSONArray array;
    private Activity context;
    OnItemClickLister onItemClickLister;
    private int[] quickResources = {2131231190, 2131230819, 2131232040, 2131232039, 2131231975, 2131232425, 2131232041, 2131231273, 2131232225, 2131232433};
    private int shortcutInfoImg;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.QuickAccessAdapter$OnItemClickLister */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemClickLister {
        void setOnItemClick(int i, int i2);
    }

    public QuickAccessAdapter(int i, JSONArray jSONArray, Activity activity) {
        this.adapter_type = 0;
        this.adapter_type = i;
        this.array = jSONArray;
        this.context = activity;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public QuickViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(LayoutInflater.from(this.context).inflate(2131493403, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, final int i) {
        if (quickViewHolder != null) {
            if (this.adapter_type == 0) {
                quickViewHolder.menuAdd.setVisibility(8);
                quickViewHolder.menuRemove.setVisibility(0);
            } else {
                quickViewHolder.menuAdd.setVisibility(0);
                quickViewHolder.menuRemove.setVisibility(8);
            }
            int parseInt = Integer.parseInt(this.array.optJSONObject(i).optString("iconType"));
            int[] iArr = this.quickResources;
            if (parseInt <= iArr.length) {
                this.shortcutInfoImg = iArr[Integer.parseInt(this.array.optJSONObject(i).optString("iconType")) - 1];
            } else {
                this.shortcutInfoImg = iArr[0];
            }
            quickViewHolder.menuIv.setImageResource(this.shortcutInfoImg);
            quickViewHolder.menuTv.setText(this.array.optJSONObject(i).optString("title"));
            quickViewHolder.menuRemove.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.QuickAccessAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (QuickAccessAdapter.this.onItemClickLister != null) {
                        QuickAccessAdapter.this.onItemClickLister.setOnItemClick(i, QuickAccessAdapter.this.adapter_type);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            quickViewHolder.menuAdd.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.adapter.QuickAccessAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (QuickAccessAdapter.this.onItemClickLister != null) {
                        QuickAccessAdapter.this.onItemClickLister.setOnItemClick(i, QuickAccessAdapter.this.adapter_type);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.array.length();
    }

    public void setOnItemBottomClickListenr(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    public void setOnItemTopClickListenr(OnItemClickLister onItemClickLister) {
        this.onItemClickLister = onItemClickLister;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.adapter.QuickAccessAdapter$QuickViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class QuickViewHolder extends RecyclerView.ViewHolder {
        LinearLayout menuAdd;
        ImageView menuIv;
        LinearLayout menuRemove;
        TextView menuTv;

        public QuickViewHolder(View view) {
            super(view);
            this.menuAdd = (LinearLayout) view.findViewById(2131298051);
            this.menuRemove = (LinearLayout) view.findViewById(2131298059);
            this.menuIv = (ImageView) view.findViewById(2131298056);
            this.menuTv = (TextView) view.findViewById(2131298058);
        }
    }
}
