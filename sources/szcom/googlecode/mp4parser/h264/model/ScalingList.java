package szcom.googlecode.mp4parser.h264.model;

import szcom.googlecode.mp4parser.h264.read.CAVLCReader;
import szcom.googlecode.mp4parser.h264.write.CAVLCWriter;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ScalingList {
    public int[] scalingList;
    public boolean useDefaultScalingMatrixFlag;

    public static ScalingList read(CAVLCReader cAVLCReader, int i) {
        ScalingList scalingList = new ScalingList();
        scalingList.scalingList = new int[i];
        int i2 = 8;
        int i3 = 8;
        int i4 = 0;
        while (i4 < i) {
            if (i2 != 0) {
                i2 = ((cAVLCReader.readSE("deltaScale") + i3) + 256) % 256;
                scalingList.useDefaultScalingMatrixFlag = i4 == 0 && i2 == 0;
            }
            int[] iArr = scalingList.scalingList;
            if (i2 != 0) {
                i3 = i2;
            }
            iArr[i4] = i3;
            i3 = scalingList.scalingList[i4];
            i4++;
        }
        return scalingList;
    }

    public String toString() {
        return "ScalingList{scalingList=" + this.scalingList + ", useDefaultScalingMatrixFlag=" + this.useDefaultScalingMatrixFlag + '}';
    }

    public void write(CAVLCWriter cAVLCWriter) {
        int i = 0;
        if (this.useDefaultScalingMatrixFlag) {
            cAVLCWriter.writeSE(0, "SPS: ");
            return;
        }
        int i2 = 8;
        while (true) {
            int[] iArr = this.scalingList;
            if (i >= iArr.length) {
                return;
            }
            cAVLCWriter.writeSE((iArr[i] - i2) - 256, "SPS: ");
            i2 = this.scalingList[i];
            i++;
        }
    }
}
