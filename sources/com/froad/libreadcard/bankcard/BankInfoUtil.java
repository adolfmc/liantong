package com.froad.libreadcard.bankcard;

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
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BankInfoUtil {
    private static BankInfoUtil bankInfoUtil;
    private String bankId;
    private String bankName;
    private String cardType;
    private String reg;

    /* renamed from: s1 */
    public String f10136s1;
    public String s10;
    public String s100;
    public String s101;
    public String s102;
    public String s103;
    public String s104;
    public String s105;
    public String s106;
    public String s107;
    public String s108;
    public String s109;
    public String s11;
    public String s110;
    public String s111;
    public String s112;
    public String s113;
    public String s114;
    public String s115;
    public String s116;
    public String s117;
    public String s118;
    public String s119;
    public String s12;
    public String s120;
    public String s121;
    public String s122;
    public String s123;
    public String s124;
    public String s125;
    public String s126;
    public String s127;
    public String s128;
    public String s129;
    public String s13;
    public String s130;
    public String s131;
    public String s132;
    public String s133;
    public String s134;
    public String s135;
    public String s136;
    public String s137;
    public String s138;
    public String s139;
    public String s14;
    public String s140;
    public String s141;
    public String s142;
    public String s143;
    public String s144;
    public String s145;
    public String s146;
    public String s147;
    public String s148;
    public String s149;
    public String s15;
    public String s150;
    public String s151;
    public String s152;
    public String s153;
    public String s154;
    public String s155;
    public String s156;
    public String s157;
    public String s158;
    public String s159;
    public String s16;
    public String s160;
    public String s161;
    public String s162;
    public String s163;
    public String s164;
    public String s165;
    public String s166;
    public String s167;
    public String s168;
    public String s169;
    public String s17;
    public String s170;
    public String s171;
    public String s172;
    public String s173;
    public String s174;
    public String s175;
    public String s176;
    public String s177;
    public String s178;
    public String s179;
    public String s18;
    public String s180;
    public String s181;
    public String s182;
    public String s183;
    public String s184;
    public String s185;
    public String s186;
    public String s187;
    public String s188;
    public String s189;
    public String s19;
    public String s190;
    public String s191;
    public String s192;
    public String s193;
    public String s194;
    public String s195;
    public String s196;
    public String s197;
    public String s198;
    public String s199;

    /* renamed from: s2 */
    public String f10137s2;
    public String s20;
    public String s200;
    public String s201;
    public String s202;
    public String s203;
    public String s204;
    public String s205;
    public String s206;
    public String s207;
    public String s208;
    public String s209;
    public String s21;
    public String s210;
    public String s211;
    public String s212;
    public String s213;
    public String s214;
    public String s215;
    public String s216;
    public String s217;
    public String s218;
    public String s219;
    public String s22;
    public String s220;
    public String s221;
    public String s222;
    public String s223;
    public String s224;
    public String s225;
    public String s226;
    public String s227;
    public String s228;
    public String s229;
    public String s23;
    public String s230;
    public String s231;
    public String s232;
    public String s233;
    public String s234;
    public String s235;
    public String s236;
    public String s237;
    public String s238;
    public String s239;
    public String s24;
    public String s240;
    public String s241;
    public String s242;
    public String s243;
    public String s244;
    public String s245;
    public String s246;
    public String s247;
    public String s248;
    public String s249;
    public String s25;
    public String s250;
    public String s251;
    public String s252;
    public String s253;
    public String s254;
    public String s255;
    public String s256;
    public String s257;
    public String s258;
    public String s259;
    public String s26;
    public String s260;
    public String s261;
    public String s262;
    public String s263;
    public String s264;
    public String s265;
    public String s266;
    public String s267;
    public String s268;
    public String s269;
    public String s27;
    public String s270;
    public String s271;
    public String s272;
    public String s273;
    public String s274;
    public String s275;
    public String s276;
    public String s277;
    public String s278;
    public String s279;
    public String s28;
    public String s280;
    public String s281;
    public String s282;
    public String s283;
    public String s284;
    public String s285;
    public String s286;
    public String s287;
    public String s288;
    public String s289;
    public String s29;
    public String s290;
    public String s291;
    public String s292;
    public String s293;
    public String s294;
    public String s295;
    public String s296;
    public String s297;
    public String s298;
    public String s299;

    /* renamed from: s3 */
    public String f10138s3;
    public String s30;
    public String s300;
    public String s301;
    public String s302;
    public String s303;
    public String s304;
    public String s305;
    public String s306;
    public String s307;
    public String s308;
    public String s309;
    public String s31;
    public String s310;
    public String s311;
    public String s312;
    public String s313;
    public String s314;
    public String s315;
    public String s32;
    public String s33;
    public String s34;
    public String s35;
    public String s36;
    public String s37;
    public String s38;
    public String s39;

    /* renamed from: s4 */
    public String f10139s4;
    public String s40;
    public String s41;
    public String s42;
    public String s43;
    public String s44;
    public String s45;
    public String s46;
    public String s47;
    public String s48;
    public String s49;

    /* renamed from: s5 */
    public String f10140s5;
    public String s50;
    public String s51;
    public String s52;
    public String s53;
    public String s54;
    public String s55;
    public String s56;
    public String s57;
    public String s58;
    public String s59;

    /* renamed from: s6 */
    public String f10141s6;
    public String s60;
    public String s61;
    public String s62;
    public String s63;
    public String s64;
    public String s65;
    public String s66;
    public String s67;
    public String s68;
    public String s69;

    /* renamed from: s7 */
    public String f10142s7;
    public String s70;
    public String s71;
    public String s72;
    public String s73;
    public String s74;
    public String s75;
    public String s76;
    public String s77;
    public String s79;

    /* renamed from: s8 */
    public String f10143s8;
    public String s80;
    public String s81;
    public String s82;
    public String s83;
    public String s84;
    public String s85;
    public String s86;
    public String s87;
    public String s88;
    public String s89;

    /* renamed from: s9 */
    public String f10144s9;
    public String s90;
    public String s91;
    public String s92;
    public String s93;
    public String s94;
    public String s95;
    public String s96;
    public String s97;
    public String s98;
    public String s99;
    public String ss254;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x040e: MOVE  (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r2 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 2
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private BankInfoUtil() {
        /*
            Method dump skipped, instructions count: 1264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libreadcard.bankcard.BankInfoUtil.<init>():void");
    }

    public static BankInfoUtil getInstance() {
        if (bankInfoUtil == null) {
            bankInfoUtil = new BankInfoUtil();
        }
        return bankInfoUtil;
    }

    /*  JADX ERROR: Failed to decode insn: 0x07FE: UNKNOWN(0x09E8), method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankId(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x07FE: UNKNOWN(0x09E8)'
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
    public java.lang.String getBankId(java.lang.String r2) {
        /*
            Method dump skipped, instructions count: 2727
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libreadcard.bankcard.BankInfoUtil.getBankId(java.lang.String):java.lang.String");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x091F: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x09BB: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
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
    /*  JADX ERROR: Failed to decode insn: 0x03FE: UNKNOWN(0x09E8), method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x03FE: UNKNOWN(0x09E8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x089D: UNKNOWN(0x5EE5), method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x089D: UNKNOWN(0x5EE5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x091F: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    /*  JADX ERROR: Failed to decode insn: 0x0945: UNKNOWN(0x5EE8), method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0945: UNKNOWN(0x5EE8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x09BB: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    public java.lang.String getBankName(java.lang.String r3) {
        /*
            Method dump skipped, instructions count: 3204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libreadcard.bankcard.BankInfoUtil.getBankName(java.lang.String):java.lang.String");
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x051F: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
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
    /*  JADX ERROR: Dependency scan failed at insn: 0x05BB: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
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
    /*  JADX ERROR: Failed to decode insn: 0x049D: UNKNOWN(0x5EE5), method: com.froad.libreadcard.bankcard.BankInfoUtil.getCardType(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x049D: UNKNOWN(0x5EE5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x051F: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.froad.libreadcard.bankcard.BankInfoUtil.getCardType(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    /*  JADX ERROR: Failed to decode insn: 0x0545: UNKNOWN(0x5EE8), method: com.froad.libreadcard.bankcard.BankInfoUtil.getCardType(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0545: UNKNOWN(0x5EE8)'
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
    /*  JADX ERROR: Failed to decode insn: 0x05BB: INVOKE_CUSTOM_RANGE r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, method: com.froad.libreadcard.bankcard.BankInfoUtil.getCardType(java.lang.String):java.lang.String
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    public java.lang.String getCardType(java.lang.String r3) {
        /*
            Method dump skipped, instructions count: 2536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.froad.libreadcard.bankcard.BankInfoUtil.getCardType(java.lang.String):java.lang.String");
    }

    public String getReg() {
        return this.reg;
    }

    public void setBankId(String str) {
        this.bankId = str;
    }

    public void setBankName(String str) {
        this.bankName = str;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public void setReg(String str) {
        this.reg = str;
    }
}
