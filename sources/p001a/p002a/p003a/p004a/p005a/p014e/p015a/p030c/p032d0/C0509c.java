package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: a.a.a.a.a.e.a.c.d0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0509c extends AbstractC0507a {

    /* renamed from: A */
    public static final int f1644A = 21;

    /* renamed from: k */
    public static final int f1645k = 16;

    /* renamed from: l */
    public static final int f1646l = 7;

    /* renamed from: m */
    public static final int f1647m = 12;

    /* renamed from: n */
    public static final int f1648n = 17;

    /* renamed from: o */
    public static final int f1649o = 22;

    /* renamed from: p */
    public static final int f1650p = 5;

    /* renamed from: q */
    public static final int f1651q = 9;

    /* renamed from: r */
    public static final int f1652r = 14;

    /* renamed from: s */
    public static final int f1653s = 20;

    /* renamed from: t */
    public static final int f1654t = 4;

    /* renamed from: u */
    public static final int f1655u = 11;

    /* renamed from: v */
    public static final int f1656v = 16;

    /* renamed from: w */
    public static final int f1657w = 23;

    /* renamed from: x */
    public static final int f1658x = 6;

    /* renamed from: y */
    public static final int f1659y = 10;

    /* renamed from: z */
    public static final int f1660z = 15;

    /* renamed from: e */
    public int f1661e;

    /* renamed from: f */
    public int f1662f;

    /* renamed from: g */
    public int f1663g;

    /* renamed from: h */
    public int f1664h;

    /* renamed from: i */
    public int[] f1665i;

    /* renamed from: j */
    public int f1666j;

    public C0509c() {
        this.f1665i = new int[16];
        mo22744b();
    }

    /* renamed from: a */
    private int m22950a(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: a */
    private int m22949a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: a */
    private void m22947a(C0509c c0509c) {
        super.m22964a((AbstractC0507a) c0509c);
        this.f1661e = c0509c.f1661e;
        this.f1662f = c0509c.f1662f;
        this.f1663g = c0509c.f1663g;
        this.f1664h = c0509c.f1664h;
        int[] iArr = c0509c.f1665i;
        System.arraycopy(iArr, 0, this.f1665i, 0, iArr.length);
        this.f1666j = c0509c.f1666j;
    }

    /* renamed from: b */
    private int m22946b(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: c */
    private int m22945c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: d */
    private int m22944d(int i, int i2, int i3) {
        return (i | (~i3)) ^ i2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public String mo22748a() {
        return "MD5";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: b */
    public void mo22933b(byte[] bArr, int i) {
        int[] iArr = this.f1665i;
        int i2 = this.f1666j;
        int i3 = i2 + 1;
        this.f1666j = i3;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (i3 == 16) {
            mo22930g();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: c */
    public int mo22743c() {
        return 16;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: e */
    public InterfaceC0674f mo22453e() {
        return new C0509c(this);
    }

    /*  JADX ERROR: Failed to decode insn: 0x04BC: CONST_STRING r0, method: a.a.a.a.a.e.a.c.d0.c.g():void
        java.lang.IllegalArgumentException: newPosition > limit: (21495920 > 13017656)
        	at java.nio.Buffer.createPositionException(Buffer.java:269)
        	at java.nio.Buffer.position(Buffer.java:244)
        	at jadx.plugins.input.dex.sections.SectionReader.absPos(SectionReader.java:82)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:175)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: g */
    public void mo22930g() {
        /*
            Method dump skipped, instructions count: 1361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0509c.mo22930g():void");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: b */
    public void mo22744b() {
        super.mo22744b();
        this.f1661e = 1732584193;
        this.f1662f = -271733879;
        this.f1663g = -1732584194;
        this.f1664h = 271733878;
        this.f1666j = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f1665i;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.AbstractC0507a
    /* renamed from: a */
    public void mo22937a(long j) {
        if (this.f1666j > 14) {
            mo22930g();
        }
        int[] iArr = this.f1665i;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    public C0509c(C0509c c0509c) {
        super(c0509c);
        this.f1665i = new int[16];
        m22947a(c0509c);
    }

    /* renamed from: a */
    private void m22948a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o
    /* renamed from: a */
    public int mo22746a(byte[] bArr, int i) {
        m22963f();
        m22948a(this.f1661e, bArr, i);
        m22948a(this.f1662f, bArr, i + 4);
        m22948a(this.f1663g, bArr, i + 8);
        m22948a(this.f1664h, bArr, i + 12);
        mo22744b();
        return 16;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f
    /* renamed from: a */
    public void mo22454a(InterfaceC0674f interfaceC0674f) {
        m22947a((C0509c) interfaceC0674f);
    }
}
