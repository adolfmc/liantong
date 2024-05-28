package com.sinovatech.unicom.separatemodule.dialog.action;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface KeyboardAction {
    void hideKeyboard(View view);

    void showKeyboard(View view);

    void toggleSoftInput(View view);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.KeyboardAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
        public static void $default$showKeyboard(KeyboardAction keyboardAction, View view) {
            InputMethodManager inputMethodManager;
            if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.showSoftInput(view, 2);
        }

        public static void $default$hideKeyboard(KeyboardAction keyboardAction, View view) {
            InputMethodManager inputMethodManager;
            if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 2);
        }

        public static void $default$toggleSoftInput(KeyboardAction keyboardAction, View view) {
            InputMethodManager inputMethodManager;
            if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.toggleSoftInput(0, 0);
        }
    }
}
