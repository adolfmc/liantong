package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MultiPartInputStream extends InputStream implements PublicMemberKeeper {

    /* renamed from: a */
    private ArrayList<InputStream> f15003a = new ArrayList<>();

    /* renamed from: b */
    private int f15004b;

    public void addInputStream(InputStream inputStream) throws Throwable {
        this.f15003a.add(inputStream);
    }

    /* renamed from: a */
    private boolean m11323a() {
        ArrayList<InputStream> arrayList = this.f15003a;
        return arrayList == null || arrayList.size() <= 0;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (m11323a()) {
            return 0;
        }
        return this.f15003a.get(this.f15004b).available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Iterator<InputStream> it = this.f15003a.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        throw new IOException();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (m11323a()) {
            return -1;
        }
        int read = this.f15003a.get(this.f15004b).read();
        while (read < 0) {
            this.f15004b++;
            if (this.f15004b >= this.f15003a.size()) {
                break;
            }
            read = this.f15003a.get(this.f15004b).read();
        }
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (m11323a()) {
            return -1;
        }
        int read = this.f15003a.get(this.f15004b).read(bArr, i, i2);
        while (read < 0) {
            this.f15004b++;
            if (this.f15004b >= this.f15003a.size()) {
                break;
            }
            read = this.f15003a.get(this.f15004b).read(bArr, i, i2);
        }
        return read;
    }
}
