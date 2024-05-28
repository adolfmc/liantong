package org.codehaus.jackson.map.deser.impl;

import java.lang.reflect.Member;
import java.util.HashMap;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.deser.ValueInstantiator;
import org.codehaus.jackson.map.deser.std.StdValueInstantiator;
import org.codehaus.jackson.map.introspect.AnnotatedConstructor;
import org.codehaus.jackson.map.introspect.AnnotatedWithParams;
import org.codehaus.jackson.map.introspect.BasicBeanDescription;
import org.codehaus.jackson.map.util.ClassUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CreatorCollector {
    final BasicBeanDescription _beanDesc;
    protected AnnotatedWithParams _booleanCreator;
    final boolean _canFixAccess;
    protected AnnotatedConstructor _defaultConstructor;
    protected AnnotatedWithParams _delegateCreator;
    protected AnnotatedWithParams _doubleCreator;
    protected AnnotatedWithParams _intCreator;
    protected AnnotatedWithParams _longCreator;
    protected CreatorProperty[] _propertyBasedArgs = null;
    protected AnnotatedWithParams _propertyBasedCreator;
    protected AnnotatedWithParams _stringCreator;

    public CreatorCollector(BasicBeanDescription basicBeanDescription, boolean z) {
        this._beanDesc = basicBeanDescription;
        this._canFixAccess = z;
    }

    public ValueInstantiator constructValueInstantiator(DeserializationConfig deserializationConfig) {
        StdValueInstantiator stdValueInstantiator = new StdValueInstantiator(deserializationConfig, this._beanDesc.getType());
        stdValueInstantiator.configureFromObjectSettings(this._defaultConstructor, this._delegateCreator, this._delegateCreator == null ? null : this._beanDesc.bindingsForBeanType().resolveType(this._delegateCreator.getParameterType(0)), this._propertyBasedCreator, this._propertyBasedArgs);
        stdValueInstantiator.configureFromStringCreator(this._stringCreator);
        stdValueInstantiator.configureFromIntCreator(this._intCreator);
        stdValueInstantiator.configureFromLongCreator(this._longCreator);
        stdValueInstantiator.configureFromDoubleCreator(this._doubleCreator);
        stdValueInstantiator.configureFromBooleanCreator(this._booleanCreator);
        return stdValueInstantiator;
    }

    public void setDefaultConstructor(AnnotatedConstructor annotatedConstructor) {
        this._defaultConstructor = annotatedConstructor;
    }

    public void addStringCreator(AnnotatedWithParams annotatedWithParams) {
        this._stringCreator = verifyNonDup(annotatedWithParams, this._stringCreator, "String");
    }

    public void addIntCreator(AnnotatedWithParams annotatedWithParams) {
        this._intCreator = verifyNonDup(annotatedWithParams, this._intCreator, "int");
    }

    public void addLongCreator(AnnotatedWithParams annotatedWithParams) {
        this._longCreator = verifyNonDup(annotatedWithParams, this._longCreator, "long");
    }

    public void addDoubleCreator(AnnotatedWithParams annotatedWithParams) {
        this._doubleCreator = verifyNonDup(annotatedWithParams, this._doubleCreator, "double");
    }

    public void addBooleanCreator(AnnotatedWithParams annotatedWithParams) {
        this._booleanCreator = verifyNonDup(annotatedWithParams, this._booleanCreator, "boolean");
    }

    public void addDelegatingCreator(AnnotatedWithParams annotatedWithParams) {
        this._delegateCreator = verifyNonDup(annotatedWithParams, this._delegateCreator, "delegate");
    }

    public void addPropertyCreator(AnnotatedWithParams annotatedWithParams, CreatorProperty[] creatorPropertyArr) {
        Integer num;
        this._propertyBasedCreator = verifyNonDup(annotatedWithParams, this._propertyBasedCreator, "property-based");
        if (creatorPropertyArr.length > 1) {
            HashMap hashMap = new HashMap();
            int length = creatorPropertyArr.length;
            for (int i = 0; i < length; i++) {
                String name = creatorPropertyArr[i].getName();
                if ((name.length() != 0 || creatorPropertyArr[i].getInjectableValueId() == null) && (num = (Integer) hashMap.put(name, Integer.valueOf(i))) != null) {
                    throw new IllegalArgumentException("Duplicate creator property \"" + name + "\" (index " + num + " vs " + i + ")");
                }
            }
        }
        this._propertyBasedArgs = creatorPropertyArr;
    }

    protected AnnotatedWithParams verifyNonDup(AnnotatedWithParams annotatedWithParams, AnnotatedWithParams annotatedWithParams2, String str) {
        if (annotatedWithParams2 != null && annotatedWithParams2.getClass() == annotatedWithParams.getClass()) {
            throw new IllegalArgumentException("Conflicting " + str + " creators: already had " + annotatedWithParams2 + ", encountered " + annotatedWithParams);
        }
        if (this._canFixAccess) {
            ClassUtil.checkAndFixAccess((Member) annotatedWithParams.getAnnotated());
        }
        return annotatedWithParams;
    }
}
