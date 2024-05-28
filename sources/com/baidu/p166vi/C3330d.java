package com.baidu.p166vi;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.vi.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
class C3330d implements SensorEventListener {

    /* renamed from: a */
    final /* synthetic */ VCompass f8207a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C3330d(VCompass vCompass) {
        this.f8207a = vCompass;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float m17407a;
        if (sensorEvent.sensor.getType() != 3) {
            return;
        }
        m17407a = this.f8207a.m17407a(sensorEvent.values[0]);
        this.f8207a.updateCompass((int) m17407a);
    }
}
