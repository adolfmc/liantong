package com.xiaomi.clientreport.processor;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.C11052a;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.push.C11647w;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.clientreport.processor.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11066e {
    /* renamed from: a */
    public static void m5215a(String str, C11052a[] c11052aArr) {
        RandomAccessFile randomAccessFile;
        if (c11052aArr == null || c11052aArr.length <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        FileLock fileLock = null;
        try {
            File file = new File(str + ".lock");
            C11647w.m2272a(file);
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                fileLock = randomAccessFile.getChannel().lock();
                HashMap<String, String> m5219a = m5219a(str);
                for (C11052a c11052a : c11052aArr) {
                    if (c11052a != null) {
                        String m5222a = m5222a((PerfClientReport) c11052a);
                        long j = ((PerfClientReport) c11052a).perfCounts;
                        long j2 = ((PerfClientReport) c11052a).perfLatencies;
                        if (!TextUtils.isEmpty(m5222a) && j > 0 && j2 >= 0) {
                            m5214a(m5219a, m5222a, j, j2);
                        }
                    }
                }
                m5216a(str, m5219a);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e) {
                        e = e;
                        AbstractC11049b.m5276a(e);
                        C11647w.m2274a(randomAccessFile);
                    }
                }
            } catch (Throwable unused) {
                try {
                    AbstractC11049b.m5270c("failed to write perf to file ");
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e2) {
                            e = e2;
                            AbstractC11049b.m5276a(e);
                            C11647w.m2274a(randomAccessFile);
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                } catch (Throwable th) {
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e3) {
                            AbstractC11049b.m5276a(e3);
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                    throw th;
                }
            }
        } catch (Throwable unused2) {
            randomAccessFile = null;
        }
        C11647w.m2274a(randomAccessFile);
    }

    /* renamed from: a */
    private static void m5214a(HashMap<String, String> hashMap, String str, long j, long j2) {
        String str2;
        String str3 = hashMap.get(str);
        if (TextUtils.isEmpty(str3)) {
            hashMap.put(str, j + "#" + j2);
            return;
        }
        long[] m5218a = m5218a(str3);
        if (m5218a == null || m5218a[0] <= 0 || m5218a[1] < 0) {
            str2 = j + "#" + j2;
        } else {
            str2 = (j + m5218a[0]) + "#" + (j2 + m5218a[1]);
        }
        hashMap.put(str, str2);
    }

    /* renamed from: a */
    protected static long[] m5218a(String str) {
        long[] jArr = new long[2];
        try {
            String[] split = str.split("#");
            if (split.length >= 2) {
                jArr[0] = Long.parseLong(split[0].trim());
                jArr[1] = Long.parseLong(split[1].trim());
            }
            return jArr;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return null;
        }
    }

    /* renamed from: a */
    private static void m5216a(String str, HashMap<String, String> hashMap) {
        BufferedWriter bufferedWriter;
        Throwable th;
        Exception e;
        if (TextUtils.isEmpty(str) || hashMap == null || hashMap.size() == 0) {
            return;
        }
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
        } catch (Exception e2) {
            bufferedWriter = null;
            e = e2;
        } catch (Throwable th2) {
            bufferedWriter = null;
            th = th2;
            C11647w.m2274a(bufferedWriter);
            throw th;
        }
        try {
            try {
                for (String str2 : hashMap.keySet()) {
                    bufferedWriter.write(str2 + "%%%" + hashMap.get(str2));
                    bufferedWriter.newLine();
                }
            } catch (Throwable th3) {
                th = th3;
                C11647w.m2274a(bufferedWriter);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            AbstractC11049b.m5276a(e);
            C11647w.m2274a(bufferedWriter);
        }
        C11647w.m2274a(bufferedWriter);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v9, types: [java.lang.Object] */
    /* renamed from: a */
    private static HashMap<String, String> m5219a(String str) {
        BufferedReader bufferedReader;
        HashMap hashMap = new HashMap();
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return hashMap;
        }
        BufferedReader bufferedReader2 = 0;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        String[] split = readLine.split("%%%");
                        bufferedReader2 = split.length;
                        if (bufferedReader2 >= 2) {
                            bufferedReader2 = 0;
                            bufferedReader2 = 0;
                            if (!TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
                                bufferedReader2 = split[0];
                                hashMap.put(bufferedReader2, split[1]);
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedReader2 = bufferedReader;
                        AbstractC11049b.m5276a(e);
                        C11647w.m2274a(bufferedReader2);
                        return hashMap;
                    } catch (Throwable th) {
                        th = th;
                        C11647w.m2274a(bufferedReader);
                        throw th;
                    }
                }
                C11647w.m2274a(bufferedReader);
            } catch (Exception e2) {
                e = e2;
            }
            return hashMap;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReader2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:65:0x00d3, code lost:
        if (r1 != null) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f4  */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.io.Closeable, java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<java.lang.String> m5223a(android.content.Context r7, java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.clientreport.processor.C11066e.m5223a(android.content.Context, java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private static String[] m5217a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("#");
    }

    /* renamed from: a */
    private static PerfClientReport m5220a(String str) {
        PerfClientReport perfClientReport = null;
        try {
            String[] m5217a = m5217a(str);
            if (m5217a == null || m5217a.length < 4 || TextUtils.isEmpty(m5217a[0]) || TextUtils.isEmpty(m5217a[1]) || TextUtils.isEmpty(m5217a[2]) || TextUtils.isEmpty(m5217a[3])) {
                return null;
            }
            perfClientReport = PerfClientReport.getBlankInstance();
            perfClientReport.production = Integer.parseInt(m5217a[0]);
            perfClientReport.clientInterfaceId = m5217a[1];
            perfClientReport.reportType = Integer.parseInt(m5217a[2]);
            perfClientReport.code = Integer.parseInt(m5217a[3]);
            return perfClientReport;
        } catch (Exception unused) {
            AbstractC11049b.m5270c("parse per key error");
            return perfClientReport;
        }
    }

    /* renamed from: a */
    private static PerfClientReport m5221a(PerfClientReport perfClientReport, String str) {
        long[] m5218a;
        if (perfClientReport == null || (m5218a = m5218a(str)) == null) {
            return null;
        }
        perfClientReport.perfCounts = m5218a[0];
        perfClientReport.perfLatencies = m5218a[1];
        return perfClientReport;
    }

    /* renamed from: a */
    public static String m5222a(PerfClientReport perfClientReport) {
        return perfClientReport.production + "#" + perfClientReport.clientInterfaceId + "#" + perfClientReport.reportType + "#" + perfClientReport.code;
    }
}
