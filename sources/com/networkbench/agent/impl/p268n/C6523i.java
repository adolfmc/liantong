package com.networkbench.agent.impl.p268n;

import android.webkit.WebView;
import com.networkbench.agent.impl.harvest.ActionData;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6523i extends AbstractC6527l {

    /* renamed from: g */
    private int f16629g;

    /* renamed from: a */
    public void m9545a(int i) {
        this.f16629g = i;
    }

    public C6523i(WebView webView, String str) {
        super(webView, str);
    }

    @Override // com.networkbench.agent.impl.p268n.AbstractC6527l
    /* renamed from: a */
    protected ActionData mo9524a() {
        ActionData actionData = new ActionData();
        actionData.setStatusCode(908);
        actionData.setErrorCode(908);
        return actionData;
    }
}
