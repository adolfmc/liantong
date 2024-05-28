package com.sinovatech.unicom.separatemodule.audience.quickrecycler.provider;

import android.content.Context;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class BaseItemProvider<T, V extends BaseViewHolder> {
    public Context mContext;
    public List<T> mData;

    public abstract void convert(V v, T t, int i);

    public abstract int layout();

    public void onClick(V v, T t, int i) {
    }

    public boolean onLongClick(V v, T t, int i) {
        return false;
    }

    public abstract int viewType();
}
