package com.sinovatech.unicom.separatemodule.esim;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.text.TextUtils;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EsimUtil {
    public static boolean isVpnWhiteList() {
        return true;
    }

    public static boolean yumingWhite() {
        return true;
    }

    public static String[] getEsimParams(Activity activity, WebView webView, String str, String str2, String str3, String str4) {
        String[] strArr;
        boolean z;
        int i;
        String str5;
        String[] strArr2 = new String[2];
        JSONObject jSONObject = new JSONObject();
        try {
            String ywCodeDefault = UserManager.getInstance().getYwCodeDefault();
            App.getSharePreferenceUtil().getString("platformToken");
            String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
            String currentCityCode = UserManager.getInstance().getCurrentCityCode();
            String locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
            String locateCityCode = UserManager.getInstance().getLocateCityCode();
            UserManager.getInstance().getMenuNetType();
            String string = App.getInstance().getString(2131886969);
            String valueOf = String.valueOf(DeviceHelper.getDeviceOSVersionCode());
            String deviceBrand = DeviceHelper.getDeviceBrand();
            String deviceModel = DeviceHelper.getDeviceModel();
            String deviceID = DeviceHelper.getDeviceID(true);
            String loginType = UserManager.getInstance().getLoginType();
            String faceType = UserManager.getInstance().getFaceType();
            if ("29".equals(faceType) || "30".equals(faceType) || "31".equals(faceType)) {
                loginType = faceType;
            }
            String localIpAddress = SystemServiceUtils.getLocalIpAddress();
            DeviceHelper.getNETType(App.getInstance());
            float pixelRatio = UIUtils.getPixelRatio(App.getInstance());
            int px2dp = UIUtils.px2dp(UIUtils.getScreenWidth(App.getInstance()));
            strArr = strArr2;
            try {
                int px2dp2 = UIUtils.px2dp(UIUtils.getScreenHeight(App.getInstance()));
                int px2dp3 = UIUtils.px2dp(webView.getMeasuredWidth());
                int px2dp4 = UIUtils.px2dp(webView.getMeasuredHeight());
                int px2dp5 = UIUtils.px2dp(UIUtils.getStatusBarHeight(activity));
                boolean isZhediepingPadMode = DeviceHelper.isZhediepingPadMode(activity);
                if (!SystemServiceUtils.netIsAvailable()) {
                    z = isZhediepingPadMode;
                    i = px2dp;
                    str5 = "Unconnected";
                } else if (SystemServiceUtils.isWifiActive()) {
                    i = px2dp;
                    z = isZhediepingPadMode;
                    str5 = "Wifi";
                } else {
                    z = isZhediepingPadMode;
                    i = px2dp;
                    str5 = DeviceHelper.getCarrierType(activity) == -1 ? "" : "Cellular";
                }
                ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
                jSONObject.put("appVersion", string);
                jSONObject.put("devicedId", deviceID);
                jSONObject.put("deviceModel", deviceModel);
                jSONObject.put("deviceBrand", deviceBrand);
                jSONObject.put("networkType", str5);
                jSONObject.put("loginType", loginType);
                jSONObject.put("latitude", locationEntity.getBdLocation().getLatitude());
                jSONObject.put("longitude", locationEntity.getBdLocation().getLongitude());
                jSONObject.put("timeTemp", String.valueOf(SystemTimeUtil.currentTimeMillis()));
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("watchEid", str);
                }
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("watchDevicedId", str2);
                }
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("watchICCID", str3);
                }
                if (!TextUtils.isEmpty(str4)) {
                    jSONObject.put("watchIMSI", str4);
                }
                jSONObject.put("isLogin", App.hasLogined());
                jSONObject.put("currentPhoneNumber", UserManager.getInstance().getCurrentPhoneNumber());
                jSONObject.put("ywCode", ywCodeDefault);
                jSONObject.put("provinceCode", currentProvinceCode);
                jSONObject.put("cityCode", currentCityCode);
                jSONObject.put("provinceCode_1", locateProvinceCode);
                jSONObject.put("cityCode_1", locateCityCode);
                jSONObject.put("osVersion", valueOf);
                jSONObject.put("pip", localIpAddress);
                jSONObject.put("pixelRatio", pixelRatio);
                jSONObject.put("screenWidth", i);
                jSONObject.put("screenHeight", px2dp2);
                jSONObject.put("windowWidth", px2dp3);
                jSONObject.put("windowHeight", px2dp4);
                jSONObject.put("statusBarHeight", px2dp5);
                jSONObject.put("theme", "light");
                jSONObject.put("isZDPPadMode", z);
                jSONObject.put("liushuiId", String.valueOf(System.currentTimeMillis()));
                String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                MsLogUtil.m7979d("jsonLength", jSONObject2.length() + "");
                String ecbEncrypt = RSAUtil.ecbEncrypt(jSONObject2);
                String sign = RSAUtil.sign(ecbEncrypt);
                strArr[0] = ecbEncrypt;
                strArr[1] = sign;
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                return strArr;
            }
        } catch (Exception e2) {
            e = e2;
            strArr = strArr2;
        }
        return strArr;
    }

    public static boolean isVPNorProxy() {
        boolean z;
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService("connectivity");
        boolean z2 = connectivityManager.getDefaultProxy() != null;
        Network[] allNetworks = connectivityManager.getAllNetworks();
        int length = allNetworks.length;
        int i = 0;
        while (true) {
            if (i < length) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(allNetworks[i]);
                if (networkCapabilities != null && networkCapabilities.hasTransport(4)) {
                    z = true;
                    break;
                }
                i++;
            } else {
                z = false;
                break;
            }
        }
        return z2 || z;
    }
}
