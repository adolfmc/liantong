package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import com.baidu.p120ar.face.IFaceResultData;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.CaptureResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaptureResult implements ICaptureResult {
    IFaceResultData faceData;
    private Bitmap originPhoto;
    byte[] originPhotoData;
    private Bitmap outputPhoto;
    byte[] outputPhotoData;
    int photoHeight;
    int photoWidth;
    long timestamp;

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public Bitmap getOriginPhoto() {
        byte[] bArr;
        if (this.originPhoto == null && (bArr = this.originPhotoData) != null) {
            this.originPhoto = parseToBitmap(bArr, this.photoWidth, this.photoHeight);
        }
        return this.originPhoto;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public Bitmap getOutputPhoto() {
        byte[] bArr;
        if (this.outputPhoto == null && (bArr = this.outputPhotoData) != null) {
            this.outputPhoto = parseToBitmap(bArr, this.photoWidth, this.photoHeight);
        }
        return this.outputPhoto;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public int getPhotoWidth() {
        return this.photoWidth;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public int getPhotoHeight() {
        return this.photoHeight;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public IFaceResultData getFaceData() {
        return this.faceData;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.baidu.p120ar.capture.ICaptureResult
    public void release() {
        Bitmap bitmap = this.originPhoto;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.originPhoto.recycle();
            this.originPhoto = null;
        }
        Bitmap bitmap2 = this.outputPhoto;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            return;
        }
        this.outputPhoto.recycle();
        this.outputPhoto = null;
    }

    private Bitmap parseToBitmap(byte[] bArr, int i, int i2) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
        return createBitmap;
    }
}
