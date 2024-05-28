package com.sinovatech.unicom.separatemodule.audience;

import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import io.reactivex.functions.Consumer;

/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final /* synthetic */ class $$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI implements Consumer {
    private final /* synthetic */ AudienceActivity f$0;
    private final /* synthetic */ ZhuboDataEntity.AnchorInfoBean f$1;

    public /* synthetic */ $$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI(AudienceActivity audienceActivity, ZhuboDataEntity.AnchorInfoBean anchorInfoBean) {
        this.f$0 = audienceActivity;
        this.f$1 = anchorInfoBean;
    }

    @Override // io.reactivex.functions.Consumer
    public final void accept(Object obj) {
        r0.managerAudienceLoadData.getLivePvInfo(this.f$1.getUserId()).subscribe(
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0006: INVOKE  
              (wrap: com.uber.autodispose.ObservableSubscribeProxy<com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity> : 0x0006: INVOKE  
              (wrap: com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData : 0x0000: IGET  (r0v0 com.sinovatech.unicom.separatemodule.audience.AudienceActivity) com.sinovatech.unicom.separatemodule.audience.AudienceActivity.managerAudienceLoadData com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData)
              (wrap: java.lang.String : 0x0002: INVOKE  
              (wrap: com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity$AnchorInfoBean : 0x0002: IGET  (r1v0 com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity$AnchorInfoBean A[REMOVE]) = 
              (r2v0 'this' com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI A[IMMUTABLE_TYPE, THIS])
             com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI.f$1 com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity$AnchorInfoBean)
             type: VIRTUAL call: com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity.AnchorInfoBean.getUserId():java.lang.String)
             type: VIRTUAL call: com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData.getLivePvInfo(java.lang.String):com.uber.autodispose.ObservableSubscribeProxy)
              (wrap: io.reactivex.functions.Consumer<? super com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity> : 0x000c: CONSTRUCTOR  
              (wrap: com.sinovatech.unicom.separatemodule.audience.AudienceActivity : 0x0000: IGET  (r0v0 com.sinovatech.unicom.separatemodule.audience.AudienceActivity A[REMOVE]) = 
              (r2v0 'this' com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI A[IMMUTABLE_TYPE, THIS])
             com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI.f$0 com.sinovatech.unicom.separatemodule.audience.AudienceActivity)
              (wrap: com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity : 0x0004: CHECK_CAST (r3v1 com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity A[REMOVE]) = (com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity) (r3v0 'obj' java.lang.Object))
             call: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$G1mwjYixh0CZbKT1e5qxlvQw_PM.<init>(com.sinovatech.unicom.separatemodule.audience.AudienceActivity, com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity):void type: CONSTRUCTOR)
             type: INTERFACE call: com.uber.autodispose.ObservableSubscribeProxy.subscribe(io.reactivex.functions.Consumer):io.reactivex.disposables.Disposable in method: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI.accept(java.lang.Object):void, file: E:\11480076_dexfile_execute.dex.fixout.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:296)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:275)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:377)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
            	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
            	at java.util.ArrayList.forEach(ArrayList.java:1259)
            	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:390)
            	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$G1mwjYixh0CZbKT1e5qxlvQw_PM, state: NOT_LOADED
            	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:302)
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:769)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1097)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.sinovatech.unicom.separatemodule.audience.AudienceActivity r0 = r2.f$0
            com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity$AnchorInfoBean r1 = r2.f$1
            com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity r3 = (com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity) r3
            com.sinovatech.unicom.separatemodule.audience.AudienceActivity.lambda$startPlay$42(r0, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.$$Lambda$AudienceActivity$st0D0kBRXkgYF7k6eLSbtEqzGfI.accept(java.lang.Object):void");
    }
}
