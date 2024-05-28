package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.content.Context;
import android.util.Base64;
import com.megvii.livenesslib.util.SDCardUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.unicom.pay.normal.order.bean.WPPayInfoBean;
import java.io.File;
import java.io.FileInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileUtils {
    public static String fileToBase64(String str) {
        File file = new File(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return Base64.encodeToString(bArr, 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteDirectory(String str) {
        boolean z;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                z = true;
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isFile()) {
                        z = deleteFile(listFiles[i].getAbsolutePath());
                        if (!z) {
                            break;
                        }
                    } else {
                        if (listFiles[i].isDirectory() && !(z = deleteDirectory(listFiles[i].getAbsolutePath()))) {
                            break;
                        }
                    }
                }
            } else {
                z = true;
            }
            return z && file.delete();
        }
        return false;
    }

    public static boolean delete(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile() ? deleteFile(str) : deleteDirectory(str);
        }
        return false;
    }

    public static boolean deleteFile(String str) {
        File file = new File(str);
        return file.exists() && file.isFile() && file.delete();
    }

    public static boolean deleteSaveDir(Context context) {
        try {
            return delete(getSaveDir(context));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getSaveDir(Context context) {
        String ownFileUrl = SDCardUtil.getOwnFileUrl("YT");
        MsLogUtil.m7979d("SDCardUtil", "YT 录制视频地址");
        return ownFileUrl;
    }

    public static String getVideoSaveDir(Context context) {
        String ownFileUrl = SDCardUtil.getOwnFileUrl(WPPayInfoBean.f20229ZL);
        MsLogUtil.m7979d("SDCardUtil", "ZL 录制视频地址");
        return ownFileUrl;
    }
}
