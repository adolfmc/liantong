package org.webrtc;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SessionDescription {
    public final String description;
    public final Type type;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum Type {
        OFFER,
        PRANSWER,
        ANSWER;

        public String canonicalForm() {
            return name().toLowerCase(Locale.US);
        }

        @CalledByNative("Type")
        public static Type fromCanonicalForm(String str) {
            return (Type) valueOf(Type.class, str.toUpperCase(Locale.US));
        }
    }

    @CalledByNative
    public SessionDescription(Type type, String str) {
        this.type = type;
        this.description = str;
    }

    @CalledByNative
    String getDescription() {
        return this.description;
    }

    @CalledByNative
    String getTypeInCanonicalForm() {
        return this.type.canonicalForm();
    }
}
