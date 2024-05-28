package com.xiaomi.push.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11600j {
    /* renamed from: a */
    public static boolean m2525a(Context context, ComponentName componentName) {
        try {
            new Intent().setComponent(componentName);
            context.getPackageManager().getActivityInfo(componentName, 128);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static ComponentName m2524a(Context context, Intent intent) {
        if (intent != null) {
            try {
                ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
                if (resolveActivity != null) {
                    return new ComponentName(resolveActivity.activityInfo.packageName, TextUtils.isEmpty(resolveActivity.activityInfo.targetActivity) ? resolveActivity.activityInfo.name : resolveActivity.activityInfo.targetActivity);
                }
                return null;
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }
}
