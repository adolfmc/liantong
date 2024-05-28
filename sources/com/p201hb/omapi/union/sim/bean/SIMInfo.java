package com.p201hb.omapi.union.sim.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u0006\"\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010¨\u0006\u0014"}, m1890d2 = {"Lcom/hb/omapi/union/sim/bean/SIMInfo;", "", "()V", "installApplet", "", "getInstallApplet", "()Z", "setInstallApplet", "(Z)V", "is5G", "set5G", "name", "", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "version", "getVersion", "setVersion", "omapi_release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.hb.omapi.union.sim.bean.SIMInfo */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class SIMInfo {
    public boolean installApplet;
    public boolean is5G;
    @NotNull
    public String name;
    @NotNull
    public String version = "";

    public final boolean getInstallApplet() {
        return this.installApplet;
    }

    @NotNull
    public final String getName() {
        String str = this.name;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("name");
        }
        return str;
    }

    @NotNull
    public final String getVersion() {
        return this.version;
    }

    public final boolean is5G() {
        return this.is5G;
    }

    public final void set5G(boolean z) {
        this.is5G = z;
    }

    public final void setInstallApplet(boolean z) {
        this.installApplet = z;
    }

    public final void setName(@NotNull String str) {
        this.name = str;
    }

    public final void setVersion(@NotNull String str) {
        this.version = str;
    }
}
