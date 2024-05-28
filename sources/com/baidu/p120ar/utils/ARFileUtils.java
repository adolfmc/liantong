package com.baidu.p120ar.utils;

import android.content.Context;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ARFileUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ARFileUtils {
    public static final String AR_3D_SHADER_DB_PATH = "/sdcard/arresource/shaderDB";
    public static final String AR_APP_NAME = "AR";
    public static final String AR_RESOURCE_CACHE_DIR = "/ARResource";
    public static final String AR_RESOURCE_CACHE_DIR_HIDE = "/.ARResource";
    public static final String AR_UNZIP_ROOT_DIR = "ar";
    public static final String CASE_RES_CONFIG = "res_config.json";
    public static final String CONFIG = "config.json";
    public static final String DEFAULT_FILE_PATH = "res/default.json";
    public static final String DUMIX_RES_FILE = "dumix_res.json";
    public static final String FACE_FILE = "face_metadata.json";
    public static final String TARGET_FILE = "targets.json";
    public static final String VOICE_FILE = "res/voice.json";
    public static String mPackageName;

    private ARFileUtils() {
    }

    public static void setPackageName(String str) {
        if (mPackageName == null) {
            mPackageName = str;
        }
    }

    public static String getPackageName() {
        return mPackageName;
    }

    public static boolean deleteDir(File file) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String getFaceJsonPath(String str) {
        return str + "/face_metadata.json";
    }

    public static String getDumixResJsonPath(String str) {
        return str + "/ar/dumix_res.json";
    }

    public static File generatorUniqueDirtory(String str) {
        String transferLongToDate = transferLongToDate("yyyy-MM-dd-HH-mm-ss", Long.valueOf(System.currentTimeMillis()));
        File file = new File(str + File.separator + transferLongToDate);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    public static String transferLongToDate(String str, Long l) {
        return new SimpleDateFormat(str).format(new Date(l.longValue()));
    }

    public static void writeBytesToFile(String str, byte[] bArr) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str, false));
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static String getARCaseTempDir(Context context, String str) {
        String aRCaseDirPath = getARCaseDirPath(context, str);
        if (aRCaseDirPath != null) {
            return aRCaseDirPath + File.separator + "temp";
        }
        return null;
    }

    public static String getARCaseDirPath(Context context, String str) {
        String duMixTempStoragePath = FileUtils.getDuMixTempStoragePath(context);
        if (duMixTempStoragePath != null) {
            return duMixTempStoragePath + File.separator + "sticker" + File.separator + "bar_" + str;
        }
        return null;
    }

    public static String getARCaseFullPath(Context context, String str) {
        String aRCaseDirPath = getARCaseDirPath(context, str);
        if (aRCaseDirPath != null) {
            return aRCaseDirPath + File.separator + "ar";
        }
        return null;
    }
}
