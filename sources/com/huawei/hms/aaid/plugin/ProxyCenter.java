package com.huawei.hms.aaid.plugin;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ProxyCenter {
    private PushProxy proxy;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.aaid.plugin.ProxyCenter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4836a {

        /* renamed from: a */
        private static ProxyCenter f10887a = new ProxyCenter();
    }

    private static ProxyCenter getInstance() {
        return C4836a.f10887a;
    }

    public static PushProxy getProxy() {
        return getInstance().proxy;
    }

    public static void register(PushProxy pushProxy) {
        getInstance().proxy = pushProxy;
    }
}
