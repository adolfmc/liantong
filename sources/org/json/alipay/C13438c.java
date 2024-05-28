package org.json.alipay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.json.alipay.c */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13438c {

    /* renamed from: a */
    private int f27416a;

    /* renamed from: b */
    private Reader f27417b;

    /* renamed from: c */
    private char f27418c;

    /* renamed from: d */
    private boolean f27419d;

    private C13438c(Reader reader) {
        this.f27417b = reader.markSupported() ? reader : new BufferedReader(reader);
        this.f27419d = false;
        this.f27416a = 0;
    }

    public C13438c(String str) {
        this(new StringReader(str));
    }

    /* renamed from: a */
    private String m216a(int i) {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        int i2 = 0;
        if (this.f27419d) {
            this.f27419d = false;
            cArr[0] = this.f27418c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.f27417b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.f27416a += i2;
        if (i2 >= i) {
            this.f27418c = cArr[i - 1];
            return new String(cArr);
        }
        throw m215a("Substring bounds error");
    }

    /* renamed from: a */
    public final JSONException m215a(String str) {
        return new JSONException(str + toString());
    }

    /* renamed from: a */
    public final void m217a() {
        int i;
        if (this.f27419d || (i = this.f27416a) <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f27416a = i - 1;
        this.f27419d = true;
    }

    /* renamed from: b */
    public final char m214b() {
        if (this.f27419d) {
            this.f27419d = false;
            if (this.f27418c != 0) {
                this.f27416a++;
            }
            return this.f27418c;
        }
        try {
            int read = this.f27417b.read();
            if (read <= 0) {
                this.f27418c = (char) 0;
                return (char) 0;
            }
            this.f27416a++;
            this.f27418c = (char) read;
            return this.f27418c;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0053, code lost:
        return r0;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final char m213c() {
        /*
            r5 = this;
        L0:
            char r0 = r5.m214b()
            r1 = 13
            r2 = 10
            r3 = 47
            if (r0 != r3) goto L3e
            char r0 = r5.m214b()
            r4 = 42
            if (r0 == r4) goto L25
            if (r0 == r3) goto L1a
            r5.m217a()
            return r3
        L1a:
            char r0 = r5.m214b()
            if (r0 == r2) goto L0
            if (r0 == r1) goto L0
            if (r0 != 0) goto L1a
            goto L0
        L25:
            char r0 = r5.m214b()
            if (r0 == 0) goto L37
            if (r0 != r4) goto L25
            char r0 = r5.m214b()
            if (r0 == r3) goto L0
            r5.m217a()
            goto L25
        L37:
            java.lang.String r0 = "Unclosed comment"
            org.json.alipay.JSONException r0 = r5.m215a(r0)
            throw r0
        L3e:
            r3 = 35
            if (r0 != r3) goto L4d
        L42:
            char r0 = r5.m214b()
            if (r0 == r2) goto L0
            if (r0 == r1) goto L0
            if (r0 != 0) goto L42
            goto L0
        L4d:
            if (r0 == 0) goto L53
            r1 = 32
            if (r0 <= r1) goto L0
        L53:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C13438c.m213c():char");
    }

    /* JADX WARN: Code restructure failed: missing block: B:98:0x013d, code lost:
        throw m215a("Unterminated string");
     */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m212d() {
        /*
            Method dump skipped, instructions count: 334
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.C13438c.m212d():java.lang.Object");
    }

    public final String toString() {
        return " at character " + this.f27416a;
    }
}
