package com.sinovatech.unicom.basic.p315ui;

import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$3$d8gjYXiB0vpmS0nDEI5DdlPAK6g  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$JSInvokeHandler$3$d8gjYXiB0vpmS0nDEI5DdlPAK6g implements ManagerOneKeyLogin.OneKeyLoginCallback {
    private final /* synthetic */ JSInvokeHandler.RunnableC72063 f$0;

    @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerOneKeyLogin.OneKeyLoginCallback
    public final void callBack() {
        JSInvokeHandler.this.webViewHandler.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.-$$Lambda$JSInvokeHandler$3$jBJPgRNsDGbkbDpcnuaEuX0V4Ng
            @Override // java.lang.Runnable
            public final void run() {
                JSInvokeHandler.RunnableC72063.lambda$run$1(JSInvokeHandler.RunnableC72063.this);
            }
        });
    }
}
