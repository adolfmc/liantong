package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SelectWechatUtils {
    private static final String TAG = "selectWechatFile";

    @RequiresApi(api = 21)
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        String[] split = DocumentsContract.getDocumentId(uri).split(":");
        String str = split[0];
        if ("primary".equalsIgnoreCase(str)) {
            return Environment.getExternalStorageDirectory() + "/" + split[1];
        } else if (Build.VERSION.SDK_INT > 19) {
            File[] externalMediaDirs = context.getExternalMediaDirs();
            if (externalMediaDirs.length > 1) {
                String absolutePath = externalMediaDirs[1].getAbsolutePath();
                return absolutePath.substring(0, absolutePath.indexOf("Android")) + split[1];
            }
            return "";
        } else {
            return "/storage/" + str + "/" + split[1];
        }
    }

    public static boolean isLatestWeek(String str) throws Exception {
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(5, -7);
        return calendar.getTime().getTime() < parse.getTime();
    }
}
