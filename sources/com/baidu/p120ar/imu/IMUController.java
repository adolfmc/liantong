package com.baidu.p120ar.imu;

import android.content.Context;
import android.hardware.SensorManager;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.imu.IMUController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IMUController implements IImu {
    private static final String TAG = "IMUController";
    private HashMap<ImuListener, ImuObject> mImuObjects;
    private SensorManager mSensorManager;

    @Override // com.baidu.p120ar.imu.IImu
    public void setContext(Context context) {
        if (context != null) {
            this.mSensorManager = (SensorManager) context.getSystemService("sensor");
        }
    }

    @Override // com.baidu.p120ar.imu.IImu
    public boolean start(ImuParams imuParams, ImuListener imuListener) {
        if (this.mSensorManager == null || imuParams == null || imuListener == null) {
            return false;
        }
        if (this.mImuObjects == null) {
            this.mImuObjects = new HashMap<>();
        }
        ImuObject imuObject = this.mImuObjects.get(imuListener);
        if (imuObject == null) {
            imuObject = new ImuObject();
            this.mImuObjects.put(imuListener, imuObject);
        }
        return imuObject.start(this.mSensorManager, imuParams, imuListener);
    }

    @Override // com.baidu.p120ar.imu.IImu
    public void stop(ImuListener imuListener) {
        HashMap<ImuListener, ImuObject> hashMap;
        ImuObject remove;
        if (imuListener == null || (hashMap = this.mImuObjects) == null || (remove = hashMap.remove(imuListener)) == null) {
            return;
        }
        remove.stop();
    }

    @Override // com.baidu.p120ar.imu.IImu
    public void destroy() {
        HashMap<ImuListener, ImuObject> hashMap = this.mImuObjects;
        if (hashMap != null) {
            for (ImuObject imuObject : hashMap.values()) {
                imuObject.stop();
            }
            this.mImuObjects.clear();
            this.mImuObjects = null;
        }
        this.mSensorManager = null;
    }
}
