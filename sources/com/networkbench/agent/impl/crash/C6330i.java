package com.networkbench.agent.impl.crash;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6330i {

    /* renamed from: d */
    private static final int f15939d = 32768;

    /* renamed from: c */
    private static InterfaceC6393e f15938c = C6394f.m10150a();

    /* renamed from: a */
    public static String f15936a = "";

    /* renamed from: b */
    public static int f15937b = 50;

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0077, code lost:
        if (r0 != null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0079, code lost:
        r0.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x009a, code lost:
        if (r0 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a1, code lost:
        return r1.toString();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m10379a() {
        /*
            boolean r0 = com.networkbench.agent.impl.util.C6638h.f17115o     // Catch: java.lang.Throwable -> La8
            if (r0 != 0) goto Le
            com.networkbench.agent.impl.f.e r0 = com.networkbench.agent.impl.crash.C6330i.f15938c     // Catch: java.lang.Throwable -> La8
            java.lang.String r1 = "logcat's switch was disable !"
            r0.mo10122a(r1)     // Catch: java.lang.Throwable -> La8
            java.lang.String r0 = ""
            return r0
        Le:
            r0 = 0
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> La8
            r1.<init>()     // Catch: java.lang.Throwable -> La8
            int r2 = com.networkbench.agent.impl.crash.C6330i.f15937b     // Catch: java.lang.Throwable -> La8
            r3 = 100
            if (r2 <= r3) goto L1b
            goto L1d
        L1b:
            int r3 = com.networkbench.agent.impl.crash.C6330i.f15937b     // Catch: java.lang.Throwable -> La8
        L1d:
            java.lang.String r2 = com.networkbench.agent.impl.crash.C6330i.f15936a     // Catch: java.lang.Throwable -> La8
            boolean r4 = m10378a(r2)     // Catch: java.lang.Throwable -> La8
            if (r4 != 0) goto L30
            java.lang.String r4 = "*:W"
            com.networkbench.agent.impl.crash.C6330i.f15936a = r4     // Catch: java.lang.Throwable -> La8
            com.networkbench.agent.impl.f.e r4 = com.networkbench.agent.impl.crash.C6330i.f15938c     // Catch: java.lang.Throwable -> La8
            java.lang.String r5 = "LOG_FILTER's format was wrong,set filter to *:W  !"
            r4.mo10122a(r5)     // Catch: java.lang.Throwable -> La8
        L30:
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> La8
            r5 = 0
            java.lang.String r6 = "logcat"
            r4[r5] = r6     // Catch: java.lang.Throwable -> La8
            r5 = 1
            java.lang.String r6 = "-t"
            r4[r5] = r6     // Catch: java.lang.Throwable -> La8
            r5 = 2
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La8
            r6.<init>()     // Catch: java.lang.Throwable -> La8
            r6.append(r3)     // Catch: java.lang.Throwable -> La8
            java.lang.String r3 = ""
            r6.append(r3)     // Catch: java.lang.Throwable -> La8
            java.lang.String r3 = r6.toString()     // Catch: java.lang.Throwable -> La8
            r4[r5] = r3     // Catch: java.lang.Throwable -> La8
            r3 = 3
            r4[r3] = r2     // Catch: java.lang.Throwable -> La8
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            java.lang.Process r0 = r2.exec(r4)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            com.networkbench.agent.impl.crash.i$a r2 = new com.networkbench.agent.impl.crash.i$a     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            java.io.InputStream r3 = r0.getInputStream()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            r2.start()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            com.networkbench.agent.impl.crash.i$a r2 = new com.networkbench.agent.impl.crash.i$a     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            java.io.InputStream r3 = r0.getErrorStream()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            r2.start()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            r0.waitFor()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L7f
            if (r0 == 0) goto L9d
        L79:
            r0.destroy()     // Catch: java.lang.Throwable -> La8
            goto L9d
        L7d:
            r1 = move-exception
            goto La2
        L7f:
            r2 = move-exception
            com.networkbench.agent.impl.f.e r3 = com.networkbench.agent.impl.crash.C6330i.f15938c     // Catch: java.lang.Throwable -> L7d
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7d
            r4.<init>()     // Catch: java.lang.Throwable -> L7d
            java.lang.String r5 = "Error reading logcat output!"
            r4.append(r5)     // Catch: java.lang.Throwable -> L7d
            java.lang.String r2 = r2.getLocalizedMessage()     // Catch: java.lang.Throwable -> L7d
            r4.append(r2)     // Catch: java.lang.Throwable -> L7d
            java.lang.String r2 = r4.toString()     // Catch: java.lang.Throwable -> L7d
            r3.mo10122a(r2)     // Catch: java.lang.Throwable -> L7d
            if (r0 == 0) goto L9d
            goto L79
        L9d:
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> La8
            return r0
        La2:
            if (r0 == 0) goto La7
            r0.destroy()     // Catch: java.lang.Throwable -> La8
        La7:
            throw r1     // Catch: java.lang.Throwable -> La8
        La8:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.crash.C6330i.m10379a():java.lang.String");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.crash.i$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C6331a extends Thread {

        /* renamed from: a */
        private InputStream f15940a;

        /* renamed from: b */
        private StringBuffer f15941b;

        public C6331a(InputStream inputStream, StringBuffer stringBuffer) {
            this.f15940a = inputStream;
            this.f15941b = stringBuffer;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f15940a));
            int i = 32768;
            while (true) {
                try {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null || (i = i - readLine.getBytes("UTF-8").length) < 0) {
                            break;
                        }
                        this.f15941b.append(readLine);
                        this.f15941b.append("\n");
                    } catch (IOException unused) {
                        bufferedReader.close();
                        return;
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            bufferedReader.close();
        }
    }

    /* renamed from: a */
    private static boolean m10378a(String str) {
        String[] strArr = {"*:S", "*:V", "*:D", "*:I", "*:W", "*:E"};
        if (str.contains("*:")) {
            for (String str2 : strArr) {
                if (str.trim().endsWith(str2)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
