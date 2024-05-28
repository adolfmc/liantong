package com.networkbench.agent.impl.crash.p250b;

import android.annotation.TargetApi;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@TargetApi(9)
/* renamed from: com.networkbench.agent.impl.crash.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6320b extends HarvestableArray {

    /* renamed from: a */
    private static final InterfaceC6393e f15896a = C6394f.m10150a();

    /* renamed from: b */
    private LinkedBlockingDeque<C6318a> f15897b;

    /* renamed from: c */
    private int f15898c;

    /* renamed from: d */
    private int f15899d;

    /* renamed from: e */
    private int f15900e;

    /* renamed from: f */
    private int f15901f;

    public C6320b() {
        this.f15900e = C6638h.m8963w().m9043ac();
        if (this.f15900e < 0) {
            return;
        }
        this.f15901f = C6638h.m8963w().f17127D;
        if (this.f15900e == 0) {
            this.f15900e = 100;
        }
        this.f15897b = new LinkedBlockingDeque<>(this.f15900e + this.f15901f);
    }

    /* renamed from: a */
    public synchronized void m10428a(C6318a c6318a) {
        if (c6318a != null) {
            if (this.f15897b != null) {
                String m10435b = c6318a.m10435b();
                if (m10424c(m10435b)) {
                    if (this.f15898c < this.f15901f) {
                        this.f15897b.offer(c6318a);
                        this.f15898c++;
                    } else {
                        f15896a.mo10122a("The length of BreadCrumbs is greater than " + this.f15898c + ",remove the earliest one!");
                        m10427a(m10435b);
                        this.f15897b.offer(c6318a);
                    }
                } else if (this.f15899d < this.f15900e) {
                    this.f15897b.offer(c6318a);
                    this.f15899d++;
                } else {
                    f15896a.mo10122a("The length of Trails is greater than " + this.f15899d + ",remove the earliest one!");
                    m10427a(m10435b);
                    this.f15897b.offer(c6318a);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public synchronized boolean m10427a(String str) {
        boolean z = false;
        if (this.f15897b == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        int size = this.f15897b.size();
        if (!m10424c(str)) {
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                C6318a pollFirst = this.f15897b.pollFirst();
                String m10435b = pollFirst.m10435b();
                if (!m10424c(pollFirst.m10435b())) {
                    f15896a.mo10122a("Remove Earliest Trails Element :" + m10435b);
                    z = true;
                    break;
                }
                arrayList.add(pollFirst);
                i++;
            }
        } else {
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                C6318a pollFirst2 = this.f15897b.pollFirst();
                String m10435b2 = pollFirst2.m10435b();
                if (m10424c(m10435b2)) {
                    f15896a.mo10122a("Remove Earliest BreadCrumb Element :" + m10435b2);
                    z = true;
                    break;
                }
                arrayList.add(pollFirst2);
                i2++;
            }
        }
        if (arrayList.size() > 0) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                this.f15897b.offerFirst(arrayList.get(size2));
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public synchronized boolean m10425b(String str) {
        boolean z = false;
        if (this.f15897b == null) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        int size = this.f15897b.size();
        if (!m10424c(str)) {
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                C6318a pollLast = this.f15897b.pollLast();
                if (!m10424c(pollLast.m10435b())) {
                    z = true;
                    break;
                }
                arrayList.add(pollLast);
                i++;
            }
        } else {
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    break;
                }
                C6318a pollLast2 = this.f15897b.pollLast();
                if (m10424c(pollLast2.m10435b())) {
                    z = true;
                    break;
                }
                arrayList.add(pollLast2);
                i2++;
            }
        }
        if (arrayList.size() > 0) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                this.f15897b.offerLast(arrayList.get(size2));
            }
        }
        return z;
    }

    /* renamed from: c */
    private boolean m10424c(String str) {
        return str != null && str.contains("<_TY_C_API>");
    }

    /* renamed from: a */
    public synchronized C6318a m10429a() {
        if (this.f15897b == null) {
            return null;
        }
        return this.f15897b.peekLast();
    }

    /* renamed from: b */
    public void m10426b() {
        LinkedBlockingDeque<C6318a> linkedBlockingDeque = this.f15897b;
        if (linkedBlockingDeque != null) {
            linkedBlockingDeque.clear();
        }
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public synchronized JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        if (this.f15897b == null) {
            return jsonArray;
        }
        Iterator<C6318a> it = this.f15897b.iterator();
        while (it.hasNext()) {
            jsonArray.add(it.next().asJson());
        }
        return jsonArray;
    }
}
