package com.xiaomi.push.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.Process;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.channel.commonutils.logger.C11048a;
import com.xiaomi.push.AbstractC11356fa;
import com.xiaomi.push.AbstractC11375fo;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11144ag;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11175av;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11183ba;
import com.xiaomi.push.C11232cg;
import com.xiaomi.push.C11244cn;
import com.xiaomi.push.C11253ct;
import com.xiaomi.push.C11261cx;
import com.xiaomi.push.C11266db;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11311dz;
import com.xiaomi.push.C11333eo;
import com.xiaomi.push.C11336ep;
import com.xiaomi.push.C11339er;
import com.xiaomi.push.C11350ey;
import com.xiaomi.push.C11358fb;
import com.xiaomi.push.C11363fg;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.C11374fn;
import com.xiaomi.push.C11392fz;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11398ga;
import com.xiaomi.push.C11402gd;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11431hf;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11448hu;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11472m;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11649x;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11473n;
import com.xiaomi.push.InterfaceC11360fd;
import com.xiaomi.push.InterfaceC11362ff;
import com.xiaomi.push.InterfaceC11370fk;
import com.xiaomi.push.RunnableC11190bh;
import com.xiaomi.push.service.C11545am;
import com.xiaomi.push.service.C11563at;
import com.xiaomi.push.service.C11605n;
import com.xiaomi.push.service.C11615q;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class XMPushService extends Service implements InterfaceC11360fd {

    /* renamed from: b */
    private static boolean f23394b;

    /* renamed from: a */
    private ContentObserver f23397a;

    /* renamed from: a */
    private C11350ey f23399a;

    /* renamed from: a */
    private AbstractC11356fa f23400a;

    /* renamed from: a */
    private C11358fb f23401a;

    /* renamed from: a */
    private C11500a f23403a;

    /* renamed from: a */
    private C11505f f23404a;

    /* renamed from: a */
    private C11510k f23405a;

    /* renamed from: a */
    private C11517r f23406a;

    /* renamed from: a */
    private C11519t f23407a;

    /* renamed from: a */
    private C11562as f23409a;

    /* renamed from: a */
    private C11598h f23410a;

    /* renamed from: a */
    private Object f23413a;

    /* renamed from: a */
    private boolean f23416a = false;

    /* renamed from: a */
    private int f23395a = 0;

    /* renamed from: b */
    private int f23417b = 0;

    /* renamed from: a */
    private long f23396a = 0;

    /* renamed from: a */
    protected Class f23412a = XMJobService.class;

    /* renamed from: c */
    private int f23418c = -1;

    /* renamed from: a */
    private C11542ak f23408a = null;

    /* renamed from: a */
    private C11605n f23411a = null;

    /* renamed from: a */
    Messenger f23398a = null;

    /* renamed from: a */
    private Collection<InterfaceC11522aa> f23415a = Collections.synchronizedCollection(new ArrayList());

    /* renamed from: a */
    private ArrayList<InterfaceC11513n> f23414a = new ArrayList<>();

    /* renamed from: a */
    private InterfaceC11362ff f23402a = new InterfaceC11362ff() { // from class: com.xiaomi.push.service.XMPushService.1
        @Override // com.xiaomi.push.InterfaceC11362ff
        /* renamed from: a */
        public void mo2830a(AbstractC11375fo abstractC11375fo) {
            XMPushService xMPushService = XMPushService.this;
            xMPushService.m2885a(new C11512m(abstractC11375fo));
        }

        @Override // com.xiaomi.push.InterfaceC11362ff
        /* renamed from: a */
        public void mo2831a(C11339er c11339er) {
            if (AbstractC11590e.m2559a(c11339er)) {
                C11563at.m2639a().m2636a(c11339er.m3941e(), SystemClock.elapsedRealtime(), XMPushService.this.m2903a());
            }
            XMPushService xMPushService = XMPushService.this;
            xMPushService.m2885a(new C11503d(c11339er));
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.XMPushService$n */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11513n {
        /* renamed from: a */
        void mo2566a();
    }

    /*  JADX ERROR: Dependency scan failed at insn: 0x07D0: INVOKE_CUSTOM r6, r3
        jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x12
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
    /*  JADX ERROR: Failed to decode insn: 0x041F: UNKNOWN(0x4873), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x041F: UNKNOWN(0x4873)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0471: UNKNOWN(0x04EB), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0471: UNKNOWN(0x04EB)'
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
    /*  JADX ERROR: Failed to decode insn: 0x065F: UNKNOWN(0x5DEC), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x065F: UNKNOWN(0x5DEC)'
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
    /*  JADX ERROR: Failed to decode insn: 0x0778: UNKNOWN(0x25E5), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0778: UNKNOWN(0x25E5)'
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
    /*  JADX ERROR: Failed to decode insn: 0x07A8: UNKNOWN(0x25EF), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x07A8: UNKNOWN(0x25EF)'
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
    /*  JADX ERROR: Failed to decode insn: 0x07D0: INVOKE_CUSTOM r6, r3, method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0x12
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
        	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:443)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x12
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
        	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
        	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
        	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
        	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
        	... 11 more
        */
    /*  JADX ERROR: Failed to decode insn: 0x0815: UNKNOWN(0x5CE6), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0815: UNKNOWN(0x5CE6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x08C0: FILLED_NEW_ARRAY , method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
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
    /*  JADX ERROR: Failed to decode insn: 0x08C6: FILLED_NEW_ARRAY , method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:477)
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
    /*  JADX ERROR: Failed to decode insn: 0x08C9: UNKNOWN(0x1BE3), method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x08C9: UNKNOWN(0x1BE3)'
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
    /*  JADX ERROR: Failed to decode insn: 0x08CC: CONST_STRING r91, method: com.xiaomi.push.service.XMPushService.d(android.content.Intent):void
        jadx.plugins.input.dex.DexException: Bad byte
        	at jadx.plugins.input.dex.utils.MUtf8.decode(MUtf8.java:36)
        	at jadx.plugins.input.dex.sections.SectionReader.getString(SectionReader.java:178)
        	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsString(DexInsnData.java:121)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:84)
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
    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m2844d(android.content.Intent r13) {
        /*
            Method dump skipped, instructions count: 2301
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.XMPushService.m2844d(android.content.Intent):void");
    }

    /* renamed from: h */
    private void m2837h() {
    }

    /* renamed from: a */
    public int m2903a() {
        if (this.f23418c < 0) {
            this.f23418c = C11395g.m3720a((Context) this, "com.xiaomi.xmsf");
        }
        return this.f23418c;
    }

    @Override // android.app.Service
    public void onCreate() {
        String[] split;
        super.onCreate();
        AbstractC11049b.m5287a(getApplicationContext());
        C11479r.m2931a((Context) this);
        C11614p m2430a = C11615q.m2430a((Context) this);
        if (m2430a != null) {
            C11649x.m2263a(m2430a.f23729a);
        }
        if (C11469j.m2972a(getApplicationContext())) {
            HandlerThread handlerThread = new HandlerThread("hb-alarm");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            this.f23403a = new C11500a();
            C11472m.m2946a(this, this.f23403a, new IntentFilter(AbstractC11555an.f23591q), "com.xiaomi.xmsf.permission.MIPUSH_RECEIVE", handler, 4);
            f23394b = true;
            handler.post(new Runnable() { // from class: com.xiaomi.push.service.XMPushService.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PackageManager packageManager = XMPushService.this.getApplicationContext().getPackageManager();
                        ComponentName componentName = new ComponentName(XMPushService.this.getApplicationContext(), "com.xiaomi.push.service.receivers.PingReceiver");
                        if (packageManager.getComponentEnabledSetting(componentName) != 2) {
                            packageManager.setComponentEnabledSetting(componentName, 2, 1);
                        }
                    } catch (Throwable th) {
                        AbstractC11049b.m5282a("[Alarm] disable ping receiver may be failure. " + th);
                    }
                }
            });
        }
        this.f23398a = new Messenger(new Handler() { // from class: com.xiaomi.push.service.XMPushService.8
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message != null) {
                    try {
                        switch (message.what) {
                            case 17:
                                if (message.obj != null) {
                                    XMPushService.this.onStart((Intent) message.obj, 1);
                                    break;
                                }
                                break;
                            case 18:
                                Message obtain = Message.obtain((Handler) null, 0);
                                obtain.what = 18;
                                Bundle bundle = new Bundle();
                                bundle.putString("xmsf_region", C11578b.m2591a(XMPushService.this.getApplicationContext()).m2592a());
                                obtain.setData(bundle);
                                message.replyTo.send(obtain);
                                break;
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        });
        C11556ao.m2659a(this);
        this.f23401a = new C11358fb(null, 5222, "xiaomi.com", null) { // from class: com.xiaomi.push.service.XMPushService.9
            @Override // com.xiaomi.push.C11358fb
            /* renamed from: a */
            public byte[] mo2828a() {
                try {
                    C11289dp.C11291b c11291b = new C11289dp.C11291b();
                    c11291b.m4274a(C11571ax.m2625a().m2627a());
                    return c11291b.mo4063a();
                } catch (Exception e) {
                    AbstractC11049b.m5282a("getOBBString err: " + e.toString());
                    return null;
                }
            }
        };
        this.f23401a.m3866a(true);
        this.f23399a = new C11350ey(this, this.f23401a);
        this.f23410a = m2901a();
        C11311dz.m4066a(this);
        this.f23399a.m3891a(this);
        this.f23408a = new C11542ak(this);
        this.f23409a = new C11562as(this);
        new C11599i().m2528a();
        C11333eo.m3990a().m3983a(this);
        this.f23411a = new C11605n("Connection Controller Thread");
        C11545am m2692a = C11545am.m2692a();
        m2692a.m2679b();
        m2692a.m2687a(new C11545am.InterfaceC11546a() { // from class: com.xiaomi.push.service.XMPushService.10
            @Override // com.xiaomi.push.service.C11545am.InterfaceC11546a
            /* renamed from: a */
            public void mo2678a() {
                XMPushService.this.m2843e();
                if (C11545am.m2692a().m2693a() <= 0) {
                    XMPushService xMPushService = XMPushService.this;
                    xMPushService.m2885a(new C11506g(12, null));
                }
            }
        });
        if (m2832k()) {
            m2837h();
        }
        C11402gd.m3687a(this).m3686a(new C11612o(this), "UPLOADER_PUSH_CHANNEL");
        m2883a(new C11398ga(this));
        m2883a(new C11585bd(this));
        if (C11469j.m2972a((Context) this)) {
            m2883a(new C11543al());
            if (C11455i.m3054a()) {
                m2883a(new InterfaceC11513n() { // from class: com.xiaomi.push.service.XMPushService.11
                    @Override // com.xiaomi.push.service.XMPushService.InterfaceC11513n
                    /* renamed from: a */
                    public void mo2566a() {
                        RunnableC11190bh.m4731a(XMPushService.this.getApplicationContext());
                    }
                });
            }
        }
        m2885a(new C11507h());
        this.f23415a.add(C11574ay.m2605a(this));
        if (m2836h()) {
            this.f23404a = new C11505f();
            C11472m.m2947a(this, this.f23404a, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"), (String) null, (Handler) null);
            this.f23413a = C11169au.m4851a((Context) this);
        }
        if (C11469j.m2972a(getApplicationContext())) {
            this.f23407a = new C11519t();
            C11472m.m2946a(this, this.f23407a, new IntentFilter("miui.net.wifi.DIGEST_INFORMATION_CHANGED"), "miui.net.wifi.permission.ACCESS_WIFI_DIGEST_INFO", null, 2);
            this.f23405a = new C11510k();
            C11472m.m2946a(this, this.f23405a, new IntentFilter("com.xiaomi.xmsf.USE_INTELLIGENT_HB"), "com.xiaomi.xmsf.permission.INTELLIGENT_HB", null, 2);
        }
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            Uri uriFor = Settings.System.getUriFor("power_supersave_mode_open");
            if (uriFor != null) {
                this.f23397a = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.xiaomi.push.service.XMPushService.12
                    @Override // android.database.ContentObserver
                    public void onChange(boolean z) {
                        super.onChange(z);
                        boolean m2838g = XMPushService.this.m2838g();
                        AbstractC11049b.m5282a("SuperPowerMode:" + m2838g);
                        XMPushService.this.m2843e();
                        if (m2838g) {
                            XMPushService xMPushService = XMPushService.this;
                            xMPushService.m2885a(new C11506g(24, null));
                            return;
                        }
                        XMPushService.this.m2865a(true);
                    }
                };
                try {
                    getContentResolver().registerContentObserver(uriFor, false, this.f23397a);
                } catch (Throwable th) {
                    AbstractC11049b.m5268d("register super-power-mode observer err:" + th.getMessage());
                }
            }
            int[] m2897a = m2897a();
            if (m2897a != null) {
                this.f23406a = new C11517r();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                C11472m.m2947a(this, this.f23406a, intentFilter, (String) null, (Handler) null);
                this.f23395a = m2897a[0];
                this.f23417b = m2897a[1];
                AbstractC11049b.m5282a("falldown initialized: " + this.f23395a + "," + this.f23417b);
            }
        }
        C11244cn.m4519a(this, this.f23399a);
        C11253ct.m4460a(this, this.f23399a);
        String str = "";
        if (m2430a != null) {
            try {
                if (!TextUtils.isEmpty(m2430a.f23730a) && (split = m2430a.f23730a.split("@")) != null && split.length > 0) {
                    str = split[0];
                }
            } catch (Exception unused) {
            }
        }
        C11261cx.m4403a(this);
        AbstractC11049b.m5266e("XMPushService created. pid=" + Process.myPid() + ", uid=" + Process.myUid() + ", vc=" + C11395g.m3720a(getApplicationContext(), getPackageName()) + ", uuid=" + str);
    }

    /* renamed from: a */
    private int[] m2897a() {
        String[] split;
        String m2717a = C11537ah.m2715a(getApplicationContext()).m2717a(EnumC11409gk.FallDownTimeRange.m3637a(), "");
        if (!TextUtils.isEmpty(m2717a) && (split = m2717a.split(",")) != null && split.length >= 2) {
            int[] iArr = new int[2];
            try {
                iArr[0] = Integer.valueOf(split[0]).intValue();
                iArr[1] = Integer.valueOf(split[1]).intValue();
                if (iArr[0] >= 0 && iArr[0] <= 23 && iArr[1] >= 0 && iArr[1] <= 23) {
                    if (iArr[0] != iArr[1]) {
                        return iArr;
                    }
                }
            } catch (NumberFormatException e) {
                AbstractC11049b.m5268d("parse falldown time range failure: " + e);
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private String m2900a() {
        String m2968a = C11469j.m2968a("ro.miui.region");
        return TextUtils.isEmpty(m2968a) ? C11469j.m2968a("ro.product.locale.region") : m2968a;
    }

    /* renamed from: b */
    private String m2861b() {
        String str;
        C11144ag.m4910a();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Object obj = new Object();
        int i = 0;
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            C11559ap m2653a = C11559ap.m2653a(this);
            String str2 = null;
            while (true) {
                if (!TextUtils.isEmpty(str2) && m2653a.m2656a() != 0) {
                    break;
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = m2900a();
                }
                try {
                    synchronized (obj) {
                        if (i < 30) {
                            obj.wait(1000L);
                        } else {
                            obj.wait(30000L);
                        }
                    }
                } catch (InterruptedException unused) {
                }
                i++;
            }
            str = m2900a();
        } else {
            str = "CN";
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
        AbstractC11049b.m5282a("wait coutrycode :" + str + " cost = " + elapsedRealtime2 + " , count = " + i);
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m2852c() {
        C11232cg.m4574a().m4551d();
        C11603m.m2511a(getApplicationContext()).m2514a();
        C11578b m2591a = C11578b.m2591a(getApplicationContext());
        String m2592a = m2591a.m2592a();
        String str = "";
        AbstractC11049b.m5280a("XMPushService", "region of cache is " + m2592a);
        if (TextUtils.isEmpty(m2592a)) {
            str = m2861b();
            m2592a = C11469j.m2969a(str).name();
        }
        if (!TextUtils.isEmpty(m2592a) && EnumC11473n.China.name().equals(m2592a)) {
            m2591a.m2588a(m2592a, true);
            str = "CN";
            m2591a.m2586b("CN", true);
        } else if (!TextUtils.isEmpty(m2592a)) {
            if ("com.xiaomi.xmsf".equals(getPackageName())) {
                m2592a = "";
                str = "";
            } else {
                m2592a = EnumC11473n.China.name();
                str = "CN";
            }
            m2591a.m2588a(m2592a, true);
            m2591a.m2586b(str, true);
        } else {
            m2592a = EnumC11473n.China.name();
        }
        AbstractC11049b.m5277a("XMPushService", "after check, appRegion is ", m2592a, ", countryCode=", str);
        if (EnumC11473n.China.name().equals(m2592a)) {
            C11358fb.m3868a("cn.app.chat.xiaomi.net");
        }
        m2871a(m2592a);
        if (m2836h()) {
            AbstractC11049b.m5280a("XMPushService", "-->postOnCreate(): try trigger connect now");
            final AbstractC11509j abstractC11509j = new AbstractC11509j(11) { // from class: com.xiaomi.push.service.XMPushService.13
                @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                /* renamed from: a */
                public String mo2375a() {
                    return "prepare the mi push account.";
                }

                @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
                /* renamed from: a */
                public void mo2374a() {
                    C11632w.m2358a(XMPushService.this);
                    if (C11169au.m4849a((Context) XMPushService.this)) {
                        XMPushService.this.m2865a(true);
                    }
                }
            };
            m2885a(abstractC11509j);
            C11615q.m2422a(new C11615q.InterfaceC11616a() { // from class: com.xiaomi.push.service.XMPushService.14
                @Override // com.xiaomi.push.service.C11615q.InterfaceC11616a
                /* renamed from: a */
                public void mo2421a() {
                    XMPushService.this.m2885a(abstractC11509j);
                }
            });
        }
        try {
            if (C11479r.m2932a()) {
                this.f23410a.m2538a(this);
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
        if ("com.xiaomi.xmsf".equals(getPackageName())) {
            C11395g.m3721a((Context) this, getApplicationInfo(), true);
        }
    }

    /* renamed from: a */
    private static void m2871a(String str) {
        if (EnumC11473n.China.name().equals(str)) {
            C11232cg.m4562a("cn.app.chat.xiaomi.net", "cn.app.chat.xiaomi.net");
            C11232cg.m4562a("cn.app.chat.xiaomi.net", "111.13.141.211:443");
            C11232cg.m4562a("cn.app.chat.xiaomi.net", "39.156.81.172:443");
            C11232cg.m4562a("cn.app.chat.xiaomi.net", "111.202.1.250:443");
            C11232cg.m4562a("cn.app.chat.xiaomi.net", "123.125.102.213:443");
            C11232cg.m4562a("resolver.msg.xiaomi.net", "111.13.142.153:443");
            C11232cg.m4562a("resolver.msg.xiaomi.net", "111.202.1.252:443");
        }
    }

    /* renamed from: a */
    private void m2891a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString("digest");
        C11603m.m2511a(getApplicationContext()).m2509a(string);
        C11244cn.m4518a(this, string);
    }

    /* renamed from: d */
    private void m2846d() {
        C11175av m4855a = C11169au.m4855a();
        C11603m.m2511a(getApplicationContext()).m2510a(m4855a);
        if (m4855a != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("network changed,");
            sb.append("[type: " + m4855a.m4823a() + "[" + m4855a.m4819b() + "], state: " + m4855a.m4824a() + "/" + m4855a.m4825a());
            AbstractC11049b.m5280a("XMPushService", sb.toString());
            NetworkInfo.State m4824a = m4855a.m4824a();
            if (m4824a == NetworkInfo.State.SUSPENDED || m4824a == NetworkInfo.State.UNKNOWN) {
                return;
            }
        } else {
            AbstractC11049b.m5280a("XMPushService", "network changed, no active network");
        }
        if (C11333eo.m3991a() != null) {
            C11333eo.m3991a().m3996a();
        }
        C11392fz.m3735a((Context) this);
        this.f23399a.m3874d();
        if (C11169au.m4849a((Context) this)) {
            if (m2851c() && m2840f()) {
                m2853b(false);
            }
            if (!m2851c() && !m2845d()) {
                this.f23411a.m2472a(1);
                m2885a(new C11504e());
            }
            C11266db.m4384a(this).m4386a();
        } else {
            m2885a(new C11506g(2, null));
        }
        m2843e();
    }

    /* renamed from: f */
    private boolean m2840f() {
        if (SystemClock.elapsedRealtime() - this.f23396a < 30000) {
            return false;
        }
        return C11169au.m4834c(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2899a() {
        if (SystemClock.elapsedRealtime() - this.f23396a >= C11363fg.m3860a() && C11169au.m4834c(this)) {
            m2853b(true);
        }
    }

    /* renamed from: b */
    private void m2853b(boolean z) {
        this.f23396a = SystemClock.elapsedRealtime();
        if (!m2851c()) {
            m2865a(true);
        } else if (C11169au.m4849a((Context) this)) {
            m2849c(new C11514o(z));
        } else {
            m2849c(new C11506g(17, null));
            m2865a(true);
        }
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (intent == null) {
            AbstractC11049b.m5268d("onStart() with intent NULL");
        } else {
            try {
                String stringExtra = intent.getStringExtra(AbstractC11555an.f23596v);
                String stringExtra2 = intent.getStringExtra(AbstractC11555an.f23562F);
                String stringExtra3 = intent.getStringExtra("mipush_app_package");
                if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && !"miui.net.wifi.DIGEST_INFORMATION_CHANGED".equals(intent.getAction())) {
                    AbstractC11049b.m5280a("XMPushService", String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s", intent.getAction(), stringExtra, stringExtra2, stringExtra3));
                }
                AbstractC11049b.m5280a("XMPushService", String.format("onStart() with intent.Action = %s, chid = %s, pkg = %s|%s, intent = %s", intent.getAction(), stringExtra, stringExtra2, stringExtra3, C11469j.m2971a(intent)));
            } catch (Throwable th) {
                AbstractC11049b.m5268d("onStart() cause error: " + th.getMessage());
                return;
            }
        }
        if (intent != null && intent.getAction() != null) {
            if ("com.xiaomi.push.timer".equalsIgnoreCase(intent.getAction()) || "com.xiaomi.push.check_alive".equalsIgnoreCase(intent.getAction())) {
                if (this.f23411a.m2473a()) {
                    AbstractC11049b.m5268d("ERROR, the job controller is blocked.");
                    C11545am.m2692a().m2688a(this, 14);
                    stopSelf();
                } else {
                    m2885a(new C11508i(intent));
                }
            } else if (!"com.xiaomi.push.network_status_changed".equalsIgnoreCase(intent.getAction())) {
                m2885a(new C11508i(intent));
            }
        }
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (currentTimeMillis2 > 50) {
            AbstractC11049b.m5270c("[Prefs] spend " + currentTimeMillis2 + " ms, too more times.");
        }
    }

    /* renamed from: b */
    private void m2858b(Intent intent) {
        long j;
        String stringExtra = intent.getStringExtra(AbstractC11555an.f23562F);
        String stringExtra2 = intent.getStringExtra(AbstractC11555an.f23566J);
        Bundle bundleExtra = intent.getBundleExtra("ext_packet");
        C11545am m2692a = C11545am.m2692a();
        C11339er c11339er = null;
        if (bundleExtra != null) {
            C11374fn c11374fn = (C11374fn) m2886a(new C11374fn(bundleExtra), stringExtra, stringExtra2);
            if (c11374fn == null) {
                return;
            }
            c11339er = C11339er.m3959a(c11374fn, m2692a.m2681a(c11374fn.m3789k(), c11374fn.m3785m()).f23540h);
        } else {
            byte[] byteArrayExtra = intent.getByteArrayExtra("ext_raw_packet");
            if (byteArrayExtra != null) {
                try {
                    j = Long.parseLong(intent.getStringExtra(AbstractC11555an.f23593s));
                } catch (NumberFormatException unused) {
                    j = 0;
                }
                String stringExtra3 = intent.getStringExtra(AbstractC11555an.f23594t);
                String stringExtra4 = intent.getStringExtra(AbstractC11555an.f23595u);
                String stringExtra5 = intent.getStringExtra("ext_chid");
                C11545am.C11547b m2681a = m2692a.m2681a(stringExtra5, String.valueOf(j));
                if (m2681a != null) {
                    C11339er c11339er2 = new C11339er();
                    try {
                        c11339er2.m3962a(Integer.parseInt(stringExtra5));
                    } catch (NumberFormatException unused2) {
                    }
                    c11339er2.m3956a("SECMSG", (String) null);
                    if (TextUtils.isEmpty(stringExtra3)) {
                        stringExtra3 = "xiaomi.com";
                    }
                    c11339er2.m3960a(j, stringExtra3, stringExtra4);
                    c11339er2.m3958a(intent.getStringExtra("ext_pkt_id"));
                    c11339er2.m3953a(byteArrayExtra, m2681a.f23540h);
                    AbstractC11049b.m5282a("send a message: chid=" + stringExtra5 + ", packetId=" + intent.getStringExtra("ext_pkt_id"));
                    c11339er = c11339er2;
                }
            }
        }
        if (c11339er != null) {
            m2849c(new C11570aw(this, c11339er));
        }
    }

    /* renamed from: c */
    private void m2850c(Intent intent) {
        String stringExtra = intent.getStringExtra(AbstractC11555an.f23562F);
        String stringExtra2 = intent.getStringExtra(AbstractC11555an.f23566J);
        Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra("ext_packets");
        C11374fn[] c11374fnArr = new C11374fn[parcelableArrayExtra.length];
        intent.getBooleanExtra("ext_encrypt", true);
        for (int i = 0; i < parcelableArrayExtra.length; i++) {
            c11374fnArr[i] = new C11374fn((Bundle) parcelableArrayExtra[i]);
            c11374fnArr[i] = (C11374fn) m2886a(c11374fnArr[i], stringExtra, stringExtra2);
            if (c11374fnArr[i] == null) {
                return;
            }
        }
        C11545am m2692a = C11545am.m2692a();
        C11339er[] c11339erArr = new C11339er[c11374fnArr.length];
        for (int i2 = 0; i2 < c11374fnArr.length; i2++) {
            C11374fn c11374fn = c11374fnArr[i2];
            c11339erArr[i2] = C11339er.m3959a(c11374fn, m2692a.m2681a(c11374fn.m3789k(), c11374fn.m3785m()).f23540h);
        }
        m2849c(new C11587c(this, c11339erArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2866a(String str, byte[] bArr, boolean z) {
        Collection<C11545am.C11547b> m2684a = C11545am.m2692a().m2684a("5");
        if (m2684a.isEmpty()) {
            if (z) {
                C11620t.m2402b(str, bArr);
            }
        } else if (m2684a.iterator().next().f23527a == C11545am.EnumC11554c.binded) {
            m2885a(new C114933(4, str, bArr));
        } else if (z) {
            C11620t.m2402b(str, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$3 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C114933 extends AbstractC11509j {

        /* renamed from: a */
        final /* synthetic */ String f23431a;

        /* renamed from: a */
        final /* synthetic */ byte[] f23432a;

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "send mi push message";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C114933(int i, String str, byte[] bArr) {
            super(i);
            this.f23431a = str;
            this.f23432a = bArr;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            try {
                C11632w.m2354a(XMPushService.this, this.f23431a, this.f23432a);
            } catch (C11368fi e) {
                AbstractC11049b.m5276a(e);
                XMPushService.this.m2894a(10, e);
            }
        }
    }

    /* renamed from: a */
    public void m2864a(byte[] bArr, String str) {
        if (bArr == null) {
            C11620t.m2406a(this, str, bArr, 70000003, "null payload");
            AbstractC11049b.m5282a("register request without payload");
            return;
        }
        C11427hb c11427hb = new C11427hb();
        try {
            C11441hp.m3084a(c11427hb, bArr);
            if (c11427hb.f22974a == EnumC11404gf.Registration) {
                C11431hf c11431hf = new C11431hf();
                try {
                    C11441hp.m3084a(c11431hf, c11427hb.m3384a());
                    m2885a(new C11619s(this, c11427hb.m3374b(), c11431hf.m3303b(), c11431hf.m3298c(), bArr));
                    C11305dt.m4117a(getApplicationContext()).m4111a(c11427hb.m3374b(), "E100003", c11431hf.m3312a(), 6002, null);
                } catch (C11448hu e) {
                    AbstractC11049b.m5268d("app register error. " + e);
                    C11620t.m2406a(this, str, bArr, 70000003, " data action error.");
                }
            } else {
                C11620t.m2406a(this, str, bArr, 70000003, " registration action required.");
                AbstractC11049b.m5282a("register request with invalid payload");
            }
        } catch (C11448hu e2) {
            AbstractC11049b.m5268d("app register fail. " + e2);
            C11620t.m2406a(this, str, bArr, 70000003, " data container error.");
        }
    }

    /* renamed from: a */
    private AbstractC11375fo m2886a(AbstractC11375fo abstractC11375fo, String str, String str2) {
        C11545am m2692a = C11545am.m2692a();
        List<String> m2683a = m2692a.m2683a(str);
        if (m2683a.isEmpty()) {
            AbstractC11049b.m5282a("open channel should be called first before sending a packet, pkg=" + str);
            return null;
        }
        abstractC11375fo.m3780o(str);
        String m3789k = abstractC11375fo.m3789k();
        if (TextUtils.isEmpty(m3789k)) {
            m3789k = m2683a.get(0);
            abstractC11375fo.m3786l(m3789k);
        }
        C11545am.C11547b m2681a = m2692a.m2681a(m3789k, abstractC11375fo.m3785m());
        if (!m2851c()) {
            AbstractC11049b.m5282a("drop a packet as the channel is not connected, chid=" + m3789k);
            return null;
        } else if (m2681a == null || m2681a.f23527a != C11545am.EnumC11554c.binded) {
            AbstractC11049b.m5282a("drop a packet as the channel is not opened, chid=" + m3789k);
            return null;
        } else if (TextUtils.equals(str2, m2681a.f23541i)) {
            return abstractC11375fo;
        } else {
            AbstractC11049b.m5282a("invalid session. " + str2);
            return null;
        }
    }

    /* renamed from: a */
    private boolean m2868a(String str, Intent intent) {
        C11545am.C11547b m2681a = C11545am.m2692a().m2681a(str, intent.getStringExtra(AbstractC11555an.f23593s));
        boolean z = false;
        if (m2681a == null || str == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(AbstractC11555an.f23566J);
        String stringExtra2 = intent.getStringExtra(AbstractC11555an.f23558B);
        if (!TextUtils.isEmpty(m2681a.f23541i) && !TextUtils.equals(stringExtra, m2681a.f23541i)) {
            AbstractC11049b.m5282a("session changed. old session=" + m2681a.f23541i + ", new session=" + stringExtra + " chid = " + str);
            z = true;
        }
        if (stringExtra2.equals(m2681a.f23540h)) {
            return z;
        }
        AbstractC11049b.m5282a("security changed. chid = " + str + " sechash = " + C11183ba.m4761a(stringExtra2));
        return true;
    }

    /* renamed from: a */
    private C11545am.C11547b m2869a(String str, Intent intent) {
        C11545am.C11547b m2681a = C11545am.m2692a().m2681a(str, intent.getStringExtra(AbstractC11555an.f23593s));
        if (m2681a == null) {
            m2681a = new C11545am.C11547b(this);
        }
        m2681a.f23539g = intent.getStringExtra(AbstractC11555an.f23596v);
        m2681a.f23533b = intent.getStringExtra(AbstractC11555an.f23593s);
        m2681a.f23535c = intent.getStringExtra(AbstractC11555an.f23600z);
        m2681a.f23529a = intent.getStringExtra(AbstractC11555an.f23562F);
        m2681a.f23537e = intent.getStringExtra(AbstractC11555an.f23560D);
        m2681a.f23538f = intent.getStringExtra(AbstractC11555an.f23561E);
        m2681a.f23531a = intent.getBooleanExtra(AbstractC11555an.f23559C, false);
        m2681a.f23540h = intent.getStringExtra(AbstractC11555an.f23558B);
        m2681a.f23541i = intent.getStringExtra(AbstractC11555an.f23566J);
        m2681a.f23536d = intent.getStringExtra(AbstractC11555an.f23557A);
        m2681a.f23528a = this.f23410a;
        m2681a.m2672a((Messenger) intent.getParcelableExtra(AbstractC11555an.f23570N));
        m2681a.f23521a = getApplicationContext();
        C11545am.m2692a().m2686a(m2681a);
        return m2681a;
    }

    /* renamed from: a */
    public void m2867a(String str, String str2, int i, String str3, String str4) {
        C11545am.C11547b m2681a = C11545am.m2692a().m2681a(str, str2);
        if (m2681a != null) {
            m2885a(new C11518s(m2681a, i, str4, str3));
        }
        C11545am.m2692a().m2680a(str, str2);
    }

    /* renamed from: a */
    private void m2870a(String str, int i) {
        Collection<C11545am.C11547b> m2684a = C11545am.m2692a().m2684a(str);
        if (m2684a != null) {
            for (C11545am.C11547b c11547b : m2684a) {
                if (c11547b != null) {
                    m2885a(new C11518s(c11547b, i, null, null));
                }
            }
        }
        C11545am.m2692a().m2682a(str);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        onStart(intent, i2);
        return C11469j.m2972a((Context) this) ? 1 : 2;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f23398a.getBinder();
    }

    /* renamed from: c */
    private void m2849c(AbstractC11509j abstractC11509j) {
        this.f23411a.m2469a(abstractC11509j);
    }

    /* renamed from: a */
    public void m2885a(AbstractC11509j abstractC11509j) {
        m2884a(abstractC11509j, 0L);
    }

    /* renamed from: a */
    public void m2884a(AbstractC11509j abstractC11509j, long j) {
        try {
            this.f23411a.m2468a(abstractC11509j, j);
        } catch (IllegalStateException e) {
            AbstractC11049b.m5282a("can't execute job err = " + e.getMessage());
        }
    }

    /* renamed from: a */
    private void m2893a(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver != null) {
            try {
                unregisterReceiver(broadcastReceiver);
            } catch (IllegalArgumentException e) {
                AbstractC11049b.m5276a(e);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        C11505f c11505f = this.f23404a;
        if (c11505f != null) {
            m2893a(c11505f);
            this.f23404a = null;
        }
        Object obj = this.f23413a;
        if (obj != null) {
            C11169au.m4848a(this, obj);
            this.f23413a = null;
        }
        C11519t c11519t = this.f23407a;
        if (c11519t != null) {
            m2893a(c11519t);
            this.f23407a = null;
        }
        C11510k c11510k = this.f23405a;
        if (c11510k != null) {
            m2893a(c11510k);
            this.f23405a = null;
        }
        C11517r c11517r = this.f23406a;
        if (c11517r != null) {
            m2893a(c11517r);
            this.f23406a = null;
        }
        C11500a c11500a = this.f23403a;
        if (c11500a != null) {
            m2893a(c11500a);
            this.f23403a = null;
        }
        if ("com.xiaomi.xmsf".equals(getPackageName()) && this.f23397a != null) {
            try {
                getContentResolver().unregisterContentObserver(this.f23397a);
            } catch (Throwable th) {
                AbstractC11049b.m5268d("unregister super-power-mode err:" + th.getMessage());
            }
        }
        this.f23415a.clear();
        this.f23411a.m2466b();
        m2885a(new AbstractC11509j(2) { // from class: com.xiaomi.push.service.XMPushService.4
            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public String mo2375a() {
                return "disconnect for service destroy.";
            }

            @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
            /* renamed from: a */
            public void mo2374a() {
                if (XMPushService.this.f23400a != null) {
                    XMPushService.this.f23400a.mo3842b(15, (Exception) null);
                    XMPushService.this.f23400a = null;
                }
            }
        });
        m2885a(new C11511l());
        C11545am.m2692a().m2679b();
        C11545am.m2692a().m2688a(this, 15);
        C11545am.m2692a().m2690a();
        this.f23399a.m3879b(this);
        C11571ax.m2625a().m2623a();
        C11311dz.m4068a();
        m2835i();
        C11244cn.m4515b(this, this.f23399a);
        C11253ct.m4456b(this, this.f23399a);
        super.onDestroy();
        AbstractC11049b.m5282a("Service destroyed");
    }

    /* renamed from: a */
    public void m2890a(C11339er c11339er) {
        AbstractC11356fa abstractC11356fa = this.f23400a;
        if (abstractC11356fa != null) {
            abstractC11356fa.mo3880b(c11339er);
            return;
        }
        throw new C11368fi("try send msg while connection is null.");
    }

    /* renamed from: a */
    public void m2863a(C11339er[] c11339erArr) {
        AbstractC11356fa abstractC11356fa = this.f23400a;
        if (abstractC11356fa != null) {
            abstractC11356fa.mo3843a(c11339erArr);
            return;
        }
        throw new C11368fi("try send msg while connection is null.");
    }

    /* renamed from: a */
    public void m2865a(boolean z) {
        this.f23409a.m2640a(z);
    }

    /* renamed from: a */
    public void m2872a(C11545am.C11547b c11547b) {
        if (c11547b != null) {
            long m2677a = c11547b.m2677a();
            AbstractC11049b.m5282a("schedule rebind job in " + (m2677a / 1000));
            m2884a(new C11501b(c11547b), m2677a);
        }
    }

    /* renamed from: a */
    public void m2894a(int i, Exception exc) {
        StringBuilder sb = new StringBuilder();
        sb.append("disconnect ");
        sb.append(hashCode());
        sb.append(", ");
        AbstractC11356fa abstractC11356fa = this.f23400a;
        sb.append(abstractC11356fa == null ? null : Integer.valueOf(abstractC11356fa.hashCode()));
        AbstractC11049b.m5282a(sb.toString());
        AbstractC11356fa abstractC11356fa2 = this.f23400a;
        if (abstractC11356fa2 != null) {
            abstractC11356fa2.mo3842b(i, exc);
            this.f23400a = null;
        }
        m2896a(7);
        m2896a(4);
        C11545am.m2692a().m2688a(this, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public boolean m2838g() {
        return "com.xiaomi.xmsf".equals(getPackageName()) && Settings.System.getInt(getContentResolver(), "power_supersave_mode_open", 0) == 1;
    }

    /* renamed from: a */
    public boolean m2898a() {
        boolean m4849a = C11169au.m4849a((Context) this);
        boolean z = C11545am.m2692a().m2693a() > 0;
        boolean z2 = !m2859b();
        boolean m2836h = m2836h();
        boolean z3 = !m2838g();
        boolean z4 = m4849a && z && z2 && m2836h && z3;
        if (!z4) {
            AbstractC11049b.m5266e(String.format("not conn, net=%s;cnt=%s;!dis=%s;enb=%s;!spm=%s;", Boolean.valueOf(m4849a), Boolean.valueOf(z), Boolean.valueOf(z2), Boolean.valueOf(m2836h), Boolean.valueOf(z3)));
        }
        return z4;
    }

    /* renamed from: h */
    private boolean m2836h() {
        boolean z;
        String packageName = getPackageName();
        if ("com.xiaomi.xmsf".equals(packageName)) {
            AbstractC11049b.m5282a("current sdk expect region is cn");
            z = EnumC11473n.China.name().equals(C11578b.m2591a(getApplicationContext()).m2592a());
        } else {
            z = !C11617r.m2420a(this).m2416b(packageName);
        }
        if (!z) {
            AbstractC11049b.m5277a("XMPushService", "-->isPushEnabled(): isEnabled=", Boolean.valueOf(z), ", package=", packageName, ", region=", C11578b.m2591a(getApplicationContext()).m2592a());
        }
        return z;
    }

    /* renamed from: b */
    public boolean m2859b() {
        try {
            Class<?> m2929a = C11479r.m2929a(this, "miui.os.Build");
            Field field = m2929a.getField("IS_CM_CUSTOMIZATION_TEST");
            Field field2 = m2929a.getField("IS_CU_CUSTOMIZATION_TEST");
            Field field3 = m2929a.getField("IS_CT_CUSTOMIZATION_TEST");
            if (!field.getBoolean(null) && !field2.getBoolean(null)) {
                if (!field3.getBoolean(null)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2843e() {
        if (m2898a()) {
            if (C11311dz.m4067a()) {
                return;
            }
            C11311dz.m4064a(true);
            return;
        }
        C11311dz.m4068a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m2841f() {
        AbstractC11356fa abstractC11356fa = this.f23400a;
        if (abstractC11356fa != null && abstractC11356fa.m3881b()) {
            AbstractC11049b.m5268d("try to connect while connecting.");
            return;
        }
        AbstractC11356fa abstractC11356fa2 = this.f23400a;
        if (abstractC11356fa2 != null && abstractC11356fa2.m3875c()) {
            AbstractC11049b.m5268d("try to connect while is connected.");
            return;
        }
        this.f23401a.m3864b(C11169au.m4850a((Context) this));
        m2839g();
        if (this.f23400a == null) {
            C11545am.m2692a().m2689a(this);
            m2847c(false);
        }
    }

    /* renamed from: c */
    private void m2847c(boolean z) {
        try {
            if (C11479r.m2932a()) {
                if (z) {
                    if (C11469j.m2972a((Context) this)) {
                        Intent intent = new Intent("miui.intent.action.NETWORK_CONNECTED");
                        intent.addFlags(1073741824);
                        sendBroadcast(intent);
                    }
                    for (InterfaceC11522aa interfaceC11522aa : (InterfaceC11522aa[]) this.f23415a.toArray(new InterfaceC11522aa[0])) {
                        interfaceC11522aa.mo2606a();
                    }
                } else if (C11469j.m2972a((Context) this)) {
                    Intent intent2 = new Intent("miui.intent.action.NETWORK_BLOCKED");
                    intent2.addFlags(1073741824);
                    sendBroadcast(intent2);
                }
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.XMPushService$5 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C114955 implements InterfaceC11370fk {
        @Override // com.xiaomi.push.InterfaceC11370fk
        /* renamed from: a */
        public boolean mo2829a(AbstractC11375fo abstractC11375fo) {
            return true;
        }

        C114955() {
        }
    }

    /* renamed from: g */
    private void m2839g() {
        try {
            this.f23399a.m3889a(this.f23402a, new C114955());
            this.f23399a.m3838e();
            this.f23400a = this.f23399a;
        } catch (C11368fi e) {
            AbstractC11049b.m5279a("fail to create Slim connection", e);
            this.f23399a.mo3842b(3, e);
        }
    }

    /* renamed from: a */
    public C11598h m2901a() {
        return new C11598h();
    }

    /* renamed from: b */
    public C11598h m2862b() {
        return this.f23410a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.XMPushService$h */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11507h extends AbstractC11509j {
        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "Init Job";
        }

        C11507h() {
            super(65535);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.m2852c();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$l */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11511l extends AbstractC11509j {
        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "ask the job queue to quit";
        }

        public C11511l() {
            super(5);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.f23411a.m2474a();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$j */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractC11509j extends C11605n.AbstractRunnableC11608b {
        /* renamed from: a */
        public abstract String mo2375a();

        /* renamed from: a */
        public abstract void mo2374a();

        public AbstractC11509j(int i) {
            super(i);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f23707a != 4 && this.f23707a != 8) {
                AbstractC11049b.m5280a(C11048a.f21273a, mo2375a());
            }
            mo2374a();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$m */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11512m extends AbstractC11509j {

        /* renamed from: a */
        private AbstractC11375fo f23456a;

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "receive a message.";
        }

        public C11512m(AbstractC11375fo abstractC11375fo) {
            super(8);
            this.f23456a = null;
            this.f23456a = abstractC11375fo;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.f23408a.m2698a(this.f23456a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$i */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11508i extends AbstractC11509j {

        /* renamed from: a */
        private Intent f23452a;

        public C11508i(Intent intent) {
            super(15);
            this.f23452a = null;
            this.f23452a = intent;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.m2844d(this.f23452a);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "Handle intent action = " + this.f23452a.getAction();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11503d extends AbstractC11509j {

        /* renamed from: a */
        private C11339er f23444a;

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "receive a message.";
        }

        public C11503d(C11339er c11339er) {
            super(8);
            this.f23444a = null;
            this.f23444a = c11339er;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.f23408a.m2700a(this.f23444a);
            if (AbstractC11590e.m2559a(this.f23444a)) {
                XMPushService.this.m2884a(new C11563at.C11565a(), 15000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$b */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11501b extends AbstractC11509j {

        /* renamed from: a */
        C11545am.C11547b f23442a;

        public C11501b(C11545am.C11547b c11547b) {
            super(9);
            this.f23442a = null;
            this.f23442a = c11547b;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            try {
                if (!XMPushService.this.m2851c()) {
                    AbstractC11049b.m5268d("trying bind while the connection is not created, quit!");
                } else {
                    C11545am.C11547b m2681a = C11545am.m2692a().m2681a(this.f23442a.f23539g, this.f23442a.f23533b);
                    if (m2681a == null) {
                        AbstractC11049b.m5282a("ignore bind because the channel " + this.f23442a.f23539g + " is removed ");
                    } else if (m2681a.f23527a == C11545am.EnumC11554c.unbind) {
                        m2681a.m2666a(C11545am.EnumC11554c.binding, 0, 0, (String) null, (String) null);
                        XMPushService.this.f23400a.mo3887a(m2681a);
                        C11336ep.m3975a(XMPushService.this, m2681a);
                    } else {
                        AbstractC11049b.m5282a("trying duplicate bind, ingore! " + m2681a.f23527a);
                    }
                }
            } catch (Exception e) {
                AbstractC11049b.m5268d("Meet error when trying to bind. " + e);
                XMPushService.this.m2894a(10, e);
            } catch (Throwable unused) {
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "bind the client. " + this.f23442a.f23539g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11502c extends AbstractC11509j {

        /* renamed from: a */
        private final C11545am.C11547b f23443a;

        public C11502c(C11545am.C11547b c11547b) {
            super(12);
            this.f23443a = c11547b;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            this.f23443a.m2666a(C11545am.EnumC11554c.unbind, 1, 21, (String) null, (String) null);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "bind time out. chid=" + this.f23443a.f23539g;
        }

        public boolean equals(Object obj) {
            if (obj instanceof C11502c) {
                return TextUtils.equals(((C11502c) obj).f23443a.f23539g, this.f23443a.f23539g);
            }
            return false;
        }

        public int hashCode() {
            return this.f23443a.f23539g.hashCode();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$p */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11515p extends AbstractC11509j {

        /* renamed from: a */
        C11545am.C11547b f23461a;

        public C11515p(C11545am.C11547b c11547b) {
            super(4);
            this.f23461a = null;
            this.f23461a = c11547b;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            try {
                this.f23461a.m2666a(C11545am.EnumC11554c.unbind, 1, 16, (String) null, (String) null);
                XMPushService.this.f23400a.mo3885a(this.f23461a.f23539g, this.f23461a.f23533b);
                XMPushService.this.m2884a(new C11501b(this.f23461a), 300L);
            } catch (C11368fi e) {
                AbstractC11049b.m5276a(e);
                XMPushService.this.m2894a(10, e);
            }
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "rebind the client. " + this.f23461a.f23539g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$s */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11518s extends AbstractC11509j {

        /* renamed from: a */
        C11545am.C11547b f23465a;

        /* renamed from: a */
        String f23466a;

        /* renamed from: b */
        int f23467b;

        /* renamed from: b */
        String f23468b;

        public C11518s(C11545am.C11547b c11547b, int i, String str, String str2) {
            super(9);
            this.f23465a = null;
            this.f23465a = c11547b;
            this.f23467b = i;
            this.f23466a = str;
            this.f23468b = str2;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            if (this.f23465a.f23527a != C11545am.EnumC11554c.unbind && XMPushService.this.f23400a != null) {
                try {
                    XMPushService.this.f23400a.mo3885a(this.f23465a.f23539g, this.f23465a.f23533b);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    XMPushService.this.m2894a(10, e);
                }
            }
            this.f23465a.m2666a(C11545am.EnumC11554c.unbind, this.f23467b, 0, this.f23468b, this.f23466a);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "unbind the channel. " + this.f23465a.f23539g;
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$e */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11504e extends AbstractC11509j {
        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "do reconnect..";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C11504e() {
            super(1);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            if (XMPushService.this.m2898a()) {
                XMPushService xMPushService = XMPushService.this;
                if (xMPushService.m2892a(xMPushService.getApplicationContext())) {
                    XMPushService.this.m2841f();
                    return;
                }
            }
            AbstractC11049b.m5282a("should not connect. quit the job.");
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$g */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11506g extends AbstractC11509j {

        /* renamed from: a */
        public Exception f23449a;

        /* renamed from: b */
        public int f23450b;

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "disconnect the connection.";
        }

        C11506g(int i, Exception exc) {
            super(2);
            this.f23450b = i;
            this.f23449a = exc;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.m2894a(this.f23450b, this.f23449a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$q */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11516q extends AbstractC11509j {
        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "reset the connection.";
        }

        C11516q() {
            super(3);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            XMPushService.this.m2894a(11, (Exception) null);
            if (XMPushService.this.m2898a()) {
                XMPushService xMPushService = XMPushService.this;
                if (xMPushService.m2892a(xMPushService.getApplicationContext())) {
                    XMPushService.this.m2841f();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.XMPushService$o */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C11514o extends AbstractC11509j {

        /* renamed from: a */
        boolean f23459a;

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "send ping..";
        }

        public C11514o(boolean z) {
            super(4);
            this.f23459a = z;
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            if (XMPushService.this.m2851c()) {
                try {
                    if (!this.f23459a) {
                        C11336ep.m3981a();
                    }
                    XMPushService.this.f23400a.mo3841b(this.f23459a);
                } catch (C11368fi e) {
                    AbstractC11049b.m5276a(e);
                    XMPushService.this.m2894a(10, e);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m2892a(Context context) {
        try {
            C11144ag.m4910a();
            for (int i = 100; i > 0; i--) {
                if (C11169au.m4835b(context)) {
                    AbstractC11049b.m5282a("network connectivity ok.");
                    return true;
                }
                try {
                    Thread.sleep(100L);
                } catch (Exception unused) {
                }
            }
            return false;
        } catch (Exception unused2) {
            return true;
        }
    }

    /* renamed from: c */
    public boolean m2851c() {
        AbstractC11356fa abstractC11356fa = this.f23400a;
        return abstractC11356fa != null && abstractC11356fa.m3875c();
    }

    /* renamed from: d */
    public boolean m2845d() {
        AbstractC11356fa abstractC11356fa = this.f23400a;
        return abstractC11356fa != null && abstractC11356fa.m3881b();
    }

    /* renamed from: a */
    public AbstractC11356fa m2902a() {
        return this.f23400a;
    }

    /* renamed from: a */
    public void m2896a(int i) {
        this.f23411a.m2472a(i);
    }

    /* renamed from: a */
    public boolean m2895a(int i) {
        return this.f23411a.m2471a(i);
    }

    /* renamed from: b */
    public void m2856b(AbstractC11509j abstractC11509j) {
        this.f23411a.m2470a(abstractC11509j.f23707a, abstractC11509j);
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2889a(AbstractC11356fa abstractC11356fa) {
        AbstractC11049b.m5270c("begin to connect...");
        C11333eo.m3991a().mo2889a(abstractC11356fa);
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2888a(AbstractC11356fa abstractC11356fa, int i, Exception exc) {
        C11333eo.m3991a().mo2888a(abstractC11356fa, i, exc);
        if (m2834i()) {
            return;
        }
        m2865a(false);
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: b */
    public void mo2857b(AbstractC11356fa abstractC11356fa) {
        C11333eo.m3991a().mo2857b(abstractC11356fa);
        m2847c(true);
        this.f23409a.m2641a();
        if (!C11311dz.m4067a() && !m2834i()) {
            AbstractC11049b.m5282a("reconnection successful, reactivate alarm.");
            C11311dz.m4064a(true);
        }
        Iterator<C11545am.C11547b> it = C11545am.m2692a().m2691a().iterator();
        while (it.hasNext()) {
            m2885a(new C11501b(it.next()));
        }
        if (this.f23416a || !C11469j.m2972a(getApplicationContext())) {
            return;
        }
        C11134ae.m4937a(getApplicationContext()).m4928a(new Runnable() { // from class: com.xiaomi.push.service.XMPushService.6
            @Override // java.lang.Runnable
            public void run() {
                XMPushService.this.f23416a = true;
                try {
                    AbstractC11049b.m5282a("try to trigger the wifi digest broadcast.");
                    Object systemService = XMPushService.this.getApplicationContext().getSystemService("MiuiWifiService");
                    if (systemService != null) {
                        C11176aw.m4804b(systemService, "sendCurrentWifiDigestInfo", new Object[0]);
                    }
                } catch (Throwable unused) {
                }
            }
        });
    }

    @Override // com.xiaomi.push.InterfaceC11360fd
    /* renamed from: a */
    public void mo2887a(AbstractC11356fa abstractC11356fa, Exception exc) {
        C11333eo.m3991a().mo2887a(abstractC11356fa, exc);
        m2847c(false);
        if (m2834i()) {
            return;
        }
        m2865a(false);
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$f */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11505f extends BroadcastReceiver {
        C11505f() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            C11169au.m4854a();
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$t */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11519t extends BroadcastReceiver {
        C11519t() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!XMPushService.this.f23416a) {
                XMPushService.this.f23416a = true;
            }
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$k */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11510k extends BroadcastReceiver {
        C11510k() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            AbstractC11049b.m5282a("[HB] hold short heartbeat, " + C11469j.m2971a(intent));
            if (intent == null || intent.getExtras() == null) {
                return;
            }
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.XMPushService$r */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class C11517r extends BroadcastReceiver {
        C11517r() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            XMPushService.this.onStart(intent, 1);
        }
    }

    /* renamed from: i */
    private boolean m2834i() {
        return getApplicationContext().getPackageName().equals("com.xiaomi.xmsf") && m2833j() && !C11455i.m3040b((Context) this) && !C11455i.m3049a(getApplicationContext());
    }

    /* renamed from: j */
    private boolean m2833j() {
        int intValue = Integer.valueOf(String.format("%tH", new Date())).intValue();
        int i = this.f23395a;
        int i2 = this.f23417b;
        if (i > i2) {
            if (intValue >= i || intValue < i2) {
                return true;
            }
        } else if (i < i2 && intValue >= i && intValue < i2) {
            return true;
        }
        return false;
    }

    /* renamed from: k */
    private boolean m2832k() {
        if (TextUtils.equals(getPackageName(), "com.xiaomi.xmsf")) {
            return false;
        }
        return C11537ah.m2715a(this).m2716a(EnumC11409gk.ForegroundServiceSwitch.m3637a(), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m2860b() {
        C11603m.m2511a(getApplicationContext()).m2496d();
        Iterator it = new ArrayList(this.f23414a).iterator();
        while (it.hasNext()) {
            ((InterfaceC11513n) it.next()).mo2566a();
        }
    }

    /* renamed from: a */
    public void m2883a(InterfaceC11513n interfaceC11513n) {
        synchronized (this.f23414a) {
            this.f23414a.add(interfaceC11513n);
        }
    }

    /* renamed from: i */
    private void m2835i() {
        synchronized (this.f23414a) {
            this.f23414a.clear();
        }
    }

    /* renamed from: com.xiaomi.push.service.XMPushService$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class C11500a extends BroadcastReceiver {

        /* renamed from: a */
        private final Object f23440a;

        private C11500a() {
            this.f23440a = new Object();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            long currentTimeMillis = System.currentTimeMillis();
            AbstractC11049b.m5270c("[Alarm] heartbeat alarm has been triggered.");
            if (AbstractC11555an.f23591q.equals(intent.getAction())) {
                if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
                    AbstractC11049b.m5270c("[Alarm] Ping XMChannelService on timer");
                    try {
                        Intent intent2 = new Intent(context, XMPushService.class);
                        intent2.putExtra("time_stamp", System.currentTimeMillis());
                        intent2.setAction("com.xiaomi.push.timer");
                        ServiceClient.getInstance(context).startServiceSafely(intent2);
                        m2826a(3000L);
                        AbstractC11049b.m5282a("[Alarm] heartbeat alarm finish in " + (System.currentTimeMillis() - currentTimeMillis));
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
                return;
            }
            AbstractC11049b.m5282a("[Alarm] cancel the old ping timer");
            C11311dz.m4068a();
        }

        /* renamed from: a */
        private void m2826a(long j) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                AbstractC11049b.m5268d("[Alarm] Cannot perform lock.wait in the UI thread!");
                return;
            }
            synchronized (this.f23440a) {
                try {
                    this.f23440a.wait(j);
                } catch (InterruptedException e) {
                    AbstractC11049b.m5282a("[Alarm] interrupt from waiting state. " + e);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m2827a() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                AbstractC11049b.m5268d("[Alarm] Cannot perform lock.notifyAll in the UI thread!");
                return;
            }
            synchronized (this.f23440a) {
                try {
                    this.f23440a.notifyAll();
                } catch (Exception e) {
                    AbstractC11049b.m5282a("[Alarm] notify lock. " + e);
                }
            }
        }
    }

    /* renamed from: e */
    public static boolean m2842e() {
        return f23394b;
    }
}
