package com.baidu.bdhttpdns;

import android.content.Context;
import android.content.IntentFilter;
import com.baidu.bdhttpdns.BDHttpDnsResult;
import com.baidu.bdhttpdns.C2433d;
import com.baidu.bdhttpdns.C2436e;
import com.baidu.bdhttpdns.C2438f;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BDHttpDns {

    /* renamed from: a */
    private static volatile BDHttpDns f4291a;

    /* renamed from: f */
    private BDNetworkStateChangeReceiver f4296f;

    /* renamed from: g */
    private final Context f4297g;

    /* renamed from: b */
    private final C2438f f4292b = C2438f.m20121a();

    /* renamed from: c */
    private final C2433d f4293c = C2433d.m20140a();

    /* renamed from: d */
    private final C2436e f4294d = new C2436e("DNS", true);

    /* renamed from: e */
    private final C2436e f4295e = new C2436e("HTTPDNS", false);

    /* renamed from: h */
    private CachePolicy f4298h = CachePolicy.POLICY_TOLERANT;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CachePolicy {
        POLICY_AGGRESSIVE,
        POLICY_TOLERANT,
        POLICY_STRICT
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface CompletionHandler {
        void completionHandler(BDHttpDnsResult bDHttpDnsResult);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.BDHttpDns$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C2428a implements C2433d.InterfaceC2434a {

        /* renamed from: b */
        private final CompletionHandler f4300b;

        public C2428a(CompletionHandler completionHandler) {
            this.f4300b = completionHandler;
        }

        @Override // com.baidu.bdhttpdns.C2433d.InterfaceC2434a
        /* renamed from: a */
        public void mo20135a(int i, ArrayList<String> arrayList, long j, String str) {
            CompletionHandler completionHandler;
            BDHttpDnsResult bDHttpDnsResult;
            switch (i) {
                case -1:
                    C2444h.m20103a("Async resolve failed, host(%s), dns resolve failed", str);
                    completionHandler = this.f4300b;
                    if (completionHandler != null) {
                        bDHttpDnsResult = new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_NONE, BDHttpDnsResult.ResolveStatus.STATUS_ERR_DNS_RESOLVE, arrayList);
                        break;
                    } else {
                        return;
                    }
                case 0:
                    C2444h.m20103a("Async resolve successful, host(%s) ipList(%s) resolveType(%s)", str, arrayList.toString(), BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS.toString());
                    C2436e.C2437a c2437a = new C2436e.C2437a();
                    c2437a.m20127a(60L);
                    c2437a.m20124b(System.currentTimeMillis() / 1000);
                    c2437a.m20126a(arrayList);
                    BDHttpDns.this.f4294d.m20132a(str, c2437a);
                    completionHandler = this.f4300b;
                    if (completionHandler != null) {
                        bDHttpDnsResult = new BDHttpDnsResult(BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS, BDHttpDnsResult.ResolveStatus.STATUS_OK, arrayList);
                        break;
                    } else {
                        return;
                    }
                default:
                    C2444h.m20103a("Internal error: async dns resolve completion get error ret(%d)", Integer.valueOf(i));
                    return;
            }
            completionHandler.completionHandler(bDHttpDnsResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.BDHttpDns$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2429b implements C2438f.InterfaceC2439a {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C2429b() {
        }

        @Override // com.baidu.bdhttpdns.C2438f.InterfaceC2439a
        /* renamed from: a */
        public void mo20106a(int i, ArrayList<String> arrayList, long j, String str) {
            switch (i) {
                case -1:
                    if (BDHttpDns.this.f4298h == CachePolicy.POLICY_TOLERANT) {
                        BDHttpDns.this.f4295e.m20129b(str);
                        return;
                    }
                    return;
                case 0:
                    C2436e.C2437a c2437a = new C2436e.C2437a();
                    c2437a.m20127a(j);
                    c2437a.m20124b(System.currentTimeMillis() / 1000);
                    c2437a.m20126a(arrayList);
                    BDHttpDns.this.f4295e.m20132a(str, c2437a);
                    return;
                default:
                    C2444h.m20103a("Internal error: async httpdns resolve completion get error ret(%d)", Integer.valueOf(i));
                    return;
            }
        }
    }

    private BDHttpDns(Context context) {
        this.f4297g = context;
        m20147c();
    }

    /* renamed from: c */
    private void m20147c() {
        this.f4296f = new BDNetworkStateChangeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        this.f4297g.registerReceiver(this.f4296f, intentFilter);
    }

    public static BDHttpDns getService(Context context) {
        if (f4291a == null) {
            synchronized (BDHttpDns.class) {
                if (f4291a == null) {
                    f4291a = new BDHttpDns(context);
                }
            }
        }
        return f4291a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public C2436e m20151a() {
        return this.f4295e;
    }

    public void asyncResolve(String str, CompletionHandler completionHandler) {
        C2436e.C2437a m20133a = this.f4295e.m20133a(str);
        if (m20133a == null || m20133a.m20128a()) {
            this.f4292b.m20114a(str, new C2429b());
        }
        if (m20133a != null) {
            BDHttpDnsResult.ResolveType resolveType = m20133a.m20128a() ? BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE : BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_CACHE;
            C2444h.m20103a("Async resolve successful, host(%s) ipList(%s) resolveType(%s)", str, m20133a.m20125b().toString(), resolveType.toString());
            C2445i.m20101a().m20100b().execute(new RunnableC2430a(this, completionHandler, resolveType, m20133a));
            return;
        }
        C2436e.C2437a m20133a2 = this.f4294d.m20133a(str);
        if (m20133a2 == null) {
            this.f4293c.m20137a(str, new C2428a(completionHandler));
            return;
        }
        BDHttpDnsResult.ResolveType resolveType2 = BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS_CACHE;
        C2444h.m20103a("Async resolve successful, host(%s) ipList(%s) resolveType(%s)", str, m20133a2.m20125b().toString(), resolveType2.toString());
        C2445i.m20101a().m20100b().execute(new RunnableC2431b(this, completionHandler, resolveType2, m20133a2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public C2436e m20149b() {
        return this.f4294d;
    }

    public void setAccountID(String str) {
        if (str.length() <= 64) {
            this.f4292b.m20116a(str);
            C2444h.m20103a("Set account id to %s", str);
            return;
        }
        throw new IllegalArgumentException("accountID length(" + str.length() + ") is bigger than 64");
    }

    public void setCachePolicy(CachePolicy cachePolicy) {
        this.f4298h = cachePolicy;
        if (cachePolicy == CachePolicy.POLICY_STRICT) {
            this.f4295e.m20131a(true);
        } else {
            this.f4295e.m20131a(false);
        }
        C2444h.m20103a("Set cache policy to %s", cachePolicy.name());
    }

    public void setHttpsRequestEnable(boolean z) {
        this.f4292b.m20111a(z);
        C2444h.m20103a("Set https enabled to %b", Boolean.valueOf(z));
    }

    public void setLogEnable(boolean z) {
        C2444h.m20102a(z);
        C2444h.m20103a("Set debug log enabled to %b", Boolean.valueOf(z));
    }

    public void setNetworkSwitchPolicy(boolean z, boolean z2) {
        this.f4296f.m20144a(z);
        this.f4296f.m20143b(z2);
        C2444h.m20103a("Set network change policy, clearCache(%b), httpDnsPrefetch(%b)", Boolean.valueOf(z), Boolean.valueOf(z2));
    }

    public void setPreResolveHosts(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            C2444h.m20103a("Set pre resolve hosts error, get empty hosts", new Object[0]);
            return;
        }
        C2444h.m20103a("Set pre resolve hosts: %s", arrayList.toString());
        C2429b c2429b = new C2429b();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            this.f4292b.m20114a(it.next(), c2429b);
        }
    }

    public void setSecret(String str) {
        int length = str.length();
        if (length > 64 || length < 8) {
            throw new IllegalArgumentException("secret length(" + str.length() + ") check failed");
        }
        this.f4292b.m20109b(str);
        String substring = str.substring(0, 3);
        for (int i = 0; i < length - 6; i++) {
            substring = substring + String.valueOf('*');
        }
        C2444h.m20103a("Set secret to %s", substring + str.substring(length - 3));
    }

    public BDHttpDnsResult syncResolve(String str, boolean z) {
        BDHttpDnsResult.ResolveType resolveType = BDHttpDnsResult.ResolveType.RESOLVE_NONE;
        C2436e.C2437a m20133a = this.f4295e.m20133a(str);
        if (m20133a == null || m20133a.m20128a()) {
            this.f4292b.m20114a(str, new C2429b());
        }
        if (m20133a != null) {
            BDHttpDnsResult.ResolveType resolveType2 = m20133a.m20128a() ? BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_EXPIRED_CACHE : BDHttpDnsResult.ResolveType.RESOLVE_FROM_HTTPDNS_CACHE;
            C2444h.m20103a("Sync resolve successful, host(%s) ipList(%s) resolveType(%s)", str, m20133a.m20125b().toString(), resolveType2.toString());
            return new BDHttpDnsResult(resolveType2, BDHttpDnsResult.ResolveStatus.STATUS_OK, m20133a.m20125b());
        } else if (m20133a == null && z) {
            C2444h.m20103a("Sync resolve failed, host(%s), find no httpdns cache entry and cacheOnly is true", str);
            return new BDHttpDnsResult(resolveType, BDHttpDnsResult.ResolveStatus.STATUS_ERR_CACHE_MISS, null);
        } else {
            C2436e.C2437a m20133a2 = this.f4294d.m20133a(str);
            if (m20133a2 != null) {
                BDHttpDnsResult.ResolveType resolveType3 = BDHttpDnsResult.ResolveType.RESOLVE_FROM_DNS_CACHE;
                C2444h.m20103a("Sync resolve successful, host(%s) ipList(%s) resolveType(%s)", str, m20133a2.m20125b().toString(), resolveType3.toString());
                return new BDHttpDnsResult(resolveType3, BDHttpDnsResult.ResolveStatus.STATUS_OK, m20133a2.m20125b());
            }
            BDHttpDnsResult m20138a = this.f4293c.m20138a(str);
            if (m20138a.getResolveStatus() == BDHttpDnsResult.ResolveStatus.STATUS_OK) {
                C2436e.C2437a c2437a = new C2436e.C2437a();
                c2437a.m20127a(60L);
                c2437a.m20124b(System.currentTimeMillis() / 1000);
                c2437a.m20126a(m20138a.getIpv4List());
                this.f4294d.m20132a(str, c2437a);
                C2444h.m20103a("Sync resolve successful, host(%s) ipList(%s) resolveType(%s)", str, c2437a.m20125b().toString(), m20138a.getResolveType().toString());
            } else {
                C2444h.m20103a("Sync resolve failed, host(%s), dns resolve failed", str);
            }
            return m20138a;
        }
    }
}
