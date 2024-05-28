package org.simalliance.openmobileapi;

import android.util.Log;
import java.io.IOException;
import org.simalliance.openmobileapi.internal.ErrorStrings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SEDiscovery {
    public static final String LOG_TAG = "SEDiscovery";
    public int mCounter;
    public Reader[] mReaders;
    public SERecognizer mRecognizer;
    public SEService mService;

    public SEDiscovery(SEService sEService, SERecognizer sERecognizer) {
        if (sEService == null) {
            throw new IllegalArgumentException(ErrorStrings.paramNull("service"));
        }
        if (sERecognizer != null) {
            this.mService = sEService;
            this.mRecognizer = sERecognizer;
            this.mCounter = -1;
            return;
        }
        throw new IllegalArgumentException(ErrorStrings.paramNull("recognizer"));
    }

    public Reader getFirstMatch() {
        Reader[] readers = this.mService.getReaders();
        this.mReaders = readers;
        if (readers != null && readers.length >= 1) {
            this.mCounter = 0;
            try {
                return getNextMatch();
            } catch (IllegalStateException unused) {
                return null;
            }
        }
        this.mCounter = -1;
        return null;
    }

    public Reader getNextMatch() {
        if (this.mCounter == -1) {
            throw new IllegalStateException("getFirstMatch needs to be called before getNextMatch()");
        }
        while (true) {
            int i = this.mCounter;
            Reader[] readerArr = this.mReaders;
            if (i < readerArr.length) {
                Reader reader = readerArr[i];
                if (reader.isSecureElementPresent()) {
                    try {
                        Session openSession = reader.openSession();
                        if (this.mRecognizer.isMatching(openSession)) {
                            openSession.close();
                            this.mCounter++;
                            return reader;
                        }
                        openSession.close();
                    } catch (IOException e) {
                        Log.e("SEDiscovery", "Session could not be opened, assume it is not matching", e);
                    } catch (IllegalArgumentException e2) {
                        Log.e("SEDiscovery", "If session is null, assume it is not matching", e2);
                    }
                }
                this.mCounter++;
            } else {
                this.mCounter = -1;
                return null;
            }
        }
    }
}
