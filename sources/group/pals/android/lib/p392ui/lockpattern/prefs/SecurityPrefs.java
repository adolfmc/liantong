package group.pals.android.lib.p392ui.lockpattern.prefs;

import android.content.Context;
import group.pals.android.lib.p392ui.lockpattern.C11982R;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.prefs.SecurityPrefs */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SecurityPrefs extends Prefs {
    public static boolean isAutoSavePattern(Context context) {
        return m2014p(context).getBoolean(context.getString(C11982R.string.alp_pkey_sys_auto_save_pattern), context.getResources().getBoolean(C11982R.bool.alp_pkey_sys_auto_save_pattern_default));
    }

    public static void setAutoSavePattern(Context context, boolean z) {
        m2014p(context).edit().putBoolean(context.getString(C11982R.string.alp_pkey_sys_auto_save_pattern), z).commit();
        if (z) {
            return;
        }
        setPattern(context, "0", null);
    }

    public static char[] getPattern(Context context, String str) {
        String string = m2014p(context).getString(context.getString(C11982R.string.alp_pkey_sys_pattern) + str, null);
        if (string == null) {
            return null;
        }
        return string.toCharArray();
    }

    public static void setPattern(Context context, String str, char[] cArr) {
        m2014p(context).edit().putString(context.getString(C11982R.string.alp_pkey_sys_pattern) + str, cArr != null ? new String(cArr) : null).commit();
    }

    public static char[] getEncrypterClass(Context context) {
        String string = m2014p(context).getString(context.getString(C11982R.string.alp_pkey_sys_encrypter_class), null);
        if (string == null) {
            return null;
        }
        return string.toCharArray();
    }

    public static void setEncrypterClass(Context context, Class<?> cls) {
        setEncrypterClass(context, cls != null ? cls.getName().toCharArray() : null);
    }

    public static void setEncrypterClass(Context context, char[] cArr) {
        m2014p(context).edit().putString(context.getString(C11982R.string.alp_pkey_sys_encrypter_class), cArr != null ? new String(cArr) : null).commit();
    }
}
