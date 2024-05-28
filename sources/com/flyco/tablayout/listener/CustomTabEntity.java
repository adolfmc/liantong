package com.flyco.tablayout.listener;

import android.support.annotation.DrawableRes;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface CustomTabEntity {
    @DrawableRes
    int getTabSelectedIcon();

    String getTabTitle();

    @DrawableRes
    int getTabUnselectedIcon();
}
