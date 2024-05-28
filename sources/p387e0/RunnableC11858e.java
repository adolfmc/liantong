package p387e0;

import android.text.Html;
import com.unicom.pay.normal.discount.bean.WPDiscountDetailBean;
import p387e0.C11860g;

/* renamed from: e0.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC11858e implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C11860g.View$OnClickListenerC11861a f24130a;

    /* renamed from: b */
    public final /* synthetic */ WPDiscountDetailBean f24131b;

    public RunnableC11858e(C11860g.View$OnClickListenerC11861a view$OnClickListenerC11861a, WPDiscountDetailBean wPDiscountDetailBean) {
        this.f24130a = view$OnClickListenerC11861a;
        this.f24131b = wPDiscountDetailBean;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f24130a.f24145e.setText(Html.fromHtml(this.f24131b.getDiscountDesc()));
        if (this.f24130a.f24145e.getLineCount() > 1) {
            this.f24130a.f24144d.setText(Html.fromHtml(this.f24131b.getDiscountDesc()));
            this.f24130a.f24145e.setVisibility(8);
            this.f24130a.f24144d.setVisibility(0);
            this.f24130a.f24146f.setVisibility(0);
            return;
        }
        this.f24130a.f24145e.setVisibility(0);
        this.f24130a.f24144d.setVisibility(8);
        this.f24130a.f24146f.setVisibility(8);
    }
}
