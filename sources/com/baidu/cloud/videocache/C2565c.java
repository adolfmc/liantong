package com.baidu.cloud.videocache;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2565c extends ProxySelector {

    /* renamed from: a */
    private static final List f4864a = Arrays.asList(Proxy.NO_PROXY);

    /* renamed from: b */
    private final ProxySelector f4865b;

    /* renamed from: c */
    private final String f4866c;

    /* renamed from: d */
    private final int f4867d;

    C2565c(ProxySelector proxySelector, String str, int i) {
        this.f4865b = (ProxySelector) C2571g.m19807a(proxySelector);
        this.f4866c = (String) C2571g.m19807a(str);
        this.f4867d = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m19830a(String str, int i) {
        ProxySelector.setDefault(new C2565c(ProxySelector.getDefault(), str, i));
    }

    @Override // java.net.ProxySelector
    public void connectFailed(URI uri, SocketAddress socketAddress, IOException iOException) {
        this.f4865b.connectFailed(uri, socketAddress, iOException);
    }

    @Override // java.net.ProxySelector
    public List select(URI uri) {
        return this.f4866c.equals(uri.getHost()) && this.f4867d == uri.getPort() ? f4864a : this.f4865b.select(uri);
    }
}
