package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/newWebRtc")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NewWebRtcJsPlugin extends BaseJSPlugin {
    private static final String TAG = "NewWebRtcJsPlugin";

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        char c;
        String optString = this.parameterJO.optString("action");
        UIUtils.logD(TAG, "========action=" + optString);
        int optInt = this.parameterJO.optInt("serviceType");
        int hashCode = optString.hashCode();
        if (hashCode != -1242674731) {
            if (hashCode == -320707588 && optString.equals("hiddenWebRtcView")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (optString.equals("remoteVideoWeatherOffCamera")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                boolean optBoolean = this.parameterJO.optBoolean("off");
                if (optInt == 1) {
                    if (RtcHelper.getInstance() != null) {
                        RtcHelper.getInstance().setSwitchCamera(optBoolean);
                        return;
                    } else {
                        null2show();
                        return;
                    }
                } else if (optInt == 2) {
                    if (RtcVoiceHelper.getInstance() != null) {
                        RtcVoiceHelper.getInstance().setSwitchCamera(optBoolean);
                        return;
                    } else {
                        null2show();
                        return;
                    }
                } else {
                    return;
                }
            case 1:
                boolean optBoolean2 = this.parameterJO.optBoolean("hidden");
                if (optInt == 1) {
                    RtcHelper.getInstance().hiddenWebRtcView(optBoolean2);
                    callbackStatus("webRtc窗口隐藏", true);
                    return;
                } else if (optInt == 2) {
                    RtcVoiceHelper.getInstance().hiddenWebRtcView(optBoolean2);
                    callbackStatus("webRtcVideoToAudio窗口隐藏", true);
                    return;
                } else {
                    callbackStatus("", false);
                    return;
                }
            default:
                return;
        }
    }

    private void null2show() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("code", 4);
            jSONObject.put("msg", "获取janus管理对象失败，请确认第一步init方法是否执行成功。");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        callbackSuccess(jSONObject);
    }

    private void callbackStatus(String str, boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (z) {
            try {
                jSONObject.put("msg", str);
                callbackSuccess(jSONObject);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        try {
            jSONObject.put("msg", "调用失败");
            callbackFail(jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
