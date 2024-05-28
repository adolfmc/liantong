package io.socket.parser;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Packet<T> {
    public int attachments;
    public T data;

    /* renamed from: id */
    public int f24819id;
    public String nsp;
    public String query;
    public int type;

    public Packet() {
        this.type = -1;
        this.f24819id = -1;
    }

    public Packet(int i) {
        this.type = -1;
        this.f24819id = -1;
        this.type = i;
    }

    public Packet(int i, T t) {
        this.type = -1;
        this.f24819id = -1;
        this.type = i;
        this.data = t;
    }
}
