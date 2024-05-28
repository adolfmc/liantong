package p393h;

import android.view.View;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import p395i.C12048b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: h.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11999a implements C12048b.InterfaceC12052d {
    @Override // p395i.C12048b.InterfaceC12052d
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        NBSActionInstrumentation.onClickEventExit();
    }
}
