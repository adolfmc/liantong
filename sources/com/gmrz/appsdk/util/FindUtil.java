package com.gmrz.appsdk.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FindUtil {
    public static List<String> getPackNameByIntent(Context context, Intent intent) {
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 164)) {
            if (hashSet.add(resolveInfo.activityInfo.packageName)) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        return arrayList;
    }
}
