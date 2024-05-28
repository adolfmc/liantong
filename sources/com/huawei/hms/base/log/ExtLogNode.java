package com.huawei.hms.base.log;

import android.content.Context;
import com.huawei.hms.support.log.HMSExtLogger;

/* renamed from: com.huawei.hms.base.log.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ExtLogNode implements LogNode {

    /* renamed from: a */
    private final HMSExtLogger f11048a;

    /* renamed from: b */
    private LogNode f11049b;

    public ExtLogNode(HMSExtLogger hMSExtLogger) {
        this.f11048a = hMSExtLogger;
    }

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15176a(Context context, String str) {
        LogNode logNode = this.f11049b;
        if (logNode != null) {
            logNode.mo15176a(context, str);
        }
    }

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15175a(LogNode logNode) {
        this.f11049b = logNode;
    }

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15174a(String str, int i, String str2, String str3) {
        HMSExtLogger hMSExtLogger = this.f11048a;
        if (hMSExtLogger != null) {
            if (i == 3) {
                hMSExtLogger.m14120d(str2, str3);
            } else if (i == 4) {
                hMSExtLogger.m14118i(str2, str3);
            } else if (i != 5) {
                hMSExtLogger.m14119e(str2, str3);
            } else {
                hMSExtLogger.m14117w(str2, str3);
            }
        }
        LogNode logNode = this.f11049b;
        if (logNode != null) {
            logNode.mo15174a(str, i, str2, str3);
        }
    }
}
