package android.support.p086v7.widget;

import android.graphics.Rect;
import android.support.annotation.RestrictTo;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: android.support.v7.widget.FitWindowsViewGroup */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface FitWindowsViewGroup {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v7.widget.FitWindowsViewGroup$OnFitSystemWindowsListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
