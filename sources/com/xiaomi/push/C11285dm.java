package com.xiaomi.push;

import android.content.Context;

/* renamed from: com.xiaomi.push.dm */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11285dm extends AbstractC11284dl {
    @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
    /* renamed from: a */
    public String mo2289a() {
        return "23";
    }

    public C11285dm(Context context, int i) {
        super(context, i);
    }

    @Override // com.xiaomi.push.AbstractC11284dl
    /* renamed from: b */
    public String mo4339b() {
        return "ram:" + C11455i.m3055a() + ",rom:" + C11455i.m3042b() + "|ramOriginal:" + C11455i.m3038c() + ",romOriginal:" + C11455i.m3035d();
    }

    @Override // com.xiaomi.push.AbstractC11284dl
    /* renamed from: a */
    public EnumC11406gh mo4340a() {
        return EnumC11406gh.Storage;
    }
}
