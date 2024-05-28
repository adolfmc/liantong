package com.baidu.p120ar.imu;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.VpasProvider */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VpasProvider extends ImprovedOrientationSensor2Provider {
    private float[] gravityValues;
    private float[] magnitudeValues;

    public VpasProvider(SensorManager sensorManager) {
        super(sensorManager);
        this.magnitudeValues = new float[3];
        this.gravityValues = new float[3];
        this.sensorList.add(sensorManager.getDefaultSensor(9));
        this.sensorList.add(sensorManager.getDefaultSensor(2));
        this.sensorList.add(sensorManager.getDefaultSensor(4));
        this.sensorList.add(sensorManager.getDefaultSensor(11));
    }

    @Override // com.baidu.p120ar.imu.ImprovedOrientationSensor2Provider, android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 11 || sensorEvent.sensor.getType() == 4) {
            super.onSensorChanged(sensorEvent);
        } else if (sensorEvent.sensor.getType() == 2) {
            this.magnitudeValues = (float[]) sensorEvent.values.clone();
        } else if (sensorEvent.sensor.getType() == 9) {
            this.gravityValues = (float[]) sensorEvent.values.clone();
            this.mGravityValues = this.gravityValues;
        }
        if (this.magnitudeValues == null || this.gravityValues == null) {
            return;
        }
        SensorManager.getRotationMatrix(this.currentOrientationRotationMatrix.matrix, new float[16], this.gravityValues, this.magnitudeValues);
        this.currentOrientationQuaternion.setRowMajor(this.currentOrientationRotationMatrix.matrix);
    }
}
