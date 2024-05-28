package com.networkbench.agent.impl.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.p243c.C6309j;
import com.networkbench.agent.impl.p243c.p244a.C6250b;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p244a.C6259j;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6642k {

    /* renamed from: a */
    public static final int f17213a = 20;

    /* renamed from: b */
    public static final int f17214b = 29;

    /* renamed from: c */
    private static final int f17215c = -1;

    /* renamed from: a */
    public static boolean m8922a(int i) {
        return i >= 8;
    }

    /* renamed from: b */
    public static boolean m8913b(int i) {
        return i >= 400 || i == -1 || i == 0;
    }

    /* renamed from: a */
    public static boolean m8920a(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                C6638h.f17124y.mo10115e("couldn't get connectivity manager");
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m8912b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return activeNetworkInfo.getType() == 1;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: g */
    private static boolean m8900g(Context context) {
        if (Build.VERSION.SDK_INT >= 30) {
            if (context.getPackageManager().checkPermission("android.permission.READ_PHONE_STATE", NBSAgent.getApplicationInformation().getPackageId()) == 0) {
                C6638h.f17124y.mo10122a("isGetNetworkTypePermission get READ_PHONE_STATE permission");
                return true;
            }
            return false;
        }
        return true;
    }

    /* renamed from: c */
    public static int m8908c(Context context) {
        TelephonyManager telephonyManager;
        if (m8900g(context) && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
            return telephonyManager.getNetworkType();
        }
        return 0;
    }

    /* renamed from: d */
    public static int m8906d(Context context) {
        if (m8920a(context) && m8900g(context)) {
            if (m8912b(context)) {
                return 1;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                int networkType = telephonyManager.getNetworkType();
                if (networkType != 20) {
                    switch (networkType) {
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return 2;
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                        case 14:
                        case 15:
                            return 3;
                        case 13:
                            return m8919a(context, networkType) == 20 ? 5 : 4;
                        default:
                            return 0;
                    }
                }
                return 5;
            }
            return 0;
        }
        return 0;
    }

    /* renamed from: a */
    public static int m8919a(Context context, int i) {
        return i == 13 ? m8911b(context, i) : i;
    }

    /* renamed from: b */
    private static int m8911b(Context context, int i) {
        ServiceState serviceState;
        if (Build.VERSION.SDK_INT < 29 || context.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0) {
            return i;
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            int m8914b = m8914b();
            if (m8914b == -1) {
                serviceState = telephonyManager.getServiceState();
            } else {
                Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getServiceStateForSubscriber", Integer.TYPE);
                declaredMethod.setAccessible(true);
                ServiceState serviceState2 = (ServiceState) declaredMethod.invoke(telephonyManager, Integer.valueOf(m8914b));
                serviceState = serviceState2 == null ? telephonyManager.getServiceState() : serviceState2;
            }
            if (serviceState != null) {
                if (m8899g(serviceState.toString())) {
                    return 20;
                }
                return i;
            }
            return i;
        } catch (Throwable th) {
            th.printStackTrace();
            return i;
        }
    }

    /* renamed from: b */
    private static int m8914b() {
        if (Build.VERSION.SDK_INT >= 24) {
            return SubscriptionManager.getDefaultDataSubscriptionId();
        }
        return -1;
    }

    /* renamed from: g */
    private static boolean m8899g(String str) {
        return !TextUtils.isEmpty(str) && (str.contains("nrState=NOT_RESTRICTED") || str.contains("nrState=CONNECTED"));
    }

    /* renamed from: h */
    private static int m8898h(Context context) {
        if (m8900g(context) && !m8912b(context)) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null) {
                return 0;
            }
            return telephonyManager.getNetworkType();
        }
        return -1;
    }

    /* renamed from: e */
    public static String m8904e(Context context) {
        int m8898h = m8898h(context);
        if (m8898h != -1) {
            if (m8898h != 20) {
                switch (m8898h) {
                    case 1:
                        return "GPRS";
                    case 2:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case 4:
                        return "CDMA";
                    case 5:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "iDen";
                    case 12:
                        return "EVDO_B";
                    case 13:
                        return "LTE";
                    case 14:
                        return "EHRPD";
                    case 15:
                        return "HSPAP";
                    default:
                        return "UNKNOWN";
                }
            }
            return "NR";
        }
        return "WIFI";
    }

    /* renamed from: a */
    public static int m8917a(String str) {
        try {
            if (!Harvest.isHttp_network_enabled() || TextUtils.isEmpty(str) || C6653u.m8708e()) {
                return -1;
            }
            long currentTimeMillis = System.currentTimeMillis();
            InetAddress.getAllByName(str);
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            if (m8922a(currentTimeMillis2)) {
                return currentTimeMillis2;
            }
            return -1;
        } catch (Exception unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public static String m8909b(String str) {
        for (List<String> list : C6648q.f17232b.keySet()) {
            if (list != null && list.contains(str)) {
                String str2 = C6648q.f17232b.get(list);
                C6648q.f17232b.remove(list);
                return str2;
            }
        }
        return str;
    }

    /* renamed from: a */
    public static void m8916a(String str, String str2, int i, int i2) {
        C6309j c6309j = new C6309j();
        c6309j.m10497a(str2);
        c6309j.m10494b(str);
        c6309j.m10495b(i2);
        c6309j.m10492c(i);
        c6309j.m10498a(0);
        if (C6640i.m8959a(i2)) {
            c6309j.mo9222a(true);
        }
        C6648q.m8782a(c6309j);
    }

    /* renamed from: a */
    public static void m8915a(String str, boolean z, String str2, IOException iOException) throws IOException {
        TextUtils.isEmpty(str);
        throw iOException;
    }

    /* renamed from: f */
    public static JsonArray m8902f(Context context) {
        JsonArray jsonArray = new JsonArray();
        if (m8906d(context) == 1) {
            jsonArray.add(new JsonPrimitive(""));
        } else {
            jsonArray.add(new JsonPrimitive(NBSAgent.getActiveNetworkCarrier()));
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m8906d(context))));
        jsonArray.add(new JsonPrimitive(m8904e(context)));
        String m8964v = C6638h.m8963w().m8964v();
        if (m8964v == null) {
            m8964v = "";
        }
        jsonArray.add(new JsonPrimitive(m8964v));
        return jsonArray;
    }

    /* renamed from: a */
    public static JsonArray m8923a() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(NBSAgent.getApplicationInformation().getAppVersion()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getVersion()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getBuildId()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getApplicationInformation().getChannelId()));
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("buildId is" + NBSAgent.getBuildId());
        InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
        interfaceC6393e2.mo10122a("appinfo is" + jsonArray.toString());
        return jsonArray;
    }

    /* renamed from: c */
    public static JsonArray m8907c(String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(NBSAgent.getApplicationInformation().getAppVersion()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getVersion()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getBuildId()));
        jsonArray.add(new JsonPrimitive(NBSAgent.getApplicationInformation().getChannelId()));
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive(str));
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("buildId is " + NBSAgent.getBuildId());
        InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
        interfaceC6393e2.mo10122a("appinfo is" + jsonArray.toString());
        InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
        interfaceC6393e3.mo10122a("crashType" + str);
        return jsonArray;
    }

    /* renamed from: a */
    public static void m8918a(C6410a c6410a) {
        m8910b(c6410a);
        C6648q.m8781a(c6410a);
    }

    /* renamed from: b */
    public static void m8910b(C6410a c6410a) {
        if (m8905d(c6410a.m10060d())) {
            return;
        }
        C6255f.m10813a(c6410a);
        C6259j.m10766a(c6410a);
        C6250b.m10857a(c6410a);
        NBSTraceEngine.notifyObserverAddNetworkToSegment(c6410a);
    }

    /* renamed from: d */
    public static boolean m8905d(String str) {
        return !TextUtils.isEmpty(str) && str.contains("networkbench.com");
    }

    /* renamed from: e */
    public static boolean m8903e(String str) {
        HarvestConfiguration m9150m;
        if (str == null) {
            return true;
        }
        NBSAndroidAgentImpl impl = NBSAgent.getImpl();
        if (impl == null || (m9150m = impl.m9150m()) == null) {
            return false;
        }
        return !C6652t.m8758b(str, m9150m.getUrlFilterMode(), m9150m.getUrlRules());
    }

    /* renamed from: a */
    public static boolean m8921a(int i, String str) {
        HarvestConfiguration m9150m;
        NBSAndroidAgentImpl impl = NBSAgent.getImpl();
        return impl != null && (m9150m = impl.m9150m()) != null && m8913b(i) && C6652t.m8759a(str, i, m9150m.getIgnoreErrRules());
    }

    /* renamed from: f */
    public static Bitmap m8901f(String str) throws Throwable {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            if (str == null) {
                throw new IllegalArgumentException("loadBitmap url is null");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            return BitmapFactory.decodeStream(httpURLConnection.getInputStream());
        }
        throw new IllegalStateException("run network action in ui thread");
    }
}
