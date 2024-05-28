package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.service.C11541aj;
import com.xiaomi.push.service.C11561ar;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.er */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11339er {

    /* renamed from: a */
    int f22186a;

    /* renamed from: a */
    private C11289dp.C11290a f22187a;

    /* renamed from: a */
    String f22188a;

    /* renamed from: a */
    private short f22189a;

    /* renamed from: b */
    private final long f22190b;

    /* renamed from: b */
    private byte[] f22191b;

    /* renamed from: b */
    private static String f22185b = C11389fx.m3749a(5) + "-";

    /* renamed from: a */
    private static long f22183a = 0;

    /* renamed from: a */
    private static final byte[] f22184a = new byte[0];

    public C11339er() {
        this.f22189a = (short) 2;
        this.f22191b = f22184a;
        this.f22188a = null;
        this.f22190b = System.currentTimeMillis();
        this.f22187a = new C11289dp.C11290a();
        this.f22186a = 1;
    }

    C11339er(C11289dp.C11290a c11290a, short s, byte[] bArr) {
        this.f22189a = (short) 2;
        this.f22191b = f22184a;
        this.f22188a = null;
        this.f22190b = System.currentTimeMillis();
        this.f22187a = c11290a;
        this.f22189a = s;
        this.f22191b = bArr;
        this.f22186a = 2;
    }

    /* renamed from: a */
    public long m3967a() {
        return this.f22190b;
    }

    /* renamed from: a */
    public void m3956a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("command should not be empty");
        }
        this.f22187a.m4296c(str);
        this.f22187a.m4314a();
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f22187a.m4291d(str2);
    }

    /* renamed from: a */
    public String m3966a() {
        return this.f22187a.m4300c();
    }

    /* renamed from: b */
    public String m3950b() {
        return this.f22187a.m4294d();
    }

    /* renamed from: a */
    public void m3962a(int i) {
        this.f22187a.m4311a(i);
    }

    /* renamed from: a */
    public int m3968a() {
        return this.f22187a.m4302c();
    }

    /* renamed from: a */
    public void m3958a(String str) {
        this.f22187a.m4287e(str);
    }

    /* renamed from: a */
    public void m3961a(long j) {
        this.f22187a.m4310a(j);
    }

    /* renamed from: a */
    public boolean m3964a() {
        return this.f22187a.m4279j();
    }

    /* renamed from: b */
    public int m3952b() {
        return this.f22187a.m4286f();
    }

    /* renamed from: c */
    public String m3945c() {
        return this.f22187a.m4285f();
    }

    /* renamed from: b */
    public boolean m3949b() {
        return this.f22187a.m4277l();
    }

    /* renamed from: b */
    public void m3948b(long j) {
        this.f22187a.m4304b(j);
    }

    /* renamed from: c */
    public void m3944c(long j) {
        this.f22187a.m4297c(j);
    }

    /* renamed from: b */
    public long m3951b() {
        return this.f22187a.m4308b();
    }

    /* renamed from: c */
    public long m3946c() {
        return this.f22187a.m4315a();
    }

    /* renamed from: d */
    public static synchronized String m3942d() {
        String sb;
        synchronized (C11339er.class) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(f22185b);
            long j = f22183a;
            f22183a = 1 + j;
            sb2.append(Long.toString(j));
            sb = sb2.toString();
        }
        return sb;
    }

    /* renamed from: e */
    public String m3941e() {
        String m4289e = this.f22187a.m4289e();
        if ("ID_NOT_AVAILABLE".equals(m4289e)) {
            return null;
        }
        if (this.f22187a.m4282g()) {
            return m4289e;
        }
        String m3942d = m3942d();
        this.f22187a.m4287e(m3942d);
        return m3942d;
    }

    /* renamed from: b */
    public void m3947b(String str) {
        this.f22188a = str;
    }

    /* renamed from: f */
    public String m3940f() {
        return this.f22188a;
    }

    /* renamed from: c */
    public void m3943c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int indexOf = str.indexOf("@");
        try {
            long parseLong = Long.parseLong(str.substring(0, indexOf));
            int indexOf2 = str.indexOf("/", indexOf);
            String substring = str.substring(indexOf + 1, indexOf2);
            String substring2 = str.substring(indexOf2 + 1);
            this.f22187a.m4310a(parseLong);
            this.f22187a.m4309a(substring);
            this.f22187a.m4303b(substring2);
        } catch (Exception e) {
            AbstractC11049b.m5282a("Blob parse user err " + e.getMessage());
        }
    }

    /* renamed from: a */
    public void m3960a(long j, String str, String str2) {
        if (j != 0) {
            this.f22187a.m4310a(j);
        }
        if (!TextUtils.isEmpty(str)) {
            this.f22187a.m4309a(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        this.f22187a.m4303b(str2);
    }

    /* renamed from: g */
    public String m3939g() {
        if (this.f22187a.m4306b()) {
            return Long.toString(this.f22187a.m4315a()) + "@" + this.f22187a.m4313a() + "/" + this.f22187a.m4307b();
        }
        return null;
    }

    /* renamed from: a */
    public void m3953a(byte[] bArr, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f22187a.m4298c(1);
            this.f22191b = C11561ar.m2644a(C11561ar.m2648a(str, m3941e()), bArr);
            return;
        }
        this.f22187a.m4298c(0);
        this.f22191b = bArr;
    }

    /* renamed from: a */
    public byte[] m3963a() {
        return C11340es.m3938a(this, this.f22191b);
    }

    /* renamed from: a */
    public byte[] m3957a(String str) {
        if (this.f22187a.m4290e() == 1) {
            return C11340es.m3938a(this, C11561ar.m2644a(C11561ar.m2648a(str, m3941e()), this.f22191b));
        }
        if (this.f22187a.m4290e() == 0) {
            return C11340es.m3938a(this, this.f22191b);
        }
        AbstractC11049b.m5282a("unknow cipher = " + this.f22187a.m4290e());
        return C11340es.m3938a(this, this.f22191b);
    }

    @Deprecated
    /* renamed from: a */
    public static C11339er m3959a(AbstractC11375fo abstractC11375fo, String str) {
        int i;
        C11339er c11339er = new C11339er();
        try {
            i = Integer.parseInt(abstractC11375fo.m3789k());
        } catch (Exception e) {
            AbstractC11049b.m5282a("Blob parse chid err " + e.getMessage());
            i = 1;
        }
        c11339er.m3962a(i);
        c11339er.m3958a(abstractC11375fo.m3790j());
        c11339er.m3943c(abstractC11375fo.m3785m());
        c11339er.m3947b(abstractC11375fo.m3783n());
        c11339er.m3956a("XMLMSG", (String) null);
        try {
            c11339er.m3953a(abstractC11375fo.mo3775a().getBytes("utf8"), str);
            if (TextUtils.isEmpty(str)) {
                c11339er.m3954a((short) 3);
            } else {
                c11339er.m3954a((short) 2);
                c11339er.m3956a("SECMSG", (String) null);
            }
        } catch (UnsupportedEncodingException e2) {
            AbstractC11049b.m5282a("Blob setPayload err： " + e2.getMessage());
        }
        return c11339er;
    }

    /* renamed from: c */
    public int mo3914c() {
        return this.f22187a.mo4055b() + 8 + this.f22191b.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public ByteBuffer mo3915a(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            byteBuffer = ByteBuffer.allocate(mo3914c());
        }
        byteBuffer.putShort(this.f22189a);
        byteBuffer.putShort((short) this.f22187a.mo4063a());
        byteBuffer.putInt(this.f22191b.length);
        int position = byteBuffer.position();
        this.f22187a.m4057a(byteBuffer.array(), byteBuffer.arrayOffset() + position, this.f22187a.mo4063a());
        byteBuffer.position(position + this.f22187a.mo4063a());
        byteBuffer.put(this.f22191b);
        return byteBuffer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C11339er m3955a(ByteBuffer byteBuffer) {
        try {
            ByteBuffer slice = byteBuffer.slice();
            short s = slice.getShort(0);
            short s2 = slice.getShort(2);
            int i = slice.getInt(4);
            C11289dp.C11290a c11290a = new C11289dp.C11290a();
            c11290a.m4057a(slice.array(), slice.arrayOffset() + 8, (int) s2);
            byte[] bArr = new byte[i];
            slice.position(s2 + 8);
            slice.get(bArr, 0, i);
            return new C11339er(c11290a, s, bArr);
        } catch (Exception e) {
            AbstractC11049b.m5282a("read Blob err :" + e.getMessage());
            throw new IOException("Malformed Input");
        }
    }

    /* renamed from: a */
    public short m3965a() {
        return this.f22189a;
    }

    /* renamed from: a */
    public void m3954a(short s) {
        this.f22189a = s;
    }

    public String toString() {
        return "Blob [chid=" + m3968a() + "; Id=" + C11541aj.m2702a(m3941e()) + "; cmd=" + m3966a() + "; type=" + ((int) m3965a()) + "; from=" + m3939g() + " ]";
    }
}
