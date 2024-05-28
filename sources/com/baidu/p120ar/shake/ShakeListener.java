package com.baidu.p120ar.shake;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.p120ar.utils.ARLog;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.shake.ShakeListener */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShakeListener implements SensorEventListener {
    private static final int LINEAR_GRAVITY_MAX = 5;
    private static final int NORMAL_GRAVITY_MAX = 10;
    private static final int SPEED_SHRESHOLD = 2000;
    private static final String TAG = "ShakeListener";
    private static final int UPTATE_INTERVAL_TIME = 70;
    private Context mContext;
    private long mLastUpdateTime;
    private float mLastX;
    private float mLastY;
    private float mLastZ;
    private OnShakeListener mOnShakeListener;
    private Sensor mSensorAcce;
    private SensorManager mSensorManager;
    private boolean mShakeEnable = true;
    private LinkedList<Double> mAccelerationQueue = new LinkedList<>();
    private LinkedList<Float> mAccelerationXQueue = new LinkedList<>();
    private LinkedList<Float> mAccelerationYQueue = new LinkedList<>();
    private LinkedList<Float> mAccelerationZQueue = new LinkedList<>();
    private int mQueueSize = 10;
    private double mMaxAcc = 0.0d;
    private boolean isNormal = true;
    private boolean mIsUseNormalAccelerater = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.shake.ShakeListener$OnShakeListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnShakeListener {
        void destroy();

        void onShake(float f, float f2, float f3, float f4);
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public ShakeListener(Context context) {
        this.mContext = context;
    }

    public void start() {
        SensorManager sensorManager;
        this.mSensorManager = (SensorManager) this.mContext.getSystemService("sensor");
        SensorManager sensorManager2 = this.mSensorManager;
        if (sensorManager2 != null) {
            this.mSensorAcce = sensorManager2.getDefaultSensor(10);
            if (this.mSensorAcce == null) {
                this.mSensorAcce = this.mSensorManager.getDefaultSensor(1);
                this.mIsUseNormalAccelerater = true;
            }
        }
        Sensor sensor = this.mSensorAcce;
        if (sensor == null || (sensorManager = this.mSensorManager) == null) {
            return;
        }
        sensorManager.registerListener(this, sensor, 1);
    }

    public void stop() {
        OnShakeListener onShakeListener = this.mOnShakeListener;
        if (onShakeListener != null) {
            onShakeListener.destroy();
            this.mOnShakeListener = null;
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.mSensorManager = null;
        }
        this.mIsUseNormalAccelerater = false;
    }

    public void setOnShakeListener(OnShakeListener onShakeListener) {
        this.mOnShakeListener = onShakeListener;
    }

    public void setShakeEnable(boolean z) {
        this.mShakeEnable = z;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.mShakeEnable) {
            float f = sensorEvent.values[0];
            float f2 = sensorEvent.values[1];
            float f3 = sensorEvent.values[2];
            handleAcceleration(f, f2, f3);
            ARLog.m20422d("acc  x : " + f + " , y : " + f2 + ", z : " + f3);
        }
    }

    public void offer(LinkedList linkedList, double d) {
        if (linkedList.size() >= this.mQueueSize) {
            linkedList.poll();
        }
        linkedList.offer(Double.valueOf(d));
    }

    public void offer(LinkedList linkedList, float f) {
        if (linkedList.size() >= this.mQueueSize) {
            linkedList.poll();
        }
        linkedList.offer(Float.valueOf(f));
    }

    public void handleAcceleration(float f, float f2, float f3) {
        double sqrt = Math.sqrt((f * f) + (f2 * f2) + (f3 * f3));
        if (sqrt > this.mMaxAcc) {
            this.mMaxAcc = sqrt;
        }
        ARLog.m20422d("max acc is : " + this.mMaxAcc);
        offer(this.mAccelerationQueue, sqrt);
        offer((LinkedList) this.mAccelerationXQueue, Math.abs(f));
        offer((LinkedList) this.mAccelerationYQueue, Math.abs(f2));
        offer((LinkedList) this.mAccelerationZQueue, Math.abs(f3));
        if (this.mAccelerationQueue.size() == this.mQueueSize) {
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            double d = 0.0d;
            for (int i = 0; i < this.mAccelerationQueue.size(); i++) {
                d += this.mAccelerationQueue.get(i).doubleValue();
                f4 += this.mAccelerationXQueue.get(i).floatValue();
                f5 += this.mAccelerationYQueue.get(i).floatValue();
                f6 += this.mAccelerationZQueue.get(i).floatValue();
            }
            int i2 = this.mQueueSize;
            double d2 = d / i2;
            float f7 = f4 / i2;
            float f8 = f5 / i2;
            float f9 = f6 / i2;
            if (this.mIsUseNormalAccelerater) {
                if (this.isNormal) {
                    if (d2 > 10.0d) {
                        this.isNormal = false;
                    }
                } else if (d2 < 10.0d) {
                    this.isNormal = true;
                    OnShakeListener onShakeListener = this.mOnShakeListener;
                    if (onShakeListener != null) {
                        onShakeListener.onShake(f7, f8, f9, (float) this.mMaxAcc);
                        this.mMaxAcc = 0.0d;
                    }
                }
            } else if (this.isNormal) {
                if (d2 > 5.0d) {
                    this.isNormal = false;
                }
            } else if (d2 < 5.0d) {
                this.isNormal = true;
                OnShakeListener onShakeListener2 = this.mOnShakeListener;
                if (onShakeListener2 != null) {
                    onShakeListener2.onShake(f7, f8, f9, (float) this.mMaxAcc);
                    this.mMaxAcc = 0.0d;
                }
            }
        }
    }
}
