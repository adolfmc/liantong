package com.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class NativeLibrary {
    private static String TAG = "NativeLibrary";
    private static Object lock = new Object();
    private static boolean libraryLoaded = false;

    NativeLibrary() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize(NativeLibraryLoader nativeLibraryLoader, String str) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.m5305d(TAG, "Native library has already been loaded.");
            } else {
                String str2 = TAG;
                Logging.m5305d(str2, "Loading native library: " + str);
                libraryLoaded = nativeLibraryLoader.load(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLoaded() {
        boolean z;
        synchronized (lock) {
            z = libraryLoaded;
        }
        return z;
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class DefaultLoader implements NativeLibraryLoader {
        @Override // com.webrtc.NativeLibraryLoader
        public boolean load(String str) {
            String str2 = NativeLibrary.TAG;
            Logging.m5305d(str2, "Loading library: " + str);
            try {
                System.loadLibrary(str);
                return true;
            } catch (UnsatisfiedLinkError e) {
                String str3 = NativeLibrary.TAG;
                Logging.m5303e(str3, "Failed to load native library: " + str, e);
                Logging.m5303e(NativeLibrary.TAG, "force treat as OK.", e);
                return true;
            }
        }
    }
}
