package com.networkbench.agent.impl.plugin.p273d;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.p272c.C6543a;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.d.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6555b {

    /* renamed from: a */
    protected static final InterfaceC6393e f16758a = new C6395g("PingManager");

    /* renamed from: e */
    private static final String f16759e = "\\d+ bytes from (\\d+\\.\\d+.\\d+.\\d+):.*";

    /* renamed from: f */
    private static final String f16760f = ".*\\((\\d+\\.\\d+\\.\\d+\\.\\d+).*";

    /* renamed from: b */
    List<String> f16761b;

    /* renamed from: c */
    List<String> f16762c;

    /* renamed from: d */
    protected int f16763d;

    /* renamed from: a */
    private void m9431a(List<String> list) {
    }

    /* renamed from: a */
    public void m9432a(String str, Map<String, String> map, int i) throws IOException {
        try {
            this.f16763d = i;
            Process start = new ProcessBuilder(m9433a(str, map)).start();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(start.getErrorStream()));
            this.f16761b = m9435a(bufferedReader);
            this.f16762c = m9435a(bufferedReader2);
            bufferedReader.close();
            bufferedReader2.close();
            C6396h.m10126p("doPingCommand normalInfo " + this.f16761b.size());
            C6396h.m10126p("doPingCommand errorInfo " + this.f16762c.size());
            start.destroy();
        } catch (Throwable th) {
            C6396h.m10126p("doPingCommand " + th.getMessage());
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public String m9437a() throws Throwable {
        List<String> list = this.f16762c;
        if (list != null) {
            Iterator<String> it = list.iterator();
            if (it.hasNext()) {
                return it.next();
            }
        }
        List<String> list2 = this.f16762c;
        return (((list2 == null || list2.size() == 0) && m9427c() > 0 && m9425d() == 0) || m9427c() != m9425d()) ? "timeout" : "";
    }

    /* renamed from: b */
    public String m9430b() {
        String str;
        List<String> list = this.f16761b;
        if (list == null || list.size() <= 0 || (str = this.f16761b.get(0)) == null || !str.toLowerCase().startsWith("ping")) {
            return "";
        }
        Matcher matcher = Pattern.compile(".*\\((\\d+\\.\\d+\\.\\d+\\.\\d+).*").matcher(str);
        if (matcher.find()) {
            String group2 = matcher.group(1);
            return TextUtils.isEmpty(group2) ? "" : group2;
        }
        return "";
    }

    /* renamed from: a */
    private List<String> m9435a(BufferedReader bufferedReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
    }

    /* renamed from: a */
    private List<String> m9433a(String str, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("ping");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(entry.getKey().toString());
            arrayList.add(entry.getValue().toString());
            C6396h.m10126p("combineArgs  combineArgs : ping  " + entry.getKey().toString() + " " + entry.getValue().toString());
        }
        arrayList.add(str);
        return arrayList;
    }

    /* renamed from: c */
    public int m9427c() throws Throwable {
        return this.f16763d;
    }

    /* renamed from: d */
    public int m9425d() throws Throwable {
        return Integer.valueOf(m9429b(1)).intValue();
    }

    /* renamed from: e */
    public String m9424e() throws Throwable {
        return m9429b(2).replace("%", "");
    }

    /* renamed from: f */
    public double[] m9423f() throws Throwable {
        if (this.f16761b.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str : this.f16761b) {
            if (m9434a(str) && str != null && !str.equals("")) {
                arrayList.add(Double.valueOf(str.split("time=")[1].replace("ms", "").trim()));
            }
        }
        double[] dArr = new double[arrayList.size()];
        int i = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            dArr[i] = ((Double) it.next()).doubleValue();
            i++;
        }
        return dArr;
    }

    /* renamed from: g */
    public JsonArray m9422g() throws Throwable {
        JsonArray jsonArray = new JsonArray();
        List<String> list = this.f16761b;
        if (list == null || list.size() <= 0) {
            return m9415n();
        }
        int i = 0;
        for (String str : this.f16761b) {
            if (m9434a(str) && str != null && !str.equals("")) {
                C6554a c6554a = new C6554a();
                c6554a.f16754a = Integer.valueOf(str.split(" ")[0].trim()).intValue();
                c6554a.f16755b = Integer.valueOf(str.split("icmp_seq=")[1].split(" ")[0]).intValue();
                int i2 = c6554a.f16755b - i;
                for (int i3 = 1; i3 < i2; i3++) {
                    jsonArray.add(m9436a(i + i3).asJsonArray());
                }
                i = c6554a.f16755b;
                c6554a.f16756c = Integer.valueOf(str.split("ttl=")[1].split(" ")[0]).intValue();
                c6554a.f16757d = Float.valueOf(str.split("time=")[1].replace("ms", " ").trim()).floatValue();
                c6554a.m9438a();
                jsonArray.add(c6554a.asJsonArray());
            }
        }
        for (int i4 = i + 1; i4 < m9427c() + 1; i4++) {
            jsonArray.add(m9436a(i4).asJsonArray());
        }
        return jsonArray;
    }

    /* renamed from: a */
    private C6554a m9436a(int i) {
        C6554a c6554a = new C6554a();
        c6554a.f16755b = i;
        return c6554a;
    }

    /* renamed from: n */
    private JsonArray m9415n() {
        JsonArray jsonArray = new JsonArray();
        int i = 0;
        while (i < this.f16763d) {
            i++;
            jsonArray.add(m9436a(i).asJsonArray());
        }
        return jsonArray;
    }

    /* renamed from: h */
    public float m9421h() throws Throwable {
        float f = 0.0f;
        for (double d : m9423f()) {
            f = (float) (f + d);
        }
        return f;
    }

    /* renamed from: i */
    public String m9420i() throws Throwable {
        if (this.f16761b.size() <= 0) {
            return "";
        }
        String str = "";
        for (String str2 : this.f16761b) {
            if (m9434a(str2)) {
                str = str2;
            }
        }
        return (str == null || str.equals("")) ? "" : str.split("time=")[1].replace("ms", "").trim();
    }

    /* renamed from: a */
    private boolean m9434a(String str) {
        return !TextUtils.isEmpty(str) && str.contains("bytes from") && str.contains("icmp_seq=") && str.contains("ttl=") && str.contains("time=");
    }

    /* renamed from: b */
    private String m9429b(int i) throws C6632b {
        if (this.f16761b.size() == 0) {
            throw new C6632b("no normal ping info");
        }
        if (m9413p()) {
            return m9414o()[i].trim().split(" ")[0];
        }
        throw new C6632b("warning not find packet num");
    }

    /* renamed from: o */
    private String[] m9414o() {
        return m9426c(this.f16761b.size() - 2).split(",");
    }

    /* renamed from: p */
    private boolean m9413p() {
        String m9426c = m9426c(this.f16761b.size() - 2);
        return m9426c.contains("packets transmitted") && m9426c.contains("received");
    }

    /* renamed from: j */
    public String m9419j() throws Throwable {
        return !m9412q() ? "0.0" : m9428b(m9426c(this.f16761b.size() - 1))[1];
    }

    /* renamed from: k */
    public String m9418k() {
        return !m9412q() ? "0.0" : m9428b(m9426c(this.f16761b.size() - 1))[0].trim();
    }

    /* renamed from: l */
    public String m9417l() {
        return !m9412q() ? "0.0" : m9428b(m9426c(this.f16761b.size() - 1))[2];
    }

    /* renamed from: m */
    public String m9416m() throws Throwable {
        double[] m9423f = m9423f();
        if (m9423f == null || m9423f.length <= 0) {
            return "0.0";
        }
        C6543a c6543a = new C6543a();
        return c6543a.m9464d(m9423f) + "";
    }

    /* renamed from: c */
    private String m9426c(int i) {
        return this.f16761b.get(i);
    }

    /* renamed from: b */
    private String[] m9428b(String str) {
        return str.split("=")[1].split("/");
    }

    /* renamed from: q */
    private boolean m9412q() {
        if (this.f16761b.size() == 0) {
            return false;
        }
        List<String> list = this.f16761b;
        return list.get(list.size() - 1).contains("min/avg/max/");
    }
}
