package com.sinovatech.unicom.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import com.megvii.livenesslib.util.SDCardUtil;
import java.io.File;
import java.io.FileOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PhotoAlbum {
    public static boolean base64StorePhotos(Context context, String str) {
        if (context.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", "com.sinovatech.unicom.ui") == 0) {
            return saveImageToGallery(context, FileTools.base64ToBitmap(str));
        }
        UIUtils.toast("请打开存储卡存储权限");
        return false;
    }

    private static boolean saveImageToGallery(Context context, Bitmap bitmap) {
        String str = SDCardUtil.getMediaFileUrl("Pic") + (System.currentTimeMillis() / 1000) + ".jpg";
        if (!FileTools.fileIsExists(str)) {
            FileTools.createPath(str);
        }
        File file = new File(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 60, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
