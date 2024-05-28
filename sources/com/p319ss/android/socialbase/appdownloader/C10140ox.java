package com.p319ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.socialbase.appdownloader.p338mb.AbstractC10134mb;
import com.p319ss.android.socialbase.appdownloader.p338mb.C10129hj;
import com.p319ss.android.socialbase.appdownloader.p338mb.C10136ox;
import com.p319ss.android.socialbase.appdownloader.p338mb.C10137u;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10153mb;
import com.p319ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.p319ss.android.socialbase.downloader.common.AppStatusManager;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.Downloader;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.thread.WeakDownloadHandler;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.ss.android.socialbase.appdownloader.ox */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10140ox {

    /* renamed from: b */
    private static C10146mb f19544b = null;

    /* renamed from: mb */
    private static final String f19545mb = "ox";

    /* renamed from: ox */
    private static InterfaceC10143b f19546ox;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.appdownloader.ox$b */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface InterfaceC10143b {
        /* renamed from: mb */
        void mo6696mb(DownloadInfo downloadInfo, C10126mb c10126mb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.appdownloader.ox$ox */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface InterfaceC10147ox {
        /* renamed from: mb */
        boolean mo6693mb(@NonNull Context context);
    }

    /* renamed from: mb */
    public static boolean m6713mb(Context context, DownloadInfo downloadInfo, Intent intent, boolean z) {
        JSONArray optJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray("ah_plans");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (C10153mb.m6555mb(optJSONObject) && m6714mb(context, downloadInfo, intent, optJSONObject, z)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: mb */
    private static boolean m6714mb(Context context, DownloadInfo downloadInfo, Intent intent, JSONObject jSONObject, boolean z) {
        char c;
        boolean z2 = false;
        if (jSONObject == null || downloadInfo == null) {
            return false;
        }
        C10126mb c10126mb = new C10126mb();
        String optString = jSONObject.optString("type");
        if (!TextUtils.isEmpty(optString)) {
            DownloadSetting obtain = DownloadSetting.obtain(downloadInfo);
            c10126mb.f19536mb = optString;
            switch (optString.hashCode()) {
                case -985763637:
                    if (optString.equals("plan_a")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763636:
                    if (optString.equals("plan_b")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763635:
                    if (optString.equals("plan_c")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763634:
                    if (optString.equals("plan_d")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763633:
                    if (optString.equals("plan_e")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763632:
                    if (optString.equals("plan_f")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763631:
                    if (optString.equals("plan_g")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -985763630:
                    if (optString.equals("plan_h")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    if (!downloadInfo.isSavePathRedirected()) {
                        c10126mb.f19537ox = downloadInfo.getAntiHijackErrorCode(-1);
                        break;
                    }
                case 2:
                    C10126mb m6704mb = m6704mb(jSONObject, obtain);
                    if (m6704mb.f19537ox == 0) {
                        if (optString.equals("plan_f") && TextUtils.isEmpty(downloadInfo.getDBJsonString("file_content_uri"))) {
                            c10126mb.f19537ox = downloadInfo.getAntiHijackErrorCode(10);
                            break;
                        } else {
                            z2 = m6712mb(context, downloadInfo, jSONObject, c10126mb);
                            break;
                        }
                    } else {
                        c10126mb = m6704mb;
                        break;
                    }
                    break;
                case 3:
                    C10126mb m6704mb2 = m6704mb(jSONObject, obtain);
                    if (m6704mb2.f19537ox == 0) {
                        if (!downloadInfo.isSavePathRedirected()) {
                            c10126mb.f19537ox = downloadInfo.getAntiHijackErrorCode(-1);
                            break;
                        } else {
                            z2 = m6698ox(context, downloadInfo, jSONObject, c10126mb);
                            break;
                        }
                    } else {
                        c10126mb = m6704mb2;
                        break;
                    }
                case 4:
                    c10126mb.f19536mb = "plan_d";
                    if (!C10152hj.m6580b()) {
                        c10126mb.f19537ox = 2;
                        break;
                    } else {
                        try {
                            C10085b.m6914mb(context, intent);
                            c10126mb.f19537ox = 0;
                            z2 = true;
                            break;
                        } catch (Throwable th) {
                            c10126mb.f19537ox = 4;
                            c10126mb.f19533b = th.toString();
                            break;
                        }
                    }
                case 5:
                    C10126mb m6697ox = m6697ox(jSONObject, obtain);
                    if (m6697ox.f19537ox == 0) {
                        z2 = m6711mb(context, downloadInfo, jSONObject, c10126mb, obtain);
                        break;
                    } else {
                        c10126mb = m6697ox;
                        break;
                    }
                case 6:
                    String m6594mb = C10150b.m6594mb(obtain.optString("bh"), obtain.optString("s"));
                    C10126mb m6703mb = m6703mb(jSONObject, m6594mb, context, obtain);
                    if (m6703mb.f19537ox == 0) {
                        String packageName = context.getPackageName();
                        if (C10153mb.m6554mb(DownloadSetting.getGlobalSettings(), context, m6594mb)) {
                            try {
                                C10085b.m6914mb(context, intent);
                                c10126mb.f19537ox = 0;
                                C10153mb.m6554mb(DownloadSetting.getGlobalSettings(), context, packageName);
                                z2 = true;
                                break;
                            } catch (Throwable th2) {
                                try {
                                    c10126mb.f19537ox = 1;
                                    c10126mb.f19533b = th2.toString();
                                    break;
                                } finally {
                                    C10153mb.m6554mb(DownloadSetting.getGlobalSettings(), context, packageName);
                                }
                            }
                        } else {
                            c10126mb.f19537ox = 11;
                            break;
                        }
                    } else {
                        c10126mb = m6703mb;
                        break;
                    }
                case 7:
                    if (Build.VERSION.SDK_INT >= 26 || C10152hj.m6565ox()) {
                        if ((jSONObject.optInt("enable_for_all", 0) == 1) || z) {
                            if (!(jSONObject.optInt("show_unknown_source_on_startup") == 1)) {
                                z2 = m6716mb(context, intent, jSONObject, downloadInfo.getId(), c10126mb);
                                break;
                            }
                        }
                    }
                    break;
            }
            if (z2) {
                downloadInfo.getTempCacheData().put("ah_attempt", c10126mb.m6739mb());
            }
            if (f19546ox != null) {
                downloadInfo.getTempCacheData().put("intent", intent);
                f19546ox.mo6696mb(downloadInfo, c10126mb);
            }
        }
        return z2;
    }

    /* renamed from: mb */
    private static boolean m6711mb(Context context, @NonNull DownloadInfo downloadInfo, JSONObject jSONObject, @NonNull C10126mb c10126mb, DownloadSetting downloadSetting) {
        boolean z;
        String optString = jSONObject.optString("type");
        c10126mb.f19536mb = optString;
        Intent ox = C10129hj.m6733mb(context, "vbi", jSONObject, downloadInfo).mo6729ox();
        StringBuilder sb = new StringBuilder();
        try {
            z = m6699ox(context, ox);
        } catch (Throwable th) {
            sb.append(optString);
            sb.append(" startActivity failed : ");
            sb.append(m6705mb(th));
            m6710mb(c10126mb, 1);
            z = false;
        }
        if (!z) {
            c10126mb.f19533b = sb.toString();
        } else {
            c10126mb.f19537ox = 0;
        }
        return true;
    }

    /* renamed from: mb */
    private static boolean m6712mb(Context context, DownloadInfo downloadInfo, JSONObject jSONObject, C10126mb c10126mb) {
        boolean z;
        if (context == null || jSONObject == null) {
            return false;
        }
        String optString = jSONObject.optString("device_plans");
        c10126mb.f19534h = optString;
        if (TextUtils.isEmpty(optString)) {
            return false;
        }
        String[] split = optString.split(",");
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath)) {
            return false;
        }
        File file = new File(savePath);
        StringBuilder sb = new StringBuilder();
        String str = null;
        int length = split.length;
        int i = 0;
        while (true) {
            z = true;
            if (i >= length) {
                z = false;
                break;
            }
            String str2 = split[i];
            AbstractC10134mb m6733mb = C10129hj.m6733mb(context, str2, jSONObject, downloadInfo);
            if (m6733mb != null) {
                Intent ox = m6733mb.mo6729ox();
                if (ox != null) {
                    if (m6706mb(file, downloadInfo, jSONObject)) {
                        try {
                            m6715mb(context, ox, false);
                            str = str2;
                            break;
                        } catch (Throwable th) {
                            sb.append(str2);
                            sb.append(" startActivity failed : ");
                            sb.append(m6705mb(th));
                            m6710mb(c10126mb, 1);
                        }
                    } else {
                        m6710mb(c10126mb, 6);
                        sb.append(str2);
                        sb.append(" createDescFile failed! ");
                    }
                } else {
                    m6710mb(c10126mb, 3);
                    sb.append(str2);
                    sb.append(" resolveActivity failed! ");
                }
            }
            sb.append("  ");
            i++;
        }
        if (!z) {
            c10126mb.f19533b = sb.toString();
        } else {
            c10126mb.f19535hj = str;
            c10126mb.f19537ox = 0;
        }
        return z;
    }

    /* renamed from: ox */
    private static boolean m6698ox(Context context, @NonNull DownloadInfo downloadInfo, JSONObject jSONObject, @NonNull C10126mb c10126mb) {
        if (context == null || jSONObject == null) {
            return false;
        }
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath)) {
            return false;
        }
        c10126mb.f19535hj = "custom";
        AbstractC10134mb m6733mb = C10129hj.m6733mb(context, "custom", jSONObject, downloadInfo);
        if (m6733mb == null || !m6733mb.m6731mb()) {
            c10126mb.f19537ox = 3;
            return false;
        }
        Intent ox = m6733mb.mo6729ox();
        if (ox == null) {
            return false;
        }
        if (m6706mb(new File(savePath), downloadInfo, jSONObject)) {
            if (m6699ox(context, ox)) {
                c10126mb.f19537ox = 0;
                return true;
            }
            c10126mb.f19537ox = 1;
        } else {
            c10126mb.f19537ox = 6;
        }
        return false;
    }

    /* renamed from: mb */
    public static int m6707mb(@NonNull DownloadSetting downloadSetting) {
        JSONObject optJSONObject;
        if (downloadSetting.optJSONObject("download_dir") != null ? !TextUtils.isEmpty(optJSONObject.optString("dir_name")) : false) {
            if (DownloadSetting.obtainGlobal().optBugFix("get_download_info_by_list")) {
                JSONArray optJSONArray = downloadSetting.optJSONArray("ah_plans");
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    int i = -1;
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (C10153mb.m6555mb(optJSONObject2)) {
                            String optString = optJSONObject2.optString("type");
                            if ("plan_a".equals(optString) || "plan_b".equals(optString) || "plan_e".equals(optString) || "plan_f".equals(optString)) {
                                C10126mb m6704mb = m6704mb(optJSONObject2, downloadSetting);
                                int i3 = m6704mb.f19537ox;
                                if (m6704mb.f19537ox == 0) {
                                    return 0;
                                }
                                i = i3;
                            } else if ("plan_d".equalsIgnoreCase(optString) || "plan_h".equalsIgnoreCase(optString)) {
                                return 0;
                            } else {
                                if ("plan_g".equalsIgnoreCase(optString)) {
                                    C10126mb m6697ox = m6697ox(optJSONObject2, downloadSetting);
                                    int i4 = m6697ox.f19537ox;
                                    if (m6697ox.f19537ox == 0) {
                                        return 0;
                                    }
                                    i = i4;
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                    return i;
                }
                return -1;
            }
            return 4;
        }
        return 5;
    }

    @NonNull
    /* renamed from: mb */
    public static C10126mb m6704mb(JSONObject jSONObject, DownloadSetting downloadSetting) {
        C10126mb c10126mb = new C10126mb();
        if (jSONObject == null) {
            return c10126mb;
        }
        String optString = jSONObject.optString("type");
        c10126mb.f19536mb = optString;
        if ("plan_b".equals(optString)) {
            c10126mb.f19534h = "custom";
            if (C10129hj.m6732mb(DownloadComponentManager.getAppContext(), "custom", jSONObject, downloadSetting)) {
                c10126mb.f19537ox = 0;
                return c10126mb;
            }
            m6710mb(c10126mb, 3);
        } else {
            String optString2 = jSONObject.optString("device_plans");
            c10126mb.f19534h = optString2;
            if (!TextUtils.isEmpty(optString2)) {
                for (String str : optString2.split(",")) {
                    if (C10129hj.m6732mb(DownloadComponentManager.getAppContext(), str, jSONObject, downloadSetting)) {
                        c10126mb.f19537ox = 0;
                        return c10126mb;
                    }
                    m6710mb(c10126mb, 3);
                }
            }
        }
        return c10126mb;
    }

    /* renamed from: ox */
    public static C10126mb m6697ox(JSONObject jSONObject, DownloadSetting downloadSetting) {
        C10126mb c10126mb = new C10126mb();
        if (jSONObject == null) {
            return c10126mb;
        }
        c10126mb.f19536mb = jSONObject.optString("type");
        c10126mb.f19534h = "vbi";
        if (C10129hj.m6732mb(DownloadComponentManager.getAppContext(), "vbi", jSONObject, downloadSetting)) {
            c10126mb.f19537ox = 0;
        } else {
            m6710mb(c10126mb, 3);
        }
        return c10126mb;
    }

    /* renamed from: mb */
    public static C10126mb m6703mb(JSONObject jSONObject, String str, Context context, DownloadSetting downloadSetting) {
        C10126mb c10126mb = new C10126mb();
        if (jSONObject == null || !C10152hj.m6565ox()) {
            return c10126mb;
        }
        c10126mb.f19536mb = jSONObject.optString("type");
        if (downloadSetting.optInt("bi", 0) == 1) {
            c10126mb.f19537ox = 0;
            return c10126mb;
        }
        if (m6720mb(context)) {
            c10126mb.f19537ox = 2;
        } else if (C10153mb.m6559mb(str) != null) {
            c10126mb.f19537ox = 0;
        } else {
            c10126mb.f19537ox = 9;
        }
        return c10126mb;
    }

    /* renamed from: mb */
    private static void m6710mb(C10126mb c10126mb, int i) {
        if (c10126mb.f19537ox != -1) {
            c10126mb.f19537ox = (c10126mb.f19537ox * 10) + i;
        } else {
            c10126mb.f19537ox = i;
        }
    }

    /* renamed from: mb */
    private static boolean m6706mb(File file, DownloadInfo downloadInfo, @NonNull JSONObject jSONObject) {
        if (file == null) {
            return false;
        }
        String path = file.getPath();
        JSONObject optJSONObject = DownloadSetting.obtain(downloadInfo.getId()).optJSONObject("download_dir");
        File file2 = null;
        String optString = optJSONObject != null ? optJSONObject.optString("ins_desc") : null;
        if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(optString)) {
            file2 = new File(path + File.separator + optString);
        }
        if (file2 != null) {
            try {
                if (file2.createNewFile()) {
                    file2.deleteOnExit();
                    return true;
                }
                return true;
            } catch (IOException unused) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: mb */
    public static boolean m6716mb(Context context, @Nullable Intent intent, JSONObject jSONObject, int i, @Nullable C10126mb c10126mb) {
        if (context == null || jSONObject == null) {
            return false;
        }
        long optLong = jSONObject.optLong("jump_interval", 0L);
        if (optLong <= 0) {
            return false;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("sp_ah_config", 0);
        if ((System.currentTimeMillis() - sharedPreferences.getLong("last_jump_unknown_source_time", 0L)) / 60000 >= optLong && !m6720mb(context)) {
            sharedPreferences.edit().putLong("last_jump_unknown_source_time", System.currentTimeMillis()).apply();
            if (jSONObject.optInt("show_unknown_source_dialog", 0) == 1) {
                Intent intent2 = new Intent(context, JumpUnknownSourceActivity.class);
                intent2.addFlags(268435456);
                intent2.putExtra("intent", intent);
                intent2.putExtra("config", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                intent2.putExtra("id", i);
                try {
                    if (m6715mb(context, intent2, false)) {
                        m6725hj(i, jSONObject);
                    }
                    return true;
                } catch (Throwable th) {
                    if (c10126mb != null) {
                        c10126mb.f19537ox = 1;
                        c10126mb.f19533b = "tryShowUnknownSourceDialog" + m6705mb(th);
                    }
                    return false;
                }
            }
            if (m6718mb(context, intent, i, jSONObject)) {
                m6728b(i, jSONObject);
            }
            return true;
        }
        return false;
    }

    /* renamed from: mb */
    public static boolean m6718mb(Context context, @Nullable Intent intent, int i, JSONObject jSONObject) {
        try {
            if (C10152hj.m6565ox() && Build.VERSION.SDK_INT < 26 && !m6724hj(context)) {
                C10137u c10137u = new C10137u(context);
                if (c10137u.m6731mb()) {
                    m6717mb(context, intent, i, jSONObject, new InterfaceC10147ox() { // from class: com.ss.android.socialbase.appdownloader.ox.1
                        @Override // com.p319ss.android.socialbase.appdownloader.C10140ox.InterfaceC10147ox
                        /* renamed from: mb */
                        public boolean mo6693mb(@NonNull Context context2) {
                            return C10140ox.m6724hj(context2);
                        }
                    });
                    return m6699ox(context, c10137u.mo6729ox());
                }
            } else if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26 || m6726h(context)) {
                return false;
            } else {
                C10136ox c10136ox = new C10136ox(context);
                if (c10136ox.m6731mb()) {
                    m6717mb(context, intent, i, jSONObject, new InterfaceC10147ox() { // from class: com.ss.android.socialbase.appdownloader.ox.2
                        @Override // com.p319ss.android.socialbase.appdownloader.C10140ox.InterfaceC10147ox
                        /* renamed from: mb */
                        public boolean mo6693mb(@NonNull Context context2) {
                            return C10140ox.m6726h(context2);
                        }
                    });
                    return m6699ox(context, c10136ox.mo6729ox());
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: mb */
    public static boolean m6720mb(Context context) {
        if (context == null) {
            return true;
        }
        try {
            if (C10152hj.m6565ox() && Build.VERSION.SDK_INT < 26) {
                return m6724hj(context);
            }
            if (Build.VERSION.SDK_INT < 26 || context.getApplicationInfo().targetSdkVersion < 26) {
                return true;
            }
            return m6726h(context);
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: hj */
    public static boolean m6724hj(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "install_non_market_apps", 1) > 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 26)
    /* renamed from: h */
    public static boolean m6726h(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return context.getPackageManager().canRequestPackageInstalls();
        } catch (Throwable unused) {
            return true;
        }
    }

    /* renamed from: mb */
    public static boolean m6723mb() {
        return C10144h.f19547mb == 1;
    }

    /* renamed from: hj */
    private static void m6725hj(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt("show_unknown_source_on_startup") == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, "guide_auth_dialog_show", jSONObject2);
    }

    /* renamed from: mb */
    public static void m6722mb(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt("show_unknown_source_on_startup") == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, "guide_auth_dialog_confirm", jSONObject2);
    }

    /* renamed from: ox */
    public static void m6702ox(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt("show_unknown_source_on_startup") == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, "guide_auth_dialog_cancel", jSONObject2);
    }

    /* renamed from: b */
    public static void m6728b(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt("show_unknown_source_on_startup") == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, "guide_auth_open_setting", jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static void m6701ox(int i, boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        int i2 = 1;
        try {
            jSONObject.put("scene", z ? 1 : 2);
            if (!z2) {
                i2 = 2;
            }
            jSONObject.put("result_code", i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, "guide_auth_result", jSONObject);
    }

    /* renamed from: mb */
    private static void m6717mb(Context context, Intent intent, int i, JSONObject jSONObject, InterfaceC10147ox interfaceC10147ox) {
        if (f19544b != null) {
            AppStatusManager.getInstance().unregisterAppSwitchListener(f19544b);
            f19544b = null;
        }
        f19544b = new C10146mb(context, intent, i, jSONObject, interfaceC10147ox);
        AppStatusManager.getInstance().registerAppSwitchListener(f19544b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static boolean m6699ox(Context context, Intent intent) {
        return m6715mb(context, intent, true);
    }

    /* renamed from: mb */
    public static boolean m6715mb(Context context, Intent intent, boolean z) {
        if (context == null || intent == null) {
            return false;
        }
        if (z) {
            try {
                intent.putExtra("start_only_for_android", true);
                context.startActivity(intent);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        intent.putExtra("start_only_for_android", true);
        context.startActivity(intent);
        return true;
    }

    /* renamed from: mb */
    public static String m6705mb(Throwable th) {
        String th2 = th.toString();
        return th2.length() > 800 ? th2.substring(0, 500) : th2;
    }

    /* renamed from: com.ss.android.socialbase.appdownloader.ox$hj */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    static class CallableC10145hj implements Callable<Boolean> {

        /* renamed from: b */
        private final Handler f19556b;

        /* renamed from: hj */
        private final long f19557hj;

        /* renamed from: mb */
        private final Context f19558mb;

        /* renamed from: ox */
        private final InterfaceC10147ox f19559ox;

        public CallableC10145hj(Handler handler, Context context, InterfaceC10147ox interfaceC10147ox, long j) {
            this.f19558mb = context;
            this.f19559ox = interfaceC10147ox;
            this.f19556b = handler;
            this.f19557hj = j;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() throws Exception {
            if (this.f19559ox != null && this.f19557hj > 0 && this.f19557hj <= 10000) {
                Context context = this.f19558mb;
                boolean mo6693mb = context != null ? this.f19559ox.mo6693mb(context) : false;
                Message obtain = Message.obtain();
                if (mo6693mb) {
                    obtain.what = 2;
                    this.f19556b.sendMessage(obtain);
                } else {
                    obtain.what = 1;
                    this.f19556b.sendMessageDelayed(obtain, this.f19557hj);
                }
                return false;
            }
            return false;
        }
    }

    /* renamed from: mb */
    public static void m6709mb(InterfaceC10143b interfaceC10143b) {
        f19546ox = interfaceC10143b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.appdownloader.ox$mb */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static class C10146mb implements AppStatusManager.AppStatusChangeListener {

        /* renamed from: b */
        private JSONObject f19560b;

        /* renamed from: mb */
        private final C10144h f19561mb;

        /* renamed from: ox */
        private final int f19562ox;

        public C10146mb(Context context, Intent intent, int i, JSONObject jSONObject, InterfaceC10147ox interfaceC10147ox) {
            this.f19560b = jSONObject;
            this.f19562ox = jSONObject.optInt("query_interval", 1000);
            this.f19561mb = new C10144h(context, intent, i, interfaceC10147ox, this.f19562ox);
        }

        @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppForeground() {
            if (!this.f19561mb.f19553lz) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                this.f19561mb.f19554u.sendMessage(obtain);
            }
            AppStatusManager.getInstance().unregisterAppSwitchListener(this);
            C10146mb unused = C10140ox.f19544b = null;
        }

        @Override // com.p319ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppBackground() {
            int optInt = this.f19560b.optInt("time_out_second", 20);
            Message obtain = Message.obtain();
            obtain.what = 1;
            this.f19561mb.f19554u.sendMessage(obtain);
            if (optInt <= 0 || optInt >= 60) {
                return;
            }
            Message obtain2 = Message.obtain();
            obtain2.what = 2;
            this.f19561mb.f19554u.sendMessageDelayed(obtain2, optInt * 1000);
        }
    }

    /* renamed from: com.ss.android.socialbase.appdownloader.ox$h */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    static class C10144h implements WeakDownloadHandler.IHandler {

        /* renamed from: mb */
        public static int f19547mb;

        /* renamed from: ox */
        private static int f19548ox;

        /* renamed from: b */
        private final Context f19549b;

        /* renamed from: h */
        private final InterfaceC10147ox f19550h;

        /* renamed from: hj */
        private final Intent f19551hj;

        /* renamed from: ko */
        private final long f19552ko;

        /* renamed from: lz */
        private boolean f19553lz = false;

        /* renamed from: u */
        private final Handler f19554u;

        /* renamed from: ww */
        private Future<Boolean> f19555ww;

        public C10144h(Context context, Intent intent, int i, InterfaceC10147ox interfaceC10147ox, long j) {
            this.f19549b = context;
            this.f19551hj = intent;
            f19548ox = i;
            this.f19550h = interfaceC10147ox;
            this.f19554u = new WeakDownloadHandler(Looper.getMainLooper(), this);
            this.f19552ko = j;
        }

        @Override // com.p319ss.android.socialbase.downloader.thread.WeakDownloadHandler.IHandler
        public void handleMsg(Message message) {
            if (message != null) {
                if (message.what == 1) {
                    long j = this.f19552ko;
                    if (j <= 0 || j > 10000) {
                        return;
                    }
                    f19547mb = 1;
                    this.f19555ww = DownloadComponentManager.getCPUThreadExecutor().submit(new CallableC10145hj(this.f19554u, this.f19549b, this.f19550h, this.f19552ko));
                } else if (message.what == 2) {
                    f19547mb = 2;
                    this.f19554u.removeMessages(2);
                    this.f19554u.removeMessages(1);
                    Future<Boolean> future = this.f19555ww;
                    if (future != null) {
                        future.cancel(true);
                    }
                    if (!this.f19553lz && (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground())) {
                        Intent intent = this.f19551hj;
                        if (intent != null) {
                            C10140ox.m6699ox(this.f19549b, intent);
                        } else {
                            DownloadInfo downloadInfo = Downloader.getInstance(this.f19549b).getDownloadInfo(f19548ox);
                            if (downloadInfo != null && downloadInfo.isDownloadOverStatus()) {
                                C10085b.m6895ox(this.f19549b, f19548ox, false);
                            }
                        }
                        this.f19553lz = true;
                    }
                    C10140ox.m6701ox(f19548ox, this.f19551hj == null, C10140ox.m6720mb(this.f19549b));
                }
            }
        }
    }
}
