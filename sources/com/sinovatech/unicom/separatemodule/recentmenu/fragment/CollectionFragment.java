package com.sinovatech.unicom.separatemodule.recentmenu.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinovatech.unicom.basic.eventbus.ChangeCollectionGuanLiStateEvent;
import com.sinovatech.unicom.basic.eventbus.CollectionEvent;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionParamsEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionFragment extends BaseFragment {
    private static final String TAG = "XiaLaFragment";
    public NBSTraceUnit _nbs_trace;
    private String categoryId;
    private View fragmentCacheView;
    private int fragmentType;
    public boolean isFragmentVisible;
    private boolean isPrepared;
    private AppCompatActivity mActivity;
    private CollectionMoreAdapter mAdapter;
    private CheckBox mCb_CheckAll;
    private LinearLayout mLinFailLayout;
    private RelativeLayout mLin_Bottom;
    private LinearLayout mLin_CheckAll;
    private RecyclerView mRv_Recycleview;
    private SmartRefreshLayout mSrl_Refresh;
    private TextView mTvEmpty;
    private TextView mTvRefreshButton;
    private TextView mTv_ShanChu;
    private CollectionParamsEntity paramsEntity;

    /* renamed from: pd */
    private ProgressDialog f18603pd;
    private RecentCustomManager recentCustomManager;
    List<RecentMiniEntity> mList = new ArrayList();
    private String fromType = "";
    private int pageNum = 1;
    public String searchContent = "";
    private boolean isFirstLoad = true;
    private boolean isRecnet = true;
    private boolean isItemChangeCheck = false;
    private boolean isCurrentSearch = false;

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment");
    }

    public static CollectionFragment newInstance(CollectionParamsEntity collectionParamsEntity) {
        CollectionFragment collectionFragment = new CollectionFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("XiaLaFragment_Params", collectionParamsEntity);
        collectionFragment.setArguments(bundle);
        return collectionFragment;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (AppCompatActivity) context;
        this.recentCustomManager = new RecentCustomManager(this.mActivity);
        this.f18603pd = new CustomePorgressDialog(this.mActivity);
        this.f18603pd.setCancelable(false);
        this.f18603pd.setCanceledOnTouchOutside(false);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        EventBusUtils.register(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.paramsEntity = (CollectionParamsEntity) arguments.getParcelable("XiaLaFragment_Params");
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131493133, viewGroup, false);
        this.fragmentCacheView = inflate;
        this.isFirstLoad = true;
        initView(inflate);
        initData();
        initListener();
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment");
        return view3;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.isPrepared = true;
        loadData();
    }

    private void initView(View view) {
        if (view == null) {
            return;
        }
        try {
            this.mSrl_Refresh = (SmartRefreshLayout) view.findViewById(2131299832);
            this.mCb_CheckAll = (CheckBox) view.findViewById(2131299826);
            this.mLin_CheckAll = (LinearLayout) view.findViewById(2131299827);
            this.mTv_ShanChu = (TextView) view.findViewById(2131299836);
            this.mLin_Bottom = (RelativeLayout) view.findViewById(2131299825);
            this.mRv_Recycleview = (RecyclerView) view.findViewById(2131299831);
            this.mLinFailLayout = (LinearLayout) view.findViewById(2131299828);
            this.mTvEmpty = (TextView) view.findViewById(2131299829);
            this.mTvRefreshButton = (TextView) view.findViewById(2131299833);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "收藏类别详情页初始化view异常:" + e.getMessage());
        }
    }

    public void initListener() {
        this.mSrl_Refresh.setEnableRefresh(!this.isRecnet);
        this.mSrl_Refresh.setEnableLoadMore(!this.isRecnet);
        this.mSrl_Refresh.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.1
            @Override // com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                CollectionFragment.this.getCollectionData();
            }
        });
        this.mSrl_Refresh.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.2
            @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                CollectionFragment.this.pageNum = 1;
                CollectionFragment.this.getCollectionData();
            }
        });
        this.mCb_CheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Tracker.onCheckedChanged(compoundButton, z);
                if (!CollectionFragment.this.isItemChangeCheck) {
                    CollectionFragment.this.checkAllState(z);
                }
                CollectionFragment.this.isItemChangeCheck = false;
            }
        });
        this.mTv_ShanChu.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CollectionFragment.this.batchDelete();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mLin_CheckAll.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                boolean isChecked = CollectionFragment.this.mCb_CheckAll.isChecked();
                CollectionFragment.this.isItemChangeCheck = false;
                CollectionFragment.this.mCb_CheckAll.setChecked(!isChecked);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mTvRefreshButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CollectionFragment.this.cancelListState();
                CollectionFragment.this.pdShow();
                CollectionFragment.this.getCollectionData();
                CollectionFragment.this.log("点击刷新重试按钮");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    private void loadData() {
        if (this.isPrepared && this.isFragmentVisible && this.isFirstLoad) {
            this.isFirstLoad = false;
            if (this.isRecnet) {
                notifyCollectionData(true, RecentBoxManager.getInstance().get("2", this.searchContent.trim()));
                return;
            }
            if (!this.isCurrentSearch) {
                pdShow();
            }
            this.isCurrentSearch = false;
            getCollectionData();
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
        if (getUserVisibleHint()) {
            onVisible();
        } else {
            onInvisible();
        }
    }

    protected void onInvisible() {
        this.isFragmentVisible = false;
        cancelListState();
    }

    protected void onVisible() {
        this.isFragmentVisible = true;
        if (!this.isFirstLoad && !TextUtils.equals(this.searchContent, CollectionMoreActivity.mStrSearchContent)) {
            this.isFirstLoad = true;
            this.pageNum = 1;
        }
        loadData();
    }

    public void initRecycleView() {
        this.mAdapter = new CollectionMoreAdapter(this.mActivity, this.fragmentType, this.mList);
        this.mAdapter.setCheckListener(new CollectionMoreAdapter.ChangeCheckListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.7
            @Override // com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.ChangeCheckListener
            public void onCheckState(RecentMiniEntity recentMiniEntity, int i) {
                if (CollectionFragment.this.mList != null) {
                    CollectionFragment.this.mList.set(i, recentMiniEntity);
                    CollectionFragment.this.checkAllState();
                }
            }
        });
        this.mAdapter.setItemClickListener(new CollectionMoreAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.8
            @Override // com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.OnItemClickListener
            public void onItemClick(RecentMiniEntity recentMiniEntity) {
                MenuEntity menuEntity = new MenuEntity();
                menuEntity.setMenuIconURL(recentMiniEntity.getAppImg());
                menuEntity.setMenuURL(recentMiniEntity.getAppletUrl());
                menuEntity.setMenuTitle(recentMiniEntity.getAppName());
                IntentManager.generateIntentAndGo(CollectionFragment.this.mActivity, menuEntity, "get");
                CollectionFragment.this.clickLog(recentMiniEntity.getAppletUrl(), recentMiniEntity.getAppName());
                RecentBoxManager.getInstance().put(recentMiniEntity);
            }
        });
        this.mAdapter.setSwipeClickListener(new CollectionMoreAdapter.OnSwipeClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.9
            @Override // com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.OnSwipeClickListener
            public void onLeftClick(final RecentMiniEntity recentMiniEntity, final int i) {
                if (CollectionFragment.this.isRecnet) {
                    CollectionFragment.this.pdShow();
                    CollectionFragment.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.ADD, recentMiniEntity, new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.9.1
                        @Override // io.reactivex.functions.Consumer
                        public void accept(RecentStateEntity recentStateEntity) throws Exception {
                            try {
                                if (recentStateEntity.isSuccess()) {
                                    UIUtils.toastCenter("已收藏");
                                } else {
                                    UIUtils.toastCenter(recentStateEntity.getRespMsg());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                UIUtils.toastCenter("收藏失败,请稍后再试");
                            }
                            CollectionFragment.this.pdDissmiss();
                        }
                    });
                    CollectionFragment collectionFragment = CollectionFragment.this;
                    collectionFragment.log(recentMiniEntity.getAppName() + "添加收藏");
                    return;
                }
                CollectionFragment.this.pdShow();
                CollectionFragment.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.ADD, recentMiniEntity, new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.9.2
                    @Override // io.reactivex.functions.Consumer
                    public void accept(RecentStateEntity recentStateEntity) throws Exception {
                        try {
                            if (recentStateEntity.isSuccess()) {
                                CollectionFragment.this.mList.remove(i);
                                CollectionFragment.this.mList.add(0, recentMiniEntity);
                                CollectionFragment.this.mAdapter.notifyDataSetChanged();
                                UIUtils.toastCenter("已添加主页");
                            } else {
                                UIUtils.toastCenter(recentStateEntity.getRespMsg());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            UIUtils.toastCenter("收藏失败,请稍后再试");
                        }
                        CollectionFragment.this.pdDissmiss();
                    }
                });
                CollectionFragment collectionFragment2 = CollectionFragment.this;
                collectionFragment2.log(recentMiniEntity.getAppName() + "添加到首页");
            }

            @Override // com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionMoreAdapter.OnSwipeClickListener
            public void onRightClick(final RecentMiniEntity recentMiniEntity, final int i) {
                if (!CollectionFragment.this.isRecnet) {
                    CollectionFragment.this.pdShow();
                    CollectionFragment.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.CANCEL, recentMiniEntity.getAppId(), recentMiniEntity.getProductId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.9.3
                        @Override // io.reactivex.functions.Consumer
                        public void accept(RecentStateEntity recentStateEntity) throws Exception {
                            try {
                                if (recentStateEntity.isSuccess()) {
                                    CollectionFragment.this.mList.remove(i);
                                    CollectionFragment.this.mAdapter.notifyDataSetChanged();
                                    if (CollectionFragment.this.mList.size() == 0) {
                                        CollectionFragment.this.showEmptyLayout();
                                    } else {
                                        CollectionFragment.this.hideEmptyOrFailLayout();
                                    }
                                    CollectionEvent collectionEvent = new CollectionEvent(EventBusUtils.EVENT_COLLECTION_DELETE);
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add(recentMiniEntity);
                                    collectionEvent.setRecentMiniEntities(arrayList);
                                    EventBusUtils.post(collectionEvent);
                                    UIUtils.toastCenter("已取消收藏");
                                } else {
                                    UIUtils.toastCenter("取消收藏失败,请稍后再试");
                                }
                            } catch (Exception unused) {
                                UIUtils.toastCenter("取消收藏失败,请稍后再试");
                            }
                            CollectionFragment.this.pdDissmiss();
                        }
                    });
                    CollectionFragment.this.log(recentMiniEntity.getAppName() + "取消收藏");
                    return;
                }
                try {
                    RecentBoxManager.getInstance().reMove(recentMiniEntity);
                    int i2 = i - 1;
                    RecentMiniEntity recentMiniEntity2 = null;
                    if (i2 >= 0 && i2 < CollectionFragment.this.mList.size()) {
                        recentMiniEntity2 = CollectionFragment.this.mList.get(i2);
                    }
                    int i3 = i + 1;
                    if (i3 >= 0 && i3 < CollectionFragment.this.mList.size()) {
                        recentMiniEntity2 = CollectionFragment.this.mList.get(i3);
                    }
                    CollectionFragment.this.mList.remove(i);
                    if (recentMiniEntity2 != null && recentMiniEntity2.getType() == 0) {
                        CollectionFragment.this.mList.remove(i2);
                    }
                    CollectionFragment.this.mAdapter.notifyDataSetChanged();
                    if (CollectionFragment.this.mList.size() == 0) {
                        CollectionFragment.this.showEmptyLayout();
                    } else {
                        CollectionFragment.this.hideEmptyOrFailLayout();
                    }
                    UIUtils.toastCenter("已删除");
                    CollectionFragment.this.log("最近使用" + recentMiniEntity.getAppName() + "删除");
                } catch (Exception e) {
                    MsLogUtil.m7977e(CollectionFragment.TAG, "最近使用更多页面删除数据异常:" + e.getMessage());
                }
            }
        });
        this.mRv_Recycleview.setLayoutManager(new LinearLayoutManager(this.mActivity, 1, false));
        this.mRv_Recycleview.setAdapter(this.mAdapter);
        if (this.isRecnet) {
            this.mList.addAll(RecentBoxManager.getInstance().get("2", ""));
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void checkAllState() {
        boolean z = true;
        this.isItemChangeCheck = true;
        List<RecentMiniEntity> list = this.mList;
        if (list == null || list.size() == 0) {
            this.mCb_CheckAll.setChecked(false);
            return;
        }
        Iterator<RecentMiniEntity> it = this.mList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            RecentMiniEntity next = it.next();
            if (next.getType() != 0 && !next.isSelect()) {
                z = false;
                break;
            }
        }
        this.mCb_CheckAll.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCollectionData(boolean z, List<RecentMiniEntity> list) {
        if (z) {
            if (this.pageNum == 1) {
                this.mList.clear();
            }
            if (list.size() > 0) {
                this.pageNum++;
            }
            this.mList.addAll(list);
            this.mAdapter.notifyDataSetChanged();
            if (this.mList.size() == 0) {
                showEmptyLayout();
                return;
            } else {
                hideEmptyOrFailLayout();
                return;
            }
        }
        if (this.pageNum == 1) {
            this.mList.clear();
        }
        if (this.mList.size() == 0) {
            showFailLayout();
        } else {
            hideEmptyOrFailLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmptyLayout() {
        this.mTvEmpty.setVisibility(0);
        this.mLinFailLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideEmptyOrFailLayout() {
        this.mTvEmpty.setVisibility(8);
        this.mLinFailLayout.setVisibility(8);
    }

    private void showFailLayout() {
        this.mTvEmpty.setVisibility(8);
        this.mLinFailLayout.setVisibility(0);
    }

    private void initData() {
        CollectionParamsEntity collectionParamsEntity = this.paramsEntity;
        if (collectionParamsEntity != null) {
            this.categoryId = collectionParamsEntity.getCategoryId();
            this.fragmentType = this.paramsEntity.getType();
            this.fromType = this.paramsEntity.getFromType();
            this.isRecnet = this.fragmentType == 1;
            this.mTv_ShanChu.setText(this.isRecnet ? "删除" : "取消收藏");
        }
        initRecycleView();
    }

    public void getCollectionData() {
        this.searchContent = CollectionMoreActivity.mStrSearchContent;
        this.recentCustomManager.getCollect("2", String.valueOf(this.pageNum), this.categoryId, this.searchContent, new Consumer<CollectEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.10
            @Override // io.reactivex.functions.Consumer
            public void accept(CollectEntity collectEntity) throws Exception {
                CollectionFragment.this.pdDissmiss();
                if (collectEntity != null) {
                    CollectionFragment.this.notifyCollectionData(collectEntity.isSuccess(), collectEntity.getAppList());
                }
                CollectionFragment.this.mSrl_Refresh.finishLoadMore();
                CollectionFragment.this.mSrl_Refresh.finishRefresh();
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.11
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                CollectionFragment.this.pdDissmiss();
                CollectionFragment.this.notifyCollectionData(false, new ArrayList());
                CollectionFragment.this.mSrl_Refresh.finishLoadMore();
                CollectionFragment.this.mSrl_Refresh.finishRefresh();
            }
        });
    }

    public void setSearchContent(String str) {
        this.searchContent = str;
        this.isCurrentSearch = true;
        this.pageNum = 1;
        this.isFirstLoad = true;
        loadData();
    }

    public void setBianJiState() {
        this.mCb_CheckAll.setChecked(false);
        if (this.mList != null) {
            this.mAdapter.setBianJi(true);
            this.mAdapter.notifyDataSetChanged();
        }
        this.mSrl_Refresh.setEnabled(false);
        this.mSrl_Refresh.setEnableLoadMore(false);
        showBottomLayout(0);
    }

    public void showBottomLayout(int i) {
        this.mLin_Bottom.setVisibility(i);
    }

    public void cancelListState() {
        EventBusUtils.post(new ChangeCollectionGuanLiStateEvent(EventBusUtils.EVENT_COLLECTION_CHANGE_STATE));
        if (this.mAdapter == null) {
            return;
        }
        if (this.mList != null) {
            for (int i = 0; i < this.mList.size(); i++) {
                this.mList.get(i).setSelect(false);
                this.mList.get(i).setBianJi(false);
            }
            this.mAdapter.setBianJi(false);
            this.mAdapter.notifyDataSetChanged();
        }
        this.mSrl_Refresh.setEnabled(!this.isRecnet);
        this.mSrl_Refresh.setEnableRefresh(!this.isRecnet);
        this.mSrl_Refresh.setEnableLoadMore(!this.isRecnet);
        showBottomLayout(8);
    }

    public void checkAllState(boolean z) {
        if (this.mList != null) {
            for (int i = 0; i < this.mList.size(); i++) {
                this.mList.get(i).setSelect(z);
            }
            this.mAdapter.notifyDataSetChanged();
        }
    }

    public void batchDelete() {
        final ArrayList arrayList = new ArrayList();
        for (RecentMiniEntity recentMiniEntity : this.mList) {
            if (recentMiniEntity.getType() != 0 && recentMiniEntity.isSelect()) {
                arrayList.add(recentMiniEntity);
            }
        }
        if (arrayList.size() == 0) {
            UIUtils.toastCenter("请选择要删除的数据");
        } else if (this.isRecnet) {
            CustomDialogManager.show4(this.mActivity, "", "删除后数据不可恢复,请确认是否删除最近使用记录", 17, true, "删除", "取消", false, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.12
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    RecentBoxManager.getInstance().batchRemoveBox(arrayList);
                    CollectionFragment.this.mList.clear();
                    CollectionFragment.this.mList.addAll(RecentBoxManager.getInstance().get("2", CollectionFragment.this.searchContent));
                    CollectionFragment.this.mAdapter.notifyDataSetChanged();
                    if (CollectionFragment.this.mList.size() == 0) {
                        CollectionFragment.this.showEmptyLayout();
                    } else {
                        CollectionFragment.this.hideEmptyOrFailLayout();
                    }
                    CollectionFragment.this.cancelListState();
                    CollectionFragment.this.log("最近使用批量删除");
                }
            });
        } else {
            CustomDialogManager.show4(this.mActivity, "", "请确认是否对选中功能批量取消收藏", 17, true, "取消收藏", "取消", false, true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.13
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    CollectionFragment.this.pdShow();
                    CollectionFragment.this.recentCustomManager.batchCancelCollect(arrayList).subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.13.1
                        @Override // io.reactivex.functions.Consumer
                        public void accept(Boolean bool) throws Exception {
                            if (bool.booleanValue()) {
                                UIUtils.toastCenter("已取消收藏");
                                CollectionFragment.this.mList.removeAll(arrayList);
                                CollectionEvent collectionEvent = new CollectionEvent(EventBusUtils.EVENT_COLLECTION_DELETE);
                                collectionEvent.setRecentMiniEntities(arrayList);
                                EventBusUtils.post(collectionEvent);
                                CollectionFragment.this.checkAllState();
                                CollectionFragment.this.mAdapter.notifyDataSetChanged();
                                CollectionFragment.this.cancelListState();
                                CollectionFragment.this.pdShow();
                                CollectionFragment.this.getCollectionData();
                                if (CollectionFragment.this.mList.size() == 0) {
                                    CollectionFragment.this.showEmptyLayout();
                                } else {
                                    CollectionFragment.this.hideEmptyOrFailLayout();
                                }
                            } else {
                                UIUtils.toastCenter("取消收藏失败,请稍后再试");
                            }
                            CollectionFragment.this.pdDissmiss();
                        }
                    }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment.13.2
                        @Override // io.reactivex.functions.Consumer
                        public void accept(Throwable th) throws Exception {
                            UIUtils.toastCenter("取消收藏失败,请稍后再试");
                            CollectionFragment.this.pdDissmiss();
                        }
                    });
                    CollectionFragment.this.log("我的收藏批量删除");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            UIUtils.dismissDialog(this.mActivity, this.f18603pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            UIUtils.showDialog(this.mActivity, this.f18603pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteItem(CollectionEvent collectionEvent) {
        List<RecentMiniEntity> recentMiniEntities;
        try {
            if (this.mList != null && this.mList.size() != 0 && this.mAdapter != null && (recentMiniEntities = collectionEvent.getRecentMiniEntities()) != null && recentMiniEntities.size() != 0) {
                for (RecentMiniEntity recentMiniEntity : recentMiniEntities) {
                    if (recentMiniEntity != null) {
                        Iterator<RecentMiniEntity> it = this.mList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            RecentMiniEntity next = it.next();
                            if (next != null && TextUtils.equals(recentMiniEntity.getAppId(), next.getAppId()) && TextUtils.equals(recentMiniEntity.getProductId(), next.getProductId())) {
                                it.remove();
                                break;
                            }
                        }
                    }
                }
                this.mAdapter.notifyDataSetChanged();
                if (this.mList.size() == 0) {
                    showEmptyLayout();
                } else {
                    hideEmptyOrFailLayout();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "通过event删除数据异常" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvXiala(this.isRecnet ? "最近使用页面" : "我的收藏页面", str, "下拉进入小程序");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickLog(String str, String str2) {
        if (!TextUtils.isEmpty(this.fromType)) {
            MsLogUtil.m7979d("JIA_REN_LOG", String.format("transId = S2ndpage1240 \n url = %s \n name = %s \n remark3 = 最近使用 \n 最近使用 = 最近使用 \n actCode = 我的页面", str, str2));
            PvCurrencyLogUtils.pvLogDJ("", "S2ndpage1240", str + "", "最近使用", str2 + "", "我的页面", "最近使用", "", "");
            return;
        }
        MsLogUtil.m7979d("JIA_REN_LOG", "不落日志");
    }
}
