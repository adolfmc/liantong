package com.alipay.tscenter.biz.rpc.deviceFp;

import com.alipay.mobile.framework.service.annotation.OperationType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface BugTrackMessageService {
    @OperationType("alipay.security.errorLog.collect")
    String logCollect(String str);
}
