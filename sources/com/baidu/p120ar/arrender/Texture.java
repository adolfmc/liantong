package com.baidu.p120ar.arrender;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.Texture */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Texture {
    public static final long NOT_CREATE_INSIDE = -1;
    public static final int NO_TEXTURE = -1;
    private long mHandle;
    private int mId;
    private int mType;

    public Texture() {
        this.mHandle = -1L;
        this.mId = -1;
        this.mType = 3553;
    }

    public Texture(int i, int i2) {
        this.mHandle = -1L;
        this.mId = -1;
        this.mType = 3553;
        this.mId = i;
        this.mType = i2;
    }

    public long getHandle() {
        return this.mHandle;
    }

    public void setHandle(long j) {
        this.mHandle = j;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }
}
