package com.alipay.security.mobile.module.http.model;

import com.alipay.security.mobile.module.p110a.C2081a;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportRequest;
import com.alipay.tscenter.biz.rpc.report.general.model.DataReportResult;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.http.model.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2106b {
    /* renamed from: a */
    public static C2107c m20466a(DataReportResult dataReportResult) {
        C2107c c2107c = new C2107c();
        if (dataReportResult == null) {
            return null;
        }
        c2107c.f4014a = dataReportResult.success;
        c2107c.f4015b = dataReportResult.resultCode;
        Map<String, String> map = dataReportResult.resultData;
        if (map != null) {
            c2107c.f4021h = map.get("apdid");
            c2107c.f4022i = map.get("apdidToken");
            c2107c.f4025l = map.get("dynamicKey");
            c2107c.f4026m = map.get("timeInterval");
            c2107c.f4027n = map.get("webrtcUrl");
            c2107c.f4028o = "";
            String str = map.get("drmSwitch");
            if (C2081a.m20573b(str)) {
                if (str.length() > 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str.charAt(0));
                    c2107c.f4023j = sb.toString();
                }
                if (str.length() >= 3) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str.charAt(2));
                    c2107c.f4024k = sb2.toString();
                }
            }
            if (map.containsKey("apse_degrade")) {
                c2107c.f4029p = map.get("apse_degrade");
            }
        }
        return c2107c;
    }

    /* renamed from: a */
    public static DataReportRequest m20467a(C2108d c2108d) {
        DataReportRequest dataReportRequest = new DataReportRequest();
        if (c2108d == null) {
            return null;
        }
        dataReportRequest.f4042os = c2108d.f4030a;
        dataReportRequest.rpcVersion = c2108d.f4039j;
        dataReportRequest.bizType = "1";
        dataReportRequest.bizData = new HashMap();
        dataReportRequest.bizData.put("apdid", c2108d.f4031b);
        dataReportRequest.bizData.put("apdidToken", c2108d.f4032c);
        dataReportRequest.bizData.put("umidToken", c2108d.f4033d);
        dataReportRequest.bizData.put("dynamicKey", c2108d.f4034e);
        dataReportRequest.deviceData = c2108d.f4035f;
        return dataReportRequest;
    }
}
