package com.sinovatech.unicom.separatemodule.recentmenu;

import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MiniGridAdpater extends RecyclerView.Adapter<Holder> {
    private AdapterClickInterface adapterClickInterface;
    private List<RecentMiniEntity> list;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface AdapterClickInterface {
        void onClick(int i, RecentMiniEntity recentMiniEntity);
    }

    public MiniGridAdpater(List<RecentMiniEntity> list) {
        this.list = list;
    }

    public void update(List<RecentMiniEntity> list) {
        update(list, false);
    }

    public void update(List<RecentMiniEntity> list, boolean z) {
        if (list == null) {
            try {
                list = new ArrayList<>();
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
                return;
            }
        }
        if (!z && list != null && list.size() > 8) {
            list = list.subList(0, 8);
        }
        this.list = list;
        notifyDataSetChanged();
    }

    public void setAdapterClickInterface(AdapterClickInterface adapterClickInterface) {
        this.adapterClickInterface = adapterClickInterface;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        try {
            return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493162, viewGroup, false));
        } catch (Exception e) {
            MsLogUtil.m7978e("创建ViewHolder异常:" + e.getMessage());
            return null;
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final Holder holder, int i) {
        if (holder == null) {
            return;
        }
        try {
            final RecentMiniEntity recentMiniEntity = this.list.get(i);
            if (recentMiniEntity == null) {
                return;
            }
            holder.textView.setText(recentMiniEntity.getAppName());
            GlideApp.with(App.getInstance()).load(recentMiniEntity.getAppImg()).placeholder(2131231246).error(2131231246).into(holder.imageView);
            holder.rootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (MiniGridAdpater.this.adapterClickInterface != null) {
                        MiniGridAdpater.this.adapterClickInterface.onClick(holder.getAdapterPosition(), recentMiniEntity);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<RecentMiniEntity> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void removeItem(int i) {
        try {
            if (this.list != null && i >= 0 && i <= this.list.size()) {
                this.list.remove(i);
                notifyItemRemoved(i);
            }
        } catch (Exception unused) {
            MsLogUtil.m7978e("删除单个收藏失败");
        }
    }

    public void removeItemFromDrag(int i) {
        List<RecentMiniEntity> list = this.list;
        if (list != null && i >= 0 && i <= list.size()) {
            this.list.remove(i);
            notifyItemRemoved(i);
            notifyItemRangeChanged(i, getItemCount() - i, "payload");
        }
    }

    public void addItem(RecentMiniEntity recentMiniEntity) {
        try {
            if (this.list == null) {
                return;
            }
            Iterator<RecentMiniEntity> it = this.list.iterator();
            while (it.hasNext()) {
                RecentMiniEntity next = it.next();
                if (next != null && (TextUtils.equals(recentMiniEntity.getAppId(), next.getAppId()) || TextUtils.equals(recentMiniEntity.getProductId(), next.getProductId()))) {
                    it.remove();
                    break;
                }
            }
            this.list.add(0, recentMiniEntity);
            if (this.list.size() > 8) {
                this.list = this.list.subList(0, 8);
            }
            notifyDataSetChanged();
        } catch (Exception e) {
            MsLogUtil.m7978e("添加收藏本地刷新失败" + e.getMessage());
        }
    }

    public List<RecentMiniEntity> getList() {
        List<RecentMiniEntity> list = this.list;
        return list == null ? new ArrayList() : list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout rootLayout;
        private TextView textView;

        public Holder(View view) {
            super(view);
            this.rootLayout = (RelativeLayout) view.findViewById(2131297140);
            this.imageView = (ImageView) view.findViewById(2131297141);
            this.textView = (TextView) view.findViewById(2131297144);
        }
    }
}
