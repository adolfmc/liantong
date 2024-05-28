package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils;

import android.content.Context;
import android.content.UriPermission;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileUriUtils {
    public static String statusPath = "Android/data/com.tencent.mm/MicroMsg/Download";

    public static boolean isGrant(Context context) {
        for (UriPermission uriPermission : context.getContentResolver().getPersistedUriPermissions()) {
            if (uriPermission.isReadPermission() && uriPermission.getUri().toString().equals("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata")) {
                return true;
            }
        }
        return false;
    }

    public static String changeToUri(String str) {
        if (str.endsWith("/")) {
            str = str.substring(0, str.length() - 1);
        }
        String replace = str.replace("/storage/emulated/0/", "").replace("/", "%2F");
        return "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata/document/primary%3A" + replace;
    }

    public static String changeToUri3(String str) {
        String replace = str.replace("/storage/emulated/0/", "").replace("/", "%2F");
        return "content://com.android.externalstorage.documents/tree/primary%3A" + replace;
    }
}
