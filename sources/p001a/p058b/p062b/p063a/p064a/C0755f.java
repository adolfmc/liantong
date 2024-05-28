package p001a.p058b.p062b.p063a.p064a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p067c.p068a.C0725b;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* renamed from: a.b.b.a.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0755f {

    /* renamed from: a */
    public static final InterfaceC3321a f2312a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2313b = UAQ.getInstance();

    /* renamed from: c */
    public final SharedPreferences f2314c;

    /* renamed from: d */
    public SharedPreferences.Editor f2315d;

    /* renamed from: e */
    public final Lock f2316e = new ReentrantLock();

    /* renamed from: f */
    public C0725b f2317f = new C0725b();

    /* renamed from: g */
    public long f2318g;

    @SuppressLint({"CommitPrefEdits"})
    public C0755f(Context context) {
        JSONArray jSONArray;
        StringBuilder m24437a = outline.m24437a("com.baidu.uaq.android.agent.v2_");
        m24437a.append(context.getPackageName());
        this.f2314c = context.getSharedPreferences(m24437a.toString(), 0);
        int[] iArr = new int[2];
        C0725b c0725b = null;
        String string = this.f2314c.getString("dataToken", null);
        if (string != null) {
            try {
                jSONArray = (JSONArray) new JSONTokener(string).nextValue();
            } catch (JSONException e) {
                f2312a.mo17426a("Caught error while getDataToken: ", e);
            }
            if (jSONArray != null) {
                iArr[0] = jSONArray.getInt(0);
                iArr[1] = jSONArray.getInt(1);
                c0725b = new C0725b(iArr[0], iArr[1]);
            }
        }
        if (c0725b != null) {
            C0725b c0725b2 = this.f2317f;
            c0725b2.f2206c = c0725b.f2206c;
            c0725b2.f2207d = c0725b.f2207d;
        }
        m22268a();
        if (!this.f2314c.contains("dataReportLimit")) {
            m22264b(f2313b.getConfig().getDataReportLimit());
        }
        this.f2314c.getLong("dataReportLimit", 0L);
        if (!this.f2314c.contains("lastUpdateTimestamp")) {
            m22263c(System.currentTimeMillis());
        }
        this.f2314c.getLong("lastUpdateTimestamp", 0L);
    }

    /* renamed from: a */
    public final String m22266a(String str) {
        return this.f2314c.getString(str, null);
    }

    /* renamed from: a */
    public final void m22268a() {
        if (this.f2314c.contains("dataReportPeriod")) {
            this.f2318g = this.f2314c.getLong("dataReportPeriod", 0L);
        }
    }

    /* renamed from: a */
    public void m22267a(long j) {
        InterfaceC3321a interfaceC3321a = f2312a;
        interfaceC3321a.mo17428D("!! saving dataReportPeriod: " + j);
        m22265a("dataReportPeriod", j);
    }

    /* renamed from: a */
    public final void m22265a(String str, long j) {
        this.f2316e.lock();
        try {
            try {
                if (this.f2315d == null) {
                    this.f2315d = this.f2314c.edit();
                }
                this.f2315d.putLong(str, j);
                this.f2315d.apply();
            } catch (Exception e) {
                f2312a.mo17426a("Caught error while SavedState save: ", e);
            }
        } finally {
            this.f2316e.unlock();
        }
    }

    /* renamed from: b */
    public void m22264b(long j) {
        InterfaceC3321a interfaceC3321a = f2312a;
        interfaceC3321a.mo17428D("!! saving dataReportLimit: " + j);
        m22265a("dataReportLimit", j);
    }

    /* renamed from: c */
    public void m22263c(long j) {
        InterfaceC3321a interfaceC3321a = f2312a;
        interfaceC3321a.mo17428D("!! saving lastUpdateTimestamp: " + j);
        m22265a("lastUpdateTimestamp", j);
    }
}
