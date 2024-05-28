package com.sina.weibo.sdk.share;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sina.weibo.sdk.C7061a;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.p311b.C7089a;
import com.sina.weibo.sdk.p311b.C7092c;
import com.sina.weibo.sdk.p311b.C7093d;
import com.sina.weibo.sdk.p311b.C7094e;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareTransActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: t */
    private Intent f18329t;

    /* renamed from: u */
    private FrameLayout f18330u;

    /* renamed from: v */
    private AsyncTaskC7112d f18331v;

    /* renamed from: w */
    private String f18332w;

    /* renamed from: x */
    private Handler f18333x = new Handler(Looper.getMainLooper()) { // from class: com.sina.weibo.sdk.share.ShareTransActivity.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 1 || !(message.obj instanceof Intent)) {
                ShareTransActivity.this.m8036j();
            } else {
                ShareTransActivity.this.m8040b((Intent) message.obj);
            }
        }
    };

    @Override // com.sina.weibo.sdk.share.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 30);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.share.ShareTransActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class C71082 implements InterfaceC7110b {
        C71082() {
        }

        @Override // com.sina.weibo.sdk.share.InterfaceC7110b
        /* renamed from: a */
        public final void mo8034a(C7111c c7111c) {
            ShareTransActivity.this.f18330u.setVisibility(4);
            if (c7111c == null) {
                ShareTransActivity.this.m8037c("Trans result is null.");
            } else if (c7111c.f18337z) {
                ShareTransActivity.this.m8045a(c7111c.f18336A);
            } else if (TextUtils.isEmpty(c7111c.errorMessage)) {
                ShareTransActivity.this.m8037c("Trans resource fail.");
            } else {
                ShareTransActivity.this.m8037c(c7111c.errorMessage);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m8045a(WeiboMultiMessage weiboMultiMessage) {
        C7092c.m8072a("WBShareTag", "start wb composer");
        try {
            this.f18329t.putExtra("start_flag", 1002);
            this.f18332w = C7093d.m8069f(String.valueOf((Math.random() * 10000.0d) + System.currentTimeMillis()));
            this.f18329t.putExtra("share_back_flag", this.f18332w);
            this.f18329t.putExtra("share_flag_for_new_version", 1);
            Bundle extras = this.f18329t.getExtras();
            Intent intent = new Intent("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
            C7089a.C7090a m8085c = C7089a.m8085c(this);
            if (m8085c != null) {
                intent.setPackage(m8085c.packageName);
            }
            intent.putExtras(weiboMultiMessage.writeToBundle(extras));
            intent.putExtra("_weibo_sdkVersion", "0041005000");
            intent.putExtra("_weibo_appPackage", getPackageName());
            intent.putExtra("_weibo_appKey", C7061a.m8107a().getAppKey());
            intent.putExtra("_weibo_flag", 538116905);
            intent.putExtra("_weibo_sign", C7093d.m8069f(C7094e.m8067b(this, getPackageName())));
            String stringExtra = this.f18329t.getStringExtra("start_web_activity");
            if (!TextUtils.isEmpty(stringExtra) && "com.sina.weibo.sdk.web.WebActivity".equals(stringExtra)) {
                intent.setClassName(this, stringExtra);
                startActivityForResult(intent, 10001);
            } else if (C7061a.m8106a(this)) {
                if (m8085c != null) {
                    intent.setPackage(m8085c.packageName);
                }
                startActivityForResult(intent, 10001);
            } else {
                m8037c("Start weibo client's composer fail. And Weibo client is not installed.");
            }
        } catch (Throwable th) {
            C7092c.m8071b("WBShareTag", "start wb composer fail," + th.getMessage());
            m8037c("Start weibo client's composer fail. " + th.getMessage());
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        C7092c.m8072a("WBShareTag", "onActivityResult. Means share result coming!");
        Handler handler = this.f18333x;
        if (handler != null) {
            if (i2 == -1) {
                Message obtain = Message.obtain(handler, 1);
                obtain.obj = intent;
                this.f18333x.sendMessageDelayed(obtain, 100L);
                return;
            }
            handler.sendEmptyMessageDelayed(0, 100L);
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        C7092c.m8072a("WBShareTag", "start share activity again. Means share result coming!");
        int intExtra = intent.getIntExtra("start_flag", -1);
        if (intExtra == 1001) {
            return;
        }
        if (intExtra == 1002) {
            m8040b(intent);
        } else {
            m8036j();
        }
    }

    /* renamed from: a */
    private boolean m8046a(Intent intent) {
        if (TextUtils.isEmpty(this.f18332w) || intent == null || !intent.getExtras().containsKey("share_back_flag")) {
            return false;
        }
        return TextUtils.equals(this.f18332w, intent.getStringExtra("share_back_flag"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m8040b(Intent intent) {
        FrameLayout frameLayout = this.f18330u;
        if (frameLayout != null) {
            frameLayout.setVisibility(4);
        }
        Handler handler = this.f18333x;
        if (handler != null) {
            handler.removeMessages(0);
            this.f18333x = null;
        }
        if (!m8046a(intent)) {
            m8036j();
            return;
        }
        m8038c(intent);
        setResult(-1, intent);
        finish();
    }

    /* renamed from: c */
    private static void m8038c(Intent intent) {
        if (intent == null || intent.getFlags() == 0) {
            return;
        }
        int flags = intent.getFlags();
        String binaryString = Integer.toBinaryString(flags);
        ArrayList<Integer> arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 19) {
            arrayList.add(64);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            arrayList.add(128);
        }
        arrayList.add(1);
        arrayList.add(2);
        for (Integer num : arrayList) {
            flags &= ~num.intValue();
        }
        C7092c.m8072a("WBShareTag", "clear flags: " + binaryString + "->" + Integer.toBinaryString(flags));
        intent.setFlags(flags);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m8037c(String str) {
        FrameLayout frameLayout = this.f18330u;
        if (frameLayout != null) {
            frameLayout.setVisibility(4);
        }
        Handler handler = this.f18333x;
        if (handler != null) {
            handler.removeMessages(0);
            this.f18333x = null;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("_weibo_resp_errcode", 2);
        bundle.putString("_weibo_resp_errstr", str);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m8036j() {
        FrameLayout frameLayout = this.f18330u;
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
        Handler handler = this.f18333x;
        if (handler != null) {
            handler.removeMessages(0);
            this.f18333x = null;
        }
        try {
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_resp_errcode", 1);
            intent.putExtras(bundle);
            setResult(-1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
