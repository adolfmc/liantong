package com.baidu.cloud.mediaprocess.muxer;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class H264StreamRemuxer extends VideoStreamRemuxer {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class SrsAvcNaluType {
        public static final int AccessUnitDelimiter = 9;
        public static final int CodedSliceExt = 20;
        public static final int DataPartitionA = 2;
        public static final int DataPartitionB = 3;
        public static final int DataPartitionC = 4;
        public static final int EOSequence = 10;
        public static final int EOStream = 11;
        public static final int FilterData = 12;
        public static final int IDR = 5;
        public static final int LayerWithoutPartition = 19;
        public static final int NonIDR = 1;
        public static final int PPS = 8;
        public static final int PrefixNALU = 14;
        public static final int Reserved = 0;
        public static final int SEI = 6;
        public static final int SPS = 7;
        public static final int SPSExt = 13;
        public static final int SubsetSPS = 15;
    }

    /* renamed from: a */
    public final VideoFrameBuffer m19852a(VideoFrameBuffer videoFrameBuffer, int i, int i2, int i3, int i4) {
        VideoFrameBuffer videoFrameBuffer2 = new VideoFrameBuffer();
        videoFrameBuffer2.size = videoFrameBuffer.size + 5;
        if (i2 == 1) {
            videoFrameBuffer2.size += 4;
        }
        videoFrameBuffer2.buffer = ByteBuffer.allocate(videoFrameBuffer2.size);
        videoFrameBuffer2.buffer.put((byte) ((i << 4) | 7));
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
        int i4;
        switch (videoFrameBuffer.buffer.get(0) & 31) {
            case 5:
                i3 = 1;
                i4 = i2;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
            default:
                i4 = i;
                i3 = 2;
                break;
        }
        return m19852a(videoFrameBuffer, i3, 1, i4, i2);
    }

    @Override // com.baidu.cloud.mediaprocess.muxer.VideoStreamRemuxer
    public VideoFrameBuffer remuxSequenceHeader(byte[] bArr, int i, int i2) {
        VideoFrameBuffer videoFrameBuffer = new VideoFrameBuffer();
        videoFrameBuffer.size = i;
        videoFrameBuffer.buffer = ByteBuffer.wrap(bArr);
        videoFrameBuffer.buffer.rewind();
        return m19852a(videoFrameBuffer, 1, 0, i2, i2);
    }
}
