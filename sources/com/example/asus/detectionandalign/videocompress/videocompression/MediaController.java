package com.example.asus.detectionandalign.videocompress.videocompression;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@SuppressLint({"NewApi"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaController {
    private static final int DEFAULT_VIDEO_BITRATE = 450000;
    private static final int DEFAULT_VIDEO_HEIGHT = 360;
    private static final int DEFAULT_VIDEO_WIDTH = 640;
    private static volatile MediaController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    public static File cachedFile;
    public String path;
    private boolean videoConvertFirstWrite = true;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class VideoConvertRunnable implements Runnable {
        private File destDirectory;
        private String videoPath;

        private VideoConvertRunnable(String str, File file) {
            this.videoPath = str;
            this.destDirectory = file;
        }

        public static void runConversion(final String str, final File file) {
            new Thread(new Runnable() { // from class: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.VideoConvertRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread thread = new Thread(new VideoConvertRunnable(str, file), "VideoConvertRunnable");
                        thread.start();
                        thread.join();
                    } catch (Exception e) {
                        Log.e("tmessages", e.getMessage());
                    }
                }
            }).start();
        }

        @Override // java.lang.Runnable
        public void run() {
            MediaController.getInstance().convertVideo(this.videoPath, this.destDirectory);
        }
    }

    public static native int convertVideoFrame(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int i, int i2, int i3, int i4, int i5);

    public static void copyFile(File file, File file2) {
        FileChannel channel = new FileInputStream(file).getChannel();
        FileChannel channel2 = new FileOutputStream(file2).getChannel();
        try {
            channel.transferTo(1L, channel.size(), channel2);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (channel2 != null) {
                channel2.close();
            }
        }
    }

    private void didWriteData(boolean z, boolean z2) {
        if (this.videoConvertFirstWrite) {
            this.videoConvertFirstWrite = false;
        }
    }

    public static MediaController getInstance() {
        MediaController mediaController = Instance;
        if (mediaController == null) {
            synchronized (MediaController.class) {
                mediaController = Instance;
                if (mediaController == null) {
                    mediaController = new MediaController();
                    Instance = mediaController;
                }
            }
        }
        return mediaController;
    }

    private static boolean isRecognizedFormat(int i) {
        if (i == 39 || i == 2130706688) {
            return true;
        }
        switch (i) {
            case 19:
            case 20:
            case 21:
                return true;
            default:
                return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0088, code lost:
        if (r10 == (-1)) goto L13;
     */
    @android.annotation.TargetApi(16)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private long readAndWriteTrack(android.media.MediaExtractor r22, com.example.asus.detectionandalign.videocompress.videocompression.MP4Builder r23, android.media.MediaCodec.BufferInfo r24, long r25, long r27, java.io.File r29, boolean r30) {
        /*
            r21 = this;
            r0 = r22
            r1 = r23
            r2 = r24
            r3 = r25
            r5 = r21
            r6 = r30
            int r7 = r5.selectTrack(r0, r6)
            r8 = -1
            if (r7 < 0) goto L9b
            r0.selectTrack(r7)
            android.media.MediaFormat r10 = r0.getTrackFormat(r7)
            int r11 = r1.addTrack(r10, r6)
            java.lang.String r12 = "max-input-size"
            int r10 = r10.getInteger(r12)
            r12 = 0
            int r14 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            r15 = 0
            if (r14 <= 0) goto L30
            r0.seekTo(r3, r15)
            goto L33
        L30:
            r0.seekTo(r12, r15)
        L33:
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocateDirect(r10)
            r16 = r8
            r4 = r15
        L3a:
            if (r4 != 0) goto L97
            int r10 = r22.getSampleTrackIndex()
            r18 = 1
            if (r10 != r7) goto L85
            int r10 = r0.readSampleData(r3, r15)
            r2.size = r10
            int r10 = r2.size
            if (r10 >= 0) goto L53
            r2.size = r15
            r19 = r12
            goto L8a
        L53:
            long r12 = r22.getSampleTime()
            r2.presentationTimeUs = r12
            if (r14 <= 0) goto L62
            int r10 = (r16 > r8 ? 1 : (r16 == r8 ? 0 : -1))
            if (r10 != 0) goto L62
            long r12 = r2.presentationTimeUs
            goto L64
        L62:
            r12 = r16
        L64:
            r19 = 0
            int r10 = (r27 > r19 ? 1 : (r27 == r19 ? 0 : -1))
            if (r10 < 0) goto L74
            long r8 = r2.presentationTimeUs
            int r8 = (r8 > r27 ? 1 : (r8 == r27 ? 0 : -1))
            if (r8 >= 0) goto L71
            goto L74
        L71:
            r16 = r12
            goto L8a
        L74:
            r2.offset = r15
            int r8 = r22.getSampleFlags()
            r2.flags = r8
            r1.writeSampleData(r11, r3, r2, r6)
            r22.advance()
            r16 = r12
            goto L8d
        L85:
            r19 = r12
            r8 = -1
            if (r10 != r8) goto L8d
        L8a:
            r8 = r18
            goto L8e
        L8d:
            r8 = r15
        L8e:
            if (r8 == 0) goto L92
            r4 = r18
        L92:
            r12 = r19
            r8 = -1
            goto L3a
        L97:
            r0.unselectTrack(r7)
            return r16
        L9b:
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.readAndWriteTrack(android.media.MediaExtractor, com.example.asus.detectionandalign.videocompress.videocompression.MP4Builder, android.media.MediaCodec$BufferInfo, long, long, java.io.File, boolean):long");
    }

    public static MediaCodecInfo selectCodec(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        MediaCodecInfo mediaCodecInfo = null;
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.isEncoder()) {
                MediaCodecInfo mediaCodecInfo2 = mediaCodecInfo;
                for (String str2 : codecInfoAt.getSupportedTypes()) {
                    if (str2.equalsIgnoreCase(str)) {
                        if (!codecInfoAt.getName().equals("OMX.SEC.avc.enc") || codecInfoAt.getName().equals("OMX.SEC.AVC.Encoder")) {
                            return codecInfoAt;
                        }
                        mediaCodecInfo2 = codecInfoAt;
                    }
                }
                mediaCodecInfo = mediaCodecInfo2;
            }
        }
        return mediaCodecInfo;
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo mediaCodecInfo, String str) {
        MediaCodecInfo.CodecCapabilities capabilitiesForType = mediaCodecInfo.getCapabilitiesForType(str);
        int i = 0;
        for (int i2 = 0; i2 < capabilitiesForType.colorFormats.length; i2++) {
            int i3 = capabilitiesForType.colorFormats[i2];
            if (isRecognizedFormat(i3)) {
                if (!mediaCodecInfo.getName().equals("OMX.SEC.AVC.Encoder") || i3 != 19) {
                    return i3;
                }
                i = i3;
            }
        }
        return i;
    }

    @TargetApi(16)
    private int selectTrack(MediaExtractor mediaExtractor, boolean z) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            String string = mediaExtractor.getTrackFormat(i).getString("mime");
            if (z) {
                if (string.startsWith("audio/")) {
                    return i;
                }
            } else if (string.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    private void startVideoConvertFromQueue(String str, File file) {
        VideoConvertRunnable.runConversion(str, file);
    }

    public boolean convertVideo(String str, File file) {
        return convertVideo(str, file, 0, 0, 0);
    }

    /*  JADX ERROR: Failed to decode insn: 0x04FE: UNKNOWN(0x0042), method: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.convertVideo(java.lang.String, java.io.File, int, int, int):boolean
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04FE: UNKNOWN(0x0042)'
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
    /*  JADX ERROR: Failed to decode insn: 0x06ED: FILLED_NEW_ARRAY_RANGE r33618, r33619, r33620, r33621, r33622, r33623, r33624, r33625, r33626, r33627, r33628, r33629, r33630, r33631, r33632, r33633, r33634, r33635, r33636, r33637, r33638, r33639, r33640, r33641, r33642, r33643, r33644, r33645, r33646, r33647, r33648, r33649, r33650, r33651, r33652, r33653, r33654, r33655, r33656, r33657, r33658, r33659, r33660, r33661, r33662, r33663, r33664, r33665, r33666, r33667, r33668, r33669, r33670, r33671, r33672, r33673, r33674, r33675, r33676, r33677, r33678, r33679, r33680, r33681, r33682, r33683, r33684, r33685, r33686, r33687, r33688, r33689, r33690, r33691, r33692, r33693, r33694, r33695, r33696, r33697, r33698, r33699, r33700, r33701, r33702, r33703, r33704, r33705, r33706, r33707, r33708, r33709, r33710, r33711, r33712, r33713, r33714, r33715, r33716, r33717, r33718, r33719, r33720, r33721, r33722, r33723, r33724, r33725, r33726, r33727, r33728, r33729, r33730, r33731, r33732, r33733, r33734, r33735, r33736, r33737, r33738, r33739, r33740, r33741, r33742, r33743, r33744, r33745, r33746, r33747, r33748, r33749, r33750, r33751, r33752, r33753, r33754, r33755, r33756, r33757, r33758, r33759, r33760, r33761, r33762, r33763, r33764, r33765, r33766, r33767, r33768, r33769, r33770, r33771, r33772, r33773, r33774, r33775, r33776, r33777, r33778, r33779, r33780, r33781, r33782, r33783, r33784, r33785, r33786, r33787, r33788, r33789, r33790, r33791, r33792, r33793, r33794, r33795, r33796, r33797, r33798, r33799, r33800, r33801, r33802, r33803, r33804, r33805, r33806, r33807, r33808, r33809, r33810, r33811, r33812, r33813, r33814, r33815, r33816, r33817, r33818, r33819, r33820, r33821, r33822, r33823, r33824, r33825, r33826, r33827, r33828, r33829, r33830, r33831, r33832, r33833, r33834, r33835, r33836, r33837, r33838, r33839, r33840, r33841, r33842, r33843, r33844, r33845, r33846, r33847, r33848, r33849, r33850, r33851, r33852, r33853, r33854, r33855, r33856, r33857, r33858, r33859, r33860, r33861, r33862, r33863, r33864, method: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.convertVideo(java.lang.String, java.io.File, int, int, int):boolean
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
    /*  JADX ERROR: Failed to decode insn: 0x0826: UNKNOWN(0x003E), method: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.convertVideo(java.lang.String, java.io.File, int, int, int):boolean
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0826: UNKNOWN(0x003E)'
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
    @android.annotation.TargetApi(16)
    public boolean convertVideo(java.lang.String r48, java.io.File r49, int r50, int r51, int r52) {
        /*
            Method dump skipped, instructions count: 2254
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.videocompression.MediaController.convertVideo(java.lang.String, java.io.File, int, int, int):boolean");
    }

    public void scheduleVideoConvert(String str, File file) {
        startVideoConvertFromQueue(str, file);
    }
}
