package com.xiaomi.clientreport.manager;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.C11052a;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11194bi;
import com.xiaomi.push.C11195bj;
import com.xiaomi.push.C11197bl;
import com.xiaomi.push.C11200bm;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.RunnableC11196bk;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.xiaomi.clientreport.manager.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11053a {

    /* renamed from: a */
    private static final int f21287a;

    /* renamed from: a */
    private static volatile C11053a f21288a;

    /* renamed from: a */
    private Context f21289a;

    /* renamed from: a */
    private Config f21290a;

    /* renamed from: a */
    private IEventProcessor f21291a;

    /* renamed from: a */
    private IPerfProcessor f21292a;

    /* renamed from: a */
    private String f21293a;

    /* renamed from: a */
    private ExecutorService f21295a = Executors.newSingleThreadExecutor();

    /* renamed from: a */
    private HashMap<String, HashMap<String, C11052a>> f21294a = new HashMap<>();

    /* renamed from: b */
    private HashMap<String, ArrayList<C11052a>> f21296b = new HashMap<>();

    static {
        f21287a = C11469j.m2974a() ? 30 : 10;
    }

    private C11053a(Context context) {
        this.f21289a = context;
    }

    /* renamed from: a */
    public static C11053a m5261a(Context context) {
        if (f21288a == null) {
            synchronized (C11053a.class) {
                if (f21288a == null) {
                    f21288a = new C11053a(context);
                }
            }
        }
        return f21288a;
    }

    /* renamed from: a */
    public synchronized Config m5264a() {
        if (this.f21290a == null) {
            this.f21290a = Config.defaultConfig(this.f21289a);
        }
        return this.f21290a;
    }

    /* renamed from: a */
    public void m5263a() {
        m5261a(this.f21289a).m5240f();
        m5261a(this.f21289a).m5239g();
    }

    /* renamed from: a */
    public void m5251a(String str) {
        this.f21293a = str;
    }

    /* renamed from: a */
    public void m5260a(Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        this.f21290a = config;
        this.f21291a = iEventProcessor;
        this.f21292a = iPerfProcessor;
        this.f21291a.setEventMap(this.f21296b);
        this.f21292a.setPerfMap(this.f21294a);
    }

    /* renamed from: a */
    public void m5250a(boolean z, boolean z2, long j, long j2) {
        Config config = this.f21290a;
        if (config != null) {
            if (z == config.isEventUploadSwitchOpen() && z2 == this.f21290a.isPerfUploadSwitchOpen() && j == this.f21290a.getEventUploadFrequency() && j2 == this.f21290a.getPerfUploadFrequency()) {
                return;
            }
            long eventUploadFrequency = this.f21290a.getEventUploadFrequency();
            long perfUploadFrequency = this.f21290a.getPerfUploadFrequency();
            Config build = Config.getBuilder().setAESKey(C11197bl.m4718a(this.f21289a)).setEventEncrypted(this.f21290a.isEventEncrypted()).setEventUploadSwitchOpen(z).setEventUploadFrequency(j).setPerfUploadSwitchOpen(z2).setPerfUploadFrequency(j2).build(this.f21289a);
            this.f21290a = build;
            if (!this.f21290a.isEventUploadSwitchOpen()) {
                C11134ae.m4937a(this.f21289a).m4925a("100886");
            } else if (eventUploadFrequency != build.getEventUploadFrequency()) {
                AbstractC11049b.m5270c(this.f21289a.getPackageName() + "reset event job " + build.getEventUploadFrequency());
                m5240f();
            }
            if (!this.f21290a.isPerfUploadSwitchOpen()) {
                C11134ae.m4937a(this.f21289a).m4925a("100887");
            } else if (perfUploadFrequency != build.getPerfUploadFrequency()) {
                AbstractC11049b.m5270c(this.f21289a.getPackageName() + " reset perf job " + build.getPerfUploadFrequency());
                m5239g();
            }
        }
    }

    /* renamed from: a */
    private void m5252a(C11134ae.AbstractRunnableC11137a abstractRunnableC11137a, int i) {
        C11134ae.m4937a(this.f21289a).m4924b(abstractRunnableC11137a, i);
    }

    /* renamed from: a */
    public void m5259a(final EventClientReport eventClientReport) {
        if (m5264a().isEventUploadSwitchOpen()) {
            this.f21295a.execute(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.1
                @Override // java.lang.Runnable
                public void run() {
                    C11053a.this.m5247b(eventClientReport);
                }
            });
        }
    }

    /* renamed from: a */
    public void m5258a(final PerfClientReport perfClientReport) {
        if (m5264a().isPerfUploadSwitchOpen()) {
            this.f21295a.execute(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.2
                @Override // java.lang.Runnable
                public void run() {
                    C11053a.this.m5246b(perfClientReport);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m5247b(EventClientReport eventClientReport) {
        IEventProcessor iEventProcessor = this.f21291a;
        if (iEventProcessor != null) {
            iEventProcessor.mo5225a(eventClientReport);
            if (m5265a() >= 10) {
                m5242d();
                C11134ae.m4937a(this.f21289a).m4925a("100888");
                return;
            }
            m5252a(new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.clientreport.manager.a.3
                @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
                /* renamed from: a */
                public String mo2289a() {
                    return "100888";
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (C11053a.this.m5265a() > 0) {
                        C11053a.this.f21295a.execute(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                C11053a.this.m5242d();
                            }
                        });
                    }
                }
            }, f21287a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m5246b(PerfClientReport perfClientReport) {
        IPerfProcessor iPerfProcessor = this.f21292a;
        if (iPerfProcessor != null) {
            iPerfProcessor.mo5225a(perfClientReport);
            if (m5249b() >= 10) {
                m5241e();
                C11134ae.m4937a(this.f21289a).m4925a("100889");
                return;
            }
            m5252a(new C11134ae.AbstractRunnableC11137a() { // from class: com.xiaomi.clientreport.manager.a.4
                @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
                /* renamed from: a */
                public String mo2289a() {
                    return "100889";
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (C11053a.this.m5249b() > 0) {
                        C11053a.this.f21295a.execute(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                C11053a.this.m5241e();
                            }
                        });
                    }
                }
            }, f21287a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m5242d() {
        try {
            this.f21291a.mo5224b();
        } catch (Exception e) {
            AbstractC11049b.m5268d("we: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m5241e() {
        try {
            this.f21292a.mo5224b();
        } catch (Exception e) {
            AbstractC11049b.m5268d("wp: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public int m5265a() {
        HashMap<String, ArrayList<C11052a>> hashMap = this.f21296b;
        if (hashMap != null) {
            int i = 0;
            for (String str : hashMap.keySet()) {
                ArrayList<C11052a> arrayList = this.f21296b.get(str);
                i += arrayList != null ? arrayList.size() : 0;
            }
            return i;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public int m5249b() {
        HashMap<String, HashMap<String, C11052a>> hashMap = this.f21294a;
        int i = 0;
        if (hashMap != null) {
            for (String str : hashMap.keySet()) {
                HashMap<String, C11052a> hashMap2 = this.f21294a.get(str);
                if (hashMap2 != null) {
                    for (String str2 : hashMap2.keySet()) {
                        C11052a c11052a = hashMap2.get(str2);
                        if (c11052a instanceof PerfClientReport) {
                            i = (int) (i + ((PerfClientReport) c11052a).perfCounts);
                        }
                    }
                }
            }
        }
        return i;
    }

    /* renamed from: b */
    public void m5248b() {
        if (m5264a().isEventUploadSwitchOpen()) {
            RunnableC11196bk runnableC11196bk = new RunnableC11196bk();
            runnableC11196bk.m4721a(this.f21289a);
            runnableC11196bk.m4720a(this.f21291a);
            this.f21295a.execute(runnableC11196bk);
        }
    }

    /* renamed from: c */
    public void m5243c() {
        if (m5264a().isPerfUploadSwitchOpen()) {
            RunnableC11196bk runnableC11196bk = new RunnableC11196bk();
            runnableC11196bk.m4720a(this.f21292a);
            runnableC11196bk.m4721a(this.f21289a);
            this.f21295a.execute(runnableC11196bk);
        }
    }

    /* renamed from: f */
    private void m5240f() {
        if (m5261a(this.f21289a).m5264a().isEventUploadSwitchOpen()) {
            final C11194bi c11194bi = new C11194bi(this.f21289a);
            int eventUploadFrequency = (int) m5261a(this.f21289a).m5264a().getEventUploadFrequency();
            if (eventUploadFrequency < 1800) {
                eventUploadFrequency = 1800;
            }
            if (System.currentTimeMillis() - C11200bm.m4710a(this.f21289a).m4709a("sp_client_report_status", "event_last_upload_time", 0L) > eventUploadFrequency * 1000) {
                C11134ae.m4937a(this.f21289a).m4927a(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.5
                    @Override // java.lang.Runnable
                    public void run() {
                        c11194bi.run();
                    }
                }, 10);
            }
            synchronized (C11053a.class) {
                if (!C11134ae.m4937a(this.f21289a).m4934a((C11134ae.AbstractRunnableC11137a) c11194bi, eventUploadFrequency)) {
                    C11134ae.m4937a(this.f21289a).m4925a("100886");
                    C11134ae.m4937a(this.f21289a).m4934a((C11134ae.AbstractRunnableC11137a) c11194bi, eventUploadFrequency);
                }
            }
        }
    }

    /* renamed from: g */
    private void m5239g() {
        if (m5261a(this.f21289a).m5264a().isPerfUploadSwitchOpen()) {
            final C11195bj c11195bj = new C11195bj(this.f21289a);
            int perfUploadFrequency = (int) m5261a(this.f21289a).m5264a().getPerfUploadFrequency();
            if (perfUploadFrequency < 1800) {
                perfUploadFrequency = 1800;
            }
            if (System.currentTimeMillis() - C11200bm.m4710a(this.f21289a).m4709a("sp_client_report_status", "perf_last_upload_time", 0L) > perfUploadFrequency * 1000) {
                C11134ae.m4937a(this.f21289a).m4927a(new Runnable() { // from class: com.xiaomi.clientreport.manager.a.6
                    @Override // java.lang.Runnable
                    public void run() {
                        c11195bj.run();
                    }
                }, 15);
            }
            synchronized (C11053a.class) {
                if (!C11134ae.m4937a(this.f21289a).m4934a((C11134ae.AbstractRunnableC11137a) c11195bj, perfUploadFrequency)) {
                    C11134ae.m4937a(this.f21289a).m4925a("100887");
                    C11134ae.m4937a(this.f21289a).m4934a((C11134ae.AbstractRunnableC11137a) c11195bj, perfUploadFrequency);
                }
            }
        }
    }

    /* renamed from: a */
    public EventClientReport m5262a(int i, String str) {
        EventClientReport eventClientReport = new EventClientReport();
        eventClientReport.eventContent = str;
        eventClientReport.eventTime = System.currentTimeMillis();
        eventClientReport.eventType = i;
        eventClientReport.eventId = C11184bb.m4758a(6);
        eventClientReport.production = 1000;
        eventClientReport.reportType = 1001;
        eventClientReport.clientInterfaceId = "E100004";
        eventClientReport.setAppPackageName(this.f21289a.getPackageName());
        eventClientReport.setSdkVersion(this.f21293a);
        return eventClientReport;
    }
}
