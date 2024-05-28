package com.networkbench.com.google.gson.internal;

import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonIOException;
import com.networkbench.com.google.gson.JsonNull;
import com.networkbench.com.google.gson.JsonParseException;
import com.networkbench.com.google.gson.JsonSyntaxException;
import com.networkbench.com.google.gson.internal.p280a.C6736m;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonWriter;
import com.networkbench.com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Writer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class Streams {
    public static JsonElement parse(JsonReader jsonReader) throws JsonParseException {
        boolean z;
        try {
            try {
                jsonReader.peek();
                z = false;
                try {
                    return C6736m.f17436P.read(jsonReader);
                } catch (EOFException e) {
                    e = e;
                    if (z) {
                        return JsonNull.INSTANCE;
                    }
                    throw new JsonSyntaxException(e);
                }
            } catch (MalformedJsonException e2) {
                throw new JsonSyntaxException(e2);
            } catch (IOException e3) {
                throw new JsonIOException(e3);
            } catch (NumberFormatException e4) {
                throw new JsonSyntaxException(e4);
            }
        } catch (EOFException e5) {
            e = e5;
            z = true;
        }
    }

    public static void write(JsonElement jsonElement, JsonWriter jsonWriter) throws IOException {
        C6736m.f17436P.write(jsonWriter, jsonElement);
    }

    public static Writer writerForAppendable(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new C6705a(appendable);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.Streams$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class C6705a extends Writer {

        /* renamed from: a */
        private final Appendable f17364a;

        /* renamed from: b */
        private final C6706a f17365b;

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        private C6705a(Appendable appendable) {
            this.f17365b = new C6706a();
            this.f17364a = appendable;
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            C6706a c6706a = this.f17365b;
            c6706a.f17366a = cArr;
            this.f17364a.append(c6706a, i, i2 + i);
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.f17364a.append((char) i);
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.networkbench.com.google.gson.internal.Streams$a$a */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        static class C6706a implements CharSequence {

            /* renamed from: a */
            char[] f17366a;

            C6706a() {
            }

            @Override // java.lang.CharSequence
            public int length() {
                return this.f17366a.length;
            }

            @Override // java.lang.CharSequence
            public char charAt(int i) {
                return this.f17366a[i];
            }

            @Override // java.lang.CharSequence
            public CharSequence subSequence(int i, int i2) {
                return new String(this.f17366a, i, i2 - i);
            }
        }
    }
}
