package szcom.googlecode.mp4parser.h264.write;

import java.io.OutputStream;
import szcom.googlecode.mp4parser.h264.Debug;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class BitstreamWriter {
    private int curBit;
    private int[] curByte = new int[8];

    /* renamed from: os */
    private final OutputStream f27671os;

    public BitstreamWriter(OutputStream outputStream) {
        this.f27671os = outputStream;
    }

    private void writeCurByte() {
        int[] iArr = this.curByte;
        this.f27671os.write(iArr[7] | (iArr[0] << 7) | (iArr[1] << 6) | (iArr[2] << 5) | (iArr[3] << 4) | (iArr[4] << 3) | (iArr[5] << 2) | (iArr[6] << 1));
    }

    public void flush() {
        for (int i = this.curBit; i < 8; i++) {
            this.curByte[i] = 0;
        }
        this.curBit = 0;
        writeCurByte();
    }

    public void write1Bit(int i) {
        Debug.print(i);
        if (this.curBit == 8) {
            this.curBit = 0;
            writeCurByte();
        }
        int[] iArr = this.curByte;
        int i2 = this.curBit;
        this.curBit = i2 + 1;
        iArr[i2] = i;
    }

    public void writeByte(int i) {
        this.f27671os.write(i);
    }

    public void writeNBit(long j, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            write1Bit(((int) (j >> ((i - i2) - 1))) & 1);
        }
    }

    public void writeRemainingZero() {
        writeNBit(0L, 8 - this.curBit);
    }
}
