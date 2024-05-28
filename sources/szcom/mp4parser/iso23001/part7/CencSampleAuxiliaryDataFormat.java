package szcom.mp4parser.iso23001.part7;

import java.math.BigInteger;
import java.util.Arrays;
import szcom.coremedia.iso.Hex;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencSampleAuxiliaryDataFormat {

    /* renamed from: iv */
    public byte[] f27682iv = new byte[0];
    public Pair[] pairs = null;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    abstract class AbstractPair implements Pair {
        private AbstractPair() {
        }

        /* synthetic */ AbstractPair(CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat, AbstractPair abstractPair) {
            this();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return clear() == pair.clear() && encrypted() == pair.encrypted();
        }

        public String toString() {
            return "P(" + clear() + "|" + encrypted() + ")";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ByteBytePair extends AbstractPair {
        private byte clear;
        private byte encrypted;

        public ByteBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i;
            this.encrypted = (byte) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ByteIntPair extends AbstractPair {
        private byte clear;
        private int encrypted;

        public ByteIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i;
            this.encrypted = (int) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ByteLongPair extends AbstractPair {
        private byte clear;
        private long encrypted;

        public ByteLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i;
            this.encrypted = j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ByteShortPair extends AbstractPair {
        private byte clear;
        private short encrypted;

        public ByteShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (byte) i;
            this.encrypted = (short) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class IntBytePair extends AbstractPair {
        private int clear;
        private byte encrypted;

        public IntBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i;
            this.encrypted = (byte) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class IntIntPair extends AbstractPair {
        private int clear;
        private int encrypted;

        public IntIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i;
            this.encrypted = (int) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class IntLongPair extends AbstractPair {
        private int clear;
        private long encrypted;

        public IntLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i;
            this.encrypted = j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class IntShortPair extends AbstractPair {
        private int clear;
        private short encrypted;

        public IntShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = i;
            this.encrypted = (short) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface Pair {
        int clear();

        long encrypted();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ShortBytePair extends AbstractPair {
        private short clear;
        private byte encrypted;

        public ShortBytePair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i;
            this.encrypted = (byte) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ShortIntPair extends AbstractPair {
        private short clear;
        private int encrypted;

        public ShortIntPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i;
            this.encrypted = (int) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ShortLongPair extends AbstractPair {
        private short clear;
        private long encrypted;

        public ShortLongPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i;
            this.encrypted = j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class ShortShortPair extends AbstractPair {
        private short clear;
        private short encrypted;

        public ShortShortPair(int i, long j) {
            super(CencSampleAuxiliaryDataFormat.this, null);
            this.clear = (short) i;
            this.encrypted = (short) j;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public int clear() {
            return this.clear;
        }

        @Override // szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat.Pair
        public long encrypted() {
            return this.encrypted;
        }
    }

    public Pair createPair(int i, long j) {
        return i <= 127 ? j <= 127 ? new ByteBytePair(i, j) : j <= 32767 ? new ByteShortPair(i, j) : j <= 2147483647L ? new ByteIntPair(i, j) : new ByteLongPair(i, j) : i <= 32767 ? j <= 127 ? new ShortBytePair(i, j) : j <= 32767 ? new ShortShortPair(i, j) : j <= 2147483647L ? new ShortIntPair(i, j) : new ShortLongPair(i, j) : j <= 127 ? new IntBytePair(i, j) : j <= 32767 ? new IntShortPair(i, j) : j <= 2147483647L ? new IntIntPair(i, j) : new IntLongPair(i, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = (CencSampleAuxiliaryDataFormat) obj;
        if (new BigInteger(this.f27682iv).equals(new BigInteger(cencSampleAuxiliaryDataFormat.f27682iv))) {
            Pair[] pairArr = this.pairs;
            return pairArr == null ? cencSampleAuxiliaryDataFormat.pairs == null : Arrays.equals(pairArr, cencSampleAuxiliaryDataFormat.pairs);
        }
        return false;
    }

    public int getSize() {
        int length = this.f27682iv.length;
        Pair[] pairArr = this.pairs;
        return (pairArr == null || pairArr.length <= 0) ? length : length + 2 + (pairArr.length * 6);
    }

    public int hashCode() {
        byte[] bArr = this.f27682iv;
        int hashCode = (bArr != null ? Arrays.hashCode(bArr) : 0) * 31;
        Pair[] pairArr = this.pairs;
        return hashCode + (pairArr != null ? Arrays.hashCode(pairArr) : 0);
    }

    public String toString() {
        return "Entry{iv=" + Hex.encodeHex(this.f27682iv) + ", pairs=" + Arrays.toString(this.pairs) + '}';
    }
}
