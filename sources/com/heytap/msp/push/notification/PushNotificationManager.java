package com.heytap.msp.push.notification;

import com.heytap.mcssdk.p206d.C4731b;
import com.heytap.msp.push.notification.PushNotification;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushNotificationManager {
    private PushNotification.Builder builder;
    private Executor executor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final PushNotificationManager INSTANCE = new PushNotificationManager();

        private SingletonHolder() {
        }
    }

    public static PushNotificationManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void enqueue(final ISortListener iSortListener) {
        if (this.executor == null) {
            this.executor = new ThreadPoolExecutor(1, 0, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(16), new ThreadPoolExecutor.DiscardPolicy());
        }
        this.executor.execute(new Runnable() { // from class: com.heytap.msp.push.notification.PushNotificationManager.1
            @Override // java.lang.Runnable
            public void run() {
                C4731b.m15545a().m15530a(PushNotificationManager.this.builder, iSortListener);
            }
        });
    }

    public void execute(ISortListener iSortListener) {
        C4731b.m15545a().m15530a(this.builder, iSortListener);
    }

    public PushNotificationManager with(PushNotification.Builder builder) {
        this.builder = builder;
        return this;
    }
}
