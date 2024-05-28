package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/saveImageToPhotosAlbum")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SaveImageToAlbumJSPlugin extends BaseJSPlugin {
    private String TAG = "SaveImageToPhotos";
    private Bitmap bitmapImage;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("base64Data");
            if (!TextUtils.isEmpty(optString)) {
                try {
                    byte[] decode = Base64.decode(optString, 0);
                    this.bitmapImage = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                } catch (Exception e) {
                    e.printStackTrace();
                    String str = this.TAG;
                    MsLogUtil.m7979d(str, "bimap转换有误:" + e.getMessage());
                }
                if (this.bitmapImage != null) {
                    if (UIUtils.checkPermissions("android.permission.WRITE_EXTERNAL_STORAGE")) {
                        if (saveImageToGallery()) {
                            callbackSuccess(new JSONObject());
                            return;
                        } else {
                            callbackFail("13", "系统错误，图片保存到系统相册失败");
                            return;
                        }
                    }
                    callbackFail("12", "没有访问相册系统的权限，此时应该引导用户去开启权限");
                    return;
                }
                callbackFail("11", "图片的base64有问题，为空串或者转图片失败");
                return;
            }
            callbackFail("11", "图片的base64有问题，为空串或者转图片失败");
        } catch (Exception e2) {
            e2.printStackTrace();
            callbackFail("10", "程序异常" + e2.getMessage());
        }
    }

    private boolean saveImageToGallery() {
        if (Build.VERSION.SDK_INT < 29) {
            MsLogUtil.m7979d(this.TAG, "android10.0版本以下");
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (externalStoragePublicDirectory.exists() || externalStoragePublicDirectory.mkdirs()) {
                File file = new File(externalStoragePublicDirectory, (System.currentTimeMillis() / 1000) + ".png");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    boolean compress = this.bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.activityContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
                    return compress;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return false;
        }
        MsLogUtil.m7979d(this.TAG, "android10.0版本以上");
        String str = Environment.DIRECTORY_PICTURES;
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", Long.valueOf(System.currentTimeMillis() / 1000));
        contentValues.put("mime_type", "image/png");
        contentValues.put("relative_path", str);
        Uri insert = this.activityContext.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        if (insert != null) {
            try {
                OutputStream openOutputStream = this.activityContext.getContentResolver().openOutputStream(insert);
                boolean compress2 = this.bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, openOutputStream);
                openOutputStream.flush();
                openOutputStream.close();
                return compress2;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
