package com.sinovatech.unicom.basic.p315ui.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Base64;
import com.sinovatech.unicom.common.CustomDensityHandler;
import com.sinovatech.unicom.common.ImageCompressUtils;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.ImageMergeUtil */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ImageMergeUtil {
    private static final String TAG = "ImageMergeUtil";

    public static Bitmap createSnapshotWithBottomLogo(Context context, Bitmap bitmap, Bitmap bitmap2) {
        Bitmap fitBitmapShare;
        Bitmap bitmap3;
        try {
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), 2131231991);
            int width = bitmap.getWidth();
            int width2 = decodeResource.getWidth();
            if (width > width2) {
                fitBitmapShare = bitmap;
                bitmap3 = ImageCompressUtils.fitBitmapShare(decodeResource, width);
            } else {
                fitBitmapShare = ImageCompressUtils.fitBitmapShare(bitmap, width2);
                bitmap3 = decodeResource;
            }
            int height = bitmap3.getHeight();
            if (fitBitmapShare == null || bitmap3 == null) {
                return null;
            }
            return drawLogo(context, height, bitmap3, bitmap2, fitBitmapShare, decodeResource);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.logD(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    private static Bitmap drawLogo(Context context, float f, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4) {
        try {
            float f2 = context.getResources().getDisplayMetrics().density;
            float f3 = CustomDensityHandler.sNonCompatDensity;
            float height = (f / bitmap2.getHeight()) * 0.8f;
            Matrix matrix = new Matrix();
            matrix.postScale(height, height);
            Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true);
            Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), 2131231623);
            float height2 = createBitmap.getHeight() / 5;
            Matrix matrix2 = new Matrix();
            matrix2.postScale((createBitmap.getWidth() / 5) / decodeResource.getWidth(), height2 / decodeResource.getHeight());
            return joinBitmap(bitmap3, bitmap, createBitmap, Bitmap.createBitmap(decodeResource, 0, 0, decodeResource.getWidth(), decodeResource.getHeight(), matrix2, true), bitmap4, decodeResource, bitmap2);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.logE(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    private static Bitmap joinBitmap(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Bitmap bitmap7) {
        Bitmap bitmap8 = bitmap;
        Bitmap bitmap9 = bitmap2;
        if (bitmap8 != null) {
            try {
                if (!bitmap.isRecycled() && bitmap9 != null && !bitmap2.isRecycled()) {
                    int width = bitmap2.getWidth();
                    int width2 = bitmap.getWidth() > bitmap2.getWidth() ? bitmap.getWidth() : bitmap2.getWidth();
                    if (bitmap.getWidth() != width2) {
                        bitmap8 = Bitmap.createScaledBitmap(bitmap8, width2, (int) (((bitmap.getHeight() * 1.0f) / bitmap.getWidth()) * width2), false);
                    } else if (bitmap2.getWidth() != width2) {
                        bitmap9 = Bitmap.createScaledBitmap(bitmap9, width2, (int) (((bitmap2.getHeight() * 1.0f) / bitmap2.getWidth()) * width2), false);
                    }
                    int height = bitmap8.getHeight() + bitmap9.getHeight();
                    Bitmap createBitmap = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    Rect rect = new Rect(0, 0, bitmap8.getWidth(), bitmap8.getHeight());
                    Rect rect2 = new Rect(0, 0, bitmap9.getWidth(), bitmap9.getHeight());
                    Rect rect3 = new Rect(0, bitmap8.getHeight(), width2, height);
                    int height2 = (bitmap9.getHeight() - bitmap3.getHeight()) / 2;
                    int height3 = ((bitmap3.getHeight() - bitmap4.getHeight()) / 2) + height2;
                    int i = (int) (width * 0.05f);
                    Rect rect4 = new Rect(0, bitmap8.getHeight(), width2, height);
                    rect4.offset(i, height2);
                    Rect rect5 = new Rect(0, bitmap8.getHeight(), width2, height);
                    rect5.offset(((bitmap3.getWidth() - bitmap4.getWidth()) / 2) + i, height3);
                    canvas.drawBitmap(bitmap8, rect, rect, (Paint) null);
                    canvas.drawBitmap(bitmap9, rect2, rect3, (Paint) null);
                    canvas.drawBitmap(bitmap3, rect2, rect4, (Paint) null);
                    canvas.drawBitmap(bitmap4, rect2, rect5, (Paint) null);
                    return createBitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
                UIUtils.logE(TAG, e.getLocalizedMessage());
                return null;
            }
        }
        return null;
    }

    public static Bitmap stringtoBitmap(String str) {
        try {
            byte[] decode = Base64.decode(str, 2);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
