package com.baidu.cloud.mediaprocess.device;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.OutPortFactory;
import com.baidu.cloud.framework.frame.AudioFrameBuffer;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MediaDecoderDevice {
    public static final String TAG = "MediaDecoderDevice";
    public static final boolean VERBOSE = false;

    /* renamed from: j */
    public DecoderThread f4579j;

    /* renamed from: k */
    public volatile String f4580k;

    /* renamed from: l */
    public File f4581l;

    /* renamed from: t */
    public volatile Surface f4589t;

    /* renamed from: u */
    public volatile OnDecodeStateChangeListener f4590u;

    /* renamed from: a */
    public volatile int f4570a = 0;

    /* renamed from: b */
    public volatile boolean f4571b = false;

    /* renamed from: c */
    public volatile boolean f4572c = false;

    /* renamed from: d */
    public volatile boolean f4573d = false;

    /* renamed from: e */
    public boolean f4574e = false;

    /* renamed from: f */
    public MediaExtractor f4575f = null;

    /* renamed from: g */
    public MediaExtractor f4576g = null;

    /* renamed from: h */
    public MediaFormat f4577h = null;

    /* renamed from: i */
    public MediaFormat f4578i = null;

    /* renamed from: m */
    public volatile boolean f4582m = false;

    /* renamed from: n */
    public volatile boolean f4583n = true;

    /* renamed from: o */
    public volatile long f4584o = -1;

    /* renamed from: p */
    public volatile long f4585p = -1;

    /* renamed from: q */
    public volatile int f4586q = 1;

    /* renamed from: r */
    public OutPort<AudioFrameBuffer> f4587r = new OutPortFactory().createOutPort();

    /* renamed from: s */
    public OutPort<VideoFrameBuffer> f4588s = new OutPortFactory().createOutPort();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class DecoderThread extends Thread {

        /* renamed from: a */
        public MediaDecoderDevice f4591a;

        /* renamed from: b */
        public DecoderHandler f4592b;

        /* renamed from: c */
        public final Object f4593c = new Object();

        /* renamed from: d */
        public volatile boolean f4594d = false;

        /* renamed from: e */
        public volatile boolean f4595e = false;

        /* renamed from: f */
        public Thread f4596f = null;

        /* renamed from: g */
        public Thread f4597g = null;

        /* renamed from: h */
        public long f4598h = 0;

        /* renamed from: i */
        public long f4599i = 0;

        /* renamed from: j */
        public long f4600j = 0;

        /* renamed from: k */
        public long f4601k = 0;

        /* renamed from: l */
        public volatile long f4602l = -1;

        /* renamed from: m */
        public volatile long f4603m = -1;

        /* renamed from: n */
        public volatile long f4604n = 0;

        /* renamed from: o */
        public volatile long f4605o = 0;

        /* renamed from: p */
        public volatile int f4606p = -1;

        /* renamed from: q */
        public volatile int f4607q = -1;

        /* renamed from: r */
        public volatile boolean f4608r = false;

        /* renamed from: s */
        public volatile boolean f4609s = false;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class DecoderHandler extends Handler {
            public static final int MSG_SHUTDOWN = 2;
            public static final int START_EXTRACT_AND_DECODE = 1;

            /* renamed from: a */
            public WeakReference<DecoderThread> f4612a;

            public DecoderHandler(DecoderThread decoderThread) {
                this.f4612a = new WeakReference<>(decoderThread);
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                int i = message.what;
                DecoderThread decoderThread = this.f4612a.get();
                if (decoderThread == null) {
                    Log.w("MediaDecoderDevice", "DecoderHandler.handleMessage: weak ref is null");
                } else if (i == 1) {
                    decoderThread.startDecoder();
                } else if (i == 2) {
                    decoderThread.m19950a();
                } else {
                    throw new RuntimeException("unknown message " + i);
                }
            }
        }

        public DecoderThread(MediaDecoderDevice mediaDecoderDevice) {
            this.f4591a = mediaDecoderDevice;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(12:(2:2|3)|(38:8|9|10|11|12|13|14|15|16|17|(4:19|(3:27|(1:29)(1:31)|30)|23|(1:25))|32|(1:36)|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:52|53)(1:202)|54|(1:56)|57|58|59|60|61|(4:67|(6:172|173|174|175|176|177)(6:69|70|(8:78|(1:80)(1:170)|81|82|(2:84|85)|86|87|(2:88|(2:95|(3:162|163|164)(2:97|(2:160|161)(2:99|(2:157|158)(2:101|(1:1)(1:107)))))(3:169|168|167)))|171|87|(3:88|(1:169)(4:91|93|95|(0)(0))|107))|142|62)|182|183)|208|209|210|211|212|213|214|182|183|(2:(0)|(7:136|137|138|139|140|141|142))) */
        /* JADX WARN: Can't wrap try/catch for region: R(35:8|9|10|11|12|13|(2:14|15)|(3:16|17|(4:19|(3:27|(1:29)(1:31)|30)|23|(1:25)))|32|(1:36)|37|38|39|40|41|42|43|44|45|46|47|48|49|50|(2:52|53)(1:202)|54|(1:56)|57|58|59|60|61|(4:67|(6:172|173|174|175|176|177)(6:69|70|(8:78|(1:80)(1:170)|81|82|(2:84|85)|86|87|(2:88|(2:95|(3:162|163|164)(2:97|(2:160|161)(2:99|(2:157|158)(2:101|(1:1)(1:107)))))(3:169|168|167)))|171|87|(3:88|(1:169)(4:91|93|95|(0)(0))|107))|142|62)|182|183) */
        /* JADX WARN: Code restructure failed: missing block: B:117:0x0238, code lost:
            if (r6 <= 0) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0240, code lost:
            if (r28.f4591a.f4583n == false) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:120:0x0242, code lost:
            if (r18 != 0) goto L115;
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x0244, code lost:
            r28.m19949a(r4 ? 1 : 0, r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:123:0x024d, code lost:
            if (r28.f4602l >= 0) goto L152;
         */
        /* JADX WARN: Code restructure failed: missing block: B:124:0x024f, code lost:
            r28.f4602l = r12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:126:0x0253, code lost:
            r14 = new java.lang.StringBuilder();
         */
        /* JADX WARN: Code restructure failed: missing block: B:128:0x025a, code lost:
            r14.append("firstVideoFramePTS = ");
            r16 = r5;
            r14.append(r28.f4602l);
            android.util.Log.d("MediaDecoderDevice", r14.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:129:0x026c, code lost:
            r16 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:130:0x0274, code lost:
            if (r28.f4591a.f4570a != 0) goto L122;
         */
        /* JADX WARN: Code restructure failed: missing block: B:131:0x0276, code lost:
            r28.m19948a(r28.f4602l, r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:133:0x027d, code lost:
            if (r9.size == 0) goto L151;
         */
        /* JADX WARN: Code restructure failed: missing block: B:134:0x027f, code lost:
            if (r18 != 0) goto L151;
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:0x0281, code lost:
            r3 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:136:0x0284, code lost:
            r3 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x0285, code lost:
            r8.releaseOutputBuffer(r11, r3);
            r18 = (r18 + 1) % r28.f4591a.f4586q;
         */
        /* JADX WARN: Code restructure failed: missing block: B:138:0x0292, code lost:
            if (r3 == false) goto L129;
         */
        /* JADX WARN: Code restructure failed: missing block: B:139:0x0294, code lost:
            r28.m19945a(new com.baidu.cloud.framework.frame.VideoFrameBuffer(null, r6, 0));
         */
        /* JADX WARN: Code restructure failed: missing block: B:140:0x029e, code lost:
            r24 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x02a8, code lost:
            if (r28.f4591a.f4585p <= 0) goto L147;
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x02b6, code lost:
            if (r12 >= (r28.f4602l + r28.f4591a.f4585p)) goto L133;
         */
        /* JADX WARN: Code restructure failed: missing block: B:146:0x02bc, code lost:
            if ((r9.flags & 4) == 0) goto L150;
         */
        /* JADX WARN: Code restructure failed: missing block: B:147:0x02be, code lost:
            r8.stop();
            r8.release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x02c4, code lost:
            r8 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:149:0x02c6, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:151:0x02cb, code lost:
            android.util.Log.e("MediaDecoderDevice", "error while releasing videoDecoder", r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:155:0x02e9, code lost:
            r5 = null;
            r0 = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:160:0x02ff, code lost:
            r8.stop();
         */
        /* JADX WARN: Code restructure failed: missing block: B:162:0x0303, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x0304, code lost:
            r8 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:171:0x032a, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:173:0x032c, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:177:0x0330, code lost:
            r1 = r0;
            r3 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:180:0x0335, code lost:
            r8 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x0338, code lost:
            android.util.Log.d("MediaDecoderDevice", android.util.Log.getStackTraceString(r0));
         */
        /* JADX WARN: Code restructure failed: missing block: B:183:0x0345, code lost:
            if (r28.f4591a.f4590u != null) goto L199;
         */
        /* JADX WARN: Code restructure failed: missing block: B:184:0x0347, code lost:
            r28.f4591a.f4590u.onFinish(false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:185:0x0351, code lost:
            if (r8 == null) goto L182;
         */
        /* JADX WARN: Code restructure failed: missing block: B:187:0x0354, code lost:
            r8.release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:189:0x0358, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:190:0x0359, code lost:
            android.util.Log.e("MediaDecoderDevice", "error while releasing videoDecoder", r0);
         */
        /* JADX WARN: Not initialized variable reg: 8, insn: 0x036a: MOVE  (r3 I:??[OBJECT, ARRAY]) = (r8 I:??[OBJECT, ARRAY]), block:B:194:0x036a */
        /* JADX WARN: Removed duplicated region for block: B:103:0x01ea  */
        /* JADX WARN: Removed duplicated region for block: B:184:0x0347 A[Catch: all -> 0x0369, TRY_LEAVE, TryCatch #3 {all -> 0x0369, blocks: (B:62:0x0130, B:63:0x0147, B:66:0x0151, B:68:0x0155, B:71:0x015f, B:72:0x0162, B:73:0x0165, B:76:0x0174, B:78:0x0178, B:81:0x017f, B:83:0x018b, B:85:0x01a7, B:89:0x01b4, B:92:0x01c8, B:95:0x01d2, B:97:0x01d6, B:102:0x01e1, B:105:0x01ed, B:106:0x01f2, B:108:0x01fa, B:109:0x01ff, B:111:0x0219, B:113:0x0223, B:115:0x0229, B:118:0x023a, B:121:0x0244, B:122:0x0247, B:124:0x024f, B:126:0x0253, B:128:0x025a, B:132:0x027b, B:137:0x0285, B:139:0x0294, B:140:0x029e, B:142:0x02aa, B:147:0x02be, B:153:0x02d3, B:151:0x02cb, B:144:0x02b8, B:129:0x026c, B:131:0x0276, B:182:0x0338, B:184:0x0347), top: B:208:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:213:0x036e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:225:0x0165 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:231:0x015d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:236:0x01e1 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00ed  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00f4  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x010b A[Catch: all -> 0x032e, Exception -> 0x0333, TryCatch #9 {Exception -> 0x0333, blocks: (B:3:0x000a, B:5:0x0012, B:8:0x001c, B:10:0x0024, B:12:0x002c, B:33:0x009f, B:35:0x00a9, B:37:0x00b3, B:39:0x00be, B:41:0x00c5, B:43:0x00cb, B:45:0x00d4, B:47:0x00d9, B:49:0x00e2, B:51:0x00e7, B:54:0x00ef, B:56:0x00f5, B:58:0x010b, B:59:0x0114, B:61:0x0122, B:32:0x009c), top: B:208:0x000a }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static /* synthetic */ void m19944a(com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread r28) {
            /*
                Method dump skipped, instructions count: 902
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread.m19944a(com.baidu.cloud.mediaprocess.device.MediaDecoderDevice$DecoderThread):void");
        }

        /* renamed from: b */
        public static /* synthetic */ void m19942b(DecoderThread decoderThread) {
            if (decoderThread.f4591a.f4582m) {
                return;
            }
            if (!decoderThread.f4591a.getExtractVideoEnabled() || decoderThread.f4609s) {
                if ((!decoderThread.f4591a.getExtractAudioEnabled() || decoderThread.f4608r) && decoderThread.f4591a.f4590u != null) {
                    decoderThread.f4591a.f4590u.onFinish(true);
                }
            }
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:(2:2|3)|(25:8|9|10|11|12|13|14|15|16|17|(4:19|(3:27|(1:29)(1:31)|30)|23|(1:25))|32|(1:36)|37|39|40|41|(5:48|(6:242|243|244|245|246|248)(9:50|51|52|(3:55|56|(9:61|(4:63|64|65|66)(1:235)|67|68|(3:71|72|73)|74|(3:75|76|(2:83|(2:204|205)(2:85|(7:195|196|197|198|199|200|201)(3:87|88|(2:191|192)(3:90|91|(1:1)(1:97)))))(4:208|207|206|209))|193|194))|241|74|(4:75|76|(1:209)(4:79|81|83|(0)(0))|97)|193|194)|151|42|43)|254|(2:256|257)|173|174|175|170|171)|278|279|280|281|282|283|284|170|171) */
        /* JADX WARN: Can't wrap try/catch for region: R(20:8|9|10|11|12|13|(2:14|15)|(3:16|17|(4:19|(3:27|(1:29)(1:31)|30)|23|(1:25)))|32|(1:36)|37|39|40|41|(5:48|(6:242|243|244|245|246|248)(9:50|51|52|(3:55|56|(9:61|(4:63|64|65|66)(1:235)|67|68|(3:71|72|73)|74|(3:75|76|(2:83|(2:204|205)(2:85|(7:195|196|197|198|199|200|201)(3:87|88|(2:191|192)(3:90|91|(1:1)(1:97)))))(4:208|207|206|209))|193|194))|241|74|(4:75|76|(1:209)(4:79|81|83|(0)(0))|97)|193|194)|151|42|43)|254|(2:256|257)|(3:173|174|175)|170|171) */
        /* JADX WARN: Code restructure failed: missing block: B:119:0x0225, code lost:
            if (r14 <= 0) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:121:0x022d, code lost:
            if (r41.f4591a.f4583n == false) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:122:0x022f, code lost:
            r41.m19949a(r4, r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:126:0x023a, code lost:
            if (r41.f4603m >= 0) goto L185;
         */
        /* JADX WARN: Code restructure failed: missing block: B:127:0x023c, code lost:
            r41.f4603m = r7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:129:0x0240, code lost:
            r12 = new java.lang.StringBuilder();
         */
        /* JADX WARN: Code restructure failed: missing block: B:131:0x0247, code lost:
            r12.append("mFirstAudioFramePTS = ");
            r12.append(r41.f4603m);
            android.util.Log.d("MediaDecoderDevice", r12.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:135:0x025e, code lost:
            if (r41.f4591a.f4570a != 1) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:136:0x0260, code lost:
            r41.m19948a(r41.f4603m, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x0265, code lost:
            r3 = r10.flags;
         */
        /* JADX WARN: Code restructure failed: missing block: B:138:0x0267, code lost:
            if (r13 <= 0) goto L184;
         */
        /* JADX WARN: Code restructure failed: missing block: B:139:0x0269, code lost:
            r4 = r24[r12].duplicate();
            r11 = new byte[r13];
            r4.get(r11);
            r4.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:140:0x0277, code lost:
            r4 = r32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:141:0x0279, code lost:
            if (r4 == null) goto L118;
         */
        /* JADX WARN: Code restructure failed: missing block: B:142:0x027b, code lost:
            r4.setRate(((r41.f4607q * 1.0f) * r41.f4591a.f4586q) / 44100.0f);
            r12 = r33;
            r12.offer(java.lang.Long.valueOf(r14));
            r4.putBytes(r11);
            r13 = (r13 * 44100) / (r41.f4607q * r41.f4591a.f4586q);
         */
        /* JADX WARN: Code restructure failed: missing block: B:143:0x02ae, code lost:
            if (r41.f4606p != 1) goto L161;
         */
        /* JADX WARN: Code restructure failed: missing block: B:144:0x02b0, code lost:
            r13 = r13 * 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:145:0x02b2, code lost:
            r2 = r13 - (r13 % 4);
            r11 = new byte[r2];
            r13 = r4.getBytesWithTwoChannels(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:146:0x02bd, code lost:
            r12 = r33;
            r2 = r25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:147:0x02c1, code lost:
            if (r13 <= 0) goto L156;
         */
        /* JADX WARN: Code restructure failed: missing block: B:148:0x02c3, code lost:
            r11 = java.nio.ByteBuffer.wrap(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:149:0x02cb, code lost:
            if (r12.size() <= 0) goto L155;
         */
        /* JADX WARN: Code restructure failed: missing block: B:150:0x02cd, code lost:
            r30 = r0;
            r29 = r9;
            r28 = r10;
            r9 = ((java.lang.Long) r12.poll()).longValue();
         */
        /* JADX WARN: Code restructure failed: missing block: B:151:0x02e0, code lost:
            r30 = r0;
            r29 = r9;
            r28 = r10;
            r9 = r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:152:0x02e7, code lost:
            r41.m19946a(new com.baidu.cloud.framework.frame.AudioFrameBuffer(r11, r9, r13));
         */
        /* JADX WARN: Code restructure failed: missing block: B:153:0x02f0, code lost:
            r30 = r0;
            r29 = r9;
            r28 = r10;
         */
        /* JADX WARN: Code restructure failed: missing block: B:154:0x02f6, code lost:
            r0 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:155:0x02f8, code lost:
            r30 = r0;
            r29 = r9;
            r28 = r10;
            r4 = r32;
            r12 = r33;
            r0 = r25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:156:0x0304, code lost:
            r5.releaseOutputBuffer(r12, false);
         */
        /* JADX WARN: Code restructure failed: missing block: B:157:0x0314, code lost:
            if (r41.f4591a.f4585p <= 0) goto L152;
         */
        /* JADX WARN: Code restructure failed: missing block: B:159:0x0322, code lost:
            if (r7 >= (r41.f4603m + r41.f4591a.f4585p)) goto L129;
         */
        /* JADX WARN: Code restructure failed: missing block: B:161:0x0326, code lost:
            if ((r3 & 4) == 0) goto L154;
         */
        /* JADX WARN: Code restructure failed: missing block: B:162:0x0328, code lost:
            if (r4 == null) goto L144;
         */
        /* JADX WARN: Code restructure failed: missing block: B:163:0x032a, code lost:
            r4.finish();
         */
        /* JADX WARN: Code restructure failed: missing block: B:164:0x032d, code lost:
            r3 = 4096;
         */
        /* JADX WARN: Code restructure failed: missing block: B:165:0x032f, code lost:
            if (r0 <= 4096) goto L134;
         */
        /* JADX WARN: Code restructure failed: missing block: B:166:0x0331, code lost:
            r3 = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:167:0x0332, code lost:
            r3 = new byte[r3];
            r9 = r4.getBytesWithTwoChannels(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:168:0x0338, code lost:
            if (r9 <= 0) goto L143;
         */
        /* JADX WARN: Code restructure failed: missing block: B:169:0x033a, code lost:
            r3 = java.nio.ByteBuffer.wrap(r3);
            r10 = 1 + r14;
         */
        /* JADX WARN: Code restructure failed: missing block: B:170:0x0345, code lost:
            if (r12.size() <= 0) goto L139;
         */
        /* JADX WARN: Code restructure failed: missing block: B:171:0x0347, code lost:
            r10 = ((java.lang.Long) r12.poll()).longValue();
         */
        /* JADX WARN: Code restructure failed: missing block: B:172:0x0351, code lost:
            r41.m19946a(new com.baidu.cloud.framework.frame.AudioFrameBuffer(r3, r10, r9));
         */
        /* JADX WARN: Code restructure failed: missing block: B:173:0x0359, code lost:
            if (r9 != 0) goto L131;
         */
        /* JADX WARN: Code restructure failed: missing block: B:175:0x035f, code lost:
            android.util.Log.d("MediaDecoderDevice", "audio decoder: EOS");
            r3 = new com.baidu.cloud.framework.frame.AudioFrameBuffer(null, r14, 0);
            r3.flag |= 4;
            r41.m19946a(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:176:0x0378, code lost:
            if (r41.f4591a.f4590u == null) goto L148;
         */
        /* JADX WARN: Code restructure failed: missing block: B:177:0x037a, code lost:
            r41.f4591a.f4582m = true;
            r41.f4591a.f4590u.onFinish(true);
         */
        /* JADX WARN: Code restructure failed: missing block: B:178:0x0389, code lost:
            r29 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:180:0x038d, code lost:
            r25 = r0;
            r22 = r7;
            r20 = r14;
            r7 = 0;
            r10 = r28;
            r9 = r29;
            r0 = r30;
            r3 = false;
            r11 = 2;
            r14 = r4;
            r15 = r12;
            r4 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:181:0x03a2, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:200:0x03e7, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:201:0x03e8, code lost:
            r2 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:202:0x03ec, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:203:0x03ed, code lost:
            r4 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:211:0x0413, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:213:0x0415, code lost:
            r0 = e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:217:0x0419, code lost:
            r1 = r0;
            r2 = r9;
            r5 = r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:220:0x041f, code lost:
            r4 = r9;
            r5 = r4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:230:0x0451, code lost:
            if (r5 == null) goto L170;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0198, code lost:
            r15 = r33;
            r7 = 0;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:224:0x0432 A[Catch: all -> 0x0468, TRY_LEAVE, TryCatch #6 {all -> 0x0468, blocks: (B:142:0x027b, B:144:0x02b0, B:145:0x02b2, B:148:0x02c3, B:150:0x02cd, B:152:0x02e7, B:156:0x0304, B:158:0x0316, B:163:0x032a, B:167:0x0332, B:169:0x033a, B:171:0x0347, B:172:0x0351, B:175:0x035f, B:177:0x037a, B:160:0x0324, B:222:0x0423, B:224:0x0432), top: B:261:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:266:0x03ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:268:0x046d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:273:0x0444 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:278:0x047c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:291:0x0102 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:299:0x00f5 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:301:0x0192 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:97:0x01a0  */
        /* JADX WARN: Type inference failed for: r4v0 */
        /* JADX WARN: Type inference failed for: r4v11 */
        /* JADX WARN: Type inference failed for: r4v14 */
        /* JADX WARN: Type inference failed for: r4v2 */
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static /* synthetic */ void m19940c(com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread r41) {
            /*
                Method dump skipped, instructions count: 1171
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread.m19940c(com.baidu.cloud.mediaprocess.device.MediaDecoderDevice$DecoderThread):void");
        }

        /* renamed from: a */
        public final MediaCodec m19947a(MediaFormat mediaFormat) {
            MediaCodec createDecoderByType = MediaCodec.createDecoderByType(this.f4591a.f4577h.getString("mime"));
            createDecoderByType.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 0);
            createDecoderByType.start();
            return createDecoderByType;
        }

        /* renamed from: a */
        public void m19950a() {
            this.f4595e = true;
            this.f4591a.getAudioOutPort().unlinkAll();
            this.f4591a.f4588s.unlinkAll();
            this.f4591a.f4590u = null;
            try {
                if (this.f4597g != null && this.f4597g.isAlive()) {
                    this.f4597g.join(500L);
                }
                if (this.f4596f != null && this.f4596f.isAlive()) {
                    this.f4596f.join(500L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Looper.myLooper().quit();
            this.f4597g = null;
            this.f4596f = null;
        }

        /* renamed from: a */
        public final void m19949a(int i, long j) {
            if (i != 0) {
                long currentTimeMillis = System.currentTimeMillis();
                long j2 = this.f4599i;
                if (j2 != 0) {
                    currentTimeMillis = ((j - this.f4598h) / 1000) + j2;
                    try {
                        long currentTimeMillis2 = currentTimeMillis - System.currentTimeMillis();
                        if (currentTimeMillis2 >= 0) {
                            Thread.sleep(currentTimeMillis2);
                        }
                    } catch (Exception unused) {
                    }
                }
                this.f4598h = j;
                this.f4599i = currentTimeMillis;
                return;
            }
            long j3 = this.f4601k;
            if (j3 != 0) {
                long j4 = ((j - this.f4600j) / 1000) + j3;
                try {
                    long currentTimeMillis3 = j4 - System.currentTimeMillis();
                    if (currentTimeMillis3 >= 0) {
                        Thread.sleep(currentTimeMillis3);
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("done'wait;videoWait=");
                        sb.append(currentTimeMillis3);
                        Log.d("MediaDecoderDevice", sb.toString());
                    }
                } catch (Exception unused2) {
                }
                this.f4601k = j4;
            } else {
                this.f4601k = System.currentTimeMillis();
            }
            this.f4600j = j;
        }

        /* renamed from: a */
        public final void m19948a(long j, long j2) {
            if (this.f4591a.f4590u == null || this.f4604n <= 0) {
                return;
            }
            long j3 = ((j2 - j) * 1000) / this.f4604n;
            if (j3 > this.f4605o) {
                this.f4605o = j3;
                this.f4591a.f4590u.onProgress(((int) this.f4605o) / 10, j2);
            }
        }

        /* renamed from: a */
        public final void m19946a(AudioFrameBuffer audioFrameBuffer) {
            if (this.f4591a.getAudioOutPort().isPortLinked()) {
                this.f4591a.getAudioOutPort().onFrame((OutPort<AudioFrameBuffer>) audioFrameBuffer);
            }
        }

        /* renamed from: a */
        public final void m19945a(VideoFrameBuffer videoFrameBuffer) {
            if (this.f4591a.f4588s.isPortLinked()) {
                this.f4591a.f4588s.onFrame((OutPort) videoFrameBuffer);
            }
        }

        public DecoderHandler getHandler() {
            synchronized (this.f4593c) {
                if (!this.f4594d) {
                    throw new RuntimeException("not ready");
                }
            }
            return this.f4592b;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Looper.prepare();
            this.f4592b = new DecoderHandler(this);
            Log.d("MediaDecoderDevice", "decoder thread ready");
            synchronized (this.f4593c) {
                this.f4594d = true;
                this.f4593c.notifyAll();
            }
            Looper.loop();
            synchronized (this.f4593c) {
                this.f4594d = false;
                this.f4592b = null;
            }
            Log.d("MediaDecoderDevice", "looper quit");
        }

        public void startDecoder() {
            try {
                this.f4595e = false;
                if (this.f4591a.getExtractVideoEnabled()) {
                    this.f4596f = new Thread(new Runnable() { // from class: com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            DecoderThread.m19944a(DecoderThread.this);
                            DecoderThread.this.f4609s = true;
                            DecoderThread.m19942b(DecoderThread.this);
                        }
                    });
                    this.f4596f.start();
                } else {
                    this.f4591a.f4570a = 1;
                }
                if (this.f4591a.getExtractAudioEnabled()) {
                    this.f4597g = new Thread(new Runnable() { // from class: com.baidu.cloud.mediaprocess.device.MediaDecoderDevice.DecoderThread.2
                        @Override // java.lang.Runnable
                        public void run() {
                            DecoderThread.m19940c(DecoderThread.this);
                            DecoderThread.this.f4608r = true;
                            DecoderThread.m19942b(DecoderThread.this);
                        }
                    });
                    this.f4597g.start();
                }
            } catch (Exception e) {
                Log.d("MediaDecoderDevice", Log.getStackTraceString(e));
            }
        }

        public void waitUntilReady() {
            synchronized (this.f4593c) {
                while (!this.f4594d) {
                    try {
                        this.f4593c.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDecodeStateChangeListener {
        void onDurationUpdated(int i);

        void onFinish(boolean z);

        void onProgress(int i, long j);
    }

    public MediaDecoderDevice(String str) {
        this.f4580k = null;
        this.f4581l = null;
        this.f4580k = str;
        this.f4581l = new File(this.f4580k);
        if (this.f4581l.canRead()) {
            if (this.f4579j == null) {
                this.f4579j = new DecoderThread(this);
            }
            this.f4579j.start();
            this.f4579j.waitUntilReady();
            return;
        }
        throw new FileNotFoundException("Unable to read " + this.f4581l);
    }

    /* renamed from: a */
    public final MediaFormat m19969a(MediaExtractor mediaExtractor, String str) {
        for (int i = 0; i < mediaExtractor.getTrackCount(); i++) {
            MediaFormat trackFormat = mediaExtractor.getTrackFormat(i);
            if (trackFormat.getString("mime").startsWith(str)) {
                mediaExtractor.selectTrack(i);
                return trackFormat;
            }
        }
        return null;
    }

    public void configClip(long j, long j2) {
        Log.d("MediaDecoderDevice", "configClip start=" + j + ";duration=" + j2);
        if (j > 0) {
            this.f4584o = j;
        }
        if (j2 > 0) {
            this.f4585p = j2;
        }
    }

    public OutPort<AudioFrameBuffer> getAudioOutPort() {
        return this.f4587r;
    }

    public boolean getExtractAudioEnabled() {
        return this.f4572c;
    }

    public boolean getExtractVideoEnabled() {
        return this.f4571b;
    }

    public String getFileFullPath() {
        return this.f4580k;
    }

    public int getRotation() {
        MediaFormat mediaFormat;
        if (!this.f4574e || (mediaFormat = this.f4578i) == null) {
            return 0;
        }
        return mediaFormat.getInteger("rotation-degrees");
    }

    public int getVideoHeight() {
        MediaFormat mediaFormat;
        if (!this.f4574e || (mediaFormat = this.f4578i) == null) {
            return 0;
        }
        return mediaFormat.getInteger("height");
    }

    public int getVideoWidth() {
        MediaFormat mediaFormat;
        if (!this.f4574e || (mediaFormat = this.f4578i) == null) {
            return 0;
        }
        return mediaFormat.getInteger("width");
    }

    public boolean isSyncWithSystemTime() {
        return this.f4583n;
    }

    public void pause() {
        this.f4573d = true;
    }

    public void release() {
        if (this.f4574e) {
            this.f4574e = false;
            MediaExtractor mediaExtractor = this.f4575f;
            if (mediaExtractor != null) {
                mediaExtractor.release();
                this.f4575f = null;
            }
            MediaExtractor mediaExtractor2 = this.f4576g;
            if (mediaExtractor2 != null) {
                mediaExtractor2.release();
                this.f4576g = null;
            }
        }
    }

    public void resume() {
        this.f4573d = false;
    }

    public void setExtractAudioEnabled(boolean z) {
        this.f4572c = z;
    }

    public void setExtractVideoEnabled(boolean z) {
        this.f4571b = z;
    }

    public void setIsSyncWithSystemTime(boolean z) {
        this.f4583n = z;
    }

    public void setOnDecodeStateChangeListener(OnDecodeStateChangeListener onDecodeStateChangeListener) {
        this.f4590u = onDecodeStateChangeListener;
    }

    public void setPlaybackRate(int i) {
        if (i <= 0 || i >= 9) {
            Log.i("MediaDecoderDevice", "setPlaybackRate: playbackRate should be in [1,9]");
        } else {
            this.f4586q = i;
        }
    }

    public void setVideoOutputSurface(Surface surface) {
        this.f4589t = surface;
    }

    public void setup() {
        if (this.f4574e) {
            return;
        }
        try {
            if (this.f4575f == null) {
                this.f4575f = new MediaExtractor();
                this.f4575f.setDataSource(this.f4580k);
                this.f4578i = m19969a(this.f4575f, "video");
            }
            if (this.f4576g == null) {
                this.f4576g = new MediaExtractor();
                this.f4576g.setDataSource(this.f4580k);
                this.f4577h = m19969a(this.f4576g, "audio");
            }
            this.f4574e = true;
        } catch (IOException e) {
            Log.e("MediaDecoderDevice", "prepare: Video not found!");
            this.f4574e = false;
            e.printStackTrace();
        }
    }

    public void startDecoder() {
        if (!this.f4574e) {
            throw new IllegalStateException("Device is not prepared!");
        }
        DecoderThread.DecoderHandler handler = this.f4579j.getHandler();
        handler.sendMessage(handler.obtainMessage(1));
    }

    public void stopDecoder() {
        if (!this.f4574e) {
            throw new IllegalStateException("Device is not prepared!");
        }
        DecoderThread decoderThread = this.f4579j;
        if (decoderThread != null) {
            DecoderThread.DecoderHandler handler = decoderThread.getHandler();
            handler.sendMessage(handler.obtainMessage(2));
            try {
                this.f4579j.join(1000L);
            } catch (InterruptedException e) {
                Log.w("MediaDecoderDevice", "Encoder thread join() was interrupted", e);
            }
        }
        Log.d("MediaDecoderDevice", "The avc decoder was destroyed!");
    }
}
