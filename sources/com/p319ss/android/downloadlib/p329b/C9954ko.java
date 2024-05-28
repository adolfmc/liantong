package com.p319ss.android.downloadlib.p329b;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.p319ss.android.download.api.model.DownloadAlertDialogInfo;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10070x;
import com.p319ss.android.socialbase.appdownloader.p335b.C10095mb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.b.ko */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C9954ko extends C10095mb {

    /* renamed from: mb */
    private static String f19191mb = "ko";

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.C10095mb, com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b
    /* renamed from: mb */
    public boolean mo6874mb() {
        return true;
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p335b.C10095mb, com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b
    /* renamed from: mb */
    public InterfaceC10092je mo6873mb(final Context context) {
        return new InterfaceC10092je() { // from class: com.ss.android.downloadlib.b.ko.1

            /* renamed from: b */
            private DownloadAlertDialogInfo.C9826mb f19192b;

            /* renamed from: h */
            private DialogInterface.OnClickListener f19193h;

            /* renamed from: hj */
            private DialogInterface.OnClickListener f19194hj;

            /* renamed from: u */
            private DialogInterface.OnCancelListener f19197u;

            {
                this.f19192b = new DownloadAlertDialogInfo.C9826mb(context);
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10092je mo6782mb(int i) {
                this.f19192b.m7888mb(context.getResources().getString(i));
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10092je mo6779mb(String str) {
                this.f19192b.m7885ox(str);
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10092je mo6781mb(int i, DialogInterface.OnClickListener onClickListener) {
                this.f19192b.m7898b(context.getResources().getString(i));
                this.f19194hj = onClickListener;
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: ox */
            public InterfaceC10092je mo6778ox(int i, DialogInterface.OnClickListener onClickListener) {
                this.f19192b.m7895hj(context.getResources().getString(i));
                this.f19193h = onClickListener;
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10092je mo6780mb(DialogInterface.OnCancelListener onCancelListener) {
                this.f19197u = onCancelListener;
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10092je mo6871mb(boolean z) {
                this.f19192b.m7887mb(z);
                return this;
            }

            @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je
            /* renamed from: mb */
            public InterfaceC10091jb mo6783mb() {
                this.f19192b.m7889mb(new DownloadAlertDialogInfo.InterfaceC9827ox() { // from class: com.ss.android.downloadlib.b.ko.1.1
                    @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                    /* renamed from: mb */
                    public void mo7140mb(DialogInterface dialogInterface) {
                        if (C99551.this.f19194hj != null) {
                            C99551.this.f19194hj.onClick(dialogInterface, -1);
                        }
                    }

                    @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                    /* renamed from: ox */
                    public void mo7139ox(DialogInterface dialogInterface) {
                        if (C99551.this.f19193h != null) {
                            C99551.this.f19193h.onClick(dialogInterface, -2);
                        }
                    }

                    @Override // com.p319ss.android.download.api.model.DownloadAlertDialogInfo.InterfaceC9827ox
                    /* renamed from: b */
                    public void mo7141b(DialogInterface dialogInterface) {
                        if (C99551.this.f19197u == null || dialogInterface == null) {
                            return;
                        }
                        C99551.this.f19197u.onCancel(dialogInterface);
                    }
                });
                C10070x.m6967mb(C9954ko.f19191mb, "getThemedAlertDlgBuilder", null);
                this.f19192b.m7892mb(3);
                return new C9957mb(C9940x.m7377b().mo7903ox(this.f19192b.m7893mb()));
            }
        };
    }

    /* renamed from: com.ss.android.downloadlib.b.ko$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static class C9957mb implements InterfaceC10091jb {

        /* renamed from: mb */
        private Dialog f19199mb;

        public C9957mb(Dialog dialog) {
            if (dialog != null) {
                this.f19199mb = dialog;
                mo6777mb();
            }
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb
        /* renamed from: mb */
        public void mo6777mb() {
            Dialog dialog = this.f19199mb;
            if (dialog != null) {
                dialog.show();
            }
        }

        @Override // com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb
        /* renamed from: ox */
        public boolean mo6776ox() {
            Dialog dialog = this.f19199mb;
            if (dialog != null) {
                return dialog.isShowing();
            }
            return false;
        }
    }
}
