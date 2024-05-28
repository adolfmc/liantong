package com.megvii.idcard.sdk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Log;
import com.megvii.idcard.sdk.jni.IDCardApi;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/* renamed from: com.megvii.idcard.sdk.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5293a {

    /* renamed from: a */
    private long f12225a;

    /* renamed from: b */
    private int f12226b = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5294a implements Serializable {
        private static final long serialVersionUID = 3786070988580648667L;

        /* renamed from: a */
        public float[] f12227a;

        /* renamed from: b */
        public float[] f12228b;

        /* renamed from: c */
        public C5299f[] f12229c;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5295b implements Serializable {
        private static final long serialVersionUID = 4644547927906498343L;

        /* renamed from: a */
        public float[] f12230a;

        /* renamed from: b */
        public float[] f12231b;

        /* renamed from: c */
        public C5299f[] f12232c;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5296c {

        /* renamed from: a */
        public int f12233a;

        /* renamed from: b */
        public float f12234b;

        /* renamed from: c */
        public float f12235c;

        /* renamed from: d */
        public float f12236d;

        /* renamed from: e */
        public int f12237e;

        /* renamed from: f */
        public int f12238f;

        /* renamed from: g */
        public int f12239g;

        /* renamed from: h */
        public int f12240h;

        /* renamed from: i */
        public int f12241i;

        /* renamed from: j */
        public int f12242j;

        /* renamed from: k */
        public int f12243k;

        /* renamed from: l */
        public int f12244l;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5297d {

        /* renamed from: a */
        public float f12245a;

        /* renamed from: b */
        public float f12246b;

        /* renamed from: c */
        public float f12247c;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$e */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5298e implements Serializable {
        private static final long serialVersionUID = 5507432037314593640L;

        /* renamed from: a */
        public boolean f12248a = false;

        /* renamed from: b */
        public boolean f12249b = false;

        /* renamed from: c */
        public C5300g[] f12250c;

        /* renamed from: d */
        public C5295b[] f12251d;

        /* renamed from: e */
        public C5294a[] f12252e;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$f */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5299f implements Serializable {
        private static final long serialVersionUID = 7096991384851649494L;

        /* renamed from: a */
        public float f12253a;

        /* renamed from: b */
        public float f12254b;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.megvii.idcard.sdk.a$g */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5300g implements Serializable {
        private static final long serialVersionUID = -5095788114139817829L;

        /* renamed from: a */
        public float[] f12255a;

        /* renamed from: b */
        public float[] f12256b;

        /* renamed from: c */
        public C5299f[] f12257c;
    }

    /* renamed from: a */
    private static String m13723a(int i) {
        switch (i) {
            case -1:
                return "MG_RETCODE_FAILED";
            case 0:
                return "MG_RETCODE_OK";
            case 1:
                return "MG_RETCODE_INVALID_ARGUMENT";
            case 2:
                return "MG_RETCODE_INVALID_HANDLE";
            case 3:
                return "MG_RETCODE_INDEX_OUT_OF_RANGE";
            default:
                switch (i) {
                    case 101:
                        return "MG_RETCODE_EXPIRE";
                    case 102:
                        return "MG_RETCODE_INVALID_BUNDLEID";
                    case 103:
                        return "MG_RETCODE_INVALID_LICENSE";
                    case 104:
                        return "MG_RETCODE_INVALID_MODEL";
                    default:
                        return null;
                }
        }
    }

    /* renamed from: a */
    public final String m13722a(Context context, byte[] bArr) {
        if (context == null || bArr == null) {
            return m13723a(1);
        }
        long nativeInit = IDCardApi.nativeInit(context, bArr);
        String m13723a = m13723a((int) nativeInit);
        if (m13723a == null) {
            this.f12225a = nativeInit;
            return null;
        }
        return m13723a;
    }

    /* renamed from: a */
    public final C5296c m13724a() {
        long j = this.f12225a;
        if (j == 0) {
            return null;
        }
        float[] nativeGetIDCardConfig = IDCardApi.nativeGetIDCardConfig(j);
        C5296c c5296c = new C5296c();
        c5296c.f12233a = (int) nativeGetIDCardConfig[0];
        c5296c.f12234b = nativeGetIDCardConfig[1];
        c5296c.f12235c = nativeGetIDCardConfig[2];
        c5296c.f12236d = nativeGetIDCardConfig[3];
        c5296c.f12237e = (int) nativeGetIDCardConfig[4];
        c5296c.f12238f = (int) nativeGetIDCardConfig[5];
        c5296c.f12239g = (int) nativeGetIDCardConfig[6];
        c5296c.f12240h = (int) nativeGetIDCardConfig[7];
        c5296c.f12241i = (int) nativeGetIDCardConfig[8];
        c5296c.f12242j = (int) nativeGetIDCardConfig[9];
        c5296c.f12243k = (int) nativeGetIDCardConfig[10];
        return c5296c;
    }

    /* renamed from: a */
    public final void m13718a(C5296c c5296c) {
        long j = this.f12225a;
        if (j == 0) {
            return;
        }
        IDCardApi.nativeSetIDCardConfig(j, c5296c.f12233a, c5296c.f12234b, c5296c.f12235c, c5296c.f12236d, c5296c.f12237e, c5296c.f12238f, c5296c.f12239g, c5296c.f12240h, c5296c.f12241i, c5296c.f12242j, c5296c.f12243k, c5296c.f12244l);
    }

    /* renamed from: a */
    public final C5297d m13717a(byte[] bArr, int i, int i2, int i3) {
        if (this.f12225a == 0) {
            return null;
        }
        C5297d c5297d = new C5297d();
        float[] nativeDetect = IDCardApi.nativeDetect(this.f12225a, bArr, i, i2, i3);
        c5297d.f12245a = nativeDetect[0];
        c5297d.f12246b = nativeDetect[1];
        c5297d.f12247c = nativeDetect[2];
        return c5297d;
    }

    /* renamed from: b */
    public final C5298e m13711b() {
        if (this.f12225a == 0) {
            return null;
        }
        this.f12226b = 0;
        C5298e c5298e = new C5298e();
        float[] nativeCalculateQuality = IDCardApi.nativeCalculateQuality(this.f12225a);
        int i = this.f12226b;
        this.f12226b = i + 1;
        int i2 = (int) nativeCalculateQuality[i];
        C5300g[] c5300gArr = new C5300g[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            C5300g c5300g = new C5300g();
            c5300g.f12255a = new float[3];
            for (int i4 = 0; i4 < c5300g.f12255a.length; i4++) {
                float[] fArr = c5300g.f12255a;
                int i5 = this.f12226b;
                this.f12226b = i5 + 1;
                fArr[i4] = nativeCalculateQuality[i5];
            }
            c5300g.f12256b = new float[3];
            for (int i6 = 0; i6 < c5300g.f12256b.length; i6++) {
                float[] fArr2 = c5300g.f12256b;
                int i7 = this.f12226b;
                this.f12226b = i7 + 1;
                fArr2[i6] = nativeCalculateQuality[i7];
            }
            int i8 = this.f12226b;
            this.f12226b = i8 + 1;
            int i9 = (int) nativeCalculateQuality[i8];
            c5300g.f12257c = new C5299f[i9];
            for (int i10 = 0; i10 < i9; i10++) {
                c5300g.f12257c[i10] = new C5299f();
                C5299f c5299f = c5300g.f12257c[i10];
                int i11 = this.f12226b;
                this.f12226b = i11 + 1;
                c5299f.f12253a = nativeCalculateQuality[i11];
                C5299f c5299f2 = c5300g.f12257c[i10];
                int i12 = this.f12226b;
                this.f12226b = i12 + 1;
                c5299f2.f12254b = nativeCalculateQuality[i12];
            }
            c5300gArr[i3] = c5300g;
        }
        c5298e.f12250c = c5300gArr;
        int i13 = this.f12226b;
        this.f12226b = i13 + 1;
        int i14 = (int) nativeCalculateQuality[i13];
        C5295b[] c5295bArr = new C5295b[i14];
        Log.w("ceshi", "size===" + i14);
        for (int i15 = 0; i15 < i14; i15++) {
            C5295b c5295b = new C5295b();
            c5295b.f12230a = new float[3];
            for (int i16 = 0; i16 < c5295b.f12230a.length; i16++) {
                float[] fArr3 = c5295b.f12230a;
                int i17 = this.f12226b;
                this.f12226b = i17 + 1;
                fArr3[i16] = nativeCalculateQuality[i17];
            }
            c5295b.f12231b = new float[3];
            for (int i18 = 0; i18 < c5295b.f12231b.length; i18++) {
                float[] fArr4 = c5295b.f12231b;
                int i19 = this.f12226b;
                this.f12226b = i19 + 1;
                fArr4[i18] = nativeCalculateQuality[i19];
            }
            int i20 = this.f12226b;
            this.f12226b = i20 + 1;
            int i21 = (int) nativeCalculateQuality[i20];
            c5295b.f12232c = new C5299f[i21];
            for (int i22 = 0; i22 < i21; i22++) {
                c5295b.f12232c[i22] = new C5299f();
                C5299f c5299f3 = c5295b.f12232c[i22];
                int i23 = this.f12226b;
                this.f12226b = i23 + 1;
                c5299f3.f12253a = nativeCalculateQuality[i23];
                C5299f c5299f4 = c5295b.f12232c[i22];
                int i24 = this.f12226b;
                this.f12226b = i24 + 1;
                c5299f4.f12254b = nativeCalculateQuality[i24];
            }
            c5295bArr[i15] = c5295b;
        }
        c5298e.f12251d = c5295bArr;
        int i25 = this.f12226b;
        this.f12226b = i25 + 1;
        c5298e.f12252e = m13715a(nativeCalculateQuality, (int) nativeCalculateQuality[i25]);
        if (c5298e.f12250c.length == 0) {
            c5298e.f12248a = true;
        }
        if (c5298e.f12251d.length == 0) {
            c5298e.f12249b = true;
        }
        return c5298e;
    }

    /* renamed from: a */
    private C5294a[] m13715a(float[] fArr, int i) {
        C5294a[] c5294aArr = new C5294a[i];
        for (int i2 = 0; i2 < i; i2++) {
            C5294a c5294a = new C5294a();
            c5294a.f12227a = new float[3];
            for (int i3 = 0; i3 < c5294a.f12227a.length; i3++) {
                float[] fArr2 = c5294a.f12227a;
                int i4 = this.f12226b;
                this.f12226b = i4 + 1;
                fArr2[i3] = fArr[i4];
            }
            c5294a.f12228b = new float[3];
            for (int i5 = 0; i5 < c5294a.f12228b.length; i5++) {
                float[] fArr3 = c5294a.f12228b;
                int i6 = this.f12226b;
                this.f12226b = i6 + 1;
                fArr3[i5] = fArr[i6];
            }
            int i7 = this.f12226b;
            this.f12226b = i7 + 1;
            int i8 = (int) fArr[i7];
            c5294a.f12229c = new C5299f[i8];
            for (int i9 = 0; i9 < i8; i9++) {
                c5294a.f12229c[i9] = new C5299f();
                C5299f c5299f = c5294a.f12229c[i9];
                int i10 = this.f12226b;
                this.f12226b = i10 + 1;
                c5299f.f12253a = fArr[i10];
                C5299f c5299f2 = c5294a.f12229c[i9];
                int i11 = this.f12226b;
                this.f12226b = i11 + 1;
                c5299f2.f12254b = fArr[i11];
            }
            c5294aArr[i2] = c5294a;
        }
        return c5294aArr;
    }

    /* renamed from: c */
    public final void m13709c() {
        long j = this.f12225a;
        if (j == 0) {
            return;
        }
        IDCardApi.nativeRelease(j);
        this.f12225a = 0L;
    }

    /* renamed from: a */
    public static Rect m13713a(Point[] pointArr, float f) {
        if (pointArr == null || pointArr.length < 2) {
            return null;
        }
        Rect rect = new Rect();
        rect.left = pointArr[0].x;
        rect.top = pointArr[0].y;
        rect.right = pointArr[pointArr.length - 1].x;
        rect.bottom = pointArr[pointArr.length - 1].y;
        for (Point point : pointArr) {
            rect.union(point.x, point.y);
        }
        if (f <= 0.0f) {
            return rect;
        }
        int width = rect.width();
        int height = rect.height();
        float f2 = width * f;
        rect.left = (int) (rect.left - f2);
        float f3 = height * f;
        rect.top = (int) (rect.top - f3);
        rect.right = (int) (rect.right + f2);
        rect.bottom = (int) (rect.bottom + f3);
        return rect;
    }

    /* renamed from: a */
    public static Rect m13714a(Point[] pointArr) {
        return m13713a(pointArr, 0.0f);
    }

    /* renamed from: a */
    public static Bitmap m13716a(byte[] bArr, int i, int i2, Rect rect, int i3) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        rect.left = Math.max(0, rect.left);
        rect.top = Math.max(0, rect.top);
        rect.right = Math.min(rect.right, i - 1);
        rect.bottom = Math.min(rect.bottom, i2 - 1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(rect, 100, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        if (i3 <= 0 || i3 >= Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight())) {
            return decodeByteArray;
        }
        float max = Math.max(decodeByteArray.getHeight(), decodeByteArray.getWidth()) / i3;
        return Bitmap.createScaledBitmap(decodeByteArray, (int) (decodeByteArray.getWidth() / max), (int) (decodeByteArray.getHeight() / max), true);
    }

    /* renamed from: a */
    public static Point[] m13712a(C5299f[] c5299fArr, Rect rect) {
        float width = (c5299fArr[0].f12253a * rect.width()) + rect.left;
        float width2 = (c5299fArr[0].f12253a * rect.width()) + rect.left;
        float height = (c5299fArr[0].f12254b * rect.height()) + rect.top;
        float height2 = (c5299fArr[0].f12254b * rect.height()) + rect.top;
        float f = width2;
        float f2 = width;
        for (int i = 0; i < c5299fArr.length; i++) {
            float width3 = (c5299fArr[i].f12253a * rect.width()) + rect.left;
            float height3 = (c5299fArr[i].f12254b * rect.height()) + rect.top;
            if (f2 > width3) {
                f2 = width3;
            }
            if (f < width3) {
                f = width3;
            }
            if (height > height3) {
                height = height3;
            }
            if (height2 < height3) {
                height2 = height3;
            }
        }
        int i2 = (int) f2;
        int i3 = (int) height;
        int i4 = (int) f;
        int i5 = (int) height2;
        return new Point[]{new Point(i2, i3), new Point(i4, i3), new Point(i4, i5), new Point(i2, i5)};
    }

    /* renamed from: a */
    public static int m13721a(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        long j = 0;
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            j += ((iArr[i2] >> 16) & 255) - ((((iArr[i2] >> 8) & 255) + (iArr[i2] & 255)) / 2);
            i++;
        }
        return (int) (j / i);
    }

    /* renamed from: a */
    public static Bitmap m13719a(Rect rect, byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return m13720a(rect, BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
    }

    /* renamed from: b */
    public static float m13710b(Bitmap bitmap) {
        if (bitmap == null) {
            return 0.0f;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (int i = 0; i < iArr.length; i++) {
            j += (iArr[i] >> 16) & 255;
            j2 += (iArr[i] >> 8) & 255;
            j3 += iArr[i] & 255;
        }
        return (float) ((((j * 0.299d) + (j2 * 0.587d)) + (j3 * 0.114d)) / iArr.length);
    }

    /* renamed from: a */
    public static Bitmap m13720a(Rect rect, Bitmap bitmap) {
        float width = rect.width();
        if (width > bitmap.getWidth()) {
            width = bitmap.getWidth();
        }
        float height = rect.height();
        if (height > bitmap.getHeight()) {
            height = bitmap.getHeight();
        }
        float centerX = rect.centerX() - (width / 2.0f);
        if (centerX < 0.0f) {
            centerX = 0.0f;
        }
        float centerY = rect.centerY() - (height / 2.0f);
        if (centerY < 0.0f) {
            centerY = 0.0f;
        }
        if (centerX + width > bitmap.getWidth()) {
            width = bitmap.getWidth() - centerX;
        }
        if (centerY + height > bitmap.getHeight()) {
            height = bitmap.getHeight() - centerY;
        }
        return Bitmap.createBitmap(bitmap, (int) centerX, (int) centerY, (int) width, (int) height);
    }
}
