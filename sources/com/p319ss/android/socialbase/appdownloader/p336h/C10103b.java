package com.p319ss.android.socialbase.appdownloader.p336h;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: com.ss.android.socialbase.appdownloader.h.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10103b {

    /* renamed from: mb */
    private static int f19465mb = 8;

    /* renamed from: ox */
    private static volatile C10103b f19466ox;

    /* renamed from: b */
    private C10105mb<Integer, Bitmap> f19467b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.socialbase.appdownloader.h.b$mb */
    /* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
    public static class C10105mb<K, T> extends LinkedHashMap<K, T> {

        /* renamed from: mb */
        final int f19471mb;

        public C10105mb(int i, int i2) {
            super(i2, 0.75f, true);
            this.f19471mb = i;
        }

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<K, T> entry) {
            return size() > this.f19471mb;
        }
    }

    /* renamed from: mb */
    public static C10103b m6845mb() {
        if (f19466ox == null) {
            synchronized (C10103b.class) {
                if (f19466ox == null) {
                    f19466ox = new C10103b();
                }
            }
        }
        return f19466ox;
    }

    private C10103b() {
        this.f19467b = null;
        int i = f19465mb;
        this.f19467b = new C10105mb<>(i, i / 2);
    }

    /* renamed from: mb */
    public Bitmap m6844mb(int i) {
        return this.f19467b.get(Integer.valueOf(i));
    }

    /* renamed from: mb */
    public void m6842mb(final int i, final String str) {
        if (TextUtils.isEmpty(str) || m6844mb(i) != null) {
            return;
        }
        DownloadComponentManager.getIOThreadExecutor().submit(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.h.b.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v0 */
            /* JADX WARN: Type inference failed for: r2v12 */
            /* JADX WARN: Type inference failed for: r2v13 */
            /* JADX WARN: Type inference failed for: r2v5, types: [java.io.Closeable[]] */
            @Override // java.lang.Runnable
            public void run() {
                ByteArrayOutputStream byteArrayOutputStream;
                ByteArrayInputStream byteArrayInputStream;
                ByteArrayInputStream byteArrayInputStream2;
                Throwable th;
                InputStream inputStream;
                Exception e;
                IDownloadHttpConnection downloadWithConnection;
                int i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                i2 = 4;
                try {
                    try {
                        downloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                    } catch (Exception e2) {
                        byteArrayOutputStream = null;
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        e = e2;
                        inputStream = null;
                    } catch (Throwable th2) {
                        byteArrayOutputStream = null;
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        th = th2;
                        inputStream = null;
                    }
                    if (downloadWithConnection == null) {
                        DownloadUtils.safeClose(null, null, null, null);
                        return;
                    }
                    inputStream = downloadWithConnection.getInputStream();
                    try {
                        byteArrayOutputStream = C10103b.m6839ox(inputStream);
                        try {
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            try {
                                byteArrayInputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            } catch (Exception e3) {
                                byteArrayInputStream2 = null;
                                e = e3;
                            } catch (Throwable th3) {
                                byteArrayInputStream2 = null;
                                th = th3;
                                Closeable[] closeableArr = new Closeable[i2];
                                closeableArr[0] = inputStream;
                                closeableArr[1] = byteArrayOutputStream;
                                closeableArr[2] = byteArrayInputStream;
                                closeableArr[3] = byteArrayInputStream2;
                                DownloadUtils.safeClose(closeableArr);
                                throw th;
                            }
                        } catch (Exception e4) {
                            byteArrayInputStream2 = null;
                            e = e4;
                            byteArrayInputStream = null;
                        } catch (Throwable th4) {
                            byteArrayInputStream2 = null;
                            th = th4;
                            byteArrayInputStream = null;
                        }
                    } catch (Exception e5) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        e = e5;
                        byteArrayOutputStream = null;
                    } catch (Throwable th5) {
                        byteArrayInputStream = null;
                        byteArrayInputStream2 = null;
                        th = th5;
                        byteArrayOutputStream = null;
                    }
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeStream(byteArrayInputStream, null, options);
                        int i3 = options.outWidth;
                        int i4 = options.outHeight;
                        int m6918mb = C10085b.m6918mb(DownloadComponentManager.getAppContext(), 44.0f);
                        options.inSampleSize = C10103b.m6843mb(m6918mb, m6918mb, options);
                        options.inJustDecodeBounds = false;
                        C10103b.this.f19467b.put(Integer.valueOf(i), BitmapFactory.decodeStream(byteArrayInputStream2, null, options));
                        i2 = new Closeable[]{inputStream, byteArrayOutputStream, byteArrayInputStream, byteArrayInputStream2};
                    } catch (Exception e6) {
                        e = e6;
                        e.printStackTrace();
                        i2 = new Closeable[]{inputStream, byteArrayOutputStream, byteArrayInputStream, byteArrayInputStream2};
                        DownloadUtils.safeClose((Closeable[]) i2);
                    }
                    DownloadUtils.safeClose((Closeable[]) i2);
                } catch (Throwable th6) {
                    th = th6;
                }
            }
        });
    }

    /* renamed from: mb */
    public static int m6843mb(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static ByteArrayOutputStream m6839ox(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream;
            }
        }
    }
}
