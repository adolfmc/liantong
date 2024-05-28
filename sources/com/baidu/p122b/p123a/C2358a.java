package com.baidu.p122b.p123a;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2358a {

    /* renamed from: a */
    AbstractC2365g[] f4099a = {new C2366h(8, 0), new C2369j(0, 1), new C2369j(1, 1), new C2366h(7, 1)};

    /* renamed from: a */
    public byte[] m20412a(byte[] bArr) {
        C2364f c2364f = new C2364f();
        byte[] m20385a = C2360c.m20385a(bArr, bArr.length + ((this.f4099a.length + 1) * C2364f.f4105a));
        C2360c.m20384a(m20385a, c2364f.m20373a(), bArr.length);
        int i = 0;
        while (true) {
            AbstractC2365g[] abstractC2365gArr = this.f4099a;
            if (i >= abstractC2365gArr.length) {
                return Arrays.copyOf(c2364f.m20373a(), C2364f.f4105a);
            }
            AbstractC2365g abstractC2365g = abstractC2365gArr[i];
            i++;
            int length = bArr.length + (C2364f.f4105a * i);
            c2364f.m20372a(abstractC2365g.mo20361a(m20385a, 0, length), abstractC2365g.m20371a(), abstractC2365g.m20370b(), abstractC2365g.m20369c());
            C2360c.m20384a(m20385a, c2364f.m20373a(), length);
        }
    }
}
