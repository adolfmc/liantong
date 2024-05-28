package p482w;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.UnicomPaySDK;
import com.unicom.pay.common.bean.WPTicketBean;
import com.unicom.pay.common.callback.DataCallback;
import java.util.HashMap;
import p470p0.C13648k;
import p470p0.C13652o;

@NBSInstrumented
/* renamed from: w.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class RunnableC14250b implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ HashMap f27764a;

    /* renamed from: b */
    public final /* synthetic */ String f27765b;

    /* renamed from: c */
    public final /* synthetic */ int f27766c = 5000;

    /* renamed from: d */
    public final /* synthetic */ boolean f27767d;

    /* renamed from: e */
    public final /* synthetic */ AbstractC14257d f27768e;

    /* renamed from: f */
    public final /* synthetic */ C14255c f27769f;

    /* renamed from: w.b$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC14251a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ C10546a f27770a;

        @NBSInstrumented
        /* renamed from: w.b$a$a */
        /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
        public class C14252a implements DataCallback {
            public C14252a() {
            }

            @Override // com.unicom.pay.common.callback.DataCallback
            public final void onResult(String str) {
                C13652o.m174a("UnicomPaySdk code:", str);
                try {
                    Gson gson = C13648k.f27492a;
                    RunnableC14251a.this.f27770a.f20057g = ((WPTicketBean) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) WPTicketBean.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) WPTicketBean.class))).getTicket();
                } catch (Exception unused) {
                    RunnableC14251a.this.f27770a.f20057g = "";
                }
                RunnableC14250b runnableC14250b = RunnableC14250b.this;
                runnableC14250b.f27769f.m29a(runnableC14250b.f27765b, runnableC14250b.f27764a, true, runnableC14250b.f27768e);
            }
        }

        public RunnableC14251a(C10546a c10546a) {
            this.f27770a = c10546a;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C14255c.m28a(RunnableC14250b.this.f27769f, UnicomPaySDK.getInstance().getNativeFunctionCallback(), new C14252a());
        }
    }

    /* renamed from: w.b$b */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC14253b implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ C10546a f27773a;

        @NBSInstrumented
        /* renamed from: w.b$b$a */
        /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
        public class C14254a implements DataCallback {
            public C14254a() {
            }

            @Override // com.unicom.pay.common.callback.DataCallback
            public final void onResult(String str) {
                C13652o.m174a("UnicomPaySdk code:", str);
                try {
                    Gson gson = C13648k.f27492a;
                    RunnableC14253b.this.f27773a.f20057g = ((WPTicketBean) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) WPTicketBean.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) WPTicketBean.class))).getTicket();
                } catch (Exception unused) {
                    RunnableC14253b.this.f27773a.f20057g = "";
                }
                RunnableC14250b runnableC14250b = RunnableC14250b.this;
                runnableC14250b.f27769f.m29a(runnableC14250b.f27765b, runnableC14250b.f27764a, true, runnableC14250b.f27768e);
            }
        }

        public RunnableC14253b(C10546a c10546a) {
            this.f27773a = c10546a;
        }

        @Override // java.lang.Runnable
        public final void run() {
            C14255c.m28a(RunnableC14250b.this.f27769f, UnicomPaySDK.getInstance().getNativeFunctionCallback(), new C14254a());
        }
    }

    public RunnableC14250b(C14255c c14255c, HashMap hashMap, String str, boolean z, AbstractC14257d abstractC14257d) {
        this.f27769f = c14255c;
        this.f27764a = hashMap;
        this.f27765b = str;
        this.f27767d = z;
        this.f27768e = abstractC14257d;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0336 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 850
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p482w.RunnableC14250b.run():void");
    }
}
