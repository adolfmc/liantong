package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.joda.time.ReadableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePeriod;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JodaDeserializers implements Provider<StdDeserializer<?>> {
    @Override // org.codehaus.jackson.map.util.Provider
    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new DateTimeDeserializer(DateTime.class), new DateTimeDeserializer(ReadableDateTime.class), new DateTimeDeserializer(ReadableInstant.class), new LocalDateDeserializer(), new LocalDateTimeDeserializer(), new DateMidnightDeserializer(), new PeriodDeserializer());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static abstract class JodaDeserializer<T> extends StdScalarDeserializer<T> {
        static final DateTimeFormatter _localDateTimeFormat = ISODateTimeFormat.localDateOptionalTimeParser();

        protected JodaDeserializer(Class<T> cls) {
            super((Class<?>) cls);
        }

        protected DateTime parseLocal(JsonParser jsonParser) throws IOException, JsonProcessingException {
            String trim = jsonParser.getText().trim();
            if (trim.length() == 0) {
                return null;
            }
            return _localDateTimeFormat.parseDateTime(trim);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class DateTimeDeserializer<T extends ReadableInstant> extends JodaDeserializer<T> {
        public DateTimeDeserializer(Class<T> cls) {
            super(cls);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                return new DateTime(jsonParser.getLongValue(), DateTimeZone.UTC);
            }
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return null;
                }
                return new DateTime(trim, DateTimeZone.UTC);
            }
            throw deserializationContext.mappingException(getValueClass());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class LocalDateDeserializer extends JodaDeserializer<LocalDate> {
        public LocalDateDeserializer() {
            super(LocalDate.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDate ints");
                }
                return new LocalDate(intValue, intValue2, intValue3);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new LocalDate(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime parseLocal = parseLocal(jsonParser);
                    if (parseLocal == null) {
                        return null;
                    }
                    return parseLocal.toLocalDate();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, String or Number");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class LocalDateTimeDeserializer extends JodaDeserializer<LocalDateTime> {
        public LocalDateTimeDeserializer() {
            super(LocalDateTime.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            int i;
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue4 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue5 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue6 = jsonParser.getIntValue();
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    int intValue7 = jsonParser.getIntValue();
                    jsonParser.nextToken();
                    i = intValue7;
                } else {
                    i = 0;
                }
                if (jsonParser.getCurrentToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after LocalDateTime ints");
                }
                return new LocalDateTime(intValue, intValue2, intValue3, intValue4, intValue5, intValue6, i);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new LocalDateTime(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime parseLocal = parseLocal(jsonParser);
                    if (parseLocal == null) {
                        return null;
                    }
                    return parseLocal.toLocalDateTime();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array or Number");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class DateMidnightDeserializer extends JodaDeserializer<DateMidnight> {
        public DateMidnightDeserializer() {
            super(DateMidnight.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public DateMidnight deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            if (jsonParser.isExpectedStartArrayToken()) {
                jsonParser.nextToken();
                int intValue = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue2 = jsonParser.getIntValue();
                jsonParser.nextToken();
                int intValue3 = jsonParser.getIntValue();
                if (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.END_ARRAY, "after DateMidnight ints");
                }
                return new DateMidnight(intValue, intValue2, intValue3);
            }
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new DateMidnight(jsonParser.getLongValue());
                case VALUE_STRING:
                    DateTime parseLocal = parseLocal(jsonParser);
                    if (parseLocal == null) {
                        return null;
                    }
                    return parseLocal.toDateMidnight();
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Array, Number or String");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PeriodDeserializer extends JodaDeserializer<ReadablePeriod> {
        public PeriodDeserializer() {
            super(ReadablePeriod.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public ReadablePeriod deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            switch (jsonParser.getCurrentToken()) {
                case VALUE_NUMBER_INT:
                    return new Period(jsonParser.getLongValue());
                case VALUE_STRING:
                    return new Period(jsonParser.getText());
                default:
                    throw deserializationContext.wrongTokenException(jsonParser, JsonToken.START_ARRAY, "expected JSON Number or String");
            }
        }
    }
}
