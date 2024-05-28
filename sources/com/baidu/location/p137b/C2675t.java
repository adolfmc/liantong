package com.baidu.location.p137b;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import com.baidu.location.ServiceC2737f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.t */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2675t implements SensorEventListener {

    /* renamed from: c */
    private static C2675t f5423c;

    /* renamed from: a */
    private float[] f5424a;

    /* renamed from: b */
    private SensorManager f5425b;

    /* renamed from: d */
    private float f5426d;

    /* renamed from: e */
    private boolean f5427e = false;

    /* renamed from: f */
    private boolean f5428f = false;

    /* renamed from: g */
    private boolean f5429g = false;

    private C2675t() {
    }

    /* renamed from: a */
    public static synchronized C2675t m19327a() {
        C2675t c2675t;
        synchronized (C2675t.class) {
            if (f5423c == null) {
                f5423c = new C2675t();
            }
            c2675t = f5423c;
        }
        return c2675t;
    }

    /* renamed from: a */
    public void m19326a(boolean z) {
        this.f5427e = z;
    }

    /* renamed from: b */
    public synchronized void m19325b() {
        Sensor defaultSensor;
        if (this.f5429g) {
            return;
        }
        if (this.f5427e) {
            if (this.f5425b == null) {
                this.f5425b = (SensorManager) ServiceC2737f.getServiceContext().getSystemService("sensor");
            }
            if (this.f5425b != null && (defaultSensor = this.f5425b.getDefaultSensor(11)) != null && this.f5427e) {
                this.f5425b.registerListener(this, defaultSensor, 3);
            }
            this.f5429g = true;
        }
    }

    /* renamed from: c */
    public synchronized void m19324c() {
        if (this.f5429g) {
            if (this.f5425b != null) {
                this.f5425b.unregisterListener(this);
                this.f5425b = null;
            }
            this.f5429g = false;
        }
    }

    /* renamed from: d */
    public boolean m19323d() {
        return this.f5427e;
    }

    /* renamed from: e */
    public float m19322e() {
        return this.f5426d;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    @SuppressLint({"NewApi"})
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 11) {
            return;
        }
        this.f5424a = (float[]) sensorEvent.values.clone();
        float[] fArr = this.f5424a;
        if (fArr != null) {
            float[] fArr2 = new float[9];
            try {
                SensorManager.getRotationMatrixFromVector(fArr2, fArr);
                float[] fArr3 = new float[3];
                SensorManager.getOrientation(fArr2, fArr3);
                this.f5426d = (float) Math.toDegrees(fArr3[0]);
                this.f5426d = (float) Math.floor(this.f5426d >= 0.0f ? this.f5426d : this.f5426d + 360.0f);
            } catch (Exception unused) {
                this.f5426d = 0.0f;
            }
        }
    }
}
