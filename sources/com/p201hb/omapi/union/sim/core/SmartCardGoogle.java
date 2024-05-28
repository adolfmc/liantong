package com.p201hb.omapi.union.sim.core;

import android.content.Context;
import android.se.omapi.Channel;
import android.se.omapi.Reader;
import android.se.omapi.SEService;
import android.se.omapi.Session;
import androidx.annotation.RequiresApi;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import com.p201hb.omapi.union.sim.bean.SIMInfo;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p001a.p002a.p003a.p004a.p005a.p006b.C0099c;

@RequiresApi(28)
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001CB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010'\u001a\u00020(2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001a\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0*J\b\u0010.\u001a\u00020(H\u0016J\u0006\u0010/\u001a\u00020(J\u0006\u00100\u001a\u00020(J\b\u00101\u001a\u0004\u0018\u00010-J\u000e\u00102\u001a\b\u0012\u0004\u0012\u00020+0*H\u0016J\f\u00103\u001a\b\u0012\u0004\u0012\u00020-0*J\"\u00104\u001a\u00020(2\f\u00105\u001a\b\u0012\u0004\u0012\u00020-0*2\f\u00106\u001a\b\u0012\u0004\u0012\u00020+0*J\u001b\u00107\u001a\u0004\u0018\u00010-2\f\u00108\u001a\b\u0012\u0004\u0012\u00020-09¢\u0006\u0002\u0010:J\b\u0010;\u001a\u00020(H\u0016J\u0010\u0010<\u001a\u00020\n2\u0006\u0010=\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020>2\u0006\u0010@\u001a\u00020>H\u0016J\u0010\u0010A\u001a\u00020>2\u0006\u0010@\u001a\u00020>H\u0002J\b\u0010B\u001a\u00020(H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001a\"\u0004\b \u0010\u001cR\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u0006D"}, m1890d2 = {"Lcom/hb/omapi/union/sim/core/SmartCardGoogle;", "Lcom/hb/omapi/union/sim/core/BaseSmartCard;", "Landroid/se/omapi/SEService$OnConnectedListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "setContext", "isConnection", "", "listener", "Lcom/hb/omapi/union/sim/listener/ConnectListener;", "getListener", "()Lcom/hb/omapi/union/sim/listener/ConnectListener;", "setListener", "(Lcom/hb/omapi/union/sim/listener/ConnectListener;)V", "mChannel", "Landroid/se/omapi/Channel;", "mSEService", "Landroid/se/omapi/SEService;", "mSession", "Landroid/se/omapi/Session;", "sessionKey", "", "getSessionKey", "()Ljava/lang/String;", "setSessionKey", "(Ljava/lang/String;)V", "value", "simType", "getSimType", "setSimType", "timer", "Ljava/util/Timer;", "getTimer", "()Ljava/util/Timer;", "setTimer", "(Ljava/util/Timer;)V", "bindService", "", "check5G", "", "Lcom/hb/omapi/union/sim/bean/SIMInfo;", "simList", "Landroid/se/omapi/Reader;", "closeChannel", "closeChannelAndSession", "closeTimer", "getCurrentAvailableReader", "getSIM", "getSIMList", "getSimAvailable", "list", "list5g", "getSimReader", "readers", "", "([Landroid/se/omapi/Reader;)Landroid/se/omapi/Reader;", "onConnected", "openCurrentAvailableChannel", "aid", "", "sendApdu", "command", "transmitCommand", "unbindService", "OMAExecutor", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: a.a.a.a.a.c.f  reason: from Kotlin metadata */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public final class SmartCardGoogle implements BaseSmartCard, SEService.OnConnectedListener {

    /* renamed from: d */
    public SEService f61d;

    /* renamed from: e */
    public boolean f62e;

    /* renamed from: f */
    public Channel f63f;

    /* renamed from: g */
    public Session f64g;
    @NotNull

    /* renamed from: h */
    public Context f65h;
    @NotNull

    /* renamed from: i */
    public Timer f66i;
    @Nullable

    /* renamed from: j */
    public ConnectListener f67j;
    @NotNull

    /* renamed from: k */
    public String f68k = "";
    @NotNull

    /* renamed from: l */
    public String f69l = "SIM1";

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.a.a.a.a.c.f$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static final class ExecutorC0105a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(@NotNull Runnable runnable) {
            runnable.run();
        }
    }

    /* renamed from: a.a.a.a.a.c.f$b */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static final class C0106b extends TimerTask {

        /* renamed from: D */
        public final /* synthetic */ ConnectListener f71D;

        public C0106b(ConnectListener connectListener) {
            this.f71D = connectListener;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            C0103d.m24388a("定时器  google");
            ConnectListener connectListener = this.f71D;
            if (connectListener != null) {
                connectListener.finish(16);
            }
            SmartCardGoogle.this.m24357g();
        }
    }

    public SmartCardGoogle(@NotNull Context context) {
        this.f65h = context;
    }

    /* renamed from: a */
    public final void m24366a(@NotNull Context context) {
        this.f65h = context;
    }

    /* renamed from: b */
    public final void m24361b(@Nullable ConnectListener connectListener) {
        this.f67j = connectListener;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: c */
    public String mo24340c() {
        return this.f68k;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: d */
    public void mo24338d() {
        m24358f();
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: e */
    public String mo24337e() {
        return this.f69l;
    }

    /* renamed from: f */
    public final void m24358f() {
        try {
            if (this.f63f != null) {
                Channel channel = this.f63f;
                if (channel == null) {
                    Intrinsics.throwNpe();
                }
                if (channel.isOpen()) {
                    Channel channel2 = this.f63f;
                    if (channel2 != null) {
                        channel2.close();
                    }
                    this.f63f = null;
                    C0103d.m24386c("channel close success");
                }
            }
        } catch (Exception e) {
            C0103d.m24387b("channel close error:" + e.getMessage());
        }
        try {
            if (this.f64g != null) {
                Session session = this.f64g;
                if (session == null) {
                    Intrinsics.throwNpe();
                }
                if (session.isClosed()) {
                    return;
                }
                Session session2 = this.f64g;
                if (session2 != null) {
                    session2.close();
                }
                this.f64g = null;
                C0103d.m24386c("session close success");
            }
        } catch (Exception e2) {
            C0103d.m24387b("session close error:" + e2.getMessage());
        }
    }

    /* renamed from: g */
    public final void m24357g() {
        Timer timer = this.f66i;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
        }
        timer.cancel();
    }

    @NotNull
    /* renamed from: h */
    public final Context m24356h() {
        return this.f65h;
    }

    @Nullable
    /* renamed from: i */
    public final Reader m24355i() {
        try {
            SEService sEService = this.f61d;
            if (sEService == null) {
                Intrinsics.throwNpe();
            }
            Reader[] readers = sEService.getReaders();
            Intrinsics.checkExpressionValueIsNotNull(readers, "mSEService!!.readers");
            Reader m24362a = m24362a(readers);
            if (m24362a != null) {
                return m24362a;
            }
            return null;
        } catch (Exception e) {
            C0103d.m24388a("getCurrentAvailableReader error:" + e.getMessage());
            return null;
        }
    }

    @Nullable
    /* renamed from: j */
    public final ConnectListener m24354j() {
        return this.f67j;
    }

    @NotNull
    /* renamed from: k */
    public final List<Reader> m24353k() {
        ArrayList arrayList = new ArrayList();
        SEService sEService = this.f61d;
        if (sEService == null) {
            Intrinsics.throwNpe();
        }
        Reader[] readers = sEService.getReaders();
        Intrinsics.checkExpressionValueIsNotNull(readers, "mSEService!!.readers");
        for (Reader reader : readers) {
            Intrinsics.checkExpressionValueIsNotNull(reader, "reader");
            String name = reader.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "reader.name");
            if (StringsKt.startsWith$default(name, "SIM", false, 2, (Object) null) && reader.isSecureElementPresent()) {
                arrayList.add(reader);
            }
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: l */
    public final Timer m24352l() {
        Timer timer = this.f66i;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
        }
        return timer;
    }

    @Override // android.se.omapi.SEService.OnConnectedListener
    public synchronized void onConnected() {
        this.f62e = true;
        C0103d.m24386c("SmartCard OMA connect success(Google)");
        ConnectListener connectListener = this.f67j;
        if (connectListener != null) {
            connectListener.finish(0);
        }
        m24357g();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001e, code lost:
        if (r0.isOpen() == false) goto L31;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized byte[] m24359c(byte[] r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.se.omapi.SEService r0 = r2.f61d     // Catch: java.lang.Throwable -> L81
            if (r0 == 0) goto L79
            if (r0 != 0) goto La
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L81
        La:
            boolean r0 = r0.isConnected()     // Catch: java.lang.Throwable -> L81
            if (r0 != 0) goto L11
            goto L79
        L11:
            android.se.omapi.Channel r0 = r2.f63f     // Catch: java.lang.Throwable -> L81
            if (r0 == 0) goto L20
            if (r0 != 0) goto L1a
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L81
        L1a:
            boolean r0 = r0.isOpen()     // Catch: java.lang.Throwable -> L81
            if (r0 != 0) goto L34
        L20:
            a.a.a.a.a.b.c r0 = p001a.p002a.p003a.p004a.p005a.p006b.C0099c.f45l     // Catch: java.lang.Throwable -> L81
            byte[] r0 = r0.m24422a()     // Catch: java.lang.Throwable -> L81
            boolean r0 = r2.m24360b(r0)     // Catch: java.lang.Throwable -> L81
            if (r0 != 0) goto L34
            java.lang.String r3 = "6fff"
            byte[] r3 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24374c(r3)     // Catch: java.lang.Throwable -> L81
            monitor-exit(r2)
            return r3
        L34:
            android.se.omapi.Channel r0 = r2.f63f     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            if (r0 != 0) goto L3b
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
        L3b:
            byte[] r3 = r0.transmit(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            java.lang.String r0 = "mChannel!!.transmit(command)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            r0.<init>()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            java.lang.String r1 = "response-->"
            r0.append(r1)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            java.lang.String r1 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24368h(r3)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            r0.append(r1)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            p001a.p002a.p003a.p004a.p005a.p007c.C0103d.m24388a(r0)     // Catch: java.lang.Exception -> L5e java.lang.Throwable -> L81
            monitor-exit(r2)
            return r3
        L5e:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L81
            if (r3 == 0) goto L6d
            p001a.p002a.p003a.p004a.p005a.p007c.C0103d.m24387b(r3)     // Catch: java.lang.Throwable -> L81
            com.hb.omapi.union.sim.bean.ErrCode$Companion r0 = com.p201hb.omapi.union.sim.bean.ErrCode.Companion     // Catch: java.lang.Throwable -> L81
            r0.setErrException(r3)     // Catch: java.lang.Throwable -> L81
        L6d:
            com.hb.omapi.union.sim.bean.ErrCode$Companion r3 = com.p201hb.omapi.union.sim.bean.ErrCode.Companion     // Catch: java.lang.Throwable -> L81
            java.lang.String r3 = r3.getException_HEX()     // Catch: java.lang.Throwable -> L81
            byte[] r3 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24374c(r3)     // Catch: java.lang.Throwable -> L81
            monitor-exit(r2)
            return r3
        L79:
            java.lang.String r3 = "6ffe"
            byte[] r3 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24374c(r3)     // Catch: java.lang.Throwable -> L81
            monitor-exit(r2)
            return r3
        L81:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p201hb.omapi.union.sim.core.SmartCardGoogle.m24359c(byte[]):byte[]");
    }

    /* renamed from: a */
    public final void m24363a(@NotNull Timer timer) {
        this.f66i = timer;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: b */
    public void mo24342b(@NotNull String str) {
        this.f68k = str;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: a */
    public synchronized void mo24350a(@Nullable ConnectListener connectListener) {
        if (connectListener != null) {
            this.f67j = connectListener;
        }
        SEService sEService = this.f61d;
        if (sEService != null) {
            if (sEService == null) {
                Intrinsics.throwNpe();
            }
            if (sEService.isConnected()) {
                if (connectListener != null) {
                    connectListener.finish(0);
                }
                m24357g();
            }
        }
        this.f61d = new SEService(this.f65h, new ExecutorC0105a(), this);
        Timer timer = kotlin.concurrent.Timer.timer("", false);
        timer.scheduleAtFixedRate(new C0106b(connectListener), 2000L, 2000L);
        this.f66i = timer;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: b */
    public void mo24344b() {
        try {
            mo24342b("");
            mo24338d();
            if (this.f61d != null) {
                SEService sEService = this.f61d;
                if (sEService == null) {
                    Intrinsics.throwNpe();
                }
                if (sEService.isConnected()) {
                    SEService sEService2 = this.f61d;
                    if (sEService2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sEService2.shutdown();
                    this.f61d = null;
                    this.f62e = false;
                    C0103d.m24386c("SEService shutdown success");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            C0103d.m24387b("SEService shutdown error:" + e.getMessage());
        }
    }

    /* renamed from: b */
    private final boolean m24360b(byte[] bArr) {
        Reader m24355i;
        if (this.f62e && (m24355i = m24355i()) != null && m24355i.isSecureElementPresent()) {
            Session openSession = m24355i.openSession();
            this.f64g = openSession;
            if (openSession != null) {
                try {
                    C0103d.m24388a("打开通道:" + C0104e.m24368h(bArr));
                    Session session = this.f64g;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(bArr) : null;
                    this.f63f = openLogicalChannel;
                    if (openLogicalChannel != null) {
                        openLogicalChannel.getSelectResponse();
                    }
                } catch (Exception e) {
                    C0103d.m24387b("openLogicalChannel error:" + e.getMessage());
                    return false;
                }
            }
            return this.f63f != null;
        }
        return false;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: a */
    public void mo24349a(@NotNull String str) {
        String upperCase = str.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        this.f69l = upperCase;
        if (!m24360b(C0099c.f45l.m24422a())) {
            ConnectListener connectListener = this.f67j;
            if (connectListener != null) {
                connectListener.finish(ErrCode.Companion.getErrCode("6fff"));
                return;
            }
            return;
        }
        ConnectListener connectListener2 = this.f67j;
        if (connectListener2 != null) {
            connectListener2.finish(ErrCode.Companion.getErrCode("9000"));
        }
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: a */
    public List<SIMInfo> mo24351a() {
        if (!this.f62e) {
            return new ArrayList();
        }
        List<Reader> m24353k = m24353k();
        if (m24353k.size() == 0) {
            return new ArrayList();
        }
        List<SIMInfo> m24365a = m24365a(m24353k);
        m24364a(m24353k, m24365a);
        return m24365a;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: a */
    public byte[] mo24345a(@NotNull byte[] bArr) {
        if (!this.f62e) {
            mo24350a((ConnectListener) null);
        }
        C0103d.m24388a("sendApdu:" + C0104e.m24368h(bArr));
        return m24359c(bArr);
    }

    @NotNull
    /* renamed from: a */
    public final List<SIMInfo> m24365a(@NotNull List<Reader> list) {
        byte[] m24374c = C0104e.m24374c("A0000000871002FF86FFFF89FFFFFFFF");
        ArrayList arrayList = new ArrayList();
        for (Reader reader : list) {
            SIMInfo sIMInfo = new SIMInfo();
            String name = reader.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "reader.name");
            sIMInfo.setName(name);
            Session openSession = reader.openSession();
            this.f64g = openSession;
            if (openSession != null) {
                try {
                    if (this.f63f != null) {
                        Channel channel = this.f63f;
                        if (channel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (channel.isOpen()) {
                            Channel channel2 = this.f63f;
                            if (channel2 != null) {
                                channel2.close();
                            }
                            this.f63f = null;
                        }
                    }
                    Session session = this.f64g;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(m24374c) : null;
                    this.f63f = openLogicalChannel;
                    if (openLogicalChannel != null) {
                        if (openLogicalChannel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (openLogicalChannel.isOpen()) {
                            Channel channel3 = this.f63f;
                            if (channel3 == null) {
                                Intrinsics.throwNpe();
                            }
                            byte[] transmit = channel3.transmit(C0104e.m24374c("00a4000c025FC0"));
                            Intrinsics.checkExpressionValueIsNotNull(transmit, "mChannel!!.transmit(\"00a4000c025FC0\".toBytes())");
                            C0103d.m24388a("check 5g response:" + C0104e.m24368h(transmit));
                            if (C0104e.m24371e(transmit)) {
                                sIMInfo.set5G(true);
                            }
                            m24358f();
                        }
                    }
                    sIMInfo.set5G(false);
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (message != null) {
                        C0103d.m24388a(message);
                    }
                    sIMInfo.set5G(false);
                }
            }
            arrayList.add(sIMInfo);
        }
        return arrayList;
    }

    @Nullable
    /* renamed from: a */
    public final Reader m24362a(@NotNull Reader[] readerArr) {
        if (readerArr.length < 1) {
            C0103d.m24385d("There is no avaliable reader");
            return null;
        }
        for (Reader reader : readerArr) {
            C0103d.m24388a("avaliable reader name:" + reader.getName());
            String name = reader.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "reader.name");
            if (StringsKt.startsWith$default(name, mo24337e(), false, 2, (Object) null) && reader.isSecureElementPresent()) {
                return reader;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final void m24364a(@NotNull List<Reader> list, @NotNull List<SIMInfo> list2) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            Session openSession = list.get(i).openSession();
            this.f64g = openSession;
            if (openSession != null) {
                try {
                    C0103d.m24388a("打开通道:" + C0104e.m24368h(C0099c.f45l.m24422a()));
                    Session session = this.f64g;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(C0099c.f45l.m24422a()) : null;
                    this.f63f = openLogicalChannel;
                    if (openLogicalChannel != null) {
                        if (openLogicalChannel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (openLogicalChannel.isOpen()) {
                            Channel channel = this.f63f;
                            byte[] selectResponse = channel != null ? channel.getSelectResponse() : null;
                            byte[] copyOfRange = selectResponse != null ? ArraysKt.copyOfRange(selectResponse, 0, selectResponse.length - 2) : null;
                            list2.get(i).setInstallApplet(true);
                            SIMInfo sIMInfo = list2.get(i);
                            if (copyOfRange == null) {
                                Intrinsics.throwNpe();
                            }
                            Charset charset = StandardCharsets.US_ASCII;
                            Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
                            sIMInfo.setVersion(new String(copyOfRange, charset));
                            m24358f();
                        }
                    }
                    list2.get(i).setInstallApplet(false);
                } catch (Exception unused) {
                    list2.get(i).setInstallApplet(false);
                }
            }
            if (i == size) {
                return;
            }
            i++;
        }
    }
}
