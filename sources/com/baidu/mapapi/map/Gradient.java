package com.baidu.mapapi.map;

import android.graphics.Color;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Gradient {

    /* renamed from: a */
    float[] f6027a;

    /* renamed from: b */
    private final int f6028b;

    /* renamed from: c */
    private final int[] f6029c;

    /* renamed from: d */
    private final float[] f6030d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.map.Gradient$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2744a {

        /* renamed from: b */
        private final int f6032b;

        /* renamed from: c */
        private final int f6033c;

        /* renamed from: d */
        private final float f6034d;

        private C2744a(int i, int i2, float f) {
            this.f6032b = i;
            this.f6033c = i2;
            this.f6034d = f;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr == null || fArr == null) {
            throw new IllegalArgumentException("BDMapSDKException: colors and startPoints should not be null");
        }
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("BDMapSDKException: colors and startPoints should be same length");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: No colors have been defined");
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            if (fArr[i2] <= fArr[i2 - 1]) {
                throw new IllegalArgumentException("BDMapSDKException: startPoints should be in increasing order");
            }
        }
        this.f6028b = i;
        this.f6029c = new int[iArr.length];
        this.f6030d = new float[fArr.length];
        System.arraycopy(iArr, 0, this.f6029c, 0, iArr.length);
        System.arraycopy(fArr, 0, this.f6030d, 0, fArr.length);
    }

    /* renamed from: a */
    private static int m18971a(int i, int i2, float f) {
        int alpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f) + fArr[i3];
        }
        return Color.HSVToColor(alpha, fArr3);
    }

    /* renamed from: b */
    private HashMap<Integer, C2744a> m18970b() {
        HashMap<Integer, C2744a> hashMap = new HashMap<>();
        if (this.f6030d[0] != 0.0f) {
            hashMap.put(0, new C2744a(Color.argb(0, Color.red(this.f6029c[0]), Color.green(this.f6029c[0]), Color.blue(this.f6029c[0])), this.f6029c[0], this.f6028b * this.f6030d[0]));
        }
        for (int i = 1; i < this.f6029c.length; i++) {
            int i2 = i - 1;
            Integer valueOf = Integer.valueOf((int) (this.f6028b * this.f6030d[i2]));
            int[] iArr = this.f6029c;
            int i3 = iArr[i2];
            int i4 = iArr[i];
            float[] fArr = this.f6030d;
            hashMap.put(valueOf, new C2744a(i3, i4, (fArr[i] - fArr[i2]) * this.f6028b));
        }
        float[] fArr2 = this.f6030d;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer valueOf2 = Integer.valueOf((int) (this.f6028b * fArr2[length]));
            int[] iArr2 = this.f6029c;
            hashMap.put(valueOf2, new C2744a(iArr2[length], iArr2[length], this.f6028b * (1.0f - this.f6030d[length])));
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public float[] m18973a() {
        float[] fArr = this.f6027a;
        if (fArr == null) {
            this.f6027a = new float[this.f6028b];
            int i = 0;
            while (i < this.f6028b) {
                int i2 = i + 1;
                this.f6027a[i] = i2 * 0.001f;
                i = i2;
            }
            return this.f6027a;
        }
        return fArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int[] m18972a(double d) {
        HashMap<Integer, C2744a> m18970b = m18970b();
        int[] iArr = new int[this.f6028b];
        int i = 0;
        C2744a c2744a = m18970b.get(0);
        for (int i2 = 0; i2 < this.f6028b; i2++) {
            if (m18970b.containsKey(Integer.valueOf(i2))) {
                c2744a = m18970b.get(Integer.valueOf(i2));
                i = i2;
            }
            iArr[i2] = m18971a(c2744a.f6032b, c2744a.f6033c, (i2 - i) / c2744a.f6034d);
        }
        if (d != 1.0d) {
            for (int i3 = 0; i3 < this.f6028b; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (Color.alpha(i4) * d), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }

    public int[] getColors() {
        return this.f6029c;
    }

    public float[] getStartPoints() {
        return this.f6030d;
    }
}
