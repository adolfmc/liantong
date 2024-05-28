package org.codehaus.jackson.map.deser;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.DeserializationProblemHandler;
import org.codehaus.jackson.map.DeserializerProvider;
import org.codehaus.jackson.map.InjectableValues;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;
import org.codehaus.jackson.map.util.ArrayBuilders;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.LinkedNode;
import org.codehaus.jackson.map.util.ObjectBuffer;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StdDeserializationContext extends DeserializationContext {
    static final int MAX_ERROR_STR_LEN = 500;
    protected ArrayBuilders _arrayBuilders;
    protected DateFormat _dateFormat;
    protected final DeserializerProvider _deserProvider;
    protected final InjectableValues _injectableValues;
    protected ObjectBuffer _objectBuffer;
    protected JsonParser _parser;

    public StdDeserializationContext(DeserializationConfig deserializationConfig, JsonParser jsonParser, DeserializerProvider deserializerProvider, InjectableValues injectableValues) {
        super(deserializationConfig);
        this._parser = jsonParser;
        this._deserProvider = deserializerProvider;
        this._injectableValues = injectableValues;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public DeserializerProvider getDeserializerProvider() {
        return this._deserProvider;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonParser getParser() {
        return this._parser;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public Object findInjectableValue(Object obj, BeanProperty beanProperty, Object obj2) {
        InjectableValues injectableValues = this._injectableValues;
        if (injectableValues == null) {
            throw new IllegalStateException("No 'injectableValues' configured, can not inject value with id [" + obj + "]");
        }
        return injectableValues.findInjectableValue(obj, this, beanProperty, obj2);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public final ObjectBuffer leaseObjectBuffer() {
        ObjectBuffer objectBuffer = this._objectBuffer;
        if (objectBuffer == null) {
            return new ObjectBuffer();
        }
        this._objectBuffer = null;
        return objectBuffer;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public final void returnObjectBuffer(ObjectBuffer objectBuffer) {
        if (this._objectBuffer == null || objectBuffer.initialCapacity() >= this._objectBuffer.initialCapacity()) {
            this._objectBuffer = objectBuffer;
        }
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public final ArrayBuilders getArrayBuilders() {
        if (this._arrayBuilders == null) {
            this._arrayBuilders = new ArrayBuilders();
        }
        return this._arrayBuilders;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public Date parseDate(String str) throws IllegalArgumentException {
        try {
            return getDateFormat().parse(str);
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public Calendar constructCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public boolean handleUnknownProperty(JsonParser jsonParser, JsonDeserializer<?> jsonDeserializer, Object obj, String str) throws IOException, JsonProcessingException {
        LinkedNode<DeserializationProblemHandler> problemHandlers = this._config.getProblemHandlers();
        if (problemHandlers != null) {
            JsonParser jsonParser2 = this._parser;
            this._parser = jsonParser;
            while (problemHandlers != null) {
                try {
                    if (!problemHandlers.value().handleUnknownProperty(this, jsonDeserializer, obj, str)) {
                        problemHandlers = problemHandlers.next();
                    } else {
                        return true;
                    }
                } finally {
                    this._parser = jsonParser2;
                }
            }
            return false;
        }
        return false;
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class<?> cls) {
        return mappingException(cls, this._parser.getCurrentToken());
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException mappingException(Class<?> cls, JsonToken jsonToken) {
        String _calcName = _calcName(cls);
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not deserialize instance of " + _calcName + " out of " + jsonToken + " token");
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class<?> cls, Throwable th) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + ", problem: " + th.getMessage(), th);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException instantiationException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + ", problem: " + str);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdStringException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + " from String value '" + _valueDesc() + "': " + str);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdNumberException(Class<?> cls, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct instance of " + cls.getName() + " from number value (" + _valueDesc() + "): " + str);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException weirdKeyException(Class<?> cls, String str, String str2) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Can not construct Map key of type " + cls.getName() + " from String \"" + _desc(str) + "\": " + str2);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException wrongTokenException(JsonParser jsonParser, JsonToken jsonToken, String str) {
        return JsonMappingException.from(jsonParser, "Unexpected token (" + jsonParser.getCurrentToken() + "), expected " + jsonToken + ": " + str);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownFieldException(Object obj, String str) {
        return UnrecognizedPropertyException.from(this._parser, obj, str);
    }

    @Override // org.codehaus.jackson.map.DeserializationContext
    public JsonMappingException unknownTypeException(JavaType javaType, String str) {
        JsonParser jsonParser = this._parser;
        return JsonMappingException.from(jsonParser, "Could not resolve type id '" + str + "' into a subtype of " + javaType);
    }

    protected DateFormat getDateFormat() {
        if (this._dateFormat == null) {
            this._dateFormat = (DateFormat) this._config.getDateFormat().clone();
        }
        return this._dateFormat;
    }

    protected String determineClassName(Object obj) {
        return ClassUtil.getClassDescription(obj);
    }

    protected String _calcName(Class<?> cls) {
        if (cls.isArray()) {
            return _calcName(cls.getComponentType()) + "[]";
        }
        return cls.getName();
    }

    protected String _valueDesc() {
        try {
            return _desc(this._parser.getText());
        } catch (Exception unused) {
            return "[N/A]";
        }
    }

    protected String _desc(String str) {
        if (str.length() > 500) {
            return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
        }
        return str;
    }
}
