package io.socket.client;

import io.socket.emitter.Emitter;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: io.socket.client.On */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12145On {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: io.socket.client.On$Handle */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Handle {
        void destroy();
    }

    private C12145On() {
    }

    /* renamed from: on */
    public static Handle m1933on(final Emitter emitter, final String str, final Emitter.Listener listener) {
        emitter.m1930on(str, listener);
        return new Handle() { // from class: io.socket.client.On.1
            @Override // io.socket.client.C12145On.Handle
            public void destroy() {
                Emitter.this.off(str, listener);
            }
        };
    }
}
