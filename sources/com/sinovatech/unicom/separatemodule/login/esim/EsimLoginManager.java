package com.sinovatech.unicom.separatemodule.login.esim;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseFunction;
import com.sinovatech.unicom.basic.p315ui.home.manager.BaseRequestManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.DongtaiMiyaoUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.LoginParamsEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EsimLoginManager extends BaseRequestManager<HashMap<String, String>> {
    private SharePreferenceUtil preference;
    private final Random random;
    private final UserManager userManager;

    public EsimLoginManager(AppCompatActivity appCompatActivity) {
        super(appCompatActivity);
        this.userManager = UserManager.getInstance();
        this.preference = App.getSharePreferenceUtil();
        this.random = new Random();
    }

    private HashMap<String, String> rebuildParams(HashMap<String, String> hashMap) {
        String str = hashMap.get("mobile");
        String str2 = hashMap.get("idCardNo");
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
                hashMap.put("idCardNo", Base64.encodeToString(RSACryptos.encryptByPublicKey(str5.getBytes(Charset.forName("UTF-8")), Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2));
            } catch (Exception e2) {
                hashMap.put("idCardNo", EncodeHelper.encodeByAES(str5));
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
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            hashMap.put("jumpOver", "true");
        }
        hashMap.put("isFirstInstall", App.getSharePreferenceUtil().getBoolean("user_is_fugai") ? "0" : "1");
        return hashMap;
    }

    public ObservableSubscribeProxy<HashMap<String, String>> stepZero(String str, HashMap<String, String> hashMap) {
        LoginParamsEntity esimHeaderAndBody = DongtaiMiyaoUtils.getEsimHeaderAndBody(str, rebuildParams(hashMap));
        return setRequestType("1").setNetObserver(App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getesimLogin(), esimHeaderAndBody.getBodyMap(), esimHeaderAndBody.getHeaderMap())).setFunction(new BaseFunction<HashMap<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.EsimLoginManager.1
            @Override // io.reactivex.functions.Function
            public HashMap<String, String> apply(String str2) throws Exception {
                JSONObject jSONObject = new JSONObject(str2);
                HashMap<String, String> hashMap2 = new HashMap<>();
                hashMap2.put("errorCode", jSONObject.optString("code"));
                hashMap2.put("content", str2);
                hashMap2.put("description", jSONObject.optString("dsc"));
                hashMap2.put("resultToken", jSONObject.optString("resultToken"));
                return hashMap2;
            }
        }).getObservable();
    }

    public ObservableSubscribeProxy<HashMap<String, String>> stepOne(final String str, HashMap<String, String> hashMap) {
        LoginParamsEntity esimHeaderAndBody = DongtaiMiyaoUtils.getEsimHeaderAndBody(str, rebuildParams(hashMap));
        return setRequestType("1").setNetObserver(App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getesimLogin(), esimHeaderAndBody.getBodyMap(), esimHeaderAndBody.getHeaderMap())).setFunction(new BaseFunction<HashMap<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.login.esim.EsimLoginManager.2
            @Override // io.reactivex.functions.Function
            public HashMap<String, String> apply(String str2) throws Exception {
                return LoginManager.handleLoginResponse(str, str2, "Login_Type");
            }
        }).getObservable();
    }
}
