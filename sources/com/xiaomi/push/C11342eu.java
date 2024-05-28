package com.xiaomi.push;

import android.os.Build;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.service.C11561ar;
import com.xiaomi.push.service.C11571ax;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.Adler32;

/* renamed from: com.xiaomi.push.eu */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11342eu {

    /* renamed from: a */
    private int f22201a;

    /* renamed from: a */
    private C11350ey f22202a;

    /* renamed from: a */
    private OutputStream f22203a;

    /* renamed from: a */
    private byte[] f22206a;

    /* renamed from: b */
    private int f22207b;

    /* renamed from: a */
    ByteBuffer f22204a = ByteBuffer.allocate(2048);

    /* renamed from: b */
    private ByteBuffer f22208b = ByteBuffer.allocate(4);

    /* renamed from: a */
    private Adler32 f22205a = new Adler32();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C11342eu(OutputStream outputStream, C11350ey c11350ey) {
        this.f22203a = new BufferedOutputStream(outputStream);
        this.f22202a = c11350ey;
        TimeZone timeZone = TimeZone.getDefault();
        this.f22201a = timeZone.getRawOffset() / 3600000;
        this.f22207b = timeZone.useDaylightTime() ? 1 : 0;
    }

    /* renamed from: a */
    public int m3930a(C11339er c11339er) {
        int mo3914c = c11339er.mo3914c();
        if (mo3914c > 32768) {
            AbstractC11049b.m5282a("Blob size=" + mo3914c + " should be less than 32768 Drop blob chid=" + c11339er.m3968a() + " id=" + c11339er.m3941e());
            return 0;
        }
        this.f22204a.clear();
        int i = mo3914c + 8 + 4;
        if (i > this.f22204a.capacity() || this.f22204a.capacity() > 4096) {
            this.f22204a = ByteBuffer.allocate(i);
        }
        this.f22204a.putShort((short) -15618);
        this.f22204a.putShort((short) 5);
        this.f22204a.putInt(mo3914c);
        int position = this.f22204a.position();
        this.f22204a = c11339er.mo3915a(this.f22204a);
        if (!"CONN".equals(c11339er.m3966a())) {
            if (this.f22206a == null) {
                this.f22206a = this.f22202a.m3913a();
            }
            C11561ar.m2643a(this.f22206a, this.f22204a.array(), true, position, mo3914c);
        }
        this.f22205a.reset();
        this.f22205a.update(this.f22204a.array(), 0, this.f22204a.position());
        this.f22208b.putInt(0, (int) this.f22205a.getValue());
        this.f22203a.write(this.f22204a.array(), 0, this.f22204a.position());
        this.f22203a.write(this.f22208b.array(), 0, 4);
        this.f22203a.flush();
        int position2 = this.f22204a.position() + 4;
        AbstractC11049b.m5270c("[Slim] Wrote {cmd=" + c11339er.m3966a() + ";chid=" + c11339er.m3968a() + ";len=" + position2 + "}");
        return position2;
    }

    /* renamed from: a */
    public void m3931a() {
        C11289dp.C11294e c11294e = new C11289dp.C11294e();
        c11294e.m4227a(106);
        c11294e.m4224a(C11470k.m2955a());
        c11294e.m4218b(C11479r.m2933a());
        c11294e.m4213c(C11571ax.m2624a());
        c11294e.m4220b(48);
        c11294e.m4208d(this.f22202a.m3884b());
        c11294e.m4204e(this.f22202a.mo3851a());
        c11294e.m4200f(Locale.getDefault().toString());
        c11294e.m4214c(Build.VERSION.SDK_INT);
        c11294e.m4209d(C11395g.m3720a(this.f22202a.mo3851a(), "com.xiaomi.xmsf"));
        byte[] mo2828a = this.f22202a.mo3851a().mo2828a();
        if (mo2828a != null) {
            c11294e.m4225a(C11289dp.C11291b.m4272a(mo2828a));
        }
        C11339er c11339er = new C11339er();
        c11339er.m3962a(0);
        c11339er.m3956a("CONN", (String) null);
        c11339er.m3960a(0L, "xiaomi.com", null);
        c11339er.m3953a(c11294e.mo4063a(), (String) null);
        m3930a(c11339er);
        AbstractC11049b.m5282a("[slim] open conn: andver=" + Build.VERSION.SDK_INT + " sdk=48 tz=" + this.f22201a + ":" + this.f22207b + " Model=" + C11470k.m2955a() + " os=" + Build.VERSION.INCREMENTAL);
    }

    /* renamed from: b */
    public void m3929b() {
        C11339er c11339er = new C11339er();
        c11339er.m3956a("CLOSE", (String) null);
        m3930a(c11339er);
        this.f22203a.close();
    }
}
