package com.sinovatech.unicom.separatemodule.audience.quickrecycler.util;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseMultiItemQuickAdapter;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class MultiTypeDelegate<T> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    private boolean autoMode;
    private SparseIntArray layouts;
    private boolean selfMode;

    protected abstract int getItemType(T t);

    public MultiTypeDelegate(SparseIntArray sparseIntArray) {
        this.layouts = sparseIntArray;
    }

    public MultiTypeDelegate() {
    }

    public final int getDefItemViewType(List<T> list, int i) {
        T t = list.get(i);
        if (t != null) {
            return getItemType(t);
        }
        return -255;
    }

    public final int getLayoutId(int i) {
        return this.layouts.get(i, BaseMultiItemQuickAdapter.TYPE_NOT_FOUND);
    }

    private void addItemType(int i, @LayoutRes int i2) {
        if (this.layouts == null) {
            this.layouts = new SparseIntArray();
        }
        this.layouts.put(i, i2);
    }

    public MultiTypeDelegate registerItemTypeAutoIncrease(@LayoutRes int... iArr) {
        this.autoMode = true;
        checkMode(this.selfMode);
        for (int i = 0; i < iArr.length; i++) {
            addItemType(i, iArr[i]);
        }
        return this;
    }

    public MultiTypeDelegate registerItemType(int i, @LayoutRes int i2) {
        this.selfMode = true;
        checkMode(this.autoMode);
        addItemType(i, i2);
        return this;
    }

    private void checkMode(boolean z) {
        if (z) {
            throw new IllegalArgumentException("Don't mess two register mode");
        }
    }
}
