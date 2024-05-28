package com.baidu.p120ar.arplay.representation;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.representation.Vector4f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Vector4f extends Renderable implements Serializable {
    private static final long serialVersionUID = 1;
    protected float[] points = {0.0f, 0.0f, 0.0f, 0.0f};

    public Vector4f(float f, float f2, float f3, float f4) {
        float[] fArr = this.points;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    public Vector4f() {
        float[] fArr = this.points;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
    }

    public Vector4f(Vector3f vector3f, float f) {
        this.points[0] = vector3f.m20438x();
        this.points[1] = vector3f.m20436y();
        this.points[2] = vector3f.m20434z();
        this.points[3] = f;
    }

    public float[] toArray() {
        return this.points;
    }

    public void copyVec4(Vector4f vector4f) {
        float[] fArr = this.points;
        float[] fArr2 = vector4f.points;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        fArr[2] = fArr2[2];
        fArr[3] = fArr2[3];
    }

    public void add(Vector4f vector4f) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        fArr[0] = f + fArr2[0];
        fArr[1] = fArr[1] + fArr2[1];
        fArr[2] = fArr[2] + fArr2[2];
        fArr[3] = fArr[3] + fArr2[3];
    }

    public void add(Vector3f vector3f, float f) {
        float[] fArr = this.points;
        fArr[0] = fArr[0] + vector3f.m20438x();
        float[] fArr2 = this.points;
        fArr2[1] = fArr2[1] + vector3f.m20436y();
        float[] fArr3 = this.points;
        fArr3[2] = fArr3[2] + vector3f.m20434z();
        float[] fArr4 = this.points;
        fArr4[3] = fArr4[3] + f;
    }

    public void subtract(Vector4f vector4f) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        fArr[0] = f - fArr2[0];
        fArr[1] = fArr[1] - fArr2[1];
        fArr[2] = fArr[2] - fArr2[2];
        fArr[3] = fArr[3] - fArr2[3];
    }

    public void subtract(Vector4f vector4f, Vector4f vector4f2) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        vector4f2.setXYZW(f - fArr2[0], fArr[1] - fArr2[1], fArr[2] - fArr2[2], fArr[3] - fArr2[3]);
    }

    public void subdivide(Vector4f vector4f) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        fArr[0] = f / fArr2[0];
        fArr[1] = fArr[1] / fArr2[1];
        fArr[2] = fArr[2] / fArr2[2];
        fArr[3] = fArr[3] / fArr2[3];
    }

    public void multiplyByScalar(float f) {
        float[] fArr = this.points;
        fArr[0] = fArr[0] * f;
        fArr[1] = fArr[1] * f;
        fArr[2] = fArr[2] * f;
        fArr[3] = fArr[3] * f;
    }

    public float dotProduct(Vector4f vector4f) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        return (f * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]) + (fArr[3] * fArr2[3]);
    }

    public void lerp(Vector4f vector4f, Vector4f vector4f2, float f) {
        float[] fArr = vector4f2.points;
        float[] fArr2 = this.points;
        float f2 = 1.0f * f;
        float[] fArr3 = vector4f.points;
        fArr[0] = (fArr2[0] * f2) + (fArr3[0] * f);
        fArr[1] = (fArr2[1] * f2) + (fArr3[1] * f);
        fArr[2] = (fArr2[2] * f2) + (fArr3[2] * f);
        fArr[3] = (fArr2[3] * f2) + (fArr3[3] * f);
    }

    public void normalize() {
        float[] fArr = this.points;
        if (fArr[3] == 0.0f) {
            return;
        }
        fArr[0] = fArr[0] / fArr[3];
        fArr[1] = fArr[1] / fArr[3];
        fArr[2] = fArr[2] / fArr[3];
        double sqrt = Math.sqrt((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]) + (fArr[2] * fArr[2]));
        float[] fArr2 = this.points;
        fArr2[0] = (float) (fArr2[0] / sqrt);
        fArr2[1] = (float) (fArr2[1] / sqrt);
        fArr2[2] = (float) (fArr2[2] / sqrt);
    }

    public float getX() {
        return this.points[0];
    }

    public float getY() {
        return this.points[1];
    }

    public float getZ() {
        return this.points[2];
    }

    public float getW() {
        return this.points[3];
    }

    public void setX(float f) {
        this.points[0] = f;
    }

    public void setY(float f) {
        this.points[1] = f;
    }

    public void setZ(float f) {
        this.points[2] = f;
    }

    public void setW(float f) {
        this.points[3] = f;
    }

    /* renamed from: x */
    public float m20430x() {
        return this.points[0];
    }

    /* renamed from: x */
    public void m20429x(float f) {
        this.points[0] = f;
    }

    /* renamed from: y */
    public float m20428y() {
        return this.points[1];
    }

    /* renamed from: y */
    public void m20427y(float f) {
        this.points[1] = f;
    }

    /* renamed from: z */
    public float m20426z() {
        return this.points[2];
    }

    /* renamed from: z */
    public void m20425z(float f) {
        this.points[2] = f;
    }

    /* renamed from: w */
    public float m20432w() {
        return this.points[3];
    }

    /* renamed from: w */
    public void m20431w(float f) {
        this.points[3] = f;
    }

    public void setXYZW(float f, float f2, float f3, float f4) {
        float[] fArr = this.points;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
    }

    public boolean compareTo(Vector4f vector4f) {
        float[] fArr = this.points;
        float f = fArr[0];
        float[] fArr2 = vector4f.points;
        return f == fArr2[0] && fArr[1] == fArr2[1] && fArr[2] == fArr2[2] && fArr[3] == fArr2[3];
    }

    public void copyFromV3f(Vector3f vector3f, float f) {
        this.points[0] = vector3f.m20438x();
        this.points[1] = vector3f.m20436y();
        this.points[2] = vector3f.m20434z();
        this.points[3] = f;
    }

    public String toString() {
        return "X:" + this.points[0] + " Y:" + this.points[1] + " Z:" + this.points[2] + " W:" + this.points[3];
    }
}
