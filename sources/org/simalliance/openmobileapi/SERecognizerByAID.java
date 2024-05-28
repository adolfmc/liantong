package org.simalliance.openmobileapi;

import android.util.Log;
import org.simalliance.openmobileapi.internal.ErrorStrings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SERecognizerByAID extends SERecognizer {
    public static final int AID_MAX_LENGTH = 16;
    public static final int AID_MIN_LENGTH = 5;
    public static final String LOG_TAG = "SERecognizerByAID";
    public byte[] mAID;

    public SERecognizerByAID(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length >= 5 && bArr.length <= 16) {
                byte[] bArr2 = new byte[bArr.length];
                this.mAID = bArr2;
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                return;
            }
            throw new IllegalArgumentException(ErrorStrings.paramInvalidValue("aid"));
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("aid"));
    }

    @Override // org.simalliance.openmobileapi.SERecognizer
    public boolean isMatching(Session session) {
        if (session != null) {
            try {
                Channel openLogicalChannel = session.openLogicalChannel(this.mAID);
                if (openLogicalChannel != null) {
                    openLogicalChannel.close();
                    return true;
                }
                return false;
            } catch (Exception e) {
                Log.e("SERecognizerByAID", "Catch general Exception", e);
                return false;
            }
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("session"));
    }
}
