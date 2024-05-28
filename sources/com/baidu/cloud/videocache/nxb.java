package com.baidu.cloud.videocache;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class nxb {

    /* renamed from: e */
    private static final Pattern f4900e = Pattern.compile("[R,r]ange:[ ]?bytes=(\\d*)-");

    /* renamed from: f */
    private static final Pattern f4901f = Pattern.compile("GET /(.*) HTTP");

    /* renamed from: a */
    public final String f4902a;

    /* renamed from: b */
    public final long f4903b;

    /* renamed from: c */
    public final boolean f4904c;

    /* renamed from: d */
    public final String f4905d;

    public nxb(String str) {
        C2571g.m19807a(str);
        long m19774a = m19774a(str);
        this.f4903b = Math.max(0L, m19774a);
        this.f4904c = m19774a >= 0;
        this.f4902a = m19773b(str);
        this.f4905d = str;
    }

    /* renamed from: a */
    private long m19774a(String str) {
        Matcher matcher = f4900e.matcher(str);
        if (matcher.find()) {
            return Long.parseLong(matcher.group(1));
        }
        return -1L;
    }

    /* renamed from: a */
    public static nxb m19775a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (TextUtils.isEmpty(readLine)) {
                return new nxb(sb.toString());
            }
            sb.append(readLine);
            sb.append('\n');
        }
    }

    /* renamed from: b */
    private String m19773b(String str) {
        Matcher matcher = f4901f.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Invalid request `" + str + "`: url not found!");
    }

    public String toString() {
        return "GetRequest{rangeOffset=" + this.f4903b + ", partial=" + this.f4904c + ", uri='" + this.f4902a + "'}";
    }
}
