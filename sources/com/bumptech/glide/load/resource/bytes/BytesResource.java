package com.bumptech.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BytesResource implements Resource<byte[]> {
    private final byte[] bytes;

    @Override // com.bumptech.glide.load.engine.Resource
    public void recycle() {
    }

    public BytesResource(byte[] bArr) {
        this.bytes = (byte[]) Preconditions.checkNotNull(bArr);
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    @NonNull
    public byte[] get() {
        return this.bytes;
    }

    @Override // com.bumptech.glide.load.engine.Resource
    public int getSize() {
        return this.bytes.length;
    }
}
