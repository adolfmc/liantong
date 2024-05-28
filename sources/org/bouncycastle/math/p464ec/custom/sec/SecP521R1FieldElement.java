package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP521R1FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP521R1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: Q */
    public static final BigInteger f27022Q = new BigInteger(1, Hex.decodeStrict("01FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF"));

    /* renamed from: x */
    protected int[] f27023x;

    public SecP521R1FieldElement() {
        this.f27023x = Nat.create(17);
    }

    public SecP521R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f27022Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP521R1FieldElement");
        }
        this.f27023x = SecP521R1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP521R1FieldElement(int[] iArr) {
        this.f27023x = iArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.add(this.f27023x, ((SecP521R1FieldElement) eCFieldElement).f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat.create(17);
        SecP521R1Field.addOne(this.f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.inv(((SecP521R1FieldElement) eCFieldElement).f27023x, create);
        SecP521R1Field.multiply(create, this.f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP521R1FieldElement) {
            return Nat.m260eq(17, this.f27023x, ((SecP521R1FieldElement) obj).f27023x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecP521R1Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return f27022Q.bitLength();
    }

    public int hashCode() {
        return f27022Q.hashCode() ^ Arrays.hashCode(this.f27023x, 0, 17);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat.create(17);
        SecP521R1Field.inv(this.f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat.isOne(17, this.f27023x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat.isZero(17, this.f27023x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.multiply(this.f27023x, ((SecP521R1FieldElement) eCFieldElement).f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat.create(17);
        SecP521R1Field.negate(this.f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f27023x;
        if (Nat.isZero(17, iArr) || Nat.isOne(17, iArr)) {
            return this;
        }
        int[] create = Nat.create(33);
        int[] create2 = Nat.create(17);
        int[] create3 = Nat.create(17);
        SecP521R1Field.squareN(iArr, 519, create2, create);
        SecP521R1Field.square(create2, create3, create);
        if (Nat.m260eq(17, iArr, create3)) {
            return new SecP521R1FieldElement(create2);
        }
        return null;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat.create(17);
        SecP521R1Field.square(this.f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat.create(17);
        SecP521R1Field.subtract(this.f27023x, ((SecP521R1FieldElement) eCFieldElement).f27023x, create);
        return new SecP521R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return Nat.getBit(this.f27023x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat.toBigInteger(17, this.f27023x);
    }
}
