package android.support.p083v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.widget.TintableCompoundButton */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface TintableCompoundButton {
    @Nullable
    ColorStateList getSupportButtonTintList();

    @Nullable
    PorterDuff.Mode getSupportButtonTintMode();

    void setSupportButtonTintList(@Nullable ColorStateList colorStateList);

    void setSupportButtonTintMode(@Nullable PorterDuff.Mode mode);
}
