package net.lingala.zip4j.util;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BitUtils {
    public static boolean isBitSet(byte b, int i) {
        return ((1 << i) & ((long) b)) != 0;
    }

    public static byte setBit(byte b, int i) {
        return (byte) (b | (1 << i));
    }

    public static byte unsetBit(byte b, int i) {
        return (byte) (b & (~(1 << i)));
    }
}
