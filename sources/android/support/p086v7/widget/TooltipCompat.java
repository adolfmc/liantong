package android.support.p086v7.widget;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.widget.TooltipCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TooltipCompat {
    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 26) {
            view.setTooltipText(charSequence);
        } else {
            TooltipCompatHandler.setTooltipText(view, charSequence);
        }
    }

    private TooltipCompat() {
    }
}
