package com.baidu.platform.comapi.basestruct;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Point implements Serializable {
    public double doubleX;
    public double doubleY;

    /* renamed from: x */
    public int f7536x;

    /* renamed from: y */
    public int f7537y;

    public Point() {
    }

    public Point(int i, int i2) {
        this.f7536x = i;
        this.f7537y = i2;
        this.doubleX = i;
        this.doubleY = i2;
    }

    public Point(double d, double d2) {
        this.f7536x = (int) d;
        this.f7537y = (int) d2;
        this.doubleX = d;
        this.doubleY = d2;
    }

    public Point(Point point) {
        if (point != null) {
            this.doubleX = point.getDoubleX();
            this.doubleY = point.getDoubleY();
            this.f7536x = point.getIntX();
            this.f7537y = point.getIntY();
        }
    }

    public int getmPtx() {
        return this.f7536x;
    }

    public void setmPtx(int i) {
        this.f7536x = i;
    }

    public int getmPty() {
        return this.f7537y;
    }

    public void setmPty(int i) {
        this.f7537y = i;
    }

    public int getIntX() {
        return this.f7536x;
    }

    public void setIntX(int i) {
        this.f7536x = i;
    }

    public int getIntY() {
        return this.f7537y;
    }

    public void setIntY(int i) {
        this.f7537y = i;
    }

    public double getDoubleX() {
        return this.doubleX;
    }

    public void setDoubleX(double d) {
        this.doubleX = d;
    }

    public double getDoubleY() {
        return this.doubleY;
    }

    public void setDoubleY(double d) {
        this.doubleY = d;
    }

    public void setTo(double d, double d2) {
        setDoubleX(d);
        setDoubleY(d2);
    }

    public void setTo(Point point) {
        if (point != null) {
            setDoubleX(point.getDoubleX());
            setDoubleY(point.getDoubleY());
        }
    }

    public String toString() {
        return "Point [x=" + getDoubleX() + ", y=" + getDoubleY() + "]";
    }

    public int hashCode() {
        return ((getIntX() + 31) * 31) + getIntY();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            Point point = (Point) obj;
            return getDoubleX() == point.getDoubleX() && getIntX() == point.getIntX() && getDoubleY() == point.getDoubleY() && getIntY() == point.getIntY() && getDoubleY() == point.getDoubleY();
        }
        return false;
    }

    public String toQuery() {
        return String.format("(%d,%d)", Integer.valueOf(getIntX()), Integer.valueOf(getIntY()));
    }
}
