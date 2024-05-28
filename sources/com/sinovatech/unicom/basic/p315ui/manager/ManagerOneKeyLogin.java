package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.p086v7.app.AppCompatActivity;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.p315ui.activity.OneKeyLoginActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.DeviceInfoStatistics;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerOneKeyLogin {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin$OneKeyLoginCallback */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OneKeyLoginCallback {
        void callBack();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0136 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean checkISCanOneKeyLogin(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin.checkISCanOneKeyLogin(android.content.Context):boolean");
    }

    private static boolean checkOld(Context context) {
        try {
            if (DeviceHelper.getNETType(context).equalsIgnoreCase("Wifi") || !SystemServiceUtils.netIsAvailable()) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 22) {
                return ((TelephonyManager) context.getSystemService("phone")).getSubscriberId().startsWith("46001");
            }
            for (SubscriptionInfo subscriptionInfo : SubscriptionManager.from(context).getActiveSubscriptionInfoList()) {
                if (subscriptionInfo.getMnc() == 1 || subscriptionInfo.getMnc() == 6 || subscriptionInfo.getMnc() == 9 || subscriptionInfo.getMnc() == 10) {
                    if (Build.VERSION.SDK_INT < 24) {
                        return true;
                    }
                    SubscriptionManager.from(context);
                    if (SubscriptionManager.getDefaultDataSubscriptionId() == subscriptionInfo.getSubscriptionId()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int getMobileDataState(@NotNull Context context) {
        String str;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simOperator = telephonyManager.getSimOperator();
            if (telephonyManager.getSimState() == 5) {
                if (!"46001".equals(simOperator) && !"46006".equals(simOperator) && !"46009".equals(simOperator)) {
                    if (!"46000".equals(simOperator) && !"46002".equals(simOperator) && !"46004".equals(simOperator) && !"46007".equals(simOperator)) {
                        if (!"46003".equals(simOperator) && !"46005".equals(simOperator) && !"46011".equals(simOperator)) {
                            str = "46020".equals(simOperator) ? "中国铁通" : "OHTER";
                            MsLogUtil.m7979d("getMobileDataState", "上网的卡---" + str);
                        }
                        str = "中国电信";
                        MsLogUtil.m7979d("getMobileDataState", "上网的卡---" + str);
                    }
                    str = "中国移动";
                    MsLogUtil.m7979d("getMobileDataState", "上网的卡---" + str);
                }
                str = "中国联通";
                MsLogUtil.m7979d("getMobileDataState", "上网的卡---" + str);
            }
            if (telephonyManager == null) {
                MsLogUtil.m7979d("getMobileDataState", "----1");
                return -1;
            }
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod == null) {
                MsLogUtil.m7979d("getMobileDataState", "----1");
                return -1;
            }
            MsLogUtil.m7979d("getMobileDataState", "---" + ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])));
            return !((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static void onekeyLogin(AppCompatActivity appCompatActivity, String str, String str2, OneKeyLoginCallback oneKeyLoginCallback) {
        UserManager userManager = UserManager.getInstance();
        CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(appCompatActivity);
        customePorgressDialog.setMessage("正在登录 请稍候");
        customePorgressDialog.setCanceledOnTouchOutside(false);
        customePorgressDialog.setCancelable(false);
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", appCompatActivity.getString(2131886969));
        requestParams.put("desmobile", userManager.getPassBackDesmobile());
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody("0", requestParams.getRealParams());
        App.getAsyncHttpClient().post(URLSet.getLogoutURL(), headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), new C78131(customePorgressDialog, userManager, str, appCompatActivity, str2, oneKeyLoginCallback));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78131 extends AsyncHttpResponseHandler {
        final /* synthetic */ OneKeyLoginCallback val$callback;
        final /* synthetic */ AppCompatActivity val$context;
        final /* synthetic */ CustomePorgressDialog val$pd;
        final /* synthetic */ String val$phone;
        final /* synthetic */ String val$urlInfo;
        final /* synthetic */ UserManager val$userManager;

        C78131(CustomePorgressDialog customePorgressDialog, UserManager userManager, String str, AppCompatActivity appCompatActivity, String str2, OneKeyLoginCallback oneKeyLoginCallback) {
            this.val$pd = customePorgressDialog;
            this.val$userManager = userManager;
            this.val$phone = str;
            this.val$context = appCompatActivity;
            this.val$urlInfo = str2;
            this.val$callback = oneKeyLoginCallback;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            super.onStart();
            this.val$pd.show();
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            super.onFinish();
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
            App.setLogined(LoginStateConst.UNLOGIN);
            this.val$userManager.saveAutoLoginStatus(true);
            final HashMap hashMap = new HashMap();
            hashMap.put("mobile", this.val$phone);
            hashMap.put("appId", this.val$userManager.getLoginAppId());
            hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
            hashMap.put("version", this.val$context.getString(2131886969));
            hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
            hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
            hashMap.put(this.val$context.getString(2131886495), DeviceHelper.getAndroidId());
            hashMap.put("netWay", DeviceHelper.getNETType(this.val$context));
            hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
            hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
            hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
            hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
                try {
                    hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                    hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                hashMap.put("provinceChanel", this.val$context.getPackageManager().getApplicationInfo(this.val$context.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
                hashMap.put("resultToken", LoginFilterUtil.resultToken);
            }
            hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
            App.getAsyncHttpClient().rxPost(URLSet.getOneKeyLogin(), hashMap, 1, 1000).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.1.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    if (C78131.this.val$pd.isShowing()) {
                        return;
                    }
                    C78131.this.val$pd.show();
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    String str2;
                    try {
                        if (C78131.this.val$pd != null && C78131.this.val$pd.isShowing()) {
                            C78131.this.val$pd.dismiss();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        App.setLogined(LoginStateConst.UNLOGIN);
                        if (C78131.this.val$pd != null && C78131.this.val$pd.isShowing()) {
                            C78131.this.val$pd.dismiss();
                        }
                        LoginManager.showLoginErrorMessage(C78131.this.val$context, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】\n接口返状态码=200，数据处理有问题，需查询服务器日志定位问题！", false);
                    }
                    if (LoginFilterUtil.filerLogin(C78131.this.val$context, str, new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.1.1.1
                        @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                        public void complete(String str3) {
                            hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                            hashMap.put("resultToken", str3);
                            ManagerOneKeyLogin.onekeyLogin(C78131.this.val$context, C78131.this.val$phone, C78131.this.val$urlInfo, C78131.this.val$callback);
                        }
                    })) {
                        return;
                    }
                    HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse("", str, "Login_Type");
                    String str3 = handleLoginResponse.get("ok");
                    if (str3 != null && "ok".equals(str3)) {
                        String userAccountName = C78131.this.val$userManager.getUserAccountName();
                        App.setLogined(LoginStateConst.DID_LOGIN);
                        App.reLoadDefaultMenuData = true;
                        App.reLoadAdvertise = true;
                        WebFragment.isRelogin = true;
                        App.getSharePreferenceUtil().putString(SharePreferenceUtil.FILE_LoginInfo, "latestLoginTime_" + userAccountName, new SimpleDateFormat("yyyyMM").format(new Date()));
                        try {
                            UnicomPayUtils.getInstance(C78131.this.val$context).loginPaySdk();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        String str4 = handleLoginResponse.get("description");
                        if (!TextUtils.isEmpty(str4)) {
                            UIUtils.toastLong(str4);
                        }
                        C78131.this.val$userManager.saveSelectAccountName(userAccountName, C78131.this.val$userManager.getUserAreaid(), "999", "999", C78131.this.val$userManager.getKeyVersion(), "1", C78131.this.val$userManager.getUserTouxiangURL());
                        if (C78131.this.val$pd != null && C78131.this.val$pd.isShowing()) {
                            C78131.this.val$pd.dismiss();
                        }
                        if (TextUtils.isEmpty(C78131.this.val$urlInfo)) {
                            C78131.this.val$context.finish();
                        } else {
                            C78131.this.val$callback.callBack();
                        }
                        DeviceInfoStatistics.uploadTianYuan("1", "01", "01", userAccountName);
                    } else {
                        C78131.this.val$userManager.removeUserTouxiangURL();
                        App.setLogined(LoginStateConst.UNLOGIN);
                        if (C78131.this.val$pd != null && C78131.this.val$pd.isShowing()) {
                            C78131.this.val$pd.dismiss();
                        }
                        String str5 = TextUtils.isEmpty(handleLoginResponse.get("errorCode")) ? "SERVER CODE IS NULL" : handleLoginResponse.get("errorCode");
                        if (TextUtils.isEmpty(handleLoginResponse.get("description"))) {
                            str2 = "很抱歉，暂时无法使用，请您稍候再试(code=" + str5 + ")";
                        } else {
                            str2 = handleLoginResponse.get("description");
                        }
                        String str6 = str2;
                        Log.d("一键登录报错", "onNext: " + handleLoginResponse.get("description"));
                        if ("ECS10002".equals(str5)) {
                            CustomDialogManager.show(SoulPermission.getInstance().getTopActivity(), "号码已注销", str6, false, "", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.1.1.2
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
                                    HashMap hashMap2 = new HashMap();
                                    hashMap2.put("type", "01");
                                    IntentManager.gotoWebViewActivityWithParams(C78131.this.val$context, URLSet.getFreezeHtml(), "", hashMap2);
                                }
                            });
                        } else {
                            CustomDialogManager.show(C78131.this.val$context, "", str6, "取消", "确认", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.1.1.3
                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                                public void onClickOk() {
                                    if (TextUtils.isEmpty(C78131.this.val$urlInfo)) {
                                        C78131.this.val$context.finish();
                                    }
                                }
                            });
                        }
                    }
                    EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    Log.d("一键登录error", "onError: " + th.getMessage());
                    LoginManager.showLoginNetWorkErrorMessage(C78131.this.val$context, th, true);
                    if (C78131.this.val$pd == null || !C78131.this.val$pd.isShowing()) {
                        return;
                    }
                    C78131.this.val$pd.dismiss();
                }
            });
        }
    }

    public static void onekeyLogin(AppCompatActivity appCompatActivity, String str, OneKeyLoginCallback oneKeyLoginCallback) {
        UserManager userManager = UserManager.getInstance();
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", appCompatActivity.getString(2131886969));
        requestParams.put("desmobile", userManager.getPassBackDesmobile());
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        App.getAsyncHttpClient().post(URLSet.getLogoutURL(), requestParams, new C78182(userManager, str, appCompatActivity, oneKeyLoginCallback));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78182 extends AsyncHttpResponseHandler {
        final /* synthetic */ OneKeyLoginCallback val$callback;
        final /* synthetic */ AppCompatActivity val$context;
        final /* synthetic */ String val$phone;
        final /* synthetic */ UserManager val$userManager;

        C78182(UserManager userManager, String str, AppCompatActivity appCompatActivity, OneKeyLoginCallback oneKeyLoginCallback) {
            this.val$userManager = userManager;
            this.val$phone = str;
            this.val$context = appCompatActivity;
            this.val$callback = oneKeyLoginCallback;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            super.onStart();
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            super.onFinish();
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
            App.setLogined(LoginStateConst.UNLOGIN);
            this.val$userManager.saveAutoLoginStatus(true);
            final HashMap hashMap = new HashMap();
            hashMap.put("mobile", this.val$phone);
            hashMap.put("appId", this.val$userManager.getLoginAppId());
            hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
            hashMap.put("version", this.val$context.getString(2131886969));
            hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
            hashMap.put("deviceCode", DeviceHelper.getDeviceID(true));
            hashMap.put("netWay", DeviceHelper.getNETType(this.val$context));
            hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
            hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
            hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
            hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
                try {
                    hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                    hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                hashMap.put("provinceChanel", this.val$context.getPackageManager().getApplicationInfo(this.val$context.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!TextUtils.isEmpty(LoginFilterUtil.resultToken)) {
                hashMap.put("resultToken", LoginFilterUtil.resultToken);
            }
            hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
            App.getAsyncHttpClient().rxPost(URLSet.getOneKeyLogin(), hashMap, 1, 1000).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.2.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String str) {
                    HashMap<String, String> handleLoginResponse;
                    try {
                        handleLoginResponse = LoginManager.handleLoginResponse("", str, "Login_Type");
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        App.setLogined(LoginStateConst.UNLOGIN);
                    }
                    if (LoginFilterUtil.filerLogin(C78182.this.val$context, str, new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.2.1.1
                        @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                        public void complete(String str2) {
                            hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                            hashMap.put("resultToken", str2);
                            ManagerOneKeyLogin.onekeyLogin(C78182.this.val$context, C78182.this.val$phone, C78182.this.val$callback);
                        }
                    })) {
                        return;
                    }
                    String str2 = handleLoginResponse.get("ok");
                    if (str2 != null && "ok".equals(str2)) {
                        String userAccountName = C78182.this.val$userManager.getUserAccountName();
                        App.setLogined(LoginStateConst.DID_LOGIN);
                        App.reLoadDefaultMenuData = true;
                        App.reLoadAdvertise = true;
                        WebFragment.isRelogin = true;
                        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                        sharePreferenceUtil.putString(SharePreferenceUtil.FILE_LoginInfo, "latestLoginTime_" + userAccountName, new SimpleDateFormat("yyyyMM").format(new Date()));
                        String str3 = handleLoginResponse.get("description");
                        if (!TextUtils.isEmpty(str3)) {
                            UIUtils.toastLong(str3);
                        }
                        C78182.this.val$userManager.saveSelectAccountName(userAccountName, C78182.this.val$userManager.getUserAreaid(), "999", "999", C78182.this.val$userManager.getKeyVersion(), "1", C78182.this.val$userManager.getUserTouxiangURL());
                    }
                    EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                    C78182.this.val$callback.callBack();
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    LoginManager.showLoginNetWorkErrorMessage(C78182.this.val$context, th, true);
                    C78182.this.val$callback.callBack();
                }
            });
        }
    }

    public static void mianmiSDKAccessCode(final AppCompatActivity appCompatActivity, final ProgressDialog progressDialog) {
        try {
            progressDialog.setMessage("正在登录 请稍候");
            progressDialog.show();
            new OneKeyLoginUtil().start(new OneKeyLoginUtil.OnekeyLoginCallBack() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.3
                @Override // com.sinovatech.unicom.separatemodule.onekeylogin.OneKeyLoginUtil.OnekeyLoginCallBack
                public void onComplete(OneKeyLoginUtil.MianMiLoginEntity mianMiLoginEntity) {
                    progressDialog.cancel();
                    try {
                        if (mianMiLoginEntity.getCode() == 0) {
                            Intent intent = new Intent(appCompatActivity, OneKeyLoginActivity.class);
                            intent.putExtra("accessCode", mianMiLoginEntity.getAccessCode());
                            intent.putExtra("fakeMobile", mianMiLoginEntity.getFakeMobile());
                            new AvoidOnResult(appCompatActivity).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.3.1
                                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                public void onActivityResult(int i, Intent intent2) {
                                    if (App.hasLogined()) {
                                        appCompatActivity.setResult(-1);
                                        appCompatActivity.finish();
                                    }
                                }
                            });
                        } else {
                            AppCompatActivity appCompatActivity2 = appCompatActivity;
                            CustomDialogManager.show(appCompatActivity2, "温馨提示", mianMiLoginEntity.getMsg() + mianMiLoginEntity.getCode());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomDialogManager.show(appCompatActivity, "温馨提示", "未登录成功 请重试");
                    }
                }
            });
            TYCJBoxManager.getInstance().collectClickSdk(appCompatActivity, "S2ndpage1214", "登录", "联通免密取号", "", "com.sdk.cp", "1");
        } catch (Exception e) {
            e.printStackTrace();
            progressDialog.cancel();
        }
    }

    public static void mianmiSDKLogin(final AppCompatActivity appCompatActivity, String str, final ProgressDialog progressDialog) {
        progressDialog.setMessage("正在登录 请稍候");
        progressDialog.show();
        HashMap hashMap = new HashMap();
        hashMap.put("imei", DeviceHelper.getDeviceID(true));
        hashMap.put("pip", SystemServiceUtils.getIPAddress(appCompatActivity));
        hashMap.put("reqTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        hashMap.put("accessCode", str);
        hashMap.put("appVersion", "anroid");
        App.getAsyncHttpClient().rxGet(URLSet.getMianmiSDKNumber(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.4
            @Override // io.reactivex.functions.Consumer
            public void accept(String str2) throws Exception {
                MsLogUtil.m7980d("免密登录>>>成功：" + str2);
                progressDialog.cancel();
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.getString("rspCode").equals("0000")) {
                    ManagerOneKeyLogin.onekeyLogin(appCompatActivity, jSONObject.getString("data"), "", new OneKeyLoginCallback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.4.1
                        @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin.OneKeyLoginCallback
                        public void callBack() {
                        }
                    });
                    return;
                }
                CustomDialogManager.show(appCompatActivity, "温馨提示", jSONObject.optString("rspDesc", "取号失败 请重试"));
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerOneKeyLogin.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7980d("免密登录>>>失败：" + th.getMessage());
                progressDialog.cancel();
                CustomDialogManager.show(appCompatActivity, "温馨提示", "未登录成功 请重试");
            }
        });
    }
}
