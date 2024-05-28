package com.github.anrwatchdog;

import java.io.Serializable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ANRError$$ implements Serializable {
    private final String _name;
    private final StackTraceElement[] _stackTrace;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class _Thread extends Throwable {
        private _Thread(_Thread _thread) {
            super(ANRError$$.this._name, _thread);
        }

        @Override // java.lang.Throwable
        public Throwable fillInStackTrace() {
            setStackTrace(ANRError$$.this._stackTrace);
            return this;
        }
    }

    private ANRError$$(String str, StackTraceElement[] stackTraceElementArr) {
        this._name = str;
        this._stackTrace = stackTraceElementArr;
    }
}
