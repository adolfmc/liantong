package com.zhpan.bannerview.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ViewHolder<T> {
    View createView(ViewGroup viewGroup, Context context, int i);

    void onBind(Context context, T t, int i, int i2);
}
