package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0001H\u0087\b\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f\u001a\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\n\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000e\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\u0087\f\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\n\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0001H\u0087\b\u001a!\u0010\u0010\u001a\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\rH\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u0001*\u00020\u0016H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u0001*\u00020\u0001H\u0087\n\u001a\u0015\u0010\u0018\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0087\f¨\u0006\u0019"}, m1890d2 = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", "n", "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/NumbersKt")
/* renamed from: kotlin.NumbersKt__BigIntegersKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BigIntegers extends BigDecimals {
    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger plus, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        BigInteger add = plus.add(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger minus, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(minus, "$this$minus");
        BigInteger subtract = minus.subtract(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger times, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(times, "$this$times");
        BigInteger multiply = times.multiply(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger div, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(div, "$this$div");
        BigInteger divide = div.divide(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other)");
        return divide;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger rem, BigInteger bigInteger) {
        Intrinsics.checkParameterIsNotNull(rem, "$this$rem");
        BigInteger remainder = rem.remainder(bigInteger);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger unaryMinus) {
        Intrinsics.checkParameterIsNotNull(unaryMinus, "$this$unaryMinus");
        BigInteger negate = unaryMinus.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inc(@NotNull BigInteger inc) {
        Intrinsics.checkParameterIsNotNull(inc, "$this$inc");
        BigInteger add = inc.add(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(BigInteger.ONE)");
        return add;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger dec(@NotNull BigInteger dec) {
        Intrinsics.checkParameterIsNotNull(dec, "$this$dec");
        BigInteger subtract = dec.subtract(BigInteger.ONE);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(BigInteger.ONE)");
        return subtract;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger inv(@NotNull BigInteger bigInteger) {
        BigInteger not = bigInteger.not();
        Intrinsics.checkExpressionValueIsNotNull(not, "this.not()");
        return not;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger and(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger and = bigInteger.and(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(and, "this.and(other)");
        return and;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    /* renamed from: or */
    private static final BigInteger m1884or(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger or = bigInteger.or(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(or, "this.or(other)");
        return or;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger xor(@NotNull BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger xor = bigInteger.xor(bigInteger2);
        Intrinsics.checkExpressionValueIsNotNull(xor, "this.xor(other)");
        return xor;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shl(@NotNull BigInteger bigInteger, int i) {
        BigInteger shiftLeft = bigInteger.shiftLeft(i);
        Intrinsics.checkExpressionValueIsNotNull(shiftLeft, "this.shiftLeft(n)");
        return shiftLeft;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger shr(@NotNull BigInteger bigInteger, int i) {
        BigInteger shiftRight = bigInteger.shiftRight(i);
        Intrinsics.checkExpressionValueIsNotNull(shiftRight, "this.shiftRight(n)");
        return shiftRight;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(int i) {
        BigInteger valueOf = BigInteger.valueOf(i);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this.toLong())");
        return valueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigInteger toBigInteger(long j) {
        BigInteger valueOf = BigInteger.valueOf(j);
        Intrinsics.checkExpressionValueIsNotNull(valueOf, "BigInteger.valueOf(this)");
        return valueOf;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger) {
        return new BigDecimal(bigInteger);
    }

    static /* synthetic */ BigDecimal toBigDecimal$default(BigInteger bigInteger, int i, MathContext mathContext, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            mathContext = MathContext.UNLIMITED;
            Intrinsics.checkExpressionValueIsNotNull(mathContext, "MathContext.UNLIMITED");
        }
        return new BigDecimal(bigInteger, i, mathContext);
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final BigDecimal toBigDecimal(@NotNull BigInteger bigInteger, int i, MathContext mathContext) {
        return new BigDecimal(bigInteger, i, mathContext);
    }
}
