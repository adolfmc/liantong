package com.p319ss.android.socialbase.downloader.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.network.IDownloadDns */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadDns {
    List<InetAddress> lookup(String str) throws UnknownHostException;
}
