package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.ReflectHelper;
import java.io.InputStream;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class HTTPPart implements PublicMemberKeeper {

    /* renamed from: a */
    private long f14998a;

    /* renamed from: b */
    private OnReadListener f14999b;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract InputStream mo11311a() throws Throwable;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public abstract long mo11310b() throws Throwable;

    public InputStream toInputStream() throws Throwable {
        return new ByteCounterInputStream(mo11311a());
    }

    public Object getInputStreamEntity() throws Throwable {
        InputStream inputStream = toInputStream();
        ReflectHelper.importClass("org.apache.http.entity.InputStreamEntity");
        return ReflectHelper.newInstance("InputStreamEntity", inputStream, Long.valueOf(mo11310b() - this.f14998a));
    }

    public void setOffset(long j) {
        this.f14998a = j;
    }

    public void setOnReadListener(OnReadListener onReadListener) {
        this.f14999b = onReadListener;
    }
}
