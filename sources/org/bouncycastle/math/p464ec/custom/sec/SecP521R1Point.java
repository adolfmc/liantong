package org.bouncycastle.math.p464ec.custom.sec;

import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1Point */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP521R1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP521R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f26949x;
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f26950y;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) eCPoint.getXCoord();
        SecP521R1FieldElement secP521R1FieldElement4 = (SecP521R1FieldElement) eCPoint.getYCoord();
        SecP521R1FieldElement secP521R1FieldElement5 = (SecP521R1FieldElement) this.f26951zs[0];
        SecP521R1FieldElement secP521R1FieldElement6 = (SecP521R1FieldElement) eCPoint.getZCoord(0);
        int[] create = Nat.create(33);
        int i = 17;
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        int[] create4 = Nat.create(17);
        int[] create5 = Nat.create(17);
        boolean isOne = secP521R1FieldElement5.isOne();
        if (isOne) {
            iArr = secP521R1FieldElement3.f27023x;
            iArr2 = secP521R1FieldElement4.f27023x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement5.f27023x, create4, create);
            SecP521R1Field.multiply(create4, secP521R1FieldElement3.f27023x, create3, create);
            SecP521R1Field.multiply(create4, secP521R1FieldElement5.f27023x, create4, create);
            SecP521R1Field.multiply(create4, secP521R1FieldElement4.f27023x, create4, create);
            iArr = create3;
            iArr2 = create4;
        }
        boolean isOne2 = secP521R1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = secP521R1FieldElement.f27023x;
            iArr4 = secP521R1FieldElement2.f27023x;
        } else {
            SecP521R1Field.square(secP521R1FieldElement6.f27023x, create5, create);
            SecP521R1Field.multiply(create5, secP521R1FieldElement.f27023x, create2, create);
            SecP521R1Field.multiply(create5, secP521R1FieldElement6.f27023x, create5, create);
            SecP521R1Field.multiply(create5, secP521R1FieldElement2.f27023x, create5, create);
            iArr3 = create2;
            iArr4 = create5;
            i = 17;
        }
        int[] create6 = Nat.create(i);
        SecP521R1Field.subtract(iArr3, iArr, create6);
        SecP521R1Field.subtract(iArr4, iArr2, create3);
        if (Nat.isZero(i, create6)) {
            return Nat.isZero(i, create3) ? twice() : curve.getInfinity();
        }
        SecP521R1Field.square(create6, create4, create);
        int[] create7 = Nat.create(i);
        SecP521R1Field.multiply(create4, create6, create7, create);
        SecP521R1Field.multiply(create4, iArr3, create4, create);
        SecP521R1Field.multiply(iArr4, create7, create2, create);
        SecP521R1FieldElement secP521R1FieldElement7 = new SecP521R1FieldElement(create5);
        SecP521R1Field.square(create3, secP521R1FieldElement7.f27023x, create);
        SecP521R1Field.add(secP521R1FieldElement7.f27023x, create7, secP521R1FieldElement7.f27023x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f27023x, create4, secP521R1FieldElement7.f27023x);
        SecP521R1Field.subtract(secP521R1FieldElement7.f27023x, create4, secP521R1FieldElement7.f27023x);
        SecP521R1FieldElement secP521R1FieldElement8 = new SecP521R1FieldElement(create7);
        SecP521R1Field.subtract(create4, secP521R1FieldElement7.f27023x, secP521R1FieldElement8.f27023x);
        SecP521R1Field.multiply(secP521R1FieldElement8.f27023x, create3, create3, create);
        SecP521R1Field.subtract(create3, create2, secP521R1FieldElement8.f27023x);
        SecP521R1FieldElement secP521R1FieldElement9 = new SecP521R1FieldElement(create6);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f27023x, secP521R1FieldElement5.f27023x, secP521R1FieldElement9.f27023x, create);
        }
        if (!isOne2) {
            SecP521R1Field.multiply(secP521R1FieldElement9.f27023x, secP521R1FieldElement6.f27023x, secP521R1FieldElement9.f27023x, create);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement7, secP521R1FieldElement8, new ECFieldElement[]{secP521R1FieldElement9});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint detach() {
        return new SecP521R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    protected ECFieldElement doubleProductFromSquares(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3, ECFieldElement eCFieldElement4) {
        return eCFieldElement.add(eCFieldElement2).square().subtract(eCFieldElement3).subtract(eCFieldElement4);
    }

    protected ECFieldElement eight(ECFieldElement eCFieldElement) {
        return four(two(eCFieldElement));
    }

    protected ECFieldElement four(ECFieldElement eCFieldElement) {
        return two(two(eCFieldElement));
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP521R1Point(this.curve, this.f26949x, this.f26950y.negate(), this.f26951zs);
    }

    protected ECFieldElement three(ECFieldElement eCFieldElement) {
        return two(eCFieldElement).add(eCFieldElement);
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
        SecP521R1FieldElement secP521R1FieldElement = (SecP521R1FieldElement) this.f26950y;
        if (secP521R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP521R1FieldElement secP521R1FieldElement2 = (SecP521R1FieldElement) this.f26949x;
        SecP521R1FieldElement secP521R1FieldElement3 = (SecP521R1FieldElement) this.f26951zs[0];
        int[] create = Nat.create(33);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        int[] create4 = Nat.create(17);
        SecP521R1Field.square(secP521R1FieldElement.f27023x, create4, create);
        int[] create5 = Nat.create(17);
        SecP521R1Field.square(create4, create5, create);
        boolean isOne = secP521R1FieldElement3.isOne();
        int[] iArr = secP521R1FieldElement3.f27023x;
        if (!isOne) {
            SecP521R1Field.square(secP521R1FieldElement3.f27023x, create3, create);
            iArr = create3;
        }
        SecP521R1Field.subtract(secP521R1FieldElement2.f27023x, iArr, create2);
        SecP521R1Field.add(secP521R1FieldElement2.f27023x, iArr, create3);
        SecP521R1Field.multiply(create3, create2, create3, create);
        Nat.addBothTo(17, create3, create3, create3);
        SecP521R1Field.reduce23(create3);
        SecP521R1Field.multiply(create4, secP521R1FieldElement2.f27023x, create4, create);
        Nat.shiftUpBits(17, create4, 2, 0);
        SecP521R1Field.reduce23(create4);
        Nat.shiftUpBits(17, create5, 3, 0, create2);
        SecP521R1Field.reduce23(create2);
        SecP521R1FieldElement secP521R1FieldElement4 = new SecP521R1FieldElement(create5);
        SecP521R1Field.square(create3, secP521R1FieldElement4.f27023x, create);
        SecP521R1Field.subtract(secP521R1FieldElement4.f27023x, create4, secP521R1FieldElement4.f27023x);
        SecP521R1Field.subtract(secP521R1FieldElement4.f27023x, create4, secP521R1FieldElement4.f27023x);
        SecP521R1FieldElement secP521R1FieldElement5 = new SecP521R1FieldElement(create4);
        SecP521R1Field.subtract(create4, secP521R1FieldElement4.f27023x, secP521R1FieldElement5.f27023x);
        SecP521R1Field.multiply(secP521R1FieldElement5.f27023x, create3, secP521R1FieldElement5.f27023x, create);
        SecP521R1Field.subtract(secP521R1FieldElement5.f27023x, create2, secP521R1FieldElement5.f27023x);
        SecP521R1FieldElement secP521R1FieldElement6 = new SecP521R1FieldElement(create3);
        SecP521R1Field.twice(secP521R1FieldElement.f27023x, secP521R1FieldElement6.f27023x);
        if (!isOne) {
            SecP521R1Field.multiply(secP521R1FieldElement6.f27023x, secP521R1FieldElement3.f27023x, secP521R1FieldElement6.f27023x, create);
        }
        return new SecP521R1Point(curve, secP521R1FieldElement4, secP521R1FieldElement5, new ECFieldElement[]{secP521R1FieldElement6});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f26950y.isZero() ? eCPoint : twice().add(eCPoint);
    }

    protected ECFieldElement two(ECFieldElement eCFieldElement) {
        return eCFieldElement.add(eCFieldElement);
    }
}
