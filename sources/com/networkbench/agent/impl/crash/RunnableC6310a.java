package com.networkbench.agent.impl.crash;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.harvest.HarvestSoc;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6310a implements Runnable {

    /* renamed from: b */
    private static final InterfaceC6393e f15837b = C6394f.m10150a();

    /* renamed from: a */
    private JsonObject f15838a;

    /* renamed from: c */
    private InterfaceC6329h f15839c;

    /* renamed from: d */
    private String f15840d;

    /* renamed from: e */
    private int f15841e;

    /* renamed from: f */
    private String f15842f;

    public RunnableC6310a(JsonObject jsonObject, InterfaceC6329h interfaceC6329h, String str, int i, String str2) {
        if (jsonObject == null || interfaceC6329h == null) {
            throw new IllegalArgumentException("error in param in error runnable");
        }
        this.f15838a = jsonObject;
        this.f15839c = interfaceC6329h;
        this.f15840d = str;
        this.f15841e = i;
        this.f15842f = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            HarvestResponse sendDataInfo = HarvestSoc.sendDataInfo(this.f15838a.toString(), this.f15841e, C6638h.m8963w().m9075L(), "token=");
            if (sendDataInfo == null) {
                return;
            }
            InterfaceC6393e interfaceC6393e = f15837b;
            interfaceC6393e.mo10122a("crash report status code:" + sendDataInfo.getStatusCode());
            if (sendDataInfo.getStatusCode() < 400) {
                f15837b.mo10122a("start to delete store crash");
                this.f15839c.mo10376a(this.f15840d);
                f15837b.mo10122a("report crash success ,crash has been delete");
                if (this.f15841e != 5 || TextUtils.isEmpty(this.f15842f)) {
                    return;
                }
                Harvest.getInstance().getHarvester().sendForgeUserActionItemPb(this.f15842f);
            }
        } catch (Exception e) {
            f15837b.mo10121a("crash report thread occur Exception", e);
        }
    }
}
