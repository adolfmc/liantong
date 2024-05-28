package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0;

import java.security.SecureRandom;

/* renamed from: a.a.a.a.a.e.a.c.l0.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0555d implements InterfaceC0552a {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public int mo22849a(byte[] bArr, int i) {
        int length = bArr.length - i;
        while (i < bArr.length) {
            bArr[i] = 0;
            i++;
        }
        return length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public String mo22852a() {
        return "ZeroByte";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public void mo22851a(SecureRandom secureRandom) {
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public int mo22850a(byte[] bArr) {
        int length = bArr.length;
        while (length > 0 && bArr[length - 1] == 0) {
            length--;
        }
        return bArr.length - length;
    }
}
