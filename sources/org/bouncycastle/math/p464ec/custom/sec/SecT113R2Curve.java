package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.AbstractECLookupTable;
import org.bouncycastle.math.p464ec.ECConstants;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECLookupTable;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT113R2Curve */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecT113R2Curve extends ECCurve.AbstractF2m {
    private static final ECFieldElement[] SECT113R2_AFFINE_ZS = {new SecT113FieldElement(ECConstants.ONE)};
    private static final int SECT113R2_DEFAULT_COORDS = 6;
    protected SecT113R2Point infinity;

    public SecT113R2Curve() {
        super(113, 9, 0, 0);
        this.infinity = new SecT113R2Point(this, null, null);
        this.f26934a = fromBigInteger(new BigInteger(1, Hex.decodeStrict("00689918DBEC7E5A0DD6DFC0AA55C7")));
        this.f26935b = fromBigInteger(new BigInteger(1, Hex.decodeStrict("0095E9A9EC9B297BD4BF36E059184F")));
        this.order = new BigInteger(1, Hex.decodeStrict("010000000000000108789B2496AF93"));
        this.cofactor = BigInteger.valueOf(2L);
        this.coord = 6;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecT113R2Curve();
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, final int i2) {
        final long[] jArr = new long[i2 * 2 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawXCoord()).f27024x, 0, jArr, i3);
            int i5 = i3 + 2;
            Nat128.copy64(((SecT113FieldElement) eCPoint.getRawYCoord()).f27024x, 0, jArr, i5);
            i3 = i5 + 2;
        }
        return new AbstractECLookupTable() { // from class: org.bouncycastle.math.ec.custom.sec.SecT113R2Curve.1
            private ECPoint createPoint(long[] jArr2, long[] jArr3) {
                return SecT113R2Curve.this.createRawPoint(new SecT113FieldElement(jArr2), new SecT113FieldElement(jArr3), SecT113R2Curve.SECT113R2_AFFINE_ZS);
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public int getSize() {
                return i2;
            }

            @Override // org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookup(int i6) {
                long[] create64 = Nat128.create64();
                long[] create642 = Nat128.create64();
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    long j = ((i8 ^ i6) - 1) >> 31;
                    for (int i9 = 0; i9 < 2; i9++) {
                        long j2 = create64[i9];
                        long[] jArr2 = jArr;
                        create64[i9] = j2 ^ (jArr2[i7 + i9] & j);
                        create642[i9] = create642[i9] ^ (jArr2[(i7 + 2) + i9] & j);
                    }
                    i7 += 4;
                }
                return createPoint(create64, create642);
            }

            @Override // org.bouncycastle.math.p464ec.AbstractECLookupTable, org.bouncycastle.math.p464ec.ECLookupTable
            public ECPoint lookupVar(int i6) {
                long[] create64 = Nat128.create64();
                long[] create642 = Nat128.create64();
                int i7 = i6 * 2 * 2;
                for (int i8 = 0; i8 < 2; i8++) {
                    long[] jArr2 = jArr;
                    create64[i8] = jArr2[i7 + i8];
                    create642[i8] = jArr2[i7 + 2 + i8];
                }
                return createPoint(create64, create642);
            }
        };
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return new SecT113R2Point(this, eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        return new SecT113R2Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecT113FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public int getFieldSize() {
        return 113;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public int getK1() {
        return 9;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 113;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve.AbstractF2m
    public boolean isKoblitz() {
        return false;
    }

    public boolean isTrinomial() {
        return true;
    }

    @Override // org.bouncycastle.math.p464ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 6;
    }
}
