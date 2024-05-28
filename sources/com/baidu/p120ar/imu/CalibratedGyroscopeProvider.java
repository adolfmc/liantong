package com.baidu.p120ar.imu;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.CalibratedGyroscopeProvider */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CalibratedGyroscopeProvider extends OrientationProvider {
    private static final double EPSILON = 0.10000000149011612d;
    private static final float NS2S = 1.0E-9f;
    private static final String TAG = "CalibratedGyroscopeProvider";
    private final Quaternion deltaQuaternion;
    private double gyroscopeRotationVelocity;
    private int mImuGyroDatacount;
    private long timestamp;

    public CalibratedGyroscopeProvider(SensorManager sensorManager) {
        super(sensorManager);
        this.deltaQuaternion = new Quaternion();
        this.mImuGyroDatacount = 0;
        this.gyroscopeRotationVelocity = 0.0d;
        String str = TAG;
        ARLog.m20421d(str, "sensorList size " + String.valueOf(this.sensorList.size()));
        if (this.sensorList.size() > 120) {
            this.sensorList.clear();
        }
        this.sensorList.add(sensorManager.getDefaultSensor(4));
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 4) {
            if (this.timestamp != 0) {
                float f = ((float) (sensorEvent.timestamp - this.timestamp)) * 1.0E-9f;
                float f2 = sensorEvent.values[0];
                float f3 = sensorEvent.values[1];
                float f4 = sensorEvent.values[2];
                this.gyroscopeRotationVelocity = Math.sqrt((f2 * f2) + (f3 * f3) + (f4 * f4));
                double d = this.gyroscopeRotationVelocity;
                if (d > 0.10000000149011612d) {
                    f2 = (float) (f2 / d);
                    f3 = (float) (f3 / d);
                    f4 = (float) (f4 / d);
                }
                double d2 = (this.gyroscopeRotationVelocity * f) / 2.0d;
                double sin = Math.sin(d2);
                double cos = Math.cos(d2);
                this.deltaQuaternion.setX((float) (f2 * sin));
                this.deltaQuaternion.setY((float) (f3 * sin));
                this.deltaQuaternion.setZ((float) (sin * f4));
                this.deltaQuaternion.setW(-((float) cos));
                synchronized (this.syncToken) {
                    this.deltaQuaternion.multiplyByQuat(this.currentOrientationQuaternion, this.currentOrientationQuaternion);
                }
                Quaternion m24463clone = this.currentOrientationQuaternion.m24463clone();
                m24463clone.m20431w(-m24463clone.m20432w());
                synchronized (this.syncToken) {
                    SensorManager.getRotationMatrixFromVector(this.currentOrientationRotationMatrix.matrix, m24463clone.toArray());
                }
            }
            this.timestamp = sensorEvent.timestamp;
            this.mImuGyroDatacount++;
            if (this.mImuGyroDatacount > 20) {
                transformMatrix();
                setChanged();
                notifyObservers();
            }
        }
    }

    private void transformMatrix() {
        if (!this.mInitMFlag) {
            System.arraycopy(this.currentOrientationRotationMatrix.matrix, 0, this.mInitOrientationRotationMatrix.matrix, 0, this.mInitOrientationRotationMatrix.matrix.length);
            Matrix.setIdentityM(this.mFinalRotatedOrientationRotationMatrix.matrix, 0);
            this.mInitMFlag = true;
            return;
        }
        toFinalRotationMatrix(this.mFinalRotatedOrientationRotationMatrix.matrix, this.currentOrientationRotationMatrix.matrix, this.mInitOrientationRotationMatrix.matrix);
    }
}
