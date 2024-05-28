package com.huawei.hms.base.log;

import android.content.Context;
import android.util.Log;

/* renamed from: com.huawei.hms.base.log.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LogCatNode implements LogNode {

    /* renamed from: a */
    private LogNode f11053a;

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15176a(Context context, String str) {
        LogNode logNode = this.f11053a;
        if (logNode != null) {
            logNode.mo15176a(context, str);
        }
    }

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15175a(LogNode logNode) {
        this.f11053a = logNode;
    }

    @Override // com.huawei.hms.base.log.LogNode
    /* renamed from: a */
    public void mo15174a(String str, int i, String str2, String str3) {
        Log.println(i, "HMSSDK_" + str2, str3);
        LogNode logNode = this.f11053a;
        if (logNode != null) {
            logNode.mo15174a(str, i, str2, str3);
        }
    }
}
