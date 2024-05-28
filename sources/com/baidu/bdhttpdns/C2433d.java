package com.baidu.bdhttpdns;

import com.baidu.bdhttpdns.BDHttpDnsResult;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.bdhttpdns.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2433d {

    /* renamed from: a */
    private static volatile C2433d f4317a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.d$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface InterfaceC2434a {
        /* renamed from: a */
        void mo20135a(int i, ArrayList<String> arrayList, long j, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.d$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class RunnableC2435b implements Runnable {

        /* renamed from: b */
        private String f4319b;

        /* renamed from: c */
        private InterfaceC2434a f4320c;

        public RunnableC2435b(String str, InterfaceC2434a interfaceC2434a) {
            this.f4319b = str;
            this.f4320c = interfaceC2434a;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<String> m20136b = C2433d.this.m20136b(this.f4319b);
            this.f4320c.mo20135a((m20136b == null || m20136b.isEmpty()) ? -1 : 0, m20136b, 60L, this.f4319b);
        }
    }

    private C2433d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2433d m20140a() {
        if (f4317a == null) {
            synchronized (C2433d.class) {
                if (f4317a == null) {
                    f4317a = new C2433d();
                }
            }
        }
        return f4317a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public ArrayList<String> m20136b(String str) {
        String str2;
        Object[] objArr;
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName == null || allByName.length == 0) {
                str2 = "Dns resolve failed, host(%s), get empty resolve result";
                objArr = new Object[]{str};
            } else {
                ArrayList<String> arrayList = new ArrayList<>();
                for (InetAddress inetAddress : allByName) {
                    if (inetAddress instanceof Inet4Address) {
                        String hostAddress = inetAddress.getHostAddress();
                        if (C2432c.m20142a(hostAddress)) {
                            arrayList.add(hostAddress);
                        }
                    }
                }
                if (!arrayList.isEmpty()) {
                    C2444h.m20103a("Dns resolve successful, host(%s), ipList(%s)", str, arrayList.toString());
                    return arrayList;
                }
                str2 = "Dns resolve failed, host(%s), get no valid resolve result";
                objArr = new Object[]{str};
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            str2 = "Dns resolve failed, host(%s), caught UnknownHostException";
            objArr = new Object[]{str};
        }
        C2444h.m20103a(str2, objArr);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public BDHttpDnsResult m20138a(String str) {
        ArrayList<String> m20136b = m20136b(str);
        return (m20136b == null || m20136b.isEmpty()) ? new BDHttpDnsResult(BDHttpDnsResult.ResolveStatus.STATUS_ERR_DNS_RESOLVE) : new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS, BDHttpDnsResult.ResolveStatus.STATUS_OK, m20136b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20137a(String str, InterfaceC2434a interfaceC2434a) {
        if (str == null || str.isEmpty()) {
            return;
        }
        C2445i.m20101a().m20100b().execute(new RunnableC2435b(str, interfaceC2434a));
    }
}
