package com.danikula.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Source {
    void close() throws ProxyCacheException;

    long length() throws ProxyCacheException;

    void open(long j) throws ProxyCacheException;

    int read(byte[] bArr) throws ProxyCacheException;
}
