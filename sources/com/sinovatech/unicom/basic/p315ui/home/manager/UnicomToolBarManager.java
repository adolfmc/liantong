package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener;
import com.sinovatech.unicom.basic.boxcenter.AdvDataCenter;
import com.sinovatech.unicom.basic.myinterface.MyLocationInterface;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.activity.CitySelectActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeToastEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeChangeCityOrToastManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomToolBarManager;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerHomeCommon;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMiniProgram;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.p315ui.view.HomeSearchView;
import com.sinovatech.unicom.basic.server.CityCodingManager;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.server.WoPayScanManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.ScanCustomDialog;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout2;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.ActivityResultInfo;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.capture.CapuActivity2;
import com.sinovatech.unicom.separatemodule.capture.history.HistoryManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import com.sinovatech.unicom.separatemodule.search.SearchManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomToolBarManager {
    private static final String TAG = "UnicomToolBarManager";
    public static boolean istoTianyuan;
    private AppCompatActivity activityContext;
    private HomeToastEntity bottomWindowEntity;
    public OnChangeCityListener changeCityListener;
    public RelativeLayout cityLayout;
    private HomeChangeCityOrToastManager cityOrToastManager;
    private TextView cityText;
    private ConfigManager configManager;
    private OnHeadTabChangeListener headTabChangeListener;
    private HistoryManager historyManager;
    private ManagerHomeCommon homeCommon;
    private HomeSearchView homeSearchView;
    public boolean isCheckCityView;
    public boolean isCheckToastView;
    private ImageView kefuImage;
    private View mBottomView;
    private View mCacheView;
    private LinearLayout mLinToolBarLayout;
    private RadioButton mRbFuJin;
    private RadioButton mRbShowYe;
    private RadioGroup mRg;
    private int oldIndexHeadTabTimeout;
    private boolean oldMiniProgramOpenUrlFlag;
    private HomeTabEntity.DataDTO.IndexHeadTabDTO.TabCfgArrayDTO oldTabDTO2;
    private ImageView qiandaoImage;
    private ImageView scanImage;
    private ConsecutiveScrollerLayout2 scrollerLayout;
    private SearchManager searchManager;
    private FrameLayout tabFragmentLayoutWai;
    private WoPayScanManager woPayScanManager;
    private WebParamsEntity yingYeTingTabParams;
    private final int maxScrollDistance = 100;
    private int toolBackgroundColor = Color.parseColor("#E60027");
    private int alpha = 0;
    private boolean isInitHome = true;
    private String changeCityName = "";
    private boolean isInitFuJin = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager$OnChangeCityListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnChangeCityListener {
        void changeCity();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager$OnHeadTabChangeListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnHeadTabChangeListener {
        void onFuJin(WebParamsEntity webParamsEntity, boolean z);

        void onHome(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getSearchCache$0(Object obj) throws Exception {
    }

    public void setIndexSelectedTabOpenFlag() {
        if (this.mRg.getCheckedRadioButtonId() != 2131299175) {
            this.tabFragmentLayoutWai.setVisibility(0);
        }
    }

    public void setHeadTabChangeListener(OnHeadTabChangeListener onHeadTabChangeListener) {
        this.headTabChangeListener = onHeadTabChangeListener;
    }

    public void setChangeCityListener(OnChangeCityListener onChangeCityListener) {
        this.changeCityListener = onChangeCityListener;
    }

    public UnicomToolBarManager(Activity activity, LinearLayout linearLayout, View view) {
        this.activityContext = (AppCompatActivity) activity;
        this.mLinToolBarLayout = linearLayout;
        this.mCacheView = view;
        initView();
        initListener();
        getSearchCache();
    }

    public void initView() {
        this.historyManager = new HistoryManager(this.activityContext);
        this.woPayScanManager = new WoPayScanManager(this.activityContext);
        this.homeCommon = new ManagerHomeCommon(this.activityContext);
        this.searchManager = new SearchManager(this.activityContext);
        this.configManager = new ConfigManager();
        this.mRg = (RadioGroup) this.mLinToolBarLayout.findViewById(2131299176);
        this.mRbShowYe = (RadioButton) this.mLinToolBarLayout.findViewById(2131299174);
        this.mRbFuJin = (RadioButton) this.mLinToolBarLayout.findViewById(2131299175);
        this.homeSearchView = (HomeSearchView) this.mLinToolBarLayout.findViewById(2131297193);
        this.cityLayout = (RelativeLayout) this.mLinToolBarLayout.findViewById(2131296654);
        this.cityText = (TextView) this.mLinToolBarLayout.findViewById(2131296664);
        this.qiandaoImage = (ImageView) this.mLinToolBarLayout.findViewById(2131297190);
        this.kefuImage = (ImageView) this.mLinToolBarLayout.findViewById(2131297156);
        this.scanImage = (ImageView) this.mLinToolBarLayout.findViewById(2131297202);
        this.tabFragmentLayoutWai = (FrameLayout) this.mLinToolBarLayout.findViewById(2131298737);
        this.mBottomView = this.mCacheView.findViewById(2131299173);
        this.cityOrToastManager = new HomeChangeCityOrToastManager(this.activityContext, this.mBottomView);
    }

    public void getSearchCache() {
        Observable.zip(this.searchManager.loadScrollKeywordFromBox(), this.searchManager.loadScrollHuoDongFromBox(), new BiFunction<List<SearchEntity>, List<SearchHuoDongEntity>, List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.1
            @Override // io.reactivex.functions.BiFunction
            public List<SearchEntity> apply(List<SearchEntity> list, List<SearchHuoDongEntity> list2) throws Exception {
                try {
                    UnicomToolBarManager.this.homeSearchView.setData(list, list2);
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$A0HMfnZQ8YNsz0_435rS6lyUkxU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UnicomToolBarManager.lambda$getSearchCache$0(obj);
            }
        });
    }

    public void resetHeadTabState() {
        this.isInitFuJin = true;
        this.isInitHome = true;
        RadioButton radioButton = this.mRbShowYe;
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    public void onTopTabBackHome() {
        RadioButton radioButton;
        RadioGroup radioGroup = this.mRg;
        if (radioGroup == null || radioGroup.getVisibility() == 8 || (radioButton = this.mRbShowYe) == null || radioButton.isChecked()) {
            return;
        }
        this.mRbShowYe.setChecked(true);
    }

    public void initTopTab(boolean z, boolean z2, int i, HomeTabEntity.DataDTO.IndexHeadTabDTO indexHeadTabDTO, boolean z3) {
        try {
            boolean z4 = this.mRg.getVisibility() == 0 && this.mRbFuJin.isChecked();
            UnicomHomeLogUtils.getInstance().removeLog(UnicomHomeLogUtils.LOG_TYPE_TOP_TAB);
            if (!z2) {
                this.mRg.setVisibility(8);
                return;
            }
            this.yingYeTingTabParams = null;
            if (indexHeadTabDTO == null) {
                this.mRg.setVisibility(8);
                return;
            }
            List<HomeTabEntity.DataDTO.IndexHeadTabDTO.TabCfgArrayDTO> tabCfgArray = indexHeadTabDTO.getTabCfgArray();
            if (tabCfgArray.size() < 2) {
                this.mRg.setVisibility(8);
                return;
            }
            HomeTabEntity.DataDTO.IndexHeadTabDTO.TabCfgArrayDTO tabCfgArrayDTO = tabCfgArray.get(0);
            if (tabCfgArrayDTO == null) {
                this.mRbShowYe.setText("首页");
            } else {
                String title = tabCfgArrayDTO.getTitle();
                if (TextUtils.isEmpty(title)) {
                    title = "首页";
                }
                this.mRbShowYe.setText(title);
            }
            HomeTabEntity.DataDTO.IndexHeadTabDTO.TabCfgArrayDTO tabCfgArrayDTO2 = tabCfgArray.get(1);
            if (tabCfgArrayDTO2 == null) {
                this.mRg.setVisibility(8);
                return;
            }
            String title2 = tabCfgArrayDTO2.getTitle();
            if (TextUtils.isEmpty(title2)) {
                title2 = "附近营业厅";
            }
            this.mRbFuJin.setText(title2);
            String url = tabCfgArrayDTO2.getUrl();
            String miniProgramUrl = tabCfgArrayDTO2.getMiniProgramUrl();
            if (TextUtils.isEmpty(url)) {
                if (!z) {
                    this.mRg.setVisibility(8);
                    return;
                } else if (TextUtils.isEmpty(miniProgramUrl)) {
                    this.mRg.setVisibility(8);
                    return;
                }
            }
            if (this.oldTabDTO2 == null || !TextUtils.equals(this.oldTabDTO2.getUrl(), tabCfgArrayDTO2.getUrl()) || !TextUtils.equals(this.oldTabDTO2.getMiniProgramUrl(), tabCfgArrayDTO2.getMiniProgramUrl()) || !TextUtils.equals(this.oldTabDTO2.getTitle(), tabCfgArrayDTO2.getTitle()) || this.oldMiniProgramOpenUrlFlag != z || this.oldIndexHeadTabTimeout != i) {
                resetHeadTabState();
            }
            if (this.mRbShowYe.isChecked()) {
                UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TOP_TAB, new HomeLogEntity("1010106", "首页首页"));
            }
            if (this.mRbFuJin.isChecked()) {
                UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TOP_TAB, new HomeLogEntity("1010102", "首页附近营业厅"));
            }
            this.oldIndexHeadTabTimeout = i;
            this.oldMiniProgramOpenUrlFlag = z;
            this.oldTabDTO2 = tabCfgArrayDTO2;
            this.yingYeTingTabParams = new WebParamsEntity();
            this.yingYeTingTabParams.setTitle(title2);
            this.yingYeTingTabParams.setUrl(url);
            this.yingYeTingTabParams.setMiniProgramUrl(miniProgramUrl);
            this.yingYeTingTabParams.setMiniProgramOpenUrlFlag(z);
            this.yingYeTingTabParams.setIndexHeadTabTimeout(i);
            this.mRg.setVisibility(0);
            if (z4 && this.mRbShowYe.isChecked() && UnicomHomeConstants.isJsStartLogin3) {
                UnicomHomeConstants.isJsStartLogin3 = false;
                this.mRg.check(2131299175);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化顶部tab异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkHome() {
        try {
            this.mRbShowYe.setTypeface(Typeface.defaultFromStyle(1));
            this.mRbShowYe.setTextColor(Color.parseColor("#E60027"));
            this.mRbFuJin.setTypeface(Typeface.defaultFromStyle(0));
            this.mRbFuJin.setTextColor(Color.parseColor("#ffffff"));
            this.tabFragmentLayoutWai.setVisibility(0);
            if (this.headTabChangeListener != null) {
                this.headTabChangeListener.onHome(this.isInitHome);
            }
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TOP_TAB, new HomeLogEntity("1010106", "首页首页"));
            this.isInitHome = false;
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "切换首页异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkYingYeTing() {
        try {
            this.isInitHome = false;
            this.mRbShowYe.setTypeface(Typeface.defaultFromStyle(0));
            this.mRbShowYe.setTextColor(Color.parseColor("#ffffff"));
            this.mRbFuJin.setTypeface(Typeface.defaultFromStyle(1));
            this.mRbFuJin.setTextColor(Color.parseColor("#E60027"));
            this.tabFragmentLayoutWai.setVisibility(8);
            if (this.headTabChangeListener != null && this.yingYeTingTabParams != null) {
                this.headTabChangeListener.onFuJin(this.yingYeTingTabParams, this.isInitFuJin);
            }
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_TOP_TAB, new HomeLogEntity("1010102", "首页附近营业厅"));
            this.isInitFuJin = false;
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "切换营业厅异常:" + e.getMessage());
        }
    }

    public void initListener() {
        try {
            this.mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.2
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    Tracker.onCheckedChanged(radioGroup, i);
                    switch (i) {
                        case 2131299174:
                            UnicomToolBarManager.this.checkHome();
                            return;
                        case 2131299175:
                            UnicomToolBarManager.this.checkYingYeTing();
                            return;
                        default:
                            return;
                    }
                }
            });
            this.cityLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$Y6M8SrWFwMyiadebWzINup0S0T0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnicomToolBarManager.lambda$initListener$2(UnicomToolBarManager.this, view);
                }
            });
            this.kefuImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$pDic40R9OIr-_YAL9iy7G-WNiyY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnicomToolBarManager.lambda$initListener$3(UnicomToolBarManager.this, view);
                }
            });
            this.scanImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (UIUtils.isFastClick()) {
                        boolean checkPermissions = UIUtils.checkPermissions("android.permission.CAMERA");
                        if (!checkPermissions) {
                            try {
                                PvCurrencyLogUtils.pvLogMainDJ("1010105", "", "", "首页扫码-请求相机权限", "首页扫码");
                            } catch (Exception e) {
                                MsLogUtil.m7978e(e.getMessage());
                            }
                        }
                        PermissionDialog.show("扫一扫为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
                        try {
                            SoulPermission.getInstance().checkAndRequestPermission("android.permission.CAMERA", new C77171(checkPermissions));
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            PermissionDialog.dimissDialog();
                        }
                    }
                    UnicomHomeLogUtils.getInstance().clickLog("1010105", "首页扫码");
                    NBSActionInstrumentation.onClickEventExit();
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager$5$1 */
                /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
                public class C77171 implements CheckRequestPermissionListener {
                    final /* synthetic */ boolean val$isCamera;

                    C77171(boolean z) {
                        this.val$isCamera = z;
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionOk(Permission permission) {
                        PermissionDialog.dimissDialog();
                        new AvoidOnResult(UnicomToolBarManager.this.activityContext).startForResult(CapuActivity2.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$5$1$UuvgeHirjgBiscyATCdpRkcw9qE
                            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                            public final void onActivityResult(int i, Intent intent) {
                                UnicomToolBarManager.View$OnClickListenerC77165.C77171.lambda$onPermissionOk$0(UnicomToolBarManager.View$OnClickListenerC77165.C77171.this, i, intent);
                            }
                        });
                        try {
                            PvCurrencyLogUtils.pvLogMainDJ("1010105", "", "", this.val$isCamera ? "" : "首页扫码-允许相机权限", "首页扫码");
                        } catch (Exception e) {
                            MsLogUtil.m7978e(e.getMessage());
                        }
                    }

                    public static /* synthetic */ void lambda$onPermissionOk$0(C77171 c77171, int i, Intent intent) {
                        if (intent != null) {
                            String stringExtra = intent.getStringExtra("SCAN_RESULT");
                            if (!TextUtils.isEmpty(stringExtra)) {
                                stringExtra = stringExtra.trim();
                            }
                            UnicomToolBarManager.this.historyManager.deleteHistoryItem(stringExtra);
                            UnicomToolBarManager.this.historyManager.addHistoryItem(stringExtra);
                            UnicomToolBarManager.this.showResult(stringExtra);
                        }
                    }

                    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
                    public void onPermissionDenied(Permission permission) {
                        PermissionDialog.dimissDialog();
                        UIUtils.toast("未开启摄像头权限");
                        try {
                            PvCurrencyLogUtils.pvLogMainDJ("1010105", "", "", "首页扫码-拒绝相机权限", "首页扫码");
                        } catch (Exception e) {
                            MsLogUtil.m7978e(e.getMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化监听异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initListener$2(final UnicomToolBarManager unicomToolBarManager, View view) {
        try {
            final String locateCityCode = UserManager.getInstance().getLocateCityCode();
            if (!App.hasLogined() && "0".equals(App.getSharePreferenceUtil().getString("home_city_switch_yuanshen"))) {
                Intent intent = new Intent(unicomToolBarManager.activityContext, CitySelectActivity.class);
                intent.putExtra("from", "home");
                new AvoidOnResult(unicomToolBarManager.activityContext).startForResult(intent).subscribe(new Consumer<ActivityResultInfo>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.3
                    @Override // io.reactivex.functions.Consumer
                    public void accept(ActivityResultInfo activityResultInfo) throws Exception {
                        try {
                            if (TextUtils.isEmpty(locateCityCode) || locateCityCode.equals(UserManager.getInstance().getLocateCityCode())) {
                                return;
                            }
                            String string = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY);
                            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                            sharePreferenceUtil.putLong(CityChangeManager.PREFERENCE_SELECTCITYTIME_KEY() + string, 0L);
                            UnicomToolBarManager.this.setCityText(string);
                            UnicomToolBarManager.this.checkCityAndToastStatus(false);
                            if (UnicomToolBarManager.this.changeCityListener != null) {
                                UnicomToolBarManager.this.changeCityListener.changeCity();
                            }
                            if (App.hasLogined()) {
                                return;
                            }
                            UnicomToolBarManager.this.startLocation(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.4
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Throwable th) throws Exception {
                        th.printStackTrace();
                    }
                });
                try {
                    PvCurrencyLogUtils.pvLogMainDJ("1010101", "原生地市选择器", "", "", "首页地市专区");
                    UnicomHomeLogUtils.getInstance().clickLog("1010101", unicomToolBarManager.cityText.getText().toString(), UserManager.getInstance().getLocateProvinceCode(), UserManager.getInstance().getLocateCityCode());
                    return;
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                    return;
                }
            }
            Intent intent2 = new Intent(unicomToolBarManager.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            String dishizhuanqu = URLSet.getDishizhuanqu();
            istoTianyuan = true;
            String cityChangeUriHead = unicomToolBarManager.configManager.getCityChangeUriHead();
            if (!TextUtils.isEmpty(cityChangeUriHead)) {
                dishizhuanqu = cityChangeUriHead;
            }
            String charSequence = unicomToolBarManager.cityText.getText().toString();
            try {
                PvCurrencyLogUtils.pvLogMainDJ("1010101", dishizhuanqu + "", "", charSequence + "", "首页地市专区");
                UnicomHomeLogUtils.getInstance().clickLog("1010101", charSequence, UserManager.getInstance().getLocateProvinceCode(), UserManager.getInstance().getLocateCityCode());
            } catch (Exception e2) {
                MsLogUtil.m7978e(e2.getMessage());
            }
            webParamsEntity.setUrl(dishizhuanqu);
            webParamsEntity.setType("city");
            intent2.putExtra(WebFragment.webParams, webParamsEntity);
            new AvoidOnResult(unicomToolBarManager.activityContext).startForResult(intent2, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$5IzVrnxi6HUcPYxPFkE8AknzmNA
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent3) {
                    UnicomToolBarManager.lambda$initListener$1(UnicomToolBarManager.this, locateCityCode, i, intent3);
                }
            });
            return;
        } catch (Exception e3) {
            MsLogUtil.m7977e(TAG, "切换地市专区异常:" + e3.getMessage());
        }
        MsLogUtil.m7977e(TAG, "切换地市专区异常:" + e3.getMessage());
    }

    public static /* synthetic */ void lambda$initListener$1(UnicomToolBarManager unicomToolBarManager, String str, int i, Intent intent) {
        istoTianyuan = false;
        try {
            if (TextUtils.isEmpty(str) || str.equals(UserManager.getInstance().getLocateCityCode())) {
                return;
            }
            String string = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY);
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            sharePreferenceUtil.putLong(CityChangeManager.PREFERENCE_SELECTCITYTIME_KEY() + string, 0L);
            unicomToolBarManager.setCityText(string);
            unicomToolBarManager.checkCityAndToastStatus(false);
            if (unicomToolBarManager.changeCityListener != null) {
                unicomToolBarManager.changeCityListener.changeCity();
            }
            if (App.hasLogined()) {
                return;
            }
            unicomToolBarManager.startLocation(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initListener$3(UnicomToolBarManager unicomToolBarManager, View view) {
        IntentManager.gotoWebViewActivity(unicomToolBarManager.activityContext, URLSet.getKefuzhongxinUrl(), "客服中心");
        try {
            PvCurrencyLogUtils.pvLogMainDJ("1010104", URLSet.getKefuzhongxinUrl() + "", "", "", "首页客服");
            UnicomHomeLogUtils.getInstance().clickLog("1010104", "首页客服");
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void setToolBarBackgroundColor(String str) {
        try {
            this.toolBackgroundColor = Color.parseColor(str);
        } catch (Exception unused) {
            this.toolBackgroundColor = Color.parseColor("#E60027");
        }
        changeToolBackground();
    }

    public void changeToolBackground() {
        try {
            int scrollY = this.scrollerLayout.getScrollY();
            if (this.mRg.getVisibility() == 0 && this.mRbFuJin.isChecked()) {
                this.mLinToolBarLayout.setBackgroundColor(this.toolBackgroundColor);
            } else if ((scrollY > 100 || scrollY < 0) && this.alpha >= 255) {
            } else {
                this.alpha = (int) ((scrollY / 100.0f) * 255.0f);
                if (this.alpha > 255) {
                    this.alpha = 255;
                }
                this.mLinToolBarLayout.setBackgroundColor(Color.argb(this.alpha, Color.red(this.toolBackgroundColor), Color.green(this.toolBackgroundColor), Color.blue(this.toolBackgroundColor)));
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "修改顶部工具栏背景色异常:" + e.getMessage());
        }
    }

    public void setScrollerLayout(ConsecutiveScrollerLayout2 consecutiveScrollerLayout2) {
        this.scrollerLayout = consecutiveScrollerLayout2;
    }

    public void changeTabStatus(int i, int i2) {
        try {
            if (this.mRg.getVisibility() == 0 && !this.mRbFuJin.isChecked()) {
                if (i2 > i) {
                    UnicomHomeConstants.isTabVisiable = true;
                } else if (i2 < i) {
                    UnicomHomeConstants.isTabVisiable = false;
                }
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "修改推荐tab栏是否可见状态异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showResult(final String str) {
        try {
            if (URLUtil.isValidUrl(str)) {
                try {
                    if (str.contains("openminiprogram={")) {
                        String substring = str.substring(str.indexOf("openminiprogram={"));
                        new ManagerMiniProgram().openWxMiniProgramFormClient(this.activityContext, new JSONObject(substring.substring(substring.indexOf("{"), substring.indexOf("}") + 1)));
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                boolean z = false;
                try {
                    if (str.startsWith("ms_unicom") || str.startsWith("https://ms_unicom") || str.startsWith("http://ms_unicom") || str.startsWith("edop_unicom") || str.startsWith("https://edop_unicom") || str.startsWith("http://edop_unicom")) {
                        WebParamsEntity webParamsEntity = new WebParamsEntity();
                        webParamsEntity.setUrl(str);
                        webParamsEntity.setTitle("");
                        webParamsEntity.setBackMode("1");
                        webParamsEntity.setRequestType("get");
                        webParamsEntity.setNeedTitle(false);
                        webParamsEntity.setYule(false);
                        webParamsEntity.setType("UnicomMiniProgram");
                        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
                        intent.putExtra(WebFragment.webParams, webParamsEntity);
                        this.activityContext.startActivity(intent);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (!str.contains("10010.com") && !str.contains("wht.wo.com.cn")) {
                    if (str.contains("10010nm.com/automaticSecurity/selfHelp-cowcatcher")) {
                        IntentManager.generateIntentAndGo(this.activityContext, "https://wx.10010nm.com/nmweb/nmwx-gyf/sk-nmltNewUI/band-3rd.html?random=" + IntentManager.getValueByName(str, "random"), "内蒙古联通", false, "get");
                        return;
                    }
                    Iterator<String> it = this.woPayScanManager.getWoPayScanWhiteList().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        } else if (str.contains(it.next())) {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.woPayScanManager.assembleWoPayURL(str), "二维码内容", true, "get");
                        return;
                    } else {
                        ScanCustomDialog.show(this.activityContext, "扫描结果", str, true, "取消", "打开", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.6
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onBackKeyDown() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onCancel() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onClickCancel() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onShow() {
                            }

                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                            public void onClickOk() {
                                IntentManager.generateIntentAndGo(UnicomToolBarManager.this.activityContext, str, "二维码内容", false, "get");
                            }
                        });
                        return;
                    }
                }
                IntentManager.generateIntentAndGo(this.activityContext, str, "中国联通", false, "get");
                return;
            }
            ScanCustomDialog.show(this.activityContext, "扫描结果", str, false, null, "确定", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.7
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }
            });
        } catch (Exception e3) {
            MsLogUtil.m7977e(TAG, "解析扫一扫结果异常:" + e3.getMessage());
        }
    }

    public void startLocation(final boolean z) {
        try {
            ManagerLocation.getInstance().startLocation(this.activityContext, "首页启动定位", new MyLocationInterface.CallBack() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$yaxVXXrOx4OZQ3A-OSGKWXxqT5I
                @Override // com.sinovatech.unicom.basic.myinterface.MyLocationInterface.CallBack
                public final void Success(ManagerLocation.LocationEntity locationEntity) {
                    UnicomToolBarManager.lambda$startLocation$4(UnicomToolBarManager.this, z, locationEntity);
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "定位异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$startLocation$4(UnicomToolBarManager unicomToolBarManager, boolean z, ManagerLocation.LocationEntity locationEntity) {
        MsLogUtil.m7979d("百度定位--->", "首页HomeFragment 启动定位回调" + locationEntity.toString());
        unicomToolBarManager.loadSearch(locationEntity);
        if (z) {
            return;
        }
        if (locationEntity.isLocationSuccess()) {
            try {
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                sharePreferenceUtil.putString("lat", locationEntity.getBdLocation().getLatitude() + "");
                SharePreferenceUtil sharePreferenceUtil2 = App.getSharePreferenceUtil();
                sharePreferenceUtil2.putString("long", locationEntity.getBdLocation().getLongitude() + "");
                String string = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY);
                String string2 = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_location_KEY);
                MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "缓存的省市名称:" + string + "  定位的省市名称:" + string2);
                String substring = locationEntity.getBdLocation().getCity().substring(0, locationEntity.getBdLocation().getCity().length() - 1);
                MsLogUtil.m7979d("cityChange", "定位出的城市名称" + substring + "---缓存的城市名称" + string2);
                if (!TextUtils.isEmpty(substring) && !TextUtils.isEmpty(string2) && !substring.equals(string2)) {
                    UserManager.getInstance().saveUserLocateCityTime(0L);
                    MsLogUtil.m7979d("cityChange", "定位变化的时候清空时长标识" + string2);
                }
                SharePreferenceUtil sharePreferenceUtil3 = App.getSharePreferenceUtil();
                long j = sharePreferenceUtil3.getLong(CityChangeManager.PREFERENCE_SELECTCITYTIME_KEY() + string);
                boolean z2 = System.currentTimeMillis() - j > unicomToolBarManager.configManager.getCityChangeTime();
                MsLogUtil.m7979d("cityChange", "是否时间到期" + z2 + j + "----" + unicomToolBarManager.configManager.getCityChangeTime() + "---是否标识重置" + App.reEnterAfterCloseApplication_ForCitySelected);
                unicomToolBarManager.saveDwCityName("");
                if (z2) {
                    MsLogUtil.m7979d("cityChange", "本地" + string + "---定位" + substring);
                    if (!TextUtils.isEmpty(substring) && !substring.contains(string)) {
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "定位不一致展示弹窗");
                        unicomToolBarManager.changeCityName = substring;
                        unicomToolBarManager.saveDwCityName(substring);
                        App.getSharePreferenceUtil().putString(CityChangeManager.PREFERENCE_location_KEY, substring);
                    } else {
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "定位一直不展示弹窗");
                        unicomToolBarManager.changeCityName = "";
                        unicomToolBarManager.saveDwCityName("");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                unicomToolBarManager.changeCityName = "";
                unicomToolBarManager.saveDwCityName("");
            }
            if (locationEntity.isLocationSuccess()) {
                unicomToolBarManager.homeCommon.pushFujinyineyeting(locationEntity.getBdLocation());
            }
        } else {
            unicomToolBarManager.changeCityName = "";
            unicomToolBarManager.saveDwCityName("");
        }
        unicomToolBarManager.isCheckCityView = true;
        unicomToolBarManager.checkShowBottomOrToast();
    }

    public void saveDwCityName(String str) {
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        sharePreferenceUtil.putString(UserManager.getInstance().getCurrentPhoneNumber() + "_dingwei_city_name", str);
    }

    public void checkCityState() {
        try {
            if (this.cityOrToastManager != null) {
                this.cityOrToastManager.hideCityAndToastView();
            }
            this.isCheckCityView = true;
            this.isCheckToastView = true;
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            this.changeCityName = sharePreferenceUtil.getString(UserManager.getInstance().getCurrentPhoneNumber() + "_dingwei_city_name");
            if (TextUtils.isEmpty(this.changeCityName)) {
                return;
            }
            checkShowBottomOrToast();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCityText(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            UnicomHomeLogUtils.getInstance().putLockLogData(UnicomHomeLogUtils.LOG_TYPE_CITY, new HomeLogEntity("1010101", "首页地市专区"));
            if (!TextUtils.isEmpty(str) && str.length() > 3) {
                if (str.equals("阿里地区")) {
                    str = "阿里...";
                } else {
                    str = str.substring(0, 3) + "...";
                }
            }
            this.cityText.setText(str);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "设置左上角省市名称异常:" + e.getMessage());
        }
    }

    public void loadSearch(ManagerLocation.LocationEntity locationEntity) {
        try {
            Observable.zip(this.searchManager.loadScrollKeywordFromNetwork(), this.searchManager.loadHuoDongFromNetwork(locationEntity), new BiFunction<List<SearchEntity>, List<SearchHuoDongEntity>, List<SearchEntity>>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.10
                @Override // io.reactivex.functions.BiFunction
                public List<SearchEntity> apply(List<SearchEntity> list, List<SearchHuoDongEntity> list2) throws Exception {
                    try {
                        UnicomToolBarManager.this.homeSearchView.setData(list, list2);
                        return null;
                    } catch (Exception e) {
                        UIUtils.logE(e.getMessage());
                        return null;
                    }
                }
            }).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.8
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.9
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载搜索数据异常:" + e.getMessage());
        }
    }

    public void setSearchData(List<SearchEntity> list, List<SearchHuoDongEntity> list2) {
        HomeSearchView homeSearchView = this.homeSearchView;
        if (homeSearchView != null) {
            homeSearchView.setData(list, list2);
        }
    }

    public void checkCityAndToastStatus(boolean z) {
        try {
            this.isCheckCityView = false;
            if (z) {
                this.isCheckToastView = false;
                getHomeBottomData();
            } else {
                this.isCheckToastView = true;
            }
            startLocation(false);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "检查弹窗状态异常:" + e.getMessage());
        }
    }

    public void getHomeBottomData() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("version", App.getInstance().getString(2131886969));
        } catch (Exception e) {
            e.printStackTrace();
        }
        App.getAsyncHttpClient().rxPost(URLSet.getService_home_toast_url(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, HomeToastEntity>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.13
            @Override // io.reactivex.functions.Function
            public HomeToastEntity apply(String str) throws Exception {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if ("0000".equals(jSONObject.optString("code"))) {
                        JSONArray optJSONArray = jSONObject.optJSONArray("list");
                        if (optJSONArray.length() > 0) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                            String optString = optJSONObject.optString("productName");
                            String optString2 = optJSONObject.optString("productDesc");
                            String optString3 = optJSONObject.optString("productRedirecturl");
                            String optString4 = optJSONObject.optString("productUrl");
                            HomeToastEntity homeToastEntity = new HomeToastEntity();
                            homeToastEntity.setIconUrl(optString4);
                            homeToastEntity.setProductDesc(optString2);
                            homeToastEntity.setProductName(optString);
                            homeToastEntity.setProductRedirecturl(optString3);
                            return homeToastEntity;
                        }
                        return null;
                    }
                    return null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HomeToastEntity>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.11
            @Override // io.reactivex.functions.Consumer
            public void accept(HomeToastEntity homeToastEntity) throws Exception {
                UnicomToolBarManager.this.bottomWindowEntity = homeToastEntity;
                UnicomToolBarManager.this.isCheckToastView = true;
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载完智慧推荐数据 检查弹窗状态");
                        UnicomToolBarManager.this.checkShowBottomOrToast();
                    }
                }, 3000L);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.12
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                UnicomToolBarManager unicomToolBarManager = UnicomToolBarManager.this;
                unicomToolBarManager.isCheckToastView = true;
                unicomToolBarManager.bottomWindowEntity = null;
                MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载智慧推荐数据失败  检查弹窗状态");
                UnicomToolBarManager.this.checkShowBottomOrToast();
            }
        });
    }

    public void checkShowBottomOrToast() {
        try {
            if (this.isCheckCityView && this.isCheckToastView) {
                if (this.mBottomView != null) {
                    this.cityOrToastManager.showBottomView(this.bottomWindowEntity, this.changeCityName, new HomeChangeCityOrToastManager.ICityChange() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomToolBarManager.14
                        @Override // com.sinovatech.unicom.basic.p315ui.home.manager.HomeChangeCityOrToastManager.ICityChange
                        public void change(String str) {
                            try {
                                UnicomToolBarManager.this.setCityText(str);
                                String privienceCode = CityCodingManager.privienceCode(UnicomToolBarManager.this.activityContext, str);
                                String cityCode = CityCodingManager.cityCode(UnicomToolBarManager.this.activityContext, str);
                                UserManager.getInstance().saveUserLocateCityName(str);
                                UserManager.getInstance().saveLocateCityName(str);
                                UserManager.getInstance().saveLocateProvinceCode(privienceCode);
                                UserManager.getInstance().saveLocateCityCode(cityCode);
                                if (UnicomToolBarManager.this.changeCityListener != null) {
                                    UnicomToolBarManager.this.changeCityListener.changeCity();
                                }
                                UnicomToolBarManager.this.startLocation(false);
                                UserManager.getInstance().saveUserLocateCityTime(System.currentTimeMillis());
                            } catch (Exception e) {
                                String str2 = UnicomToolBarManager.TAG;
                                MsLogUtil.m7977e(str2, "底部省市切换弹窗切换城市异常:" + e.getMessage());
                            }
                        }

                        @Override // com.sinovatech.unicom.basic.p315ui.home.manager.HomeChangeCityOrToastManager.ICityChange
                        public void onClose(String str) {
                            UserManager.getInstance().saveUserLocateCityTime(System.currentTimeMillis());
                        }
                    });
                }
                this.bottomWindowEntity = null;
                this.changeCityName = "";
                this.isCheckCityView = false;
                this.isCheckToastView = false;
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "弹窗状态异常:" + e.getMessage());
        }
    }

    public void hideChangeCityView() {
        HomeChangeCityOrToastManager homeChangeCityOrToastManager = this.cityOrToastManager;
        if (homeChangeCityOrToastManager != null) {
            homeChangeCityOrToastManager.hideCityAndToastView();
        }
    }

    public void initProvence() {
        try {
            setCityText(App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY));
            if (TextUtils.isEmpty(App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_CITY_SELECT_KEY_LOCATION())) && App.getSharePreferenceUtil().getBoolean("isBaseMobileProvinceInfo")) {
                String userAreaname = UserManager.getInstance().getUserAreaname();
                if (TextUtils.isEmpty(userAreaname)) {
                    setCityText(CityChangeManager.DEFAULT_SELECT_CITY);
                    App.getSharePreferenceUtil().putString(CityChangeManager.PREFERENCE_SELECT_KEY, CityChangeManager.DEFAULT_SELECT_CITY);
                    UserManager.getInstance().saveLocateProvinceCode(CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE);
                    UserManager.getInstance().saveLocateCityCode(CityChangeManager.DEFAULT_SELECT_CITY_CODE);
                } else {
                    setCityText(userAreaname);
                    App.getSharePreferenceUtil().putString(CityChangeManager.PREFERENCE_SELECT_KEY, userAreaname);
                    UserManager.getInstance().saveLocateProvinceCode(UserManager.getInstance().getCurrentProvinceCode());
                    UserManager.getInstance().saveLocateCityCode(UserManager.getInstance().getUserAreaid());
                }
                App.getSharePreferenceUtil().putBoolean("isBaseMobileProvinceInfo", false);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化省份名称异常:" + e.getMessage());
        }
    }

    public void setQiandao(final HomeTabEntity.DataDTO dataDTO) {
        if (dataDTO == null) {
            return;
        }
        try {
            final String signImg = dataDTO.getSignImg();
            if (UserManager.getInstance().isYiwang()) {
                signImg = dataDTO.getSignDiffImg();
            }
            if (AdvDataCenter.getInstance().getQiandaoHasClicked() && App.hasLogined()) {
                signImg = dataDTO.getSignedImg();
            }
            if (!TextUtils.isEmpty(signImg) && !TextUtils.isEmpty(dataDTO.getSignUrl())) {
                this.qiandaoImage.setVisibility(0);
                GlideApp.with(App.getInstance()).load(signImg).placeholder(2131231795).into(this.qiandaoImage);
                this.qiandaoImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$PvMZj5JnCiY7EQvSTgQIzubTjC8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UnicomToolBarManager.lambda$setQiandao$5(UnicomToolBarManager.this, dataDTO, signImg, view);
                    }
                });
            }
            this.qiandaoImage.setVisibility(4);
            this.qiandaoImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$UnicomToolBarManager$PvMZj5JnCiY7EQvSTgQIzubTjC8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnicomToolBarManager.lambda$setQiandao$5(UnicomToolBarManager.this, dataDTO, signImg, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setQiandao$5(UnicomToolBarManager unicomToolBarManager, HomeTabEntity.DataDTO dataDTO, String str, View view) {
        IntentManager.gotoWebViewActivity(unicomToolBarManager.activityContext, dataDTO.getSignUrl(), "");
        PvCurrencyLogUtils.pvLogMainDJ("1010103", dataDTO.getSignUrl(), "", str, "首页签到");
        UnicomHomeLogUtils.getInstance().clickLog("1010103", "首页签到");
    }

    public void removeTopTabData() {
        this.oldTabDTO2 = null;
        this.oldMiniProgramOpenUrlFlag = false;
        this.oldIndexHeadTabTimeout = 5;
    }
}
