package com.example.asus.detectionandalign.utils;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;

/* renamed from: com.example.asus.detectionandalign.utils.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4295g implements SensorEventListener {

    /* renamed from: a */
    private Handler f10093a;

    public C4295g(Handler handler) {
        this.f10093a = handler;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        int i;
        float[] fArr = sensorEvent.values;
        float f = -fArr[0];
        float f2 = -fArr[1];
        float f3 = -fArr[2];
        if (((f * f) + (f2 * f2)) * 4.0f >= f3 * f3) {
            i = 90 - Math.round(((float) Math.atan2(-f2, f)) * 57.29578f);
            while (i >= 360) {
                i -= 360;
            }
            while (i < 0) {
                i += 360;
            }
        } else {
            i = -1;
        }
        Handler handler = this.f10093a;
        if (handler != null) {
            handler.obtainMessage(888, i, 0).sendToTarget();
        }
    }
}
