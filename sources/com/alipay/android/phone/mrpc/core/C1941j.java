package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1941j extends AbstractC1920a {

    /* renamed from: g */
    private InterfaceC1938g f3413g;

    public C1941j(InterfaceC1938g interfaceC1938g, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.f3413g = interfaceC1938g;
    }

    @Override // com.alipay.android.phone.mrpc.core.InterfaceC1954v
    /* renamed from: a */
    public final Object mo21064a() {
        C1947o c1947o = new C1947o(this.f3413g.mo21117a());
        c1947o.m21096a(this.f3382b);
        c1947o.m21100a(this.f3385e);
        c1947o.m21097a(this.f3386f);
        c1947o.m21099a("id", String.valueOf(this.f3384d));
        c1947o.m21099a("operationType", this.f3383c);
        c1947o.m21099a("gzip", String.valueOf(this.f3413g.mo21114d()));
        c1947o.m21098a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> m21133b = this.f3413g.mo21115c().m21133b();
        if (m21133b != null && !m21133b.isEmpty()) {
            for (Header header : m21133b) {
                c1947o.m21098a(header);
            }
        }
        StringBuilder sb = new StringBuilder("threadid = ");
        sb.append(Thread.currentThread().getId());
        sb.append("; ");
        sb.append(c1947o.toString());
        try {
            C1953u c1953u = this.f3413g.mo21116b().mo21105a(c1947o).get();
            if (c1953u != null) {
                return c1953u.m21066b();
            }
            throw new RpcException((Integer) 9, "response is null");
        } catch (InterruptedException e) {
            throw new RpcException(13, "", e);
        } catch (CancellationException e2) {
            throw new RpcException(13, "", e2);
        } catch (ExecutionException e3) {
            Throwable cause = e3.getCause();
            if (cause == null || !(cause instanceof HttpException)) {
                throw new RpcException(9, "", e3);
            }
            HttpException httpException = (HttpException) cause;
            int code = httpException.getCode();
            switch (code) {
                case 1:
                    code = 2;
                    break;
                case 2:
                    code = 3;
                    break;
                case 3:
                    code = 4;
                    break;
                case 4:
                    code = 5;
                    break;
                case 5:
                    code = 6;
                    break;
                case 6:
                    code = 7;
                    break;
                case 7:
                    code = 8;
                    break;
                case 8:
                    code = 15;
                    break;
                case 9:
                    code = 16;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        }
    }
}
