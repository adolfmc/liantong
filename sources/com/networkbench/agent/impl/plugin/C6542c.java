package com.networkbench.agent.impl.plugin;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.RouteInfo;
import android.os.Build;
import com.networkbench.agent.impl.p254f.C6396h;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6542c {

    /* renamed from: a */
    private static final String[] f16715a = {"8.8.8.8", "8.8.4.4"};

    /* renamed from: b */
    private static final String f16716b = "]: [";

    /* renamed from: c */
    private Context f16717c;

    public C6542c(Context context) {
        this.f16717c = context;
    }

    /* renamed from: a */
    public String[] m9473a() {
        String[] m9469c = m9469c();
        if (m9469c == null || m9469c.length <= 0) {
            String[] m9470b = m9470b();
            if (m9470b == null || m9470b.length <= 0) {
                String[] m9468d = m9468d();
                return (m9468d == null || m9468d.length <= 0) ? f16715a : m9468d;
            }
            return m9470b;
        }
        return m9469c;
    }

    /* renamed from: b */
    private String[] m9470b() {
        Network[] allNetworks;
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                ConnectivityManager connectivityManager = (ConnectivityManager) this.f16717c.getSystemService("connectivity");
                if (connectivityManager != null) {
                    for (Network network : connectivityManager.getAllNetworks()) {
                        if (connectivityManager.getNetworkInfo(network).isConnected()) {
                            LinkProperties linkProperties = connectivityManager.getLinkProperties(network);
                            List<InetAddress> dnsServers = linkProperties.getDnsServers();
                            if (m9472a(linkProperties)) {
                                for (InetAddress inetAddress : dnsServers) {
                                    arrayList.add(inetAddress.getHostAddress());
                                }
                            } else {
                                for (InetAddress inetAddress2 : dnsServers) {
                                    arrayList2.add(inetAddress2.getHostAddress());
                                }
                            }
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    arrayList.addAll(arrayList2);
                }
                if (arrayList.size() > 0) {
                    return (String[]) arrayList.toArray(new String[0]);
                }
                return null;
            } catch (Exception e) {
                C6396h.m10131k("Exception detecting DNS servers using ConnectivityManager method" + e.getMessage());
                return null;
            }
        }
        return null;
    }

    /* renamed from: c */
    private String[] m9469c() {
        if (Build.VERSION.SDK_INT < 26) {
            ArrayList arrayList = new ArrayList();
            try {
                Method method = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
                String[] strArr = {"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
                for (int i = 0; i < strArr.length; i++) {
                    String str = (String) method.invoke(null, strArr[i]);
                    if (str != null && ((str.matches("^\\d+(\\.\\d+){3}$") || str.matches("^[0-9a-f]+(:[0-9a-f]*)+:[0-9a-f]+$")) && !arrayList.contains(str))) {
                        arrayList.add(str);
                    }
                }
                if (arrayList.size() > 0) {
                    return (String[]) arrayList.toArray(new String[0]);
                }
            } catch (Exception e) {
                C6396h.m10131k("Exception detecting DNS servers using SystemProperties method" + e.getMessage());
            }
        }
        return null;
    }

    /* renamed from: d */
    private String[] m9468d() {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                Set<String> m9471a = m9471a(new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream())));
                if (m9471a == null || m9471a.size() <= 0) {
                    return null;
                }
                return (String[]) m9471a.toArray(new String[0]);
            } catch (Exception e) {
                C6396h.m10131k("Exception in getServersMethodExec " + e.getMessage());
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private Set<String> m9471a(BufferedReader bufferedReader) throws Exception {
        String hostAddress;
        HashSet hashSet = new HashSet(10);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return hashSet;
            }
            int indexOf = readLine.indexOf("]: [");
            if (indexOf != -1) {
                String substring = readLine.substring(1, indexOf);
                int i = indexOf + 4;
                int length = readLine.length() - 1;
                if (length < i) {
                    C6396h.m10131k("Malformed property detected: \"" + readLine + '\"');
                } else {
                    String substring2 = readLine.substring(i, length);
                    if (!substring2.isEmpty() && (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4"))) {
                        InetAddress byName = InetAddress.getByName(substring2);
                        if (byName != null && (hostAddress = byName.getHostAddress()) != null && hostAddress.length() != 0) {
                            hashSet.add(hostAddress);
                        }
                    }
                }
            }
        }
    }

    @TargetApi(21)
    /* renamed from: a */
    private boolean m9472a(LinkProperties linkProperties) {
        for (RouteInfo routeInfo : linkProperties.getRoutes()) {
            if (routeInfo.isDefaultRoute()) {
                return true;
            }
        }
        return false;
    }
}
