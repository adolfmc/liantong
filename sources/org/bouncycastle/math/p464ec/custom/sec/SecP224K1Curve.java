package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p464ec.AbstractECLookupTable;
import org.bouncycastle.math.p464ec.ECConstants;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECLookupTable;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224K1Curve */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP224K1Curve extends ECCurve.AbstractFp {
    private static final int SECP224K1_DEFAULT_COORDS = 2;
    protected SecP224K1Point infinity;

    /* renamed from: q */
    public static final BigInteger f26993q = SecP224K1FieldElement.f26996Q;
    private static final ECFieldElement[] SECP224K1_AFFINE_ZS = {new SecP224K1FieldElement(ECConstants.ONE)};

    public SecP224K1Curve() {
        super(f26993q);
        this.infinity = new SecP224K1Point(this, null, null);
        this.f26934a = fromBigInteger(ECConstants.ZERO);
        this.f26935b = fromBigInteger(BigInteger.valueOf(5L));
        this.order = new BigInteger(1, Hex.decodeStrict("010000000000000000000000000001DCE8D2EC6184CAF0A971769FB1F7"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP224K1Curve();
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[i2 * 7 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat224.copy(((SecP224K1FieldElement) eCPoint.getRawXCoord()).f26997x, 0, iArr, i3);
            int i5 = i3 + 7;
            Nat224.copy(((SecP224K1FieldElement) eCPoint.getRawYCoord()).f26997x, 0, iArr, i5);
            i3 = i5 + 7;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP224K1Curve.1
            private ECPoint createPoint(int[] iArr2, int[] iArr3) {
                return SecP224K1Curve.this.createRawPoint(new SecP224K1FieldElement(iArr2), new SecP224K1FieldElement(iArr3), SecP224K1Curve.SECP224K1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public int getSize() {
                return i2;
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookup(int i6) {
                int[] create = Nat224.create();
                int[] create2 = Nat224.create();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = ((i8 ^ i6) - 1) >> 31;
                    for (int i10 = 0; i10 < 7; i10++) {
                        int i11 = create[i10];
                        int[] iArr2 = iArr;
                        create[i10] = i11 ^ (iArr2[i7 + i10] & i9);
                        create2[i10] = create2[i10] ^ (iArr2[(i7 + 7) + i10] & i9);
                    }
                    i7 += 14;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p464ec.AbstractECLookupTable, org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookupVar(int i6) {
                int[] create = Nat224.create();
                int[] create2 = Nat224.create();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = ((i8 ^ i6) - 1) >> 31;
                    for (int i10 = 0; i10 < 7; i10++) {
                        int i11 = create[i10];
                        int[] iArr2 = iArr;
                        create[i10] = i11 ^ (iArr2[i7 + i10] & i9);
                        create2[i10] = create2[i10] ^ (iArr2[(i7 + 7) + i10] & i9);
                    }
                    i7 += 14;
                }
                return createPoint(create, create2);
            }
        };
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP224K1Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP224K1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP224K1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public int getFieldSize() {
        return f26993q.bitLength();
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f26993q;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve.AbstractFp, org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat224.create();
        SecP224K1Field.random(secureRandom, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve.AbstractFp, org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat224.create();
        SecP224K1Field.randomMult(secureRandom, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
