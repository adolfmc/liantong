package com.megvii.lv5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.a5 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5382a5 implements InterfaceC5478i4 {

    /* renamed from: e */
    public static final char[] f12375e = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: c */
    public String f12378c;

    /* renamed from: a */
    public final byte[] f12376a = "Content-Transfer-Encoding: binary\r\n\r\n".getBytes();

    /* renamed from: b */
    public final byte[] f12377b = "Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes();

    /* renamed from: d */
    public ByteArrayOutputStream f12379d = new ByteArrayOutputStream();

    public C5382a5() {
        this.f12378c = null;
        this.f12378c = m13611d();
    }

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: a */
    public void mo13280a() {
    }

    /* renamed from: a */
    public void m13614a(String str, String str2) {
        m13613a(str, str2.getBytes(), "text/plain; charset=UTF-8", this.f12377b, "");
    }

    /* renamed from: a */
    public final void m13613a(String str, byte[] bArr, String str2, byte[] bArr2, String str3) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = this.f12379d;
            byteArrayOutputStream.write(("--" + this.f12378c + "\r\n").getBytes());
            ByteArrayOutputStream byteArrayOutputStream2 = this.f12379d;
            byteArrayOutputStream2.write(("Content-Type: " + str2 + "\r\n").getBytes());
            this.f12379d.write(m13612b(str, str3));
            this.f12379d.write(bArr2);
            this.f12379d.write(bArr);
            this.f12379d.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: b */
    public InputStream mo13279b() {
        return new ByteArrayInputStream(this.f12379d.toByteArray());
    }

    /* renamed from: b */
    public final byte[] m13612b(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("Content-Disposition: form-data; name=\"" + str + "\"");
        if (str2 != null && !"".equals(str2)) {
            sb.append("; filename=\"" + str2 + "\"");
        }
        sb.append("\r\n");
        return sb.toString().getBytes();
    }

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: c */
    public long mo13278c() {
        return this.f12379d.toByteArray().length;
    }

    /* renamed from: d */
    public final String m13611d() {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            char[] cArr = f12375e;
            stringBuffer.append(cArr[random.nextInt(cArr.length)]);
        }
        return stringBuffer.toString();
    }
}
