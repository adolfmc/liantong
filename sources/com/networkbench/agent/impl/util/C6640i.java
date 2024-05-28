package com.networkbench.agent.impl.util;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6640i {

    /* renamed from: a */
    public static String f17186a;

    /* renamed from: b */
    private static final InterfaceC6393e f17187b = C6394f.m10150a();

    /* renamed from: a */
    public static boolean m8959a(int i) {
        return i == 80 || i == 443;
    }

    private C6640i() {
    }

    /* renamed from: a */
    public static Map<String, Object> m8956a(Map<String, List<String>> map) {
        List<String> list;
        TreeMap treeMap = new TreeMap();
        if (map != null) {
            for (String str : map.keySet()) {
                if (str != null && (list = map.get(str)) != null && list.size() > 0) {
                    treeMap.put(str, list.get(0));
                }
            }
        }
        return treeMap;
    }

    /* renamed from: a */
    public static String m8958a(Serializable serializable) {
        if (serializable == null) {
            return "";
        }
        try {
            String[] split = serializable.toString().split("/");
            if (split == null || split.length != 2) {
                return "";
            }
            if (TextUtils.isEmpty(split[0])) {
                return split[1] != null ? split[1].contains(":") ? split[1].split(":")[0] : split[1] : "";
            }
            return split[0];
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m8954b(Serializable serializable) {
        if (serializable == null) {
            return "";
        }
        try {
            String[] split = serializable.toString().split("/");
            if (split == null || split.length != 2 || TextUtils.isEmpty(split[1])) {
                return "";
            }
            if (split[1].contains(".")) {
                return split[1].contains(":") ? split[1].split(":")[0] : split[1];
            }
            return split[1];
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m8957a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return new URL(str).getHost();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f17187b;
            interfaceC6393e.mo10122a("getHostFromUrl is error:" + e.getMessage() + ", url:" + str);
            return "";
        }
    }

    /* renamed from: a */
    public static String m8955a(InetAddress[] inetAddressArr) {
        StringBuilder sb = new StringBuilder();
        if (inetAddressArr != null && inetAddressArr.length > 0) {
            for (InetAddress inetAddress : inetAddressArr) {
                sb.append(inetAddress.toString());
                sb.append(";");
            }
        }
        return sb.toString();
    }
}
