package com.baidu.cloud.mediaprocess.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BitmapUtils {
    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeBitmap(Context context, String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        if (!str.contains(File.separator)) {
            if (context == null) {
                return null;
            }
            return BitmapFactory.decodeResource(context.getResources(), getImageIdByName(str, context));
        }
        BitmapFactory.decodeFile(str, options);
        options.inSampleSize = calculateInSampleSize(options, 720, 1280);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static int getImageIdByName(String str, Context context) {
        try {
            String str2 = null;
            int identifier = context.getResources().getIdentifier(str, "drawable", context.getApplicationInfo() == null ? null : context.getApplicationInfo().packageName);
            if (identifier == 0) {
                Resources resources = context.getResources();
                if (context.getApplicationInfo() != null) {
                    str2 = context.getApplicationInfo().packageName;
                }
                identifier = resources.getIdentifier(str, "mipmap", str2);
            }
            if (identifier != 0) {
                return identifier;
            }
            int identifier2 = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
            if (identifier2 == 0) {
                return context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
            }
            return identifier2;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
