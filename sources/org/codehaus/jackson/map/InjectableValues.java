package org.codehaus.jackson.map;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class InjectableValues {
    public abstract Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2);

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Std extends InjectableValues {
        protected final Map<String, Object> _values;

        public Std() {
            this(new HashMap());
        }

        public Std(Map<String, Object> map) {
            this._values = map;
        }

        public Std addValue(String str, Object obj) {
            this._values.put(str, obj);
            return this;
        }

        public Std addValue(Class<?> cls, Object obj) {
            this._values.put(cls.getName(), obj);
            return this;
        }

        @Override // org.codehaus.jackson.map.InjectableValues
        public Object findInjectableValue(Object obj, DeserializationContext deserializationContext, BeanProperty beanProperty, Object obj2) {
            if (!(obj instanceof String)) {
                String name = obj == null ? "[null]" : obj.getClass().getName();
                throw new IllegalArgumentException("Unrecognized inject value id type (" + name + "), expecting String");
            }
            String str = (String) obj;
            Object obj3 = this._values.get(str);
            if (obj3 != null || this._values.containsKey(str)) {
                return obj3;
            }
            throw new IllegalArgumentException("No injectable id with value '" + str + "' found (for property '" + beanProperty.getName() + "')");
        }
    }
}
