package com.p319ss.android.download.api.model;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.model.DownloadAlertDialogInfo */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DownloadAlertDialogInfo {

    /* renamed from: b */
    public String f18771b;

    /* renamed from: h */
    public String f18772h;

    /* renamed from: hj */
    public String f18773hj;

    /* renamed from: ko */
    public Drawable f18774ko;

    /* renamed from: lz */
    public View f18775lz;

    /* renamed from: mb */
    public Context f18776mb;

    /* renamed from: ox */
    public String f18777ox;

    /* renamed from: u */
    public boolean f18778u;

    /* renamed from: ww */
    public InterfaceC9827ox f18779ww;

    /* renamed from: x */
    public int f18780x;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.download.api.model.DownloadAlertDialogInfo$Scene */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface Scene {
        public static final int BACK_INSTALL = 1;
        public static final int CANCEL = 3;
        public static final int OPEN_APP = 2;
        public static final int WEBVIEW_START = 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.download.api.model.DownloadAlertDialogInfo$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9827ox {
        /* renamed from: b */
        void mo7141b(DialogInterface dialogInterface);

        /* renamed from: mb */
        void mo7140mb(DialogInterface dialogInterface);

        /* renamed from: ox */
        void mo7139ox(DialogInterface dialogInterface);
    }

    private DownloadAlertDialogInfo(C9826mb c9826mb) {
        this.f18778u = true;
        this.f18776mb = c9826mb.f18781b;
        this.f18777ox = c9826mb.f18783hj;
        this.f18771b = c9826mb.f18782h;
        this.f18773hj = c9826mb.f18788u;
        this.f18772h = c9826mb.f18784ko;
        this.f18778u = c9826mb.f18789ww;
        this.f18774ko = c9826mb.f18785lz;
        this.f18779ww = c9826mb.f18790x;
        this.f18775lz = c9826mb.f18786mb;
        this.f18780x = c9826mb.f18787ox;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.download.api.model.DownloadAlertDialogInfo$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class C9826mb {

        /* renamed from: b */
        private Context f18781b;

        /* renamed from: h */
        private String f18782h;

        /* renamed from: hj */
        private String f18783hj;

        /* renamed from: ko */
        private String f18784ko;

        /* renamed from: lz */
        private Drawable f18785lz;

        /* renamed from: mb */
        public View f18786mb;

        /* renamed from: ox */
        public int f18787ox;

        /* renamed from: u */
        private String f18788u;

        /* renamed from: ww */
        private boolean f18789ww;

        /* renamed from: x */
        private InterfaceC9827ox f18790x;

        public C9826mb(Context context) {
            this.f18781b = context;
        }

        /* renamed from: mb */
        public C9826mb m7888mb(String str) {
            this.f18783hj = str;
            return this;
        }

        /* renamed from: ox */
        public C9826mb m7885ox(String str) {
            this.f18782h = str;
            return this;
        }

        /* renamed from: b */
        public C9826mb m7898b(String str) {
            this.f18788u = str;
            return this;
        }

        /* renamed from: hj */
        public C9826mb m7895hj(String str) {
            this.f18784ko = str;
            return this;
        }

        /* renamed from: mb */
        public C9826mb m7887mb(boolean z) {
            this.f18789ww = z;
            return this;
        }

        /* renamed from: mb */
        public C9826mb m7891mb(Drawable drawable) {
            this.f18785lz = drawable;
            return this;
        }

        /* renamed from: mb */
        public C9826mb m7889mb(InterfaceC9827ox interfaceC9827ox) {
            this.f18790x = interfaceC9827ox;
            return this;
        }

        /* renamed from: mb */
        public C9826mb m7892mb(int i) {
            this.f18787ox = i;
            return this;
        }

        /* renamed from: mb */
        public DownloadAlertDialogInfo m7893mb() {
            return new DownloadAlertDialogInfo(this);
        }
    }
}
