package com.p319ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.sdk.openadsdk.C3958R;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.compliance.C9861hj;
import com.p319ss.android.downloadlib.addownload.model.C9922ox;
import com.p319ss.android.downloadlib.guide.install.ClipImageView;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.socialbase.appdownloader.C10085b;

/* renamed from: com.ss.android.downloadlib.addownload.compliance.mb */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DialogC9866mb extends Dialog {

    /* renamed from: b */
    private TextView f18951b;

    /* renamed from: h */
    private TextView f18952h;

    /* renamed from: hj */
    private TextView f18953hj;

    /* renamed from: jb */
    private long f18954jb;

    /* renamed from: je */
    private final C9922ox f18955je;

    /* renamed from: ko */
    private ClipImageView f18956ko;

    /* renamed from: lz */
    private Activity f18957lz;

    /* renamed from: mb */
    private TextView f18958mb;

    /* renamed from: ox */
    private TextView f18959ox;

    /* renamed from: u */
    private TextView f18960u;

    /* renamed from: ww */
    private LinearLayout f18961ww;

    /* renamed from: x */
    private final long f18962x;

    public DialogC9866mb(@NonNull Activity activity, long j) {
        super(activity);
        this.f18957lz = activity;
        this.f18962x = j;
        this.f18955je = (C9922ox) C9857b.m7677mb().get(Long.valueOf(j));
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f18955je == null) {
            dismiss();
            return;
        }
        requestWindowFeature(1);
        setContentView(C3958R.C3963layout.ttdownloader_dialog_appinfo);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(C3958R.C3960drawable.ttdownloader_bg_transparent);
        }
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.f18954jb = this.f18955je.f19128ox;
        m7657mb();
        C9860h.m7667ox("lp_app_dialog_show", this.f18954jb);
        setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.1
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                C9860h.m7670mb("lp_app_dialog_cancel", DialogC9866mb.this.f18954jb);
            }
        });
    }

    /* renamed from: mb */
    private void m7657mb() {
        this.f18958mb = (TextView) findViewById(C3958R.C3961id.tv_app_name);
        this.f18959ox = (TextView) findViewById(C3958R.C3961id.tv_app_version);
        this.f18951b = (TextView) findViewById(C3958R.C3961id.tv_app_developer);
        this.f18953hj = (TextView) findViewById(C3958R.C3961id.tv_app_detail);
        this.f18952h = (TextView) findViewById(C3958R.C3961id.tv_app_privacy);
        this.f18960u = (TextView) findViewById(C3958R.C3961id.tv_give_up);
        this.f18956ko = (ClipImageView) findViewById(C3958R.C3961id.iv_app_icon);
        this.f18961ww = (LinearLayout) findViewById(C3958R.C3961id.ll_download);
        this.f18958mb.setText(C10050jb.m7036mb(this.f18955je.f19123h, "--"));
        TextView textView = this.f18959ox;
        textView.setText("版本号：" + C10050jb.m7036mb(this.f18955je.f19129u, "--"));
        TextView textView2 = this.f18951b;
        textView2.setText("开发者：" + C10050jb.m7036mb(this.f18955je.f19125ko, "应用信息正在完善中"));
        this.f18956ko.setRoundRadius(C10050jb.m7054mb(C9940x.getContext(), 8.0f));
        this.f18956ko.setBackgroundColor(Color.parseColor("#EBEBEB"));
        C9861hj.m7666mb().m7663mb(this.f18962x, new C9861hj.InterfaceC9864mb() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.2
            @Override // com.p319ss.android.downloadlib.addownload.compliance.C9861hj.InterfaceC9864mb
            /* renamed from: mb */
            public void mo7654mb(Bitmap bitmap) {
                if (bitmap != null) {
                    DialogC9866mb.this.f18956ko.setImageBitmap(bitmap);
                } else {
                    C9860h.m7672mb(8, DialogC9866mb.this.f18954jb);
                }
            }
        });
        this.f18953hj.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9873ox.m7653mb().m7650mb(DialogC9866mb.this.f18957lz);
                AppDetailInfoActivity.m7686mb(DialogC9866mb.this.f18957lz, DialogC9866mb.this.f18962x);
                C9860h.m7670mb("lp_app_dialog_click_detail", DialogC9866mb.this.f18954jb);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f18952h.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9873ox.m7653mb().m7650mb(DialogC9866mb.this.f18957lz);
                AppPrivacyPolicyActivity.m7682mb(DialogC9866mb.this.f18957lz, DialogC9866mb.this.f18962x);
                C9860h.m7670mb("lp_app_dialog_click_privacy", DialogC9866mb.this.f18954jb);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f18960u.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                DialogC9866mb.this.dismiss();
                C9860h.m7670mb("lp_app_dialog_click_giveup", DialogC9866mb.this.f18954jb);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f18961ww.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.mb.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9860h.m7670mb("lp_app_dialog_click_download", DialogC9866mb.this.f18954jb);
                C9873ox.m7653mb().m7645ox(DialogC9866mb.this.f18954jb);
                DialogC9866mb.this.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        C10085b.m6920mb(this.f18957lz);
    }
}
