package com.p201hb.omapi.union.sim.core;

import android.content.Context;
import androidx.annotation.NonNull;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import com.p201hb.omapi.union.sim.bean.SIMInfo;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.Session;
import p001a.p002a.p003a.p004a.p005a.p006b.C0099c;

@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0012\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0012\u0010$\u001a\u00020%2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020*0'J\b\u0010+\u001a\u00020%H\u0016J\u0006\u0010,\u001a\u00020%J\u0006\u0010-\u001a\u00020%J\n\u0010.\u001a\u0004\u0018\u00010*H\u0002J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u00020(0'H\u0016J\f\u00100\u001a\b\u0012\u0004\u0012\u00020*0'J\"\u00101\u001a\u00020%2\f\u00102\u001a\b\u0012\u0004\u0012\u00020*0'2\f\u00103\u001a\b\u0012\u0004\u0012\u00020(0'J\u0010\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u000206H\u0002J\u0012\u00107\u001a\u0002062\b\b\u0001\u00108\u001a\u000206H\u0016J\u0012\u00109\u001a\u00020%2\b\u0010:\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010;\u001a\u0002062\u0006\u00108\u001a\u000206H\u0002J\b\u0010<\u001a\u00020%H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0015@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001a\u0010\u001e\u001a\u00020\u001fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#¨\u0006="}, m1890d2 = {"Lcom/hb/omapi/union/sim/core/SmartCardSimalliance;", "Lcom/hb/omapi/union/sim/core/BaseSmartCard;", "Lorg/simalliance/openmobileapi/SEService$CallBack;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isConnection", "", "listener", "Lcom/hb/omapi/union/sim/listener/ConnectListener;", "getListener", "()Lcom/hb/omapi/union/sim/listener/ConnectListener;", "setListener", "(Lcom/hb/omapi/union/sim/listener/ConnectListener;)V", "mChannel", "Lorg/simalliance/openmobileapi/Channel;", "mSEService", "Lorg/simalliance/openmobileapi/SEService;", "mSession", "Lorg/simalliance/openmobileapi/Session;", "sessionKey", "", "getSessionKey", "()Ljava/lang/String;", "setSessionKey", "(Ljava/lang/String;)V", "value", "simType", "getSimType", "setSimType", "timer", "Ljava/util/Timer;", "getTimer", "()Ljava/util/Timer;", "setTimer", "(Ljava/util/Timer;)V", "bindService", "", "check5G", "", "Lcom/hb/omapi/union/sim/bean/SIMInfo;", "simList", "Lorg/simalliance/openmobileapi/Reader;", "closeChannel", "closeChannelAndSession", "closeTimer", "getCurrentAvailableReader", "getSIM", "getSIMList", "getSimAvailable", "list", "list5g", "openCurrentAvailableChannel", "aid", "", "sendApdu", "command", "serviceConnected", "p0", "transmitCommand", "unbindService", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: a.a.a.a.a.c.g  reason: from Kotlin metadata */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public final class SmartCardSimalliance implements BaseSmartCard, SEService.CallBack {

    /* renamed from: d */
    public SEService f72d;

    /* renamed from: e */
    public Context f73e;

    /* renamed from: f */
    public boolean f74f;

    /* renamed from: g */
    public Channel f75g;

    /* renamed from: h */
    public Session f76h;
    @NotNull

    /* renamed from: i */
    public String f77i = "";
    @NotNull

    /* renamed from: j */
    public String f78j = "SIM1";
    @NotNull

    /* renamed from: k */
    public Timer f79k;
    @Nullable

    /* renamed from: l */
    public ConnectListener f80l;

    /* renamed from: a.a.a.a.a.c.g$a */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static final class C0107a extends TimerTask {

        /* renamed from: D */
        public final /* synthetic */ ConnectListener f82D;

        public C0107a(ConnectListener connectListener) {
            this.f82D = connectListener;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            C0103d.m24388a("定时器   sim");
            ConnectListener connectListener = this.f82D;
            if (connectListener != null) {
                connectListener.finish(16);
            }
            SmartCardSimalliance.this.m24335g();
        }
    }

    public SmartCardSimalliance(@NotNull Context context) {
        this.f73e = context;
    }

    /* renamed from: k */
    private final Reader m24331k() {
        try {
            SEService sEService = this.f72d;
            Reader[] readers = sEService != null ? sEService.getReaders() : null;
            if (readers != null && readers.length >= 1) {
                for (Reader reader : readers) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("avaliable reader name:");
                    Intrinsics.checkExpressionValueIsNotNull(reader, "reader");
                    sb.append(reader.getName());
                    C0103d.m24388a(sb.toString());
                    if (reader.getName().equals(mo24337e()) && reader.isSecureElementPresent()) {
                        return reader;
                    }
                }
                return null;
            }
            C0103d.m24385d("There is no avaliable reader");
            return null;
        } catch (Exception e) {
            C0103d.m24388a("getCurrentAvailableReader error:" + e.getMessage());
            return null;
        }
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: a */
    public void mo24349a(@NotNull String str) {
        ConnectListener connectListener;
        String upperCase = str.toUpperCase();
        Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
        this.f78j = upperCase;
        if (m24341b(C0099c.f45l.m24422a()) || (connectListener = this.f80l) == null) {
            return;
        }
        connectListener.finish(ErrCode.Companion.getErrCode("6fff"));
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: b */
    public void mo24342b(@NotNull String str) {
        this.f77i = str;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: c */
    public String mo24340c() {
        return this.f77i;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: d */
    public void mo24338d() {
        m24336f();
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: e */
    public String mo24337e() {
        return this.f78j;
    }

    /* renamed from: f */
    public final void m24336f() {
        try {
            if (this.f75g != null) {
                Channel channel = this.f75g;
                if (channel == null) {
                    Intrinsics.throwNpe();
                }
                if (!channel.isClosed()) {
                    Channel channel2 = this.f75g;
                    if (channel2 == null) {
                        Intrinsics.throwNpe();
                    }
                    channel2.close();
                    this.f75g = null;
                    C0103d.m24386c("channel close success");
                }
            }
        } catch (Exception e) {
            C0103d.m24387b("channel close error:" + e.getMessage());
        }
        try {
            if (this.f76h != null) {
                Session session = this.f76h;
                if (session == null) {
                    Intrinsics.throwNpe();
                }
                if (session.isClosed()) {
                    return;
                }
                Session session2 = this.f76h;
                if (session2 == null) {
                    Intrinsics.throwNpe();
                }
                session2.close();
                this.f76h = null;
                C0103d.m24386c("session close success");
            }
        } catch (Exception e2) {
            C0103d.m24387b("session close error:" + e2.getMessage());
        }
    }

    /* renamed from: g */
    public final void m24335g() {
        Timer timer = this.f79k;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
        }
        timer.cancel();
    }

    @Nullable
    /* renamed from: h */
    public final ConnectListener m24334h() {
        return this.f80l;
    }

    @NotNull
    /* renamed from: i */
    public final List<Reader> m24333i() {
        Reader[] readers;
        ArrayList arrayList = new ArrayList();
        SEService sEService = this.f72d;
        if (sEService == null) {
            Intrinsics.throwNpe();
        }
        for (Reader reader : sEService.getReaders()) {
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
    /* renamed from: j */
    public final Timer m24332j() {
        Timer timer = this.f79k;
        if (timer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timer");
        }
        return timer;
    }

    @Override // org.simalliance.openmobileapi.SEService.CallBack
    public synchronized void serviceConnected(@Nullable SEService sEService) {
        C0103d.m24386c("SmartCard OMA connect success(Simalliance)");
        this.f72d = sEService;
        this.f74f = true;
        ConnectListener connectListener = this.f80l;
        if (connectListener != null) {
            connectListener.finish(0);
        }
        m24335g();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001e, code lost:
        if (r0.isClosed() != false) goto L23;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final synchronized byte[] m24339c(byte[] r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            org.simalliance.openmobileapi.SEService r0 = r2.f72d     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L5e
            if (r0 != 0) goto La
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L66
        La:
            boolean r0 = r0.isConnected()     // Catch: java.lang.Throwable -> L66
            if (r0 != 0) goto L11
            goto L5e
        L11:
            org.simalliance.openmobileapi.Channel r0 = r2.f75g     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L20
            if (r0 != 0) goto L1a
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L66
        L1a:
            boolean r0 = r0.isClosed()     // Catch: java.lang.Throwable -> L66
            if (r0 == 0) goto L34
        L20:
            a.a.a.a.a.b.c r0 = p001a.p002a.p003a.p004a.p005a.p006b.C0099c.f45l     // Catch: java.lang.Throwable -> L66
            byte[] r0 = r0.m24422a()     // Catch: java.lang.Throwable -> L66
            boolean r0 = r2.m24341b(r0)     // Catch: java.lang.Throwable -> L66
            if (r0 != 0) goto L34
            java.lang.String r3 = "6fff"
            byte[] r3 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24374c(r3)     // Catch: java.lang.Throwable -> L66
            monitor-exit(r2)
            return r3
        L34:
            org.simalliance.openmobileapi.Channel r0 = r2.f75g     // Catch: java.lang.Throwable -> L66
            if (r0 != 0) goto L3b
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch: java.lang.Throwable -> L66
        L3b:
            byte[] r3 = r0.transmit(r3)     // Catch: java.lang.Throwable -> L66
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66
            r0.<init>()     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = "response-》"
            r0.append(r1)     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = "byteRapdu"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r1)     // Catch: java.lang.Throwable -> L66
            java.lang.String r1 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24368h(r3)     // Catch: java.lang.Throwable -> L66
            r0.append(r1)     // Catch: java.lang.Throwable -> L66
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L66
            p001a.p002a.p003a.p004a.p005a.p007c.C0103d.m24388a(r0)     // Catch: java.lang.Throwable -> L66
            monitor-exit(r2)
            return r3
        L5e:
            java.lang.String r3 = "6ffe"
            byte[] r3 = p001a.p002a.p003a.p004a.p005a.p007c.C0104e.m24374c(r3)     // Catch: java.lang.Throwable -> L66
            monitor-exit(r2)
            return r3
        L66:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p201hb.omapi.union.sim.core.SmartCardSimalliance.m24339c(byte[]):byte[]");
    }

    /* renamed from: b */
    public final void m24343b(@Nullable ConnectListener connectListener) {
        this.f80l = connectListener;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: b */
    public void mo24344b() {
        try {
            mo24342b("");
            mo24338d();
            if (this.f72d != null) {
                SEService sEService = this.f72d;
                if (sEService == null) {
                    Intrinsics.throwNpe();
                }
                if (sEService.isConnected()) {
                    SEService sEService2 = this.f72d;
                    if (sEService2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sEService2.shutdown();
                    this.f72d = null;
                    this.f74f = false;
                    C0103d.m24386c("SEService shutdown success");
                }
            }
        } catch (Exception e) {
            C0103d.m24387b("SEService shutdown error:" + e.getMessage());
        }
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: a */
    public List<SIMInfo> mo24351a() {
        if (!this.f74f) {
            return new ArrayList();
        }
        List<Reader> m24333i = m24333i();
        if (m24333i.size() == 0) {
            return new ArrayList();
        }
        List<SIMInfo> m24348a = m24348a(m24333i);
        m24347a(m24333i, m24348a);
        return m24348a;
    }

    /* renamed from: b */
    private final boolean m24341b(byte[] bArr) {
        Reader m24331k;
        if (this.f74f && (m24331k = m24331k()) != null && m24331k.isSecureElementPresent()) {
            this.f76h = m24331k.openSession();
            C0103d.m24388a("open channel applet：" + C0104e.m24368h(bArr));
            if (this.f76h != null) {
                try {
                    C0103d.m24388a("打开通道:" + C0104e.m24368h(bArr));
                    Session session = this.f76h;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(bArr) : null;
                    this.f75g = openLogicalChannel;
                    if (openLogicalChannel != null) {
                        openLogicalChannel.getSelectResponse();
                    }
                } catch (Exception e) {
                    C0103d.m24387b("openLogicalChannel error:" + e.getMessage());
                    return false;
                }
            }
            return this.f75g != null;
        }
        return false;
    }

    @NotNull
    /* renamed from: a */
    public final List<SIMInfo> m24348a(@NotNull List<Reader> list) {
        byte[] m24374c = C0104e.m24374c("A0000000871002FF86FFFF89FFFFFFFF");
        ArrayList arrayList = new ArrayList();
        for (Reader reader : list) {
            SIMInfo sIMInfo = new SIMInfo();
            String name = reader.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "reader.name");
            sIMInfo.setName(name);
            Session openSession = reader.openSession();
            this.f76h = openSession;
            if (openSession != null) {
                try {
                    if (this.f75g != null) {
                        Channel channel = this.f75g;
                        if (channel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!channel.isClosed()) {
                            Channel channel2 = this.f75g;
                            if (channel2 != null) {
                                channel2.close();
                            }
                            this.f75g = null;
                        }
                    }
                    Session session = this.f76h;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(m24374c) : null;
                    this.f75g = openLogicalChannel;
                    if (openLogicalChannel == null) {
                        if (openLogicalChannel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (openLogicalChannel.isClosed()) {
                            sIMInfo.set5G(false);
                        }
                    }
                    Channel channel3 = this.f75g;
                    if (channel3 == null) {
                        Intrinsics.throwNpe();
                    }
                    byte[] result = channel3.transmit(C0104e.m24374c("00a4000c025FC0"));
                    StringBuilder sb = new StringBuilder();
                    sb.append("check 5g response:");
                    Intrinsics.checkExpressionValueIsNotNull(result, "result");
                    sb.append(C0104e.m24368h(result));
                    C0103d.m24388a(sb.toString());
                    if (C0104e.m24371e(result)) {
                        sIMInfo.set5G(true);
                    }
                    m24336f();
                } catch (Exception unused) {
                    sIMInfo.set5G(false);
                }
            }
            arrayList.add(sIMInfo);
        }
        return arrayList;
    }

    /* renamed from: a */
    public final void m24347a(@NotNull List<Reader> list, @NotNull List<SIMInfo> list2) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            Session openSession = list.get(i).openSession();
            this.f76h = openSession;
            if (openSession != null) {
                try {
                    C0103d.m24388a("打开通道:" + C0104e.m24368h(C0099c.f45l.m24422a()));
                    Session session = this.f76h;
                    Channel openLogicalChannel = session != null ? session.openLogicalChannel(C0099c.f45l.m24422a()) : null;
                    this.f75g = openLogicalChannel;
                    if (openLogicalChannel != null) {
                        if (openLogicalChannel == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!openLogicalChannel.isClosed()) {
                            Channel channel = this.f75g;
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
                            m24336f();
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

    /* renamed from: a */
    public final void m24346a(@NotNull Timer timer) {
        this.f79k = timer;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    /* renamed from: a */
    public synchronized void mo24350a(@Nullable ConnectListener connectListener) {
        if (connectListener != null) {
            this.f80l = connectListener;
        }
        SEService sEService = this.f72d;
        if (sEService != null) {
            if (sEService == null) {
                Intrinsics.throwNpe();
            }
            if (sEService.isConnected()) {
                if (connectListener != null) {
                    connectListener.finish(0);
                }
                m24335g();
            }
        }
        this.f72d = new SEService(this.f73e, this);
        Timer timer = kotlin.concurrent.Timer.timer("", false);
        timer.scheduleAtFixedRate(new C0107a(connectListener), 2000L, 2000L);
        this.f79k = timer;
    }

    @Override // com.p201hb.omapi.union.sim.core.BaseSmartCard
    @NotNull
    /* renamed from: a */
    public byte[] mo24345a(@NonNull @NotNull byte[] bArr) {
        if (!this.f74f) {
            mo24350a((ConnectListener) null);
        }
        C0103d.m24388a("sendApdu" + C0104e.m24368h(bArr));
        return m24339c(bArr);
    }
}
