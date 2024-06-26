package com.bumptech.glide.signature;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.bumptech.glide.load.Key;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ApplicationVersionSignature {
    private static final ConcurrentMap<String, Key> PACKAGE_NAME_TO_KEY = new ConcurrentHashMap();
    private static final String TAG = "AppVersionSignature";

    @NonNull
    public static Key obtain(@NonNull Context context) {
        String packageName = context.getPackageName();
        Key key = PACKAGE_NAME_TO_KEY.get(packageName);
        if (key == null) {
            Key obtainVersionSignature = obtainVersionSignature(context);
            Key putIfAbsent = PACKAGE_NAME_TO_KEY.putIfAbsent(packageName, obtainVersionSignature);
            return putIfAbsent == null ? obtainVersionSignature : putIfAbsent;
        }
        return key;
    }

    @VisibleForTesting
    static void reset() {
        PACKAGE_NAME_TO_KEY.clear();
    }

    @NonNull
    private static Key obtainVersionSignature(@NonNull Context context) {
        return new ObjectKey(getVersionCode(getPackageInfo(context)));
    }

    @NonNull
    private static String getVersionCode(@Nullable PackageInfo packageInfo) {
        if (packageInfo != null) {
            return String.valueOf(packageInfo.versionCode);
        }
        return UUID.randomUUID().toString();
    }

    @Nullable
    private static PackageInfo getPackageInfo(@NonNull Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("AppVersionSignature", "Cannot resolve info for" + context.getPackageName(), e);
            return null;
        }
    }

    private ApplicationVersionSignature() {
    }
}
