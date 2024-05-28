package p409n0;

import android.view.View;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import p395i.C12048b;

@NBSInstrumented
/* renamed from: n0.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12362b implements C12048b.InterfaceC12052d {
    @Override // p395i.C12048b.InterfaceC12052d
    public final void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        WPTrendsEventsUtils.trendsPageButtonData("关闭确认弹窗", "98U01170qp016", "qp016", "点击-关闭极速支付弹窗-继续使用");
        NBSActionInstrumentation.onClickEventExit();
    }
}
