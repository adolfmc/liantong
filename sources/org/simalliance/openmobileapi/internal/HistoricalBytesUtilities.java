package org.simalliance.openmobileapi.internal;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class HistoricalBytesUtilities {
    public static byte[] getHistBytes(byte[] bArr) {
        byte b = bArr[1];
        boolean z = (b & 240) != 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(b & 15));
        int i = 2;
        while (z) {
            if ((b & 16) != 0) {
                i++;
            }
            if ((b & 32) != 0) {
                i++;
            }
            if ((b & 64) != 0) {
                i++;
            }
            if ((b & 128) != 0) {
                b = bArr[i];
                z = (b & 240) != 0;
                arrayList.add(Integer.valueOf(b & 15));
                i++;
            } else {
                z = false;
            }
        }
        int length = bArr.length - i;
        if (isTckPresent(arrayList)) {
            length--;
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, i, bArr2, 0, length);
        return bArr2;
    }

    public static boolean isTckPresent(ArrayList<Integer> arrayList) {
        return (arrayList.size() == 1 && arrayList.contains(0)) ? false : true;
    }
}
