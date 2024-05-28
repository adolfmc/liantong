package com.baidu.p120ar.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.Size */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Size {
    private int mHeight;
    private int mWidth;

    public Size() {
    }

    public Size(int i, int i2) {
        this.mWidth = i;
        this.mHeight = i2;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.mWidth == size.mWidth && this.mHeight == size.mHeight;
        }
        return false;
    }

    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(120);
        }
        if (indexOf < 0) {
            return null;
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int hashCode() {
        int i = this.mHeight;
        int i2 = this.mWidth;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }
}
