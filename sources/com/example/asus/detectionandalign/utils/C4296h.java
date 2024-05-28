package com.example.asus.detectionandalign.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Date;
import org.p415a.p445f.C12927b;
import org.p415a.p445f.C12931d;
import org.p415a.p445f.C12937i;
import org.p415a.p445f.C12939k;
import org.p415a.p445f.C12944o;
import org.p415a.p445f.C12946q;
import org.p415a.p445f.C12953x;
import org.p415a.p445f.p446a.p447a.C12905b;
import org.p415a.p445f.p446a.p447a.C12906c;
import org.p415a.p445f.p446a.p447a.C12912f;
import org.p415a.p448g.p449a.C12959a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4296h {
    /* renamed from: a */
    public static String m15947a(String str, String str2) {
        return C12959a.m428a(m15944a(str.getBytes("UTF-8"), m15948a(str2)));
    }

    /* renamed from: a */
    private static C12931d m15946a(C12944o c12944o) {
        C12931d c12931d = new C12931d(new C12906c(9).m501a(true).m503a(new SecureRandom()));
        c12931d.m466a(new C12912f(c12944o));
        return c12931d;
    }

    /* renamed from: a */
    public static C12944o m15948a(String str) {
        return ((C12946q) new C12939k(C12953x.m444a(new ByteArrayInputStream(str.getBytes())), new C12905b()).m456a()).m450a();
    }

    /* renamed from: a */
    private static byte[] m15945a(byte[] bArr) {
        C12927b c12927b = new C12927b(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream m458a = new C12937i().m458a(c12927b.m471a(byteArrayOutputStream), 'b', "", bArr.length, new Date());
        m458a.write(bArr);
        m458a.close();
        c12927b.mo443a();
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static byte[] m15944a(byte[] bArr, C12944o c12944o) {
        byte[] m15945a = m15945a(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream m468a = m15946a(c12944o).m468a(byteArrayOutputStream, m15945a.length);
        m468a.write(m15945a);
        m468a.close();
        return byteArrayOutputStream.toByteArray();
    }
}
