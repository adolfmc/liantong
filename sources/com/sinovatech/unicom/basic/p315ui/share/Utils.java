package com.sinovatech.unicom.basic.p315ui.share;

import android.graphics.Bitmap;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.share.Utils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Utils {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.Utils$ImageScaleListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ImageScaleListener {
        void onImageScale(float f);
    }

    public static void getImageScale(Bitmap bitmap, final SubsamplingScaleImageView subsamplingScaleImageView, final ImageScaleListener imageScaleListener) {
        if (bitmap == null) {
            imageScaleListener.onImageScale(2.0f);
            return;
        }
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        subsamplingScaleImageView.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.share.Utils.1
            @Override // java.lang.Runnable
            public void run() {
                int width2 = SubsamplingScaleImageView.this.getWidth();
                int height2 = SubsamplingScaleImageView.this.getHeight();
                int i = width;
                float f = (i <= width2 || height > height2) ? 1.0f : (width2 * 1.0f) / i;
                int i2 = width;
                if (i2 <= width2 && height > height2) {
                    f = (width2 * 1.0f) / i2;
                }
                int i3 = width;
                if (i3 < width2 && height < height2) {
                    f = (width2 * 1.0f) / i3;
                }
                int i4 = width;
                if (i4 > width2 && height > height2) {
                    f = (width2 * 1.0f) / i4;
                }
                imageScaleListener.onImageScale(f);
            }
        });
    }
}
