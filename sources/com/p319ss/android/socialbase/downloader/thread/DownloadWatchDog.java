package com.p319ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

/* renamed from: com.ss.android.socialbase.downloader.thread.DownloadWatchDog */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class DownloadWatchDog implements Handler.Callback {
    private static final int MSG_SCHEDULE_WATCH = 0;
    private volatile Handler handler = new Handler(Holder.LOOPER, this);

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.thread.DownloadWatchDog$IWatcher */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface IWatcher {
        long onScheduleWatch();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.thread.DownloadWatchDog$Holder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class Holder {
        private static final Looper LOOPER;

        private Holder() {
        }

        static {
            HandlerThread handlerThread = new HandlerThread("DownloadWatchDog");
            handlerThread.start();
            LOOPER = handlerThread.getLooper();
        }
    }

    public static Looper getThreadLooper() {
        return Holder.LOOPER;
    }

    public void addWatcher(IWatcher iWatcher, long j) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = iWatcher;
        handler.sendMessageDelayed(obtain, j);
    }

    public void removeWatcher(IWatcher iWatcher) {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.removeMessages(0, iWatcher);
    }

    public void release() {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        this.handler = null;
        handler.removeCallbacksAndMessages(null);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(@NonNull Message message) {
        if (message.what != 0) {
            return true;
        }
        try {
            IWatcher iWatcher = (IWatcher) message.obj;
            long onScheduleWatch = iWatcher.onScheduleWatch();
            if (onScheduleWatch > 0) {
                addWatcher(iWatcher, onScheduleWatch);
                return true;
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return true;
        }
    }
}
