package net.lingala.zip4j.p410io.outputstream;

import java.io.IOException;
import java.io.OutputStream;
import net.lingala.zip4j.crypto.Encrypter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: net.lingala.zip4j.io.outputstream.NoCipherOutputStream */
/* loaded from: E:\11617560_dexfile_execute.dex */
class NoCipherOutputStream extends CipherOutputStream<NoEncrypter> {
    public NoCipherOutputStream(ZipEntryOutputStream zipEntryOutputStream, ZipParameters zipParameters, char[] cArr) throws IOException, ZipException {
        super(zipEntryOutputStream, zipParameters, cArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.lingala.zip4j.p410io.outputstream.CipherOutputStream
    public NoEncrypter initializeEncrypter(OutputStream outputStream, ZipParameters zipParameters, char[] cArr) {
        return new NoEncrypter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: net.lingala.zip4j.io.outputstream.NoCipherOutputStream$NoEncrypter */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class NoEncrypter implements Encrypter {
        @Override // net.lingala.zip4j.crypto.Encrypter
        public int encryptData(byte[] bArr, int i, int i2) {
            return i2;
        }

        NoEncrypter() {
        }

        @Override // net.lingala.zip4j.crypto.Encrypter
        public int encryptData(byte[] bArr) {
            return encryptData(bArr, 0, bArr.length);
        }
    }
}
