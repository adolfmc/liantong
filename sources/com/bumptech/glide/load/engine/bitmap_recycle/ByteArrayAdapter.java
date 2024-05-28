package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ByteArrayAdapter implements ArrayAdapterInterface<byte[]> {
    private static final String TAG = "ByteArrayPool";

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getElementSizeInBytes() {
        return 1;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public String getTag() {
        return "ByteArrayPool";
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public int getArrayLength(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.bumptech.glide.load.engine.bitmap_recycle.ArrayAdapterInterface
    public byte[] newArray(int i) {
        return new byte[i];
    }
}
