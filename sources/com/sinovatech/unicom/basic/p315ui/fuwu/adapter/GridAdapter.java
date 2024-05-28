package com.sinovatech.unicom.basic.p315ui.fuwu.adapter;

import android.app.Activity;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.GridAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private String actCode;
    private Activity activity;
    private List<MenuEntity> itemList;
    private String transId;

    public GridAdapter(Activity activity, List<MenuEntity> list, String str, String str2) {
        this.itemList = list;
        this.activity = activity;
        this.transId = str;
        this.actCode = str2;
    }

    public MenuEntity getItem(int i) {
        return this.itemList.get(i);
    }

    public String getTransId() {
        return this.transId;
    }

    public void setData() {
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493134, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final MenuEntity menuEntity = this.itemList.get(i);
        String menuTitle = menuEntity.getMenuTitle();
        if (!TextUtils.isEmpty(menuTitle)) {
            int length = menuTitle.length();
            if (length <= 5) {
                viewHolder.textView.setText(menuTitle);
            } else if (length <= 8) {
                viewHolder.textView.setText(menuTitle.substring(0, 4) + "\n" + menuTitle.substring(4));
            } else if (length <= 10) {
                viewHolder.textView.setText(menuTitle.substring(0, 5) + "\n" + menuTitle.substring(5));
            } else {
                viewHolder.textView.setText(menuTitle);
            }
        } else {
            viewHolder.textView.setText("");
        }
        GlideApp.with(this.activity).asBitmap().placeholder(2131231795).error(2131231795).load(menuEntity.getMenuIconURL()).into(viewHolder.imageView);
        viewHolder.imageView.setContentDescription(menuEntity.getMenuTitle());
        viewHolder.rl_all.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.adapter.GridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!FuWuConstant.isBianJiState) {
                    FuWuUtils.clickCollect(GridAdapter.this.transId, menuEntity.getMenuTitle(), menuEntity.getMenuURL());
                    PvCurrencyLogUtils.pvLogFuWu(GridAdapter.this.transId, GridAdapter.this.actCode, menuEntity.getMenuURL(), menuEntity.getMenuTitle(), menuEntity.getGroupTitle());
                    IntentManager.generateIntentAndGo(GridAdapter.this.activity, menuEntity, "get");
                    RecentBoxManager.getInstance().put(menuEntity);
                    PvCurrencyLogUtils.addMenu(menuEntity);
                } else if (!MenuDataCenter.getInstance().isCunZai(menuEntity.getMenuTitle())) {
                    GridAdapter.this.addIcon(menuEntity);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        viewHolder.iv_icon.setOnClickListener(null);
        viewHolder.lin_iv_click.setOnClickListener(null);
        if (FuWuConstant.isBianJiState) {
            viewHolder.rl_item_bg.setImageResource(2131231308);
            if (MenuDataCenter.getInstance().isCunZai(menuEntity.getMenuTitle())) {
                viewHolder.iv_icon.setImageResource(2131231322);
                return;
            }
            viewHolder.iv_icon.setImageResource(2131231319);
            viewHolder.iv_icon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.adapter.GridAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    GridAdapter.this.addIcon(menuEntity);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return;
        }
        viewHolder.iv_icon.setImageResource(0);
        viewHolder.rl_item_bg.setImageResource(2131231310);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addIcon(MenuEntity menuEntity) {
        if (MenuDataCenter.getInstance().isMaxSize()) {
            UIUtils.showCenterOnlyTextFuWuToast(this.activity, "最多可添加8个首页应用", 1000, 191);
            return;
        }
        ServiceMenuEvent serviceMenuEvent = new ServiceMenuEvent(EventBusUtils.EVENT_FUWU_ADDTOPLIST);
        serviceMenuEvent.setMenuEntity(menuEntity);
        EventBusUtils.post(serviceMenuEvent);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.GridAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private ImageView iv_icon;
        private LinearLayout lin_iv_click;
        private RelativeLayout rl_all;
        private ImageView rl_item_bg;
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            this.textView = (TextView) view.findViewById(2131297052);
            this.imageView = (ImageView) view.findViewById(2131297051);
            this.rl_item_bg = (ImageView) view.findViewById(2131298347);
            this.iv_icon = (ImageView) view.findViewById(2131297398);
            this.rl_all = (RelativeLayout) view.findViewById(2131298308);
            this.lin_iv_click = (LinearLayout) view.findViewById(2131297606);
        }
    }
}
