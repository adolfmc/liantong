package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecT193FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecT193FieldElement extends ECFieldElement.AbstractF2m {

    /* renamed from: x */
    protected long[] f27027x;

    public SecT193FieldElement() {
        this.f27027x = Nat256.create64();
    }

    public SecT193FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.bitLength() > 193) {
            throw new IllegalArgumentException("x value invalid for SecT193FieldElement");
        }
        this.f27027x = SecT193Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecT193FieldElement(long[] jArr) {
        this.f27027x = jArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        long[] create64 = Nat256.create64();
        SecT193Field.add(this.f27027x, ((SecT193FieldElement) eCFieldElement).f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        long[] create64 = Nat256.create64();
        SecT193Field.addOne(this.f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        return multiply(eCFieldElement.invert());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecT193FieldElement) {
            return Nat256.eq64(this.f27027x, ((SecT193FieldElement) obj).f27027x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecT193Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return 193;
    }

    public int getK1() {
        return 15;
    }

    public int getK2() {
        return 0;
    }

    public int getK3() {
        return 0;
    }

    public int getM() {
        return 193;
    }

    public int getRepresentation() {
        return 2;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public ECFieldElement halfTrace() {
        long[] create64 = Nat256.create64();
        SecT193Field.halfTrace(this.f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public boolean hasFastTrace() {
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(this.f27027x, 0, 4) ^ 1930015;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        long[] create64 = Nat256.create64();
        SecT193Field.invert(this.f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat256.isOne64(this.f27027x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat256.isZero64(this.f27027x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        long[] create64 = Nat256.create64();
        SecT193Field.multiply(this.f27027x, ((SecT193FieldElement) eCFieldElement).f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        return multiplyPlusProduct(eCFieldElement, eCFieldElement2, eCFieldElement3);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiplyPlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement eCFieldElement3) {
        long[] jArr = this.f27027x;
        long[] jArr2 = ((SecT193FieldElement) eCFieldElement).f27027x;
        long[] jArr3 = ((SecT193FieldElement) eCFieldElement2).f27027x;
        long[] jArr4 = ((SecT193FieldElement) eCFieldElement3).f27027x;
        long[] createExt64 = Nat256.createExt64();
        SecT193Field.multiplyAddToExt(jArr, jArr2, createExt64);
        SecT193Field.multiplyAddToExt(jArr3, jArr4, createExt64);
        long[] create64 = Nat256.create64();
        SecT193Field.reduce(createExt64, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        return this;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        long[] create64 = Nat256.create64();
        SecT193Field.sqrt(this.f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        long[] create64 = Nat256.create64();
        SecT193Field.square(this.f27027x, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squareMinusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        return squarePlusProduct(eCFieldElement, eCFieldElement2);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePlusProduct(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2) {
        long[] jArr = this.f27027x;
        long[] jArr2 = ((SecT193FieldElement) eCFieldElement).f27027x;
        long[] jArr3 = ((SecT193FieldElement) eCFieldElement2).f27027x;
        long[] createExt64 = Nat256.createExt64();
        SecT193Field.squareAddToExt(jArr, createExt64);
        SecT193Field.multiplyAddToExt(jArr2, jArr3, createExt64);
        long[] create64 = Nat256.create64();
        SecT193Field.reduce(createExt64, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement squarePow(int i) {
        if (i < 1) {
            return this;
        }
        long[] create64 = Nat256.create64();
        SecT193Field.squareN(this.f27027x, i, create64);
        return new SecT193FieldElement(create64);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        return add(eCFieldElement);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return (this.f27027x[0] & 1) != 0;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger64(this.f27027x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement.AbstractF2m
    public int trace() {
        return SecT193Field.trace(this.f27027x);
    }
}
