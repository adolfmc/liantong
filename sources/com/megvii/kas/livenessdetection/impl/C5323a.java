package com.megvii.kas.livenessdetection.impl;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import com.megvii.kas.livenessdetection.DetectionConfig;
import com.megvii.kas.livenessdetection.DetectionFrame;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import com.megvii.kas.livenessdetection.obf.C5327b;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: com.megvii.kas.livenessdetection.impl.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5323a extends DetectionFrame {

    /* renamed from: a */
    private Bitmap f12331a;

    /* renamed from: b */
    private byte[] f12332b;

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData(int i) {
        return null;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData(int i, Rect rect) {
        return null;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData(Rect rect) {
        return null;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getEncodedFaceImageData(int i, int i2, Rect rect) {
        return null;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getImageData(Rect rect, boolean z, int i, int i2, boolean z2, boolean z3, int i3) {
        return null;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getRotation() {
        return 0;
    }

    public C5323a(Bitmap bitmap) {
        this.f12331a = bitmap;
    }

    /* renamed from: b */
    private boolean m13659b() {
        Bitmap bitmap = this.f12331a;
        return (bitmap == null || bitmap.isRecycled() || !Bitmap.Config.ARGB_8888.equals(this.f12331a.getConfig())) ? false : true;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getImageWidth() {
        if (m13659b()) {
            return this.f12331a.getWidth();
        }
        return -1;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final int getImageHeight() {
        if (m13659b()) {
            return this.f12331a.getHeight();
        }
        return -1;
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final synchronized byte[] getYUVData() {
        return null;
    }

    /* renamed from: c */
    private synchronized void m13658c() {
        if (this.mFaceInfo == null) {
            return;
        }
        RectF rectF = this.mFaceInfo.position;
        float width = this.f12331a.getWidth();
        float height = this.f12331a.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(this.f12331a, (int) (rectF.left * width), (int) (rectF.top * height), (int) (rectF.width() * width), (int) (rectF.height() * height));
        if (createBitmap == null) {
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        createBitmap.recycle();
        try {
            byteArrayOutputStream.close();
        } catch (IOException unused) {
        }
        this.f12332b = byteArrayOutputStream.toByteArray();
    }

    @Override // com.megvii.kas.livenessdetection.DetectionFrame
    public final byte[] getCroppedFaceImageData() {
        if (this.f12332b == null && m13659b() && hasFace()) {
            m13658c();
        }
        return this.f12332b;
    }

    /* renamed from: a */
    public final byte[] m13661a() {
        if (m13659b()) {
            Bitmap bitmap = this.f12331a;
            byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight()];
            for (int i = 0; i < bitmap.getHeight(); i++) {
                for (int i2 = 0; i2 < bitmap.getWidth(); i2++) {
                    int pixel = bitmap.getPixel(i2, i);
                    bArr[(bitmap.getWidth() * i) + i2] = (byte) ((((((pixel >> 16) & 255) * 299) + (((pixel >> 8) & 255) * 587)) + ((pixel & 255) * 114)) / 1000);
                }
            }
            return bArr;
        }
        return null;
    }

    /* renamed from: a */
    public final void m13660a(String str, DetectionConfig detectionConfig, C5327b c5327b) {
        this.mFaceInfo = FaceInfo.FaceInfoFactory.createFaceInfo(str);
        if (this.mFaceInfo != null) {
            c5327b.m13645a(this.mFaceInfo);
        }
    }
}
