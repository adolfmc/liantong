package net.lingala.zip4j.model;

import net.lingala.zip4j.headers.HeaderSignature;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class ZipHeader {
    private HeaderSignature signature;

    public HeaderSignature getSignature() {
        return this.signature;
    }

    public void setSignature(HeaderSignature headerSignature) {
        this.signature = headerSignature;
    }
}
