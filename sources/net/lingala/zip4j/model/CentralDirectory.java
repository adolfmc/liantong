package net.lingala.zip4j.model;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CentralDirectory {
    private List<FileHeader> fileHeaders = new ArrayList();
    private DigitalSignature digitalSignature = new DigitalSignature();

    public List<FileHeader> getFileHeaders() {
        return this.fileHeaders;
    }

    public void setFileHeaders(List<FileHeader> list) {
        this.fileHeaders = list;
    }

    public DigitalSignature getDigitalSignature() {
        return this.digitalSignature;
    }

    public void setDigitalSignature(DigitalSignature digitalSignature) {
        this.digitalSignature = digitalSignature;
    }
}
