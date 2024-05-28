package org.bouncycastle.math.p464ec;

import java.math.BigInteger;
import java.util.Random;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Integers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.ECFieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ECFieldElement implements ECConstants {

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$AbstractF2m */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractF2m extends ECFieldElement {
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v3, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public ECFieldElement halfTrace() {
            int fieldSize = getFieldSize();
            if ((fieldSize & 1) != 0) {
                int i = (fieldSize + 1) >>> 1;
                int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(i);
                ECFieldElement eCFieldElement = this;
                int i2 = 1;
                while (numberOfLeadingZeros > 0) {
                    eCFieldElement = eCFieldElement.squarePow(i2 << 1).add(eCFieldElement);
                    numberOfLeadingZeros--;
                    i2 = i >>> numberOfLeadingZeros;
                    if ((i2 & 1) != 0) {
                        eCFieldElement = eCFieldElement.squarePow(2).add(this);
                    }
                }
                return eCFieldElement;
            }
            throw new IllegalStateException("Half-trace only defined for odd m");
        }

        public boolean hasFastTrace() {
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [org.bouncycastle.math.ec.ECFieldElement] */
        /* JADX WARN: Type inference failed for: r3v3 */
        /* JADX WARN: Type inference failed for: r4v2, types: [org.bouncycastle.math.ec.ECFieldElement] */
        public int trace() {
            int fieldSize = getFieldSize();
            int numberOfLeadingZeros = 31 - Integers.numberOfLeadingZeros(fieldSize);
            ECFieldElement eCFieldElement = this;
            int i = 1;
            while (numberOfLeadingZeros > 0) {
                eCFieldElement = eCFieldElement.squarePow(i).add(eCFieldElement);
                numberOfLeadingZeros--;
                i = fieldSize >>> numberOfLeadingZeros;
                if ((i & 1) != 0) {
                    eCFieldElement = eCFieldElement.square().add(this);
                }
            }
            if (eCFieldElement.isZero()) {
                return 0;
            }
            if (eCFieldElement.isOne()) {
                return 1;
            }
            throw new IllegalStateException("Internal error in trace calculation");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$AbstractFp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractFp extends ECFieldElement {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$F2m */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class F2m extends AbstractF2m {
        public static final int GNB = 1;
        public static final int PPB = 3;
        public static final int TPB = 2;

        /* renamed from: ks */
        private int[] f26943ks;

        /* renamed from: m */
        private int f26944m;
        private int representation;

        /* renamed from: x */
        LongArray f26945x;

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(int i, int[] iArr, LongArray longArray) {
            this.f26944m = i;
            this.representation = iArr.length == 1 ? 2 : 3;
            this.f26943ks = iArr;
            this.f26945x = longArray;
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            LongArray longArray = (LongArray) this.f26945x.clone();
            longArray.addShiftedByWords(((F2m) eCFieldElement).f26945x, 0);
            return new F2m(this.f26944m, this.f26943ks, longArray);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement addOne() {
            return new F2m(this.f26944m, this.f26943ks, this.f26945x.addOne());
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public int bitLength() {
            return this.f26945x.degree();
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return multiply(eCFieldElement.invert());
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof F2m) {
                F2m f2m = (F2m) obj;
                return this.f26944m == f2m.f26944m && this.representation == f2m.representation && Arrays.areEqual(this.f26943ks, f2m.f26943ks) && this.f26945x.equals(f2m.f26945x);
            }
            return false;
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public String getFieldName() {
            return "F2m";
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public int getFieldSize() {
            return this.f26944m;
        }

        public int getK1() {
            return this.f26943ks[0];
        }

        public int getK2() {
            int[] iArr = this.f26943ks;
            if (iArr.length >= 2) {
                return iArr[1];
            }
            return 0;
        }

        public int getK3() {
            int[] iArr = this.f26943ks;
            if (iArr.length >= 3) {
                return iArr[2];
            }
            return 0;
        }

        public int getM() {
            return this.f26944m;
        }

        public int getRepresentation() {
            return this.representation;
        }

        public int hashCode() {
            return (this.f26945x.hashCode() ^ this.f26944m) ^ Arrays.hashCode(this.f26943ks);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement invert() {
            int i = this.f26944m;
            int[] iArr = this.f26943ks;
            return new F2m(i, iArr, this.f26945x.modInverse(i, iArr));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public boolean isOne() {
            return this.f26945x.isOne();
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public boolean isZero() {
            return this.f26945x.isZero();
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            int i = this.f26944m;
            int[] iArr = this.f26943ks;
            return new F2m(i, iArr, this.f26945x.modMultiply(((F2m) eCFieldElement).f26945x, i, iArr));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            LongArray longArray = this.f26945x;
            LongArray longArray2 = ((F2m) eCFieldElement).f26945x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f26945x;
            LongArray longArray4 = ((F2m) eCFieldElement3).f26945x;
            LongArray multiply = longArray.multiply(longArray2, this.f26944m, this.f26943ks);
            LongArray multiply2 = longArray3.multiply(longArray4, this.f26944m, this.f26943ks);
            if (multiply == longArray || multiply == longArray2) {
                multiply = (LongArray) multiply.clone();
            }
            multiply.addShiftedByWords(multiply2, 0);
            multiply.reduce(this.f26944m, this.f26943ks);
            return new F2m(this.f26944m, this.f26943ks, multiply);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement negate() {
            return this;
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement sqrt() {
            return (this.f26945x.isZero() || this.f26945x.isOne()) ? this : squarePow(this.f26944m - 1);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement square() {
            int i = this.f26944m;
            int[] iArr = this.f26943ks;
            return new F2m(i, iArr, this.f26945x.modSquare(i, iArr));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            return squarePlusProduct(eCFieldElement, eCFieldElement2);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            LongArray longArray = this.f26945x;
            LongArray longArray2 = ((F2m) eCFieldElement).f26945x;
            LongArray longArray3 = ((F2m) eCFieldElement2).f26945x;
            LongArray square = longArray.square(this.f26944m, this.f26943ks);
            LongArray multiply = longArray2.multiply(longArray3, this.f26944m, this.f26943ks);
            if (square == longArray) {
                square = (LongArray) square.clone();
            }
            square.addShiftedByWords(multiply, 0);
            square.reduce(this.f26944m, this.f26943ks);
            return new F2m(this.f26944m, this.f26943ks, square);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement squarePow(int i) {
            if (i < 1) {
                return this;
            }
            int i2 = this.f26944m;
            int[] iArr = this.f26943ks;
            return new F2m(i2, iArr, this.f26945x.modSquareN(i, i2, iArr));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public boolean testBitZero() {
            return this.f26945x.testBitZero();
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f26945x.toBigInteger();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECFieldElement$Fp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13319Fp extends AbstractFp {

        /* renamed from: q */
        BigInteger f26946q;

        /* renamed from: r */
        BigInteger f26947r;

        /* renamed from: x */
        BigInteger f26948x;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C13319Fp(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            this.f26946q = bigInteger;
            this.f26947r = bigInteger2;
            this.f26948x = bigInteger3;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static BigInteger calculateResidue(BigInteger bigInteger) {
            int bitLength = bigInteger.bitLength();
            if (bitLength < 96 || bigInteger.shiftRight(bitLength - 64).longValue() != -1) {
                return null;
            }
            return ONE.shiftLeft(bitLength).subtract(bigInteger);
        }

        private ECFieldElement checkSqrt(ECFieldElement eCFieldElement) {
            if (eCFieldElement.square().equals(this)) {
                return eCFieldElement;
            }
            return null;
        }

        private BigInteger[] lucasSequence(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
            int bitLength = bigInteger3.bitLength();
            int lowestSetBit = bigInteger3.getLowestSetBit();
            BigInteger bigInteger4 = ECConstants.ONE;
            BigInteger bigInteger5 = ECConstants.TWO;
            BigInteger bigInteger6 = ECConstants.ONE;
            BigInteger bigInteger7 = ECConstants.ONE;
            BigInteger bigInteger8 = bigInteger;
            for (int i = bitLength - 1; i >= lowestSetBit + 1; i--) {
                bigInteger6 = modMult(bigInteger6, bigInteger7);
                if (bigInteger3.testBit(i)) {
                    bigInteger7 = modMult(bigInteger6, bigInteger2);
                    bigInteger4 = modMult(bigInteger4, bigInteger8);
                    bigInteger5 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger8 = modReduce(bigInteger8.multiply(bigInteger8).subtract(bigInteger7.shiftLeft(1)));
                } else {
                    bigInteger4 = modReduce(bigInteger4.multiply(bigInteger5).subtract(bigInteger6));
                    BigInteger modReduce = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(bigInteger6)));
                    bigInteger5 = modReduce(bigInteger5.multiply(bigInteger5).subtract(bigInteger6.shiftLeft(1)));
                    bigInteger8 = modReduce;
                    bigInteger7 = bigInteger6;
                }
            }
            BigInteger modMult = modMult(bigInteger6, bigInteger7);
            BigInteger modMult2 = modMult(modMult, bigInteger2);
            BigInteger modReduce2 = modReduce(bigInteger4.multiply(bigInteger5).subtract(modMult));
            BigInteger modReduce3 = modReduce(bigInteger8.multiply(bigInteger5).subtract(bigInteger.multiply(modMult)));
            BigInteger modMult3 = modMult(modMult, modMult2);
            BigInteger bigInteger9 = modReduce3;
            for (int i2 = 1; i2 <= lowestSetBit; i2++) {
                modReduce2 = modMult(modReduce2, bigInteger9);
                bigInteger9 = modReduce(bigInteger9.multiply(bigInteger9).subtract(modMult3.shiftLeft(1)));
                modMult3 = modMult(modMult3, modMult3);
            }
            return new BigInteger[]{modReduce2, bigInteger9};
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement add(ECFieldElement eCFieldElement) {
            return new C13319Fp(this.f26946q, this.f26947r, modAdd(this.f26948x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement addOne() {
            BigInteger add = this.f26948x.add(ECConstants.ONE);
            if (add.compareTo(this.f26946q) == 0) {
                add = ECConstants.ZERO;
            }
            return new C13319Fp(this.f26946q, this.f26947r, add);
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement divide(ECFieldElement eCFieldElement) {
            return new C13319Fp(this.f26946q, this.f26947r, modMult(this.f26948x, modInverse(eCFieldElement.toBigInteger())));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof C13319Fp) {
                C13319Fp c13319Fp = (C13319Fp) obj;
                return this.f26946q.equals(c13319Fp.f26946q) && this.f26948x.equals(c13319Fp.f26948x);
            }
            return false;
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public String getFieldName() {
            return "Fp";
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public int getFieldSize() {
            return this.f26946q.bitLength();
        }

        public BigInteger getQ() {
            return this.f26946q;
        }

        public int hashCode() {
            return this.f26946q.hashCode() ^ this.f26948x.hashCode();
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement invert() {
            return new C13319Fp(this.f26946q, this.f26947r, modInverse(this.f26948x));
        }

        protected BigInteger modAdd(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger add = bigInteger.add(bigInteger2);
            return add.compareTo(this.f26946q) >= 0 ? add.subtract(this.f26946q) : add;
        }

        protected BigInteger modDouble(BigInteger bigInteger) {
            BigInteger shiftLeft = bigInteger.shiftLeft(1);
            return shiftLeft.compareTo(this.f26946q) >= 0 ? shiftLeft.subtract(this.f26946q) : shiftLeft;
        }

        protected BigInteger modHalf(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f26946q.add(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modHalfAbs(BigInteger bigInteger) {
            if (bigInteger.testBit(0)) {
                bigInteger = this.f26946q.subtract(bigInteger);
            }
            return bigInteger.shiftRight(1);
        }

        protected BigInteger modInverse(BigInteger bigInteger) {
            return BigIntegers.modOddInverse(this.f26946q, bigInteger);
        }

        protected BigInteger modMult(BigInteger bigInteger, BigInteger bigInteger2) {
            return modReduce(bigInteger.multiply(bigInteger2));
        }

        protected BigInteger modReduce(BigInteger bigInteger) {
            if (this.f26947r != null) {
                boolean z = bigInteger.signum() < 0;
                if (z) {
                    bigInteger = bigInteger.abs();
                }
                int bitLength = this.f26946q.bitLength();
                boolean equals = this.f26947r.equals(ECConstants.ONE);
                while (bigInteger.bitLength() > bitLength + 1) {
                    BigInteger shiftRight = bigInteger.shiftRight(bitLength);
                    BigInteger subtract = bigInteger.subtract(shiftRight.shiftLeft(bitLength));
                    if (!equals) {
                        shiftRight = shiftRight.multiply(this.f26947r);
                    }
                    bigInteger = shiftRight.add(subtract);
                }
                while (bigInteger.compareTo(this.f26946q) >= 0) {
                    bigInteger = bigInteger.subtract(this.f26946q);
                }
                return (!z || bigInteger.signum() == 0) ? bigInteger : this.f26946q.subtract(bigInteger);
            }
            return bigInteger.mod(this.f26946q);
        }

        protected BigInteger modSubtract(BigInteger bigInteger, BigInteger bigInteger2) {
            BigInteger subtract = bigInteger.subtract(bigInteger2);
            return subtract.signum() < 0 ? subtract.add(this.f26946q) : subtract;
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiply(ECFieldElement eCFieldElement) {
            return new C13319Fp(this.f26946q, this.f26947r, modMult(this.f26948x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f26948x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C13319Fp(this.f26946q, this.f26947r, modReduce(bigInteger.multiply(bigInteger2).subtract(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
            BigInteger bigInteger = this.f26948x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            BigInteger bigInteger4 = eCFieldElement3.toBigInteger();
            return new C13319Fp(this.f26946q, this.f26947r, modReduce(bigInteger.multiply(bigInteger2).add(bigInteger3.multiply(bigInteger4))));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement negate() {
            if (this.f26948x.signum() == 0) {
                return this;
            }
            BigInteger bigInteger = this.f26946q;
            return new C13319Fp(bigInteger, this.f26947r, bigInteger.subtract(this.f26948x));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement sqrt() {
            if (isZero() || isOne()) {
                return this;
            }
            if (!this.f26946q.testBit(0)) {
                throw new RuntimeException("not done yet");
            }
            if (this.f26946q.testBit(1)) {
                BigInteger add = this.f26946q.shiftRight(2).add(ECConstants.ONE);
                BigInteger bigInteger = this.f26946q;
                return checkSqrt(new C13319Fp(bigInteger, this.f26947r, this.f26948x.modPow(add, bigInteger)));
            } else if (this.f26946q.testBit(2)) {
                BigInteger modPow = this.f26948x.modPow(this.f26946q.shiftRight(3), this.f26946q);
                BigInteger modMult = modMult(modPow, this.f26948x);
                if (modMult(modMult, modPow).equals(ECConstants.ONE)) {
                    return checkSqrt(new C13319Fp(this.f26946q, this.f26947r, modMult));
                }
                return checkSqrt(new C13319Fp(this.f26946q, this.f26947r, modMult(modMult, ECConstants.TWO.modPow(this.f26946q.shiftRight(2), this.f26946q))));
            } else {
                BigInteger shiftRight = this.f26946q.shiftRight(1);
                if (!this.f26948x.modPow(shiftRight, this.f26946q).equals(ECConstants.ONE)) {
                    return null;
                }
                BigInteger bigInteger2 = this.f26948x;
                BigInteger modDouble = modDouble(modDouble(bigInteger2));
                BigInteger add2 = shiftRight.add(ECConstants.ONE);
                BigInteger subtract = this.f26946q.subtract(ECConstants.ONE);
                Random random = new Random();
                while (true) {
                    BigInteger bigInteger3 = new BigInteger(this.f26946q.bitLength(), random);
                    if (bigInteger3.compareTo(this.f26946q) < 0 && modReduce(bigInteger3.multiply(bigInteger3).subtract(modDouble)).modPow(shiftRight, this.f26946q).equals(subtract)) {
                        BigInteger[] lucasSequence = lucasSequence(bigInteger3, bigInteger2, add2);
                        BigInteger bigInteger4 = lucasSequence[0];
                        BigInteger bigInteger5 = lucasSequence[1];
                        if (modMult(bigInteger5, bigInteger5).equals(modDouble)) {
                            return new C13319Fp(this.f26946q, this.f26947r, modHalfAbs(bigInteger5));
                        }
                        if (!bigInteger4.equals(ECConstants.ONE) && !bigInteger4.equals(subtract)) {
                            return null;
                        }
                    }
                }
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement square() {
            BigInteger bigInteger = this.f26946q;
            BigInteger bigInteger2 = this.f26947r;
            BigInteger bigInteger3 = this.f26948x;
            return new C13319Fp(bigInteger, bigInteger2, modMult(bigInteger3, bigInteger3));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f26948x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C13319Fp(this.f26946q, this.f26947r, modReduce(bigInteger.multiply(bigInteger).subtract(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            BigInteger bigInteger = this.f26948x;
            BigInteger bigInteger2 = eCFieldElement.toBigInteger();
            BigInteger bigInteger3 = eCFieldElement2.toBigInteger();
            return new C13319Fp(this.f26946q, this.f26947r, modReduce(bigInteger.multiply(bigInteger).add(bigInteger2.multiply(bigInteger3))));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public ECFieldElement subtract(ECFieldElement eCFieldElement) {
            return new C13319Fp(this.f26946q, this.f26947r, modSubtract(this.f26948x, eCFieldElement.toBigInteger()));
        }

        @Override // org.bouncycastle.math.p464ec.ECFieldElement
        public BigInteger toBigInteger() {
            return this.f26948x;
        }
    }

    public abstract ECFieldElement add(ECFieldElement eCFieldElement);

    public abstract ECFieldElement addOne();

    public int bitLength() {
        return toBigInteger().bitLength();
    }

    public abstract ECFieldElement divide(ECFieldElement eCFieldElement);

    public byte[] getEncoded() {
        return BigIntegers.asUnsignedByteArray((getFieldSize() + 7) / 8, toBigInteger());
    }

    public abstract String getFieldName();

    public abstract int getFieldSize();

    public abstract ECFieldElement invert();

    public boolean isOne() {
        return bitLength() == 1;
    }

    public boolean isZero() {
        return toBigInteger().signum() == 0;
    }

    public abstract ECFieldElement multiply(ECFieldElement eCFieldElement);

    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).subtract(eCFieldElement2.multiply(eCFieldElement3));
    }

    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiply(eCFieldElement).add(eCFieldElement2.multiply(eCFieldElement3));
    }

    public abstract ECFieldElement negate();

    public abstract ECFieldElement sqrt();

    public abstract ECFieldElement square();

    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().subtract(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return square().add(eCFieldElement.multiply(eCFieldElement2));
    }

    public ECFieldElement squarePow(int i) {
        ECFieldElement eCFieldElement = this;
        for (int i2 = 0; i2 < i; i2++) {
            eCFieldElement = eCFieldElement.square();
        }
        return eCFieldElement;
    }

    public abstract ECFieldElement subtract(ECFieldElement eCFieldElement);

    public boolean testBitZero() {
        return toBigInteger().testBit(0);
    }

    public abstract BigInteger toBigInteger();

    public String toString() {
        return toBigInteger().toString(16);
    }
}
