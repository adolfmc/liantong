package com.xiaomi.push;

import android.net.NetworkInfo;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.xiaomi.push.av */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11175av {

    /* renamed from: a */
    private final NetworkInfo f21556a;

    /* renamed from: a */
    private final ConcurrentHashMap<String, Object> f21557a = new ConcurrentHashMap<>();

    public C11175av(NetworkInfo networkInfo) {
        this.f21556a = networkInfo;
    }

    /* renamed from: a */
    private <T> T m4821a(String str) {
        if (!this.f21557a.containsKey(str)) {
            synchronized (str) {
                if (!this.f21557a.contains(str)) {
                    Object obj = null;
                    char c = 65535;
                    switch (str.hashCode()) {
                        case -830707388:
                            if (str.equals("getSubtype")) {
                                c = 2;
                                break;
                            }
                            break;
                        case -75106384:
                            if (str.equals("getType")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -66906641:
                            if (str.equals("getSubtypeName")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 599209215:
                            if (str.equals("isConnected")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 711698955:
                            if (str.equals("getDetailedState")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 1401392731:
                            if (str.equals("getTypeName")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 1965583067:
                            if (str.equals("getState")) {
                                c = 5;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            obj = Integer.valueOf(this.f21556a.getType());
                            break;
                        case 1:
                            obj = this.f21556a.getTypeName();
                            break;
                        case 2:
                            obj = Integer.valueOf(this.f21556a.getSubtype());
                            break;
                        case 3:
                            obj = this.f21556a.getSubtypeName();
                            break;
                        case 4:
                            obj = Boolean.valueOf(this.f21556a.isConnected());
                            break;
                        case 5:
                            obj = this.f21556a.getState();
                            break;
                        case 6:
                            obj = this.f21556a.getDetailedState();
                            break;
                    }
                    if (obj != null) {
                        this.f21557a.put(str, obj);
                    }
                }
            }
        }
        return (T) this.f21557a.get(str);
    }

    /* renamed from: a */
    public int m4826a() {
        return ((Integer) m4821a("getType")).intValue();
    }

    /* renamed from: b */
    public int m4820b() {
        return ((Integer) m4821a("getSubtype")).intValue();
    }

    /* renamed from: a */
    public String m4823a() {
        return (String) m4821a("getTypeName");
    }

    /* renamed from: b */
    public String m4819b() {
        return (String) m4821a("getSubtypeName");
    }

    /* renamed from: a */
    public boolean m4822a() {
        return ((Boolean) m4821a("isConnected")).booleanValue();
    }

    /* renamed from: a */
    public NetworkInfo.State m4824a() {
        return (NetworkInfo.State) m4821a("getState");
    }

    /* renamed from: a */
    public NetworkInfo.DetailedState m4825a() {
        return (NetworkInfo.DetailedState) m4821a("getDetailedState");
    }
}
