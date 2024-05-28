package com.gmrz.appsdk.commlib;

import android.annotation.SuppressLint;
import com.gmrz.appsdk.commlib.api.ICommunicationClientResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressLint({"UseSparseArrays"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafRequestObject {

    /* renamed from: c */
    private static final Map<Integer, UafRequestObject> f10274c = new HashMap(4);

    /* renamed from: d */
    private static final AtomicInteger f10275d = new AtomicInteger(0);

    /* renamed from: a */
    private ICommunicationClientResponse f10276a;

    /* renamed from: b */
    private Station f10277b = Station.VALID;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum Station {
        VALID,
        INVALID
    }

    /* renamed from: a */
    public static int m15842a(UafRequestObject uafRequestObject) {
        int incrementAndGet = f10275d.incrementAndGet();
        f10274c.put(Integer.valueOf(incrementAndGet), uafRequestObject);
        return incrementAndGet;
    }

    /* renamed from: b */
    public synchronized Station m15840b() {
        return this.f10277b;
    }

    /* renamed from: c */
    public synchronized void m15839c() {
        this.f10277b = Station.INVALID;
    }

    /* renamed from: a */
    public static UafRequestObject m15843a(int i) {
        return f10274c.get(Integer.valueOf(i));
    }

    /* renamed from: a */
    public ICommunicationClientResponse m15844a() {
        return this.f10276a;
    }

    /* renamed from: a */
    public void m15841a(ICommunicationClientResponse iCommunicationClientResponse) {
        this.f10276a = iCommunicationClientResponse;
    }
}
