package com.baidu.cloud.plugin;

import android.support.annotation.NonNull;
import com.baidu.cloud.util.INotProguard;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ISoHelper extends INotProguard {
    void download(String str, String str2, String str3, ISoCallback iSoCallback);

    void downloadSo(@NonNull String str);

    void downloadSo(@NonNull String str, ISoCallback iSoCallback);

    void downloadSo(@NonNull String str, @NonNull String str2, ISoCallback iSoCallback);

    void downloadSo(@NonNull String str, boolean z, ISoCallback iSoCallback);

    boolean isDownloadComplete(@NonNull String str);

    boolean isDownloadComplete(@NonNull String str, String str2);

    boolean loadSo(@NonNull String str);

    void onDestroy();

    void registerCallback(ISoCallback iSoCallback);

    void removeCallback(ISoCallback iSoCallback);

    void setSoCpuType(String str);
}
