package com.baidu.p120ar.utils;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.IoUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IoUtils {
    private static final int BUFFER_SIZE = 1024;
    private static final int EOF = -1;
    private static final String UTF8_BOM = "\ufeff";
    public static final String UTF_8 = "utf-8";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.utils.IoUtils$Cancelable */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Cancelable {
        boolean isCancelled();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.utils.IoUtils$Operation */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Operation extends Cancelable, ProgressListener {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.utils.IoUtils$ProgressListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ProgressListener {
        void progress(long j, long j2);
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    public static void copyStream(InputStream inputStream, File file) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = FileUtils.openFileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            copyStream(inputStream, fileOutputStream);
            closeQuietly(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, long j, Operation operation) throws IOException {
        byte[] bArr = new byte[8192];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return;
            }
            outputStream.write(bArr, 0, read);
            j2 += read;
            if (operation != null) {
                operation.progress(j2, j);
                if (operation.isCancelled()) {
                    return;
                }
            }
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, final Cancelable cancelable) throws IOException {
        copyStream(inputStream, outputStream, 0L, new Operation() { // from class: com.baidu.ar.utils.IoUtils.1
            @Override // com.baidu.p120ar.utils.IoUtils.ProgressListener
            public void progress(long j, long j2) {
            }

            @Override // com.baidu.p120ar.utils.IoUtils.Cancelable
            public boolean isCancelled() {
                Cancelable cancelable2 = Cancelable.this;
                return cancelable2 != null && cancelable2.isCancelled();
            }
        });
    }

    public static void copyStream(InputStream inputStream, File file, Cancelable cancelable) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = FileUtils.openFileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            copyStream(inputStream, fileOutputStream, cancelable);
            closeQuietly(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static void copyStream(InputStream inputStream, OutputStream outputStream, long j, final ProgressListener progressListener) throws IOException {
        copyStream(inputStream, outputStream, j, new Operation() { // from class: com.baidu.ar.utils.IoUtils.2
            @Override // com.baidu.p120ar.utils.IoUtils.Cancelable
            public boolean isCancelled() {
                return false;
            }

            @Override // com.baidu.p120ar.utils.IoUtils.ProgressListener
            public void progress(long j2, long j3) {
                ProgressListener progressListener2 = ProgressListener.this;
                if (progressListener2 != null) {
                    progressListener2.progress(j2, j3);
                }
            }
        });
    }

    public static void copyStream(InputStream inputStream, File file, long j, ProgressListener progressListener) throws IOException {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = FileUtils.openFileOutputStream(file);
        } catch (Throwable th) {
            th = th;
            fileOutputStream = null;
        }
        try {
            copyStream(inputStream, fileOutputStream, j, progressListener);
            closeQuietly(fileOutputStream);
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(fileOutputStream);
            throw th;
        }
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String loadContent(InputStream inputStream) throws IOException {
        return loadContent(inputStream, null);
    }

    public static String loadContent(InputStream inputStream, String str) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("stream may not be null.");
        }
        if (TextUtils.isEmpty(str)) {
            str = System.getProperty("file.encoding", "utf-8");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        StringWriter stringWriter = new StringWriter();
        char[] cArr = new char[4096];
        for (int read = inputStreamReader.read(cArr); read > 0; read = inputStreamReader.read(cArr)) {
            stringWriter.write(cArr, 0, read);
        }
        String stringWriter2 = stringWriter.toString();
        inputStreamReader.close();
        stringWriter.close();
        return ("utf-8".equalsIgnoreCase(str) && stringWriter2.startsWith("\ufeff")) ? stringWriter2.substring(1) : stringWriter2;
    }

    public static List<String> loadLineContent(InputStream inputStream) throws IOException {
        return loadLineContent(inputStream, null);
    }

    public static List<String> loadLineContent(InputStream inputStream, String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        if (inputStream == null) {
            throw new IllegalArgumentException("stream may not be null.");
        }
        if (TextUtils.isEmpty(str)) {
            str = System.getProperty("file.encoding", "utf-8");
        }
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                arrayList.add(readLine);
            } else {
                bufferedReader.close();
                inputStreamReader.close();
                return arrayList;
            }
        }
    }
}
