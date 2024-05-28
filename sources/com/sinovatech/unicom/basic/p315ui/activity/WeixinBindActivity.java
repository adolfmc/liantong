package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.WebSocketEvent;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
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
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.LogFileUploadUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJAddressUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WeixinBindActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private WeixinBindActivity activityContext;
    private ImageView backButton;
    private ImageButton clearMobileButton;
    private ImageButton clearSMSButton;
    private Disposable countDownDisposable;
    private int countDownTime;
    private Button loginButton;
    private LoginConfigEntity loginConfigEntity;
    private EditText mobileText;

    /* renamed from: pd */
    private CustomePorgressDialog f18407pd;
    private EditText smsText;
    private TextView smsVerifyButton;
    private UserManager userManager;
    private String wx_code;
    private boolean isSmsCountDown = false;
    private Random random = new Random();
    private String randomStr = "000000";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 62);
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

    static /* synthetic */ int access$1210(WeixinBindActivity weixinBindActivity) {
        int i = weixinBindActivity.countDownTime;
        weixinBindActivity.countDownTime = i - 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSmsVerifyButtonStyle(boolean z) {
        if (z && !this.isSmsCountDown) {
            this.smsVerifyButton.setTextColor(-40912);
            this.smsVerifyButton.setBackgroundResource(2131232636);
            this.smsVerifyButton.setClickable(true);
            return;
        }
        this.smsVerifyButton.setTextColor(-3486507);
        this.smsVerifyButton.setBackgroundResource(2131232637);
        this.smsVerifyButton.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetSmsButtonCountDown() {
        this.isSmsCountDown = false;
        this.smsVerifyButton.setText("获取验证码");
        setSmsVerifyButtonStyle(true);
        Disposable disposable = this.countDownDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.countDownDisposable.dispose();
    }

    private void initClickListener() {
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WeixinBindActivity.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.clearMobileButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WeixinBindActivity.this.mobileText.setText("");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.clearSMSButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                WeixinBindActivity.this.smsText.setText("");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        InitialValueObservable<CharSequence> textChanges = RxTextView.textChanges(this.mobileText);
        ((ObservableSubscribeProxy) textChanges.m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer<CharSequence>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.4
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) throws Exception {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    WeixinBindActivity.this.clearMobileButton.setVisibility(8);
                } else {
                    WeixinBindActivity.this.clearMobileButton.setVisibility(0);
                }
            }
        });
        InitialValueObservable<CharSequence> textChanges2 = RxTextView.textChanges(this.smsText);
        ((ObservableSubscribeProxy) textChanges2.m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer<CharSequence>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(CharSequence charSequence) throws Exception {
                if (TextUtils.isEmpty(charSequence.toString())) {
                    WeixinBindActivity.this.clearSMSButton.setVisibility(8);
                } else {
                    WeixinBindActivity.this.clearSMSButton.setVisibility(0);
                }
            }
        });
        ((ObservableSubscribeProxy) Observable.combineLatest(textChanges, textChanges2, new BiFunction<CharSequence, CharSequence, Object>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.7
            @Override // io.reactivex.functions.BiFunction
            public Object apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                UIUtils.logD("合并事件订阅 " + ((Object) charSequence) + "  " + ((Object) charSequence2));
                if (TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2) || charSequence2.length() < 4 || WeixinBindActivity.this.mobileText.getText().toString().trim().length() != 11) {
                    WeixinBindActivity.this.loginButton.setClickable(false);
                    WeixinBindActivity.this.loginButton.setBackgroundResource(2131231880);
                } else {
                    WeixinBindActivity.this.loginButton.setClickable(true);
                    WeixinBindActivity.this.loginButton.setBackgroundResource(2131231879);
                }
                return new Object();
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).m1940as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this.activityContext, Lifecycle.Event.ON_DESTROY)))).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
            }
        });
        this.smsVerifyButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (TextUtils.isEmpty(WeixinBindActivity.this.mobileText.getText().toString()) || WeixinBindActivity.this.mobileText.getText().toString().trim().length() != 11) {
                    UIUtils.toastCenter("请输入11位手机号码");
                    NBSActionInstrumentation.onClickEventExit();
                } else if (!SystemServiceUtils.netIsAvailable()) {
                    CustomDialogManager.show(WeixinBindActivity.this.activityContext, "", "网络连接失败，请检查网络设置！");
                    NBSActionInstrumentation.onClickEventExit();
                } else {
                    WeixinBindActivity.this.setSmsVerifyButtonStyle(false);
                    WeixinBindActivity.this.getSmsCode();
                    NBSActionInstrumentation.onClickEventExit();
                }
            }
        });
        ((ObservableSubscribeProxy) RxView.clicks(this.loginButton).throttleFirst(1L, TimeUnit.SECONDS).m1940as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this.activityContext, Lifecycle.Event.ON_DESTROY)))).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                WeixinBindActivity.this.logoutBeforeBind("");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void countDown() {
        this.countDownTime = 60;
        Disposable disposable = this.countDownDisposable;
        if (disposable != null && !disposable.isDisposed()) {
            this.countDownDisposable.dispose();
        }
        ((ObservableSubscribeProxy) Observable.intervalRange(1L, 60L, 0L, 1L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.10
            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable2) {
                WeixinBindActivity.this.countDownDisposable = disposable2;
                WeixinBindActivity.this.isSmsCountDown = true;
                WeixinBindActivity.this.smsVerifyButton.setText("重新获取(60)");
                WeixinBindActivity.this.setSmsVerifyButtonStyle(false);
            }

            @Override // io.reactivex.Observer
            public void onNext(Long l) {
                WeixinBindActivity.access$1210(WeixinBindActivity.this);
                UIUtils.logD("验证码倒计时：" + WeixinBindActivity.this.countDownTime);
                TextView textView = WeixinBindActivity.this.smsVerifyButton;
                textView.setText("重新获取(" + WeixinBindActivity.this.countDownTime + ")");
                if (l.longValue() <= 0) {
                    WeixinBindActivity.this.resetSmsButtonCountDown();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                th.printStackTrace();
                UIUtils.logE("验证码倒计时：" + th.getMessage());
                WeixinBindActivity.this.resetSmsButtonCountDown();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                WeixinBindActivity.this.resetSmsButtonCountDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSmsCode() {
        String trim = this.mobileText.getText().toString().trim();
        String str = trim + (this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9));
        RequestParams requestParams = new RequestParams();
        try {
            requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
        } catch (Exception e) {
            requestParams.put("mobile", EncodeHelper.encodeByAES(str));
            e.printStackTrace();
        }
        requestParams.put("msg_type", "10");
        requestParams.put("version", this.activityContext.getString(2131886969));
        requestParams.put("loginCodeLen", this.loginConfigEntity.getLoginCodeLen());
        requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
        requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
        requestParams.put(getString(2131886495), DeviceHelper.getAndroidId());
        requestParams.put("netWay", DeviceHelper.getNETType(getApplicationContext()));
        requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
        requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
        requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        requestParams.put("appId", this.userManager.getLoginAppId());
        requestParams.put("pip", SystemServiceUtils.getLocalIpAddress());
        TYCJAddressEntity addressEntity = TYCJAddressUtil.getAddressEntity();
        requestParams.put("provinceCode", addressEntity.getLocateProvinceCode());
        requestParams.put("cityCode", addressEntity.getLocateCityCode());
        getSmsCode(trim, requestParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSmsCode(final String str, final RequestParams requestParams) {
        LoginParamsEntity headerAndBody = DongtaiMiyaoUtils.getHeaderAndBody(str, requestParams.getRealParams());
        App.getAsyncHttpClient().post(URLSet.getRandomPswdURL(), headerAndBody.getBodyMap(), headerAndBody.getHeaderMap(), new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.11
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str2) {
                super.onSuccess(i, str2);
                try {
                    WeixinBindActivity.this.resetSmsButtonCountDown();
                    if (LoginFilterUtil.filerLogin(WeixinBindActivity.this.activityContext, str2, new LoginFilterUtil.CallBackInterface() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.11.1
                        @Override // com.sinovatech.unicom.separatemodule.login.fengkong.LoginFilterUtil.CallBackInterface
                        public void complete(String str3) {
                            if (TextUtils.isEmpty(str3)) {
                                return;
                            }
                            requestParams.put("resultToken", str3);
                            WeixinBindActivity.this.getSmsCode(str, requestParams);
                        }
                    })) {
                        return;
                    }
                    if (App.isSuccessful(i)) {
                        UIUtils.logD("短信验证码接口返回：" + str2);
                        JSONObject jSONObject = new JSONObject(str2);
                        String optString = jSONObject.optString("rsp_code");
                        String optString2 = jSONObject.optString("rsp_desc");
                        if (!TextUtils.isEmpty(optString2)) {
                            UIUtils.toastCenterLong(optString2);
                        }
                        if ("0000".equals(optString)) {
                            WeixinBindActivity.this.countDown();
                            return;
                        }
                        return;
                    }
                    throw new RuntimeException("获取短信验证码接口返回错误的状态码[" + i + "]");
                } catch (Exception e) {
                    e.printStackTrace();
                    if (!App.isSuccessful(i)) {
                        WeixinBindActivity weixinBindActivity = WeixinBindActivity.this.activityContext;
                        CustomDialogManager.show(weixinBindActivity, "", "获取短信验证码异常，请重试【ECS" + i + "】");
                    } else {
                        CustomDialogManager.show(WeixinBindActivity.this.activityContext, "", "获取短信验证码异常，请重试【ECS0003】");
                    }
                    LogFileUploadUtils.writeLoginErrorLog(WeixinBindActivity.this.activityContext, str, "获取短信验证码", "--", str2, e);
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str2) {
                super.onFailure(th, str2);
                CustomDialogManager.show(WeixinBindActivity.this.activityContext, "", "网络连接异常，请您重新获取验证码");
                LogFileUploadUtils.writeLoginErrorLog(WeixinBindActivity.this.activityContext, str, "获取短信验证码", "--", str2, th);
                WeixinBindActivity.this.resetSmsButtonCountDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logoutBeforeBind(final String str) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        UserManager userManager = this.userManager;
        requestParams.put("token_online", userManager.getOnlineToken(userManager.getCurrentPhoneNumber()));
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getLogoutURL(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.12
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                if (WeixinBindActivity.this.f18407pd != null) {
                    WeixinBindActivity.this.f18407pd.setMessage("正在登录 请稍候");
                }
                WeixinBindActivity.this.pdShow();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                WeixinBindActivity.this.pdDissmiss();
                WeixinBindActivity.this.bindAndLogin(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (isDestroyed() || isFinishing() || this.f18407pd == null || !this.f18407pd.isShowing()) {
                return;
            }
            this.f18407pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18407pd == null || this.f18407pd.isShowing()) {
                return;
            }
            this.f18407pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindAndLogin(String str) {
        try {
            App.clearPersistentCookiesList();
            UnicomCookieManager.removeSessionCookieAndResetNecessaryCookie();
            StatisticsUploadUtils.upload(this.activityContext, "603", "微信绑定并登录", "按钮", "0", "绑定并登录", "");
            App.setLogined(LoginStateConst.UNLOGIN);
            this.userManager.saveAutoLoginStatus(true);
            final String trim = this.mobileText.getText().toString().trim();
            String trim2 = this.smsText.getText().toString().trim();
            if (!TextUtils.isEmpty(trim) && trim.length() == 11) {
                if (TextUtils.isEmpty(trim2)) {
                    UIUtils.toast("请输入短信验证码！");
                    return;
                }
                RequestParams requestParams = new RequestParams();
                this.randomStr = this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9);
                StringBuilder sb = new StringBuilder();
                sb.append(trim);
                sb.append(this.randomStr);
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                sb3.append(trim2);
                sb3.append(this.randomStr);
                String sb4 = sb3.toString();
                try {
                    requestParams.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(sb2.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
                } catch (Exception e) {
                    requestParams.put("mobile", EncodeHelper.encodeByAES(sb2));
                    e.printStackTrace();
                }
                try {
                    requestParams.put("password", Base64.encodeToString(RSACryptos.encryptByPublicKey(sb4.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
                } catch (Exception e2) {
                    requestParams.put("password", EncodeHelper.encodeByAES(sb4));
                    e2.printStackTrace();
                }
                requestParams.put("wx_code", this.wx_code);
                if (!TextUtils.isEmpty(str) && str.equals("is_force")) {
                    requestParams.put("is_force", "0");
                    requestParams.put("grantType", "02");
                } else {
                    requestParams.put("grantType", "01");
                }
                requestParams.put("appId", UserManager.getInstance().getLoginAppId());
                requestParams.put("pip", SystemServiceUtils.getLocalIpAddress());
                requestParams.put("isRemberPwd", String.valueOf(UserManager.getInstance().getAutoLoginStatus()));
                requestParams.put("keyVersion", UserManager.getInstance().getKeyVersion());
                requestParams.put("version", this.activityContext.getString(2131886969));
                requestParams.put("deviceId", DeviceHelper.getDeviceID(true));
                requestParams.put("deviceCode", DeviceHelper.getDeviceID(true));
                requestParams.put(this.activityContext.getString(2131886495), DeviceHelper.getAndroidId());
                requestParams.put("netWay", DeviceHelper.getNETType(this.activityContext));
                requestParams.put("deviceBrand", DeviceHelper.getDeviceBrand());
                requestParams.put("deviceModel", DeviceHelper.getDeviceModel());
                requestParams.put("deviceOS", DeviceHelper.getDeviceOSVersion());
                requestParams.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                try {
                    requestParams.put("provinceChanel", this.activityContext.getPackageManager().getApplicationInfo(this.activityContext.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
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
                App.getAsyncHttpClient().post(URLSet.getWeixinBindLogin(), requestParams, new AsyncHttpResponseHandler(1) { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.13
                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onStart() {
                        super.onStart();
                        App.setLogined(LoginStateConst.DOING_NORMAL_LOGIN);
                        if (WeixinBindActivity.this.f18407pd != null) {
                            WeixinBindActivity.this.f18407pd.setMessage("正在登录 请稍候");
                        }
                        WeixinBindActivity.this.pdShow();
                    }

                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onSuccess(int i, String str2) {
                        String str3;
                        super.onSuccess(i, str2);
                        WeixinBindActivity.this.pdDissmiss();
                        try {
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            App.setLogined(LoginStateConst.UNLOGIN);
                            if (!App.isSuccessful(i)) {
                                LoginManager.showLoginErrorMessage(WeixinBindActivity.this.activityContext, "未登录成功，请重试【ECS" + i + "】", true, "未登录成功，请重试【ECS" + i + "】\n接口状态码=" + i, false);
                            } else {
                                LoginManager.showLoginErrorMessage(WeixinBindActivity.this.activityContext, "未登录成功，请重试【ECS0002】", true, "未登录成功，请重试【ECS0002】\n接口返状态码=200，数据处理有问题，需查询服务器日志定位问题！", false);
                            }
                            LogFileUploadUtils.writeLoginErrorLog(WeixinBindActivity.this.activityContext, trim, "微信绑定并登录", "", str2, e3);
                        }
                        if (App.isSuccessful(i)) {
                            Log.d("CESHI", "微信绑定并登录接口返回：" + str2);
                            JSONObject jSONObject = new JSONObject(str2);
                            if ("25".equals(jSONObject.getString("code"))) {
                                CustomDialogManager.show((Activity) WeixinBindActivity.this.activityContext, "", jSONObject.getString("dsc"), true, "绑定新微信", "换个手机号", false, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.basic.ui.activity.WeixinBindActivity.13.1
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
                                        WeixinBindActivity.this.mobileText.setText("");
                                        WeixinBindActivity.this.smsText.setText("");
                                    }

                                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                                    public void onClickCancel() {
                                        WeixinBindActivity.this.bindAndLogin("is_force");
                                    }
                                });
                            } else {
                                HashMap<String, String> handleLoginResponse = LoginManager.handleLoginResponse(trim, str2, "Login_Type");
                                String str4 = handleLoginResponse.get("ok");
                                if (str4 == null || !"ok".equals(str4)) {
                                    WeixinBindActivity.this.userManager.removeUserTouxiangURL();
                                    App.setLogined(LoginStateConst.UNLOGIN);
                                    String str5 = handleLoginResponse.get("exception");
                                    String str6 = TextUtils.isEmpty(handleLoginResponse.get("errorCode")) ? "SERVER CODE IS NULL" : handleLoginResponse.get("errorCode");
                                    if (TextUtils.isEmpty(handleLoginResponse.get("description"))) {
                                        str3 = "很抱歉，暂时无法使用，请您稍候再试(code=" + str6 + ")";
                                    } else {
                                        str3 = handleLoginResponse.get("description");
                                    }
                                    if (TextUtils.isEmpty(str5)) {
                                        if (str6.trim().equals("2")) {
                                            WeixinBindActivity.this.userManager.removeUserPassword();
                                            WeixinBindActivity.this.userManager.removeKeyVersion();
                                        }
                                        CustomDialogManager.show(WeixinBindActivity.this.activityContext, "", str3);
                                    } else {
                                        throw new RuntimeException("接口数据解析异常[" + str5 + "]");
                                    }
                                } else {
                                    App.setLogined(LoginStateConst.DID_LOGIN);
                                    App.reLoadDefaultMenuData = true;
                                    App.reLoadAdvertise = true;
                                    String str7 = handleLoginResponse.get("description");
                                    if (!TextUtils.isEmpty(str7)) {
                                        UIUtils.toastLong(str7);
                                    }
                                    WeixinBindActivity.this.userManager.saveSelectAccountName(trim, WeixinBindActivity.this.userManager.getUserAreaid(), WeixinBindActivity.this.userManager.getLoginType(), WeixinBindActivity.this.userManager.getUserPassword(), WeixinBindActivity.this.userManager.getKeyVersion(), "1", WeixinBindActivity.this.userManager.getUserTouxiangURL(), WeixinBindActivity.this.userManager.getYwCode(trim));
                                    WeixinBindActivity weixinBindActivity = WeixinBindActivity.this.activityContext;
                                    WeixinBindActivity unused3 = WeixinBindActivity.this.activityContext;
                                    weixinBindActivity.setResult(-1);
                                    WeixinBindActivity.this.activityContext.finish();
                                }
                            }
                            LogFileUploadUtils.upload(WeixinBindActivity.this.activityContext);
                            return;
                        }
                        throw new RuntimeException("登录接口返回错误的状态码[" + i + "]");
                    }

                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onFailure(Throwable th, String str2) {
                        super.onFailure(th, str2);
                        th.printStackTrace();
                        App.setLogined(LoginStateConst.UNLOGIN);
                        LogFileUploadUtils.writeLoginErrorLog(WeixinBindActivity.this.activityContext, trim, "微信绑定并登录", "", str2, th);
                        LoginManager.showLoginNetWorkErrorMessage(WeixinBindActivity.this.activityContext, th, true);
                        WeixinBindActivity.this.pdDissmiss();
                        LogFileUploadUtils.upload(WeixinBindActivity.this.activityContext);
                    }

                    @Override // com.loopj.android.http.AsyncHttpResponseHandler
                    public void onFinish() {
                        super.onFinish();
                        EventBusUtils.post(new WebSocketEvent(EventBusUtils.EVENTCODE_START_WEBSOCKET));
                        PushManager.getInstance().pushSwitch(WeixinBindActivity.this.activityContext, App.getSharePreferenceUtil().getBoolean("isAllowNotification"));
                    }
                });
                return;
            }
            UIUtils.toast("请输入11位手机号码！");
        } catch (Exception e3) {
            e3.printStackTrace();
            UIUtils.toast("绑定微信并登录错误【" + e3.getMessage() + "】");
        }
    }
}
