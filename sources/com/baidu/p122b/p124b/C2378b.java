package com.baidu.p122b.p124b;

import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2378b implements Comparator<AbstractC2372a> {
    @Override // java.util.Comparator
    /* renamed from: a */
    public int compare(AbstractC2372a abstractC2372a, AbstractC2372a abstractC2372a2) {
        int i = ((abstractC2372a.m20352b() - abstractC2372a2.m20352b()) > 0L ? 1 : ((abstractC2372a.m20352b() - abstractC2372a2.m20352b()) == 0L ? 0 : -1));
        return i != 0 ? i > 0 ? -1 : 1 : abstractC2372a.m20354a().compareTo(abstractC2372a2.m20354a());
    }
}
