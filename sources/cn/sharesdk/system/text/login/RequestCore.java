package cn.sharesdk.system.text.login;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.system.text.login.gui.IdentifyNumPage;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.RxMob;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RequestCore {

    /* renamed from: j */
    private static String f3076j = "";

    /* renamed from: b */
    private Context f3078b;

    /* renamed from: c */
    private String f3079c;

    /* renamed from: d */
    private String f3080d;

    /* renamed from: e */
    private String f3081e;

    /* renamed from: f */
    private String f3082f;

    /* renamed from: g */
    private int f3083g;

    /* renamed from: k */
    private LoginActionListener f3086k;

    /* renamed from: l */
    private HashMap<String, Object> f3087l;

    /* renamed from: m */
    private FakeActivity f3088m;

    /* renamed from: n */
    private DialogShell f3089n;

    /* renamed from: o */
    private Dialog f3090o;

    /* renamed from: a */
    private int f3077a = 0;

    /* renamed from: h */
    private int f3084h = 2;

    /* renamed from: i */
    private String f3085i = "";

    public RequestCore(FakeActivity fakeActivity, DialogShell dialogShell) {
        this.f3088m = fakeActivity;
        this.f3078b = fakeActivity.getContext();
        if (dialogShell != null) {
            dialogShell.m21570a();
        }
        this.f3089n = new DialogShell(this.f3078b);
    }

    /* renamed from: a */
    public void m21529a(int i) {
        this.f3077a = i;
    }

    /* renamed from: a */
    public void m21528a(LoginActionListener loginActionListener) {
        this.f3086k = loginActionListener;
    }

    /* renamed from: a */
    public HashMap<String, Object> m21523a(final HashMap<String, Object> hashMap) {
        this.f3090o = DialogShell.m21568a(this.f3078b);
        RxMob.Subscribable create = RxMob.create(new RxMob.OnSubscribe() { // from class: cn.sharesdk.system.text.login.RequestCore$1
            @Override // com.mob.tools.RxMob.OnSubscribe
            public void call(RxMob.Subscriber subscriber) {
                String str;
                subscriber.onStart();
                Protocol protocol = new Protocol();
                RequestCore.this.f3079c = (String) hashMap.get("zone");
                RequestCore.this.f3080d = (String) hashMap.get("phone");
                RequestCore.this.f3083g = ((Integer) hashMap.get("type")).intValue();
                if (hashMap.containsKey("country")) {
                    RequestCore.this.f3082f = (String) hashMap.get("country");
                }
                if (hashMap.containsKey("code")) {
                    RequestCore.this.f3081e = (String) hashMap.get("code");
                    RequestCore requestCore = RequestCore.this;
                    String str2 = requestCore.f3079c;
                    String str3 = RequestCore.this.f3080d;
                    int i = RequestCore.this.f3083g;
                    str = RequestCore.this.f3081e;
                    requestCore.f3087l = protocol.m21533a(str2, str3, i, str);
                } else {
                    RequestCore requestCore2 = RequestCore.this;
                    requestCore2.f3087l = protocol.m21534a(requestCore2.f3079c, RequestCore.this.f3080d, RequestCore.this.f3083g);
                }
                subscriber.onCompleted();
            }
        });
        create.subscribeOn(RxMob.Thread.NEW_THREAD);
        create.observeOn(RxMob.Thread.UI_THREAD);
        create.subscribe(new RxMob.Subscriber() { // from class: cn.sharesdk.system.text.login.RequestCore$2
            @Override // com.mob.tools.RxMob.Subscriber
            public void onStart() {
                Dialog dialog;
                dialog = RequestCore.this.f3090o;
                dialog.show();
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onCompleted() {
                Dialog dialog;
                HashMap hashMap2;
                HashMap hashMap3;
                HashMap hashMap4;
                HashMap hashMap5;
                HashMap hashMap6;
                String str;
                dialog = RequestCore.this.f3090o;
                dialog.dismiss();
                hashMap2 = RequestCore.this.f3087l;
                if (hashMap2 != null) {
                    hashMap3 = RequestCore.this.f3087l;
                    if (hashMap3.containsKey("status")) {
                        hashMap4 = RequestCore.this.f3087l;
                        int intValue = ((Integer) hashMap4.get("status")).intValue();
                        RequestCore requestCore = RequestCore.this;
                        hashMap5 = requestCore.f3087l;
                        if (hashMap5.containsKey("recordId")) {
                            hashMap6 = RequestCore.this.f3087l;
                            str = (String) hashMap6.get("recordId");
                        } else {
                            str = "";
                        }
                        requestCore.f3085i = str;
                        if (intValue != 200) {
                            Context context = RequestCore.this.f3078b;
                            int stringRes = ResHelper.getStringRes(context, "ssdk_sms_dialog_error_desc_" + Math.abs(intValue));
                            if (stringRes < 0) {
                                stringRes = ResHelper.getStringRes(RequestCore.this.f3078b, "ssdk_sms_dialog_system_error");
                            }
                            RequestCore.this.f3089n.m21569a(stringRes, 2);
                            return;
                        }
                        if (intValue == 2 || hashMap.containsKey("code")) {
                            RequestCore.this.m21522b();
                        } else {
                            RequestCore.this.m21530a();
                        }
                        hashMap.clear();
                        return;
                    }
                }
                int stringRes2 = ResHelper.getStringRes(RequestCore.this.f3078b, "ssdk_sms_dialog_net_error");
                if (stringRes2 > 0) {
                    RequestCore.this.f3089n.m21569a(stringRes2, 2);
                }
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onError(Throwable th) {
                SSDKLog.m21740b().m21737b(th);
            }
        });
        return this.f3087l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m21530a() {
        if (this.f3087l.containsKey("smart")) {
            this.f3084h = ((Integer) this.f3087l.get("smart")).intValue();
            if (this.f3084h == 1) {
                this.f3089n.m21565c().setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.system.text.login.c.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        RequestCore.this.f3089n.m21570a();
                        int stringRes = ResHelper.getStringRes(RequestCore.this.f3078b, "ssdk_sms_dialog_login_success");
                        if (stringRes > 0) {
                            final HashMap hashMap = new HashMap();
                            hashMap.put("phoneNumber", RequestCore.this.f3080d);
                            hashMap.put("country", RequestCore.this.f3082f);
                            hashMap.put("zone", RequestCore.this.f3079c);
                            hashMap.put("smart", 1);
                            hashMap.put("type", Integer.valueOf(RequestCore.this.f3083g));
                            new DialogShell(RequestCore.this.f3078b).m21569a(stringRes, 2).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.sharesdk.system.text.login.c.1.1
                                @Override // android.content.DialogInterface.OnDismissListener
                                public void onDismiss(DialogInterface dialogInterface) {
                                    RequestCore.this.f3086k.onSuccess(hashMap);
                                    RequestCore.this.f3088m.finish();
                                }
                            });
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                return;
            }
            return;
        }
        f3076j = !this.f3087l.containsKey("recordId") ? "" : (String) this.f3087l.get("recordId");
        if (this.f3077a == 1) {
            m21519c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m21522b() {
        final HashMap hashMap = new HashMap();
        hashMap.put("phoneNumber", this.f3080d);
        hashMap.put("country", this.f3082f);
        hashMap.put("zone", this.f3079c);
        hashMap.put("smart", 0);
        hashMap.put("type", Integer.valueOf(this.f3083g));
        hashMap.put("sendRecordId", f3076j);
        hashMap.put("checkRecordId", this.f3085i);
        int stringRes = ResHelper.getStringRes(this.f3078b, "ssdk_sms_dialog_login_success");
        if (stringRes > 0) {
            new DialogShell(this.f3078b).m21569a(stringRes, 2).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.sharesdk.system.text.login.c.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    RequestCore.this.f3086k.onSuccess(hashMap);
                    HashMap<String, Object> hashMap2 = new HashMap<>();
                    hashMap2.put("page", 2);
                    RequestCore.this.f3088m.setResult(hashMap2);
                    RequestCore.this.f3088m.finish();
                }
            });
        }
    }

    /* renamed from: c */
    private void m21519c() {
        IdentifyNumPage identifyNumPage = new IdentifyNumPage();
        identifyNumPage.setRequestData(this.f3079c, this.f3080d, this.f3083g, this.f3082f, f3076j);
        identifyNumPage.setLoginActionListener(this.f3086k);
        identifyNumPage.showForResult(this.f3078b, null, this.f3088m);
    }
}
