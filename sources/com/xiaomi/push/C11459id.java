package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.id */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11459id {

    /* renamed from: a */
    private static int f23326a = Integer.MAX_VALUE;

    /* renamed from: a */
    public static void m2995a(AbstractC11456ia abstractC11456ia, byte b) {
        m2994a(abstractC11456ia, b, f23326a);
    }

    /* renamed from: a */
    public static void m2994a(AbstractC11456ia abstractC11456ia, byte b, int i) {
        if (i <= 0) {
            throw new C11448hu("Maximum skip depth exceeded");
        }
        int i2 = 0;
        switch (b) {
            case 2:
                abstractC11456ia.mo3017a();
                return;
            case 3:
                abstractC11456ia.mo3025a();
                return;
            case 4:
                abstractC11456ia.mo3024a();
                return;
            case 5:
            case 7:
            case 9:
            default:
                return;
            case 6:
                abstractC11456ia.mo3019a();
                return;
            case 8:
                abstractC11456ia.mo3023a();
                return;
            case 10:
                abstractC11456ia.mo3022a();
                return;
            case 11:
                abstractC11456ia.mo2989a();
                return;
            case 12:
                abstractC11456ia.mo3020a();
                while (true) {
                    C11452hx mo3021a = abstractC11456ia.mo3021a();
                    if (mo3021a.f23307a != 0) {
                        m2994a(abstractC11456ia, mo3021a.f23307a, i - 1);
                        abstractC11456ia.mo3000g();
                    } else {
                        abstractC11456ia.mo3001f();
                        return;
                    }
                }
            case 13:
                C11454hz mo2992a = abstractC11456ia.mo2992a();
                while (i2 < mo2992a.f23313a) {
                    int i3 = i - 1;
                    m2994a(abstractC11456ia, mo2992a.f23312a, i3);
                    m2994a(abstractC11456ia, mo2992a.f23314b, i3);
                    i2++;
                }
                abstractC11456ia.mo2999h();
                return;
            case 14:
                C11460ie mo2991a = abstractC11456ia.mo2991a();
                while (i2 < mo2991a.f23328a) {
                    m2994a(abstractC11456ia, mo2991a.f23327a, i - 1);
                    i2++;
                }
                abstractC11456ia.mo2997j();
                return;
            case 15:
                C11453hy mo2993a = abstractC11456ia.mo2993a();
                while (i2 < mo2993a.f23311a) {
                    m2994a(abstractC11456ia, mo2993a.f23310a, i - 1);
                    i2++;
                }
                abstractC11456ia.mo2998i();
                return;
        }
    }
}
