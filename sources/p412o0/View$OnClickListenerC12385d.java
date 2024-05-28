package p412o0;

import android.view.View;
import cn.ltzf.passguard.C1730a;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.qpay.setting.bean.WPLimitBean;
import com.unicom.pay.qpay.setting.p361ui.WPSettingLimitActivity;
import p412o0.C12386e;

@NBSInstrumented
/* renamed from: o0.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC12385d implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ C12386e.C12387a f25050a;

    /* renamed from: b */
    public final /* synthetic */ WPLimitBean f25051b;

    /* renamed from: c */
    public final /* synthetic */ C12386e f25052c;

    public View$OnClickListenerC12385d(C12386e c12386e, C12386e.C12387a c12387a, WPLimitBean wPLimitBean) {
        this.f25052c = c12386e;
        this.f25050a = c12387a;
        this.f25051b = wPLimitBean;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (this.f25052c.f25055c == this.f25050a.getAdapterPosition()) {
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        this.f25052c.f25055c = this.f25050a.getAdapterPosition();
        this.f25052c.notifyDataSetChanged();
        C12386e.InterfaceC12388b interfaceC12388b = this.f25052c.f25057e;
        if (interfaceC12388b != null) {
            WPLimitBean wPLimitBean = this.f25051b;
            StringBuilder m22016a = C1730a.m22016a("勾选-限额设置-");
            m22016a.append(wPLimitBean.getPayLimit());
            ((WPSettingLimitActivity) interfaceC12388b).m1991k(m22016a.toString());
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
