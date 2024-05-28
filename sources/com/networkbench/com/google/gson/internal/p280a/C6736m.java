package com.networkbench.com.google.gson.internal.p280a;

import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonIOException;
import com.networkbench.com.google.gson.JsonNull;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import com.networkbench.com.google.gson.JsonSyntaxException;
import com.networkbench.com.google.gson.TypeAdapter;
import com.networkbench.com.google.gson.TypeAdapterFactory;
import com.networkbench.com.google.gson.annotations.SerializedName;
import com.networkbench.com.google.gson.internal.LazilyParsedNumber;
import com.networkbench.com.google.gson.reflect.TypeToken;
import com.networkbench.com.google.gson.stream.JsonReader;
import com.networkbench.com.google.gson.stream.JsonToken;
import com.networkbench.com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.com.google.gson.internal.a.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6736m {

    /* renamed from: a */
    public static final TypeAdapter<Class> f17439a = new TypeAdapter<Class>() { // from class: com.networkbench.com.google.gson.internal.a.m.1
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Class cls) throws IOException {
            if (cls == null) {
                jsonWriter.nullValue();
                return;
            }
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Class read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }
    };

    /* renamed from: b */
    public static final TypeAdapterFactory f17440b = m8584a(Class.class, f17439a);

    /* renamed from: c */
    public static final TypeAdapter<BitSet> f17441c = new TypeAdapter<BitSet>() { // from class: com.networkbench.com.google.gson.internal.a.m.12
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public BitSet read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            BitSet bitSet = new BitSet();
            jsonReader.beginArray();
            JsonToken peek = jsonReader.peek();
            int i = 0;
            while (peek != JsonToken.END_ARRAY) {
                boolean z = true;
                switch (C675626.f17485a[peek.ordinal()]) {
                    case 1:
                        if (jsonReader.nextInt() == 0) {
                            z = false;
                            break;
                        }
                        break;
                    case 2:
                        z = jsonReader.nextBoolean();
                        break;
                    case 3:
                        String nextString = jsonReader.nextString();
                        try {
                            if (Integer.parseInt(nextString) == 0) {
                                z = false;
                                break;
                            }
                        } catch (NumberFormatException unused) {
                            throw new JsonSyntaxException("Error: Expecting: bitset number value (1, 0), Found: " + nextString);
                        }
                        break;
                    default:
                        throw new JsonSyntaxException("Invalid bitset value type: " + peek);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                peek = jsonReader.peek();
            }
            jsonReader.endArray();
            return bitSet;
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, BitSet bitSet) throws IOException {
            if (bitSet == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            for (int i = 0; i < bitSet.length(); i++) {
                jsonWriter.value(bitSet.get(i) ? 1L : 0L);
            }
            jsonWriter.endArray();
        }
    };

    /* renamed from: d */
    public static final TypeAdapterFactory f17442d = m8584a(BitSet.class, f17441c);

    /* renamed from: e */
    public static final TypeAdapter<Boolean> f17443e = new TypeAdapter<Boolean>() { // from class: com.networkbench.com.google.gson.internal.a.m.23
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Boolean read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else if (jsonReader.peek() == JsonToken.STRING) {
                return Boolean.valueOf(Boolean.parseBoolean(jsonReader.nextString()));
            } else {
                return Boolean.valueOf(jsonReader.nextBoolean());
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            if (bool == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(bool.booleanValue());
            }
        }
    };

    /* renamed from: f */
    public static final TypeAdapter<Boolean> f17444f = new TypeAdapter<Boolean>() { // from class: com.networkbench.com.google.gson.internal.a.m.27
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Boolean read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Boolean.valueOf(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool == null ? "null" : bool.toString());
        }
    };

    /* renamed from: g */
    public static final TypeAdapterFactory f17445g = m8583a(Boolean.TYPE, Boolean.class, f17443e);

    /* renamed from: h */
    public static final TypeAdapter<Number> f17446h = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.28
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Byte.valueOf((byte) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: i */
    public static final TypeAdapterFactory f17447i = m8583a(Byte.TYPE, Byte.class, f17446h);

    /* renamed from: j */
    public static final TypeAdapter<Number> f17448j = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.29
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Short.valueOf((short) jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: k */
    public static final TypeAdapterFactory f17449k = m8583a(Short.TYPE, Short.class, f17448j);

    /* renamed from: l */
    public static final TypeAdapter<Number> f17450l = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.30
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Integer.valueOf(jsonReader.nextInt());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: m */
    public static final TypeAdapterFactory f17451m = m8583a(Integer.TYPE, Integer.class, f17450l);

    /* renamed from: n */
    public static final TypeAdapter<Number> f17452n = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.31
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return Long.valueOf(jsonReader.nextLong());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: o */
    public static final TypeAdapter<Number> f17453o = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.32
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Float.valueOf((float) jsonReader.nextDouble());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: p */
    public static final TypeAdapter<Number> f17454p = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.2
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return Double.valueOf(jsonReader.nextDouble());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: q */
    public static final TypeAdapter<Number> f17455q = new TypeAdapter<Number>() { // from class: com.networkbench.com.google.gson.internal.a.m.3
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Number read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            int i = C675626.f17485a[peek.ordinal()];
            if (i != 1) {
                if (i == 4) {
                    jsonReader.nextNull();
                    return null;
                }
                throw new JsonSyntaxException("Expecting number, got: " + peek);
            }
            return new LazilyParsedNumber(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            jsonWriter.value(number);
        }
    };

    /* renamed from: r */
    public static final TypeAdapterFactory f17456r = m8584a(Number.class, f17455q);

    /* renamed from: s */
    public static final TypeAdapter<Character> f17457s = new TypeAdapter<Character>() { // from class: com.networkbench.com.google.gson.internal.a.m.4
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Character read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if (nextString.length() != 1) {
                throw new JsonSyntaxException("Expecting character, got: " + nextString);
            }
            return Character.valueOf(nextString.charAt(0));
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch == null ? null : String.valueOf(ch));
        }
    };

    /* renamed from: t */
    public static final TypeAdapterFactory f17458t = m8583a(Character.TYPE, Character.class, f17457s);

    /* renamed from: u */
    public static final TypeAdapter<String> f17459u = new TypeAdapter<String>() { // from class: com.networkbench.com.google.gson.internal.a.m.5
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public String read(JsonReader jsonReader) throws IOException {
            JsonToken peek = jsonReader.peek();
            if (peek == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else if (peek == JsonToken.BOOLEAN) {
                return Boolean.toString(jsonReader.nextBoolean());
            } else {
                return jsonReader.nextString();
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    };

    /* renamed from: v */
    public static final TypeAdapter<BigDecimal> f17460v = new TypeAdapter<BigDecimal>() { // from class: com.networkbench.com.google.gson.internal.a.m.6
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public BigDecimal read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigDecimal(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, BigDecimal bigDecimal) throws IOException {
            jsonWriter.value(bigDecimal);
        }
    };

    /* renamed from: w */
    public static final TypeAdapter<BigInteger> f17461w = new TypeAdapter<BigInteger>() { // from class: com.networkbench.com.google.gson.internal.a.m.7
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public BigInteger read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                return new BigInteger(jsonReader.nextString());
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, BigInteger bigInteger) throws IOException {
            jsonWriter.value(bigInteger);
        }
    };

    /* renamed from: x */
    public static final TypeAdapterFactory f17462x = m8584a(String.class, f17459u);

    /* renamed from: y */
    public static final TypeAdapter<StringBuilder> f17463y = new TypeAdapter<StringBuilder>() { // from class: com.networkbench.com.google.gson.internal.a.m.8
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public StringBuilder read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuilder(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, StringBuilder sb) throws IOException {
            jsonWriter.value(sb == null ? null : sb.toString());
        }
    };

    /* renamed from: z */
    public static final TypeAdapterFactory f17464z = m8584a(StringBuilder.class, f17463y);

    /* renamed from: A */
    public static final TypeAdapter<StringBuffer> f17421A = new TypeAdapter<StringBuffer>() { // from class: com.networkbench.com.google.gson.internal.a.m.9
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public StringBuffer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return new StringBuffer(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, StringBuffer stringBuffer) throws IOException {
            jsonWriter.value(stringBuffer == null ? null : stringBuffer.toString());
        }
    };

    /* renamed from: B */
    public static final TypeAdapterFactory f17422B = m8584a(StringBuffer.class, f17421A);

    /* renamed from: C */
    public static final TypeAdapter<URL> f17423C = new TypeAdapter<URL>() { // from class: com.networkbench.com.google.gson.internal.a.m.10
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public URL read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            String nextString = jsonReader.nextString();
            if ("null".equals(nextString)) {
                return null;
            }
            return new URL(nextString);
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, URL url) throws IOException {
            jsonWriter.value(url == null ? null : url.toExternalForm());
        }
    };

    /* renamed from: D */
    public static final TypeAdapterFactory f17424D = m8584a(URL.class, f17423C);

    /* renamed from: E */
    public static final TypeAdapter<URI> f17425E = new TypeAdapter<URI>() { // from class: com.networkbench.com.google.gson.internal.a.m.11
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public URI read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            try {
                String nextString = jsonReader.nextString();
                if ("null".equals(nextString)) {
                    return null;
                }
                return new URI(nextString);
            } catch (URISyntaxException e) {
                throw new JsonIOException(e);
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, URI uri) throws IOException {
            jsonWriter.value(uri == null ? null : uri.toASCIIString());
        }
    };

    /* renamed from: F */
    public static final TypeAdapterFactory f17426F = m8584a(URI.class, f17425E);

    /* renamed from: G */
    public static final TypeAdapter<InetAddress> f17427G = new TypeAdapter<InetAddress>() { // from class: com.networkbench.com.google.gson.internal.a.m.13
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public InetAddress read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return InetAddress.getByName(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, InetAddress inetAddress) throws IOException {
            jsonWriter.value(inetAddress == null ? null : inetAddress.getHostAddress());
        }
    };

    /* renamed from: H */
    public static final TypeAdapterFactory f17428H = m8582b(InetAddress.class, f17427G);

    /* renamed from: I */
    public static final TypeAdapter<UUID> f17429I = new TypeAdapter<UUID>() { // from class: com.networkbench.com.google.gson.internal.a.m.14
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public UUID read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return UUID.fromString(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
            jsonWriter.value(uuid == null ? null : uuid.toString());
        }
    };

    /* renamed from: J */
    public static final TypeAdapterFactory f17430J = m8584a(UUID.class, f17429I);

    /* renamed from: K */
    public static final TypeAdapterFactory f17431K = new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.15
        @Override // com.networkbench.com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            if (typeToken.getRawType() != Timestamp.class) {
                return null;
            }
            final TypeAdapter<T> adapter = gson.getAdapter(Date.class);
            return (TypeAdapter<T>) new TypeAdapter<Timestamp>() { // from class: com.networkbench.com.google.gson.internal.a.m.15.1
                @Override // com.networkbench.com.google.gson.TypeAdapter
                /* renamed from: a */
                public Timestamp read(JsonReader jsonReader) throws IOException {
                    Date date = (Date) adapter.read(jsonReader);
                    if (date != null) {
                        return new Timestamp(date.getTime());
                    }
                    return null;
                }

                @Override // com.networkbench.com.google.gson.TypeAdapter
                /* renamed from: a */
                public void write(JsonWriter jsonWriter, Timestamp timestamp) throws IOException {
                    adapter.write(jsonWriter, timestamp);
                }
            };
        }
    };

    /* renamed from: L */
    public static final TypeAdapter<Calendar> f17432L = new TypeAdapter<Calendar>() { // from class: com.networkbench.com.google.gson.internal.a.m.16

        /* renamed from: a */
        private static final String f17467a = "year";

        /* renamed from: b */
        private static final String f17468b = "month";

        /* renamed from: c */
        private static final String f17469c = "dayOfMonth";

        /* renamed from: d */
        private static final String f17470d = "hourOfDay";

        /* renamed from: e */
        private static final String f17471e = "minute";

        /* renamed from: f */
        private static final String f17472f = "second";

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Calendar read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (jsonReader.peek() != JsonToken.END_OBJECT) {
                String nextName = jsonReader.nextName();
                int nextInt = jsonReader.nextInt();
                if ("year".equals(nextName)) {
                    i = nextInt;
                } else if ("month".equals(nextName)) {
                    i2 = nextInt;
                } else if ("dayOfMonth".equals(nextName)) {
                    i3 = nextInt;
                } else if ("hourOfDay".equals(nextName)) {
                    i4 = nextInt;
                } else if ("minute".equals(nextName)) {
                    i5 = nextInt;
                } else if ("second".equals(nextName)) {
                    i6 = nextInt;
                }
            }
            jsonReader.endObject();
            return new GregorianCalendar(i, i2, i3, i4, i5, i6);
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Calendar calendar) throws IOException {
            if (calendar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("year");
            jsonWriter.value(calendar.get(1));
            jsonWriter.name("month");
            jsonWriter.value(calendar.get(2));
            jsonWriter.name("dayOfMonth");
            jsonWriter.value(calendar.get(5));
            jsonWriter.name("hourOfDay");
            jsonWriter.value(calendar.get(11));
            jsonWriter.name("minute");
            jsonWriter.value(calendar.get(12));
            jsonWriter.name("second");
            jsonWriter.value(calendar.get(13));
            jsonWriter.endObject();
        }
    };

    /* renamed from: M */
    public static final TypeAdapterFactory f17433M = m8581b(Calendar.class, GregorianCalendar.class, f17432L);

    /* renamed from: N */
    public static final TypeAdapter<Locale> f17434N = new TypeAdapter<Locale>() { // from class: com.networkbench.com.google.gson.internal.a.m.17
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public Locale read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(jsonReader.nextString(), "_");
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            if (nextToken2 == null && nextToken3 == null) {
                return new Locale(nextToken);
            }
            if (nextToken3 == null) {
                return new Locale(nextToken, nextToken2);
            }
            return new Locale(nextToken, nextToken2, nextToken3);
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, Locale locale) throws IOException {
            jsonWriter.value(locale == null ? null : locale.toString());
        }
    };

    /* renamed from: O */
    public static final TypeAdapterFactory f17435O = m8584a(Locale.class, f17434N);

    /* renamed from: P */
    public static final TypeAdapter<JsonElement> f17436P = new TypeAdapter<JsonElement>() { // from class: com.networkbench.com.google.gson.internal.a.m.18
        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public JsonElement read(JsonReader jsonReader) throws IOException {
            switch (C675626.f17485a[jsonReader.peek().ordinal()]) {
                case 1:
                    return new JsonPrimitive((Number) new LazilyParsedNumber(jsonReader.nextString()));
                case 2:
                    return new JsonPrimitive(Boolean.valueOf(jsonReader.nextBoolean()));
                case 3:
                    return new JsonPrimitive(jsonReader.nextString());
                case 4:
                    jsonReader.nextNull();
                    return JsonNull.INSTANCE;
                case 5:
                    JsonArray jsonArray = new JsonArray();
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonArray.add(read(jsonReader));
                    }
                    jsonReader.endArray();
                    return jsonArray;
                case 6:
                    JsonObject jsonObject = new JsonObject();
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        jsonObject.add(jsonReader.nextName(), read(jsonReader));
                    }
                    jsonReader.endObject();
                    return jsonObject;
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, JsonElement jsonElement) throws IOException {
            if (jsonElement == null || jsonElement.isJsonNull()) {
                jsonWriter.nullValue();
            } else if (jsonElement.isJsonPrimitive()) {
                JsonPrimitive asJsonPrimitive = jsonElement.getAsJsonPrimitive();
                if (asJsonPrimitive.isNumber()) {
                    jsonWriter.value(asJsonPrimitive.getAsNumber());
                } else if (asJsonPrimitive.isBoolean()) {
                    jsonWriter.value(asJsonPrimitive.getAsBoolean());
                } else {
                    jsonWriter.value(asJsonPrimitive.getAsString());
                }
            } else if (jsonElement.isJsonArray()) {
                jsonWriter.beginArray();
                Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
                while (it.hasNext()) {
                    write(jsonWriter, it.next());
                }
                jsonWriter.endArray();
            } else if (jsonElement.isJsonObject()) {
                jsonWriter.beginObject();
                for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                    jsonWriter.name(entry.getKey());
                    write(jsonWriter, entry.getValue());
                }
                jsonWriter.endObject();
            } else {
                throw new IllegalArgumentException("Couldn't write " + jsonElement.getClass());
            }
        }
    };

    /* renamed from: Q */
    public static final TypeAdapterFactory f17437Q = m8582b(JsonElement.class, f17436P);

    /* renamed from: R */
    public static final TypeAdapterFactory f17438R = m8586a();

    private C6736m() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.m$26 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static /* synthetic */ class C675626 {

        /* renamed from: a */
        static final /* synthetic */ int[] f17485a = new int[JsonToken.values().length];

        static {
            try {
                f17485a[JsonToken.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f17485a[JsonToken.BOOLEAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f17485a[JsonToken.STRING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f17485a[JsonToken.NULL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f17485a[JsonToken.BEGIN_ARRAY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f17485a[JsonToken.BEGIN_OBJECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f17485a[JsonToken.END_DOCUMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f17485a[JsonToken.NAME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f17485a[JsonToken.END_OBJECT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f17485a[JsonToken.END_ARRAY.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.com.google.gson.internal.a.m$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class C6770a<T extends Enum<T>> extends TypeAdapter<T> {

        /* renamed from: a */
        private final Map<String, T> f17486a = new HashMap();

        /* renamed from: b */
        private final Map<T, String> f17487b = new HashMap();

        public C6770a(Class<T> cls) {
            T[] enumConstants;
            try {
                for (T t : cls.getEnumConstants()) {
                    String name = t.name();
                    SerializedName serializedName = (SerializedName) cls.getField(name).getAnnotation(SerializedName.class);
                    name = serializedName != null ? serializedName.value() : name;
                    this.f17486a.put(name, t);
                    this.f17487b.put(t, name);
                }
            } catch (NoSuchFieldException unused) {
                throw new AssertionError();
            }
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            return this.f17486a.get(jsonReader.nextString());
        }

        @Override // com.networkbench.com.google.gson.TypeAdapter
        /* renamed from: a */
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(t == null ? null : this.f17487b.get(t));
        }
    }

    /* renamed from: a */
    public static TypeAdapterFactory m8586a() {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.19
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class rawType = typeToken.getRawType();
                if (!Enum.class.isAssignableFrom(rawType) || rawType == Enum.class) {
                    return null;
                }
                if (!rawType.isEnum()) {
                    rawType = (Class<? super Object>) rawType.getSuperclass();
                }
                return new C6770a(rawType);
            }
        };
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m8585a(final TypeToken<TT> typeToken, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.20
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken2) {
                if (typeToken2.equals(TypeToken.this)) {
                    return typeAdapter;
                }
                return null;
            }
        };
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m8584a(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.21
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (typeToken.getRawType() == cls) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    /* renamed from: a */
    public static <TT> TypeAdapterFactory m8583a(final Class<TT> cls, final Class<TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.22
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + "+" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    /* renamed from: b */
    public static <TT> TypeAdapterFactory m8581b(final Class<TT> cls, final Class<? extends TT> cls2, final TypeAdapter<? super TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.24
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                Class<? super T> rawType = typeToken.getRawType();
                if (rawType == cls || rawType == cls2) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + "+" + cls2.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }

    /* renamed from: b */
    public static <TT> TypeAdapterFactory m8582b(final Class<TT> cls, final TypeAdapter<TT> typeAdapter) {
        return new TypeAdapterFactory() { // from class: com.networkbench.com.google.gson.internal.a.m.25
            @Override // com.networkbench.com.google.gson.TypeAdapterFactory
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                if (cls.isAssignableFrom(typeToken.getRawType())) {
                    return typeAdapter;
                }
                return null;
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + typeAdapter + "]";
            }
        };
    }
}
