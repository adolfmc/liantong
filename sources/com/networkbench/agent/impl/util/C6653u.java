package com.networkbench.agent.impl.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.TextView;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.C6317b;
import com.networkbench.agent.impl.crash.C6325e;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p264j.RunnableC6488d;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.squareup.okhttp.internal.Version;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.Deflater;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.u */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6653u {

    /* renamed from: a */
    private static final Random f17248a = new Random();

    /* renamed from: b */
    private static InterfaceC6393e f17249b = C6394f.m10150a();

    /* renamed from: c */
    private static final String f17250c = "com.networkbench.agent.impl.v2_";

    /* renamed from: c */
    public static boolean m8721c(int i) {
        return i <= 512;
    }

    /* renamed from: d */
    public static int m8713d(int i) {
        if (i > 0) {
            return i;
        }
        return 0;
    }

    /* renamed from: a */
    public static String m8746a(InputStream inputStream) throws IOException {
        char[] cArr = new char[8192];
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read >= 0) {
                sb.append(cArr, 0, read);
            } else {
                return sb.toString();
            }
        }
    }

    /* renamed from: a */
    public static String m8744a(String str) {
        if (str == null) {
            return null;
        }
        try {
            URL url = new URL(str);
            StringBuffer m8737a = m8737a(url, str);
            m8737a.append(url.getPath());
            return m8737a.toString();
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    /* renamed from: a */
    private static StringBuffer m8737a(URL url, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(url.getProtocol());
        stringBuffer.append("://");
        if (str.startsWith("file") && !url.getHost().startsWith("localfile")) {
            stringBuffer.append("localfile");
        }
        stringBuffer.append(url.getHost());
        if (url.getPort() != -1) {
            stringBuffer.append(":");
            stringBuffer.append(url.getPort());
        }
        return stringBuffer;
    }

    /* renamed from: b */
    public static String m8726b(String str) {
        if (str == null) {
            return null;
        }
        try {
            URL url = new URL(str);
            StringBuffer m8737a = m8737a(url, str);
            m8737a.append(url.getFile());
            return m8737a.toString();
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    /* renamed from: a */
    public static Random m8757a() {
        return f17248a;
    }

    /* renamed from: b */
    public static String m8731b() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace != null) {
            try {
                return stackTrace[2].getClassName() + "#" + stackTrace[2].getMethodName() + "->";
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    /* renamed from: a */
    public static String m8753a(Context context) {
        if (context == null) {
            return null;
        }
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
    }

    /* renamed from: a */
    public static String[] m8740a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        if (str3.startsWith(str)) {
            str3 = str3.replace(str, "");
        }
        return str3.split(str2);
    }

    /* renamed from: c */
    public static JsonArray m8722c() {
        if (C6638h.m8963w().m9043ac() < 0) {
            return new JsonArray();
        }
        try {
            if (Harvest.getInstance().actionAndInteractions != null) {
                return Harvest.getInstance().actionAndInteractions.asJsonArray();
            }
        } catch (Exception e) {
            f17249b.mo10121a("getTrailInterActionsAndActions occur an error", e);
        }
        return new JsonArray();
    }

    /* renamed from: a */
    public static SharedPreferences m8752a(Context context, String str) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(str, 0);
    }

    /* renamed from: c */
    public static String m8718c(String str) {
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            Pattern compile = Pattern.compile("-{5}\\send\\s\\d+\\s-{5}");
            int i = 1;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                } else if (i > 10) {
                    bufferedReader.close();
                    return null;
                } else if (m8734a(compile, readLine)) {
                    int m8739a = m8739a(sb);
                    f17249b.mo10120a("NBSAgent", "包名匹配完成(-1为不匹配,大于0则为匹配成功)" + m8739a);
                    if (m8739a != -1) {
                        break;
                    }
                    sb.delete(0, sb.length());
                    i++;
                } else {
                    sb.append(readLine);
                    sb.append('\n');
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static int m8739a(StringBuilder sb) {
        return sb.indexOf(C6638h.m8963w().m9076K().getPackageName());
    }

    /* renamed from: a */
    public static boolean m8734a(Pattern pattern, String str) throws IOException {
        return (str == null || pattern == null || !pattern.matcher(str).matches()) ? false : true;
    }

    /* renamed from: a */
    public static boolean m8741a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equalsIgnoreCase(str2)) {
            return false;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int length = split.length;
        int length2 = split2.length;
        if (length == 0 || length2 == 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (i >= length2) {
                return true;
            }
            try {
                if (Integer.parseInt(split[i]) > Integer.parseInt(split2[i])) {
                    return true;
                }
                if (Integer.parseInt(split[i]) != Integer.parseInt(split2[i])) {
                    return false;
                }
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static int m8742a(String str, long j, long j2) {
        if (j2 > 0 && str.startsWith("https:")) {
            return (int) (j - j2);
        }
        return -1;
    }

    /* renamed from: d */
    public static Map<String, String> m8712d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        try {
            String[] split = str.split("\n");
            if (split != null && split.length > 0) {
                for (String str2 : split) {
                    String[] split2 = str2.split(":", 2);
                    if (split2 != null && split2.length == 2) {
                        hashMap.put(split2[0].trim(), split2[1].trim());
                    }
                }
            }
        } catch (Exception e) {
            C6638h.f17124y.mo10115e("Util get header error:" + e.getMessage());
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m8732a(Closeable... closeableArr) throws IOException {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                closeable.close();
            }
        }
    }

    /* renamed from: b */
    public static boolean m8729b(Context context) {
        if (context == null) {
            return false;
        }
        String str = ConfigurationName.processName;
        return str == null || str.equals(context.getPackageName());
    }

    /* renamed from: a */
    public static String m8756a(int i) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
        } catch (Throwable th) {
            th = th;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (Exception e) {
                f17249b.mo10115e("error happend in getProcessName " + e.getMessage());
            }
            return readLine;
        } catch (Throwable th2) {
            th = th2;
            try {
                f17249b.mo10115e("error happend in getProcessName " + th.getMessage());
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e2) {
                        f17249b.mo10115e("error happend in getProcessName " + e2.getMessage());
                    }
                }
                return null;
            } catch (Throwable th3) {
                BufferedReader bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Exception e3) {
                        f17249b.mo10115e("error happend in getProcessName " + e3.getMessage());
                    }
                }
                throw th3;
            }
        }
    }

    /* renamed from: a */
    public static String m8745a(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getSimpleName();
    }

    /* renamed from: e */
    public static int m8706e(String str) {
        if (TextUtils.isEmpty(str) || !C6648q.f17233c.containsKey(str)) {
            return 200;
        }
        int intValue = C6648q.f17233c.get(str).intValue();
        C6648q.f17233c.remove(str);
        return intValue;
    }

    /* renamed from: a */
    public static String m8751a(Context context, boolean z) {
        if (context == null) {
            return "";
        }
        try {
            JsonObject jsonObject = new JsonObject();
            System.currentTimeMillis();
            jsonObject.addProperty("pt", C6317b.m10446d());
            jsonObject.addProperty("arch", C6317b.m10452a());
            jsonObject.addProperty("pu", Long.valueOf(RunnableC6488d.m9808a()));
            jsonObject.addProperty("mem", Long.valueOf(C6317b.m10443f(context)));
            jsonObject.addProperty("sp", Long.valueOf(C6317b.m10450b()));
            long m10448c = C6317b.m10448c();
            if (m10448c != -1) {
                jsonObject.addProperty("sd", Long.valueOf(m10448c));
            }
            jsonObject.addProperty("pwr", Integer.valueOf(C6317b.m10447c(context)));
            jsonObject.addProperty("jb", Integer.valueOf(C6317b.m10441h(context)));
            long m10445d = C6317b.m10445d(context);
            if (m10445d != -1) {
                jsonObject.addProperty("gps", Long.valueOf(m10445d));
            }
            try {
                if (C6317b.m10444e(context) != -1) {
                    jsonObject.addProperty("bt", Integer.valueOf(C6317b.m10444e(context)));
                }
            } catch (Throwable unused) {
            }
            jsonObject.addProperty("orui", Integer.valueOf(C6317b.m10451a(context)));
            JsonObject m10398a = C6325e.m10398a();
            if (m10398a != null) {
                jsonObject.add("cust", m10398a);
            }
            if (z) {
                jsonObject.add("tr", m8722c());
            }
            System.currentTimeMillis();
            return jsonObject.toString();
        } catch (Exception e) {
            f17249b.mo10121a("getADDITIONAL_INFO error :", e);
            return "";
        }
    }

    /* renamed from: f */
    public static byte[] m8702f(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m8755a(int i, int i2) {
        byte[] bArr = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3] = (byte) ((i >> (((i2 - 1) - i3) * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: a */
    public static int m8733a(byte... bArr) {
        if (bArr == null) {
            throw new NumberFormatException("byte[] convert to int: bytes is illegal!");
        }
        int length = bArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            i |= (bArr[i2] & 255) << (((length - 1) - i2) * 8);
        }
        return i;
    }

    /* renamed from: b */
    public static String m8723b(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* renamed from: g */
    public static byte[] m8699g(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.length() % 2 == 1) {
            throw new IllegalArgumentException("invalid hex length: " + str);
        }
        byte[] bArr = new byte[str.length() / 2];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            int i3 = i + 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i, i3), 16);
            i2++;
            i = i3;
        }
        return bArr;
    }

    /* renamed from: a */
    public static byte[] m8743a(String str, int i) {
        String[] split = str.split("\\.");
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 >= split.length) {
                bArr[i2] = 0;
            } else {
                bArr[i2] = m8755a(Integer.parseInt(split[i2]), 1)[0];
            }
        }
        return bArr;
    }

    /* renamed from: h */
    public static boolean m8696h(String str) {
        if (TextUtils.isEmpty(str) || str.length() > 64) {
            return false;
        }
        return Pattern.compile("^[a-zA-Z][a-z0-9A-Z_]*$").matcher(str).matches();
    }

    /* renamed from: b */
    public static <T> boolean m8727b(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            return ((String) obj).length() <= 255;
        } else if (obj instanceof List) {
            List list = (List) obj;
            if (list.size() > 100) {
                return false;
            }
            return list.size() <= 0 || (list.get(0) instanceof String);
        } else {
            return m8719c(obj);
        }
    }

    /* renamed from: c */
    public static boolean m8719c(Object obj) {
        return obj instanceof Number;
    }

    /* renamed from: a */
    public static JsonObject m8735a(Map<String, Object> map) {
        if (map == null || map.size() < 1) {
            return new JsonObject();
        }
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                jsonObject.add(key, null);
            } else if (value instanceof Number) {
                jsonObject.add(key, new JsonPrimitive(value));
            } else if (value instanceof List) {
                List list = (List) value;
                int size = list.size();
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < size; i++) {
                    jsonArray.add(new JsonPrimitive((String) list.get(i)));
                }
                jsonObject.add(key, jsonArray);
            } else {
                jsonObject.add(key, new JsonPrimitive(value.toString()));
            }
        }
        return jsonObject;
    }

    /* renamed from: b */
    public static Map<String, String> m8724b(Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        if (map == null || map.size() < 1) {
            return hashMap;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                hashMap.put(entry.getKey(), (String) value);
            } else if (value instanceof List) {
                StringBuilder sb = new StringBuilder();
                sb.append('[');
                for (Object obj : (List) value) {
                    sb.append(obj.toString());
                    sb.append(',');
                }
                hashMap.put(entry.getKey(), sb.substring(0, sb.length() - 1) + "]");
            } else {
                hashMap.put(entry.getKey(), value.toString());
            }
        }
        return hashMap;
    }

    /* renamed from: i */
    public static String m8694i(String str) {
        if (str == null) {
            return "";
        }
        try {
            return str.split(";")[0];
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: d */
    public static Application m8714d() {
        Application application = null;
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            if (Build.VERSION.SDK_INT >= 9) {
                application = (Application) cls.getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            } else {
                application = (Application) cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
        return application;
    }

    /* renamed from: a */
    public static String m8750a(View view) {
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(view);
        for (ViewParent parent = view.getParent(); parent != null && (parent instanceof ViewGroup); parent = parent.getParent()) {
            arrayList.add((ViewGroup) parent);
        }
        int size = arrayList.size() - 1;
        View view2 = (View) arrayList.get(size);
        String simpleName = view2.getParent() instanceof View ? "" : view2.getClass().getSimpleName();
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            for (int i = size - 1; i >= 0; i--) {
                View view3 = (View) arrayList.get(i);
                String simpleName2 = view3.getClass().getSimpleName();
                int indexOfChild = viewGroup.indexOfChild(view3);
                if (viewGroup instanceof AdapterView) {
                    indexOfChild += ((AdapterView) viewGroup).getFirstVisiblePosition();
                }
                if (!(view3 instanceof ViewGroup)) {
                    break;
                }
                simpleName = simpleName + "/" + simpleName2 + "[" + indexOfChild + "]";
                viewGroup = (ViewGroup) view3;
            }
        }
        return simpleName;
    }

    /* renamed from: b */
    private static String m8728b(View view) {
        String str = "";
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    m8728b(childAt);
                }
                if (childAt instanceof TextView) {
                    str = str + ((TextView) childAt).getText().toString();
                }
            }
        }
        return str;
    }

    /* renamed from: j */
    public static String m8692j(String str) {
        return "com.networkbench.agent.impl.v2_" + str;
    }

    /* renamed from: e */
    public static boolean m8708e() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: c */
    public static byte[] m8715c(byte[] bArr) {
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[8192];
        while (!deflater.finished()) {
            int deflate = deflater.deflate(bArr2);
            if (deflate <= 0) {
                f17249b.mo10116d("HTTP request contains an incomplete payload");
            }
            byteArrayOutputStream.write(bArr2, 0, deflate);
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: f */
    public static int m8703f() {
        return TextUtils.isEmpty(C6638h.m8963w().m9075L()) ? 0 : 1;
    }

    /* renamed from: b */
    public static void m8730b(int i) {
        try {
            if (NBSAgent.getImpl() == null || NBSAgent.getImpl().m9147p() == null) {
                return;
            }
            NBSAgent.getImpl().m9147p().m8861a(i);
        } catch (Exception e) {
            f17249b.mo10121a("crash before init, set feature the min value", e);
        }
    }

    /* renamed from: g */
    public static int m8700g() {
        try {
            return Harvest.getInstance().getConfiguration().getAnrThreshold();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f17249b;
            interfaceC6393e.mo10116d("get anr threshold failed e:" + e.getMessage());
            return 5000;
        }
    }

    /* renamed from: h */
    public static synchronized String m8697h() {
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        synchronized (C6653u.class) {
            try {
                Context m9076K = C6638h.m8963w().m9076K();
                try {
                    PackageInfo packageInfo = m9076K.getPackageManager().getPackageInfo(m9076K.getPackageName(), 64);
                    if (packageInfo == null) {
                        return "";
                    }
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
                    String str = null;
                    try {
                        certificateFactory = CertificateFactory.getInstance("X509");
                    } catch (CertificateException e) {
                        e.printStackTrace();
                        certificateFactory = null;
                    }
                    try {
                        x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
                    } catch (CertificateException e2) {
                        e2.printStackTrace();
                        x509Certificate = null;
                    }
                    try {
                        str = m8704e(MessageDigest.getInstance("SHA1").digest(x509Certificate.getEncoded()));
                    } catch (NoSuchAlgorithmException e3) {
                        e3.printStackTrace();
                    } catch (CertificateEncodingException e4) {
                        e4.printStackTrace();
                    }
                    return str;
                } catch (PackageManager.NameNotFoundException e5) {
                    e5.printStackTrace();
                    return "";
                }
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    /* renamed from: e */
    private static String m8704e(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = "0" + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static byte[] m8709d(byte[] bArr) {
        if (bArr == null) {
            return new byte[1];
        }
        return !m8721c(bArr.length) ? m8715c(bArr) : bArr;
    }

    /* renamed from: i */
    public static String m8695i() {
        try {
            return Version.userAgent();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f17249b;
            interfaceC6393e.mo10115e("get okhttp2 version failed:" + th);
            return "";
        }
    }

    /* renamed from: j */
    public static String m8693j() {
        try {
            try {
                return okhttp3.internal.Version.userAgent();
            } catch (Throwable unused) {
                return String.valueOf(okhttp3.internal.Version.userAgent);
            }
        } catch (Throwable unused2) {
            return "";
        }
    }

    /* renamed from: s */
    private static boolean m8682s(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: k */
    public static boolean m8690k(String str) {
        Matcher matcher = Pattern.compile("\\$[0-9]$").matcher(str);
        return matcher != null && matcher.find();
    }

    /* renamed from: c */
    public static JsonObject m8716c(Map<String, JsonObject> map) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, JsonObject> entry : map.entrySet()) {
            jsonObject.add(entry.getKey(), entry.getValue());
        }
        return jsonObject;
    }

    /* renamed from: d */
    public static JsonObject m8710d(Map<String, String> map) {
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonObject.add(entry.getKey(), new JsonPrimitive((Object) entry.getValue()));
        }
        return jsonObject;
    }

    /* renamed from: l */
    public static boolean m8689l(String str) {
        if (TextUtils.isEmpty(str) || str.length() > 64) {
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("eventId 为空,或者超过32字符长度, 被舍弃 . eventId = " + str, new Object[0]);
            }
            return false;
        }
        for (String str2 : new String[]{" ", "\n", "\t", "\b", "\f", "\r", "\u0000"}) {
            if (str.contains(str2)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: e */
    public static HashMap<String, String> m8705e(Map<String, List<String>> map) {
        if (map != null) {
            try {
                if (map.size() != 0) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                        List<String> value = entry.getValue();
                        if (value.size() > 0 && entry.getKey() != null) {
                            hashMap.put(entry.getKey().toLowerCase(), value.get(0));
                        }
                    }
                    return hashMap;
                }
            } catch (Throwable th) {
                InterfaceC6393e interfaceC6393e = f17249b;
                interfaceC6393e.mo10122a("           getHeader  has an error :" + th);
                return null;
            }
        }
        return new HashMap<>();
    }

    /* renamed from: m */
    public static boolean m8688m(String str) {
        return !TextUtils.isEmpty(str) && str.matches("[0-9.]+");
    }

    /* renamed from: a */
    public static String m8736a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : hashMap.keySet()) {
            stringBuffer.append("key:" + str + "value:" + hashMap.get(str) + "--------");
        }
        return stringBuffer.toString();
    }

    /* renamed from: n */
    public static String m8687n(String str) {
        try {
            return new URL(str).getHost();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f17249b;
            interfaceC6393e.mo10115e("DownloadPlugin get hostName error: " + e.getMessage());
            return "";
        }
    }

    /* renamed from: o */
    public static int m8686o(String str) {
        int i;
        try {
            i = new URL(str).getPort();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f17249b;
            interfaceC6393e.mo10115e("error getPortFromUrl: " + th.getMessage());
            i = -1;
        }
        return i == -1 ? str.startsWith("https://") ? 443 : 80 : i;
    }

    /* renamed from: p */
    public static String m8685p(String str) {
        if (str == null || !str.contains("?")) {
            return null;
        }
        return str.substring(str.indexOf("?") + 1);
    }

    /* renamed from: a */
    public static void m8749a(JsonArray jsonArray, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (C6638h.m8963w().f17182z) {
                jsonArray.add(new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(str)));
                return;
            } else {
                jsonArray.add(new JsonPrimitive(str));
                return;
            }
        }
        jsonArray.add(new JsonPrimitive(""));
    }

    /* renamed from: a */
    public static void m8748a(JsonObject jsonObject, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (C6638h.m8963w().f17182z) {
                jsonObject.add(str2, new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(str)));
                return;
            } else {
                jsonObject.add(str2, new JsonPrimitive(str));
                return;
            }
        }
        jsonObject.add(str2, new JsonPrimitive(""));
    }

    /* renamed from: a */
    public static void m8747a(JsonObject jsonObject, Map<String, Object> map, HashMap<String, String> hashMap) {
        if (!C6638h.m8963w().f17182z) {
            if (map != null) {
                jsonObject.add("responseHeader", HarvestableObject.fromMap(map).asJson());
            }
            if (hashMap == null || !C6638h.m8963w().m9065V()) {
                return;
            }
            jsonObject.add("requestHeader", HarvestableObject.fromMapString(hashMap).asJson());
            return;
        }
        if (map == null || map.size() <= 0) {
            jsonObject.add("responseHeader", HarvestableObject.fromMap(Collections.emptyMap()).asJson());
        } else if (C6638h.m8963w().m9065V()) {
            jsonObject.add("responseHeader", new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(HarvestableObject.fromMap(map).asJson().toString())));
        }
        if (hashMap == null || hashMap.size() <= 0) {
            jsonObject.add("requestHeader", HarvestableObject.fromMapString(new HashMap()).asJson());
        } else if (C6638h.m8963w().m9065V()) {
            jsonObject.add("requestHeader", HarvestableObject.fromMapString(hashMap).asJson());
        }
    }

    /* renamed from: q */
    public static long m8684q(String str) throws ParseException {
        return new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss z", Locale.US).parse(str).getTime();
    }

    /* renamed from: a */
    public static StringBuilder m8754a(int i, Throwable th) {
        StringBuilder sb = new StringBuilder();
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        int i2 = 0;
        while (th != null) {
            sb.append("Caused by: " + m8717c(th) + "\n");
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i3 = i2;
            int i4 = 0;
            while (true) {
                if (i4 >= stackTrace.length) {
                    break;
                } else if (i3 >= i) {
                    sb.append("\t... ");
                    sb.append(stackTrace.length - i4);
                    sb.append(" more");
                    f17249b.mo10122a("sDepth is" + i3);
                    break;
                } else {
                    i3++;
                    sb.append("\tat " + stackTrace[i4] + "\n");
                    i4++;
                }
            }
            th = th.getCause();
            i2 = i3;
        }
        return sb;
    }

    /* renamed from: a */
    public static StringBuilder m8738a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        sb.append("Caused by: " + m8717c(th));
        sb.append(m8711d(th));
        return sb;
    }

    /* renamed from: b */
    public static boolean m8725b(Throwable th) {
        return th.getClass().getName().contains("JavascriptException") && th.getClass().getName().contains("react");
    }

    /* renamed from: c */
    public static String m8717c(Throwable th) {
        if (m8725b(th)) {
            String[] split = th.getMessage().split(", stack:");
            return (split == null || split.length != 2) ? "" : split[0];
        }
        return th.toString();
    }

    /* renamed from: d */
    public static String m8711d(Throwable th) {
        String[] split;
        return (m8725b(th) && (split = th.getMessage().split(", stack:")) != null && split.length == 2) ? split[1] : "";
    }

    /* renamed from: e */
    public static Throwable m8707e(int i) {
        Throwable th = new Throwable();
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace != null && stackTrace.length > i) {
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[stackTrace.length - i];
            System.arraycopy(stackTrace, i, stackTraceElementArr, 0, stackTrace.length - i);
            th.setStackTrace(stackTraceElementArr);
        }
        return th;
    }

    /* renamed from: r */
    public static boolean m8683r(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: c */
    public static boolean m8720c(Context context) {
        return (context.getApplicationInfo() == null || (context.getApplicationInfo().flags & 2) == 0) ? false : true;
    }

    /* renamed from: k */
    public static String m8691k() {
        try {
            C6646o c6646o = new C6646o(C6638h.m8963w().m9076K());
            String m8831g = c6646o.m8831g();
            if (TextUtils.isEmpty(m8831g)) {
                return null;
            }
            c6646o.m8847b("");
            return m8831g;
        } catch (Throwable th) {
            f17249b.mo10121a("getLastCrashUUID error ", th);
            return null;
        }
    }

    /* renamed from: f */
    public static String m8701f(Map map) {
        return m8698g(map);
    }

    /* renamed from: g */
    public static String m8698g(Map<String, Object> map) {
        try {
            InterfaceC6393e interfaceC6393e = f17249b;
            interfaceC6393e.mo10122a("getJsonObjectFromMapTag : " + map.get(ConfigurationName.dataTagKey));
        } catch (Throwable th) {
            th.printStackTrace();
        }
        if (map == null || map.size() < 1) {
            return "";
        }
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                jsonObject.add(key, null);
            } else if (value instanceof Number) {
                jsonObject.add(key, new JsonPrimitive(value));
            } else if (value instanceof List) {
                List list = (List) value;
                int size = list.size();
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < size; i++) {
                    jsonArray.add(new JsonPrimitive((String) list.get(i)));
                }
                jsonObject.add(key, jsonArray);
            } else {
                jsonObject.add(key, new JsonPrimitive(value.toString()));
            }
        }
        return jsonObject.toString();
    }
}
