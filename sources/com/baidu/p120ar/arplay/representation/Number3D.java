package com.baidu.p120ar.arplay.representation;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.representation.Number3D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Number3D {
    public static final int M00 = 0;
    public static final int M01 = 4;
    public static final int M02 = 8;
    public static final int M03 = 12;
    public static final int M10 = 1;
    public static final int M11 = 5;
    public static final int M12 = 9;
    public static final int M13 = 13;
    public static final int M20 = 2;
    public static final int M21 = 6;
    public static final int M22 = 10;
    public static final int M23 = 14;
    public static final int M30 = 3;
    public static final int M31 = 7;
    public static final int M32 = 11;
    public static final int M33 = 15;
    private static Number3D _temp = new Number3D();

    /* renamed from: x */
    public float f4083x;

    /* renamed from: y */
    public float f4084y;

    /* renamed from: z */
    public float f4085z;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.representation.Number3D$Axis */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Axis {
        X,
        Y,
        Z
    }

    public Number3D() {
        this.f4083x = 0.0f;
        this.f4084y = 0.0f;
        this.f4085z = 0.0f;
    }

    public Number3D(Number3D number3D) {
        this.f4083x = number3D.f4083x;
        this.f4084y = number3D.f4084y;
        this.f4085z = number3D.f4085z;
    }

    public Number3D(String[] strArr) {
        if (strArr.length != 3) {
            Log.e(Number3D.class.getSimpleName(), "Number3D should be initialized with 3 values");
        }
        try {
            this.f4083x = Float.parseFloat(strArr[0]);
            this.f4084y = Float.parseFloat(strArr[1]);
            this.f4085z = Float.parseFloat(strArr[2]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public Number3D(float f, float f2, float f3) {
        this.f4083x = f;
        this.f4084y = f2;
        this.f4085z = f3;
    }

    public Number3D(double d, double d2, double d3) {
        this.f4083x = (float) d;
        this.f4084y = (float) d2;
        this.f4085z = (float) d3;
    }

    public boolean equals(Object obj) {
        Number3D number3D = obj instanceof Number3D ? (Number3D) obj : null;
        return number3D != null && number3D.f4083x == this.f4083x && number3D.f4084y == this.f4084y && number3D.f4085z == this.f4085z;
    }

    public void setAll(float f, float f2, float f3) {
        this.f4083x = f;
        this.f4084y = f2;
        this.f4085z = f3;
    }

    public void setAll(double d, double d2, double d3) {
        this.f4083x = (float) d;
        this.f4084y = (float) d2;
        this.f4085z = (float) d3;
    }

    public void project(float[] fArr) {
        if (fArr == null || fArr.length <= 15) {
            return;
        }
        float f = this.f4083x;
        float f2 = this.f4084y;
        float f3 = (fArr[3] * f) + (fArr[7] * f2);
        float f4 = this.f4085z;
        float f5 = f3 + (fArr[11] * f4) + fArr[15];
        setAll(((((fArr[0] * f) + (fArr[4] * f2)) + (fArr[8] * f4)) + fArr[12]) / f5, ((((fArr[1] * f) + (fArr[5] * f2)) + (fArr[9] * f4)) + fArr[13]) / f5, ((((f * fArr[2]) + (f2 * fArr[6])) + (f4 * fArr[10])) + fArr[14]) / f5);
    }

    public void setAllFrom(Number3D number3D) {
        this.f4083x = number3D.f4083x;
        this.f4084y = number3D.f4084y;
        this.f4085z = number3D.f4085z;
    }

    public float normalize() {
        float f = this.f4083x;
        float f2 = this.f4084y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.f4085z;
        double sqrt = Math.sqrt(f3 + (f4 * f4));
        if (sqrt != 0.0d && sqrt != 1.0d) {
            sqrt = 1.0d / sqrt;
            this.f4083x = (float) (this.f4083x * sqrt);
            this.f4084y = (float) (this.f4084y * sqrt);
            this.f4085z = (float) (this.f4085z * sqrt);
        }
        return (float) sqrt;
    }

    public Number3D inverse() {
        return new Number3D(-this.f4083x, -this.f4084y, -this.f4085z);
    }

    public Number3D add(Number3D number3D) {
        this.f4083x += number3D.f4083x;
        this.f4084y += number3D.f4084y;
        this.f4085z += number3D.f4085z;
        return this;
    }

    public Number3D add(float f, float f2, float f3) {
        this.f4083x += f;
        this.f4084y += f2;
        this.f4085z += f3;
        return this;
    }

    public Number3D subtract(Number3D number3D) {
        this.f4083x -= number3D.f4083x;
        this.f4084y -= number3D.f4084y;
        this.f4085z -= number3D.f4085z;
        return this;
    }

    public Number3D multiply(float f) {
        this.f4083x *= f;
        this.f4084y *= f;
        this.f4085z *= f;
        return this;
    }

    public void multiply(Number3D number3D) {
        this.f4083x *= number3D.f4083x;
        this.f4084y *= number3D.f4084y;
        this.f4085z *= number3D.f4085z;
    }

    public void multiply(float[] fArr) {
        float f = this.f4083x;
        float f2 = this.f4084y;
        float f3 = this.f4085z;
        this.f4083x = (fArr[0] * f) + (fArr[4] * f2) + (fArr[8] * f3) + fArr[12];
        this.f4084y = (fArr[1] * f) + (fArr[5] * f2) + (fArr[9] * f3) + fArr[13];
        this.f4085z = (f * fArr[2]) + (f2 * fArr[6]) + (f3 * fArr[10]) + fArr[14];
    }

    public float distanceTo(Number3D number3D) {
        float f = this.f4083x;
        float f2 = number3D.f4083x;
        float f3 = (f - f2) * (f - f2);
        float f4 = this.f4084y;
        float f5 = number3D.f4084y;
        float f6 = this.f4085z;
        float f7 = number3D.f4085z;
        return (float) Math.sqrt(f3 + ((f4 - f5) * (f4 - f5)) + ((f6 - f7) * (f6 - f7)));
    }

    public float length() {
        float f = this.f4083x;
        float f2 = this.f4084y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.f4085z;
        return (float) Math.sqrt(f3 + (f4 * f4));
    }

    /* renamed from: clone */
    public Number3D m24462clone() {
        return new Number3D(this.f4083x, this.f4084y, this.f4085z);
    }

    public void rotateX(float f) {
        Number3D number3D;
        double d = f;
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        _temp.setAll(this.f4083x, this.f4084y, this.f4085z);
        float f2 = _temp.f4085z;
        this.f4084y = (float) ((number3D.f4084y * cos) - (f2 * sin));
        this.f4085z = (float) ((number3D.f4084y * sin) + (f2 * cos));
    }

    public void rotateY(float f) {
        Number3D number3D;
        double d = f;
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        _temp.setAll(this.f4083x, this.f4084y, this.f4085z);
        float f2 = _temp.f4085z;
        this.f4083x = (float) ((number3D.f4083x * cos) + (f2 * sin));
        this.f4085z = (float) ((number3D.f4083x * (-sin)) + (f2 * cos));
    }

    public void rotateZ(float f) {
        Number3D number3D;
        double d = f;
        double cos = Math.cos(d);
        double sin = Math.sin(d);
        _temp.setAll(this.f4083x, this.f4084y, this.f4085z);
        float f2 = _temp.f4084y;
        this.f4083x = (float) ((number3D.f4083x * cos) - (f2 * sin));
        this.f4084y = (float) ((number3D.f4083x * sin) + (f2 * cos));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f4083x);
        stringBuffer.append(", ");
        stringBuffer.append(this.f4084y);
        stringBuffer.append(", ");
        stringBuffer.append(this.f4085z);
        return stringBuffer.toString();
    }

    public String formatIntToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((int) this.f4083x);
        stringBuffer.append(", ");
        stringBuffer.append((int) this.f4084y);
        stringBuffer.append(", ");
        stringBuffer.append((int) this.f4085z);
        return stringBuffer.toString();
    }

    public static Number3D add(Number3D number3D, Number3D number3D2) {
        return new Number3D(number3D.f4083x + number3D2.f4083x, number3D.f4084y + number3D2.f4084y, number3D.f4085z + number3D2.f4085z);
    }

    public static Number3D subtract(Number3D number3D, Number3D number3D2) {
        return new Number3D(number3D.f4083x - number3D2.f4083x, number3D.f4084y - number3D2.f4084y, number3D.f4085z - number3D2.f4085z);
    }

    public static Number3D multiply(Number3D number3D, Number3D number3D2) {
        return new Number3D(number3D.f4083x * number3D2.f4083x, number3D.f4084y * number3D2.f4084y, number3D.f4085z * number3D2.f4085z);
    }

    public static Number3D multiply(Number3D number3D, float f) {
        return new Number3D(number3D.f4083x * f, number3D.f4084y * f, number3D.f4085z * f);
    }

    public static Number3D cross(Number3D number3D, Number3D number3D2) {
        float f = number3D2.f4084y;
        float f2 = number3D.f4085z;
        float f3 = number3D2.f4085z;
        float f4 = number3D.f4084y;
        float f5 = number3D.f4083x;
        float f6 = number3D2.f4083x;
        return new Number3D((f * f2) - (f3 * f4), (f3 * f5) - (f2 * f6), (f6 * f4) - (f * f5));
    }

    public Number3D cross(Number3D number3D) {
        _temp.setAllFrom(this);
        float f = number3D.f4084y;
        Number3D number3D2 = _temp;
        float f2 = number3D2.f4085z;
        float f3 = number3D.f4085z;
        this.f4083x = (f * f2) - (number3D2.f4084y * f3);
        float f4 = number3D2.f4083x;
        float f5 = number3D.f4083x;
        this.f4084y = (f3 * f4) - (f2 * f5);
        this.f4085z = (f5 * number3D2.f4084y) - (number3D.f4084y * f4);
        return this;
    }

    public static float dot(Number3D number3D, Number3D number3D2) {
        return (number3D.f4083x * number3D2.f4083x) + (number3D.f4084y * number3D2.f4084y) + (number3D.f4085z * number3D2.f4085z);
    }

    public float dot(Number3D number3D) {
        return (this.f4083x * number3D.f4083x) + (this.f4084y * number3D.f4084y) + (this.f4085z * number3D.f4085z);
    }

    public static Number3D getAxisVector(Axis axis) {
        Number3D number3D = new Number3D();
        switch (axis) {
            case X:
                number3D.setAll(1.0f, 0.0f, 0.0f);
                break;
            case Y:
                number3D.setAll(0.0f, 1.0f, 0.0f);
                break;
            case Z:
                number3D.setAll(0.0f, 0.0f, 1.0f);
                break;
        }
        return number3D;
    }

    public Quaternion getRotationTo(Number3D number3D) {
        Quaternion quaternion = new Quaternion();
        normalize();
        number3D.normalize();
        float dot = dot(this, number3D);
        if (dot >= 1.0f) {
            quaternion.loadIdentityQuat();
        }
        if (dot < -0.999999f) {
            Number3D cross = cross(getAxisVector(Axis.X), this);
            if (cross.length() == 0.0f) {
                cross = cross(getAxisVector(Axis.Y), this);
            }
            cross.normalize();
            quaternion.setAxisAngle(new Vector3f(cross.f4083x, cross.f4084y, cross.f4085z), (float) Math.toDegrees(3.141592653589793d));
        } else {
            double sqrt = Math.sqrt((dot + 1.0f) * 2.0f);
            double d = 1.0d / sqrt;
            Number3D cross2 = cross(this, number3D);
            quaternion.points[0] = (float) (cross2.f4083x * d);
            quaternion.points[1] = (float) (cross2.f4084y * d);
            quaternion.points[2] = (float) (cross2.f4085z * d);
            quaternion.points[3] = (float) (sqrt * 0.5d);
            quaternion.normalize();
        }
        return quaternion;
    }

    public static Number3D getUpVector() {
        return new Number3D(0.0f, 1.0f, 0.0f);
    }

    public static Number3D lerp(Number3D number3D, Number3D number3D2, float f) {
        Number3D number3D3 = new Number3D();
        float f2 = number3D.f4083x;
        number3D3.f4083x = f2 + ((number3D2.f4083x - f2) * f);
        float f3 = number3D.f4084y;
        number3D3.f4084y = f3 + ((number3D2.f4084y - f3) * f);
        float f4 = number3D.f4085z;
        number3D3.f4085z = f4 + ((number3D2.f4085z - f4) * f);
        return number3D3;
    }

    public void lerpSelf(Number3D number3D, Number3D number3D2, float f) {
        float f2 = number3D.f4083x;
        this.f4083x = f2 + ((number3D2.f4083x - f2) * f);
        float f3 = number3D.f4084y;
        this.f4084y = f3 + ((number3D2.f4084y - f3) * f);
        float f4 = number3D.f4085z;
        this.f4085z = f4 + ((number3D2.f4085z - f4) * f);
    }
}
