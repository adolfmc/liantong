package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getSystemInfo")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetSystemInfoJSPlugin extends BaseJSPlugin {
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
        String string = this.activityContext.getString(2131886969);
        String valueOf = String.valueOf(DeviceHelper.getDeviceOSVersionCode());
        String deviceBrand = DeviceHelper.getDeviceBrand();
        String deviceModel = DeviceHelper.getDeviceModel();
        String deviceID = DeviceHelper.getDeviceID(true);
        String localIpAddress = SystemServiceUtils.getLocalIpAddress();
        String nETType = DeviceHelper.getNETType(this.activityContext);
        float pixelRatio = UIUtils.getPixelRatio(this.activityContext);
        int px2dp = UIUtils.px2dp(this.activityContext, UIUtils.getScreenWidth(this.activityContext));
        int px2dp2 = UIUtils.px2dp(this.activityContext, UIUtils.getScreenHeight(this.activityContext));
        int px2dp3 = UIUtils.px2dp(this.activityContext, this.f18589wv.getMeasuredWidth());
        int px2dp4 = UIUtils.px2dp(this.activityContext, this.f18589wv.getMeasuredHeight());
        int px2dp5 = UIUtils.px2dp(this.activityContext, UIUtils.getStatusBarHeight(this.activityContext));
        boolean isZhediepingPadMode = DeviceHelper.isZhediepingPadMode(this.activityContext);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appVersion", string);
        jSONObject.put("osVersion", valueOf);
        jSONObject.put("deviceBrand", deviceBrand);
        jSONObject.put("deviceModel", deviceModel);
        jSONObject.put("devicedId", deviceID);
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
        return jSONObject;
    }
}
