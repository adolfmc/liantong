package org.codehaus.jackson.map.ext;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.deser.std.StdScalarDeserializer;
import org.codehaus.jackson.map.util.Provider;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CoreXMLDeserializers implements Provider<StdDeserializer<?>> {
    static final DatatypeFactory _dataTypeFactory;

    static {
        try {
            _dataTypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.codehaus.jackson.map.util.Provider
    public Collection<StdDeserializer<?>> provide() {
        return Arrays.asList(new DurationDeserializer(), new GregorianCalendarDeserializer(), new QNameDeserializer());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class DurationDeserializer extends FromStringDeserializer<Duration> {
        public DurationDeserializer() {
            super(Duration.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Duration _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return CoreXMLDeserializers._dataTypeFactory.newDuration(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class GregorianCalendarDeserializer extends StdScalarDeserializer<XMLGregorianCalendar> {
        public GregorianCalendarDeserializer() {
            super(XMLGregorianCalendar.class);
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public XMLGregorianCalendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Date _parseDate = _parseDate(jsonParser, deserializationContext);
            if (_parseDate == null) {
                return null;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(_parseDate);
            return CoreXMLDeserializers._dataTypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class QNameDeserializer extends FromStringDeserializer<QName> {
        public QNameDeserializer() {
            super(QName.class);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public QName _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return QName.valueOf(str);
        }
    }
}
