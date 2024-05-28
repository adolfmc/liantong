package org.bouncycastle.math.p464ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat224;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.sec.SecP224K1FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SecP224K1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: x */
    protected int[] f26997x;

    /* renamed from: Q */
    public static final BigInteger f26996Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFEFFFFE56D"));
    private static final int[] PRECOMP_POW2 = {868209154, -587542221, 579297866, -1014948952, -1470801668, 514782679, -1897982644};

    public SecP224K1FieldElement() {
        this.f26997x = Nat224.create();
    }

    public SecP224K1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26996Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SecP224K1FieldElement");
        }
        this.f26997x = SecP224K1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SecP224K1FieldElement(int[] iArr) {
        this.f26997x = iArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224K1Field.add(this.f26997x, ((SecP224K1FieldElement) eCFieldElement).f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat224.create();
        SecP224K1Field.addOne(this.f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224K1Field.inv(((SecP224K1FieldElement) eCFieldElement).f26997x, create);
        SecP224K1Field.multiply(create, this.f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SecP224K1FieldElement) {
            return Nat224.m256eq(this.f26997x, ((SecP224K1FieldElement) obj).f26997x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SecP224K1Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return f26996Q.bitLength();
    }

    public int hashCode() {
        return f26996Q.hashCode() ^ Arrays.hashCode(this.f26997x, 0, 7);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat224.create();
        SecP224K1Field.inv(this.f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat224.isOne(this.f26997x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat224.isZero(this.f26997x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224K1Field.multiply(this.f26997x, ((SecP224K1FieldElement) eCFieldElement).f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat224.create();
        SecP224K1Field.negate(this.f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f26997x;
        if (Nat224.isZero(iArr) || Nat224.isOne(iArr)) {
            return this;
        }
        int[] create = Nat224.create();
        SecP224K1Field.square(iArr, create);
        SecP224K1Field.multiply(create, iArr, create);
        SecP224K1Field.square(create, create);
        SecP224K1Field.multiply(create, iArr, create);
        int[] create2 = Nat224.create();
        SecP224K1Field.square(create, create2);
        SecP224K1Field.multiply(create2, iArr, create2);
        int[] create3 = Nat224.create();
        SecP224K1Field.squareN(create2, 4, create3);
        SecP224K1Field.multiply(create3, create2, create3);
        int[] create4 = Nat224.create();
        SecP224K1Field.squareN(create3, 3, create4);
        SecP224K1Field.multiply(create4, create, create4);
        SecP224K1Field.squareN(create4, 8, create4);
        SecP224K1Field.multiply(create4, create3, create4);
        SecP224K1Field.squareN(create4, 4, create3);
        SecP224K1Field.multiply(create3, create2, create3);
        SecP224K1Field.squareN(create3, 19, create2);
        SecP224K1Field.multiply(create2, create4, create2);
        int[] create5 = Nat224.create();
        SecP224K1Field.squareN(create2, 42, create5);
        SecP224K1Field.multiply(create5, create2, create5);
        SecP224K1Field.squareN(create5, 23, create2);
        SecP224K1Field.multiply(create2, create3, create2);
        SecP224K1Field.squareN(create2, 84, create3);
        SecP224K1Field.multiply(create3, create5, create3);
        SecP224K1Field.squareN(create3, 20, create3);
        SecP224K1Field.multiply(create3, create4, create3);
        SecP224K1Field.squareN(create3, 3, create3);
        SecP224K1Field.multiply(create3, iArr, create3);
        SecP224K1Field.squareN(create3, 2, create3);
        SecP224K1Field.multiply(create3, iArr, create3);
        SecP224K1Field.squareN(create3, 4, create3);
        SecP224K1Field.multiply(create3, create, create3);
        SecP224K1Field.square(create3, create3);
        SecP224K1Field.square(create3, create5);
        if (Nat224.m256eq(iArr, create5)) {
            return new SecP224K1FieldElement(create3);
        }
        SecP224K1Field.multiply(create3, PRECOMP_POW2, create3);
        SecP224K1Field.square(create3, create5);
        if (Nat224.m256eq(iArr, create5)) {
            return new SecP224K1FieldElement(create3);
        }
        return null;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat224.create();
        SecP224K1Field.square(this.f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat224.create();
        SecP224K1Field.subtract(this.f26997x, ((SecP224K1FieldElement) eCFieldElement).f26997x, create);
        return new SecP224K1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return Nat224.getBit(this.f26997x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat224.toBigInteger(this.f26997x);
    }
}
