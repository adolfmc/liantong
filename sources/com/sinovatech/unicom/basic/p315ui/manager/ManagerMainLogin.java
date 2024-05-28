package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.location.BDLocation;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.sinovatech.unicom.basic.eventbus.FragmentOnResumeEvent;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.eventbus.WelcomLoginEvent;
import com.sinovatech.unicom.basic.p315ui.activity.CustomMainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment;
import com.sinovatech.unicom.basic.p315ui.fragment.UnicomHomeFragment;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.DeviceInfoStatistics;
import com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.esim.ESIMLoginActivity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerMainLogin {
    private static ManagerMainLogin managerMainLogin;
    private Disposable onlineTokenDisposable;
    private Disposable timeSubscribe;
    private int refreshTime = 30000;
    private int logCount = 0;
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin$LoginCompleteInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LoginCompleteInterface {
        void complete();
    }

    public static synchronized ManagerMainLogin getManagerMainLogin() {
        ManagerMainLogin managerMainLogin2;
        synchronized (ManagerMainLogin.class) {
            if (managerMainLogin == null) {
                synchronized (ManagerMainLogin.class) {
                    if (managerMainLogin == null) {
                        managerMainLogin = new ManagerMainLogin();
                    }
                }
            }
            managerMainLogin2 = managerMainLogin;
        }
        return managerMainLogin2;
    }

    private ManagerMainLogin() {
    }

    public void loginStart() {
        if (TextUtils.isEmpty(this.userManager.getUserAccountName()) || TextUtils.isEmpty(this.userManager.getUserPassword()) || TextUtils.isEmpty(this.userManager.getKeyVersion())) {
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
        }
    }

    public void refreshOnlineToken(final Activity activity) {
        App.refreshbackisRequest = false;
        stopRefreshOnlineToken();
        int i = this.refreshTime;
        Observable.interval(i, i, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                ManagerMainLogin.this.onlineTokenDisposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                if (App.hasLogined()) {
                    ManagerMainLogin.this.refreshOnlindata(activity);
                }
            }
        });
    }

    public void refreshOnlindata(Activity activity) {
        long j;
        BDLocation bdLocation;
        if (App.refreshbackisRequest) {
            return;
        }
        EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_REFRESH_WEBSOCKET));
        String onlineToken = UserManager.getInstance().getOnlineToken(this.userManager.getCurrentPhoneNumber());
        if (!App.hasLogined() || TextUtils.isEmpty(onlineToken)) {
            return;
        }
        UserManager userManager = this.userManager;
        try {
            j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userManager.getInvalidat(userManager.getCurrentPhoneNumber())).getTime() - System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            j = 0;
        }
        if (j > 0) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("token_online", onlineToken);
        hashMap.put("version", activity.getString(2131886969));
        hashMap.put("reqtime", System.currentTimeMillis() + "");
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put(activity.getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("netWay", DeviceHelper.getNETType(activity.getApplicationContext()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("step", "dingshi");
        ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
        String str = "";
        String str2 = "";
        if (locationEntity.getBdLocation() != null && locationEntity.isLocationSuccess()) {
            str = bdLocation.getLatitude() + "";
            str2 = bdLocation.getLongitude() + "";
        }
        hashMap.put("latitude", str);
        hashMap.put("longitude", str2);
        try {
            hashMap.put("provinceChanel", activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (DeviceHelper.isHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
            hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
        }
        App.refreshbackisRequest = true;
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.2
            @Override // java.lang.Runnable
            public void run() {
                App.refreshbackisRequest = false;
            }
        }, 10000L);
        refreshOnlindataParams(activity, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshOnlindataParams(final Activity activity, final Map<String, String> map) {
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            map.put("resultToken", LoginFilterUtil.resultToken);
        }
        map.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        LoginParamsEntity onlineHeaderAndBody = DongtaiMiyaoUtils.getOnlineHeaderAndBody(this.userManager.getCurrentPhoneNumber(), map, ESIMLoginActivity.ESIMTYPE.equals(this.userManager.getLoginType()));
        App.getAsyncHttpClient().rxPost(URLSet.getOnline(), onlineHeaderAndBody.getBodyMap(), onlineHeaderAndBody.getHeaderMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.5
            @Override // io.reactivex.functions.Function
            public HashMap<String, String> apply(String str) throws Exception {
                UIUtils.logD("getAsyncHttpClient", str);
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("desmobile");
                String optString2 = jSONObject.optString("default");
                String optString3 = jSONObject.optString("code");
                String optString4 = jSONObject.optString("dsc");
                if (optString.equals(ManagerMainLogin.this.userManager.getCurrentPhoneNumber()) || optString2.equals(ManagerMainLogin.this.userManager.getCurrentPhoneNumber()) || "ECS99999".equals(optString3)) {
                    return LoginManager.handleLoginResponse(ManagerMainLogin.this.userManager.getUserAccountName(), str, "OnlineToken_Type");
                }
                HashMap<String, String> hashMap = new HashMap<>();
                if ("1".equals(optString3) || "2".equals(optString3) || "ECS10002".equals(optString3)) {
                    hashMap.put("ok", "error");
                    hashMap.put("errorCode", optString3);
                    hashMap.put("description", optString4);
                } else {
                    hashMap.put("ok", "ok");
                }
                return hashMap;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.3
            @Override // io.reactivex.functions.Consumer
            public void accept(final HashMap<String, String> hashMap) {
                if ("ECS99999".equals(hashMap.get("errorCode")) && LoginFilterUtil.filerLogin(activity, hashMap.get("content"), new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.3.1
                    @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                    public void complete(String str) {
                        map.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                        map.put("resultToken", str);
                        ManagerMainLogin.this.refreshOnlindataParams(activity, map);
                    }
                })) {
                    return;
                }
                App.refreshbackisRequest = false;
                if ("ok".equals(hashMap.get("ok"))) {
                    return;
                }
                if ("1".equals(hashMap.get("errorCode"))) {
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("version", activity.getString(2131886969));
                    requestParams.put("desmobile", ManagerMainLogin.this.userManager.getPassBackDesmobile());
                    requestParams.put("token_online", ManagerMainLogin.this.userManager.getOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber()));
                    App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.3.2
                        @Override // com.loopj.android.http.AsyncHttpResponseHandler
                        public void onStart() {
                            super.onStart();
                        }

                        @Override // com.loopj.android.http.AsyncHttpResponseHandler
                        public void onFinish() {
                            super.onFinish();
                            App.clearPersistentCookiesList();
                            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
                            ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                            LoginManager.logout(activity);
                            App.setLogined(LoginStateConst.UNLOGIN);
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra("needDialog", "1");
                            intent.putExtra("errorCode", "1");
                            intent.putExtra("desc", (String) hashMap.get("description"));
                            activity.startActivity(intent);
                        }
                    });
                } else if ("2".equals(hashMap.get("errorCode"))) {
                    ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                    LoginManager.logout(activity);
                    App.setLogined(LoginStateConst.UNLOGIN);
                    Intent intent = new Intent(activity, MainActivity.class);
                    intent.putExtra("needDialog", "1");
                    intent.putExtra("errorCode", "2");
                    intent.putExtra("desc", hashMap.get("description"));
                    activity.startActivity(intent);
                } else if ("ECS10002".equals(hashMap.get("errorCode"))) {
                    ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                    LoginManager.logout(activity);
                    UnicomHomeFragment.currentPhone = "";
                    ServiceFragment.currentPhone = "0";
                    UserFragment.currentPhone = "0";
                    App.setLogined(LoginStateConst.UNLOGIN);
                    UserManager.getInstance().removeSelectAccountName(UserManager.getInstance().getUserAccountName());
                    UIUtils.toastCenterLong(hashMap.get("description"));
                }
                ManagerMainLogin.this.onResumeFragment();
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                App.refreshbackisRequest = false;
                LogFileUploadUtils.writeLoginErrorLog(activity, ManagerMainLogin.this.userManager.getCurrentPhoneNumber(), "后台刷新token", "已经登录", th.getMessage(), th);
                LogFileUploadUtils.upload(activity);
            }
        });
    }

    public void stopRefreshOnlineToken() {
        Disposable disposable = this.onlineTokenDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.onlineTokenDisposable.dispose();
    }

    public void onResumeFragment() {
        try {
            FragmentOnResumeEvent fragmentOnResumeEvent = new FragmentOnResumeEvent(EventBusUtils.EVENT_MEMBER_INFO);
            fragmentOnResumeEvent.setType(FragmentOnResumeEvent.HOME);
            EventBusUtils.post(fragmentOnResumeEvent);
            FragmentOnResumeEvent fragmentOnResumeEvent2 = new FragmentOnResumeEvent(EventBusUtils.EVENT_MEMBER_INFO);
            fragmentOnResumeEvent2.setType(FragmentOnResumeEvent.HOME_TJ);
            EventBusUtils.post(fragmentOnResumeEvent2);
            FragmentOnResumeEvent fragmentOnResumeEvent3 = new FragmentOnResumeEvent(EventBusUtils.EVENT_MEMBER_INFO);
            fragmentOnResumeEvent3.setType(FragmentOnResumeEvent.USER);
            EventBusUtils.post(fragmentOnResumeEvent3);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void welcomRefreshToken(final Activity activity, final String str, final LoginCompleteInterface loginCompleteInterface) {
        BDLocation bdLocation;
        final String currentPhoneNumber = this.userManager.getCurrentPhoneNumber();
        final String onlineToken = UserManager.getInstance().getOnlineToken(currentPhoneNumber);
        if (!App.hasLogined() || TextUtils.isEmpty(onlineToken)) {
            loginCompleteInterface.complete();
            return;
        }
        final HashMap hashMap = new HashMap();
        hashMap.put("token_online", onlineToken);
        hashMap.put("version", activity.getString(2131886969));
        hashMap.put("reqtime", System.currentTimeMillis() + "");
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
        hashMap.put(activity.getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("netWay", DeviceHelper.getNETType(activity.getApplicationContext()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("flushkey", str);
        if ("2".equals(str)) {
            hashMap.put("step", "background");
            ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
            String str2 = "";
            String str3 = "";
            if (locationEntity.getBdLocation() != null && locationEntity.isLocationSuccess()) {
                str2 = bdLocation.getLatitude() + "";
                str3 = bdLocation.getLongitude() + "";
            }
            hashMap.put("latitude", str2);
            hashMap.put("longitude", str3);
            if (App.refreshbackisRequest) {
                return;
            }
            MsLogUtil.m7979d("MyLocationListenner", "latitude：" + str2 + "    longitude：" + str3);
        } else {
            hashMap.put("step", "welcom");
        }
        if (DeviceHelper.isHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
            hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
        }
        try {
            hashMap.put("provinceChanel", activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e) {
            e.printStackTrace();
            loginCompleteInterface.complete();
        }
        if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
            hashMap.put("resultToken", LoginFilterUtil.resultToken);
        }
        hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        LoginParamsEntity onlineHeaderAndBody = DongtaiMiyaoUtils.getOnlineHeaderAndBody(this.userManager.getCurrentPhoneNumber(), hashMap, ESIMLoginActivity.ESIMTYPE.equals(this.userManager.getLoginType()));
        App.getAsyncHttpClient(5, 5, 5, 5).rxPost(URLSet.getOnline(), onlineHeaderAndBody.getBodyMap(), onlineHeaderAndBody.getHeaderMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.8
            @Override // io.reactivex.functions.Function
            public HashMap<String, String> apply(String str4) throws Exception {
                return LoginManager.handleLoginResponse(ManagerMainLogin.this.userManager.getUserAccountName(), str4, "Login_Type");
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6
            @Override // io.reactivex.functions.Consumer
            public void accept(final HashMap<String, String> hashMap2) {
                if ("1".equals(str)) {
                    if ("1".equals(hashMap2.get("errorCode")) || "2".equals(hashMap2.get("errorCode")) || "ECS10002".equals(hashMap2.get("errorCode"))) {
                        if ("1".equals(hashMap2.get("errorCode"))) {
                            RequestParams requestParams = new RequestParams();
                            requestParams.put("version", activity.getString(2131886969));
                            requestParams.put("desmobile", currentPhoneNumber);
                            requestParams.put("token_online", onlineToken);
                            App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6.1
                                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                                public void onStart() {
                                    super.onStart();
                                }

                                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                                public void onFinish() {
                                    super.onFinish();
                                }
                            });
                        }
                        ManagerMainLogin.this.userManager.removeOnlineToken(currentPhoneNumber);
                        LoginManager.logout(activity);
                    }
                    EventBusUtils.postSticky(new WelcomLoginEvent(0, hashMap2));
                    loginCompleteInterface.complete();
                } else if ("2".equals(str) && "ECS99999".equals(hashMap2.get("errorCode")) && LoginFilterUtil.filerLogin(activity, hashMap2.get("content"), new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6.2
                    @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                    public void complete(String str4) {
                        hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                        hashMap.put("resultToken", str4);
                        ManagerMainLogin.this.refreshOnlindataParams(activity, hashMap);
                    }
                })) {
                } else {
                    if ("2".equals(str) && "error".equals(hashMap2.get("ok"))) {
                        if (CustomMainActivity.isCustomMain()) {
                            CustomDialogManager.show(activity, "温馨提示", hashMap2.get("description"), false, "", "返回普通版", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6.3
                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                                public void onClickOk() {
                                    CustomMainActivity.goMainActivity(activity);
                                    ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                                    LoginManager.logout(activity);
                                    App.setLogined(LoginStateConst.UNLOGIN);
                                    loginCompleteInterface.complete();
                                }
                            });
                            return;
                        }
                        if ("1".equals(hashMap2.get("errorCode"))) {
                            RequestParams requestParams2 = new RequestParams();
                            requestParams2.put("version", activity.getString(2131886969));
                            requestParams2.put("desmobile", ManagerMainLogin.this.userManager.getPassBackDesmobile());
                            requestParams2.put("token_online", ManagerMainLogin.this.userManager.getOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber()));
                            ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                            LoginManager.logout(activity);
                            App.setLogined(LoginStateConst.UNLOGIN);
                            App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams2, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6.4
                                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                                public void onStart() {
                                    super.onStart();
                                }

                                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                                public void onFinish() {
                                    super.onFinish();
                                    Intent intent = new Intent(activity, MainActivity.class);
                                    intent.putExtra("needDialog", "1");
                                    intent.putExtra("errorCode", "1");
                                    intent.putExtra("desc", (String) hashMap2.get("description"));
                                    activity.startActivity(intent);
                                }
                            });
                        } else if ("2".equals(hashMap2.get("errorCode"))) {
                            ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                            LoginManager.logout(activity);
                            App.setLogined(LoginStateConst.UNLOGIN);
                            Intent intent = new Intent(activity, MainActivity.class);
                            intent.putExtra("needDialog", "1");
                            intent.putExtra("errorCode", "2");
                            intent.putExtra("desc", hashMap2.get("description"));
                            activity.startActivity(intent);
                        } else if ("ECS10002".equals(hashMap2.get("errorCode"))) {
                            ManagerMainLogin.this.userManager.removeOnlineToken(ManagerMainLogin.this.userManager.getCurrentPhoneNumber());
                            LoginManager.logout(activity);
                            UnicomHomeFragment.currentPhone = "";
                            ServiceFragment.currentPhone = "0";
                            UserFragment.currentPhone = "";
                            App.setLogined(LoginStateConst.UNLOGIN);
                            UserManager.getInstance().removeSelectAccountName(UserManager.getInstance().getUserAccountName());
                            UIUtils.toastCenterLong(hashMap2.get("description"));
                            Activity activity2 = activity;
                            activity2.startActivity(new Intent(activity2, LoginBindActivity.class));
                        } else {
                            loginCompleteInterface.complete();
                        }
                        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.6.5
                            @Override // java.lang.Runnable
                            public void run() {
                                DeviceInfoStatistics.uploadTianYuan("2", "01", "02");
                            }
                        }, 10000L);
                        return;
                    }
                    loginCompleteInterface.complete();
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                loginCompleteInterface.complete();
            }
        });
    }

    public void handlerLoginResult(Activity activity, HashMap<String, String> hashMap) {
        if ("ECS99999".equals(hashMap.get("errorCode"))) {
            welcomRefreshToken(activity, "2", new LoginCompleteInterface() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.9
                @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin.LoginCompleteInterface
                public void complete() {
                }
            });
        } else if ("ok".equals(hashMap.get("ok"))) {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$ManagerMainLogin$E1jPfOkCI_ytjTLUwne3bPnBKcs
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceInfoStatistics.uploadTianYuan("2", "01", "01");
                }
            }, 10000L);
        } else {
            if ("1".equals(hashMap.get("errorCode"))) {
                Intent intent = new Intent(activity, LoginBindActivity.class);
                intent.putExtra("needDialog", "1");
                intent.putExtra("desc", hashMap.get("description"));
                activity.startActivity(intent);
            } else if ("2".equals(hashMap.get("errorCode"))) {
                if (!TextUtils.isEmpty(hashMap.get("description"))) {
                    CustomDialogManager.show(activity, "温馨提示", hashMap.get("description"));
                }
            } else if ("ECS10002".equals(hashMap.get("errorCode"))) {
                UserManager.getInstance().removeSelectAccountName(UserManager.getInstance().getUserAccountName());
                UIUtils.toastCenterLong(hashMap.get("description"));
            }
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerMainLogin.10
                @Override // java.lang.Runnable
                public void run() {
                    DeviceInfoStatistics.uploadTianYuan("2", "01", "02");
                }
            }, 10000L);
        }
    }
}
