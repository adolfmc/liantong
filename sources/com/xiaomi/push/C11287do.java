package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.do */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11287do {

    /* renamed from: com.xiaomi.push.do$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11288a extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21877a;

        /* renamed from: b */
        private boolean f21879b;

        /* renamed from: d */
        private boolean f21882d;

        /* renamed from: e */
        private boolean f21883e;

        /* renamed from: a */
        private int f21875a = 0;

        /* renamed from: c */
        private boolean f21881c = false;

        /* renamed from: b */
        private int f21878b = 0;

        /* renamed from: f */
        private boolean f21884f = false;

        /* renamed from: a */
        private List<String> f21876a = Collections.emptyList();

        /* renamed from: c */
        private int f21880c = -1;

        /* renamed from: c */
        public int m4322c() {
            return this.f21875a;
        }

        /* renamed from: a */
        public boolean m4331a() {
            return this.f21877a;
        }

        /* renamed from: a */
        public C11288a m4330a(int i) {
            this.f21877a = true;
            this.f21875a = i;
            return this;
        }

        /* renamed from: b */
        public boolean m4326b() {
            return this.f21881c;
        }

        /* renamed from: c */
        public boolean m4321c() {
            return this.f21879b;
        }

        /* renamed from: a */
        public C11288a m4328a(boolean z) {
            this.f21879b = true;
            this.f21881c = z;
            return this;
        }

        /* renamed from: d */
        public int m4320d() {
            return this.f21878b;
        }

        /* renamed from: d */
        public boolean m4319d() {
            return this.f21882d;
        }

        /* renamed from: b */
        public C11288a m4325b(int i) {
            this.f21882d = true;
            this.f21878b = i;
            return this;
        }

        /* renamed from: e */
        public boolean m4317e() {
            return this.f21884f;
        }

        /* renamed from: f */
        public boolean m4316f() {
            return this.f21883e;
        }

        /* renamed from: b */
        public C11288a m4323b(boolean z) {
            this.f21883e = true;
            this.f21884f = z;
            return this;
        }

        /* renamed from: a */
        public List<String> m4332a() {
            return this.f21876a;
        }

        /* renamed from: e */
        public int m4318e() {
            return this.f21876a.size();
        }

        /* renamed from: a */
        public C11288a m4329a(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            if (this.f21876a.isEmpty()) {
                this.f21876a = new ArrayList();
            }
            this.f21876a.add(str);
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4331a()) {
                c11224c.m4622b(1, m4322c());
            }
            if (m4321c()) {
                c11224c.m4642a(2, m4326b());
            }
            if (m4319d()) {
                c11224c.m4652a(3, m4320d());
            }
            if (m4316f()) {
                c11224c.m4642a(4, m4317e());
            }
            for (String str : m4332a()) {
                c11224c.m4644a(5, str);
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21880c < 0) {
                mo4055b();
            }
            return this.f21880c;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int i = 0;
            int m4623b = m4331a() ? C11224c.m4623b(1, m4322c()) + 0 : 0;
            if (m4321c()) {
                m4623b += C11224c.m4643a(2, m4326b());
            }
            if (m4319d()) {
                m4623b += C11224c.m4653a(3, m4320d());
            }
            if (m4316f()) {
                m4623b += C11224c.m4643a(4, m4317e());
            }
            for (String str : m4332a()) {
                i += C11224c.m4633a(str);
            }
            int size = m4623b + i + (m4332a().size() * 1);
            this.f21880c = size;
            return size;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11288a mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 8) {
                    m4330a(c11182b.m4768c());
                } else if (m4787a == 16) {
                    m4328a(c11182b.m4782a());
                } else if (m4787a == 24) {
                    m4325b(c11182b.m4773b());
                } else if (m4787a == 32) {
                    m4323b(c11182b.m4782a());
                } else if (m4787a != 42) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4329a(c11182b.m4784a());
                }
            }
        }

        /* renamed from: a */
        public static C11288a m4327a(byte[] bArr) {
            return (C11288a) new C11288a().m4327a(bArr);
        }

        /* renamed from: b */
        public static C11288a m4324b(C11182b c11182b) {
            return new C11288a().mo4061a(c11182b);
        }
    }
}
