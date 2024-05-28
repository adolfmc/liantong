package p393h;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.C0884R;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.p083v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import p470p0.C13646i;

/* renamed from: h.d */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC12007d extends BottomSheetDialogFragment implements InterfaceC12016h {

    /* renamed from: a */
    public ImageView f24335a;

    /* renamed from: b */
    public ImageView f24336b;

    /* renamed from: c */
    public boolean f24337c;

    /* renamed from: d */
    public long f24338d;

    /* renamed from: e */
    public long f24339e;

    /* renamed from: f */
    public WPBusinessInfoBean f24340f;

    /* renamed from: g */
    public WPBusinessInfoBean f24341g;

    /* renamed from: h.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnShowListenerC12008a implements DialogInterface.OnShowListener {
        @Override // android.content.DialogInterface.OnShowListener
        public final void onShow(DialogInterface dialogInterface) {
            try {
                BottomSheetBehavior.from((FrameLayout) ((BottomSheetDialog) dialogInterface).findViewById(C0884R.C0887id.design_bottom_sheet)).setState(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: h.d$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class View$OnClickListenerC12009b implements View.OnClickListener {
        public View$OnClickListenerC12009b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            AbstractC12007d.this.mo1989a();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: h.d$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class View$OnClickListenerC12010c implements View.OnClickListener {
        public View$OnClickListenerC12010c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            AbstractC12007d.this.mo1989a();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public void mo1989a() {
        dismiss();
    }

    /* renamed from: a */
    public final void m1988a(View view, String str) {
        ((TextView) view.findViewById(C10531R.C10534id.wopay_half_title_tv)).setText(str);
        ImageView imageView = (ImageView) view.findViewById(C10531R.C10534id.wopay_half_close_iv);
        this.f24336b = imageView;
        imageView.setOnClickListener(new View$OnClickListenerC12009b());
        ImageView imageView2 = (ImageView) view.findViewById(C10531R.C10534id.wopay_half_back_iv);
        this.f24335a = imageView2;
        imageView2.setOnClickListener(new View$OnClickListenerC12010c());
    }

    /* renamed from: a */
    public final void m1987a(String str) {
        try {
            if (this.f24341g == null) {
                this.f24341g = WPBusinessInfoBean.generateButtonEntity();
            }
            WPTrendsEventsUtils.trendsPageButtonData(this.f24341g, mo56D(), mo36p(), mo53O(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public final void m1986b() {
        this.f24339e = System.currentTimeMillis();
        try {
            if (this.f24340f == null) {
                this.f24340f = WPBusinessInfoBean.generatePageEntity();
            }
            WPTrendsEventsUtils.trendsPageData(this.f24340f, mo56D(), mo36p(), mo53O(), C13646i.m182a(this.f24339e - this.f24338d));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.DialogFragment
    public final void dismiss() {
        super.dismiss();
        this.f24337c = false;
        m1986b();
    }

    @Override // android.support.p083v4.app.DialogFragment
    public final void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
        this.f24337c = false;
        m1986b();
    }

    @Override // android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.f24337c = false;
        m1986b();
    }

    @Override // android.support.design.widget.BottomSheetDialogFragment, android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext(), C10531R.C10537style.WpBottomSheetDialog);
        bottomSheetDialog.setOnShowListener(new DialogInterface$OnShowListenerC12008a());
        return bottomSheetDialog;
    }

    @Override // android.support.p083v4.app.DialogFragment
    public final void show(FragmentManager fragmentManager, String str) {
        if ((fragmentManager.findFragmentByTag(str) == null || !isAdded()) && !this.f24337c) {
            super.show(fragmentManager, str);
            this.f24337c = true;
            this.f24338d = System.currentTimeMillis();
            WPTrendsEventsUtils.addWindow(mo53O());
            if (this.f24340f == null) {
                this.f24340f = WPBusinessInfoBean.generatePageEntity();
            }
        }
    }
}
