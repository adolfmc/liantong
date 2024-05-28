package org.p415a.p445f.p446a.p447a;

import org.p415a.p427d.InterfaceC12630a;
import org.p415a.p427d.InterfaceC12680d;
import org.p415a.p427d.InterfaceC12724i;
import org.p415a.p427d.InterfaceC12734s;
import org.p415a.p427d.p428a.C12633c;
import org.p415a.p427d.p428a.C12634d;
import org.p415a.p427d.p428a.C12635e;
import org.p415a.p427d.p428a.C12636f;
import org.p415a.p427d.p428a.C12637g;
import org.p415a.p427d.p428a.C12638h;
import org.p415a.p427d.p428a.C12639i;
import org.p415a.p427d.p428a.C12640j;
import org.p415a.p427d.p428a.C12641k;
import org.p415a.p427d.p430c.C12677a;
import org.p415a.p427d.p431d.C12681a;
import org.p415a.p427d.p431d.C12682b;
import org.p415a.p427d.p431d.C12683c;
import org.p415a.p427d.p431d.C12684d;
import org.p415a.p427d.p431d.C12685e;
import org.p415a.p427d.p431d.C12686f;
import org.p415a.p427d.p431d.C12687g;
import org.p415a.p427d.p431d.C12688h;
import org.p415a.p427d.p431d.C12689i;
import org.p415a.p427d.p431d.C12690j;
import org.p415a.p427d.p431d.C12692l;
import org.p415a.p445f.C12934f;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12904a {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static InterfaceC12724i m508a(int i) {
        switch (i) {
            case 1:
                return new C12634d();
            case 2:
                return new C12636f();
            case 3:
                return new C12635e();
            case 4:
            case 7:
            default:
                throw new C12934f("cannot recognise digest");
            case 5:
                return new C12633c();
            case 6:
                return new C12641k();
            case 8:
                return new C12638h();
            case 9:
                return new C12639i();
            case 10:
                return new C12640j();
            case 11:
                return new C12637g();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static InterfaceC12680d m507b(int i) {
        switch (i) {
            case 1:
                return new C12688h();
            case 2:
                return new C12686f();
            case 3:
                return new C12683c();
            case 4:
                return new C12682b();
            case 5:
            default:
                throw new C12934f("cannot recognise cipher");
            case 6:
                return new C12685e();
            case 7:
            case 8:
            case 9:
                return new C12681a();
            case 10:
                return new C12692l();
            case 11:
            case 12:
            case 13:
                return new C12684d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static InterfaceC12734s m506c(int i) {
        switch (i) {
            case 7:
            case 8:
            case 9:
                return new C12689i(new C12681a());
            case 10:
            default:
                throw new C12934f("unknown wrap algorithm: " + i);
            case 11:
            case 12:
            case 13:
                return new C12689i(new C12684d());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static InterfaceC12630a m505d(int i) {
        switch (i) {
            case 1:
            case 2:
                return new C12677a(new C12690j());
            default:
                switch (i) {
                    case 16:
                    case 20:
                        return new C12677a(new C12687g());
                    case 17:
                        throw new C12934f("Can't use DSA for encryption.");
                    case 18:
                        throw new C12934f("Not implemented.");
                    case 19:
                        throw new C12934f("Can't use ECDSA for encryption.");
                    default:
                        throw new C12934f("unknown asymmetric algorithm: " + i);
                }
        }
    }
}
