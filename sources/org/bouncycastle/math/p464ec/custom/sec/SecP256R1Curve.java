package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.math.p464ec.AbstractECLookupTable;
import org.bouncycastle.math.p464ec.ECConstants;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECLookupTable;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP256R1Curve */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP256R1Curve extends ECCurve.AbstractFp {
    private static final int SECP256R1_DEFAULT_COORDS = 2;
    protected SecP256R1Point infinity;

    /* renamed from: q */
    public static final BigInteger f27009q = SecP256R1FieldElement.f27013Q;
    private static final ECFieldElement[] SECP256R1_AFFINE_ZS = {new SecP256R1FieldElement(ECConstants.ONE)};

    public SecP256R1Curve() {
        super(f27009q);
        this.infinity = new SecP256R1Point(this, null, null);
        this.f26934a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("FFFFFFFF00000001000000000000000000000000FFFFFFFFFFFFFFFFFFFFFFFC")));
        this.f26935b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("5AC635D8AA3A93E7B3EBBD55769886BC651D06B0CC53B0F63BCE3C3E27D2604B")));
        this.order = new BigInteger(1, Hex.decodeStrict("FFFFFFFF00000000FFFFFFFFFFFFFFFFBCE6FAADA7179E84F3B9CAC2FC632551"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP256R1Curve();
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final int[] iArr = new int[i2 * 8 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat256.copy(((SecP256R1FieldElement) eCPoint.getRawXCoord()).f27014x, 0, iArr, i3);
            int i5 = i3 + 8;
            Nat256.copy(((SecP256R1FieldElement) eCPoint.getRawYCoord()).f27014x, 0, iArr, i5);
            i3 = i5 + 8;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecP256R1Curve.1
            private ECPoint createPoint(int[] iArr2, int[] iArr3) {
                return SecP256R1Curve.this.createRawPoint(new SecP256R1FieldElement(iArr2), new SecP256R1FieldElement(iArr3), SecP256R1Curve.SECP256R1_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public int getSize() {
                return i2;
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookup(int i6) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    int i9 = ((i8 ^ i6) - 1) >> 31;
                    for (int i10 = 0; i10 < 8; i10++) {
                        int i11 = create[i10];
                        int[] iArr2 = iArr;
                        create[i10] = i11 ^ (iArr2[i7 + i10] & i9);
                        create2[i10] = create2[i10] ^ (iArr2[(i7 + 8) + i10] & i9);
                    }
                    i7 += 16;
                }
                return createPoint(create, create2);
            }

            @Override // org.bouncycastle.math.p464ec.AbstractECLookupTable, org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookupVar(int i6) {
                int[] create = Nat256.create();
                int[] create2 = Nat256.create();
                int i7 = i6 * 8 * 2;
                for (int i8 = 0; i8 < 8; i8++) {
                    int[] iArr2 = iArr;
                    create[i8] = iArr2[i7 + i8];
                    create2[i8] = iArr2[i7 + 8 + i8];
                }
                return createPoint(create, create2);
            }
        };
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecP256R1Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecP256R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP256R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public int getFieldSize() {
        return f27009q.bitLength();
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return f27009q;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve.AbstractFp, org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement randomFieldElement(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256R1Field.random(secureRandom, create);
        return new SecP256R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve.AbstractFp, org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement randomFieldElementMult(SecureRandom secureRandom) {
        int[] create = Nat256.create();
        SecP256R1Field.randomMult(secureRandom, create);
        return new SecP256R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
