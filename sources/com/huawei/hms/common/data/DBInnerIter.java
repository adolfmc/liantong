package com.huawei.hms.common.data;

import com.huawei.hms.common.internal.Preconditions;
import java.util.Iterator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DBInnerIter<O> implements Iterator<O> {
    protected final DataBuffer<O> dataBuffer;
    protected int index = -1;

    public DBInnerIter(DataBuffer<O> dataBuffer) {
        Preconditions.checkNotNull(dataBuffer, "dataBuffer cannot be null");
        this.dataBuffer = dataBuffer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index + 1 < this.dataBuffer.getCount();
    }

    @Override // java.util.Iterator
    public O next() {
        if (hasNext()) {
            DataBuffer<O> dataBuffer = this.dataBuffer;
            int i = this.index + 1;
            this.index = i;
            return dataBuffer.get(i);
        }
        return null;
    }
}
