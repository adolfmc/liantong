package com.mob.tools.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MultiPart extends HTTPPart {

    /* renamed from: a */
    private ArrayList<HTTPPart> f15002a = new ArrayList<>();

    public MultiPart append(HTTPPart hTTPPart) throws Throwable {
        this.f15002a.add(hTTPPart);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: a */
    public InputStream mo11311a() throws Throwable {
        MultiPartInputStream multiPartInputStream = new MultiPartInputStream();
        Iterator<HTTPPart> it = this.f15002a.iterator();
        while (it.hasNext()) {
            multiPartInputStream.addInputStream(it.next().mo11311a());
        }
        return multiPartInputStream;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<HTTPPart> it = this.f15002a.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.mob.tools.network.HTTPPart
    /* renamed from: b */
    public long mo11310b() throws Throwable {
        Iterator<HTTPPart> it = this.f15002a.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().mo11310b();
        }
        return j;
    }
}
