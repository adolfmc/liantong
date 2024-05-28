package cn.finalteam.galleryfinal.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecycleViewBitmapUtils {
    public static void recycleViewGroup(ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        synchronized (viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    recycleViewGroup((ViewGroup) childAt);
                } else if (childAt instanceof ImageView) {
                    recycleImageView((ImageView) childAt);
                }
            }
        }
    }

    public static void recycleImageView(View view) {
        Bitmap bitmap;
        if (view != null && (view instanceof ImageView)) {
            ImageView imageView = (ImageView) view;
            Drawable drawable = imageView.getDrawable();
            if (!(drawable instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null || bitmap.isRecycled()) {
                return;
            }
            imageView.setImageBitmap(null);
            bitmap.recycle();
        }
    }
}
