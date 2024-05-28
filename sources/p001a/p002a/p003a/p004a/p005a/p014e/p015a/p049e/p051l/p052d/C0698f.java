package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.p052d;

import java.io.BufferedWriter;
import java.io.Writer;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.C0679a;

/* renamed from: a.a.a.a.a.e.a.e.l.d.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0698f extends BufferedWriter {

    /* renamed from: E */
    public static final int f2062E = 64;

    /* renamed from: C */
    public final int f2063C;

    /* renamed from: D */
    public char[] f2064D;

    public C0698f(Writer writer) {
        super(writer);
        this.f2064D = new char[64];
        String property = System.getProperty("line.separator");
        if (property != null) {
            this.f2063C = property.length();
        } else {
            this.f2063C = 2;
        }
    }

    /* renamed from: b */
    private void m22372b(String str) {
        write(C0697e.f2060C + str + "-----");
        newLine();
    }

    /* renamed from: a */
    public int m22376a(C0694b c0694b) {
        int length = ((c0694b.m22381d().length() + 10 + this.f2063C) * 2) + 6 + 4;
        if (!c0694b.m22382c().isEmpty()) {
            for (C0693a c0693a : c0694b.m22382c()) {
                length += c0693a.m22387a().length() + 2 + c0693a.m22384b().length() + this.f2063C;
            }
            length += this.f2063C;
        }
        int length2 = ((c0694b.m22383b().length + 2) / 3) * 4;
        return length + length2 + ((((length2 + 64) - 1) / 64) * this.f2063C);
    }

    /* renamed from: a */
    public void m22375a(InterfaceC0695c interfaceC0695c) {
        C0694b mo22380a = interfaceC0695c.mo22380a();
        m22372b(mo22380a.m22381d());
        if (!mo22380a.m22382c().isEmpty()) {
            for (C0693a c0693a : mo22380a.m22382c()) {
                write(c0693a.m22387a());
                write(": ");
                write(c0693a.m22384b());
                newLine();
            }
            newLine();
        }
        m22373a(mo22380a.m22383b());
        m22374a(mo22380a.m22381d());
    }

    /* renamed from: a */
    private void m22373a(byte[] bArr) {
        int i;
        byte[] m22431b = C0679a.m22431b(bArr);
        int i2 = 0;
        while (i2 < m22431b.length) {
            int i3 = 0;
            while (true) {
                char[] cArr = this.f2064D;
                if (i3 != cArr.length && (i = i2 + i3) < m22431b.length) {
                    cArr[i3] = (char) m22431b[i];
                    i3++;
                }
            }
            write(this.f2064D, 0, i3);
            newLine();
            i2 += this.f2064D.length;
        }
    }

    /* renamed from: a */
    private void m22374a(String str) {
        write(C0697e.f2061D + str + "-----");
        newLine();
    }
}
