package com.networkbench.agent.impl.harvest.p260a;

import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.net.HttpURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6444i {

    /* renamed from: a */
    protected final InterfaceC6393e f16287a = C6394f.m10150a();

    /* renamed from: b */
    protected String f16288b;

    /* renamed from: c */
    private boolean f16289c;

    /* renamed from: d */
    private String f16290d;

    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        return harvestResponse;
    }

    /* renamed from: a */
    public abstract String mo9948a();

    /* renamed from: a */
    public HttpURLConnection mo9970a(HttpURLConnection httpURLConnection) {
        return httpURLConnection;
    }

    /* renamed from: b */
    public void mo9951b(HttpURLConnection httpURLConnection) {
    }

    public AbstractC6444i(String str, boolean z) {
        this.f16288b = str;
        this.f16289c = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public String m9971a(String str) {
        if (str.startsWith("http")) {
            return this.f16288b + str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f16289c ? "https://" : "http://");
        sb.append(this.f16288b);
        sb.append(str);
        return sb.toString();
    }

    /* renamed from: b */
    public void m9968b(String str) {
        this.f16290d = str;
    }

    /* renamed from: b */
    public String m9969b() {
        return this.f16290d;
    }
}
