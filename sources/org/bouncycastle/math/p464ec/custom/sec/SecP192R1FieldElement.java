package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat192;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP192R1FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP192R1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: Q */
    public static final BigInteger f26991Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFFFFFFFFFFFFF"));

    /* renamed from: x */
    protected int[] f26992x;

    public SecP192R1FieldElement() {
        this.f26992x = Nat192.create();
    }

    public SecP192R1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26991Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP192R1FieldElement");
        }
        this.f26992x = SecP192R1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP192R1FieldElement(int[] iArr) {
        this.f26992x = iArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.add(this.f26992x, ((SecP192R1FieldElement) eCFieldElement).f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat192.create();
        SecP192R1Field.addOne(this.f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.inv(((SecP192R1FieldElement) eCFieldElement).f26992x, create);
        SecP192R1Field.multiply(create, this.f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP192R1FieldElement) {
            return Nat192.m257eq(this.f26992x, ((SecP192R1FieldElement) obj).f26992x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecP192R1Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return f26991Q.bitLength();
    }

    public int hashCode() {
        return f26991Q.hashCode() ^ Arrays.hashCode(this.f26992x, 0, 6);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat192.create();
        SecP192R1Field.inv(this.f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat192.isOne(this.f26992x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat192.isZero(this.f26992x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.multiply(this.f26992x, ((SecP192R1FieldElement) eCFieldElement).f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat192.create();
        SecP192R1Field.negate(this.f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f26992x;
        if (Nat192.isZero(iArr) || Nat192.isOne(iArr)) {
            return this;
        }
        int[] create = Nat192.create();
        int[] create2 = Nat192.create();
        SecP192R1Field.square(iArr, create);
        SecP192R1Field.multiply(create, iArr, create);
        SecP192R1Field.squareN(create, 2, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 4, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 8, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 16, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 32, create2);
        SecP192R1Field.multiply(create2, create, create2);
        SecP192R1Field.squareN(create2, 64, create);
        SecP192R1Field.multiply(create, create2, create);
        SecP192R1Field.squareN(create, 62, create);
        SecP192R1Field.square(create, create2);
        if (Nat192.m257eq(iArr, create2)) {
            return new SecP192R1FieldElement(create);
        }
        return null;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat192.create();
        SecP192R1Field.square(this.f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat192.create();
        SecP192R1Field.subtract(this.f26992x, ((SecP192R1FieldElement) eCFieldElement).f26992x, create);
        return new SecP192R1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return Nat192.getBit(this.f26992x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat192.toBigInteger(this.f26992x);
    }
}
