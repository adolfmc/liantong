package com.megvii.lv5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.u4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5623u4 {

    /* renamed from: e */
    public static final Comparator<byte[]> f13749e = new C5624a();

    /* renamed from: a */
    public final List<byte[]> f13750a = new LinkedList();

    /* renamed from: b */
    public final List<byte[]> f13751b = new ArrayList(64);

    /* renamed from: c */
    public int f13752c = 0;

    /* renamed from: d */
    public final int f13753d;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.u4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5624a implements Comparator<byte[]> {
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            return bArr.length - bArr2.length;
        }
    }

    public C5623u4(int i) {
        this.f13753d = i;
    }

    /* renamed from: a */
    public synchronized void m12974a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.f13753d) {
                this.f13750a.add(bArr);
                int binarySearch = Collections.binarySearch(this.f13751b, bArr, f13749e);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.f13751b.add(binarySearch, bArr);
                this.f13752c += bArr.length;
                synchronized (this) {
                    while (this.f13752c > this.f13753d) {
                        byte[] remove = this.f13750a.remove(0);
                        this.f13751b.remove(remove);
                        this.f13752c -= remove.length;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public synchronized byte[] m12975a(int i) {
        for (int i2 = 0; i2 < this.f13751b.size(); i2++) {
            byte[] bArr = this.f13751b.get(i2);
            if (bArr.length >= i) {
                this.f13752c -= bArr.length;
                this.f13751b.remove(i2);
                this.f13750a.remove(bArr);
                return bArr;
            }
        }
        return new byte[i];
    }
}
