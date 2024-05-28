package com.p281qq.p282e.comm.managers.plugin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.p281qq.p282e.comm.constants.CustomPkgConstants;
import com.p281qq.p282e.comm.constants.Sig;
import com.p281qq.p282e.comm.managers.status.SDKStatus;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.managers.plugin.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6879b {

    /* renamed from: a */
    private static volatile String f17945a;

    /* renamed from: a */
    public static synchronized String m8262a(Context context) {
        ActivityManager.RunningAppProcessInfo next;
        synchronized (C6879b.class) {
            if (!TextUtils.isEmpty(f17945a)) {
                return f17945a;
            } else if (Build.VERSION.SDK_INT >= 28) {
                f17945a = Application.getProcessName();
                return f17945a;
            } else {
                int myPid = Process.myPid();
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
                    while (it.hasNext()) {
                        try {
                            next = it.next();
                        } catch (Exception unused) {
                        }
                        if (next.pid == myPid) {
                            f17945a = next.processName;
                            return f17945a;
                        }
                        continue;
                    }
                }
                return null;
            }
        }
    }

    /* renamed from: a */
    public static synchronized String m8259a(String str) {
        synchronized (C6879b.class) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            String str2 = f17945a;
            if (TextUtils.isEmpty(str2)) {
                return str;
            }
            boolean endsWith = str2.endsWith("_");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(endsWith ? "" : "_");
            String str3 = null;
            try {
                String str4 = new String(str2);
                try {
                    str3 = C6883d.m8255a(MessageDigest.getInstance("MD5").digest(str4.getBytes("UTF-8")));
                } catch (Exception unused) {
                    str3 = str4;
                }
            } catch (Exception unused2) {
            }
            sb.append(str3);
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m8261a(Context context, File file, File file2) throws Throwable {
        Throwable th;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        String str;
        boolean z;
        AssetManager assets = context.getAssets();
        InputStream inputStream2 = null;
        try {
            C6887h.m8246a();
            String[] list = assets.list("gdt_plugin");
            if (Arrays.binarySearch(list, "gdtadv2.jar") < 0) {
                if (list != null && list.length > 0) {
                    str = TextUtils.join(",", list);
                    String str2 = "Asset Error " + str;
                    GDTLogger.m8234e(str2);
                    throw new Exception(str2);
                }
                str = "no asset";
                String str22 = "Asset Error " + str;
                GDTLogger.m8234e(str22);
                throw new Exception(str22);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("gdt_plugin");
            sb.append(File.separator);
            sb.append("gdtadv2.jar");
            String sb2 = sb.toString();
            String str3 = Sig.ASSET_PLUGIN_SIG;
            if (str3 == null) {
                str3 = "";
            }
            C6887h.m8241a(SDKStatus.getBuildInPluginVersion() + "#####" + str3, file2);
            if (TextUtils.isEmpty(CustomPkgConstants.getAssetPluginXorKey())) {
                z = C6887h.m8242a(assets.open(sb2), file);
                fileOutputStream = null;
            } else {
                inputStream = assets.open(sb2);
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        byte[] bytes = CustomPkgConstants.getAssetPluginXorKey().getBytes(Charset.forName("UTF-8"));
                        byte[] bArr = new byte[1024];
                        int length = bytes.length;
                        int i = 0;
                        int i2 = 0;
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            int i3 = i2;
                            int i4 = i;
                            int i5 = 0;
                            while (i5 < read) {
                                int i6 = i4 + 1;
                                if (i4 >= 64) {
                                    bArr[i5] = (byte) (bArr[i5] ^ bytes[i3 % length]);
                                    i3++;
                                }
                                i5++;
                                i4 = i6;
                            }
                            fileOutputStream.write(bArr, 0, read);
                            i = i4;
                            i2 = i3;
                        }
                        inputStream2 = inputStream;
                        z = true;
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            GDTLogger.m8233e("插件加载失败", th);
                            throw th;
                        } finally {
                            m8260a(inputStream);
                            m8260a(fileOutputStream);
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    GDTLogger.m8233e("插件加载失败", th);
                    throw th;
                }
            }
            if (!z) {
                throw new Exception("Plugin prepare failed");
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
        }
    }

    /* renamed from: a */
    private static void m8260a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
