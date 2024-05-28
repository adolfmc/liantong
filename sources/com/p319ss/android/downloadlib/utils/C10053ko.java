package com.p319ss.android.downloadlib.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.download.api.config.InterfaceC9802l;
import com.p319ss.android.downloadad.api.p324mb.C9837ox;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.activity.JumpKllkActivity;
import com.p319ss.android.downloadlib.activity.TTDelegateActivity;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9916h;
import com.p319ss.android.downloadlib.addownload.model.C9923u;
import com.p319ss.android.downloadlib.addownload.model.OpenAppResult;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.p331mb.p332mb.C10008ox;
import com.p319ss.android.downloadlib.p331mb.p333ox.C10015mb;
import com.p319ss.android.downloadlib.p331mb.p333ox.C10018ox;
import com.p319ss.android.downloadlib.p331mb.p333ox.InterfaceC10012hj;
import com.p319ss.android.downloadlib.p334ox.C10032mb;
import com.p319ss.android.downloadlib.p334ox.C10039u;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10153mb;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.sdk.p285a.C6960d;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.ko */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10053ko {
    /* renamed from: mb */
    public static OpenAppResult m7017mb(Context context, Uri uri) {
        if (context == null || uri == null || !"market".equals(uri.getScheme())) {
            return new OpenAppResult(6, 12);
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!C10050jb.m7053mb(context, intent)) {
                return new OpenAppResult(6, 13);
            }
            String m6570lz = C10152hj.m6570lz();
            if (C10050jb.m7060hj(context, m6570lz) && !C10152hj.m6563u()) {
                intent.setPackage(m6570lz);
            }
            if (DownloadSetting.obtainGlobal().optBugFix("fix_jump_market")) {
                intent.addFlags(335544320);
            } else if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            if (DownloadSetting.obtainGlobal().optInt("test_jump_market_failed") == 1) {
                C9971b.m7285mb().m7281mb(false, "jump market error");
                return new OpenAppResult(6, 25);
            }
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return new OpenAppResult(5);
        } catch (Exception unused) {
            return new OpenAppResult(6, 14);
        }
    }

    /* renamed from: mb */
    public static OpenAppResult m7015mb(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new OpenAppResult(6, 11);
        }
        if (C10152hj.m6563u() && C10050jb.m7060hj(context, "com.sec.android.app.samsungapps")) {
            return m7020hj(context, str);
        }
        return m7017mb(context, Uri.parse("market://details?id=" + str));
    }

    /* renamed from: mb */
    public static OpenAppResult m7016mb(Context context, C9916h c9916h, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new OpenAppResult(6, 11);
        }
        if (C10152hj.m6563u() && C10050jb.m7060hj(context, "com.sec.android.app.samsungapps")) {
            return m7020hj(context, str);
        }
        if (!c9916h.f19103ox.isAd() || !c9916h.f19101hj.enableAM()) {
            return m7017mb(context, Uri.parse("market://details?id=" + str));
        }
        JSONArray optJSONArray = C9940x.m7364lz().optJSONArray("am_plans");
        if (C10152hj.m6565ox() && C10153mb.m6557mb(optJSONArray, "am_0")) {
            m7005ox(context, c9916h, str);
            return new OpenAppResult(7, "am_m1");
        } else if (C10152hj.m6577hj() && C10153mb.m6557mb(optJSONArray, "am_3")) {
            return m7025b(context, c9916h, str);
        } else {
            if (C10152hj.m6578h() && C10153mb.m6557mb(optJSONArray, "am_2")) {
                m7021hj(context, c9916h, str);
                return new OpenAppResult(7, "am_m2");
            } else if (C10152hj.m6565ox() && C10153mb.m6557mb(optJSONArray, "am_6")) {
                return m7017mb(context, m7009mb(c9916h));
            } else {
                if (C10152hj.m6580b() && C10153mb.m6557mb(optJSONArray, "am_5")) {
                    m7023h(context, c9916h, str);
                    return new OpenAppResult(7, "am_v1");
                }
                return m7017mb(context, Uri.parse("market://details?id=" + str));
            }
        }
    }

    /* renamed from: hj */
    private static OpenAppResult m7020hj(@NonNull Context context, @NonNull String str) {
        try {
            Uri parse = Uri.parse("https://www.samsungapps.com/appquery/appDetail.as?appId=" + str);
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
            intent.setData(parse);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return new OpenAppResult(5);
        } catch (Exception unused) {
            return new OpenAppResult(6, 14);
        }
    }

    /* renamed from: ox */
    private static void m7005ox(final Context context, final C9916h c9916h, final String str) {
        C9982hj.m7254mb().m7253mb(new Runnable() { // from class: com.ss.android.downloadlib.utils.ko.1
            @Override // java.lang.Runnable
            public void run() {
                final JSONObject m7364lz = C9940x.m7364lz();
                final String optString = m7364lz.optString("s");
                final JSONObject jSONObject = new JSONObject();
                String m6594mb = C10150b.m6594mb(m7364lz.optString("x"), optString);
                JSONObject jSONObject2 = new JSONObject();
                C10050jb.m7040mb(jSONObject2, "p", str);
                C10050jb.m7040mb(jSONObject2, "i", Build.VERSION.INCREMENTAL);
                C10050jb.m7040mb(jSONObject2, "m", Build.MODEL);
                C10050jb.m7040mb(jSONObject2, "im", C10008ox.m7188mb(context));
                C10050jb.m7040mb(jSONObject2, C6960d.f18019d, C10008ox.m7186ox(context));
                C10050jb.m7040mb(jSONObject2, "t", "m");
                byte[] bytes = (!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).getBytes();
                C9940x.m7371hj().mo7931mb(m6594mb, C9940x.m7375e().mo7951mb(bytes, bytes.length), "application/octet-stream;tt-data=a", 0, new InterfaceC9802l() { // from class: com.ss.android.downloadlib.utils.ko.1.1
                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6999mb(String str2) {
                        C10053ko.m7002ox(context, str, str2, c9916h, jSONObject, m7364lz, optString);
                    }

                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6998mb(Throwable th) {
                        Context context2 = context;
                        C10032mb.m7125mb(C10053ko.m7017mb(context2, Uri.parse("market://details?id=" + str)), c9916h, true);
                        C10050jb.m7040mb(jSONObject, "ttdownloader_message", th != null ? th.getMessage() : "null");
                        C9916h c9916h2 = c9916h;
                        JSONObject jSONObject3 = jSONObject;
                        C10053ko.m7001ox(c9916h2, jSONObject3, 4, 1, "market://details?id=" + str);
                    }
                });
            }
        });
    }

    /* renamed from: mb */
    private static String m7006mb(String str, @NonNull JSONObject jSONObject, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String m6594mb = C10150b.m6594mb(jSONObject.optString("g"), str2);
        String m6594mb2 = C10150b.m6594mb(jSONObject.optString("h"), str2);
        return (TextUtils.isEmpty(m6594mb) || TextUtils.isEmpty(m6594mb2)) ? str : str.replace(m6594mb, m6594mb2);
    }

    /* renamed from: b */
    private static OpenAppResult m7025b(Context context, C9916h c9916h, String str) {
        Intent intent = new Intent(context, JumpKllkActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("p", str);
        intent.putExtra("id", c9916h.f19102mb);
        intent.putExtra("start_only_for_android", true);
        JSONObject jSONObject = new JSONObject();
        try {
            context.startActivity(intent);
            return new OpenAppResult(7, "am_kllk2");
        } catch (Throwable unused) {
            m7001ox(c9916h, jSONObject, 1, 3, "market://details?id=" + str);
            return m7017mb(context, Uri.parse("market://details?id=" + str));
        }
    }

    /* renamed from: mb */
    public static void m7014mb(Context context, String str, long j, boolean z) {
        JSONObject jSONObject = new JSONObject();
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        try {
            JSONObject m7364lz = C9940x.m7364lz();
            String optString = m7364lz.optString("s");
            String m6594mb = C10150b.m6594mb(m7364lz.optString("aa"), optString);
            String m6594mb2 = C10150b.m6594mb(m7364lz.optString("ac"), optString);
            String m6594mb3 = C10150b.m6594mb(m7364lz.optString("af"), optString);
            boolean m6554mb = C10153mb.m6554mb(m7364lz, context, m6594mb2);
            StringBuilder sb = new StringBuilder(String.format(m6594mb, str, m6594mb3, m6594mb2));
            Intent intent = new Intent("android.intent.action.VIEW");
            String m6570lz = C10152hj.m6570lz();
            if (C10050jb.m7060hj(context, m6570lz)) {
                intent.setPackage(m6570lz);
            }
            if (z) {
                sb.append(C10150b.m6594mb(m7364lz.optString("ae"), optString));
            } else {
                intent.addFlags(335544320);
            }
            C10050jb.m7040mb(jSONObject, "mf", Boolean.valueOf(m6554mb));
            C10050jb.m7040mb(jSONObject, "if", Boolean.valueOf(z));
            intent.setData(Uri.parse(sb.toString()));
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            C10032mb.m7119mb("am_kllk2", jSONObject, m7453h, true);
            if (m6554mb) {
                m7001ox(m7453h, jSONObject, -1, 3, sb.toString());
            } else {
                m7001ox(m7453h, jSONObject, 3, 3, sb.toString());
            }
        } catch (Exception unused) {
            Context context2 = C9940x.getContext();
            C10032mb.m7125mb(m7017mb(context2, Uri.parse("market://details?id=" + str)), m7453h, true);
            m7001ox(m7453h, jSONObject, 2, 3, "market://details?id=" + str);
        }
    }

    /* renamed from: h */
    private static boolean m7022h(Context context, String str) {
        if (context == null) {
            context = C9940x.getContext();
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            if (DownloadSetting.obtainGlobal().optBugFix("fix_app_link_flag")) {
                intent.addFlags(32768);
            }
        }
        intent.setData(Uri.parse(str));
        intent.putExtra("start_only_for_android", true);
        String m6570lz = C10152hj.m6570lz();
        if (C10050jb.m7060hj(C9940x.getContext(), m6570lz)) {
            intent.setPackage(m6570lz);
        }
        if (C10050jb.m7053mb(C9940x.getContext(), intent)) {
            try {
                context.startActivity(intent);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    /* renamed from: mb */
    private static boolean m7018mb(@NonNull Activity activity, @NonNull String str, @NonNull HashMap<String, String> hashMap) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + str));
        intent.putExtra("start_only_for_android", true);
        intent.putExtra("param", hashMap);
        String m6570lz = C10152hj.m6570lz();
        if (C10050jb.m7060hj(C9940x.getContext(), m6570lz)) {
            intent.setPackage(m6570lz);
        }
        if (C10050jb.m7053mb(C9940x.getContext(), intent)) {
            try {
                activity.startActivity(intent);
                return true;
            } catch (Exception e) {
                C9971b.m7285mb().mo7282mb(e, "start v1");
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m7002ox(Context context, String str, String str2, @NonNull C9916h c9916h, @NonNull JSONObject jSONObject, @NonNull JSONObject jSONObject2, String str3) {
        C10050jb.m7040mb(jSONObject, "ttdownloader_type", (Object) 1);
        try {
            String m7006mb = m7006mb(C10150b.m6595mb(new JSONObject(str2).optString("a")), jSONObject2, str3);
            C10050jb.m7040mb(jSONObject, "open_url", m7006mb);
            if (m7022h(context, m7006mb)) {
                m7001ox(c9916h, jSONObject, -1, 1, m7006mb);
                C10032mb.m7119mb("am_m1", jSONObject, c9916h, true);
            } else {
                C10032mb.m7125mb(m7017mb(context, Uri.parse("market://details?id=" + str)), c9916h, true);
                m7001ox(c9916h, jSONObject, 2, 1, "market://details?id=" + str);
            }
        } catch (Exception unused) {
            C10032mb.m7125mb(m7017mb(context, Uri.parse("market://details?id=" + str)), c9916h, true);
            m7001ox(c9916h, jSONObject, 3, 1, "market://details?id=" + str);
        }
    }

    /* renamed from: hj */
    private static void m7021hj(final Context context, final C9916h c9916h, final String str) {
        C9982hj.m7254mb().m7253mb(new Runnable() { // from class: com.ss.android.downloadlib.utils.ko.2
            @Override // java.lang.Runnable
            public void run() {
                Context context2 = context;
                C10032mb.m7125mb(C10053ko.m7017mb(context2, Uri.parse("market://details?id=" + str)), c9916h, true);
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject m7364lz = C9940x.m7364lz();
                    Thread.sleep(m7364lz.optInt("m2_delay_millis", 1000));
                    C10015mb.m7171mb().m7169mb(context, true);
                    C10018ox c10018ox = new C10018ox();
                    c10018ox.f19315mb = 1;
                    c10018ox.f19316ox = 0;
                    String m6594mb = C10150b.m6594mb(m7364lz.optString("v"), m7364lz.optString("s"));
                    c10018ox.f19312b = String.format(m6594mb, str);
                    C10015mb.m7171mb().m7168mb(c10018ox, (InterfaceC10012hj) null);
                    C10015mb.m7171mb().m7166ox();
                    C10053ko.m7001ox(c9916h, jSONObject, -1, 2, String.format(m6594mb, str));
                } catch (Throwable th) {
                    th.printStackTrace();
                    C9916h c9916h2 = c9916h;
                    C10053ko.m7001ox(c9916h2, jSONObject, 1, 2, "market://details?id=" + str);
                }
            }
        });
    }

    /* renamed from: h */
    private static void m7023h(final Context context, final C9916h c9916h, final String str) {
        C9982hj.m7254mb().m7253mb(new Runnable() { // from class: com.ss.android.downloadlib.utils.ko.3
            @Override // java.lang.Runnable
            public void run() {
                JSONObject m7364lz = C9940x.m7364lz();
                String optString = m7364lz.optString("s");
                final JSONObject jSONObject = new JSONObject();
                String m6594mb = C10150b.m6594mb(m7364lz.optString("x"), optString);
                JSONObject jSONObject2 = new JSONObject();
                C10050jb.m7040mb(jSONObject2, "t", "v");
                C10050jb.m7040mb(jSONObject2, "p", str);
                byte[] bytes = (!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)).getBytes();
                C9940x.m7371hj().mo7931mb(m6594mb, C9940x.m7375e().mo7951mb(bytes, bytes.length), "application/octet-stream;tt-data=a", 0, new InterfaceC9802l() { // from class: com.ss.android.downloadlib.utils.ko.3.1
                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6999mb(String str2) {
                        C10053ko.m7003ox(context, str, str2, c9916h, jSONObject);
                    }

                    @Override // com.p319ss.android.download.api.config.InterfaceC9802l
                    /* renamed from: mb */
                    public void mo6998mb(Throwable th) {
                        Context context2 = context;
                        C10032mb.m7125mb(C10053ko.m7017mb(context2, Uri.parse("market://details?id=" + str)), c9916h, true);
                        C10050jb.m7040mb(jSONObject, "ttdownloader_message", th != null ? th.getMessage() : "null");
                        C9916h c9916h2 = c9916h;
                        JSONObject jSONObject3 = jSONObject;
                        C10053ko.m7001ox(c9916h2, jSONObject3, 7, 5, "market://details?id=" + str);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m7003ox(Context context, String str, String str2, @NonNull C9916h c9916h, @NonNull JSONObject jSONObject) {
        C10050jb.m7040mb(jSONObject, "ttdownloader_type", (Object) 5);
        try {
            String m6595mb = C10150b.m6595mb(new JSONObject(str2).optString("a"));
            if (!TextUtils.isEmpty(m6595mb)) {
                TTDelegateActivity.m7717mb(str, c9916h.f19102mb, m6595mb, jSONObject);
            } else {
                C10032mb.m7125mb(m7017mb(context, Uri.parse("market://details?id=" + str)), c9916h, true);
                m7001ox(c9916h, jSONObject, 5, 5, "market://details?id=" + str);
            }
        } catch (Exception unused) {
            C10032mb.m7125mb(m7017mb(context, Uri.parse("market://details?id=" + str)), c9916h, true);
            m7001ox(c9916h, jSONObject, 6, 5, "market://details?id=" + str);
        }
    }

    /* renamed from: mb */
    public static void m7019mb(@NonNull Activity activity, String str, long j, String str2, String str3) {
        JSONObject jSONObject;
        int i;
        try {
            jSONObject = new JSONObject(str3);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        C9916h m7453h = C9923u.m7451mb().m7453h(j);
        try {
            JSONObject m7364lz = C9940x.m7364lz();
            boolean m6554mb = C10153mb.m6554mb(m7364lz, activity, C10150b.m6594mb(m7364lz.optString("bg"), m7364lz.optString("s")));
            HashMap<String, String> m7029ox = C10050jb.m7029ox(new JSONObject(str2));
            if (m6554mb && !m7029ox.isEmpty() && m7018mb(activity, str, m7029ox)) {
                m7001ox(m7453h, jSONObject, -1, 5, "market://details?id=" + str);
                C10032mb.m7119mb("am_v1", jSONObject, m7453h, true);
                return;
            }
            if (m6554mb) {
                i = m7029ox.isEmpty() ? 1 : 2;
            } else {
                i = 3;
            }
            m7001ox(m7453h, jSONObject, i, 5, "market://details?id=" + str);
            C10032mb.m7125mb(m7017mb(activity, Uri.parse("market://details?id=" + str)), m7453h, true);
        } catch (Exception unused2) {
            Context context = C9940x.getContext();
            C10032mb.m7125mb(m7017mb(context, Uri.parse("market://details?id=" + str)), m7453h, true);
            m7001ox(m7453h, jSONObject, 4, 5, "market://details?id=" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m7001ox(C9916h c9916h, JSONObject jSONObject, int i, int i2, String str) {
        C10050jb.m7040mb(jSONObject, "error_code", Integer.valueOf(i));
        C10050jb.m7040mb(jSONObject, "ttdownloader_type", Integer.valueOf(i2));
        C10050jb.m7040mb(jSONObject, "rmu", str);
        C10050jb.m7040mb(jSONObject, C10152hj.m6570lz(), Integer.valueOf(C10050jb.m7032ox(C9940x.getContext(), C10152hj.m6570lz())));
        AdEventHandler.m7315mb().m7293ox("am_result", jSONObject, c9916h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: ox */
    public static OpenAppResult m7004ox(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new OpenAppResult(4, 11);
        }
        if (context == null) {
            context = C9940x.getContext();
        }
        Intent m7028u = C10050jb.m7028u(context, str);
        if (m7028u == null) {
            return new OpenAppResult(4, 22);
        }
        m7028u.putExtra("start_only_for_android", true);
        try {
            context.startActivity(m7028u);
            return new OpenAppResult(3);
        } catch (Exception unused) {
            return new OpenAppResult(4, 23);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public static OpenAppResult m7013mb(Context context, String str, InterfaceC9836mb interfaceC9836mb) {
        Intent m7028u = C10050jb.m7028u(context, str);
        if (m7028u == null) {
            return new OpenAppResult(4, 22);
        }
        if (Build.VERSION.SDK_INT >= 26 && C9940x.m7364lz().optInt("open_package_mode") == 1 && C9940x.m7369jb() != null && C9940x.m7369jb().mo7935mb() && interfaceC9836mb.mo7492e()) {
            TTDelegateActivity.m7709ox(str, interfaceC9836mb);
            return new OpenAppResult(3);
        }
        m7028u.putExtra("start_only_for_android", true);
        try {
            context.startActivity(m7028u);
            return new OpenAppResult(3);
        } catch (Exception unused) {
            return new OpenAppResult(4, 23);
        }
    }

    /* renamed from: mb */
    static OpenAppResult m7007mb(String str, InterfaceC9836mb interfaceC9836mb) {
        return m7013mb(C9940x.getContext(), str, interfaceC9836mb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: ox */
    public static OpenAppResult m7000ox(String str, @NonNull InterfaceC9836mb interfaceC9836mb) {
        if (TextUtils.isEmpty(str)) {
            return new OpenAppResult(2, 21);
        }
        Context context = C9940x.getContext();
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        intent.addFlags(268435456);
        intent.putExtra("open_url", str);
        intent.putExtra("start_only_for_android", true);
        if (DownloadSetting.obtainGlobal().optBugFix("fix_app_link_flag")) {
            intent.addFlags(67108864);
        }
        if (C10050jb.m7033ox(context, intent)) {
            if (C9940x.m7364lz().optInt("open_url_mode") == 0 && C9940x.m7369jb() != null && C9940x.m7369jb().mo7935mb() && Build.VERSION.SDK_INT >= 26 && interfaceC9836mb.mo7492e()) {
                TTDelegateActivity.m7716mb(str, interfaceC9836mb);
            } else {
                try {
                    C9940x.getContext().startActivity(intent);
                } catch (Exception unused) {
                    return new OpenAppResult(2);
                }
            }
            return new OpenAppResult(1);
        }
        return new OpenAppResult(2, 24);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public static OpenAppResult m7010mb(@NonNull C9837ox c9837ox, String str, String str2) {
        OpenAppResult m7000ox = m7000ox(str, c9837ox);
        return (C10039u.m7108mb(c9837ox) && m7000ox.getType() == 2) ? m7007mb(str2, c9837ox) : m7000ox;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static boolean m7024b(Context context, String str) {
        if (context == null) {
            return false;
        }
        try {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(parse);
            intent.addFlags(268435456);
            intent.putExtra("open_url", str);
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* renamed from: mb */
    public static Uri m7009mb(C9916h c9916h) {
        String mo7489h = c9916h.mo7489h();
        Uri.Builder builder = new Uri.Builder();
        JSONObject jSONObject = new JSONObject();
        JSONObject m7364lz = C9940x.m7364lz();
        String m6594mb = C10150b.m6594mb(m7364lz.optString("bl"), m7364lz.optString("s"));
        builder.scheme("market").authority("details").appendQueryParameter("id", mo7489h);
        if (!TextUtils.isEmpty(m6594mb)) {
            builder.appendPath(m6594mb);
        }
        Uri build = builder.build();
        m7001ox(c9916h, jSONObject, -1, 6, build.toString());
        return build;
    }
}
