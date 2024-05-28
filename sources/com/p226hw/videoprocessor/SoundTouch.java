package com.p226hw.videoprocessor;

/* renamed from: com.hw.videoprocessor.SoundTouch */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class SoundTouch {
    long handle;

    private final native void deleteInstance(long j);

    public static final native String getErrorString();

    public static final native String getVersionString();

    private static final native long newInstance();

    private final native int processFile(long j, String str, String str2);

    private final native void setPitchSemiTones(long j, float f);

    private final native void setSpeed(long j, float f);

    private final native void setTempo(long j, float f);

    public SoundTouch() {
        this.handle = 0L;
        this.handle = newInstance();
    }

    public void close() {
        deleteInstance(this.handle);
        this.handle = 0L;
    }

    public void setTempo(float f) {
        setTempo(this.handle, f);
    }

    public void setPitchSemiTones(float f) {
        setPitchSemiTones(this.handle, f);
    }

    public void setSpeed(float f) {
        setSpeed(this.handle, f);
    }

    public int processFile(String str, String str2) {
        return processFile(this.handle, str, str2);
    }

    static {
        System.loadLibrary("soundtouch");
    }
}
