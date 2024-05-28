package com.networkbench.agent.impl.p254f;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.f.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6389a {

    /* renamed from: a */
    public static C6389a f16136a = new C6389a(null);

    /* renamed from: b */
    private String f16137b;

    /* renamed from: c */
    private Throwable f16138c;

    /* renamed from: d */
    private Object[] f16139d;

    public C6389a(String str) {
        this(str, null, null);
    }

    public C6389a(String str, Object[] objArr, Throwable th) {
        this.f16137b = str;
        this.f16138c = th;
        if (th == null) {
            this.f16139d = objArr;
        } else {
            this.f16139d = m10177a(objArr);
        }
    }

    /* renamed from: a */
    static Object[] m10177a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalStateException("non-sensical empty or null argument array");
        }
        int length = objArr.length - 1;
        Object[] objArr2 = new Object[length];
        System.arraycopy(objArr, 0, objArr2, 0, length);
        return objArr2;
    }

    /* renamed from: a */
    public String m10178a() {
        return this.f16137b;
    }

    /* renamed from: b */
    public Object[] m10176b() {
        return this.f16139d;
    }

    /* renamed from: c */
    public Throwable m10175c() {
        return this.f16138c;
    }
}
