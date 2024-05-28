package com.p319ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: com.ss.android.socialbase.downloader.thread.WeakDownloadHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WeakDownloadHandler extends Handler {
    private final WeakReference<IHandler> mRef;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.thread.WeakDownloadHandler$IHandler */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface IHandler {
        void handleMsg(Message message);
    }

    public WeakDownloadHandler(IHandler iHandler) {
        this.mRef = new WeakReference<>(iHandler);
    }

    public WeakDownloadHandler(Looper looper, IHandler iHandler) {
        super(looper);
        this.mRef = new WeakReference<>(iHandler);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        IHandler iHandler = this.mRef.get();
        if (iHandler == null || message == null) {
            return;
        }
        iHandler.handleMsg(message);
    }
}
