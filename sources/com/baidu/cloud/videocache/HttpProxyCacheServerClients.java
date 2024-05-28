package com.baidu.cloud.videocache;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class HttpProxyCacheServerClients {
    private final oia config;
    private volatile C2563a proxyCache;
    private final CacheListener uiCacheListener;
    private final String url;
    private final AtomicInteger clientsCount = new AtomicInteger(0);
    private final List listeners = new CopyOnWriteArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class UiListenerHandler extends Handler implements CacheListener {
        private final List listeners;
        private final String url;

        public UiListenerHandler(String str, List list) {
            super(Looper.getMainLooper());
            this.url = str;
            this.listeners = list;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            for (CacheListener cacheListener : this.listeners) {
                cacheListener.onCacheAvailable((File) message.obj, this.url, message.arg1);
            }
        }

        @Override // com.baidu.cloud.videocache.CacheListener
        public void onCacheAvailable(File file, String str, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }
    }

    public HttpProxyCacheServerClients(String str, oia oiaVar) {
        this.url = (String) C2571g.m19807a(str);
        this.config = (oia) C2571g.m19807a(oiaVar);
        this.uiCacheListener = new UiListenerHandler(str, this.listeners);
    }

    private C2563a createProxyCache() {
        C2563a m19786a = C2575k.m19786a(this.url, this.config);
        m19786a.mo19844a(this.uiCacheListener);
        return m19786a;
    }

    private synchronized void finishProcessRequest() {
        if (this.clientsCount.decrementAndGet() <= 0) {
            this.proxyCache.mo19796b();
            this.proxyCache = null;
        }
    }

    private synchronized void startProcessRequest() {
        this.proxyCache = this.proxyCache == null ? createProxyCache() : this.proxyCache;
    }

    public int getClientsCount() {
        return this.clientsCount.get();
    }

    public boolean isReadingInProgress(String str) {
        if (this.proxyCache == null) {
            return false;
        }
        return this.proxyCache.mo19802a();
    }

    public void processRequest(nxb nxbVar, Socket socket) {
        startProcessRequest();
        try {
            this.clientsCount.incrementAndGet();
            this.proxyCache.mo19843a(nxbVar, socket);
        } finally {
            finishProcessRequest();
        }
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listeners.add(cacheListener);
    }

    public void shutdown() {
        this.listeners.clear();
        if (this.proxyCache != null) {
            this.proxyCache.mo19844a((CacheListener) null);
            this.proxyCache.mo19796b();
            this.proxyCache = null;
        }
        this.clientsCount.set(0);
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        this.listeners.remove(cacheListener);
    }
}
