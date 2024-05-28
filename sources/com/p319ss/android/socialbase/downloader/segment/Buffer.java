package com.p319ss.android.socialbase.downloader.segment;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.segment.Buffer */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Buffer {
    public final byte[] data;
    public Buffer next;
    IOutput output;
    public int size;

    public Buffer(int i) {
        this.data = new byte[i];
    }
}
