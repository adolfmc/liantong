package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import okhttp3.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getClientInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetClientInfoJSPlugin extends BaseJSPlugin {
    private String permissionHint = "edop小程序不允许使用getClientInfo接口，请申请getSystemInfo或者getUserInfo接口的使用权限";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    private boolean checkPermission() {
        String currentCumpAppId = getCurrentCumpAppId();
        return TextUtils.isEmpty(currentCumpAppId) || !currentCumpAppId.contains("edop_unicom");
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        if (checkPermission()) {
            return callbackSuccessSync(getClientInfo());
        }
        UIUtils.toastLong(this.permissionHint);
        return callbackFailSync(this.permissionHint);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (checkPermission()) {
            callbackSuccess(getClientInfo());
            return;
        }
        UIUtils.toastLong(this.permissionHint);
        callbackFail(this.permissionHint);
    }

    private JSONObject getClientInfo() throws Exception {
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if (currentPhoneNumber.equals("0")) {
            currentPhoneNumber = "";
        }
        String ecsToken = UserManager.getInstance().getEcsToken(currentPhoneNumber);
        String ywCodeDefault = UserManager.getInstance().getYwCodeDefault();
        String string = App.getSharePreferenceUtil().getString("platformToken");
        String currentProvinceCode = UserManager.getInstance().getCurrentProvinceCode();
        String currentCityCode = UserManager.getInstance().getCurrentCityCode();
        String locateProvinceCode = UserManager.getInstance().getLocateProvinceCode();
        String locateCityCode = UserManager.getInstance().getLocateCityCode();
        String menuNetType = UserManager.getInstance().getMenuNetType();
        String string2 = this.activityContext.getString(2131886969);
        String valueOf = String.valueOf(DeviceHelper.getDeviceOSVersionCode());
        String deviceBrand = DeviceHelper.getDeviceBrand();
        String deviceModel = DeviceHelper.getDeviceModel();
        String deviceID = DeviceHelper.getDeviceID(true);
        String loginType = UserManager.getInstance().getLoginType();
        String faceType = UserManager.getInstance().getFaceType();
        if (!"29".equals(faceType) && !"30".equals(faceType) && !"31".equals(faceType)) {
            faceType = loginType;
        }
        String localIpAddress = SystemServiceUtils.getLocalIpAddress();
        String nETType = DeviceHelper.getNETType(this.activityContext);
        float pixelRatio = UIUtils.getPixelRatio(this.activityContext);
        int px2dp = UIUtils.px2dp(this.activityContext, UIUtils.getScreenWidth(this.activityContext));
        int px2dp2 = UIUtils.px2dp(this.activityContext, UIUtils.getScreenHeight(this.activityContext));
        int px2dp3 = UIUtils.px2dp(this.activityContext, this.f18589wv.getMeasuredWidth());
        int px2dp4 = UIUtils.px2dp(this.activityContext, this.f18589wv.getMeasuredHeight());
        int px2dp5 = UIUtils.px2dp(this.activityContext, UIUtils.getStatusBarHeight(this.activityContext));
        boolean isZhediepingPadMode = DeviceHelper.isZhediepingPadMode(this.activityContext);
        JSONArray filterCookies = filterCookies();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("isLogin", App.hasLogined());
        jSONObject.put("currentPhoneNumber", currentPhoneNumber);
        jSONObject.put("ywCode", ywCodeDefault);
        jSONObject.put("provinceCode", currentProvinceCode);
        jSONObject.put("cityCode", currentCityCode);
        jSONObject.put("provinceCode_1", locateProvinceCode);
        jSONObject.put("cityCode_1", locateCityCode);
        jSONObject.put("netPayType", menuNetType);
        jSONObject.put("appVersion", string2);
        jSONObject.put("osVersion", valueOf);
        jSONObject.put("deviceBrand", deviceBrand);
        jSONObject.put("deviceModel", deviceModel);
        jSONObject.put("devicedId", deviceID);
        jSONObject.put("loginType", faceType);
        jSONObject.put("pip", localIpAddress);
        jSONObject.put("netWay", nETType);
        jSONObject.put("pixelRatio", pixelRatio);
        jSONObject.put("screenWidth", px2dp);
        jSONObject.put("screenHeight", px2dp2);
        jSONObject.put("windowWidth", px2dp3);
        jSONObject.put("windowHeight", px2dp4);
        jSONObject.put("statusBarHeight", px2dp5);
        jSONObject.put("theme", "light");
        jSONObject.put("isZDPPadMode", isZhediepingPadMode);
        if (WebHostSafelyManager.Strategy_WhiteOrPass.equals(WebHostSafelyManager.getInstance().interceptEcsTokenHost(this.webFragment.currentURLForPlugin))) {
            jSONObject.put("ecsToken", ecsToken);
            jSONObject.put("huaweiPushToken", string);
            jSONObject.put("cookies", filterCookies);
        }
        return jSONObject;
    }

    private JSONArray filterCookies() {
        JSONArray jSONArray = new JSONArray();
        for (Cookie cookie : App.getPersistentCookiesList()) {
            try {
                if ("u_account".equals(cookie.name())
                        || "a_token".equals(cookie.name())
                        || "c_id".equals(cookie.name())
                        || "u_type".equals(cookie.name())
                        || "login_type".equals(cookie.name())
                        || "city".equals(cookie.name())
                        || "c_version".equals(cookie.name())
                        || "enc_acc".equals(cookie.name())
                        || "ecs_acc".equals(cookie.name())
                        || "random_login".equals(cookie.name())
                        || "cw_mutual".equals(cookie.name())
                        || "t3_token".equals(cookie.name())
                        || "invalid_at".equals(cookie.name())
                        || "c_mobile".equals(cookie.name())
                        || "wo_family".equals(cookie.name())
                        || "u_areaCode".equals(cookie.name())
                        || "third_token".equals(cookie.name())
                        || "ecs_token".equals(cookie.name())
                        || "jwt".equals(cookie.name())
                        || "yw_login".equals(cookie.name())
                        || "mail_account".equals(cookie.name())) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("name", cookie.name());
                    jSONObject.put("value", cookie.value());
                    jSONObject.put("domain", cookie.domain());
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }
}
