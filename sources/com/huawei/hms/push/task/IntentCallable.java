package com.huawei.hms.push.task;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.push.utils.PushBiUtil;
import java.util.concurrent.Callable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IntentCallable implements Callable<Void> {

    /* renamed from: a */
    private Context f11677a;

    /* renamed from: b */
    private Intent f11678b;

    /* renamed from: c */
    private String f11679c;

    public IntentCallable(Context context, Intent intent, String str) {
        this.f11677a = context;
        this.f11678b = intent;
        this.f11679c = str;
    }

    @Override // java.util.concurrent.Callable
    public Void call() throws Exception {
        this.f11677a.sendBroadcast(this.f11678b);
        PushBiUtil.reportExit(this.f11677a, "push.setNotifyFlag", this.f11679c, ErrorEnum.SUCCESS);
        return null;
    }
}
