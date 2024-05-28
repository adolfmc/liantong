package com.bytedance.pangle.servermanager;

import com.bytedance.pangle.InterfaceC3780c;
import com.bytedance.pangle.InterfaceC3786d;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.servermanager.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3924b {

    /* renamed from: a */
    private static final Object f9331a = new Object();

    /* renamed from: b */
    private static final Object f9332b = new Object();

    /* renamed from: c */
    private static final Object f9333c = new Object();

    /* renamed from: d */
    private static final Map<String, Boolean> f9334d = new ConcurrentHashMap();

    /* renamed from: e */
    private static final Map<String, InterfaceC3786d> f9335e = new ConcurrentHashMap();

    /* renamed from: f */
    private static InterfaceC3780c f9336f;

    /* renamed from: a */
    public static InterfaceC3786d m16681a(String str) {
        Boolean bool = f9334d.get(str);
        if (bool == null || !bool.booleanValue()) {
            f9335e.remove(str);
        }
        if (f9335e.get(str) == null) {
            synchronized (f9332b) {
                InterfaceC3786d interfaceC3786d = (InterfaceC3786d) m16680a("service", str);
                if (interfaceC3786d == null) {
                    ZeusLogger.errReport("Zeus/server_pangle", "getServiceManager failed!!!");
                    return null;
                }
                f9335e.put(str, interfaceC3786d);
            }
        }
        return f9335e.get(str);
    }

    /* renamed from: a */
    public static InterfaceC3780c m16682a() {
        Boolean bool = f9334d.get("main");
        if (bool == null || !bool.booleanValue()) {
            f9336f = null;
        }
        if (f9336f == null) {
            synchronized (f9333c) {
                InterfaceC3780c interfaceC3780c = (InterfaceC3780c) m16680a("package", "main");
                if (interfaceC3780c == null) {
                    ZeusLogger.errReport("Zeus/server_pangle", "getPackageManager failed!!!");
                    return null;
                }
                f9336f = interfaceC3780c;
            }
        }
        return f9336f;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0080 A[Catch: RemoteException -> 0x0098, TryCatch #0 {RemoteException -> 0x0098, blocks: (B:15:0x0056, B:27:0x008a, B:29:0x008e, B:31:0x0093, B:20:0x0076, B:23:0x0080), top: B:40:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e A[Catch: RemoteException -> 0x0098, TryCatch #0 {RemoteException -> 0x0098, blocks: (B:15:0x0056, B:27:0x008a, B:29:0x008e, B:31:0x0093, B:20:0x0076, B:23:0x0080), top: B:40:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0093 A[Catch: RemoteException -> 0x0098, TRY_LEAVE, TryCatch #0 {RemoteException -> 0x0098, blocks: (B:15:0x0056, B:27:0x008a, B:29:0x008e, B:31:0x0093, B:20:0x0076, B:23:0x0080), top: B:40:0x0056 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.os.IInterface m16680a(java.lang.String r5, final java.lang.String r6) {
        /*
            boolean r0 = com.bytedance.pangle.Zeus.hasInit()
            if (r0 == 0) goto Lb2
            java.util.HashMap r0 = com.bytedance.pangle.Zeus.getServerManagerHashMap()
            java.lang.Object r0 = r0.get(r6)
            android.content.pm.ProviderInfo r0 = (android.content.pm.ProviderInfo) r0
            if (r0 == 0) goto La1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "content://"
            r1.<init>(r2)
            java.lang.String r0 = r0.authority
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            android.app.Application r1 = com.bytedance.pangle.Zeus.getAppApplication()
            android.content.ContentResolver r1 = r1.getContentResolver()
            java.lang.String r2 = "query_binder"
            r3 = 0
            android.os.Bundle r0 = r1.call(r0, r2, r5, r3)
            if (r0 == 0) goto L4d
            java.lang.Class<com.bytedance.pangle.servermanager.AbsServerManager> r1 = com.bytedance.pangle.servermanager.AbsServerManager.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
            r0.setClassLoader(r1)
            java.lang.String r1 = "binder"
            android.os.Parcelable r0 = r0.getParcelable(r1)
            com.bytedance.pangle.servermanager.a r0 = (com.bytedance.pangle.servermanager.C3922a) r0
            if (r0 == 0) goto L4d
            android.os.IBinder r0 = r0.f9330a
            goto L4e
        L4d:
            r0 = r3
        L4e:
            if (r0 == 0) goto La0
            boolean r1 = r0.isBinderAlive()
            if (r1 == 0) goto La0
            com.bytedance.pangle.servermanager.b$1 r1 = new com.bytedance.pangle.servermanager.b$1     // Catch: android.os.RemoteException -> L98
            r1.<init>()     // Catch: android.os.RemoteException -> L98
            r2 = 0
            r0.linkToDeath(r1, r2)     // Catch: android.os.RemoteException -> L98
            java.util.Map<java.lang.String, java.lang.Boolean> r1 = com.bytedance.pangle.servermanager.C3924b.f9334d     // Catch: android.os.RemoteException -> L98
            java.lang.Boolean r4 = java.lang.Boolean.TRUE     // Catch: android.os.RemoteException -> L98
            r1.put(r6, r4)     // Catch: android.os.RemoteException -> L98
            r6 = -1
            int r1 = r5.hashCode()     // Catch: android.os.RemoteException -> L98
            r4 = -807062458(0xffffffffcfe53446, float:-7.6908165E9)
            if (r1 == r4) goto L80
            r2 = 1984153269(0x7643c6b5, float:9.927033E32)
            if (r1 == r2) goto L76
            goto L89
        L76:
            java.lang.String r1 = "service"
            boolean r5 = r5.equals(r1)     // Catch: android.os.RemoteException -> L98
            if (r5 == 0) goto L89
            r2 = 1
            goto L8a
        L80:
            java.lang.String r1 = "package"
            boolean r5 = r5.equals(r1)     // Catch: android.os.RemoteException -> L98
            if (r5 == 0) goto L89
            goto L8a
        L89:
            r2 = r6
        L8a:
            switch(r2) {
                case 0: goto L93;
                case 1: goto L8e;
                default: goto L8d;
            }     // Catch: android.os.RemoteException -> L98
        L8d:
            goto La0
        L8e:
            com.bytedance.pangle.d r5 = com.bytedance.pangle.InterfaceC3786d.AbstractBinderC3787a.m16957a(r0)     // Catch: android.os.RemoteException -> L98
            return r5
        L93:
            com.bytedance.pangle.c r5 = com.bytedance.pangle.InterfaceC3780c.AbstractBinderC3781a.m16962a(r0)     // Catch: android.os.RemoteException -> L98
            return r5
        L98:
            r5 = move-exception
            java.lang.String r6 = "Zeus/server_pangle"
            java.lang.String r0 = "generateServerManager failed."
            com.bytedance.pangle.log.ZeusLogger.errReport(r6, r0, r5)
        La0:
            return r3
        La1:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r0 = "宿主中没有找对对应进程的serverManager "
            java.lang.String r6 = r0.concat(r6)
            r5.<init>(r6)
            throw r5
        Lb2:
            java.lang.RuntimeException r5 = new java.lang.RuntimeException
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.String r0 = "generateServerManager 请先初始化Zeus, processName:"
            java.lang.String r6 = r0.concat(r6)
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.servermanager.C3924b.m16680a(java.lang.String, java.lang.String):android.os.IInterface");
    }
}
