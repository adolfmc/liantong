package com.xiaomi.push.service;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.service.XMPushService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.xiaomi.push.service.am */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11545am {

    /* renamed from: a */
    private static C11545am f23517a;

    /* renamed from: a */
    private ConcurrentHashMap<String, HashMap<String, C11547b>> f23519a = new ConcurrentHashMap<>();

    /* renamed from: a */
    private List<InterfaceC11546a> f23518a = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.am$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11546a {
        /* renamed from: a */
        void mo2678a();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.am$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum EnumC11554c {
        unbind,
        binding,
        binded
    }

    /* renamed from: a */
    public static synchronized C11545am m2692a() {
        C11545am c11545am;
        synchronized (C11545am.class) {
            if (f23517a == null) {
                f23517a = new C11545am();
            }
            c11545am = f23517a;
        }
        return c11545am;
    }

    private C11545am() {
    }

    /* renamed from: a */
    public synchronized void m2686a(C11547b c11547b) {
        HashMap<String, C11547b> hashMap = this.f23519a.get(c11547b.f23539g);
        if (hashMap == null) {
            hashMap = new HashMap<>();
            this.f23519a.put(c11547b.f23539g, hashMap);
        }
        hashMap.put(m2685a(c11547b.f23533b), c11547b);
        AbstractC11049b.m5282a("add active client. " + c11547b.f23529a);
        for (InterfaceC11546a interfaceC11546a : this.f23518a) {
            interfaceC11546a.mo2678a();
        }
    }

    /* renamed from: a */
    public synchronized void m2680a(String str, String str2) {
        HashMap<String, C11547b> hashMap = this.f23519a.get(str);
        if (hashMap != null) {
            C11547b c11547b = hashMap.get(m2685a(str2));
            if (c11547b != null) {
                c11547b.m2676a();
            }
            hashMap.remove(m2685a(str2));
            if (hashMap.isEmpty()) {
                this.f23519a.remove(str);
            }
        }
        for (InterfaceC11546a interfaceC11546a : this.f23518a) {
            interfaceC11546a.mo2678a();
        }
    }

    /* renamed from: a */
    public synchronized void m2682a(String str) {
        HashMap<String, C11547b> hashMap = this.f23519a.get(str);
        if (hashMap != null) {
            for (C11547b c11547b : hashMap.values()) {
                c11547b.m2676a();
            }
            hashMap.clear();
            this.f23519a.remove(str);
        }
        for (InterfaceC11546a interfaceC11546a : this.f23518a) {
            interfaceC11546a.mo2678a();
        }
    }

    /* renamed from: a */
    public synchronized List<String> m2683a(String str) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (HashMap<String, C11547b> hashMap : this.f23519a.values()) {
            for (C11547b c11547b : hashMap.values()) {
                if (str.equals(c11547b.f23529a)) {
                    arrayList.add(c11547b.f23539g);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public synchronized ArrayList<C11547b> m2691a() {
        ArrayList<C11547b> arrayList;
        arrayList = new ArrayList<>();
        for (HashMap<String, C11547b> hashMap : this.f23519a.values()) {
            arrayList.addAll(hashMap.values());
        }
        return arrayList;
    }

    /* renamed from: a */
    public synchronized Collection<C11547b> m2684a(String str) {
        if (!this.f23519a.containsKey(str)) {
            return new ArrayList();
        }
        return ((HashMap) this.f23519a.get(str).clone()).values();
    }

    /* renamed from: a */
    public synchronized C11547b m2681a(String str, String str2) {
        HashMap<String, C11547b> hashMap = this.f23519a.get(str);
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(m2685a(str2));
    }

    /* renamed from: a */
    public synchronized void m2688a(Context context, int i) {
        for (HashMap<String, C11547b> hashMap : this.f23519a.values()) {
            for (C11547b c11547b : hashMap.values()) {
                c11547b.m2666a(EnumC11554c.unbind, 2, i, (String) null, (String) null);
            }
        }
    }

    /* renamed from: a */
    public synchronized int m2693a() {
        return this.f23519a.size();
    }

    /* renamed from: a */
    public synchronized void m2690a() {
        Iterator<C11547b> it = m2691a().iterator();
        while (it.hasNext()) {
            it.next().m2676a();
        }
        this.f23519a.clear();
    }

    /* renamed from: a */
    public synchronized void m2689a(Context context) {
        for (HashMap<String, C11547b> hashMap : this.f23519a.values()) {
            for (C11547b c11547b : hashMap.values()) {
                c11547b.m2666a(EnumC11554c.unbind, 1, 3, (String) null, (String) null);
            }
        }
    }

    /* renamed from: a */
    private String m2685a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("@");
        return indexOf > 0 ? str.substring(0, indexOf) : str;
    }

    /* renamed from: com.xiaomi.push.service.am$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11547b {

        /* renamed from: a */
        public Context f23521a;

        /* renamed from: a */
        Messenger f23523a;

        /* renamed from: a */
        private XMPushService f23525a;

        /* renamed from: a */
        public C11598h f23528a;

        /* renamed from: a */
        public String f23529a;

        /* renamed from: a */
        public boolean f23531a;

        /* renamed from: b */
        public String f23533b;

        /* renamed from: c */
        public String f23535c;

        /* renamed from: d */
        public String f23536d;

        /* renamed from: e */
        public String f23537e;

        /* renamed from: f */
        public String f23538f;

        /* renamed from: g */
        public String f23539g;

        /* renamed from: h */
        public String f23540h;

        /* renamed from: i */
        public String f23541i;

        /* renamed from: a */
        EnumC11554c f23527a = EnumC11554c.unbind;

        /* renamed from: a */
        private int f23520a = 0;

        /* renamed from: a */
        private final CopyOnWriteArrayList<InterfaceC11549a> f23530a = new CopyOnWriteArrayList<>();

        /* renamed from: b */
        EnumC11554c f23532b = null;

        /* renamed from: b */
        private boolean f23534b = false;

        /* renamed from: a */
        private XMPushService.C11502c f23524a = new XMPushService.C11502c(this);

        /* renamed from: a */
        IBinder.DeathRecipient f23522a = null;

        /* renamed from: a */
        final C11550b f23526a = new C11550b();

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.xiaomi.push.service.am$b$a */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public interface InterfaceC11549a {
            /* renamed from: a */
            void mo2344a(EnumC11554c enumC11554c, EnumC11554c enumC11554c2, int i);
        }

        /* renamed from: a */
        public String m2675a(int i) {
            switch (i) {
                case 1:
                    return "OPEN";
                case 2:
                    return "CLOSE";
                case 3:
                    return "KICK";
                default:
                    return "unknown";
            }
        }

        public C11547b() {
        }

        public C11547b(XMPushService xMPushService) {
            this.f23525a = xMPushService;
            m2671a(new InterfaceC11549a() { // from class: com.xiaomi.push.service.am.b.1
                @Override // com.xiaomi.push.service.C11545am.C11547b.InterfaceC11549a
                /* renamed from: a */
                public void mo2344a(EnumC11554c enumC11554c, EnumC11554c enumC11554c2, int i) {
                    if (enumC11554c2 == EnumC11554c.binding) {
                        C11547b.this.f23525a.m2884a(C11547b.this.f23524a, 60000L);
                    } else {
                        C11547b.this.f23525a.m2856b(C11547b.this.f23524a);
                    }
                }
            });
        }

        /* renamed from: com.xiaomi.push.service.am$b$c */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        class C11551c implements IBinder.DeathRecipient {

            /* renamed from: a */
            final Messenger f23548a;

            /* renamed from: a */
            final C11547b f23549a;

            C11551c(C11547b c11547b, Messenger messenger) {
                this.f23549a = c11547b;
                this.f23548a = messenger;
            }

            @Override // android.os.IBinder.DeathRecipient
            public void binderDied() {
                AbstractC11049b.m5274b("peer died, chid = " + this.f23549a.f23539g);
                C11547b.this.f23525a.m2884a(new XMPushService.AbstractC11509j(0) { // from class: com.xiaomi.push.service.am.b.c.1
                    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                    /* renamed from: a */
                    public String mo2375a() {
                        return "clear peer job";
                    }

                    @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                    /* renamed from: a */
                    public void mo2374a() {
                        if (C11551c.this.f23548a == C11551c.this.f23549a.f23523a) {
                            AbstractC11049b.m5274b("clean peer, chid = " + C11551c.this.f23549a.f23539g);
                            C11551c.this.f23549a.f23523a = null;
                        }
                    }
                }, 0L);
                if ("9".equals(this.f23549a.f23539g) && "com.xiaomi.xmsf".equals(C11547b.this.f23525a.getPackageName())) {
                    C11547b.this.f23525a.m2884a(new XMPushService.AbstractC11509j(0) { // from class: com.xiaomi.push.service.am.b.c.2
                        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                        /* renamed from: a */
                        public String mo2375a() {
                            return "check peer job";
                        }

                        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                        /* renamed from: a */
                        public void mo2374a() {
                            if (C11545am.m2692a().m2681a(C11551c.this.f23549a.f23539g, C11551c.this.f23549a.f23533b).f23523a == null) {
                                C11547b.this.f23525a.m2867a(C11551c.this.f23549a.f23539g, C11551c.this.f23549a.f23533b, 2, null, null);
                            }
                        }
                    }, 60000L);
                }
            }
        }

        /* renamed from: a */
        void m2676a() {
            try {
                Messenger messenger = this.f23523a;
                if (messenger != null && this.f23522a != null) {
                    messenger.getBinder().unlinkToDeath(this.f23522a, 0);
                }
            } catch (Exception unused) {
            }
            this.f23532b = null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public void m2672a(Messenger messenger) {
            m2676a();
            try {
                if (messenger != null) {
                    this.f23523a = messenger;
                    this.f23534b = true;
                    this.f23522a = new C11551c(this, messenger);
                    messenger.getBinder().linkToDeath(this.f23522a, 0);
                } else {
                    AbstractC11049b.m5274b("peer linked with old sdk chid = " + this.f23539g);
                }
            } catch (Exception e) {
                AbstractC11049b.m5274b("peer linkToDeath err: " + e.getMessage());
                this.f23523a = null;
                this.f23534b = false;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.xiaomi.push.service.am$b$b */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C11550b extends XMPushService.AbstractC11509j {

            /* renamed from: a */
            String f23544a;

            /* renamed from: b */
            int f23545b;

            /* renamed from: b */
            String f23546b;

            /* renamed from: c */
            int f23547c;

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "notify job";
            }

            public C11550b() {
                super(0);
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                if (C11547b.this.m2674a(this.f23545b, this.f23547c, this.f23546b)) {
                    C11547b.this.m2673a(this.f23545b, this.f23547c, this.f23544a, this.f23546b);
                    return;
                }
                AbstractC11049b.m5274b(" ignore notify client :" + C11547b.this.f23539g);
            }

            /* renamed from: a */
            public XMPushService.AbstractC11509j m2662a(int i, int i2, String str, String str2) {
                this.f23545b = i;
                this.f23547c = i2;
                this.f23546b = str2;
                this.f23544a = str;
                return this;
            }
        }

        /* renamed from: a */
        public void m2666a(EnumC11554c enumC11554c, int i, int i2, String str, String str2) {
            boolean z;
            Iterator<InterfaceC11549a> it = this.f23530a.iterator();
            while (it.hasNext()) {
                InterfaceC11549a next = it.next();
                if (next != null) {
                    next.mo2344a(this.f23527a, enumC11554c, i2);
                }
            }
            EnumC11554c enumC11554c2 = this.f23527a;
            int i3 = 0;
            if (enumC11554c2 != enumC11554c) {
                AbstractC11049b.m5282a(String.format("update the client %7$s status. %1$s->%2$s %3$s %4$s %5$s %6$s", enumC11554c2, enumC11554c, m2675a(i), AbstractC11555an.m2661a(i2), str, str2, this.f23539g));
                this.f23527a = enumC11554c;
            }
            if (this.f23528a == null) {
                AbstractC11049b.m5268d("status changed while the client dispatcher is missing");
            } else if (enumC11554c == EnumC11554c.binding) {
            } else {
                if (this.f23532b != null && (z = this.f23534b)) {
                    i3 = (this.f23523a == null || !z) ? 10100 : 1000;
                }
                this.f23525a.m2856b(this.f23526a);
                if (m2664b(i, i2, str2)) {
                    m2673a(i, i2, str, str2);
                } else {
                    this.f23525a.m2884a(this.f23526a.m2662a(i, i2, str, str2), i3);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2673a(int i, int i2, String str, String str2) {
            EnumC11554c enumC11554c = this.f23527a;
            this.f23532b = enumC11554c;
            if (i == 2) {
                this.f23528a.m2536a(this.f23521a, this, i2);
            } else if (i == 3) {
                this.f23528a.m2535a(this.f23521a, this, str2, str);
            } else if (i == 1) {
                boolean z = enumC11554c == EnumC11554c.binded;
                if (!z && "wait".equals(str2)) {
                    this.f23520a++;
                } else if (z) {
                    this.f23520a = 0;
                    if (this.f23523a != null) {
                        try {
                            this.f23523a.send(Message.obtain(null, 16, this.f23525a.f23398a));
                        } catch (RemoteException unused) {
                        }
                    }
                }
                this.f23528a.m2534a(this.f23525a, this, z, i2, str);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public boolean m2674a(int i, int i2, String str) {
            boolean z;
            EnumC11554c enumC11554c = this.f23532b;
            if (enumC11554c == null || !(z = this.f23534b)) {
                return true;
            }
            if (enumC11554c == this.f23527a) {
                AbstractC11049b.m5274b(" status recovered, don't notify client:" + this.f23539g);
                return false;
            } else if (this.f23523a != null && z) {
                AbstractC11049b.m5274b("Peer alive notify status to client:" + this.f23539g);
                return true;
            } else {
                AbstractC11049b.m5274b("peer died, ignore notify " + this.f23539g);
                return false;
            }
        }

        /* renamed from: b */
        private boolean m2664b(int i, int i2, String str) {
            switch (i) {
                case 1:
                    return (this.f23527a == EnumC11554c.binded || !this.f23525a.m2851c() || i2 == 21 || (i2 == 7 && "wait".equals(str))) ? false : true;
                case 2:
                    return this.f23525a.m2851c();
                case 3:
                    return !"wait".equals(str);
                default:
                    return false;
            }
        }

        /* renamed from: a */
        public void m2671a(InterfaceC11549a interfaceC11549a) {
            this.f23530a.add(interfaceC11549a);
        }

        /* renamed from: b */
        public void m2663b(InterfaceC11549a interfaceC11549a) {
            this.f23530a.remove(interfaceC11549a);
        }

        /* renamed from: a */
        public long m2677a() {
            return (((long) ((Math.random() * 20.0d) - 10.0d)) + ((this.f23520a + 1) * 15)) * 1000;
        }

        /* renamed from: a */
        public static String m2665a(String str) {
            int lastIndexOf;
            return (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf("/")) == -1) ? "" : str.substring(lastIndexOf + 1);
        }
    }

    /* renamed from: a */
    public synchronized void m2687a(InterfaceC11546a interfaceC11546a) {
        this.f23518a.add(interfaceC11546a);
    }

    /* renamed from: b */
    public synchronized void m2679b() {
        this.f23518a.clear();
    }
}
