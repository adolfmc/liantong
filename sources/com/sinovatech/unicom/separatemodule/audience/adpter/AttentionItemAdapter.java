package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseMultiItemQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AttentionItemAdapter extends BaseMultiItemQuickAdapter<AttentionItemEntity, BaseViewHolder> {
    private static final String STATUS_Y = "Y";
    private final AppCompatActivity activityContext;
    private boolean isShowHelpBtn;
    private boolean isShowMoreBtn;
    private final SmallVideoAdapter.ItemClickedListener listener;
    private PositionCallBack mCallBack;
    private int mCurrentPosition;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface PositionCallBack {
        void playVideo(int i, AttentionItemEntity attentionItemEntity);
    }

    /*  JADX ERROR: Failed to decode insn: 0x0544: UNKNOWN(0xA6E7), method: com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0544: UNKNOWN(0xA6E7)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0552: UNKNOWN(0xA6EC), method: com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0552: UNKNOWN(0xA6EC)'
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
    /*  JADX ERROR: Failed to decode insn: 0x056E: UNKNOWN(0xA6F1), method: com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x056E: UNKNOWN(0xA6F1)'
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
    /*  JADX ERROR: Failed to decode insn: 0x057E: UNKNOWN(0xA6F6), method: com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x057E: UNKNOWN(0xA6F6)'
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
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder r44, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity r45) {
        /*
            Method dump skipped, instructions count: 1412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity):void");
    }

    public void setShowMoreBtn(boolean z) {
        this.isShowMoreBtn = z;
    }

    public void setShowHelpBtn(boolean z) {
        this.isShowHelpBtn = z;
    }

    public void setCurrentPosition(int i) {
        this.mCurrentPosition = i;
        PositionCallBack positionCallBack = this.mCallBack;
        if (positionCallBack != null) {
            positionCallBack.playVideo(i, (AttentionItemEntity) getItem(i));
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
    public void setNewData(@Nullable List<AttentionItemEntity> list) {
        this.mCurrentPosition = 0;
        super.setNewData(list);
    }

    public AttentionItemAdapter(AppCompatActivity appCompatActivity, List<AttentionItemEntity> list, SmallVideoAdapter.ItemClickedListener itemClickedListener, PositionCallBack positionCallBack) {
        super(list);
        this.activityContext = appCompatActivity;
        this.listener = itemClickedListener;
        this.mCallBack = positionCallBack;
        addItemType(1, 2131492993);
        addItemType(2, 2131492993);
        addItemType(3, 2131493237);
    }

    private void convertType2(BaseViewHolder baseViewHolder, AttentionItemEntity attentionItemEntity) {
        baseViewHolder.setGone(2131296435, false);
        baseViewHolder.setGone(2131297749, false);
        GlideApp.with((FragmentActivity) this.activityContext).load(attentionItemEntity.getLiveData().getCoverImg()).into((ImageView) baseViewHolder.getView(2131296393));
    }

    public static /* synthetic */ void lambda$convert$0(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$1(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$2(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.shagnpinInfo(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$3(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(i, data, false);
        }
    }

    public static /* synthetic */ void lambda$convert$4(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$5(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$6(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$7(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$8(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.guanzhu(i, data);
        }
    }

    public static /* synthetic */ void lambda$convert$9(AttentionItemAdapter attentionItemAdapter, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    public static /* synthetic */ void lambda$convert$10(AttentionItemAdapter attentionItemAdapter, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    public static /* synthetic */ void lambda$convert$11(AttentionItemAdapter attentionItemAdapter, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, SmallVideoEntity.Data data, View view) {
        linearLayout.setVisibility(8);
        linearLayout2.setVisibility(0);
        textView.setVisibility(8);
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(true, data);
        }
    }

    public static /* synthetic */ void lambda$convert$12(AttentionItemAdapter attentionItemAdapter, LinearLayout linearLayout, LinearLayout linearLayout2, TextView textView, SmallVideoEntity.Data data, View view) {
        linearLayout.setVisibility(0);
        linearLayout2.setVisibility(8);
        textView.setVisibility(data.isShowFull() ? 0 : 8);
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(false, data);
        }
    }

    public static /* synthetic */ void lambda$convert$13(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(i, data, true);
        }
    }

    public static /* synthetic */ void lambda$convert$14(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.setRing(i, data, "我知道了");
        }
    }

    public static /* synthetic */ void lambda$convert$15(AttentionItemAdapter attentionItemAdapter, int i, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.setRing(i, data, "预览");
        }
    }

    public static /* synthetic */ void lambda$convert$16(AttentionItemAdapter attentionItemAdapter, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.back();
        }
    }

    public static /* synthetic */ void lambda$convert$17(AttentionItemAdapter attentionItemAdapter, SmallVideoEntity.Data data, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showMore(data);
        }
    }

    public static /* synthetic */ void lambda$convert$18(AttentionItemAdapter attentionItemAdapter, View view) {
        SmallVideoAdapter.ItemClickedListener itemClickedListener = attentionItemAdapter.listener;
        if (itemClickedListener != null) {
            itemClickedListener.showHelp();
        }
    }
}
