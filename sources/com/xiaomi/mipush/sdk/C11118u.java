package com.xiaomi.mipush.sdk;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11183ba;
import com.xiaomi.push.C11251cs;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11431hf;
import com.xiaomi.push.C11437hl;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11405gg;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.InterfaceC11442hq;
import com.xiaomi.push.service.AbstractC11555an;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11541aj;
import com.xiaomi.push.service.C11559ap;
import com.xiaomi.push.service.C11595g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.xiaomi.mipush.sdk.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11118u {

    /* renamed from: a */
    private static C11118u f21418a;

    /* renamed from: a */
    private static final ArrayList<C11124a> f21419a = new ArrayList<>();

    /* renamed from: b */
    private static boolean f21420b;

    /* renamed from: a */
    private long f21421a;

    /* renamed from: a */
    private Context f21422a;

    /* renamed from: a */
    private Handler f21424a;

    /* renamed from: a */
    private Messenger f21425a;

    /* renamed from: a */
    private boolean f21429a;

    /* renamed from: a */
    private List<Message> f21428a = new ArrayList();

    /* renamed from: c */
    private boolean f21431c = false;

    /* renamed from: b */
    private String f21430b = null;

    /* renamed from: a */
    private Intent f21423a = null;

    /* renamed from: a */
    private Integer f21426a = null;

    /* renamed from: a */
    private String f21427a = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.u$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11124a<T extends InterfaceC11442hq<T, ?>> {

        /* renamed from: a */
        EnumC11404gf f21437a;

        /* renamed from: a */
        T f21438a;

        /* renamed from: a */
        boolean f21439a;

        C11124a() {
        }
    }

    /* renamed from: a */
    public static synchronized C11118u m5003a(Context context) {
        C11118u c11118u;
        synchronized (C11118u.class) {
            if (f21418a == null) {
                f21418a = new C11118u(context);
            }
            c11118u = f21418a;
        }
        return c11118u;
    }

    private C11118u(Context context) {
        this.f21429a = false;
        this.f21424a = null;
        this.f21422a = context.getApplicationContext();
        this.f21429a = m4968c();
        f21420b = m4963d();
        this.f21424a = new Handler(Looper.getMainLooper()) { // from class: com.xiaomi.mipush.sdk.u.1
            @Override // android.os.Handler
            public void dispatchMessage(Message message) {
                if (message.what != 19) {
                    return;
                }
                String str = (String) message.obj;
                int i = message.arg1;
                synchronized (C11111p.class) {
                    if (C11111p.m5047a(C11118u.this.f21422a).m5042a(str)) {
                        if (C11111p.m5047a(C11118u.this.f21422a).m5044a(str) >= 10) {
                            C11111p.m5047a(C11118u.this.f21422a).m5040c(str);
                        } else {
                            String string = message.getData() != null ? message.getData().getString("third_sync_reason") : "";
                            if (EnumC11125v.DISABLE_PUSH.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.DISABLE_PUSH))) {
                                C11118u.this.m4979a(str, EnumC11125v.DISABLE_PUSH, true, (HashMap<String, String>) null);
                            } else if (EnumC11125v.ENABLE_PUSH.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.ENABLE_PUSH))) {
                                C11118u.this.m4979a(str, EnumC11125v.ENABLE_PUSH, true, (HashMap<String, String>) null);
                            } else if (EnumC11125v.UPLOAD_HUAWEI_TOKEN.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.UPLOAD_HUAWEI_TOKEN))) {
                                HashMap<String, String> m5099a = C11094f.m5099a(C11118u.this.f21422a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
                                m5099a.put("third_sync_reason", string);
                                C11118u.this.m4979a(str, EnumC11125v.UPLOAD_HUAWEI_TOKEN, false, m5099a);
                            } else if (EnumC11125v.UPLOAD_FCM_TOKEN.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.UPLOAD_FCM_TOKEN))) {
                                C11118u.this.m4979a(str, EnumC11125v.UPLOAD_FCM_TOKEN, false, C11094f.m5099a(C11118u.this.f21422a, EnumC11090d.ASSEMBLE_PUSH_FCM));
                            } else if (EnumC11125v.UPLOAD_COS_TOKEN.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.UPLOAD_COS_TOKEN))) {
                                HashMap<String, String> m5099a2 = C11094f.m5099a(C11118u.this.f21422a, EnumC11090d.ASSEMBLE_PUSH_COS);
                                m5099a2.put("third_sync_reason", string);
                                C11118u.this.m4979a(str, EnumC11125v.UPLOAD_COS_TOKEN, false, m5099a2);
                            } else if (EnumC11125v.UPLOAD_FTOS_TOKEN.ordinal() == i && "syncing".equals(C11111p.m5047a(C11118u.this.f21422a).m5046a(EnumC11125v.UPLOAD_FTOS_TOKEN))) {
                                HashMap<String, String> m5099a3 = C11094f.m5099a(C11118u.this.f21422a, EnumC11090d.ASSEMBLE_PUSH_FTOS);
                                m5099a3.put("third_sync_reason", string);
                                C11118u.this.m4979a(str, EnumC11125v.UPLOAD_FTOS_TOKEN, false, m5099a3);
                            }
                            C11111p.m5047a(C11118u.this.f21422a).m5041b(str);
                        }
                    }
                }
            }
        };
        if (C11469j.m2972a(context)) {
            C11595g.m2540a(new C11595g.InterfaceC11597b() { // from class: com.xiaomi.mipush.sdk.u.2
            });
        }
        Intent m4975b = m4975b();
        if (m4975b != null) {
            m4971b(m4975b);
        }
    }

    /* renamed from: c */
    private synchronized void m4967c(int i) {
        this.f21422a.getSharedPreferences("mipush_extra", 0).edit().putInt("service_boot_mode", i).commit();
    }

    /* renamed from: a */
    private synchronized int m5013a() {
        return this.f21422a.getSharedPreferences("mipush_extra", 0).getInt("service_boot_mode", -1);
    }

    /* renamed from: g */
    private void m4957g() {
        this.f21421a = SystemClock.elapsedRealtime();
    }

    /* renamed from: a */
    public long m5012a() {
        return this.f21421a;
    }

    /* renamed from: a */
    public final void m4990a(C11431hf c11431hf, boolean z) {
        C11305dt.m4117a(this.f21422a.getApplicationContext()).m4111a(this.f21422a.getPackageName(), "E100003", c11431hf.m3312a(), 6001, null);
        this.f21423a = null;
        C11087b.m5151a(this.f21422a).f21354a = c11431hf.m3312a();
        Intent m5011a = m5011a();
        byte[] m3085a = C11441hp.m3085a(C11113r.m5038a(this.f21422a, c11431hf, EnumC11404gf.Registration));
        if (m3085a == null) {
            AbstractC11049b.m5282a("register fail, because msgBytes is null.");
            return;
        }
        m5011a.setAction("com.xiaomi.mipush.REGISTER_APP");
        m5011a.putExtra("mipush_app_id", C11087b.m5151a(this.f21422a).m5156a());
        m5011a.putExtra("mipush_payload", m3085a);
        m5011a.putExtra("mipush_session", this.f21427a);
        m5011a.putExtra("mipush_env_chanage", z);
        m5011a.putExtra("mipush_env_type", C11087b.m5151a(this.f21422a).m5157a());
        if (C11169au.m4849a(this.f21422a) && m4973b()) {
            m4957g();
            m4966c(m5011a);
            return;
        }
        this.f21423a = m5011a;
    }

    /* renamed from: a */
    public void m5009a() {
        m4971b(m5011a());
    }

    /* renamed from: a */
    public final void m4989a(C11437hl c11437hl) {
        byte[] m3085a = C11441hp.m3085a(C11113r.m5038a(this.f21422a, c11437hl, EnumC11404gf.UnRegistration));
        if (m3085a == null) {
            AbstractC11049b.m5282a("unregister fail, because msgBytes is null.");
            return;
        }
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.UNREGISTER_APP");
        m5011a.putExtra("mipush_app_id", C11087b.m5151a(this.f21422a).m5156a());
        m5011a.putExtra("mipush_payload", m3085a);
        m4966c(m5011a);
    }

    /* renamed from: b */
    public final void m4974b() {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.DISABLE_PUSH");
        m4966c(m5011a);
    }

    /* renamed from: a */
    public final void m4977a(boolean z) {
        m4976a(z, (String) null);
    }

    /* renamed from: a */
    public final void m4976a(boolean z, String str) {
        if (z) {
            C11111p.m5047a(this.f21422a).m5045a(EnumC11125v.DISABLE_PUSH, "syncing");
            C11111p.m5047a(this.f21422a).m5045a(EnumC11125v.ENABLE_PUSH, "");
            m4979a(str, EnumC11125v.DISABLE_PUSH, true, (HashMap<String, String>) null);
            return;
        }
        C11111p.m5047a(this.f21422a).m5045a(EnumC11125v.ENABLE_PUSH, "syncing");
        C11111p.m5047a(this.f21422a).m5045a(EnumC11125v.DISABLE_PUSH, "");
        m4979a(str, EnumC11125v.ENABLE_PUSH, true, (HashMap<String, String>) null);
    }

    /* renamed from: a */
    public void m5002a(Context context) {
        if (C11469j.m2974a()) {
            return;
        }
        EnumC11112q m5073a = C11100h.m5073a(context);
        if (EnumC11112q.HUAWEI.equals(m5073a)) {
            m4980a((String) null, EnumC11125v.UPLOAD_HUAWEI_TOKEN, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, "update");
        }
        if (EnumC11112q.OPPO.equals(m5073a)) {
            m4980a((String) null, EnumC11125v.UPLOAD_COS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_COS, "update");
        }
        if (EnumC11112q.VIVO.equals(m5073a)) {
            m4980a((String) null, EnumC11125v.UPLOAD_FTOS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_FTOS, "update");
        }
    }

    /* renamed from: a */
    public final void m4980a(String str, EnumC11125v enumC11125v, EnumC11090d enumC11090d, String str2) {
        C11111p.m5047a(this.f21422a).m5045a(enumC11125v, "syncing");
        HashMap<String, String> m5099a = C11094f.m5099a(this.f21422a, enumC11090d);
        m5099a.put("third_sync_reason", str2);
        m4979a(str, enumC11125v, false, m5099a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m5004a(int i, String str) {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.thirdparty");
        m5011a.putExtra("com.xiaomi.mipush.thirdparty_LEVEL", i);
        m5011a.putExtra("com.xiaomi.mipush.thirdparty_DESC", str);
        m4971b(m5011a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m4979a(String str, EnumC11125v enumC11125v, boolean z, HashMap<String, String> hashMap) {
        C11430he c11430he;
        if (C11087b.m5151a(this.f21422a).m5141b() && C11169au.m4849a(this.f21422a)) {
            C11430he c11430he2 = new C11430he();
            c11430he2.m3340a(true);
            Intent m5011a = m5011a();
            if (TextUtils.isEmpty(str)) {
                str = C11541aj.m2703a();
                c11430he2.m3344a(str);
                c11430he = z ? new C11430he(str, true) : null;
                synchronized (C11111p.class) {
                    C11111p.m5047a(this.f21422a).m5043a(str);
                }
            } else {
                c11430he2.m3344a(str);
                c11430he = z ? new C11430he(str, true) : null;
            }
            switch (enumC11125v) {
                case DISABLE_PUSH:
                    c11430he2.m3331c(EnumC11414gp.DisablePushMessage.f22745a);
                    c11430he.m3331c(EnumC11414gp.DisablePushMessage.f22745a);
                    if (hashMap != null) {
                        c11430he2.m3341a(hashMap);
                        c11430he.m3341a(hashMap);
                    }
                    m5011a.setAction("com.xiaomi.mipush.DISABLE_PUSH_MESSAGE");
                    break;
                case ENABLE_PUSH:
                    c11430he2.m3331c(EnumC11414gp.EnablePushMessage.f22745a);
                    c11430he.m3331c(EnumC11414gp.EnablePushMessage.f22745a);
                    if (hashMap != null) {
                        c11430he2.m3341a(hashMap);
                        c11430he.m3341a(hashMap);
                    }
                    m5011a.setAction("com.xiaomi.mipush.ENABLE_PUSH_MESSAGE");
                    break;
                case UPLOAD_HUAWEI_TOKEN:
                case UPLOAD_FCM_TOKEN:
                case UPLOAD_COS_TOKEN:
                case UPLOAD_FTOS_TOKEN:
                    c11430he2.m3331c(EnumC11414gp.ThirdPartyRegUpdate.f22745a);
                    if (hashMap != null) {
                        c11430he2.m3341a(hashMap);
                        break;
                    }
                    break;
            }
            AbstractC11049b.m5266e("type:" + enumC11125v + ", " + str);
            c11430he2.m3335b(C11087b.m5151a(this.f21422a).m5156a());
            c11430he2.m3327d(this.f21422a.getPackageName());
            m4986a((C11118u) c11430he2, EnumC11404gf.Notification, false, (C11417gs) null);
            if (z) {
                c11430he.m3335b(C11087b.m5151a(this.f21422a).m5156a());
                c11430he.m3327d(this.f21422a.getPackageName());
                byte[] m3085a = C11441hp.m3085a(C11113r.m5037a(this.f21422a, c11430he, EnumC11404gf.Notification, false, this.f21422a.getPackageName(), C11087b.m5151a(this.f21422a).m5156a()));
                if (m3085a != null) {
                    C11251cs.m4464a(this.f21422a.getPackageName(), this.f21422a, c11430he, EnumC11404gf.Notification, m3085a.length);
                    m5011a.putExtra("mipush_payload", m3085a);
                    m5011a.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", true);
                    m5011a.putExtra("mipush_app_id", C11087b.m5151a(this.f21422a).m5156a());
                    m5011a.putExtra("mipush_app_token", C11087b.m5151a(this.f21422a).m5143b());
                    m4966c(m5011a);
                }
            }
            Message obtain = Message.obtain();
            obtain.what = 19;
            int ordinal = enumC11125v.ordinal();
            obtain.obj = str;
            obtain.arg1 = ordinal;
            if (hashMap != null && hashMap.get("third_sync_reason") != null) {
                Bundle bundle = new Bundle();
                bundle.putString("third_sync_reason", hashMap.get("third_sync_reason"));
                obtain.setData(bundle);
            }
            this.f21424a.sendMessageDelayed(obtain, 5000L);
        }
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4988a(T t, EnumC11404gf enumC11404gf, C11417gs c11417gs) {
        m4986a((C11118u) t, enumC11404gf, !enumC11404gf.equals(EnumC11404gf.Registration), c11417gs);
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4985a(T t, EnumC11404gf enumC11404gf, boolean z, C11417gs c11417gs, boolean z2) {
        m4984a(t, enumC11404gf, z, true, c11417gs, z2);
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4986a(T t, EnumC11404gf enumC11404gf, boolean z, C11417gs c11417gs) {
        m4984a(t, enumC11404gf, z, true, c11417gs, true);
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4984a(T t, EnumC11404gf enumC11404gf, boolean z, boolean z2, C11417gs c11417gs, boolean z3) {
        m4983a(t, enumC11404gf, z, z2, c11417gs, z3, this.f21422a.getPackageName(), C11087b.m5151a(this.f21422a).m5156a());
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4983a(T t, EnumC11404gf enumC11404gf, boolean z, boolean z2, C11417gs c11417gs, boolean z3, String str, String str2) {
        m4982a(t, enumC11404gf, z, z2, c11417gs, z3, str, str2, true);
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4982a(T t, EnumC11404gf enumC11404gf, boolean z, boolean z2, C11417gs c11417gs, boolean z3, String str, String str2, boolean z4) {
        m4981a(t, enumC11404gf, z, z2, c11417gs, z3, str, str2, z4, true);
    }

    /* renamed from: a */
    public final <T extends InterfaceC11442hq<T, ?>> void m4981a(T t, EnumC11404gf enumC11404gf, boolean z, boolean z2, C11417gs c11417gs, boolean z3, String str, String str2, boolean z4, boolean z5) {
        C11427hb m5034b;
        if (z5 && !C11087b.m5151a(this.f21422a).m5136c()) {
            if (z2) {
                m4987a((C11118u) t, enumC11404gf, z);
                return;
            } else {
                AbstractC11049b.m5282a("drop the message before initialization.");
                return;
            }
        }
        if (z4) {
            m5034b = C11113r.m5037a(this.f21422a, t, enumC11404gf, z, str, str2);
        } else {
            m5034b = C11113r.m5034b(this.f21422a, t, enumC11404gf, z, str, str2);
        }
        if (c11417gs != null) {
            m5034b.m3382a(c11417gs);
        }
        byte[] m3085a = C11441hp.m3085a(m5034b);
        if (m3085a == null) {
            AbstractC11049b.m5282a("send message fail, because msgBytes is null.");
            return;
        }
        C11251cs.m4464a(this.f21422a.getPackageName(), this.f21422a, t, enumC11404gf, m3085a.length);
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.SEND_MESSAGE");
        m5011a.putExtra("mipush_payload", m3085a);
        m5011a.putExtra("com.xiaomi.mipush.MESSAGE_CACHE", z3);
        m4966c(m5011a);
    }

    /* renamed from: a */
    public final void m4991a(C11408gj c11408gj) {
        Intent m5011a = m5011a();
        byte[] m3085a = C11441hp.m3085a(c11408gj);
        if (m3085a == null) {
            AbstractC11049b.m5282a("send TinyData failed, because tinyDataBytes is null.");
            return;
        }
        m5011a.setAction("com.xiaomi.mipush.SEND_TINYDATA");
        m5011a.putExtra("mipush_payload", m3085a);
        m4971b(m5011a);
    }

    /* renamed from: c */
    private boolean m4968c() {
        try {
            PackageInfo packageInfo = this.f21422a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4);
            if (packageInfo == null) {
                return false;
            }
            return packageInfo.versionCode >= 105;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: a */
    private Intent m5011a() {
        if (m5008a() && !"com.xiaomi.xmsf".equals(this.f21422a.getPackageName())) {
            return m4965d();
        }
        return m4961e();
    }

    /* renamed from: b */
    private Intent m4975b() {
        if (!"com.xiaomi.xmsf".equals(this.f21422a.getPackageName())) {
            return m4970c();
        }
        AbstractC11049b.m5270c("pushChannel xmsf create own channel");
        return m4961e();
    }

    /* renamed from: c */
    private Intent m4970c() {
        if (m5008a()) {
            AbstractC11049b.m5270c("pushChannel app start miui china channel");
            return m4965d();
        }
        AbstractC11049b.m5270c("pushChannel app start  own channel");
        return m4961e();
    }

    /* renamed from: d */
    private Intent m4965d() {
        Intent intent = new Intent();
        String packageName = this.f21422a.getPackageName();
        intent.setPackage("com.xiaomi.xmsf");
        intent.setClassName("com.xiaomi.xmsf", m5010a());
        intent.putExtra("mipush_app_package", packageName);
        m4956h();
        return intent;
    }

    /* renamed from: e */
    private Intent m4961e() {
        Intent intent = new Intent();
        String packageName = this.f21422a.getPackageName();
        m4955i();
        intent.setComponent(new ComponentName(this.f21422a, "com.xiaomi.push.service.XMPushService"));
        intent.putExtra("mipush_app_package", packageName);
        return intent;
    }

    /* renamed from: a */
    private String m5010a() {
        String str = this.f21430b;
        if (str != null) {
            return str;
        }
        try {
            if (this.f21422a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 106) {
                this.f21430b = "com.xiaomi.push.service.XMPushService";
                return this.f21430b;
            }
        } catch (Exception unused) {
        }
        this.f21430b = "com.xiaomi.xmsf.push.service.XMPushService";
        return this.f21430b;
    }

    /* renamed from: h */
    private void m4956h() {
        try {
            PackageManager packageManager = this.f21422a.getPackageManager();
            ComponentName componentName = new ComponentName(this.f21422a, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 2) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: i */
    private void m4955i() {
        try {
            PackageManager packageManager = this.f21422a.getPackageManager();
            ComponentName componentName = new ComponentName(this.f21422a, "com.xiaomi.push.service.XMPushService");
            if (packageManager.getComponentEnabledSetting(componentName) == 1) {
                return;
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public boolean m5008a() {
        return this.f21429a && 1 == C11087b.m5151a(this.f21422a).m5157a();
    }

    /* renamed from: c */
    public void m4969c() {
        if (this.f21423a != null) {
            m4957g();
            m4966c(this.f21423a);
            this.f21423a = null;
        }
    }

    /* renamed from: a */
    public <T extends InterfaceC11442hq<T, ?>> void m4987a(T t, EnumC11404gf enumC11404gf, boolean z) {
        C11124a c11124a = new C11124a();
        c11124a.f21438a = t;
        c11124a.f21437a = enumC11404gf;
        c11124a.f21439a = z;
        synchronized (f21419a) {
            f21419a.add(c11124a);
            if (f21419a.size() > 10) {
                f21419a.remove(0);
            }
        }
    }

    /* renamed from: d */
    public void m4964d() {
        synchronized (f21419a) {
            boolean z = Thread.currentThread() == Looper.getMainLooper().getThread();
            Iterator<C11124a> it = f21419a.iterator();
            while (it.hasNext()) {
                C11124a next = it.next();
                m4984a(next.f21438a, next.f21437a, next.f21439a, false, null, true);
                if (!z) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
            f21419a.clear();
        }
    }

    /* renamed from: a */
    public void m5007a(int i) {
        m5005a(i, 0);
    }

    /* renamed from: a */
    void m5005a(int i, int i2) {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        m5011a.putExtra(AbstractC11555an.f23562F, this.f21422a.getPackageName());
        m5011a.putExtra(AbstractC11555an.f23563G, i);
        m5011a.putExtra(AbstractC11555an.f23564H, i2);
        m4966c(m5011a);
    }

    /* renamed from: d */
    private boolean m4963d() {
        if (m5008a()) {
            try {
                return this.f21422a.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 4).versionCode >= 108;
            } catch (Exception unused) {
            }
        }
        return true;
    }

    /* renamed from: a */
    public void m4978a(String str, String str2) {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.CLEAR_NOTIFICATION");
        m5011a.putExtra(AbstractC11555an.f23562F, this.f21422a.getPackageName());
        m5011a.putExtra(AbstractC11555an.f23568L, str);
        m5011a.putExtra(AbstractC11555an.f23569M, str2);
        m4966c(m5011a);
    }

    /* renamed from: e */
    public void m4960e() {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.CLEAR_HEADSUPNOTIFICATION");
        Application application = (Application) C11176aw.m4810a("android.app.ActivityThread", "currentApplication", new Object[0]);
        String packageName = (application == null || application.getApplicationContext() == null) ? null : application.getApplicationContext().getPackageName();
        String packageName2 = this.f21422a.getPackageName();
        if (TextUtils.isEmpty(packageName) || packageName.equals(packageName2)) {
            packageName = packageName2;
        } else {
            AbstractC11049b.m5282a("application package name: " + packageName + ", not equals context package name: " + packageName2);
        }
        m5011a.putExtra(AbstractC11555an.f23562F, packageName);
        m4966c(m5011a);
    }

    /* renamed from: b */
    public void m4972b(int i) {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        m5011a.putExtra(AbstractC11555an.f23562F, this.f21422a.getPackageName());
        m5011a.putExtra(AbstractC11555an.f23565I, i);
        String str = AbstractC11555an.f23567K;
        m5011a.putExtra(str, C11183ba.m4759b(this.f21422a.getPackageName() + i));
        m4966c(m5011a);
    }

    /* renamed from: f */
    public void m4958f() {
        Intent m5011a = m5011a();
        m5011a.setAction("com.xiaomi.mipush.SET_NOTIFICATION_TYPE");
        m5011a.putExtra(AbstractC11555an.f23562F, this.f21422a.getPackageName());
        m5011a.putExtra(AbstractC11555an.f23567K, C11183ba.m4759b(this.f21422a.getPackageName()));
        m4966c(m5011a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m5000a(Intent intent) {
        intent.fillIn(m5011a(), 24);
        m4966c(intent);
    }

    /* renamed from: b */
    public boolean m4973b() {
        if (m5008a() && m4959e()) {
            if (this.f21426a == null) {
                this.f21426a = Integer.valueOf(C11559ap.m2653a(this.f21422a).m2656a());
                if (this.f21426a.intValue() == 0) {
                    this.f21422a.getContentResolver().registerContentObserver(C11559ap.m2653a(this.f21422a).m2655a(), false, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.xiaomi.mipush.sdk.u.3
                        @Override // android.database.ContentObserver
                        public void onChange(boolean z) {
                            C11118u c11118u = C11118u.this;
                            c11118u.f21426a = Integer.valueOf(C11559ap.m2653a(c11118u.f21422a).m2656a());
                            if (C11118u.this.f21426a.intValue() != 0) {
                                C11118u.this.f21422a.getContentResolver().unregisterContentObserver(this);
                                if (C11169au.m4849a(C11118u.this.f21422a)) {
                                    C11118u.this.m4969c();
                                }
                            }
                        }
                    });
                }
            }
            return this.f21426a.intValue() != 0;
        }
        return true;
    }

    /* renamed from: e */
    private boolean m4959e() {
        String packageName = this.f21422a.getPackageName();
        return packageName.contains("miui") || packageName.contains("xiaomi") || (this.f21422a.getApplicationInfo().flags & 1) != 0;
    }

    /* renamed from: b */
    private void m4971b(Intent intent) {
        try {
            if (!C11469j.m2974a() && Build.VERSION.SDK_INT >= 26) {
                m4962d(intent);
            } else {
                this.f21422a.startService(intent);
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
    }

    /* renamed from: c */
    private void m4966c(Intent intent) {
        int m2719a = C11537ah.m2715a(this.f21422a).m2719a(EnumC11409gk.ServiceBootMode.m3637a(), EnumC11405gg.START.m3682a());
        int m5013a = m5013a();
        boolean z = m2719a == EnumC11405gg.BIND.m3682a() && f21420b;
        int m3682a = (z ? EnumC11405gg.BIND : EnumC11405gg.START).m3682a();
        if (m3682a != m5013a) {
            m5006a(m3682a);
        }
        if (z) {
            m4962d(intent);
        } else {
            m4971b(intent);
        }
    }

    /* renamed from: d */
    private synchronized void m4962d(Intent intent) {
        if (this.f21431c) {
            Message m5001a = m5001a(intent);
            if (this.f21428a.size() >= 50) {
                this.f21428a.remove(0);
            }
            this.f21428a.add(m5001a);
            return;
        }
        if (this.f21425a == null) {
            Context context = this.f21422a;
            ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.xiaomi.mipush.sdk.u.4
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    synchronized (C11118u.this) {
                        C11118u.this.f21425a = new Messenger(iBinder);
                        C11118u.this.f21431c = false;
                        for (Message message : C11118u.this.f21428a) {
                            try {
                                C11118u.this.f21425a.send(message);
                            } catch (RemoteException e) {
                                AbstractC11049b.m5276a(e);
                            }
                        }
                        C11118u.this.f21428a.clear();
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    C11118u.this.f21425a = null;
                    C11118u.this.f21431c = false;
                }
            };
            Context context2 = this.f21422a;
            context.bindService(intent, serviceConnection, 1);
            this.f21431c = true;
            this.f21428a.clear();
            this.f21428a.add(m5001a(intent));
        } else {
            try {
                this.f21425a.send(m5001a(intent));
            } catch (RemoteException unused) {
                this.f21425a = null;
                this.f21431c = false;
            }
        }
    }

    /* renamed from: a */
    private Message m5001a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    /* renamed from: a */
    public boolean m5006a(int i) {
        if (C11087b.m5151a(this.f21422a).m5141b()) {
            m4967c(i);
            C11430he c11430he = new C11430he();
            c11430he.m3344a(C11541aj.m2703a());
            c11430he.m3335b(C11087b.m5151a(this.f21422a).m5156a());
            c11430he.m3327d(this.f21422a.getPackageName());
            c11430he.m3331c(EnumC11414gp.ClientABTest.f22745a);
            c11430he.f23010a = new HashMap();
            Map<String, String> map = c11430he.f23010a;
            map.put("boot_mode", i + "");
            m5003a(this.f21422a).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null);
            return true;
        }
        return false;
    }
}
