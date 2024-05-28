package org.simalliance.openmobileapi;

import java.util.Arrays;
import org.simalliance.openmobileapi.internal.ErrorStrings;
import org.simalliance.openmobileapi.internal.HistoricalBytesUtilities;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SERecognizerByHistoricalBytes extends SERecognizer {
    public static final int HISTORICAL_BYTES_MAX_LENGTH = 15;
    public static final int HISTORICAL_BYTES_MIN_LENGTH = 0;
    public byte[] mHistBytes;

    public SERecognizerByHistoricalBytes(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length >= 0 && bArr.length <= 15) {
                byte[] bArr2 = new byte[bArr.length];
                this.mHistBytes = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                return;
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidArrayLength("values"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("values"));
    }

    @Override // org.simalliance.openmobileapi.SERecognizer
    public boolean isMatching(Session session) {
        if (session != null) {
            return Arrays.equals(this.mHistBytes, HistoricalBytesUtilities.getHistBytes(session.getATR()));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("session"));
    }
}
