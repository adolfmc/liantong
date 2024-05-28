package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import io.objectbox.annotation.Transient;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CumpLowCodeRenderEntity {
    private String checksum;
    private String downloadUrl;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18563id;
    @Transient
    private boolean isRequestUpdate = false;
    private String releaseNotes;
    private String version;

    public long getId() {
        return this.f18563id;
    }

    public void setId(long j) {
        this.f18563id = j;
    }

    public String getReleaseNotes() {
        return this.releaseNotes;
    }

    public void setReleaseNotes(String str) {
        this.releaseNotes = str;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public void setChecksum(String str) {
        this.checksum = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public boolean isRequestUpdate() {
        return this.isRequestUpdate;
    }

    public void setRequestUpdate(boolean z) {
        this.isRequestUpdate = z;
    }
}
