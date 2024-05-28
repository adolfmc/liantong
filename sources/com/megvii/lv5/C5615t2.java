package com.megvii.lv5;

import java.util.LinkedList;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.t2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5615t2<T> extends LinkedList<T> {

    /* renamed from: a */
    public int f13737a;

    public C5615t2(int i) {
        this.f13737a = i;
    }

    @Override // java.util.LinkedList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List, java.util.Deque, java.util.Queue
    public boolean add(T t) {
        if (size() + 1 > this.f13737a) {
            removeFirst();
        }
        return super.add(t);
    }
}
