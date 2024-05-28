package com.p319ss.android.socialbase.downloader.network.connectionpool;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.connectionpool.IFakeDownloadHttpConnection */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IFakeDownloadHttpConnection {
    void execute() throws Exception;

    boolean isRequesting();

    boolean isSuccessful();

    boolean isValid();

    void joinExecute() throws InterruptedException;
}
