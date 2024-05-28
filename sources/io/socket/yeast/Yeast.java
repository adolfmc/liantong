package io.socket.yeast;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Yeast {
    private static String prev;
    private static char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-_".toCharArray();
    private static int length = alphabet.length;
    private static int seed = 0;
    private static Map<Character, Integer> map = new HashMap(length);

    static {
        for (int i = 0; i < length; i++) {
            map.put(Character.valueOf(alphabet[i]), Integer.valueOf(i));
        }
    }

    private Yeast() {
    }

    public static String encode(long j) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.insert(0, alphabet[(int) (j % length)]);
            j /= length;
        } while (j > 0);
        return sb.toString();
    }

    public static long decode(String str) {
        long j = 0;
        for (char c : str.toCharArray()) {
            j = (j * length) + map.get(Character.valueOf(c)).intValue();
        }
        return j;
    }

    public static String yeast() {
        String encode = encode(new Date().getTime());
        if (!encode.equals(prev)) {
            seed = 0;
            prev = encode;
            return encode;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(encode);
        sb.append(".");
        int i = seed;
        seed = i + 1;
        sb.append(encode(i));
        return sb.toString();
    }
}
