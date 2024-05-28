package p001a.p058b.p062b.p063a.p064a.p065a;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.uaq.agent.android.crashes.InterfaceC3317d;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0717g implements InterfaceC3317d {

    /* renamed from: a */
    public static final InterfaceC3321a f2176a = C0749a.f2299a;

    /* renamed from: b */
    public final Context f2177b;

    public C0717g(Context context) {
        this.f2177b = context;
    }

    @Override // com.baidu.uaq.agent.android.crashes.InterfaceC3317d
    /* renamed from: L */
    public List<C0710b> mo17437L() {
        Map<String, ?> all;
        SharedPreferences sharedPreferences = this.f2177b.getSharedPreferences("APMCrashStore", 0);
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            all = sharedPreferences.getAll();
        }
        for (Object obj : all.values()) {
            if (obj instanceof String) {
                try {
                    arrayList.add(C0710b.m22337b((String) obj));
                } catch (Exception e) {
                    f2176a.mo17426a("Exception encountered while deserializing crash", e);
                }
            }
        }
        return arrayList;
    }

    @Override // com.baidu.uaq.agent.android.crashes.InterfaceC3317d
    /* renamed from: b */
    public void mo17436b(C0710b c0710b) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f2177b.getSharedPreferences("APMCrashStore", 0).edit();
            edit.putString(c0710b.f2142e.toString(), c0710b.mo17432az().toString());
            edit.commit();
        }
    }

    @Override // com.baidu.uaq.agent.android.crashes.InterfaceC3317d
    /* renamed from: c */
    public void mo17435c(C0710b c0710b) {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f2177b.getSharedPreferences("APMCrashStore", 0).edit();
            edit.remove(c0710b.f2142e.toString());
            edit.commit();
        }
    }

    @Override // com.baidu.uaq.agent.android.crashes.InterfaceC3317d
    public void clear() {
        synchronized (this) {
            SharedPreferences.Editor edit = this.f2177b.getSharedPreferences("APMCrashStore", 0).edit();
            edit.clear();
            edit.commit();
        }
    }

    @Override // com.baidu.uaq.agent.android.crashes.InterfaceC3317d
    public int count() {
        return this.f2177b.getSharedPreferences("APMCrashStore", 0).getAll().size();
    }
}
