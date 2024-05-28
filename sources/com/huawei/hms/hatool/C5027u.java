package com.huawei.hms.hatool;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* renamed from: com.huawei.hms.hatool.u */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5027u {

    /* renamed from: a */
    private List<C4971b1> f11515a;

    /* renamed from: b */
    private String f11516b;

    /* renamed from: c */
    private String f11517c;

    /* renamed from: d */
    private String f11518d;

    public C5027u(List<C4971b1> list, String str, String str2, String str3) {
        this.f11515a = list;
        this.f11516b = str;
        this.f11517c = str2;
        this.f11518d = str3;
    }

    /* renamed from: a */
    private void m14469a(List<C4971b1> list, String str, String str2) {
        if (list.isEmpty()) {
            return;
        }
        int size = (list.size() / 500) + 1;
        for (int i = 0; i < size; i++) {
            int i2 = i * 500;
            List<C4971b1> subList = list.subList(i2, Math.min(list.size(), i2 + 500));
            String replace = UUID.randomUUID().toString().replace("-", "");
            long currentTimeMillis = System.currentTimeMillis();
            long m14816b = AbstractC4966a1.m14816b(str2, str) * 86400000;
            ArrayList arrayList = new ArrayList();
            for (C4971b1 c4971b1 : subList) {
                if (!C4973c0.m14777a(c4971b1.m14790b(), currentTimeMillis, m14816b)) {
                    arrayList.add(c4971b1);
                }
            }
            if (arrayList.size() > 0) {
                new C5002l0(str2, str, this.f11518d, arrayList, replace).m14624a();
            } else {
                C5029v.m14452e("hmsSdk", "No data to report handler");
            }
        }
    }

    /* renamed from: a */
    public void m14470a() {
        if (!"_default_config_tag".equals(this.f11517c)) {
            m14469a(this.f11515a, this.f11517c, this.f11516b);
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (C4971b1 c4971b1 : this.f11515a) {
            String m14788c = c4971b1.m14788c();
            if (TextUtils.isEmpty(m14788c) || "oper".equals(m14788c)) {
                arrayList4.add(c4971b1);
            } else if ("maint".equals(m14788c)) {
                arrayList.add(c4971b1);
            } else if ("preins".equals(m14788c)) {
                arrayList2.add(c4971b1);
            } else if ("diffprivacy".equals(m14788c)) {
                arrayList3.add(c4971b1);
            }
        }
        m14469a(arrayList4, "oper", "_default_config_tag");
        m14469a(arrayList, "maint", "_default_config_tag");
        m14469a(arrayList2, "preins", "_default_config_tag");
        m14469a(arrayList3, "diffprivacy", "_default_config_tag");
    }
}
