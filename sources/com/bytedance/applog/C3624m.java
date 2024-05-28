package com.bytedance.applog;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/* renamed from: com.bytedance.applog.m */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3624m {

    /* renamed from: m */
    public static long f8551m;

    /* renamed from: n */
    public static long f8552n;

    /* renamed from: o */
    public static C3626b f8553o;

    /* renamed from: a */
    public final C3591h f8554a;

    /* renamed from: b */
    public C3711v1 f8555b;

    /* renamed from: c */
    public C3711v1 f8556c;

    /* renamed from: d */
    public String f8557d;

    /* renamed from: e */
    public long f8558e;

    /* renamed from: f */
    public int f8559f;

    /* renamed from: g */
    public long f8560g = -1;

    /* renamed from: h */
    public volatile boolean f8561h;

    /* renamed from: i */
    public long f8562i;

    /* renamed from: j */
    public int f8563j;

    /* renamed from: k */
    public String f8564k;

    /* renamed from: l */
    public volatile String f8565l;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.m$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3626b extends C3729x1 {
        public /* synthetic */ C3626b(C3625a c3625a) {
        }
    }

    public C3624m(C3591h c3591h) {
        this.f8554a = c3591h;
    }

    /* renamed from: b */
    public static long m17237b() {
        long j = f8552n + 1;
        f8552n = j;
        return j;
    }

    /* renamed from: b */
    public static boolean m17236b(AbstractC3628m1 abstractC3628m1) {
        if (abstractC3628m1 instanceof C3711v1) {
            return ((C3711v1) abstractC3628m1).m17080i();
        }
        return false;
    }

    /* renamed from: a */
    public synchronized Bundle m17241a(long j, long j2) {
        Bundle bundle;
        bundle = null;
        if (this.f8554a.f8465d.f8897b.isPlayEnable() && m17242a() && j - this.f8558e > j2) {
            bundle = new Bundle();
            bundle.putInt("session_no", this.f8563j);
            int i = this.f8559f + 1;
            this.f8559f = i;
            bundle.putInt("send_times", i);
            bundle.putLong("current_duration", (j - this.f8558e) / 1000);
            bundle.putString("session_start_time", AbstractC3628m1.f8574j.format(new Date(this.f8560g)));
            this.f8558e = j;
        }
        return bundle;
    }

    /* renamed from: a */
    public void m17240a(AbstractC3628m1 abstractC3628m1) {
        if (abstractC3628m1 != null) {
            abstractC3628m1.f8579e = f8551m;
            abstractC3628m1.f8580f = AppLog.getUserUniqueID();
            abstractC3628m1.f8578d = this.f8557d;
            abstractC3628m1.f8577c = m17237b();
            abstractC3628m1.f8581g = AppLog.getAbSdkVersion();
            abstractC3628m1.f8582h = this.f8554a.m17289b();
        }
    }

    /* renamed from: a */
    public boolean m17242a() {
        return this.f8561h && this.f8562i == 0;
    }

    /* renamed from: a */
    public boolean m17239a(AbstractC3628m1 abstractC3628m1, ArrayList<AbstractC3628m1> arrayList) {
        C3711v1 c3711v1;
        boolean z = abstractC3628m1 instanceof C3711v1;
        boolean m17236b = m17236b(abstractC3628m1);
        boolean z2 = true;
        if (this.f8560g == -1) {
            m17238a(abstractC3628m1, arrayList, m17236b(abstractC3628m1));
        } else if (this.f8561h || !m17236b) {
            long j = this.f8562i;
            if ((j == 0 || abstractC3628m1.f8576b <= this.f8554a.f8465d.f8900e.getLong("session_interval", 30000L) + j) && this.f8560g <= abstractC3628m1.f8576b + 7200000) {
                z2 = false;
            } else {
                m17238a(abstractC3628m1, arrayList, m17236b);
            }
        } else {
            m17238a(abstractC3628m1, arrayList, true);
        }
        if (z) {
            C3711v1 c3711v12 = (C3711v1) abstractC3628m1;
            if (c3711v12.m17080i()) {
                this.f8558e = abstractC3628m1.f8576b;
                this.f8562i = 0L;
                arrayList.add(abstractC3628m1);
                if (TextUtils.isEmpty(c3711v12.f8860l) && (((c3711v1 = this.f8556c) != null && (c3711v12.f8576b - c3711v1.f8576b) - c3711v1.f8859k < 500) || ((c3711v1 = this.f8555b) != null && (c3711v12.f8576b - c3711v1.f8576b) - c3711v1.f8859k < 500))) {
                    c3711v12.f8860l = c3711v1.f8861m;
                }
            } else {
                Bundle m17241a = m17241a(abstractC3628m1.f8576b, 0L);
                if (m17241a != null) {
                    AppLog.onEventV3("play_session", m17241a);
                }
                this.f8558e = 0L;
                this.f8562i = c3711v12.f8576b;
                arrayList.add(abstractC3628m1);
                if (c3711v12.f8861m.contains(":")) {
                    this.f8555b = c3711v12;
                } else {
                    this.f8556c = c3711v12;
                    this.f8555b = null;
                }
            }
        } else if (!(abstractC3628m1 instanceof C3626b)) {
            arrayList.add(abstractC3628m1);
        }
        m17240a(abstractC3628m1);
        return z2;
    }

    /* renamed from: a */
    public synchronized C3694t1 m17238a(AbstractC3628m1 abstractC3628m1, ArrayList<AbstractC3628m1> arrayList, boolean z) {
        C3694t1 c3694t1;
        long j = abstractC3628m1 instanceof C3626b ? -1L : abstractC3628m1.f8576b;
        this.f8557d = UUID.randomUUID().toString();
        if (z && !this.f8554a.f8483v && TextUtils.isEmpty(this.f8565l)) {
            this.f8565l = this.f8557d;
        }
        f8552n = 10000L;
        this.f8560g = j;
        this.f8561h = z;
        this.f8562i = 0L;
        if (z) {
            Calendar calendar = Calendar.getInstance();
            StringBuilder m17349a = C3535a.m17349a("");
            m17349a.append(calendar.get(1));
            m17349a.append(calendar.get(2));
            m17349a.append(calendar.get(5));
            String sb = m17349a.toString();
            C3726x c3726x = this.f8554a.f8465d;
            if (TextUtils.isEmpty(this.f8564k)) {
                this.f8564k = c3726x.f8899d.getString("session_last_day", "");
                this.f8563j = c3726x.f8899d.getInt("session_order", 0);
            }
            if (sb.equals(this.f8564k)) {
                this.f8563j++;
            } else {
                this.f8564k = sb;
                this.f8563j = 1;
            }
            c3726x.f8899d.edit().putString("session_last_day", sb).putInt("session_order", this.f8563j).apply();
            this.f8559f = 0;
        }
        if (j != -1) {
            c3694t1 = new C3694t1();
            c3694t1.f8578d = this.f8557d;
            c3694t1.f8818m = true ^ this.f8561h;
            c3694t1.f8577c = m17237b();
            c3694t1.m17233a(this.f8560g);
            c3694t1.f8817l = this.f8554a.f8469h.m17008f();
            c3694t1.f8816k = this.f8554a.f8469h.m17010e();
            c3694t1.f8579e = f8551m;
            c3694t1.f8580f = AppLog.getUserUniqueID();
            c3694t1.f8581g = AppLog.getAbSdkVersion();
            arrayList.add(c3694t1);
        } else {
            c3694t1 = null;
        }
        if (AppLog.sLaunchFrom <= 0) {
            AppLog.sLaunchFrom = 6;
        }
        StringBuilder m17349a2 = C3535a.m17349a("startSession, ");
        m17349a2.append(this.f8561h ? "fg" : "bg");
        m17349a2.append(", ");
        m17349a2.append(this.f8557d);
        C3704u2.m17108a(m17349a2.toString(), (Throwable) null);
        return c3694t1;
    }
}
