package com.huawei.hms.framework.network.grs.p217g;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.local.model.C4959a;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p215e.C4925c;
import com.huawei.hms.framework.network.grs.p216f.C4927b;
import com.huawei.hms.framework.network.grs.p217g.p218j.C4946a;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4949c;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4950d;
import com.huawei.hms.framework.network.grs.p220h.C4954d;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;

/* renamed from: com.huawei.hms.framework.network.grs.g.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4935c {

    /* renamed from: n */
    private static final String f11261n = "c";

    /* renamed from: a */
    private final GrsBaseInfo f11262a;

    /* renamed from: b */
    private final Context f11263b;

    /* renamed from: c */
    private final C4923a f11264c;

    /* renamed from: d */
    private C4937d f11265d;

    /* renamed from: j */
    private final C4949c f11271j;

    /* renamed from: k */
    private C4950d f11272k;

    /* renamed from: e */
    private final Map<String, Future<C4937d>> f11266e = new ConcurrentHashMap(16);

    /* renamed from: f */
    private final List<C4937d> f11267f = new CopyOnWriteArrayList();

    /* renamed from: g */
    private final JSONArray f11268g = new JSONArray();

    /* renamed from: h */
    private final List<String> f11269h = new CopyOnWriteArrayList();

    /* renamed from: i */
    private final List<String> f11270i = new CopyOnWriteArrayList();

    /* renamed from: l */
    private String f11273l = "";

    /* renamed from: m */
    private long f11274m = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.framework.network.grs.g.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class CallableC4936a implements Callable<C4937d> {

        /* renamed from: a */
        final /* synthetic */ ExecutorService f11275a;

        /* renamed from: b */
        final /* synthetic */ String f11276b;

        /* renamed from: c */
        final /* synthetic */ C4925c f11277c;

        CallableC4936a(ExecutorService executorService, String str, C4925c c4925c) {
            this.f11275a = executorService;
            this.f11276b = str;
            this.f11277c = c4925c;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public C4937d call() {
            return C4935c.this.m14934b(this.f11275a, this.f11276b, this.f11277c);
        }
    }

    public C4935c(C4949c c4949c, C4923a c4923a) {
        this.f11271j = c4949c;
        this.f11262a = c4949c.m14878b();
        this.f11263b = c4949c.m14880a();
        this.f11264c = c4923a;
        m14933c();
        m14932d();
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x009c A[LOOP:0: B:3:0x0006->B:36:0x009c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0094 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.huawei.hms.framework.network.grs.p217g.C4937d m14938a(java.util.concurrent.ExecutorService r17, java.util.List<java.lang.String> r18, java.lang.String r19, com.huawei.hms.framework.network.grs.p215e.C4925c r20) {
        /*
            r16 = this;
            r9 = r16
            r10 = 0
            r0 = 0
            r12 = r0
            r11 = r10
        L6:
            int r0 = r18.size()
            if (r11 >= r0) goto La0
            r13 = r18
            java.lang.Object r0 = r13.get(r11)
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r14 = 1
            if (r1 != 0) goto L8f
            com.huawei.hms.framework.network.grs.g.a r15 = new com.huawei.hms.framework.network.grs.g.a
            android.content.Context r5 = r9.f11263b
            com.huawei.hms.framework.network.grs.GrsBaseInfo r7 = r9.f11262a
            r1 = r15
            r2 = r0
            r3 = r11
            r4 = r16
            r6 = r19
            r8 = r20
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            java.util.concurrent.Callable r1 = r15.m14947g()
            r2 = r17
            java.util.concurrent.Future r1 = r2.submit(r1)
            java.util.Map<java.lang.String, java.util.concurrent.Future<com.huawei.hms.framework.network.grs.g.d>> r3 = r9.f11266e
            r3.put(r0, r1)
            long r3 = r9.f11274m     // Catch: java.util.concurrent.TimeoutException -> L65 java.lang.InterruptedException -> L6e java.util.concurrent.ExecutionException -> L7a java.util.concurrent.CancellationException -> L86
            java.util.concurrent.TimeUnit r0 = java.util.concurrent.TimeUnit.SECONDS     // Catch: java.util.concurrent.TimeoutException -> L65 java.lang.InterruptedException -> L6e java.util.concurrent.ExecutionException -> L7a java.util.concurrent.CancellationException -> L86
            java.lang.Object r0 = r1.get(r3, r0)     // Catch: java.util.concurrent.TimeoutException -> L65 java.lang.InterruptedException -> L6e java.util.concurrent.ExecutionException -> L7a java.util.concurrent.CancellationException -> L86
            r1 = r0
            com.huawei.hms.framework.network.grs.g.d r1 = (com.huawei.hms.framework.network.grs.p217g.C4937d) r1     // Catch: java.util.concurrent.TimeoutException -> L65 java.lang.InterruptedException -> L6e java.util.concurrent.ExecutionException -> L7a java.util.concurrent.CancellationException -> L86
            if (r1 == 0) goto L84
            boolean r0 = r1.m14901o()     // Catch: java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L63
            if (r0 != 0) goto L55
            boolean r0 = r1.m14903m()     // Catch: java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L63
            if (r0 == 0) goto L84
        L55:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n     // Catch: java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L63
            java.lang.String r3 = "grs request return body is not null and is OK."
            com.huawei.hms.framework.common.Logger.m15049i(r0, r3)     // Catch: java.util.concurrent.TimeoutException -> L5d java.lang.InterruptedException -> L5f java.util.concurrent.ExecutionException -> L61 java.util.concurrent.CancellationException -> L63
            goto L78
        L5d:
            r12 = r1
            goto L65
        L5f:
            r0 = move-exception
            goto L70
        L61:
            r0 = move-exception
            goto L7c
        L63:
            r12 = r1
            goto L86
        L65:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n
            java.lang.String r1 = "the wait timed out"
            com.huawei.hms.framework.common.Logger.m15045w(r0, r1)
            goto L91
        L6e:
            r0 = move-exception
            r1 = r12
        L70:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n
            java.lang.String r4 = "the current thread was interrupted while waiting"
            com.huawei.hms.framework.common.Logger.m15044w(r3, r4, r0)
        L78:
            r12 = r1
            goto L92
        L7a:
            r0 = move-exception
            r1 = r12
        L7c:
            java.lang.String r3 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n
            java.lang.String r4 = "the computation threw an ExecutionException"
            com.huawei.hms.framework.common.Logger.m15044w(r3, r4, r0)
        L84:
            r12 = r1
            goto L91
        L86:
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n
            java.lang.String r1 = "{requestServer} the computation was cancelled"
            com.huawei.hms.framework.common.Logger.m15049i(r0, r1)
            goto L92
        L8f:
            r2 = r17
        L91:
            r14 = r10
        L92:
            if (r14 == 0) goto L9c
            java.lang.String r0 = com.huawei.hms.framework.network.grs.p217g.C4935c.f11261n
            java.lang.String r1 = "needBreak is true so need break current circulation"
            com.huawei.hms.framework.common.Logger.m15047v(r0, r1)
            goto La0
        L9c:
            int r11 = r11 + 1
            goto L6
        La0:
            com.huawei.hms.framework.network.grs.g.d r0 = r9.m14936b(r12)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p217g.C4935c.m14938a(java.util.concurrent.ExecutorService, java.util.List, java.lang.String, com.huawei.hms.framework.network.grs.e.c):com.huawei.hms.framework.network.grs.g.d");
    }

    /* renamed from: a */
    private void m14940a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str);
        String grsReqParamJoint = this.f11262a.getGrsReqParamJoint(false, false, m14931e(), this.f11263b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.f11270i.add(sb.toString());
    }

    /* renamed from: b */
    private C4937d m14936b(C4937d c4937d) {
        String str;
        String str2;
        for (Map.Entry<String, Future<C4937d>> entry : this.f11266e.entrySet()) {
            if (c4937d != null && (c4937d.m14901o() || c4937d.m14903m())) {
                break;
            }
            try {
                c4937d = entry.getValue().get(40000L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e = e;
                str = f11261n;
                str2 = "{checkResponse} when check result, find InterruptedException, check others";
                Logger.m15044w(str, str2, e);
            } catch (CancellationException unused) {
                Logger.m15049i(f11261n, "{checkResponse} when check result, find CancellationException, check others");
            } catch (ExecutionException e2) {
                e = e2;
                str = f11261n;
                str2 = "{checkResponse} when check result, find ExecutionException, check others";
                Logger.m15044w(str, str2, e);
            } catch (TimeoutException unused2) {
                Logger.m15045w(f11261n, "{checkResponse} when check result, find TimeoutException, cancel current request task");
                if (!entry.getValue().isCancelled()) {
                    entry.getValue().cancel(true);
                }
            }
        }
        return c4937d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public C4937d m14934b(ExecutorService executorService, String str, C4925c c4925c) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        C4937d m14938a = m14938a(executorService, this.f11270i, str, c4925c);
        int m14925b = m14938a == null ? 0 : m14938a.m14925b();
        Logger.m15046v(f11261n, "use 2.0 interface return http's code is：{%s}", Integer.valueOf(m14925b));
        if (m14925b == 404 || m14925b == 401) {
            if (TextUtils.isEmpty(m14931e()) && TextUtils.isEmpty(this.f11262a.getAppName())) {
                Logger.m15049i(f11261n, "request grs server use 1.0 API must set appName,please check.");
                return null;
            }
            this.f11266e.clear();
            Logger.m15049i(f11261n, "this env has not deploy new interface,so use old interface.");
            m14938a = m14938a(executorService, this.f11269h, str, c4925c);
        }
        C4938e.m14895a(new ArrayList(this.f11267f), SystemClock.elapsedRealtime() - elapsedRealtime, this.f11268g, this.f11263b);
        this.f11267f.clear();
        return m14938a;
    }

    /* renamed from: b */
    private void m14935b(String str, String str2) {
        if (TextUtils.isEmpty(this.f11262a.getAppName()) && TextUtils.isEmpty(m14931e())) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[1];
        objArr[0] = TextUtils.isEmpty(m14931e()) ? this.f11262a.getAppName() : m14931e();
        sb.append(String.format(locale, str, objArr));
        String grsReqParamJoint = this.f11262a.getGrsReqParamJoint(false, false, "1.0", this.f11263b);
        if (!TextUtils.isEmpty(grsReqParamJoint)) {
            sb.append("?");
            sb.append(grsReqParamJoint);
        }
        this.f11269h.add(sb.toString());
    }

    /* renamed from: c */
    private void m14933c() {
        C4950d m14883a = C4946a.m14883a(this.f11263b);
        if (m14883a == null) {
            Logger.m15045w(f11261n, "g*s***_se****er_conf*** maybe has a big error");
            return;
        }
        m14941a(m14883a);
        List<String> m14873a = m14883a.m14873a();
        if (m14873a == null || m14873a.size() <= 0) {
            Logger.m15047v(f11261n, "maybe grs_base_url config with [],please check.");
        } else if (m14873a.size() > 10) {
            throw new IllegalArgumentException("grs_base_url's count is larger than MAX value 10");
        } else {
            String m14867c = m14883a.m14867c();
            String m14869b = m14883a.m14869b();
            if (m14873a.size() > 0) {
                for (String str : m14873a) {
                    if (str.startsWith("https://")) {
                        m14935b(m14867c, str);
                        m14940a(m14869b, str);
                    } else {
                        Logger.m15045w(f11261n, "grs server just support https scheme url,please check.");
                    }
                }
            }
            Logger.m15046v(f11261n, "request to GRS server url is{%s} and {%s}", this.f11269h, this.f11270i);
        }
    }

    /* renamed from: d */
    private void m14932d() {
        String grsParasKey = this.f11262a.getGrsParasKey(true, true, this.f11263b);
        C4925c m15004a = this.f11264c.m15004a();
        this.f11273l = m15004a.m14990a(grsParasKey + "ETag", "");
    }

    /* renamed from: e */
    private String m14931e() {
        C4927b m14963a = C4927b.m14963a(this.f11263b.getPackageName(), this.f11262a);
        C4959a m14969a = m14963a != null ? m14963a.m14969a() : null;
        if (m14969a != null) {
            String m14843b = m14969a.m14843b();
            Logger.m15046v(f11261n, "get appName from local assets is{%s}", m14843b);
            return m14843b;
        }
        return "";
    }

    /* renamed from: a */
    public C4937d m14939a(ExecutorService executorService, String str, C4925c c4925c) {
        String str2;
        String str3;
        if (this.f11269h.isEmpty() && this.f11270i.isEmpty()) {
            return null;
        }
        try {
            C4950d m14937b = m14937b();
            return (C4937d) executorService.submit(new CallableC4936a(executorService, str, c4925c)).get(m14937b != null ? m14937b.m14866d() : 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            str2 = f11261n;
            str3 = "{submitExcutorTaskWithTimeout} the current thread was interrupted while waiting";
            Logger.m15044w(str2, str3, e);
            return null;
        } catch (CancellationException unused) {
            Logger.m15049i(f11261n, "{submitExcutorTaskWithTimeout} the computation was cancelled");
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            str2 = f11261n;
            str3 = "{submitExcutorTaskWithTimeout} the computation threw an ExecutionException";
            Logger.m15044w(str2, str3, e);
            return null;
        } catch (TimeoutException unused2) {
            Logger.m15045w(f11261n, "{submitExcutorTaskWithTimeout} the wait timed out");
            return null;
        } catch (Exception e3) {
            e = e3;
            str2 = f11261n;
            str3 = "{submitExcutorTaskWithTimeout} catch Exception";
            Logger.m15044w(str2, str3, e);
            return null;
        }
    }

    /* renamed from: a */
    public String m14944a() {
        return this.f11273l;
    }

    /* renamed from: a */
    public synchronized void m14942a(C4937d c4937d) {
        this.f11267f.add(c4937d);
        C4937d c4937d2 = this.f11265d;
        if (c4937d2 != null && (c4937d2.m14901o() || this.f11265d.m14903m())) {
            Logger.m15047v(f11261n, "grsResponseResult is ok");
        } else if (c4937d.m14902n()) {
            Logger.m15049i(f11261n, "GRS server open 503 limiting strategy.");
            C4954d.m14855a(this.f11262a.getGrsParasKey(true, true, this.f11263b), new C4954d.C4955a(c4937d.m14905k(), SystemClock.elapsedRealtime()));
        } else {
            if (c4937d.m14903m()) {
                Logger.m15049i(f11261n, "GRS server open 304 Not Modified.");
            }
            if (!c4937d.m14901o() && !c4937d.m14903m()) {
                Logger.m15047v(f11261n, "grsResponseResult has exception so need return");
                return;
            }
            this.f11265d = c4937d;
            this.f11264c.m15001a(this.f11262a, this.f11265d, this.f11263b, this.f11271j);
            C4927b.m14968a(this.f11263b, this.f11262a);
            for (Map.Entry<String, Future<C4937d>> entry : this.f11266e.entrySet()) {
                if (!entry.getKey().equals(c4937d.m14904l()) && !entry.getValue().isCancelled()) {
                    Logger.m15049i(f11261n, "future cancel");
                    entry.getValue().cancel(true);
                }
            }
        }
    }

    /* renamed from: a */
    public void m14941a(C4950d c4950d) {
        this.f11272k = c4950d;
    }

    /* renamed from: b */
    public C4950d m14937b() {
        return this.f11272k;
    }
}
