package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.binioter.guideview.GuideBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.player.VideoCacheManager;
import com.fort.andjni.JniLib;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.CustomActivityEvent;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p314po.MyUnicomEntity;
import com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainConfig;
import com.sinovatech.unicom.basic.p315ui.view.UpdateProgressDialogView;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.SettingCareComponent;
import com.sinovatech.unicom.basic.webview.UnicomSrcResourceIntercepter;
import com.sinovatech.unicom.common.DownloaderVideo;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager;
import com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity;
import com.sinovatech.unicom.separatemodule.login.MimaGuanli.MimaGuanliActivity;
import com.sinovatech.unicom.separatemodule.login.MimaguanliEntity;
import com.sinovatech.unicom.separatemodule.messagenotification.activity.MessageNotificationActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLanucher;
import com.sinovatech.unicom.separatemodule.miniprogram.dic.ManagerWebCacheDictionary;
import com.sinovatech.unicom.separatemodule.miniprogram.lowcode.CumpLowcodeResourceIntercepter;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.FileSizeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.security.SecurityActivity;
import com.sinovatech.unicom.separatemodule.shilaohua.AccessibilityActivity;
import com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUser;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SettingNewActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private LinearLayout aboutLayout;
    private ListAdapter adapter1;
    private LinearLayout addressLayout;
    private LinearLayout anquanLayout;
    private ImageButton backButton;
    private ToggleButton caerToggleButton;
    private LinearLayout careLayout;
    private LinearLayout clearCacheLayout;
    private TextView clearCacheTextView;
    private ConfigManager configManager;
    private AppCompatActivity context;
    private LinearLayout customLanguageLayout;
    private MenuDataCenter dataCenter;
    private LinearLayout evaluation;
    private LinearLayout fontLayout;
    private TextView guanhuaiText;
    private LinearLayout homedataLayout;
    private LinearLayout kefuzhongxinLayout;
    private View lineView;
    private List<MenuEntity> list1;
    private ListView listView1;
    private Button logoutButton;
    private TextView mTvAnQuan;
    private TextView mTvBanBenGengXin;
    private TextView mTvDiZhiGuanLi;
    private TextView mTvDuoYuYan;
    private TextView mTvGuanYuWoMen;
    private TextView mTvQingChuHuanCun;
    private TextView mTvShouYeShuJu;
    private TextView mTvWuZhangAiText;
    private TextView mTvYinSi;
    private ManagerUser managerUser;
    private LinearLayout messageLayout;
    LinearLayout mimalayout;
    TextView mimaviceView;
    private MyUnicomEntity myUnicomEntity;
    private TextView nickName;
    private boolean opencare = true;
    RequestOptions options;

    /* renamed from: pd */
    private ProgressDialog f18406pd;
    private CircularImage photoImage;
    private SharePreferenceUtil preference;
    private UpdateProgressDialogView progressDialogView;
    private TextView qianmingtv;
    private LinearLayout setting_quanxian_layout;
    private TextView titleView;
    String url;
    private UserManager userManager;
    private LinearLayout versionLayout;
    private TextView versionTextView;
    private LinearLayout wuzhangaiLayout;
    private LinearLayout yinsiXieyiLayout;
    private TextView yiwangTip;
    private LinearLayout zhanghuLayout;
    private LinearLayout zidingyipifuLayout;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 61);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C74881 implements CompoundButton.OnCheckedChangeListener {
        C74881() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            Tracker.onCheckedChanged(compoundButton, z);
            if (z) {
                LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
                EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
                App.getSharePreferenceUtil().putString("unicom_app_main_type", "1");
                App.getSharePreferenceUtil().putString("unicom_app_main_url", "ms_unicom_guanhuai");
                Intent intent = new Intent(SettingNewActivity.this.context, CustomMainActivity.class);
                intent.putExtra("ms_unicom_url", "ms_unicom_guanhuai");
                intent.putExtra("fromSetting", true);
                SettingNewActivity.this.startActivity(intent);
                SettingNewActivity.this.preference.putBoolean("CareHome", true);
                StatisticsUploadUtils.upload(SettingNewActivity.this.context, "7", "设置", "按钮", "0", "关怀版切换按钮-打开", "");
                SettingNewActivity.this.finish();
                return;
            }
            App.getSharePreferenceUtil().putString("unicom_app_main_type", "0");
            App.getSharePreferenceUtil().putString("unicom_app_main_url", "");
            EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
            SettingNewActivity settingNewActivity = SettingNewActivity.this;
            settingNewActivity.startActivity(new Intent(settingNewActivity.context, MainActivity.class));
            SettingNewActivity.this.preference.putBoolean("CareHome", false);
            StatisticsUploadUtils.upload(SettingNewActivity.this.context, "7", "设置", "按钮", "0", "关怀版切换按钮-关闭", "");
            SettingNewActivity.this.finish();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class RunnableC74982 implements Runnable {
        RunnableC74982() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SettingNewActivity.this.showGuideView();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ProgressDialog progressDialog;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296473:
                finish();
                break;
            case 2131296951:
                PingjiaDialogManager.rewardQuery(this.context, new PingjiaDialogManager.PingjiaDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.3
                    @Override // com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.PingjiaDialogListener
                    public void onNext(JSONObject jSONObject) {
                        String optString = jSONObject.optString("code");
                        String optString2 = jSONObject.optString("data");
                        String str = "";
                        if ("0000".equals(optString)) {
                            str = "01";
                        } else {
                            PingjiaDialogManager.jumpAppMarket(SettingNewActivity.this.context);
                        }
                        PingjiaDialogManager.show(SettingNewActivity.this.context, str, optString2, "0");
                    }

                    @Override // com.sinovatech.unicom.separatemodule.appscore.PingjiaDialogManager.PingjiaDialogListener
                    public void onError() {
                        UIUtils.showCenterOnlyTextToast(SettingNewActivity.this.context, "活动太火爆，请稍后再试。", 0);
                    }
                });
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "给我好评", "");
                break;
            case 2131298062:
                startActivity(new Intent(this, MessageNotificationActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "通用", "");
                break;
            case 2131298542:
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "关于我们", "");
                IntentManager.generateIntentAndGo(this.context, URLSet.getAbout(), "关于我们", false, "get");
                break;
            case 2131298543:
                MyUnicomEntity myUnicomEntity = this.myUnicomEntity;
                if (myUnicomEntity != null) {
                    String jpUrl = myUnicomEntity.getJpUrl();
                    if (UserManager.getInstance().isYiwang()) {
                        String str = "--";
                        TextView textView = this.nickName;
                        if (textView != null && textView.getText() != null) {
                            str = TextUtils.isEmpty(this.nickName.getText().toString()) ? "--" : this.nickName.getText().toString();
                        }
                        if (TextUtils.isEmpty(jpUrl)) {
                            IntentManager.goYwUserInfoActivity(this.context, str, this.myUnicomEntity.getInfoDetail());
                            NBSActionInstrumentation.onClickEventExit();
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("infoUrl", this.myUnicomEntity.getInfoDetail());
                        bundle.putString("nickName", str);
                        IntentManager.gotoWebViewActivityBundle(this.context, jpUrl, "", bundle);
                    } else {
                        if (TextUtils.isEmpty(jpUrl)) {
                            jpUrl = URLSet.userDetails();
                        }
                        IntentManager.gotoWebViewActivity(this.context, jpUrl, "");
                    }
                    StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "设置-个人资料", "");
                    break;
                }
                break;
            case 2131298544:
                startActivity(new Intent(this.context, SecurityActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "设置-安全中心", "");
                break;
            case 2131298546:
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "清除缓存", "");
                VideoCacheManager.clearAllCache(this);
                App.getSharePreferenceUtil().putString("kaiping_url", "");
                deleteCache();
                break;
            case 2131298549:
                startActivity(new Intent(this, FontSelectActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "字号设置", "");
                break;
            case 2131298550:
                startActivity(new Intent(this, LanguageActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "多语言", "");
                break;
            case 2131298552:
                IntentManager.generateIntentAndGo(this.context, this.configManager.getAddressGuanliUrl(), "");
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "设置-地址管理", "");
                break;
            case 2131298559:
                IntentManager.generateIntentAndGo(this.context, URLSet.getSetting_homedata(), "协议与条款", false, "get");
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "协议与条款", "");
                break;
            case 2131298560:
                IntentManager.generateIntentAndGo(this.context, URLSet.getKefuzhongxinUrl(), "客服中心", false, "get");
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "客服中心", "");
                break;
            case 2131298571:
                this.zhanghuLayout.performClick();
                break;
            case 2131298577:
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "版本更新", "");
                this.f18406pd.setMessage("正在检查最新版本...");
                if (!isFinishing() && !isDestroyed() && (progressDialog = this.f18406pd) != null && !progressDialog.isShowing()) {
                    this.f18406pd.show();
                }
                checkVersionUpdate(true);
                break;
            case 2131298580:
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "用户隐私政策", "");
                IntentManager.generateIntentAndGo(this.context, URLSet.getUserPrivacy(), "中国联通APP用户隐私政策", false, "get");
                break;
            case 2131298583:
                startActivity(new Intent(this, HomeBackGroundActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "首页换肤", "");
                break;
            case 2131299418:
                if (this.preference.getBoolean("CareHome")) {
                    logout("0", "1");
                } else if (!LanguageUtil.getInstance().isChinese()) {
                    logout("0", "1");
                } else {
                    showlogout();
                }
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "退出登录", "");
                break;
            case 2131299822:
                startActivity(new Intent(this.context, AccessibilityActivity.class));
                StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "无障碍设置", "");
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        if (App.hasLogined()) {
            getInfo(true);
            getInfo(false);
            this.logoutButton.setVisibility(0);
            this.zidingyipifuLayout.setVisibility(0);
        } else {
            this.zidingyipifuLayout.setVisibility(8);
            this.logoutButton.setVisibility(8);
        }
        if (App.hasLogined() && !"05".equals(this.userManager.getLoginType())) {
            this.zhanghuLayout.setVisibility(0);
        } else {
            this.zhanghuLayout.setVisibility(8);
        }
        checkGuanHuai();
        if (UserManager.getInstance().isYiwang()) {
            this.yiwangTip.setVisibility(8);
        } else {
            this.yiwangTip.setVisibility(0);
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void checkVersionUpdate(final boolean z) {
        AsyncHttpClient asyncHttpClient = App.getAsyncHttpClient();
        asyncHttpClient.get(URLSet.getConfig_url() + "?version=" + getString(2131886969), new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.4
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            /* JADX WARN: Removed duplicated region for block: B:27:0x00d0 A[Catch: Exception -> 0x0202, TryCatch #0 {Exception -> 0x0202, blocks: (B:6:0x000d, B:8:0x0033, B:10:0x0072, B:13:0x0095, B:15:0x00a4, B:17:0x00ac, B:25:0x00c6, B:27:0x00d0, B:29:0x00e7, B:30:0x00f1, B:32:0x00f7, B:34:0x00ff, B:36:0x0109, B:37:0x010d, B:39:0x0172, B:41:0x017e, B:44:0x018b, B:46:0x01bd, B:47:0x01c3, B:19:0x00b2, B:21:0x00b8), top: B:52:0x000d }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0172 A[Catch: Exception -> 0x0202, TryCatch #0 {Exception -> 0x0202, blocks: (B:6:0x000d, B:8:0x0033, B:10:0x0072, B:13:0x0095, B:15:0x00a4, B:17:0x00ac, B:25:0x00c6, B:27:0x00d0, B:29:0x00e7, B:30:0x00f1, B:32:0x00f7, B:34:0x00ff, B:36:0x0109, B:37:0x010d, B:39:0x0172, B:41:0x017e, B:44:0x018b, B:46:0x01bd, B:47:0x01c3, B:19:0x00b2, B:21:0x00b8), top: B:52:0x000d }] */
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onSuccess(int r8, java.lang.String r9) {
                /*
                    Method dump skipped, instructions count: 519
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.C75004.onSuccess(int, java.lang.String):void");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                if (!SettingNewActivity.this.isFinishing() && !SettingNewActivity.this.isDestroyed() && SettingNewActivity.this.f18406pd != null && SettingNewActivity.this.f18406pd.isShowing()) {
                    SettingNewActivity.this.f18406pd.cancel();
                }
                super.onFinish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpdateDialog(String str, final String str2, String str3) {
        String str4;
        if ("1".equals(str3.trim()) || "0".equals(str3.trim())) {
            if (SystemServiceUtils.isWifiActive()) {
                str4 = str + "\r\n" + getResources().getString(2131886973);
            } else {
                str4 = str;
            }
            CustomDialogManager.show((Activity) this.context, "升级提示", str4, 3, true, "不，谢谢", "升级", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.5
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    ManagerMainConfig.isGaryPub = false;
                    SettingNewActivity.this.download(str2, false);
                }
            });
        } else if ("2".equals(str3.trim())) {
            CustomDialogManager.show((Activity) this.context, "升级提示", str, 3, false, "", "升级", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.6
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    ManagerMainConfig.isGaryPub = false;
                    SettingNewActivity.this.download(str2, true);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void download(String str, boolean z) {
        this.progressDialogView = new UpdateProgressDialogView(this, z, null);
        this.progressDialogView.startDownLoad(str);
    }

    public void setDownLoadProgress(int i, int i2) {
        UpdateProgressDialogView updateProgressDialogView = this.progressDialogView;
        if (updateProgressDialogView != null) {
            updateProgressDialogView.setProgressBarProgress(i, i2);
        }
    }

    private void deleteCache() {
        CustomDialogManager.show((Activity) this, "温馨提示", "是否要清除缓存？", true, "取消", "清除", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.7
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
                try {
                    ManagerWebCacheDictionary.clearGlideImage();
                    DownloaderVideo.clearVideo();
                    CumpLanucher.getInstance(SettingNewActivity.this.context).deleteAllCache();
                    CacheDataCenter.getInstance().setTongyicaijiJs("");
                    UnicomSrcResourceIntercepter.getInstance(SettingNewActivity.this.context).clearAllCache();
                    CumpLowcodeResourceIntercepter.getInstance(SettingNewActivity.this.context).clearAllCache();
                    SettingNewActivity.this.clearCacheTextView.setText(SettingNewActivity.this.getCacheSize());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCacheSize() {
        try {
            return FileSizeUtil.getAutoFileOrFilesSize(ManagerWebCacheDictionary.getUnicomCacheRootDic(true).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void showlogout() {
        if (this.userManager.getBindAccountNameList(null).size() > 0) {
            showSelectAccountDialog();
        } else {
            showUnbindDialog();
        }
    }

    public void showSelectAccountDialog() {
        final Dialog dialog = new Dialog(this, 2131952236);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493398, (ViewGroup) null);
        linearLayout.findViewById(2131298197).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((Button) linearLayout.findViewById(2131298199)).setText("切换账号登录");
        linearLayout.findViewById(2131298199).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                Intent intent = new Intent(SettingNewActivity.this.context, LoginBindActivity.class);
                intent.putExtra("needLogout", true);
                SettingNewActivity.this.startActivityForResult(intent, 2801);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((Button) linearLayout.findViewById(2131298198)).setText("退出登录");
        linearLayout.findViewById(2131298198).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                SettingNewActivity.this.showUnbindDialog();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        setShareDialog(dialog, linearLayout);
        dialog.show();
    }

    public void showUnbindDialog() {
        UserManager userManager = this.userManager;
        if (userManager.getSelectAccountName(userManager.getUserAccountName(), "1") != null) {
            CustomDialogManager.show((Activity) this.context, "退出登录", "是否解除绑定并退出登录，解除绑定后下次登录需要手动输入账号和密码", true, "解绑并退出登录", "仅退出登录", true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.11
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                    SettingNewActivity.this.logout("0", "1");
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                    SettingNewActivity.this.userManager.removeSelectAccountName(SettingNewActivity.this.userManager.getUserAccountName());
                    SettingNewActivity.this.logout("0", "2");
                }
            });
        } else {
            logout("0", "1");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logout(final String str, String str2) {
        StatisticsUploadUtils.upload(this.context, "7", "设置", "按钮", "0", "退出登录", "");
        RequestParams requestParams = new RequestParams();
        requestParams.put("logout_flag", str2);
        requestParams.put("version", getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        UserManager userManager = this.userManager;
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.12
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                try {
                    SettingNewActivity.this.f18406pd.setMessage("退出登录");
                    if (UIUtils.isShowDialog(SettingNewActivity.this, SettingNewActivity.this.f18406pd)) {
                        SettingNewActivity.this.f18406pd.show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                SettingNewActivity settingNewActivity = SettingNewActivity.this;
                if (UIUtils.isDismissDialog(settingNewActivity, settingNewActivity.f18406pd)) {
                    SettingNewActivity.this.f18406pd.cancel();
                }
                if (SettingNewActivity.this.preference.getBoolean("CareHome")) {
                    EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
                    SettingNewActivity.this.preference.putBoolean("CareHome", false);
                    SettingNewActivity.this.caerToggleButton.setChecked(SettingNewActivity.this.preference.getBoolean("CareHome"));
                } else if (LanguageUtil.isWeiWen()) {
                    EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
                    LanguageUtil.setLanguage(LanguageUtil.CHN_CN, "");
                }
                LoginManager.logout(SettingNewActivity.this.context);
                EventBusUtils.post(new CustomActivityEvent(EventBusUtils.EVENT_MAIN_CUSTOM_ACTIVITY_FINISH));
                if (str.equals("0")) {
                    App.mainTagFromOtherActivity = MainActivity.Fragment_User;
                    SettingNewActivity.this.startActivity(new Intent(SettingNewActivity.this.context, MainActivity.class));
                    SettingNewActivity.this.context.finish();
                } else if (str.equals("1")) {
                    SettingNewActivity.this.startActivity(new Intent(SettingNewActivity.this.context, LoginBindActivity.class));
                }
            }
        });
    }

    private void setShareDialog(Dialog dialog, View view) {
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952235);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ListAdapter extends BaseAdapter {
        private List<MenuEntity> list;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        public ListAdapter(List<MenuEntity> list) {
            this.list = list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.list.size();
        }

        @Override // android.widget.Adapter
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view2;
            final ViewHodler viewHodler;
            if (view == null) {
                viewHodler = new ViewHodler(SettingNewActivity.this, null);
                view2 = SettingNewActivity.this.context.getLayoutInflater().inflate(2131493517, (ViewGroup) null);
                viewHodler.imageView = (ImageView) view2.findViewById(2131299371);
                viewHodler.textView = (TextView) view2.findViewById(2131299374);
                viewHodler.bottomLineView = view2.findViewById(2131299373);
                viewHodler.jiantou = (ImageView) view2.findViewById(2131299372);
                viewHodler.redPoitn = (ImageView) view2.findViewById(2131298574);
                view2.setTag(viewHodler);
            } else {
                ViewHodler viewHodler2 = (ViewHodler) view.getTag();
                viewHodler2.textView.setText((CharSequence) null);
                viewHodler2.imageView.setImageBitmap(null);
                viewHodler2.bottomLineView.setVisibility(0);
                view2 = view;
                viewHodler = viewHodler2;
            }
            viewHodler.jiantou.setVisibility(8);
            final MenuEntity menuEntity = this.list.get(i);
            viewHodler.textView.setText(LanguageUtil.getInstance().getText(menuEntity.getMenuTitle(), viewHodler.textView));
            viewHodler.imageView.setVisibility(8);
            if (menuEntity.isNewItem()) {
                viewHodler.redPoitn.setVisibility(8);
            } else {
                viewHodler.redPoitn.setVisibility(8);
            }
            if (i == getCount() - 1) {
                viewHodler.bottomLineView.setVisibility(8);
            }
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.ListAdapter.1
                /* JADX WARN: Removed duplicated region for block: B:15:0x0056  */
                /* JADX WARN: Removed duplicated region for block: B:16:0x0064  */
                @Override // android.view.View.OnClickListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onClick(android.view.View r8) {
                    /*
                        r7 = this;
                        com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation.onClickEventEnter(r8, r7)
                        com.bytedance.applog.tracker.Tracker.onClick(r8)
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        java.util.List r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$300(r8)
                        if (r8 == 0) goto L53
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        java.util.List r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$300(r8)
                        int r8 = r8.size()
                        if (r8 <= 0) goto L53
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        java.util.List r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$300(r8)
                        int r0 = r2
                        java.lang.Object r8 = r8.get(r0)
                        if (r8 == 0) goto L53
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        java.util.List r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$300(r8)
                        int r0 = r2
                        java.lang.Object r8 = r8.get(r0)
                        com.sinovatech.unicom.basic.po.MenuEntity r8 = (com.sinovatech.unicom.basic.p314po.MenuEntity) r8
                        java.lang.String r8 = r8.getMenuTitle()
                        boolean r0 = android.text.TextUtils.isEmpty(r8)
                        if (r0 != 0) goto L53
                        java.lang.String r0 = "解除协议授权"
                        boolean r8 = r8.equals(r0)
                        if (r8 == 0) goto L53
                        r8 = 1
                        goto L54
                    L53:
                        r8 = 0
                    L54:
                        if (r8 == 0) goto L64
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        android.support.v7.app.AppCompatActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$000(r8)
                        java.lang.String r0 = "4"
                        com.sinovatech.unicom.basic.p315ui.home.util.DialogUtils.checkYinsiTiShiDialog(r8, r0)
                        goto L73
                    L64:
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        android.support.v7.app.AppCompatActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$000(r8)
                        com.sinovatech.unicom.basic.po.MenuEntity r0 = r3
                        java.lang.String r1 = "get"
                        com.sinovatech.unicom.basic.server.IntentManager.generateIntentAndGo(r8, r0, r1)
                    L73:
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        android.support.v7.app.AppCompatActivity r0 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$000(r8)
                        java.lang.String r1 = "7"
                        java.lang.String r2 = "设置"
                        java.lang.String r3 = "按钮"
                        java.lang.String r4 = ""
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ListAdapter r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.this
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.this
                        java.util.List r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.access$300(r8)
                        int r5 = r2
                        java.lang.Object r8 = r8.get(r5)
                        com.sinovatech.unicom.basic.po.MenuEntity r8 = (com.sinovatech.unicom.basic.p314po.MenuEntity) r8
                        java.lang.String r5 = r8.getMenuTitle()
                        java.lang.String r6 = ""
                        com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils.upload(r0, r1, r2, r3, r4, r5, r6)
                        com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ViewHodler r8 = r4
                        android.widget.ImageView r8 = com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ViewHodler.access$2000(r8)
                        r0 = 8
                        r8.setVisibility(r0)
                        com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation.onClickEventExit()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.SettingNewActivity.ListAdapter.View$OnClickListenerC75061.onClick(android.view.View):void");
                }
            });
            return view2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$ViewHodler */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class ViewHodler {
        private View bottomLineView;
        private ImageView imageView;
        private ImageView jiantou;
        private ImageView redPoitn;
        private TextView textView;

        private ViewHodler() {
        }

        /* synthetic */ ViewHodler(SettingNewActivity settingNewActivity, C74881 c74881) {
            this();
        }
    }

    public void setListViewHeightBasedOnChildren1() {
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493517, (ViewGroup) null);
        int i = 0;
        for (int i2 = 0; i2 < this.adapter1.getCount(); i2++) {
            this.adapter1.getItemViewType(i2);
            linearLayout.measure(0, 0);
            i += linearLayout.getMeasuredHeight();
        }
        ViewGroup.LayoutParams layoutParams = this.listView1.getLayoutParams();
        layoutParams.height = i + (this.listView1.getDividerHeight() * (this.adapter1.getCount() - 1));
        this.listView1.setLayoutParams(layoutParams);
    }

    private void getInfo(boolean z) {
        this.nickName.setText("--");
        this.managerUser.getNickName(z).subscribe(new Consumer<MyUnicomEntity>() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.13
            @Override // io.reactivex.functions.Consumer
            public void accept(MyUnicomEntity myUnicomEntity) throws Exception {
                SettingNewActivity.this.myUnicomEntity = myUnicomEntity;
                SettingNewActivity settingNewActivity = SettingNewActivity.this;
                settingNewActivity.url = settingNewActivity.myUnicomEntity.getInfoDetail();
                if (!TextUtils.isEmpty(SettingNewActivity.this.myUnicomEntity.getQianming())) {
                    SettingNewActivity.this.qianmingtv.setText(SettingNewActivity.this.myUnicomEntity.getQianming());
                }
                if (!TextUtils.isEmpty(SettingNewActivity.this.myUnicomEntity.getHeadImage())) {
                    Glide.with(App.getInstance()).load(SettingNewActivity.this.myUnicomEntity.getHeadImage()).placeholder(2131232482).error(2131232482).into(SettingNewActivity.this.photoImage);
                    SettingNewActivity.this.userManager.saveUserTouxiangURL(SettingNewActivity.this.myUnicomEntity.getHeadImage());
                }
                if (!TextUtils.isEmpty(SettingNewActivity.this.myUnicomEntity.getNickName())) {
                    SettingNewActivity.this.nickName.setText(SettingNewActivity.this.myUnicomEntity.getNickName());
                }
                SettingNewActivity.this.zhanghuLayout.setOnClickListener(SettingNewActivity.this);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.14
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        });
    }

    public void showGuideView() {
        GuideBuilder guideBuilder = new GuideBuilder();
        guideBuilder.setTargetView(this.caerToggleButton).setAlpha(150).setHighTargetGraphStyle(1);
        guideBuilder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.15
            @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
            public void onDismiss() {
            }

            @Override // com.binioter.guideview.GuideBuilder.OnVisibilityChangedListener
            public void onShown() {
            }
        });
        guideBuilder.addComponent(new SettingCareComponent());
        guideBuilder.createGuide().show(this);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    private void checkGuanHuai() {
        try {
            if (App.hasLogined()) {
                if (!UserManager.getInstance().isYiwang()) {
                    String careProvinceSwitch = this.configManager.getCareProvinceSwitch();
                    String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
                    if (!TextUtils.isEmpty(careProvinceSwitch)) {
                        if (!careProvinceSwitch.contains("098") && !careProvinceSwitch.contains(currentProvinceCode)) {
                            this.careLayout.setVisibility(8);
                        }
                        String loginType = UserManager.getInstance().getLoginType();
                        if (!"01".equals(loginType) && !"06".equals(loginType) && !"999".equals(loginType) && !"27".equals(loginType) && !"23".equals(loginType)) {
                            this.careLayout.setVisibility(8);
                        }
                        this.careLayout.setVisibility(0);
                    } else {
                        this.careLayout.setVisibility(8);
                    }
                } else {
                    this.careLayout.setVisibility(8);
                }
            } else {
                this.careLayout.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mimaguanli() {
        mimaguanliRequest(true);
        mimaguanliRequest(false);
    }

    private void mimaguanliRequest(boolean z) {
        Observable<String> rxPost;
        if (!App.hasLogined()) {
            this.mimalayout.setVisibility(8);
            return;
        }
        final String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if (z) {
            rxPost = Observable.just(CacheDataCenter.getInstance().getLoginMimaguanliData(currentPhoneNumber));
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("tokenOnline", UserManager.getInstance().getOnlineToken(currentPhoneNumber));
                jSONObject.put("seq", RecentCustomManager.uuid());
                jSONObject.put("version", this.context.getString(2131886969));
            } catch (Exception e) {
                e.printStackTrace();
            }
            rxPost = App.getAsyncHttpClient().rxPost(URLSet.getSettingMimaguanli(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
        ((ObservableSubscribeProxy) rxPost.subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).subscribeOn(Schedulers.m1934io()).map(new Function<String, MimaguanliEntity>() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.18
            @Override // io.reactivex.functions.Function
            public MimaguanliEntity apply(String str) throws Exception {
                MimaguanliEntity mimaguanliEntity = new MimaguanliEntity();
                if (TextUtils.isEmpty(str)) {
                    mimaguanliEntity.setCode("9999");
                    return mimaguanliEntity;
                }
                JSONObject jSONObject2 = new JSONObject(str);
                String optString = jSONObject2.optString("code");
                mimaguanliEntity.setCode(optString);
                if ("0000".equals(optString)) {
                    JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                    if (optJSONObject != null) {
                        mimaguanliEntity.setPasswordSwitch(optJSONObject.optString("passwordSwitch"));
                        mimaguanliEntity.setPasswordEntryHint(optJSONObject.optString("passwordEntryHint"));
                        mimaguanliEntity.setLoginHint(optJSONObject.optString("loginHint"));
                        mimaguanliEntity.setServiceHint(optJSONObject.optString("serviceHint"));
                    }
                    CacheDataCenter.getInstance().setLoginMimaguanliData(str, currentPhoneNumber);
                } else {
                    CacheDataCenter.getInstance().setLoginMimaguanliData(str, "");
                }
                return mimaguanliEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.context))).subscribe(new C749516(), new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity.17
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7979d("mimaguanli", th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.SettingNewActivity$16 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C749516 implements Consumer<MimaguanliEntity> {
        C749516() {
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(final MimaguanliEntity mimaguanliEntity) throws Exception {
            if (!TextUtils.equals(mimaguanliEntity.getCode(), "0000")) {
                SettingNewActivity.this.mimalayout.setVisibility(8);
            } else if (!TextUtils.equals(mimaguanliEntity.getPasswordSwitch(), "on")) {
                SettingNewActivity.this.mimalayout.setVisibility(8);
            } else {
                SettingNewActivity.this.mimalayout.setVisibility(0);
                if (TextUtils.isEmpty(mimaguanliEntity.getPasswordEntryHint())) {
                    SettingNewActivity.this.mimaviceView.setVisibility(8);
                } else {
                    SettingNewActivity.this.mimaviceView.setText(mimaguanliEntity.getPasswordEntryHint());
                    SettingNewActivity.this.mimaviceView.setVisibility(0);
                }
                SettingNewActivity.this.mimalayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.-$$Lambda$SettingNewActivity$16$-6x6xwQWbzZUjaydIrt96KzDKiE
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        SettingNewActivity.C749516.lambda$accept$0(SettingNewActivity.C749516.this, mimaguanliEntity, view);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$accept$0(C749516 c749516, MimaguanliEntity mimaguanliEntity, View view) {
            Intent intent = new Intent(SettingNewActivity.this.context, MimaGuanliActivity.class);
            intent.putExtra("serviceHint", mimaguanliEntity.getServiceHint());
            intent.putExtra("loginHint", mimaguanliEntity.getLoginHint());
            SettingNewActivity.this.context.startActivity(intent);
        }
    }
}
