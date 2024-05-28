package org.p415a.p445f.p446a.p447a;

import java.io.OutputStream;
import java.security.SecureRandom;
import org.p415a.p427d.C12693e;
import org.p415a.p427d.p433f.C12698b;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.p446a.InterfaceC12919c;
import org.p415a.p445f.p446a.InterfaceC12920d;
import org.p415a.p445f.p446a.InterfaceC12921e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12906c implements InterfaceC12920d {

    /* renamed from: a */
    private SecureRandom f26149a;

    /* renamed from: b */
    private boolean f26150b;

    /* renamed from: c */
    private int f26151c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.a.a.c$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C12907a implements InterfaceC12919c {

        /* renamed from: b */
        private final C12693e f26153b;

        C12907a(byte[] bArr) {
            try {
                this.f26153b = C12914g.m493a(true, C12904a.m507b(C12906c.this.f26151c), C12906c.this.f26150b, bArr);
            } catch (IllegalArgumentException e) {
                throw new C12934f("invalid parameters: " + e.getMessage(), e);
            }
        }

        @Override // org.p415a.p445f.p446a.InterfaceC12919c
        /* renamed from: a */
        public OutputStream mo486a(OutputStream outputStream) {
            return new C12698b(outputStream, this.f26153b);
        }

        @Override // org.p415a.p445f.p446a.InterfaceC12919c
        /* renamed from: a */
        public InterfaceC12921e mo487a() {
            if (C12906c.this.f26150b) {
                return new C12916i();
            }
            return null;
        }

        @Override // org.p415a.p445f.p446a.InterfaceC12919c
        /* renamed from: b */
        public int mo485b() {
            return this.f26153b.m1371a();
        }
    }

    public C12906c(int i) {
        this.f26151c = i;
        if (i == 0) {
            throw new IllegalArgumentException("null cipher specified");
        }
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12920d
    /* renamed from: a */
    public int mo484a() {
        return this.f26151c;
    }

    /* renamed from: a */
    public C12906c m503a(SecureRandom secureRandom) {
        this.f26149a = secureRandom;
        return this;
    }

    /* renamed from: a */
    public C12906c m501a(boolean z) {
        this.f26150b = z;
        return this;
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12920d
    /* renamed from: a */
    public InterfaceC12919c mo483a(byte[] bArr) {
        return new C12907a(bArr);
    }

    @Override // org.p415a.p445f.p446a.InterfaceC12920d
    /* renamed from: b */
    public SecureRandom mo482b() {
        if (this.f26149a == null) {
            this.f26149a = new SecureRandom();
        }
        return this.f26149a;
    }
}
