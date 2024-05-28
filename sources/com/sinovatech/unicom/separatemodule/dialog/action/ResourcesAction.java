package com.sinovatech.unicom.separatemodule.dialog.action;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface ResourcesAction {
    @ColorInt
    int getColor(@ColorRes int i);

    Context getContext();

    Drawable getDrawable(@DrawableRes int i);

    Resources getResources();

    String getString(@StringRes int i);

    String getString(@StringRes int i, Object... objArr);

    <S> S getSystemService(@NonNull Class<S> cls);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.dialog.action.ResourcesAction$-CC  reason: invalid class name */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public final /* synthetic */ class CC {
    }
}
