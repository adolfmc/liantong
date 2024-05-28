package com.baidu.p122b;

import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2414d implements Comparator<C2371b> {

    /* renamed from: a */
    final /* synthetic */ C2393c f4260a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2414d(C2393c c2393c) {
        this.f4260a = c2393c;
    }

    @Override // java.util.Comparator
    /* renamed from: a */
    public int compare(C2371b c2371b, C2371b c2371b2) {
        int i = c2371b2.f4124b - c2371b.f4124b;
        if (i == 0) {
            if (c2371b.f4126d && c2371b2.f4126d) {
                return 0;
            }
            if (c2371b.f4126d) {
                return -1;
            }
            if (c2371b2.f4126d) {
                return 1;
            }
        }
        return i;
    }
}
