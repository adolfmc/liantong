package com.bytedance.pangle.p176d;

import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.C3945e;
import com.bytedance.pangle.util.C3947g;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.FieldUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.d.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3791b {

    /* renamed from: a */
    private static String f9078a;

    /* renamed from: b */
    private static Map<String, Integer> f9079b;

    static {
        HashMap hashMap = new HashMap();
        f9079b = hashMap;
        hashMap.put("arm64-v8a", 64);
        f9079b.put("armeabi-v7a", 32);
        f9079b.put("armeabi", 32);
        f9079b.put("x86_64", 64);
        f9079b.put("x86", 32);
        f9079b.put("mips64", 64);
        f9079b.put("mips", 32);
        f9078a = m16942c();
    }

    /* renamed from: a */
    private static Map<String, List<ZipEntry>> m16949a(ZipFile zipFile) {
        String[] split;
        HashMap hashMap = new HashMap();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        Pattern compile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            if (!nextElement.isDirectory() && !nextElement.getName().contains("../") && nextElement.getName().startsWith("lib/") && compile.matcher(nextElement.getName()).matches() && (split = nextElement.getName().split(File.separator)) != null && split.length >= 2) {
                String str = split[split.length - 2];
                if (f9079b.containsKey(str)) {
                    if (hashMap.get(str) == null) {
                        hashMap.put(str, new LinkedList());
                    }
                    ((List) hashMap.get(str)).add(nextElement);
                }
            }
        }
        ZeusLogger.m16792i("Zeus/so_pangle", "NativeLibHelper getAllSoZipEntries, zipFile=" + zipFile.getName() + ", soEntries=" + hashMap.toString());
        return hashMap;
    }

    /* renamed from: a */
    private static void m16950a(Map<String, List<ZipEntry>> map, String str, List<ZipEntry> list, Set<String> set) {
        List<ZipEntry> list2 = map.get(str);
        if (list2 == null || list2.size() == 0) {
            return;
        }
        for (ZipEntry zipEntry : list2) {
            String substring = zipEntry.getName().substring(zipEntry.getName().lastIndexOf(File.separator) + 1);
            if (!set.contains(substring)) {
                list.add(zipEntry);
                set.add(substring);
            }
        }
    }

    /* renamed from: a */
    private static void m16948a(ZipFile zipFile, ZipEntry zipEntry, File file) {
        String name = zipEntry.getName();
        if (name.contains("..")) {
            return;
        }
        File file2 = new File(file, name.substring(name.lastIndexOf(File.separator) + 1));
        int i = 0;
        boolean z = false;
        do {
            if (file2.exists()) {
                file2.delete();
            }
            try {
                ZeusLogger.m16792i("Zeus/install_pangle", "NativeLibHelper copySoZipEntry, soZipEntry=" + zipEntry + ", targetSoFile=" + file2);
                C3947g.m16633a(zipFile.getInputStream(zipEntry), new FileOutputStream(file2));
                z = true;
                continue;
            } catch (IOException e) {
                if (i >= 3) {
                    throw e;
                }
                i++;
                continue;
            }
        } while (!z);
    }

    /* renamed from: a */
    public static String m16954a() {
        String str = f9078a;
        if (str != null) {
            return str;
        }
        String m16942c = m16942c();
        f9078a = m16942c;
        return m16942c;
    }

    /* renamed from: b */
    public static int m16945b() {
        return f9079b.get(m16954a()).intValue();
    }

    /* renamed from: c */
    private static String m16942c() {
        JSONObject m16941d = m16941d();
        String m16947a = m16947a(m16941d);
        return m16947a == null ? m16943b(m16941d) : m16947a;
    }

    /* renamed from: a */
    private static String m16947a(JSONObject jSONObject) {
        if (C3950i.m16623a()) {
            try {
                String str = (String) FieldUtils.readField(Zeus.getAppApplication().getApplicationInfo(), "primaryCpuAbi");
                ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto, primaryCpuAbi=".concat(String.valueOf(str)));
                m16946a(jSONObject, "primaryCpuAbi", str);
                if (str != null) {
                    int i = 0;
                    if (Build.VERSION.SDK_INT >= 23) {
                        try {
                            i = Process.is64Bit() ? 64 : 32;
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto, processMode=".concat(String.valueOf(i)));
                        } catch (Exception unused) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto, processMode exception default=".concat(String.valueOf(i)));
                        }
                    } else {
                        ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto, processMode default=0");
                    }
                    m16946a(jSONObject, "processMode", String.valueOf(i));
                    if (i != 0) {
                        if (f9079b.get(str).intValue() == i) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto2, sHostAbi=".concat(String.valueOf(str)));
                            return str;
                        }
                        return null;
                    }
                    ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto1, sHostAbi=".concat(String.valueOf(str)));
                    return str;
                }
                return null;
            } catch (Exception e) {
                ZeusLogger.errReport("Zeus/so_pangle", "NativeLibHelper inferHostAbiAuto failed!", e);
                m16946a(jSONObject, "autoError", "1");
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static String m16943b(JSONObject jSONObject) {
        HashSet hashSet;
        String[] strArr;
        try {
            ZipFile zipFile = new ZipFile(new File(Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 0).applicationInfo.sourceDir));
            hashSet = new HashSet(m16949a(zipFile).keySet());
            try {
                zipFile.close();
            } catch (IOException unused) {
                ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiManual, close sourceApkZipFile error!");
            }
            strArr = C3950i.m16623a() ? Build.SUPPORTED_ABIS : new String[]{Build.CPU_ABI, Build.CPU_ABI2};
        } catch (Throwable th) {
            ZeusLogger.errReport("Zeus/so_pangle", "NativeLibHelper inferHostAbiManual failed!", th);
            m16946a(jSONObject, "manualError", "1");
        }
        if (hashSet.isEmpty()) {
            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiManual, host source apk .so is empty, use supportedABIs[0]=" + strArr[0]);
            m16946a(jSONObject, "supportedABI0", strArr[0]);
            return strArr[0];
        }
        for (String str : strArr) {
            if (hashSet.contains(str)) {
                ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper inferHostAbiManual, match cpuAbi=".concat(String.valueOf(str)));
                m16946a(jSONObject, "matchCpuAbi", str);
                return str;
            }
        }
        if (C3950i.m16623a()) {
            m16946a(jSONObject, "defaultABI0", Build.SUPPORTED_ABIS[0]);
            return Build.SUPPORTED_ABIS[0];
        }
        m16946a(jSONObject, "defaultABI", Build.CPU_ABI);
        return Build.CPU_ABI;
    }

    /* renamed from: a */
    public static C3945e<Boolean, Map<String, List<ZipEntry>>> m16953a(File file) {
        ZipFile zipFile;
        boolean m16951a;
        HashMap hashMap = new HashMap();
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
                try {
                    hashMap.putAll(m16949a(zipFile));
                    if (hashMap.isEmpty()) {
                        m16951a = true;
                        ZeusLogger.m16792i("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi [true] soEntries empty, ".concat(String.valueOf(file)));
                    } else {
                        m16951a = m16951a(hashMap, f9078a);
                        if (m16951a) {
                            ZeusLogger.m16792i("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi [" + m16951a + "], " + file);
                        } else {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi [" + m16951a + "], " + file);
                        }
                    }
                    C3945e<Boolean, Map<String, List<ZipEntry>>> c3945e = new C3945e<>(Boolean.valueOf(m16951a), hashMap);
                    try {
                        zipFile.close();
                    } catch (IOException unused) {
                        ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
                    }
                    return c3945e;
                } catch (IOException e) {
                    e = e;
                    zipFile2 = zipFile;
                    ZeusLogger.errReport("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi, get sourceApk ZipFile failed!", e);
                    C3945e<Boolean, Map<String, List<ZipEntry>>> c3945e2 = new C3945e<>(Boolean.FALSE, hashMap);
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (IOException unused2) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
                        }
                    }
                    return c3945e2;
                } catch (Throwable th) {
                    th = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused3) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper isPluginApkMatchHostAbi, close sourceApkZipFile error!");
                        }
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = null;
        }
    }

    /* renamed from: a */
    private static boolean m16951a(Map<String, List<ZipEntry>> map, String str) {
        if (TextUtils.equals(str, "armeabi") || TextUtils.equals(str, "armeabi-v7a")) {
            return map.containsKey("armeabi") || map.containsKey("armeabi-v7a");
        }
        return map.containsKey(str);
    }

    /* renamed from: b */
    public static boolean m16944b(File file) {
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(file);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    Pattern compile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
                    while (entries.hasMoreElements()) {
                        ZipEntry nextElement = entries.nextElement();
                        if (!nextElement.isDirectory() && compile.matcher(nextElement.getName()).matches()) {
                            try {
                                zipFile.close();
                                return true;
                            } catch (IOException unused) {
                                ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                                return true;
                            }
                        }
                    }
                    try {
                        zipFile.close();
                    } catch (IOException unused2) {
                        ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                    }
                    return false;
                } catch (IOException e) {
                    e = e;
                    zipFile2 = zipFile;
                    ZeusLogger.errReport("Zeus/so_pangle", "NativeLibHelper hasNativeLib, get sourceApk ZipFile failed!", e);
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                        } catch (IOException unused3) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException unused4) {
                            ZeusLogger.m16788w("Zeus/so_pangle", "NativeLibHelper hasNativeLib, close sourceApkZipFile error!");
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zipFile = zipFile2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    /* renamed from: a */
    private static void m16946a(JSONObject jSONObject, String str, String str2) {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    private static JSONObject m16941d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("primaryCpuAbi", "0");
            jSONObject.put("processMode", "0");
            jSONObject.put("supportedABI0", "0");
            jSONObject.put("matchCpuAbi", "0");
            jSONObject.put("defaultABI0", "0");
            jSONObject.put("defaultABI", "0");
            jSONObject.put("autoError", "0");
            jSONObject.put("manualError", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX WARN: Code restructure failed: missing block: B:63:0x0155, code lost:
        if (r0.isEmpty() == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x015c, code lost:
        if (r13.exists() != false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x015e, code lost:
        r13.mkdirs();
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0161, code lost:
        r12 = r0.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0169, code lost:
        if (r12.hasNext() == false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x016b, code lost:
        m16948a(r1, (java.util.zip.ZipEntry) r12.next(), r13);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m16952a(java.io.File r12, java.io.File r13, java.lang.String r14, java.util.Map<java.lang.String, java.util.List<java.util.zip.ZipEntry>> r15) {
        /*
            Method dump skipped, instructions count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p176d.C3791b.m16952a(java.io.File, java.io.File, java.lang.String, java.util.Map):void");
    }
}
