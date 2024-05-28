package com.sinovatech.unicom.basic.p315ui.manager;

import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import cn.finalteam.galleryfinal.utils.LoginConstUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.eventbus.WeixinEvent;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WeixinBindActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerWeixinBind {
    public static void checkIsBind(LoginActivity loginActivity, WeixinEvent weixinEvent) {
        UserManager.getInstance().getPassBackDesmobile();
        try {
            CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(loginActivity);
            customePorgressDialog.setCancelable(false);
            customePorgressDialog.setCanceledOnTouchOutside(false);
            RequestParams requestParams = new RequestParams();
            requestParams.put("version", loginActivity.getString(2131886969));
            requestParams.put("desmobile", UserManager.getInstance().getPassBackDesmobile());
            requestParams.put("token_online", UserManager.getInstance().getOnlineToken(UserManager.getInstance().getCurrentPhoneNumber()));
            App.getAsyncHttpClient().post(URLSet.getLogoutURL(), requestParams, new C78311(customePorgressDialog, loginActivity, weixinEvent));
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("检查是否已经绑定微信错误【" + e.getMessage() + "】");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C78311 extends AsyncHttpResponseHandler {
        final /* synthetic */ LoginActivity val$activityConntext;
        final /* synthetic */ CustomePorgressDialog val$pd;
        final /* synthetic */ WeixinEvent val$weixinEvent;

        C78311(CustomePorgressDialog customePorgressDialog, LoginActivity loginActivity, WeixinEvent weixinEvent) {
            this.val$pd = customePorgressDialog;
            this.val$activityConntext = loginActivity;
            this.val$weixinEvent = weixinEvent;
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onStart() {
            super.onStart();
            CustomePorgressDialog customePorgressDialog = this.val$pd;
            if (customePorgressDialog != null) {
                customePorgressDialog.setMessage("正在登录 请稍候");
            }
            try {
                if (this.val$activityConntext == null || this.val$activityConntext.isDestroyed() || this.val$activityConntext.isFinishing() || this.val$pd == null || this.val$pd.isShowing()) {
                    return;
                }
                this.val$pd.show();
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }

        @Override // com.loopj.android.http.AsyncHttpResponseHandler
        public void onFinish() {
            super.onFinish();
            try {
                if (this.val$activityConntext != null && !this.val$activityConntext.isDestroyed() && !this.val$activityConntext.isFinishing() && this.val$pd != null && this.val$pd.isShowing()) {
                    this.val$pd.dismiss();
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
            App.setLogined(LoginStateConst.UNLOGIN);
            UserManager.getInstance().saveAutoLoginStatus(true);
            RequestParams requestParams = new RequestParams();
            requestParams.put("wx_code", this.val$weixinEvent.getData().toString());
            requestParams.put("appId", UserManager.getInstance().getLoginAppId());
            requestParams.put("pip", SystemServiceUtils.getLocalIpAddress());
            requestParams.put("isRemberPwd", String.valueOf(UserManager.getInstance().getAutoLoginStatus()));
            requestParams.put("keyVersion", UserManager.getInstance().getKeyVersion());
            requestParams.put("version", this.val$activityConntext.getString(2131886969));
            requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
            requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
            requestParams.put("netWay", DeviceHelper.getNETType(this.val$activityConntext));
            requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
            requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
            requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
            requestParams.put(this.val$activityConntext.getString(2131886495), DeviceHelper.getAndroidId());
            requestParams.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            try {
                requestParams.put("provinceChanel", this.val$activityConntext.getPackageManager().getApplicationInfo(this.val$activityConntext.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
            } catch (Exception unused) {
            }
            if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
                try {
                    requestParams.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                    requestParams.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
                } catch (Exception unused2) {
                }
            }
            requestParams.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
            App.getAsyncHttpClient().post(URLSet.getWeixinCheckBind(), requestParams, new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind.1.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    App.setLogined(LoginStateConst.DOING_NORMAL_LOGIN);
                    if (C78311.this.val$pd != null) {
                        C78311.this.val$pd.setMessage("正在登录 请稍候");
                    }
                    try {
                        if (C78311.this.val$activityConntext == null || C78311.this.val$activityConntext.isDestroyed() || C78311.this.val$activityConntext.isFinishing() || C78311.this.val$pd == null || C78311.this.val$pd.isShowing()) {
                            return;
                        }
                        C78311.this.val$pd.show();
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    String str2;
                    super.onSuccess(i, str);
                    try {
                        if (C78311.this.val$activityConntext != null && !C78311.this.val$activityConntext.isDestroyed() && !C78311.this.val$activityConntext.isFinishing() && C78311.this.val$pd != null && C78311.this.val$pd.isShowing()) {
                            C78311.this.val$pd.dismiss();
                        }
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    try {
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        App.setLogined(LoginStateConst.UNLOGIN);
                        if (!App.isSuccessful(i)) {
                            LoginManager.showLoginErrorMessage(C78311.this.val$activityConntext, "未登录成功，请重试【ECS" + i + "】", true, "未登录成功，请重试【ECS" + i + "】\n接口状态码=" + i, false);
                        } else {
                            LoginManager.showLoginErrorMessage(C78311.this.val$activityConntext, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】\n接口返状态码=200，数据处理有问题，需查询服务器日志定位问题！", false);
                        }
                        LogFileUploadUtils.writeLoginErrorLog(C78311.this.val$activityConntext, C78311.this.val$weixinEvent.getData().toString(), "微信登录", "", str, e3);
                    }
                    if (App.isSuccessful(i)) {
                        Log.d("CESHI", "微信检查绑定接口返回：" + str);
                        JSONObject jSONObject = new JSONObject(str);
                        if ("22".equals(jSONObject.getString("code"))) {
                            String string = jSONObject.getString("wx_code");
                            Intent intent = new Intent(C78311.this.val$activityConntext, WeixinBindActivity.class);
                            intent.putExtra("wx_code", string);
                            new AvoidOnResult(C78311.this.val$activityConntext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind.1.1.1
                                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                                public void onActivityResult(int i2, Intent intent2) {
                                    if (App.hasLogined()) {
                                        C78311.this.val$activityConntext.directAccess();
                                    }
                                }
                            });
                            StatisticsUploadUtils.upload(C78311.this.val$activityConntext, "601", "登录—跳转微信", "按钮", "0", "微信", "");
                        } else {
                            HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse("", str, "Login_Type");
                            String userAccountName = UserManager.getInstance().getUserAccountName();
                            String str3 = handleLoginResponse.get("ok");
                            if (str3 != null && "ok".equals(str3)) {
                                App.setLogined(LoginStateConst.DID_LOGIN);
                                App.reLoadDefaultMenuData = true;
                                App.reLoadAdvertise = true;
                                WebFragment.isRelogin = true;
                                String str4 = handleLoginResponse.get("description");
                                if (!TextUtils.isEmpty(str4)) {
                                    UIUtils.toastLong(str4);
                                }
                                UserManager.getInstance().saveSelectAccountName(userAccountName, UserManager.getInstance().getUserAreaid(), UserManager.getInstance().getLoginType(), UserManager.getInstance().getUserPassword(), UserManager.getInstance().getKeyVersion(), "1", UserManager.getInstance().getUserTouxiangURL(), UserManager.getInstance().getYwCode(userAccountName));
                                try {
                                    UnicomPayUtils.getInstance(C78311.this.val$activityConntext).loginPaySdk();
                                } catch (Exception e4) {
                                    e4.printStackTrace();
                                }
                                C78311.this.val$activityConntext.directAccess();
                            } else {
                                UserManager.getInstance().removeUserTouxiangURL();
                                App.setLogined(LoginStateConst.UNLOGIN);
                                String str5 = handleLoginResponse.get("exception");
                                String str6 = TextUtils.isEmpty(handleLoginResponse.get("errorCode")) ? "SERVER CODE IS NULL" : handleLoginResponse.get("errorCode");
                                if (TextUtils.isEmpty(handleLoginResponse.get("description"))) {
                                    str2 = "很抱歉，暂时无法使用，请您稍候再试(code=" + str6 + ")";
                                } else {
                                    str2 = handleLoginResponse.get("description");
                                }
                                if (TextUtils.isEmpty(str5)) {
                                    if (str6.trim().equals("2")) {
                                        UserManager.getInstance().removeUserPassword();
                                        UserManager.getInstance().removeKeyVersion();
                                    }
                                    if ("ECS10002".equals(str6)) {
                                        CustomDialogManager.showZhuXiao(SoulPermission.getInstance().getTopActivity(), "号码已注销", LoginConstUtil.ZHUXIAOMSG, true, "取消", "立即激活", true, true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind.1.1.2
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
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("type", "01");
                                                IntentManager.gotoWebViewActivityWithParams(C78311.this.val$activityConntext, URLSet.getFreezeHtml(), "", hashMap);
                                            }
                                        });
                                    } else {
                                        CustomDialogManager.show(C78311.this.val$activityConntext, "", str2);
                                    }
                                } else {
                                    throw new RuntimeException("接口数据解析异常[" + str5 + "]");
                                }
                            }
                        }
                        LogFileUploadUtils.upload(C78311.this.val$activityConntext);
                        return;
                    }
                    throw new RuntimeException("登录接口返回错误的状态码[" + i + "]");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                    th.printStackTrace();
                    App.setLogined(LoginStateConst.UNLOGIN);
                    LogFileUploadUtils.writeLoginErrorLog(C78311.this.val$activityConntext, C78311.this.val$weixinEvent.getData().toString(), "微信登录", "", str, th);
                    LoginManager.showLoginNetWorkErrorMessage(C78311.this.val$activityConntext, th, true);
                    try {
                        if (C78311.this.val$activityConntext != null && !C78311.this.val$activityConntext.isDestroyed() && !C78311.this.val$activityConntext.isFinishing() && C78311.this.val$pd != null && C78311.this.val$pd.isShowing()) {
                            C78311.this.val$pd.dismiss();
                        }
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                    LogFileUploadUtils.upload(C78311.this.val$activityConntext);
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                    EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                    PushManager.getInstance().pushSwitch(C78311.this.val$activityConntext, App.getSharePreferenceUtil().getBoolean("isAllowNotification"));
                }
            });
        }
    }

    public static void unBind(final AppCompatActivity appCompatActivity) {
        try {
            final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(appCompatActivity);
            customePorgressDialog.setCanceledOnTouchOutside(false);
            customePorgressDialog.setCancelable(false);
            final UserManager userManager = UserManager.getInstance();
            StatisticsUploadUtils.upload(appCompatActivity, "602", "安全设置—微信解绑并退出", "按钮", "0", "解绑并退出", "");
            String userAccountName = userManager.getUserAccountName();
            RequestParams requestParams = new RequestParams();
            Random random = new Random();
            String str = userAccountName + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9));
            try {
                requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
            } catch (Exception e) {
                requestParams.put("mobile", EncodeHelper.encodeByAES(str));
                e.printStackTrace();
            }
            requestParams.put("grantType", "02");
            requestParams.put("token_online", userManager.getOnlineToken(userManager.getUserAccountName()));
            requestParams.put("appId", userManager.getLoginAppId());
            requestParams.put("pip", SystemServiceUtils.getLocalIpAddress());
            requestParams.put("isRemberPwd", String.valueOf(userManager.getAutoLoginStatus()));
            requestParams.put("keyVersion", userManager.getKeyVersion());
            requestParams.put("version", appCompatActivity.getString(2131886969));
            requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
            requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
            requestParams.put(appCompatActivity.getString(2131886495), DeviceHelper.getAndroidId());
            requestParams.put("netWay", DeviceHelper.getNETType(appCompatActivity));
            requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
            requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
            requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
            requestParams.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            App.getAsyncHttpClient().post(URLSet.getWeixinBindLogin(), requestParams, new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerWeixinBind.2
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onStart() {
                    super.onStart();
                    CustomePorgressDialog customePorgressDialog2 = customePorgressDialog;
                    if (customePorgressDialog2 != null) {
                        customePorgressDialog2.setMessage("正在解绑 请稍候");
                    }
                    try {
                        if (appCompatActivity == null || appCompatActivity.isDestroyed() || appCompatActivity.isFinishing() || customePorgressDialog == null || customePorgressDialog.isShowing()) {
                            return;
                        }
                        customePorgressDialog.show();
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str2) {
                    super.onSuccess(i, str2);
                    try {
                        if (App.isSuccessful(i)) {
                            UIUtils.logD("微信解绑接口返回" + str2);
                            JSONObject jSONObject = new JSONObject(str2);
                            String string = jSONObject.getString("code");
                            String optString = jSONObject.optString("dsc", "解绑code非0");
                            if ("0".equals(string)) {
                                userManager.removeSelectAccountName(userManager.getUserAccountName());
                                LoginManager.logout(appCompatActivity);
                                UIUtils.toast("解绑成功");
                                App.mainTagFromOtherActivity = MainActivity.Fragment_Home;
                                appCompatActivity.startActivity(new Intent(appCompatActivity, MainActivity.class));
                                return;
                            }
                            throw new RuntimeException(optString);
                        }
                        throw new RuntimeException("解绑返回错误的状态码[" + i + "]");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        UIUtils.toast(e2.getMessage());
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str2) {
                    super.onFailure(th, str2);
                    th.printStackTrace();
                    UIUtils.toast("解绑失败 请重试");
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFinish() {
                    super.onFinish();
                    try {
                        if (appCompatActivity == null || appCompatActivity.isDestroyed() || appCompatActivity.isFinishing() || customePorgressDialog == null || !customePorgressDialog.isShowing()) {
                            return;
                        }
                        customePorgressDialog.dismiss();
                    } catch (Exception e2) {
                        MsLogUtil.m7978e(e2.getMessage());
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            UIUtils.toast("解绑微信错误【" + e2.getMessage() + "】");
        }
    }
}
