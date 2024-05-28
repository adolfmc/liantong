package com.sinovatech.unicom.separatemodule.audience.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.adpter.AudienceAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener;
import com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MultiViewLiveFragment extends BaseFragment {
    public static final String INDEX_FOR_LIVE = "indexForLive";
    public static final String PAGE_NUM_PLAY_BACK = "pageNumByPlayBack";
    public static final int REQUEST_CODE = 3001;
    public static final int RESULT_CODE = 2001;
    private static final String TAG = "MultiViewLiveFragment";
    public NBSTraceUnit _nbs_trace;
    private AudienceMainActivity activityContext;
    private Disposable autoJion;
    private BDCloudVideoView bdCloudVideoView;
    private View fragmentCacheView;
    private boolean isLoadMore;
    private boolean isLoadPBing;
    private boolean isXiabo;
    private ImageView ivPlayIcon;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private LinearLayout llEmpty;
    private LinearLayout llPayInfo;
    private LinearLayout llPayTipe;
    private LinearLayout llTrySeeDialog;
    private AudienceAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private Disposable payInfoCountDown;
    private View roomInfo;
    private int timePayInfo;
    private TextView tvFreeTimeTips;
    private TextView tvNoOrder;
    private TextView tvOrder;
    private TextView tvPayTips;
    private String userId;
    private String pageNum = "0";
    private String pageNumPlayBack = "0";
    private int mCurrentItem = 0;
    private List<ListDataEntity> list = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRoomInfo$17(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRoomInfo$18(Boolean bool) throws Exception {
    }

    public void dismissLoading() {
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    public void showLoading() {
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (AudienceMainActivity) context;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131492973, viewGroup, false);
        GlideApp.with(this).load((Integer) 2131232734).into((ImageView) inflate.findViewById(2131297427));
        ((TextView) inflate.findViewById(2131298933)).setText("多视角内容还未配置，请耐心等待下。");
        this.llEmpty = (LinearLayout) inflate.findViewById(2131297711);
        this.llEmpty.setVisibility(8);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(2131296463);
        this.mLayoutManager = new ViewPagerLayoutManager(this.activityContext, 1);
        initListener();
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new AudienceAdapter(this.list, this.activityContext);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.fragmentCacheView = inflate;
        this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        UIUtils.logD("多视角页签", "onCreateView");
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment");
        return view3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        UIUtils.logD("多视角页签", "loadData()");
        if (this.managerAudienceLoadData == null) {
            if (this.activityContext == null) {
                this.activityContext = (AudienceMainActivity) getActivity();
            }
            this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        }
        this.managerAudienceLoadData.getAngleMoreLiveList(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$vbxa2g3I9OgwvY3ozdJsF-wUXgs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$loadData$1(MultiViewLiveFragment.this, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$QO7Hy5ta-IS09y1B7aHqyxUOzSc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$loadData$2(MultiViewLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadData$1(final MultiViewLiveFragment multiViewLiveFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        if ("0000".equals(audienceDataEntity.getStatusCode())) {
            multiViewLiveFragment.llEmpty.setVisibility(8);
            if (TextUtils.isEmpty(multiViewLiveFragment.userId)) {
                multiViewLiveFragment.pageNum = audienceDataEntity.getNextPageNum();
                if (multiViewLiveFragment.isLoadMore) {
                    multiViewLiveFragment.mAdapter.loadMore(audienceDataEntity.getList());
                    multiViewLiveFragment.isLoadMore = false;
                    return;
                }
                multiViewLiveFragment.list = audienceDataEntity.getList();
                for (ListDataEntity listDataEntity : multiViewLiveFragment.list) {
                    UIUtils.logD("ListDataEntity", listDataEntity.getJobNumber());
                }
            } else {
                ListDataEntity listDataEntity2 = null;
                for (ListDataEntity listDataEntity3 : audienceDataEntity.getList()) {
                    UIUtils.logD("ListDataEntity", listDataEntity3.getJobNumber());
                    if (listDataEntity3.getJobNumber().trim().equals(multiViewLiveFragment.userId.trim())) {
                        listDataEntity2 = listDataEntity3;
                    }
                }
                if (multiViewLiveFragment.isLoadMore) {
                    if (listDataEntity2 != null) {
                        audienceDataEntity.getList().remove(listDataEntity2);
                    }
                    multiViewLiveFragment.mAdapter.loadMore(audienceDataEntity.getList());
                    multiViewLiveFragment.isLoadMore = false;
                    multiViewLiveFragment.pageNum = audienceDataEntity.getNextPageNum();
                    return;
                }
                multiViewLiveFragment.list = audienceDataEntity.getList();
                if (listDataEntity2 == null) {
                    listDataEntity2 = new ListDataEntity();
                    listDataEntity2.setJobNumber(multiViewLiveFragment.userId);
                } else if (multiViewLiveFragment.list.size() > 0) {
                    multiViewLiveFragment.list.remove(listDataEntity2);
                }
                if (multiViewLiveFragment.pageNum.equals("0")) {
                    multiViewLiveFragment.list.add(0, listDataEntity2);
                }
                multiViewLiveFragment.pageNum = audienceDataEntity.getNextPageNum();
            }
            multiViewLiveFragment.mAdapter.updateList(audienceDataEntity.getList());
            if (multiViewLiveFragment.mAdapter.getItemCount() > 0) {
                multiViewLiveFragment.mRecyclerView.scrollToPosition(multiViewLiveFragment.mCurrentItem);
                multiViewLiveFragment.mRecyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$G7b9TevesUOZ1ZJfwzaHIOFdwdk
                    @Override // java.lang.Runnable
                    public final void run() {
                        r0.startPlay(MultiViewLiveFragment.this.mCurrentItem);
                    }
                });
            }
            if (!audienceDataEntity.getNextPageNum().equals("-1") || TextUtils.isEmpty(multiViewLiveFragment.pageNumPlayBack) || "-1".equals(multiViewLiveFragment.pageNumPlayBack) || multiViewLiveFragment.isLoadPBing) {
                return;
            }
            multiViewLiveFragment.loadPlayBackData(multiViewLiveFragment.pageNumPlayBack);
        } else if (audienceDataEntity.getNextPageNum().equals("-1") && !TextUtils.isEmpty(multiViewLiveFragment.pageNumPlayBack) && !"-1".equals(multiViewLiveFragment.pageNumPlayBack) && !multiViewLiveFragment.isLoadPBing) {
            multiViewLiveFragment.loadPlayBackData(multiViewLiveFragment.pageNumPlayBack);
        } else {
            UIUtils.toast("加载失败，请稍后再试");
            multiViewLiveFragment.llEmpty.setVisibility(0);
        }
    }

    public static /* synthetic */ void lambda$loadData$2(MultiViewLiveFragment multiViewLiveFragment, Throwable th) throws Exception {
        if (!TextUtils.isEmpty(multiViewLiveFragment.pageNumPlayBack) && !"-1".equals(multiViewLiveFragment.pageNumPlayBack) && !multiViewLiveFragment.isLoadPBing) {
            multiViewLiveFragment.loadPlayBackData(multiViewLiveFragment.pageNumPlayBack);
            return;
        }
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
        multiViewLiveFragment.llEmpty.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadPlayBackData(String str) {
        UIUtils.logD("多视角页签", "loadPlayBackData()");
        this.isLoadPBing = true;
        this.managerAudienceLoadData.getAngleMorePlayBackList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$byOWAg5d1YGWOv-s62dDY4YiBa4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$loadPlayBackData$4(MultiViewLiveFragment.this, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$thvh5Ys4mGiL5RtbmHBcPtMCe_M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$loadPlayBackData$5(MultiViewLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadPlayBackData$4(final MultiViewLiveFragment multiViewLiveFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        multiViewLiveFragment.pageNumPlayBack = audienceDataEntity.getNextPageNum();
        multiViewLiveFragment.isLoadPBing = false;
        multiViewLiveFragment.isLoadMore = false;
        multiViewLiveFragment.mAdapter.loadMore(audienceDataEntity.getList());
        if (multiViewLiveFragment.mAdapter.getItemCount() == audienceDataEntity.getList().size()) {
            multiViewLiveFragment.mRecyclerView.scrollToPosition(multiViewLiveFragment.mCurrentItem);
            multiViewLiveFragment.mRecyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$gSo59_LrubOZGjRcHnlYCiNBDZo
                @Override // java.lang.Runnable
                public final void run() {
                    r0.startPlay(MultiViewLiveFragment.this.mCurrentItem);
                }
            });
        }
        UIUtils.logD("多视角页签", "loadPlayBackData()加载完毕");
    }

    public static /* synthetic */ void lambda$loadPlayBackData$5(MultiViewLiveFragment multiViewLiveFragment, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("多视角测试", "多视角回放列表报错：" + th.getMessage());
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
        multiViewLiveFragment.llEmpty.setVisibility(0);
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    public long getFragmentId() {
        char[] charArray = TAG.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : charArray) {
            stringBuffer.append((int) c);
        }
        long parseLong = Long.parseLong(stringBuffer.toString());
        MsLogUtil.m7979d(TAG, "转换后：" + parseLong);
        return parseLong;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    void lazyLoad() {
        playLive();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    public void clearData() {
        AudienceAdapter audienceAdapter = this.mAdapter;
        if (audienceAdapter != null) {
            audienceAdapter.updateList(new ArrayList());
        }
    }

    public void playLive() {
        loadData();
    }

    public void playLive(String str) {
        uploadLog("");
        this.pageNum = str;
        this.pageNumPlayBack = "0";
        this.mCurrentItem = 0;
        playLive();
        UIUtils.logD("多视角页签", "playLive（" + str + ")");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Exception e;
        ArrayList arrayList;
        super.onActivityResult(i, i2, intent);
        if (i != 3001 || i2 != 2001 || intent == null) {
            if (i == 4001) {
                startPlay(this.mCurrentItem);
                return;
            }
            return;
        }
        this.mCurrentItem = intent.getIntExtra("indexForLive", this.mCurrentItem);
        String stringExtra = intent.getStringExtra(PAGE_NUM_PLAY_BACK);
        if (!TextUtils.isEmpty(stringExtra)) {
            this.pageNumPlayBack = stringExtra;
        }
        String stringExtra2 = intent.getStringExtra("liveList");
        if (!TextUtils.isEmpty(stringExtra2)) {
            try {
                JSONArray jSONArray = new JSONArray(stringExtra2);
                arrayList = new ArrayList();
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    try {
                        ListDataEntity listDataEntity = new ListDataEntity();
                        JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                        listDataEntity.setId(optJSONObject.optString("id"));
                        listDataEntity.setCoverImg(optJSONObject.optString("coverImg"));
                        listDataEntity.setDataType(optJSONObject.optString("dataType"));
                        listDataEntity.setJobNumber(optJSONObject.optString("jobNumber"));
                        listDataEntity.setLivePvUrl(optJSONObject.optString("livePvUrl"));
                        arrayList.add(listDataEntity);
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (arrayList != null) {
                            this.list.clear();
                            this.list.addAll(arrayList);
                            this.mAdapter.updateList(this.list);
                        }
                        this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                        this.mRecyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$t1hvcmdRQjn4gWI0lnAs_OnUpWo
                            @Override // java.lang.Runnable
                            public final void run() {
                                r0.startPlay(MultiViewLiveFragment.this.mCurrentItem);
                            }
                        });
                    }
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.list.clear();
                this.list.addAll(arrayList);
                this.mAdapter.updateList(this.list);
            }
        } else {
            this.mCurrentItem = 0;
        }
        this.mRecyclerView.scrollToPosition(this.mCurrentItem);
        this.mRecyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$t1hvcmdRQjn4gWI0lnAs_OnUpWo
            @Override // java.lang.Runnable
            public final void run() {
                r0.startPlay(MultiViewLiveFragment.this.mCurrentItem);
            }
        });
    }

    private boolean isCurrentRoom(String str) {
        String jobNumber = this.list.get(this.mCurrentItem).getJobNumber();
        UIUtils.logD("fufeizhibo", "roomId=" + str + "|currentRoomId=" + jobNumber);
        return str.equals(jobNumber);
    }

    private void getSharpnessInfo(int i) {
        final String jobNumber = this.list.get(i).getJobNumber();
        this.managerAudienceLoadData.getSharpnessInfo(jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$mZlljxULgdH5bomyyXtMGY99tAU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.this.handleSharpnessInfo((SharpnessEntity) obj, jobNumber);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSharpnessInfo(SharpnessEntity sharpnessEntity, String str) {
        if ("0000".equals(sharpnessEntity.getStatusCode()) && isCurrentRoom(str)) {
            handlePayInfo(sharpnessEntity, str);
            List<SharpnessEntity.LiveUrlBean> list = this.liveUrlList;
            if (list == null) {
                this.liveUrlList = sharpnessEntity.getData();
            } else {
                list.clear();
                this.liveUrlList.addAll(sharpnessEntity.getData());
            }
            if (sharpnessEntity.getData() == null || sharpnessEntity.getData().size() <= 0) {
                return;
            }
            if (sharpnessEntity.getData().size() > 2) {
                setPlayerUrl(sharpnessEntity.getData().get(1));
            } else {
                setPlayerUrl(sharpnessEntity.getData().get(0));
            }
        }
    }

    private void handlePayInfo(SharpnessEntity sharpnessEntity, final String str) {
        if (!TextUtils.isEmpty(sharpnessEntity.getPromptText())) {
            UIUtils.toastCenter(sharpnessEntity.getPromptText());
        }
        if (!"Y".equals(sharpnessEntity.getPaidLive()) || "Y".equals(sharpnessEntity.getPayingUser())) {
            return;
        }
        initPayInfo(sharpnessEntity.getPaidLinks(), sharpnessEntity.getPaidAd());
        clearPayInfo();
        try {
            this.timePayInfo = Integer.valueOf(sharpnessEntity.getFreeTime()).intValue();
        } catch (Exception unused) {
        }
        if (this.timePayInfo < 10) {
            showPayInfo();
            return;
        }
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(0);
        TextView textView = this.tvFreeTimeTips;
        textView.setText("可试看" + this.timePayInfo + "秒，订购权益包后可观看全部内容");
        this.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$FNY80lMGV5m3PYLjVnSeTHSg3u4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$handlePayInfo$8(MultiViewLiveFragment.this, str, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$handlePayInfo$8(MultiViewLiveFragment multiViewLiveFragment, String str, Long l) throws Exception {
        multiViewLiveFragment.timePayInfo--;
        if (multiViewLiveFragment.timePayInfo % 10 == 0) {
            multiViewLiveFragment.consumeFreeTime(str);
        }
        if (multiViewLiveFragment.timePayInfo == 0) {
            multiViewLiveFragment.showPayInfo();
            return;
        }
        multiViewLiveFragment.tvFreeTimeTips.setText("可试看" + multiViewLiveFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
    }

    private void consumeFreeTime(String str) {
        UIUtils.logD("LiveFragment", "扣减时间==" + str);
        this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$3Wd-OJK8xEjqifYobbD1G7l3DXc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("LiveFragment", ((String) obj) + "===========");
            }
        });
    }

    private void clearPayInfo() {
        Disposable disposable = this.payInfoCountDown;
        if (disposable != null) {
            disposable.dispose();
            this.payInfoCountDown = null;
        }
        this.timePayInfo = 0;
        LinearLayout linearLayout = this.llPayInfo;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPayInfo() {
        clearPayInfo();
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(0);
        AudienceMainActivity.isNeedPay = true;
        this.bdCloudVideoView.stopPlayback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissPayInfo() {
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        this.ivPlayIcon.setVisibility(0);
    }

    private void initPayInfo(final String str, String str2) {
        this.llPayInfo = (LinearLayout) this.roomInfo.findViewById(2131297753);
        this.llPayTipe = (LinearLayout) this.roomInfo.findViewById(2131297754);
        this.tvFreeTimeTips = (TextView) this.roomInfo.findViewById(2131298945);
        this.ivPlayIcon = (ImageView) this.roomInfo.findViewById(2131297450);
        this.llTrySeeDialog = (LinearLayout) this.roomInfo.findViewById(2131297789);
        this.tvPayTips = (TextView) this.roomInfo.findViewById(2131299043);
        this.tvPayTips.setText(str2);
        this.tvNoOrder = (TextView) this.roomInfo.findViewById(2131299028);
        this.tvOrder = (TextView) this.roomInfo.findViewById(2131299036);
        this.llPayInfo.setVisibility(8);
        this.llPayTipe.setVisibility(8);
        this.ivPlayIcon.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$7zHAHY9xxH7LOxMwqB5D_ppBovA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiViewLiveFragment.this.showPayInfo();
            }
        });
        this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$aWfH1TD4IVwlDYUKjJ82P-S-vXQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiViewLiveFragment.this.dismissPayInfo();
            }
        });
        this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$avzLY3YfwC9p0Qcic5Zit7QwvQ4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultiViewLiveFragment.lambda$initPayInfo$12(MultiViewLiveFragment.this, str, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initPayInfo$12(MultiViewLiveFragment multiViewLiveFragment, String str, View view) {
        Intent intent = new Intent(multiViewLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        multiViewLiveFragment.startActivityForResult(intent, 4001);
    }

    private void setPlayerUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        UIUtils.logD("多视角页签", "setPlayerUrl");
        showBgImg();
        this.bdCloudVideoView = this.activityContext.playVideo(getSwitchUrl(liveUrlBean));
        setVideoSizeView();
    }

    private String getSwitchUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        if (liveUrlBean == null) {
            return "";
        }
        return !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)) ? liveUrlBean.getLiveFreePullUrlByFlv() : liveUrlBean.getLivePullUrlByFlv();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlay(int i) {
        UIUtils.logD("多视角页签", "startPlay(" + i + ")");
        leaveRoom();
        AudienceMainActivity.isNeedPay = false;
        List<ListDataEntity> list = this.list;
        if (list != null && list.size() > 0) {
            if (i >= this.list.size()) {
                i = this.list.size() - 1;
            }
            ListDataEntity listDataEntity = this.list.get(i);
            String dataType = listDataEntity.getDataType();
            if (!"2".equals(dataType) && !"3".equals(dataType)) {
                getSharpnessInfo(i);
            } else {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
                if (findViewByPosition == null) {
                    return;
                }
                findViewByPosition.findViewById(2131296393).setVisibility(0);
                this.bdCloudVideoView = this.activityContext.playVideo(listDataEntity.getLivePvUrl());
                setVideoSizeView();
                if ("3".equals(dataType)) {
                    getPlayBackVideoInfo(i);
                }
            }
            getRoomInfo(listDataEntity.getJobNumber(), dataType);
            return;
        }
        UIUtils.toast("加载失败，请稍后再试");
        this.llEmpty.setVisibility(0);
    }

    public void setVideoSizeView() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView == null) {
            return;
        }
        bDCloudVideoView.setVideoScalingMode(2);
        View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
        if (findViewByPosition == null) {
            return;
        }
        FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
        if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
            this.bdCloudVideoView.setVideoScalingMode(1);
            frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
        }
        ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        frameLayout.addView(this.bdCloudVideoView);
        UIUtils.logD("多视角页签", "setVideoSizeView");
    }

    private void getRoomInfo(final String str, final String str2) {
        this.managerAudienceLoadData.loadZhuboData(str, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$4yeQwr-HBgDxxNlWF_NySP8SIoo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$getRoomInfo$28(MultiViewLiveFragment.this, str, str2, (ZhuboDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$jn4Vwq-krtZg99HgGuAijLTZm9A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$getRoomInfo$29(MultiViewLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$28(final MultiViewLiveFragment multiViewLiveFragment, String str, final String str2, ZhuboDataEntity zhuboDataEntity) throws Exception {
        if ("0000".equals(zhuboDataEntity.getStatusCode())) {
            if (!TextUtils.isEmpty(multiViewLiveFragment.userId) && str.equals(multiViewLiveFragment.userId) && "N".equals(zhuboDataEntity.getAnchorInfoBean().getLiveStatus())) {
                BDCloudVideoView bDCloudVideoView = multiViewLiveFragment.bdCloudVideoView;
                if (bDCloudVideoView != null) {
                    bDCloudVideoView.stopPlayback();
                    multiViewLiveFragment.bdCloudVideoView.reSetRender();
                }
                Intent intent = new Intent(multiViewLiveFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(zhuboDataEntity.getAnchorInfoBean().getLiveHistoryUrl(multiViewLiveFragment.userId));
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                multiViewLiveFragment.activityContext.startActivityForResult(intent, 4000);
                multiViewLiveFragment.activityContext.finish();
                return;
            }
            final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
            if ("1".equals(str2) && "N".equals(anchorInfoBean.getLiveStatus())) {
                multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).setLiving("N");
                multiViewLiveFragment.isXiabo = true;
                BDCloudVideoView bDCloudVideoView2 = multiViewLiveFragment.bdCloudVideoView;
                if (bDCloudVideoView2 != null) {
                    bDCloudVideoView2.stopPlayback();
                    multiViewLiveFragment.bdCloudVideoView.reSetRender();
                }
                multiViewLiveFragment.mAdapter.notifyItemChanged(multiViewLiveFragment.mCurrentItem);
                return;
            }
            multiViewLiveFragment.roomInfo = LayoutInflater.from(multiViewLiveFragment.activityContext).inflate(2131493228, (ViewGroup) null);
            GlideApp.with((FragmentActivity) multiViewLiveFragment.activityContext).asGif().load((Integer) 2131232744).into((ImageView) multiViewLiveFragment.roomInfo.findViewById(2131297281));
            multiViewLiveFragment.roomInfo.findViewById(2131297726).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$_akVuXXII8gck86_DHrWDg4Tvsg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MultiViewLiveFragment.this.jumpLiveInfo();
                }
            });
            GlideApp.with((FragmentActivity) multiViewLiveFragment.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into((ImageView) multiViewLiveFragment.roomInfo.findViewById(2131297332));
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131298863)).setText(anchorInfoBean.getUserName());
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131299144)).setText(anchorInfoBean.getLiveTitle());
            multiViewLiveFragment.roomInfo.findViewById(2131299144).setVisibility(0);
            multiViewLiveFragment.roomInfo.findViewById(2131297685).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$S8_xHuyIO6FsTjSXvJ4AHPs7OIY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MultiViewLiveFragment.lambda$getRoomInfo$14(MultiViewLiveFragment.this, anchorInfoBean, str2, view);
                }
            });
            multiViewLiveFragment.roomInfo.findViewById(2131297390).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$1KxMcP6FE4rqJF-oVy8vNWrn-QQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.managerAudienceLoadData.guanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$8WS3B1jADz7TsETTKhCR17orSiQ
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            MultiViewLiveFragment.lambda$getRoomInfo$15(MultiViewLiveFragment.this, (String) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
            });
            multiViewLiveFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str2) ? 8 : 0);
            View findViewByPosition = multiViewLiveFragment.mLayoutManager.findViewByPosition(multiViewLiveFragment.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(multiViewLiveFragment.roomInfo);
            multiViewLiveFragment.managerAudienceLoadData.addRenqi(multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$p1WCmiaKYLgryYkI4suE0A3Mg3k
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$17((String) obj);
                }
            });
            multiViewLiveFragment.managerAudienceLoadData.loadShopData(multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).getJobNumber(), "Y").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$MnKL-XWpeQGO3uZ1vgEuMvzwi0U
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$20(MultiViewLiveFragment.this, anchorInfoBean, (ShopEntity) obj);
                }
            });
            multiViewLiveFragment.managerAudienceLoadData.loadShopList(multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$zbD_XnokVRfAeGnXaJDagePHKws
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$21(MultiViewLiveFragment.this, (List) obj);
                }
            });
            multiViewLiveFragment.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$-6MQIqQNHDVii5EkiKRUCVp9LVs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$22(MultiViewLiveFragment.this, (Boolean) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$Z10cnkmLjEkCGqhCGHDgiXDluPc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$23(MultiViewLiveFragment.this, (Throwable) obj);
                }
            });
            if ("2".equals(str2)) {
                multiViewLiveFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                multiViewLiveFragment.roomInfo.findViewById(2131299141).setVisibility(8);
                multiViewLiveFragment.roomInfo.findViewById(2131297802).setVisibility(0);
                multiViewLiveFragment.managerAudienceLoadData.reserveLivePv("2", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$hd5hVTuDfVgV4Ye5fr45UepneYg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        r0.managerAudienceLoadData.getLivePvInfo(r1.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$-DzzZw5ToVj9qs6l8sU1rKgjqlA
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj2) {
                                MultiViewLiveFragment.lambda$getRoomInfo$26(MultiViewLiveFragment.this, r2, r3, (LivePvInfoEntity) obj2);
                            }
                        });
                    }
                });
            } else if (!"3".equals(str2)) {
                ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131299029)).setText("点击进入直播间");
                TextView textView = (TextView) multiViewLiveFragment.roomInfo.findViewById(2131299146);
                textView.setText("正在直播-多视角");
                textView.setVisibility(0);
                multiViewLiveFragment.roomInfo.findViewById(2131297802).setVisibility(8);
                multiViewLiveFragment.roomInfo.findViewById(2131299141).setVisibility(8);
            } else {
                ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131299029)).setText("点击查看精彩回放");
                TextView textView2 = (TextView) multiViewLiveFragment.roomInfo.findViewById(2131299141);
                textView2.setText("精彩回放-多视角");
                textView2.setVisibility(0);
                multiViewLiveFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                multiViewLiveFragment.roomInfo.findViewById(2131297802).setVisibility(8);
            }
            multiViewLiveFragment.roomInfo.findViewById(2131298355).setVisibility(0);
            multiViewLiveFragment.roomInfo.setVisibility(0);
            return;
        }
        UIUtils.logD("test", "数据返回异常-->code=" + zhuboDataEntity.getStatusCode() + "|" + zhuboDataEntity.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(zhuboDataEntity.getMessage());
        }
        multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).setLiving("N");
        multiViewLiveFragment.mAdapter.updateList(multiViewLiveFragment.list);
    }

    public static /* synthetic */ void lambda$getRoomInfo$14(MultiViewLiveFragment multiViewLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, View view) {
        try {
            String userIndexUrl = anchorInfoBean.getUserIndexUrl();
            if (TextUtils.isEmpty(userIndexUrl)) {
                return;
            }
            Intent intent = new Intent(multiViewLiveFragment.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(multiViewLiveFragment.addChannel(userIndexUrl, "直播"));
            webParamsEntity.setNeedTitle(true);
            if (!"2".equals(str)) {
                webParamsEntity.setType("audienceMain");
            }
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            multiViewLiveFragment.startActivityForResult(intent, 4001);
        } catch (Exception e) {
            MsLogUtil.m7979d("多视角直播", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$15(MultiViewLiveFragment multiViewLiveFragment, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            multiViewLiveFragment.roomInfo.findViewById(2131297390).setVisibility(8);
            return;
        }
        UIUtils.toastCenter(str);
    }

    public static /* synthetic */ void lambda$getRoomInfo$20(final MultiViewLiveFragment multiViewLiveFragment, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ShopEntity shopEntity) throws Exception {
        ShopEntity.DataBean data;
        if (!"0000".equals(shopEntity.getStatusCode()) || (data = shopEntity.getData()) == null) {
            return;
        }
        try {
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131298862)).setText(FormatUtils.getShowString(data.getRoundPraise()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("Y".equalsIgnoreCase(data.getHaveGoods())) {
            multiViewLiveFragment.roomInfo.findViewById(2131296461).setVisibility(0);
            multiViewLiveFragment.roomInfo.findViewById(2131298338).setVisibility(0);
            final ShopEntity.DataBean.GoodsBean goods = data.getGoods();
            GlideApp.with((FragmentActivity) multiViewLiveFragment.activityContext).load(goods.getCoverImgUrl()).into((ImageView) multiViewLiveFragment.roomInfo.findViewById(2131296407));
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131296419)).setText(goods.getName());
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131296406)).setText(goods.getDesc());
            ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131296418)).setText(TextUtils.isEmpty(goods.getPrice()) ? goods.getOriginalPrice() : goods.getPrice());
            TextView textView = (TextView) multiViewLiveFragment.roomInfo.findViewById(2131296417);
            if (TextUtils.isEmpty(goods.getOriginalPrice())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.getPaint().setFlags(16);
            }
            multiViewLiveFragment.roomInfo.findViewById(2131296461).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$hL5MiHN-3XY7IFI_go2PrNb8E9I
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MultiViewLiveFragment.lambda$getRoomInfo$19(MultiViewLiveFragment.this, anchorInfoBean, goods, view);
                }
            });
            return;
        }
        multiViewLiveFragment.roomInfo.findViewById(2131296461).setVisibility(8);
        multiViewLiveFragment.roomInfo.findViewById(2131298338).setVisibility(8);
    }

    public static /* synthetic */ void lambda$getRoomInfo$19(MultiViewLiveFragment multiViewLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ShopEntity.DataBean.GoodsBean goodsBean, View view) {
        if (!TextUtils.isEmpty("0")) {
            multiViewLiveFragment.managerAudienceLoadData.goodsLog(anchorInfoBean.getUserId(), goodsBean.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$OPrI3z1N_jewfIzDWJASH3PRH6k
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$18((Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        Intent intent = new Intent(multiViewLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(goodsBean.getGoodsUrl());
        webParamsEntity.setType("audienceMain");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        multiViewLiveFragment.startActivityForResult(intent, 4001);
    }

    public static /* synthetic */ void lambda$getRoomInfo$21(MultiViewLiveFragment multiViewLiveFragment, List list) throws Exception {
        ((TextView) multiViewLiveFragment.roomInfo.findViewById(2131298965)).setText("" + list.size());
        multiViewLiveFragment.roomInfo.findViewById(2131297720).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$22(MultiViewLiveFragment multiViewLiveFragment, Boolean bool) throws Exception {
        multiViewLiveFragment.roomInfo.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
        multiViewLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$23(MultiViewLiveFragment multiViewLiveFragment, Throwable th) throws Exception {
        multiViewLiveFragment.roomInfo.findViewById(2131297390).setVisibility(0);
        multiViewLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$26(final MultiViewLiveFragment multiViewLiveFragment, LivePvInfoEntity livePvInfoEntity, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, LivePvInfoEntity livePvInfoEntity2) throws Exception {
        if ("0000".equals(livePvInfoEntity2.getStatusCode())) {
            LivePvInfoEntity.InfoEntity data = livePvInfoEntity2.getData();
            final TextView textView = (TextView) multiViewLiveFragment.roomInfo.findViewById(2131299154);
            final TextView textView2 = (TextView) multiViewLiveFragment.roomInfo.findViewById(2131299155);
            textView2.setText(data != null ? data.getPrevueTime() : "该主播未发布预告");
            String statusCode = livePvInfoEntity.getStatusCode();
            char c = 65535;
            switch (statusCode.hashCode()) {
                case 1567967:
                    if (statusCode.equals("3101")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1567968:
                    if (statusCode.equals("3102")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1567969:
                    if (statusCode.equals("3103")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1567970:
                    if (statusCode.equals("3104")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    textView.setText("已预约");
                    break;
                case 1:
                    textView.setText("预约");
                    break;
                case 2:
                case 3:
                    textView.setVisibility(8);
                    break;
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$S_1cNvSnZqqpvLwFE0oZd_RyaHI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MultiViewLiveFragment.lambda$getRoomInfo$25(MultiViewLiveFragment.this, textView, anchorInfoBean, textView2, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$25(final MultiViewLiveFragment multiViewLiveFragment, final TextView textView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final TextView textView2, View view) {
        if ("预约".equals(textView.getText())) {
            multiViewLiveFragment.managerAudienceLoadData.reserveLivePv("1", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$eVI1ly1mHplw18GAYEa-Yzst13w
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getRoomInfo$24(MultiViewLiveFragment.this, textView2, textView, (LivePvInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$24(MultiViewLiveFragment multiViewLiveFragment, TextView textView, TextView textView2, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            LivePvResultDialog livePvResultDialog = new LivePvResultDialog(multiViewLiveFragment.activityContext, textView.getText().toString().replace(" 开播", ""));
            textView2.setText("已预约");
            livePvResultDialog.show();
            return;
        }
        textView2.setText("预约");
        ToastUtil.showToast("出了一点儿小问题，请稍后再试");
    }

    public static /* synthetic */ void lambda$getRoomInfo$29(MultiViewLiveFragment multiViewLiveFragment, Throwable th) throws Exception {
        UIUtils.logD("test", "主播接口信息异常-->" + th.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(th.getMessage());
        }
        multiViewLiveFragment.list.get(multiViewLiveFragment.mCurrentItem).setLiving("N");
        multiViewLiveFragment.mAdapter.updateList(multiViewLiveFragment.list);
    }

    private String addChannel(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (str.contains("?")) {
            stringBuffer.append("&channel=");
            stringBuffer.append(str2);
        } else {
            stringBuffer.append("?channel=");
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public void dismissImg() {
        try {
            autoJionLiveInfo();
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            findViewByPosition.findViewById(2131296393).setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBgImg() {
        View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
        if (findViewByPosition != null) {
            findViewByPosition.findViewById(2131296393).setVisibility(0);
        }
    }

    private String getLiveList() {
        String str = System.currentTimeMillis() + "json";
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        Gson gson = new Gson();
        List<ListDataEntity> list = this.list;
        sharePreferenceUtil.putString(AudienceActivity.JSON_LIST_STR_FILE, str, !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
        return str;
    }

    private void leaveRoom() {
        ViewGroup viewGroup;
        View view = this.roomInfo;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeAllViews();
        }
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.stopPlayback();
        }
        showBgImg();
        stopJumpInfo();
        clearPayInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void filerList(int i) {
        this.isXiabo = false;
        Iterator<ListDataEntity> it = this.list.iterator();
        while (it.hasNext()) {
            ListDataEntity next = it.next();
            if ("N".equals(next.getLiving()) && "1".equals(next.getDataType())) {
                it.remove();
            }
        }
        this.mAdapter.notifyItemRemoved(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83461 implements OnViewPagerListener {
        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageRelease(boolean z, int i) {
        }

        C83461() {
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageSelected(int i, boolean z) {
            if (MultiViewLiveFragment.this.mCurrentItem != i) {
                if (!MultiViewLiveFragment.this.isXiabo) {
                    MultiViewLiveFragment.this.mCurrentItem = i;
                } else {
                    MultiViewLiveFragment multiViewLiveFragment = MultiViewLiveFragment.this;
                    multiViewLiveFragment.filerList(multiViewLiveFragment.mCurrentItem);
                }
                MultiViewLiveFragment.this.mRecyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$1$HBz3Lirhco0BZwZyU-8MWuP0QTM
                    @Override // java.lang.Runnable
                    public final void run() {
                        MultiViewLiveFragment.C83461.lambda$onPageSelected$0(MultiViewLiveFragment.C83461.this);
                    }
                }, 800L);
            }
            if (MultiViewLiveFragment.this.pageNum.equals("-1") || !z || MultiViewLiveFragment.this.isLoadMore) {
                if (MultiViewLiveFragment.this.pageNumPlayBack.equals("-1") || !z || MultiViewLiveFragment.this.isLoadMore || MultiViewLiveFragment.this.isLoadPBing) {
                    return;
                }
                MultiViewLiveFragment.this.isLoadMore = true;
                MultiViewLiveFragment multiViewLiveFragment2 = MultiViewLiveFragment.this;
                multiViewLiveFragment2.loadPlayBackData(multiViewLiveFragment2.pageNumPlayBack);
                return;
            }
            MultiViewLiveFragment.this.isLoadMore = true;
            MultiViewLiveFragment.this.loadData();
        }

        public static /* synthetic */ void lambda$onPageSelected$0(C83461 c83461) {
            MultiViewLiveFragment multiViewLiveFragment = MultiViewLiveFragment.this;
            multiViewLiveFragment.startPlay(multiViewLiveFragment.mCurrentItem);
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onLayoutComplete() {
            UIUtils.logD("test", "onLayoutComplete");
        }
    }

    private void initListener() {
        this.mLayoutManager.setOnViewPagerListener(new C83461());
    }

    public void autoJionLiveInfo() {
        stopJumpInfo();
        this.autoJion = Observable.timer(30000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$9Bgrsi7HbK8UJeAegBLmtCepsHY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                MultiViewLiveFragment.lambda$autoJionLiveInfo$30(MultiViewLiveFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$autoJionLiveInfo$30(MultiViewLiveFragment multiViewLiveFragment, Long l) throws Exception {
        multiViewLiveFragment.jumpLiveInfo();
        multiViewLiveFragment.autoJion.dispose();
        multiViewLiveFragment.autoJion = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpLiveInfo() {
        leaveRoom();
        Intent intent = new Intent(getActivity(), AudienceActivity.class);
        intent.putExtra("listStr", getLiveList());
        intent.putExtra("from", "fromMain");
        intent.putExtra("pageNum", this.pageNum);
        intent.putExtra("playBackPageNum", TextUtils.isEmpty(this.pageNumPlayBack) ? "0" : this.pageNumPlayBack);
        intent.putExtra("shareUserNumSc", "");
        intent.putExtra("index", this.mCurrentItem + "");
        startActivityForResult(intent, 3001);
    }

    public void stopJumpInfo() {
        Disposable disposable = this.autoJion;
        if (disposable != null) {
            disposable.dispose();
            this.autoJion = null;
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        stopJumpInfo();
        clearPayInfo();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        stopJumpInfo();
        clearPayInfo();
    }

    private void uploadLog(String str) {
        StatisticsUploadUtils.uploadRealTime2(this.activityContext, "szhibo0001", "直播LIVE", "03", "", "多视角", "", "", "");
    }

    private void getPlayBackVideoInfo(int i) {
        try {
            final ListDataEntity listDataEntity = this.list.get(i);
            UIUtils.logD("多视角测试", "多视角回放主视频播放");
            this.managerAudienceLoadData.getAngleMoreVideoInfo(listDataEntity.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$_wt_lnPU9wnYWHVB0H0QoLPyfR4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getPlayBackVideoInfo$31(MultiViewLiveFragment.this, listDataEntity, (SharpnessEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$MultiViewLiveFragment$a7BceCni8yCp58v3UyKimI9bHIk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MultiViewLiveFragment.lambda$getPlayBackVideoInfo$32((Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getPlayBackVideoInfo$31(MultiViewLiveFragment multiViewLiveFragment, ListDataEntity listDataEntity, SharpnessEntity sharpnessEntity) throws Exception {
        UIUtils.logD("多视角测试", "多视角回放请求成功");
        multiViewLiveFragment.handlePayInfo(sharpnessEntity, listDataEntity.getJobNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPlayBackVideoInfo$32(Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("多视角测试", "多视角回放请求错误：" + th.getMessage());
    }
}
