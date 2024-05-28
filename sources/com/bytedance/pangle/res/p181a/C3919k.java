package com.bytedance.pangle.res.p181a;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.res.a.k */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3919k {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m16687a(C3915g c3915g) {
        return (int) c3915g.f9329a.m16693a().m16700b();
    }

    /* renamed from: a */
    private static byte[] m16689a(int i) {
        return new byte[]{(byte) (i >> 0), (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m16686a(byte[] bArr, int i, int i2, InterfaceC3916h interfaceC3916h) {
        if (i >= 2130706432) {
            int mo16695a = interfaceC3916h.mo16695a(i);
            byte[] m16689a = m16689a(mo16695a);
            bArr[i2] = m16689a[0];
            bArr[i2 + 1] = m16689a[1];
            bArr[i2 + 2] = m16689a[2];
            bArr[i2 + 3] = m16689a[3];
            return mo16695a;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m16685a(byte[] bArr, InterfaceC3916h interfaceC3916h) {
        C3908b c3908b = new C3908b(bArr, interfaceC3916h);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        c3908b.m16713a();
        c3908b.f9305c = new C3915g(new C3917i(new C3913e(byteArrayInputStream)));
        do {
        } while (c3908b.m16712b() != 1);
    }

    /* renamed from: a */
    public static void m16688a(int i, byte[] bArr, int[] iArr, int i2, HashMap<Integer, Integer> hashMap) {
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        int i3 = -1;
        for (int i4 = 0; i4 < i2; i4++) {
            int i5 = i4 * 5;
            int i6 = iArr[i5 + 1];
            if (hashMap.containsKey(Integer.valueOf(i6))) {
                if (i3 == -1) {
                    i3 = i4;
                }
                int i7 = (i5 * 4) + i;
                hashMap2.put(Integer.valueOf(i4), Arrays.copyOfRange(bArr, i7, i7 + 20));
                hashMap3.put(Integer.valueOf(hashMap.get(Integer.valueOf(i6)).intValue()), Integer.valueOf(i4));
            }
        }
        ArrayList arrayList = new ArrayList(hashMap3.keySet());
        Collections.sort(arrayList);
        Iterator it = arrayList.iterator();
        int i8 = 0;
        while (it.hasNext()) {
            byte[] bArr2 = (byte[]) hashMap2.get(Integer.valueOf(((Integer) hashMap3.get((Integer) it.next())).intValue()));
            System.arraycopy(bArr2, 0, bArr, ((i8 + i3) * 5 * 4) + i, bArr2.length);
            i8++;
        }
    }
}
