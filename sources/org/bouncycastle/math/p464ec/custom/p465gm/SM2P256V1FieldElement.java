package org.bouncycastle.math.p464ec.custom.p465gm;

import java.math.BigInteger;
import org.bouncycastle.math.p464ec.ECFieldElement;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.custom.gm.SM2P256V1FieldElement */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SM2P256V1FieldElement extends ECFieldElement.AbstractFp {

    /* renamed from: Q */
    public static final BigInteger f26963Q = new BigInteger(1, Hex.decodeStrict("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF"));

    /* renamed from: x */
    protected int[] f26964x;

    public SM2P256V1FieldElement() {
        this.f26964x = Nat256.create();
    }

    public SM2P256V1FieldElement(BigInteger bigInteger) {
        if (bigInteger == null || bigInteger.signum() < 0 || bigInteger.compareTo(f26963Q) >= 0) {
            throw new IllegalArgumentException("x value invalid for SM2P256V1FieldElement");
        }
        this.f26964x = SM2P256V1Field.fromBigInteger(bigInteger);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SM2P256V1FieldElement(int[] iArr) {
        this.f26964x = iArr;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement add(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SM2P256V1Field.add(this.f26964x, ((SM2P256V1FieldElement) eCFieldElement).f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement addOne() {
        int[] create = Nat256.create();
        SM2P256V1Field.addOne(this.f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement divide(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SM2P256V1Field.inv(((SM2P256V1FieldElement) eCFieldElement).f26964x, create);
        SM2P256V1Field.multiply(create, this.f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SM2P256V1FieldElement) {
            return Nat256.m255eq(this.f26964x, ((SM2P256V1FieldElement) obj).f26964x);
        }
        return false;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public String getFieldName() {
        return "SM2P256V1Field";
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public int getFieldSize() {
        return f26963Q.bitLength();
    }

    public int hashCode() {
        return f26963Q.hashCode() ^ Arrays.hashCode(this.f26964x, 0, 8);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement invert() {
        int[] create = Nat256.create();
        SM2P256V1Field.inv(this.f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isOne() {
        return Nat256.isOne(this.f26964x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean isZero() {
        return Nat256.isZero(this.f26964x);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement multiply(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SM2P256V1Field.multiply(this.f26964x, ((SM2P256V1FieldElement) eCFieldElement).f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement negate() {
        int[] create = Nat256.create();
        SM2P256V1Field.negate(this.f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement sqrt() {
        int[] iArr = this.f26964x;
        if (Nat256.isZero(iArr) || Nat256.isOne(iArr)) {
            return this;
        }
        int[] create = Nat256.create();
        SM2P256V1Field.square(iArr, create);
        SM2P256V1Field.multiply(create, iArr, create);
        int[] create2 = Nat256.create();
        SM2P256V1Field.squareN(create, 2, create2);
        SM2P256V1Field.multiply(create2, create, create2);
        int[] create3 = Nat256.create();
        SM2P256V1Field.squareN(create2, 2, create3);
        SM2P256V1Field.multiply(create3, create, create3);
        SM2P256V1Field.squareN(create3, 6, create);
        SM2P256V1Field.multiply(create, create3, create);
        int[] create4 = Nat256.create();
        SM2P256V1Field.squareN(create, 12, create4);
        SM2P256V1Field.multiply(create4, create, create4);
        SM2P256V1Field.squareN(create4, 6, create);
        SM2P256V1Field.multiply(create, create3, create);
        SM2P256V1Field.square(create, create3);
        SM2P256V1Field.multiply(create3, iArr, create3);
        SM2P256V1Field.squareN(create3, 31, create4);
        SM2P256V1Field.multiply(create4, create3, create);
        SM2P256V1Field.squareN(create4, 32, create4);
        SM2P256V1Field.multiply(create4, create, create4);
        SM2P256V1Field.squareN(create4, 62, create4);
        SM2P256V1Field.multiply(create4, create, create4);
        SM2P256V1Field.squareN(create4, 4, create4);
        SM2P256V1Field.multiply(create4, create2, create4);
        SM2P256V1Field.squareN(create4, 32, create4);
        SM2P256V1Field.multiply(create4, iArr, create4);
        SM2P256V1Field.squareN(create4, 62, create4);
        SM2P256V1Field.square(create4, create2);
        if (Nat256.m255eq(iArr, create2)) {
            return new SM2P256V1FieldElement(create4);
        }
        return null;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement square() {
        int[] create = Nat256.create();
        SM2P256V1Field.square(this.f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public ECFieldElement subtract(ECFieldElement eCFieldElement) {
        int[] create = Nat256.create();
        SM2P256V1Field.subtract(this.f26964x, ((SM2P256V1FieldElement) eCFieldElement).f26964x, create);
        return new SM2P256V1FieldElement(create);
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public boolean testBitZero() {
        return Nat256.getBit(this.f26964x, 0) == 1;
    }

    @Override // org.bouncycastle.math.p464ec.ECFieldElement
    public BigInteger toBigInteger() {
        return Nat256.toBigInteger(this.f26964x);
    }
}
