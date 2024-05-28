package com.gmrz.android.client.asm.api;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AsmException extends Exception {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private String f10147a;

    /* renamed from: b */
    private final AsmError f10148b;

    public AsmException(AsmError asmError) {
        super(asmError.name());
        this.f10148b = asmError;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AsmException(com.gmrz.android.client.asm.api.AsmError r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.name()
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
            r3.f10148b = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.client.asm.api.AsmException.<init>(com.gmrz.android.client.asm.api.AsmError, java.lang.String):void");
    }

    public AsmError error() {
        return this.f10148b;
    }

    public String getDetails() {
        return this.f10147a;
    }

    public AsmException setDetails(String str) {
        this.f10147a = str;
        return this;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public AsmException(com.gmrz.android.client.asm.api.AsmError r4, java.lang.String r5, java.lang.Throwable r6) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r4.name()
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
            r3.<init>(r5, r6)
            r3.f10148b = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.android.client.asm.api.AsmException.<init>(com.gmrz.android.client.asm.api.AsmError, java.lang.String, java.lang.Throwable):void");
    }

    public AsmException(AsmError asmError, Throwable th) {
        super(asmError.name(), th);
        this.f10148b = asmError;
    }
}
