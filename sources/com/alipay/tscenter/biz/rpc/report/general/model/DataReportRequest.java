package com.alipay.tscenter.biz.rpc.report.general.model;

import java.io.Serializable;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DataReportRequest implements Serializable {
    public Map<String, String> bizData;
    public String bizType;
    public Map<String, String> deviceData;

    /* renamed from: os */
    public String f4042os;
    public String rpcVersion;
}
