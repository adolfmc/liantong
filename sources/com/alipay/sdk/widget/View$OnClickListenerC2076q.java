package com.alipay.sdk.widget;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import com.alipay.sdk.widget.WebViewWindow;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.widget.q */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class View$OnClickListenerC2076q implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ WebViewWindow f3979a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC2076q(WebViewWindow webViewWindow) {
        this.f3979a = webViewWindow;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        WebViewWindow.InterfaceC2057c interfaceC2057c;
        Handler handler;
        ImageView imageView;
        ImageView imageView2;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        interfaceC2057c = this.f3979a.f3920i;
        if (interfaceC2057c != null) {
            view.setEnabled(false);
            handler = WebViewWindow.f3912f;
            handler.postDelayed(new RunnableC2077r(this, view), 256L);
            imageView = this.f3979a.f3913a;
            if (view != imageView) {
                imageView2 = this.f3979a.f3915c;
                if (view == imageView2) {
                    interfaceC2057c.mo20592b(this.f3979a);
                }
            } else {
                interfaceC2057c.mo20604a(this.f3979a);
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
