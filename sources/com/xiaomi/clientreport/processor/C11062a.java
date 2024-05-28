package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.C11052a;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.manager.C11053a;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11197bl;
import com.xiaomi.push.C11425h;
import com.xiaomi.push.C11647w;
import com.xiaomi.push.C11650y;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* renamed from: com.xiaomi.clientreport.processor.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11062a implements IEventProcessor {

    /* renamed from: a */
    protected Context f21309a;

    /* renamed from: a */
    private HashMap<String, ArrayList<C11052a>> f21310a;

    public C11062a(Context context) {
        m5238a(context);
    }

    /* renamed from: a */
    public void m5238a(Context context) {
        this.f21309a = context;
    }

    /* renamed from: a */
    public static String m5237a(C11052a c11052a) {
        return String.valueOf(c11052a.production);
    }

    /* renamed from: a */
    public void mo4135a(List<String> list) {
        C11197bl.m4712a(this.f21309a, list);
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11064c
    /* renamed from: a */
    public void mo5226a() {
        int i;
        C11197bl.m4713a(this.f21309a, "event", "eventUploading");
        File[] m4714a = C11197bl.m4714a(this.f21309a, "eventUploading");
        if (m4714a == null || m4714a.length <= 0) {
            return;
        }
        int length = m4714a.length;
        FileLock fileLock = null;
        RandomAccessFile randomAccessFile = null;
        File file = null;
        while (i < length) {
            File file2 = m4714a[i];
            if (file2 == null) {
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e) {
                        AbstractC11049b.m5276a(e);
                    }
                }
                C11647w.m2274a(randomAccessFile);
                i = file == null ? i + 1 : 0;
                file.delete();
            } else {
                try {
                    try {
                    } catch (Exception e2) {
                        e = e2;
                    }
                    if (file2.length() > 5242880) {
                        AbstractC11049b.m5268d("eventData read from cache file failed because " + file2.getName() + " is too big, length " + file2.length());
                        m5234a(file2.getName(), Formatter.formatFileSize(this.f21309a, file2.length()));
                        file2.delete();
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException e3) {
                                AbstractC11049b.m5276a(e3);
                            }
                        }
                        C11647w.m2274a(randomAccessFile);
                        if (file == null) {
                        }
                        file.delete();
                    } else {
                        String absolutePath = file2.getAbsolutePath();
                        File file3 = new File(absolutePath + ".lock");
                        try {
                            C11647w.m2272a(file3);
                            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file3, "rw");
                            try {
                                fileLock = randomAccessFile2.getChannel().lock();
                                mo4135a(m5235a(absolutePath));
                                file2.delete();
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e4) {
                                        AbstractC11049b.m5276a(e4);
                                    }
                                }
                                C11647w.m2274a(randomAccessFile2);
                                file3.delete();
                                randomAccessFile = randomAccessFile2;
                                file = file3;
                            } catch (Exception e5) {
                                e = e5;
                                randomAccessFile = randomAccessFile2;
                                file = file3;
                                AbstractC11049b.m5276a(e);
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e6) {
                                        AbstractC11049b.m5276a(e6);
                                    }
                                }
                                C11647w.m2274a(randomAccessFile);
                                if (file == null) {
                                }
                                file.delete();
                            } catch (Throwable th) {
                                th = th;
                                randomAccessFile = randomAccessFile2;
                                file = file3;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e7) {
                                        AbstractC11049b.m5276a(e7);
                                    }
                                }
                                C11647w.m2274a(randomAccessFile);
                                if (file != null) {
                                    file.delete();
                                }
                                throw th;
                            }
                        } catch (Exception e8) {
                            e = e8;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
    }

    /* renamed from: a */
    private void m5234a(String str, String str2) {
        C11053a m5261a = C11053a.m5261a(this.f21309a);
        EventClientReport m5262a = m5261a.m5262a(5001, "24:" + str + "," + str2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(m5262a.toJsonString());
        mo4135a(arrayList);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x006b, code lost:
        com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d("eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K");
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<java.lang.String> m5235a(java.lang.String r8) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 4
            byte[] r2 = new byte[r1]
            byte[] r3 = new byte[r1]
            r4 = 0
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7c
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7c
            r6.<init>(r8)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7c
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7c
        L15:
            int r8 = r5.read(r2)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r4 = -1
            if (r8 != r4) goto L1d
            goto L70
        L1d:
            if (r8 == r1) goto L25
            java.lang.String r8 = "eventData read from cache file failed because magicNumber error"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L70
        L25:
            int r8 = com.xiaomi.push.C11650y.m2260a(r2)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r6 = -573785174(0xffffffffddccbbaa, float:-1.84407149E18)
            if (r8 == r6) goto L34
            java.lang.String r8 = "eventData read from cache file failed because magicNumber error"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L70
        L34:
            int r8 = r5.read(r3)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            if (r8 != r4) goto L3b
            goto L70
        L3b:
            if (r8 == r1) goto L43
            java.lang.String r8 = "eventData read from cache file failed cause lengthBuffer error"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L70
        L43:
            int r8 = com.xiaomi.push.C11650y.m2260a(r3)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            r4 = 1
            if (r8 < r4) goto L6b
            r4 = 4096(0x1000, float:5.74E-42)
            if (r8 <= r4) goto L4f
            goto L6b
        L4f:
            byte[] r4 = new byte[r8]     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            int r6 = r5.read(r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            if (r6 == r8) goto L5d
            java.lang.String r8 = "eventData read from cache file failed cause buffer size not equal length"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L70
        L5d:
            java.lang.String r8 = r7.bytesToString(r4)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            boolean r4 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            if (r4 != 0) goto L15
            r0.add(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
            goto L15
        L6b:
            java.lang.String r8 = "eventData read from cache file failed cause lengthBuffer < 1 || lengthBuffer > 4K"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r8)     // Catch: java.lang.Throwable -> L74 java.lang.Exception -> L76
        L70:
            com.xiaomi.push.C11647w.m2274a(r5)
            goto L83
        L74:
            r8 = move-exception
            goto L84
        L76:
            r8 = move-exception
            r4 = r5
            goto L7d
        L79:
            r8 = move-exception
            r5 = r4
            goto L84
        L7c:
            r8 = move-exception
        L7d:
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r8)     // Catch: java.lang.Throwable -> L79
            com.xiaomi.push.C11647w.m2274a(r4)
        L83:
            return r0
        L84:
            com.xiaomi.push.C11647w.m2274a(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.C11062a.m5235a(java.lang.String):java.util.List");
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public String bytesToString(byte[] bArr) {
        byte[] m4711a;
        if (bArr == null || bArr.length < 1) {
            return null;
        }
        if (!C11053a.m5261a(this.f21309a).m5264a().isEventEncrypted()) {
            return C11184bb.m4747b(bArr);
        }
        String m4718a = C11197bl.m4718a(this.f21309a);
        if (!TextUtils.isEmpty(m4718a) && (m4711a = C11197bl.m4711a(m4718a)) != null && m4711a.length > 0) {
            try {
                return C11184bb.m4747b(Base64.decode(C11425h.m3410a(m4711a, bArr), 2));
            } catch (InvalidAlgorithmParameterException e) {
                AbstractC11049b.m5276a(e);
            } catch (InvalidKeyException e2) {
                AbstractC11049b.m5276a(e2);
            } catch (NoSuchAlgorithmException e3) {
                AbstractC11049b.m5276a(e3);
            } catch (BadPaddingException e4) {
                AbstractC11049b.m5276a(e4);
            } catch (IllegalBlockSizeException e5) {
                AbstractC11049b.m5276a(e5);
            } catch (NoSuchPaddingException e6) {
                AbstractC11049b.m5276a(e6);
            }
        }
        return null;
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public byte[] stringToBytes(String str) {
        byte[] m4711a;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!C11053a.m5261a(this.f21309a).m5264a().isEventEncrypted()) {
            return C11184bb.m4755a(str);
        }
        String m4718a = C11197bl.m4718a(this.f21309a);
        byte[] m4755a = C11184bb.m4755a(str);
        if (!TextUtils.isEmpty(m4718a) && m4755a != null && m4755a.length > 1 && (m4711a = C11197bl.m4711a(m4718a)) != null) {
            try {
                if (m4711a.length > 1) {
                    return C11425h.m3409b(m4711a, Base64.encode(m4755a, 2));
                }
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
            }
        }
        return null;
    }

    @Override // com.xiaomi.clientreport.processor.IEventProcessor
    public void setEventMap(HashMap<String, ArrayList<C11052a>> hashMap) {
        this.f21310a = hashMap;
    }

    /* renamed from: a */
    public void m5233a(C11052a[] c11052aArr) {
        if (c11052aArr == null || c11052aArr.length == 0 || c11052aArr[0] == null) {
            AbstractC11049b.m5282a("event data write to cache file failed because data null");
            return;
        }
        do {
            c11052aArr = m5232a(c11052aArr);
            if (c11052aArr == null || c11052aArr.length <= 0) {
                return;
            }
        } while (c11052aArr[0] != null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.nio.channels.FileLock] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* renamed from: a */
    private C11052a[] m5232a(C11052a[] c11052aArr) {
        Closeable closeable;
        RandomAccessFile randomAccessFile;
        BufferedOutputStream bufferedOutputStream;
        String m5231b = m5231b(c11052aArr[0]);
        FileLock isEmpty = TextUtils.isEmpty(m5231b);
        try {
            if (isEmpty != 0) {
                return null;
            }
            try {
                File file = new File(m5231b + ".lock");
                C11647w.m2272a(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    isEmpty = randomAccessFile.getChannel().lock();
                    try {
                        bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(m5231b), true));
                    } catch (Exception e) {
                        e = e;
                        bufferedOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        closeable = null;
                        C11647w.m2274a(closeable);
                        m5236a(randomAccessFile, isEmpty);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    isEmpty = 0;
                    bufferedOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    isEmpty = 0;
                    closeable = null;
                }
            } catch (Exception e3) {
                e = e3;
                isEmpty = 0;
                randomAccessFile = null;
                bufferedOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                isEmpty = 0;
                randomAccessFile = null;
                closeable = null;
            }
            try {
                int i = 0;
                for (C11052a c11052a : c11052aArr) {
                    if (c11052a != null) {
                        byte[] stringToBytes = stringToBytes(c11052a.toJsonString());
                        if (stringToBytes != null && stringToBytes.length >= 1 && stringToBytes.length <= 4096) {
                            if (!C11197bl.m4715a(this.f21309a, m5231b)) {
                                int length = c11052aArr.length - i;
                                C11052a[] c11052aArr2 = new C11052a[length];
                                System.arraycopy(c11052aArr, i, c11052aArr2, 0, length);
                                C11647w.m2274a(bufferedOutputStream);
                                m5236a(randomAccessFile, (FileLock) isEmpty);
                                return c11052aArr2;
                            }
                            bufferedOutputStream.write(C11650y.m2261a(-573785174));
                            bufferedOutputStream.write(C11650y.m2261a(stringToBytes.length));
                            bufferedOutputStream.write(stringToBytes);
                            bufferedOutputStream.flush();
                            i++;
                        }
                        AbstractC11049b.m5268d("event data throw a invalid item ");
                    }
                }
            } catch (Exception e4) {
                e = e4;
                AbstractC11049b.m5279a("event data write to cache file failed cause exception", e);
                C11647w.m2274a(bufferedOutputStream);
                m5236a(randomAccessFile, isEmpty);
                return null;
            }
            C11647w.m2274a(bufferedOutputStream);
            m5236a(randomAccessFile, isEmpty);
            return null;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    private void m5236a(RandomAccessFile randomAccessFile, FileLock fileLock) {
        if (fileLock != null && fileLock.isValid()) {
            try {
                fileLock.release();
            } catch (IOException e) {
                AbstractC11049b.m5276a(e);
            }
        }
        C11647w.m2274a(randomAccessFile);
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11065d
    /* renamed from: b */
    public void mo5224b() {
        HashMap<String, ArrayList<C11052a>> hashMap = this.f21310a;
        if (hashMap == null) {
            return;
        }
        if (hashMap.size() > 0) {
            for (String str : this.f21310a.keySet()) {
                ArrayList<C11052a> arrayList = this.f21310a.get(str);
                if (arrayList != null && arrayList.size() > 0) {
                    C11052a[] c11052aArr = new C11052a[arrayList.size()];
                    arrayList.toArray(c11052aArr);
                    m5233a(c11052aArr);
                }
            }
        }
        this.f21310a.clear();
    }

    @Override // com.xiaomi.clientreport.processor.InterfaceC11065d
    /* renamed from: a */
    public void mo5225a(C11052a c11052a) {
        if ((c11052a instanceof EventClientReport) && this.f21310a != null) {
            EventClientReport eventClientReport = (EventClientReport) c11052a;
            String m5237a = m5237a((C11052a) eventClientReport);
            ArrayList<C11052a> arrayList = this.f21310a.get(m5237a);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(eventClientReport);
            this.f21310a.put(m5237a, arrayList);
        }
    }

    /* renamed from: b */
    private String m5231b(C11052a c11052a) {
        File file = new File(this.f21309a.getFilesDir(), "event");
        String str = file.getAbsolutePath() + File.separator + m5237a(c11052a);
        for (int i = 0; i < 100; i++) {
            String str2 = str + i;
            if (C11197bl.m4715a(this.f21309a, str2)) {
                return str2;
            }
        }
        return null;
    }
}
