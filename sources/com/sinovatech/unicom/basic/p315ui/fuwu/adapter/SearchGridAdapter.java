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
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.SearchGridAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchGridAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Activity activity;
    private List<MenuEntity> itemList;

    public SearchGridAdapter(Activity activity, List<MenuEntity> list) {
        this.itemList = list;
        this.activity = activity;
    }

    public void setData(List<MenuEntity> list) {
        this.itemList = list;
        notifyDataSetChanged();
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493139, viewGroup, false));
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bindData(this.activity, this.itemList.get(i), i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.SearchGridAdapter$ViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
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

        public void bindData(final Activity activity, final MenuEntity menuEntity, int i) {
            String menuTitle = menuEntity.getMenuTitle();
            if (!TextUtils.isEmpty(menuTitle)) {
                int length = menuTitle.length();
                if (length <= 5) {
                    this.textView.setText(menuTitle);
                } else if (length <= 8) {
                    this.textView.setText(menuTitle.substring(0, 4) + "\n" + menuTitle.substring(4));
                } else if (length <= 10) {
                    this.textView.setText(menuTitle.substring(0, 5) + "\n" + menuTitle.substring(5));
                } else {
                    this.textView.setText(menuTitle);
                }
            } else {
                this.textView.setText("");
            }
            GlideApp.with(activity).asBitmap().placeholder(2131231795).error(2131231795).load(menuEntity.getMenuIconURL()).into(this.imageView);
            this.imageView.setContentDescription(menuEntity.getMenuTitle());
            this.rl_item_bg.setImageResource(2131231308);
            this.iv_icon.setOnClickListener(null);
            this.lin_iv_click.setOnClickListener(null);
            this.rl_all.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.adapter.SearchGridAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!FuWuConstant.isBianJiState) {
                        IntentManager.generateIntentAndGo(activity, menuEntity.getMenuURL(), menuEntity.getMenuTitle(), false, "get");
                    } else if (!MenuDataCenter.getInstance().isCunZai(menuEntity.getMenuTitle())) {
                        ViewHolder.this.addIcon(activity, menuEntity);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            if (FuWuConstant.isBianJiState) {
                if (MenuDataCenter.getInstance().isCunZai(menuEntity.getMenuTitle())) {
                    this.iv_icon.setImageResource(2131231322);
                    return;
                }
                this.iv_icon.setImageResource(2131231319);
                this.iv_icon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.adapter.SearchGridAdapter.ViewHolder.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        ViewHolder.this.addIcon(activity, menuEntity);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                return;
            }
            this.iv_icon.setImageResource(0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addIcon(Activity activity, MenuEntity menuEntity) {
            if (MenuDataCenter.getInstance().isMaxSize()) {
                UIUtils.showCenterOnlyTextFuWuToast(activity, "最多可添加8个首页应用", 1000, 191);
                return;
            }
            MsLogUtil.m7979d("服务页面", "可以添加！");
            ServiceMenuEvent serviceMenuEvent = new ServiceMenuEvent(EventBusUtils.EVENT_FUWU_ADDTOPLIST);
            serviceMenuEvent.setMenuEntity(menuEntity);
            EventBusUtils.post(serviceMenuEvent);
        }
    }
}
