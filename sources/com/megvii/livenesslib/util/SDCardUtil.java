package com.megvii.livenesslib.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SDCardUtil {
    public static final String TAG = "SDCardUtil";

    public static String getExternalDir() {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            str = "";
        }
        if (str.equals("mounted")) {
            if (Utils.getApp().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                return Environment.getExternalStorageDirectory().getAbsolutePath();
            }
            return Utils.getApp().getExternalFilesDir(null).getAbsolutePath();
        }
        return Utils.getApp().getFilesDir().getAbsolutePath();
    }

    public static boolean isExternalStorageReadable() {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            str = "";
        }
        return str.equals("mounted");
    }

    public static String getMediaFileUrl(String str) {
        try {
            String externalDir = getExternalDir(Environment.DIRECTORY_DCIM, str);
            File file = new File(externalDir);
            if (!file.exists()) {
                file.mkdir();
            }
            return externalDir;
        } catch (Exception e) {
            Log.e("SDCardUtil", "getMediaFileUrl(String dirName) 异常:" + e.getMessage());
            return "";
        }
    }

    public static String getDocumentFileUrl(String str) {
        try {
            String externalDir = getExternalDir(Environment.DIRECTORY_DOCUMENTS, str);
            File file = new File(externalDir);
            if (!file.exists()) {
                file.mkdir();
            }
            return externalDir;
        } catch (Exception e) {
            Log.e("SDCardUtil", "getMediaFileUrl(String dirName) 异常:" + e.getMessage());
            return "";
        }
    }

    public static String getExternalDir(String str, String str2) {
        String str3;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(getExternalDir());
            sb.append(File.separator);
            sb.append(str);
            sb.append(File.separator);
            sb.append("Unicom");
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = File.separator + str2;
            }
            sb.append(str3);
            sb.append(File.separator);
            String sb2 = sb.toString();
            File file = new File(sb2);
            if (!file.exists()) {
                file.mkdir();
            }
            Log.d("SDCardUtil", str2 + "生成的地址为:" + sb2);
            return sb2;
        } catch (Exception e) {
            Log.e("SDCardUtil", "getExternalDir(String dirType,String dirName) 异常:" + e.getMessage());
            return "";
        }
    }

    public static String getOwnFileUrl(String str) {
        String str2;
        String absolutePath;
        String str3;
        String str4 = "";
        try {
            try {
                str2 = Environment.getExternalStorageState();
            } catch (NullPointerException unused) {
                str2 = "";
            }
            if (str2.equals("mounted")) {
                absolutePath = Utils.getApp().getExternalFilesDir(null).getAbsolutePath();
            } else {
                absolutePath = Utils.getApp().getFilesDir().getAbsolutePath();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(absolutePath);
            sb.append(File.separator);
            if (TextUtils.isEmpty(str)) {
                str3 = "File";
            } else {
                str3 = str + File.separator;
            }
            sb.append(str3);
            str4 = sb.toString();
            File file = new File(str4);
            if (!file.exists()) {
                file.mkdir();
            }
            Log.d("SDCardUtil", "生成的地址为:" + str4);
        } catch (Exception e) {
            Log.e("SDCardUtil", "getOwnFileUrl(String dirName) 异常:" + e.getMessage());
        }
        return str4;
    }
}
