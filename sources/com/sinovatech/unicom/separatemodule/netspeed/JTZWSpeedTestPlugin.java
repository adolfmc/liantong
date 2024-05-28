package com.sinovatech.unicom.separatemodule.netspeed;

import android.annotation.SuppressLint;
import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chinaunicon.jtwifilib.jtcommon.JtOnWifiClientSpeedListener;
import com.chinaunicon.jtwifilib.jtcommon.XWIFI;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/JTZWSpeedTest")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JTZWSpeedTestPlugin extends BaseJSPlugin {
    public static String TAG = "NetSpeedJsPlugin";
    private XWIFI xwifi;

    /* JADX INFO: Access modifiers changed from: private */
    public String changeWifiSpeedType(int i) {
        switch (i) {
            case 0:
                return "XWIFI.GET_INTERNET_START";
            case 1:
                return "XWIFI.GET_INTERNET_END";
            case 2:
                return "XWIFI.START_SPEED";
            case 3:
                return "XWIFI.SPEEDING";
            case 4:
                return "XWIFI.END_SPEED";
            case 5:
            case 6:
            default:
                return "XWIFI.GET_INTERNET_START";
            case 7:
                return "XWIFI.START_NETWORK_STATUS";
            case 8:
                return "XWIFI.END_NETWORK_STATUS";
            case 9:
                return "XWIFI.START_UPLOAD_SPEED";
            case 10:
                return "XWIFI.END_UPLOAD_SPEED";
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    @SuppressLint({"CheckResult"})
    public void onExec() throws Exception {
        try {
            new RxPermissions(this.activityContext).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.netspeed.JTZWSpeedTestPlugin.1
                @Override // io.reactivex.functions.Consumer
                @SuppressLint({"CheckResult"})
                public void accept(Boolean bool) throws Exception {
                    if (bool.booleanValue()) {
                        String optString = JTZWSpeedTestPlugin.this.parameterJO.optString("action");
                        if ("pause".equals(optString)) {
                            JTZWSpeedTestPlugin jTZWSpeedTestPlugin = JTZWSpeedTestPlugin.this;
                            jTZWSpeedTestPlugin.xwifi = NetSpeedManeger.getInstance(jTZWSpeedTestPlugin.activityContext);
                            if (JTZWSpeedTestPlugin.this.xwifi != null) {
                                JTZWSpeedTestPlugin.this.xwifi.stop();
                            }
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("msg", "用户主动取消测速");
                            jSONObject.put("code", "113");
                            JTZWSpeedTestPlugin.this.callbackFail(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                            return;
                        }
                        if ("init".equals(optString)) {
                            JSONObject optJSONObject = JTZWSpeedTestPlugin.this.parameterJO.optJSONObject("params");
                            NetSpeedManeger.initSDk();
                            JTZWSpeedTestPlugin jTZWSpeedTestPlugin2 = JTZWSpeedTestPlugin.this;
                            jTZWSpeedTestPlugin2.xwifi = NetSpeedManeger.getInstance(jTZWSpeedTestPlugin2.activityContext);
                            JTZWSpeedTestPlugin.this.xwifi.initSpeed(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject));
                            JTZWSpeedTestPlugin.this.xwifi.start();
                            TYCJBoxManager.getInstance().collectClickSdk(SoulPermission.getInstance().getTopActivity(), "S2ndpage1214", JTZWSpeedTestPlugin.this.f18589wv.getTitle(), "wifi测速", JTZWSpeedTestPlugin.this.getCurrentURL(), "com.chinaunicon.jtwifilib", "1");
                        } else {
                            MsLogUtil.m7979d(JTZWSpeedTestPlugin.TAG, "无符合信息");
                        }
                        JTZWSpeedTestPlugin.this.xwifi.setOnWifiSpeedListener(new JtOnWifiClientSpeedListener() { // from class: com.sinovatech.unicom.separatemodule.netspeed.JTZWSpeedTestPlugin.1.1
                            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiClientSpeedListener, com.chinaunicon.jtwifilib.jtcommon.JtOnWifiSpeedListener
                            public void filed(String str) {
                                super.filed(str);
                                JTZWSpeedTestPlugin.this.callbackFail(str);
                                MsLogUtil.m7979d(JTZWSpeedTestPlugin.TAG, str);
                            }

                            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiClientSpeedListener
                            public void onWifiSpeed(String str, int i, String str2) {
                                super.onWifiSpeed(str, i, str2);
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("speed", str);
                                    jSONObject2.put("type", JTZWSpeedTestPlugin.this.changeWifiSpeedType(i));
                                    jSONObject2.put("speedInfo", str2);
                                    JTZWSpeedTestPlugin.this.callbackSuccess(jSONObject2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnWifiClientSpeedListener
                            public void onSuccess(String str) {
                                super.onSuccess(str);
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("type", "XWIFI.SUCCESS");
                                    JSONObject jSONObject3 = new JSONObject(str);
                                    Iterator<String> keys = jSONObject3.keys();
                                    while (keys.hasNext()) {
                                        String next = keys.next();
                                        jSONObject2.put(next, jSONObject3.optString(next));
                                    }
                                    JTZWSpeedTestPlugin.this.callbackSuccess(jSONObject2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        return;
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("msg", "用户未授权系统权限");
                    jSONObject2.put("code", "11");
                    JTZWSpeedTestPlugin.this.callbackFail(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.netspeed.JTZWSpeedTestPlugin.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("msg", th.getMessage());
                    jSONObject.put("code", "10");
                    JTZWSpeedTestPlugin.this.callbackFail(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                }
            });
            this.webFragment.addLifeListener("/MsJSPlugin/JTZWSpeedTest", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.netspeed.JTZWSpeedTestPlugin.3
                @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                public void onDestory() {
                    MsLogUtil.m7979d("测速sdk页面销毁", "-------------》");
                    if (JTZWSpeedTestPlugin.this.xwifi != null) {
                        JTZWSpeedTestPlugin.this.xwifi.onDestroy();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("msg", e.getMessage());
            jSONObject.put("code", "10");
            callbackFail(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7977e(TAG, e.getMessage());
        }
    }
}
