package com.networkbench.agent.impl.crash;

import android.content.Context;
import android.content.SharedPreferences;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6636f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6332j implements InterfaceC6329h {

    /* renamed from: b */
    private static final InterfaceC6393e f15942b = C6394f.m10150a();

    /* renamed from: a */
    private String f15943a;

    /* renamed from: c */
    private Context f15944c;

    /* renamed from: f */
    public String m10365f() {
        return this.f15943a;
    }

    public C6332j(Context context, String str) {
        this.f15944c = context;
        this.f15943a = str;
    }

    /* renamed from: b */
    public void m10373b(String str) {
        this.f15943a = str;
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: a */
    public void mo10375a(String str, String str2) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f15944c.getSharedPreferences(this.f15943a, 0).edit();
            while (mo10371c() >= 5) {
                C6396h.m10125q("data count>5");
                String m9092c = C6636f.m9092c(mo10367e());
                if (m9092c != null) {
                    mo10376a(m9092c);
                    C6396h.m10125q("delete the oldest data ,oldestTimeStamp is" + m9092c);
                }
            }
            edit.putString(C6636f.m9093b(str), C6636f.m9093b(str2));
            edit.commit();
            C6396h.m10125q("saved data,timeStamp is " + str);
        }
    }

    /* renamed from: b */
    public void m10372b(String str, String str2) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f15944c.getSharedPreferences(this.f15943a, 0).edit();
            edit.putString(C6636f.m9093b(str), C6636f.m9093b(str2));
            edit.apply();
            C6396h.m10125q("saved data,timeStamp is " + str);
        }
    }

    /* renamed from: c */
    public String m10370c(String str) {
        String string;
        synchronized (this) {
            string = this.f15944c.getSharedPreferences(this.f15943a, 0).getString(C6636f.m9093b(str), "");
        }
        return string;
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: e */
    public String mo10367e() {
        SharedPreferences sharedPreferences = this.f15944c.getSharedPreferences(this.f15943a, 0);
        ArrayList arrayList = new ArrayList();
        for (String str : sharedPreferences.getAll().keySet()) {
            if (str instanceof String) {
                arrayList.add(str);
            }
        }
        if (arrayList.size() < 1) {
            f15942b.mo10116d("get the oldest Crash has problem");
            return null;
        }
        Collections.sort(arrayList, new Comparator<String>() { // from class: com.networkbench.agent.impl.crash.j.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(String str2, String str3) {
                return str2.compareTo(str3);
            }
        });
        return (String) arrayList.get(0);
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: a */
    public List<String> mo10377a() {
        ArrayList arrayList = new ArrayList();
        SharedPreferences sharedPreferences = this.f15944c.getSharedPreferences(this.f15943a, 0);
        synchronized (this) {
            for (Object obj : sharedPreferences.getAll().values()) {
                if (obj instanceof String) {
                    arrayList.add((String) obj);
                }
            }
        }
        return arrayList;
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: b */
    public Map<String, ?> mo10374b() {
        Map<String, ?> all;
        SharedPreferences sharedPreferences = this.f15944c.getSharedPreferences(this.f15943a, 0);
        synchronized (this) {
            all = sharedPreferences.getAll();
        }
        return all;
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: c */
    public int mo10371c() {
        return this.f15944c.getSharedPreferences(this.f15943a, 0).getAll().size();
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: d */
    public void mo10369d() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f15944c.getSharedPreferences(this.f15943a, 0).edit();
            edit.clear();
            edit.commit();
        }
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6329h
    /* renamed from: a */
    public void mo10376a(String str) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f15944c.getSharedPreferences(this.f15943a, 0).edit();
            edit.remove(C6636f.m9093b(str));
            edit.commit();
            C6396h.m10125q("delete crash success, data timeStamp is " + str);
        }
    }

    /* renamed from: d */
    public void m10368d(String str) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f15944c.getSharedPreferences(this.f15943a, 0).edit();
            edit.remove(C6636f.m9093b(str));
            edit.apply();
            C6396h.m10125q("delete crash success, data timeStamp is " + str);
        }
    }

    /* renamed from: e */
    public String m10366e(String str) {
        String string;
        synchronized (this) {
            string = this.f15944c.getSharedPreferences(this.f15943a, 0).getString(str, "");
        }
        return string;
    }
}
