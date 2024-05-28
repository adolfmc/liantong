package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileBusyAfterRunException extends IOException {
    public static final FileBusyAfterRunException SIGNAL = new FileBusyAfterRunException() { // from class: com.liulishuo.okdownload.core.exception.FileBusyAfterRunException.1
    };

    private FileBusyAfterRunException() {
        super("File busy after run");
    }
}
