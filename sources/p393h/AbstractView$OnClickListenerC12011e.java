package p393h;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.DialogFragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import com.unicom.pay.normal.order.p359ui.C10679a;
import com.unicom.pay.normal.order.p359ui.C10681b;
import com.unicom.pay.utils.buried.WPBusinessInfoBean;
import com.unicom.pay.utils.buried.WPTrendsEventsUtils;
import p411o.AbstractC12375a;
import p411o.InterfaceC12376b;
import p470p0.C13646i;
import p472q0.C13692n;
import p477s0.C14116a;

@NBSInstrumented
/* renamed from: h.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractView$OnClickListenerC12011e<P extends AbstractC12375a> extends DialogFragment implements View.OnClickListener, InterfaceC12016h, InterfaceC12376b {

    /* renamed from: a */
    public P f24344a;

    /* renamed from: b */
    public InterfaceC12013b f24345b;

    /* renamed from: c */
    public long f24346c;

    /* renamed from: d */
    public long f24347d;

    /* renamed from: e */
    public WPBusinessInfoBean f24348e;

    /* renamed from: f */
    public WPBusinessInfoBean f24349f;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: h.e$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class DialogInterface$OnDismissListenerC12012a implements DialogInterface.OnDismissListener {
        public DialogInterface$OnDismissListenerC12012a() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            AbstractView$OnClickListenerC12011e.this.f24345b.mo1983a();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: h.e$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12013b {
        /* renamed from: a */
        void mo1983a();
    }

    /* renamed from: A */
    public void mo113A() {
        this.f24347d = System.currentTimeMillis();
        try {
            if (this.f24348e == null) {
                this.f24348e = WPBusinessInfoBean.generatePageEntity();
            }
            WPTrendsEventsUtils.trendsPageData(this.f24348e, mo56D(), mo36p(), mo53O(), C13646i.m182a(this.f24347d - this.f24346c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: D */
    public String mo56D() {
        return null;
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: I */
    public final void mo1793I() {
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof AbstractDialogInterface$OnCancelListenerC12000b)) {
            ((AbstractDialogInterface$OnCancelListenerC12000b) activity).mo1793I();
        }
    }

    /* renamed from: O */
    public String mo53O() {
        return null;
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: Y */
    public final void mo1792Y() {
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof AbstractDialogInterface$OnCancelListenerC12000b)) {
            AbstractDialogInterface$OnCancelListenerC12000b abstractDialogInterface$OnCancelListenerC12000b = (AbstractDialogInterface$OnCancelListenerC12000b) activity;
            FragmentManager childFragmentManager = getChildFragmentManager();
            if (abstractDialogInterface$OnCancelListenerC12000b.f24315e == null) {
                C14116a c14116a = new C14116a();
                abstractDialogInterface$OnCancelListenerC12000b.f24315e = c14116a;
                c14116a.setCancelable(true);
                abstractDialogInterface$OnCancelListenerC12000b.f24315e.f24345b = new C12006c(abstractDialogInterface$OnCancelListenerC12000b);
            }
            if (abstractDialogInterface$OnCancelListenerC12000b.f24315e.isAdded() || abstractDialogInterface$OnCancelListenerC12000b.isFinishing()) {
                return;
            }
            abstractDialogInterface$OnCancelListenerC12000b.f24315e.show(childFragmentManager, "loadingDialog");
        }
    }

    /* renamed from: Z */
    public int mo52Z() {
        return 17;
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: a */
    public final void mo1791a(int i) {
        try {
            C13692n.m135a(C13692n.f27554a.getResources().getText(i));
        } catch (Resources.NotFoundException unused) {
            C13692n.m135a(String.valueOf(i));
        }
    }

    /* renamed from: a */
    public abstract void mo5a(Dialog dialog);

    /* renamed from: a0 */
    public abstract int mo4a0();

    /* renamed from: b0 */
    public int mo44b0() {
        return C10531R.C10537style.WPOtherDialog;
    }

    /* renamed from: c0 */
    public float mo3c0() {
        return 0.0f;
    }

    /* renamed from: d0 */
    public abstract void mo2d0();

    @Override // android.support.p083v4.app.DialogFragment
    public void dismiss() {
        super.dismiss();
        mo113A();
    }

    @Override // android.support.p083v4.app.DialogFragment
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
        mo113A();
    }

    /* renamed from: e0 */
    public boolean mo1985e0() {
        return !(this instanceof C10679a);
    }

    /* renamed from: f */
    public final void m1984f(String str) {
        try {
            if (this.f24349f == null) {
                this.f24349f = WPBusinessInfoBean.generateButtonEntity();
            }
            WPTrendsEventsUtils.trendsPageButtonData(this.f24349f, mo56D(), mo36p(), mo53O(), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: f0 */
    public float mo1f0() {
        return 0.0f;
    }

    @Override // p411o.InterfaceC12376b
    /* renamed from: i */
    public final void mo1790i(String str) {
        C13692n.m135a(str);
    }

    @Override // p411o.InterfaceC12376b
    @Nullable
    /* renamed from: l */
    public final /* synthetic */ Activity mo1789l() {
        return super.getActivity();
    }

    /* renamed from: m */
    public abstract P mo0m();

    @Override // android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        mo113A();
    }

    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.p083v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        P mo0m = mo0m();
        this.f24344a = mo0m;
        if (mo0m != null) {
            mo0m.m1796a(this);
        }
        Dialog dialog = new Dialog(getActivity(), mo44b0());
        dialog.setContentView(mo4a0());
        dialog.setCanceledOnTouchOutside(mo1985e0());
        Window window = dialog.getWindow();
        window.setWindowAnimations(0);
        if (this.f24345b != null) {
            dialog.setOnDismissListener(new DialogInterface$OnDismissListenerC12012a());
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = mo52Z() == 0 ? 80 : mo52Z();
        attributes.y = 0;
        attributes.width = -1;
        attributes.width = mo1f0() > 0.0f ? (int) (mo1f0() * getActivity().getWindowManager().getDefaultDisplay().getWidth()) : getActivity().getWindowManager().getDefaultDisplay().getWidth();
        attributes.height = mo3c0() > 0.0f ? (int) (mo3c0() * getActivity().getWindowManager().getDefaultDisplay().getHeight()) : getActivity().getWindowManager().getDefaultDisplay().getHeight() / 2;
        if (this instanceof C10681b) {
            attributes.height = -2;
        }
        window.setAttributes(attributes);
        mo5a(dialog);
        mo2d0();
        return dialog;
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    /* renamed from: p */
    public String mo36p() {
        return null;
    }

    /* renamed from: q */
    public void mo112q() {
        this.f24346c = System.currentTimeMillis();
        WPTrendsEventsUtils.addWindow(mo53O());
        if (this.f24348e == null) {
            this.f24348e = WPBusinessInfoBean.generatePageEntity();
        }
    }

    @Override // android.support.p083v4.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        if (fragmentManager == null) {
            return;
        }
        if (((AbstractView$OnClickListenerC12011e) fragmentManager.findFragmentByTag(str)) == null || !isAdded()) {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(this, str);
            beginTransaction.commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            mo112q();
        }
    }
}
