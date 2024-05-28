package com.gmrz.android.client.asm.api;

import android.content.ComponentName;
import android.graphics.drawable.Drawable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IAsmBinder {
    boolean bind();

    ComponentName getComponentName();

    Drawable getIcon();

    void unbind();
}
