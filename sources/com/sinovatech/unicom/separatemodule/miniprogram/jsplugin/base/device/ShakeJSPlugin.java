package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Base64;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import java.net.URLEncoder;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/shake")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ShakeJSPlugin extends BaseJSPlugin {
    private JSONObject mConfigJO;
    private ShakeHelper shakeHelper;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void exec(Activity activity, BaseWebFragment baseWebFragment, WebView webView, String str) throws Exception {
        try {
            this.activityContext = activity;
            this.webFragment = baseWebFragment;
            this.f18589wv = webView;
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject("parameter");
            String optString = optJSONObject.optString("type", "start");
            if ("start".equals(optString)) {
                if (this.mConfigJO == null) {
                    this.mConfigJO = jSONObject;
                    boolean optBoolean = optJSONObject.optBoolean("vibrator", false);
                    if (this.shakeHelper != null) {
                        this.shakeHelper.stop();
                        this.shakeHelper = null;
                    }
                    this.shakeHelper = new ShakeHelper(this.activityContext, optBoolean);
                    this.shakeHelper.start();
                    baseWebFragment.addLifeListener("/MsJSPlugin/shake", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device.ShakeJSPlugin.1
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                        public void onDestory() {
                            if (ShakeJSPlugin.this.shakeHelper != null) {
                                ShakeJSPlugin.this.shakeHelper.stop();
                            }
                            ShakeJSPlugin.this.shakeHelper = null;
                            ShakeJSPlugin.this.mConfigJO = null;
                        }
                    });
                }
            } else if (!"stop".equals(optString) || this.mConfigJO == null || this.shakeHelper == null) {
            } else {
                this.shakeHelper.stop();
                callbackSucces("12", "手动关闭服务");
                this.shakeHelper = null;
                this.mConfigJO = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("11", "程序错误:" + e.getMessage());
        }
    }

    private void callbackFromNative(String str, Object obj) {
        try {
            if (this.mConfigJO != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("status", str);
                jSONObject.put("data", obj);
                this.mConfigJO.put("parameter", jSONObject);
                JSONObject jSONObject2 = this.mConfigJO;
                String encodeToString = Base64.encodeToString(URLEncoder.encode(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), "utf-8").getBytes("utf-8"), 0);
                WebView webView = this.f18589wv;
                String str2 = "javascript:MsJSBridge.callbackFromNative('" + encodeToString + "')";
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                } else {
                    webView.loadUrl(str2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void callbackFail(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
            callbackFromNative("fail", jSONObject);
            this.shakeHelper = null;
            this.mConfigJO = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callbackSucces(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("status", str);
            jSONObject.put("msg", str2);
            callbackFromNative("success", jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class ShakeHelper implements SensorEventListener {
        private Handler handler;
        private boolean isShake = false;
        private boolean isVibrator;
        private Context mContext;
        private Sensor mSensor;
        private SensorManager mSensorManager;
        private Runnable runnable;

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public ShakeHelper(Context context, boolean z) {
            this.isVibrator = false;
            this.mContext = context;
            this.isVibrator = z;
        }

        public void start() {
            this.mSensorManager = (SensorManager) this.mContext.getSystemService("sensor");
            SensorManager sensorManager = this.mSensorManager;
            if (sensorManager != null) {
                this.mSensor = sensorManager.getDefaultSensor(1);
            }
            Sensor sensor = this.mSensor;
            if (sensor != null) {
                this.mSensorManager.registerListener(this, sensor, 3);
                ShakeJSPlugin.this.callbackSucces("00", "注册成功");
                this.handler = new Handler();
                this.runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device.ShakeJSPlugin.ShakeHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (ShakeHelper.this.isShake) {
                            return;
                        }
                        ShakeHelper.this.isShake = true;
                        ShakeHelper.this.stop();
                        ShakeJSPlugin.this.callbackFail("10", "超时自动关闭服务");
                    }
                };
                this.handler.postDelayed(this.runnable, 30000L);
            }
        }

        public void stop() {
            try {
                this.mSensorManager.unregisterListener(this);
                this.handler.removeCallbacks(this.runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == 1) {
                float[] fArr = sensorEvent.values;
                float f = fArr[0];
                float f2 = fArr[1];
                float f3 = fArr[2];
                MsLogUtil.m7980d("摇一摇：" + f + " " + f2 + " " + f3);
                float f4 = (float) 15;
                if ((Math.abs(f) > f4 || Math.abs(f2) > f4 || Math.abs(f3) > f4) && !this.isShake) {
                    this.isShake = true;
                    ShakeJSPlugin.this.callbackSucces("01", "摇动了手机");
                    stop();
                    ShakeJSPlugin.this.shakeHelper = null;
                    ShakeJSPlugin.this.mConfigJO = null;
                    try {
                        if (this.isVibrator) {
                            ((Vibrator) this.mContext.getSystemService("vibrator")).vibrate(500L);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
