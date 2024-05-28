package com.baidu.p120ar.imu;

import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.ImprovedOrientationSensor3Provider */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImprovedOrientationSensor3Provider extends OrientationProvider {
    private static final double EPSILON = 0.10000000149011612d;
    private static final float INDIRECT_INTERPOLATION_WEIGHT = 0.01f;
    private static final float NS2S = 1.0E-9f;
    private static final float OUTLIER_PANIC_THRESHOLD = 0.0f;
    private static final float OUTLIER_THRESHOLD = 0.0f;
    private static final int PANIC_THRESHOLD = 60;
    private static final String TAG = "ImprovedOrientationSensor3Provider";
    private final Quaternion deltaQuaternion;
    private double gyroscopeRotationVelocity;
    private int mImuGyroDatacount;
    private int panicCounter;
    private boolean positionInitialised;
    private Quaternion quaternionGyroscope;
    private Quaternion quaternionRotationVector;
    private long timestamp;

    public ImprovedOrientationSensor3Provider(SensorManager sensorManager) {
        super(sensorManager);
        this.deltaQuaternion = new Quaternion();
        this.quaternionGyroscope = new Quaternion();
        this.quaternionRotationVector = new Quaternion();
        this.gyroscopeRotationVelocity = 0.0d;
        this.positionInitialised = false;
        this.mImuGyroDatacount = 0;
        this.sensorList.add(sensorManager.getDefaultSensor(4));
        this.sensorList.add(sensorManager.getDefaultSensor(11));
    }

    public static void getQuaternionFromVector(float[] fArr, float[] fArr2) {
        if (fArr2.length >= 4) {
            fArr[0] = fArr2[3];
        } else {
            fArr[0] = ((1.0f - (fArr2[0] * fArr2[0])) - (fArr2[1] * fArr2[1])) - (fArr2[2] * fArr2[2]);
            fArr[0] = fArr[0] > 0.0f ? (float) Math.sqrt(fArr[0]) : 0.0f;
        }
        fArr[1] = fArr2[0];
        fArr[2] = fArr2[1];
        fArr[3] = fArr2[2];
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 11) {
            synchronized (this.mObject) {
                for (int i = 0; i < sensorEvent.values.length; i++) {
                    if (Float.isNaN(sensorEvent.values[i])) {
                        this.mIsValid = false;
                        return;
                    }
                }
                float[] fArr = new float[4];
                try {
                    SensorManager.getQuaternionFromVector(fArr, sensorEvent.values);
                } catch (Exception unused) {
                    getQuaternionFromVector(fArr, sensorEvent.values);
                }
                this.quaternionRotationVector.setXYZW(fArr[1], fArr[2], fArr[3], -fArr[0]);
                if (this.positionInitialised) {
                    return;
                }
                this.quaternionGyroscope.set(this.quaternionRotationVector);
                this.positionInitialised = true;
            }
        } else if (sensorEvent.sensor.getType() == 4) {
            synchronized (this.mObject) {
                if (this.mIsValid.booleanValue()) {
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
                        } else {
                            this.gyroscopeRotationVelocity = 0.0d;
                        }
                        double d2 = (this.gyroscopeRotationVelocity * f) / 2.0d;
                        double sin = Math.sin(d2);
                        double cos = Math.cos(d2);
                        this.deltaQuaternion.setX((float) (f2 * sin));
                        this.deltaQuaternion.setY((float) (f3 * sin));
                        this.deltaQuaternion.setZ((float) (sin * f4));
                        this.deltaQuaternion.setW(-((float) cos));
                        Quaternion quaternion = this.deltaQuaternion;
                        Quaternion quaternion2 = this.quaternionGyroscope;
                        quaternion.multiplyByQuat(quaternion2, quaternion2);
                        float dotProduct = this.quaternionGyroscope.dotProduct(this.quaternionRotationVector);
                        if (Math.abs(dotProduct) < 0.0f) {
                            if (Math.abs(dotProduct) < 0.0f) {
                                this.panicCounter++;
                            }
                            setOrientationQuaternionAndMatrix(this.quaternionGyroscope);
                        } else {
                            Quaternion quaternion3 = new Quaternion();
                            this.quaternionGyroscope.slerp(this.quaternionRotationVector, quaternion3, (float) (this.gyroscopeRotationVelocity * 0.009999999776482582d));
                            setOrientationQuaternionAndMatrix(quaternion3);
                            this.quaternionGyroscope.copyVec4(quaternion3);
                            this.panicCounter = 0;
                        }
                        if (this.panicCounter > 60) {
                            ARLog.m20421d(TAG, "Rotation VectorPanic counter is bigger than threshold; this indicates a Gyroscope failure. Panic reset is imminent.");
                            double d3 = this.gyroscopeRotationVelocity;
                            if (d3 < 3.0d) {
                                ARLog.m20421d(TAG, "Rotation VectorPerforming Panic-reset. Resetting orientation to rotation-vector value.");
                                setOrientationQuaternionAndMatrix(this.quaternionRotationVector);
                                this.quaternionGyroscope.copyVec4(this.quaternionRotationVector);
                                this.panicCounter = 0;
                            } else {
                                String format = String.format("Panic reset delayed due to ongoing motion (user is still shaking the device). Gyroscope Velocity: %.2f > 3", Double.valueOf(d3));
                                ARLog.m20421d(TAG, "Rotation Vector" + format);
                            }
                        }
                    }
                    this.timestamp = sensorEvent.timestamp;
                }
            }
        }
    }

    private void setOrientationQuaternionAndMatrix(Quaternion quaternion) {
        Quaternion m24463clone = quaternion.m24463clone();
        m24463clone.m20431w(-m24463clone.m20432w());
        synchronized (this.syncToken) {
            this.currentOrientationQuaternion.copyVec4(quaternion);
            SensorManager.getRotationMatrixFromVector(this.currentOrientationRotationMatrix.matrix, m24463clone.toArray());
            this.mImuGyroDatacount++;
            if (this.mImuGyroDatacount > 20) {
                transformMatrix();
                setChanged();
                notifyObservers();
            }
        }
    }

    private boolean isIdentify() {
        return ((double) Math.abs(1.0f - this.mFinalRotatedOrientationRotationMatrix.matrix[0])) < 0.001d && ((double) Math.abs(1.0f - this.mFinalRotatedOrientationRotationMatrix.matrix[5])) < 0.001d && ((double) Math.abs(1.0f - this.mFinalRotatedOrientationRotationMatrix.matrix[10])) < 0.001d && ((double) Math.abs(1.0f - this.mFinalRotatedOrientationRotationMatrix.matrix[15])) < 0.001d;
    }

    protected void transformMatrix() {
        if (!this.mInitMFlag) {
            this.mInitMFlag = true;
            System.arraycopy(this.currentOrientationRotationMatrix.matrix, 0, this.mInitOrientationRotationMatrix.matrix, 0, this.currentOrientationRotationMatrix.matrix.length);
            Matrix.setIdentityM(this.mFinalRotatedOrientationRotationMatrix.matrix, 0);
            return;
        }
        toFinalRotationMatrix(this.mFinalRotatedOrientationRotationMatrix.matrix, this.currentOrientationRotationMatrix.matrix, this.mInitOrientationRotationMatrix.matrix);
    }
}
