package com.fido.uaf.ver0100.types;

import com.fido.uaf.ver0100.types.UafError;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UafException extends Exception {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private final UafError.Error f10125a;

    public UafException(UafError.Error error) {
        super(error.text());
        this.f10125a = error;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public UafException(com.fido.uaf.ver0100.types.UafError.Error r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.text()
            r0.append(r1)
            if (r5 == 0) goto L25
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = " ("
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = ")"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            goto L27
        L25:
            java.lang.String r5 = ""
        L27:
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r3.<init>(r5)
            r3.f10125a = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fido.uaf.ver0100.types.UafException.<init>(com.fido.uaf.ver0100.types.UafError$Error, java.lang.String):void");
    }

    public UafError.Error error() {
        return this.f10125a;
    }
}
