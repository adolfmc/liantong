package com.p319ss.android.socialbase.downloader.segment;

import android.support.annotation.NonNull;
import java.io.IOException;

/* renamed from: com.ss.android.socialbase.downloader.segment.OutputStub */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class OutputStub implements IOutput {
    private final IOutput output;
    private final IOutput target;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OutputStub(IOutput iOutput, IOutput iOutput2) {
        this.output = iOutput;
        this.target = iOutput2;
    }

    @Override // com.p319ss.android.socialbase.downloader.segment.IOutput
    public void write(@NonNull Buffer buffer) throws IOException {
        buffer.output = this.target;
        this.output.write(buffer);
    }
}
