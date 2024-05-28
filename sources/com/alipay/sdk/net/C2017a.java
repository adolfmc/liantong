package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.net.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2017a {

    /* renamed from: a */
    private static final String f3766a = "msp";

    /* renamed from: b */
    private static final String f3767b = "application/octet-stream;binary/octet-stream";

    /* renamed from: c */
    private static final CookieManager f3768c = new CookieManager();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.net.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2018a {

        /* renamed from: a */
        public final String f3769a;

        /* renamed from: b */
        public final byte[] f3770b;

        /* renamed from: c */
        public final Map<String, String> f3771c;

        public C2018a(String str, Map<String, String> map, byte[] bArr) {
            this.f3769a = str;
            this.f3770b = bArr;
            this.f3771c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s requestBody=%s headers=%s>", this.f3769a, this.f3770b, this.f3771c);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.net.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C2019b {

        /* renamed from: a */
        public final Map<String, List<String>> f3772a;

        /* renamed from: b */
        public final String f3773b;

        /* renamed from: c */
        public final byte[] f3774c;

        public C2019b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.f3772a = map;
            this.f3773b = str;
            this.f3774c = bArr;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    public static com.alipay.sdk.net.C2017a.C2019b m20825a(android.content.Context r12, com.alipay.sdk.net.C2017a.C2018a r13) {
        /*
            Method dump skipped, instructions count: 487
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.net.C2017a.m20825a(android.content.Context, com.alipay.sdk.net.a$a):com.alipay.sdk.net.a$b");
    }

    /* renamed from: a */
    private static Proxy m20826a(Context context) {
        String m20822c = m20822c(context);
        if (m20822c == null || m20822c.contains("wap")) {
            try {
                String property = System.getProperty("https.proxyHost");
                String property2 = System.getProperty("https.proxyPort");
                if (TextUtils.isEmpty(property)) {
                    return null;
                }
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static NetworkInfo m20823b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    private static String m20822c(Context context) {
        try {
            NetworkInfo m20823b = m20823b(context);
            return (m20823b == null || !m20823b.isAvailable()) ? "none" : m20823b.getType() == 1 ? "wifi" : m20823b.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }

    /* renamed from: a */
    private static byte[] m20824a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
