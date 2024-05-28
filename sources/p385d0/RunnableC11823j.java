package p385d0;

import android.widget.LinearLayout;
import android.widget.TextView;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;

/* renamed from: d0.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC11823j implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ WPOrderActivity f24083a;

    public RunnableC11823j(WPOrderActivity wPOrderActivity) {
        this.f24083a = wPOrderActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView textView;
        try {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f24083a.f20257A.getLayoutParams();
            if (this.f24083a.f20257A.getLineCount() > 1) {
                layoutParams.weight = 1.0f;
                layoutParams.width = 0;
                textView = this.f24083a.f20257A;
            } else {
                layoutParams.weight = 0.0f;
                layoutParams.width = -2;
                textView = this.f24083a.f20257A;
            }
            textView.setLayoutParams(layoutParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
