package p088b;

import android.app.Activity;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoCallback;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.callback.DataCallback;

/* renamed from: b.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1466b implements FidoCallback<FidoReInfo> {

    /* renamed from: a */
    public final /* synthetic */ Activity f2500a;

    /* renamed from: b */
    public final /* synthetic */ DataCallback f2501b;

    /* renamed from: c */
    public final /* synthetic */ C10546a f2502c;

    public C1466b(C10546a c10546a, Activity activity, DataCallback dataCallback) {
        this.f2502c = c10546a;
        this.f2500a = activity;
        this.f2501b = dataCallback;
    }

    @Override // com.gmrz.appsdk.commlib.api.FidoCallback
    public final void onFidoProcess(FidoReInfo fidoReInfo) {
        this.f2500a.runOnUiThread(new RunnableC1465a(this, fidoReInfo));
    }
}
