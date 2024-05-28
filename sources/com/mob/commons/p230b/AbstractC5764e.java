package com.mob.commons.p230b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.mob.commons.b.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC5764e {

    /* renamed from: a */
    protected final Context f14224a;

    /* renamed from: b */
    protected final String f14225b;

    /* renamed from: c */
    private boolean f14226c = false;

    /* renamed from: d */
    private boolean f14227d = false;

    /* renamed from: e */
    private String f14228e = null;

    /* renamed from: f */
    private int f14229f = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.b.e$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5767b {

        /* renamed from: a */
        boolean f14233a;

        /* renamed from: b */
        String f14234b;
    }

    /* renamed from: a */
    protected Intent mo12492a() {
        return null;
    }

    /* renamed from: a */
    protected C5767b mo12491a(IBinder iBinder) {
        return null;
    }

    /* renamed from: b */
    protected C5767b mo12493b() {
        return null;
    }

    public AbstractC5764e(Context context) {
        this.f14224a = context;
        this.f14225b = context.getPackageName();
    }

    /* renamed from: c */
    protected long mo12506c() {
        return (((this.f14229f - 1) * 2) + 2) * 1000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void m12522a(String str) {
        if (str != null) {
            if (!Pattern.compile("^[0fF\\-]+").matcher(str).matches()) {
                this.f14228e = str;
            }
        }
    }

    /* renamed from: f */
    private synchronized void m12519f() {
        if (this.f14226c) {
            return;
        }
        if (!m12523a(mo12492a())) {
            if (this.f14229f >= 4) {
                this.f14226c = true;
            }
        } else {
            this.f14226c = true;
        }
    }

    /* renamed from: a */
    private synchronized boolean m12523a(Intent intent) {
        boolean z;
        z = true;
        this.f14229f++;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        C5767b mo12493b = mo12493b();
        if (mo12493b == null) {
            mo12493b = m12524a(this.f14224a, intent);
        }
        if (mo12493b != null) {
            this.f14227d = mo12493b.f14233a;
            this.f14228e = mo12493b.f14234b;
        } else {
            z = false;
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        MobLog.getInstance().m11342d("oa use time: " + elapsedRealtime2, new Object[0]);
        return z;
    }

    /* renamed from: d */
    public synchronized String mo12514d() {
        m12519f();
        return this.f14228e;
    }

    /* renamed from: e */
    public synchronized boolean mo12495e() {
        m12519f();
        return this.f14227d;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    protected java.lang.String m12520a(java.lang.String r7, android.os.IBinder r8, java.lang.String r9, int r10, java.lang.String... r11) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch: java.lang.Throwable -> L3f
            android.os.Parcel r3 = android.os.Parcel.obtain()     // Catch: java.lang.Throwable -> L38
            r2.writeInterfaceToken(r9)     // Catch: java.lang.Throwable -> L33
            if (r11 == 0) goto L1e
            int r9 = r11.length     // Catch: java.lang.Throwable -> L33
            if (r9 <= 0) goto L1e
            int r9 = r11.length     // Catch: java.lang.Throwable -> L33
            r4 = r0
        L14:
            if (r4 >= r9) goto L1e
            r5 = r11[r4]     // Catch: java.lang.Throwable -> L33
            r2.writeString(r5)     // Catch: java.lang.Throwable -> L33
            int r4 = r4 + 1
            goto L14
        L1e:
            r8.transact(r10, r2, r3, r0)     // Catch: java.lang.Throwable -> L33
            r3.readException()     // Catch: java.lang.Throwable -> L33
            java.lang.String r7 = r3.readString()     // Catch: java.lang.Throwable -> L33
            if (r3 == 0) goto L2d
            r3.recycle()     // Catch: java.lang.Throwable -> L32
        L2d:
            if (r2 == 0) goto L32
            r2.recycle()     // Catch: java.lang.Throwable -> L32
        L32:
            return r7
        L33:
            r8 = move-exception
            goto L42
        L35:
            r7 = move-exception
            r3 = r1
            goto L74
        L38:
            r8 = move-exception
            r3 = r1
            goto L42
        L3b:
            r7 = move-exception
            r2 = r1
            r3 = r2
            goto L74
        L3f:
            r8 = move-exception
            r2 = r1
            r3 = r2
        L42:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L73
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L73
            r10.<init>()     // Catch: java.lang.Throwable -> L73
            java.lang.String r11 = "getStringValue: "
            r10.append(r11)     // Catch: java.lang.Throwable -> L73
            r10.append(r7)     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = " failed! "
            r10.append(r7)     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = r8.getMessage()     // Catch: java.lang.Throwable -> L73
            r10.append(r7)     // Catch: java.lang.Throwable -> L73
            java.lang.String r7 = r10.toString()     // Catch: java.lang.Throwable -> L73
            java.lang.Object[] r8 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L73
            r9.m11342d(r7, r8)     // Catch: java.lang.Throwable -> L73
            if (r3 == 0) goto L6d
            r3.recycle()     // Catch: java.lang.Throwable -> L72
        L6d:
            if (r2 == 0) goto L72
            r2.recycle()     // Catch: java.lang.Throwable -> L72
        L72:
            return r1
        L73:
            r7 = move-exception
        L74:
            if (r3 == 0) goto L79
            r3.recycle()     // Catch: java.lang.Throwable -> L7e
        L79:
            if (r2 == 0) goto L7e
            r2.recycle()     // Catch: java.lang.Throwable -> L7e
        L7e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p230b.AbstractC5764e.m12520a(java.lang.String, android.os.IBinder, java.lang.String, int, java.lang.String[]):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005f A[Catch: Throwable -> 0x0062, TRY_LEAVE, TryCatch #4 {Throwable -> 0x0062, blocks: (B:24:0x005a, B:26:0x005f), top: B:35:0x005a }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x005a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m12521a(java.lang.String r4, android.os.IBinder r5, java.lang.String r6, int r7) {
        /*
            r3 = this;
            r0 = 0
            r1 = 0
            android.os.Parcel r2 = android.os.Parcel.obtain()     // Catch: java.lang.Throwable -> L29 android.os.RemoteException -> L2c
            android.os.Parcel r0 = android.os.Parcel.obtain()     // Catch: java.lang.Throwable -> L22 android.os.RemoteException -> L26
            r2.writeInterfaceToken(r6)     // Catch: java.lang.Throwable -> L22 android.os.RemoteException -> L26
            r5.transact(r7, r2, r0, r1)     // Catch: java.lang.Throwable -> L22 android.os.RemoteException -> L26
            r0.readException()     // Catch: java.lang.Throwable -> L22 android.os.RemoteException -> L26
            int r4 = r0.readInt()     // Catch: java.lang.Throwable -> L22 android.os.RemoteException -> L26
            if (r0 == 0) goto L1c
            r0.recycle()     // Catch: java.lang.Throwable -> L21
        L1c:
            if (r2 == 0) goto L21
            r2.recycle()     // Catch: java.lang.Throwable -> L21
        L21:
            return r4
        L22:
            r4 = move-exception
            r5 = r0
            r0 = r2
            goto L58
        L26:
            r5 = r0
            r0 = r2
            goto L2d
        L29:
            r4 = move-exception
            r5 = r0
            goto L58
        L2c:
            r5 = r0
        L2d:
            com.mob.tools.log.NLog r6 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L57
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57
            r7.<init>()     // Catch: java.lang.Throwable -> L57
            java.lang.String r2 = "getIntValue: "
            r7.append(r2)     // Catch: java.lang.Throwable -> L57
            r7.append(r4)     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = " failed! (remoteException)"
            r7.append(r4)     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = r7.toString()     // Catch: java.lang.Throwable -> L57
            java.lang.Object[] r7 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L57
            r6.m11342d(r4, r7)     // Catch: java.lang.Throwable -> L57
            if (r5 == 0) goto L51
            r5.recycle()     // Catch: java.lang.Throwable -> L56
        L51:
            if (r0 == 0) goto L56
            r0.recycle()     // Catch: java.lang.Throwable -> L56
        L56:
            return r1
        L57:
            r4 = move-exception
        L58:
            if (r5 == 0) goto L5d
            r5.recycle()     // Catch: java.lang.Throwable -> L62
        L5d:
            if (r0 == 0) goto L62
            r0.recycle()     // Catch: java.lang.Throwable -> L62
        L62:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p230b.AbstractC5764e.m12521a(java.lang.String, android.os.IBinder, java.lang.String, int):int");
    }

    /* renamed from: a */
    private C5767b m12524a(Context context, Intent intent) throws Throwable {
        boolean bindService;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Throwable("unable to invoke in main thread!");
        }
        ServiceConnectionC5766a serviceConnectionC5766a = new ServiceConnectionC5766a();
        try {
            if (Build.VERSION.SDK_INT >= 34) {
                bindService = context.bindService(intent, serviceConnectionC5766a, 513);
            } else {
                bindService = context.bindService(intent, serviceConnectionC5766a, 1);
            }
            if (intent != null && bindService) {
                long mo12506c = mo12506c();
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("wte " + mo12506c, new Object[0]);
                IBinder m12518a = serviceConnectionC5766a.m12518a(mo12506c());
                if (m12518a != null) {
                    return mo12491a(m12518a);
                }
                throw new Throwable("get binder " + intent.getComponent() + " failed!");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("bind service ");
            sb.append(intent == null ? "null" : intent.getComponent());
            sb.append(" failed!");
            throw new Throwable(sb.toString());
        } finally {
            try {
                context.unbindService(serviceConnectionC5766a);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.b.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ServiceConnectionC5766a implements ServiceConnection {

        /* renamed from: a */
        boolean f14230a;

        /* renamed from: c */
        private final BlockingQueue<IBinder> f14232c;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        private ServiceConnectionC5766a() {
            this.f14230a = false;
            this.f14232c = new LinkedBlockingQueue();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f14232c.put(iBinder);
            } catch (Throwable unused) {
            }
        }

        /* renamed from: a */
        public IBinder m12518a(long j) throws InterruptedException {
            if (this.f14230a) {
                throw new IllegalStateException();
            }
            this.f14230a = true;
            BlockingQueue<IBinder> blockingQueue = this.f14232c;
            if (j <= 0) {
                j = 1500;
            }
            return blockingQueue.poll(j, TimeUnit.MILLISECONDS);
        }
    }
}
