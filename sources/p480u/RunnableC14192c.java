package p480u;

import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import p470p0.C13648k;
import p480u.C14193d;

@NBSInstrumented
/* renamed from: u.c */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class RunnableC14192c implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ FidoReInfo f27695a;

    /* renamed from: b */
    public final /* synthetic */ C14193d.C14194a f27696b;

    public RunnableC14192c(C14193d.C14194a c14194a, FidoReInfo fidoReInfo) {
        this.f27696b = c14194a;
        this.f27695a = fidoReInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f27695a.getStatus() == FidoStatus.SUCCESS) {
            C14193d c14193d = C14193d.this;
            C14200g.m76a(c14193d.f27701g, c14193d.f27698d, this.f27695a.getMfacResponse(), C14193d.this.f27699e);
            return;
        }
        C14193d.this.f27700f.setCode("0003");
        C14193d.this.f27700f.setMsg("开通失败，请稍后重试");
        C14193d c14193d2 = C14193d.this;
        DataCallback dataCallback = c14193d2.f27699e;
        WPResult wPResult = c14193d2.f27700f;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        C14193d.this.f27701g.m1798a().mo110P();
    }
}
