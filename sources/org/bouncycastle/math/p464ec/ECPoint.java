package org.bouncycastle.math.p464ec;

import java.math.BigInteger;
import java.util.Hashtable;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.math.p464ec.ECCurve;
import org.bouncycastle.math.p464ec.ECFieldElement;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.ECPoint */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ECPoint {
    protected static final ECFieldElement[] EMPTY_ZS = new ECFieldElement[0];
    protected ECCurve curve;
    protected Hashtable preCompTable;

    /* renamed from: x */
    protected ECFieldElement f26949x;

    /* renamed from: y */
    protected ECFieldElement f26950y;

    /* renamed from: zs */
    protected ECFieldElement[] f26951zs;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECPoint$AbstractF2m */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractF2m extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractF2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement multiplyPlusProduct;
            ECFieldElement squarePlusProduct;
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f26949x;
            ECFieldElement a = curve.getA();
            ECFieldElement b = curve.getB();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                ECFieldElement eCFieldElement2 = this.f26950y;
                ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement2);
                switch (coordinateSystem) {
                    case 0:
                        break;
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                    case 1:
                        ECFieldElement eCFieldElement3 = this.f26951zs[0];
                        if (!eCFieldElement3.isOne()) {
                            ECFieldElement multiply2 = eCFieldElement3.multiply(eCFieldElement3.square());
                            multiply = multiply.multiply(eCFieldElement3);
                            a = a.multiply(eCFieldElement3);
                            b = b.multiply(multiply2);
                            break;
                        }
                        break;
                }
                return multiply.equals(eCFieldElement.add(a).multiply(eCFieldElement.square()).add(b));
            }
            ECFieldElement eCFieldElement4 = this.f26951zs[0];
            boolean isOne = eCFieldElement4.isOne();
            if (eCFieldElement.isZero()) {
                ECFieldElement square = this.f26950y.square();
                if (!isOne) {
                    b = b.multiply(eCFieldElement4.square());
                }
                return square.equals(b);
            }
            ECFieldElement eCFieldElement5 = this.f26950y;
            ECFieldElement square2 = eCFieldElement.square();
            if (isOne) {
                multiplyPlusProduct = eCFieldElement5.square().add(eCFieldElement5).add(a);
                squarePlusProduct = square2.square().add(b);
            } else {
                ECFieldElement square3 = eCFieldElement4.square();
                ECFieldElement square4 = square3.square();
                multiplyPlusProduct = eCFieldElement5.add(eCFieldElement4).multiplyPlusProduct(eCFieldElement5, a, square3);
                squarePlusProduct = square2.squarePlusProduct(b, square4);
            }
            return multiplyPlusProduct.multiply(square2).equals(squarePlusProduct);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected boolean satisfiesOrder() {
            BigInteger cofactor = this.curve.getCofactor();
            if (ECConstants.TWO.equals(cofactor)) {
                return ((ECFieldElement.AbstractF2m) normalize().getAffineXCoord()).trace() != 0;
            } else if (ECConstants.FOUR.equals(cofactor)) {
                ECPoint normalize = normalize();
                ECFieldElement affineXCoord = normalize.getAffineXCoord();
                ECFieldElement solveQuadraticEquation = ((ECCurve.AbstractF2m) this.curve).solveQuadraticEquation(affineXCoord.add(this.curve.getA()));
                if (solveQuadraticEquation == null) {
                    return false;
                }
                return ((ECFieldElement.AbstractF2m) affineXCoord.multiply(solveQuadraticEquation).add(normalize.getAffineYCoord())).trace() == 0;
            } else {
                return super.satisfiesOrder();
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint scaleX(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                    ECFieldElement rawXCoord = getRawXCoord();
                    return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).divide(eCFieldElement).add(rawXCoord.multiply(eCFieldElement)), getRawZCoords());
                case 6:
                    ECFieldElement rawXCoord2 = getRawXCoord();
                    ECFieldElement rawYCoord = getRawYCoord();
                    ECFieldElement eCFieldElement2 = getRawZCoords()[0];
                    ECFieldElement multiply = rawXCoord2.multiply(eCFieldElement.square());
                    return getCurve().createRawPoint(multiply, rawYCoord.add(rawXCoord2).add(multiply), new ECFieldElement[]{eCFieldElement2.multiply(eCFieldElement)});
                default:
                    return super.scaleX(eCFieldElement);
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
            return scaleX(eCFieldElement);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint scaleY(ECFieldElement eCFieldElement) {
            if (isInfinity()) {
                return this;
            }
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    ECFieldElement rawXCoord = getRawXCoord();
                    return getCurve().createRawPoint(rawXCoord, getRawYCoord().add(rawXCoord).multiply(eCFieldElement).add(rawXCoord), getRawZCoords());
                default:
                    return super.scaleY(eCFieldElement);
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
            return scaleY(eCFieldElement);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }

        public AbstractF2m tau() {
            ECPoint createRawPoint;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f26949x;
            switch (coordinateSystem) {
                case 0:
                case 5:
                    createRawPoint = curve.createRawPoint(eCFieldElement.square(), this.f26950y.square());
                    break;
                case 1:
                case 6:
                    createRawPoint = curve.createRawPoint(eCFieldElement.square(), this.f26950y.square(), new ECFieldElement[]{this.f26951zs[0].square()});
                    break;
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return (AbstractF2m) createRawPoint;
        }

        public AbstractF2m tauPow(int i) {
            ECPoint createRawPoint;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement = this.f26949x;
            switch (coordinateSystem) {
                case 0:
                case 5:
                    createRawPoint = curve.createRawPoint(eCFieldElement.squarePow(i), this.f26950y.squarePow(i));
                    break;
                case 1:
                case 6:
                    createRawPoint = curve.createRawPoint(eCFieldElement.squarePow(i), this.f26950y.squarePow(i), new ECFieldElement[]{this.f26951zs[0].squarePow(i)});
                    break;
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return (AbstractF2m) createRawPoint;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECPoint$AbstractFp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class AbstractFp extends ECPoint {
        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public AbstractFp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected boolean getCompressionYTilde() {
            return getAffineYCoord().testBitZero();
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected boolean satisfiesCurveEquation() {
            ECFieldElement eCFieldElement = this.f26949x;
            ECFieldElement eCFieldElement2 = this.f26950y;
            ECFieldElement a = this.curve.getA();
            ECFieldElement b = this.curve.getB();
            ECFieldElement square = eCFieldElement2.square();
            switch (getCurveCoordinateSystem()) {
                case 0:
                    break;
                case 1:
                    ECFieldElement eCFieldElement3 = this.f26951zs[0];
                    if (!eCFieldElement3.isOne()) {
                        ECFieldElement square2 = eCFieldElement3.square();
                        ECFieldElement multiply = eCFieldElement3.multiply(square2);
                        square = square.multiply(eCFieldElement3);
                        a = a.multiply(square2);
                        b = b.multiply(multiply);
                        break;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    ECFieldElement eCFieldElement4 = this.f26951zs[0];
                    if (!eCFieldElement4.isOne()) {
                        ECFieldElement square3 = eCFieldElement4.square();
                        ECFieldElement square4 = square3.square();
                        ECFieldElement multiply2 = square3.multiply(square4);
                        a = a.multiply(square4);
                        b = b.multiply(multiply2);
                        break;
                    }
                    break;
                default:
                    throw new IllegalStateException("unsupported coordinate system");
            }
            return square.equals(eCFieldElement.square().add(a).multiply(eCFieldElement).add(b));
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint subtract(ECPoint eCPoint) {
            return eCPoint.isInfinity() ? this : add(eCPoint.negate());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECPoint$F2m */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class F2m extends AbstractF2m {
        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public F2m(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
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
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement4 = this.f26949x;
            ECFieldElement eCFieldElement5 = eCPoint.f26949x;
            if (coordinateSystem != 6) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement eCFieldElement6 = this.f26950y;
                        ECFieldElement eCFieldElement7 = eCPoint.f26950y;
                        ECFieldElement add = eCFieldElement4.add(eCFieldElement5);
                        ECFieldElement add2 = eCFieldElement6.add(eCFieldElement7);
                        if (add.isZero()) {
                            return add2.isZero() ? twice() : curve.getInfinity();
                        }
                        ECFieldElement divide = add2.divide(add);
                        ECFieldElement add3 = divide.square().add(divide).add(add).add(curve.getA());
                        return new F2m(curve, add3, divide.multiply(eCFieldElement4.add(add3)).add(add3).add(eCFieldElement6));
                    case 1:
                        ECFieldElement eCFieldElement8 = this.f26950y;
                        ECFieldElement eCFieldElement9 = this.f26951zs[0];
                        ECFieldElement eCFieldElement10 = eCPoint.f26950y;
                        ECFieldElement eCFieldElement11 = eCPoint.f26951zs[0];
                        boolean isOne = eCFieldElement11.isOne();
                        ECFieldElement add4 = eCFieldElement9.multiply(eCFieldElement10).add(isOne ? eCFieldElement8 : eCFieldElement8.multiply(eCFieldElement11));
                        ECFieldElement add5 = eCFieldElement9.multiply(eCFieldElement5).add(isOne ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement11));
                        if (add5.isZero()) {
                            return add4.isZero() ? twice() : curve.getInfinity();
                        }
                        ECFieldElement square = add5.square();
                        ECFieldElement multiply3 = square.multiply(add5);
                        if (!isOne) {
                            eCFieldElement9 = eCFieldElement9.multiply(eCFieldElement11);
                        }
                        ECFieldElement add6 = add4.add(add5);
                        ECFieldElement add7 = add6.multiplyPlusProduct(add4, square, curve.getA()).multiply(eCFieldElement9).add(multiply3);
                        ECFieldElement multiply4 = add5.multiply(add7);
                        if (!isOne) {
                            square = square.multiply(eCFieldElement11);
                        }
                        return new F2m(curve, multiply4, add4.multiplyPlusProduct(eCFieldElement4, add5, eCFieldElement8).multiplyPlusProduct(square, add6, add7), new ECFieldElement[]{multiply3.multiply(eCFieldElement9)});
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            } else if (eCFieldElement4.isZero()) {
                return eCFieldElement5.isZero() ? curve.getInfinity() : eCPoint.add(this);
            } else {
                ECFieldElement eCFieldElement12 = this.f26950y;
                ECFieldElement eCFieldElement13 = this.f26951zs[0];
                ECFieldElement eCFieldElement14 = eCPoint.f26950y;
                ECFieldElement eCFieldElement15 = eCPoint.f26951zs[0];
                boolean isOne2 = eCFieldElement13.isOne();
                if (isOne2) {
                    eCFieldElement = eCFieldElement5;
                    eCFieldElement2 = eCFieldElement14;
                } else {
                    eCFieldElement = eCFieldElement5.multiply(eCFieldElement13);
                    eCFieldElement2 = eCFieldElement14.multiply(eCFieldElement13);
                }
                boolean isOne3 = eCFieldElement15.isOne();
                if (isOne3) {
                    eCFieldElement3 = eCFieldElement12;
                } else {
                    eCFieldElement4 = eCFieldElement4.multiply(eCFieldElement15);
                    eCFieldElement3 = eCFieldElement12.multiply(eCFieldElement15);
                }
                ECFieldElement add8 = eCFieldElement3.add(eCFieldElement2);
                ECFieldElement add9 = eCFieldElement4.add(eCFieldElement);
                if (add9.isZero()) {
                    return add8.isZero() ? twice() : curve.getInfinity();
                }
                if (eCFieldElement5.isZero()) {
                    ECPoint normalize = normalize();
                    ECFieldElement xCoord = normalize.getXCoord();
                    ECFieldElement yCoord = normalize.getYCoord();
                    ECFieldElement divide2 = yCoord.add(eCFieldElement14).divide(xCoord);
                    ECFieldElement add10 = divide2.square().add(divide2).add(xCoord).add(curve.getA());
                    if (add10.isZero()) {
                        return new F2m(curve, add10, curve.getB().sqrt());
                    }
                    ECFieldElement add11 = divide2.multiply(xCoord.add(add10)).add(add10).add(yCoord).divide(add10).add(add10);
                    multiply = add10;
                    squarePlusProduct = add11;
                    multiply2 = curve.fromBigInteger(ECConstants.ONE);
                } else {
                    ECFieldElement square2 = add9.square();
                    ECFieldElement multiply5 = add8.multiply(eCFieldElement4);
                    ECFieldElement multiply6 = add8.multiply(eCFieldElement);
                    multiply = multiply5.multiply(multiply6);
                    if (multiply.isZero()) {
                        return new F2m(curve, multiply, curve.getB().sqrt());
                    }
                    ECFieldElement multiply7 = add8.multiply(square2);
                    multiply2 = !isOne3 ? multiply7.multiply(eCFieldElement15) : multiply7;
                    squarePlusProduct = multiply6.add(square2).squarePlusProduct(multiply2, eCFieldElement12.add(eCFieldElement13));
                    if (!isOne2) {
                        multiply2 = multiply2.multiply(eCFieldElement13);
                    }
                }
                return new F2m(curve, multiply, squarePlusProduct, new ECFieldElement[]{multiply2});
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected ECPoint detach() {
            return new F2m(null, getAffineXCoord(), getAffineYCoord());
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected boolean getCompressionYTilde() {
            ECFieldElement rawXCoord = getRawXCoord();
            if (rawXCoord.isZero()) {
                return false;
            }
            ECFieldElement rawYCoord = getRawYCoord();
            switch (getCurveCoordinateSystem()) {
                case 5:
                case 6:
                    return rawYCoord.testBitZero() != rawXCoord.testBitZero();
                default:
                    return rawYCoord.divide(rawXCoord).testBitZero();
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECFieldElement getYCoord() {
            int curveCoordinateSystem = getCurveCoordinateSystem();
            switch (curveCoordinateSystem) {
                case 5:
                case 6:
                    ECFieldElement eCFieldElement = this.f26949x;
                    ECFieldElement eCFieldElement2 = this.f26950y;
                    if (isInfinity() || eCFieldElement.isZero()) {
                        return eCFieldElement2;
                    }
                    ECFieldElement multiply = eCFieldElement2.add(eCFieldElement).multiply(eCFieldElement);
                    if (6 == curveCoordinateSystem) {
                        ECFieldElement eCFieldElement3 = this.f26951zs[0];
                        return !eCFieldElement3.isOne() ? multiply.divide(eCFieldElement3) : multiply;
                    }
                    return multiply;
                default:
                    return this.f26950y;
            }
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
            switch (getCurveCoordinateSystem()) {
                case 0:
                    return new F2m(this.curve, eCFieldElement, this.f26950y.add(eCFieldElement));
                case 1:
                    return new F2m(this.curve, eCFieldElement, this.f26950y.add(eCFieldElement), new ECFieldElement[]{this.f26951zs[0]});
                case 2:
                case 3:
                case 4:
                default:
                    throw new IllegalStateException("unsupported coordinate system");
                case 5:
                    return new F2m(this.curve, eCFieldElement, this.f26950y.addOne());
                case 6:
                    ECFieldElement eCFieldElement2 = this.f26950y;
                    ECFieldElement eCFieldElement3 = this.f26951zs[0];
                    return new F2m(this.curve, eCFieldElement, eCFieldElement2.add(eCFieldElement3), new ECFieldElement[]{eCFieldElement3});
            }
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint twice() {
            ECFieldElement add;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement = this.f26949x;
            if (eCFieldElement.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 6) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement add2 = this.f26950y.divide(eCFieldElement).add(eCFieldElement);
                        ECFieldElement add3 = add2.square().add(add2).add(curve.getA());
                        return new F2m(curve, add3, eCFieldElement.squarePlusProduct(add3, add2.addOne()));
                    case 1:
                        ECFieldElement eCFieldElement2 = this.f26950y;
                        ECFieldElement eCFieldElement3 = this.f26951zs[0];
                        boolean isOne = eCFieldElement3.isOne();
                        ECFieldElement multiply = isOne ? eCFieldElement : eCFieldElement.multiply(eCFieldElement3);
                        if (!isOne) {
                            eCFieldElement2 = eCFieldElement2.multiply(eCFieldElement3);
                        }
                        ECFieldElement square = eCFieldElement.square();
                        ECFieldElement add4 = square.add(eCFieldElement2);
                        ECFieldElement square2 = multiply.square();
                        ECFieldElement add5 = add4.add(multiply);
                        ECFieldElement multiplyPlusProduct = add5.multiplyPlusProduct(add4, square2, curve.getA());
                        return new F2m(curve, multiply.multiply(multiplyPlusProduct), square.square().multiplyPlusProduct(multiply, multiplyPlusProduct, add5), new ECFieldElement[]{multiply.multiply(square2)});
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
            ECFieldElement eCFieldElement4 = this.f26950y;
            ECFieldElement eCFieldElement5 = this.f26951zs[0];
            boolean isOne2 = eCFieldElement5.isOne();
            ECFieldElement multiply2 = isOne2 ? eCFieldElement4 : eCFieldElement4.multiply(eCFieldElement5);
            ECFieldElement square3 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
            ECFieldElement a = curve.getA();
            ECFieldElement multiply3 = isOne2 ? a : a.multiply(square3);
            ECFieldElement add6 = eCFieldElement4.square().add(multiply2).add(multiply3);
            if (add6.isZero()) {
                return new F2m(curve, add6, curve.getB().sqrt());
            }
            ECFieldElement square4 = add6.square();
            ECFieldElement multiply4 = isOne2 ? add6 : add6.multiply(square3);
            ECFieldElement b = curve.getB();
            if (b.bitLength() < (curve.getFieldSize() >> 1)) {
                ECFieldElement square5 = eCFieldElement4.add(eCFieldElement).square();
                add = square5.add(add6).add(square3).multiply(square5).add(b.isOne() ? multiply3.add(square3).square() : multiply3.squarePlusProduct(b, square3.square())).add(square4);
                if (!a.isZero()) {
                    if (!a.isOne()) {
                        add = add.add(a.addOne().multiply(multiply4));
                    }
                    return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
                }
            } else {
                if (!isOne2) {
                    eCFieldElement = eCFieldElement.multiply(eCFieldElement5);
                }
                add = eCFieldElement.squarePlusProduct(add6, multiply2).add(square4);
            }
            add = add.add(multiply4);
            return new F2m(curve, square4, add, new ECFieldElement[]{multiply4});
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
            if (curve.getCoordinateSystem() != 6) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = eCPoint.f26949x;
            ECFieldElement eCFieldElement3 = eCPoint.f26951zs[0];
            if (eCFieldElement2.isZero() || !eCFieldElement3.isOne()) {
                return twice().add(eCPoint);
            }
            ECFieldElement eCFieldElement4 = this.f26950y;
            ECFieldElement eCFieldElement5 = this.f26951zs[0];
            ECFieldElement eCFieldElement6 = eCPoint.f26950y;
            ECFieldElement square = eCFieldElement.square();
            ECFieldElement square2 = eCFieldElement4.square();
            ECFieldElement square3 = eCFieldElement5.square();
            ECFieldElement add = curve.getA().multiply(square3).add(square2).add(eCFieldElement4.multiply(eCFieldElement5));
            ECFieldElement addOne = eCFieldElement6.addOne();
            ECFieldElement multiplyPlusProduct = curve.getA().add(addOne).multiply(square3).add(square2).multiplyPlusProduct(add, square, square3);
            ECFieldElement multiply = eCFieldElement2.multiply(square3);
            ECFieldElement square4 = multiply.add(add).square();
            if (square4.isZero()) {
                return multiplyPlusProduct.isZero() ? eCPoint.twice() : curve.getInfinity();
            } else if (multiplyPlusProduct.isZero()) {
                return new F2m(curve, multiplyPlusProduct, curve.getB().sqrt());
            } else {
                ECFieldElement multiply2 = multiplyPlusProduct.square().multiply(multiply);
                ECFieldElement multiply3 = multiplyPlusProduct.multiply(square4).multiply(square3);
                return new F2m(curve, multiply2, multiplyPlusProduct.add(square4).square().multiplyPlusProduct(add, addOne, multiply3), new ECFieldElement[]{multiply3});
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.ECPoint$Fp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C13321Fp extends AbstractFp {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C13321Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            super(eCCurve, eCFieldElement, eCFieldElement2);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C13321Fp(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
            super(eCCurve, eCFieldElement, eCFieldElement2, eCFieldElementArr);
        }

        /* JADX WARN: Code restructure failed: missing block: B:91:0x01ea, code lost:
            if (r1 == r6) goto L64;
         */
        @Override // org.bouncycastle.math.p464ec.ECPoint
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public org.bouncycastle.math.p464ec.ECPoint add(org.bouncycastle.math.p464ec.ECPoint r17) {
            /*
                Method dump skipped, instructions count: 530
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.math.p464ec.ECPoint.C13321Fp.add(org.bouncycastle.math.ec.ECPoint):org.bouncycastle.math.ec.ECPoint");
        }

        protected ECFieldElement calculateJacobianModifiedW(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
            ECFieldElement a = getCurve().getA();
            if (a.isZero() || eCFieldElement.isOne()) {
                return a;
            }
            if (eCFieldElement2 == null) {
                eCFieldElement2 = eCFieldElement.square();
            }
            ECFieldElement square = eCFieldElement2.square();
            ECFieldElement negate = a.negate();
            return negate.bitLength() < a.bitLength() ? square.multiply(negate).negate() : square.multiply(a);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        protected ECPoint detach() {
            return new C13321Fp(null, getAffineXCoord(), getAffineYCoord());
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

        protected ECFieldElement getJacobianModifiedW() {
            ECFieldElement eCFieldElement = this.f26951zs[1];
            if (eCFieldElement == null) {
                ECFieldElement[] eCFieldElementArr = this.f26951zs;
                ECFieldElement calculateJacobianModifiedW = calculateJacobianModifiedW(this.f26951zs[0], null);
                eCFieldElementArr[1] = calculateJacobianModifiedW;
                return calculateJacobianModifiedW;
            }
            return eCFieldElement;
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECFieldElement getZCoord(int i) {
            return (i == 1 && 4 == getCurveCoordinateSystem()) ? getJacobianModifiedW() : super.getZCoord(i);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint negate() {
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            return curve.getCoordinateSystem() != 0 ? new C13321Fp(curve, this.f26949x, this.f26950y.negate(), this.f26951zs) : new C13321Fp(curve, this.f26949x, this.f26950y.negate());
        }

        protected ECFieldElement three(ECFieldElement eCFieldElement) {
            return two(eCFieldElement).add(eCFieldElement);
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint threeTimes() {
            if (isInfinity()) {
                return this;
            }
            ECFieldElement eCFieldElement = this.f26950y;
            if (eCFieldElement.isZero()) {
                return this;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(this) : twiceJacobianModified(false).add(this);
            }
            ECFieldElement eCFieldElement2 = this.f26949x;
            ECFieldElement two = two(eCFieldElement);
            ECFieldElement square = two.square();
            ECFieldElement add = three(eCFieldElement2.square()).add(getCurve().getA());
            ECFieldElement subtract = three(eCFieldElement2).multiply(square).subtract(add.square());
            if (subtract.isZero()) {
                return getCurve().getInfinity();
            }
            ECFieldElement invert = subtract.multiply(two).invert();
            ECFieldElement multiply = subtract.multiply(invert).multiply(add);
            ECFieldElement subtract2 = square.square().multiply(invert).subtract(multiply);
            ECFieldElement add2 = subtract2.subtract(multiply).multiply(multiply.add(subtract2)).add(eCFieldElement2);
            return new C13321Fp(curve, add2, eCFieldElement2.subtract(add2).multiply(subtract2).subtract(eCFieldElement));
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint timesPow2(int i) {
            ECFieldElement square;
            if (i >= 0) {
                if (i == 0 || isInfinity()) {
                    return this;
                }
                if (i == 1) {
                    return twice();
                }
                ECCurve curve = getCurve();
                ECFieldElement eCFieldElement = this.f26950y;
                if (eCFieldElement.isZero()) {
                    return curve.getInfinity();
                }
                int coordinateSystem = curve.getCoordinateSystem();
                ECFieldElement a = curve.getA();
                ECFieldElement eCFieldElement2 = this.f26949x;
                ECFieldElement fromBigInteger = this.f26951zs.length < 1 ? curve.fromBigInteger(ECConstants.ONE) : this.f26951zs[0];
                if (!fromBigInteger.isOne()) {
                    if (coordinateSystem != 4) {
                        switch (coordinateSystem) {
                            case 0:
                                break;
                            case 1:
                                square = fromBigInteger.square();
                                eCFieldElement2 = eCFieldElement2.multiply(fromBigInteger);
                                eCFieldElement = eCFieldElement.multiply(square);
                                a = calculateJacobianModifiedW(fromBigInteger, square);
                                break;
                            case 2:
                                square = null;
                                a = calculateJacobianModifiedW(fromBigInteger, square);
                                break;
                            default:
                                throw new IllegalStateException("unsupported coordinate system");
                        }
                    } else {
                        a = getJacobianModifiedW();
                    }
                }
                ECFieldElement eCFieldElement3 = a;
                ECFieldElement eCFieldElement4 = eCFieldElement;
                int i2 = 0;
                while (i2 < i) {
                    if (eCFieldElement4.isZero()) {
                        return curve.getInfinity();
                    }
                    ECFieldElement three = three(eCFieldElement2.square());
                    ECFieldElement two = two(eCFieldElement4);
                    ECFieldElement multiply = two.multiply(eCFieldElement4);
                    ECFieldElement two2 = two(eCFieldElement2.multiply(multiply));
                    ECFieldElement two3 = two(multiply.square());
                    if (!eCFieldElement3.isZero()) {
                        three = three.add(eCFieldElement3);
                        eCFieldElement3 = two(two3.multiply(eCFieldElement3));
                    }
                    ECFieldElement subtract = three.square().subtract(two(two2));
                    eCFieldElement4 = three.multiply(two2.subtract(subtract)).subtract(two3);
                    fromBigInteger = fromBigInteger.isOne() ? two : two.multiply(fromBigInteger);
                    i2++;
                    eCFieldElement2 = subtract;
                }
                if (coordinateSystem != 4) {
                    switch (coordinateSystem) {
                        case 0:
                            ECFieldElement invert = fromBigInteger.invert();
                            ECFieldElement square2 = invert.square();
                            return new C13321Fp(curve, eCFieldElement2.multiply(square2), eCFieldElement4.multiply(square2.multiply(invert)));
                        case 1:
                            return new C13321Fp(curve, eCFieldElement2.multiply(fromBigInteger), eCFieldElement4, new ECFieldElement[]{fromBigInteger.multiply(fromBigInteger.square())});
                        case 2:
                            return new C13321Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger});
                        default:
                            throw new IllegalStateException("unsupported coordinate system");
                    }
                }
                return new C13321Fp(curve, eCFieldElement2, eCFieldElement4, new ECFieldElement[]{fromBigInteger, eCFieldElement3});
            }
            throw new IllegalArgumentException("'e' cannot be negative");
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint twice() {
            ECFieldElement eCFieldElement;
            ECFieldElement multiply;
            if (isInfinity()) {
                return this;
            }
            ECCurve curve = getCurve();
            ECFieldElement eCFieldElement2 = this.f26950y;
            if (eCFieldElement2.isZero()) {
                return curve.getInfinity();
            }
            int coordinateSystem = curve.getCoordinateSystem();
            ECFieldElement eCFieldElement3 = this.f26949x;
            if (coordinateSystem != 4) {
                switch (coordinateSystem) {
                    case 0:
                        ECFieldElement divide = three(eCFieldElement3.square()).add(getCurve().getA()).divide(two(eCFieldElement2));
                        ECFieldElement subtract = divide.square().subtract(two(eCFieldElement3));
                        return new C13321Fp(curve, subtract, divide.multiply(eCFieldElement3.subtract(subtract)).subtract(eCFieldElement2));
                    case 1:
                        ECFieldElement eCFieldElement4 = this.f26951zs[0];
                        boolean isOne = eCFieldElement4.isOne();
                        ECFieldElement a = curve.getA();
                        if (!a.isZero() && !isOne) {
                            a = a.multiply(eCFieldElement4.square());
                        }
                        ECFieldElement add = a.add(three(eCFieldElement3.square()));
                        ECFieldElement multiply2 = isOne ? eCFieldElement2 : eCFieldElement2.multiply(eCFieldElement4);
                        ECFieldElement square = isOne ? eCFieldElement2.square() : multiply2.multiply(eCFieldElement2);
                        ECFieldElement four = four(eCFieldElement3.multiply(square));
                        ECFieldElement subtract2 = add.square().subtract(two(four));
                        ECFieldElement two = two(multiply2);
                        ECFieldElement multiply3 = subtract2.multiply(two);
                        ECFieldElement two2 = two(square);
                        return new C13321Fp(curve, multiply3, four.subtract(subtract2).multiply(add).subtract(two(two2.square())), new ECFieldElement[]{two(isOne ? two(two2) : two.square()).multiply(multiply2)});
                    case 2:
                        ECFieldElement eCFieldElement5 = this.f26951zs[0];
                        boolean isOne2 = eCFieldElement5.isOne();
                        ECFieldElement square2 = eCFieldElement2.square();
                        ECFieldElement square3 = square2.square();
                        ECFieldElement a2 = curve.getA();
                        ECFieldElement negate = a2.negate();
                        if (negate.toBigInteger().equals(BigInteger.valueOf(3L))) {
                            ECFieldElement square4 = isOne2 ? eCFieldElement5 : eCFieldElement5.square();
                            eCFieldElement = three(eCFieldElement3.add(square4).multiply(eCFieldElement3.subtract(square4)));
                            multiply = square2.multiply(eCFieldElement3);
                        } else {
                            ECFieldElement three = three(eCFieldElement3.square());
                            if (!isOne2) {
                                if (a2.isZero()) {
                                    eCFieldElement = three;
                                } else {
                                    ECFieldElement square5 = eCFieldElement5.square().square();
                                    if (negate.bitLength() < a2.bitLength()) {
                                        eCFieldElement = three.subtract(square5.multiply(negate));
                                    } else {
                                        a2 = square5.multiply(a2);
                                    }
                                }
                                multiply = eCFieldElement3.multiply(square2);
                            }
                            eCFieldElement = three.add(a2);
                            multiply = eCFieldElement3.multiply(square2);
                        }
                        ECFieldElement four2 = four(multiply);
                        ECFieldElement subtract3 = eCFieldElement.square().subtract(two(four2));
                        ECFieldElement subtract4 = four2.subtract(subtract3).multiply(eCFieldElement).subtract(eight(square3));
                        ECFieldElement two3 = two(eCFieldElement2);
                        if (!isOne2) {
                            two3 = two3.multiply(eCFieldElement5);
                        }
                        return new C13321Fp(curve, subtract3, subtract4, new ECFieldElement[]{two3});
                    default:
                        throw new IllegalStateException("unsupported coordinate system");
                }
            }
            return twiceJacobianModified(true);
        }

        protected C13321Fp twiceJacobianModified(boolean z) {
            ECFieldElement eCFieldElement = this.f26949x;
            ECFieldElement eCFieldElement2 = this.f26950y;
            ECFieldElement eCFieldElement3 = this.f26951zs[0];
            ECFieldElement jacobianModifiedW = getJacobianModifiedW();
            ECFieldElement add = three(eCFieldElement.square()).add(jacobianModifiedW);
            ECFieldElement two = two(eCFieldElement2);
            ECFieldElement multiply = two.multiply(eCFieldElement2);
            ECFieldElement two2 = two(eCFieldElement.multiply(multiply));
            ECFieldElement subtract = add.square().subtract(two(two2));
            ECFieldElement two3 = two(multiply.square());
            ECFieldElement subtract2 = add.multiply(two2.subtract(subtract)).subtract(two3);
            ECFieldElement two4 = z ? two(two3.multiply(jacobianModifiedW)) : null;
            if (!eCFieldElement3.isOne()) {
                two = two.multiply(eCFieldElement3);
            }
            return new C13321Fp(getCurve(), subtract, subtract2, new ECFieldElement[]{two, two4});
        }

        @Override // org.bouncycastle.math.p464ec.ECPoint
        public ECPoint twicePlus(ECPoint eCPoint) {
            if (this == eCPoint) {
                return threeTimes();
            }
            if (isInfinity()) {
                return eCPoint;
            }
            if (eCPoint.isInfinity()) {
                return twice();
            }
            ECFieldElement eCFieldElement = this.f26950y;
            if (eCFieldElement.isZero()) {
                return eCPoint;
            }
            ECCurve curve = getCurve();
            int coordinateSystem = curve.getCoordinateSystem();
            if (coordinateSystem != 0) {
                return coordinateSystem != 4 ? twice().add(eCPoint) : twiceJacobianModified(false).add(eCPoint);
            }
            ECFieldElement eCFieldElement2 = this.f26949x;
            ECFieldElement eCFieldElement3 = eCPoint.f26949x;
            ECFieldElement eCFieldElement4 = eCPoint.f26950y;
            ECFieldElement subtract = eCFieldElement3.subtract(eCFieldElement2);
            ECFieldElement subtract2 = eCFieldElement4.subtract(eCFieldElement);
            if (subtract.isZero()) {
                return subtract2.isZero() ? threeTimes() : this;
            }
            ECFieldElement square = subtract.square();
            ECFieldElement subtract3 = square.multiply(two(eCFieldElement2).add(eCFieldElement3)).subtract(subtract2.square());
            if (subtract3.isZero()) {
                return curve.getInfinity();
            }
            ECFieldElement invert = subtract3.multiply(subtract).invert();
            ECFieldElement multiply = subtract3.multiply(invert).multiply(subtract2);
            ECFieldElement subtract4 = two(eCFieldElement).multiply(square).multiply(subtract).multiply(invert).subtract(multiply);
            ECFieldElement add = subtract4.subtract(multiply).multiply(multiply.add(subtract4)).add(eCFieldElement3);
            return new C13321Fp(curve, add, eCFieldElement2.subtract(add).multiply(subtract4).subtract(eCFieldElement));
        }

        protected ECFieldElement two(ECFieldElement eCFieldElement) {
            return eCFieldElement.add(eCFieldElement);
        }
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        this(eCCurve, eCFieldElement, eCFieldElement2, getInitialZCoords(eCCurve));
    }

    protected ECPoint(ECCurve eCCurve, ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr) {
        this.preCompTable = null;
        this.curve = eCCurve;
        this.f26949x = eCFieldElement;
        this.f26950y = eCFieldElement2;
        this.f26951zs = eCFieldElementArr;
    }

    protected static ECFieldElement[] getInitialZCoords(ECCurve eCCurve) {
        int coordinateSystem = eCCurve == null ? 0 : eCCurve.getCoordinateSystem();
        if (coordinateSystem == 0 || coordinateSystem == 5) {
            return EMPTY_ZS;
        }
        ECFieldElement fromBigInteger = eCCurve.fromBigInteger(ECConstants.ONE);
        if (coordinateSystem != 6) {
            switch (coordinateSystem) {
                case 1:
                case 2:
                    break;
                case 3:
                    return new ECFieldElement[]{fromBigInteger, fromBigInteger, fromBigInteger};
                case 4:
                    return new ECFieldElement[]{fromBigInteger, eCCurve.getA()};
                default:
                    throw new IllegalArgumentException("unknown coordinate system");
            }
        }
        return new ECFieldElement[]{fromBigInteger};
    }

    public abstract ECPoint add(ECPoint eCPoint);

    protected void checkNormalized() {
        if (!isNormalized()) {
            throw new IllegalStateException("point not in normal form");
        }
    }

    protected ECPoint createScaledPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().multiply(eCFieldElement2));
    }

    protected abstract ECPoint detach();

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ECPoint) {
            return equals((ECPoint) obj);
        }
        return false;
    }

    public boolean equals(ECPoint eCPoint) {
        ECPoint eCPoint2;
        ECPoint eCPoint3;
        if (eCPoint == null) {
            return false;
        }
        ECCurve curve = getCurve();
        ECCurve curve2 = eCPoint.getCurve();
        boolean z = curve == null;
        boolean z2 = curve2 == null;
        boolean isInfinity = isInfinity();
        boolean isInfinity2 = eCPoint.isInfinity();
        if (isInfinity || isInfinity2) {
            if (isInfinity && isInfinity2) {
                return z || z2 || curve.equals(curve2);
            }
            return false;
        }
        if (!z || !z2) {
            if (!z) {
                if (z2) {
                    eCPoint3 = eCPoint;
                    eCPoint2 = normalize();
                } else if (!curve.equals(curve2)) {
                    return false;
                } else {
                    ECPoint[] eCPointArr = {this, curve.importPoint(eCPoint)};
                    curve.normalizeAll(eCPointArr);
                    eCPoint2 = eCPointArr[0];
                    eCPoint3 = eCPointArr[1];
                }
                return eCPoint2.getXCoord().equals(eCPoint3.getXCoord()) && eCPoint2.getYCoord().equals(eCPoint3.getYCoord());
            }
            eCPoint = eCPoint.normalize();
        }
        eCPoint3 = eCPoint;
        eCPoint2 = this;
        if (eCPoint2.getXCoord().equals(eCPoint3.getXCoord())) {
            return false;
        }
    }

    public ECFieldElement getAffineXCoord() {
        checkNormalized();
        return getXCoord();
    }

    public ECFieldElement getAffineYCoord() {
        checkNormalized();
        return getYCoord();
    }

    protected abstract boolean getCompressionYTilde();

    public ECCurve getCurve() {
        return this.curve;
    }

    protected int getCurveCoordinateSystem() {
        ECCurve eCCurve = this.curve;
        if (eCCurve == null) {
            return 0;
        }
        return eCCurve.getCoordinateSystem();
    }

    public final ECPoint getDetachedPoint() {
        return normalize().detach();
    }

    public byte[] getEncoded(boolean z) {
        if (isInfinity()) {
            return new byte[1];
        }
        ECPoint normalize = normalize();
        byte[] encoded = normalize.getXCoord().getEncoded();
        if (z) {
            byte[] bArr = new byte[encoded.length + 1];
            bArr[0] = (byte) (normalize.getCompressionYTilde() ? 3 : 2);
            System.arraycopy(encoded, 0, bArr, 1, encoded.length);
            return bArr;
        }
        byte[] encoded2 = normalize.getYCoord().getEncoded();
        byte[] bArr2 = new byte[encoded.length + encoded2.length + 1];
        bArr2[0] = 4;
        System.arraycopy(encoded, 0, bArr2, 1, encoded.length);
        System.arraycopy(encoded2, 0, bArr2, encoded.length + 1, encoded2.length);
        return bArr2;
    }

    public final ECFieldElement getRawXCoord() {
        return this.f26949x;
    }

    public final ECFieldElement getRawYCoord() {
        return this.f26950y;
    }

    protected final ECFieldElement[] getRawZCoords() {
        return this.f26951zs;
    }

    public ECFieldElement getXCoord() {
        return this.f26949x;
    }

    public ECFieldElement getYCoord() {
        return this.f26950y;
    }

    public ECFieldElement getZCoord(int i) {
        if (i >= 0) {
            ECFieldElement[] eCFieldElementArr = this.f26951zs;
            if (i < eCFieldElementArr.length) {
                return eCFieldElementArr[i];
            }
        }
        return null;
    }

    public ECFieldElement[] getZCoords() {
        ECFieldElement[] eCFieldElementArr = this.f26951zs;
        int length = eCFieldElementArr.length;
        if (length == 0) {
            return EMPTY_ZS;
        }
        ECFieldElement[] eCFieldElementArr2 = new ECFieldElement[length];
        System.arraycopy(eCFieldElementArr, 0, eCFieldElementArr2, 0, length);
        return eCFieldElementArr2;
    }

    public int hashCode() {
        ECCurve curve = getCurve();
        int i = curve == null ? 0 : ~curve.hashCode();
        if (isInfinity()) {
            return i;
        }
        ECPoint normalize = normalize();
        return (i ^ (normalize.getXCoord().hashCode() * 17)) ^ (normalize.getYCoord().hashCode() * 257);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean implIsValid(final boolean z, final boolean z2) {
        if (isInfinity()) {
            return true;
        }
        return !((ValidityPrecompInfo) getCurve().precompute(this, "bc_validity", new PreCompCallback() { // from class: org.bouncycastle.math.ec.ECPoint.1
            @Override // org.bouncycastle.math.p464ec.PreCompCallback
            public PreCompInfo precompute(PreCompInfo preCompInfo) {
                ValidityPrecompInfo validityPrecompInfo = preCompInfo instanceof ValidityPrecompInfo ? (ValidityPrecompInfo) preCompInfo : null;
                if (validityPrecompInfo == null) {
                    validityPrecompInfo = new ValidityPrecompInfo();
                }
                if (validityPrecompInfo.hasFailed()) {
                    return validityPrecompInfo;
                }
                if (!validityPrecompInfo.hasCurveEquationPassed()) {
                    if (!z && !ECPoint.this.satisfiesCurveEquation()) {
                        validityPrecompInfo.reportFailed();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.reportCurveEquationPassed();
                }
                if (z2 && !validityPrecompInfo.hasOrderPassed()) {
                    if (!ECPoint.this.satisfiesOrder()) {
                        validityPrecompInfo.reportFailed();
                        return validityPrecompInfo;
                    }
                    validityPrecompInfo.reportOrderPassed();
                }
                return validityPrecompInfo;
            }
        })).hasFailed();
    }

    public boolean isInfinity() {
        if (this.f26949x != null && this.f26950y != null) {
            ECFieldElement[] eCFieldElementArr = this.f26951zs;
            if (eCFieldElementArr.length <= 0 || !eCFieldElementArr[0].isZero()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNormalized() {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        return curveCoordinateSystem == 0 || curveCoordinateSystem == 5 || isInfinity() || this.f26951zs[0].isOne();
    }

    public boolean isValid() {
        return implIsValid(false, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isValidPartial() {
        return implIsValid(false, false);
    }

    public ECPoint multiply(BigInteger bigInteger) {
        return getCurve().getMultiplier().multiply(this, bigInteger);
    }

    public abstract ECPoint negate();

    public ECPoint normalize() {
        int curveCoordinateSystem;
        if (isInfinity() || (curveCoordinateSystem = getCurveCoordinateSystem()) == 0 || curveCoordinateSystem == 5) {
            return this;
        }
        ECFieldElement zCoord = getZCoord(0);
        if (zCoord.isOne()) {
            return this;
        }
        if (this.curve != null) {
            ECFieldElement randomFieldElementMult = this.curve.randomFieldElementMult(CryptoServicesRegistrar.getSecureRandom());
            return normalize(zCoord.multiply(randomFieldElementMult).invert().multiply(randomFieldElementMult));
        }
        throw new IllegalStateException("Detached points must be in affine coordinates");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ECPoint normalize(ECFieldElement eCFieldElement) {
        int curveCoordinateSystem = getCurveCoordinateSystem();
        if (curveCoordinateSystem != 6) {
            switch (curveCoordinateSystem) {
                case 1:
                    break;
                case 2:
                case 3:
                case 4:
                    ECFieldElement square = eCFieldElement.square();
                    return createScaledPoint(square, square.multiply(eCFieldElement));
                default:
                    throw new IllegalStateException("not a projective coordinate system");
            }
        }
        return createScaledPoint(eCFieldElement, eCFieldElement);
    }

    protected abstract boolean satisfiesCurveEquation();

    protected boolean satisfiesOrder() {
        BigInteger order;
        return ECConstants.ONE.equals(this.curve.getCofactor()) || (order = this.curve.getOrder()) == null || ECAlgorithms.referenceMultiply(this, order).isInfinity();
    }

    public ECPoint scaleX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord(), getRawZCoords());
    }

    public ECPoint scaleXNegateY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().multiply(eCFieldElement), getRawYCoord().negate(), getRawZCoords());
    }

    public ECPoint scaleY(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord(), getRawYCoord().multiply(eCFieldElement), getRawZCoords());
    }

    public ECPoint scaleYNegateX(ECFieldElement eCFieldElement) {
        return isInfinity() ? this : getCurve().createRawPoint(getRawXCoord().negate(), getRawYCoord().multiply(eCFieldElement), getRawZCoords());
    }

    public abstract ECPoint subtract(ECPoint eCPoint);

    public ECPoint threeTimes() {
        return twicePlus(this);
    }

    public ECPoint timesPow2(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("'e' cannot be negative");
        }
        ECPoint eCPoint = this;
        while (true) {
            i--;
            if (i < 0) {
                return eCPoint;
            }
            eCPoint = eCPoint.twice();
        }
    }

    public String toString() {
        if (isInfinity()) {
            return "INF";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(getRawXCoord());
        stringBuffer.append(',');
        stringBuffer.append(getRawYCoord());
        for (int i = 0; i < this.f26951zs.length; i++) {
            stringBuffer.append(',');
            stringBuffer.append(this.f26951zs[i]);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public abstract ECPoint twice();

    public ECPoint twicePlus(ECPoint eCPoint) {
        return twice().add(eCPoint);
    }
}
