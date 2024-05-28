package org.codehaus.jackson.map.ser.impl;

import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.map.ser.BeanPropertyFilter;
import org.codehaus.jackson.map.ser.FilterProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SimpleFilterProvider extends FilterProvider {
    protected boolean _cfgFailOnUnknownId;
    protected BeanPropertyFilter _defaultFilter;
    protected final Map<String, BeanPropertyFilter> _filtersById;

    public SimpleFilterProvider() {
        this(new HashMap());
    }

    public SimpleFilterProvider(Map<String, BeanPropertyFilter> map) {
        this._cfgFailOnUnknownId = true;
        this._filtersById = map;
    }

    public SimpleFilterProvider setDefaultFilter(BeanPropertyFilter beanPropertyFilter) {
        this._defaultFilter = beanPropertyFilter;
        return this;
    }

    public BeanPropertyFilter getDefaultFilter() {
        return this._defaultFilter;
    }

    public SimpleFilterProvider setFailOnUnknownId(boolean z) {
        this._cfgFailOnUnknownId = z;
        return this;
    }

    public boolean willFailOnUnknownId() {
        return this._cfgFailOnUnknownId;
    }

    public SimpleFilterProvider addFilter(String str, BeanPropertyFilter beanPropertyFilter) {
        this._filtersById.put(str, beanPropertyFilter);
        return this;
    }

    public BeanPropertyFilter removeFilter(String str) {
        return this._filtersById.remove(str);
    }

    @Override // org.codehaus.jackson.map.ser.FilterProvider
    public BeanPropertyFilter findFilter(Object obj) {
        BeanPropertyFilter beanPropertyFilter = this._filtersById.get(obj);
        if (beanPropertyFilter == null && (beanPropertyFilter = this._defaultFilter) == null && this._cfgFailOnUnknownId) {
            throw new IllegalArgumentException("No filter configured with id '" + obj + "' (type " + obj.getClass().getName() + ")");
        }
        return beanPropertyFilter;
    }
}
