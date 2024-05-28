package com.networkbench.agent.impl.plugin.p270a;

import android.content.Context;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6533c {

    /* renamed from: a */
    private static final InterfaceC6393e f16683a = C6394f.m10150a();

    /* renamed from: c */
    private static C6533c f16684c;

    /* renamed from: b */
    private TelephonyManager f16685b;

    /* renamed from: d */
    private Context f16686d;

    /* renamed from: a */
    private void m9499a(C6531a c6531a, CellInfoCdma cellInfoCdma) {
    }

    /* renamed from: b */
    private int m9490b(int i) {
        if (Integer.MAX_VALUE == i) {
            return 0;
        }
        return i;
    }

    /* renamed from: b */
    private boolean m9491b() {
        return false;
    }

    /* renamed from: a */
    public static C6533c m9505a() {
        if (f16684c == null) {
            synchronized (C6533c.class) {
                if (f16684c == null) {
                    f16684c = new C6533c(C6638h.m8963w().m9076K());
                }
            }
        }
        return f16684c;
    }

    private C6533c(Context context) {
        this.f16685b = (TelephonyManager) context.getSystemService("phone");
        this.f16686d = context;
    }

    /* renamed from: a */
    private boolean m9504a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    public List<C6531a> m9492a(JSONArray jSONArray, int i) {
        ArrayList arrayList = new ArrayList();
        if (m9491b()) {
            List<CellInfo> m9487c = m9487c();
            int i2 = 0;
            InterfaceC6393e interfaceC6393e = f16683a;
            interfaceC6393e.mo10122a("cellInfoTypeName:" + jSONArray + ", numLImit:" + i);
            Iterator<CellInfo> it = m9487c.iterator();
            while (it.hasNext()) {
                CellInfo next = it.next();
                if (!m9501a(next, jSONArray)) {
                    InterfaceC6393e interfaceC6393e2 = f16683a;
                    StringBuilder sb = new StringBuilder();
                    sb.append("cellinfo classname:");
                    sb.append(next == null ? next.getClass().getSimpleName() : "null");
                    interfaceC6393e2.mo10115e(sb.toString());
                } else {
                    if (next.isRegistered()) {
                        i2++;
                    }
                    if (i + i2 > arrayList.size() || next.isRegistered()) {
                        arrayList.add(m9503a(next));
                    }
                }
            }
            m9494a(arrayList);
            return arrayList;
        }
        return arrayList;
    }

    /* renamed from: c */
    private List<CellInfo> m9487c() {
        return this.f16685b.getAllCellInfo();
    }

    /* renamed from: a */
    void m9494a(List<C6531a> list) {
        if (list.size() <= 0) {
            return;
        }
        Collections.sort(list, new Comparator<C6531a>() { // from class: com.networkbench.agent.impl.plugin.a.c.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(C6531a c6531a, C6531a c6531a2) {
                if (c6531a.f16677i == c6531a2.f16677i) {
                    return 0;
                }
                return (c6531a.f16677i == 0 || c6531a2.f16677i == 0 || c6531a.f16677i > c6531a2.f16677i) ? -1 : 1;
            }
        });
    }

    /* renamed from: b */
    public C6531a m9488b(List<String> list) {
        try {
            if (m9491b()) {
                f16683a.mo10122a("getRegisterCellInfo isEnvironmentGood is true");
                List<CellInfo> m9487c = m9487c();
                if (m9487c == null) {
                    return null;
                }
                for (String str : list) {
                    for (CellInfo cellInfo : m9487c) {
                        if (m9502a(cellInfo, str)) {
                            return m9503a(cellInfo);
                        }
                    }
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private C6531a m9503a(CellInfo cellInfo) {
        C6531a c6531a = new C6531a();
        c6531a.f16669a = cellInfo.isRegistered();
        c6531a.f16671c = this.f16685b.getNetworkOperator();
        c6531a.f16670b = C6642k.m8908c(this.f16686d);
        if (cellInfo instanceof CellInfoLte) {
            m9497a(c6531a, (CellInfoLte) cellInfo);
        }
        if (cellInfo instanceof CellInfoGsm) {
            m9498a(c6531a, (CellInfoGsm) cellInfo);
        }
        if (cellInfo instanceof CellInfoCdma) {
            m9499a(c6531a, (CellInfoCdma) cellInfo);
        }
        if (m9504a(18) && (cellInfo instanceof CellInfoWcdma)) {
            m9496a(c6531a, (CellInfoWcdma) cellInfo);
        }
        m9500a(c6531a);
        return c6531a;
    }

    /* renamed from: a */
    private void m9500a(C6531a c6531a) {
        if (!c6531a.f16669a) {
            c6531a.f16671c = "";
        }
        c6531a.f16672d = m9490b(c6531a.f16672d);
        c6531a.f16673e = m9490b(c6531a.f16673e);
        c6531a.f16674f = m9490b(c6531a.f16674f);
        c6531a.f16675g = m9490b(c6531a.f16675g);
        c6531a.f16676h = m9490b(c6531a.f16676h);
        c6531a.f16677i = m9490b(c6531a.f16677i);
        c6531a.f16678j = m9490b(c6531a.f16678j);
    }

    /* renamed from: a */
    private boolean m9502a(CellInfo cellInfo, String str) {
        if (!TextUtils.isEmpty(str) && cellInfo.isRegistered()) {
            return m9489b(cellInfo, str);
        }
        return false;
    }

    /* renamed from: a */
    private boolean m9501a(CellInfo cellInfo, JSONArray jSONArray) {
        if (jSONArray.length() <= 0 || cellInfo == null) {
            return false;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            if (cellInfo.getClass().getSimpleName().equals(String.valueOf(jSONArray.opt(i)))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private boolean m9489b(CellInfo cellInfo, String str) {
        return cellInfo != null && cellInfo.getClass().getSimpleName().equals(str);
    }

    /* renamed from: a */
    void m9497a(C6531a c6531a, CellInfoLte cellInfoLte) {
        c6531a.f16672d = cellInfoLte.getCellIdentity().getMcc();
        c6531a.f16673e = cellInfoLte.getCellIdentity().getMnc();
        c6531a.f16674f = cellInfoLte.getCellIdentity().getCi();
        c6531a.f16675g = cellInfoLte.getCellIdentity().getPci();
        if (Build.VERSION.SDK_INT >= 24) {
            c6531a.f16676h = cellInfoLte.getCellIdentity().getEarfcn();
        }
        Map<String, Integer> m9495a = m9495a(cellInfoLte.getCellSignalStrength().toString());
        c6531a.f16677i = m9493a(m9495a, "rsrp");
        c6531a.f16678j = m9493a(m9495a, "rsrq");
    }

    /* renamed from: a */
    private void m9498a(C6531a c6531a, CellInfoGsm cellInfoGsm) {
        c6531a.f16672d = cellInfoGsm.getCellIdentity().getMcc();
        c6531a.f16673e = cellInfoGsm.getCellIdentity().getMnc();
        c6531a.f16674f = cellInfoGsm.getCellIdentity().getCid();
    }

    /* renamed from: a */
    private void m9496a(C6531a c6531a, CellInfoWcdma cellInfoWcdma) {
        c6531a.f16672d = cellInfoWcdma.getCellIdentity().getMcc();
        c6531a.f16673e = cellInfoWcdma.getCellIdentity().getMnc();
        c6531a.f16674f = cellInfoWcdma.getCellIdentity().getCid();
    }

    /* renamed from: a */
    private int m9493a(Map<String, Integer> map, String str) {
        if (map.containsKey(str)) {
            return map.get(str).intValue();
        }
        return 0;
    }

    /* renamed from: a */
    private Map<String, Integer> m9495a(String str) {
        String[] split;
        HashMap hashMap = new HashMap();
        try {
            for (String str2 : str.split(" ")) {
                if (str2.contains("=")) {
                    String[] split2 = str2.split("=");
                    if (split2.length != 2) {
                        f16683a.mo10116d("error keyvalue info:" + str2);
                    } else {
                        hashMap.put(split2[0], Integer.valueOf(split2[1]));
                    }
                }
            }
        } catch (Throwable th) {
            f16683a.mo10116d("parseString error:" + th.getMessage());
        }
        return hashMap;
    }
}
