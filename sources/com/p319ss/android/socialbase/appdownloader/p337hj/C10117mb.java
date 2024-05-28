package com.p319ss.android.socialbase.appdownloader.p337hj;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.p319ss.android.socialbase.appdownloader.p335b.AbstractC10098ox;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.hj.mb */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10117mb extends AbstractC10098ox {

    /* renamed from: mb */
    private AlertDialog.Builder f19516mb;

    public C10117mb(Context context) {
        this.f19516mb = new AlertDialog.Builder(context);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: mb */
    public InterfaceC10092je mo6782mb(int i) {
        AlertDialog.Builder builder = this.f19516mb;
        if (builder != null) {
            builder.setTitle(i);
        }
        return this;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: mb */
    public InterfaceC10092je mo6779mb(String str) {
        AlertDialog.Builder builder = this.f19516mb;
        if (builder != null) {
            builder.setMessage(str);
        }
        return this;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: mb */
    public InterfaceC10092je mo6781mb(int i, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = this.f19516mb;
        if (builder != null) {
            builder.setPositiveButton(i, onClickListener);
        }
        return this;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: ox */
    public InterfaceC10092je mo6778ox(int i, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = this.f19516mb;
        if (builder != null) {
            builder.setNegativeButton(i, onClickListener);
        }
        return this;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: mb */
    public InterfaceC10092je mo6780mb(DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = this.f19516mb;
        if (builder != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        return this;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
    /* renamed from: mb */
    public InterfaceC10091jb mo6783mb() {
        return new C10118mb(this.f19516mb);
    }

    /* renamed from: com.ss.android.socialbase.appdownloader.hj.mb$mb */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    static class C10118mb implements InterfaceC10091jb {

        /* renamed from: mb */
        private AlertDialog f19517mb;

        public C10118mb(AlertDialog.Builder builder) {
            if (builder != null) {
                this.f19517mb = builder.show();
            }
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb
        /* renamed from: mb */
        public void mo6777mb() {
            AlertDialog alertDialog = this.f19517mb;
            if (alertDialog != null) {
                alertDialog.show();
            }
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb
        /* renamed from: ox */
        public boolean mo6776ox() {
            AlertDialog alertDialog = this.f19517mb;
            if (alertDialog != null) {
                return alertDialog.isShowing();
            }
            return false;
        }
    }
}
