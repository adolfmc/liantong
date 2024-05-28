package com.unicom.pay.widget.ticker;

import java.util.HashMap;
import java.util.Map;

/* renamed from: com.unicom.pay.widget.ticker.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10727a {

    /* renamed from: a */
    public final int f20544a;

    /* renamed from: b */
    public final char[] f20545b;

    /* renamed from: c */
    public final Map<Character, Integer> f20546c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.ticker.a$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class C10728a {

        /* renamed from: a */
        public final int f20547a;

        /* renamed from: b */
        public final int f20548b;

        public C10728a(int i, int i2) {
            this.f20547a = i;
            this.f20548b = i2;
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.Map<java.lang.Character, java.lang.Integer>, java.util.HashMap] */
    public C10727a(String str) {
        int i = 0;
        if (str.contains(Character.toString((char) 0))) {
            throw new IllegalArgumentException("You cannot include TickerUtils.EMPTY_CHAR in the character list.");
        }
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        this.f20544a = length;
        this.f20546c = new HashMap(length);
        for (int i2 = 0; i2 < length; i2++) {
            this.f20546c.put(Character.valueOf(charArray[i2]), Integer.valueOf(i2));
        }
        char[] cArr = new char[(length * 2) + 1];
        this.f20545b = cArr;
        cArr[0] = 0;
        while (i < length) {
            char[] cArr2 = this.f20545b;
            int i3 = i + 1;
            cArr2[i3] = charArray[i];
            cArr2[length + 1 + i] = charArray[i];
            i = i3;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map<java.lang.Character, java.lang.Integer>, java.util.HashMap] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.Map<java.lang.Character, java.lang.Integer>, java.util.HashMap] */
    /* renamed from: a */
    public final int m6028a(char c) {
        if (c == 0) {
            return 0;
        }
        if (this.f20546c.containsKey(Character.valueOf(c))) {
            return ((Integer) this.f20546c.get(Character.valueOf(c))).intValue() + 1;
        }
        return -1;
    }
}
