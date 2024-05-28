package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.AAIDResult;
import java.util.concurrent.Callable;

/* renamed from: com.huawei.hms.opendevice.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AAIDCallable implements Callable<AAIDResult> {

    /* renamed from: a */
    private Context f11535a;

    public AAIDCallable(Context context) {
        this.f11535a = context;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public AAIDResult call() throws Exception {
        Context context = this.f11535a;
        if (context != null) {
            String m14396b = AaidUtils.m14396b(context);
            AAIDResult aAIDResult = new AAIDResult();
            aAIDResult.setId(m14396b);
            return aAIDResult;
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }
}
