package com.mob.tools.network;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FilePart extends HTTPPart {

    /* renamed from: a */
    private File f14997a;

    public void setFile(File file) {
        this.f14997a = file;
    }

    public void setFile(String str) {
        this.f14997a = new File(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: a */
    public InputStream mo11311a() throws Throwable {
        return new FileInputStream(this.f14997a);
    }

    public String toString() {
        return this.f14997a.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: b */
    public long mo11310b() throws Throwable {
        return this.f14997a.length();
    }
}
