package p395i;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p083v4.app.DialogFragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentTransaction;
import android.support.p086v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.normal.order.p359ui.WPOrderActivity;
import java.io.Serializable;
import p091c0.C1529j;
import p408n.C12348l;

@NBSInstrumented
/* renamed from: i.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC12045a extends DialogFragment {

    /* renamed from: a */
    public InterfaceC12047b f24380a;

    /* renamed from: b */
    public C12348l.C12349a f24381b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: i.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C12046a<T extends C12046a> implements Serializable {

        /* renamed from: a */
        public C12348l.C12349a f24382a;

        public C12046a(FragmentActivity fragmentActivity) {
            this.f24382a = new C12348l.C12349a(fragmentActivity);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: i.a$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC12047b {
    }

    @Override // android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        InterfaceC12047b interfaceC12047b = this.f24380a;
        if (interfaceC12047b != null) {
            WPOrderActivity.C10659e c10659e = (WPOrderActivity.C10659e) interfaceC12047b;
            WPOrderActivity wPOrderActivity = WPOrderActivity.this;
            ((C1529j) wPOrderActivity.f24311a).m22172b(wPOrderActivity.f20315s0.getData().getTradeOrderNo());
            WPOrderActivity.this.f20327y0 = true;
        }
    }

    @Override // android.support.p083v4.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        C12348l c12348l = ((C12048b) this).f24384c;
        builder.setView(c12348l == null ? null : c12348l.f24991c.f25007c);
        AlertDialog create = builder.create();
        C12348l.C12349a c12349a = this.f24381b;
        if (c12349a != null) {
            c12349a.f24992a = this;
            create.setCanceledOnTouchOutside(c12349a.f24998g);
            this.f24381b.getClass();
        }
        return create;
    }

    @Override // android.support.p083v4.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.f24381b = null;
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onStart() {
        C12348l.C12349a c12349a;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (c12349a = this.f24381b) == null) {
            return;
        }
        setCancelable(c12349a.f24999h);
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(17170445);
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f24381b.getClass();
        attributes.width = (int) (displayMetrics.widthPixels * 0.8f);
        this.f24381b.getClass();
        this.f24381b.getClass();
        this.f24381b.getClass();
        attributes.gravity = 17;
        if (this.f24381b.f24995d.mo1908b() == 2 && attributes.gravity == 80) {
            attributes.y = 20;
        }
        this.f24381b.getClass();
        this.f24381b.getClass();
        this.f24381b.getClass();
        window.addFlags(2);
        this.f24381b.getClass();
        window.setAttributes(attributes);
    }

    @Override // android.support.p083v4.app.DialogFragment
    public final void show(FragmentManager fragmentManager, String str) {
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.setTransition(4097);
        beginTransaction.add(this, str);
        beginTransaction.commitAllowingStateLoss();
    }
}
