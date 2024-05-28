package com.baidu.p120ar.arplay.representation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.representation.EulerAngles */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EulerAngles {
    private float pitch;
    private float roll;
    private float yaw;

    public EulerAngles(float f, float f2, float f3) {
        this.yaw = f;
        this.pitch = f2;
        this.roll = f3;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public float getRoll() {
        return this.roll;
    }
}
