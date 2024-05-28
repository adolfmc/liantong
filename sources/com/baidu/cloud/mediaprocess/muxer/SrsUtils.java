package com.baidu.cloud.mediaprocess.muxer;

import android.media.MediaCodec;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SrsUtils {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsAacObjectType {
        public static final int AacHE = 5;
        public static final int AacHEV2 = 29;
        public static final int AacLC = 2;
        public static final int AacMain = 1;
        public static final int AacSSR = 3;
        public static final int Reserved = 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsAacProfile {

        /* renamed from: LC */
        public static final int f4844LC = 1;
        public static final int Main = 0;
        public static final int Reserved = 3;
        public static final int SSR = 2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsAnnexbSearch {
        public int nbStartCodeLength = 0;
        public boolean match = false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsCodecVideo {
        public static final int AVC = 7;
        public static final int HEVC = 12;
        public static final int On2VP6 = 4;
        public static final int On2VP6WithAlphaChannel = 5;
        public static final int ScreenVideo = 3;
        public static final int ScreenVideoVersion2 = 6;
        public static final int SorensonH263 = 2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsCodecVideoFrameType {
        public static final int DisposableInterFrame = 3;
        public static final int GeneratedKeyFrame = 4;
        public static final int InterFrame = 2;
        public static final int KeyFrame = 1;
        public static final int VideoInfoFrame = 5;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class SrsCodecVideoPacketType {
        public static final int NALU = 1;
        public static final int SequenceHeader = 0;
        public static final int SequenceHeaderEOF = 2;
    }

    public static int convertRtmp2TSForAac(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    return 2;
                }
                if (i != 5 && i != 29) {
                    return 3;
                }
            }
            return 1;
        }
        return 0;
    }

    public static int convertTS2RtmpForAac(int i) {
        if (i != 0) {
            if (i != 1) {
                return i != 2 ? 0 : 3;
            }
            return 2;
        }
        return 1;
    }

    public static boolean findAdtsStartCode(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        int position = byteBuffer.position();
        return bufferInfo.size - position >= 2 && byteBuffer.get(position) == -1 && ((byte) (byteBuffer.get(position + 1) & 240)) == -16;
    }

    public static SrsAnnexbSearch findAnnexbStartCode(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        SrsAnnexbSearch srsAnnexbSearch = new SrsAnnexbSearch();
        srsAnnexbSearch.match = false;
        int position = byteBuffer.position();
        while (true) {
            if (position >= bufferInfo.size - 3 || byteBuffer.get(position) != 0) {
                break;
            }
            int i = position + 1;
            if (byteBuffer.get(i) != 0) {
                break;
            } else if (byteBuffer.get(position + 2) == 1) {
                srsAnnexbSearch.match = true;
                srsAnnexbSearch.nbStartCodeLength = (position + 3) - byteBuffer.position();
                break;
            } else {
                position = i;
            }
        }
        return srsAnnexbSearch;
    }

    public static boolean isBytesEquals(byte[] bArr, byte[] bArr2) {
        if (((bArr == null || bArr2 == null) && !(bArr == null && bArr2 == null)) || bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length && i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }
}
