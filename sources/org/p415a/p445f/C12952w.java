package org.p415a.p445f;

import org.p415a.p424c.C12589ak;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12952w {

    /* renamed from: a */
    C12589ak[] f26232a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12952w(C12589ak[] c12589akArr) {
        this.f26232a = c12589akArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C12952w)) {
            return false;
        }
        C12952w c12952w = (C12952w) obj;
        if (c12952w.f26232a.length != this.f26232a.length) {
            return false;
        }
        int i = 0;
        while (true) {
            C12589ak[] c12589akArr = this.f26232a;
            if (i == c12589akArr.length) {
                return true;
            }
            if (!c12952w.f26232a[i].equals(c12589akArr[i])) {
                return false;
            }
            i++;
        }
    }

    public int hashCode() {
        int i = 0;
        int i2 = 0;
        while (true) {
            C12589ak[] c12589akArr = this.f26232a;
            if (i == c12589akArr.length) {
                return i2;
            }
            i2 ^= c12589akArr[i].hashCode();
            i++;
        }
    }
}
