package cn.sharesdk.framework.utils;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class UnicodeEscaper implements Escaper {
    /* renamed from: a */
    protected abstract char[] mo21674a(int i);

    /* renamed from: a */
    protected int mo21673a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int m21670b = m21670b(charSequence, i, i2);
            if (m21670b < 0 || mo21674a(m21670b) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(m21670b) ? 2 : 1;
        }
        return i;
    }

    @Override // cn.sharesdk.framework.utils.Escaper
    public String escape(String str) {
        int length = str.length();
        int mo21673a = mo21673a(str, 0, length);
        return mo21673a == length ? str : m21672a(str, mo21673a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final String m21672a(String str, int i) {
        int i2;
        int length = str.length();
        char[] cArr = new C1785a().get();
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int m21670b = m21670b(str, i, length);
            if (m21670b < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] mo21674a = mo21674a(m21670b);
            if (mo21674a != null) {
                int i5 = i - i3;
                int i6 = i4 + i5;
                int length2 = mo21674a.length + i6;
                if (cArr.length < length2) {
                    cArr = m21671a(cArr, i4, length2 + (length - i) + 32);
                }
                if (i5 > 0) {
                    str.getChars(i3, i, cArr, i4);
                    i4 = i6;
                }
                if (mo21674a.length > 0) {
                    System.arraycopy(mo21674a, 0, cArr, i4, mo21674a.length);
                    i4 += mo21674a.length;
                }
            }
            i3 = (Character.isSupplementaryCodePoint(m21670b) ? 2 : 1) + i;
            i = mo21673a(str, i3, length);
        }
        int i7 = length - i3;
        if (i7 > 0) {
            i2 = i7 + i4;
            if (cArr.length < i2) {
                cArr = m21671a(cArr, i4, i2);
            }
            str.getChars(i3, length, cArr, i4);
        } else {
            i2 = i4;
        }
        return new String(cArr, 0, i2);
    }

    @Override // cn.sharesdk.framework.utils.Escaper
    public Appendable escape(final Appendable appendable) {
        Preconditions.m21692a(appendable);
        return new Appendable() { // from class: cn.sharesdk.framework.utils.k.1

            /* renamed from: a */
            int f2977a = -1;

            /* renamed from: b */
            char[] f2978b = new char[2];

            @Override // java.lang.Appendable
            public Appendable append(CharSequence charSequence) throws IOException {
                return append(charSequence, 0, charSequence.length());
            }

            @Override // java.lang.Appendable
            public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
                int i3;
                if (i < i2) {
                    if (this.f2977a != -1) {
                        char charAt = charSequence.charAt(i);
                        int i4 = i + 1;
                        if (!Character.isLowSurrogate(charAt)) {
                            throw new IllegalArgumentException("Expected low surrogate character but got " + charAt);
                        }
                        char[] mo21674a = UnicodeEscaper.this.mo21674a(Character.toCodePoint((char) this.f2977a, charAt));
                        if (mo21674a != null) {
                            m21669a(mo21674a, mo21674a.length);
                            i = i4;
                        } else {
                            appendable.append((char) this.f2977a);
                        }
                        this.f2977a = -1;
                        i3 = i;
                        i = i4;
                    } else {
                        i3 = i;
                    }
                    while (true) {
                        int mo21673a = UnicodeEscaper.this.mo21673a(charSequence, i, i2);
                        if (mo21673a > i3) {
                            appendable.append(charSequence, i3, mo21673a);
                        }
                        if (mo21673a == i2) {
                            break;
                        }
                        int m21670b = UnicodeEscaper.m21670b(charSequence, mo21673a, i2);
                        if (m21670b < 0) {
                            this.f2977a = -m21670b;
                            break;
                        }
                        char[] mo21674a2 = UnicodeEscaper.this.mo21674a(m21670b);
                        if (mo21674a2 != null) {
                            m21669a(mo21674a2, mo21674a2.length);
                        } else {
                            m21669a(this.f2978b, Character.toChars(m21670b, this.f2978b, 0));
                        }
                        i3 = (Character.isSupplementaryCodePoint(m21670b) ? 2 : 1) + mo21673a;
                        i = i3;
                    }
                }
                return this;
            }

            @Override // java.lang.Appendable
            public Appendable append(char c) throws IOException {
                if (this.f2977a != -1) {
                    if (!Character.isLowSurrogate(c)) {
                        throw new IllegalArgumentException("Expected low surrogate character but got '" + c + "' with value " + ((int) c));
                    }
                    char[] mo21674a = UnicodeEscaper.this.mo21674a(Character.toCodePoint((char) this.f2977a, c));
                    if (mo21674a != null) {
                        m21669a(mo21674a, mo21674a.length);
                    } else {
                        appendable.append((char) this.f2977a);
                        appendable.append(c);
                    }
                    this.f2977a = -1;
                } else if (Character.isHighSurrogate(c)) {
                    this.f2977a = c;
                } else if (Character.isLowSurrogate(c)) {
                    throw new IllegalArgumentException("Unexpected low surrogate character '" + c + "' with value " + ((int) c));
                } else {
                    char[] mo21674a2 = UnicodeEscaper.this.mo21674a(c);
                    if (mo21674a2 != null) {
                        m21669a(mo21674a2, mo21674a2.length);
                    } else {
                        appendable.append(c);
                    }
                }
                return this;
            }

            /* renamed from: a */
            private void m21669a(char[] cArr, int i) throws IOException {
                for (int i2 = 0; i2 < i; i2++) {
                    appendable.append(cArr[i2]);
                }
            }
        };
    }

    /* renamed from: b */
    protected static final int m21670b(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            char charAt = charSequence.charAt(i);
            int i3 = i + 1;
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                throw new IllegalArgumentException(sb.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + ((int) charAt2) + " at index " + i3);
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    /* renamed from: a */
    private static final char[] m21671a(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: UnicodeEscaper.java */
    /* renamed from: cn.sharesdk.framework.utils.k$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class C1785a extends ThreadLocal<char[]> {
        private C1785a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public char[] initialValue() {
            return new char[1024];
        }
    }
}
