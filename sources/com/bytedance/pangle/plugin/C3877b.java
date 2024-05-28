package com.bytedance.pangle.plugin;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.support.annotation.NonNull;
import com.bytedance.pangle.C3865h;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.C3870a;
import com.bytedance.pangle.log.C3871b;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.p171a.C3769a;
import com.bytedance.pangle.p175c.C3784b;
import com.bytedance.pangle.p176d.C3791b;
import com.bytedance.pangle.p176d.C3792c;
import com.bytedance.pangle.p177e.C3805b;
import com.bytedance.pangle.p177e.C3811f;
import com.bytedance.pangle.p177e.C3815g;
import com.bytedance.pangle.p180g.C3846e;
import com.bytedance.pangle.res.p181a.C3910c;
import com.bytedance.pangle.util.C3945e;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3948h;
import com.bytedance.pangle.util.C3950i;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.plugin.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3877b {

    /* renamed from: a */
    private static final C3865h f9242a = C3865h.m16801a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static boolean m16780a(final File file, final String str, final int i) {
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("useOpt;");
        try {
            f9242a.m16800a(1000, 0, str, i, null);
            C3870a m16784a = C3870a.m16784a("Zeus/install_pangle", "PluginInstaller", "install:".concat(String.valueOf(str)));
            m16778a(C3784b.f9039e, C3784b.C3785a.f9060n, str, i, -1L, null);
            C3947g.m16632a(C3792c.m16937a(str, i));
            C3769a.m16982a(new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.b.1
                @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                /* renamed from: a */
                public final void mo16758a() {
                    C3877b.m16779a(file, str, i, stringBuffer);
                }
            }, new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.b.2
                @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                /* renamed from: a */
                public final void mo16758a() {
                    final Map m16771f = C3877b.m16771f(file, str, i, stringBuffer);
                    C3877b.m16774c(file, str, i, stringBuffer);
                    C3877b.m16776a(C3877b.m16770g(file, str, i, stringBuffer), str, i, stringBuffer);
                    if (C3950i.m16619e() || C3950i.m16622b()) {
                        C3769a.m16982a(new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.b.2.1
                            @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                            /* renamed from: a */
                            public final void mo16758a() {
                                C3877b.m16777a(str, i, m16771f, stringBuffer);
                            }
                        }, new C3769a.InterfaceC3771a() { // from class: com.bytedance.pangle.plugin.b.2.2
                            @Override // com.bytedance.pangle.p171a.C3769a.InterfaceC3771a
                            /* renamed from: a */
                            public final void mo16758a() {
                                C3877b.m16772e(file, str, i, stringBuffer);
                            }
                        });
                        return;
                    }
                    C3877b.m16777a(str, i, m16771f, stringBuffer);
                    C3877b.m16772e(file, str, i, stringBuffer);
                }
            });
            C3947g.m16634a(file);
            m16778a(C3784b.f9040f, C3784b.C3785a.f9061o, str, i, m16784a.m16786a(), stringBuffer.toString());
            m16784a.m16785a("success");
            f9242a.m16800a(1100, 0, str, i, null);
            return true;
        } catch (Throwable th) {
            if (th instanceof C3882a) {
                ZeusLogger.m16787w("Zeus/install_pangle", "PluginInstaller " + str + " install failed.", th);
            } else {
                ZeusLogger.m16787w("Zeus/install_pangle", "PluginInstaller " + str + " install failed unknown error.", th);
                m16778a(C3784b.f9040f, C3784b.C3785a.f9062p, str, i, -1L, stringBuffer.toString());
                f9242a.m16800a(1100, -1, str, i, th);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public static Map<String, List<ZipEntry>> m16771f(File file, String str, int i, StringBuffer stringBuffer) {
        if (GlobalParam.getInstance().checkMatchHostAbi()) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                try {
                    C3945e<Boolean, Map<String, List<ZipEntry>>> m16953a = C3791b.m16953a(file);
                    boolean booleanValue = m16953a.f9369a.booleanValue();
                    Map<String, List<ZipEntry>> map = m16953a.f9370b;
                    if (booleanValue) {
                        return map;
                    }
                    throw new C3882a("插件包包含so不符合宿主ABI类型", (byte) 0);
                } catch (Exception e) {
                    m16778a(C3784b.f9040f, C3784b.C3785a.f9069w, str, i, -1L, null);
                    f9242a.m16800a(1100, -5, str, i, e);
                    throw new C3882a("插件包包含so不符合宿主ABI类型", e, (byte) 0);
                }
            } finally {
                stringBuffer.append("checkMatchHostAbi cost:");
                stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
                stringBuffer.append(";");
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static String m16770g(File file, String str, int i, StringBuffer stringBuffer) {
        long currentTimeMillis = System.currentTimeMillis();
        String m16934b = C3792c.m16934b(str, i);
        try {
            try {
                C3948h.m16627a(file.getAbsolutePath(), m16934b);
                return m16934b;
            } catch (Exception e) {
                m16778a(C3784b.f9040f, C3784b.C3785a.f9065s, str, i, -1L, null);
                f9242a.m16800a(1100, -6, str, i, e);
                throw new C3882a("安装包拷贝失败", e, (byte) 0);
            }
        } finally {
            stringBuffer.append("copyApk cost:");
            stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuffer.append(";");
        }
    }

    /* renamed from: a */
    private static void m16778a(String str, int i, @NonNull String str2, int i2, long j, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.putOpt("status_code", C3871b.m16783a(Integer.valueOf(i)));
            jSONObject.putOpt("plugin_package_name", C3871b.m16783a(str2));
            jSONObject.putOpt("version_code", C3871b.m16783a(Integer.valueOf(i2)));
            jSONObject3.putOpt("duration", Integer.valueOf(C3871b.m16782b(Long.valueOf(j))));
            jSONObject2.putOpt("message", C3871b.m16783a(str3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        C3784b.m16961a().m16960a(str, jSONObject, jSONObject3, jSONObject2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.plugin.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3882a extends IOException {
        /* synthetic */ C3882a(String str, byte b) {
            this(str);
        }

        /* synthetic */ C3882a(String str, Throwable th, byte b) {
            this(str, th);
        }

        private C3882a(String str) {
            super(str);
        }

        private C3882a(String str, Throwable th) {
            super(str, th);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m16779a(File file, String str, int i, StringBuffer stringBuffer) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (C3846e.m16839a(file.getAbsolutePath(), str)) {
                    return;
                }
                throw new RuntimeException("安装包签名校验失败[1]");
            } catch (Exception e) {
                m16778a(C3784b.f9040f, C3784b.C3785a.f9063q, str, i, -1L, null);
                f9242a.m16800a(1100, -3, str, i, e);
                throw new C3882a(e.getMessage(), e, (byte) 0);
            }
        } finally {
            stringBuffer.append("checkSignature cost:");
            stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuffer.append(";");
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m16774c(File file, String str, int i, StringBuffer stringBuffer) {
        String[] strArr;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 4096);
                PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(file.getAbsolutePath(), 4096);
                List asList = Arrays.asList(packageInfo.requestedPermissions);
                if (packageArchiveInfo.requestedPermissions != null && packageArchiveInfo.requestedPermissions.length > 0) {
                    ArrayList arrayList = new ArrayList();
                    for (String str2 : packageArchiveInfo.requestedPermissions) {
                        if (!asList.contains(str2)) {
                            arrayList.add(str2);
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        ZeusLogger.m16788w("PluginInstaller", "The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)));
                        if (GlobalParam.getInstance().checkPermission()) {
                            throw new C3882a("The following permissions are declared in the plugin but not in the host: ".concat(String.valueOf(arrayList)), (byte) 0);
                        }
                    }
                }
            } catch (Exception e) {
                m16778a(C3784b.f9040f, C3784b.C3785a.f9064r, str, i, -1L, null);
                f9242a.m16800a(1100, -4, str, i, e);
                throw new C3882a("安装包权限校验失败", e, (byte) 0);
            }
        } finally {
            stringBuffer.append("checkPermissions cost:");
            stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuffer.append(";");
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m16776a(String str, String str2, int i, StringBuffer stringBuffer) {
        long currentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        int m16705a = new C3910c().m16705a(new File(str), false, sb);
        stringBuffer.append(m16705a == 100 ? "modifyRes" : "noModifyRes");
        stringBuffer.append(" cost:");
        stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
        stringBuffer.append(";");
        if (m16705a == 100 || m16705a == 200) {
            return;
        }
        String sb2 = sb.toString();
        m16778a(C3784b.f9040f, C3784b.C3785a.f9070x, str2, i, -1L, sb2);
        f9242a.m16800a(1100, -2, str2, i, null);
        throw new C3882a("modifyRes failed. result = " + m16705a + ", errorLog = " + sb2, (byte) 0);
    }

    /* renamed from: a */
    static /* synthetic */ void m16777a(String str, int i, Map map, StringBuffer stringBuffer) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                C3791b.m16952a(new File(C3792c.m16934b(str, i)), new File(C3792c.m16930d(str, i)), str, map);
            } catch (Exception e) {
                m16778a(C3784b.f9040f, C3784b.C3785a.f9066t, str, i, -1L, null);
                f9242a.m16800a(1100, -7, str, i, e);
                throw new C3882a("安装包动态库拷贝失败", e, (byte) 0);
            }
        } finally {
            stringBuffer.append("copySo cost:");
            stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuffer.append(";");
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m16772e(File file, String str, int i, StringBuffer stringBuffer) {
        String m16934b;
        ZipFile zipFile;
        File file2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (!C3950i.m16622b()) {
                    if (!C3950i.m16620d() && !C3950i.m16618f()) {
                        if (C3950i.m16619e()) {
                            String m16932c = C3792c.m16932c(str, i);
                            C3805b.m16904a(C3792c.m16934b(str, i), m16932c + File.separator + C3805b.m16905a(m16934b));
                        }
                    }
                    C3805b.m16906a(Zeus.getAppApplication()).edit().putInt(str, i).apply();
                    C3811f.m16894a();
                } else if (!file.exists() || str == null) {
                    throw new IOException("Could not check apk info " + file.getAbsolutePath());
                } else {
                    try {
                        zipFile = new ZipFile(file);
                    } catch (Throwable th) {
                        th = th;
                        zipFile = null;
                    }
                    try {
                        ArrayList arrayList = new ArrayList();
                        File file3 = new File(C3792c.m16925i(str, i));
                        C3815g.m16889a(file3);
                        String str2 = "classes";
                        int i2 = 1;
                        int i3 = 1;
                        while (true) {
                            StringBuilder sb = new StringBuilder("classes");
                            sb.append(i3 == i2 ? "" : Integer.valueOf(i3));
                            sb.append(".dex");
                            ZipEntry entry = zipFile.getEntry(sb.toString());
                            if (entry != null) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(str2);
                                sb2.append(i3 == i2 ? "" : Integer.valueOf(i3));
                                sb2.append(".dex");
                                C3815g.C3816a c3816a = new C3815g.C3816a(file3, sb2.toString());
                                int i4 = 0;
                                boolean z = false;
                                while (i4 < 3 && !z) {
                                    try {
                                        C3815g.m16886a(zipFile, entry, c3816a, str2);
                                        file2 = file3;
                                        z = true;
                                    } catch (IOException e) {
                                        file2 = file3;
                                        ZeusLogger.m16787w("Zeus/install_pangle", "Plugin-MultiDex Failed to extract entry from " + c3816a.getAbsolutePath(), e);
                                    }
                                    i4++;
                                    StringBuilder sb3 = new StringBuilder("Plugin-MultiDex Extraction ");
                                    sb3.append(z ? "succeeded" : "failed");
                                    sb3.append(" '");
                                    sb3.append(c3816a.getAbsolutePath());
                                    sb3.append("': length ");
                                    String str3 = str2;
                                    sb3.append(c3816a.length());
                                    ZeusLogger.m16792i("Zeus/install_pangle", sb3.toString());
                                    if (!z) {
                                        c3816a.delete();
                                        if (c3816a.exists()) {
                                            ZeusLogger.m16788w("Zeus/install_pangle", "Plugin-MultiDex Failed to delete corrupted secondary dex '" + c3816a.getPath() + "'");
                                        }
                                    }
                                    str2 = str3;
                                    file3 = file2;
                                }
                                File file4 = file3;
                                String str4 = str2;
                                if (!z) {
                                    throw new IOException("Could not create zip file " + c3816a.getAbsolutePath() + " for secondary dex (" + i3 + ")");
                                }
                                arrayList.add(c3816a);
                                i3++;
                                str2 = str4;
                                file3 = file4;
                                i2 = 1;
                            } else {
                                file.getName();
                                SharedPreferences.Editor edit = C3815g.m16891a().edit();
                                edit.putInt((str + "-" + i) + ".dex.number", arrayList.size());
                                edit.commit();
                                C3947g.m16630a(zipFile);
                                C3805b.m16906a(Zeus.getAppApplication()).edit().putInt(str, i).apply();
                                C3811f.m16894a();
                                break;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        C3947g.m16630a(zipFile);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                m16778a(C3784b.f9040f, C3784b.C3785a.f9068v, str, i, -1L, null);
                throw new C3882a("dexOpt失败", e2, (byte) 0);
            }
        } finally {
            stringBuffer.append("dexOpt cost:");
            stringBuffer.append(System.currentTimeMillis() - currentTimeMillis);
            stringBuffer.append(";");
        }
    }
}
