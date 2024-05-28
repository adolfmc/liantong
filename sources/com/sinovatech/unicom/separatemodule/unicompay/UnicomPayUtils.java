package com.sinovatech.unicom.separatemodule.unicompay;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.text.TextUtils;
import android.view.WindowManager;
import com.baidu.location.BDLocation;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin;
import com.sinovatech.unicom.basic.server.CityCodingManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.collect.CollectAddressEntity;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.CollectUtils;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.bean.ConfigInfo;
import com.unicom.pay.common.bean.EnvConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomPayUtils {
    private static UnicomPayUtils payUtils;
    private Activity activityContext;
    private Map<String, String> headerMap;
    private ManagerMainLogin managerMainLogin = ManagerMainLogin.getManagerMainLogin();
    private final UserManager userManager = UserManager.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$refreshToken$0() {
    }

    public UnicomPayUtils(Activity activity) {
        this.activityContext = activity;
    }

    public static UnicomPayUtils getInstance(Activity activity) {
        if (payUtils == null) {
            synchronized (UnicomPayUtils.class) {
                if (payUtils == null) {
                    payUtils = new UnicomPayUtils(activity);
                }
            }
        }
        return payUtils;
    }

    public void refreshToken(Activity activity) {
        this.managerMainLogin.welcomRefreshToken(activity, "2", new ManagerMainLogin.LoginCompleteInterface() { // from class: com.sinovatech.unicom.separatemodule.unicompay.-$$Lambda$UnicomPayUtils$YO3hAvk1xaPxiykb7oQeAD5bwGM
            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMainLogin.LoginCompleteInterface
            public final void complete() {
                UnicomPayUtils.lambda$refreshToken$0();
            }
        });
    }

    public void loginPaySdk() {
        try {
            UnicomPaySDK.getInstance().login(UserManager.getInstance().getCurrentPhoneType(), UserManager.getInstance().getYwCode(UserManager.getInstance().getCurrentPhoneNumber()), UserManager.getInstance().getCurrentPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
            String str = UnicomPayJSPlugin.TAG;
            MsLogUtil.m7979d(str, "支付sdk登录异常" + e.getMessage());
        }
    }

    public void loginOutPaySdk() {
        try {
            this.userManager.savePriToken("");
            UnicomPaySDK.getInstance().logout();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initSdk(Application application) {
        try {
            ConfigInfo configInfo = new ConfigInfo();
            configInfo.setOpenLog(true);
            configInfo.setChannelCode(UnicomPayConfig.channelCode);
            if (URLEnvironmentConfig.isProductionEnvironment()) {
                configInfo.setEnvConfig(EnvConfig.PRO);
            } else {
                if (!URLEnvironmentConfig.isPrepubEnvironment() && !URLEnvironmentConfig.isPreMoxEnvironment() && !URLEnvironmentConfig.isPrepubEnvironment2()) {
                    configInfo.setEnvConfig(EnvConfig.SIT);
                }
                configInfo.setEnvConfig(EnvConfig.UAT);
            }
            String replace = application.getResources().getString(2131886969).replace("android@", "");
            MsLogUtil.m7979d("initUnifiedPaySdk", "版本号" + replace);
            configInfo.setAppVersion(replace);
            UnicomPaySDK.getInstance().init(application, configInfo);
            TYCJBoxManager.getInstance().collectClickSdk(SoulPermission.getInstance().getTopActivity(), "S2ndpage1214", "", "统一支付", "", UnicomPayConfig.paySdkPackage, "0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getHeaderMap() {
        if (this.headerMap == null) {
            this.headerMap = new HashMap();
        }
        return this.headerMap;
    }

    public void gotoWebViewActivity(Activity activity, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || IntentManager.handleLocal(activity, str2, str) || TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle(str2);
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        webParamsEntity.setReferer(str3);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        activity.startActivity(intent);
    }

    public void collect(Map<String, Object> map) {
        try {
            map.putAll(getDataMap());
            JSONObject mapToJson = new CollectUtils().mapToJson(map);
            MsLogUtil.m7979d("collectPay", !(mapToJson instanceof JSONObject) ? mapToJson.toString() : NBSJSONObjectInstrumentation.toString(mapToJson));
            NBSAppAgent.customActionStart("androidpoint");
            if (URLEnvironmentConfig.isForPublish()) {
                NBSAppAgent.customActionEnd("androidpoint", "dis", map);
            } else {
                NBSAppAgent.customActionEnd("androidpoint", "test", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, Object> getDataMap() throws Exception {
        String currentPhoneNumber = App.hasLogined() ? UserManager.getInstance().getCurrentPhoneNumber() : "";
        HashMap hashMap = new HashMap();
        hashMap.put("uid", currentPhoneNumber);
        hashMap.put("cid", DeviceHelper.getDeviceID(false));
        hashMap.put("client_time", formatTime(SystemTimeUtil.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        int width = ((WindowManager) this.activityContext.getSystemService("window")).getDefaultDisplay().getWidth();
        int height = ((WindowManager) this.activityContext.getSystemService("window")).getDefaultDisplay().getHeight();
        hashMap.put("res", width + "x" + height);
        String str = "直立式";
        hashMap.put("device_types", (UIUtils.isXiaoMiFoldableDevice() || UIUtils.isViVOFoldableDevice()) ? "折叠式" : "折叠式");
        hashMap.put("device_type", DeviceHelper.getDeviceModel());
        hashMap.put("province_code", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("city_code", UserManager.getInstance().getCurrentCityCode());
        CollectAddressEntity addressEntity = getAddressEntity();
        hashMap.put("gps_province_code", addressEntity.getLocateProvinceCode());
        hashMap.put("gps_city_code", addressEntity.getLocateCityCode());
        hashMap.put("is_outside", "N");
        hashMap.put("session_id", CollectConfig.sessionId);
        String str2 = "";
        String accountTypeUser = LoginManager.getAccountTypeUser();
        if (!TextUtils.isEmpty(accountTypeUser)) {
            if (accountTypeUser.equals("1")) {
                str2 = "1";
            } else if (accountTypeUser.equals("2")) {
                str2 = "4";
            } else if (accountTypeUser.equals("3")) {
                str2 = "2";
            } else if (accountTypeUser.equals("4")) {
                str2 = "3";
            } else {
                str2 = accountTypeUser.equals("0") ? "6" : "5";
            }
        }
        hashMap.put("network_type", str2);
        hashMap.put("app_version", App.getInstance().getVersion());
        return hashMap;
    }

    public CollectAddressEntity getAddressEntity() {
        CollectAddressEntity collectAddressEntity = new CollectAddressEntity();
        try {
            ManagerLocation.LocationEntity locationEntity = ManagerLocation.getInstance().getLocationEntity();
            if (locationEntity.isLocationSuccess()) {
                BDLocation bdLocation = locationEntity.getBdLocation();
                double latitude = bdLocation.getLatitude();
                double longitude = bdLocation.getLongitude();
                String city = bdLocation.getCity();
                if ("市".equals(city.substring(city.length() - 1, city.length()))) {
                    city = city.substring(0, city.length() - 1);
                }
                String privienceCode = CityCodingManager.privienceCode(App.getInstance(), city);
                String cityCode = CityCodingManager.cityCode(App.getInstance(), city);
                MsLogUtil.m7979d("UnicomCollectManager", "latitude:" + latitude + "   longitude:" + longitude + "  cityName:" + city + "  provinceCode:" + privienceCode + "  cityCode:" + cityCode);
                collectAddressEntity.setLatitude(latitude);
                collectAddressEntity.setLongitude(longitude);
                collectAddressEntity.setCityName(city);
                collectAddressEntity.setLocateProvinceCode(privienceCode);
                collectAddressEntity.setLocateCityCode(cityCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return collectAddressEntity;
    }

    public String formatTime(long j, String str) {
        try {
            return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
        } catch (Exception e) {
            MsLogUtil.m7979d("UnicomCollectManager", e.getMessage());
            return "";
        }
    }
}
