package com.king.zxing;

import android.graphics.Bitmap;
import com.google.zxing.Result;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface OnCaptureListener {
    void onHandleDecode(Result result, Bitmap bitmap, float f);
}
