package com.p201hb.omapi.union.sim;

import android.content.Context;
import android.os.Build;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import com.p201hb.omapi.union.sim.bean.SIMInfo;
import com.p201hb.omapi.union.sim.core.BaseSmartCard;
import com.p201hb.omapi.union.sim.core.SmartCardGoogle;
import com.p201hb.omapi.union.sim.core.SmartCardSimalliance;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p001a.p002a.p003a.p004a.p005a.p007c.C0100a;
import p001a.p002a.p003a.p004a.p005a.p007c.C0103d;
import p001a.p002a.p003a.p004a.p005a.p007c.C0104e;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;
import p001a.p002a.p003a.p004a.p005a.p008d.C0111d;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, m1890d2 = {"Lcom/hb/omapi/union/sim/SmartCard;", "", "()V", "Companion", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.hb.omapi.union.sim.SmartCard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class SmartCard {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static String aesKey;
    public static boolean bsmartCardJarReady;
    @NotNull
    public static Context context;
    @Nullable
    public static BaseSmartCard smartCardInterface;
    @Nullable
    public static BaseSmartCard smartCardJar;

    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004J\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020&J\u001e\u0010'\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\"\u0010*\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010+j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`,J\u0016\u0010-\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010.\u001a\u00020\u001fJ*\u0010/\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010+j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0001`,2\u0006\u0010 \u001a\u00020\u0004J\f\u00100\u001a\b\u0012\u0004\u0012\u00020201J\u0006\u00103\u001a\u00020\u0004J\u000e\u00104\u001a\u00020#2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u00105\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0004J\u0016\u00108\u001a\u00020\u001f2\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004J\u000e\u0010;\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020\u0004J\u001e\u0010?\u001a\u00020\u001f2\u0006\u0010(\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0004J\u000e\u0010@\u001a\u00020\u001f2\u0006\u0010<\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001a¨\u0006A"}, m1890d2 = {"Lcom/hb/omapi/union/sim/SmartCard$Companion;", "", "()V", "aesKey", "", "getAesKey", "()Ljava/lang/String;", "setAesKey", "(Ljava/lang/String;)V", "bsmartCardJarReady", "", "getBsmartCardJarReady", "()Z", "setBsmartCardJarReady", "(Z)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "smartCardInterface", "Lcom/hb/omapi/union/sim/core/BaseSmartCard;", "getSmartCardInterface", "()Lcom/hb/omapi/union/sim/core/BaseSmartCard;", "setSmartCardInterface", "(Lcom/hb/omapi/union/sim/core/BaseSmartCard;)V", "smartCardJar", "getSmartCardJar", "setSmartCardJar", "appendItem", "", "type", "dataCiphertext", "close", "", "connect", "listener", "Lcom/hb/omapi/union/sim/listener/ConnectListener;", "delSecurityInformation", "recId", "time", "getApplicationInformation", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "getRandom", "size", "getSecurityInformation", "getSupport", "", "Lcom/hb/omapi/union/sim/bean/SIMInfo;", "getVersion", "init", "modifyPassword", "oldPinCipher", "newPinCipher", "resetPassword", "pinCipher", "controlCipher", "setPassword", "ciphertext", "setSIM", "sim", "updateItem", "verifyPassword", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: com.hb.omapi.union.sim.SmartCard$Companion */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static final class Companion {

        /* renamed from: com.hb.omapi.union.sim.SmartCard$Companion$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static final class C4707a implements ConnectListener {
            @Override // com.p201hb.omapi.union.sim.listener.ConnectListener
            public void finish(int i) {
                if (i == 0) {
                    SmartCard.Companion.setBsmartCardJarReady(true);
                }
            }
        }

        public Companion() {
        }

        public final int appendItem(@NotNull String str, @NotNull String str2) {
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str2);
            if (m24324c != null) {
                BaseSmartCard smartCardInterface = SmartCard.Companion.getSmartCardInterface();
                if (smartCardInterface == null) {
                    Intrinsics.throwNpe();
                }
                return C0100a.m24413a(smartCardInterface, C0104e.m24374c(str)[0], m24324c);
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public final void close() {
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            if (smartCardInterface != null) {
                smartCardInterface.mo24344b();
            }
        }

        public final void connect(@NotNull ConnectListener connectListener) {
            BaseSmartCard smartCardJar;
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            if (smartCardInterface != null) {
                smartCardInterface.mo24350a(connectListener);
            }
            if (Build.VERSION.SDK_INT < 28 || (smartCardJar = getSmartCardJar()) == null) {
                return;
            }
            smartCardJar.mo24350a(new C4707a());
        }

        public final int delSecurityInformation(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            if (smartCardInterface == null) {
                Intrinsics.throwNpe();
            }
            return C0100a.m24414a(smartCardInterface, C0104e.m24374c(str)[0], C0104e.m24374c(str2)[0], str3);
        }

        @NotNull
        public final String getAesKey() {
            String str = SmartCard.aesKey;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aesKey");
            }
            return str;
        }

        @NotNull
        public final HashMap<String, Object> getApplicationInformation() {
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            if (smartCardInterface == null) {
                Intrinsics.throwNpe();
            }
            return C0100a.m24416a(smartCardInterface);
        }

        public final boolean getBsmartCardJarReady() {
            return SmartCard.bsmartCardJarReady;
        }

        @NotNull
        public final Context getContext() {
            Context context = SmartCard.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
            }
            return context;
        }

        @NotNull
        public final String getRandom(int i, int i2) {
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            if (smartCardInterface == null) {
                Intrinsics.throwNpe();
            }
            String m24406b = C0100a.m24406b(smartCardInterface);
            if (!"".equals(m24406b) && i == 1) {
                String m24306a = C0111d.f96a.m24306a(m24406b);
                if (m24306a != null) {
                    setAesKey(C0111d.f96a.m24303c(m24306a));
                }
                C0103d.m24388a("sdk AES key:" + getAesKey());
            }
            return m24406b;
        }

        @NotNull
        public final HashMap<String, Object> getSecurityInformation(@NotNull String str) {
            BaseSmartCard smartCardInterface = getSmartCardInterface();
            byte[] m24415a = smartCardInterface != null ? C0100a.m24415a(smartCardInterface, C0104e.m24374c(str)[0]) : null;
            HashMap<String, Object> hashMap = new HashMap<>();
            if (m24415a == null) {
                Intrinsics.throwNpe();
            }
            if (m24415a.length <= 2) {
                hashMap.put("code", Integer.valueOf(ErrCode.Companion.getErrCode(C0104e.m24369g(m24415a))));
                return hashMap;
            }
            hashMap.put("CODE", Integer.valueOf(ErrCode.Companion.getErrCode(C0104e.m24369g(m24415a))));
            String m24323d = C0108a.f89g.m24323d(getAesKey(), C0104e.m24368h(ArraysKt.copyOfRange(m24415a, 0, m24415a.length - 2)));
            if (m24323d != null) {
                hashMap.put("msg", m24323d);
            }
            return hashMap;
        }

        @Nullable
        public final BaseSmartCard getSmartCardInterface() {
            return SmartCard.smartCardInterface;
        }

        @Nullable
        public final BaseSmartCard getSmartCardJar() {
            return SmartCard.smartCardJar;
        }

        @NotNull
        public final List<SIMInfo> getSupport() {
            try {
                BaseSmartCard smartCardInterface = getSmartCardInterface();
                List<SIMInfo> mo24351a = smartCardInterface != null ? smartCardInterface.mo24351a() : null;
                if (mo24351a != null && mo24351a.size() == 0 && !(getSmartCardInterface() instanceof SmartCardSimalliance) && getBsmartCardJarReady()) {
                    BaseSmartCard smartCardJar = getSmartCardJar();
                    mo24351a = smartCardJar != null ? smartCardJar.mo24351a() : null;
                    if (mo24351a == null || mo24351a.size() != 0) {
                        BaseSmartCard smartCardInterface2 = getSmartCardInterface();
                        if (smartCardInterface2 != null) {
                            smartCardInterface2.mo24344b();
                        }
                        setSmartCardInterface(null);
                        setSmartCardInterface(getSmartCardJar());
                        setSmartCardJar(null);
                    }
                }
                if (mo24351a == null) {
                    Intrinsics.throwNpe();
                }
                return mo24351a;
            } catch (Exception e) {
                String message = e.getMessage();
                if (message != null) {
                    C0103d.m24388a(message);
                }
                return new ArrayList();
            }
        }

        @NotNull
        public final String getVersion() {
            return "1.1.1";
        }

        public final void init(@NotNull Context context) {
            C0103d.m24386c("omapi sdk version " + getVersion());
            SmartCard.Companion.setContext(context);
            if (Build.VERSION.SDK_INT >= 28) {
                setSmartCardInterface(new SmartCardGoogle(context));
                setSmartCardJar(new SmartCardSimalliance(context));
                return;
            }
            setSmartCardInterface(new SmartCardSimalliance(context));
        }

        public final int modifyPassword(@NotNull String str, @NotNull String str2) {
            if (getSmartCardInterface() == null) {
                return ErrCode.Companion.getError_BIND_NULL();
            }
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str);
            String m24324c2 = C0108a.f89g.m24324c(getAesKey(), str2);
            if (m24324c != null && m24324c2 != null) {
                if (m24324c.length() >= 6 && m24324c2.length() >= 6) {
                    BaseSmartCard smartCardInterface = getSmartCardInterface();
                    if (smartCardInterface == null) {
                        Intrinsics.throwNpe();
                    }
                    return C0100a.m24411a(smartCardInterface, m24324c, m24324c2);
                }
                return ErrCode.Companion.getError_PSW_FAIL();
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public final int resetPassword(@NotNull String str, @NotNull String str2) {
            if (getSmartCardInterface() == null) {
                return ErrCode.Companion.getError_BIND_NULL();
            }
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str);
            String m24324c2 = C0108a.f89g.m24324c(getAesKey(), str2);
            if (m24324c != null && m24324c2 != null) {
                C0103d.m24388a("control plaintex:" + m24324c2 + "----control flip:" + C0100a.m24408a(m24324c2));
                if (m24324c.length() < 6) {
                    return ErrCode.Companion.getError_PSW_FAIL();
                }
                BaseSmartCard smartCardInterface = getSmartCardInterface();
                if (smartCardInterface == null) {
                    Intrinsics.throwNpe();
                }
                return C0100a.m24410a(smartCardInterface, m24324c, C0104e.m24374c(C0100a.m24408a(m24324c2)));
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public final void setAesKey(@NotNull String str) {
            SmartCard.aesKey = str;
        }

        public final void setBsmartCardJarReady(boolean z) {
            SmartCard.bsmartCardJarReady = z;
        }

        public final void setContext(@NotNull Context context) {
            SmartCard.context = context;
        }

        public final int setPassword(@NotNull String str) {
            if (getSmartCardInterface() == null) {
                return ErrCode.Companion.getError_BIND_NULL();
            }
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str);
            if (m24324c != null) {
                if (m24324c.length() < 6) {
                    return ErrCode.Companion.getError_PSW_FAIL();
                }
                BaseSmartCard smartCardInterface = SmartCard.Companion.getSmartCardInterface();
                if (smartCardInterface == null) {
                    Intrinsics.throwNpe();
                }
                return C0100a.m24402c(smartCardInterface, m24324c);
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public final void setSIM(@NotNull String str) {
            try {
                BaseSmartCard smartCardInterface = getSmartCardInterface();
                if (smartCardInterface != null) {
                    String upperCase = str.toUpperCase();
                    Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                    smartCardInterface.mo24349a(upperCase);
                }
            } catch (Exception e) {
                String message = e.getMessage();
                if (message != null) {
                    C0103d.m24388a(message);
                }
            }
        }

        public final void setSmartCardInterface(@Nullable BaseSmartCard baseSmartCard) {
            SmartCard.smartCardInterface = baseSmartCard;
        }

        public final void setSmartCardJar(@Nullable BaseSmartCard baseSmartCard) {
            SmartCard.smartCardJar = baseSmartCard;
        }

        public final int updateItem(@NotNull String str, @NotNull String str2, @NotNull String str3) {
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str3);
            if (m24324c != null) {
                BaseSmartCard smartCardInterface = SmartCard.Companion.getSmartCardInterface();
                if (smartCardInterface == null) {
                    Intrinsics.throwNpe();
                }
                return C0100a.m24405b(smartCardInterface, C0104e.m24374c(str)[0], C0104e.m24374c(str2)[0], m24324c);
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public final int verifyPassword(@NotNull String str) {
            if (getSmartCardInterface() == null) {
                return ErrCode.Companion.getError_BIND_NULL();
            }
            String m24324c = C0108a.f89g.m24324c(getAesKey(), str);
            if (m24324c != null) {
                if (m24324c.length() < 6) {
                    return ErrCode.Companion.getError_PSW_FAIL();
                }
                BaseSmartCard smartCardInterface = SmartCard.Companion.getSmartCardInterface();
                if (smartCardInterface == null) {
                    Intrinsics.throwNpe();
                }
                return C0100a.m24399e(smartCardInterface, m24324c);
            }
            return ErrCode.Companion.getError_DATA_FAIL();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
