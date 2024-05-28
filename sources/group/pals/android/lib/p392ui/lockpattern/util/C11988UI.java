package group.pals.android.lib.p392ui.lockpattern.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;
import group.pals.android.lib.p392ui.lockpattern.C11982R;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: group.pals.android.lib.ui.lockpattern.util.UI */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11988UI {
    private static final String CLASSNAME = "group.pals.android.lib.ui.lockpattern.util.UI";

    public static void adjustDialogSizeForLargeScreen(Dialog dialog) {
        adjustDialogSizeForLargeScreen(dialog.getWindow());
    }

    @SuppressLint({"ResourceType"})
    public static void adjustDialogSizeForLargeScreen(Window window) {
        int i;
        int i2;
        if (window.isFloating() && window.getContext().getResources().getBoolean(C11982R.bool.alp_is_large_screen)) {
            DisplayMetrics displayMetrics = window.getContext().getResources().getDisplayMetrics();
            boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            Resources resources = window.getContext().getResources();
            if (z) {
                i = C11982R.dimen.aosp_dialog_fixed_width_minor;
            } else {
                i = C11982R.dimen.aosp_dialog_fixed_width_major;
            }
            int fraction = (int) resources.getFraction(i, i3, i3);
            Resources resources2 = window.getContext().getResources();
            if (z) {
                i2 = C11982R.dimen.aosp_dialog_fixed_height_major;
            } else {
                i2 = C11982R.dimen.aosp_dialog_fixed_height_minor;
            }
            window.setLayout(fraction, (int) resources2.getFraction(i2, i4, i4));
        }
    }

    public static int resolveAttribute(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i, typedValue, true)) {
            return typedValue.resourceId;
        }
        return 0;
    }
}
