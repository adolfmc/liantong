package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.harvest.HarvestResponse;
import java.net.HttpURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6434a extends AbstractC6444i {
    public C6434a(String str, boolean z) {
        super(str, z);
        m9968b("ActionSelectUpload");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return m9971a("/mobile/operate/api/produceAppData");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        return super.mo9947a(str, harvestResponse);
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HttpURLConnection mo9970a(HttpURLConnection httpURLConnection) {
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary====newlens===");
        return httpURLConnection;
    }
}
