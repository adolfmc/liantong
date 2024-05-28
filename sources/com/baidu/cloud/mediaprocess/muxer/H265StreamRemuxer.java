package com.baidu.cloud.mediaprocess.muxer;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class H265StreamRemuxer extends VideoStreamRemuxer {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class SrsHvcNaluType {
        public static final int ACCESS_UNIT_DELIMITER = 35;
        public static final int CODED_SLICE_BLA_N_LP = 18;
        public static final int CODED_SLICE_BLA_W_LP = 16;
        public static final int CODED_SLICE_BLA_W_RADL = 17;
        public static final int CODED_SLICE_CRA = 21;
        public static final int CODED_SLICE_IDR_N_LP = 20;
        public static final int CODED_SLICE_IDR_W_RADL = 19;
        public static final int CODED_SLICE_RADL_N = 6;
        public static final int CODED_SLICE_RADL_R = 7;
        public static final int CODED_SLICE_RASL_N = 8;
        public static final int CODED_SLICE_RASL_R = 9;
        public static final int CODED_SLICE_STSA_N = 4;
        public static final int CODED_SLICE_STSA_R = 5;
        public static final int CODED_SLICE_TRAIL_N = 0;
        public static final int CODED_SLICE_TRAIL_R = 1;
        public static final int CODED_SLICE_TSA_N = 2;
        public static final int CODED_SLICE_TSA_R = 3;
        public static final int EOB = 37;
        public static final int EOS = 36;
        public static final int FILLER_DATA = 38;
        public static final int PPS = 34;
        public static final int PREFIX_SEI = 39;
        public static final int SPS = 33;
        public static final int SUFFIX_SEI = 40;
        public static final int VPS = 32;
    }

    /* renamed from: a */
    public final VideoFrameBuffer m19851a(VideoFrameBuffer videoFrameBuffer, int i, int i2, int i3, int i4) {
        VideoFrameBuffer videoFrameBuffer2 = new VideoFrameBuffer();
        videoFrameBuffer2.size = videoFrameBuffer.size + 5;
        if (i2 == 1) {
            videoFrameBuffer2.size += 4;
        }
        videoFrameBuffer2.buffer = ByteBuffer.allocate(videoFrameBuffer2.size);
        videoFrameBuffer2.buffer.put((byte) ((i << 4) | 12));
        videoFrameBuffer2.buffer.put((byte) i2);
        int i5 = i4 - i3;
        videoFrameBuffer2.buffer.put((byte) (i5 >> 16));
        videoFrameBuffer2.buffer.put((byte) (i5 >> 8));
        videoFrameBuffer2.buffer.put((byte) i5);
        if (i2 == 1) {
            videoFrameBuffer2.buffer.putInt(videoFrameBuffer.size);
        }
        byte[] bArr = new byte[videoFrameBuffer.size];
        videoFrameBuffer.buffer.get(bArr);
        videoFrameBuffer2.buffer.put(bArr);
        videoFrameBuffer2.buffer.rewind();
        return videoFrameBuffer2;
    }

    @Override // com.baidu.cloud.mediaprocess.muxer.VideoStreamRemuxer
    public VideoFrameBuffer remuxNaluTag(VideoFrameBuffer videoFrameBuffer, int i, int i2) {
        int i3;
        int i4 = 1;
        int i5 = (videoFrameBuffer.buffer.get(0) & 126) >> 1;
        if (i5 == 39 || i5 == 40) {
            return null;
        }
        switch (i5) {
            case 19:
            case 20:
            case 21:
                i3 = i2;
                break;
            default:
                switch (i5) {
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                        return null;
                    default:
                        i4 = 2;
                        i3 = i;
                        break;
                }
        }
        return m19851a(videoFrameBuffer, i4, 1, i3, i2);
    }

    @Override // com.baidu.cloud.mediaprocess.muxer.VideoStreamRemuxer
    public VideoFrameBuffer remuxSequenceHeader(byte[] bArr, int i, int i2) {
        VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer();
        videoFrameBuffer.size = i;
        videoFrameBuffer.buffer = ByteBuffer.wrap(bArr);
        videoFrameBuffer.buffer.rewind();
        return m19851a(videoFrameBuffer, 1, 0, i2, i2);
    }
}
