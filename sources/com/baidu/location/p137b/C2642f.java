package com.baidu.location.p137b;

import com.baidu.location.p137b.C2634e;
import java.util.Comparator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2642f implements Comparator<C2634e.C2639d> {

    /* renamed from: a */
    final /* synthetic */ C2634e f5229a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2642f(C2634e c2634e) {
        this.f5229a = c2634e;
    }

    @Override // java.util.Comparator
    /* renamed from: a */
    public int compare(C2634e.C2639d c2639d, C2634e.C2639d c2639d2) {
        if (c2639d.f5221b > c2639d2.f5221b) {
            return -1;
        }
        return c2639d.f5221b == c2639d2.f5221b ? 0 : 1;
    }
}
