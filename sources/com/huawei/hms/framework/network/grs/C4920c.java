package com.huawei.hms.framework.network.grs;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.ExecutorsUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import com.huawei.hms.framework.network.grs.p216f.C4927b;
import com.huawei.hms.framework.network.grs.p217g.C4942h;
import com.huawei.hms.framework.network.grs.p217g.C4945i;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import com.huawei.hms.framework.network.grs.p220h.C4951a;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.huawei.hms.framework.network.grs.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4920c {

    /* renamed from: i */
    private static final String f11208i = "c";

    /* renamed from: j */
    private static final ExecutorService f11209j = ExecutorsUtils.newSingleThreadExecutor("GRS_GrsClient-Init");

    /* renamed from: k */
    private static AtomicInteger f11210k = new AtomicInteger(0);

    /* renamed from: a */
    private GrsBaseInfo f11211a;

    /* renamed from: b */
    private Context f11212b;

    /* renamed from: c */
    private C4942h f11213c;

    /* renamed from: d */
    private C4923a f11214d;

    /* renamed from: e */
    private C4925c f11215e;

    /* renamed from: f */
    private C4925c f11216f;

    /* renamed from: g */
    private C4916a f11217g;

    /* renamed from: h */
    private FutureTask<Boolean> f11218h;

    /* renamed from: com.huawei.hms.framework.network.grs.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class CallableC4921a implements Callable<Boolean> {

        /* renamed from: a */
        final /* synthetic */ Context f11219a;

        /* renamed from: b */
        final /* synthetic */ GrsBaseInfo f11220b;

        CallableC4921a(Context context, GrsBaseInfo grsBaseInfo) {
            this.f11219a = context;
            this.f11220b = grsBaseInfo;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            C4920c.this.f11213c = new C4942h();
            C4920c c4920c = C4920c.this;
            Context context = this.f11219a;
            c4920c.f11215e = new C4925c(context, GrsApp.getInstance().getBrand("_") + "share_pre_grs_conf_");
            C4920c c4920c2 = C4920c.this;
            Context context2 = this.f11219a;
            c4920c2.f11216f = new C4925c(context2, GrsApp.getInstance().getBrand("_") + "share_pre_grs_services_");
            C4920c c4920c3 = C4920c.this;
            c4920c3.f11214d = new C4923a(c4920c3.f11215e, C4920c.this.f11216f, C4920c.this.f11213c);
            C4920c c4920c4 = C4920c.this;
            c4920c4.f11217g = new C4916a(c4920c4.f11211a, C4920c.this.f11214d, C4920c.this.f11213c, C4920c.this.f11216f);
            if (C4920c.f11210k.incrementAndGet() <= 2 || C4927b.m14963a(this.f11219a.getPackageName(), C4920c.this.f11211a) == null) {
                new C4927b(this.f11219a, this.f11220b, true).m14964a(this.f11220b);
            }
            String m14877c = new C4949c(this.f11220b, this.f11219a).m14877c();
            String str = C4920c.f11208i;
            Logger.m15047v(str, "scan serviceSet is: " + m14877c);
            String m14990a = C4920c.this.f11216f.m14990a("services", "");
            String m14885a = C4945i.m14885a(m14990a, m14877c);
            if (!TextUtils.isEmpty(m14885a)) {
                C4920c.this.f11216f.m14988b("services", m14885a);
                String str2 = C4920c.f11208i;
                Logger.m15049i(str2, "postList is:" + StringUtils.anonymizeMessage(m14885a));
                String str3 = C4920c.f11208i;
                Logger.m15049i(str3, "currentServices:" + StringUtils.anonymizeMessage(m14990a));
                if (!m14885a.equals(m14990a)) {
                    C4920c.this.f11213c.m14887a(C4920c.this.f11211a.getGrsParasKey(true, true, this.f11219a));
                    C4920c.this.f11213c.m14888a(new C4949c(this.f11220b, this.f11219a), (String) null, C4920c.this.f11216f);
                }
            }
            C4920c c4920c5 = C4920c.this;
            c4920c5.m15015a(c4920c5.f11215e.m14993a());
            C4920c.this.f11214d.m14997b(this.f11220b, this.f11219a);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4920c(Context context, GrsBaseInfo grsBaseInfo) {
        this.f11218h = null;
        this.f11212b = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        m15027a(grsBaseInfo);
        GrsBaseInfo grsBaseInfo2 = this.f11211a;
        this.f11218h = new FutureTask<>(new CallableC4921a(this.f11212b, grsBaseInfo2));
        f11209j.execute(this.f11218h);
        Logger.m15048i(f11208i, "GrsClient Instance is init, GRS SDK version: %s, GrsBaseInfoParam: app_name=%s, reg_country=%s, ser_country=%s, issue_country=%s", C4951a.m14865a(), grsBaseInfo2.getAppName(), grsBaseInfo.getRegCountry(), grsBaseInfo.getSerCountry(), grsBaseInfo.getIssueCountry());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4920c(GrsBaseInfo grsBaseInfo) {
        this.f11218h = null;
        m15027a(grsBaseInfo);
    }

    /* renamed from: a */
    private void m15027a(GrsBaseInfo grsBaseInfo) {
        try {
            this.f11211a = grsBaseInfo.m24475clone();
        } catch (CloneNotSupportedException e) {
            Logger.m15044w(f11208i, "GrsClient catch CloneNotSupportedException", e);
            this.f11211a = grsBaseInfo.copy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15015a(Map<String, ?> map) {
        if (map == null || map.isEmpty()) {
            Logger.m15047v(f11208i, "sp's content is empty.");
            return;
        }
        for (String str : map.keySet()) {
            if (str.endsWith("time")) {
                String m14990a = this.f11215e.m14990a(str, "");
                long j = 0;
                if (!TextUtils.isEmpty(m14990a) && m14990a.matches("\\d+")) {
                    try {
                        j = Long.parseLong(m14990a);
                    } catch (NumberFormatException e) {
                        Logger.m15044w(f11208i, "convert expire time from String to Long catch NumberFormatException.", e);
                    }
                }
                if (!m15028a(j)) {
                    Logger.m15049i(f11208i, "init interface auto clear some invalid sp's data.");
                    String substring = str.substring(0, str.length() - 4);
                    this.f11215e.m14991a(substring);
                    this.f11215e.m14991a(str);
                    this.f11215e.m14991a(substring + "ETag");
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m15028a(long j) {
        return System.currentTimeMillis() - j <= 604800000;
    }

    /* renamed from: e */
    private boolean m15007e() {
        String str;
        String str2;
        FutureTask<Boolean> futureTask = this.f11218h;
        if (futureTask == null) {
            return false;
        }
        try {
            return futureTask.get(8L, TimeUnit.SECONDS).booleanValue();
        } catch (InterruptedException e) {
            e = e;
            str = f11208i;
            str2 = "init compute task interrupted.";
            Logger.m15044w(str, str2, e);
            return false;
        } catch (CancellationException unused) {
            Logger.m15049i(f11208i, "init compute task canceled.");
            return false;
        } catch (ExecutionException e2) {
            e = e2;
            str = f11208i;
            str2 = "init compute task failed.";
            Logger.m15044w(str, str2, e);
            return false;
        } catch (TimeoutException unused2) {
            Logger.m15045w(f11208i, "init compute task timed out");
            return false;
        } catch (Exception e3) {
            e = e3;
            str = f11208i;
            str2 = "init compute task occur unknown Exception";
            Logger.m15044w(str, str2, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public String m15017a(String str, String str2) {
        if (this.f11211a == null || str == null || str2 == null) {
            Logger.m15045w(f11208i, "invalid para!");
            return null;
        } else if (m15007e()) {
            return this.f11217g.m15034a(str, str2, this.f11212b);
        } else {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public Map<String, String> m15019a(String str) {
        if (this.f11211a != null && str != null) {
            return m15007e() ? this.f11217g.m15038a(str, this.f11212b) : new HashMap();
        }
        Logger.m15045w(f11208i, "invalid para!");
        return new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15029a() {
        if (m15007e()) {
            String grsParasKey = this.f11211a.getGrsParasKey(true, true, this.f11212b);
            this.f11215e.m14991a(grsParasKey);
            C4925c c4925c = this.f11215e;
            c4925c.m14991a(grsParasKey + "time");
            C4925c c4925c2 = this.f11215e;
            c4925c2.m14991a(grsParasKey + "ETag");
            this.f11213c.m14887a(grsParasKey);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15018a(String str, IQueryUrlsCallBack iQueryUrlsCallBack) {
        if (iQueryUrlsCallBack == null) {
            Logger.m15045w(f11208i, "IQueryUrlsCallBack is must not null for process continue.");
        } else if (this.f11211a == null || str == null) {
            iQueryUrlsCallBack.onCallBackFail(-6);
        } else if (m15007e()) {
            this.f11217g.m15037a(str, iQueryUrlsCallBack, this.f11212b);
        } else {
            Logger.m15049i(f11208i, "grs init task has not completed.");
            iQueryUrlsCallBack.onCallBackFail(-7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15016a(String str, String str2, IQueryUrlCallBack iQueryUrlCallBack) {
        if (iQueryUrlCallBack == null) {
            Logger.m15045w(f11208i, "IQueryUrlCallBack is must not null for process continue.");
        } else if (this.f11211a == null || str == null || str2 == null) {
            iQueryUrlCallBack.onCallBackFail(-6);
        } else if (m15007e()) {
            this.f11217g.m15033a(str, str2, iQueryUrlCallBack, this.f11212b);
        } else {
            Logger.m15049i(f11208i, "grs init task has not completed.");
            iQueryUrlCallBack.onCallBackFail(-7);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m15020a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C4920c.class == obj.getClass() && (obj instanceof C4920c)) {
            return this.f11211a.compare(((C4920c) obj).f11211a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m15014b() {
        GrsBaseInfo grsBaseInfo;
        Context context;
        if (!m15007e() || (grsBaseInfo = this.f11211a) == null || (context = this.f11212b) == null) {
            return false;
        }
        this.f11214d.m15003a(grsBaseInfo, context);
        return true;
    }
}
