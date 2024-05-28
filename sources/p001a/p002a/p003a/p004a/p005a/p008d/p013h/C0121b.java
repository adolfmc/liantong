package p001a.p002a.p003a.p004a.p005a.p008d.p013h;

import p001a.p002a.p003a.p004a.p005a.p007c.C0103d;
import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;

/* renamed from: a.a.a.a.a.d.h.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0121b {

    /* renamed from: a */
    public String f144a = "";

    /* renamed from: b */
    public String f145b = "00000000000000000000000000000000";

    /* renamed from: c */
    public boolean f146c = false;

    /* renamed from: a */
    public String m24201a(String str) {
        byte[] bytes;
        byte[] bytes2;
        try {
            C0122c c0122c = new C0122c();
            c0122c.f149c = false;
            c0122c.f147a = 0;
            if (this.f146c) {
                bytes = C0112e.m24274f(this.f144a);
                bytes2 = C0112e.m24274f(this.f145b);
            } else {
                bytes = this.f144a.getBytes();
                bytes2 = this.f145b.getBytes();
            }
            C0120a c0120a = new C0120a();
            c0120a.m24204b(c0122c, bytes);
            return C0112e.m24285b(c0120a.m24211a(c0122c, bytes2, C0112e.m24272g(str)));
        } catch (Exception e) {
            C0103d.m24387b(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public String m24200b(String str) {
        byte[] bytes;
        try {
            C0122c c0122c = new C0122c();
            c0122c.f149c = false;
            c0122c.f147a = 0;
            if (this.f146c) {
                bytes = C0112e.m24274f(this.f144a);
            } else {
                bytes = this.f144a.getBytes();
            }
            C0120a c0120a = new C0120a();
            c0120a.m24204b(c0122c, bytes);
            return C0112e.m24271g(c0120a.m24212a(c0122c, C0112e.m24274f(str)));
        } catch (Exception e) {
            C0103d.m24387b(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public String m24199c(String str) {
        byte[] bytes;
        byte[] bytes2;
        try {
            C0122c c0122c = new C0122c();
            c0122c.f149c = false;
            c0122c.f147a = 1;
            if (this.f146c) {
                bytes = C0112e.m24274f(this.f144a);
                bytes2 = C0112e.m24274f(this.f145b);
            } else {
                bytes = this.f144a.getBytes();
                bytes2 = this.f145b.getBytes();
            }
            C0120a c0120a = new C0120a();
            c0120a.m24202c(c0122c, bytes);
            return C0112e.m24285b(c0120a.m24211a(c0122c, bytes2, C0112e.m24272g(str)));
        } catch (Exception e) {
            C0103d.m24387b(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public String m24198d(String str) {
        byte[] bytes;
        try {
            C0122c c0122c = new C0122c();
            c0122c.f149c = false;
            c0122c.f147a = 1;
            if (this.f146c) {
                bytes = C0112e.m24274f(this.f144a);
            } else {
                bytes = this.f144a.getBytes();
            }
            C0120a c0120a = new C0120a();
            c0120a.m24202c(c0122c, bytes);
            return C0112e.m24285b(c0120a.m24212a(c0122c, C0112e.m24274f(str)));
        } catch (Exception e) {
            C0103d.m24387b(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
