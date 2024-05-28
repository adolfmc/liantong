package com.megvii.livenesslib.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SensorUtil implements SensorEventListener {

    /* renamed from: Y */
    private float f12358Y;
    boolean isCunZaiChuanGanQi = false;
    private Sensor mSensor;
    private SensorManager mSensorManager;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public SensorUtil(Context context) {
        init(context);
    }

    private void init(Context context) {
        try {
            this.mSensorManager = (SensorManager) context.getSystemService("sensor");
            this.mSensor = this.mSensorManager.getDefaultSensor(1);
            if (this.mSensor != null) {
                this.mSensorManager.registerListener(this, this.mSensor, 3);
            }
            for (Sensor sensor : this.mSensorManager.getSensorList(-1)) {
                if (sensor.getType() == 1) {
                    this.isCunZaiChuanGanQi = true;
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.f12358Y = sensorEvent.values[1];
    }

    public void release() {
        SensorManager sensorManager;
        if (this.mSensor == null || (sensorManager = this.mSensorManager) == null) {
            return;
        }
        sensorManager.unregisterListener(this);
    }

    public boolean isVertical() {
        return !this.isCunZaiChuanGanQi || this.f12358Y >= 7.0f;
    }
}
