package com.mob.tools.utils;

import android.text.TextUtils;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5868q;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.mob.tools.utils.e */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6212e {

    /* renamed from: a */
    private static volatile C6212e f15321a;

    /* renamed from: b */
    private volatile HashMap<String, LinkedList<int[]>> f15322b;

    /* renamed from: c */
    private final HashMap<String, HashMap<String, Object>> f15323c = new HashMap<>();

    /* renamed from: d */
    private final byte[] f15324d = new byte[0];

    /* renamed from: e */
    private SimpleDateFormat f15325e;

    private C6212e() {
        ArrayList arrayList;
        try {
            this.f15325e = new SimpleDateFormat(JtDateUtil.dateFormatYMDHM);
            this.f15322b = new HashMap<>();
            if (!this.f15323c.isEmpty() || (arrayList = (ArrayList) C5747b.m12583a("spsd", (Object) null)) == null) {
                return;
            }
            if (arrayList.isEmpty()) {
                return;
            }
            try {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    HashMap<String, Object> hashMap = (HashMap) it.next();
                    String str = (String) ResHelper.forceCast(hashMap.get("pit"), null);
                    if (!TextUtils.isEmpty(str)) {
                        this.f15323c.put(str, hashMap);
                    }
                }
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* renamed from: a */
    public static C6212e m11053a() {
        if (f15321a == null) {
            synchronized (C6212e.class) {
                if (f15321a == null) {
                    f15321a = new C6212e();
                }
            }
        }
        return f15321a;
    }

    /* renamed from: b */
    public HashMap<String, HashMap<String, Object>> m11048b() {
        return this.f15323c;
    }

    /* renamed from: a */
    public boolean m11049a(HashMap<String, Object> hashMap, int i) {
        synchronized (this.f15324d) {
            try {
                String str = (String) ResHelper.forceCast(hashMap.get("pit"), "");
                LinkedList<int[]> m11050a = m11050a(hashMap);
                C5741aa m12650a = C5741aa.m12650a();
                long m12634b = m12650a.m12634b("key_lvpttms" + str, 0L);
                long currentTimeMillis = System.currentTimeMillis();
                int intValue = ((Integer) ResHelper.forceCast(hashMap.get("dn"), 0)).intValue();
                if (m12634b == 0) {
                    m11050a.add(new int[intValue]);
                } else if (currentTimeMillis >= m12634b && !C5873u.m12187a(currentTimeMillis, m12634b)) {
                    m11050a.add(new int[intValue]);
                    int intValue2 = ((Integer) ResHelper.forceCast(hashMap.get(C5868q.m12203b("002)egcb")), 0)).intValue();
                    while (m11050a.size() > intValue2) {
                        m11050a.removeFirst();
                    }
                }
                int[] last = m11050a.getLast();
                if (last == null || i >= last.length) {
                    return false;
                }
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("LPT cs " + i + " ptalen " + last.length, new Object[0]);
                if (i == 0 || (i > 0 && last[i - 1] == 1)) {
                    last[i] = 1;
                }
                C5741aa m12650a2 = C5741aa.m12650a();
                m12650a2.m12643a("key_lvpttms" + str, System.currentTimeMillis());
                C5741aa m12650a3 = C5741aa.m12650a();
                m12650a3.m12642a("key_lvptme" + str, m11050a);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* renamed from: a */
    private LinkedList<int[]> m11050a(HashMap<String, Object> hashMap) {
        LinkedList<int[]> linkedList;
        synchronized (this.f15324d) {
            String str = (String) ResHelper.forceCast(hashMap.get("pit"), "");
            linkedList = this.f15322b.get(str);
            if (linkedList == null) {
                C5741aa m12650a = C5741aa.m12650a();
                linkedList = (LinkedList) m12650a.m12645a("key_lvptme" + str);
                if (linkedList == null) {
                    linkedList = new LinkedList<>();
                }
                this.f15322b.put(str, linkedList);
            }
        }
        return linkedList;
    }

    /* renamed from: a */
    public int m11052a(String str, int i) {
        HashMap<String, Object> hashMap = this.f15323c.get(str);
        if (hashMap == null || hashMap.isEmpty()) {
            return 0;
        }
        try {
            LinkedList<int[]> m11050a = m11050a(hashMap);
            if (m11050a.size() < ((Integer) ResHelper.forceCast(hashMap.get("cpd"), 0)).intValue()) {
                return 0;
            }
            Iterator<int[]> it = m11050a.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                int[] next = it.next();
                if (next.length > i && next[i] == 1) {
                    i2++;
                }
            }
            return new BigDecimal(i2).divide(BigDecimal.valueOf(m11050a.size()), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).intValue();
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return 0;
        }
    }

    /* renamed from: b */
    public int m11047b(String str, int i) {
        HashMap<String, Object> hashMap = this.f15323c.get(str);
        if (hashMap == null || hashMap.isEmpty() || i <= 0 || m11052a(str, 0) < i) {
            return -1;
        }
        int i2 = 1;
        while (m11052a(str, i2) >= i) {
            i2++;
        }
        return i2 > 0 ? i2 - 1 : i2;
    }

    /* renamed from: a */
    public Date m11051a(Date date, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                String format = this.f15325e.format(date);
                return new Date(String.valueOf(this.f15325e.parse(format.substring(0, 11) + str)));
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return date;
    }
}
