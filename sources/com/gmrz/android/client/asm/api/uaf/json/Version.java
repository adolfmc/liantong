package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.annotations.Expose;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Version {
    @Expose
    public Integer major;
    @Expose
    public Integer minor;

    public Version() {
        this.major = null;
        this.minor = null;
    }

    public Version(int i, int i2) {
        this.major = Integer.valueOf(i);
        this.minor = Integer.valueOf(i2);
    }

    public boolean checkVersion(Version version) {
        return checkVersion(version.major.intValue(), version.minor.intValue());
    }

    public boolean checkVersion(int i, int i2) {
        return this.major.intValue() == i && this.minor.intValue() == i2;
    }
}
