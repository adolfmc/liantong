package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.PushMessageHandler;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11201bn;
import com.xiaomi.push.C11305dt;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11416gr;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11420gv;
import com.xiaomi.push.C11421gw;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11434hi;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11448hu;
import com.xiaomi.push.C11476p;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.EnumC11418gt;
import com.xiaomi.push.InterfaceC11442hq;
import com.xiaomi.push.service.C11534ag;
import com.xiaomi.push.service.C11568au;
import com.xiaomi.push.service.C11635x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.xiaomi.mipush.sdk.t */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11116t {

    /* renamed from: a */
    private static C11116t f21413a;

    /* renamed from: a */
    private static Object f21414a = new Object();

    /* renamed from: a */
    private static Queue<String> f21415a;

    /* renamed from: a */
    private Context f21416a;

    /*  JADX ERROR: Failed to decode insn: 0x04DC: UNKNOWN(0xBDE6), method: com.xiaomi.mipush.sdk.t.a(com.xiaomi.push.hb, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04DC: UNKNOWN(0xBDE6)'
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
    /*  JADX ERROR: Failed to decode insn: 0x051C: UNKNOWN(0xBDF0), method: com.xiaomi.mipush.sdk.t.a(com.xiaomi.push.hb, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x051C: UNKNOWN(0xBDF0)'
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
    /*  JADX ERROR: Failed to decode insn: 0x054D: FILLED_NEW_ARRAY_RANGE , method: com.xiaomi.mipush.sdk.t.a(com.xiaomi.push.hb, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
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
    /*  JADX ERROR: Failed to decode insn: 0x0552: CONST_METHOD_HANDLE r189, method: com.xiaomi.mipush.sdk.t.a(com.xiaomi.push.hb, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0552: CONST_METHOD_HANDLE r189'
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
    /* renamed from: a */
    private com.xiaomi.mipush.sdk.PushMessageHandler.InterfaceC11085a m5022a(com.xiaomi.push.C11427hb r26, boolean r27, byte[] r28, java.lang.String r29, int r30, android.content.Intent r31) {
        /*
            Method dump skipped, instructions count: 2890
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.C11116t.m5022a(com.xiaomi.push.hb, boolean, byte[], java.lang.String, int, android.content.Intent):com.xiaomi.mipush.sdk.PushMessageHandler$a");
    }

    /* renamed from: a */
    public static C11116t m5030a(Context context) {
        if (f21413a == null) {
            f21413a = new C11116t(context);
        }
        return f21413a;
    }

    private C11116t(Context context) {
        this.f21416a = context.getApplicationContext();
        if (this.f21416a == null) {
            this.f21416a = context;
        }
    }

    /* renamed from: a */
    public PushMessageHandler.InterfaceC11085a m5026a(Intent intent) {
        String action = intent.getAction();
        AbstractC11049b.m5282a("receive an intent from server, action=" + action);
        String stringExtra = intent.getStringExtra("mrt");
        if (stringExtra == null) {
            stringExtra = Long.toString(System.currentTimeMillis());
        }
        String stringExtra2 = intent.getStringExtra("messageId");
        int intExtra = intent.getIntExtra("eventMessageType", -1);
        if ("com.xiaomi.mipush.RECEIVE_MESSAGE".equals(action)) {
            byte[] byteArrayExtra = intent.getByteArrayExtra("mipush_payload");
            boolean booleanExtra = intent.getBooleanExtra("mipush_notified", false);
            if (byteArrayExtra == null) {
                AbstractC11049b.m5268d("receiving an empty message, drop");
                C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, "12");
                return null;
            }
            C11427hb c11427hb = new C11427hb();
            try {
                C11441hp.m3084a(c11427hb, byteArrayExtra);
                C11087b m5151a = C11087b.m5151a(this.f21416a);
                C11417gs m3388a = c11427hb.m3388a();
                if (c11427hb.m3389a() == EnumC11404gf.SendMessage && m3388a != null && !m5151a.m5132e() && !booleanExtra) {
                    m3388a.m3551a("mrt", stringExtra);
                    m3388a.m3551a("mat", Long.toString(System.currentTimeMillis()));
                    if (!m5023a(c11427hb)) {
                        m5015b(c11427hb);
                    } else {
                        AbstractC11049b.m5274b("this is a mina's message, ack later");
                        m3388a.m3551a("__hybrid_message_ts", String.valueOf(m3388a.m3561a()));
                        m3388a.m3551a("__hybrid_device_status", String.valueOf((int) C11441hp.m3089a(this.f21416a, c11427hb)));
                    }
                }
                if (c11427hb.m3389a() == EnumC11404gf.SendMessage && !c11427hb.m3373b()) {
                    if (C11635x.m2311a(c11427hb)) {
                        Object[] objArr = new Object[2];
                        objArr[0] = c11427hb.m3374b();
                        objArr[1] = m3388a != null ? m3388a.m3559a() : "";
                        AbstractC11049b.m5282a(String.format("drop an un-encrypted wake-up messages. %1$s, %2$s", objArr));
                        C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, String.format("13: %1$s", c11427hb.m3374b()));
                    } else {
                        Object[] objArr2 = new Object[2];
                        objArr2[0] = c11427hb.m3374b();
                        objArr2[1] = m3388a != null ? m3388a.m3559a() : "";
                        AbstractC11049b.m5282a(String.format("drop an un-encrypted messages. %1$s, %2$s", objArr2));
                        C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, String.format("14: %1$s", c11427hb.m3374b()));
                    }
                    C11102j.m5066a(this.f21416a, c11427hb, booleanExtra);
                    return null;
                }
                if (c11427hb.m3389a() == EnumC11404gf.SendMessage && c11427hb.m3373b() && C11635x.m2311a(c11427hb) && (!booleanExtra || m3388a == null || m3388a.m3558a() == null || !m3388a.m3558a().containsKey("notify_effect"))) {
                    Object[] objArr3 = new Object[2];
                    objArr3[0] = c11427hb.m3374b();
                    objArr3[1] = m3388a != null ? m3388a.m3559a() : "";
                    AbstractC11049b.m5282a(String.format("drop a wake-up messages which not has 'notify_effect' attr. %1$s, %2$s", objArr3));
                    C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, String.format("25: %1$s", c11427hb.m3374b()));
                    C11102j.m5064b(this.f21416a, c11427hb, booleanExtra);
                    return null;
                }
                if (!m5151a.m5136c() && c11427hb.f22974a != EnumC11404gf.Registration) {
                    if (C11635x.m2311a(c11427hb)) {
                        return m5022a(c11427hb, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                    }
                    C11102j.m5061e(this.f21416a, c11427hb, booleanExtra);
                    boolean m5134d = m5151a.m5134d();
                    AbstractC11049b.m5268d("receive message without registration. need re-register!registered?" + m5134d);
                    C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, "15");
                    if (m5134d) {
                        m5031a();
                    }
                } else if (m5151a.m5136c() && m5151a.m5130f()) {
                    if (c11427hb.f22974a == EnumC11404gf.UnRegistration) {
                        if (c11427hb.m3373b()) {
                            m5151a.m5155a();
                            MiPushClient.clearExtras(this.f21416a);
                            PushMessageHandler.m5179a();
                        } else {
                            AbstractC11049b.m5268d("receiving an un-encrypt unregistration message");
                        }
                    } else {
                        C11102j.m5061e(this.f21416a, c11427hb, booleanExtra);
                        MiPushClient.unregisterPush(this.f21416a);
                    }
                } else {
                    return m5022a(c11427hb, booleanExtra, byteArrayExtra, stringExtra2, intExtra, intent);
                }
            } catch (C11448hu e) {
                C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, "16");
                AbstractC11049b.m5276a(e);
            } catch (Exception e2) {
                C11305dt.m4117a(this.f21416a).m4113a(this.f21416a.getPackageName(), intent, "17");
                AbstractC11049b.m5276a(e2);
            }
        } else if ("com.xiaomi.mipush.ERROR".equals(action)) {
            MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
            C11427hb c11427hb2 = new C11427hb();
            try {
                byte[] byteArrayExtra2 = intent.getByteArrayExtra("mipush_payload");
                if (byteArrayExtra2 != null) {
                    C11441hp.m3084a(c11427hb2, byteArrayExtra2);
                }
            } catch (C11448hu unused) {
            }
            miPushCommandMessage.setCommand(String.valueOf(c11427hb2.m3389a()));
            miPushCommandMessage.setResultCode(intent.getIntExtra("mipush_error_code", 0));
            miPushCommandMessage.setReason(intent.getStringExtra("mipush_error_msg"));
            AbstractC11049b.m5268d("receive a error message. code = " + intent.getIntExtra("mipush_error_code", 0) + ", msg= " + intent.getStringExtra("mipush_error_msg"));
            return miPushCommandMessage;
        } else if ("com.xiaomi.mipush.MESSAGE_ARRIVED".equals(action)) {
            byte[] byteArrayExtra3 = intent.getByteArrayExtra("mipush_payload");
            if (byteArrayExtra3 == null) {
                AbstractC11049b.m5268d("message arrived: receiving an empty message, drop");
                return null;
            }
            C11427hb c11427hb3 = new C11427hb();
            try {
                C11441hp.m3084a(c11427hb3, byteArrayExtra3);
                C11087b m5151a2 = C11087b.m5151a(this.f21416a);
                if (C11635x.m2311a(c11427hb3)) {
                    AbstractC11049b.m5268d("message arrived: receive ignore reg message, ignore!");
                } else if (!m5151a2.m5136c()) {
                    AbstractC11049b.m5268d("message arrived: receive message without registration. need unregister or re-register!");
                } else if (m5151a2.m5136c() && m5151a2.m5130f()) {
                    AbstractC11049b.m5268d("message arrived: app info is invalidated");
                } else {
                    return m5021a(c11427hb3, byteArrayExtra3);
                }
            } catch (Exception e3) {
                AbstractC11049b.m5268d("fail to deal with arrived message. " + e3);
            }
        }
        return null;
    }

    /* renamed from: a */
    private PushMessageHandler.InterfaceC11085a m5021a(C11427hb c11427hb, byte[] bArr) {
        String str = null;
        try {
            InterfaceC11442hq m5039a = C11113r.m5039a(this.f21416a, c11427hb);
            if (m5039a == null) {
                AbstractC11049b.m5268d("message arrived: receiving an un-recognized message. " + c11427hb.f22974a);
                return null;
            }
            EnumC11404gf m3389a = c11427hb.m3389a();
            AbstractC11049b.m5282a("message arrived: processing an arrived message, action=" + m3389a);
            if (C111171.f21417a[m3389a.ordinal()] != 1) {
                return null;
            }
            if (!c11427hb.m3373b()) {
                AbstractC11049b.m5268d("message arrived: receiving an un-encrypt message(SendMessage).");
                return null;
            }
            C11434hi c11434hi = (C11434hi) m5039a;
            C11416gr m3214a = c11434hi.m3214a();
            if (m3214a == null) {
                AbstractC11049b.m5268d("message arrived: receive an empty message without push content, drop it");
                return null;
            }
            if (c11427hb.f22975a != null && c11427hb.f22975a.m3558a() != null) {
                str = c11427hb.f22975a.f22818a.get("jobkey");
            }
            MiPushMessage generateMessage = PushMessageHelper.generateMessage(c11434hi, c11427hb.m3388a(), false);
            generateMessage.setArrivedMessage(true);
            AbstractC11049b.m5282a("message arrived: receive a message, msgid=" + m3214a.m3590a() + ", jobkey=" + str);
            return generateMessage;
        } catch (C11104l e) {
            AbstractC11049b.m5276a(e);
            AbstractC11049b.m5268d("message arrived: receive a message but decrypt failed. report when click.");
            return null;
        } catch (C11448hu e2) {
            AbstractC11049b.m5276a(e2);
            AbstractC11049b.m5268d("message arrived: receive a message which action string is not valid. is the reg expired?");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.t$1 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static /* synthetic */ class C111171 {

        /* renamed from: a */
        static final /* synthetic */ int[] f21417a = new int[EnumC11404gf.values().length];

        static {
            try {
                f21417a[EnumC11404gf.SendMessage.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f21417a[EnumC11404gf.Registration.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f21417a[EnumC11404gf.UnRegistration.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f21417a[EnumC11404gf.Subscription.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f21417a[EnumC11404gf.UnSubscription.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f21417a[EnumC11404gf.Command.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f21417a[EnumC11404gf.Notification.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* renamed from: a */
    private void m5018a(String str, long j, EnumC11090d enumC11090d) {
        EnumC11125v m5077a = C11097g.m5077a(enumC11090d);
        if (m5077a == null) {
            return;
        }
        if (j == 0) {
            synchronized (C11111p.class) {
                if (C11111p.m5047a(this.f21416a).m5042a(str)) {
                    C11111p.m5047a(this.f21416a).m5040c(str);
                    if ("syncing".equals(C11111p.m5047a(this.f21416a).m5046a(m5077a))) {
                        C11111p.m5047a(this.f21416a).m5045a(m5077a, "synced");
                    }
                }
            }
        } else if ("syncing".equals(C11111p.m5047a(this.f21416a).m5046a(m5077a))) {
            synchronized (C11111p.class) {
                if (C11111p.m5047a(this.f21416a).m5042a(str)) {
                    if (C11111p.m5047a(this.f21416a).m5044a(str) < 10) {
                        C11111p.m5047a(this.f21416a).m5041b(str);
                        C11118u.m5003a(this.f21416a).m4980a(str, m5077a, enumC11090d, "retry");
                    } else {
                        C11111p.m5047a(this.f21416a).m5040c(str);
                    }
                }
            }
        } else {
            C11111p.m5047a(this.f21416a).m5040c(str);
        }
    }

    /* renamed from: a */
    private void m5025a(C11421gw c11421gw) {
        String m3472a = c11421gw.m3472a();
        AbstractC11049b.m5274b("receive ack " + m3472a);
        Map<String, String> m3471a = c11421gw.m3471a();
        if (m3471a != null) {
            String str = m3471a.get("real_source");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            AbstractC11049b.m5274b("receive ack : messageId = " + m3472a + "  realSource = " + str);
            C11201bn.m4703a(this.f21416a).m4695a(m3472a, str, Boolean.valueOf(c11421gw.f22900a == 0));
        }
    }

    /* renamed from: b */
    private void m5016b(C11421gw c11421gw) {
        AbstractC11049b.m5270c("ASSEMBLE_PUSH : " + c11421gw.toString());
        String m3472a = c11421gw.m3472a();
        Map<String, String> m3471a = c11421gw.m3471a();
        if (m3471a != null) {
            String str = m3471a.get("RegInfo");
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (str.contains("brand:" + EnumC11112q.FCM.name())) {
                AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive fcm token sync ack");
                C11094f.m5084b(this.f21416a, EnumC11090d.ASSEMBLE_PUSH_FCM, str);
                m5018a(m3472a, c11421gw.f22900a, EnumC11090d.ASSEMBLE_PUSH_FCM);
                return;
            }
            if (!str.contains("brand:" + EnumC11112q.HUAWEI.name())) {
                if (!str.contains("channel:" + EnumC11112q.HUAWEI.name())) {
                    if (!str.contains("brand:" + EnumC11112q.OPPO.name())) {
                        if (!str.contains("channel:" + EnumC11112q.OPPO.name())) {
                            if (!str.contains("brand:" + EnumC11112q.VIVO.name())) {
                                if (!str.contains("channel:" + EnumC11112q.VIVO.name())) {
                                    return;
                                }
                            }
                            AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive FTOS token sync ack");
                            C11094f.m5084b(this.f21416a, EnumC11090d.ASSEMBLE_PUSH_FTOS, str);
                            m5018a(m3472a, c11421gw.f22900a, EnumC11090d.ASSEMBLE_PUSH_FTOS);
                            return;
                        }
                    }
                    AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive COS token sync ack");
                    C11094f.m5084b(this.f21416a, EnumC11090d.ASSEMBLE_PUSH_COS, str);
                    m5018a(m3472a, c11421gw.f22900a, EnumC11090d.ASSEMBLE_PUSH_COS);
                    return;
                }
            }
            AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive hw token sync ack");
            C11094f.m5084b(this.f21416a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, str);
            m5018a(m3472a, c11421gw.f22900a, EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
        }
    }

    /* renamed from: a */
    public List<String> m5017a(TimeZone timeZone, TimeZone timeZone2, List<String> list) {
        if (timeZone.equals(timeZone2)) {
            return list;
        }
        long rawOffset = ((timeZone.getRawOffset() - timeZone2.getRawOffset()) / 1000) / 60;
        long parseLong = ((((Long.parseLong(list.get(0).split(":")[0]) * 60) + Long.parseLong(list.get(0).split(":")[1])) - rawOffset) + 1440) % 1440;
        long parseLong2 = ((((Long.parseLong(list.get(1).split(":")[0]) * 60) + Long.parseLong(list.get(1).split(":")[1])) - rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong / 60), Long.valueOf(parseLong % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(parseLong2 / 60), Long.valueOf(parseLong2 % 60)));
        return arrayList;
    }

    /* renamed from: a */
    private void m5031a() {
        SharedPreferences sharedPreferences = this.f21416a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (Math.abs(currentTimeMillis - sharedPreferences.getLong("last_reinitialize", 0L)) > 1800000) {
            MiPushClient.reInitialize(this.f21416a, EnumC11418gt.PackageUnregistered);
            sharedPreferences.edit().putLong("last_reinitialize", currentTimeMillis).commit();
        }
    }

    /* renamed from: a */
    private void m5024a(C11427hb c11427hb) {
        AbstractC11049b.m5282a("receive a message but decrypt failed. report now.");
        C11430he c11430he = new C11430he(c11427hb.m3388a().f22816a, false);
        c11430he.m3331c(EnumC11414gp.DecryptMessageFail.f22745a);
        c11430he.m3335b(c11427hb.m3387a());
        c11430he.m3327d(c11427hb.f22981b);
        c11430he.f23010a = new HashMap();
        c11430he.f23010a.put("regid", MiPushClient.getRegId(this.f21416a));
        C11118u.m5003a(this.f21416a).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null);
    }

    /* renamed from: b */
    private void m5015b(C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        if (m3388a != null) {
            m3388a = C11568au.m2630a(m3388a.m3560a());
        }
        C11420gv c11420gv = new C11420gv();
        c11420gv.m3497b(c11427hb.m3387a());
        c11420gv.m3501a(m3388a.m3559a());
        c11420gv.m3504a(m3388a.m3561a());
        if (!TextUtils.isEmpty(m3388a.m3547b())) {
            c11420gv.m3494c(m3388a.m3547b());
        }
        c11420gv.m3500a(C11441hp.m3089a(this.f21416a, c11427hb));
        C11118u.m5003a(this.f21416a).m4986a((C11118u) c11420gv, EnumC11404gf.AckMessage, false, m3388a);
    }

    /* renamed from: a */
    private void m5019a(C11434hi c11434hi, C11427hb c11427hb) {
        C11417gs m3388a = c11427hb.m3388a();
        if (m3388a != null) {
            m3388a = C11568au.m2630a(m3388a.m3560a());
        }
        C11420gv c11420gv = new C11420gv();
        c11420gv.m3497b(c11434hi.m3207b());
        c11420gv.m3501a(c11434hi.m3213a());
        c11420gv.m3504a(c11434hi.m3214a().m3591a());
        if (!TextUtils.isEmpty(c11434hi.m3205c())) {
            c11420gv.m3494c(c11434hi.m3205c());
        }
        if (!TextUtils.isEmpty(c11434hi.m3203d())) {
            c11420gv.m3491d(c11434hi.m3203d());
        }
        c11420gv.m3500a(C11441hp.m3089a(this.f21416a, c11427hb));
        C11118u.m5003a(this.f21416a).m4988a((C11118u) c11420gv, EnumC11404gf.AckMessage, m3388a);
    }

    /* renamed from: a */
    private void m5020a(C11430he c11430he) {
        C11421gw c11421gw = new C11421gw();
        c11421gw.m3458c(EnumC11414gp.CancelPushMessageACK.f22745a);
        c11421gw.m3464a(c11430he.m3351a());
        c11421gw.m3467a(c11430he.m3352a());
        c11421gw.m3460b(c11430he.m3337b());
        c11421gw.m3454e(c11430he.m3329d());
        c11421gw.m3468a(0L);
        c11421gw.m3456d("success clear push message.");
        C11118u.m5003a(this.f21416a).m4982a(c11421gw, EnumC11404gf.Notification, false, true, null, false, this.f21416a.getPackageName(), C11087b.m5151a(this.f21416a).m5156a(), false);
    }

    /* renamed from: a */
    private static boolean m5028a(Context context, String str) {
        synchronized (f21414a) {
            C11087b.m5151a(context);
            SharedPreferences m5152a = C11087b.m5152a(context);
            if (f21415a == null) {
                String[] split = m5152a.getString("pref_msg_ids", "").split(",");
                f21415a = new LinkedList();
                for (String str2 : split) {
                    f21415a.add(str2);
                }
            }
            if (f21415a.contains(str)) {
                return true;
            }
            f21415a.add(str);
            if (f21415a.size() > 25) {
                f21415a.poll();
            }
            String m4753a = C11184bb.m4753a(f21415a, ",");
            SharedPreferences.Editor edit = m5152a.edit();
            edit.putString("pref_msg_ids", m4753a);
            C11476p.m2938a(edit);
            return false;
        }
    }

    /* renamed from: a */
    public static void m5029a(Context context, String str) {
        synchronized (f21414a) {
            f21415a.remove(str);
            C11087b.m5151a(context);
            SharedPreferences m5152a = C11087b.m5152a(context);
            String m4753a = C11184bb.m4753a(f21415a, ",");
            SharedPreferences.Editor edit = m5152a.edit();
            edit.putString("pref_msg_ids", m4753a);
            C11476p.m2938a(edit);
        }
    }

    /* renamed from: a */
    public static Intent m5027a(Context context, String str, Map<String, String> map, int i) {
        return C11635x.m2299b(context, str, map, i);
    }

    /* renamed from: a */
    private boolean m5023a(C11427hb c11427hb) {
        Map<String, String> m3558a = c11427hb.m3388a() == null ? null : c11427hb.m3388a().m3558a();
        if (m3558a == null) {
            return false;
        }
        String str = m3558a.get("push_server_action");
        return TextUtils.equals(str, "hybrid_message") || TextUtils.equals(str, "platform_message");
    }

    /* renamed from: b */
    private void m5014b(C11430he c11430he) {
        Map<String, String> m3350a = c11430he.m3350a();
        if (m3350a == null) {
            AbstractC11049b.m5282a("detect failed because null");
            return;
        }
        String str = (String) C11534ag.m2727a(m3350a, "pkgList", (Object) null);
        if (TextUtils.isEmpty(str)) {
            AbstractC11049b.m5282a("detect failed because empty");
            return;
        }
        Map<String, String> m3716a = C11395g.m3716a(this.f21416a, str);
        if (m3716a != null) {
            String str2 = m3716a.get("alive");
            String str3 = m3716a.get("notAlive");
            if (!TextUtils.isEmpty(str2)) {
                C11430he c11430he2 = new C11430he();
                c11430he2.m3344a(c11430he.m3351a());
                c11430he2.m3335b(c11430he.m3337b());
                c11430he2.m3327d(c11430he.m3329d());
                c11430he2.m3331c(EnumC11414gp.DetectAppAliveResult.f22745a);
                c11430he2.f23010a = new HashMap();
                c11430he2.f23010a.put("alive", str2);
                if (Boolean.parseBoolean((String) C11534ag.m2727a(m3350a, "reportNotAliveApp", "false")) && !TextUtils.isEmpty(str3)) {
                    c11430he2.f23010a.put("notAlive", str3);
                }
                C11118u.m5003a(this.f21416a).m4986a((C11118u) c11430he2, EnumC11404gf.Notification, false, (C11417gs) null);
                return;
            }
            AbstractC11049b.m5274b("detect failed because no alive process");
            return;
        }
        AbstractC11049b.m5282a("detect failed because get status illegal");
    }
}
