package org.jsoup.parser;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ParseError {
    private String errorMsg;
    private int pos;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseError(int i, String str) {
        this.pos = i;
        this.errorMsg = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ParseError(int i, String str, Object... objArr) {
        this.errorMsg = String.format(str, objArr);
        this.pos = i;
    }

    public String getErrorMessage() {
        return this.errorMsg;
    }

    public int getPosition() {
        return this.pos;
    }

    public String toString() {
        return this.pos + ": " + this.errorMsg;
    }
}
