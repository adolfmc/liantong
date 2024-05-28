package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p042n0;

import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0640u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0589q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;

/* renamed from: a.a.a.a.a.e.a.c.n0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0604b implements InterfaceC0640u {

    /* renamed from: a */
    public C0589q f1897a;

    public C0604b(C0589q c0589q) {
        this.f1897a = c0589q;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0640u
    /* renamed from: a */
    public C0557a mo22657a(InputStream inputStream) {
        byte[] bArr;
        int read = inputStream.read();
        switch (read) {
            case 0:
                throw new IOException("Sender's public key invalid.");
            case 1:
            case 5:
            default:
                throw new IOException("Sender's public key has invalid point encoding 0x" + Integer.toString(read, 16));
            case 2:
            case 3:
                bArr = new byte[((this.f1897a.m22775a().mo22604c() + 7) / 8) + 1];
                break;
            case 4:
            case 6:
            case 7:
                bArr = new byte[(((this.f1897a.m22775a().mo22604c() + 7) / 8) * 2) + 1];
                break;
        }
        bArr[0] = (byte) read;
        inputStream.read(bArr, 1, bArr.length - 1);
        return new C0596u(this.f1897a.m22775a().m22621a(bArr), this.f1897a);
    }
}
