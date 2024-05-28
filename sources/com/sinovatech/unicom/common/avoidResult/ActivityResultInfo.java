package com.sinovatech.unicom.common.avoidResult;

import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ActivityResultInfo {
    private Intent data;
    private int resultCode;

    public ActivityResultInfo(int i, Intent intent) {
        this.resultCode = i;
        this.data = intent;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public void setResultCode(int i) {
        this.resultCode = i;
    }

    public Intent getData() {
        return this.data;
    }

    public void setData(Intent intent) {
        this.data = intent;
    }
}
