package p480u;

import android.app.Activity;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.common.bean.WPFidoResultBean;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import java.lang.reflect.Type;
import p411o.AbstractC12375a;
import p470p0.C13648k;
import p470p0.C13652o;
import p482w.AbstractC14257d;

/* renamed from: u.d */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14193d extends AbstractC14257d<WPFidoResultBean> {

    /* renamed from: c */
    public final /* synthetic */ Activity f27697c;

    /* renamed from: d */
    public final /* synthetic */ String f27698d;

    /* renamed from: e */
    public final /* synthetic */ DataCallback f27699e;

    /* renamed from: f */
    public final /* synthetic */ WPResult f27700f;

    /* renamed from: g */
    public final /* synthetic */ C14200g f27701g;

    @NBSInstrumented
    /* renamed from: u.d$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class C14194a implements FidoCallback<FidoReInfo> {
        public C14194a() {
        }

        @Override // com.gmrz.appsdk.commlib.api.FidoCallback
        public final void onFidoProcess(FidoReInfo fidoReInfo) {
            FidoReInfo fidoReInfo2 = fidoReInfo;
            Gson gson = C13648k.f27492a;
            C13652o.m174a("FidoOpen", !(gson instanceof Gson) ? gson.toJson(fidoReInfo2) : NBSGsonInstrumentation.toJson(gson, fidoReInfo2));
            C14193d.this.f27697c.runOnUiThread(new RunnableC14192c(this, fidoReInfo2));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: u.d$b */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class C14195b extends TypeToken<WPResult<WPFidoResultBean>> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C14193d(C14200g c14200g, AbstractC12375a abstractC12375a, Activity activity, String str, DataCallback dataCallback, WPResult wPResult) {
        super(abstractC12375a);
        this.f27701g = c14200g;
        this.f27697c = activity;
        this.f27698d = str;
        this.f27699e = dataCallback;
        this.f27700f = wPResult;
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: b */
    public final void mo9b(WPResult<WPFidoResultBean> wPResult) {
        Activity activity = this.f27697c;
        String uafRequest = wPResult.getData().getUafRequest();
        C14194a c14194a = new C14194a();
        FidoAppSDK.getInstance().processAsync(activity, FidoIn.Builder().setFidoIn(uafRequest), c14194a);
    }

    @Override // p482w.AbstractC14257d
    /* renamed from: c */
    public final Type mo8c() {
        return new C14195b().getType();
    }
}
