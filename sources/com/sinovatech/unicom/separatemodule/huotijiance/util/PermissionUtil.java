package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.content.Context;
import android.support.p083v4.content.ContextCompat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PermissionUtil {
    public static final int REQUEST_CODE_CAMERA = 1001;
    public static final int REQUEST_CODE_WRITE_STORAGE = 1002;
    public static String[] mShootVideoPermissions = {"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    public static String[] mWriteStoragePermissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
    public static String[] audioPermissions = {"android.permission.RECORD_AUDIO"};
    public static String[] cameraPermissions = {"android.permission.CAMERA"};
    public static String[] allPermissions = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    public static String[] videoPermissions = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO", "android.permission.WRITE_EXTERNAL_STORAGE"};

    public static boolean checkPermission(Context context, String[] strArr) {
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkShootPermission(Context context) {
        for (String str : mShootVideoPermissions) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkStoragePermission(Context context) {
        for (String str : mShootVideoPermissions) {
            if (ContextCompat.checkSelfPermission(context, str) != 0) {
                return false;
            }
        }
        return true;
    }
}
