package com.huawei.hms.activity.internal;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ForegroundBusResponseMgr {

    /* renamed from: b */
    private static final ForegroundBusResponseMgr f10909b = new ForegroundBusResponseMgr();

    /* renamed from: a */
    private final Map<String, BusResponseCallback> f10910a = new HashMap();

    public static ForegroundBusResponseMgr getInstance() {
        return f10909b;
    }

    public BusResponseCallback get(String str) {
        BusResponseCallback busResponseCallback;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.f10910a) {
            busResponseCallback = this.f10910a.get(str);
        }
        return busResponseCallback;
    }

    public void registerObserver(String str, BusResponseCallback busResponseCallback) {
        if (TextUtils.isEmpty(str) || busResponseCallback == null) {
            return;
        }
        synchronized (this.f10910a) {
            if (!this.f10910a.containsKey(str)) {
                this.f10910a.put(str, busResponseCallback);
            }
        }
    }

    public void unRegisterObserver(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.f10910a) {
            this.f10910a.remove(str);
        }
    }
}
