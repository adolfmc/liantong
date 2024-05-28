package com.huawei.hms.common.data;

import android.os.Bundle;
import com.huawei.hms.common.api.Releasable;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DataBuffer<T> extends Releasable, Iterable<T> {
    @Deprecated
    void close();

    T get(int i);

    int getCount();

    Bundle getMetadata();

    @Deprecated
    boolean isClosed();

    Iterator<T> iterator();

    @Override // com.huawei.hms.common.api.Releasable
    void release();

    Iterator<T> singleRefIterator();
}
