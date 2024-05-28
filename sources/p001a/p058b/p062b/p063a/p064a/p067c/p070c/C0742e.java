package p001a.p058b.p062b.p063a.p064a.p067c.p070c;

import android.content.Context;
import android.content.SharedPreferences;
import com.baidu.uaq.agent.android.AgentConfig;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import com.baidu.uaq.agent.android.util.C3324f;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p066b.C0721b;
import p001a.p058b.p062b.p063a.p064a.p067c.C0723a;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p081k.C0771c;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: a.b.b.a.a.c.c.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0742e {

    /* renamed from: a */
    public static final InterfaceC3321a f2273a = C0749a.f2299a;

    /* renamed from: b */
    public static final UAQ f2274b = UAQ.getInstance();

    /* renamed from: c */
    public static C0723a f2275c;

    /* renamed from: d */
    public APMUploadConfigure f2276d;

    /* renamed from: e */
    public C0739b f2277e;

    /* renamed from: f */
    public final C3324f f2278f;

    /* renamed from: g */
    public long f2279g;

    /* renamed from: h */
    public long f2280h;

    /* renamed from: i */
    public SharedPreferences f2281i;

    /* renamed from: j */
    public SharedPreferences.Editor f2282j;

    /* renamed from: k */
    public Context f2283k;

    /* renamed from: l */
    public ArrayList<String> f2284l = new ArrayList<>();

    /* renamed from: m */
    public EnumC0743a f2285m;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.b.b.a.a.c.c.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC0743a {
        CONNECTEDWIFI,
        CONNECTEDNOTWIFI,
        DISCONNECTED
    }

    public C0742e(Context context, APMUploadConfigure aPMUploadConfigure) {
        this.f2283k = context;
        this.f2276d = aPMUploadConfigure;
        this.f2278f = new C3324f(context, aPMUploadConfigure.getUploadName());
        this.f2281i = context.getSharedPreferences("com.baidu.uaq.android.agent.v2_customer_", 0);
    }

    /* renamed from: a */
    public static void m22279a(JSONObject jSONObject) {
        boolean z;
        AgentConfig.Builder newBuilder = f2274b.getConfig().newBuilder();
        try {
            if (f2274b.getConfig().isNativeControlDRP() || !jSONObject.has("dataReportPeriod") || f2274b.getConfig().getDataReportPeriod() == jSONObject.getLong("dataReportPeriod")) {
                z = false;
            } else {
                newBuilder.dataReportPeriod(jSONObject.getLong("dataReportPeriod"));
                InterfaceC3321a interfaceC3321a = f2273a;
                StringBuilder sb = new StringBuilder();
                sb.append("Update dataReportPeriod: ");
                sb.append(jSONObject.getLong("dataReportPeriod"));
                interfaceC3321a.mo17428D(sb.toString());
                z = true;
            }
            if (jSONObject.has("dataReportLimit") && f2274b.getConfig().getDataReportLimit() != jSONObject.getLong("dataReportLimit")) {
                newBuilder.dataReportLimit(jSONObject.getLong("dataReportLimit"));
                InterfaceC3321a interfaceC3321a2 = f2273a;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Update dataReportLimit: ");
                sb2.append(jSONObject.getLong("dataReportLimit"));
                interfaceC3321a2.mo17428D(sb2.toString());
                z = true;
            }
            if (jSONObject.has("responseBodyLimit") && f2274b.getConfig().getResponseBodyLimit() != jSONObject.getLong("responseBodyLimit")) {
                newBuilder.responseBodyLimit(jSONObject.getLong("responseBodyLimit"));
                InterfaceC3321a interfaceC3321a3 = f2273a;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Update responseBodyLimit: ");
                sb3.append(jSONObject.getLong("responseBodyLimit"));
                interfaceC3321a3.mo17428D(sb3.toString());
                z = true;
            }
            if (jSONObject.has("sampleRate") && f2274b.getConfig().getSampleRate() != jSONObject.getDouble("sampleRate")) {
                newBuilder.sampleRate(jSONObject.getDouble("sampleRate"));
                InterfaceC3321a interfaceC3321a4 = f2273a;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("Update sampleRate: ");
                sb4.append(jSONObject.getDouble("sampleRate"));
                interfaceC3321a4.mo17428D(sb4.toString());
                z = true;
            }
            if (jSONObject.has("harvestableCacheLimit") && f2274b.getConfig().getHarvestableCacheLimit() != jSONObject.getInt("harvestableCacheLimit")) {
                newBuilder.harvestableCacheLimit(jSONObject.getInt("harvestableCacheLimit"));
                InterfaceC3321a interfaceC3321a5 = f2273a;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Update harvestableCacheLimit: ");
                sb5.append(jSONObject.getInt("harvestableCacheLimit"));
                interfaceC3321a5.mo17428D(sb5.toString());
                z = true;
            }
            if (jSONObject.has("samplerFreq") && f2274b.getConfig().getSamplerFreq() != jSONObject.getLong("samplerFreq")) {
                newBuilder.samplerFreq(jSONObject.getLong("samplerFreq"));
                InterfaceC3321a interfaceC3321a6 = f2273a;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("Update samplerFreq: ");
                sb6.append(jSONObject.getLong("samplerFreq"));
                interfaceC3321a6.mo17428D(sb6.toString());
                z = true;
            }
            if (z) {
                f2274b.reconfig(newBuilder.build());
            }
        } catch (JSONException e) {
            f2273a.mo17426a("Caught error while updateAgentConfig: ", e);
            C0735a.m22302a(e);
        }
    }

    /* renamed from: a */
    public final C0723a m22284a(C0723a c0723a) {
        if (c0723a.m22312b().f2221o) {
            return c0723a;
        }
        try {
            String m22240a = C0771c.m22240a(c0723a.m22312b().f2210d);
            String m22240a2 = C0771c.m22240a(c0723a.m22312b().f2212f);
            String m22240a3 = C0771c.m22240a(c0723a.m22312b().f2215i);
            String m22240a4 = C0771c.m22240a(c0723a.m22312b().f2211e);
            String m22240a5 = C0771c.m22240a(c0723a.m22312b().f2217k);
            c0723a.m22312b().f2210d = m22240a;
            c0723a.m22312b().f2212f = m22240a2;
            c0723a.m22312b().f2215i = m22240a3;
            c0723a.m22312b().f2211e = m22240a4;
            c0723a.m22312b().f2217k = m22240a5;
            c0723a.m22312b().f2221o = true;
            return c0723a;
        } catch (Exception e) {
            f2273a.mo17426a("Caught error while data2AES: ", e);
            C0735a.m22302a(e);
            return c0723a;
        }
    }

    /* renamed from: a */
    public final String m22281a(String str) {
        return C3324f.InterfaceC3325a.f8172b + str + "]}";
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x0426: MOVE  (r163 I:??[OBJECT, ARRAY]) = (r391 I:??[OBJECT, ARRAY]), expected to be less than 18
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* renamed from: a */
    public void m22282a(com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure r17) {
        /*
            Method dump skipped, instructions count: 1307
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0742e.m22282a(com.baidu.uaq.agent.android.customtransmission.APMUploadConfigure):void");
    }

    /* renamed from: a */
    public final void m22280a(String str, String str2) {
        this.f2278f.m17420a(str2, str);
        InterfaceC3321a interfaceC3321a = f2273a;
        StringBuilder m24437a = outline.m24437a("localizeData4APM, localized file size: ");
        m24437a.append(this.f2278f.m17423a().size());
        interfaceC3321a.mo17428D(m24437a.toString());
    }

    /* renamed from: b */
    public void m22278b() {
        if (this.f2276d.getUploadName().equals("APMPerformanceConfigurationName")) {
            f2275c.f2196f.f2245c.m22301a();
        } else {
            this.f2284l.clear();
        }
    }

    /* renamed from: b */
    public final void m22277b(String str) {
        Iterator<String> it = this.f2284l.iterator();
        while (it.hasNext()) {
            this.f2278f.m17420a(str, it.next());
            InterfaceC3321a interfaceC3321a = f2273a;
            StringBuilder m24437a = outline.m24437a("Log Persist, fileList: ");
            m24437a.append(this.f2278f.m17423a().size());
            interfaceC3321a.mo17428D(m24437a.toString());
        }
    }

    /* renamed from: c */
    public final long m22276c() {
        int i = C0741d.f2272a[this.f2285m.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return 0L;
            }
            return this.f2276d.getMaxBytes4g();
        }
        return this.f2276d.getMaxBytesWifi();
    }

    /* renamed from: d */
    public final String m22275d() {
        StringBuilder sb;
        String str;
        int i = C0741d.f2272a[this.f2285m.ordinal()];
        if (i == 1) {
            sb = new StringBuilder();
            sb.append(this.f2276d.getUploadName());
            str = "_dataReportLimitWIFI";
        } else if (i != 2) {
            return null;
        } else {
            sb = new StringBuilder();
            sb.append(this.f2276d.getUploadName());
            str = "_dataReportLimitNOTWIFI";
        }
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: e */
    public final void m22274e() {
        this.f2282j = this.f2281i.edit();
        String m22275d = m22275d();
        if (m22275d == null) {
            return;
        }
        InterfaceC3321a interfaceC3321a = f2273a;
        StringBuilder m24437a = outline.m24437a("saveMaxBytesState uploaded bytes:");
        m24437a.append(this.f2280h);
        m24437a.append(" key:");
        m24437a.append(m22275d);
        m24437a.append(" uploadStartTime:");
        m24437a.append(this.f2279g);
        interfaceC3321a.mo17428D(m24437a.toString());
        this.f2282j.putLong(m22275d, this.f2280h);
        this.f2282j.apply();
    }

    /* renamed from: f */
    public final String m22273f() {
        return this.f2276d.getUploadName() + "apmUploadStartDate";
    }

    /* JADX WARN: Code restructure failed: missing block: B:59:0x0153, code lost:
        if (r9.f2207d == r2.f2207d) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0158 A[Catch: JSONException -> 0x0173, TryCatch #0 {JSONException -> 0x0173, blocks: (B:19:0x0090, B:21:0x0097, B:23:0x00a1, B:25:0x00aa, B:27:0x00af, B:30:0x00c1, B:32:0x00c7, B:34:0x00cf, B:36:0x00d3, B:39:0x00fa, B:41:0x0100, B:43:0x0108, B:45:0x010c, B:47:0x0124, B:49:0x012a, B:62:0x0158, B:63:0x015d, B:65:0x0164, B:66:0x0167, B:68:0x016b, B:53:0x013c, B:56:0x0145, B:58:0x014d), top: B:74:0x0090 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0164 A[Catch: JSONException -> 0x0173, TryCatch #0 {JSONException -> 0x0173, blocks: (B:19:0x0090, B:21:0x0097, B:23:0x00a1, B:25:0x00aa, B:27:0x00af, B:30:0x00c1, B:32:0x00c7, B:34:0x00cf, B:36:0x00d3, B:39:0x00fa, B:41:0x0100, B:43:0x0108, B:45:0x010c, B:47:0x0124, B:49:0x012a, B:62:0x0158, B:63:0x015d, B:65:0x0164, B:66:0x0167, B:68:0x016b, B:53:0x013c, B:56:0x0145, B:58:0x014d), top: B:74:0x0090 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m22283a(p001a.p058b.p062b.p063a.p064a.p067c.C0734b r9) {
        /*
            Method dump skipped, instructions count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p067c.p070c.C0742e.m22283a(a.b.b.a.a.c.b):boolean");
    }

    /* renamed from: a */
    public void m22285a() {
        if (this.f2276d.getUploadName().equals("APMPerformanceConfigurationName")) {
            String obj = m22284a(f2275c).mo17432az().toString();
            if (obj != null) {
                InterfaceC3321a interfaceC3321a = f2273a;
                StringBuilder m24437a = outline.m24437a("harvester exec for :");
                m24437a.append(this.f2276d.getUploadName());
                m24437a.append(", network is not connected, choose to localize data");
                interfaceC3321a.mo17428D(m24437a.toString());
                m22280a(obj, this.f2276d.getUploadName());
                return;
            }
            return;
        }
        ArrayList<String> m22319a = C0721b.m22319a(this.f2276d.getUploadName(), Boolean.valueOf(this.f2276d.isEnableRetransmission()));
        if (m22319a != null) {
            this.f2284l.addAll(m22319a);
            InterfaceC3321a interfaceC3321a2 = f2273a;
            StringBuilder m24437a2 = outline.m24437a("harvester exec for :");
            m24437a2.append(this.f2276d.getUploadName());
            m24437a2.append(", network is not connected, choose to localize data");
            interfaceC3321a2.mo17428D(m24437a2.toString());
            m22277b(this.f2276d.getUploadName());
            C0721b.m22315b(this.f2276d.getUploadName(), Boolean.valueOf(this.f2276d.isEnableRetransmission()));
        }
    }
}
