package com.megvii.kas.livenessdetection.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.YuvImage;
import com.megvii.kas.livenessdetection.DetectionConfig;
import com.megvii.kas.livenessdetection.DetectionFrame;
import com.megvii.kas.livenessdetection.Detector;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import com.megvii.kas.livenessdetection.obf.C5327b;
import com.megvii.kas.livenessdetection.obf.C5330d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: com.megvii.kas.livenessdetection.impl.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5324b extends DetectionFrame {

    /* renamed from: a */
    private int f12333a;

    /* renamed from: b */
    private int f12334b;

    /* renamed from: c */
    private Detector.DetectionType f12335c;

    /* renamed from: d */
    private byte[] f12336d;

    /* renamed from: e */
    private int f12337e;

    public C5324b(byte[] bArr, int i, int i2, int i3, Detector.DetectionType detectionType) {
        this.f12333a = i;
        this.f12334b = i2;
        this.f12337e = i3;
        System.currentTimeMillis();
        this.f12335c = detectionType;
        this.f12336d = bArr;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getRotation() {
        return this.f12337e;
    }

    /* renamed from: a */
    public final Detector.DetectionType m13657a() {
        return this.f12335c;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getImageWidth() {
        return this.f12333a;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getImageHeight() {
        return this.f12334b;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getYUVData() {
        return this.f12336d;
    }

    /* renamed from: a */
    private synchronized byte[] m13656a(int i, Rect rect) {
        return m13655a(i, rect, 80);
    }

    /* renamed from: a */
    private synchronized byte[] m13655a(int i, Rect rect, int i2) {
        if (hasFace()) {
            YuvImage yuvImage = new YuvImage(this.f12336d, 17, this.f12333a, this.f12334b, null);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Matrix matrix = new Matrix();
            matrix.postRotate(360 - this.f12337e, 0.5f, 0.5f);
            RectF rectF = new RectF();
            matrix.mapRect(rectF, this.mFaceInfo.position);
            Rect rect2 = new Rect();
            rect2.left = (int) (rectF.left * this.f12333a);
            rect2.right = (int) (rectF.right * this.f12333a);
            rect2.top = (int) (rectF.top * this.f12334b);
            rect2.bottom = (int) (rectF.bottom * this.f12334b);
            Matrix matrix2 = new Matrix();
            matrix2.postScale(1.5f, 1.5f, rect2.centerX(), rect2.centerY());
            RectF rectF2 = new RectF();
            matrix2.mapRect(rectF2, new RectF(rect2));
            rectF2.left = Math.max(0.0f, rectF2.left);
            rectF2.top = Math.max(0.0f, rectF2.top);
            rectF2.right = Math.min(rectF2.right, this.f12333a);
            rectF2.bottom = Math.min(rectF2.bottom, this.f12334b);
            yuvImage.compressToJpeg(new Rect((int) rectF2.left, (int) rectF2.top, (int) rectF2.right, (int) rectF2.bottom), i2, byteArrayOutputStream);
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                C5330d.m13631a(e.toString());
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            Matrix matrix3 = new Matrix();
            matrix3.postRotate(this.f12337e);
            if (i != -1) {
                float max = Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight()) / i;
                if (max > 1.0f) {
                    float f = 1.0f / max;
                    matrix3.postScale(f, f);
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix3, true);
            float centerX = rect2.centerX() - rectF2.left;
            float centerY = rect2.centerY() - rectF2.top;
            Matrix matrix4 = new Matrix();
            matrix4.setRotate(this.f12337e, 0.5f, 0.5f);
            float[] fArr = new float[2];
            matrix4.mapPoints(fArr, new float[]{centerX / rectF2.width(), centerY / rectF2.height()});
            float width = fArr[0] * createBitmap.getWidth();
            float height = fArr[1] * createBitmap.getHeight();
            float max2 = Math.max(createBitmap.getWidth(), createBitmap.getHeight()) / 1.5f;
            Rect rect3 = rect == null ? new Rect() : rect;
            float f2 = max2 / 2.0f;
            rect3.left = (int) (width - f2);
            rect3.top = (int) (height - f2);
            rect3.right = (int) (width + f2);
            rect3.bottom = (int) (height + f2);
            String m13646a = C5327b.m13646a(rect3);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            createBitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream2);
            try {
                byteArrayOutputStream2.close();
                return EncodeImpl.m13662a(byteArrayOutputStream2.toByteArray(), false, false, 1824058797, m13646a);
            } catch (IOException unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    private synchronized byte[] m13652b(int i, Rect rect, int i2) {
        byte[] byteArray;
        YuvImage yuvImage = new YuvImage(this.f12336d, 17, this.f12333a, this.f12334b, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, this.f12333a, this.f12334b), i2, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            C5330d.m13631a(e.toString());
        }
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray2, 0, byteArray2.length);
        Matrix matrix = new Matrix();
        matrix.postRotate(this.f12337e);
        if (i != -1) {
            float max = Math.max(decodeByteArray.getWidth(), decodeByteArray.getHeight()) / i;
            if (max > 1.0f) {
                float f = 1.0f / max;
                matrix.postScale(f, f);
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(decodeByteArray, 0, 0, decodeByteArray.getWidth(), decodeByteArray.getHeight(), matrix, true);
        if (rect == null) {
            rect = new Rect();
        }
        if (hasFace()) {
            rect.left = (int) (this.mFaceInfo.position.left * createBitmap.getWidth());
            rect.top = (int) (this.mFaceInfo.position.top * createBitmap.getHeight());
            rect.right = (int) (this.mFaceInfo.position.right * createBitmap.getWidth());
            rect.bottom = (int) (this.mFaceInfo.position.bottom * createBitmap.getHeight());
        }
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, i2, byteArrayOutputStream2);
        try {
            byteArrayOutputStream2.close();
            byteArray = byteArrayOutputStream2.toByteArray();
            if (hasFace()) {
                byteArray = EncodeImpl.m13662a(byteArray, false, false, 1824058797, C5327b.m13646a(rect));
            }
        } catch (IOException unused) {
            return null;
        }
        return byteArray;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final synchronized byte[] getCroppedFaceImageData() {
        return m13656a(-1, null);
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final synchronized byte[] getCroppedFaceImageData(int i) {
        return m13656a(i, null);
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData(Rect rect) {
        return m13656a(-1, rect);
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData(int i, Rect rect) {
        return m13656a(i, rect);
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getEncodedFaceImageData(int i, int i2, Rect rect) {
        return EncodeImpl.m13663a(getCroppedFaceImageData(i2, rect), true, true, i);
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getImageData(Rect rect, boolean z, int i, int i2, boolean z2, boolean z3, int i3) {
        if (((z2 || z3) && i3 < 0) || i < 0 || i > 100) {
            return null;
        }
        if (z) {
            if (i2 <= 0) {
                i2 = -1;
            }
            return EncodeImpl.m13663a(m13655a(i2, rect, i), z2, z3, i3);
        }
        if (i2 <= 0) {
            i2 = -1;
        }
        return EncodeImpl.m13663a(m13652b(i2, rect, i), z2, z3, i3);
    }

    /* renamed from: a */
    public final boolean m13654a(DetectionFrame detectionFrame) {
        return detectionFrame == null || !detectionFrame.hasFace() || (this.mFaceInfo == null ? 0.0f : this.mFaceInfo.smoothQuality) > detectionFrame.getFaceInfo().smoothQuality;
    }

    /* renamed from: a */
    public final void m13653a(String str, DetectionConfig detectionConfig, C5327b c5327b) {
        this.mFaceInfo = FaceInfo.FaceInfoFactory.createFaceInfo(str);
        c5327b.m13645a(this.mFaceInfo);
    }
}
