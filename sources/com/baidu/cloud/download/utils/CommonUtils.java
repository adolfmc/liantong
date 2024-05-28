package com.baidu.cloud.download.utils;

import android.app.Activity;
import android.support.p083v4.app.ActivityCompat;
import java.text.DecimalFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CommonUtils {

    /* renamed from: DF */
    private static final DecimalFormat f4349DF = new DecimalFormat("0.00");
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static String getDownloadPerSize(long j, long j2) {
        return f4349DF.format(((float) j) / 1048576.0f) + "M/" + f4349DF.format(((float) j2) / 1048576.0f) + "M";
    }

    public static void verifyStoragePermissions(Activity activity) {
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
        }
    }
}
