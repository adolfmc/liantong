package org.bouncycastle.math.p464ec.custom.sec;

import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP256K1Point */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP256K1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP256K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP256K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        if (this == eCPoint) {
            return twice();
        }
        ECCurve curve = getCurve();
        SecP256K1FieldElement secP256K1FieldElement = (SecP256K1FieldElement) this.f26949x;
        SecP256K1FieldElement secP256K1FieldElement2 = (SecP256K1FieldElement) this.f26950y;
        SecP256K1FieldElement secP256K1FieldElement3 = (SecP256K1FieldElement) eCPoint.getXCoord();
        SecP256K1FieldElement secP256K1FieldElement4 = (SecP256K1FieldElement) eCPoint.getYCoord();
        SecP256K1FieldElement secP256K1FieldElement5 = (SecP256K1FieldElement) this.f26951zs[0];
        SecP256K1FieldElement secP256K1FieldElement6 = (SecP256K1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat256.createExt();
        int[] createExt2 = Nat256.createExt();
        int[] create = Nat256.create();
        int[] create2 = Nat256.create();
        int[] create3 = Nat256.create();
        boolean isOne = secP256K1FieldElement5.isOne();
        if (isOne) {
            iArr = secP256K1FieldElement3.f27008x;
            iArr2 = secP256K1FieldElement4.f27008x;
        } else {
            SecP256K1Field.square(secP256K1FieldElement5.f27008x, create2, createExt);
            SecP256K1Field.multiply(create2, secP256K1FieldElement3.f27008x, create, createExt);
            SecP256K1Field.multiply(create2, secP256K1FieldElement5.f27008x, create2, createExt);
            SecP256K1Field.multiply(create2, secP256K1FieldElement4.f27008x, create2, createExt);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = secP256K1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = secP256K1FieldElement.f27008x;
            iArr4 = secP256K1FieldElement2.f27008x;
        } else {
            SecP256K1Field.square(secP256K1FieldElement6.f27008x, create3, createExt);
            SecP256K1Field.multiply(create3, secP256K1FieldElement.f27008x, createExt2, createExt);
            SecP256K1Field.multiply(create3, secP256K1FieldElement6.f27008x, create3, createExt);
            SecP256K1Field.multiply(create3, secP256K1FieldElement2.f27008x, create3, createExt);
            iArr3 = createExt2;
            iArr4 = create3;
        }
        int[] create4 = Nat256.create();
        SecP256K1Field.subtract(iArr3, iArr, create4);
        SecP256K1Field.subtract(iArr4, iArr2, create);
        if (Nat256.isZero(create4)) {
            return Nat256.isZero(create) ? twice() : curve.getInfinity();
        }
        SecP256K1Field.square(create4, create2, createExt);
        int[] create5 = Nat256.create();
        SecP256K1Field.multiply(create2, create4, create5, createExt);
        SecP256K1Field.multiply(create2, iArr3, create2, createExt);
        SecP256K1Field.negate(create5, create5);
        Nat256.mul(iArr4, create5, createExt2);
        SecP256K1Field.reduce32(Nat256.addBothTo(create2, create2, create5), create5);
        SecP256K1FieldElement secP256K1FieldElement7 = new SecP256K1FieldElement(create3);
        SecP256K1Field.square(create, secP256K1FieldElement7.f27008x, createExt);
        SecP256K1Field.subtract(secP256K1FieldElement7.f27008x, create5, secP256K1FieldElement7.f27008x);
        SecP256K1FieldElement secP256K1FieldElement8 = new SecP256K1FieldElement(create5);
        SecP256K1Field.subtract(create2, secP256K1FieldElement7.f27008x, secP256K1FieldElement8.f27008x);
        SecP256K1Field.multiplyAddToExt(secP256K1FieldElement8.f27008x, create, createExt2);
        SecP256K1Field.reduce(createExt2, secP256K1FieldElement8.f27008x);
        SecP256K1FieldElement secP256K1FieldElement9 = new SecP256K1FieldElement(create4);
        if (!isOne) {
            SecP256K1Field.multiply(secP256K1FieldElement9.f27008x, secP256K1FieldElement5.f27008x, secP256K1FieldElement9.f27008x, createExt);
        }
        if (!isOne2) {
            SecP256K1Field.multiply(secP256K1FieldElement9.f27008x, secP256K1FieldElement6.f27008x, secP256K1FieldElement9.f27008x, createExt);
        }
        return new SecP256K1Point(curve, secP256K1FieldElement7, secP256K1FieldElement8, new ECFieldElement[]{secP256K1FieldElement9});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint detach() {
        return new SecP256K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP256K1Point(this.curve, this.f26949x, this.f26950y.negate(), this.f26951zs);
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint threeTimes() {
        return (isInfinity() || this.f26950y.isZero()) ? this : twice().add(this);
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        SecP256K1FieldElement secP256K1FieldElement = (SecP256K1FieldElement) this.f26950y;
        if (secP256K1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP256K1FieldElement secP256K1FieldElement2 = (SecP256K1FieldElement) this.f26949x;
        SecP256K1FieldElement secP256K1FieldElement3 = (SecP256K1FieldElement) this.f26951zs[0];
        int[] createExt = Nat256.createExt();
        int[] create = Nat256.create();
        SecP256K1Field.square(secP256K1FieldElement.f27008x, create, createExt);
        int[] create2 = Nat256.create();
        SecP256K1Field.square(create, create2, createExt);
        int[] create3 = Nat256.create();
        SecP256K1Field.square(secP256K1FieldElement2.f27008x, create3, createExt);
        SecP256K1Field.reduce32(Nat256.addBothTo(create3, create3, create3), create3);
        SecP256K1Field.multiply(create, secP256K1FieldElement2.f27008x, create, createExt);
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, create, 2, 0), create);
        int[] create4 = Nat256.create();
        SecP256K1Field.reduce32(Nat.shiftUpBits(8, create2, 3, 0, create4), create4);
        SecP256K1FieldElement secP256K1FieldElement4 = new SecP256K1FieldElement(create2);
        SecP256K1Field.square(create3, secP256K1FieldElement4.f27008x, createExt);
        SecP256K1Field.subtract(secP256K1FieldElement4.f27008x, create, secP256K1FieldElement4.f27008x);
        SecP256K1Field.subtract(secP256K1FieldElement4.f27008x, create, secP256K1FieldElement4.f27008x);
        SecP256K1FieldElement secP256K1FieldElement5 = new SecP256K1FieldElement(create);
        SecP256K1Field.subtract(create, secP256K1FieldElement4.f27008x, secP256K1FieldElement5.f27008x);
        SecP256K1Field.multiply(secP256K1FieldElement5.f27008x, create3, secP256K1FieldElement5.f27008x, createExt);
        SecP256K1Field.subtract(secP256K1FieldElement5.f27008x, create4, secP256K1FieldElement5.f27008x);
        SecP256K1FieldElement secP256K1FieldElement6 = new SecP256K1FieldElement(create3);
        SecP256K1Field.twice(secP256K1FieldElement.f27008x, secP256K1FieldElement6.f27008x);
        if (!secP256K1FieldElement3.isOne()) {
            SecP256K1Field.multiply(secP256K1FieldElement6.f27008x, secP256K1FieldElement3.f27008x, secP256K1FieldElement6.f27008x, createExt);
        }
        return new SecP256K1Point(curve, secP256K1FieldElement4, secP256K1FieldElement5, new ECFieldElement[]{secP256K1FieldElement6});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f26950y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
