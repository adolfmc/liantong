package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11230ce;
import com.xiaomi.push.C11287do;
import com.xiaomi.push.C11333eo;
import com.xiaomi.push.C11336ep;
import com.xiaomi.push.C11647w;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.z */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11641z {

    /* renamed from: a */
    private static final Pattern f23796a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");

    /* renamed from: a */
    private static long f23794a = 0;

    /* renamed from: a */
    private static ThreadPoolExecutor f23795a = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());

    /* renamed from: a */
    public static void m2286a() {
        C11287do.C11288a m2626a;
        long currentTimeMillis = System.currentTimeMillis();
        if ((f23795a.getActiveCount() <= 0 || currentTimeMillis - f23794a >= 1800000) && C11333eo.m3990a().m3988a() && (m2626a = C11571ax.m2625a().m2626a()) != null && m2626a.m4318e() > 0) {
            f23794a = currentTimeMillis;
            m2283a(m2626a.m4332a(), true);
        }
    }

    /* renamed from: a */
    public static void m2283a(final List<String> list, final boolean z) {
        f23795a.execute(new Runnable() { // from class: com.xiaomi.push.service.z.1
            @Override // java.lang.Runnable
            public void run() {
                boolean m2281b = C11641z.m2281b("www.baidu.com:80");
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    m2281b = m2281b || C11641z.m2281b((String) it.next());
                    if (m2281b && !z) {
                        break;
                    }
                }
                C11336ep.m3979a(m2281b ? 1 : 2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static boolean m2281b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            AbstractC11049b.m5282a("ConnectivityTest: begin to connect to " + str);
            Socket socket = new Socket();
            socket.connect(C11230ce.m4575a(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
            AbstractC11049b.m5282a("ConnectivityTest: connect to " + str + " in " + currentTimeMillis2);
            socket.close();
            return true;
        } catch (Throwable th) {
            AbstractC11049b.m5268d("ConnectivityTest: could not connect to:" + str + " exception: " + th.getClass().getSimpleName() + " description: " + th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private static String m2285a(String str) {
        BufferedReader bufferedReader;
        Throwable th;
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
        } catch (Exception unused) {
            bufferedReader = null;
        } catch (Throwable th2) {
            bufferedReader = null;
            th = th2;
        }
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append("\n");
                    sb.append(readLine);
                } else {
                    String sb2 = sb.toString();
                    C11647w.m2274a(bufferedReader);
                    return sb2;
                }
            }
        } catch (Exception unused2) {
            C11647w.m2274a(bufferedReader);
            return null;
        } catch (Throwable th3) {
            th = th3;
            C11647w.m2274a(bufferedReader);
            throw th;
        }
    }

    /* renamed from: b */
    public static void m2282b() {
        String m2285a = m2285a("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(m2285a)) {
            AbstractC11049b.m5282a("dump tcp for uid = " + Process.myUid());
            AbstractC11049b.m5282a(m2285a);
        }
        String m2285a2 = m2285a("/proc/self/net/tcp6");
        if (TextUtils.isEmpty(m2285a2)) {
            return;
        }
        AbstractC11049b.m5282a("dump tcp6 for uid = " + Process.myUid());
        AbstractC11049b.m5282a(m2285a2);
    }
}
