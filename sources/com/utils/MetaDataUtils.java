package com.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MetaDataUtils {
    private static final String KEY_SDK_HASH_TAG = "com.gmrz.sdk.hash.salt";
    private static final String TAG = "MetaDataUtils";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum HASH_SALT_TYPE {
        FULL,
        PART,
        NONE
    }

    public static HASH_SALT_TYPE getHashSaltType(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return HASH_SALT_TYPE.NONE;
            }
            String string = bundle.getString("com.gmrz.sdk.hash.salt");
            if (string != null && !TextUtils.isEmpty(string)) {
                return HASH_SALT_TYPE.valueOf(string.toUpperCase());
            }
            return HASH_SALT_TYPE.NONE;
        } catch (Exception e) {
            e.printStackTrace();
            return HASH_SALT_TYPE.NONE;
        }
    }
}
