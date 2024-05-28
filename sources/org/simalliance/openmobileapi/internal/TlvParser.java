package org.simalliance.openmobileapi.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class TlvParser {
    public abstract byte[] getLengthBytes(byte[] bArr, int i);

    public abstract int getLengthValue(byte[] bArr);

    public abstract byte[] getTagBytes(byte[] bArr, int i);

    public byte[] getValidTlvData(byte[] bArr) {
        if (isValidTlvStructure(bArr)) {
            return bArr;
        }
        int i = 0;
        while (true) {
            try {
                int length = getTagBytes(bArr, i).length + i;
                byte[] lengthBytes = getLengthBytes(bArr, length);
                int length2 = length + lengthBytes.length + getLengthValue(lengthBytes);
                if (length2 > bArr.length) {
                    break;
                }
                i = length2;
            } catch (Exception unused) {
            }
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        return bArr2;
    }

    public boolean isValidTlvStructure(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            try {
                int length = i + getTagBytes(bArr, i).length;
                byte[] lengthBytes = getLengthBytes(bArr, length);
                i = length + lengthBytes.length + getLengthValue(lengthBytes);
            } catch (Exception unused) {
                return false;
            }
        }
        return i == bArr.length;
    }

    public List<TlvEntryWrapper> parseArray(byte[] bArr) {
        byte[] validTlvData = getValidTlvData(bArr);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < validTlvData.length) {
            TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(validTlvData, i, this);
            arrayList.add(tlvEntryWrapper);
            i += tlvEntryWrapper.getTotalLength();
        }
        return arrayList;
    }

    public int searchTag(byte[] bArr, byte[] bArr2, int i) {
        if (i >= 0) {
            while (i < bArr.length) {
                TlvEntryWrapper tlvEntryWrapper = new TlvEntryWrapper(bArr, i, this);
                if (Arrays.equals(bArr2, tlvEntryWrapper.getTag())) {
                    return i;
                }
                i += tlvEntryWrapper.getTotalLength();
            }
            throw new IllegalArgumentException("Tag not found.");
        }
        throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("startPosition"));
    }
}
