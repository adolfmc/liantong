package com.networkbench.agent.impl.plugin.p275f.p276a;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6572a {

    /* renamed from: a */
    private static final String f16820a = "<iframe src=\"";

    /* renamed from: b */
    private static final String f16821b = ".php";

    /* renamed from: c */
    private static final String f16822c = "<tr>";

    /* renamed from: d */
    private static final String f16823d = "</table>";

    /* renamed from: d */
    private static String m9346d() throws IOException {
        int indexOf;
        int i;
        int indexOf2;
        String m9315a = C6588g.m9315a("http://ns.pbt.cloudxns.net/fast_tools/fetch_ldns_diag_client.php");
        if (m9315a != null && (indexOf = m9315a.indexOf("<iframe src=\"")) > 0 && (indexOf2 = m9315a.indexOf(".php", (i = indexOf + 13))) > 0) {
            return m9315a.substring(i, indexOf2 + 4);
        }
        return null;
    }

    /* renamed from: a */
    public static String m9349a() {
        String str;
        String str2 = null;
        try {
            str = m9346d();
        } catch (IOException e) {
            e.printStackTrace();
            str = null;
        }
        if (str == null) {
            return "get fetch url failed";
        }
        try {
            str2 = C6588g.m9315a(str);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (str2 == null) {
            return "check server error";
        }
        int indexOf = str2.indexOf("<tr>") + 4;
        return str2.substring(indexOf, str2.indexOf("</table>", indexOf)).replaceAll("</", "<").replaceAll("<tr>", "").replaceAll("<th>", "").replaceAll("<td>", "").replaceAll("<td>", "").replaceAll("<td width=\"128\" >", "").replaceAll("<table>", "").replaceAll("<p class=\"result\">", "").replaceAll("<pre>", "").replaceAll("<p>", "").replaceAll("\n\n", "\n").trim();
    }

    /* renamed from: e */
    private static InetAddress[] m9345e() {
        String hostAddress;
        try {
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("getprop").getInputStream()));
            ArrayList arrayList = new ArrayList(5);
            while (true) {
                String readLine = lineNumberReader.readLine();
                if (readLine == null) {
                    break;
                }
                int indexOf = readLine.indexOf("]: [");
                if (indexOf != -1) {
                    String substring = readLine.substring(1, indexOf);
                    String substring2 = readLine.substring(indexOf + 4, readLine.length() - 1);
                    if (substring.endsWith(".dns") || substring.endsWith(".dns1") || substring.endsWith(".dns2") || substring.endsWith(".dns3") || substring.endsWith(".dns4")) {
                        InetAddress byName = InetAddress.getByName(substring2);
                        if (byName != null && (hostAddress = byName.getHostAddress()) != null && hostAddress.length() != 0) {
                            arrayList.add(byName);
                        }
                    }
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
            return null;
        } catch (IOException e) {
            Logger.getLogger("getByCommand error" + e.getMessage());
            return null;
        }
    }

    /* renamed from: f */
    private static InetAddress[] m9344f() {
        InetAddress byName;
        String hostAddress;
        try {
            Method method = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            ArrayList arrayList = new ArrayList(5);
            String[] strArr = {"net.dns1", "net.dns2", "net.dns3", "net.dns4"};
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                String str = (String) method.invoke(null, strArr[i]);
                if (str != null && str.length() != 0 && (byName = InetAddress.getByName(str)) != null && (hostAddress = byName.getHostAddress()) != null && hostAddress.length() != 0 && !arrayList.contains(byName)) {
                    arrayList.add(byName);
                }
            }
            if (arrayList.size() > 0) {
                return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
            }
        } catch (Exception e) {
            Logger.getLogger("AndroidDnsServer").log(Level.WARNING, "Exception in findDNSByReflection", (Throwable) e);
        }
        return null;
    }

    /* renamed from: b */
    public static String[] m9348b() {
        InetAddress[] m9344f = m9344f();
        if (m9344f == null && (m9344f = m9345e()) == null) {
            return new String[]{"119.29.29.29"};
        }
        String[] strArr = new String[m9344f.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = m9344f[i].toString();
            if (strArr[i].indexOf(47) == 0) {
                strArr[i] = strArr[i].substring(1);
            }
        }
        return strArr;
    }

    /* renamed from: c */
    public static String m9347c() {
        InetAddress[] m9345e;
        if (m9344f() != null || (m9345e = m9345e()) == null || m9345e.length <= 0) {
            return "";
        }
        String inetAddress = m9345e[0].toString();
        return inetAddress.indexOf(47) == 0 ? inetAddress.substring(1) : "";
    }
}
