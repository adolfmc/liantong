package org.codehaus.jackson.map.deser.std;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class FromStringDeserializer<T> extends StdScalarDeserializer<T> {
    protected abstract T _deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    /* JADX INFO: Access modifiers changed from: protected */
    public FromStringDeserializer(Class<?> cls) {
        super(cls);
    }

    public static Iterable<FromStringDeserializer<?>> all() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new UUIDDeserializer());
        arrayList.add(new URLDeserializer());
        arrayList.add(new URIDeserializer());
        arrayList.add(new CurrencyDeserializer());
        arrayList.add(new PatternDeserializer());
        arrayList.add(new LocaleDeserializer());
        arrayList.add(new InetAddressDeserializer());
        arrayList.add(new TimeZoneDeserializer());
        arrayList.add(new CharsetDeserializer());
        return arrayList;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public final T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            try {
                T _deserialize = _deserialize(trim, deserializationContext);
                if (_deserialize != null) {
                    return _deserialize;
                }
            } catch (IllegalArgumentException unused) {
            }
            throw deserializationContext.weirdStringException(this._valueClass, "not a valid textual representation");
        } else if (jsonParser.getCurrentToken() == JsonToken.VALUE_EMBEDDED_OBJECT) {
            T t = (T) jsonParser.getEmbeddedObject();
            if (t == null) {
                return null;
            }
            return this._valueClass.isAssignableFrom(t.getClass()) ? t : _deserializeEmbedded(t, deserializationContext);
        } else {
            throw deserializationContext.mappingException(this._valueClass);
        }
    }

    protected T _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        throw deserializationContext.mappingException("Don't know how to convert embedded Object of type " + obj.getClass().getName() + " into " + this._valueClass.getName());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class UUIDDeserializer extends FromStringDeserializer<UUID> {
        public UUIDDeserializer() {
            super(UUID.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public UUID _deserialize(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return UUID.fromString(str);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public UUID _deserializeEmbedded(Object obj, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                if (bArr.length != 16) {
                    deserializationContext.mappingException("Can only construct UUIDs from 16 byte arrays; got " + bArr.length + " bytes");
                }
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
                return new UUID(dataInputStream.readLong(), dataInputStream.readLong());
            }
            super._deserializeEmbedded(obj, deserializationContext);
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class URLDeserializer extends FromStringDeserializer<URL> {
        public URLDeserializer() {
            super(URL.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public URL _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return new URL(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class URIDeserializer extends FromStringDeserializer<URI> {
        public URIDeserializer() {
            super(URI.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public URI _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return URI.create(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CurrencyDeserializer extends FromStringDeserializer<Currency> {
        public CurrencyDeserializer() {
            super(Currency.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Currency _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return Currency.getInstance(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PatternDeserializer extends FromStringDeserializer<Pattern> {
        public PatternDeserializer() {
            super(Pattern.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Pattern _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return Pattern.compile(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class LocaleDeserializer extends FromStringDeserializer<Locale> {
        public LocaleDeserializer() {
            super(Locale.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Locale _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            int indexOf = str.indexOf(95);
            if (indexOf < 0) {
                return new Locale(str);
            }
            String substring = str.substring(0, indexOf);
            String substring2 = str.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(95);
            if (indexOf2 < 0) {
                return new Locale(substring, substring2);
            }
            return new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class InetAddressDeserializer extends FromStringDeserializer<InetAddress> {
        public InetAddressDeserializer() {
            super(InetAddress.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public InetAddress _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return InetAddress.getByName(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class CharsetDeserializer extends FromStringDeserializer<Charset> {
        public CharsetDeserializer() {
            super(Charset.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Charset _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return Charset.forName(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class TimeZoneDeserializer extends FromStringDeserializer<TimeZone> {
        public TimeZoneDeserializer() {
            super(TimeZone.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public TimeZone _deserialize(String str, DeserializationContext deserializationContext) throws IOException {
            return TimeZone.getTimeZone(str);
        }
    }
}
