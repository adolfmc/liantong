package com.alipay.android.phone.mrpc.core.p103a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p100a.p101a.C1909e;
import java.lang.reflect.Type;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1924d extends AbstractC1921a {
    public C1924d(Type type, byte[] bArr) {
        super(type, bArr);
    }

    @Override // com.alipay.android.phone.mrpc.core.p103a.InterfaceC1923c
    /* renamed from: a */
    public final Object mo21138a() {
        try {
            String str = new String(this.f3388b);
            StringBuilder sb = new StringBuilder("threadid = ");
            sb.append(Thread.currentThread().getId());
            sb.append("; rpc response:  ");
            sb.append(str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("resultStatus");
            if (i == 1000) {
                return this.f3387a == String.class ? jSONObject.optString("result") : C1909e.m21150a(jSONObject.optString("result"), this.f3387a);
            }
            throw new RpcException(Integer.valueOf(i), jSONObject.optString("tips"));
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("response  =");
            sb2.append(new String(this.f3388b));
            sb2.append(":");
            sb2.append(e);
            throw new RpcException((Integer) 10, sb2.toString() == null ? "" : e.getMessage());
        }
    }
}
