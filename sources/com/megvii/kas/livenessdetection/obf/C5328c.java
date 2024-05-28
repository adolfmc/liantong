package com.megvii.kas.livenessdetection.obf;

import android.content.Context;
import android.os.Build;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/* renamed from: com.megvii.kas.livenessdetection.obf.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5328c {

    /* renamed from: a */
    private static Context f12345a;

    /* renamed from: b */
    private static C5328c f12346b;

    /* renamed from: a */
    public static synchronized C5328c m13639a(Context context) {
        C5328c c5328c;
        synchronized (C5328c.class) {
            if (f12346b == null) {
                f12346b = new C5328c(context);
            }
            c5328c = f12346b;
        }
        return c5328c;
    }

    private C5328c(Context context) {
        f12345a = context;
    }

    /* renamed from: a */
    public final boolean m13635a(String str, String str2) {
        boolean z;
        File filesDir;
        try {
            System.loadLibrary("livenessdetection_ka_v2.4.7");
            z = true;
        } catch (Exception unused) {
            z = false;
        }
        if (z) {
            return true;
        }
        String str3 = str + "_bak";
        if (m13633a(f12345a.getFilesDir().toString(), str3, str, str2)) {
            File file = new File(filesDir.toString() + File.separator + (str3 + File.separator + ("lib" + str + "_" + str2 + ".so")));
            StringBuilder sb = new StringBuilder("copy lib to ");
            sb.append(file.toString());
            C5330d.m13631a(sb.toString());
            if (file.exists()) {
                try {
                    System.load(file.toString());
                    return true;
                } catch (UnsatisfiedLinkError e) {
                    C5330d.m13630a("SoProtect", e.toString());
                }
            } else {
                C5330d.m13627b("SoProtect", String.format(Locale.ENGLISH, "error can't find %1$s lib in plugins_lib", str));
            }
        } else {
            C5330d.m13630a("SoProtect", String.format(Locale.ENGLISH, "error copy %1$s lib fail", str));
        }
        return z;
    }

    /* renamed from: a */
    private void m13637a(File file, String str) {
        try {
            for (File file2 : file.listFiles(new C5329a(this, str))) {
                m13638a(file2);
            }
        } catch (Exception e) {
            C5330d.m13630a("SoProtect", e.toString());
        }
    }

    /* renamed from: a */
    private void m13638a(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            } else if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    m13638a(file2);
                }
                file.delete();
                return;
            } else {
                return;
            }
        }
        C5330d.m13627b("SoProtect", "所删除的文件不存在！\n");
    }

    /* renamed from: a */
    private boolean m13633a(String str, String str2, String str3, String str4) {
        String str5;
        String str6 = Build.CPU_ABI;
        String str7 = "lib" + str3 + "_" + str4 + ".so";
        if ("x86".equals(str6)) {
            str5 = "lib/x86/" + str7;
        } else if ("armeabi-v7a".equals(str6)) {
            str5 = "lib/armeabi-v7a/" + str7;
        } else {
            C5330d.m13630a("SoProtect", "apse is not support for this mode");
            return false;
        }
        try {
            File file = new File(str + File.separator + str2);
            File file2 = new File(file.toString() + File.separator + str7);
            if (file2.exists()) {
                C5330d.m13627b("SoProtect", "file " + file2.toString() + " is exist");
                return true;
            }
            m13637a(file, "lib" + str3);
            file.mkdirs();
            boolean m13634a = m13634a(str, str5, str7, file2);
            if (m13634a || !str6.equals("armeabi-v7a")) {
                return m13634a;
            }
            C5330d.m13627b("SoProtect", String.format("%s arch copy failed, try to copy %s arch", "armeabi-v7a", "armeabi"));
            return m13634a(str, "lib/armeabi/" + str7, str7, file2);
        } catch (Exception e) {
            C5330d.m13630a("SoProtect", e.toString());
            return false;
        }
    }

    /* renamed from: a */
    private boolean m13634a(String str, String str2, String str3, File file) {
        InputStream resourceAsStream = C5328c.class.getClassLoader().getResourceAsStream(str2);
        if (resourceAsStream != null) {
            if (str == null) {
                C5330d.m13630a("SoProtect", "apse file cann't be null...");
            }
            boolean m13636a = m13636a(resourceAsStream, file);
            try {
                resourceAsStream.close();
                return m13636a;
            } catch (IOException e) {
                C5330d.m13630a("SoProtect", e.toString());
                return m13636a;
            }
        }
        C5330d.m13627b("SoProtect", "error: can't find " + str3 + " in apk");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.BufferedOutputStream] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* renamed from: a */
    private static boolean m13636a(InputStream inputStream, File file) {
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    bufferedInputStream = new BufferedInputStream(inputStream);
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                        try {
                            file = new BufferedOutputStream(fileOutputStream2);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = bufferedInputStream.read(bArr);
                                    if (read == -1) {
                                        file.flush();
                                        fileOutputStream2.flush();
                                        fileOutputStream2.close();
                                        bufferedInputStream.close();
                                        file.close();
                                        return true;
                                    }
                                    file.write(bArr, 0, read);
                                }
                            } catch (FileNotFoundException e) {
                                fileOutputStream = fileOutputStream2;
                                e = e;
                                file = file;
                                C5330d.m13630a("SoProtect", e.toString());
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (file != 0) {
                                    file.close();
                                }
                                return false;
                            } catch (IOException e2) {
                                fileOutputStream = fileOutputStream2;
                                e = e2;
                                file = file;
                                C5330d.m13630a("SoProtect", e.toString());
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (file != 0) {
                                    file.close();
                                }
                                return false;
                            } catch (Throwable th) {
                                fileOutputStream = fileOutputStream2;
                                th = th;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e3) {
                                        C5330d.m13630a("SoProtect", e3.toString());
                                        throw th;
                                    }
                                }
                                if (bufferedInputStream != null) {
                                    bufferedInputStream.close();
                                }
                                if (file != 0) {
                                    file.close();
                                }
                                throw th;
                            }
                        } catch (FileNotFoundException e4) {
                            fileOutputStream = fileOutputStream2;
                            e = e4;
                            file = 0;
                        } catch (IOException e5) {
                            fileOutputStream = fileOutputStream2;
                            e = e5;
                            file = 0;
                        } catch (Throwable th2) {
                            fileOutputStream = fileOutputStream2;
                            th = th2;
                            file = 0;
                        }
                    } catch (FileNotFoundException e6) {
                        e = e6;
                        file = 0;
                    } catch (IOException e7) {
                        e = e7;
                        file = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        file = 0;
                    }
                } catch (FileNotFoundException e8) {
                    e = e8;
                    file = 0;
                    bufferedInputStream = null;
                } catch (IOException e9) {
                    e = e9;
                    file = 0;
                    bufferedInputStream = null;
                } catch (Throwable th4) {
                    th = th4;
                    file = 0;
                    bufferedInputStream = null;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (IOException e10) {
            C5330d.m13630a("SoProtect", e10.toString());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.megvii.kas.livenessdetection.obf.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5329a implements FileFilter {

        /* renamed from: a */
        private String f12347a;

        public C5329a(C5328c c5328c, String str) {
            this.f12347a = "";
            this.f12347a = str;
        }

        @Override // java.io.FileFilter
        public final boolean accept(File file) {
            return file.getName().startsWith(this.f12347a);
        }
    }
}
