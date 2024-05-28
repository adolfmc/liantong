package com.networkbench.agent.impl.p267m;

import android.content.Context;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.harvest.p261b.C6459b;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.m.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6497d extends HarvestableObject {

    /* renamed from: a */
    protected static final InterfaceC6393e f16448a = C6394f.m10150a();

    /* renamed from: b */
    protected Context f16449b;

    /* renamed from: d */
    protected int f16451d;

    /* renamed from: e */
    protected EnumC6455q f16452e;

    /* renamed from: g */
    private HarvestConnection f16454g;

    /* renamed from: c */
    protected String f16450c = "";

    /* renamed from: f */
    private final ScheduledExecutorService f16453f = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: b */
    public abstract void mo9739b();

    /* renamed from: c */
    protected abstract boolean mo9738c();

    /* renamed from: d */
    protected abstract void mo9737d();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public void mo9750e() {
        if (C6638h.m8963w().m8976o() && C6459b.m9935e()) {
            C6396h.m10125q("harvestData  send onEvent data : .....");
            C6396h.m10130l("useraction  harvestData gather  begin !!");
            if (mo9738c()) {
                f16448a.mo10122a("the user action data is empty!");
            } else {
                m9749f();
            }
        }
    }

    /* renamed from: f */
    protected void m9749f() {
        if (HarvestConnection.isSoDisable()) {
            if (!m9751a(asJsonObject().toString())) {
                f16448a.mo10122a("flushInternal failed");
            } else {
                mo9737d();
            }
        } else if (!m9752a(asJsonObject())) {
            f16448a.mo10122a("flushInternal failed");
        } else {
            f16448a.mo10122a("flushInternal success and reset!");
            mo9737d();
        }
    }

    /* renamed from: a */
    private boolean m9752a(JsonObject jsonObject) {
        if (jsonObject == null) {
            return false;
        }
        if (this.f16454g == null) {
            this.f16454g = Harvest.getInstance().getHarvestConnection();
        }
        HarvestConnection harvestConnection = this.f16454g;
        if (harvestConnection == null) {
            f16448a.mo10122a("flushInternal harvest connection is null and stop!");
            return false;
        }
        HarvestResponse sendDataPb = harvestConnection.sendDataPb(jsonObject.toString(), this.f16451d, this.f16454g.getApplicationToken(), "token=");
        if (sendDataPb == null) {
            return false;
        }
        int statusCode = sendDataPb.getStatusCode();
        InterfaceC6393e interfaceC6393e = f16448a;
        interfaceC6393e.mo10122a("send user base data response status code:" + statusCode);
        if (statusCode != 0) {
            f16448a.mo10122a("socket error code is 1000, send failed!");
            return false;
        }
        f16448a.mo10122a("send success NBSUserBase~~~");
        return true;
    }

    /* renamed from: a */
    public void mo9741a(HarvestConnection harvestConnection) {
        this.f16454g = harvestConnection;
        this.f16453f.schedule(new Runnable() { // from class: com.networkbench.agent.impl.m.d.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    AbstractC6497d.this.mo9750e();
                } catch (Exception e) {
                    C6638h.f17124y.mo10121a("HarvestTimer userActions stop error:", e);
                }
            }
        }, 0L, TimeUnit.SECONDS);
    }

    /* renamed from: a */
    private boolean m9751a(String str) {
        if (str == null) {
            return false;
        }
        try {
            return Harvest.getInstance().getHarvestConnection().getResponse(str, this.f16452e).isStatusCode200();
        } catch (Throwable unused) {
            return false;
        }
    }
}
