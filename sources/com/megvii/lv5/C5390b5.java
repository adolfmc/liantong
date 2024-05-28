package com.megvii.lv5;

import com.megvii.lv5.C5668z3;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.b5 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5390b5 extends AbstractC5652x3<String> {

    /* renamed from: l */
    public C5382a5 f12398l;

    /* renamed from: m */
    public C5668z3.InterfaceC5670b<String> f12399m;

    public C5390b5(String str, C5668z3.InterfaceC5670b<String> interfaceC5670b, C5668z3.InterfaceC5669a interfaceC5669a) {
        super(1, str, interfaceC5669a);
        new HashMap();
        this.f12399m = interfaceC5670b;
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: a */
    public void mo12901a() {
        this.f13906e = null;
        this.f12399m = null;
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: b */
    public byte[] mo12871b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            C5382a5 c5382a5 = this.f12398l;
            c5382a5.getClass();
            c5382a5.f12379d.write(("--" + c5382a5.f12378c + "--\r\n").getBytes());
            byteArrayOutputStream.write(c5382a5.f12379d.toByteArray());
        } catch (IOException unused) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: c */
    public String mo12870c() {
        C5382a5 c5382a5;
        this.f12398l.getClass();
        String str = "multipart/form-data; boundary=" + c5382a5.f12378c;
        String str2 = (String) C5527o2.m13246a("Content-Type", "Name");
        return str;
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: a */
    public void mo12872a(String str) {
        String str2 = str;
        C5668z3.InterfaceC5670b<String> interfaceC5670b = this.f12399m;
        if (interfaceC5670b != null) {
            interfaceC5670b.mo12873a(str2);
        }
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: a */
    public C5668z3<String> mo12889a(C5622u3 c5622u3) {
        String str;
        try {
            str = new String(c5622u3.f13746b, C5527o2.m13243a(c5622u3.f13747c, "ISO-8859-1"));
        } catch (UnsupportedEncodingException unused) {
            str = new String(c5622u3.f13746b);
        }
        return new C5668z3<>(str, C5527o2.m13248a(c5622u3));
    }
}
