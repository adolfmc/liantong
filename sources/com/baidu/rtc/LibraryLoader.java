package com.baidu.rtc;

import com.webrtc.Logging;
import com.webrtc.NativeLibraryLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LibraryLoader {
    private static final String TAG = "LibraryLoader";
    private static boolean libraryLoaded;
    private static Object lock = new Object();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class LibNameLoader implements NativeLibraryLoader {
        @Override // com.webrtc.NativeLibraryLoader
        public boolean load(String str) {
            Logging.m5305d("LibraryLoader", "Loading library: " + str);
            try {
                System.loadLibrary(str);
                Logging.m5305d("LibraryLoader", "Library has loaded: " + str);
                return true;
            } catch (UnsatisfiedLinkError e) {
                Logging.m5303e("LibraryLoader", "Failed to load native library: " + str, e);
                Logging.m5304e("LibraryLoader", "Will reload later.");
                return false;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class LibPathLoader implements NativeLibraryLoader {
        @Override // com.webrtc.NativeLibraryLoader
        public boolean load(String str) {
            Logging.m5305d("LibraryLoader", "Loading library: " + str);
            try {
                System.load(str);
                Logging.m5305d("LibraryLoader", "Library has loaded: " + str);
                return true;
            } catch (Throwable th) {
                Logging.m5303e("LibraryLoader", "Failed to load native library: " + str, th);
                Logging.m5304e("LibraryLoader", "Will reload later.");
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize(NativeLibraryLoader nativeLibraryLoader, String str) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.m5305d("LibraryLoader", "Native library has already been loaded.");
                return;
            }
            Logging.m5305d("LibraryLoader", "Loading native library: " + str);
            libraryLoaded = nativeLibraryLoader.load(str);
        }
    }

    static void initializeWithPath(NativeLibraryLoader nativeLibraryLoader, String str) {
        synchronized (lock) {
            if (libraryLoaded) {
                Logging.m5305d("LibraryLoader", "Native library has already been loaded.");
                return;
            }
            Logging.m5305d("LibraryLoader", "Loading native library: " + str);
            libraryLoaded = nativeLibraryLoader.load(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLoaded() {
        boolean z;
        synchronized (lock) {
            Logging.m5305d("LibraryLoader", "is library loaded " + libraryLoaded);
            z = libraryLoaded;
        }
        return z;
    }
}
