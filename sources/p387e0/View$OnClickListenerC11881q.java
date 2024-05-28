package p387e0;

import android.text.TextUtils;
import android.view.View;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.normal.order.bean.WPNoticeInfoBean;
import java.util.ArrayList;
import p385d0.C11819f;
import p387e0.C11882r;

@NBSInstrumented
/* renamed from: e0.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class View$OnClickListenerC11881q implements View.OnClickListener {

    /* renamed from: a */
    public final /* synthetic */ WPNoticeInfoBean f24205a;

    /* renamed from: b */
    public final /* synthetic */ C11882r.C11883a f24206b;

    /* renamed from: c */
    public final /* synthetic */ C11882r f24207c;

    public View$OnClickListenerC11881q(C11882r c11882r, WPNoticeInfoBean wPNoticeInfoBean, C11882r.C11883a c11883a) {
        this.f24207c = c11882r;
        this.f24205a = wPNoticeInfoBean;
        this.f24206b = c11883a;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (TextUtils.isEmpty(this.f24205a.getNoticeDesc())) {
            NBSActionInstrumentation.onClickEventExit();
        } else if (this.f24206b.getAdapterPosition() == -1) {
            NBSActionInstrumentation.onClickEventExit();
        } else {
            this.f24205a.setExpand(!this.f24205a.isExpand());
            C11882r c11882r = this.f24207c;
            int adapterPosition = this.f24206b.getAdapterPosition();
            ArrayList<WPNoticeInfoBean> arrayList = c11882r.f24208a;
            if (arrayList != null && !arrayList.isEmpty()) {
                for (int i = 0; i < c11882r.f24208a.size(); i++) {
                    if (i != adapterPosition) {
                        c11882r.f24208a.get(i).setExpand(false);
                    }
                }
            }
            this.f24207c.notifyDataSetChanged();
            C11882r.InterfaceC11884b interfaceC11884b = this.f24207c.f24209b;
            if (interfaceC11884b != null) {
                ((C11819f) interfaceC11884b).m1987a("点击-公告页-展开公告说明");
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
