package net.lingala.zip4j.p410io.inputstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* renamed from: net.lingala.zip4j.io.inputstream.ZipStandardSplitInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ZipStandardSplitInputStream extends SplitInputStream {
    private int lastSplitZipFileNumber;

    public ZipStandardSplitInputStream(File file, boolean z, int i) throws FileNotFoundException {
        super(file, z, i);
        this.lastSplitZipFileNumber = i;
    }

    @Override // net.lingala.zip4j.p410io.inputstream.SplitInputStream
    protected File getNextSplitFile(int i) throws IOException {
        if (i == this.lastSplitZipFileNumber) {
            return this.zipFile;
        }
        String canonicalPath = this.zipFile.getCanonicalPath();
        String str = i >= 9 ? ".z" : ".z0";
        return new File(canonicalPath.substring(0, canonicalPath.lastIndexOf(".")) + str + (i + 1));
    }
}
