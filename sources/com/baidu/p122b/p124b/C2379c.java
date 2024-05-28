package com.baidu.p122b.p124b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2379c {

    /* renamed from: a */
    private InterfaceC2380a f4142a;

    /* renamed from: b */
    private Map<String, AbstractC2372a> f4143b = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2380a {
        /* renamed from: a */
        List<AbstractC2372a> mo20342a();
    }

    public C2379c(InterfaceC2380a interfaceC2380a) {
        this.f4142a = interfaceC2380a;
        for (AbstractC2372a abstractC2372a : interfaceC2380a.mo20342a()) {
            this.f4143b.put(abstractC2372a.m20354a(), abstractC2372a);
        }
    }

    /* renamed from: a */
    public List<AbstractC2372a> m20343a() {
        return new ArrayList(this.f4143b.values());
    }
}
