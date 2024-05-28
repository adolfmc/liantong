package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.activity.MainPrivateActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.face.utils.HttpPrivateUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerFaceLogin */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerFaceLogin {
    private AppCompatActivity activityContext;
    UserManager userManager = UserManager.getInstance();
    SharePreferenceUtil preference = App.getSharePreferenceUtil();
    Random random = new Random();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerFaceLogin$IFaceResult */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IFaceResult {
        void onResult(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$loadDelete$1(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$loadFaceQuery$0(String str) throws Exception {
        return str;
    }

    public ManagerFaceLogin(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    private HashMap<String, String> rebuildParams(HashMap<String, String> hashMap) {
        String str = hashMap.get("accountName");
        String str2 = hashMap.get("password");
        String str3 = this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9) + "" + this.random.nextInt(9);
        String str4 = str + str3;
        String str5 = str2 + str3;
        if (!TextUtils.isEmpty(str)) {
            try {
                hashMap.put("mobile", Base64.encodeToString(RSACryptos.encryptByPublicKey(str4.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
            } catch (Exception e) {
                hashMap.put("mobile", EncodeHelper.encodeByAES(str4));
                e.printStackTrace();
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            try {
                hashMap.put("password", Base64.encodeToString(RSACryptos.encryptByPublicKey(str5.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
            } catch (Exception e2) {
                hashMap.put("password", EncodeHelper.encodeByAES(str5));
                e2.printStackTrace();
            }
        }
        hashMap.put("appId", this.userManager.getLoginAppId());
        hashMap.put("pip", SystemServiceUtils.getLocalIpAddress());
        hashMap.put("isRemberPwd", "true");
        hashMap.put("keyVersion", this.userManager.getKeyVersion());
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("deviceId", DeviceHelper.getDeviceID(true));
        hashMap.put("deviceCode", DeviceHelper.getDeviceID(false));
        hashMap.put(this.activityContext.getString(2131886495), DeviceHelper.getAndroidId());
        hashMap.put("netWay", DeviceHelper.getNETType(this.activityContext.getApplicationContext()));
        hashMap.put("deviceBrand", DeviceHelper.getDeviceBrand());
        hashMap.put("deviceModel", DeviceHelper.getDeviceModel());
        hashMap.put("deviceOS", DeviceHelper.getDeviceOSVersion());
        hashMap.put("timestamp", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        try {
            hashMap.put("provinceChanel", this.activityContext.getPackageManager().getApplicationInfo(this.activityContext.getPackageName(), 128).metaData.getString("CHANNEL_ID"));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (DeviceHelper.getNeedHuawei() || DeviceHelper.isXIAOMI() || DeviceHelper.isVivo() || DeviceHelper.isOppo()) {
            try {
                hashMap.put("pushPlatform", DeviceHelper.getDeviceBrand().toUpperCase());
                hashMap.put("platformToken", App.getSharePreferenceUtil().getString("platformToken"));
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
        hashMap.put("accountName", "");
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            hashMap.put("jumpOver", "true");
        }
        hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        return hashMap;
    }

    public ObservableSubscribeProxy<String> loadFaceQuery(HashMap<String, String> hashMap) {
        HashMap<String, String> rebuildParams = rebuildParams(hashMap);
        rebuildParams.put("face_path_code", "1");
        rebuildParams.put("search_type", "0");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getfaceCompareAndLogin(), rebuildParams).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$ManagerFaceLogin$JwV34Ji6bPk3WBOh5jBkMYCu7uU
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerFaceLogin.lambda$loadFaceQuery$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> loadFaceRegist(HashMap<String, String> hashMap, String str) {
        HashMap<String, String> rebuildParams = rebuildParams(hashMap);
        rebuildParams.put("delta", str);
        rebuildParams.put("face_path_code", "0");
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getfaceAddLogin(), rebuildParams).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFaceLogin.1
            @Override // io.reactivex.functions.Function
            public String apply(String str2) throws Exception {
                return str2;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> loadFaceLogin(HashMap<String, String> hashMap, String str) {
        HashMap<String, String> rebuildParams = rebuildParams(hashMap);
        rebuildParams.put("face_path_code", "2");
        rebuildParams.put("search_type", "1");
        rebuildParams.put("delta", str);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getfaceCompareAndLogin(), rebuildParams).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFaceLogin.2
            @Override // io.reactivex.functions.Function
            public String apply(String str2) throws Exception {
                return str2;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> loadDelete(HashMap<String, String> hashMap) {
        return (ObservableSubscribeProxy) App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getface_del(), rebuildParams(hashMap)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$ManagerFaceLogin$UnLED7YtYuA6Wohb0qiKVQhzVUA
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerFaceLogin.lambda$loadDelete$1((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public static void starFaceLogin(final Activity activity, final IFaceResult iFaceResult) {
        Intent intent = new Intent(activity, MainPrivateActivity.class);
        intent.putExtra("type", "3");
        intent.putExtra("biz_token", "");
        intent.putExtra("livenessMegliveType", "1");
        new AvoidOnResult(activity).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.manager.ManagerFaceLogin.3
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                try {
                    if (intent2 == null) {
                        ManagerFaceLogin.showDialog(activity, "9999");
                        iFaceResult.onResult("");
                        return;
                    }
                    String stringExtra = intent2.getStringExtra("status");
                    if (TextUtils.isEmpty(stringExtra)) {
                        stringExtra = "9999";
                    }
                    String str = HttpPrivateUtil.faceV3Data;
                    if (!"0000".equals(stringExtra)) {
                        ManagerFaceLogin.showDialog(activity, stringExtra);
                        iFaceResult.onResult("");
                        return;
                    }
                    iFaceResult.onResult(str);
                } catch (Exception e) {
                    ManagerFaceLogin.showDialog(activity, "10");
                    e.printStackTrace();
                    iFaceResult.onResult("");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void showDialog(Activity activity, String str) {
        char c;
        String str2;
        int hashCode = str.hashCode();
        if (hashCode != 1477664) {
            switch (hashCode) {
                case 1567:
                    if (str.equals("10")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1568:
                    if (str.equals("11")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1569:
                    if (str.equals("12")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    switch (hashCode) {
                        case 1507425:
                            if (str.equals("1002")) {
                                c = 4;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507426:
                            if (str.equals("1003")) {
                                c = 5;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507427:
                            if (str.equals("1004")) {
                                c = 6;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507428:
                            if (str.equals("1005")) {
                                c = 7;
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507429:
                            if (str.equals("1006")) {
                                c = '\b';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507430:
                            if (str.equals("1007")) {
                                c = '\t';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507431:
                            if (str.equals("1008")) {
                                c = '\n';
                                break;
                            }
                            c = 65535;
                            break;
                        case 1507432:
                            if (str.equals("1009")) {
                                c = 11;
                                break;
                            }
                            c = 65535;
                            break;
                        default:
                            switch (hashCode) {
                                case 1507454:
                                    if (str.equals("1010")) {
                                        c = '\f';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                case 1507455:
                                    if (str.equals("1011")) {
                                        c = '\r';
                                        break;
                                    }
                                    c = 65535;
                                    break;
                                default:
                                    switch (hashCode) {
                                        case 1507457:
                                            if (str.equals("1013")) {
                                                c = 14;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        case 1507458:
                                            if (str.equals("1014")) {
                                                c = 15;
                                                break;
                                            }
                                            c = 65535;
                                            break;
                                        default:
                                            switch (hashCode) {
                                                case 1507461:
                                                    if (str.equals("1017")) {
                                                        c = 16;
                                                        break;
                                                    }
                                                    c = 65535;
                                                    break;
                                                case 1507462:
                                                    if (str.equals("1018")) {
                                                        c = 17;
                                                        break;
                                                    }
                                                    c = 65535;
                                                    break;
                                                case 1507463:
                                                    if (str.equals("1019")) {
                                                        c = 18;
                                                        break;
                                                    }
                                                    c = 65535;
                                                    break;
                                                default:
                                                    c = 65535;
                                                    break;
                                            }
                                    }
                            }
                    }
            }
        } else {
            if (str.equals("0011")) {
                c = 3;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                str2 = "系统异常请重试或使用其他方式登录[ECS010F]";
                break;
            case 1:
                str2 = "系统异常请重试或使用其他方式登录[ECS011F]";
                break;
            case 2:
                str2 = "相机权限未开启，请前往「设置>中国联通」打开相机权限[ECS012F]";
                break;
            case 3:
                str2 = "请求失败，请稍后再试或使用其他方式登录[ECS0011]";
                break;
            case 4:
                str2 = "系统异常请重试或使用其他方式登录[ECS01002F]";
                break;
            case 5:
                str2 = "系统异常请重试或使用其他方式登录[ECS01003F]";
                break;
            case 6:
                str2 = "当前设备暂不支持刷脸方式登录，请使用其他方式登录[ECS01004F]";
                break;
            case 7:
                str2 = "尊敬的客户，出了一点点问题，请您反馈我们处理，给您带来不便敬请谅解[ECS01005F]";
                break;
            case '\b':
                str2 = "请勿重复操作刷脸[ECS01006F]";
                break;
            case '\t':
                str2 = "网络请求超时，请稍后再试[ECS01007F]";
                break;
            case '\n':
                str2 = "网络请求超时，请稍后再试[ECS01008F]";
                break;
            case 11:
                str2 = "信息验证失败，请重试[ECS01009F]";
                break;
            case '\f':
                str2 = "网络请求超时，请稍后再试[ECS01010F]";
                break;
            case '\r':
                str2 = "人脸识别验证已取消，请重新登录[ECS01011F]";
                break;
            case 14:
                str2 = "无法启动相机，请检查相机功能完好或尝试重启手机[ECS01013F]";
                break;
            case 15:
                str2 = "系统异常请重试或使用其他方式登录[ECS01014F]";
                break;
            case 16:
                str2 = "人脸识别验证已取消，请将APP保持在前台[ECS01017F]";
                break;
            case 17:
                str2 = "人脸识别超时，请重新登录[ECS01018F]";
                break;
            case 18:
                str2 = "系统异常请重试或使用其他方式登录[ECS01019F]";
                break;
            default:
                str2 = "刷脸方式登录失败，请稍后再试或使用其他方式登录";
                break;
        }
        if ("1011".equals(str)) {
            UIUtils.toastCenter(str2);
        } else {
            CustomDialogManager.show(activity, "", str2);
        }
    }
}
