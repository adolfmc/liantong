package io.objectbox;

import io.objectbox.annotation.apihint.Internal;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.exception.DbException;
import io.objectbox.query.QueryCondition;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Property<ENTITY> implements Serializable {
    private static final long serialVersionUID = 8613291105982758093L;
    public final Class<? extends PropertyConverter> converterClass;
    public final Class customType;
    public final String dbName;
    public final EntityInfo<ENTITY> entity;

    /* renamed from: id */
    public final int f24389id;
    private boolean idVerified;
    public final boolean isId;
    public final boolean isVirtual;
    public final String name;
    public final int ordinal;
    public final Class<?> type;

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str) {
        this(entityInfo, i, i2, cls, str, false, str, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z) {
        this(entityInfo, i, i2, cls, str, false, z, str, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, @Nullable String str2) {
        this(entityInfo, i, i2, cls, str, z, str2, null, null);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, @Nullable String str2, @Nullable Class<? extends PropertyConverter> cls2, @Nullable Class cls3) {
        this(entityInfo, i, i2, cls, str, z, false, str2, cls2, cls3);
    }

    public Property(EntityInfo<ENTITY> entityInfo, int i, int i2, Class<?> cls, String str, boolean z, boolean z2, @Nullable String str2, @Nullable Class<? extends PropertyConverter> cls2, @Nullable Class cls3) {
        this.entity = entityInfo;
        this.ordinal = i;
        this.f24389id = i2;
        this.type = cls;
        this.name = str;
        this.isId = z;
        this.isVirtual = z2;
        this.dbName = str2;
        this.converterClass = cls2;
        this.customType = cls3;
    }

    /* renamed from: eq */
    public QueryCondition m1965eq(Object obj) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.EQUALS, obj);
    }

    public QueryCondition notEq(Object obj) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.NOT_EQUALS, obj);
    }

    public QueryCondition between(Object obj, Object obj2) {
        return new QueryCondition.PropertyCondition((Property) this, QueryCondition.PropertyCondition.Operation.BETWEEN, new Object[]{obj, obj2});
    }

    /* renamed from: in */
    public QueryCondition m1962in(Object... objArr) {
        return new QueryCondition.PropertyCondition((Property) this, QueryCondition.PropertyCondition.Operation.IN, objArr);
    }

    /* renamed from: in */
    public QueryCondition m1963in(Collection<?> collection) {
        return m1962in(collection.toArray());
    }

    /* renamed from: gt */
    public QueryCondition m1964gt(Object obj) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.GREATER_THAN, obj);
    }

    /* renamed from: lt */
    public QueryCondition m1961lt(Object obj) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.LESS_THAN, obj);
    }

    public QueryCondition isNull() {
        return new QueryCondition.PropertyCondition((Property) this, QueryCondition.PropertyCondition.Operation.IS_NULL, (Object[]) null);
    }

    public QueryCondition isNotNull() {
        return new QueryCondition.PropertyCondition((Property) this, QueryCondition.PropertyCondition.Operation.IS_NOT_NULL, (Object[]) null);
    }

    public QueryCondition contains(String str) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.CONTAINS, str);
    }

    public QueryCondition startsWith(String str) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.STARTS_WITH, str);
    }

    public QueryCondition endsWith(String str) {
        return new QueryCondition.PropertyCondition(this, QueryCondition.PropertyCondition.Operation.ENDS_WITH, str);
    }

    @Internal
    public int getEntityId() {
        return this.entity.getEntityId();
    }

    @Internal
    public int getId() {
        int i = this.f24389id;
        if (i > 0) {
            return i;
        }
        throw new IllegalStateException("Illegal property ID " + this.f24389id + " for " + toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isIdVerified() {
        return this.idVerified;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void verifyId(int i) {
        int i2 = this.f24389id;
        if (i2 <= 0) {
            throw new IllegalStateException("Illegal property ID " + this.f24389id + " for " + toString());
        } else if (i2 != i) {
            throw new DbException(toString() + " does not match ID in DB: " + i);
        } else {
            this.idVerified = true;
        }
    }

    public String toString() {
        return "Property \"" + this.name + "\" (ID: " + this.f24389id + ")";
    }
}
