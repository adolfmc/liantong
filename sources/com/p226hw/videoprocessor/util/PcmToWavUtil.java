package com.p226hw.videoprocessor.util;

import android.media.AudioRecord;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: com.hw.videoprocessor.util.PcmToWavUtil */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PcmToWavUtil {
    private int mBufferSize;
    private int mChannelConfig;
    private int mChannelCount;
    private int mEncoding;
    private int mSampleRate;

    public PcmToWavUtil() {
        this.mSampleRate = 8000;
        this.mChannelConfig = 12;
        this.mChannelCount = 2;
        this.mEncoding = 2;
        this.mBufferSize = AudioRecord.getMinBufferSize(this.mSampleRate, this.mChannelConfig, this.mEncoding);
    }

    public PcmToWavUtil(int i, int i2, int i3, int i4) {
        this.mSampleRate = 8000;
        this.mChannelConfig = 12;
        this.mChannelCount = 2;
        this.mEncoding = 2;
        this.mSampleRate = i;
        this.mChannelConfig = i2;
        this.mChannelCount = i3;
        this.mEncoding = i4;
        this.mBufferSize = AudioRecord.getMinBufferSize(this.mSampleRate, this.mChannelConfig, this.mEncoding);
    }

    public void pcmToWav(String str, String str2) {
        int i = this.mSampleRate;
        long j = i;
        int i2 = this.mChannelCount;
        long j2 = ((i * 16) * i2) / 8;
        byte[] bArr = new byte[this.mBufferSize];
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream.getChannel().size();
            writeWaveFileHeader(fileOutputStream, size, size + 36, j, i2, j2);
            while (fileInputStream.read(bArr) != -1) {
                fileOutputStream.write(bArr);
            }
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeWaveFileHeader(FileOutputStream fileOutputStream, long j, long j2, long j3, int i, long j4) throws IOException {
        fileOutputStream.write(new byte[]{82, 73, 70, 70, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) i, 0, (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), (byte) (j4 & 255), (byte) ((j4 >> 8) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 24) & 255), 4, 0, 16, 0, 100, 97, 116, 97, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)}, 0, 44);
    }
}
