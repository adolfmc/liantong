package io.objectbox.internal;

import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import org.greenrobot.essentials.p468io.IoUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NativeLibraryLoader {
    public static void ensureLoaded() {
    }

    static {
        String str = "objectbox-jni";
        String str2 = "objectbox-jni.so";
        boolean contains = System.getProperty("java.vendor").contains("Android");
        boolean z = false;
        if (!contains) {
            String lowerCase = System.getProperty("os.name").toLowerCase();
            String str3 = "32".equals(System.getProperty("sun.arch.data.model")) ? "-x86" : "-x64";
            if (lowerCase.contains("windows")) {
                str = "objectbox-jni-windows" + str3;
                str2 = str + ".dll";
                checkUnpackLib(str2);
            } else if (lowerCase.contains("linux")) {
                z = true;
                str = "objectbox-jni-linux" + str3;
                str2 = "lib" + str + ".so";
                checkUnpackLib(str2);
            } else if (lowerCase.contains("mac")) {
                str = "objectbox-jni-macos" + str3;
                str2 = "lib" + str + ".dylib";
                checkUnpackLib(str2);
            }
        }
        File file = new File(str2);
        if (file.exists()) {
            System.load(file.getAbsolutePath());
            return;
        }
        if (!contains) {
            System.err.println("File not available: " + file.getAbsolutePath());
        }
        try {
            System.loadLibrary(str);
        } catch (UnsatisfiedLinkError e) {
            if (!contains && z) {
                System.loadLibrary("objectbox-jni");
                return;
            }
            throw e;
        }
    }

    private static void checkUnpackLib(String str) {
        String str2 = "/native/" + str;
        URL resource = NativeLibraryLoader.class.getResource(str2);
        if (resource == null) {
            System.err.println("Not available in classpath: " + str2);
            return;
        }
        File file = new File(str);
        try {
            URLConnection openConnection = NBSInstrumentation.openConnection(resource.openConnection());
            int contentLength = openConnection.getContentLength();
            long lastModified = openConnection.getLastModified();
            if (file.exists() && file.length() == contentLength && file.lastModified() == lastModified) {
                return;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            IoUtils.copyAllBytes(bufferedInputStream, bufferedOutputStream);
            IoUtils.safeClose(bufferedOutputStream);
            IoUtils.safeClose(bufferedInputStream);
            if (lastModified > 0) {
                file.setLastModified(lastModified);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
