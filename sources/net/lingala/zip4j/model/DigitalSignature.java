package net.lingala.zip4j.model;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DigitalSignature extends ZipHeader {
    private String signatureData;
    private int sizeOfData;

    public int getSizeOfData() {
        return this.sizeOfData;
    }

    public void setSizeOfData(int i) {
        this.sizeOfData = i;
    }

    public String getSignatureData() {
        return this.signatureData;
    }

    public void setSignatureData(String str) {
        this.signatureData = str;
    }
}
