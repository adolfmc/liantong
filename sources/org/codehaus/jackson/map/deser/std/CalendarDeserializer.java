package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CalendarDeserializer extends StdScalarDeserializer<Calendar> {
    protected final Class<? extends Calendar> _calendarClass;

    public CalendarDeserializer() {
        this(null);
    }

    public CalendarDeserializer(Class<? extends Calendar> cls) {
        super(Calendar.class);
        this._calendarClass = cls;
    }

    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Calendar deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Date _parseDate = _parseDate(jsonParser, deserializationContext);
        if (_parseDate == null) {
            return null;
        }
        Class<? extends Calendar> cls = this._calendarClass;
        if (cls == null) {
            return deserializationContext.constructCalendar(_parseDate);
        }
        try {
            Calendar newInstance = cls.newInstance();
            newInstance.setTimeInMillis(_parseDate.getTime());
            return newInstance;
        } catch (Exception e) {
            throw deserializationContext.instantiationException(this._calendarClass, e);
        }
    }
}
