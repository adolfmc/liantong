package com.baidu.mapsdkplatform.comapi;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NativeLoader {

    /* renamed from: a */
    private static final String f7048a = "NativeLoader";

    /* renamed from: b */
    private static Context f7049b;

    /* renamed from: e */
    private static NativeLoader f7052e;

    /* renamed from: c */
    private static final Set<String> f7050c = new HashSet();

    /* renamed from: d */
    private static final Set<String> f7051d = new HashSet();

    /* renamed from: f */
    private static EnumC2867a f7053f = EnumC2867a.ARMEABI;

    /* renamed from: g */
    private static boolean f7054g = false;

    /* renamed from: h */
    private static String f7055h = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapsdkplatform.comapi.NativeLoader$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC2867a {
        ARMEABI("armeabi"),
        ARMV7("armeabi-v7a"),
        ARM64("arm64-v8a"),
        X86("x86"),
        X86_64("x86_64");
        

        /* renamed from: f */
        private String f7062f;

        EnumC2867a(String str) {
            this.f7062f = str;
        }

        /* renamed from: a */
        public String m18540a() {
            return this.f7062f;
        }
    }

    public static synchronized NativeLoader getInstance() {
        NativeLoader nativeLoader;
        synchronized (NativeLoader.class) {
            if (f7052e == null) {
                f7052e = new NativeLoader();
                f7053f = m18547c();
            }
            nativeLoader = f7052e;
        }
        return nativeLoader;
    }

    private NativeLoader() {
    }

    public static void setContext(Context context) {
        f7049b = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m18551a(boolean z, String str) {
        f7054g = z;
        f7055h = str;
    }

    public synchronized boolean loadLibrary(String str) {
        if (!f7054g) {
            return m18555a(str);
        } else if (f7055h != null && !f7055h.isEmpty()) {
            return m18549b(str);
        } else {
            Log.e(f7048a, "Given custom so file path is null, please check!");
            return false;
        }
    }

    /* renamed from: a */
    private boolean m18555a(String str) {
        try {
            synchronized (f7050c) {
                if (f7050c.contains(str)) {
                    return true;
                }
                System.loadLibrary(str);
                synchronized (f7050c) {
                    f7050c.add(str);
                }
                return true;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            return m18549b(str);
        }
    }

    /* renamed from: a */
    private boolean m18553a(String str, String str2) {
        if (!m18554a(str2, EnumC2867a.ARMV7)) {
            return m18548b(str, str2);
        }
        return m18542f(str2, str);
    }

    /* renamed from: b */
    private boolean m18548b(String str, String str2) {
        if (m18554a(str2, EnumC2867a.ARMEABI)) {
            return m18542f(str2, str);
        }
        String str3 = f7048a;
        Log.e(str3, "found lib " + EnumC2867a.ARMEABI.m18540a() + "/" + str + ".so error");
        return false;
    }

    /* renamed from: c */
    private boolean m18546c(String str, String str2) {
        if (!m18554a(str2, EnumC2867a.ARM64)) {
            return m18553a(str, str2);
        }
        return m18542f(str2, str);
    }

    /* renamed from: d */
    private boolean m18544d(String str, String str2) {
        if (!m18554a(str2, EnumC2867a.X86)) {
            return m18553a(str, str2);
        }
        return m18542f(str2, str);
    }

    /* renamed from: e */
    private boolean m18543e(String str, String str2) {
        if (!m18554a(str2, EnumC2867a.X86_64)) {
            return m18544d(str, str2);
        }
        return m18542f(str2, str);
    }

    /* renamed from: b */
    private boolean m18549b(String str) {
        boolean m18546c;
        String mapLibraryName = System.mapLibraryName(str);
        synchronized (f7050c) {
            if (f7050c.contains(str)) {
                return true;
            }
            switch (f7053f) {
                case ARM64:
                    m18546c = m18546c(str, mapLibraryName);
                    break;
                case ARMV7:
                    m18546c = m18553a(str, mapLibraryName);
                    break;
                case ARMEABI:
                    m18546c = m18548b(str, mapLibraryName);
                    break;
                case X86_64:
                    m18546c = m18543e(str, mapLibraryName);
                    break;
                case X86:
                    m18546c = m18544d(str, mapLibraryName);
                    break;
                default:
                    m18546c = false;
                    break;
            }
            synchronized (f7050c) {
                f7050c.add(str);
            }
            return m18546c;
        }
    }

    /* renamed from: f */
    private boolean m18542f(String str, String str2) {
        try {
            System.loadLibrary(new File(m18550b(), str).getAbsolutePath());
            synchronized (f7050c) {
                f7050c.add(str2);
            }
            m18541g(str, str2);
            return true;
        } catch (Throwable th) {
            synchronized (f7051d) {
                f7051d.add(str2);
                m18552a(th);
                return false;
            }
        }
    }

    /* renamed from: a */
    private boolean m18554a(String str, EnumC2867a enumC2867a) {
        ZipFile zipFile;
        File file = new File(m18550b(), str);
        if (!file.exists() || file.length() <= 0) {
            String str2 = m18557a(enumC2867a) + str;
            ZipFile zipFile2 = null;
            String m18558a = !f7054g ? m18558a() : f7055h;
            if (m18558a != null) {
                try {
                    if (!m18558a.isEmpty()) {
                        try {
                            zipFile = new ZipFile(m18558a);
                            try {
                                ZipEntry entry = zipFile.getEntry(str2);
                                if (entry == null) {
                                    try {
                                        zipFile.close();
                                    } catch (IOException e) {
                                        Log.e(f7048a, "Release file failed", e);
                                    }
                                    return false;
                                }
                                m18556a(zipFile.getInputStream(entry), new FileOutputStream(new File(m18550b(), str)));
                                try {
                                    zipFile.close();
                                } catch (IOException e2) {
                                    Log.e(f7048a, "Release file failed", e2);
                                }
                                return true;
                            } catch (Exception e3) {
                                e = e3;
                                zipFile2 = zipFile;
                                Log.e(f7048a, "Copy library file error", e);
                                if (zipFile2 != null) {
                                    try {
                                        zipFile2.close();
                                    } catch (IOException e4) {
                                        Log.e(f7048a, "Release file failed", e4);
                                    }
                                }
                                return false;
                            } catch (Throwable th) {
                                th = th;
                                if (zipFile != null) {
                                    try {
                                        zipFile.close();
                                    } catch (IOException e5) {
                                        Log.e(f7048a, "Release file failed", e5);
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e6) {
                            e = e6;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    zipFile = null;
                }
            }
            return false;
        }
        return true;
    }

    /* renamed from: g */
    private void m18541g(String str, String str2) {
        if (str == null || str.isEmpty() || !str.contains("libBaiduMapSDK_")) {
            return;
        }
        try {
            String[] split = str.split("_v");
            if (split.length <= 1) {
                return;
            }
            File[] listFiles = new File(m18550b()).listFiles(new C2904d(this, split[1]));
            if (listFiles == null || listFiles.length == 0) {
                return;
            }
            for (File file : listFiles) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    @TargetApi(8)
    /* renamed from: a */
    private String m18558a() {
        return (f7049b != null && 8 <= Build.VERSION.SDK_INT) ? f7049b.getPackageCodePath() : "";
    }

    /* renamed from: b */
    private String m18550b() {
        if (f7049b == null) {
            return "";
        }
        File file = new File(f7049b.getFilesDir(), "libs" + File.separator + f7053f.m18540a());
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /* renamed from: a */
    private void m18552a(Throwable th) {
        Log.e(f7048a, "loadException", th);
        Iterator<String> it = f7051d.iterator();
        while (it.hasNext()) {
            String str = f7048a;
            Log.e(str, it.next() + " Failed to load.");
        }
    }

    /* renamed from: a */
    private void m18556a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Log.e(f7048a, "Close InputStream error", e);
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e2) {
                    Log.e(f7048a, "Close OutputStream error", e2);
                }
                throw th;
            }
        }
        fileOutputStream.flush();
        try {
            inputStream.close();
        } catch (IOException e3) {
            Log.e(f7048a, "Close InputStream error", e3);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e4) {
            Log.e(f7048a, "Close OutputStream error", e4);
        }
    }

    @TargetApi(21)
    /* renamed from: c */
    private static EnumC2867a m18547c() {
        String str;
        if (Build.VERSION.SDK_INT < 21) {
            str = Build.CPU_ABI;
        } else {
            str = Build.SUPPORTED_ABIS[0];
        }
        if (str == null) {
            return EnumC2867a.ARMEABI;
        }
        if (str.contains("arm") && str.contains("v7")) {
            f7053f = EnumC2867a.ARMV7;
        }
        if (str.contains("arm") && str.contains("64") && m18545d()) {
            f7053f = EnumC2867a.ARM64;
        }
        if (str.contains("x86")) {
            if (str.contains("64")) {
                f7053f = EnumC2867a.X86_64;
            } else {
                f7053f = EnumC2867a.X86;
            }
        }
        return f7053f;
    }

    /* renamed from: d */
    private static boolean m18545d() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return Build.CPU_ABI.equals(Build.SUPPORTED_64_BIT_ABIS[0]);
        }
        return false;
    }

    /* renamed from: a */
    private String m18557a(EnumC2867a enumC2867a) {
        return "lib/" + enumC2867a.m18540a() + "/";
    }
}
