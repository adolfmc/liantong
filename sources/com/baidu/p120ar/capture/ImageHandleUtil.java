package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.databasic.ReserveHandleData;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.ImageHandleUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ImageHandleUtil {
    ImageHandleUtil() {
    }

    public static long createBase64ImagesHandle(AlgoHandleController algoHandleController, String[] strArr, int i) {
        if (strArr == null || strArr.length == 0) {
            return 0L;
        }
        Bitmap[] bitmapArr = new Bitmap[strArr.length];
        int length = strArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            byte[] decode = Base64.decode(strArr[i2], 0);
            bitmapArr[i2] = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        }
        long createImagesHandle = createImagesHandle(algoHandleController, bitmapArr, i);
        for (Bitmap bitmap : bitmapArr) {
            bitmap.recycle();
        }
        return createImagesHandle;
    }

    public static long createImagesHandle(AlgoHandleController algoHandleController, Bitmap[] bitmapArr, int i) {
        int i2;
        int i3;
        int i4;
        ArrayList arrayList;
        int[] iArr;
        int[] iArr2;
        long j;
        int i5;
        long j2 = 0;
        if (bitmapArr == null || bitmapArr.length == 0) {
            return 0L;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int[] iArr3 = new int[bitmapArr.length];
        int[] iArr4 = new int[bitmapArr.length];
        int[] iArr5 = new int[bitmapArr.length];
        ArrayList arrayList2 = new ArrayList();
        int length = bitmapArr.length;
        int i6 = 0;
        long j3 = 0;
        while (i6 < length) {
            Bitmap bitmap = bitmapArr[i6];
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (j3 == j2) {
                j = algoHandleController.createHandle();
                i2 = height;
                i3 = i6;
                i4 = length;
                arrayList = arrayList2;
                iArr = iArr5;
                iArr2 = iArr4;
                algoHandleController.setHandleInput(j, i, currentTimeMillis, 4, width, i2, false, 0, false, null);
                i5 = width;
            } else {
                i2 = height;
                i3 = i6;
                i4 = length;
                arrayList = arrayList2;
                iArr = iArr5;
                iArr2 = iArr4;
                j = j3;
                i5 = width;
            }
            iArr3[i3] = i5;
            int i7 = i2;
            int[] iArr6 = iArr2;
            iArr6[i3] = i7;
            iArr[i3] = 2;
            arrayList.add(bitmapToBytes(bitmap));
            Log.d("ChildLook", "image w: " + i5 + " h: " + i7);
            i6 = i3 + 1;
            arrayList2 = arrayList;
            iArr5 = iArr;
            iArr4 = iArr6;
            j3 = j;
            length = i4;
            j2 = 0;
        }
        ReserveHandleData reserveHandleData = new ReserveHandleData();
        reserveHandleData.setByteDataSize(bitmapArr.length);
        reserveHandleData.setByteWidths(iArr3);
        reserveHandleData.setByteHeights(iArr4);
        reserveHandleData.setByteFormats(iArr5);
        reserveHandleData.setByteArrayListData(arrayList2);
        AlgoHandleAdapter.setHandleReserveData(j3, reserveHandleData);
        return j3;
    }

    private static byte[] bitmapToBytes(Bitmap bitmap) {
        byte[] bArr = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
        bitmap.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        return bArr;
    }
}
