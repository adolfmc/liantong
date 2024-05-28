package com.networkbench.agent.impl.p268n;

import android.support.annotation.RequiresApi;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p266l.C6492b;
import com.networkbench.agent.impl.util.C6636f;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RunnableC6519e implements Runnable {

    /* renamed from: a */
    String f16613a;

    public RunnableC6519e(String str) {
        this.f16613a = str;
    }

    @Override // java.lang.Runnable
    @RequiresApi(api = 19)
    public void run() {
        try {
            C6396h.m10138d("NBSBrowserRunnable   send data  : " + this.f16613a);
            long nanoTime = System.nanoTime();
            C6492b.m9780a().m9778a(this.f16613a, nanoTime);
            boolean m9560a = m9560a(String.valueOf(nanoTime), new C6517d(this.f16613a));
            if (C6492b.m9780a().f16432b.booleanValue() || !m9560a) {
                return;
            }
            try {
                C6492b.m9780a().f16432b = true;
                Map<String, ?> m9777b = C6492b.m9780a().m9777b();
                for (String str : m9777b.keySet()) {
                    C6517d c6517d = new C6517d(C6636f.m9092c(String.valueOf(m9777b.get(str))));
                    C6396h.m10138d("NBSBrowserRunnable  url :  " + c6517d.f16609a);
                    C6396h.m10138d("NBSBrowserRunnable  body :  " + c6517d.f16611c);
                    if (!m9560a(C6636f.m9092c(str), c6517d)) {
                        C6492b.m9780a().f16432b = false;
                        return;
                    }
                }
            } catch (Throwable unused) {
            }
            C6492b.m9780a().f16432b = false;
        } catch (Throwable unused2) {
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:42:0x0100
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    private boolean m9560a(java.lang.String r10, com.networkbench.agent.impl.p268n.C6517d r11) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.p268n.RunnableC6519e.m9560a(java.lang.String, com.networkbench.agent.impl.n.d):boolean");
    }

    /* renamed from: a */
    private boolean m9562a(HarvestResponse harvestResponse) {
        return harvestResponse == null || harvestResponse.isUnknown() || !harvestResponse.isStatusCode200();
    }

    /* renamed from: a */
    private void m9561a(OutputStream outputStream, byte[] bArr) throws Exception {
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
    }

    /* renamed from: a */
    private void m9559a(HttpURLConnection httpURLConnection, Map<String, ?> map) {
        try {
            for (String str : map.keySet()) {
                String valueOf = String.valueOf(map.get(str));
                httpURLConnection.addRequestProperty(str, valueOf);
                C6396h.m10138d("setHttpURLConnectionHeader  key : " + str);
                C6396h.m10138d("setHttpURLConnectionHeader  value : " + valueOf);
            }
            httpURLConnection.addRequestProperty("User-Agent", NBSAgent.getDeviceInformation().initUserHeaderValue());
        } catch (Throwable unused) {
        }
    }
}
