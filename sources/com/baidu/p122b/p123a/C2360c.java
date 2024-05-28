package com.baidu.p122b.p123a;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2360c {
    /* renamed from: a */
    public static void m20384a(byte[] bArr, byte[] bArr2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("start should be more than zero!");
        }
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("dst array should not be null or empty");
        }
        if (bArr2 == null || bArr2.length == 0) {
            throw new IllegalArgumentException("src array should not be null or empty");
        }
        if (bArr.length < bArr2.length) {
            throw new IllegalArgumentException("dst array length should be longer than:" + bArr2.length);
        }
        if (bArr.length >= bArr2.length + i) {
            System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
            return;
        }
        throw new IllegalArgumentException("start should be less than:" + (bArr.length - bArr2.length));
    }

    /* renamed from: a */
    public static byte[] m20385a(byte[] bArr, int i) {
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("original array should not be null or empty");
        }
        if (i >= 0) {
            return Arrays.copyOf(bArr, i);
        }
        throw new IllegalArgumentException("length should be more than zero!");
    }
}
