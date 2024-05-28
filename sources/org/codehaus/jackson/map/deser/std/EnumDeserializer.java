package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.lang.reflect.Method;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.annotate.JsonCachable;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumResolver;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JsonCachable
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    protected final EnumResolver<?> _resolver;

    public EnumDeserializer(EnumResolver<?> enumResolver) {
        super(Enum.class);
        this._resolver = enumResolver;
    }

    public static JsonDeserializer<?> deserializerForCreator(DeserializationConfig deserializationConfig, Class<?> cls, AnnotatedMethod annotatedMethod) {
        Class cls2;
        Class<?> parameterClass = annotatedMethod.getParameterClass(0);
        if (parameterClass == String.class) {
            cls2 = null;
        } else if (parameterClass == Integer.TYPE || parameterClass == Integer.class) {
            cls2 = Integer.class;
        } else if (parameterClass == Long.TYPE || parameterClass == Long.class) {
            cls2 = Long.class;
        } else {
            throw new IllegalArgumentException("Parameter #0 type for factory method (" + annotatedMethod + ") not suitable, must be java.lang.String or int/Integer/long/Long");
        }
        if (deserializationConfig.isEnabled(DeserializationConfig.Feature.CAN_OVERRIDE_ACCESS_MODIFIERS)) {
            ClassUtil.checkAndFixAccess(annotatedMethod.getMember());
        }
        return new FactoryBasedDeserializer(cls, annotatedMethod, cls2);
    }

    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.Enum, java.lang.Enum<?>] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, java.lang.Enum<?>] */
    @Override // org.codehaus.jackson.map.JsonDeserializer
    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            ?? findEnum = this._resolver.findEnum(jsonParser.getText());
            if (findEnum != 0) {
                return findEnum;
            }
            throw deserializationContext.weirdStringException(this._resolver.getEnumClass(), "value not one of declared Enum instance names");
        } else if (currentToken == JsonToken.VALUE_NUMBER_INT) {
            if (deserializationContext.isEnabled(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
            }
            ?? r3 = this._resolver.getEnum(jsonParser.getIntValue());
            if (r3 != 0) {
                return r3;
            }
            Class<?> enumClass = this._resolver.getEnumClass();
            throw deserializationContext.weirdNumberException(enumClass, "index value outside legal index range [0.." + this._resolver.lastValidIndex() + "]");
        } else {
            throw deserializationContext.mappingException(this._resolver.getEnumClass());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        public FactoryBasedDeserializer(Class<?> cls, AnnotatedMethod annotatedMethod, Class<?> cls2) {
            super(Enum.class);
            this._enumClass = cls;
            this._factory = annotatedMethod.getAnnotated();
            this._inputType = cls2;
        }

        @Override // org.codehaus.jackson.map.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Object valueOf;
            Class<?> cls = this._inputType;
            if (cls == null) {
                valueOf = jsonParser.getText();
            } else if (cls == Integer.class) {
                valueOf = Integer.valueOf(jsonParser.getValueAsInt());
            } else if (cls == Long.class) {
                valueOf = Long.valueOf(jsonParser.getValueAsLong());
            } else {
                throw deserializationContext.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, valueOf);
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }
    }
}
