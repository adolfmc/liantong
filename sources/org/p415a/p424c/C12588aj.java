package org.p415a.p424c;

import java.io.ByteArrayOutputStream;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.aj */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12588aj extends AbstractC12611h {

    /* renamed from: a */
    private C12589ak[] f25573a;

    public C12588aj(C12592b c12592b) {
        C12590al c12590al = new C12590al(c12592b);
        Vector vector = new Vector();
        while (true) {
            C12589ak m1579a = c12590al.m1579a();
            if (m1579a == null) {
                break;
            }
            vector.addElement(m1579a);
        }
        this.f25573a = new C12589ak[vector.size()];
        int i = 0;
        while (true) {
            C12589ak[] c12589akArr = this.f25573a;
            if (i == c12589akArr.length) {
                return;
            }
            c12589akArr[i] = (C12589ak) vector.elementAt(i);
            i++;
        }
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            C12589ak[] c12589akArr = this.f25573a;
            if (i == c12589akArr.length) {
                c12608e.m1566a(17, byteArrayOutputStream.toByteArray(), false);
                return;
            } else {
                c12589akArr[i].m1580a(byteArrayOutputStream);
                i++;
            }
        }
    }

    /* renamed from: a */
    public C12589ak[] m1581a() {
        return this.f25573a;
    }
}
