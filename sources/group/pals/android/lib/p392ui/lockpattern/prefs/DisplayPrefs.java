package group.pals.android.lib.p392ui.lockpattern.prefs;

import android.content.Context;
import group.pals.android.lib.p392ui.lockpattern.C11982R;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.prefs.DisplayPrefs */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DisplayPrefs extends Prefs {
    public static boolean isStealthMode(Context context) {
        return m2014p(context).getBoolean(context.getString(C11982R.string.alp_pkey_display_stealth_mode), context.getResources().getBoolean(C11982R.bool.alp_pkey_display_stealth_mode_default));
    }

    public static void setStealthMode(Context context, boolean z) {
        m2014p(context).edit().putBoolean(context.getString(C11982R.string.alp_pkey_display_stealth_mode), z).commit();
    }

    public static int getMinWiredDots(Context context) {
        return m2014p(context).getInt(context.getString(C11982R.string.alp_pkey_display_min_wired_dots), context.getResources().getInteger(C11982R.integer.alp_pkey_display_min_wired_dots_default));
    }

    public static void setMinWiredDots(Context context, int i) {
        if (i <= 0 || i > 9) {
            i = context.getResources().getInteger(C11982R.integer.alp_pkey_display_min_wired_dots_default);
        }
        m2014p(context).edit().putInt(context.getString(C11982R.string.alp_pkey_display_min_wired_dots), i).commit();
    }

    public static int getMaxRetry(Context context) {
        return m2014p(context).getInt(context.getString(C11982R.string.alp_pkey_display_max_retry), context.getResources().getInteger(C11982R.integer.alp_pkey_display_max_retry_default));
    }

    public static void setMaxRetry(Context context, int i) {
        if (i <= 0) {
            i = context.getResources().getInteger(C11982R.integer.alp_pkey_display_max_retry_default);
        }
        m2014p(context).edit().putInt(context.getString(C11982R.string.alp_pkey_display_max_retry), i).commit();
    }

    public static int getCaptchaWiredDots(Context context) {
        return m2014p(context).getInt(context.getString(C11982R.string.alp_pkey_display_captcha_wired_dots), context.getResources().getInteger(C11982R.integer.alp_pkey_display_captcha_wired_dots_default));
    }

    public static void setCaptchaWiredDots(Context context, int i) {
        if (i <= 0 || i > 9) {
            i = context.getResources().getInteger(C11982R.integer.alp_pkey_display_captcha_wired_dots_default);
        }
        m2014p(context).edit().putInt(context.getString(C11982R.string.alp_pkey_display_captcha_wired_dots), i).commit();
    }
}
