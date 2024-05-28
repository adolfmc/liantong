package com.fido.uaf.ver0100.types;

import com.gmrz.android.client.asm.api.uaf.json.Extension;
import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AuthenticatorSignAssertion {
    @Expose
    public String assertion;
    @Expose
    public String assertionScheme;
    @Expose
    public List<Extension> exts;

    public AuthenticatorSignAssertion(String str, String str2, List<Extension> list) {
        this.assertionScheme = str;
        this.assertion = str2;
        this.exts = list;
    }
}
