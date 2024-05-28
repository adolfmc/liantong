package com.megvii.lv5;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.y3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5661y3 {

    /* renamed from: a */
    public final AtomicInteger f13934a;

    /* renamed from: b */
    public final Map<String, Queue<AbstractC5652x3<?>>> f13935b;

    /* renamed from: c */
    public final Set<AbstractC5652x3<?>> f13936c;

    /* renamed from: d */
    public final PriorityBlockingQueue<AbstractC5652x3<?>> f13937d;

    /* renamed from: e */
    public final PriorityBlockingQueue<AbstractC5652x3<?>> f13938e;

    /* renamed from: f */
    public final InterfaceC5509m3 f13939f;

    /* renamed from: g */
    public final InterfaceC5549r3 f13940g;

    /* renamed from: h */
    public final InterfaceC5381a4 f13941h;

    /* renamed from: i */
    public final C5557s3[] f13942i;

    /* renamed from: j */
    public C5518n3 f13943j;

    /* renamed from: k */
    public final List<InterfaceC5662a> f13944k;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.y3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5662a<T> {
        /* renamed from: a */
        void m12890a(AbstractC5652x3<T> abstractC5652x3);
    }

    public C5661y3(InterfaceC5509m3 interfaceC5509m3, InterfaceC5549r3 interfaceC5549r3, int i) {
        this(interfaceC5509m3, interfaceC5549r3, i, new C5541q3(new Handler(Looper.getMainLooper())));
    }

    public C5661y3(InterfaceC5509m3 interfaceC5509m3, InterfaceC5549r3 interfaceC5549r3, int i, InterfaceC5381a4 interfaceC5381a4) {
        this.f13934a = new AtomicInteger();
        this.f13935b = new HashMap();
        this.f13936c = new HashSet();
        this.f13937d = new PriorityBlockingQueue<>();
        this.f13938e = new PriorityBlockingQueue<>();
        this.f13944k = new ArrayList();
        this.f13939f = interfaceC5509m3;
        this.f13940g = interfaceC5549r3;
        this.f13942i = new C5557s3[i];
        this.f13941h = interfaceC5381a4;
    }
}
