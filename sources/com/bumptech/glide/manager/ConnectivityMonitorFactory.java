package com.bumptech.glide.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.manager.ConnectivityMonitor;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ConnectivityMonitorFactory {
    @NonNull
    ConnectivityMonitor build(@NonNull Context context, @NonNull ConnectivityMonitor.ConnectivityListener connectivityListener);
}
