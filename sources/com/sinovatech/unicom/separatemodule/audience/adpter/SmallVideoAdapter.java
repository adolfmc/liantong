package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmallVideoAdapter extends RecyclerView.Adapter<AudienceHolder> {
    private AppCompatActivity activityContext;
    private boolean isFuYiPing;
    private boolean isShowHelpBtn;
    private boolean isShowMoreBtn;
    private List<SmallVideoEntity.Data> list;
    private ItemClickedListener listener;
    int screenH;
    int screenW;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ItemClickedListener {
        void back();

        void backPortraitScreen();

        void clicked(int i, SmallVideoEntity.Data data);

        void dianzan(int i, SmallVideoEntity.Data data);

        void guanzhu(int i, SmallVideoEntity.Data data);

        void onPreview(boolean z, SmallVideoEntity.Data data);

        void pinglun(int i, SmallVideoEntity.Data data);

        void playVideo(int i);

        void setRing(int i, SmallVideoEntity.Data data, String str);

        void shagnpinInfo(int i, SmallVideoEntity.Data data);

        void shangpin(int i, SmallVideoEntity.Data data);

        void showHelp();

        void showMore(SmallVideoEntity.Data data);

        void toWelfareCenter();

        void zhuanfa(int i, SmallVideoEntity.Data data, boolean z);

        void zhuboxiangqing(int i, SmallVideoEntity.Data data);
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x046A: INVOKE_CUSTOM_RANGE r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171, r172, r173, r174, r175, r176, r177, r178, r179, r180, r181, r182
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
    /*  JADX ERROR: Failed to decode insn: 0x0428: UNKNOWN(0xB4E4), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0428: UNKNOWN(0xB4E4)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0434: UNKNOWN(0xB4E9), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0434: UNKNOWN(0xB4E9)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x043B: UNKNOWN(0xF941), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x043B: UNKNOWN(0xF941)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0446: UNKNOWN(0xB4EF), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0446: UNKNOWN(0xB4EF)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0452: UNKNOWN(0xB4F4), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0452: UNKNOWN(0xB4F4)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x045E: UNKNOWN(0xB4F9), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x045E: UNKNOWN(0xB4F9)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x046A: INVOKE_CUSTOM_RANGE r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, r144, r145, r146, r147, r148, r149, r150, r151, r152, r153, r154, r155, r156, r157, r158, r159, r160, r161, r162, r163, r164, r165, r166, r167, r168, r169, r170, r171, r172, r173, r174, r175, r176, r177, r178, r179, r180, r181, r182, method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
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
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
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
    /*  JADX ERROR: Failed to decode insn: 0x0572: UNKNOWN(0xB53F), method: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0572: UNKNOWN(0xB53F)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@android.support.annotation.NonNull com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.AudienceHolder r8, int r9) {
        /*
            Method dump skipped, instructions count: 1423
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.onBindViewHolder(com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$AudienceHolder, int):void");
    }

    public SmallVideoAdapter(List<SmallVideoEntity.Data> list, AppCompatActivity appCompatActivity, ItemClickedListener itemClickedListener) {
        this.list = list;
        this.activityContext = appCompatActivity;
        this.listener = itemClickedListener;
        this.screenW = UIUtils.getScreenWidth((Activity) appCompatActivity);
        this.screenH = UIUtils.getScreenHeight(appCompatActivity, UIUtils.ScreenHeightMode.FullScreen);
    }

    public void setNewData(List<SmallVideoEntity.Data> list) {
        this.list.clear();
        addData(list);
    }

    public void addData(List<SmallVideoEntity.Data> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public List<SmallVideoEntity.Data> getData() {
        return this.list;
    }

    public SmallVideoEntity.Data getItem(int i) {
        if (i < this.list.size()) {
            return this.list.get(i);
        }
        return null;
    }

    public void setFuYiPing(boolean z) {
        this.isFuYiPing = z;
    }

    public boolean isFuYiPing() {
        return this.isFuYiPing;
    }

    public void setShowMoreBtn(boolean z) {
        this.isShowMoreBtn = z;
    }

    public void setShowHelpBtn(boolean z) {
        this.isShowHelpBtn = z;
    }

    public int getShowHelp() {
        return this.isShowHelpBtn ? 0 : 8;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public AudienceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AudienceHolder(LayoutInflater.from(viewGroup.getContext()).inflate(2131493237, viewGroup, false));
    }

    public static /* synthetic */ void lambda$onBindViewHolder$0(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$1(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$2(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$3(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$4(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.shagnpinInfo(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$5(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(i, data, false);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$6(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$7(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$8(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$9(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$10(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$11(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$12(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.guanzhu(i, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$13(SmallVideoAdapter smallVideoAdapter, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$14(SmallVideoAdapter smallVideoAdapter, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$15(SmallVideoAdapter smallVideoAdapter, AudienceHolder audienceHolder, SmallVideoEntity.Data data, View view) {
        audienceHolder.root.setVisibility(8);
        audienceHolder.llPreviewRoot.setVisibility(0);
        audienceHolder.fullScreen.setVisibility(8);
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(true, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$16(SmallVideoAdapter smallVideoAdapter, AudienceHolder audienceHolder, SmallVideoEntity.Data data, View view) {
        audienceHolder.root.setVisibility(8);
        audienceHolder.llPreviewRoot.setVisibility(0);
        audienceHolder.fullScreen.setVisibility(8);
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(true, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$17(SmallVideoAdapter smallVideoAdapter, AudienceHolder audienceHolder, SmallVideoEntity.Data data, View view) {
        audienceHolder.root.setVisibility(0);
        audienceHolder.llPreviewRoot.setVisibility(8);
        audienceHolder.fullScreen.setVisibility(data.isShowFull() ? 0 : 8);
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(false, data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$18(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(i, data, true);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$19(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.setRing(i, data, "我知道了");
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$20(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.setRing(i, data, "预览");
            PvCurrencyLogUtils.pvLogLive(data.getVideoUrl(), 2, data.getUserName(), "设为彩铃", "视频", data.getViewTitle(), "6");
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$21(SmallVideoAdapter smallVideoAdapter, int i, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.setRing(i, data, "预览");
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$22(SmallVideoAdapter smallVideoAdapter, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.back();
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$23(SmallVideoAdapter smallVideoAdapter, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showMore(data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$24(SmallVideoAdapter smallVideoAdapter, SmallVideoEntity.Data data, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showMore(data);
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$25(SmallVideoAdapter smallVideoAdapter, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showHelp();
        }
    }

    public static /* synthetic */ void lambda$onBindViewHolder$26(SmallVideoAdapter smallVideoAdapter, View view) {
        ItemClickedListener itemClickedListener = smallVideoAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showHelp();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.list.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class AudienceHolder extends RecyclerView.ViewHolder {
        LinearLayout adRootView;
        ImageView anchorImg;
        ImageView anchorImg2;
        TextView anchorName;
        TextView anchorName2;
        TextView commentNum;
        FrameLayout contairlayout;
        TextView fullScreen;
        CardView goodsCard;
        TextView goodsDesc;
        ImageView goodsImg;
        TextView goodsOldPrice;
        TextView goodsPrice;
        TextView goodsTitle;
        ImageView guanzhuBtn;
        ImageView ivBack;
        ImageView ivBigV;
        ImageView ivBtnPlay;
        ImageView ivLandscapeBack;
        ImageView ivPreviewBack;
        ImageView ivPreviewShare;
        ImageView ivVideoRing;
        ImageView ivVideoRing2;
        LikeView likeView;
        ImageView liveRoomCover;
        ImageView liveRoomCover2;
        LinearLayout llBtnComment;
        LinearLayout llBtnPreview;
        LinearLayout llBtnPreview2;
        LinearLayout llBtnSetRing;
        LinearLayout llBtnSetRing2;
        LinearLayout llBtnShare;
        LinearLayout llComment;
        LinearLayout llContentLayout;
        LinearLayout llContentLayout2;
        LinearLayout llFreeTips;
        LinearLayout llLandscapeInfo;
        LinearLayout llMore;
        LinearLayout llMore2;
        LinearLayout llPreviewRoot;
        LinearLayout llShu;
        LinearLayout llVideoHelp;
        LinearLayout llVideoHelp2;
        RelativeLayout rlAnchorIcon;
        LinearLayout rl_comfort;
        View roomCoverBg;
        LinearLayout root;
        TextView shareNum;
        TextView tvLandscapeTitle;
        TextView tvPreviewSetRing;
        TextView tvTitleLabel;
        RelativeLayout videoRootView;
        TextView videoTag;
        TextView videoTag2;
        TextView videoTitle;
        TextView videoTitle2;
        TextView watchNum;
        ImageView zanIcon;
        ImageView zanIcon2;
        TextView zanNum;
        TextView zanNum2;

        public AudienceHolder(View view) {
            super(view);
            this.adRootView = (LinearLayout) view.findViewById(2131296985);
            this.videoRootView = (RelativeLayout) view.findViewById(2131298392);
            this.root = (LinearLayout) view.findViewById(2131297726);
            this.contairlayout = (FrameLayout) view.findViewById(2131296395);
            this.liveRoomCover = (ImageView) view.findViewById(2131296393);
            this.liveRoomCover2 = (ImageView) view.findViewById(2131296394);
            this.roomCoverBg = view.findViewById(2131299478);
            this.anchorImg = (ImageView) view.findViewById(2131297332);
            this.anchorImg2 = (ImageView) view.findViewById(2131297333);
            this.rlAnchorIcon = (RelativeLayout) view.findViewById(2131298309);
            this.ivBigV = (ImageView) view.findViewById(2131297518);
            this.anchorName = (TextView) view.findViewById(2131298863);
            this.anchorName2 = (TextView) view.findViewById(2131298864);
            this.tvTitleLabel = (TextView) view.findViewById(2131299112);
            this.guanzhuBtn = (ImageView) view.findViewById(2131297390);
            this.watchNum = (TextView) view.findViewById(2131299147);
            this.videoTitle = (TextView) view.findViewById(2131299144);
            this.videoTitle2 = (TextView) view.findViewById(2131299145);
            this.videoTag = (TextView) view.findViewById(2131299142);
            this.videoTag2 = (TextView) view.findViewById(2131299143);
            this.zanNum = (TextView) view.findViewById(2131299157);
            this.zanNum2 = (TextView) view.findViewById(2131299158);
            this.zanIcon = (ImageView) view.findViewById(2131297529);
            this.zanIcon2 = (ImageView) view.findViewById(2131297530);
            this.llBtnComment = (LinearLayout) view.findViewById(2131297704);
            this.commentNum = (TextView) view.findViewById(2131298917);
            this.llBtnShare = (LinearLayout) view.findViewById(2131297779);
            this.shareNum = (TextView) view.findViewById(2131299073);
            this.goodsCard = (CardView) view.findViewById(2131296461);
            this.goodsImg = (ImageView) view.findViewById(2131296407);
            this.goodsTitle = (TextView) view.findViewById(2131296419);
            this.goodsDesc = (TextView) view.findViewById(2131296406);
            this.goodsPrice = (TextView) view.findViewById(2131296418);
            this.goodsOldPrice = (TextView) view.findViewById(2131296417);
            this.ivBtnPlay = (ImageView) view.findViewById(2131297359);
            this.fullScreen = (TextView) view.findViewById(2131298899);
            this.llLandscapeInfo = (LinearLayout) view.findViewById(2131297733);
            this.ivLandscapeBack = (ImageView) view.findViewById(2131297418);
            this.tvLandscapeTitle = (TextView) view.findViewById(2131299000);
            this.llFreeTips = (LinearLayout) view.findViewById(2131297716);
            this.llComment = (LinearLayout) view.findViewById(2131297795);
            this.llBtnPreview = (LinearLayout) view.findViewById(2131297798);
            this.llBtnPreview2 = (LinearLayout) view.findViewById(2131297799);
            this.llBtnSetRing = (LinearLayout) view.findViewById(2131297777);
            this.llBtnSetRing2 = (LinearLayout) view.findViewById(2131297778);
            this.ivVideoRing = (ImageView) view.findViewById(2131297520);
            this.ivVideoRing2 = (ImageView) view.findViewById(2131297521);
            this.llPreviewRoot = (LinearLayout) view.findViewById(2131297764);
            this.ivPreviewBack = (ImageView) view.findViewById(2131297464);
            this.ivPreviewShare = (ImageView) view.findViewById(2131297465);
            this.tvPreviewSetRing = (TextView) view.findViewById(2131299055);
            this.likeView = (LikeView) view.findViewById(2131297988);
            this.ivBack = (ImageView) view.findViewById(2131297344);
            this.llMore = (LinearLayout) view.findViewById(2131297743);
            this.llMore2 = (LinearLayout) view.findViewById(2131297744);
            this.llVideoHelp = (LinearLayout) view.findViewById(2131297796);
            this.llVideoHelp2 = (LinearLayout) view.findViewById(2131297797);
            this.rl_comfort = (LinearLayout) view.findViewById(2131298331);
            this.llShu = (LinearLayout) view.findViewById(2131297750);
            this.llContentLayout = (LinearLayout) view.findViewById(2131296706);
            this.llContentLayout2 = (LinearLayout) view.findViewById(2131296707);
        }
    }
}
