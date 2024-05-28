package com.sinovatech.unicom.separatemodule.recentmenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.ChangeCollectionGuanLiStateEvent;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.view.NoScrollViewPager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionTabFlowAdapter;
import com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionViewPagerAdapter;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionParamsEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment;
import com.sinovatech.unicom.separatemodule.recentmenu.manager.CollectionBoxManager;
import com.sinovatech.unicom.separatemodule.recentmenu.view.SlidingTabLayout;
import io.objectbox.Box;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionMoreActivity extends BaseActivity {
    public static final String TAG = "CustomNewMoreActivity";
    public static final int WODESHOUCANG = 2;
    public static final int ZUIJINSHIYONG = 1;
    public static String mStrSearchContent = "";
    public NBSTraceUnit _nbs_trace;
    private CollectionTabFlowAdapter collectionTabFlowAdapter;
    private List<CollectionFragment> mCollectionFragment;
    private EditText mEdSearch;
    private ImageView mImgTypeMore;
    private LinearLayout mImgTypeMoreLayout;
    private ImageView mImg_Back;
    private RelativeLayout mLinTabParentLayout;
    private LinearLayout mLinTypeLayout;
    private CollectionViewPagerAdapter mPagerAdapter;
    private RecyclerView mRv_TabMore;
    private SlidingTabLayout mTabLayout;
    private TextView mTv_GuanLi;
    private TextView mTv_Title;
    private View mViewMengCeng;
    private NoScrollViewPager mViewpager;

    /* renamed from: pd */
    private ProgressDialog f18596pd;
    private RecentCustomManager recentCustomManager;
    private List<CollectionTabEntity> tabEntityList;
    private Box<CollectionTabEntity> tabTypeEntityBox;
    private String[] mStringTitle = new String[1];
    private int activityType = 1;
    private String fromType = "";
    private boolean isRecnet = true;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 108);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    private void initView() {
        try {
            this.f18596pd = new CustomePorgressDialog(this);
            this.f18596pd.setCanceledOnTouchOutside(false);
            this.f18596pd.setCancelable(false);
            this.mTv_Title = (TextView) findViewById(2131299843);
            this.mTv_GuanLi = (TextView) findViewById(2131299830);
            this.mImg_Back = (ImageView) findViewById(2131299823);
            this.mViewpager = (NoScrollViewPager) findViewById(2131299842);
            this.mTabLayout = (SlidingTabLayout) findViewById(2131299837);
            this.mLinTabParentLayout = (RelativeLayout) findViewById(2131299838);
            this.mImgTypeMore = (ImageView) findViewById(2131299845);
            this.mImgTypeMoreLayout = (LinearLayout) findViewById(2131299846);
            this.mRv_TabMore = (RecyclerView) findViewById(2131299840);
            this.mLinTypeLayout = (LinearLayout) findViewById(2131299841);
            this.mViewMengCeng = findViewById(2131299839);
            this.mEdSearch = (EditText) findViewById(2131299835);
            this.mViewpager.setNoScroll(false);
            hideMoreTabLayout();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "我的收藏更多页面初始化view异常:" + e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View currentFocus;
        try {
            if (motionEvent.getAction() == 0 && (currentFocus = getCurrentFocus()) != null && (currentFocus instanceof EditText)) {
                Rect rect = new Rect();
                currentFocus.getGlobalVisibleRect(rect);
                if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    currentFocus.clearFocus();
                    KeyboardUtils.hideSoftInput(this);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "点击空白区域隐藏软键盘异常:" + e.getMessage());
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private void initListener() {
        this.mEdSearch.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                CollectionMoreActivity.mStrSearchContent = CollectionMoreActivity.this.mEdSearch.getText().toString();
                CollectionMoreActivity.this.hideMoreTabLayout();
                CollectionFragment collectionFragment = (CollectionFragment) CollectionMoreActivity.this.mCollectionFragment.get(CollectionMoreActivity.this.mViewpager.getCurrentItem());
                collectionFragment.setSearchContent(CollectionMoreActivity.mStrSearchContent);
                collectionFragment.cancelListState();
            }
        });
        this.mImg_Back.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CollectionMoreActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mImgTypeMoreLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                KeyboardUtils.hideSoftInput(CollectionMoreActivity.this);
                if (CollectionMoreActivity.this.mLinTypeLayout.getVisibility() == 8) {
                    CollectionMoreActivity.this.showMoreTabLayout();
                    CollectionMoreActivity.this.log("显示更多分类");
                } else {
                    CollectionMoreActivity.this.hideMoreTabLayout();
                    CollectionMoreActivity.this.log("隐藏更多分类");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mViewMengCeng.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CollectionMoreActivity.this.hideMoreTabLayout();
                CollectionMoreActivity.this.log("隐藏更多分类");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mEdSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.5
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 3) {
                    CollectionMoreActivity.mStrSearchContent = CollectionMoreActivity.this.mEdSearch.getText().toString();
                    CollectionMoreActivity.this.hideMoreTabLayout();
                    CollectionFragment collectionFragment = (CollectionFragment) CollectionMoreActivity.this.mCollectionFragment.get(CollectionMoreActivity.this.mViewpager.getCurrentItem());
                    collectionFragment.setSearchContent(CollectionMoreActivity.mStrSearchContent);
                    collectionFragment.cancelListState();
                    CollectionMoreActivity.this.mEdSearch.clearFocus();
                    KeyboardUtils.hideSoftInput(CollectionMoreActivity.this);
                    return true;
                }
                return false;
            }
        });
        this.mTv_GuanLi.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                String charSequence = CollectionMoreActivity.this.mTv_GuanLi.getText().toString();
                if (TextUtils.equals("管理", charSequence)) {
                    ((CollectionFragment) CollectionMoreActivity.this.mCollectionFragment.get(CollectionMoreActivity.this.mViewpager.getCurrentItem())).setBianJiState();
                    CollectionMoreActivity.this.mTv_GuanLi.setText("完成");
                    CollectionMoreActivity.this.log("点击编辑");
                } else if (TextUtils.equals("完成", charSequence)) {
                    ((CollectionFragment) CollectionMoreActivity.this.mCollectionFragment.get(CollectionMoreActivity.this.mViewpager.getCurrentItem())).cancelListState();
                    CollectionMoreActivity.this.mTv_GuanLi.setText("管理");
                    CollectionMoreActivity.this.log("点击完成");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.7
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                CollectionMoreActivity.this.updateTabState(i);
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMoreTabLayout() {
        this.mImgTypeMore.setRotation(180.0f);
        this.mRv_TabMore.setVisibility(0);
        this.mLinTypeLayout.setVisibility(0);
        this.mViewMengCeng.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideMoreTabLayout() {
        this.mImgTypeMore.setRotation(0.0f);
        this.mRv_TabMore.setVisibility(8);
        this.mLinTypeLayout.setVisibility(8);
        this.mViewMengCeng.setVisibility(8);
    }

    private void initData() {
        try {
            this.recentCustomManager = new RecentCustomManager(this);
            this.tabTypeEntityBox = App.getBoxStore().boxFor(CollectionTabEntity.class);
            this.activityType = getIntent().getIntExtra("CustomNewMoreActivity_type", 1);
            this.fromType = getIntent().getStringExtra("CustomNewMoreActivityfrom_type");
            this.isRecnet = this.activityType == 1;
            if (this.isRecnet) {
                this.mTv_Title.setText("最近使用");
                ArrayList arrayList = new ArrayList();
                CollectionTabEntity collectionTabEntity = new CollectionTabEntity();
                collectionTabEntity.setCategoryName("最近使用");
                arrayList.add(collectionTabEntity);
                intViewpager(arrayList, 1);
            } else {
                this.mTv_Title.setText("我的收藏");
                getTabTypeData();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化数据异常:" + e.getMessage());
        }
    }

    private void getTabTypeData() {
        pdShow();
        this.recentCustomManager.getTabTypeData().subscribe(new Consumer<List<CollectionTabEntity>>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.8
            @Override // io.reactivex.functions.Consumer
            public void accept(List<CollectionTabEntity> list) throws Exception {
                CollectionMoreActivity.this.pdDissmiss();
                if (list != null && list.size() > 0) {
                    CollectionBoxManager.saveTabListBox(CollectionMoreActivity.this.tabTypeEntityBox, list);
                } else {
                    list = CollectionMoreActivity.this.getTabDefaultData();
                }
                CollectionMoreActivity.this.intViewpager(list, 2);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                CollectionMoreActivity.this.pdDissmiss();
                List<CollectionTabEntity> tabListBox = CollectionBoxManager.getTabListBox(CollectionMoreActivity.this.tabTypeEntityBox);
                if (tabListBox == null || tabListBox.size() == 0) {
                    tabListBox = CollectionMoreActivity.this.getTabDefaultData();
                }
                CollectionMoreActivity.this.intViewpager(tabListBox, 2);
            }
        });
    }

    public void intViewpager(List<CollectionTabEntity> list, int i) {
        try {
            this.tabEntityList = list;
            this.mCollectionFragment = new ArrayList();
            if (this.tabEntityList != null && this.tabEntityList.size() > 0) {
                this.mStringTitle = new String[this.tabEntityList.size()];
                int i2 = 0;
                while (i2 < this.tabEntityList.size()) {
                    CollectionTabEntity collectionTabEntity = this.tabEntityList.get(i2);
                    if (collectionTabEntity != null) {
                        this.mStringTitle[i2] = collectionTabEntity.getCategoryName();
                        CollectionParamsEntity collectionParamsEntity = new CollectionParamsEntity();
                        collectionParamsEntity.setFromType(this.fromType);
                        collectionParamsEntity.setType(i);
                        collectionParamsEntity.setCategoryId(collectionTabEntity.getCategoryId());
                        collectionTabEntity.setSelect(i2 == 0);
                        this.mCollectionFragment.add(CollectionFragment.newInstance(collectionParamsEntity));
                    }
                    i2++;
                }
            }
            if (this.isRecnet) {
                this.mLinTabParentLayout.setVisibility(8);
            } else {
                this.mLinTabParentLayout.setVisibility(0);
            }
            this.mPagerAdapter = new CollectionViewPagerAdapter(getSupportFragmentManager(), this.mCollectionFragment, this.mStringTitle);
            this.mViewpager.setAdapter(this.mPagerAdapter);
            this.mViewpager.setOffscreenPageLimit(this.mStringTitle.length);
            this.mTabLayout.setViewPager(this.mViewpager, this.mStringTitle);
            this.mViewpager.setCurrentItem(0);
            this.collectionTabFlowAdapter = new CollectionTabFlowAdapter(this.tabEntityList);
            this.collectionTabFlowAdapter.setItemClickListener(new CollectionTabFlowAdapter.CollectionTabFlowItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CollectionMoreActivity.10
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.adapter.CollectionTabFlowAdapter.CollectionTabFlowItemClickListener
                public void onItemClick(int i3, CollectionTabEntity collectionTabEntity2) {
                    CollectionMoreActivity.this.updateTabState(i3);
                    CollectionMoreActivity.this.hideMoreTabLayout();
                    CollectionMoreActivity collectionMoreActivity = CollectionMoreActivity.this;
                    collectionMoreActivity.log("点击更多分类:" + collectionTabEntity2.getCategoryName());
                }
            });
            this.mRv_TabMore.setLayoutManager(new GridLayoutManager(this, 3));
            this.mRv_TabMore.setAdapter(this.collectionTabFlowAdapter);
            this.collectionTabFlowAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化viewpager异常:" + e.getMessage());
        }
    }

    public void updateTabState(int i) {
        try {
            if (this.tabEntityList != null && this.tabEntityList.size() > 0) {
                int i2 = 0;
                while (i2 < this.tabEntityList.size()) {
                    this.tabEntityList.get(i2).setSelect(i2 == i);
                    i2++;
                }
            }
            this.collectionTabFlowAdapter.notifyDataSetChanged();
            this.mViewpager.setCurrentItem(i, true);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "修改tab的选中状态异常:" + e.getMessage());
        }
    }

    public static void newStartActivity(Activity activity, int i, String str) {
        mStrSearchContent = "";
        Intent intent = new Intent(activity, CollectionMoreActivity.class);
        intent.putExtra("CustomNewMoreActivity_type", i);
        intent.putExtra("CustomNewMoreActivityfrom_type", str);
        activity.startActivity(intent);
    }

    public static void newStartActivity(Activity activity, int i) {
        newStartActivity(activity, i, "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeGuanLiState(ChangeCollectionGuanLiStateEvent changeCollectionGuanLiStateEvent) {
        try {
            if (EventBusUtils.EVENT_COLLECTION_CHANGE_STATE == changeCollectionGuanLiStateEvent.getCode()) {
                this.mTv_GuanLi.setText("管理");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            UIUtils.dismissDialog(this, this.f18596pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void pdShow() {
        try {
            UIUtils.showDialog(this, this.f18596pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<CollectionTabEntity> getTabDefaultData() {
        ArrayList arrayList = new ArrayList();
        CollectionTabEntity collectionTabEntity = new CollectionTabEntity();
        collectionTabEntity.setCategoryId("9e1c63da96d6104bcf0a9fa4e4bf7812");
        collectionTabEntity.setCategoryName("全部");
        collectionTabEntity.setSort("1");
        arrayList.add(collectionTabEntity);
        CollectionTabEntity collectionTabEntity2 = new CollectionTabEntity();
        collectionTabEntity2.setCategoryId("15fd2b7ad80855fb95c793f0f5bd269f");
        collectionTabEntity2.setCategoryName("服务");
        collectionTabEntity2.setSort("2");
        arrayList.add(collectionTabEntity2);
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvXiala(this.isRecnet ? "最近使用页面" : "我的收藏页面", str, "下拉进入小程序");
    }
}
