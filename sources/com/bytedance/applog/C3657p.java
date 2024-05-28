package com.bytedance.applog;

import java.util.HashMap;
import java.util.HashSet;

/* renamed from: com.bytedance.applog.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3657p extends AbstractC3647o {
    public C3657p(HashSet<String> hashSet, HashMap<String, HashSet<String>> hashMap) {
        super(hashSet, hashMap);
    }

    @Override // com.bytedance.applog.AbstractC3647o
    /* renamed from: a */
    public boolean mo17142a(String str) {
        return !this.f8619a.contains(str);
    }

    @Override // com.bytedance.applog.AbstractC3647o
    /* renamed from: a */
    public boolean mo17141a(HashSet<String> hashSet, String str) {
        return !hashSet.contains(str);
    }
}
