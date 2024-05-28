package com.p319ss.android.downloadlib.addownload.p327mb;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.sdk.openadsdk.C3958R;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* renamed from: com.ss.android.downloadlib.addownload.mb.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DialogC9907hj extends Dialog {

    /* renamed from: b */
    private TextView f19053b;

    /* renamed from: h */
    private boolean f19054h;

    /* renamed from: hj */
    private InterfaceC9906b f19055hj;

    /* renamed from: ko */
    private String f19056ko;

    /* renamed from: lz */
    private String f19057lz;

    /* renamed from: mb */
    private TextView f19058mb;

    /* renamed from: ox */
    private TextView f19059ox;

    /* renamed from: u */
    private Activity f19060u;

    /* renamed from: ww */
    private String f19061ww;

    public DialogC9907hj(@NonNull Activity activity, String str, String str2, String str3, boolean z, @NonNull InterfaceC9906b interfaceC9906b) {
        super(activity, C3958R.C3965style.ttdownloader_translucent_dialog);
        this.f19060u = activity;
        this.f19055hj = interfaceC9906b;
        this.f19056ko = str;
        this.f19061ww = str2;
        this.f19057lz = str3;
        setCanceledOnTouchOutside(z);
        m7526hj();
    }

    /* renamed from: hj */
    private void m7526hj() {
        setContentView(LayoutInflater.from(this.f19060u.getApplicationContext()).inflate(m7525mb(), (ViewGroup) null));
        this.f19058mb = (TextView) findViewById(m7523ox());
        this.f19059ox = (TextView) findViewById(m7528b());
        this.f19053b = (TextView) findViewById(C3958R.C3961id.message_tv);
        if (!TextUtils.isEmpty(this.f19061ww)) {
            this.f19058mb.setText(this.f19061ww);
        }
        if (!TextUtils.isEmpty(this.f19057lz)) {
            this.f19059ox.setText(this.f19057lz);
        }
        if (!TextUtils.isEmpty(this.f19056ko)) {
            this.f19053b.setText(this.f19056ko);
        }
        this.f19058mb.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.mb.hj.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                DialogC9907hj.this.m7527h();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f19059ox.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.mb.hj.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                DialogC9907hj.this.m7521u();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* renamed from: mb */
    public int m7525mb() {
        return C3958R.C3963layout.ttdownloader_dialog_select_operation;
    }

    /* renamed from: ox */
    public int m7523ox() {
        return C3958R.C3961id.confirm_tv;
    }

    /* renamed from: b */
    public int m7528b() {
        return C3958R.C3961id.cancel_tv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m7527h() {
        this.f19054h = true;
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public void m7521u() {
        dismiss();
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        if (!this.f19060u.isFinishing()) {
            this.f19060u.finish();
        }
        if (this.f19054h) {
            this.f19055hj.mo7530mb();
        } else {
            this.f19055hj.mo7529ox();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 4) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: com.ss.android.downloadlib.addownload.mb.hj$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C9910mb {

        /* renamed from: b */
        private String f19064b;

        /* renamed from: h */
        private boolean f19065h;

        /* renamed from: hj */
        private String f19066hj;

        /* renamed from: mb */
        private Activity f19067mb;

        /* renamed from: ox */
        private String f19068ox;

        /* renamed from: u */
        private InterfaceC9906b f19069u;

        public C9910mb(Activity activity) {
            this.f19067mb = activity;
        }

        /* renamed from: mb */
        public C9910mb m7517mb(String str) {
            this.f19068ox = str;
            return this;
        }

        /* renamed from: ox */
        public C9910mb m7515ox(String str) {
            this.f19064b = str;
            return this;
        }

        /* renamed from: b */
        public C9910mb m7520b(String str) {
            this.f19066hj = str;
            return this;
        }

        /* renamed from: mb */
        public C9910mb m7516mb(boolean z) {
            this.f19065h = z;
            return this;
        }

        /* renamed from: mb */
        public C9910mb m7518mb(InterfaceC9906b interfaceC9906b) {
            this.f19069u = interfaceC9906b;
            return this;
        }

        /* renamed from: mb */
        public DialogC9907hj m7519mb() {
            return new DialogC9907hj(this.f19067mb, this.f19068ox, this.f19064b, this.f19066hj, this.f19065h, this.f19069u);
        }
    }
}
