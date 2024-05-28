package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat576;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT571FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecT571FieldElement extends ECFieldElement.AbstractF2m {

    /* renamed from: x */
    protected long[] f27032x;

    public SecT571FieldElement() {
        this.f27032x = Nat576.create64();
    }

    public SecT571FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 571) {
            throw new IllegalArgumentException("x value invalid for SecT571FieldElement");
        }
        this.f27032x = SecT571Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecT571FieldElement(long[] jArr) {
        this.f27032x = jArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] create64 = Nat576.create64();
        SecT571Field.add(this.f27032x, ((SecT571FieldElement) eCFieldElement).f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] create64 = Nat576.create64();
        SecT571Field.addOne(this.f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT571FieldElement) {
            return Nat576.eq64(this.f27032x, ((SecT571FieldElement) obj).f27032x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecT571Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return 571;
    }

    public int getK1() {
        return 2;
    }

    public int getK2() {
        return 5;
    }

    public int getK3() {
        return 10;
    }

    public int getM() {
        return 571;
    }

    public int getRepresentation() {
        return 3;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public ECFieldElement halfTrace() {
        long[] create64 = Nat576.create64();
        SecT571Field.halfTrace(this.f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f27032x, 0, 9) ^ 5711052;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        long[] create64 = Nat576.create64();
        SecT571Field.invert(this.f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat576.isOne64(this.f27032x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat576.isZero64(this.f27032x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] create64 = Nat576.create64();
        SecT571Field.multiply(this.f27032x, ((SecT571FieldElement) eCFieldElement).f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f27032x;
        long[] jArr2 = ((SecT571FieldElement) eCFieldElement).f27032x;
        long[] jArr3 = ((SecT571FieldElement) eCFieldElement2).f27032x;
        long[] jArr4 = ((SecT571FieldElement) eCFieldElement3).f27032x;
        long[] createExt64 = Nat576.createExt64();
        SecT571Field.multiplyAddToExt(jArr, jArr2, createExt64);
        SecT571Field.multiplyAddToExt(jArr3, jArr4, createExt64);
        long[] create64 = Nat576.create64();
        SecT571Field.reduce(createExt64, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] create64 = Nat576.create64();
        SecT571Field.sqrt(this.f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        long[] create64 = Nat576.create64();
        SecT571Field.square(this.f27032x, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f27032x;
        long[] jArr2 = ((SecT571FieldElement) eCFieldElement).f27032x;
        long[] jArr3 = ((SecT571FieldElement) eCFieldElement2).f27032x;
        long[] createExt64 = Nat576.createExt64();
        SecT571Field.squareAddToExt(jArr, createExt64);
        SecT571Field.multiplyAddToExt(jArr2, jArr3, createExt64);
        long[] create64 = Nat576.create64();
        SecT571Field.reduce(createExt64, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] create64 = Nat576.create64();
        SecT571Field.squareN(this.f27032x, i, create64);
        return new SecT571FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f27032x[0] & 1) != 0;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat576.toBigInteger64(this.f27032x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public int trace() {
        return SecT571Field.trace(this.f27032x);
    }
}
