package com.baidu.cloud.videocache;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface Cache {
    void append(byte[] bArr, int i);

    long available();

    void close();

    void complete();

    boolean isCompleted();

    int read(byte[] bArr, long j, int i);
}
