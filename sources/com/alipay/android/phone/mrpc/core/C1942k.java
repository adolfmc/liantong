package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1942k {

    /* renamed from: a */
    private static final Pattern f3414a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");

    /* renamed from: b */
    private static final Pattern f3415b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.android.phone.mrpc.core.k$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1943a {

        /* renamed from: a */
        int f3416a;

        /* renamed from: b */
        int f3417b;

        /* renamed from: c */
        int f3418c;

        C1943a(int i, int i2, int i3) {
            this.f3416a = i;
            this.f3417b = i2;
            this.f3418c = i3;
        }
    }

    /* renamed from: a */
    public static long m21113a(String str) {
        int m21110d;
        int i;
        int i2;
        C1943a c1943a;
        int i3;
        int i4;
        int i5;
        Matcher matcher = f3414a.matcher(str);
        if (matcher.find()) {
            int m21112b = m21112b(matcher.group(1));
            int m21111c = m21111c(matcher.group(2));
            int m21110d2 = m21110d(matcher.group(3));
            c1943a = m21109e(matcher.group(4));
            i = m21111c;
            i2 = m21112b;
            m21110d = m21110d2;
        } else {
            Matcher matcher2 = f3415b.matcher(str);
            if (!matcher2.find()) {
                throw new IllegalArgumentException();
            }
            int m21111c2 = m21111c(matcher2.group(1));
            int m21112b2 = m21112b(matcher2.group(2));
            C1943a m21109e = m21109e(matcher2.group(3));
            m21110d = m21110d(matcher2.group(4));
            i = m21111c2;
            i2 = m21112b2;
            c1943a = m21109e;
        }
        if (m21110d >= 2038) {
            i5 = 0;
            i3 = 2038;
            i4 = 1;
        } else {
            i3 = m21110d;
            i4 = i2;
            i5 = i;
        }
        Time time = new Time("UTC");
        time.set(c1943a.f3418c, c1943a.f3417b, c1943a.f3416a, i4, i5, i3);
        return time.toMillis(false);
    }

    /* renamed from: b */
    private static int m21112b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0') : str.charAt(0) - '0';
    }

    /* renamed from: c */
    private static int m21111c(String str) {
        int lowerCase = ((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291;
        if (lowerCase != 22) {
            if (lowerCase != 26) {
                if (lowerCase != 29) {
                    if (lowerCase != 32) {
                        if (lowerCase != 40) {
                            if (lowerCase != 42) {
                                if (lowerCase != 48) {
                                    switch (lowerCase) {
                                        case 9:
                                            return 11;
                                        case 10:
                                            return 1;
                                        default:
                                            switch (lowerCase) {
                                                case 35:
                                                    return 9;
                                                case 36:
                                                    return 4;
                                                case 37:
                                                    return 8;
                                                default:
                                                    throw new IllegalArgumentException();
                                            }
                                    }
                                }
                                return 10;
                            }
                            return 5;
                        }
                        return 6;
                    }
                    return 3;
                }
                return 2;
            }
            return 7;
        }
        return 0;
    }

    /* renamed from: d */
    private static int m21110d(String str) {
        if (str.length() == 2) {
            int charAt = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
            return charAt >= 70 ? charAt + 1900 : charAt + 2000;
        } else if (str.length() == 3) {
            return ((str.charAt(0) - '0') * 100) + ((str.charAt(1) - '0') * 10) + (str.charAt(2) - '0') + 1900;
        } else {
            if (str.length() == 4) {
                return ((str.charAt(0) - '0') * 1000) + ((str.charAt(1) - '0') * 100) + ((str.charAt(2) - '0') * 10) + (str.charAt(3) - '0');
            }
            return 1970;
        }
    }

    /* renamed from: e */
    private static C1943a m21109e(String str) {
        int i;
        int i2;
        int i3;
        int charAt = str.charAt(0) - '0';
        if (str.charAt(1) != ':') {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - '0');
        } else {
            i = 1;
        }
        int i4 = i + 1 + 1 + 1 + 1;
        return new C1943a(charAt, ((str.charAt(i2) - '0') * 10) + (str.charAt(i3) - '0'), ((str.charAt(i4) - '0') * 10) + (str.charAt(i4 + 1) - '0'));
    }
}
