package p482w;

import com.unicom.pay.C10546a;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.common.callback.NativeFunctionCallBack;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import p470p0.C13651n;

/* renamed from: w.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14255c {

    /* renamed from: a */
    public final ExecutorService f27776a = Executors.newCachedThreadPool();

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: w.c$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C14256a {

        /* renamed from: a */
        public static C14255c f27777a = new C14255c();
    }

    /* renamed from: a */
    public static void m28a(C14255c c14255c, NativeFunctionCallBack nativeFunctionCallBack, DataCallback dataCallback) {
        c14255c.getClass();
        C10546a c10546a = C10546a.C10576i.f20125a;
        String str = System.currentTimeMillis() + "";
        String str2 = c10546a.f20062l;
        String str3 = c10546a.f20063m;
        nativeFunctionCallBack.getCode(str2, str3, C13651n.m175a(str + str2 + str3), str, dataCallback);
    }

    /* renamed from: a */
    public final Future<?> m30a(String str, HashMap<String, Object> hashMap, AbstractC14257d abstractC14257d) {
        return m29a(str, hashMap, false, abstractC14257d);
    }

    /* renamed from: a */
    public final Future m29a(String str, HashMap hashMap, boolean z, AbstractC14257d abstractC14257d) {
        try {
            return this.f27776a.submit(new RunnableC14250b(this, hashMap, str, z, abstractC14257d));
        } catch (Exception e) {
            abstractC14257d.mo24b(C14249a.m31a(e));
            return null;
        }
    }
}
