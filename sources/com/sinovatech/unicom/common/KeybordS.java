package com.sinovatech.unicom.common;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class KeybordS {
    public static void openKeybord(EditText editText, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        inputMethodManager.showSoftInput(editText, 2);
        inputMethodManager.toggleSoftInput(2, 1);
    }

    public static void closeKeybord(EditText editText, Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public static boolean isSoftInputShow(Activity activity) {
        return (activity.getWindow().peekDecorView() == null || !((InputMethodManager) activity.getSystemService("input_method")).isActive() || activity.getWindow().getCurrentFocus() == null) ? false : true;
    }
}
