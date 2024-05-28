package com.mob.commons;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.ActivityTracker;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: com.mob.commons.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5855l {

    /* renamed from: a */
    private static C5855l f14420a;

    /* renamed from: c */
    private volatile Handler f14422c;

    /* renamed from: f */
    private volatile long f14425f;

    /* renamed from: b */
    private final HashSet<InterfaceC5854k> f14421b = new HashSet<>();

    /* renamed from: d */
    private String f14423d = null;

    /* renamed from: e */
    private volatile long f14424e = -1;

    /* renamed from: a */
    public static synchronized C5855l m12246a() {
        C5855l c5855l;
        synchronized (C5855l.class) {
            if (f14420a == null) {
                f14420a = new C5855l();
                if (f14420a.f14422c != null) {
                    f14420a.f14422c.sendEmptyMessage(0);
                }
            }
            c5855l = f14420a;
        }
        return c5855l;
    }

    /* renamed from: a */
    public void m12244a(InterfaceC5854k interfaceC5854k) {
        if (interfaceC5854k == null) {
            return;
        }
        synchronized (this.f14421b) {
            if (this.f14421b.contains(interfaceC5854k)) {
                return;
            }
            if (this.f14422c != null) {
                Message message = new Message();
                message.what = 3;
                message.obj = interfaceC5854k;
                this.f14422c.sendMessage(message);
            }
        }
    }

    /* renamed from: b */
    public boolean m12235b() {
        return this.f14424e == 0;
    }

    private C5855l() {
        String str = null;
        this.f14425f = 0L;
        this.f14425f = SystemClock.elapsedRealtime();
        if (!TextUtils.isEmpty("M-")) {
            str = C5892y.f14523a + m12238a("004Qhnhkjmjh");
        }
        this.f14422c = MobHandlerThread.newHandler(str, new Handler.Callback() { // from class: com.mob.commons.l.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        C5855l.this.f14424e = SystemClock.elapsedRealtime();
                        C5855l.this.m12237a(false);
                        C5855l.this.m12230d();
                        break;
                    case 1:
                        C5855l.this.m12237a(true);
                        break;
                    case 2:
                        C5855l.this.m12245a(((Long) message.obj).longValue(), true);
                        break;
                    case 3:
                        try {
                            InterfaceC5854k interfaceC5854k = (InterfaceC5854k) message.obj;
                            if (interfaceC5854k != null) {
                                C5855l.this.f14421b.add(interfaceC5854k);
                                interfaceC5854k.mo12147a(C5855l.this.f14424e > 0, true, 0L);
                                break;
                            }
                        } catch (Throwable th) {
                            MobLog.getInstance().m11341d(th);
                            break;
                        }
                        break;
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12237a(boolean z) {
        if (z) {
            m12236a(true, false, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12245a(long j, boolean z) {
        if (z) {
            m12236a(false, false, j);
        }
    }

    /* renamed from: a */
    private void m12236a(boolean z, boolean z2, long j) {
        synchronized (this.f14421b) {
            Iterator<InterfaceC5854k> it = this.f14421b.iterator();
            while (it.hasNext()) {
                it.next().mo12147a(z, z2, j);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m12230d() {
        ActivityTracker.getInstance(MobSDK.getContext()).addTracker(new ActivityTracker.Tracker() { // from class: com.mob.commons.FBManager$2
            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onCreated(Activity activity, Bundle bundle) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onPaused(Activity activity) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onSaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onStarted(Activity activity) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
                if (r0.equals(r7.toString()) == false) goto L21;
             */
            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onResumed(android.app.Activity r7) {
                /*
                    r6 = this;
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    long r0 = com.mob.commons.C5855l.m12229d(r0)     // Catch: java.lang.Throwable -> L5f
                    r2 = 0
                    int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r0 == 0) goto L24
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    java.lang.String r0 = com.mob.commons.C5855l.m12228e(r0)     // Catch: java.lang.Throwable -> L5f
                    if (r0 == 0) goto L24
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    java.lang.String r0 = com.mob.commons.C5855l.m12228e(r0)     // Catch: java.lang.Throwable -> L5f
                    java.lang.String r1 = r7.toString()     // Catch: java.lang.Throwable -> L5f
                    boolean r0 = r0.equals(r1)     // Catch: java.lang.Throwable -> L5f
                    if (r0 != 0) goto L2d
                L24:
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    long r4 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L5f
                    com.mob.commons.C5855l.m12233b(r0, r4)     // Catch: java.lang.Throwable -> L5f
                L2d:
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    long r0 = com.mob.commons.C5855l.m12231c(r0)     // Catch: java.lang.Throwable -> L5f
                    int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                    if (r0 != 0) goto L52
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    long r1 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L5f
                    com.mob.commons.C5855l.m12242a(r0, r1)     // Catch: java.lang.Throwable -> L5f
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    android.os.Handler r0 = com.mob.commons.C5855l.m12227f(r0)     // Catch: java.lang.Throwable -> L5f
                    if (r0 == 0) goto L52
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    android.os.Handler r0 = com.mob.commons.C5855l.m12227f(r0)     // Catch: java.lang.Throwable -> L5f
                    r1 = 1
                    r0.sendEmptyMessage(r1)     // Catch: java.lang.Throwable -> L5f
                L52:
                    com.mob.commons.l r0 = com.mob.commons.C5855l.this     // Catch: java.lang.Throwable -> L5f
                    if (r7 != 0) goto L58
                    r7 = 0
                    goto L5c
                L58:
                    java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L5f
                L5c:
                    com.mob.commons.C5855l.m12240a(r0, r7)     // Catch: java.lang.Throwable -> L5f
                L5f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.FBManager$2.onResumed(android.app.Activity):void");
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onStopped(Activity activity) {
                String str;
                Handler handler;
                Handler handler2;
                String str2;
                try {
                    str = C5855l.this.f14423d;
                    if (str != null) {
                        str2 = C5855l.this.f14423d;
                        if (!str2.equals(activity == null ? null : activity.toString())) {
                            return;
                        }
                    }
                    handler = C5855l.this.f14422c;
                    if (handler != null) {
                        long elapsedRealtime = C5855l.this.f14424e > 0 ? SystemClock.elapsedRealtime() - C5855l.this.f14424e : 0L;
                        Message message = new Message();
                        message.what = 2;
                        message.obj = Long.valueOf(elapsedRealtime);
                        handler2 = C5855l.this.f14422c;
                        handler2.sendMessage(message);
                    }
                    C5855l.this.f14424e = 0L;
                    C5855l.this.f14425f = 0L;
                    C5855l.this.f14423d = null;
                } catch (Throwable unused) {
                }
            }

            @Override // com.mob.tools.utils.ActivityTracker.Tracker
            public void onDestroyed(Activity activity) {
                if (C5855l.this.f14424e > 0) {
                    onStopped(activity);
                }
            }
        });
    }

    /* renamed from: c */
    public long m12232c() {
        return this.f14425f;
    }

    /* renamed from: a */
    public static String m12238a(String str) {
        return C5873u.m12180a(str, 101);
    }
}
