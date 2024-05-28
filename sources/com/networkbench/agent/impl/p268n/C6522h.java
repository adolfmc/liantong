package com.networkbench.agent.impl.p268n;

import android.webkit.WebView;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.AbstractC6527l;
import com.networkbench.agent.impl.util.C6638h;
import java.io.InputStream;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6522h extends AbstractC6527l implements AbstractC6527l.InterfaceC6528a {

    /* renamed from: g */
    private String f16620g;

    /* renamed from: h */
    private Map<String, String> f16621h;

    /* renamed from: i */
    private boolean f16622i;

    /* renamed from: j */
    private InputStream f16623j;

    /* renamed from: k */
    private String f16624k;

    /* renamed from: l */
    private String f16625l;

    /* renamed from: m */
    private String f16626m;

    /* renamed from: n */
    private Map<String, String> f16627n;

    /* renamed from: o */
    private int f16628o;

    /* renamed from: a */
    public void m9552a(String str) {
        this.f16620g = str;
    }

    /* renamed from: a */
    public void m9551a(Map<String, String> map) {
        this.f16621h = map;
    }

    /* renamed from: a */
    public void m9550a(boolean z) {
        this.f16622i = z;
    }

    /* renamed from: a */
    public void m9553a(InputStream inputStream) {
        this.f16623j = inputStream;
    }

    /* renamed from: b */
    public void m9549b(String str) {
        this.f16624k = str;
    }

    /* renamed from: c */
    public void m9547c(String str) {
        this.f16625l = str;
    }

    /* renamed from: d */
    public void m9546d(String str) {
        this.f16626m = str;
    }

    /* renamed from: b */
    public void m9548b(Map<String, String> map) {
        this.f16627n = map;
    }

    /* renamed from: a */
    public void m9554a(int i) {
        this.f16628o = i;
    }

    public C6522h(WebView webView, String str) {
        super(webView, str);
    }

    @Override // com.networkbench.agent.impl.p268n.AbstractC6527l
    /* renamed from: a */
    protected ActionData mo9524a() {
        ActionData actionData = new ActionData();
        actionData.setRequestMethod(NBSTransactionStateUtil.setRequestMethod(this.f16620g));
        actionData.setStatusCode(this.f16628o);
        actionData.setErrorCode(this.f16628o);
        if (this.f16627n != null && NBSAgent.getImpl() != null && NBSAgent.getImpl().m9150m() != null && Harvest.isCdn_enabled()) {
            C6638h.f17124y.mo10122a("begin to get cdn header name");
            String cdnHeaderName = NBSAgent.getImpl().m9150m().getCdnHeaderName();
            if (cdnHeaderName != null) {
                for (String str : this.f16627n.keySet()) {
                    if (cdnHeaderName.equalsIgnoreCase(str)) {
                        actionData.setCdnVendorName(this.f16627n.get(str) == null ? "" : this.f16627n.get(str));
                    }
                }
            }
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("response header:" + this.f16627n.toString());
        m9522a(this);
        return actionData;
    }

    @Override // com.networkbench.agent.impl.p268n.AbstractC6527l.InterfaceC6528a
    /* renamed from: b */
    public Map<String, String> mo9520b() {
        return this.f16627n;
    }
}
