package com.networkbench.nbslens.nbsnativecrashlib;

import android.os.Build;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6802m {

    /* renamed from: A */
    public static final String f17617A = "backtrace";

    /* renamed from: B */
    public static final String f17618B = "build id";

    /* renamed from: C */
    public static final String f17619C = "stack";

    /* renamed from: D */
    public static final String f17620D = "memory near";

    /* renamed from: E */
    public static final String f17621E = "memory map";

    /* renamed from: F */
    public static final String f17622F = "logcat";

    /* renamed from: G */
    public static final String f17623G = "open files";

    /* renamed from: H */
    public static final String f17624H = "network info";

    /* renamed from: I */
    public static final String f17625I = "memory info";

    /* renamed from: J */
    public static final String f17626J = "other threads";

    /* renamed from: K */
    public static final String f17627K = "java stacktrace";

    /* renamed from: L */
    public static final String f17628L = "nbscrash error";

    /* renamed from: M */
    public static final String f17629M = "foreground";

    /* renamed from: N */
    public static final String f17630N = "nbscrash error debug";

    /* renamed from: O */
    private static final Pattern f17631O = Pattern.compile("^(.*):\\s'(.*?)'$");

    /* renamed from: P */
    private static final Pattern f17632P = Pattern.compile("^pid:\\s(.*),\\stid:\\s(.*),\\sname:\\s(.*)\\s+>>>\\s(.*)\\s<<<$");

    /* renamed from: Q */
    private static final Pattern f17633Q = Pattern.compile("^pid:\\s(.*)\\s+>>>\\s(.*)\\s<<<$");

    /* renamed from: R */
    private static final Pattern f17634R = Pattern.compile("^signal\\s(.*),\\scode\\s(.*),\\sfault\\saddr\\s(.*)$");

    /* renamed from: S */
    private static final Pattern f17635S = Pattern.compile("^(\\d{20})_(.*)__(.*)$");

    /* renamed from: T */
    private static final Set<String> f17636T = new HashSet(Arrays.asList("Tombstone maker", "Crash type", "Start time", "Crash time", "App ID", "App version", "Rooted", "API level", "OS version", "Kernel version", "ABI list", "Manufacturer", "Brand", "Model", "Build fingerprint", "ABI", "Abort message"));

    /* renamed from: U */
    private static final Set<String> f17637U = new HashSet(Arrays.asList("backtrace", "build id", "stack", "memory map", "logcat", "open files", "java stacktrace", "nbscrash error", "nbscrash error debug"));

    /* renamed from: V */
    private static final Set<String> f17638V = new HashSet(Arrays.asList("foreground"));

    /* renamed from: a */
    public static final String f17639a = "Tombstone maker";

    /* renamed from: b */
    public static final String f17640b = "Crash type";

    /* renamed from: c */
    public static final String f17641c = "Start time";

    /* renamed from: d */
    public static final String f17642d = "Crash time";

    /* renamed from: e */
    public static final String f17643e = "App ID";

    /* renamed from: f */
    public static final String f17644f = "App version";

    /* renamed from: g */
    public static final String f17645g = "Rooted";

    /* renamed from: h */
    public static final String f17646h = "API level";

    /* renamed from: i */
    public static final String f17647i = "OS version";

    /* renamed from: j */
    public static final String f17648j = "Kernel version";

    /* renamed from: k */
    public static final String f17649k = "ABI list";

    /* renamed from: l */
    public static final String f17650l = "Manufacturer";

    /* renamed from: m */
    public static final String f17651m = "Brand";

    /* renamed from: n */
    public static final String f17652n = "Model";

    /* renamed from: o */
    public static final String f17653o = "Build fingerprint";

    /* renamed from: p */
    public static final String f17654p = "ABI";

    /* renamed from: q */
    public static final String f17655q = "pid";

    /* renamed from: r */
    public static final String f17656r = "tid";

    /* renamed from: s */
    public static final String f17657s = "pname";

    /* renamed from: t */
    public static final String f17658t = "tname";

    /* renamed from: u */
    public static final String f17659u = "signal";

    /* renamed from: v */
    public static final String f17660v = "code";

    /* renamed from: w */
    public static final String f17661w = "fault addr";

    /* renamed from: x */
    public static final String f17662x = "nbs err msg";

    /* renamed from: y */
    public static final String f17663y = "Abort message";

    /* renamed from: z */
    public static final String f17664z = "registers";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.nbslens.nbsnativecrashlib.m$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6804a {
        UNKNOWN,
        HEAD,
        SECTION
    }

    private C6802m() {
    }

    /* renamed from: a */
    public static Map<String, String> m8396a(File file) throws IOException {
        return m8394a(file.getAbsolutePath(), (String) null);
    }

    /* renamed from: a */
    public static Map<String, String> m8395a(String str) throws IOException {
        return m8394a(str, (String) null);
    }

    /* renamed from: a */
    public static Map<String, String> m8394a(String str, String str2) throws IOException {
        HashMap hashMap = new HashMap();
        if (str != null) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
            m8392a((Map<String, String>) hashMap, bufferedReader, true);
            bufferedReader.close();
        }
        if (str2 != null) {
            BufferedReader bufferedReader2 = new BufferedReader(new StringReader(str2));
            m8392a((Map<String, String>) hashMap, bufferedReader2, false);
            bufferedReader2.close();
        }
        m8391a(hashMap, str);
        if (TextUtils.isEmpty((String) hashMap.get("App version"))) {
            String m8521b = NBSNativeCrash.m8521b();
            if (TextUtils.isEmpty(m8521b)) {
                m8521b = "unknown";
            }
            hashMap.put("App version", m8521b);
        }
        m8393a(hashMap);
        return hashMap;
    }

    /* renamed from: a */
    private static void m8391a(Map<String, String> map, String str) {
        String substring;
        if (str == null) {
            return;
        }
        if (TextUtils.isEmpty(map.get("Crash time"))) {
            map.put("Crash time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).format(new Date(new File(str).lastModified())));
        }
        String str2 = map.get("Start time");
        String str3 = map.get("App version");
        String str4 = map.get("pname");
        String str5 = map.get("Crash type");
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
            String substring2 = str.substring(str.lastIndexOf(47) + 1);
            if (!substring2.isEmpty() && substring2.startsWith("tombstone_")) {
                String substring3 = substring2.substring(10);
                if (substring3.endsWith(".java.nbscrash")) {
                    if (TextUtils.isEmpty(str5)) {
                        map.put("Crash type", "java");
                    }
                    substring = substring3.substring(0, substring3.length() - 14);
                } else if (substring3.endsWith(".native.nbscrash")) {
                    if (TextUtils.isEmpty(str5)) {
                        map.put("Crash type", "native");
                    }
                    substring = substring3.substring(0, substring3.length() - 16);
                } else if (!substring3.endsWith(".anr.nbscrash")) {
                    return;
                } else {
                    if (TextUtils.isEmpty(str5)) {
                        map.put("Crash type", "anr");
                    }
                    substring = substring3.substring(0, substring3.length() - 13);
                }
                if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                    Matcher matcher = f17635S.matcher(substring);
                    if (matcher.find() && matcher.groupCount() == 3) {
                        if (TextUtils.isEmpty(str2)) {
                            map.put("Start time", new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US).format(new Date(Long.parseLong(matcher.group(1), 10) / 1000)));
                        }
                        if (TextUtils.isEmpty(str3)) {
                            map.put("App version", matcher.group(2));
                        }
                        if (TextUtils.isEmpty(str4)) {
                            map.put("pname", matcher.group(3));
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static void m8393a(Map<String, String> map) {
        if (TextUtils.isEmpty(map.get("App ID"))) {
            map.put("App ID", NBSNativeCrash.m8522a());
        }
        if (TextUtils.isEmpty(map.get("Tombstone maker"))) {
            map.put("Tombstone maker", "NBSNativeCrash 2.0.2.1");
        }
        if (TextUtils.isEmpty(map.get("Rooted"))) {
            map.put("Rooted", C6805n.m8388a() ? "Yes" : "No");
        }
        if (TextUtils.isEmpty(map.get("API level"))) {
            map.put("API level", String.valueOf(Build.VERSION.SDK_INT));
        }
        if (TextUtils.isEmpty(map.get("OS version"))) {
            map.put("OS version", Build.VERSION.RELEASE);
        }
        if (TextUtils.isEmpty(map.get("Build fingerprint"))) {
            map.put("Model", Build.FINGERPRINT);
        }
        if (TextUtils.isEmpty(map.get("Manufacturer"))) {
            map.put("Manufacturer", Build.MANUFACTURER);
        }
        if (TextUtils.isEmpty(map.get("Brand"))) {
            map.put("Brand", Build.BRAND);
        }
        if (TextUtils.isEmpty(map.get("Model"))) {
            map.put("Model", Build.MODEL);
        }
        if (TextUtils.isEmpty(map.get("ABI list"))) {
            map.put("ABI list", C6805n.m8379b());
        }
    }

    /* renamed from: a */
    private static String m8397a(BufferedReader bufferedReader) throws IOException {
        try {
            bufferedReader.mark(2);
            for (int i = 0; i < 2; i++) {
                try {
                    int read = bufferedReader.read();
                    if (read == -1) {
                        bufferedReader.reset();
                        return null;
                    } else if (read > 0) {
                        bufferedReader.reset();
                        return bufferedReader.readLine();
                    }
                } catch (Exception unused) {
                    bufferedReader.reset();
                    return bufferedReader.readLine();
                }
            }
            bufferedReader.reset();
            return null;
        } catch (Exception unused2) {
            return bufferedReader.readLine();
        }
    }

    /* renamed from: a */
    private static void m8392a(Map<String, String> map, BufferedReader bufferedReader, boolean z) throws IOException {
        StringBuilder sb = new StringBuilder();
        String str = "";
        EnumC6804a enumC6804a = EnumC6804a.UNKNOWN;
        String m8397a = z ? m8397a(bufferedReader) : bufferedReader.readLine();
        int i = 0;
        int i2 = m8397a == null ? 1 : 0;
        boolean z2 = false;
        String str2 = null;
        boolean z3 = false;
        while (i2 == 0) {
            String m8397a2 = z ? m8397a(bufferedReader) : bufferedReader.readLine();
            int i3 = m8397a2 == null ? 1 : i;
            switch (enumC6804a) {
                case UNKNOWN:
                    if (m8397a.equals("*** *** *** *** *** *** *** *** *** *** *** *** *** *** *** ***")) {
                        enumC6804a = EnumC6804a.HEAD;
                        i = 0;
                        break;
                    } else if (m8397a.equals("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---")) {
                        EnumC6804a enumC6804a2 = EnumC6804a.SECTION;
                        sb.append(m8397a);
                        sb.append('\n');
                        str2 = "other threads";
                        z3 = false;
                        z2 = false;
                        enumC6804a = enumC6804a2;
                        str = "+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++";
                        i = 0;
                        break;
                    } else if (m8397a.length() > 1 && m8397a.endsWith(":")) {
                        EnumC6804a enumC6804a3 = EnumC6804a.SECTION;
                        i = 0;
                        String substring = m8397a.substring(0, m8397a.length() - 1);
                        if (f17637U.contains(substring)) {
                            boolean z4 = substring.equals("backtrace") || substring.equals("build id") || substring.equals("stack") || substring.equals("memory map") || substring.equals("open files") || substring.equals("java stacktrace") || substring.equals("nbscrash error debug");
                            z2 = substring.equals("nbscrash error");
                            str2 = substring;
                            enumC6804a = enumC6804a3;
                            str = "";
                            z3 = z4;
                            break;
                        } else if (!substring.equals("memory info")) {
                            if (!substring.startsWith("memory near ")) {
                                str2 = substring;
                                z2 = false;
                                enumC6804a = enumC6804a3;
                                str = "";
                                z3 = false;
                                break;
                            } else {
                                sb.append(m8397a);
                                sb.append('\n');
                                str2 = "memory near";
                                z2 = true;
                                enumC6804a = enumC6804a3;
                                str = "";
                                z3 = false;
                                break;
                            }
                        } else {
                            str2 = substring;
                            z2 = true;
                            enumC6804a = enumC6804a3;
                            str = "";
                            z3 = false;
                            break;
                        }
                    } else {
                        i = 0;
                        break;
                    }
                    break;
                case HEAD:
                    if (m8397a.startsWith("pid: ")) {
                        Matcher matcher = f17632P.matcher(m8397a);
                        if (matcher.find() && matcher.groupCount() == 4) {
                            m8390a(map, "pid", matcher.group(1));
                            m8390a(map, "tid", matcher.group(2));
                            m8390a(map, "tname", matcher.group(3));
                            m8390a(map, "pname", matcher.group(4));
                        } else {
                            Matcher matcher2 = f17633Q.matcher(m8397a);
                            if (matcher2.find() && matcher2.groupCount() == 2) {
                                m8390a(map, "pid", matcher2.group(1));
                                m8390a(map, "pname", matcher2.group(2));
                            }
                        }
                    } else if (m8397a.startsWith("signal ")) {
                        Matcher matcher3 = f17634R.matcher(m8397a);
                        if (matcher3.find() && matcher3.groupCount() == 3) {
                            m8390a(map, "signal", matcher3.group(1));
                            m8390a(map, "code", matcher3.group(2));
                            m8390a(map, "fault addr", matcher3.group(3));
                        }
                        m8390a(map, "nbs err msg", m8397a);
                    } else {
                        Matcher matcher4 = f17631O.matcher(m8397a);
                        if (matcher4.find() && matcher4.groupCount() == 2 && f17636T.contains(matcher4.group(1))) {
                            m8390a(map, matcher4.group(1), matcher4.group(2));
                        }
                    }
                    if (m8397a2 != null && (m8397a2.startsWith("    r0 ") || m8397a2.startsWith("    x0 ") || m8397a2.startsWith("    eax ") || m8397a2.startsWith("    rax "))) {
                        enumC6804a = EnumC6804a.SECTION;
                        str2 = "registers";
                        str = "";
                        z3 = true;
                        z2 = false;
                    }
                    if (m8397a2 != null && !m8397a2.isEmpty()) {
                        i = 0;
                        break;
                    } else {
                        enumC6804a = EnumC6804a.UNKNOWN;
                        i = 0;
                        break;
                    }
                    break;
                case SECTION:
                    if (m8397a.equals(str) || i3 != 0) {
                        if (f17638V.contains(str2) && sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        m8389a(map, str2, sb.toString(), z2);
                        sb.setLength(i);
                        enumC6804a = EnumC6804a.UNKNOWN;
                        break;
                    } else {
                        if (z3) {
                            if (str2.equals("java stacktrace") && m8397a.startsWith(" ")) {
                                m8397a = m8397a.trim();
                            } else if (m8397a.startsWith("    ")) {
                                m8397a = m8397a.substring(4);
                            }
                        }
                        sb.append(m8397a);
                        sb.append('\n');
                        break;
                    }
            }
            m8397a = m8397a2;
            i2 = i3;
        }
    }

    /* renamed from: a */
    private static void m8390a(Map<String, String> map, String str, String str2) {
        m8389a(map, str, str2, false);
    }

    /* renamed from: a */
    private static void m8389a(Map<String, String> map, String str, String str2, boolean z) {
        if (str == null || str.isEmpty() || str2 == null) {
            return;
        }
        String str3 = map.get(str);
        if (!z) {
            if (str3 == null || (str3.isEmpty() && !str2.isEmpty())) {
                map.put(str, str2);
                return;
            }
            return;
        }
        if (str3 != null) {
            str2 = str3 + str2;
        }
        map.put(str, str2);
    }
}
