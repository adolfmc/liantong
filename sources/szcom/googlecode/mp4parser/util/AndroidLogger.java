package szcom.googlecode.mp4parser.util;

import android.util.Log;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AndroidLogger extends Logger {
    private static final String TAG = "isoparser";
    String name;

    public AndroidLogger(String str) {
        this.name = str;
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logDebug(String str) {
        Log.d(TAG, String.valueOf(this.name) + ":" + str);
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logError(String str) {
        Log.e(TAG, String.valueOf(this.name) + ":" + str);
    }

    @Override // szcom.googlecode.mp4parser.util.Logger
    public void logWarn(String str) {
        Log.w(TAG, String.valueOf(this.name) + ":" + str);
    }
}
