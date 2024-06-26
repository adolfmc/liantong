package com.huawei.hms.support.api.entity.core;

import android.content.Intent;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JosGetNoticeResp extends JosBaseResp {
    @Packed
    private Intent noticeIntent;

    private static <T> T get(T t) {
        return t;
    }

    public Intent getNoticeIntent() {
        return (Intent) get(this.noticeIntent);
    }

    public void setNoticeIntent(Intent intent) {
        this.noticeIntent = intent;
    }
}
