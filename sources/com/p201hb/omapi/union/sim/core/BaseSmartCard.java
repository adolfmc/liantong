package com.p201hb.omapi.union.sim.core;

import androidx.annotation.NonNull;
import com.p201hb.omapi.union.sim.bean.SIMInfo;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\bf\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\fH&J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J\u0012\u0010\u0013\u001a\u00020\u00142\b\b\u0001\u0010\u0015\u001a\u00020\u0014H&J\b\u0010\u0016\u001a\u00020\fH&R\u0018\u0010\u0002\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0005\"\u0004\b\n\u0010\u0007¨\u0006\u0018"}, m1890d2 = {"Lcom/hb/omapi/union/sim/core/BaseSmartCard;", "", "sessionKey", "", "getSessionKey", "()Ljava/lang/String;", "setSessionKey", "(Ljava/lang/String;)V", "simType", "getSimType", "setSimType", "bindService", "", "listener", "Lcom/hb/omapi/union/sim/listener/ConnectListener;", "closeChannel", "getSIM", "", "Lcom/hb/omapi/union/sim/bean/SIMInfo;", "sendApdu", "", "command", "unbindService", "Companion", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: a.a.a.a.a.c.b  reason: from Kotlin metadata */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface BaseSmartCard {

    /* renamed from: a */
    public static final C0101a f46a = C0101a.f51c;

    /* renamed from: b */
    public static final int f47b = 0;

    /* renamed from: c */
    public static final int f48c = -1;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.a.a.a.a.c.b$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public static final class C0101a {

        /* renamed from: a */
        public static final int f49a = 0;

        /* renamed from: b */
        public static final int f50b = -1;

        /* renamed from: c */
        public static final /* synthetic */ C0101a f51c = new C0101a();
    }

    @NotNull
    /* renamed from: a */
    List<SIMInfo> mo24351a();

    /* renamed from: a */
    void mo24350a(@Nullable ConnectListener connectListener);

    /* renamed from: a */
    void mo24349a(@NotNull String str);

    @NotNull
    /* renamed from: a */
    byte[] mo24345a(@NonNull @NotNull byte[] bArr);

    /* renamed from: b */
    void mo24344b();

    /* renamed from: b */
    void mo24342b(@NotNull String str);

    @NotNull
    /* renamed from: c */
    String mo24340c();

    /* renamed from: d */
    void mo24338d();

    @NotNull
    /* renamed from: e */
    String mo24337e();
}
