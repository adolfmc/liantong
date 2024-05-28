package com.networkbench.agent.impl.p268n;

import android.text.TextUtils;
import android.webkit.WebView;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.util.C6638h;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6521g extends AbstractC6527l {

    /* renamed from: g */
    private int f16615g;

    /* renamed from: h */
    private String f16616h;

    /* renamed from: i */
    private String f16617i;

    /* renamed from: j */
    private Map<String, String> f16618j;

    /* renamed from: k */
    private boolean f16619k;

    public C6521g(WebView webView, String str) {
        super(webView, str);
    }

    /* renamed from: a */
    public void m9557a(String str) {
        this.f16616h = str;
    }

    /* renamed from: a */
    public void m9558a(int i) {
        this.f16615g = i;
    }

    /* renamed from: b */
    public void m9555b(String str) {
        this.f16617i = str;
    }

    /* renamed from: a */
    public void m9556a(Map<String, String> map) {
        this.f16618j = map;
    }

    @Override // com.networkbench.agent.impl.p268n.AbstractC6527l
    /* renamed from: a */
    protected ActionData mo9524a() {
        ActionData actionData = new ActionData();
        actionData.setDataTag(C6638h.m8963w().m9001h());
        actionData.setStatusCode(this.f16615g);
        actionData.setErrorCode(this.f16615g);
        actionData.setRequestMethod(RequestMethodType.GET);
        if (!TextUtils.isEmpty(this.f16617i)) {
            actionData.setRequestMethod(NBSTransactionStateUtil.setRequestMethod(this.f16617i));
        }
        return actionData;
    }
}
