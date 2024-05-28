package com.huawei.hms.framework.network.grs.p217g;

import android.content.Context;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.common.hianalytics.HianalyticsHelper;
import com.huawei.hms.framework.common.hianalytics.LinkedHashMapPack;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.g.e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4938e {

    /* renamed from: com.huawei.hms.framework.network.grs.g.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class RunnableC4939a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ long f11294a;

        /* renamed from: b */
        final /* synthetic */ ArrayList f11295b;

        /* renamed from: c */
        final /* synthetic */ JSONArray f11296c;

        RunnableC4939a(long j, ArrayList arrayList, JSONArray jSONArray) {
            this.f11294a = j;
            this.f11295b = arrayList;
            this.f11296c = jSONArray;
        }

        /* JADX WARN: Removed duplicated region for block: B:5:0x001b  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r6 = this;
                com.huawei.hms.framework.network.grs.g.k.a r0 = new com.huawei.hms.framework.network.grs.g.k.a
                r0.<init>()
                long r1 = r6.f11294a
                java.lang.String r3 = "total_time"
                r0.put(r3, r1)
                java.util.ArrayList r1 = r6.f11295b
                java.util.Iterator r1 = r1.iterator()
            L13:
                boolean r2 = r1.hasNext()
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L39
                java.lang.Object r2 = r1.next()
                com.huawei.hms.framework.network.grs.g.d r2 = (com.huawei.hms.framework.network.grs.p217g.C4937d) r2
                boolean r5 = r2.m14901o()
                if (r5 != 0) goto L2d
                boolean r5 = r2.m14903m()
                if (r5 == 0) goto L13
            L2d:
                java.util.LinkedHashMap r2 = com.huawei.hms.framework.network.grs.p217g.C4938e.m14896a(r2)
                r0.put(r2)
                r1.remove()
                r1 = r4
                goto L3a
            L39:
                r1 = r3
            L3a:
                if (r1 != 0) goto L5d
                java.util.ArrayList r1 = r6.f11295b
                int r1 = r1.size()
                if (r1 <= 0) goto L5d
                java.util.ArrayList r1 = r6.f11295b
                int r2 = r1.size()
                int r2 = r2 - r4
                java.lang.Object r1 = r1.get(r2)
                com.huawei.hms.framework.network.grs.g.d r1 = (com.huawei.hms.framework.network.grs.p217g.C4937d) r1
                java.util.LinkedHashMap r2 = com.huawei.hms.framework.network.grs.p217g.C4938e.m14896a(r1)
                r0.put(r2)
                java.util.ArrayList r2 = r6.f11295b
                r2.remove(r1)
            L5d:
                java.util.ArrayList r1 = r6.f11295b
                int r1 = r1.size()
                if (r1 <= 0) goto L86
                java.util.ArrayList r1 = r6.f11295b
                java.util.Iterator r1 = r1.iterator()
            L6b:
                boolean r2 = r1.hasNext()
                if (r2 == 0) goto L86
                java.lang.Object r2 = r1.next()
                com.huawei.hms.framework.network.grs.g.d r2 = (com.huawei.hms.framework.network.grs.p217g.C4937d) r2
                org.json.JSONObject r5 = new org.json.JSONObject
                java.util.LinkedHashMap r2 = com.huawei.hms.framework.network.grs.p217g.C4938e.m14896a(r2)
                r5.<init>(r2)
                org.json.JSONArray r2 = r6.f11296c
                r2.put(r5)
                goto L6b
            L86:
                org.json.JSONArray r1 = r6.f11296c
                int r1 = r1.length()
                if (r1 <= 0) goto L99
                org.json.JSONArray r1 = r6.f11296c
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "failed_info"
                r0.put(r2, r1)
            L99:
                java.lang.Object[] r1 = new java.lang.Object[r4]
                org.json.JSONObject r2 = new org.json.JSONObject
                java.util.LinkedHashMap r4 = r0.get()
                r2.<init>(r4)
                r1[r3] = r2
                java.lang.String r2 = "HaReportHelper"
                java.lang.String r3 = "grssdk report data to aiops is: %s"
                com.huawei.hms.framework.common.Logger.m15053d(r2, r3, r1)
                com.huawei.hms.framework.common.hianalytics.HianalyticsHelper r1 = com.huawei.hms.framework.common.hianalytics.HianalyticsHelper.getInstance()
                java.util.LinkedHashMap r0 = r0.get()
                java.lang.String r2 = "grs_request"
                r1.onEvent(r0, r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p217g.C4938e.RunnableC4939a.run():void");
        }
    }

    /* renamed from: a */
    public static void m14895a(ArrayList<C4937d> arrayList, long j, JSONArray jSONArray, Context context) {
        if (context == null || arrayList == null || arrayList.size() <= 0 || !HianalyticsHelper.getInstance().isEnableReportNoSeed(context)) {
            return;
        }
        HianalyticsHelper.getInstance().getReportExecutor().submit(new RunnableC4939a(j, arrayList, jSONArray));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static LinkedHashMap<String, String> m14894b(C4937d c4937d) {
        LinkedHashMapPack linkedHashMapPack = new LinkedHashMapPack();
        Exception m14915d = c4937d.m14915d();
        if (m14915d != null) {
            linkedHashMapPack.put("error_code", ExceptionCode.getErrorCodeFromException(m14915d));
            linkedHashMapPack.put("exception_name", m14915d.getClass().getSimpleName());
            linkedHashMapPack.put("message", StringUtils.anonymizeMessage(m14915d.getMessage()));
        } else {
            linkedHashMapPack.put("error_code", c4937d.m14925b());
            linkedHashMapPack.put("exception_name", c4937d.m14920c());
        }
        try {
            linkedHashMapPack.put("domain", new URL(c4937d.m14904l()).getHost());
        } catch (MalformedURLException e) {
            Logger.m15044w("HaReportHelper", "report host MalformedURLException", e);
        }
        linkedHashMapPack.put("req_start_time", c4937d.m14908h());
        linkedHashMapPack.put("req_end_time", c4937d.m14909g());
        linkedHashMapPack.put("req_total_time", c4937d.m14907i());
        return linkedHashMapPack.getAll();
    }
}
