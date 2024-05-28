package com.p226hw.videoprocessor.jssrc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

/*  JADX ERROR: NullPointerException in pass: ExtractFieldInit
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.isAllBlocksEmpty(BlockUtils.java:977)
    	at jadx.core.dex.visitors.ExtractFieldInit.getConstructorsList(ExtractFieldInit.java:379)
    	at jadx.core.dex.visitors.ExtractFieldInit.moveCommonFieldsInit(ExtractFieldInit.java:128)
    	at jadx.core.dex.visitors.ExtractFieldInit.visit(ExtractFieldInit.java:51)
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.hw.videoprocessor.jssrc.SSRC */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SSRC {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int POOLSIZE = 97;
    private static final int RANDBUFLEN = 65536;
    private static final String VERSION = "1.30";

    /* renamed from: AA */
    private double f12190AA;

    /* renamed from: DF */
    private double f12191DF;
    private int FFTFIRLEN;
    private ByteOrder byteOrder;
    private SplitRadixFft fft;
    private long lastshowed;
    private int lastshowed2;
    private boolean quiet;
    private double[] randbuf;
    private int randptr;
    private double[][] shapebuf;
    private int shaper_clipmax;
    private int shaper_clipmin;
    private int shaper_len;
    private int shaper_type;
    private long starttime;
    private static final int[] scoeffreq = {0, 48000, 44100, 37800, 32000, 22050, 48000, 44100};
    private static final int[] scoeflen = {1, 16, 20, 16, 16, 15, 16, 15};
    private static final int[] samp = {8, 18, 27, 8, 8, 8, 10, 9};
    private static final double[][] shapercoefs = {new double[]{-1.0d}, new double[]{-2.87207293510437d, 5.041323184967041d, -6.244299411773682d, 5.848398685455322d, -3.706754207611084d, 1.0495119094848633d, 1.1830236911773682d, -2.1126792430877686d, 1.9094531536102295d, -0.9991308450698853d, 0.17090806365013123d, 0.32615602016448975d, -0.39127644896507263d, 0.2687646150588989d, -0.0976761057972908d, 0.023473845794796944d}, new double[]{-2.6773197650909424d, 4.830892562866211d, -6.570110321044922d, 7.4572014808654785d, -6.726327419281006d, 4.848165035247803d, -2.0412089824676514d, -0.7006359100341797d, 2.95375657081604d, -4.080038547515869d, 4.184521675109863d, -3.331181287765503d, 2.117992639541626d, -0.879302978515625d, 0.031759146600961685d, 0.4238278865814209d, -0.4788210391998291d, 0.35490813851356506d, -0.1749683916568756d, 0.06090816855430603d}, new double[]{-1.6335992813110352d, 2.261549234390259d, -2.407702922821045d, 2.634171724319458d, -2.144036293029785d, 1.8153258562088013d, -1.0816224813461304d, 0.703026533126831d, -0.15991993248462677d, -0.04154951870441437d, 0.2941657602787018d, -0.25183168053627014d, 0.27766478061676025d, -0.15785403549671173d, 0.10165894031524658d, -0.016833892092108727d}, new double[]{-0.8290129899978638d, 0.9892265796661377d, -0.5982571244239807d, 1.0028809309005737d, -0.5993821620941162d, 0.7950245141983032d, -0.42723315954208374d, 0.5449252724647522d, -0.3079260587692261d, 0.3687179982662201d, -0.187920480966568d, 0.2261127084493637d, -0.10573341697454453d, 0.11435490846633911d, -0.0388006791472435d, 0.040842197835445404d}, new double[]{-0.06522997468709946d, 0.5498126149177551d, 0.4027854800224304d, 0.3178376853466034d, 0.2820179760456085d, 0.16985194385051727d, 0.15433363616466522d, 0.12507140636444092d, 0.08903945237398148d, 0.06441012024879456d, 0.04714600369334221d, 0.03280523791909218d, 0.028495194390416145d, 0.011695005930960178d, 0.011831838637590408d}, new double[]{-2.3925774097442627d, 3.4350297451019287d, -3.185370922088623d, 1.8117271661758423d, 0.2012477070093155d, -1.4759907722473145d, 1.7210904359817505d, -0.9774670004844666d, 0.13790138065814972d, 0.38185903429985046d, -0.27421241998672485d, -0.06658421456813812d, 0.35223302245140076d, -0.37672343850135803d, 0.23964276909828186d, -0.06867482513189316d}, new double[]{-2.0833916664123535d, 3.0418450832366943d, -3.204789876937866d, 2.757192611694336d, -1.4978630542755127d, 0.34275946021080017d, 0.7173374891281128d, -1.073705792427063d, 1.0225815773010254d, -0.5664999485015869d, 0.20968692004680634d, 0.06537853181362152d, -0.10322438180446625d, 0.06744202226400375d, 0.00495197344571352d}};
    private static final double[] presets = {0.7d, 0.9d, 0.18d};

    /*  JADX ERROR: Dependency scan failed at insn: 0x04A5: INVOKE_CUSTOM r8
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x5
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:128)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:84)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:82)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:67)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:56)
        	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:41)
        	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:282)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0482: UNKNOWN(0x003F), method: com.hw.videoprocessor.jssrc.SSRC.<init>(java.lang.String[]):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0482: UNKNOWN(0x003F)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
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
    /*  JADX ERROR: Failed to decode insn: 0x04A5: INVOKE_CUSTOM r8, method: com.hw.videoprocessor.jssrc.SSRC.<init>(java.lang.String[]):void
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0x5
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:443)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x5
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    SSRC(java.lang.String[] r50) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.jssrc.SSRC.<init>(java.lang.String[]):void");
    }

    private int RINT(double d) {
        return (int) (d >= 0.0d ? d + 0.5d : d - 0.5d);
    }

    private void quit_shaper(int i) {
    }

    /*  JADX ERROR: Failed to decode insn: 0x03FC: UNKNOWN(0x01E6), method: com.hw.videoprocessor.jssrc.SSRC.downsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x03FC: UNKNOWN(0x01E6)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
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
    /*  JADX ERROR: Failed to decode insn: 0x056E: FILLED_NEW_ARRAY_RANGE , method: com.hw.videoprocessor.jssrc.SSRC.downsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
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
    public double downsample(java.io.InputStream r62, java.io.OutputStream r63, int r64, int r65, int r66, int r67, int r68, double r69, int r71, boolean r72, int r73) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2514
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.jssrc.SSRC.downsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double");
    }

    /*  JADX ERROR: Failed to decode insn: 0x055A: UNKNOWN(0x0440), method: com.hw.videoprocessor.jssrc.SSRC.upsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x055A: UNKNOWN(0x0440)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
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
    /*  JADX ERROR: Failed to decode insn: 0x05AE: UNKNOWN(0x0041), method: com.hw.videoprocessor.jssrc.SSRC.upsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05AE: UNKNOWN(0x0041)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
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
    /*  JADX ERROR: Failed to decode insn: 0x08E0: UNKNOWN(0x0540), method: com.hw.videoprocessor.jssrc.SSRC.upsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x08E0: UNKNOWN(0x0540)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
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
    public double upsample(java.io.InputStream r69, java.io.OutputStream r70, int r71, int r72, int r73, int r74, int r75, double r76, int r78, boolean r79, int r80) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.jssrc.SSRC.upsample(java.io.InputStream, java.io.OutputStream, int, int, int, int, int, double, int, boolean, int):double");
    }

    public int init_shaper(int i, int i2, int i3, int i4, int i5, int i6, double d) {
        int nextInt;
        int nextInt2;
        int[] iArr = new int[97];
        int i7 = 1;
        while (i7 < 6 && i != scoeffreq[i7]) {
            i7++;
        }
        if ((i5 == 3 || i5 == 4) && i7 == 6) {
            System.err.printf("Warning: ATH based noise shaping for destination frequency %dHz is not available, using triangular dither\n", Integer.valueOf(i));
        }
        i7 = (i5 == 2 || i7 == 6) ? 0 : 0;
        if (i5 == 4 && (i7 == 1 || i7 == 2)) {
            i7 += 5;
        }
        this.shaper_type = i7;
        this.shapebuf = new double[i2];
        this.shaper_len = scoeflen[this.shaper_type];
        for (int i8 = 0; i8 < i2; i8++) {
            this.shapebuf[i8] = new double[this.shaper_len];
        }
        this.shaper_clipmin = i3;
        this.shaper_clipmax = i4;
        this.randbuf = new double[65536];
        Random random = new Random(System.currentTimeMillis());
        for (int i9 = 0; i9 < 97; i9++) {
            iArr[i9] = random.nextInt();
        }
        switch (i6) {
            case 0:
                for (int i10 = 0; i10 < 65536; i10++) {
                    int nextInt3 = random.nextInt() % 97;
                    int i11 = iArr[nextInt3];
                    iArr[nextInt3] = random.nextInt();
                    this.randbuf[i10] = ((i11 / 2.147483647E9d) - 0.5d) * d;
                }
                break;
            case 1:
                for (int i12 = 0; i12 < 65536; i12++) {
                    int nextInt4 = random.nextInt() % 97;
                    int i13 = iArr[nextInt4];
                    iArr[nextInt4] = random.nextInt();
                    int nextInt5 = random.nextInt() % 97;
                    int i14 = iArr[nextInt5];
                    iArr[nextInt5] = random.nextInt();
                    this.randbuf[i12] = d * ((i13 / 2.147483647E9d) - (i14 / 2.147483647E9d));
                }
                break;
            case 2:
                boolean z = false;
                double d2 = 0.0d;
                double d3 = 0.0d;
                for (int i15 = 0; i15 < 65536; i15++) {
                    if (!z) {
                        double d4 = iArr[nextInt] / 2.147483647E9d;
                        iArr[random.nextInt() % 97] = random.nextInt();
                        if (d4 == 1.0d) {
                            d4 = 0.0d;
                        }
                        double sqrt = Math.sqrt(Math.log(1.0d - d4) * (-2.0d));
                        iArr[random.nextInt() % 97] = random.nextInt();
                        double d5 = (iArr[nextInt2] / 2.147483647E9d) * 6.283185307179586d;
                        this.randbuf[i15] = d * sqrt * Math.cos(d5);
                        d3 = d5;
                        d2 = sqrt;
                        z = true;
                    } else {
                        this.randbuf[i15] = d * d2 * Math.sin(d3);
                        z = false;
                    }
                }
                break;
        }
        this.randptr = 0;
        if (i5 == 0 || i5 == 1) {
            return 1;
        }
        return samp[this.shaper_type];
    }

    public int do_shaping(double d, double[] dArr, int i, int i2) {
        int i3;
        double RINT;
        if (i == 1) {
            double[] dArr2 = this.randbuf;
            int i4 = this.randptr;
            this.randptr = i4 + 1;
            double d2 = d + dArr2[i4 & 65535];
            int i5 = this.shaper_clipmin;
            if (d2 < i5) {
                double d3 = d2 / i5;
                if (dArr[0] >= d3) {
                    d3 = dArr[0];
                }
                dArr[0] = d3;
                d2 = this.shaper_clipmin;
            }
            int i6 = this.shaper_clipmax;
            if (d2 > i6) {
                double d4 = d2 / i6;
                if (dArr[0] >= d4) {
                    d4 = dArr[0];
                }
                dArr[0] = d4;
                d2 = this.shaper_clipmax;
            }
            return RINT(d2);
        }
        double d5 = 0.0d;
        int i7 = 0;
        while (true) {
            i3 = this.shaper_len;
            if (i7 >= i3) {
                break;
            }
            d5 += shapercoefs[this.shaper_type][i7] * this.shapebuf[i2][i7];
            i7++;
        }
        double d6 = d + d5;
        double[] dArr3 = this.randbuf;
        int i8 = this.randptr;
        this.randptr = i8 + 1;
        double d7 = dArr3[65535 & i8] + d6;
        for (int i9 = i3 - 2; i9 >= 0; i9--) {
            double[][] dArr4 = this.shapebuf;
            dArr4[i2][i9 + 1] = dArr4[i2][i9];
        }
        int i10 = this.shaper_clipmin;
        if (d7 < i10) {
            double d8 = d7 / i10;
            if (dArr[0] >= d8) {
                d8 = dArr[0];
            }
            dArr[0] = d8;
            RINT = this.shaper_clipmin;
            double[][] dArr5 = this.shapebuf;
            dArr5[i2][0] = RINT - d6;
            if (dArr5[i2][0] > 1.0d) {
                dArr5[i2][0] = 1.0d;
            }
            double[][] dArr6 = this.shapebuf;
            if (dArr6[i2][0] < -1.0d) {
                dArr6[i2][0] = -1.0d;
            }
        } else {
            int i11 = this.shaper_clipmax;
            if (d7 > i11) {
                double d9 = d7 / i11;
                if (dArr[0] >= d9) {
                    d9 = dArr[0];
                }
                dArr[0] = d9;
                RINT = this.shaper_clipmax;
                double[][] dArr7 = this.shapebuf;
                dArr7[i2][0] = RINT - d6;
                if (dArr7[i2][0] > 1.0d) {
                    dArr7[i2][0] = 1.0d;
                }
                double[][] dArr8 = this.shapebuf;
                if (dArr8[i2][0] < -1.0d) {
                    dArr8[i2][0] = -1.0d;
                }
            } else {
                RINT = RINT(d7);
                this.shapebuf[i2][0] = RINT - d6;
            }
        }
        return (int) RINT;
    }

    private double alpha(double d) {
        if (d <= 21.0d) {
            return 0.0d;
        }
        if (d <= 50.0d) {
            double d2 = d - 21.0d;
            return (Math.pow(d2, 0.4d) * 0.5842d) + (d2 * 0.07886d);
        }
        return (d - 8.7d) * 0.1102d;
    }

    private double win(double d, int i, double d2, double d3) {
        double d4 = 4.0d * d * d;
        double d5 = i - 1.0d;
        return I0Bessel.value(d2 * Math.sqrt(1.0d - (d4 / (d5 * d5)))) / d3;
    }

    private double sinc(double d) {
        if (d == 0.0d) {
            return 1.0d;
        }
        return Math.sin(d) / d;
    }

    private double hn_lpf(int i, double d, double d2) {
        double d3 = 1.0d / d2;
        return d * 2.0d * d3 * sinc(i * 6.283185307179586d * d * d3);
    }

    private void usage() {
        System.err.printf("http://shibatch.sourceforge.net/\n\n", new Object[0]);
        System.err.printf("usage: ssrc [<options>] <source wav file> <destination wav file>\n", new Object[0]);
        System.err.printf("options : --rate <sampling rate>     output sample rate\n", new Object[0]);
        System.err.printf("          --att <attenuation(dB)>    attenuate signal\n", new Object[0]);
        System.err.printf("          --bits <number of bits>    output quantization bit length\n", new Object[0]);
        System.err.printf("          --tmpfile <file name>      specify temporal file\n", new Object[0]);
        System.err.printf("          --twopass                  two pass processing to avoid clipping\n", new Object[0]);
        System.err.printf("          --normalize                normalize the wave file\n", new Object[0]);
        System.err.printf("          --quiet                    nothing displayed except error\n", new Object[0]);
        System.err.printf("          --dither [<type>]          dithering\n", new Object[0]);
        System.err.printf("                                       0 : no dither\n", new Object[0]);
        System.err.printf("                                       1 : no noise shaping\n", new Object[0]);
        System.err.printf("                                       2 : triangular spectral shape\n", new Object[0]);
        System.err.printf("                                       3 : ATH based noise shaping\n", new Object[0]);
        System.err.printf("                                       4 : less dither amplitude than type 3\n", new Object[0]);
        System.err.printf("          --pdf <type> [<amp>]       select p.d.f. of noise\n", new Object[0]);
        System.err.printf("                                       0 : rectangular\n", new Object[0]);
        System.err.printf("                                       1 : triangular\n", new Object[0]);
        System.err.printf("                                       2 : Gaussian\n", new Object[0]);
        System.err.printf("          --profile <type>           specify profile\n", new Object[0]);
        System.err.printf("                                       standard : the default quality\n", new Object[0]);
        System.err.printf("                                       fast     : fast, not so bad quality\n", new Object[0]);
    }

    private void fmterr(int i) {
        throw new IllegalStateException("unknown error " + i);
    }

    private void setstarttime() {
        this.starttime = System.currentTimeMillis();
        this.lastshowed = 0L;
        this.lastshowed2 = -1;
    }

    private void showprogress(double d) {
        if (this.quiet) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - this.starttime;
        int i = d == 0.0d ? 0 : (int) ((currentTimeMillis * (1.0d - d)) / d);
        int i2 = (int) (d * 100.0d);
        if (i2 != this.lastshowed2 || currentTimeMillis != this.lastshowed) {
            System.err.printf(" %3d%% processed", Integer.valueOf(i2));
            this.lastshowed2 = i2;
        }
        if (currentTimeMillis != this.lastshowed) {
            System.err.printf(", ETA =%4dmsec", Integer.valueOf(i));
            this.lastshowed = currentTimeMillis;
        }
        System.err.printf("\r", new Object[0]);
        System.err.flush();
    }

    private int gcd(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        while (i4 != 0) {
            int i5 = i3 % i4;
            i3 = i4;
            i4 = i5;
        }
        return i3;
    }

    public double no_src(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3, double d, int i4, boolean z, int i5) throws IOException {
        double[] dArr;
        ByteBuffer byteBuffer;
        double d2;
        ByteBuffer byteBuffer2;
        int i6;
        int i7;
        int i8;
        ByteBuffer byteBuffer3;
        ByteBuffer byteBuffer4;
        int i9;
        int RINT;
        int i10 = 1;
        double[] dArr2 = {0.0d};
        setstarttime();
        ByteBuffer allocate = z ? ByteBuffer.allocate(8) : null;
        int i11 = 4;
        ByteBuffer allocate2 = ByteBuffer.allocate(4);
        int i12 = 0;
        int i13 = 0;
        while (true) {
            int i14 = i4 * i;
            if (i12 < i14) {
                switch (i2) {
                    case 1:
                        byteBuffer = allocate;
                        dArr = dArr2;
                        allocate2.position(0);
                        allocate2.limit(1);
                        byte[] bArr = new byte[allocate2.limit()];
                        inputStream.read(bArr);
                        ByteBuffer wrap = ByteBuffer.wrap(bArr);
                        wrap.position(wrap.limit());
                        wrap.flip();
                        d2 = 0.007874015748031496d * (wrap.get(0) - 128);
                        byteBuffer2 = wrap;
                        break;
                    case 2:
                        byteBuffer = allocate;
                        dArr = dArr2;
                        allocate2.position(0);
                        allocate2.limit(2);
                        byte[] bArr2 = new byte[allocate2.limit()];
                        inputStream.read(bArr2);
                        ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
                        wrap2.position(wrap2.limit());
                        wrap2.flip();
                        d2 = 3.051850947599719E-5d * wrap2.order(this.byteOrder).asShortBuffer().get(0);
                        byteBuffer2 = wrap2;
                        break;
                    case 3:
                        byteBuffer = allocate;
                        allocate2.position(0);
                        allocate2.limit(3);
                        byte[] bArr3 = new byte[allocate2.limit()];
                        inputStream.read(bArr3);
                        ByteBuffer wrap3 = ByteBuffer.wrap(bArr3);
                        wrap3.position(wrap3.limit());
                        wrap3.flip();
                        dArr = dArr2;
                        d2 = 1.1920930376163766E-7d * (((wrap3.get(i10) & 255) << 8) | ((wrap3.get(0) & 255) << 0) | ((wrap3.get(2) & 255) << 16));
                        byteBuffer2 = wrap3;
                        break;
                    case 4:
                        allocate2.position(0);
                        allocate2.limit(i11);
                        byte[] bArr4 = new byte[allocate2.limit()];
                        inputStream.read(bArr4);
                        ByteBuffer wrap4 = ByteBuffer.wrap(bArr4);
                        wrap4.position(wrap4.limit());
                        wrap4.flip();
                        byteBuffer = allocate;
                        d2 = wrap4.order(this.byteOrder).asIntBuffer().get(0) * 4.656612875245797E-10d;
                        byteBuffer2 = wrap4;
                        dArr = dArr2;
                        break;
                    default:
                        byteBuffer = allocate;
                        dArr = dArr2;
                        byteBuffer2 = allocate2;
                        d2 = 0.0d;
                        break;
                }
                if (inputStream.available() != 0) {
                    double d3 = d2 * d;
                    if (!z) {
                        switch (i3) {
                            case 1:
                                i6 = i14;
                                i7 = i12;
                                byteBuffer4 = byteBuffer;
                                i8 = 4;
                                double d4 = d3 * 127.0d;
                                int do_shaping = i5 != 0 ? do_shaping(d4, dArr, i5, i13) : RINT(d4);
                                byteBuffer2.position(0);
                                byteBuffer2.limit(1);
                                byteBuffer2.put(0, (byte) (do_shaping + 128));
                                byteBuffer2.flip();
                                writeBuffers(outputStream, byteBuffer2);
                                break;
                            case 2:
                                i6 = i14;
                                i7 = i12;
                                byteBuffer4 = byteBuffer;
                                i8 = 4;
                                double d5 = d3 * 32767.0d;
                                int do_shaping2 = i5 != 0 ? do_shaping(d5, dArr, i5, i13) : RINT(d5);
                                byteBuffer2.position(0);
                                byteBuffer2.limit(2);
                                byteBuffer2.asShortBuffer().put(0, (short) do_shaping2);
                                byteBuffer2.flip();
                                writeBuffers(outputStream, byteBuffer2);
                                break;
                            case 3:
                                double d6 = d3 * 8388607.0d;
                                if (i5 != 0) {
                                    i6 = i14;
                                    i9 = 3;
                                    i7 = i12;
                                    i8 = 4;
                                    byteBuffer4 = byteBuffer;
                                    RINT = do_shaping(d6, dArr, i5, i13);
                                } else {
                                    i6 = i14;
                                    i7 = i12;
                                    byteBuffer4 = byteBuffer;
                                    i9 = 3;
                                    i8 = 4;
                                    RINT = RINT(d6);
                                }
                                byteBuffer2.position(0);
                                byteBuffer2.limit(i9);
                                byteBuffer2.put(0, (byte) (RINT & 255));
                                int i15 = RINT >> 8;
                                byteBuffer2.put(1, (byte) (i15 & 255));
                                byteBuffer2.put(2, (byte) ((i15 >> 8) & 255));
                                byteBuffer2.flip();
                                writeBuffers(outputStream, byteBuffer2);
                                break;
                            default:
                                i6 = i14;
                                i7 = i12;
                                byteBuffer4 = byteBuffer;
                                i8 = 4;
                                break;
                        }
                        byteBuffer3 = byteBuffer4;
                    } else {
                        i6 = i14;
                        i7 = i12;
                        ByteBuffer byteBuffer5 = byteBuffer;
                        i8 = 4;
                        double d7 = d3 > 0.0d ? d3 : -d3;
                        if (dArr[0] >= d7) {
                            d7 = dArr[0];
                        }
                        dArr[0] = d7;
                        byteBuffer3 = byteBuffer5;
                        byteBuffer3.position(0);
                        byteBuffer3.putDouble(d3);
                        byteBuffer3.flip();
                        writeBuffers(outputStream, byteBuffer3);
                    }
                    int i16 = i13 + 1;
                    i13 = i16 == i ? 0 : i16;
                    int i17 = i7 + 1;
                    if ((262143 & i17) == 0) {
                        showprogress(i17 / i6);
                    }
                    allocate = byteBuffer3;
                    i12 = i17;
                    allocate2 = byteBuffer2;
                    dArr2 = dArr;
                    i11 = i8;
                    i10 = 1;
                }
            } else {
                dArr = dArr2;
            }
        }
        showprogress(1.0d);
        return dArr[0];
    }

    public static void main(String[] strArr) throws Exception {
        new SSRC(strArr);
    }

    public SSRC() {
        this.byteOrder = ByteOrder.LITTLE_ENDIAN;
        this.fft = new SplitRadixFft();
        this.f12190AA = 170.0d;
        this.f12191DF = 100.0d;
        this.FFTFIRLEN = 65536;
        this.quiet = false;
    }

    public SSRC(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3, int i4, int i5, int i6, double d, int i7, boolean z) throws IOException {
        int i8;
        int i9;
        double[] dArr;
        int i10;
        int i11;
        double d2;
        int i12;
        int i13;
        SSRC ssrc;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        this.byteOrder = ByteOrder.LITTLE_ENDIAN;
        this.fft = new SplitRadixFft();
        this.f12190AA = 170.0d;
        this.f12191DF = 100.0d;
        this.FFTFIRLEN = 65536;
        this.quiet = false;
        double[] dArr2 = {0.0d};
        if (i7 < 0 || i7 > 4) {
            throw new IllegalArgumentException("unrecognized dither type : " + i7);
        }
        this.quiet = z;
        if (!this.quiet) {
            System.err.printf("Shibatch sampling rate converter version 1.30(high precision/nio)\n\n", new Object[0]);
        }
        if (i3 != 1 && i3 != 2 && i3 != 3 && i3 != 4) {
            throw new IllegalStateException("Error : Only 8bit, 16bit, 24bit and 32bit PCM are supported.");
        }
        if (i4 == -1) {
            int i21 = i3 != 1 ? i3 : 2;
            if (i21 == 4) {
                i9 = i2;
                i8 = 3;
            } else {
                i8 = i21;
                i9 = i2;
            }
        } else {
            i8 = i4;
            i9 = i2;
        }
        int i22 = i9 == -1 ? i : i9;
        int i23 = i7 == -1 ? i8 < i3 ? i8 == 1 ? 4 : 3 : 1 : i7;
        if (!this.quiet) {
            String[] strArr = {"none", "no noise shaping", "triangular spectral shape", "ATH based noise shaping", "ATH based noise shaping(less amplitude)"};
            String[] strArr2 = {"rectangular", "triangular", "gaussian"};
            System.err.printf("frequency : %d -> %d\n", Integer.valueOf(i), Integer.valueOf(i22));
            System.err.printf("attenuation : %gdB\n", Double.valueOf(d));
            System.err.printf("bits per sample : %d -> %d\n", Integer.valueOf(i3 * 8), Integer.valueOf(i8 * 8));
            System.err.printf("nchannels : %d\n", Integer.valueOf(i5));
            System.err.printf("length : %d bytes, %g secs\n", Integer.valueOf(i6), Double.valueOf(((i6 / i3) / i5) / i));
            if (i23 == 0) {
                System.err.printf("dither type : none\n", new Object[0]);
            } else {
                System.err.printf("dither type : %s, %s p.d.f, amp = %g\n", strArr[i23], strArr2[0], Double.valueOf(0.18d));
            }
            System.err.printf("\n", new Object[0]);
        }
        if (i23 != 0) {
            if (i8 == 1) {
                i14 = -128;
                i15 = 127;
                i16 = 2;
            } else {
                i14 = 0;
                i15 = 0;
                i16 = 2;
            }
            if (i8 == i16) {
                i14 = -32768;
                i15 = 32767;
                i17 = 3;
            } else {
                i17 = 3;
            }
            if (i8 == i17) {
                i14 = -8388608;
                i15 = 8388607;
                i18 = 4;
            } else {
                i18 = 4;
            }
            if (i8 == i18) {
                i19 = Integer.MIN_VALUE;
                i20 = Integer.MAX_VALUE;
            } else {
                i19 = i14;
                i20 = i15;
            }
            dArr = dArr2;
            i10 = i8;
            i11 = i22;
            d2 = d;
            init_shaper(i22, i5, i19, i20, i23, 0, 0.18d);
        } else {
            dArr = dArr2;
            i10 = i8;
            i11 = i22;
            d2 = d;
        }
        if (i < i11) {
            i13 = 0;
            dArr[0] = upsample(inputStream, outputStream, i5, i3, i10, i, i11, Math.pow(10.0d, (-d2) / 20.0d), (i6 / i3) / i5, false, i23);
            i12 = 1;
            ssrc = this;
        } else if (i > i11) {
            int i24 = i11;
            i12 = 1;
            i13 = 0;
            dArr[0] = downsample(inputStream, outputStream, i5, i3, i10, i, i24, Math.pow(10.0d, (-d2) / 20.0d), (i6 / i3) / i5, false, i23);
            ssrc = this;
        } else {
            i12 = 1;
            i13 = 0;
            dArr[0] = no_src(inputStream, outputStream, i5, i3, i10, Math.pow(10.0d, (-d2) / 20.0d), (i6 / i3) / i5, false, i23);
            ssrc = this;
        }
        if (!ssrc.quiet) {
            System.err.printf("\n", new Object[i13]);
        }
        if (i23 != 0) {
            ssrc.quit_shaper(i5);
        }
        if (dArr[i13] <= 1.0d || ssrc.quiet) {
            return;
        }
        PrintStream printStream = System.err;
        Object[] objArr = new Object[i12];
        objArr[i13] = Double.valueOf(Math.log10(dArr[i13]) * 20.0d);
        printStream.printf("clipping detected : %gdB\n", objArr);
    }

    protected byte[] getDataFromByteBuffer(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.limit() - byteBuffer.position()];
        byteBuffer.get(bArr, 0, bArr.length);
        return bArr;
    }

    protected void writeBuffers(OutputStream outputStream, ByteBuffer byteBuffer) {
        try {
            outputStream.write(getDataFromByteBuffer(byteBuffer));
        } catch (IOException unused) {
        }
    }
}
