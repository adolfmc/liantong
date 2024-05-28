package com.mob.commons.p231cc;

/* renamed from: com.mob.commons.cc.x */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5827x {

    /* renamed from: a */
    private C5828a f14345a;

    public C5827x(Number number, Number number2, Number number3) {
        this.f14345a = new C5828a(number, number2, number3);
    }

    /* renamed from: a */
    public C5828a m12340a() {
        return this.f14345a;
    }

    /* renamed from: a */
    public boolean m12339a(Number number) {
        return ((Comparable) this.f14345a.f14346a).compareTo(number) <= 0 && ((Comparable) this.f14345a.f14347b).compareTo(number) >= 0;
    }

    /* renamed from: b */
    public boolean m12337b(Number number) {
        return m12339a(number);
    }

    /* renamed from: b */
    public Number[] m12338b() {
        return new Number[]{this.f14345a.f14346a, this.f14345a.f14347b};
    }

    /* renamed from: com.mob.commons.cc.x$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5828a {

        /* renamed from: a */
        private Number f14346a;

        /* renamed from: b */
        private Number f14347b;

        /* renamed from: c */
        private Number f14348c;

        /* renamed from: d */
        private Number f14349d;

        /* renamed from: e */
        private boolean f14350e;

        public C5828a(Number number, Number number2, Number number3) {
            Number valueOf;
            Number valueOf2;
            Number[] numberArr = {number, number2, number3};
            int[] iArr = {0, 0, 0};
            for (int i = 0; i < numberArr.length; i++) {
                Number number4 = numberArr[i];
                if (number4 != null) {
                    if (number4 instanceof Byte) {
                        iArr[i] = 1;
                    } else if (number4 instanceof Short) {
                        iArr[i] = 2;
                    } else if (number4 instanceof Integer) {
                        iArr[i] = 3;
                    } else if (number4 instanceof Long) {
                        iArr[i] = 4;
                    } else if (number4 instanceof Float) {
                        iArr[i] = 5;
                    } else if (number4 instanceof Double) {
                        iArr[i] = 6;
                    }
                }
            }
            int i2 = 0;
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (i2 < iArr[i3]) {
                    i2 = iArr[i3];
                }
            }
            if (number == null) {
                valueOf = new Number[]{Integer.MIN_VALUE, Byte.MIN_VALUE, Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.valueOf(Float.MIN_VALUE), Double.valueOf(Double.MIN_VALUE)}[i2];
            } else {
                switch (i2) {
                    case 1:
                        valueOf = Byte.valueOf(Double.valueOf(String.valueOf(number)).byteValue());
                        break;
                    case 2:
                        valueOf = Short.valueOf(Double.valueOf(String.valueOf(number)).shortValue());
                        break;
                    case 3:
                        valueOf = Integer.valueOf(Double.valueOf(String.valueOf(number)).intValue());
                        break;
                    case 4:
                        valueOf = Long.valueOf(Double.valueOf(String.valueOf(number)).longValue());
                        break;
                    case 5:
                        valueOf = Float.valueOf(Double.valueOf(String.valueOf(number)).floatValue());
                        break;
                    case 6:
                        valueOf = Double.valueOf(String.valueOf(number));
                        break;
                    default:
                        valueOf = number;
                        break;
                }
            }
            if (number2 == null) {
                valueOf2 = new Number[]{Integer.MAX_VALUE, Byte.MAX_VALUE, Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.valueOf(Float.MAX_VALUE), Double.valueOf(Double.MAX_VALUE)}[i2];
            } else {
                switch (i2) {
                    case 1:
                        valueOf2 = Byte.valueOf(Double.valueOf(String.valueOf(number2)).byteValue());
                        break;
                    case 2:
                        valueOf2 = Short.valueOf(Double.valueOf(String.valueOf(number2)).shortValue());
                        break;
                    case 3:
                        valueOf2 = Integer.valueOf(Double.valueOf(String.valueOf(number2)).intValue());
                        break;
                    case 4:
                        valueOf2 = Long.valueOf(Double.valueOf(String.valueOf(number2)).longValue());
                        break;
                    case 5:
                        valueOf2 = Float.valueOf(Double.valueOf(String.valueOf(number2)).floatValue());
                        break;
                    case 6:
                        valueOf2 = Double.valueOf(String.valueOf(number2));
                        break;
                    default:
                        valueOf2 = number2;
                        break;
                }
            }
            this.f14346a = valueOf;
            this.f14347b = valueOf2;
            this.f14348c = number3;
            this.f14350e = ((Comparable) valueOf).compareTo(valueOf2) > 0;
            if (this.f14348c == null) {
                this.f14348c = Integer.valueOf(this.f14350e ? -1 : 1);
            }
        }

        /* renamed from: a */
        public boolean m12336a() {
            Number number = this.f14349d;
            if (number == null) {
                number = this.f14346a;
            }
            return this.f14350e ? ((Comparable) number).compareTo(this.f14347b) >= 0 : ((Comparable) number).compareTo(this.f14347b) <= 0;
        }

        /* renamed from: b */
        public Number m12334b() {
            if (this.f14349d == null) {
                this.f14349d = this.f14346a;
            }
            Number number = this.f14349d;
            Number number2 = this.f14348c;
            if (number2 instanceof Double) {
                this.f14349d = Double.valueOf(number.doubleValue() + this.f14348c.doubleValue());
            } else if (number2 instanceof Float) {
                this.f14349d = Float.valueOf(number.floatValue() + this.f14348c.floatValue());
            } else if (number2 instanceof Long) {
                this.f14349d = Long.valueOf(number.longValue() + this.f14348c.longValue());
            } else if (number2 instanceof Integer) {
                this.f14349d = Integer.valueOf(number.intValue() + this.f14348c.intValue());
            } else if (number2 instanceof Short) {
                this.f14349d = Integer.valueOf(number.shortValue() + this.f14348c.shortValue());
            } else {
                this.f14349d = Integer.valueOf(number.byteValue() + this.f14348c.byteValue());
            }
            return number;
        }
    }
}
