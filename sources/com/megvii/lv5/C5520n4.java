package com.megvii.lv5;

import java.io.InputStream;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.n4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5520n4 implements InterfaceC5478i4 {

    /* renamed from: a */
    public InterfaceC5466h4 f13064a;

    /* renamed from: b */
    public InterfaceC5466h4 f13065b;

    /* renamed from: c */
    public InputStream f13066c;

    /* renamed from: d */
    public long f13067d = -1;

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: a */
    public void mo13280a() {
    }

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: b */
    public InputStream mo13279b() {
        InputStream inputStream = this.f13066c;
        if (inputStream != null) {
            return inputStream;
        }
        throw new IllegalStateException("Content has not been provided");
    }

    @Override // com.megvii.lv5.InterfaceC5478i4
    /* renamed from: c */
    public long mo13278c() {
        return this.f13067d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (this.f13064a != null) {
            sb.append("Content-Type: ");
            sb.append(this.f13064a.mo13216b());
            sb.append(',');
        }
        if (this.f13065b != null) {
            sb.append("Content-Encoding: ");
            sb.append(this.f13065b.mo13216b());
            sb.append(',');
        }
        long j = this.f13067d;
        if (j >= 0) {
            sb.append("Content-Length: ");
            sb.append(j);
            sb.append(',');
        }
        sb.append("Chunked: ");
        sb.append(false);
        sb.append(']');
        return sb.toString();
    }
}
