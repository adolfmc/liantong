package com.gmrz.android.uaf.framework.service;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import com.gmrz.android.client.utils.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FacetIDsValidator {
    private static final String TAG = "FacetIDsValidator";
    private final String mAppID;
    private final Context mContext;

    public FacetIDsValidator(String str, Context context) {
        this.mContext = context;
        this.mAppID = str;
    }

    public String resolveFacetID(int i, Activity activity) {
        String str;
        PackageManager packageManager = this.mContext.getPackageManager();
        if (activity != null) {
            str = activity.getCallingPackage();
        } else {
            String[] packagesForUid = packageManager.getPackagesForUid(i);
            if (packagesForUid == null) {
                return null;
            }
            try {
                str = packageManager.getPackageInfo(packagesForUid[0], 0).packageName;
            } catch (PackageManager.NameNotFoundException e) {
                Logger.m15891e(TAG, "Failed to get packageName", e);
                return null;
            }
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(str, 64).signatures;
            if (signatureArr.length > 0) {
                Signature signature = signatureArr[0];
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
                messageDigest.update(signature.toByteArray());
                return "android:apk-key-hash:" + Base64.encodeToString(messageDigest.digest(), 3);
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e2) {
            Logger.m15891e(TAG, "Failed to generate FacetID", e2);
        }
        return null;
    }
}
