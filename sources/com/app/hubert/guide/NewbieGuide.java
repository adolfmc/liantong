package com.app.hubert.guide;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import com.app.hubert.guide.core.Builder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NewbieGuide {
    public static final int FAILED = -1;
    public static final int SUCCESS = 1;
    public static final String TAG = "NewbieGuide";

    public static Builder with(Activity activity) {
        return new Builder(activity);
    }

    public static Builder with(Fragment fragment) {
        return new Builder(fragment);
    }

    public static Builder with(android.support.p083v4.app.Fragment fragment) {
        return new Builder(fragment);
    }

    public static void resetLabel(Context context, String str) {
        context.getSharedPreferences("NewbieGuide", 0).edit().putInt(str, 0).apply();
    }
}
