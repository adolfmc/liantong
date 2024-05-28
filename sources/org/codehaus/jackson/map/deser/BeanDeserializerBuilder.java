package org.codehaus.jackson.map.deser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.BeanPropertyDefinition;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.deser.impl.BeanPropertyMap;
import org.codehaus.jackson.map.deser.impl.ValueInjector;
import org.codehaus.jackson.map.introspect.AnnotatedMember;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.Annotations;
import org.codehaus.jackson.type.JavaType;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BeanDeserializerBuilder {
    protected SettableAnyProperty _anySetter;
    protected HashMap<String, SettableBeanProperty> _backRefProperties;
    protected final BasicBeanDescription _beanDesc;
    protected HashSet<String> _ignorableProps;
    protected boolean _ignoreAllUnknown;
    protected List<ValueInjector> _injectables;
    protected final HashMap<String, SettableBeanProperty> _properties = new LinkedHashMap();
    protected ValueInstantiator _valueInstantiator;

    public void addCreatorProperty(BeanPropertyDefinition beanPropertyDefinition) {
    }

    public BeanDeserializerBuilder(BasicBeanDescription basicBeanDescription) {
        this._beanDesc = basicBeanDescription;
    }

    protected BeanDeserializerBuilder(BeanDeserializerBuilder beanDeserializerBuilder) {
        this._beanDesc = beanDeserializerBuilder._beanDesc;
        this._anySetter = beanDeserializerBuilder._anySetter;
        this._ignoreAllUnknown = beanDeserializerBuilder._ignoreAllUnknown;
        this._properties.putAll(beanDeserializerBuilder._properties);
        this._backRefProperties = _copy(beanDeserializerBuilder._backRefProperties);
        this._ignorableProps = beanDeserializerBuilder._ignorableProps;
        this._valueInstantiator = beanDeserializerBuilder._valueInstantiator;
    }

    private static HashMap<String, SettableBeanProperty> _copy(HashMap<String, SettableBeanProperty> hashMap) {
        if (hashMap == null) {
            return null;
        }
        return new HashMap<>(hashMap);
    }

    public void addOrReplaceProperty(SettableBeanProperty settableBeanProperty, boolean z) {
        this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        SettableBeanProperty put = this._properties.put(settableBeanProperty.getName(), settableBeanProperty);
        if (put == null || put == settableBeanProperty) {
            return;
        }
        throw new IllegalArgumentException("Duplicate property '" + settableBeanProperty.getName() + "' for " + this._beanDesc.getType());
    }

    public void addBackReferenceProperty(String str, SettableBeanProperty settableBeanProperty) {
        if (this._backRefProperties == null) {
            this._backRefProperties = new HashMap<>(4);
        }
        this._backRefProperties.put(str, settableBeanProperty);
        HashMap<String, SettableBeanProperty> hashMap = this._properties;
        if (hashMap != null) {
            hashMap.remove(settableBeanProperty.getName());
        }
    }

    public void addInjectable(String str, JavaType javaType, Annotations annotations, AnnotatedMember annotatedMember, Object obj) {
        if (this._injectables == null) {
            this._injectables = new ArrayList();
        }
        this._injectables.add(new ValueInjector(str, javaType, annotations, annotatedMember, obj));
    }

    public void addIgnorable(String str) {
        if (this._ignorableProps == null) {
            this._ignorableProps = new HashSet<>();
        }
        this._ignorableProps.add(str);
    }

    public void setAnySetter(SettableAnyProperty settableAnyProperty) {
        if (this._anySetter != null && settableAnyProperty != null) {
            throw new IllegalStateException("_anySetter already set to non-null");
        }
        this._anySetter = settableAnyProperty;
    }

    public void setIgnoreUnknownProperties(boolean z) {
        this._ignoreAllUnknown = z;
    }

    public void setValueInstantiator(ValueInstantiator valueInstantiator) {
        this._valueInstantiator = valueInstantiator;
    }

    public Iterator<SettableBeanProperty> getProperties() {
        return this._properties.values().iterator();
    }

    public boolean hasProperty(String str) {
        return this._properties.containsKey(str);
    }

    public SettableBeanProperty removeProperty(String str) {
        return this._properties.remove(str);
    }

    public ValueInstantiator getValueInstantiator() {
        return this._valueInstantiator;
    }

    public JsonDeserializer<?> build(BeanProperty beanProperty) {
        BeanPropertyMap beanPropertyMap = new BeanPropertyMap(this._properties.values());
        beanPropertyMap.assignIndexes();
        return new BeanDeserializer(this._beanDesc, beanProperty, this._valueInstantiator, beanPropertyMap, this._backRefProperties, this._ignorableProps, this._ignoreAllUnknown, this._anySetter, this._injectables);
    }
}
