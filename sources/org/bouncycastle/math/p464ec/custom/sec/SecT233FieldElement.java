package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT233FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecT233FieldElement extends ECFieldElement.AbstractF2m {

    /* renamed from: x */
    protected long[] f27028x;

    public SecT233FieldElement() {
        this.f27028x = Nat256.create64();
    }

    public SecT233FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 233) {
            throw new IllegalArgumentException("x value invalid for SecT233FieldElement");
        }
        this.f27028x = SecT233Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecT233FieldElement(long[] jArr) {
        this.f27028x = jArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] create64 = Nat256.create64();
        SecT233Field.add(this.f27028x, ((SecT233FieldElement) eCFieldElement).f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] create64 = Nat256.create64();
        SecT233Field.addOne(this.f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT233FieldElement) {
            return Nat256.eq64(this.f27028x, ((SecT233FieldElement) obj).f27028x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecT233Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return 233;
    }

    public int getK1() {
        return 74;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 233;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public ECFieldElement halfTrace() {
        long[] create64 = Nat256.create64();
        SecT233Field.halfTrace(this.f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f27028x, 0, 4) ^ 2330074;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        long[] create64 = Nat256.create64();
        SecT233Field.invert(this.f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat256.isOne64(this.f27028x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat256.isZero64(this.f27028x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] create64 = Nat256.create64();
        SecT233Field.multiply(this.f27028x, ((SecT233FieldElement) eCFieldElement).f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f27028x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f27028x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f27028x;
        long[] jArr4 = ((SecT233FieldElement) eCFieldElement3).f27028x;
        long[] createExt64 = Nat256.createExt64();
        SecT233Field.multiplyAddToExt(jArr, jArr2, createExt64);
        SecT233Field.multiplyAddToExt(jArr3, jArr4, createExt64);
        long[] create64 = Nat256.create64();
        SecT233Field.reduce(createExt64, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] create64 = Nat256.create64();
        SecT233Field.sqrt(this.f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        long[] create64 = Nat256.create64();
        SecT233Field.square(this.f27028x, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f27028x;
        long[] jArr2 = ((SecT233FieldElement) eCFieldElement).f27028x;
        long[] jArr3 = ((SecT233FieldElement) eCFieldElement2).f27028x;
        long[] createExt64 = Nat256.createExt64();
        SecT233Field.squareAddToExt(jArr, createExt64);
        SecT233Field.multiplyAddToExt(jArr2, jArr3, createExt64);
        long[] create64 = Nat256.create64();
        SecT233Field.reduce(createExt64, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] create64 = Nat256.create64();
        SecT233Field.squareN(this.f27028x, i, create64);
        return new SecT233FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f27028x[0] & 1) != 0;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f27028x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public int trace() {
        return SecT233Field.trace(this.f27028x);
    }
}
