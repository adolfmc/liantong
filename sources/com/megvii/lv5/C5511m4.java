package com.megvii.lv5;

import java.lang.ref.SoftReference;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.m4 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C5511m4 {

    /* renamed from: a */
    public static final String[] f12894a = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy"};

    /* renamed from: b */
    public static final Date f12895b;

    /* renamed from: c */
    public static final TimeZone f12896c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.m4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C5512a {

        /* renamed from: a */
        public static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> f12897a = new C5513a();

        /* compiled from: Proguard */
        /* renamed from: com.megvii.lv5.m4$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public static class C5513a extends ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> {
            @Override // java.lang.ThreadLocal
            public SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference<>(new HashMap());
            }
        }

        /* renamed from: a */
        public static SimpleDateFormat m13436a(String str) {
            ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> threadLocal = f12897a;
            Map<String, SimpleDateFormat> map = threadLocal.get().get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(new SoftReference<>(map));
            }
            SimpleDateFormat simpleDateFormat = map.get(str);
            if (simpleDateFormat == null) {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(str, Locale.US);
                simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("GMT"));
                map.put(str, simpleDateFormat2);
                return simpleDateFormat2;
            }
            return simpleDateFormat;
        }
    }

    static {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        f12896c = timeZone;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(timeZone);
        calendar.set(2000, 0, 1, 0, 0, 0);
        calendar.set(14, 0);
        f12895b = calendar.getTime();
    }

    /* renamed from: a */
    public static Date m13437a(String str) {
        C5527o2.m13246a(str, "Date value");
        String[] strArr = f12894a;
        Date date = f12895b;
        if (str.length() > 1 && str.startsWith("'") && str.endsWith("'")) {
            str = str.substring(1, str.length() - 1);
        }
        for (String str2 : strArr) {
            SimpleDateFormat m13436a = C5512a.m13436a(str2);
            m13436a.set2DigitYearStart(date);
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = m13436a.parse(str, parsePosition);
            if (parsePosition.getIndex() != 0) {
                return parse;
            }
        }
        return null;
    }
}
