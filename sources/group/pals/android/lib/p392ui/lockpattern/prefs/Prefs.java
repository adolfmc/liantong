package group.pals.android.lib.p392ui.lockpattern.prefs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.prefs.Prefs */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Prefs {
    public static final String UID = "a6eedbe5-1cf9-4684-8134-ad4ec9f6a131";

    public static final String genPreferenceFilename() {
        return String.format("%s_%s", "android-lockpattern", "a6eedbe5-1cf9-4684-8134-ad4ec9f6a131");
    }

    public static final String genDatabaseFilename(String str) {
        return String.format("%s_%s_%s", "android-lockpattern", "a6eedbe5-1cf9-4684-8134-ad4ec9f6a131", str);
    }

    @TargetApi(11)
    /* renamed from: p */
    public static SharedPreferences m2014p(Context context) {
        return context.getApplicationContext().getSharedPreferences(genPreferenceFilename(), 4);
    }

    @TargetApi(11)
    public static void setupPreferenceManager(Context context, PreferenceManager preferenceManager) {
        preferenceManager.setSharedPreferencesMode(4);
        preferenceManager.setSharedPreferencesName(genPreferenceFilename());
    }
}
