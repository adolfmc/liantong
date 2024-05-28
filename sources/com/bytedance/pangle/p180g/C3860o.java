package com.bytedance.pangle.p180g;

import android.content.pm.Signature;
import android.support.annotation.Nullable;
import android.util.ArraySet;
import com.bytedance.pangle.p172b.p173a.C3777a;
import com.bytedance.pangle.util.C3944d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.util.Arrays;

/* renamed from: com.bytedance.pangle.g.o */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3860o {

    /* renamed from: a */
    public static final C3860o f9205a = new C3860o(null, 0, null, null, null);
    @Nullable

    /* renamed from: b */
    public final Signature[] f9206b;

    /* renamed from: c */
    public final int f9207c;
    @Nullable

    /* renamed from: d */
    public final ArraySet<PublicKey> f9208d;
    @Nullable

    /* renamed from: e */
    public final Signature[] f9209e;
    @Nullable

    /* renamed from: f */
    public final int[] f9210f;

    /* renamed from: a */
    private static ArraySet<PublicKey> m16807a(Signature[] signatureArr) {
        ArraySet<PublicKey> arraySet = new ArraySet<>(signatureArr.length);
        for (Signature signature : signatureArr) {
            Method m16972a = C3777a.m16972a(Signature.class, "getPublicKey", new Class[0]);
            if (m16972a != null && m16972a.isAccessible()) {
                try {
                    arraySet.add((PublicKey) m16972a.invoke(signature, new Object[0]));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        return arraySet;
    }

    /* renamed from: a */
    public static boolean m16806a(Signature[] signatureArr, Signature[] signatureArr2) {
        return signatureArr.length == signatureArr2.length && C3944d.m16641a((Object[]) signatureArr, (Object[]) signatureArr2) && C3944d.m16641a((Object[]) signatureArr2, (Object[]) signatureArr);
    }

    /* renamed from: a */
    public static boolean m16808a(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public C3860o(Signature[] signatureArr, int i, ArraySet<PublicKey> arraySet, Signature[] signatureArr2, int[] iArr) {
        this.f9206b = signatureArr;
        this.f9207c = i;
        this.f9208d = arraySet;
        this.f9209e = signatureArr2;
        this.f9210f = iArr;
    }

    public C3860o(Signature[] signatureArr, int i, Signature[] signatureArr2, int[] iArr) {
        this(signatureArr, i, m16807a(signatureArr), signatureArr2, iArr);
    }

    public C3860o(Signature[] signatureArr) {
        this(signatureArr, 2, null, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C3860o) {
            C3860o c3860o = (C3860o) obj;
            if (this.f9207c == c3860o.f9207c && m16806a(this.f9206b, c3860o.f9206b)) {
                ArraySet<PublicKey> arraySet = this.f9208d;
                if (arraySet != null) {
                    if (!arraySet.equals(c3860o.f9208d)) {
                        return false;
                    }
                } else if (c3860o.f9208d != null) {
                    return false;
                }
                return Arrays.equals(this.f9209e, c3860o.f9209e) && Arrays.equals(this.f9210f, c3860o.f9210f);
            }
            return false;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = ((Arrays.hashCode(this.f9206b) * 31) + this.f9207c) * 31;
        ArraySet<PublicKey> arraySet = this.f9208d;
        return ((((hashCode + (arraySet != null ? arraySet.hashCode() : 0)) * 31) + Arrays.hashCode(this.f9209e)) * 31) + Arrays.hashCode(this.f9210f);
    }
}
