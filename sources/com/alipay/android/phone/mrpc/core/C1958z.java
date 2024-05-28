package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.p103a.C1924d;
import com.alipay.android.phone.mrpc.core.p103a.C1925e;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.z */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1958z {

    /* renamed from: a */
    private static final ThreadLocal<Object> f3470a = new ThreadLocal<>();

    /* renamed from: b */
    private static final ThreadLocal<Map<String, Object>> f3471b = new ThreadLocal<>();

    /* renamed from: c */
    private byte f3472c = 0;

    /* renamed from: d */
    private AtomicInteger f3473d = new AtomicInteger();

    /* renamed from: e */
    private C1956x f3474e;

    public C1958z(C1956x c1956x) {
        this.f3474e = c1956x;
    }

    /* renamed from: a */
    public final Object m21060a(Method method, Object[] objArr) {
        if (Looper.myLooper() != null && Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        boolean z = method.getAnnotation(ResetCookie.class) != null;
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        f3470a.set(null);
        f3471b.set(null);
        if (operationType != null) {
            String value = operationType.value();
            int incrementAndGet = this.f3473d.incrementAndGet();
            try {
                if (this.f3472c == 0) {
                    C1925e c1925e = new C1925e(incrementAndGet, value, objArr);
                    if (f3471b.get() != null) {
                        c1925e.mo21136a(f3471b.get());
                    }
                    byte[] mo21137a = c1925e.mo21137a();
                    f3471b.set(null);
                    Object mo21138a = new C1924d(genericReturnType, (byte[]) new C1941j(this.f3474e.m21062a(), method, incrementAndGet, value, mo21137a, z).mo21064a()).mo21138a();
                    if (genericReturnType != Void.TYPE) {
                        f3470a.set(mo21138a);
                    }
                }
                return f3470a.get();
            } catch (RpcException e) {
                e.setOperationType(value);
                throw e;
            }
        }
        throw new IllegalStateException("OperationType must be set.");
    }
}
