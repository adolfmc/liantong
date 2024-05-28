package com.baidu.p120ar.face.algo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.algo.FAUFaceBox */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FAUFaceBox {
    float angle;
    float height;
    float width;

    /* renamed from: x */
    float f4090x;

    /* renamed from: y */
    float f4091y;

    public FAUFaceBox(float f, float f2, float f3, float f4) {
        this.f4090x = f;
        this.f4091y = f2;
        this.width = f3;
        this.height = f4;
    }

    public FAUFaceBox(float f, float f2, float f3, float f4, float f5) {
        this.f4090x = f;
        this.f4091y = f2;
        this.width = f3;
        this.height = f4;
        this.angle = f5;
    }

    public float getX() {
        return this.f4090x;
    }

    public float getY() {
        return this.f4091y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getAngle() {
        return this.angle;
    }
}
