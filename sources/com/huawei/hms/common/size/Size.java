package com.huawei.hms.common.size;

import com.huawei.hms.common.internal.Objects;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Size {

    /* renamed from: a */
    private final int f11177a;

    /* renamed from: b */
    private final int f11178b;

    public Size(int i, int i2) {
        this.f11177a = i;
        this.f11178b = i2;
    }

    public static Size parseSize(String str) {
        try {
            int indexOf = str.indexOf("x");
            if (indexOf < 0) {
                indexOf = str.indexOf("*");
            }
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (Exception unused) {
            throw new IllegalArgumentException("Size parses failed");
        }
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj instanceof Size) {
            Size size = (Size) obj;
            return this.f11177a == size.f11177a && this.f11178b == size.f11178b;
        }
        return false;
    }

    public final int getHeight() {
        return this.f11178b;
    }

    public final int getWidth() {
        return this.f11177a;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(getWidth()), Integer.valueOf(getHeight()));
    }

    public final String toString() {
        int i = this.f11177a;
        int i2 = this.f11178b;
        return "Width is " + i + " Height is " + i2;
    }
}
