package com.megvii.lv5;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.g5 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5456g5 implements SensorEventListener {

    /* renamed from: a */
    public SensorManager f12690a;

    /* renamed from: b */
    public Sensor f12691b;

    /* renamed from: c */
    public float f12692c = 8.0f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.g5$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C5457a {

        /* renamed from: a */
        public static final C5456g5 f12693a = new C5456g5();
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null) {
            try {
                float[] fArr = sensorEvent.values;
                if (fArr != null && fArr.length > 1) {
                    this.f12692c = fArr[1];
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.f12692c = 0.0f;
    }
}
