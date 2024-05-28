package com.mob.commons;

import android.content.Context;
import android.text.TextUtils;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.AbstractRunnableC6217h;
import java.util.HashMap;
import java.util.HashSet;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C5831e {

    /* renamed from: a */
    static volatile String f14351a;

    /* renamed from: b */
    private static volatile Boolean f14352b;

    /* renamed from: c */
    private static volatile String f14353c;

    /* renamed from: d */
    private static volatile boolean f14354d;

    /* renamed from: e */
    private static HashSet<String> f14355e = new HashSet<>();

    /* renamed from: f */
    private static final C5694a f14356f = new C5694a();

    /* renamed from: c */
    static /* synthetic */ C5694a m12315c() {
        return m12314d();
    }

    /* renamed from: a */
    public static boolean m12321a() {
        return !C5747b.m12589a();
    }

    /* renamed from: a */
    public static String m12320a(Context context) {
        return C6031c.m11708a(context).m11704d().mo11562ai();
    }

    /* renamed from: b */
    public static String m12317b() {
        if (m12321a()) {
            return null;
        }
        if (TextUtils.isEmpty(f14351a)) {
            String m12824a = m12314d().m12824a();
            if (!TextUtils.isEmpty(m12824a) && TextUtils.isEmpty(f14351a)) {
                f14351a = m12824a;
            }
        }
        return f14351a;
    }

    /* renamed from: a */
    public static synchronized String m12319a(MobProduct mobProduct) {
        synchronized (C5831e.class) {
            HashMap<String, Object> m12316b = m12316b(mobProduct);
            if (m12316b != null) {
                return (String) m12316b.get(NetCommunicator.KEY_DUID);
            }
            return null;
        }
    }

    /* renamed from: b */
    public static synchronized HashMap<String, Object> m12316b(final MobProduct mobProduct) {
        boolean z;
        HashMap<String, Object> hashMap;
        synchronized (C5831e.class) {
            if (mobProduct != null) {
                C5895z.m12123a(mobProduct);
                z = !f14355e.contains(mobProduct.getProductTag());
                if (z) {
                    f14355e.add(mobProduct.getProductTag());
                }
            } else {
                z = false;
            }
            if (TextUtils.isEmpty(f14351a)) {
                f14351a = m12314d().m12807b();
                z = true;
            }
            MobLog.getInstance().m11342d("aut pro: " + mobProduct + ", ndReg: " + z + ", hsReged: " + f14354d, new Object[0]);
            if (z || !f14354d) {
                C5892y.f14525c.execute(new AbstractRunnableC6217h() { // from class: com.mob.commons.e.1
                    @Override // com.mob.tools.utils.AbstractRunnableC6217h
                    /* renamed from: a */
                    public void mo10991a() {
                        if (C5747b.m12585a(C5731l.m12674a("0028edej"))) {
                            boolean unused = C5831e.f14354d = true;
                            if (!C5747b.m12557d()) {
                                int i = 0;
                                while (i < 5) {
                                    i++;
                                    try {
                                        Thread.sleep(5000L);
                                        if (C5747b.m12557d()) {
                                            break;
                                        }
                                    } catch (Throwable unused2) {
                                    }
                                }
                            }
                            if (C5747b.m12557d()) {
                                C5831e.m12315c().m12823a(MobProduct.this, new AbstractC6201c<Void>() { // from class: com.mob.commons.e.1.1
                                    @Override // com.mob.tools.utils.AbstractC6201c
                                    /* renamed from: a  reason: avoid collision after fix types in other method */
                                    public void mo11088a(Void r1) {
                                    }
                                });
                            }
                        }
                    }
                });
            }
            if (f14352b == null) {
                String m12632b = C5741aa.m12650a().m12632b("key_curr_passed_duid", (String) null);
                f14353c = m12632b;
                if (!TextUtils.isEmpty(m12632b) && !m12632b.equals(f14351a)) {
                    f14352b = true;
                } else {
                    f14352b = false;
                }
            }
            C5741aa.m12650a().m12641a("key_curr_passed_duid", f14351a);
            hashMap = new HashMap<>();
            hashMap.put(NetCommunicator.KEY_DUID, f14351a);
            hashMap.put(NetCommunicator.KEY_IS_MODIFIED, Boolean.valueOf(f14352b.booleanValue()));
            hashMap.put(NetCommunicator.KEY_DUID_PREVIOUS, f14353c);
        }
        return hashMap;
    }

    /* renamed from: d */
    private static C5694a m12314d() {
        return f14356f;
    }
}
