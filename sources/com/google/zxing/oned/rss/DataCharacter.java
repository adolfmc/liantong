package com.google.zxing.oned.rss;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DataCharacter {
    private final int checksumPortion;
    private final int value;

    public DataCharacter(int i, int i2) {
        this.value = i;
        this.checksumPortion = i2;
    }

    public final int getValue() {
        return this.value;
    }

    public final int getChecksumPortion() {
        return this.checksumPortion;
    }

    public final String toString() {
        return this.value + "(" + this.checksumPortion + ')';
    }

    public final boolean equals(Object obj) {
        if (obj instanceof DataCharacter) {
            DataCharacter dataCharacter = (DataCharacter) obj;
            return this.value == dataCharacter.value && this.checksumPortion == dataCharacter.checksumPortion;
        }
        return false;
    }

    public final int hashCode() {
        return this.value ^ this.checksumPortion;
    }
}
