package io.socket.parser;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface Parser {
    public static final int ACK = 3;
    public static final int BINARY_ACK = 6;
    public static final int BINARY_EVENT = 5;
    public static final int CONNECT = 0;
    public static final int DISCONNECT = 1;
    public static final int ERROR = 4;
    public static final int EVENT = 2;
    public static final int protocol = 4;
    public static final String[] types = {"CONNECT", "DISCONNECT", "EVENT", "ACK", "ERROR", "BINARY_EVENT", "BINARY_ACK"};

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Decoder {

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public interface Callback {
            void call(Packet packet);
        }

        void add(String str);

        void add(byte[] bArr);

        void destroy();

        void onDecoded(Callback callback);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Encoder {

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public interface Callback {
            void call(Object[] objArr);
        }

        void encode(Packet packet, Callback callback);
    }
}
