package p477s0;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentManager;
import android.widget.ImageView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10531R;
import p393h.AbstractDialogInterface$OnCancelListenerC12000b;
import p393h.AbstractView$OnClickListenerC12011e;
import p411o.AbstractC12375a;
import p470p0.C13647j;

@NBSInstrumented
/* renamed from: s0.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C14116a extends AbstractView$OnClickListenerC12011e {

    /* renamed from: g */
    public ImageView f27652g;

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: A */
    public final void mo113A() {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: D */
    public final String mo56D() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: O */
    public final String mo53O() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: Z */
    public final int mo52Z() {
        return 17;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a */
    public final void mo5a(Dialog dialog) {
        this.f27652g = (ImageView) dialog.findViewById(C10531R.C10534id.wopay_lottie_loading);
        C13647j.m180a(getContext(), C10531R.C10533drawable.up_loading, this.f27652g);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener((AbstractDialogInterface$OnCancelListenerC12000b) getActivity());
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: a0 */
    public final int mo4a0() {
        return C10531R.C10535layout.wp_pay_dialog_loading;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: b0 */
    public final int mo44b0() {
        return C10531R.C10537style.WPLoadingDialog;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: d0 */
    public final void mo2d0() {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: m */
    public final AbstractC12375a mo0m() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment
    @NonNull
    public final Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().clearFlags(2);
        return onCreateDialog;
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onStop() {
        getDialog();
        super.onStop();
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, p393h.InterfaceC12016h
    /* renamed from: p */
    public final String mo36p() {
        return null;
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e
    /* renamed from: q */
    public final void mo112q() {
    }

    @Override // p393h.AbstractView$OnClickListenerC12011e, android.support.p083v4.app.DialogFragment
    public final void show(FragmentManager fragmentManager, String str) {
        super.show(fragmentManager, str);
    }
}
