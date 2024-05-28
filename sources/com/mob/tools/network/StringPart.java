package com.mob.tools.network;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class StringPart extends HTTPPart {

    /* renamed from: a */
    private StringBuilder f15039a = new StringBuilder();

    public StringPart append(String str) {
        this.f15039a.append(str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: a */
    public InputStream mo11311a() throws Throwable {
        return new ByteArrayInputStream(this.f15039a.toString().getBytes("utf-8"));
    }

    public String toString() {
        return this.f15039a.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: b */
    public long mo11310b() throws Throwable {
        return this.f15039a.toString().getBytes("utf-8").length;
    }
}
