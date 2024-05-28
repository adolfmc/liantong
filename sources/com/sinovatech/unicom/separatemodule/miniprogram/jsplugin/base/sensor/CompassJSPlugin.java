package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.web.MsJSEvent;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/compass")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CompassJSPlugin extends BaseJSPlugin {
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private MySensorEventListner sensorEventListner;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("onExec ");
            JSONObject jSONObject = this.originConfigJO;
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7980d(sb.toString());
            String string = this.parameterJO.getString("type");
            if ("start".equals(string)) {
                if (this.mSensorManager == null || this.mSensor == null) {
                    this.mSensorManager = (SensorManager) this.activityContext.getSystemService("sensor");
                    if (this.mSensorManager != null) {
                        this.mSensor = this.mSensorManager.getDefaultSensor(3);
                        if (this.mSensor != null) {
                            this.sensorEventListner = new MySensorEventListner();
                            this.mSensorManager.registerListener(this.sensorEventListner, this.mSensor, 3);
                        }
                    }
                    this.webFragment.addLifeListener("/MsJSPlugin/compass", new BaseWebFragment.LifeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.sensor.CompassJSPlugin.1
                        @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.LifeListener
                        public void onDestory() {
                            CompassJSPlugin.this.stop();
                        }
                    });
                }
                callbackSuccess(new JSONObject());
            } else if ("stop".equals(string)) {
                stop();
                callbackSuccess(new JSONObject());
            } else {
                callbackFail("10", "程序错误：类型不匹配");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stop() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this.sensorEventListner, this.mSensor);
            this.sensorEventListner = null;
            this.mSensor = null;
            this.mSensorManager = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MySensorEventListner implements SensorEventListener {
        private int accuracy;

        private MySensorEventListner() {
            this.accuracy = -1;
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            String str;
            try {
                if (sensorEvent.sensor.getType() == 3) {
                    float f = sensorEvent.values[0];
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("direction", f);
                    if (this.accuracy == 3) {
                        str = "high";
                    } else if (this.accuracy == 2) {
                        str = "medium";
                    } else if (this.accuracy == 1) {
                        str = "low";
                    } else if (this.accuracy == 0) {
                        str = "unreliable";
                    } else if (this.accuracy == -1) {
                        str = "no-contact";
                    } else {
                        str = "unkown" + this.accuracy;
                    }
                    jSONObject.put("androidAccuracy", str);
                    jSONObject.put("iosAccuracy", "");
                    MsLogUtil.m7980d("onSensorChanged direction=" + f + " accuracyStr=" + str);
                    CompassJSPlugin.this.webFragment.postEventToJS(MsJSEvent.onCompassSensorChanged, jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
            MsLogUtil.m7980d("onAccuracyChanged accuracy=" + i);
            this.accuracy = i;
        }
    }
}
