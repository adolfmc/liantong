package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.segment.IInput */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IInput {
    @NonNull
    Buffer read() throws StreamClosedException, InterruptedException;
}
