package com.cjt2325.cameralibrary.util;

import android.graphics.Bitmap;
import android.os.Environment;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileUtil {
    private static final String TAG = "CJT";
    private static final File parentPath = Environment.getExternalStorageDirectory();
    private static String storagePath = "";
    private static String DST_FOLDER_NAME = "JCamera";

    private static String initPath() {
        if (storagePath.equals("")) {
            storagePath = parentPath.getAbsolutePath() + File.separator + DST_FOLDER_NAME;
            File file = new File(storagePath);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return storagePath;
    }

    public static String saveBitmap(String str, Bitmap bitmap) {
        DST_FOLDER_NAME = str;
        String str2 = initPath() + File.separator + "picture_" + System.currentTimeMillis() + ".jpg";
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str2));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return str2;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
