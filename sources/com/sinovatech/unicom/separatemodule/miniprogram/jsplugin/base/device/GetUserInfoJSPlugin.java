package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager;
import okhttp3.Cookie;
import org.json.JSONArray;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getUserInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetUserInfoJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return callbackSuccessSync(getClientInfo());
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
        String loginType = UserManager.getInstance().getLoginType();
        String faceType = UserManager.getInstance().getFaceType();
        if ("29".equals(faceType) || "30".equals(faceType) || "31".equals(faceType)) {
            loginType = faceType;
        }
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
        jSONObject.put("loginType", loginType);
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
