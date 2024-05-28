package com.baidu.p120ar.imu;

import android.hardware.SensorManager;
import com.baidu.p120ar.arplay.representation.Matrix;
import com.baidu.p120ar.utils.ARLog;
import java.util.Observable;
import java.util.Observer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.ImuObject */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImuObject implements Observer {
    private static final String TAG = "ImuObject";
    private float mAngle;
    private ImuListener mImuListener;
    private ImuParams mImuParams;
    private OrientationProvider mOrientationProvider;
    private static final float[] ROTATE_MATRIX = {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] PITCH_VECTOR = {0.0f, 0.0f, -1.0f, 0.0f};
    private float[] mProcessMatrix = new float[16];
    private float mPitch = -1.0f;
    private float mDistance = 10000.0f;
    private float[] mLastMatrix = new float[16];
    private boolean mIsFirstFrame = true;
    private boolean mIsSavedLastPosition = false;
    private float[] mTempVector = new float[4];
    private float[] mTempMatrix = new float[16];

    public ImuObject() {
        Matrix.setIdentityM(this.mLastMatrix, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean start(SensorManager sensorManager, ImuParams imuParams, ImuListener imuListener) {
        if (imuParams == null) {
            return false;
        }
        this.mImuParams = imuParams;
        this.mImuListener = imuListener;
        if (this.mImuParams.getCoordinate() == Coordinate.RELATIVE && !this.mImuParams.isResumeOriginalPosition()) {
            this.mIsFirstFrame = true;
        }
        try {
            if (this.mOrientationProvider == null) {
                this.mOrientationProvider = createOrientationProvieder(this.mImuParams.getCoordinate(), sensorManager);
            }
            if (this.mOrientationProvider.isSupport()) {
                this.mOrientationProvider.start();
                return true;
            }
            return false;
        } catch (Throwable th) {
            String str = TAG;
            ARLog.m20419e(str, "IMUController start: " + th.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stop() {
        ImuParams imuParams;
        if (this.mOrientationProvider != null && (imuParams = this.mImuParams) != null && imuParams.getCoordinate() == Coordinate.RELATIVE && !this.mIsSavedLastPosition && this.mImuParams.isResumeOriginalPosition()) {
            this.mLastMatrix = this.mOrientationProvider.mInitOrientationRotationMatrix.matrix;
            this.mIsSavedLastPosition = true;
        }
        try {
            releaseOrientationProvider();
            this.mImuListener = null;
            this.mImuParams = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private OrientationProvider createOrientationProvieder(Coordinate coordinate, SensorManager sensorManager) {
        OrientationProvider improvedOrientationSensor2Provider;
        if (coordinate == Coordinate.RELATIVE) {
            if (this.mImuParams.isResumeOriginalPosition()) {
                improvedOrientationSensor2Provider = new ImprovedOrientationSensor3Provider(sensorManager);
                if (!this.mIsFirstFrame) {
                    improvedOrientationSensor2Provider.mInitMFlag = true;
                    improvedOrientationSensor2Provider.mInitOrientationRotationMatrix.matrix = this.mLastMatrix;
                }
                this.mIsFirstFrame = false;
            } else {
                improvedOrientationSensor2Provider = new CalibratedGyroscopeProvider(sensorManager);
            }
        } else if (this.mImuParams.isNeedGravity()) {
            improvedOrientationSensor2Provider = new VpasProvider(sensorManager);
        } else {
            improvedOrientationSensor2Provider = new ImprovedOrientationSensor2Provider(sensorManager);
        }
        improvedOrientationSensor2Provider.addObserver(this);
        return improvedOrientationSensor2Provider;
    }

    private void releaseOrientationProvider() {
        OrientationProvider orientationProvider = this.mOrientationProvider;
        if (orientationProvider != null) {
            orientationProvider.release();
            this.mOrientationProvider = null;
        }
    }

    @Override // java.util.Observer
    public void update(Observable observable, Object obj) {
        try {
            if (this.mImuParams != null && this.mImuListener != null) {
                float[] fArr = this.mOrientationProvider.getFinalRotationMatrixMatrix().matrix;
                if (this.mImuParams.isNeedImuAngle() || this.mImuParams.isNeedProcessMatrix()) {
                    updateMotion(fArr);
                }
                float[] gravityValues = this.mOrientationProvider.getGravityValues();
                ImuInfo imuInfo = new ImuInfo();
                if (this.mImuParams.isNeedProcessMatrix()) {
                    imuInfo.setMatrix(this.mProcessMatrix);
                } else {
                    imuInfo.setMatrix(fArr);
                }
                imuInfo.setAngle(this.mAngle);
                imuInfo.setInitPos(this.mImuParams.getInitPosition());
                imuInfo.setGravityValues(gravityValues);
                this.mImuListener.onImuUpdate(imuInfo);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void updateMotion(float[] fArr) {
        Matrix.invertM(this.mProcessMatrix, 0, fArr, 0);
        Matrix.multiplyMM(this.mTempMatrix, 0, ROTATE_MATRIX, 0, this.mProcessMatrix, 0);
        this.mPitch = absolutePitch(this.mTempMatrix);
        float f = this.mPitch;
        if (f > 0.0f) {
            this.mDistance = (float) (Math.pow(1.13d, f) + 600.0d);
            if (this.mDistance > 15000.0f) {
                this.mDistance = 15000.0f;
            }
        }
        Matrix.multiplyMV(this.mTempVector, 0, this.mTempMatrix, 0, new float[]{0.0f, 0.0f, -this.mDistance, 1.0f}, 0);
        float[] fArr2 = this.mTempMatrix;
        float[] fArr3 = this.mTempVector;
        fArr2[12] = -fArr3[0];
        fArr2[13] = -fArr3[1];
        fArr2[14] = -fArr3[2];
        Matrix.invertM(this.mProcessMatrix, 0, fArr2, 0);
        this.mAngle = getRot(this.mTempVector);
        Matrix.rotateM(this.mProcessMatrix, 0, this.mAngle, 0.0f, 0.0f, 1.0f);
    }

    private float getRot(float[] fArr) {
        float f = -fArr[0];
        float f2 = -fArr[1];
        int i = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        if (i != 0 || f >= 0.0f) {
            if (i != 0 || f <= 0.0f) {
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                if (i2 != 0 || f2 >= 0.0f) {
                    if (i2 != 0 || i <= 0) {
                        float atan = (float) ((Math.atan(Math.abs(f2) / Math.abs(f)) / 3.141592653589793d) * 180.0d);
                        if (i2 > 0 && f2 < 0.0f) {
                            atan = 180.0f - atan;
                        }
                        if (i2 > 0 && i > 0) {
                            atan += 180.0f;
                        }
                        return (f >= 0.0f || i <= 0) ? atan : 360.0f - atan;
                    }
                    return 270.0f;
                }
                return 90.0f;
            }
            return 180.0f;
        }
        return 0.0f;
    }

    private float absolutePitch(float[] fArr) {
        Matrix.multiplyMV(this.mTempVector, 0, fArr, 0, PITCH_VECTOR, 0);
        float[] fArr2 = this.mTempVector;
        if (fArr2[2] > 0.0f) {
            return -1.0f;
        }
        return 90.0f - ((float) ((Math.atan(Math.abs(this.mTempVector[2]) / ((float) Math.sqrt((fArr2[0] * fArr2[0]) + (fArr2[1] * fArr2[1])))) / 3.141592653589793d) * 180.0d));
    }
}
