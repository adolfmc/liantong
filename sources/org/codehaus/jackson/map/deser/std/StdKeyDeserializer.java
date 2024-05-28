package org.codehaus.jackson.map.deser.std;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.KeyDeserializer;
import org.codehaus.jackson.map.introspect.AnnotatedMethod;
import org.codehaus.jackson.map.util.ClassUtil;
import org.codehaus.jackson.map.util.EnumResolver;
import org.codehaus.jackson.p467io.NumberInput;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class StdKeyDeserializer extends KeyDeserializer {
    protected final Class<?> _keyClass;

    protected abstract Object _parse(String str, DeserializationContext deserializationContext) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public StdKeyDeserializer(Class<?> cls) {
        this._keyClass = cls;
    }

    @Override // org.codehaus.jackson.map.KeyDeserializer
    public final Object deserializeKey(String str, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (str == null) {
            return null;
        }
        try {
            Object _parse = _parse(str, deserializationContext);
            if (_parse != null) {
                return _parse;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not a valid representation");
        } catch (Exception e) {
            Class<?> cls = this._keyClass;
            throw deserializationContext.weirdKeyException(cls, str, "not a valid representation: " + e.getMessage());
        }
    }

    public Class<?> getKeyClass() {
        return this._keyClass;
    }

    protected int _parseInt(String str) throws IllegalArgumentException {
        return Integer.parseInt(str);
    }

    protected long _parseLong(String str) throws IllegalArgumentException {
        return Long.parseLong(str);
    }

    protected double _parseDouble(String str) throws IllegalArgumentException {
        return NumberInput.parseDouble(str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class StringKD extends StdKeyDeserializer {
        private static final StringKD sString = new StringKD(String.class);
        private static final StringKD sObject = new StringKD(Object.class);

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public String _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return str;
        }

        private StringKD(Class<?> cls) {
            super(cls);
        }

        public static StringKD forType(Class<?> cls) {
            if (cls == String.class) {
                return sString;
            }
            if (cls == Object.class) {
                return sObject;
            }
            return new StringKD(cls);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class BoolKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public BoolKD() {
            super(Boolean.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Boolean _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if ("true".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equals(str)) {
                return Boolean.FALSE;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "value not 'true' or 'false'");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class ByteKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ByteKD() {
            super(Byte.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Byte _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt < -128 || _parseInt > 255) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 8-bit value");
            }
            return Byte.valueOf((byte) _parseInt);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class ShortKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public ShortKD() {
            super(Integer.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Short _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            int _parseInt = _parseInt(str);
            if (_parseInt < -32768 || _parseInt > 32767) {
                throw deserializationContext.weirdKeyException(this._keyClass, str, "overflow, value can not be represented as 16-bit value");
            }
            return Short.valueOf((short) _parseInt);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class CharKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public CharKD() {
            super(Character.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Character _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            if (str.length() == 1) {
                return Character.valueOf(str.charAt(0));
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "can only convert 1-character Strings");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class IntKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public IntKD() {
            super(Integer.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Integer _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Integer.valueOf(_parseInt(str));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class LongKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public LongKD() {
            super(Long.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Long _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Long.valueOf(_parseLong(str));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class DoubleKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public DoubleKD() {
            super(Double.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Double _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Double.valueOf(_parseDouble(str));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class FloatKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: package-private */
        public FloatKD() {
            super(Float.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Float _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            return Float.valueOf((float) _parseDouble(str));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class EnumKD extends StdKeyDeserializer {
        protected final AnnotatedMethod _factory;
        protected final EnumResolver<?> _resolver;

        /* JADX INFO: Access modifiers changed from: protected */
        public EnumKD(EnumResolver<?> enumResolver, AnnotatedMethod annotatedMethod) {
            super(enumResolver.getEnumClass());
            this._resolver = enumResolver;
            this._factory = annotatedMethod;
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws JsonMappingException {
            AnnotatedMethod annotatedMethod = this._factory;
            if (annotatedMethod != null) {
                try {
                    return annotatedMethod.call1(str);
                } catch (Exception e) {
                    ClassUtil.unwrapAndThrowAsIAE(e);
                }
            }
            Object findEnum = this._resolver.findEnum(str);
            if (findEnum != null) {
                return findEnum;
            }
            throw deserializationContext.weirdKeyException(this._keyClass, str, "not one of values for Enum class");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class StringCtorKeyDeserializer extends StdKeyDeserializer {
        protected final Constructor<?> _ctor;

        public StringCtorKeyDeserializer(Constructor<?> constructor) {
            super(constructor.getDeclaringClass());
            this._ctor = constructor;
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._ctor.newInstance(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class StringFactoryKeyDeserializer extends StdKeyDeserializer {
        final Method _factoryMethod;

        public StringFactoryKeyDeserializer(Method method) {
            super(method.getDeclaringClass());
            this._factoryMethod = method;
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Object _parse(String str, DeserializationContext deserializationContext) throws Exception {
            return this._factoryMethod.invoke(null, str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class DateKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public DateKD() {
            super(Date.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Date _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return deserializationContext.parseDate(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class CalendarKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public CalendarKD() {
            super(Calendar.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public Calendar _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            Date parseDate = deserializationContext.parseDate(str);
            if (parseDate == null) {
                return null;
            }
            return deserializationContext.constructCalendar(parseDate);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class UuidKD extends StdKeyDeserializer {
        /* JADX INFO: Access modifiers changed from: protected */
        public UuidKD() {
            super(UUID.class);
        }

        @Override // org.codehaus.jackson.map.deser.std.StdKeyDeserializer
        public UUID _parse(String str, DeserializationContext deserializationContext) throws IllegalArgumentException, JsonMappingException {
            return UUID.fromString(str);
        }
    }
}
