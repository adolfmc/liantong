package com.megvii.lv5;

import com.megvii.lv5.C5511m4;
import com.megvii.lv5.InterfaceC5509m3;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.t4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5617t4 implements InterfaceC5549r3 {

    /* renamed from: c */
    public static final boolean f13738c = C5440f4.f12600a;

    /* renamed from: a */
    public final InterfaceC5640w4 f13739a;

    /* renamed from: b */
    public final C5623u4 f13740b;

    public C5617t4(InterfaceC5640w4 interfaceC5640w4, C5623u4 c5623u4) {
        this.f13739a = interfaceC5640w4;
        this.f13740b = c5623u4;
    }

    /* renamed from: a */
    public static Map<String, String> m12989a(InterfaceC5466h4[] interfaceC5466h4Arr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < interfaceC5466h4Arr.length; i++) {
            treeMap.put(interfaceC5466h4Arr[i].mo13215c(), interfaceC5466h4Arr[i].mo13216b());
        }
        return treeMap;
    }

    /* renamed from: a */
    public final void m12990a(Map<String, String> map, InterfaceC5509m3.C5510a c5510a) {
        if (c5510a == null) {
            return;
        }
        String str = c5510a.f12888b;
        if (str != null) {
            map.put("If-None-Match", str);
        }
        if (c5510a.f12890d > 0) {
            Date date = new Date(c5510a.f12890d);
            String[] strArr = C5511m4.f12894a;
            C5527o2.m13246a(date, "Date");
            C5527o2.m13246a("EEE, dd MMM yyyy HH:mm:ss zzz", "Pattern");
            map.put("If-Modified-Since", C5511m4.C5512a.m13436a("EEE, dd MMM yyyy HH:mm:ss zzz").format(date));
        }
    }

    /* renamed from: a */
    public final byte[] m12993a(InterfaceC5478i4 interfaceC5478i4) {
        C5401c5 c5401c5 = new C5401c5(this.f13740b, (int) interfaceC5478i4.mo13278c());
        try {
            InputStream mo13279b = interfaceC5478i4.mo13279b();
            if (mo13279b != null) {
                byte[] m12975a = this.f13740b.m12975a(1024);
                while (true) {
                    int read = mo13279b.read(m12975a);
                    if (read == -1) {
                        break;
                    }
                    c5401c5.write(m12975a, 0, read);
                }
                byte[] byteArray = c5401c5.toByteArray();
                try {
                    interfaceC5478i4.mo13280a();
                } catch (IOException unused) {
                    C5440f4.m13535b("Error occurred when calling consumingContent", new Object[0]);
                }
                this.f13740b.m12974a(m12975a);
                c5401c5.close();
                return byteArray;
            }
            throw new C5389b4();
        } catch (Throwable th) {
            try {
                interfaceC5478i4.mo13280a();
            } catch (IOException unused2) {
                C5440f4.m13535b("Error occurred when calling consumingContent", new Object[0]);
            }
            this.f13740b.m12974a((byte[]) null);
            c5401c5.close();
            throw th;
        }
    }

    /* renamed from: a */
    public static void m12991a(String str, AbstractC5652x3<?> abstractC5652x3, C5416d4 c5416d4) {
        C5535p3 c5535p3 = abstractC5652x3.f13911j;
        int i = c5535p3.f13171a;
        try {
            int i2 = c5535p3.f13172b + 1;
            c5535p3.f13172b = i2;
            float f = i;
            c5535p3.f13171a = (int) (f + (1.0f * f));
            if (!(i2 <= c5535p3.f13173c)) {
                throw c5416d4;
            }
            abstractC5652x3.m12899a(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(i)));
        } catch (C5416d4 e) {
            abstractC5652x3.m12899a(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(i)));
            throw e;
        }
    }

    /* renamed from: a */
    public final void m12994a(long j, AbstractC5652x3<?> abstractC5652x3, byte[] bArr, InterfaceC5499l4 interfaceC5499l4) {
        if (f13738c || j > 3000) {
            Object[] objArr = new Object[5];
            objArr[0] = abstractC5652x3;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(interfaceC5499l4.mo13171a());
            objArr[4] = Integer.valueOf(abstractC5652x3.f13911j.f13172b);
            C5440f4.m13536a("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b0, code lost:
        throw new java.io.IOException();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0135 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.megvii.lv5.C5622u3 m12992a(com.megvii.lv5.AbstractC5652x3<?> r23) {
        /*
            Method dump skipped, instructions count: 354
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.C5617t4.m12992a(com.megvii.lv5.x3):com.megvii.lv5.u3");
    }
}
