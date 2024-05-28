package com.baidu.p120ar.imu;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.p120ar.arplay.representation.EulerAngles;
import com.baidu.p120ar.arplay.representation.Matrix;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.OrientationProvider */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class OrientationProvider extends Observable implements SensorEventListener {
    private static final String TAG = "OrientationProvider";
    protected float[] mGravityValues;
    private SensorManager sensorManager;
    protected Object mObject = new Object();
    protected Boolean mIsValid = true;
    protected final Object syncToken = new Object();
    protected List<Sensor> sensorList = new ArrayList();
    boolean mInitMFlag = false;
    protected final Matrixf4x4 currentOrientationRotationMatrix = new Matrixf4x4();
    protected final Quaternion currentOrientationQuaternion = new Quaternion();
    protected final Matrixf4x4 mInitOrientationRotationMatrix = new Matrixf4x4();
    protected final Matrixf4x4 mFinalOrientationRotationMatrix = new Matrixf4x4();
    protected final Matrixf4x4 mFinalRotatedOrientationRotationMatrix = new Matrixf4x4();

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public OrientationProvider(SensorManager sensorManager) {
        this.sensorManager = sensorManager;
        String str = TAG;
        ARLog.m20421d(str, "sensorList size " + String.valueOf(this.sensorList.size()));
        if (this.sensorList.size() > 120) {
            this.sensorList.clear();
        }
    }

    public boolean isValid() {
        boolean booleanValue;
        synchronized (this.mObject) {
            booleanValue = this.mIsValid.booleanValue();
        }
        return booleanValue;
    }

    public boolean isSupport() {
        for (Sensor sensor : this.sensorList) {
            if (sensor == null) {
                return false;
            }
        }
        return true;
    }

    public void start() {
        for (Sensor sensor : this.sensorList) {
            this.sensorManager.registerListener(this, sensor, 1);
        }
    }

    public void release() {
        deleteObservers();
        for (Sensor sensor : this.sensorList) {
            this.sensorManager.unregisterListener(this, sensor);
        }
        this.sensorList.clear();
    }

    public Matrixf4x4 getRotationMatrix() {
        Matrixf4x4 matrixf4x4;
        synchronized (this.syncToken) {
            matrixf4x4 = this.currentOrientationRotationMatrix;
        }
        return matrixf4x4;
    }

    public Quaternion getQuaternion() {
        Quaternion m24463clone;
        synchronized (this.syncToken) {
            m24463clone = this.currentOrientationQuaternion.m24463clone();
        }
        return m24463clone;
    }

    public EulerAngles getEulerAngles() {
        EulerAngles eulerAngles;
        synchronized (this.syncToken) {
            float[] fArr = new float[3];
            SensorManager.getOrientation(this.currentOrientationRotationMatrix.matrix, fArr);
            eulerAngles = new EulerAngles(fArr[0], fArr[1], fArr[2]);
        }
        return eulerAngles;
    }

    public Matrixf4x4 getFinalRotationMatrixMatrix() {
        return this.mFinalRotatedOrientationRotationMatrix;
    }

    public float[] getGravityValues() {
        return this.mGravityValues;
    }

    public static boolean getRMOfProjectionCoord(float[] fArr, float[] fArr2) {
        float f;
        float atan;
        float[] fArr3 = new float[4];
        float[] fArr4 = {1.0f, 0.0f, 0.0f, 0.0f};
        Matrix.multiplyMV(fArr3, fArr2, fArr4);
        if (Float.compare(fArr3[0], fArr4[0]) == 0 && Float.compare(fArr3[1], fArr4[1]) == 0 && Float.compare(fArr3[2], fArr4[2]) == 0) {
            return false;
        }
        int compare = Float.compare(fArr3[0], 0.0f);
        int compare2 = Float.compare(fArr3[1], 0.0f);
        if (compare != 0) {
            double d = fArr3[1] / fArr3[0];
            if (compare > 0 && compare2 >= 0) {
                atan = (float) ((Math.atan(d) * 180.0d) / 3.141592653589793d);
            } else if (compare > 0 && compare2 < 0) {
                atan = ((float) ((Math.atan(d) * 180.0d) / 3.141592653589793d)) + 360.0f;
            } else {
                atan = ((float) ((Math.atan(d) * 180.0d) / 3.141592653589793d)) + 180.0f;
            }
            f = atan;
        } else if (Float.compare(fArr3[1], 1.0f) == 0) {
            f = 0.0f;
        } else {
            f = Float.compare(fArr3[1], -1.0f) == 0 ? 180.0f : 0.0f;
        }
        ARLog.m20421d(TAG, "orientation: outputV[0] = " + fArr3[0] + ", outputV[1] = " + fArr3[1] + ", angleZ = " + f);
        Matrix.setIdentityM(fArr, 0);
        Matrix.rotateM(fArr, 0, f, 0.0f, 0.0f, 1.0f);
        return true;
    }

    public static void toFinalRotationMatrix(float[] fArr, float[] fArr2, float[] fArr3) {
        float[] fArr4 = new float[16];
        Matrix.setIdentityM(fArr4, 0);
        Matrix.invertM(fArr4, 0, fArr3, 0);
        Matrix.multiplyMM(fArr, 0, fArr2, 0, fArr4, 0);
    }

    public static void rotateFinalRotationMatrix(float[] fArr, float[] fArr2) {
        System.arraycopy(fArr2, 0, fArr, 0, fArr2.length);
    }
}
