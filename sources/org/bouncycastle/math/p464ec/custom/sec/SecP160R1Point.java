package org.bouncycastle.math.p464ec.custom.sec;

import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECPoint;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat160;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP160R1Point */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP160R1Point extends ECPoint.AbstractFp {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP160R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecP160R1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
        SecP160R1FieldElement secP160R1FieldElement = (SecP160R1FieldElement) this.f26949x;
        SecP160R1FieldElement secP160R1FieldElement2 = (SecP160R1FieldElement) this.f26950y;
        SecP160R1FieldElement secP160R1FieldElement3 = (SecP160R1FieldElement) eCPoint.getXCoord();
        SecP160R1FieldElement secP160R1FieldElement4 = (SecP160R1FieldElement) eCPoint.getYCoord();
        SecP160R1FieldElement secP160R1FieldElement5 = (SecP160R1FieldElement) this.f26951zs[0];
        SecP160R1FieldElement secP160R1FieldElement6 = (SecP160R1FieldElement) eCPoint.getZCoord(0);
        int[] createExt = Nat160.createExt();
        int[] create = Nat160.create();
        int[] create2 = Nat160.create();
        int[] create3 = Nat160.create();
        boolean isOne = secP160R1FieldElement5.isOne();
        if (isOne) {
            iArr = secP160R1FieldElement3.f26976x;
            iArr2 = secP160R1FieldElement4.f26976x;
        } else {
            SecP160R1Field.square(secP160R1FieldElement5.f26976x, create2);
            SecP160R1Field.multiply(create2, secP160R1FieldElement3.f26976x, create);
            SecP160R1Field.multiply(create2, secP160R1FieldElement5.f26976x, create2);
            SecP160R1Field.multiply(create2, secP160R1FieldElement4.f26976x, create2);
            iArr = create;
            iArr2 = create2;
        }
        boolean isOne2 = secP160R1FieldElement6.isOne();
        if (isOne2) {
            iArr3 = secP160R1FieldElement.f26976x;
            iArr4 = secP160R1FieldElement2.f26976x;
        } else {
            SecP160R1Field.square(secP160R1FieldElement6.f26976x, create3);
            SecP160R1Field.multiply(create3, secP160R1FieldElement.f26976x, createExt);
            SecP160R1Field.multiply(create3, secP160R1FieldElement6.f26976x, create3);
            SecP160R1Field.multiply(create3, secP160R1FieldElement2.f26976x, create3);
            iArr3 = createExt;
            iArr4 = create3;
        }
        int[] create4 = Nat160.create();
        SecP160R1Field.subtract(iArr3, iArr, create4);
        SecP160R1Field.subtract(iArr4, iArr2, create);
        if (Nat160.isZero(create4)) {
            return Nat160.isZero(create) ? twice() : curve.getInfinity();
        }
        SecP160R1Field.square(create4, create2);
        int[] create5 = Nat160.create();
        SecP160R1Field.multiply(create2, create4, create5);
        SecP160R1Field.multiply(create2, iArr3, create2);
        SecP160R1Field.negate(create5, create5);
        Nat160.mul(iArr4, create5, createExt);
        SecP160R1Field.reduce32(Nat160.addBothTo(create2, create2, create5), create5);
        SecP160R1FieldElement secP160R1FieldElement7 = new SecP160R1FieldElement(create3);
        SecP160R1Field.square(create, secP160R1FieldElement7.f26976x);
        SecP160R1Field.subtract(secP160R1FieldElement7.f26976x, create5, secP160R1FieldElement7.f26976x);
        SecP160R1FieldElement secP160R1FieldElement8 = new SecP160R1FieldElement(create5);
        SecP160R1Field.subtract(create2, secP160R1FieldElement7.f26976x, secP160R1FieldElement8.f26976x);
        SecP160R1Field.multiplyAddToExt(secP160R1FieldElement8.f26976x, create, createExt);
        SecP160R1Field.reduce(createExt, secP160R1FieldElement8.f26976x);
        SecP160R1FieldElement secP160R1FieldElement9 = new SecP160R1FieldElement(create4);
        if (!isOne) {
            SecP160R1Field.multiply(secP160R1FieldElement9.f26976x, secP160R1FieldElement5.f26976x, secP160R1FieldElement9.f26976x);
        }
        if (!isOne2) {
            SecP160R1Field.multiply(secP160R1FieldElement9.f26976x, secP160R1FieldElement6.f26976x, secP160R1FieldElement9.f26976x);
        }
        return new SecP160R1Point(curve, secP160R1FieldElement7, secP160R1FieldElement8, new ECFieldElement[]{secP160R1FieldElement9});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint detach() {
        return new SecP160R1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint negate() {
        return isInfinity() ? this : new SecP160R1Point(this.curve, this.f26949x, this.f26950y.negate(), this.f26951zs);
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
        SecP160R1FieldElement secP160R1FieldElement = (SecP160R1FieldElement) this.f26950y;
        if (secP160R1FieldElement.isZero()) {
            return curve.getInfinity();
        }
        SecP160R1FieldElement secP160R1FieldElement2 = (SecP160R1FieldElement) this.f26949x;
        SecP160R1FieldElement secP160R1FieldElement3 = (SecP160R1FieldElement) this.f26951zs[0];
        int[] create = Nat160.create();
        int[] create2 = Nat160.create();
        int[] create3 = Nat160.create();
        SecP160R1Field.square(secP160R1FieldElement.f26976x, create3);
        int[] create4 = Nat160.create();
        SecP160R1Field.square(create3, create4);
        boolean isOne = secP160R1FieldElement3.isOne();
        int[] iArr = secP160R1FieldElement3.f26976x;
        if (!isOne) {
            SecP160R1Field.square(secP160R1FieldElement3.f26976x, create2);
            iArr = create2;
        }
        SecP160R1Field.subtract(secP160R1FieldElement2.f26976x, iArr, create);
        SecP160R1Field.add(secP160R1FieldElement2.f26976x, iArr, create2);
        SecP160R1Field.multiply(create2, create, create2);
        SecP160R1Field.reduce32(Nat160.addBothTo(create2, create2, create2), create2);
        SecP160R1Field.multiply(create3, secP160R1FieldElement2.f26976x, create3);
        SecP160R1Field.reduce32(Nat.shiftUpBits(5, create3, 2, 0), create3);
        SecP160R1Field.reduce32(Nat.shiftUpBits(5, create4, 3, 0, create), create);
        SecP160R1FieldElement secP160R1FieldElement4 = new SecP160R1FieldElement(create4);
        SecP160R1Field.square(create2, secP160R1FieldElement4.f26976x);
        SecP160R1Field.subtract(secP160R1FieldElement4.f26976x, create3, secP160R1FieldElement4.f26976x);
        SecP160R1Field.subtract(secP160R1FieldElement4.f26976x, create3, secP160R1FieldElement4.f26976x);
        SecP160R1FieldElement secP160R1FieldElement5 = new SecP160R1FieldElement(create3);
        SecP160R1Field.subtract(create3, secP160R1FieldElement4.f26976x, secP160R1FieldElement5.f26976x);
        SecP160R1Field.multiply(secP160R1FieldElement5.f26976x, create2, secP160R1FieldElement5.f26976x);
        SecP160R1Field.subtract(secP160R1FieldElement5.f26976x, create, secP160R1FieldElement5.f26976x);
        SecP160R1FieldElement secP160R1FieldElement6 = new SecP160R1FieldElement(create2);
        SecP160R1Field.twice(secP160R1FieldElement.f26976x, secP160R1FieldElement6.f26976x);
        if (!isOne) {
            SecP160R1Field.multiply(secP160R1FieldElement6.f26976x, secP160R1FieldElement3.f26976x, secP160R1FieldElement6.f26976x);
        }
        return new SecP160R1Point(curve, secP160R1FieldElement4, secP160R1FieldElement5, new ECFieldElement[]{secP160R1FieldElement6});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        return this == eCPoint ? threeTimes() : isInfinity() ? eCPoint : eCPoint.isInfinity() ? twice() : this.f26950y.isZero() ? eCPoint : twice().add(eCPoint);
    }
}
