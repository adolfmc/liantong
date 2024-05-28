package com.networkbench.agent.impl.plugin.traceroute;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.traceroute.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6598b {

    /* renamed from: a */
    protected static InterfaceC6393e f16915a = C6394f.m10150a();

    /* renamed from: d */
    private static final String f16916d = "traceroute to";

    /* renamed from: b */
    int f16917b = -1;

    /* renamed from: c */
    String f16918c = "";

    /* renamed from: a */
    public String m9292a() {
        return this.f16918c;
    }

    /* renamed from: b */
    public int m9291b() {
        return this.f16917b;
    }

    /* renamed from: c */
    public String m9290c() {
        String str = "";
        try {
            if (this.f16918c.contains("traceroute to")) {
                String str2 = this.f16918c.split("\n")[0];
                InterfaceC6393e interfaceC6393e = f16915a;
                interfaceC6393e.mo10122a("hostInfo:" + str2);
                str = str2.substring(str2.indexOf("(") + 1, str2.indexOf(")"));
            }
        } catch (Exception e) {
            f16915a.mo10121a("parseIpFromResult error", e);
        }
        return str == null ? "" : str;
    }

    /* renamed from: d */
    public ArrayList<String> m9289d() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            String[] split = this.f16918c.split("\n");
            if (split.length > 1) {
                for (int i = 1; i < split.length; i++) {
                    arrayList.add(split[i]);
                }
            }
        } catch (Exception e) {
            f16915a.mo10121a("parseDetailFromResult error", e);
        }
        return arrayList;
    }
}
