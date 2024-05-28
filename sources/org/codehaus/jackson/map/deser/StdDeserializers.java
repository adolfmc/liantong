package org.codehaus.jackson.map.deser;

import java.util.GregorianCalendar;
import java.util.HashMap;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.std.AtomicBooleanDeserializer;
import org.codehaus.jackson.map.deser.std.CalendarDeserializer;
import org.codehaus.jackson.map.deser.std.ClassDeserializer;
import org.codehaus.jackson.map.deser.std.JavaTypeDeserializer;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.map.deser.std.StringDeserializer;
import org.codehaus.jackson.map.deser.std.TimestampDeserializer;
import org.codehaus.jackson.map.deser.std.TokenBufferDeserializer;
import org.codehaus.jackson.map.type.ClassKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class StdDeserializers {
    final HashMap<ClassKey, JsonDeserializer<Object>> _deserializers = new HashMap<>();

    private StdDeserializers() {
        add(new org.codehaus.jackson.map.deser.std.UntypedObjectDeserializer());
        StringDeserializer stringDeserializer = new StringDeserializer();
        add(stringDeserializer, String.class);
        add(stringDeserializer, CharSequence.class);
        add(new ClassDeserializer());
        add(new StdDeserializer.BooleanDeserializer(Boolean.class, null));
        add(new StdDeserializer.ByteDeserializer(Byte.class, null));
        add(new StdDeserializer.ShortDeserializer(Short.class, null));
        add(new StdDeserializer.CharacterDeserializer(Character.class, null));
        add(new StdDeserializer.IntegerDeserializer(Integer.class, null));
        add(new StdDeserializer.LongDeserializer(Long.class, null));
        add(new StdDeserializer.FloatDeserializer(Float.class, null));
        add(new StdDeserializer.DoubleDeserializer(Double.class, null));
        add(new StdDeserializer.BooleanDeserializer(Boolean.TYPE, Boolean.FALSE));
        add(new StdDeserializer.ByteDeserializer(Byte.TYPE, (byte) 0));
        add(new StdDeserializer.ShortDeserializer(Short.TYPE, (short) 0));
        add(new StdDeserializer.CharacterDeserializer(Character.TYPE, (char) 0));
        add(new StdDeserializer.IntegerDeserializer(Integer.TYPE, 0));
        add(new StdDeserializer.LongDeserializer(Long.TYPE, 0L));
        add(new StdDeserializer.FloatDeserializer(Float.TYPE, Float.valueOf(0.0f)));
        add(new StdDeserializer.DoubleDeserializer(Double.TYPE, Double.valueOf(0.0d)));
        add(new StdDeserializer.NumberDeserializer());
        add(new StdDeserializer.BigDecimalDeserializer());
        add(new StdDeserializer.BigIntegerDeserializer());
        add(new CalendarDeserializer());
        add(new org.codehaus.jackson.map.deser.std.DateDeserializer());
        add(new CalendarDeserializer(GregorianCalendar.class), GregorianCalendar.class);
        add(new StdDeserializer.SqlDateDeserializer());
        add(new TimestampDeserializer());
        for (org.codehaus.jackson.map.deser.std.FromStringDeserializer<?> fromStringDeserializer : org.codehaus.jackson.map.deser.std.FromStringDeserializer.all()) {
            add(fromStringDeserializer);
        }
        add(new StdDeserializer.StackTraceElementDeserializer());
        add(new AtomicBooleanDeserializer());
        add(new TokenBufferDeserializer());
        add(new JavaTypeDeserializer());
    }

    public static HashMap<ClassKey, JsonDeserializer<Object>> constructAll() {
        return new StdDeserializers()._deserializers;
    }

    private void add(org.codehaus.jackson.map.deser.std.StdDeserializer<?> stdDeserializer) {
        add(stdDeserializer, stdDeserializer.getValueClass());
    }

    private void add(org.codehaus.jackson.map.deser.std.StdDeserializer<?> stdDeserializer, Class<?> cls) {
        this._deserializers.put(new ClassKey(cls), stdDeserializer);
    }
}
