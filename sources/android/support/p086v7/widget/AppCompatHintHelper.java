package android.support.p086v7.widget;

import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.widget.AppCompatHintHelper */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AppCompatHintHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputConnection onCreateInputConnection(InputConnection inputConnection, EditorInfo editorInfo, View view) {
        if (inputConnection != null && editorInfo.hintText == null) {
            ViewParent parent = view.getParent();
            while (true) {
                if (!(parent instanceof View)) {
                    break;
                } else if (parent instanceof WithHint) {
                    editorInfo.hintText = ((WithHint) parent).getHint();
                    break;
                } else {
                    parent = parent.getParent();
                }
            }
        }
        return inputConnection;
    }

    private AppCompatHintHelper() {
    }
}
