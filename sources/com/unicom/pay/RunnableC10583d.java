package com.unicom.pay;

import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10584e;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import java.util.ArrayList;
import p470p0.C13648k;
import p470p0.C13652o;

@NBSInstrumented
/* renamed from: com.unicom.pay.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10583d implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ FidoReInfo f20128a;

    /* renamed from: b */
    public final /* synthetic */ C10584e.C10585a f20129b;

    public RunnableC10583d(C10584e.C10585a c10585a, FidoReInfo fidoReInfo) {
        this.f20129b = c10585a;
        this.f20128a = fidoReInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        ArrayList arrayList = new ArrayList();
        if (C10584e.this.f20131d.contains("|")) {
            if (this.f20128a.getFidoFaceStatus() == FidoStatus.SUCCESS) {
                arrayList.add("02");
            }
            if (this.f20128a.getFpStatus() == FidoStatus.SUCCESS) {
                str = "00";
                arrayList.add(str);
            }
        } else if (this.f20128a.getStatus() == FidoStatus.SUCCESS) {
            str = C10584e.this.f20131d;
            arrayList.add(str);
        }
        C10584e.this.f20132e.setCode("0000");
        C10584e.this.f20132e.setData(arrayList);
        C10584e c10584e = C10584e.this;
        DataCallback dataCallback = c10584e.f20133f;
        WPResult wPResult = c10584e.f20132e;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
        WPResult wPResult2 = C10584e.this.f20132e;
        Gson gson2 = C13648k.f27492a;
        C13652o.m174a("WopayConfig code:", !(gson2 instanceof Gson) ? gson2.toJson(wPResult2) : NBSGsonInstrumentation.toJson(gson2, wPResult2));
    }
}
