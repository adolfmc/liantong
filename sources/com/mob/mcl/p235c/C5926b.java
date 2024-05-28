package com.mob.mcl.p235c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.mob.MobSDK;
import com.mob.commons.C5873u;
import com.mob.commons.C5892y;
import com.mob.mcl.p234b.C5906a;
import com.mob.mcl.p236d.C5957b;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.UIHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.mcl.c.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5926b {

    /* renamed from: a */
    private static volatile C5926b f14586a;

    /* renamed from: d */
    private ScheduledExecutorService f14589d;

    /* renamed from: e */
    private ScheduledFuture f14590e;

    /* renamed from: c */
    private int f14588c = -1;

    /* renamed from: b */
    private Context f14587b = MobSDK.getContext();

    /* renamed from: a */
    public static C5926b m12045a() {
        if (f14586a == null) {
            synchronized (C5926b.class) {
                if (f14586a == null) {
                    f14586a = new C5926b();
                }
            }
        }
        return f14586a;
    }

    private C5926b() {
        C5873u.m12185a(m12038d(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.f14589d = Executors.newSingleThreadScheduledExecutor();
    }

    /* renamed from: b */
    public void m12040b() {
        try {
            m12039c();
            this.f14590e = this.f14589d.scheduleWithFixedDelay(new Runnable() { // from class: com.mob.mcl.c.b.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (!C5941h.m11988b().m11979c() || !C5941h.m11988b().m12016a(2000, 0)) {
                            C5926b.this.m12041a(new AbstractC6201c<Void>() { // from class: com.mob.mcl.c.b.1.1
                                @Override // com.mob.tools.utils.AbstractC6201c
                                /* renamed from: a  reason: avoid collision after fix types in other method */
                                public void mo11088a(Void r1) {
                                }
                            });
                        } else {
                            C5957b.m11958a().m11954b("TP HB tcp send ping success");
                        }
                    } catch (Throwable unused) {
                    }
                }
            }, 0L, C5941h.m11988b().f14619e, TimeUnit.SECONDS);
        } catch (Throwable th) {
            C5957b.m11958a().m11956a("TP HB timer error", th);
        }
    }

    /* renamed from: c */
    public boolean m12039c() {
        boolean z = false;
        try {
            if (this.f14590e != null) {
                z = this.f14590e.cancel(true);
                C5957b m11958a = C5957b.m11958a();
                m11958a.m11954b("TP HB cancel: " + z);
                return z;
            }
            return false;
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            return z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12044a(int i) {
        C5957b m11958a = C5957b.m11958a();
        m11958a.m11954b("TP HB onNetworkChanged: " + i + ", last: " + this.f14588c);
        if (this.f14588c == -1) {
            this.f14588c = i;
            return;
        }
        this.f14588c = i;
        if (this.f14588c != 0) {
            if (C5941h.m11988b().m11979c()) {
                C5957b.m11958a().m11954b("TP HB tcp status: true");
            } else {
                UIHandler.sendEmptyMessageDelayed(0, 200L, new Handler.Callback() { // from class: com.mob.mcl.c.b.2
                    @Override // android.os.Handler.Callback
                    public boolean handleMessage(Message message) {
                        C5906a.f14560a.execute(new Runnable() { // from class: com.mob.mcl.c.b.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    if (C5941h.m11988b().m11979c()) {
                                        return;
                                    }
                                    if (!C5941h.m11988b().m11975d()) {
                                        C5941h.m11988b().m11973f();
                                    }
                                    C5957b.m11958a().m11954b("TP HB reg tcp");
                                    C5941h.m11988b().m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.c.b.2.1.1
                                        @Override // com.mob.tools.utils.AbstractC6201c
                                        /* renamed from: a  reason: avoid collision after fix types in other method */
                                        public void mo11088a(Boolean bool) {
                                        }
                                    });
                                } catch (Throwable unused) {
                                }
                            }
                        });
                        return false;
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.mcl.c.b$3 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C59323 extends BroadcastReceiver {
        C59323() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            try {
                C5892y.f14525c.execute(new Runnable() { // from class: com.mob.mcl.c.b.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                                C6152DH.requester(context).getDetailNetworkTypeForStatic().request(new C6152DH.DHResponder() { // from class: com.mob.mcl.c.b.3.1.1
                                    @Override // com.mob.tools.utils.C6152DH.DHResponder
                                    public void onResponse(C6152DH.DHResponse dHResponse) {
                                        int i;
                                        String detailNetworkTypeForStatic = dHResponse.getDetailNetworkTypeForStatic();
                                        C5957b m11958a = C5957b.m11958a();
                                        m11958a.m11954b("TP HB receive network: " + detailNetworkTypeForStatic);
                                        if ("wifi".equalsIgnoreCase(detailNetworkTypeForStatic)) {
                                            i = 1;
                                        } else if ("5G".equalsIgnoreCase(detailNetworkTypeForStatic)) {
                                            i = 5;
                                        } else if ("4G".equalsIgnoreCase(detailNetworkTypeForStatic)) {
                                            i = 4;
                                        } else if ("3G".equalsIgnoreCase(detailNetworkTypeForStatic)) {
                                            i = 3;
                                        } else {
                                            i = "2G".equalsIgnoreCase(detailNetworkTypeForStatic) ? 2 : 0;
                                        }
                                        C5926b.this.m12044a(i);
                                    }
                                });
                            }
                        } catch (Throwable th) {
                            C5957b.m11958a().m11955a(th);
                        }
                    }
                });
            } catch (Throwable th) {
                C5957b.m11958a().m11955a(th);
            }
        }
    }

    /* renamed from: d */
    private BroadcastReceiver m12038d() {
        return new C59323();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12041a(final AbstractC6201c<Void> abstractC6201c) {
        if (!C5941h.m11988b().m11975d()) {
            C5941h.m11988b().m11973f();
        }
        C5941h.m11988b().m11997a(new AbstractC6201c<Boolean>() { // from class: com.mob.mcl.c.b.4
            @Override // com.mob.tools.utils.AbstractC6201c
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo11088a(Boolean bool) {
                AbstractC6201c abstractC6201c2 = abstractC6201c;
                if (abstractC6201c2 != null) {
                    abstractC6201c2.mo11088a(null);
                }
            }
        });
    }
}
