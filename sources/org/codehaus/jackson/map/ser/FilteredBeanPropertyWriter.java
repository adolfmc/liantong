package org.codehaus.jackson.map.ser;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class FilteredBeanPropertyWriter {
    public static BeanPropertyWriter constructViewBased(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
        if (clsArr.length == 1) {
            return new SingleView(beanPropertyWriter, clsArr[0]);
        }
        return new MultiView(beanPropertyWriter, clsArr);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class SingleView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?> _view;

        protected SingleView(BeanPropertyWriter beanPropertyWriter, Class<?> cls) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._view = cls;
        }

        @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
            return new SingleView(this._delegate.withSerializer(jsonSerializer), this._view);
        }

        @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView == null || this._view.isAssignableFrom(serializationView)) {
                this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class MultiView extends BeanPropertyWriter {
        protected final BeanPropertyWriter _delegate;
        protected final Class<?>[] _views;

        protected MultiView(BeanPropertyWriter beanPropertyWriter, Class<?>[] clsArr) {
            super(beanPropertyWriter);
            this._delegate = beanPropertyWriter;
            this._views = clsArr;
        }

        @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
        public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
            return new MultiView(this._delegate.withSerializer(jsonSerializer), this._views);
        }

        @Override // org.codehaus.jackson.map.ser.BeanPropertyWriter
        public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
            Class<?> serializationView = serializerProvider.getSerializationView();
            if (serializationView != null) {
                int i = 0;
                int length = this._views.length;
                while (i < length && !this._views[i].isAssignableFrom(serializationView)) {
                    i++;
                }
                if (i == length) {
                    return;
                }
            }
            this._delegate.serializeAsField(obj, jsonGenerator, serializerProvider);
        }
    }
}
