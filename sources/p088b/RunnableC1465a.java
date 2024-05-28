package p088b;

import android.text.TextUtils;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import java.util.ArrayList;
import p470p0.C13648k;
import p470p0.C13652o;

@NBSInstrumented
/* renamed from: b.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class RunnableC1465a implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ FidoReInfo f2498a;

    /* renamed from: b */
    public final /* synthetic */ C1466b f2499b;

    public RunnableC1465a(C1466b c1466b, FidoReInfo fidoReInfo) {
        this.f2499b = c1466b;
        this.f2498a = fidoReInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.f2498a.getFidoFaceStatus() == FidoStatus.SUCCESS) {
            stringBuffer.append("02");
        }
        if (this.f2498a.getFpStatus() == FidoStatus.SUCCESS) {
            if (!TextUtils.isEmpty(stringBuffer)) {
                stringBuffer.append("|");
            }
            stringBuffer.append("00");
        }
        if (!TextUtils.isEmpty(stringBuffer)) {
            C1466b c1466b = this.f2499b;
            C10546a.m6171a(c1466b.f2502c, c1466b.f2500a, stringBuffer.toString(), false, this.f2499b.f2501b);
            return;
        }
        WPResult wPResult = new WPResult();
        wPResult.setCode("0000");
        wPResult.setData(new ArrayList());
        DataCallback dataCallback = this.f2499b.f2501b;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        C13652o.m174a("WopayConfig code:", "no support");
    }
}
