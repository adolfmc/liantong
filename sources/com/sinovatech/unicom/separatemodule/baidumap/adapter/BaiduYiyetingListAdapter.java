package com.sinovatech.unicom.separatemodule.baidumap.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import java.text.DecimalFormat;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduYiyetingListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BusinessEntity.BusinessBean> mList;
    private OnItemListClickListener mOnItemListClickListener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnItemListClickListener {
        void onItemListClick(String str, int i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x0418: INVOKE_CUSTOM_RANGE r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26
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
    /*  JADX ERROR: Failed to decode insn: 0x03FC: UNKNOWN(0x18F6), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x03FC: UNKNOWN(0x18F6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0401: UNKNOWN(0x5DE7), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0401: UNKNOWN(0x5DE7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0418: INVOKE_CUSTOM_RANGE r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
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
    /*  JADX ERROR: Failed to decode insn: 0x0435: UNKNOWN(0x5DEA), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0435: UNKNOWN(0x5DEA)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0441: UNKNOWN(0x5DEB), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0441: UNKNOWN(0x5DEB)'
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
    /*  JADX ERROR: Failed to decode insn: 0x044D: UNKNOWN(0x5DEC), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x044D: UNKNOWN(0x5DEC)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0465: UNKNOWN(0x5DEE), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0465: UNKNOWN(0x5DEE)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0471: UNKNOWN(0x5DF0), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0471: UNKNOWN(0x5DF0)'
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
    /*  JADX ERROR: Failed to decode insn: 0x047D: UNKNOWN(0x5DF1), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x047D: UNKNOWN(0x5DF1)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0484: FILLED_NEW_ARRAY r3, method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
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
    /*  JADX ERROR: Failed to decode insn: 0x0495: UNKNOWN(0x5DF3), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0495: UNKNOWN(0x5DF3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04AD: UNKNOWN(0x5DF5), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04AD: UNKNOWN(0x5DF5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04B9: UNKNOWN(0x5DF6), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04B9: UNKNOWN(0x5DF6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04C5: UNKNOWN(0x5DEF), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04C5: UNKNOWN(0x5DEF)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04D1: UNKNOWN(0x5DEA), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04D1: UNKNOWN(0x5DEA)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04D8: UNKNOWN(0x1943), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04D8: UNKNOWN(0x1943)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04DD: UNKNOWN(0x5DEB), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04DD: UNKNOWN(0x5DEB)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04E9: UNKNOWN(0x5DEF), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04E9: UNKNOWN(0x5DEF)'
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
    /*  JADX ERROR: Failed to decode insn: 0x04F5: UNKNOWN(0x5DEC), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04F5: UNKNOWN(0x5DEC)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0501: UNKNOWN(0x5DED), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0501: UNKNOWN(0x5DED)'
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
    /*  JADX ERROR: Failed to decode insn: 0x050D: UNKNOWN(0x5DEE), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x050D: UNKNOWN(0x5DEE)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0519: UNKNOWN(0x5DF0), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0519: UNKNOWN(0x5DF0)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0525: UNKNOWN(0x5DF1), method: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0525: UNKNOWN(0x5DF1)'
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
    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(@android.support.annotation.NonNull android.support.p086v7.widget.RecyclerView.ViewHolder r11, int r12) {
        /*
            Method dump skipped, instructions count: 1322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.onBindViewHolder(android.support.v7.widget.RecyclerView$ViewHolder, int):void");
    }

    public BaiduYiyetingListAdapter(Context context, List<BusinessEntity.BusinessBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493024, viewGroup, false));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C86174 implements BaiduBusinessimageAdapter.OnItemClickListener {
        final /* synthetic */ MyHolder val$myHolder;

        C86174(MyHolder myHolder) {
            this.val$myHolder = myHolder;
        }

        @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter.OnItemClickListener
        public void onItemClick() {
            this.val$myHolder.itemView.performClick();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C86185 implements BaiduCoffeeVolumeAdapter.OnItemClickListener {
        final /* synthetic */ MyHolder val$myHolder;

        C86185(MyHolder myHolder) {
            this.val$myHolder = myHolder;
        }

        @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter.OnItemClickListener
        public void onItemClick() {
            this.val$myHolder.itemView.performClick();
        }
    }

    private String conversion(String str) {
        DecimalFormat decimalFormat;
        float f = 0.0f;
        try {
            f = Float.valueOf(str).floatValue() / 1000.0f;
            decimalFormat = new DecimalFormat("0.00");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            decimalFormat = null;
        }
        return decimalFormat.format(f);
    }

    @Override // android.support.p086v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mList.size();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MyHolder extends RecyclerView.ViewHolder {
        private final ImageView live_image;
        private final LinearLayout mAct;
        private final TextView mActhint;
        private final TextView mActtitle;
        private final LinearLayout mAreaLayout;
        private final RecyclerView mBusinessImage;
        private final TextView mBusinessName;
        private final ImageView mCoupons;
        private final TextView mDistance;
        private final View mDivider;
        private final TextView mGrade;
        private final LinearLayout mLive;
        private final TextView mLivetitle;
        private final ImageView mPicture;
        private final RecyclerView mRecycler_volume;
        private final TextView mRegion;
        private final RecyclerView mServiceLabel;
        private final TextView mSite;
        private final ImageView mStar;
        private final TextView mTime;
        private final TextView mTypeName;

        static /* synthetic */ ImageView access$1000(MyHolder myHolder) {
            return myHolder.mStar;
        }

        static /* synthetic */ TextView access$1100(MyHolder myHolder) {
            return myHolder.mGrade;
        }

        static /* synthetic */ ImageView access$1200(MyHolder myHolder) {
            return myHolder.mPicture;
        }

        static /* synthetic */ ImageView access$1300(MyHolder myHolder) {
            return myHolder.mCoupons;
        }

        static /* synthetic */ View access$1400(MyHolder myHolder) {
            return myHolder.mDivider;
        }

        static /* synthetic */ TextView access$1500(MyHolder myHolder) {
            return myHolder.mRegion;
        }

        static /* synthetic */ TextView access$1600(MyHolder myHolder) {
            return myHolder.mTypeName;
        }

        static /* synthetic */ RecyclerView access$1700(MyHolder myHolder) {
            return myHolder.mServiceLabel;
        }

        static /* synthetic */ LinearLayout access$1800(MyHolder myHolder) {
            return myHolder.mAct;
        }

        static /* synthetic */ TextView access$1900(MyHolder myHolder) {
            return myHolder.mActtitle;
        }

        static /* synthetic */ LinearLayout access$200(MyHolder myHolder) {
            return myHolder.mLive;
        }

        static /* synthetic */ ImageView access$300(MyHolder myHolder) {
            return myHolder.live_image;
        }

        static /* synthetic */ TextView access$400(MyHolder myHolder) {
            return myHolder.mLivetitle;
        }

        static /* synthetic */ TextView access$500(MyHolder myHolder) {
            return myHolder.mBusinessName;
        }

        static /* synthetic */ TextView access$600(MyHolder myHolder) {
            return myHolder.mTime;
        }

        static /* synthetic */ TextView access$700(MyHolder myHolder) {
            return myHolder.mSite;
        }

        static /* synthetic */ LinearLayout access$800(MyHolder myHolder) {
            return myHolder.mAreaLayout;
        }

        static /* synthetic */ TextView access$900(MyHolder myHolder) {
            return myHolder.mDistance;
        }

        public MyHolder(View view) {
            super(view);
            this.mBusinessName = (TextView) view.findViewById(2131296556);
            this.mTime = (TextView) view.findViewById(2131298781);
            this.mSite = (TextView) view.findViewById(2131298631);
            this.mDistance = (TextView) view.findViewById(2131296888);
            this.mPicture = (ImageView) view.findViewById(2131297278);
            this.mStar = (ImageView) view.findViewById(2131298678);
            this.mCoupons = (ImageView) view.findViewById(2131296721);
            this.mGrade = (TextView) view.findViewById(2131297094);
            this.mBusinessImage = (RecyclerView) view.findViewById(2131296555);
            this.mAct = (LinearLayout) view.findViewById(2131296288);
            this.mActtitle = (TextView) view.findViewById(2131296319);
            this.mActhint = (TextView) view.findViewById(2131296291);
            this.mLive = (LinearLayout) view.findViewById(2131297633);
            this.mLivetitle = (TextView) view.findViewById(2131297682);
            this.live_image = (ImageView) view.findViewById(2131297639);
            this.mRecycler_volume = (RecyclerView) view.findViewById(2131298272);
            this.mAreaLayout = (LinearLayout) view.findViewById(2131296375);
            this.mRegion = (TextView) view.findViewById(2131298276);
            this.mTypeName = (TextView) view.findViewById(2131299164);
            this.mServiceLabel = (RecyclerView) view.findViewById(2131298518);
            this.mDivider = view.findViewById(2131296889);
        }
    }

    public void setOnItemListClickListener(OnItemListClickListener onItemListClickListener) {
        this.mOnItemListClickListener = onItemListClickListener;
    }
}
