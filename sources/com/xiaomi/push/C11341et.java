package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11561ar;
import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.et */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11341et {

    /* renamed from: a */
    private C11350ey f22194a;

    /* renamed from: a */
    private InputStream f22195a;

    /* renamed from: a */
    private volatile boolean f22198a;

    /* renamed from: a */
    private byte[] f22199a;

    /* renamed from: a */
    private ByteBuffer f22196a = ByteBuffer.allocate(2048);

    /* renamed from: b */
    private ByteBuffer f22200b = ByteBuffer.allocate(4);

    /* renamed from: a */
    private Adler32 f22197a = new Adler32();

    /* renamed from: a */
    private C11348ew f22193a = new C11348ew();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11341et(InputStream inputStream, C11350ey c11350ey) {
        this.f22195a = new BufferedInputStream(inputStream);
        this.f22194a = c11350ey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m3935a() {
        try {
            m3932c();
        } catch (IOException e) {
            if (!this.f22198a) {
                throw e;
            }
        }
    }

    /* renamed from: c */
    private void m3932c() {
        boolean z = false;
        this.f22198a = false;
        C11339er m3937a = m3937a();
        if ("CONN".equals(m3937a.m3966a())) {
            C11289dp.C11295f m4187a = C11289dp.C11295f.m4187a(m3937a.m3963a());
            if (m4187a.m4190a()) {
                this.f22194a.m3847a(m4187a.m4191a());
                z = true;
            }
            if (m4187a.m4183c()) {
                C11289dp.C11291b m4192a = m4187a.m4192a();
                C11339er c11339er = new C11339er();
                c11339er.m3956a("SYNC", "CONF");
                c11339er.m3953a(m4192a.mo4063a(), (String) null);
                this.f22194a.m3912a(c11339er);
            }
            AbstractC11049b.m5282a("[Slim] CONN: host = " + m4187a.m4186b());
        }
        if (!z) {
            AbstractC11049b.m5282a("[Slim] Invalid CONN");
            throw new IOException("Invalid Connection");
        }
        this.f22199a = this.f22194a.m3913a();
        while (!this.f22198a) {
            C11339er m3937a2 = m3937a();
            long currentTimeMillis = System.currentTimeMillis();
            this.f22194a.m3840c();
            switch (m3937a2.m3965a()) {
                case 1:
                    this.f22194a.m3912a(m3937a2);
                    break;
                case 2:
                    if ("SECMSG".equals(m3937a2.m3966a()) && ((m3937a2.m3968a() == 2 || m3937a2.m3968a() == 3) && TextUtils.isEmpty(m3937a2.m3950b()))) {
                        try {
                            AbstractC11375fo m3916a = this.f22193a.m3916a(m3937a2.m3957a(C11545am.m2692a().m2681a(Integer.valueOf(m3937a2.m3968a()).toString(), m3937a2.m3939g()).f23540h), this.f22194a);
                            m3916a.f22325a = currentTimeMillis;
                            this.f22194a.m3909b(m3916a);
                            break;
                        } catch (Exception e) {
                            AbstractC11049b.m5282a("[Slim] Parse packet from Blob chid=" + m3937a2.m3968a() + "; Id=" + m3937a2.m3941e() + " failure:" + e.getMessage());
                            break;
                        }
                    } else {
                        this.f22194a.m3912a(m3937a2);
                        break;
                    }
                    break;
                case 3:
                    try {
                        this.f22194a.m3909b(this.f22193a.m3916a(m3937a2.m3963a(), this.f22194a));
                        break;
                    } catch (Exception e2) {
                        AbstractC11049b.m5282a("[Slim] Parse packet from Blob chid=" + m3937a2.m3968a() + "; Id=" + m3937a2.m3941e() + " failure:" + e2.getMessage());
                        break;
                    }
                default:
                    AbstractC11049b.m5282a("[Slim] unknow blob type " + ((int) m3937a2.m3965a()));
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m3933b() {
        this.f22198a = true;
    }

    /* renamed from: a */
    private ByteBuffer m3936a() {
        this.f22196a.clear();
        m3934a(this.f22196a, 8);
        short s = this.f22196a.getShort(0);
        short s2 = this.f22196a.getShort(2);
        if (s != -15618 || s2 != 5) {
            throw new IOException("Malformed Input");
        }
        int i = this.f22196a.getInt(4);
        int position = this.f22196a.position();
        if (i > 32768) {
            throw new IOException("Blob size too large");
        }
        if (i + 4 > this.f22196a.remaining()) {
            ByteBuffer allocate = ByteBuffer.allocate(i + 2048);
            allocate.put(this.f22196a.array(), 0, this.f22196a.arrayOffset() + this.f22196a.position());
            this.f22196a = allocate;
        } else if (this.f22196a.capacity() > 4096 && i < 2048) {
            ByteBuffer allocate2 = ByteBuffer.allocate(2048);
            allocate2.put(this.f22196a.array(), 0, this.f22196a.arrayOffset() + this.f22196a.position());
            this.f22196a = allocate2;
        }
        m3934a(this.f22196a, i);
        this.f22200b.clear();
        m3934a(this.f22200b, 4);
        this.f22200b.position(0);
        int i2 = this.f22200b.getInt();
        this.f22197a.reset();
        this.f22197a.update(this.f22196a.array(), 0, this.f22196a.position());
        if (i2 != ((int) this.f22197a.getValue())) {
            AbstractC11049b.m5282a("CRC = " + ((int) this.f22197a.getValue()) + " and " + i2);
            throw new IOException("Corrupted Blob bad CRC");
        }
        byte[] bArr = this.f22199a;
        if (bArr != null) {
            C11561ar.m2643a(bArr, this.f22196a.array(), true, position, i);
        }
        return this.f22196a;
    }

    /* renamed from: a */
    C11339er m3937a() {
        int i;
        C11339er m3955a;
        try {
            ByteBuffer m3936a = m3936a();
            i = m3936a.position();
            try {
                m3936a.flip();
                m3936a.position(8);
                if (i == 8) {
                    m3955a = new C11349ex();
                } else {
                    m3955a = C11339er.m3955a(m3936a.slice());
                }
                AbstractC11049b.m5270c("[Slim] Read {cmd=" + m3955a.m3966a() + ";chid=" + m3955a.m3968a() + ";len=" + i + "}");
                return m3955a;
            } catch (IOException e) {
                e = e;
                if (i == 0) {
                    i = this.f22196a.position();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("[Slim] read Blob [");
                byte[] array = this.f22196a.array();
                if (i > 128) {
                    i = 128;
                }
                sb.append(C11131ab.m4942a(array, 0, i));
                sb.append("] Err:");
                sb.append(e.getMessage());
                AbstractC11049b.m5282a(sb.toString());
                throw e;
            }
        } catch (IOException e2) {
            e = e2;
            i = 0;
        }
    }

    /* renamed from: a */
    private void m3934a(ByteBuffer byteBuffer, int i) {
        int position = byteBuffer.position();
        do {
            int read = this.f22195a.read(byteBuffer.array(), position, i);
            if (read == -1) {
                throw new EOFException();
            }
            i -= read;
            position += read;
        } while (i > 0);
        byteBuffer.position(position);
    }
}
