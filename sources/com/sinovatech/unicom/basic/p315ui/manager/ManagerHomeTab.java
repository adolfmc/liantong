package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.AdvertiseEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHomeTab {
    private final String TAG = ManagerHomeTab.class.getSimpleName();
    private ItemAdapter adapter;
    private String clickTabCode;
    private Dialog dialog;
    private ItemTouchHelper itemTouchHelper;
    private List<AdvertiseEntity> originList;
    private RecyclerView recyclerView;
    private List<AdvertiseEntity> sortList;
    private TabInterface tabInterface;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab$TabInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface TabInterface {
        void onTabChanged(String str);
    }

    public void saveSortData(List<AdvertiseEntity> list) {
        String str = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                str = str + list.get(i).getTabCode() + ",";
            }
        }
        App.getSharePreferenceUtil().putString("HomeSortTabData", str);
    }

    public List<AdvertiseEntity> getSortData(List<AdvertiseEntity> list) {
        String string = App.getSharePreferenceUtil().getString("HomeSortTabData");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(",")) {
                for (int i = 0; i < list.size(); i++) {
                    if (str.equals(list.get(i).getTabCode())) {
                        arrayList.add(list.get(i));
                    }
                }
            }
        }
        return list.size() == arrayList.size() ? arrayList : list;
    }

    public void saveSortOldData(List<AdvertiseEntity> list) {
        String str = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                str = str + list.get(i).getTabCode() + ",";
            }
        }
        App.getSharePreferenceUtil().putString("HomeSortTabOldData", str);
    }

    public List<AdvertiseEntity> getSortOldData() {
        String string = App.getSharePreferenceUtil().getString("HomeSortTabOldData");
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(string)) {
            for (String str : string.split(",")) {
                for (int i = 0; i < this.originList.size(); i++) {
                    if (str.equals(this.originList.get(i).getTabCode())) {
                        arrayList.add(this.originList.get(i));
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean compare(List<AdvertiseEntity> list) {
        boolean z;
        List<AdvertiseEntity> sortOldData = getSortOldData();
        if (sortOldData.size() == list.size()) {
            int i = 0;
            while (true) {
                if (i >= sortOldData.size()) {
                    z = false;
                    break;
                } else if (!sortOldData.get(i).getTabCode().equals(list.get(i).getTabCode())) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            return !z;
        }
        return false;
    }

    public void showDialog(Activity activity, List<AdvertiseEntity> list, TabInterface tabInterface) {
        this.clickTabCode = "";
        this.originList = list;
        this.sortList = getSortData(this.originList);
        this.tabInterface = tabInterface;
        this.dialog = new Dialog(activity, 2131952223);
        LinearLayout linearLayout = (LinearLayout) activity.getLayoutInflater().inflate(2131493181, (ViewGroup) null);
        initDialogView(activity, linearLayout);
        this.dialog.setContentView(linearLayout);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(true);
        this.dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.1
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                ManagerHomeTab.this.noticeHome();
            }
        });
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = (UIUtils.getScreenHeight(activity) * 4) / 5;
        Window window = this.dialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952222);
        this.dialog.show();
        StatisticsUploadUtils.uploadRealTime(activity, "13", "首页-TAB", "广告", "", "自定义", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noticeHome() {
        this.tabInterface.onTabChanged(this.clickTabCode);
        String str = "";
        for (int i = 0; i < this.sortList.size(); i++) {
            try {
                str = i == this.sortList.size() - 1 ? str + this.sortList.get(i).getTabTitle() : str + this.sortList.get(i).getTabTitle() + "-";
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        StatisticsUploadUtils.upload(App.getInstance().getApplicationContext(), "13", "首页-TAB", "广告", "", "自定义", "", "", str);
    }

    private void initDialogView(Activity activity, View view) {
        final ToggleButton toggleButton = (ToggleButton) view.findViewById(2131296882);
        this.recyclerView = (RecyclerView) view.findViewById(2131296880);
        ((ImageView) view.findViewById(2131296865)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                ManagerHomeTab.this.dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        toggleButton.setChecked(compare(this.sortList));
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                if (z) {
                    ManagerHomeTab.this.saveSortData(null);
                    ManagerHomeTab managerHomeTab = ManagerHomeTab.this;
                    managerHomeTab.sortList = managerHomeTab.getSortData(managerHomeTab.getSortOldData());
                    ManagerHomeTab.this.recyclerView.getAdapter().notifyDataSetChanged();
                    ManagerHomeTab managerHomeTab2 = ManagerHomeTab.this;
                    managerHomeTab2.saveSortData(managerHomeTab2.sortList);
                }
            }
        });
        this.itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.4
            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public boolean isLongPressDragEnabled() {
                return false;
            }

            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            }

            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(15, 0);
            }

            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public boolean onMove(@NonNull final RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder2) {
                final int adapterPosition = viewHolder.getAdapterPosition();
                final int adapterPosition2 = viewHolder2.getAdapterPosition();
                if (((AdvertiseEntity) ManagerHomeTab.this.sortList.get(adapterPosition2)).getTabCode().equalsIgnoreCase("CHOICE_CODE")) {
                    return false;
                }
                if (((AdvertiseEntity) ManagerHomeTab.this.sortList.get(adapterPosition2)).getTabCode().equalsIgnoreCase("GAME_CODE_back") && adapterPosition2 == 1) {
                    return false;
                }
                if (adapterPosition < adapterPosition2) {
                    int i = adapterPosition;
                    while (i < adapterPosition2) {
                        int i2 = i + 1;
                        Collections.swap(ManagerHomeTab.this.sortList, i, i2);
                        i = i2;
                    }
                } else {
                    for (int i3 = adapterPosition; i3 > adapterPosition2; i3--) {
                        Collections.swap(ManagerHomeTab.this.sortList, i3, i3 - 1);
                    }
                }
                recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        recyclerView.getAdapter().notifyItemMoved(adapterPosition, adapterPosition2);
                    }
                });
                return true;
            }

            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int i) {
                super.onSelectedChanged(viewHolder, i);
            }

            @Override // android.support.p086v7.widget.helper.ItemTouchHelper.Callback
            public void clearView(@NonNull final RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                MsLogUtil.m7980d("HomeWebFragment 拖动完成 " + ManagerHomeTab.this.sortList.toString());
                ManagerHomeTab managerHomeTab = ManagerHomeTab.this;
                managerHomeTab.saveSortData(managerHomeTab.sortList);
                ToggleButton toggleButton2 = toggleButton;
                ManagerHomeTab managerHomeTab2 = ManagerHomeTab.this;
                toggleButton2.setChecked(managerHomeTab2.compare(managerHomeTab2.sortList));
                recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.4.2
                    @Override // java.lang.Runnable
                    public void run() {
                        recyclerView.getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });
        this.itemTouchHelper.attachToRecyclerView(this.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(activity, 4));
        this.adapter = new ItemAdapter(activity);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab$ItemAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private Activity activityContext;

        public ItemAdapter(Activity activity) {
            this.activityContext = activity;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ItemViewHolder(this.activityContext.getLayoutInflater().inflate(2131493182, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, final int i) {
            final AdvertiseEntity advertiseEntity = (AdvertiseEntity) ManagerHomeTab.this.sortList.get(i);
            itemViewHolder.titleText.setText(advertiseEntity.getTabTitle());
            itemViewHolder.titleText.setTextColor(-13421773);
            itemViewHolder.titleText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.ItemAdapter.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    NBSActionInstrumentation.onLongClickEventEnter(view, this);
                    if (!advertiseEntity.getTabCode().equalsIgnoreCase("CHOICE_CODE") && ManagerHomeTab.this.checkYouxiIsCanDrag(i)) {
                        for (int i2 = 0; i2 < ManagerHomeTab.this.sortList.size(); i2++) {
                            try {
                                if (((AdvertiseEntity) ManagerHomeTab.this.sortList.get(i2)).getTabCode().equalsIgnoreCase("CHOICE_CODE")) {
                                    ((ItemViewHolder) ManagerHomeTab.this.recyclerView.findViewHolderForAdapterPosition(i2)).titleText.setTextColor(-6710887);
                                } else if (!ManagerHomeTab.this.checkYouxiIsCanDrag(i2)) {
                                    ((ItemViewHolder) ManagerHomeTab.this.recyclerView.findViewHolderForAdapterPosition(i2)).titleText.setTextColor(-6710887);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        ManagerHomeTab.this.itemTouchHelper.startDrag(itemViewHolder);
                    } else {
                        if (advertiseEntity.getTabCode().equalsIgnoreCase("CHOICE_CODE")) {
                            UIUtils.toastLong("精选不允许编辑");
                        } else if (advertiseEntity.getTabCode().equalsIgnoreCase("GAME_CODE_back")) {
                            UIUtils.toastLong("游戏不允许编辑");
                        }
                        itemViewHolder.titleText.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.ItemAdapter.1.1
                            @Override // android.view.View.OnTouchListener
                            public boolean onTouch(View view2, MotionEvent motionEvent) {
                                return motionEvent.getAction() == 1 || motionEvent.getAction() == 3;
                            }
                        });
                    }
                    NBSActionInstrumentation.onLongClickEventExit();
                    return false;
                }
            });
            itemViewHolder.titleText.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab.ItemAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    String tabUrl = advertiseEntity.getTabUrl();
                    if (i > 8) {
                        IntentManager.gotoWebViewActivity(ItemAdapter.this.activityContext, advertiseEntity.getH5Url(), advertiseEntity.getTabTitle());
                        tabUrl = advertiseEntity.getH5Url();
                    } else {
                        ManagerHomeTab.this.clickTabCode = advertiseEntity.getTabCode();
                        ManagerHomeTab.this.dialog.dismiss();
                    }
                    PvCurrencyLogUtils.pvLogMainDJ(PvCurrencyLogUtils.getPostion("10901", i), tabUrl + "", "", advertiseEntity.getTabTitle() + "", "首页-TAB-切换");
                    PvCurrencyLogUtils.pvLogMainOmoBG(advertiseEntity.getTabUrl(), "进入", advertiseEntity.getTabTitle());
                    try {
                        PvCurrencyLogUtils.pvLogMainOmoBG(advertiseEntity.getTabUrl(), "退出", "");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return ManagerHomeTab.this.sortList.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeTab$ItemViewHolder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleText;

        public ItemViewHolder(View view) {
            super(view);
            this.titleText = (TextView) view.findViewById(2131297326);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkYouxiIsCanDrag(int i) {
        return (this.sortList.size() >= 2 && "GAME_CODE_back".equals(this.sortList.get(i).getTabCode()) && i == 1) ? false : true;
    }
}
