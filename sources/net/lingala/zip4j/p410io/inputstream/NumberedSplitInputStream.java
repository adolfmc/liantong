package net.lingala.zip4j.p410io.inputstream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import net.lingala.zip4j.util.FileUtils;

/* renamed from: net.lingala.zip4j.io.inputstream.NumberedSplitInputStream */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NumberedSplitInputStream extends SplitInputStream {
    public NumberedSplitInputStream(File file, boolean z, int i) throws FileNotFoundException {
        super(file, z, i);
    }

    @Override // net.lingala.zip4j.p410io.inputstream.SplitInputStream
    protected File getNextSplitFile(int i) throws IOException {
        String canonicalPath = this.zipFile.getCanonicalPath();
        String substring = canonicalPath.substring(0, canonicalPath.lastIndexOf("."));
        return new File(substring + FileUtils.getNextNumberedSplitFileCounterAsExtension(i));
    }
}
