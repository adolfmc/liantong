package com.alipay.android.phone.mrpc.core.p103a;

import com.alipay.android.phone.mrpc.core.RpcException;
import com.alipay.p100a.p101a.C1910f;
import java.util.ArrayList;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1925e extends AbstractC1922b {

    /* renamed from: c */
    private int f3391c;

    /* renamed from: d */
    private Object f3392d;

    public C1925e(int i, String str, Object obj) {
        super(str, obj);
        this.f3391c = i;
    }

    @Override // com.alipay.android.phone.mrpc.core.p103a.InterfaceC1926f
    /* renamed from: a */
    public final void mo21136a(Object obj) {
        this.f3392d = obj;
    }

    @Override // com.alipay.android.phone.mrpc.core.p103a.InterfaceC1926f
    /* renamed from: a */
    public final byte[] mo21137a() {
        try {
            ArrayList arrayList = new ArrayList();
            if (this.f3392d != null) {
                arrayList.add(new BasicNameValuePair("extParam", C1910f.m21149a(this.f3392d)));
            }
            arrayList.add(new BasicNameValuePair("operationType", this.f3389a));
            StringBuilder sb = new StringBuilder();
            sb.append(this.f3391c);
            arrayList.add(new BasicNameValuePair("id", sb.toString()));
            new StringBuilder("mParams is:").append(this.f3390b);
            arrayList.add(new BasicNameValuePair("requestData", this.f3390b == null ? "[]" : C1910f.m21149a(this.f3390b)));
            return URLEncodedUtils.format(arrayList, "utf-8").getBytes();
        } catch (Exception e) {
            StringBuilder sb2 = new StringBuilder("request  =");
            sb2.append(this.f3390b);
            sb2.append(":");
            sb2.append(e);
            throw new RpcException(9, sb2.toString() == null ? "" : e.getMessage(), e);
        }
    }
}
