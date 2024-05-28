package com.huawei.hms.opendevice;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import com.huawei.secure.android.common.util.IOUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import javax.net.ssl.HttpsURLConnection;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.o */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class StreamUtil {
    /* renamed from: a */
    private static void m14335a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HMSLog.m14109w("StreamUtil", "close IOException");
            }
        }
    }

    /* renamed from: a */
    public static void m14331a(HttpsURLConnection httpsURLConnection) {
        if (httpsURLConnection != null) {
            try {
                httpsURLConnection.disconnect();
            } catch (Throwable unused) {
                HMSLog.m14109w("StreamUtil", "close HttpsURLConnection Exception");
            }
        }
    }

    /* renamed from: a */
    public static String m14333a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int read = inputStream.read();
        while (-1 != read) {
            byteArrayOutputStream.write(read);
            read = inputStream.read();
        }
        String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
        m14335a((Closeable) inputStream);
        IOUtil.closeSecure((OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream2;
    }

    /* renamed from: a */
    public static void m14334a(File file) throws IOException {
        if (file.exists()) {
            return;
        }
        if (file.getParentFile() != null) {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                HMSLog.m14112e("StreamUtil", "make parent dirs failed.");
                throw new IOException("make parent dirs failed");
            } else if (file.createNewFile()) {
                return;
            } else {
                HMSLog.m14112e("StreamUtil", "create file failed.");
                throw new IOException("create file failed");
            }
        }
        HMSLog.m14112e("StreamUtil", "parent file is null.");
        throw new IOException("parent file is null");
    }

    /* renamed from: a */
    public static String m14332a(String str) {
        InputStreamReader inputStreamReader;
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                    while (true) {
                        try {
                            String readLine = bufferedReader2.readLine();
                            if (readLine == null) {
                                break;
                            }
                            sb.append(readLine);
                        } catch (FileNotFoundException unused) {
                            bufferedReader = bufferedReader2;
                            HMSLog.m14112e("StreamUtil", "file not exist.");
                            IOUtils.closeQuietly((Reader) inputStreamReader);
                            IOUtils.closeQuietly((Reader) bufferedReader);
                            return sb.toString();
                        } catch (IOException unused2) {
                            bufferedReader = bufferedReader2;
                            HMSLog.m14112e("StreamUtil", "read value IOException.");
                            IOUtils.closeQuietly((Reader) inputStreamReader);
                            IOUtils.closeQuietly((Reader) bufferedReader);
                            return sb.toString();
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            IOUtils.closeQuietly((Reader) inputStreamReader);
                            IOUtils.closeQuietly((Reader) bufferedReader);
                            throw th;
                        }
                    }
                    IOUtils.closeQuietly((Reader) inputStreamReader);
                    IOUtils.closeQuietly((Reader) bufferedReader2);
                } catch (FileNotFoundException unused3) {
                } catch (IOException unused4) {
                }
            } catch (FileNotFoundException unused5) {
                inputStreamReader = null;
            } catch (IOException unused6) {
                inputStreamReader = null;
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader = null;
            }
            return sb.toString();
        } catch (Throwable th3) {
            th = th3;
        }
    }
}
