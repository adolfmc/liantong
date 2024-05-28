package com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJAddressUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JiaoFeiManager {
    public static final String JIAOFEIURL = "paymanager/businessRecharge/bankCardRecharge.htm";
    public static boolean isFromJiaofei = false;
    public static boolean jiaofeiYichangDialog = true;
    public static String stopJiaofeiMobile;
    public static HashSet<String> jiaofeiSet = new HashSet<>();
    private static boolean isNeedButNot = false;
    private static boolean dialogLogHasUpdate = false;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    private static UserManager userManager = UserManager.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadLog$2(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$uploadLogin$3(String str) throws Exception {
    }

    public static void showJiaofeiDialog(final Activity activity, final String str) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed() || jiaofeiYichangDialog) {
            uploadLog(str, "展示弹窗", "0001展示失败", "0002页面消失（登录成功、离开登录页、关闭APP）", "1", "0");
        } else if (hasDialogOnTop(activity)) {
            uploadLog(str, "展示弹窗", "0001展示失败", "0001弹窗重叠", "1", "0");
        } else {
            final Dialog dialog = new Dialog(activity, 2131952223);
            RelativeLayout relativeLayout = (RelativeLayout) activity.getLayoutInflater().inflate(2131493259, (ViewGroup) null);
            TextView textView = (TextView) relativeLayout.findViewById(2131297984);
            TextView textView2 = (TextView) relativeLayout.findViewById(2131297981);
            TextView textView3 = (TextView) relativeLayout.findViewById(2131297980);
            TextView textView4 = (TextView) relativeLayout.findViewById(2131297982);
            TextView textView5 = (TextView) relativeLayout.findViewById(2131297983);
            LoginConfigEntity entity = LoginConfigDataCenter.getInstance().getEntity();
            if (!TextUtils.isEmpty(entity.getPayTitle())) {
                textView.setText(entity.getPayTitle());
            }
            if (!TextUtils.isEmpty(str)) {
                StringBuilder sb = new StringBuilder(str);
                if (str.length() >= 11) {
                    int length = (str.length() - 4) / 2;
                    sb.replace(length, length + 4, "****");
                } else if (str.length() >= 6) {
                    int length2 = (str.length() - 3) / 2;
                    sb.replace(length2, length2 + 3, "***");
                }
                textView2.setText(sb.toString());
            }
            if (!TextUtils.isEmpty(entity.getPayContent())) {
                textView3.setText(entity.getPayContent());
            }
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.-$$Lambda$JiaoFeiManager$yvzNH9HqGk43kGoI3JPvPMTQxiM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JiaoFeiManager.lambda$showJiaofeiDialog$0(activity, str, dialog, view);
                }
            });
            textView5.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.-$$Lambda$JiaoFeiManager$Uth5PKwqB8RNrhoR3V8W1qZ1NKM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JiaoFeiManager.lambda$showJiaofeiDialog$1(str, dialog, view);
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (JiaoFeiManager.dialogLogHasUpdate) {
                        JiaoFeiManager.uploadLog(str, "弹窗点击事件", "0003点击阴影区域收起弹窗", "", "1", "0");
                        boolean unused = JiaoFeiManager.dialogLogHasUpdate = false;
                    }
                }
            });
            dialog.setContentView(relativeLayout);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(true);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            android.view.Window window = dialog.getWindow();
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(2131952222);
            dialog.show();
            dialogLogHasUpdate = true;
            uploadLog(str, "展示弹窗", "0000展示成功", "", "1", "0");
            jiaofeiSet.add(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showJiaofeiDialog$0(Activity activity, String str, Dialog dialog, View view) {
        dialogLogHasUpdate = false;
        gotoWebiview(activity, str);
        dialog.cancel();
        uploadLog(str, "弹窗点击事件", "0001点击去交费", "", "1", "0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showJiaofeiDialog$1(String str, Dialog dialog, View view) {
        dialogLogHasUpdate = false;
        uploadLog(str, "弹窗点击事件", "0002点击取消", "", "1", "0");
        dialog.cancel();
    }

    public static void getPayMobileState(final AppCompatActivity appCompatActivity, final String str, String str2) {
        stopJiaofeiMobile = "";
        if (jiaofeiYichangDialog) {
            MsLogUtil.m7979d("getPayMobileState", "4--用户做了异常操作");
        } else if (jiaofeiSet.contains(str)) {
            MsLogUtil.m7979d("getPayMobileState", "3--已经展示过了");
            uploadLog(str, "请求接口", "0003未请求接口，本次冷启动已经弹出", "0001需要引导", "1", "0");
        } else {
            LoginConfigEntity entity = LoginConfigDataCenter.getInstance().getEntity();
            if (TextUtils.isEmpty(entity.getPayUrl())) {
                return;
            }
            if ("2".equals(entity.getSceneType())) {
                if (!isFromJiaofei) {
                    return;
                }
                MsLogUtil.m7979d("getPayMobileState", "配置了场景2,从交费过来的，url：" + str2);
            }
            MsLogUtil.m7979d("getPayMobileState", "5--");
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("seq", RecentCustomManager.uuid());
                Random random = new Random();
                String encode = URLEncoder.encode(Base64.encodeToString(RSACryptos.encryptByPublicKey((str + (random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9) + "" + random.nextInt(9))).getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2), "utf-8");
                MsLogUtil.m7979d("getPayMobileState", ":   " + encode);
                jSONObject.put("mobile", encode);
                jSONObject.put("version", appCompatActivity.getResources().getString(2131886969));
                jSONObject.put("deviceId", DeviceHelper.getDeviceID(true));
                jSONObject.put("deviceCode", DeviceHelper.getDeviceID(true));
                jSONObject.put("netWay", DeviceHelper.getNETType(appCompatActivity));
                jSONObject.put("deviceBrand", DeviceHelper.getDeviceBrand());
                jSONObject.put("deviceModel", DeviceHelper.getDeviceModel());
                jSONObject.put("deviceOS", DeviceHelper.getDeviceOSVersion());
                jSONObject.put("appId", UserManager.getInstance().getLoginAppId());
                jSONObject.put("pip", SystemServiceUtils.getLocalIpAddress());
                TYCJAddressEntity addressEntity = TYCJAddressUtil.getAddressEntity();
                jSONObject.put("provinceCode", addressEntity.getLocateProvinceCode());
                jSONObject.put("cityCode", addressEntity.getLocateCityCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getJiaofeiStateUrl(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager.4
                @Override // io.reactivex.functions.Function
                public String apply(@NonNull String str3) throws Exception {
                    return str3;
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager.2
                @Override // io.reactivex.functions.Consumer
                public void accept(String str3) throws Exception {
                    MsLogUtil.m7979d("getPayMobileState", "6--" + str3);
                    JSONObject jSONObject2 = new JSONObject(str3);
                    if ("0000".equals(jSONObject2.optString("code"))) {
                        if ("y".equalsIgnoreCase(jSONObject2.optJSONObject("data").optString("resultFlag"))) {
                            if (App.isTopProcess) {
                                boolean unused = JiaoFeiManager.isNeedButNot = true;
                                JiaoFeiManager.showJiaofeiDialog(AppCompatActivity.this, str);
                            } else {
                                boolean unused2 = JiaoFeiManager.isNeedButNot = true;
                            }
                            JiaoFeiManager.uploadLog(str, "请求接口", "0001倒计时结束，接口请求成功", "0001需要引导", "1", "0");
                            return;
                        }
                        JiaoFeiManager.uploadLog(str, "请求接口", "0001倒计时结束，接口请求成功", "0002无需引导", "1", "0");
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager.3
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            });
        }
    }

    public static void onStopJiaofei(Disposable disposable) {
        try {
            if (App.isTopProcess) {
                if (!TextUtils.isEmpty(stopJiaofeiMobile)) {
                    uploadLog(stopJiaofeiMobile, "请求接口", "0002未请求接口，页面消失（登录成功、离开登录页、关闭APP）", "0002无需引导", "1", "0");
                    stopJiaofeiMobile = "";
                }
                jiaofeiYichangDialog = true;
                isNeedButNot = false;
                stopoDisposable(disposable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onResumeJiaofei(Activity activity, String str) {
        if (!isNeedButNot || jiaofeiSet.contains(str)) {
            return;
        }
        isNeedButNot = false;
        showJiaofeiDialog(activity, str);
    }

    public static void stopoDisposable(Disposable disposable) {
        if (disposable != null) {
            try {
                if (disposable.isDisposed()) {
                    return;
                }
                disposable.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void gotoWebiview(Activity activity, String str) {
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setTitle("");
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        HashMap hashMap = new HashMap();
        hashMap.put("serviceType", "40");
        hashMap.put("phone", str);
        hashMap.put("appId", UserManager.getInstance().getLoginAppId());
        hashMap.put("deviceCode", DeviceHelper.getDeviceCode());
        hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
        hashMap.put("version", activity.getString(2131886969));
        String payUrl = LoginConfigDataCenter.getInstance().getEntity().getPayUrl();
        if (isFromJiaofei) {
            hashMap.put("fromChannel", "H5");
            webParamsEntity.setUrl(payUrl + "?sinoUnionShortAddr=H5");
        } else {
            hashMap.put("fromChannel", "native");
            webParamsEntity.setUrl(payUrl + "?sinoUnionShortAddr=native");
        }
        String uuid = UUID.randomUUID().toString();
        App.navigateParamsCacheMap.put(uuid, hashMap);
        webParamsEntity.setNavigateParamsUUID(uuid);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public static void uploadLog(String str, String str2, String str3, String str4, String str5, String str6) {
        if ("on".equals(LoginConfigDataCenter.getInstance().getEntity().getPayGreenChannelSwitch())) {
            HashMap hashMap = new HashMap();
            hashMap.put("transId", "S2ndpage1192");
            hashMap.put("bizcode", "S2n");
            hashMap.put("upTime", sdf.format(new Date()));
            hashMap.put("mobile", str);
            hashMap.put("touchcode", str5);
            hashMap.put("provId", userManager.getCurrentProvinceCode());
            hashMap.put("cityId", userManager.getCurrentCityCode());
            hashMap.put("page", "");
            hashMap.put("urlApp", isFromJiaofei ? "0001通过交费url拉起" : "0002通过其他路径拉起");
            hashMap.put("version", App.getInstance().getResources().getString(2131886969));
            hashMap.put("clientType", "Android");
            hashMap.put("remark1", DeviceHelper.getDeviceID(false));
            hashMap.put("remark2", LoginManager.getAccountType());
            hashMap.put("remark3", str3);
            hashMap.put("remark4", str4);
            hashMap.put("sessionid", App.getPvLogSessionId());
            hashMap.put("biz_proecess", str6);
            hashMap.put("page_new_old_user", CacheDataCenter.getInstance().getPageNewOldUser());
            hashMap.put("actCode", "");
            hashMap.put("titleName", "");
            hashMap.put("menuId", "");
            hashMap.put("upType", str2);
            hashMap.put("baseConvert", "");
            App.getAsyncHttpClient(5, 5, 5).rxPost(new ConfigManager(App.getInstance()).getStasticsUploadUrlKey(), hashMap).subscribeOn(Schedulers.m1934io()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.-$$Lambda$JiaoFeiManager$CchJ9BhPqAurocYLEWFrS22zMc8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    JiaoFeiManager.lambda$uploadLog$2((String) obj);
                }
            });
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void uploadLogin(String str, String str2, String str3) {
        char c;
        switch (str3.hashCode()) {
            case 49:
                if (str3.equals("1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 50:
                if (str3.equals("2")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 51:
                if (str3.equals("3")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 52:
                if (str3.equals("4")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 53:
                if (str3.equals("5")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                str3 = "B";
                break;
            case 1:
                str3 = "E01";
                break;
            case 2:
                str3 = "A";
                break;
            case 3:
                str3 = "C01";
                break;
            case 4:
                str3 = "E032";
                break;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("transId", "S2ndpage1221");
        hashMap.put("bizcode", "S2n");
        hashMap.put("upTime", sdf.format(new Date()));
        hashMap.put("mobile", str);
        hashMap.put("touchcode", "");
        hashMap.put("provId", userManager.getCurrentProvinceCode());
        hashMap.put("cityId", userManager.getCurrentCityCode());
        hashMap.put("page", "");
        hashMap.put("urlApp", "");
        hashMap.put("version", App.getInstance().getResources().getString(2131886969));
        hashMap.put("clientType", "Android");
        hashMap.put("remark1", DeviceHelper.getDeviceID(false));
        hashMap.put("remark2", LoginManager.getAccountType());
        hashMap.put("remark3", str2);
        hashMap.put("remark4", str3);
        hashMap.put("sessionid", App.getPvLogSessionId());
        hashMap.put("biz_proecess", "1");
        hashMap.put("page_new_old_user", CacheDataCenter.getInstance().getPageNewOldUser());
        hashMap.put("actCode", "");
        hashMap.put("titleName", "");
        hashMap.put("menuId", "");
        hashMap.put("upType", "隐私协议勾选类型");
        hashMap.put("baseConvert", "");
        App.getAsyncHttpClient(5, 5, 5).rxPost(new ConfigManager(App.getInstance()).getStasticsUploadUrlKey(), hashMap).subscribeOn(Schedulers.m1934io()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.-$$Lambda$JiaoFeiManager$EWFwCgd6BglTQ_DGVmFrqwUSwXs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                JiaoFeiManager.lambda$uploadLogin$3((String) obj);
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean hasDialogOnTop(android.app.Activity r6) {
        /*
            r0 = 0
            android.view.Window r6 = r6.getWindow()     // Catch: java.lang.Exception -> L4f
            android.view.View r6 = r6.getDecorView()     // Catch: java.lang.Exception -> L4f
            android.os.IBinder r1 = r6.getWindowToken()     // Catch: java.lang.Exception -> L4f
            com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window r2 = com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window.INSTANCE     // Catch: java.lang.Exception -> L4f
            java.util.List r2 = r2.getViews()     // Catch: java.lang.Exception -> L4f
            int r6 = r2.indexOf(r6)     // Catch: java.lang.Exception -> L4f
            com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window r2 = com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.Window.INSTANCE     // Catch: java.lang.Exception -> L4f
            java.util.List r2 = r2.getParams()     // Catch: java.lang.Exception -> L4f
            java.lang.Object r6 = r2.get(r6)     // Catch: java.lang.Exception -> L4f
            android.view.WindowManager$LayoutParams r6 = (android.view.WindowManager.LayoutParams) r6     // Catch: java.lang.Exception -> L4f
            android.os.IBinder r6 = r6.token     // Catch: java.lang.Exception -> L4f
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Exception -> L4f
            r3 = r0
        L2a:
            boolean r4 = r2.hasNext()     // Catch: java.lang.Exception -> L4d
            if (r4 == 0) goto L54
            java.lang.Object r4 = r2.next()     // Catch: java.lang.Exception -> L4d
            android.view.WindowManager$LayoutParams r4 = (android.view.WindowManager.LayoutParams) r4     // Catch: java.lang.Exception -> L4d
            android.os.IBinder r5 = r4.token     // Catch: java.lang.Exception -> L4d
            if (r5 == 0) goto L4a
            android.os.IBinder r5 = r4.token     // Catch: java.lang.Exception -> L4d
            boolean r5 = r5.equals(r1)     // Catch: java.lang.Exception -> L4d
            if (r5 != 0) goto L4a
            android.os.IBinder r4 = r4.token     // Catch: java.lang.Exception -> L4d
            boolean r4 = r4.equals(r6)     // Catch: java.lang.Exception -> L4d
            if (r4 == 0) goto L2a
        L4a:
            int r3 = r3 + 1
            goto L2a
        L4d:
            r6 = move-exception
            goto L51
        L4f:
            r6 = move-exception
            r3 = r0
        L51:
            r6.printStackTrace()
        L54:
            r6 = 1
            if (r3 <= r6) goto L58
            goto L59
        L58:
            r6 = r0
        L59:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.login.jiaofeitanchuang.JiaoFeiManager.hasDialogOnTop(android.app.Activity):boolean");
    }
}
