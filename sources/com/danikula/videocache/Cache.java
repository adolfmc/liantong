package com.danikula.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Cache {
    void append(byte[] bArr, int i) throws ProxyCacheException;

    long available() throws ProxyCacheException;

    void close() throws ProxyCacheException;

    void complete() throws ProxyCacheException;

    boolean isCompleted();

    int read(byte[] bArr, long j, int i) throws ProxyCacheException;
}
