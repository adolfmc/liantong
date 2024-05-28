package com.baidu.p122b.p123a;

import com.baidu.p122b.p123a.C2362e;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2366h extends AbstractC2365g {

    /* renamed from: f */
    private C2367a f4113f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.a.h$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C2367a {

        /* renamed from: a */
        private Class<?> f4114a;

        /* renamed from: b */
        private Method f4115b;

        /* renamed from: c */
        private Method f4116c;

        private C2367a() {
            m20368a();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public long m20364a(Object obj) {
            try {
                return ((Long) this.f4116c.invoke(obj, new Object[0])).longValue();
            } catch (Exception unused) {
                throw new C2362e.C2363a("");
            }
        }

        /* renamed from: a */
        private void m20368a() {
            try {
                this.f4114a = Class.forName(C2362e.m20374a(C2361d.m20383a()), true, Object.class.getClassLoader());
                this.f4115b = C2362e.m20375a(this.f4114a, C2362e.m20374a(C2361d.m20382b()), new Class[]{byte[].class, Integer.TYPE, Integer.TYPE});
                this.f4116c = C2362e.m20375a(this.f4114a, C2362e.m20374a(C2361d.m20381c()), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m20363a(Object obj, byte[] bArr, int i, int i2) {
            try {
                this.f4115b.invoke(obj, bArr, Integer.valueOf(i), Integer.valueOf(i2));
            } catch (Exception unused) {
                throw new C2362e.C2363a("");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public Object m20362b() {
            return this.f4114a.newInstance();
        }
    }

    public C2366h(int i, int i2) {
        this.f4108a = 1099511627775L;
        this.f4109b = 4;
        this.f4110c = 32;
        this.f4111d = i;
        this.f4112e = i2;
        this.f4113f = new C2367a();
    }

    @Override // com.baidu.p122b.p123a.AbstractC2365g
    /* renamed from: a */
    public C2359b mo20361a(byte[] bArr, int i, int i2) {
        long j;
        try {
            Object m20362b = this.f4113f.m20362b();
            this.f4113f.m20363a(m20362b, bArr, i, i2);
            j = this.f4113f.m20364a(m20362b);
        } catch (Exception unused) {
            j = 4294967295L;
        }
        return C2359b.m20405a(new long[]{j});
    }
}
