package com.xiaomi.push.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11180ay;
import com.xiaomi.push.C11651z;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11410gl;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.xiaomi.push.service.ah */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11537ah {

    /* renamed from: a */
    private static volatile C11537ah f23507a;

    /* renamed from: a */
    protected SharedPreferences f23508a;

    /* renamed from: a */
    private HashSet<AbstractRunnableC11538a> f23509a = new HashSet<>();

    /* renamed from: b */
    protected SharedPreferences f23510b;

    /* renamed from: a */
    public synchronized void m2711a(AbstractRunnableC11538a abstractRunnableC11538a) {
        if (!this.f23509a.contains(abstractRunnableC11538a)) {
            this.f23509a.add(abstractRunnableC11538a);
        }
    }

    /* renamed from: a */
    public synchronized void m2721a() {
        this.f23509a.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2708b() {
        AbstractC11049b.m5270c("OC_Callback : receive new oc data");
        HashSet hashSet = new HashSet();
        synchronized (this) {
            hashSet.addAll(this.f23509a);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            AbstractRunnableC11538a abstractRunnableC11538a = (AbstractRunnableC11538a) it.next();
            if (abstractRunnableC11538a != null) {
                abstractRunnableC11538a.run();
            }
        }
        hashSet.clear();
    }

    /* renamed from: a */
    public static C11537ah m2715a(Context context) {
        if (f23507a == null) {
            synchronized (C11537ah.class) {
                if (f23507a == null) {
                    f23507a = new C11537ah(context);
                }
            }
        }
        return f23507a;
    }

    private C11537ah(Context context) {
        this.f23508a = context.getSharedPreferences("mipush_oc_normal", 0);
        this.f23510b = context.getSharedPreferences("mipush_oc_custom", 0);
    }

    /* renamed from: a */
    public void m2709a(List<Pair<EnumC11410gl, Integer>> list, List<Pair<Integer, Object>> list2) {
        if (C11651z.m2259a(list) || C11651z.m2259a(list2)) {
            AbstractC11049b.m5282a("not update oc, because versions or configs are empty");
            return;
        }
        SharedPreferences.Editor edit = this.f23508a.edit();
        edit.clear();
        for (Pair<EnumC11410gl, Integer> pair : list) {
            if (pair.first != null && pair.second != null) {
                edit.putInt(m2713a((EnumC11410gl) pair.first), ((Integer) pair.second).intValue());
            }
        }
        for (Pair<Integer, Object> pair2 : list2) {
            if (pair2.first != null && pair2.second != null) {
                m2714a(edit, pair2, m2720a(((Integer) pair2.first).intValue()));
            }
        }
        edit.apply();
    }

    /* renamed from: a */
    public void m2710a(List<Pair<Integer, Object>> list) {
        if (C11651z.m2259a(list)) {
            return;
        }
        SharedPreferences.Editor edit = this.f23510b.edit();
        for (Pair<Integer, Object> pair : list) {
            if (pair.first != null) {
                String m2720a = m2720a(((Integer) pair.first).intValue());
                if (pair.second == null) {
                    edit.remove(m2720a);
                } else {
                    m2714a(edit, pair, m2720a);
                }
            }
        }
        edit.apply();
    }

    /* renamed from: a */
    private void m2714a(SharedPreferences.Editor editor, Pair<Integer, Object> pair, String str) {
        if (pair.second instanceof Integer) {
            editor.putInt(str, ((Integer) pair.second).intValue());
        } else if (pair.second instanceof Long) {
            editor.putLong(str, ((Long) pair.second).longValue());
        } else if (pair.second instanceof String) {
            String str2 = (String) pair.second;
            if (str.equals(m2720a(EnumC11409gk.AppIsInstalledList.m3637a()))) {
                editor.putString(str, C11180ay.m4797a(str2));
            } else {
                editor.putString(str, str2);
            }
        } else if (pair.second instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) pair.second).booleanValue());
        }
    }

    /* renamed from: a */
    public int m2719a(int i, int i2) {
        try {
            String m2720a = m2720a(i);
            if (this.f23510b.contains(m2720a)) {
                return this.f23510b.getInt(m2720a, 0);
            }
            return this.f23508a.contains(m2720a) ? this.f23508a.getInt(m2720a, 0) : i2;
        } catch (Exception e) {
            AbstractC11049b.m5282a(i + " oc int error " + e);
            return i2;
        }
    }

    /* renamed from: a */
    public long m2718a(int i, long j) {
        try {
            String m2720a = m2720a(i);
            if (this.f23510b.contains(m2720a)) {
                return this.f23510b.getLong(m2720a, 0L);
            }
            return this.f23508a.contains(m2720a) ? this.f23508a.getLong(m2720a, 0L) : j;
        } catch (Exception e) {
            AbstractC11049b.m5282a(i + " oc long error " + e);
            return j;
        }
    }

    /* renamed from: a */
    public String m2717a(int i, String str) {
        try {
            String m2720a = m2720a(i);
            if (this.f23510b.contains(m2720a)) {
                return this.f23510b.getString(m2720a, null);
            }
            return this.f23508a.contains(m2720a) ? this.f23508a.getString(m2720a, null) : str;
        } catch (Exception e) {
            AbstractC11049b.m5282a(i + " oc string error " + e);
            return str;
        }
    }

    /* renamed from: a */
    public boolean m2716a(int i, boolean z) {
        try {
            String m2720a = m2720a(i);
            if (this.f23510b.contains(m2720a)) {
                return this.f23510b.getBoolean(m2720a, false);
            }
            return this.f23508a.contains(m2720a) ? this.f23508a.getBoolean(m2720a, false) : z;
        } catch (Exception e) {
            AbstractC11049b.m5282a(i + " oc boolean error " + e);
            return z;
        }
    }

    /* renamed from: a */
    public int m2712a(EnumC11410gl enumC11410gl, int i) {
        try {
            return this.f23508a.getInt(m2713a(enumC11410gl), i);
        } catch (Exception e) {
            AbstractC11049b.m5282a(enumC11410gl + " version error " + e);
            return i;
        }
    }

    /* renamed from: a */
    private String m2720a(int i) {
        return "oc_" + i;
    }

    /* renamed from: a */
    private String m2713a(EnumC11410gl enumC11410gl) {
        return "oc_version_" + enumC11410gl.m3636a();
    }

    /* renamed from: com.xiaomi.push.service.ah$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractRunnableC11538a implements Runnable {
        private String mDescription;
        private int mId;

        protected abstract void onCallback();

        public AbstractRunnableC11538a(int i, String str) {
            this.mId = i;
            this.mDescription = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            onCallback();
        }

        public int hashCode() {
            return this.mId;
        }

        public boolean equals(Object obj) {
            return (obj instanceof AbstractRunnableC11538a) && this.mId == ((AbstractRunnableC11538a) obj).mId;
        }
    }
}
