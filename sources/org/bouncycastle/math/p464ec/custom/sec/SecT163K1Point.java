package org.bouncycastle.math.p464ec.custom.sec;

import org.bouncycastle.math.p464ec.ECConstants;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT163K1Point */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecT163K1Point extends ECPoint.AbstractF2m {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SecT163K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        super(eCCurve, eCFieldElement, eCFieldElement2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SecT163K1Point(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint add(ECPoint eCPoint) {
        ECFieldElement eCFieldElement;
        ECFieldElement eCFieldElement2;
        ECFieldElement eCFieldElement3;
        ECFieldElement multiply;
        ECFieldElement multiply2;
        ECFieldElement squarePlusProduct;
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement4 = this.f26949x;
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        if (eCFieldElement4.isZero()) {
            return rawXCoord.isZero() ? curve.getInfinity() : eCPoint.add(this);
        }
        ECFieldElement eCFieldElement5 = this.f26950y;
        ECFieldElement eCFieldElement6 = this.f26951zs[0];
        ECFieldElement rawYCoord = eCPoint.getRawYCoord();
        ECFieldElement zCoord = eCPoint.getZCoord(0);
        boolean isOne = eCFieldElement6.isOne();
        if (isOne) {
            eCFieldElement = rawXCoord;
            eCFieldElement2 = rawYCoord;
        } else {
            eCFieldElement = rawXCoord.multiply(eCFieldElement6);
            eCFieldElement2 = rawYCoord.multiply(eCFieldElement6);
        }
        boolean isOne2 = zCoord.isOne();
        if (isOne2) {
            eCFieldElement3 = eCFieldElement5;
        } else {
            eCFieldElement4 = eCFieldElement4.multiply(zCoord);
            eCFieldElement3 = eCFieldElement5.multiply(zCoord);
        }
        ECFieldElement add = eCFieldElement3.add(eCFieldElement2);
        ECFieldElement add2 = eCFieldElement4.add(eCFieldElement);
        if (add2.isZero()) {
            return add.isZero() ? twice() : curve.getInfinity();
        }
        if (rawXCoord.isZero()) {
            ECPoint normalize = normalize();
            ECFieldElement xCoord = normalize.getXCoord();
            ECFieldElement yCoord = normalize.getYCoord();
            ECFieldElement divide = yCoord.add(rawYCoord).divide(xCoord);
            ECFieldElement addOne = divide.square().add(divide).add(xCoord).addOne();
            if (addOne.isZero()) {
                return new SecT163K1Point(curve, addOne, curve.getB());
            }
            squarePlusProduct = divide.multiply(xCoord.add(addOne)).add(addOne).add(yCoord).divide(addOne).add(addOne);
            multiply2 = curve.fromBigInteger(ECConstants.ONE);
            multiply = addOne;
        } else {
            ECFieldElement square = add2.square();
            ECFieldElement multiply3 = add.multiply(eCFieldElement4);
            ECFieldElement multiply4 = add.multiply(eCFieldElement);
            multiply = multiply3.multiply(multiply4);
            if (multiply.isZero()) {
                return new SecT163K1Point(curve, multiply, curve.getB());
            }
            ECFieldElement multiply5 = add.multiply(square);
            multiply2 = !isOne2 ? multiply5.multiply(zCoord) : multiply5;
            squarePlusProduct = multiply4.add(square).squarePlusProduct(multiply2, eCFieldElement5.add(eCFieldElement6));
            if (!isOne) {
                multiply2 = multiply2.multiply(eCFieldElement6);
            }
        }
        return new SecT163K1Point(curve, multiply, squarePlusProduct, new ECFieldElement[]{multiply2});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint detach() {
        return new SecT163K1Point(null, getAffineXCoord(), getAffineYCoord());
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public boolean getCompressionYTilde() {
        ECFieldElement rawXCoord = getRawXCoord();
        return (rawXCoord.isZero() || getRawYCoord().testBitZero() == rawXCoord.testBitZero()) ? false : true;
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECFieldElement getYCoord() {
        ECFieldElement eCFieldElement = this.f26949x;
        ECFieldElement eCFieldElement2 = this.f26950y;
        if (isInfinity() || eCFieldElement.isZero()) {
            return eCFieldElement2;
        }
        ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
        ECFieldElement eCFieldElement3 = this.f26951zs[0];
        return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint negate() {
        if (isInfinity()) {
            return this;
        }
        ECFieldElement eCFieldElement = this.f26949x;
        if (eCFieldElement.isZero()) {
            return this;
        }
        ECFieldElement eCFieldElement2 = this.f26950y;
        ECFieldElement eCFieldElement3 = this.f26951zs[0];
        return new SecT163K1Point(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twice() {
        if (isInfinity()) {
            return this;
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement = this.f26949x;
        if (eCFieldElement.isZero()) {
            return curve.getInfinity();
        }
        ECFieldElement eCFieldElement2 = this.f26950y;
        ECFieldElement eCFieldElement3 = this.f26951zs[0];
        boolean isOne = eCFieldElement3.isOne();
        ECFieldElement multiply = isOne ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement3);
        if (!isOne) {
            eCFieldElement3 = eCFieldElement3.square();
        }
        ECFieldElement add = eCFieldElement2.square().add(multiply).add(eCFieldElement3);
        if (add.isZero()) {
            return new SecT163K1Point(curve, add, curve.getB());
        }
        ECFieldElement square = add.square();
        ECFieldElement multiply2 = isOne ? add : add.multiply(eCFieldElement3);
        ECFieldElement square2 = eCFieldElement2.add(eCFieldElement).square();
        return new SecT163K1Point(curve, square, square2.add(add).add(eCFieldElement3).multiply(square2).add(square), new ECFieldElement[]{multiply2});
    }

    @Override // org.bouncycastle.math.p464ec.ECPoint
    public ECPoint twicePlus(ECPoint eCPoint) {
        if (isInfinity()) {
            return eCPoint;
        }
        if (eCPoint.isInfinity()) {
            return twice();
        }
        ECCurve curve = getCurve();
        ECFieldElement eCFieldElement = this.f26949x;
        if (eCFieldElement.isZero()) {
            return eCPoint;
        }
        ECFieldElement rawXCoord = eCPoint.getRawXCoord();
        ECFieldElement zCoord = eCPoint.getZCoord(0);
        if (rawXCoord.isZero() || !zCoord.isOne()) {
            return twice().add(eCPoint);
        }
        ECFieldElement eCFieldElement2 = this.f26950y;
        ECFieldElement eCFieldElement3 = this.f26951zs[0];
        ECFieldElement rawYCoord = eCPoint.getRawYCoord();
        ECFieldElement square = eCFieldElement.square();
        ECFieldElement square2 = eCFieldElement2.square();
        ECFieldElement square3 = eCFieldElement3.square();
        ECFieldElement add = square3.add(square2).add(eCFieldElement2.multiply(eCFieldElement3));
        ECFieldElement multiplyPlusProduct = rawYCoord.multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
        ECFieldElement multiply = rawXCoord.multiply(square3);
        ECFieldElement square4 = multiply.add(add).square();
        if (square4.isZero()) {
            return multiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
        } else if (multiplyPlusProduct.isZero()) {
            return new SecT163K1Point(curve, multiplyPlusProduct, curve.getB());
        } else {
            ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
            ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
            return new SecT163K1Point(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, rawYCoord.addOne(), multiply3), new ECFieldElement[]{multiply3});
        }
    }
}
