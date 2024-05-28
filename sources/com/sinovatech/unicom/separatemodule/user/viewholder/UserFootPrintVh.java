package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.adapter.UserFootGridAdapter;
import com.sinovatech.unicom.separatemodule.user.entity.UserFootPrintEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserFootPrint;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserFootPrintVh extends RVItemViewHolder {
    public static int page;
    private final String TAG;
    private AppCompatActivity activityContext;
    private final LinearLayout footIndicate;
    private final ViewPager footViewPager;
    private final LinearLayout moreLayout;
    private final TextView title_tv;

    public UserFootPrintVh(Activity activity, View view) {
        super(activity, view);
        this.TAG = "UserFootPrintVh";
        this.activityContext = (AppCompatActivity) activity;
        this.footViewPager = (ViewPager) view.findViewById(2131299365);
        this.footIndicate = (LinearLayout) view.findViewById(2131299366);
        this.moreLayout = (LinearLayout) view.findViewById(2131298082);
        this.title_tv = (TextView) view.findViewById(2131298801);
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (rVItemEntity.refresh) {
                rVItemEntity.refresh = false;
                if (!App.hasLogined()) {
                    setVisibility(false);
                    return;
                }
                initView();
                initData();
                ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.USERFOOT, this.itemView);
            }
        }
    }

    private void initData() {
        try {
            cacheLogic();
            netLogic();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7999d("UserFootPrintVh", "我的足迹加载数据异常\n" + e.getMessage());
        }
    }

    private void initView() {
        this.moreLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserFootPrintVh.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CollectionMoreActivity.newStartActivity(UserFootPrintVh.this.activityContext, 1, "我的足迹");
                UserFootPrintVh.this.clickCollect("5060201", "更多");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(String str, String str2) {
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("我的").setPbName(str2).setCodeId(str).setStoreyCode(str.substring(0, 3)).build());
        PvCurrencyLogUtils.sendServicePvLog(str, "我的足迹-更多", "My page", str2, "", "2", "1", "");
    }

    private List<UserFootPrintEntity> changeList(List<RecentMiniEntity> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            try {
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        RecentMiniEntity recentMiniEntity = list.get(i);
                        UserFootPrintEntity userFootPrintEntity = new UserFootPrintEntity();
                        userFootPrintEntity.setShoppingCartUrls(recentMiniEntity.getAppletUrl());
                        userFootPrintEntity.setShoppingCartImgSrc(recentMiniEntity.getAppImg());
                        userFootPrintEntity.setShoppingCartTitle(recentMiniEntity.getAppName());
                        arrayList.add(userFootPrintEntity);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showView(final List<UserFootPrintEntity> list) {
        if (list != null) {
            List<UserFootPrintEntity> cropList = cropList(list);
            if (cropList != null && cropList.size() > 0) {
                defaultScan(list);
                new UserFootGridAdapter(this.activityContext, new ArrayList(), 0).initGridView(cropList, this.footViewPager, this.footIndicate, 4, new UserFootGridAdapter.PageSelectCallBack() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserFootPrintVh.2
                    @Override // com.sinovatech.unicom.separatemodule.user.adapter.UserFootGridAdapter.PageSelectCallBack
                    public void page(int i) {
                        ArrayList arrayList = new ArrayList();
                        MsLogUtil.m7999d("baoguang", "当前是" + UserFootPrintVh.page);
                        int i2 = 0;
                        if (i == 0) {
                            while (i2 < list.size()) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("506010");
                                int i3 = i2 + 1;
                                sb.append(i3);
                                arrayList.add(new UserLightEntity(sb.toString(), ((UserFootPrintEntity) list.get(i2)).getShoppingCartTitle()));
                                if (arrayList.size() >= 4) {
                                    break;
                                }
                                i2 = i3;
                            }
                        } else {
                            while (i2 < list.size()) {
                                if (i2 >= 4) {
                                    arrayList.add(new UserLightEntity("506010" + (i2 + 1), ((UserFootPrintEntity) list.get(i2)).getShoppingCartTitle()));
                                }
                                i2++;
                            }
                        }
                        if (UserFootPrintVh.this.moreLayout.getVisibility() == 0) {
                            arrayList.add(new UserLightEntity("5060201", "更多"));
                        }
                        ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.USERFOOT, arrayList);
                    }
                });
                setVisibility(true);
                return;
            }
            setVisibility(false);
        }
    }

    private void defaultScan(List<UserFootPrintEntity> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            StringBuilder sb = new StringBuilder();
            sb.append("506010");
            int i2 = i + 1;
            sb.append(i2);
            arrayList.add(new UserLightEntity(sb.toString(), list.get(i).getShoppingCartTitle()));
            if (arrayList.size() >= 4) {
                break;
            }
            i = i2;
        }
        if (this.moreLayout.getVisibility() == 0) {
            arrayList.add(new UserLightEntity("5060201", "更多"));
        }
        ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.USERFOOT, arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheLogic() {
        try {
            List<RecentMiniEntity> list = RecentBoxManager.getInstance().get();
            StringBuilder sb = new StringBuilder();
            sb.append(list != null ? list.size() : 0);
            sb.append("");
            MsLogUtil.m7999d("UserFootPrintVh", sb.toString());
            if (list != null) {
                moreLayoutVisibleLogic(list);
            }
            List<UserFootPrintEntity> changeList = changeList(list);
            UserFootPrintEntity cacheData = ManagerUserFootPrint.getInstance().cacheData();
            if (cacheData == null) {
                cacheData = ManagerUserFootPrint.getInstance().getDefaultData();
            }
            if (!TextUtils.isEmpty(cacheData.getFootMarkFloorTitle())) {
                this.title_tv.setText(cacheData.getFootMarkFloorTitle());
            }
            if ("1".equals(cacheData.getFootMarkSwitch())) {
                if (cacheData.isShow()) {
                    changeList.add(0, cacheData);
                }
                setVisibility(true);
                showView(changeList);
                return;
            }
            setVisibility(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void moreLayoutVisibleLogic(List<RecentMiniEntity> list) {
        if (list.size() > 1) {
            this.moreLayout.setVisibility(0);
        } else {
            this.moreLayout.setVisibility(8);
        }
    }

    private void netLogic() {
        try {
            List<RecentMiniEntity> list = RecentBoxManager.getInstance().get();
            StringBuilder sb = new StringBuilder();
            sb.append(list != null ? list.size() : 0);
            sb.append("");
            MsLogUtil.m7999d("UserFootPrintVh", sb.toString());
            if (list != null) {
                moreLayoutVisibleLogic(list);
            }
            final List<UserFootPrintEntity> changeList = changeList(list);
            ManagerUserFootPrint.getInstance().getUserFootPrint(this.activityContext).subscribe(new Consumer<UserFootPrintEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserFootPrintVh.3
                @Override // io.reactivex.functions.Consumer
                public void accept(UserFootPrintEntity userFootPrintEntity) throws Exception {
                    if (userFootPrintEntity == null) {
                        UserFootPrintVh.this.cacheLogic();
                    } else if ("1".equals(userFootPrintEntity.getFootMarkSwitch())) {
                        if (userFootPrintEntity.isShow()) {
                            changeList.add(0, userFootPrintEntity);
                            if (!TextUtils.isEmpty(userFootPrintEntity.getFootMarkFloorTitle())) {
                                UserFootPrintVh.this.title_tv.setText(userFootPrintEntity.getFootMarkFloorTitle());
                            }
                        }
                        UserFootPrintVh.this.showView(changeList);
                    } else {
                        UserFootPrintVh.this.setVisibility(false);
                    }
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserFootPrintVh$ZznS5SL8woMDY2kLggBZBMZxZiE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    UserFootPrintVh.this.cacheLogic();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<UserFootPrintEntity> cropList(List<UserFootPrintEntity> list) {
        if (list != null) {
            try {
                return list.size() > 8 ? list.subList(0, 8) : list;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return list;
    }
}
