package p469p;

import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.modules.result.p356ui.WPPayResultActivity;
import p469p.C13624c;

@NBSInstrumented
/* renamed from: p.b */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC13623b implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C13624c.C13625a f27452a;

    public View$OnClickListenerC13623b(C13624c.C13625a c13625a) {
        this.f27452a = c13625a;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        C13624c.C13625a c13625a = this.f27452a;
        C13624c.InterfaceC13633i interfaceC13633i = C13624c.this.f27455c;
        if (interfaceC13633i != null) {
            ((WPPayResultActivity) interfaceC13633i).m6163a(c13625a.f27458c, c13625a.f27456a.getAdapterPosition());
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
