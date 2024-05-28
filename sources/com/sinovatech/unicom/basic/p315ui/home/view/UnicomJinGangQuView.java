package com.sinovatech.unicom.basic.p315ui.home.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jakewharton.rxbinding2.view.RxView;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.p314po.HeaderChildEntity;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.home.adapter.HomeCardJinGangQuAdapter;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomJinGangQuView extends LinearLayout {
    private static final String TAG = "UnicomJinGangQuView";
    public static boolean clickCustomMenu;
    private LinearLayoutManager layoutManager;
    private AppCompatActivity mActivity;
    private HomeCardJinGangQuAdapter mAdapter;
    private View mLinIndicator;
    private View mLinIndicatorLayout;
    private LinearLayout mLinMore;
    private List<MenuEntity> mList;
    private RecyclerView mRVJinGangQu;
    private TextView mTvMore;
    private View mView;

    public UnicomJinGangQuView(Context context) {
        super(context);
        this.mActivity = (AppCompatActivity) context;
        initView();
        initRecycleView();
        MsLogUtil.m7979d("金刚区初始化", "初始化view");
        notifyData();
    }

    private void initView() {
        try {
            this.mView = View.inflate(this.mActivity, 2131493484, this);
            this.mRVJinGangQu = (RecyclerView) this.mView.findViewById(2131297542);
            this.mTvMore = (TextView) this.mView.findViewById(2131297541);
            this.mLinMore = (LinearLayout) this.mView.findViewById(2131297540);
            RxView.clicks(this.mLinMore).throttleFirst(1000L, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    MsLogUtil.m7979d("服务页面", "跳转到服务页面");
                    String charSequence = UnicomJinGangQuView.this.mTvMore.getText().toString();
                    if (TextUtils.isEmpty(charSequence)) {
                        charSequence = "服务大厅";
                    }
                    EventBusUtils.post(new ServiceMenuEvent(EventBusUtils.EVENT_FUWU_TIAOZHUAN));
                    UnicomHomeLogUtils.getInstance().clickLog(String.valueOf(1070201), charSequence);
                    PvCurrencyLogUtils.pvLogHomeJingangqu(String.valueOf(1070201), "首页金刚区服务大厅", UnicomJinGangQuView.this.mTvMore.getText().toString(), "", "", "", "");
                }
            });
            this.mLinIndicatorLayout = this.mView.findViewById(2131297539);
            this.mLinIndicator = this.mView.findViewById(2131297538);
            updateMoreText();
            changeIndicatorState();
        } catch (Exception e) {
            MsLogUtil.m7978e("初始化view异常:" + e.getMessage());
        }
    }

    public void changeIndicatorState() {
        try {
            if (App.hasLogined() && UserManager.getInstance().isYiwang()) {
                this.mLinIndicator.setVisibility(8);
                this.mLinIndicatorLayout.setVisibility(8);
                this.mLinMore.setVisibility(8);
                this.mRVJinGangQu.setLayoutManager(new GridLayoutManager((Context) this.mActivity, 5, 1, false));
            } else {
                this.mLinIndicator.setVisibility(0);
                this.mLinIndicatorLayout.setVisibility(0);
                this.mLinMore.setVisibility(0);
                this.layoutManager = new LinearLayoutManager(this.mActivity, 0, false);
                this.mRVJinGangQu.setLayoutManager(this.layoutManager);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "修改指示器状态异常:" + e.getMessage());
        }
    }

    private void initRecycleView() {
        try {
            this.mList = new ArrayList();
            if (!App.hasLogined() || !UserManager.getInstance().isYiwang()) {
                this.mRVJinGangQu.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView.2
                    @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                    public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                        super.onScrolled(recyclerView, i, i2);
                        try {
                            int computeHorizontalScrollRange = UnicomJinGangQuView.this.mRVJinGangQu.computeHorizontalScrollRange();
                            int i3 = computeHorizontalScrollRange > 0 ? computeHorizontalScrollRange : 0;
                            int computeHorizontalScrollExtent = UnicomJinGangQuView.this.mRVJinGangQu.computeHorizontalScrollExtent();
                            if (i3 > computeHorizontalScrollExtent) {
                                UnicomJinGangQuView.this.mLinIndicatorLayout.setBackgroundResource(2131231733);
                                UnicomJinGangQuView.this.mLinIndicator.setTranslationX((UnicomJinGangQuView.this.mLinIndicatorLayout.getWidth() - UnicomJinGangQuView.this.mLinIndicator.getWidth()) * ((float) ((UnicomJinGangQuView.this.mRVJinGangQu.computeHorizontalScrollOffset() * 1.0d) / (i3 - computeHorizontalScrollExtent))));
                            } else {
                                UnicomJinGangQuView.this.mLinIndicatorLayout.setBackgroundResource(2131231734);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7977e(UnicomJinGangQuView.TAG, "滑动改变指示器异常:" + e.getMessage());
                        }
                    }
                });
            }
            this.mAdapter = new HomeCardJinGangQuAdapter(this.mActivity, this.mList);
            this.mRVJinGangQu.setAdapter(this.mAdapter);
            this.mRVJinGangQu.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView.3
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        UnicomJinGangQuView.this.addBaoGuangLog();
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化金刚区recycleview异常:" + e.getMessage());
        }
    }

    public void addBaoGuangLog() {
        try {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRVJinGangQu.getLayoutManager();
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            boolean z = false;
            if (findFirstVisibleItemPosition < 0) {
                findFirstVisibleItemPosition = 0;
                z = true;
            }
            if (z) {
                if (findLastVisibleItemPosition < 3) {
                    findLastVisibleItemPosition = this.mList.size() > 3 ? 3 : this.mList.size() - 1;
                }
                if (findLastVisibleItemPosition - findFirstVisibleItemPosition >= 4) {
                    findLastVisibleItemPosition--;
                }
            } else {
                if (findLastVisibleItemPosition < 4) {
                    findLastVisibleItemPosition = this.mList.size() > 4 ? 4 : this.mList.size() - 1;
                }
                if (findLastVisibleItemPosition - findFirstVisibleItemPosition >= 5) {
                    findLastVisibleItemPosition--;
                }
            }
            ArrayList arrayList = new ArrayList();
            while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
                arrayList.add(new HomeLogEntity(String.valueOf(1070100 + findFirstVisibleItemPosition + 1), this.mList.get(findFirstVisibleItemPosition).getMenuTitle()));
                findFirstVisibleItemPosition++;
            }
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_JGQ, arrayList);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "添加曝光数据异常:" + e.getMessage());
        }
    }

    public void updateMoreText() {
        try {
            String string = App.getSharePreferenceUtil().getString("HOME_JGQ_TITLE");
            if (TextUtils.isEmpty(string)) {
                string = "服务大厅";
            }
            this.mTvMore.setText(string);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "修改服务大厅文案异常:" + e.getMessage());
        }
    }

    public void notifyData() {
        try {
            Observable.fromCallable(new Callable<List<MenuEntity>>() { // from class: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView.5
                @Override // java.util.concurrent.Callable
                public List<MenuEntity> call() throws Exception {
                    return UnicomJinGangQuView.this.getmList();
                }
            }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<MenuEntity>>() { // from class: com.sinovatech.unicom.basic.ui.home.view.UnicomJinGangQuView.4
                @Override // io.reactivex.functions.Consumer
                public void accept(List<MenuEntity> list) throws Exception {
                    UnicomJinGangQuView.this.mList.clear();
                    UnicomJinGangQuView.this.mList.addAll(list);
                    UnicomJinGangQuView.this.mAdapter.notifyDataSetChanged();
                    UnicomJinGangQuView.this.mRVJinGangQu.scrollToPosition(0);
                    if (!UserManager.getInstance().isYiwang()) {
                        if (UnicomJinGangQuView.this.mList.size() > 4) {
                            UnicomJinGangQuView.this.mLinIndicator.setVisibility(0);
                            UnicomJinGangQuView.this.mLinIndicatorLayout.setVisibility(0);
                        } else {
                            UnicomJinGangQuView.this.mLinIndicator.setVisibility(8);
                            UnicomJinGangQuView.this.mLinIndicatorLayout.setVisibility(8);
                        }
                    }
                    UnicomJinGangQuView.this.addBaoGuangLog();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "刷新金刚区数据异常:" + e.getMessage());
        }
    }

    public List<MenuEntity> getmList() {
        HeaderEntity headerEntity;
        List<HeaderChildEntity> headerEntityList;
        HeaderChildEntity headerChildEntity;
        MsLogUtil.m7979d("金刚区", "getmList 获取缓存数据8");
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.addAll(MenuDataCenter.getInstance().getHomeDataNew());
            if (App.hasLogined() && !UserManager.getInstance().isYiwang() && !"03".equals(UserManager.getInstance().getLoginType()) && !"04".equals(UserManager.getInstance().getLoginType()) && !"02".equals(UserManager.getInstance().getLoginType()) && (headerEntity = HomeCardDataManager.getInstance().getHeaderEntity()) != null && (headerEntityList = headerEntity.getHeaderEntityList()) != null && headerEntityList.size() != 0 && (headerChildEntity = headerEntityList.get(0)) != null && TextUtils.equals("1", headerChildEntity.getIsWarn()) && arrayList.size() > 0) {
                MenuEntity menuEntity = (MenuEntity) arrayList.get(0);
                menuEntity.setIsWarn("1");
                arrayList.set(0, menuEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void loadMenuJinGangQu() {
        try {
            updateMoreText();
            notifyData();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载金刚区数据异常:" + e.getMessage());
        }
    }
}
