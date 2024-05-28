package com.tydic.softphone.janus;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum JanusMessageType {
    message,
    trickle,
    detach,
    destroy,
    keepalive,
    create,
    attach,
    event,
    error,
    ack,
    success,
    webrtcup,
    hangup,
    detached,
    media;

    @Override // java.lang.Enum
    public String toString() {
        return name();
    }

    public boolean EqualsString(String str) {
        return toString().equals(str);
    }

    public static JanusMessageType fromString(String str) {
        return (JanusMessageType) valueOf(JanusMessageType.class, str.toLowerCase());
    }
}
