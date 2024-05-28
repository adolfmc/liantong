package szcom.googlecode.mp4parser.h264.read;

import java.io.InputStream;
import szcom.googlecode.mp4parser.h264.CharCache;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class BitstreamReader {
    protected static int bitsRead;
    private int curByte;
    protected CharCache debugBits = new CharCache(50);

    /* renamed from: is */
    private InputStream f27670is;
    int nBit;
    private int nextByte;

    public BitstreamReader(InputStream inputStream) {
        this.f27670is = inputStream;
        this.curByte = inputStream.read();
        this.nextByte = inputStream.read();
    }

    private void advance() {
        this.curByte = this.nextByte;
        this.nextByte = this.f27670is.read();
        this.nBit = 0;
    }

    public void close() {
    }

    public long getBitPosition() {
        return (bitsRead * 8) + (this.nBit % 8);
    }

    public int getCurBit() {
        return this.nBit;
    }

    public boolean isByteAligned() {
        return this.nBit % 8 == 0;
    }

    public boolean moreRBSPData() {
        if (this.nBit == 8) {
            advance();
        }
        int i = 1 << ((8 - this.nBit) - 1);
        return (this.curByte == -1 || (this.nextByte == -1 && ((((i << 1) - 1) & this.curByte) == i))) ? false : true;
    }

    public int peakNextBits(int i) {
        if (i <= 8) {
            if (this.nBit == 8) {
                advance();
                if (this.curByte == -1) {
                    return -1;
                }
            }
            int i2 = this.nBit;
            int[] iArr = new int[16 - i2];
            int i3 = 0;
            while (i2 < 8) {
                iArr[i3] = (this.curByte >> (7 - i2)) & 1;
                i2++;
                i3++;
            }
            int i4 = 0;
            while (i4 < 8) {
                iArr[i3] = (this.nextByte >> (7 - i4)) & 1;
                i4++;
                i3++;
            }
            int i5 = 0;
            for (int i6 = 0; i6 < i; i6++) {
                i5 = (i5 << 1) | iArr[i6];
            }
            return i5;
        }
        throw new IllegalArgumentException("N should be less then 8");
    }

    public int read1Bit() {
        if (this.nBit == 8) {
            advance();
            if (this.curByte == -1) {
                return -1;
            }
        }
        int i = this.curByte;
        int i2 = this.nBit;
        int i3 = (i >> (7 - i2)) & 1;
        this.nBit = i2 + 1;
        this.debugBits.append(i3 == 0 ? '0' : '1');
        bitsRead++;
        return i3;
    }

    public boolean readBool() {
        return read1Bit() == 1;
    }

    public int readByte() {
        if (this.nBit > 0) {
            advance();
        }
        int i = this.curByte;
        advance();
        return i;
    }

    public long readNBit(int i) {
        if (i <= 64) {
            long j = 0;
            for (int i2 = 0; i2 < i; i2++) {
                j = (j << 1) | read1Bit();
            }
            return j;
        }
        throw new IllegalArgumentException("Can not readByte more then 64 bit");
    }

    public long readRemainingByte() {
        return readNBit(8 - this.nBit);
    }
}
