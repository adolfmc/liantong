package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.service.C11577az;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MiTinyDataClient {
    public static final String PENDING_REASON_APPID = "com.xiaomi.xmpushsdk.tinydataPending.appId";
    public static final String PENDING_REASON_CHANNEL = "com.xiaomi.xmpushsdk.tinydataPending.channel";
    public static final String PENDING_REASON_INIT = "com.xiaomi.xmpushsdk.tinydataPending.init";

    public static void init(Context context, String str) {
        if (context == null) {
            AbstractC11049b.m5282a("context is null, MiTinyDataClient.init(Context, String) failed.");
            return;
        }
        C11077a.m5198a().m5196a(context);
        if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("channel is null or empty, MiTinyDataClient.init(Context, String) failed.");
        } else {
            C11077a.m5198a().m5191a(str);
        }
    }

    public static boolean upload(String str, String str2, long j, String str3) {
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d(str);
        c11408gj.m3653c(str2);
        c11408gj.m3668a(j);
        c11408gj.m3657b(str3);
        return C11077a.m5198a().m5192a(c11408gj);
    }

    public static boolean upload(Context context, String str, String str2, long j, String str3) {
        C11408gj c11408gj = new C11408gj();
        c11408gj.m3649d(str);
        c11408gj.m3653c(str2);
        c11408gj.m3668a(j);
        c11408gj.m3657b(str3);
        c11408gj.m3662a(true);
        c11408gj.m3665a("push_sdk_channel");
        return upload(context, c11408gj);
    }

    public static boolean upload(Context context, C11408gj c11408gj) {
        AbstractC11049b.m5270c("MiTinyDataClient.upload " + c11408gj.m3651d());
        if (!C11077a.m5198a().m5197a()) {
            C11077a.m5198a().m5196a(context);
        }
        return C11077a.m5198a().m5192a(c11408gj);
    }

    /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11077a {

        /* renamed from: a */
        private static volatile C11077a f21327a;

        /* renamed from: a */
        private Context f21328a;

        /* renamed from: a */
        private Boolean f21330a;

        /* renamed from: a */
        private String f21331a;

        /* renamed from: a */
        private C11078a f21329a = new C11078a();

        /* renamed from: a */
        private final ArrayList<C11408gj> f21332a = new ArrayList<>();

        /* renamed from: a */
        public static C11077a m5198a() {
            if (f21327a == null) {
                synchronized (C11077a.class) {
                    if (f21327a == null) {
                        f21327a = new C11077a();
                    }
                }
            }
            return f21327a;
        }

        /* renamed from: a */
        public void m5196a(Context context) {
            if (context == null) {
                AbstractC11049b.m5282a("context is null, MiTinyDataClientImp.init() failed.");
                return;
            }
            this.f21328a = context;
            this.f21330a = Boolean.valueOf(m5195a(context));
            m5188b("com.xiaomi.xmpushsdk.tinydataPending.init");
        }

        /* renamed from: a */
        public synchronized void m5191a(String str) {
            if (TextUtils.isEmpty(str)) {
                AbstractC11049b.m5282a("channel is null, MiTinyDataClientImp.setChannel(String) failed.");
                return;
            }
            this.f21331a = str;
            m5188b("com.xiaomi.xmpushsdk.tinydataPending.channel");
        }

        /* renamed from: b */
        public void m5188b(String str) {
            AbstractC11049b.m5270c("MiTinyDataClient.processPendingList(" + str + ")");
            ArrayList arrayList = new ArrayList();
            synchronized (this.f21332a) {
                arrayList.addAll(this.f21332a);
                this.f21332a.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m5192a((C11408gj) it.next());
            }
        }

        /* renamed from: a */
        private boolean m5195a(Context context) {
            if (C11118u.m5003a(context).m5008a()) {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
                    if (packageInfo == null) {
                        return false;
                    }
                    return packageInfo.versionCode >= 108;
                } catch (Exception unused) {
                    return false;
                }
            }
            return true;
        }

        /* renamed from: a */
        public boolean m5197a() {
            return this.f21328a != null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:47:0x00a5, code lost:
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5270c("MiTinyDataClient Pending " + r6.m3660b() + " reason is com.xiaomi.xmpushsdk.tinydataPending.channel");
         */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public synchronized boolean m5192a(com.xiaomi.push.C11408gj r6) {
            /*
                Method dump skipped, instructions count: 281
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.MiTinyDataClient.C11077a.m5192a(com.xiaomi.push.gj):boolean");
        }

        /* renamed from: b */
        private boolean m5190b(Context context) {
            return C11087b.m5151a(context).m5156a() == null && !m5195a(this.f21328a);
        }

        /* renamed from: a */
        private void m5193a(C11408gj c11408gj) {
            synchronized (this.f21332a) {
                if (!this.f21332a.contains(c11408gj)) {
                    this.f21332a.add(c11408gj);
                    if (this.f21332a.size() > 100) {
                        this.f21332a.remove(0);
                    }
                }
            }
        }

        /* renamed from: b */
        private boolean m5189b(C11408gj c11408gj) {
            if (C11577az.m2596a(c11408gj, false)) {
                return false;
            }
            if (this.f21330a.booleanValue()) {
                AbstractC11049b.m5270c("MiTinyDataClient Send item by PushServiceClient.sendTinyData(ClientUploadDataItem)." + c11408gj.m3651d());
                C11118u.m5003a(this.f21328a).m4991a(c11408gj);
                return true;
            }
            this.f21329a.m5183a(c11408gj);
            return true;
        }

        /* renamed from: com.xiaomi.mipush.sdk.MiTinyDataClient$a$a */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public class C11078a {

            /* renamed from: a */
            private ScheduledFuture<?> f21336a;

            /* renamed from: a */
            private ScheduledThreadPoolExecutor f21337a = new ScheduledThreadPoolExecutor(1);

            /* renamed from: a */
            public final ArrayList<C11408gj> f21335a = new ArrayList<>();

            /* renamed from: a */
            private final Runnable f21334a = new Runnable() { // from class: com.xiaomi.mipush.sdk.MiTinyDataClient.a.a.2
                @Override // java.lang.Runnable
                public void run() {
                    if (C11078a.this.f21335a.size() != 0) {
                        C11078a.this.m5182b();
                    } else if (C11078a.this.f21336a != null) {
                        C11078a.this.f21336a.cancel(false);
                        C11078a.this.f21336a = null;
                    }
                }
            };

            public C11078a() {
            }

            /* renamed from: a */
            public void m5183a(final C11408gj c11408gj) {
                this.f21337a.execute(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiTinyDataClient.a.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        C11078a.this.f21335a.add(c11408gj);
                        C11078a.this.m5187a();
                    }
                });
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: a */
            public void m5187a() {
                if (this.f21336a == null) {
                    this.f21336a = this.f21337a.scheduleAtFixedRate(this.f21334a, 1000L, 1000L, TimeUnit.MILLISECONDS);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* renamed from: b */
            public void m5182b() {
                C11408gj remove = this.f21335a.remove(0);
                for (C11430he c11430he : C11577az.m2593a(Arrays.asList(remove), C11077a.this.f21328a.getPackageName(), C11087b.m5151a(C11077a.this.f21328a).m5156a(), 30720)) {
                    AbstractC11049b.m5270c("MiTinyDataClient Send item by PushServiceClient.sendMessage(XmActionNotification)." + remove.m3651d());
                    C11118u.m5003a(C11077a.this.f21328a).m4986a((C11118u) c11430he, EnumC11404gf.Notification, true, (C11417gs) null);
                }
            }
        }
    }
}
