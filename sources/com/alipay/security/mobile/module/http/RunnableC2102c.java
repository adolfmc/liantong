package com.alipay.security.mobile.module.http;

import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.tscenter.biz.rpc.report.general.DataReportService;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.http.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2102c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DataReportRequest f4010a;

    /* renamed from: b */
    final /* synthetic */ C2101b f4011b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2102c(C2101b c2101b, DataReportRequest dataReportRequest) {
        this.f4011b = c2101b;
        this.f4010a = dataReportRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        DataReportResult dataReportResult;
        DataReportResult dataReportResult2;
        DataReportService dataReportService;
        try {
            dataReportService = this.f4011b.f4009c;
            DataReportResult unused = C2101b.f4006e = dataReportService.reportData(this.f4010a);
        } catch (Throwable th) {
            DataReportResult unused2 = C2101b.f4006e = new DataReportResult();
            dataReportResult = C2101b.f4006e;
            dataReportResult.success = false;
            dataReportResult2 = C2101b.f4006e;
            dataReportResult2.resultCode = "static data rpc upload error, " + C2081a.m20575a(th);
            new StringBuilder("rpc failed:").append(C2081a.m20575a(th));
        }
    }
}
