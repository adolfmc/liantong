package p480u;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPFidoResultBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p470p0.C13648k;
import p482w.AbstractC14257d;

@NBSInstrumented
/* renamed from: u.e */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14196e extends AbstractC14257d<WPFidoResultBean> {

    /* renamed from: c */
    public final /* synthetic */ WPResult f27703c;

    /* renamed from: d */
    public final /* synthetic */ DataCallback f27704d;

    /* renamed from: e */
    public final /* synthetic */ String f27705e;

    /* renamed from: f */
    public final /* synthetic */ C14200g f27706f;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.e$a */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14197a extends TypeToken<WPResult<WPFidoResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14196e(C14200g c14200g, AbstractC12375a abstractC12375a, WPResult wPResult, DataCallback dataCallback, String str) {
        super(abstractC12375a);
        this.f27706f = c14200g;
        this.f27703c = wPResult;
        this.f27704d = dataCallback;
        this.f27705e = str;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
        if (wPResult.getData() == null) {
            this.f27703c.setCode("0004");
            this.f27703c.setMsg("数据异常");
            DataCallback dataCallback = this.f27704d;
            WPResult wPResult2 = this.f27703c;
            Gson gson = C13648k.f27492a;
            dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson, wPResult2));
            this.f27706f.m1798a().mo110P();
            return;
        }
        WPQPayUserInfoBean m164a = C10546a.C10576i.f20125a.f20053c.m164a(wPResult.getData().getUserId());
        if (m164a == null) {
            m164a = new WPQPayUserInfoBean();
            m164a.setUserNo(wPResult.getData().getUserId());
        }
        m164a.openFido(this.f27705e);
        C10546a.C10576i.f20125a.f20053c.m165a(m164a);
        this.f27703c.setCode("0000");
        DataCallback dataCallback2 = this.f27704d;
        WPResult wPResult3 = this.f27703c;
        Gson gson2 = C13648k.f27492a;
        dataCallback2.onResult(!(gson2 instanceof Gson) ? gson2.toJson(wPResult3) : NBSGsonInstrumentation.toJson(gson2, wPResult3));
        this.f27706f.m1798a().mo103t();
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14197a().getType();
    }
}
