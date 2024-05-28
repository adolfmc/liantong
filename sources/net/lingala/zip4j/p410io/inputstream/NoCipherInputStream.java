package net.lingala.zip4j.p410io.inputstream;

import java.io.IOException;
import net.lingala.zip4j.crypto.Decrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.LocalFileHeader;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: net.lingala.zip4j.io.inputstream.NoCipherInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex */
class NoCipherInputStream extends CipherInputStream {
    public NoCipherInputStream(ZipEntryInputStream zipEntryInputStream, LocalFileHeader localFileHeader, char[] cArr) throws IOException, ZipException {
        super(zipEntryInputStream, localFileHeader, cArr);
    }

    @Override // net.lingala.zip4j.p410io.inputstream.CipherInputStream
    protected Decrypter initializeDecrypter(LocalFileHeader localFileHeader, char[] cArr) {
        return new NoDecrypter();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: net.lingala.zip4j.io.inputstream.NoCipherInputStream$NoDecrypter */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class NoDecrypter implements Decrypter {
        @Override // net.lingala.zip4j.crypto.Decrypter
        public int decryptData(byte[] bArr, int i, int i2) {
            return i2;
        }

        NoDecrypter() {
        }
    }
}
