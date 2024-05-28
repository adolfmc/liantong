package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.huawei.hms.push.AttributionReporter;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.nfc.utils.NfcUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/systemPermission")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SystemPermissionJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String string = this.parameterJO.getString("type");
        String string2 = this.parameterJO.getString(AttributionReporter.SYSTEM_PERMISSION);
        if ("checkSystemPermission".equals(string)) {
            checkSystemPermission(string2);
        }
    }

    private void checkSystemPermission(String str) throws Exception {
        new RxPermissions(this.activityContext);
        if ("camera".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.CAMERA").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("location".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.ACCESS_FINE_LOCATION").isGranted()) {
                if (SoulPermission.getInstance().checkSinglePermission("android.permission.ACCESS_COARSE_LOCATION").isGranted()) {
                    callbackSuccess(true);
                    return;
                } else {
                    callbackFail("没有权限");
                    return;
                }
            }
            callbackFail("没有权限");
        } else if ("systemLocation".equals(str)) {
            if (DeviceHelper.isLocationEnabled()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("contacts".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.READ_CONTACTS").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("microphone".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.RECORD_AUDIO").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("storage".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.READ_EXTERNAL_STORAGE").isGranted()) {
                if (SoulPermission.getInstance().checkSinglePermission("android.permission.WRITE_EXTERNAL_STORAGE").isGranted()) {
                    callbackSuccess(true);
                    return;
                } else {
                    callbackFail("没有权限");
                    return;
                }
            }
            callbackFail("没有权限");
        } else if ("phone".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("callphone".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.CALL_PHONE").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("sendsms".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.SEND_SMS").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("calllog".equals(str)) {
            if (SoulPermission.getInstance().checkSinglePermission("android.permission.READ_CALL_LOG").isGranted()) {
                callbackSuccess(true);
            } else {
                callbackFail("没有权限");
            }
        } else if ("nfcPermission".equals(str)) {
            try {
                if (this.activityContext.getPackageManager().hasSystemFeature("android.hardware.nfc")) {
                    if (NfcUtils.hasNfc(this.activityContext)) {
                        callbackSuccess("10", "当前NFC已开启");
                    } else {
                        callbackSuccess("11", "当前NFC未开启");
                    }
                } else {
                    callbackSuccess("12", "当前设备不支持NFC功能");
                }
            } catch (Exception e) {
                MsLogUtil.m7977e("NFC功能异常:", e.getMessage());
                callbackFail("NFC功能异常:" + e.getMessage());
            }
        }
    }

    private void callbackSuccess(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", str);
            jSONObject.put("msg", str2);
            callbackSuccess(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callbackFail(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", str);
            callbackFail(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
