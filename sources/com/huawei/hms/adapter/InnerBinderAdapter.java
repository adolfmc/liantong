package com.huawei.hms.adapter;

import android.content.Context;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class InnerBinderAdapter extends BinderAdapter {

    /* renamed from: j */
    private static final Object f10952j = new Object();

    /* renamed from: k */
    private static BinderAdapter f10953k;

    private InnerBinderAdapter(Context context, String str, String str2) {
        super(context, str, str2);
    }

    public static BinderAdapter getInstance(Context context, String str, String str2) {
        BinderAdapter binderAdapter;
        HMSLog.m14110i("InnerBinderAdapter", "InnerBinderAdapter getInstance.");
        synchronized (f10952j) {
            if (f10953k == null) {
                f10953k = new InnerBinderAdapter(context, str, str2);
            }
            binderAdapter = f10953k;
        }
        return binderAdapter;
    }

    @Override // com.huawei.hms.adapter.BinderAdapter
    protected int getConnTimeOut() {
        return 2001;
    }

    @Override // com.huawei.hms.adapter.BinderAdapter
    protected int getMsgDelayDisconnect() {
        return 2002;
    }
}
