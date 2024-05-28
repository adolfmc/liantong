package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.clientreport.data.C11052a;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.push.C11197bl;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import szcom.coremedia.iso.boxes.PerformerBox;

/* renamed from: com.xiaomi.clientreport.processor.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11063b implements IPerfProcessor {

    /* renamed from: a */
    protected Context f21311a;

    /* renamed from: a */
    private HashMap<String, HashMap<String, C11052a>> f21312a;

    public C11063b(Context context) {
        this.f21311a = context;
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11064c
    /* renamed from: a */
    public void mo5226a() {
        C11197bl.m4713a(this.f21311a, PerformerBox.TYPE, "perfUploading");
        File[] m4714a = C11197bl.m4714a(this.f21311a, "perfUploading");
        if (m4714a == null || m4714a.length <= 0) {
            return;
        }
        for (File file : m4714a) {
            if (file != null) {
                List<String> m5223a = C11066e.m5223a(this.f21311a, file.getAbsolutePath());
                file.delete();
                mo4134a(m5223a);
            }
        }
    }

    /* renamed from: a */
    public void mo4134a(List<String> list) {
        C11197bl.m4712a(this.f21311a, list);
    }

    /* renamed from: a */
    public void m5229a(C11052a[] c11052aArr) {
        String m5227c = m5227c(c11052aArr[0]);
        if (TextUtils.isEmpty(m5227c)) {
            return;
        }
        C11066e.m5215a(m5227c, c11052aArr);
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11065d
    /* renamed from: b */
    public void mo5224b() {
        HashMap<String, HashMap<String, C11052a>> hashMap = this.f21312a;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            for (String str : this.f21312a.keySet()) {
                HashMap<String, C11052a> hashMap2 = this.f21312a.get(str);
                if (hashMap2 != null && hashMap2.size() > 0) {
                    C11052a[] c11052aArr = new C11052a[hashMap2.size()];
                    hashMap2.values().toArray(c11052aArr);
                    m5229a(c11052aArr);
                }
            }
        }
        this.f21312a.clear();
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11065d
    /* renamed from: a */
    public void mo5225a(C11052a c11052a) {
        if ((c11052a instanceof PerfClientReport) && this.f21312a != null) {
            PerfClientReport perfClientReport = (PerfClientReport) c11052a;
            String m5230a = m5230a((C11052a) perfClientReport);
            String m5222a = C11066e.m5222a(perfClientReport);
            HashMap<String, C11052a> hashMap = this.f21312a.get(m5230a);
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            PerfClientReport perfClientReport2 = (PerfClientReport) hashMap.get(m5222a);
            if (perfClientReport2 != null) {
                perfClientReport.perfCounts += perfClientReport2.perfCounts;
                perfClientReport.perfLatencies += perfClientReport2.perfLatencies;
            }
            hashMap.put(m5222a, perfClientReport);
            this.f21312a.put(m5230a, hashMap);
        }
    }

    /* renamed from: b */
    private String m5228b(C11052a c11052a) {
        String str = "";
        int i = c11052a.production;
        String str2 = c11052a.clientInterfaceId;
        if (i > 0 && !TextUtils.isEmpty(str2)) {
            str = String.valueOf(i) + "#" + str2;
        }
        File file = new File(this.f21311a.getFilesDir(), PerformerBox.TYPE);
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, str).getAbsolutePath();
    }

    /* renamed from: c */
    private String m5227c(C11052a c11052a) {
        String m5228b = m5228b(c11052a);
        if (TextUtils.isEmpty(m5228b)) {
            return null;
        }
        for (int i = 0; i < 20; i++) {
            String str = m5228b + i;
            if (C11197bl.m4715a(this.f21311a, str)) {
                return str;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static String m5230a(C11052a c11052a) {
        return String.valueOf(c11052a.production) + "#" + c11052a.clientInterfaceId;
    }

    @Override // com.xiaomi.clientreport.processor.IPerfProcessor
    public void setPerfMap(HashMap<String, HashMap<String, C11052a>> hashMap) {
        this.f21312a = hashMap;
    }
}
