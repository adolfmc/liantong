package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.dp */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C11289dp {

    /* renamed from: com.xiaomi.push.dp$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11291b extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21913a;

        /* renamed from: c */
        private boolean f21917c;

        /* renamed from: d */
        private boolean f21919d;

        /* renamed from: e */
        private boolean f21920e;

        /* renamed from: b */
        private boolean f21915b = false;

        /* renamed from: a */
        private int f21912a = 0;

        /* renamed from: b */
        private int f21914b = 0;

        /* renamed from: c */
        private int f21916c = 0;

        /* renamed from: d */
        private int f21918d = -1;

        /* renamed from: a */
        public boolean m4275a() {
            return this.f21915b;
        }

        /* renamed from: b */
        public boolean m4271b() {
            return this.f21913a;
        }

        /* renamed from: a */
        public C11291b m4273a(boolean z) {
            this.f21913a = true;
            this.f21915b = z;
            return this;
        }

        /* renamed from: c */
        public int m4269c() {
            return this.f21912a;
        }

        /* renamed from: c */
        public boolean m4268c() {
            return this.f21917c;
        }

        /* renamed from: a */
        public C11291b m4274a(int i) {
            this.f21917c = true;
            this.f21912a = i;
            return this;
        }

        /* renamed from: d */
        public int m4266d() {
            return this.f21914b;
        }

        /* renamed from: d */
        public boolean m4265d() {
            return this.f21919d;
        }

        /* renamed from: b */
        public C11291b m4270b(int i) {
            this.f21919d = true;
            this.f21914b = i;
            return this;
        }

        /* renamed from: e */
        public int m4264e() {
            return this.f21916c;
        }

        /* renamed from: e */
        public boolean m4263e() {
            return this.f21920e;
        }

        /* renamed from: c */
        public C11291b m4267c(int i) {
            this.f21920e = true;
            this.f21916c = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4271b()) {
                c11224c.m4642a(1, m4275a());
            }
            if (m4268c()) {
                c11224c.m4652a(3, m4269c());
            }
            if (m4265d()) {
                c11224c.m4652a(4, m4266d());
            }
            if (m4263e()) {
                c11224c.m4652a(5, m4264e());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21918d < 0) {
                mo4055b();
            }
            return this.f21918d;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4643a = m4271b() ? 0 + C11224c.m4643a(1, m4275a()) : 0;
            if (m4268c()) {
                m4643a += C11224c.m4653a(3, m4269c());
            }
            if (m4265d()) {
                m4643a += C11224c.m4653a(4, m4266d());
            }
            if (m4263e()) {
                m4643a += C11224c.m4653a(5, m4264e());
            }
            this.f21918d = m4643a;
            return m4643a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11291b mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 8) {
                    m4273a(c11182b.m4782a());
                } else if (m4787a == 24) {
                    m4274a(c11182b.m4773b());
                } else if (m4787a == 32) {
                    m4270b(c11182b.m4773b());
                } else if (m4787a != 40) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4267c(c11182b.m4773b());
                }
            }
        }

        /* renamed from: a */
        public static C11291b m4272a(byte[] bArr) {
            return (C11291b) new C11291b().m4272a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11290a extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21888a;

        /* renamed from: b */
        private boolean f21892b;

        /* renamed from: c */
        private boolean f21896c;

        /* renamed from: d */
        private boolean f21899d;

        /* renamed from: e */
        private boolean f21902e;

        /* renamed from: f */
        private boolean f21904f;

        /* renamed from: g */
        private boolean f21905g;

        /* renamed from: h */
        private boolean f21906h;

        /* renamed from: i */
        private boolean f21907i;

        /* renamed from: j */
        private boolean f21908j;

        /* renamed from: k */
        private boolean f21909k;

        /* renamed from: l */
        private boolean f21910l;

        /* renamed from: m */
        private boolean f21911m;

        /* renamed from: a */
        private int f21885a = 0;

        /* renamed from: a */
        private long f21886a = 0;

        /* renamed from: a */
        private String f21887a = "";

        /* renamed from: b */
        private String f21891b = "";

        /* renamed from: c */
        private String f21895c = "";

        /* renamed from: d */
        private String f21898d = "";

        /* renamed from: e */
        private String f21901e = "";

        /* renamed from: b */
        private int f21889b = 1;

        /* renamed from: c */
        private int f21893c = 0;

        /* renamed from: d */
        private int f21897d = 0;

        /* renamed from: f */
        private String f21903f = "";

        /* renamed from: b */
        private long f21890b = 0;

        /* renamed from: c */
        private long f21894c = 0;

        /* renamed from: e */
        private int f21900e = -1;

        /* renamed from: c */
        public int m4302c() {
            return this.f21885a;
        }

        /* renamed from: a */
        public boolean m4312a() {
            return this.f21888a;
        }

        /* renamed from: a */
        public C11290a m4311a(int i) {
            this.f21888a = true;
            this.f21885a = i;
            return this;
        }

        /* renamed from: a */
        public long m4315a() {
            return this.f21886a;
        }

        /* renamed from: b */
        public boolean m4306b() {
            return this.f21892b;
        }

        /* renamed from: a */
        public C11290a m4310a(long j) {
            this.f21892b = true;
            this.f21886a = j;
            return this;
        }

        /* renamed from: a */
        public String m4313a() {
            return this.f21887a;
        }

        /* renamed from: c */
        public boolean m4299c() {
            return this.f21896c;
        }

        /* renamed from: a */
        public C11290a m4309a(String str) {
            this.f21896c = true;
            this.f21887a = str;
            return this;
        }

        /* renamed from: b */
        public String m4307b() {
            return this.f21891b;
        }

        /* renamed from: d */
        public boolean m4293d() {
            return this.f21899d;
        }

        /* renamed from: b */
        public C11290a m4303b(String str) {
            this.f21899d = true;
            this.f21891b = str;
            return this;
        }

        /* renamed from: c */
        public String m4300c() {
            return this.f21895c;
        }

        /* renamed from: e */
        public boolean m4288e() {
            return this.f21902e;
        }

        /* renamed from: c */
        public C11290a m4296c(String str) {
            this.f21902e = true;
            this.f21895c = str;
            return this;
        }

        /* renamed from: d */
        public String m4294d() {
            return this.f21898d;
        }

        /* renamed from: f */
        public boolean m4284f() {
            return this.f21904f;
        }

        /* renamed from: d */
        public C11290a m4291d(String str) {
            this.f21904f = true;
            this.f21898d = str;
            return this;
        }

        /* renamed from: a */
        public C11290a m4314a() {
            this.f21904f = false;
            this.f21898d = "";
            return this;
        }

        /* renamed from: e */
        public String m4289e() {
            return this.f21901e;
        }

        /* renamed from: g */
        public boolean m4282g() {
            return this.f21905g;
        }

        /* renamed from: e */
        public C11290a m4287e(String str) {
            this.f21905g = true;
            this.f21901e = str;
            return this;
        }

        /* renamed from: h */
        public boolean m4281h() {
            return this.f21906h;
        }

        /* renamed from: d */
        public int m4295d() {
            return this.f21889b;
        }

        /* renamed from: b */
        public C11290a m4305b(int i) {
            this.f21906h = true;
            this.f21889b = i;
            return this;
        }

        /* renamed from: e */
        public int m4290e() {
            return this.f21893c;
        }

        /* renamed from: i */
        public boolean m4280i() {
            return this.f21907i;
        }

        /* renamed from: c */
        public C11290a m4298c(int i) {
            this.f21907i = true;
            this.f21893c = i;
            return this;
        }

        /* renamed from: f */
        public int m4286f() {
            return this.f21897d;
        }

        /* renamed from: j */
        public boolean m4279j() {
            return this.f21908j;
        }

        /* renamed from: d */
        public C11290a m4292d(int i) {
            this.f21908j = true;
            this.f21897d = i;
            return this;
        }

        /* renamed from: f */
        public String m4285f() {
            return this.f21903f;
        }

        /* renamed from: k */
        public boolean m4278k() {
            return this.f21909k;
        }

        /* renamed from: f */
        public C11290a m4283f(String str) {
            this.f21909k = true;
            this.f21903f = str;
            return this;
        }

        /* renamed from: b */
        public long m4308b() {
            return this.f21890b;
        }

        /* renamed from: l */
        public boolean m4277l() {
            return this.f21910l;
        }

        /* renamed from: b */
        public C11290a m4304b(long j) {
            this.f21910l = true;
            this.f21890b = j;
            return this;
        }

        /* renamed from: c */
        public long m4301c() {
            return this.f21894c;
        }

        /* renamed from: m */
        public boolean m4276m() {
            return this.f21911m;
        }

        /* renamed from: c */
        public C11290a m4297c(long j) {
            this.f21911m = true;
            this.f21894c = j;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4312a()) {
                c11224c.m4652a(1, m4302c());
            }
            if (m4306b()) {
                c11224c.m4620b(2, m4315a());
            }
            if (m4299c()) {
                c11224c.m4644a(3, m4313a());
            }
            if (m4293d()) {
                c11224c.m4644a(4, m4307b());
            }
            if (m4288e()) {
                c11224c.m4644a(5, m4300c());
            }
            if (m4284f()) {
                c11224c.m4644a(6, m4294d());
            }
            if (m4282g()) {
                c11224c.m4644a(7, m4289e());
            }
            if (m4281h()) {
                c11224c.m4652a(8, m4295d());
            }
            if (m4280i()) {
                c11224c.m4652a(9, m4290e());
            }
            if (m4279j()) {
                c11224c.m4652a(10, m4286f());
            }
            if (m4278k()) {
                c11224c.m4644a(11, m4285f());
            }
            if (m4277l()) {
                c11224c.m4620b(12, m4308b());
            }
            if (m4276m()) {
                c11224c.m4620b(13, m4301c());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21900e < 0) {
                mo4055b();
            }
            return this.f21900e;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4653a = m4312a() ? 0 + C11224c.m4653a(1, m4302c()) : 0;
            if (m4306b()) {
                m4653a += C11224c.m4621b(2, m4315a());
            }
            if (m4299c()) {
                m4653a += C11224c.m4645a(3, m4313a());
            }
            if (m4293d()) {
                m4653a += C11224c.m4645a(4, m4307b());
            }
            if (m4288e()) {
                m4653a += C11224c.m4645a(5, m4300c());
            }
            if (m4284f()) {
                m4653a += C11224c.m4645a(6, m4294d());
            }
            if (m4282g()) {
                m4653a += C11224c.m4645a(7, m4289e());
            }
            if (m4281h()) {
                m4653a += C11224c.m4653a(8, m4295d());
            }
            if (m4280i()) {
                m4653a += C11224c.m4653a(9, m4290e());
            }
            if (m4279j()) {
                m4653a += C11224c.m4653a(10, m4286f());
            }
            if (m4278k()) {
                m4653a += C11224c.m4645a(11, m4285f());
            }
            if (m4277l()) {
                m4653a += C11224c.m4621b(12, m4308b());
            }
            if (m4276m()) {
                m4653a += C11224c.m4621b(13, m4301c());
            }
            this.f21900e = m4653a;
            return m4653a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11290a mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                switch (m4787a) {
                    case 0:
                        return this;
                    case 8:
                        m4311a(c11182b.m4773b());
                        break;
                    case 16:
                        m4310a(c11182b.m4772b());
                        break;
                    case 26:
                        m4309a(c11182b.m4784a());
                        break;
                    case 34:
                        m4303b(c11182b.m4784a());
                        break;
                    case 42:
                        m4296c(c11182b.m4784a());
                        break;
                    case 50:
                        m4291d(c11182b.m4784a());
                        break;
                    case 58:
                        m4287e(c11182b.m4784a());
                        break;
                    case 64:
                        m4305b(c11182b.m4773b());
                        break;
                    case 72:
                        m4298c(c11182b.m4773b());
                        break;
                    case 80:
                        m4292d(c11182b.m4773b());
                        break;
                    case 90:
                        m4283f(c11182b.m4784a());
                        break;
                    case 96:
                        m4304b(c11182b.m4772b());
                        break;
                    case 104:
                        m4297c(c11182b.m4772b());
                        break;
                    default:
                        if (m4060a(c11182b, m4787a)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }
    }

    /* renamed from: com.xiaomi.push.dp$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11294e extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21947a;

        /* renamed from: b */
        private boolean f21951b;

        /* renamed from: c */
        private boolean f21954c;

        /* renamed from: d */
        private boolean f21957d;

        /* renamed from: e */
        private boolean f21960e;

        /* renamed from: f */
        private boolean f21962f;

        /* renamed from: g */
        private boolean f21963g;

        /* renamed from: h */
        private boolean f21964h;

        /* renamed from: i */
        private boolean f21965i;

        /* renamed from: j */
        private boolean f21966j;

        /* renamed from: k */
        private boolean f21967k;

        /* renamed from: l */
        private boolean f21968l;

        /* renamed from: m */
        private boolean f21969m;

        /* renamed from: a */
        private int f21943a = 0;

        /* renamed from: a */
        private String f21946a = "";

        /* renamed from: b */
        private String f21950b = "";

        /* renamed from: c */
        private String f21953c = "";

        /* renamed from: b */
        private int f21948b = 0;

        /* renamed from: d */
        private String f21956d = "";

        /* renamed from: e */
        private String f21959e = "";

        /* renamed from: f */
        private String f21961f = "";

        /* renamed from: a */
        private C11291b f21945a = null;

        /* renamed from: c */
        private int f21952c = 0;

        /* renamed from: a */
        private C11129a f21944a = C11129a.f21449a;

        /* renamed from: b */
        private C11129a f21949b = C11129a.f21449a;

        /* renamed from: d */
        private int f21955d = 0;

        /* renamed from: e */
        private int f21958e = -1;

        /* renamed from: c */
        public int m4217c() {
            return this.f21943a;
        }

        /* renamed from: a */
        public boolean m4228a() {
            return this.f21947a;
        }

        /* renamed from: a */
        public C11294e m4227a(int i) {
            this.f21947a = true;
            this.f21943a = i;
            return this;
        }

        /* renamed from: a */
        public String m4229a() {
            return this.f21946a;
        }

        /* renamed from: b */
        public boolean m4221b() {
            return this.f21951b;
        }

        /* renamed from: a */
        public C11294e m4224a(String str) {
            this.f21951b = true;
            this.f21946a = str;
            return this;
        }

        /* renamed from: b */
        public String m4222b() {
            return this.f21950b;
        }

        /* renamed from: c */
        public boolean m4215c() {
            return this.f21954c;
        }

        /* renamed from: b */
        public C11294e m4218b(String str) {
            this.f21954c = true;
            this.f21950b = str;
            return this;
        }

        /* renamed from: c */
        public String m4216c() {
            return this.f21953c;
        }

        /* renamed from: d */
        public boolean m4210d() {
            return this.f21957d;
        }

        /* renamed from: c */
        public C11294e m4213c(String str) {
            this.f21957d = true;
            this.f21953c = str;
            return this;
        }

        /* renamed from: d */
        public int m4212d() {
            return this.f21948b;
        }

        /* renamed from: e */
        public boolean m4205e() {
            return this.f21960e;
        }

        /* renamed from: b */
        public C11294e m4220b(int i) {
            this.f21960e = true;
            this.f21948b = i;
            return this;
        }

        /* renamed from: d */
        public String m4211d() {
            return this.f21956d;
        }

        /* renamed from: f */
        public boolean m4201f() {
            return this.f21962f;
        }

        /* renamed from: d */
        public C11294e m4208d(String str) {
            this.f21962f = true;
            this.f21956d = str;
            return this;
        }

        /* renamed from: e */
        public String m4206e() {
            return this.f21959e;
        }

        /* renamed from: g */
        public boolean m4199g() {
            return this.f21963g;
        }

        /* renamed from: e */
        public C11294e m4204e(String str) {
            this.f21963g = true;
            this.f21959e = str;
            return this;
        }

        /* renamed from: f */
        public String m4202f() {
            return this.f21961f;
        }

        /* renamed from: h */
        public boolean m4198h() {
            return this.f21964h;
        }

        /* renamed from: f */
        public C11294e m4200f(String str) {
            this.f21964h = true;
            this.f21961f = str;
            return this;
        }

        /* renamed from: i */
        public boolean m4197i() {
            return this.f21965i;
        }

        /* renamed from: a */
        public C11291b m4230a() {
            return this.f21945a;
        }

        /* renamed from: a */
        public C11294e m4225a(C11291b c11291b) {
            if (c11291b == null) {
                throw new NullPointerException();
            }
            this.f21965i = true;
            this.f21945a = c11291b;
            return this;
        }

        /* renamed from: e */
        public int m4207e() {
            return this.f21952c;
        }

        /* renamed from: j */
        public boolean m4196j() {
            return this.f21966j;
        }

        /* renamed from: c */
        public C11294e m4214c(int i) {
            this.f21966j = true;
            this.f21952c = i;
            return this;
        }

        /* renamed from: a */
        public C11129a m4231a() {
            return this.f21944a;
        }

        /* renamed from: k */
        public boolean m4195k() {
            return this.f21967k;
        }

        /* renamed from: a */
        public C11294e m4226a(C11129a c11129a) {
            this.f21967k = true;
            this.f21944a = c11129a;
            return this;
        }

        /* renamed from: b */
        public C11129a m4223b() {
            return this.f21949b;
        }

        /* renamed from: l */
        public boolean m4194l() {
            return this.f21968l;
        }

        /* renamed from: b */
        public C11294e m4219b(C11129a c11129a) {
            this.f21968l = true;
            this.f21949b = c11129a;
            return this;
        }

        /* renamed from: f */
        public int m4203f() {
            return this.f21955d;
        }

        /* renamed from: m */
        public boolean m4193m() {
            return this.f21969m;
        }

        /* renamed from: d */
        public C11294e m4209d(int i) {
            this.f21969m = true;
            this.f21955d = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4228a()) {
                c11224c.m4622b(1, m4217c());
            }
            if (m4221b()) {
                c11224c.m4644a(2, m4229a());
            }
            if (m4215c()) {
                c11224c.m4644a(3, m4222b());
            }
            if (m4210d()) {
                c11224c.m4644a(4, m4216c());
            }
            if (m4205e()) {
                c11224c.m4652a(5, m4212d());
            }
            if (m4201f()) {
                c11224c.m4644a(6, m4211d());
            }
            if (m4199g()) {
                c11224c.m4644a(7, m4206e());
            }
            if (m4198h()) {
                c11224c.m4644a(8, m4202f());
            }
            if (m4197i()) {
                c11224c.m4646a(9, (AbstractC11313e) m4230a());
            }
            if (m4196j()) {
                c11224c.m4652a(10, m4207e());
            }
            if (m4195k()) {
                c11224c.m4648a(11, m4231a());
            }
            if (m4194l()) {
                c11224c.m4648a(12, m4223b());
            }
            if (m4193m()) {
                c11224c.m4652a(13, m4203f());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21958e < 0) {
                mo4055b();
            }
            return this.f21958e;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4623b = m4228a() ? 0 + C11224c.m4623b(1, m4217c()) : 0;
            if (m4221b()) {
                m4623b += C11224c.m4645a(2, m4229a());
            }
            if (m4215c()) {
                m4623b += C11224c.m4645a(3, m4222b());
            }
            if (m4210d()) {
                m4623b += C11224c.m4645a(4, m4216c());
            }
            if (m4205e()) {
                m4623b += C11224c.m4653a(5, m4212d());
            }
            if (m4201f()) {
                m4623b += C11224c.m4645a(6, m4211d());
            }
            if (m4199g()) {
                m4623b += C11224c.m4645a(7, m4206e());
            }
            if (m4198h()) {
                m4623b += C11224c.m4645a(8, m4202f());
            }
            if (m4197i()) {
                m4623b += C11224c.m4647a(9, (AbstractC11313e) m4230a());
            }
            if (m4196j()) {
                m4623b += C11224c.m4653a(10, m4207e());
            }
            if (m4195k()) {
                m4623b += C11224c.m4649a(11, m4231a());
            }
            if (m4194l()) {
                m4623b += C11224c.m4649a(12, m4223b());
            }
            if (m4193m()) {
                m4623b += C11224c.m4653a(13, m4203f());
            }
            this.f21958e = m4623b;
            return m4623b;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11294e mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                switch (m4787a) {
                    case 0:
                        return this;
                    case 8:
                        m4227a(c11182b.m4768c());
                        break;
                    case 18:
                        m4224a(c11182b.m4784a());
                        break;
                    case 26:
                        m4218b(c11182b.m4784a());
                        break;
                    case 34:
                        m4213c(c11182b.m4784a());
                        break;
                    case 40:
                        m4220b(c11182b.m4773b());
                        break;
                    case 50:
                        m4208d(c11182b.m4784a());
                        break;
                    case 58:
                        m4204e(c11182b.m4784a());
                        break;
                    case 66:
                        m4200f(c11182b.m4784a());
                        break;
                    case 74:
                        C11291b c11291b = new C11291b();
                        c11182b.m4777a(c11291b);
                        m4225a(c11291b);
                        break;
                    case 80:
                        m4214c(c11182b.m4773b());
                        break;
                    case 90:
                        m4226a(c11182b.m4785a());
                        break;
                    case 98:
                        m4219b(c11182b.m4785a());
                        break;
                    case 104:
                        m4209d(c11182b.m4773b());
                        break;
                    default:
                        if (m4060a(c11182b, m4787a)) {
                            break;
                        } else {
                            return this;
                        }
                }
            }
        }
    }

    /* renamed from: com.xiaomi.push.dp$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11295f extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21973a;

        /* renamed from: b */
        private boolean f21975b;

        /* renamed from: c */
        private boolean f21976c;

        /* renamed from: a */
        private String f21972a = "";

        /* renamed from: b */
        private String f21974b = "";

        /* renamed from: a */
        private C11291b f21971a = null;

        /* renamed from: a */
        private int f21970a = -1;

        /* renamed from: a */
        public String m4191a() {
            return this.f21972a;
        }

        /* renamed from: a */
        public boolean m4190a() {
            return this.f21973a;
        }

        /* renamed from: a */
        public C11295f m4188a(String str) {
            this.f21973a = true;
            this.f21972a = str;
            return this;
        }

        /* renamed from: b */
        public String m4186b() {
            return this.f21974b;
        }

        /* renamed from: b */
        public boolean m4185b() {
            return this.f21975b;
        }

        /* renamed from: b */
        public C11295f m4184b(String str) {
            this.f21975b = true;
            this.f21974b = str;
            return this;
        }

        /* renamed from: c */
        public boolean m4183c() {
            return this.f21976c;
        }

        /* renamed from: a */
        public C11291b m4192a() {
            return this.f21971a;
        }

        /* renamed from: a */
        public C11295f m4189a(C11291b c11291b) {
            if (c11291b == null) {
                throw new NullPointerException();
            }
            this.f21976c = true;
            this.f21971a = c11291b;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4190a()) {
                c11224c.m4644a(1, m4191a());
            }
            if (m4185b()) {
                c11224c.m4644a(2, m4186b());
            }
            if (m4183c()) {
                c11224c.m4646a(3, (AbstractC11313e) m4192a());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21970a < 0) {
                mo4055b();
            }
            return this.f21970a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4645a = m4190a() ? 0 + C11224c.m4645a(1, m4191a()) : 0;
            if (m4185b()) {
                m4645a += C11224c.m4645a(2, m4186b());
            }
            if (m4183c()) {
                m4645a += C11224c.m4647a(3, (AbstractC11313e) m4192a());
            }
            this.f21970a = m4645a;
            return m4645a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11295f mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 10) {
                    m4188a(c11182b.m4784a());
                } else if (m4787a == 18) {
                    m4184b(c11182b.m4784a());
                } else if (m4787a != 26) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    C11291b c11291b = new C11291b();
                    c11182b.m4777a(c11291b);
                    m4189a(c11291b);
                }
            }
        }

        /* renamed from: a */
        public static C11295f m4187a(byte[] bArr) {
            return (C11295f) new C11295f().m4187a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$h */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11297h extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21986a;

        /* renamed from: b */
        private boolean f21988b;

        /* renamed from: a */
        private int f21984a = 0;

        /* renamed from: a */
        private String f21985a = "";

        /* renamed from: b */
        private int f21987b = -1;

        /* renamed from: c */
        public int m4166c() {
            return this.f21984a;
        }

        /* renamed from: a */
        public boolean m4171a() {
            return this.f21986a;
        }

        /* renamed from: a */
        public C11297h m4170a(int i) {
            this.f21986a = true;
            this.f21984a = i;
            return this;
        }

        /* renamed from: a */
        public String m4172a() {
            return this.f21985a;
        }

        /* renamed from: b */
        public boolean m4167b() {
            return this.f21988b;
        }

        /* renamed from: a */
        public C11297h m4169a(String str) {
            this.f21988b = true;
            this.f21985a = str;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4171a()) {
                c11224c.m4652a(1, m4166c());
            }
            if (m4167b()) {
                c11224c.m4644a(2, m4172a());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21987b < 0) {
                mo4055b();
            }
            return this.f21987b;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4653a = m4171a() ? 0 + C11224c.m4653a(1, m4166c()) : 0;
            if (m4167b()) {
                m4653a += C11224c.m4645a(2, m4172a());
            }
            this.f21987b = m4653a;
            return m4653a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11297h mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 8) {
                    m4170a(c11182b.m4773b());
                } else if (m4787a != 18) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4169a(c11182b.m4784a());
                }
            }
        }

        /* renamed from: a */
        public static C11297h m4168a(byte[] bArr) {
            return (C11297h) new C11297h().m4168a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11292c extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21923a;

        /* renamed from: b */
        private boolean f21925b;

        /* renamed from: c */
        private boolean f21927c;

        /* renamed from: d */
        private boolean f21929d;

        /* renamed from: e */
        private boolean f21931e;

        /* renamed from: f */
        private boolean f21933f;

        /* renamed from: a */
        private String f21922a = "";

        /* renamed from: b */
        private String f21924b = "";

        /* renamed from: c */
        private String f21926c = "";

        /* renamed from: d */
        private String f21928d = "";

        /* renamed from: e */
        private String f21930e = "";

        /* renamed from: f */
        private String f21932f = "";

        /* renamed from: a */
        private int f21921a = -1;

        /* renamed from: a */
        public String m4262a() {
            return this.f21922a;
        }

        /* renamed from: a */
        public boolean m4261a() {
            return this.f21923a;
        }

        /* renamed from: a */
        public C11292c m4260a(String str) {
            this.f21923a = true;
            this.f21922a = str;
            return this;
        }

        /* renamed from: b */
        public String m4259b() {
            return this.f21924b;
        }

        /* renamed from: b */
        public boolean m4258b() {
            return this.f21925b;
        }

        /* renamed from: b */
        public C11292c m4257b(String str) {
            this.f21925b = true;
            this.f21924b = str;
            return this;
        }

        /* renamed from: c */
        public String m4256c() {
            return this.f21926c;
        }

        /* renamed from: c */
        public boolean m4255c() {
            return this.f21927c;
        }

        /* renamed from: c */
        public C11292c m4254c(String str) {
            this.f21927c = true;
            this.f21926c = str;
            return this;
        }

        /* renamed from: d */
        public String m4253d() {
            return this.f21928d;
        }

        /* renamed from: d */
        public boolean m4252d() {
            return this.f21929d;
        }

        /* renamed from: d */
        public C11292c m4251d(String str) {
            this.f21929d = true;
            this.f21928d = str;
            return this;
        }

        /* renamed from: e */
        public String m4250e() {
            return this.f21930e;
        }

        /* renamed from: e */
        public boolean m4249e() {
            return this.f21931e;
        }

        /* renamed from: e */
        public C11292c m4248e(String str) {
            this.f21931e = true;
            this.f21930e = str;
            return this;
        }

        /* renamed from: f */
        public String m4247f() {
            return this.f21932f;
        }

        /* renamed from: f */
        public boolean m4246f() {
            return this.f21933f;
        }

        /* renamed from: f */
        public C11292c m4245f(String str) {
            this.f21933f = true;
            this.f21932f = str;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4261a()) {
                c11224c.m4644a(1, m4262a());
            }
            if (m4258b()) {
                c11224c.m4644a(2, m4259b());
            }
            if (m4255c()) {
                c11224c.m4644a(3, m4256c());
            }
            if (m4252d()) {
                c11224c.m4644a(4, m4253d());
            }
            if (m4249e()) {
                c11224c.m4644a(5, m4250e());
            }
            if (m4246f()) {
                c11224c.m4644a(6, m4247f());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21921a < 0) {
                mo4055b();
            }
            return this.f21921a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4645a = m4261a() ? 0 + C11224c.m4645a(1, m4262a()) : 0;
            if (m4258b()) {
                m4645a += C11224c.m4645a(2, m4259b());
            }
            if (m4255c()) {
                m4645a += C11224c.m4645a(3, m4256c());
            }
            if (m4252d()) {
                m4645a += C11224c.m4645a(4, m4253d());
            }
            if (m4249e()) {
                m4645a += C11224c.m4645a(5, m4250e());
            }
            if (m4246f()) {
                m4645a += C11224c.m4645a(6, m4247f());
            }
            this.f21921a = m4645a;
            return m4645a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11292c mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 10) {
                    m4260a(c11182b.m4784a());
                } else if (m4787a == 18) {
                    m4257b(c11182b.m4784a());
                } else if (m4787a == 26) {
                    m4254c(c11182b.m4784a());
                } else if (m4787a == 34) {
                    m4251d(c11182b.m4784a());
                } else if (m4787a == 42) {
                    m4248e(c11182b.m4784a());
                } else if (m4787a != 50) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4245f(c11182b.m4784a());
                }
            }
        }
    }

    /* renamed from: com.xiaomi.push.dp$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11293d extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21936a;

        /* renamed from: c */
        private boolean f21940c;

        /* renamed from: d */
        private boolean f21941d;

        /* renamed from: e */
        private boolean f21942e;

        /* renamed from: b */
        private boolean f21938b = false;

        /* renamed from: a */
        private String f21935a = "";

        /* renamed from: b */
        private String f21937b = "";

        /* renamed from: c */
        private String f21939c = "";

        /* renamed from: a */
        private int f21934a = -1;

        /* renamed from: a */
        public boolean m4243a() {
            return this.f21938b;
        }

        /* renamed from: b */
        public boolean m4238b() {
            return this.f21936a;
        }

        /* renamed from: a */
        public C11293d m4241a(boolean z) {
            this.f21936a = true;
            this.f21938b = z;
            return this;
        }

        /* renamed from: a */
        public String m4244a() {
            return this.f21935a;
        }

        /* renamed from: c */
        public boolean m4235c() {
            return this.f21940c;
        }

        /* renamed from: a */
        public C11293d m4242a(String str) {
            this.f21940c = true;
            this.f21935a = str;
            return this;
        }

        /* renamed from: b */
        public String m4239b() {
            return this.f21937b;
        }

        /* renamed from: d */
        public boolean m4233d() {
            return this.f21941d;
        }

        /* renamed from: b */
        public C11293d m4237b(String str) {
            this.f21941d = true;
            this.f21937b = str;
            return this;
        }

        /* renamed from: c */
        public String m4236c() {
            return this.f21939c;
        }

        /* renamed from: e */
        public boolean m4232e() {
            return this.f21942e;
        }

        /* renamed from: c */
        public C11293d m4234c(String str) {
            this.f21942e = true;
            this.f21939c = str;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4238b()) {
                c11224c.m4642a(1, m4243a());
            }
            if (m4235c()) {
                c11224c.m4644a(2, m4244a());
            }
            if (m4233d()) {
                c11224c.m4644a(3, m4239b());
            }
            if (m4232e()) {
                c11224c.m4644a(4, m4236c());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21934a < 0) {
                mo4055b();
            }
            return this.f21934a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4643a = m4238b() ? 0 + C11224c.m4643a(1, m4243a()) : 0;
            if (m4235c()) {
                m4643a += C11224c.m4645a(2, m4244a());
            }
            if (m4233d()) {
                m4643a += C11224c.m4645a(3, m4239b());
            }
            if (m4232e()) {
                m4643a += C11224c.m4645a(4, m4236c());
            }
            this.f21934a = m4643a;
            return m4643a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11293d mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 8) {
                    m4241a(c11182b.m4782a());
                } else if (m4787a == 18) {
                    m4242a(c11182b.m4784a());
                } else if (m4787a == 26) {
                    m4237b(c11182b.m4784a());
                } else if (m4787a != 34) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4234c(c11182b.m4784a());
                }
            }
        }

        /* renamed from: a */
        public static C11293d m4240a(byte[] bArr) {
            return (C11293d) new C11293d().m4240a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$j */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11299j extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21995a;

        /* renamed from: b */
        private boolean f21996b;

        /* renamed from: a */
        private C11129a f21993a = C11129a.f21449a;

        /* renamed from: a */
        private C11291b f21994a = null;

        /* renamed from: a */
        private int f21992a = -1;

        /* renamed from: a */
        public C11129a m4161a() {
            return this.f21993a;
        }

        /* renamed from: a */
        public boolean m4159a() {
            return this.f21995a;
        }

        /* renamed from: a */
        public C11299j m4158a(C11129a c11129a) {
            this.f21995a = true;
            this.f21993a = c11129a;
            return this;
        }

        /* renamed from: b */
        public boolean m4155b() {
            return this.f21996b;
        }

        /* renamed from: a */
        public C11291b m4160a() {
            return this.f21994a;
        }

        /* renamed from: a */
        public C11299j m4157a(C11291b c11291b) {
            if (c11291b == null) {
                throw new NullPointerException();
            }
            this.f21996b = true;
            this.f21994a = c11291b;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4159a()) {
                c11224c.m4648a(1, m4161a());
            }
            if (m4155b()) {
                c11224c.m4646a(2, (AbstractC11313e) m4160a());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21992a < 0) {
                mo4055b();
            }
            return this.f21992a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4649a = m4159a() ? 0 + C11224c.m4649a(1, m4161a()) : 0;
            if (m4155b()) {
                m4649a += C11224c.m4647a(2, (AbstractC11313e) m4160a());
            }
            this.f21992a = m4649a;
            return m4649a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11299j mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 10) {
                    m4158a(c11182b.m4785a());
                } else if (m4787a != 18) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    C11291b c11291b = new C11291b();
                    c11182b.m4777a(c11291b);
                    m4157a(c11291b);
                }
            }
        }

        /* renamed from: a */
        public static C11299j m4156a(byte[] bArr) {
            return (C11299j) new C11299j().m4156a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11296g extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21979a;

        /* renamed from: b */
        private boolean f21981b;

        /* renamed from: c */
        private boolean f21983c;

        /* renamed from: a */
        private String f21978a = "";

        /* renamed from: b */
        private String f21980b = "";

        /* renamed from: c */
        private String f21982c = "";

        /* renamed from: a */
        private int f21977a = -1;

        /* renamed from: a */
        public String m4182a() {
            return this.f21978a;
        }

        /* renamed from: a */
        public boolean m4181a() {
            return this.f21979a;
        }

        /* renamed from: a */
        public C11296g m4180a(String str) {
            this.f21979a = true;
            this.f21978a = str;
            return this;
        }

        /* renamed from: b */
        public String m4178b() {
            return this.f21980b;
        }

        /* renamed from: b */
        public boolean m4177b() {
            return this.f21981b;
        }

        /* renamed from: b */
        public C11296g m4176b(String str) {
            this.f21981b = true;
            this.f21980b = str;
            return this;
        }

        /* renamed from: c */
        public String m4175c() {
            return this.f21982c;
        }

        /* renamed from: c */
        public boolean m4174c() {
            return this.f21983c;
        }

        /* renamed from: c */
        public C11296g m4173c(String str) {
            this.f21983c = true;
            this.f21982c = str;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4181a()) {
                c11224c.m4644a(1, m4182a());
            }
            if (m4177b()) {
                c11224c.m4644a(2, m4178b());
            }
            if (m4174c()) {
                c11224c.m4644a(3, m4175c());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21977a < 0) {
                mo4055b();
            }
            return this.f21977a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4645a = m4181a() ? 0 + C11224c.m4645a(1, m4182a()) : 0;
            if (m4177b()) {
                m4645a += C11224c.m4645a(2, m4178b());
            }
            if (m4174c()) {
                m4645a += C11224c.m4645a(3, m4175c());
            }
            this.f21977a = m4645a;
            return m4645a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11296g mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 10) {
                    m4180a(c11182b.m4784a());
                } else if (m4787a == 18) {
                    m4176b(c11182b.m4784a());
                } else if (m4787a != 26) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4173c(c11182b.m4784a());
                }
            }
        }

        /* renamed from: a */
        public static C11296g m4179a(byte[] bArr) {
            return (C11296g) new C11296g().m4179a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$k */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11300k extends AbstractC11313e {

        /* renamed from: a */
        private boolean f22000a;

        /* renamed from: b */
        private boolean f22004b;

        /* renamed from: c */
        private boolean f22005c;

        /* renamed from: d */
        private boolean f22006d;

        /* renamed from: e */
        private boolean f22007e;

        /* renamed from: g */
        private boolean f22009g;

        /* renamed from: a */
        private String f21999a = "";

        /* renamed from: b */
        private String f22003b = "";

        /* renamed from: a */
        private long f21998a = 0;

        /* renamed from: b */
        private long f22002b = 0;

        /* renamed from: f */
        private boolean f22008f = false;

        /* renamed from: a */
        private int f21997a = 0;

        /* renamed from: b */
        private int f22001b = -1;

        /* renamed from: a */
        public String m4153a() {
            return this.f21999a;
        }

        /* renamed from: a */
        public boolean m4152a() {
            return this.f22000a;
        }

        /* renamed from: a */
        public C11300k m4149a(String str) {
            this.f22000a = true;
            this.f21999a = str;
            return this;
        }

        /* renamed from: b */
        public String m4145b() {
            return this.f22003b;
        }

        /* renamed from: b */
        public boolean m4144b() {
            return this.f22004b;
        }

        /* renamed from: b */
        public C11300k m4142b(String str) {
            this.f22004b = true;
            this.f22003b = str;
            return this;
        }

        /* renamed from: a */
        public long m4154a() {
            return this.f21998a;
        }

        /* renamed from: c */
        public boolean m4140c() {
            return this.f22005c;
        }

        /* renamed from: a */
        public C11300k m4150a(long j) {
            this.f22005c = true;
            this.f21998a = j;
            return this;
        }

        /* renamed from: b */
        public long m4146b() {
            return this.f22002b;
        }

        /* renamed from: d */
        public boolean m4139d() {
            return this.f22006d;
        }

        /* renamed from: b */
        public C11300k m4143b(long j) {
            this.f22006d = true;
            this.f22002b = j;
            return this;
        }

        /* renamed from: e */
        public boolean m4138e() {
            return this.f22008f;
        }

        /* renamed from: f */
        public boolean m4137f() {
            return this.f22007e;
        }

        /* renamed from: a */
        public C11300k m4148a(boolean z) {
            this.f22007e = true;
            this.f22008f = z;
            return this;
        }

        /* renamed from: c */
        public int m4141c() {
            return this.f21997a;
        }

        /* renamed from: g */
        public boolean m4136g() {
            return this.f22009g;
        }

        /* renamed from: a */
        public C11300k m4151a(int i) {
            this.f22009g = true;
            this.f21997a = i;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4152a()) {
                c11224c.m4644a(1, m4153a());
            }
            if (m4144b()) {
                c11224c.m4644a(2, m4145b());
            }
            if (m4140c()) {
                c11224c.m4650a(3, m4154a());
            }
            if (m4139d()) {
                c11224c.m4650a(4, m4146b());
            }
            if (m4137f()) {
                c11224c.m4642a(5, m4138e());
            }
            if (m4136g()) {
                c11224c.m4652a(6, m4141c());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f22001b < 0) {
                mo4055b();
            }
            return this.f22001b;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4645a = m4152a() ? 0 + C11224c.m4645a(1, m4153a()) : 0;
            if (m4144b()) {
                m4645a += C11224c.m4645a(2, m4145b());
            }
            if (m4140c()) {
                m4645a += C11224c.m4651a(3, m4154a());
            }
            if (m4139d()) {
                m4645a += C11224c.m4651a(4, m4146b());
            }
            if (m4137f()) {
                m4645a += C11224c.m4643a(5, m4138e());
            }
            if (m4136g()) {
                m4645a += C11224c.m4653a(6, m4141c());
            }
            this.f22001b = m4645a;
            return m4645a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11300k mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a == 10) {
                    m4149a(c11182b.m4784a());
                } else if (m4787a == 18) {
                    m4142b(c11182b.m4784a());
                } else if (m4787a == 24) {
                    m4150a(c11182b.m4786a());
                } else if (m4787a == 32) {
                    m4143b(c11182b.m4786a());
                } else if (m4787a == 40) {
                    m4148a(c11182b.m4782a());
                } else if (m4787a != 48) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4151a(c11182b.m4773b());
                }
            }
        }

        /* renamed from: a */
        public static C11300k m4147a(byte[] bArr) {
            return (C11300k) new C11300k().m4147a(bArr);
        }
    }

    /* renamed from: com.xiaomi.push.dp$i */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11298i extends AbstractC11313e {

        /* renamed from: a */
        private boolean f21991a;

        /* renamed from: a */
        private C11129a f21990a = C11129a.f21449a;

        /* renamed from: a */
        private int f21989a = -1;

        /* renamed from: a */
        public C11129a m4165a() {
            return this.f21990a;
        }

        /* renamed from: a */
        public boolean m4164a() {
            return this.f21991a;
        }

        /* renamed from: a */
        public C11298i m4163a(C11129a c11129a) {
            this.f21991a = true;
            this.f21990a = c11129a;
            return this;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public void mo4059a(C11224c c11224c) {
            if (m4164a()) {
                c11224c.m4648a(1, m4165a());
            }
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public int mo4063a() {
            if (this.f21989a < 0) {
                mo4055b();
            }
            return this.f21989a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: b */
        public int mo4055b() {
            int m4649a = m4164a() ? 0 + C11224c.m4649a(1, m4165a()) : 0;
            this.f21989a = m4649a;
            return m4649a;
        }

        @Override // com.xiaomi.push.AbstractC11313e
        /* renamed from: a */
        public C11298i mo4061a(C11182b c11182b) {
            while (true) {
                int m4787a = c11182b.m4787a();
                if (m4787a == 0) {
                    return this;
                }
                if (m4787a != 10) {
                    if (!m4060a(c11182b, m4787a)) {
                        return this;
                    }
                } else {
                    m4163a(c11182b.m4785a());
                }
            }
        }

        /* renamed from: a */
        public static C11298i m4162a(byte[] bArr) {
            return (C11298i) new C11298i().m4162a(bArr);
        }
    }
}
