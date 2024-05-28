package io.objectbox.query;

import io.objectbox.Property;
import io.objectbox.annotation.apihint.Experimental;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbException;
import io.objectbox.query.QueryBuilder;
import java.util.Date;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Experimental
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface QueryCondition {
    void applyTo(QueryBuilder queryBuilder, QueryBuilder.StringOrder stringOrder);

    void setParameterFor(Query query, Object obj);

    void setParameterFor(Query query, Object obj, Object obj2);

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractCondition implements QueryCondition {
        public final Object value;
        protected final Object[] values;

        AbstractCondition(Object obj) {
            this.value = obj;
            this.values = null;
        }

        AbstractCondition(@Nullable Object[] objArr) {
            this.value = null;
            this.values = objArr;
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class PropertyCondition extends AbstractCondition {
        private final Operation operation;
        public final Property property;

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public enum Operation {
            EQUALS,
            NOT_EQUALS,
            BETWEEN,
            IN,
            GREATER_THAN,
            LESS_THAN,
            IS_NULL,
            IS_NOT_NULL,
            CONTAINS,
            STARTS_WITH,
            ENDS_WITH
        }

        public PropertyCondition(Property property, Operation operation, @Nullable Object obj) {
            super(checkValueForType(property, obj));
            this.property = property;
            this.operation = operation;
        }

        public PropertyCondition(Property property, Operation operation, @Nullable Object[] objArr) {
            super(checkValuesForType(property, operation, objArr));
            this.property = property;
            this.operation = operation;
        }

        @Override // io.objectbox.query.QueryCondition
        public void applyTo(QueryBuilder queryBuilder, QueryBuilder.StringOrder stringOrder) {
            if (this.operation == Operation.EQUALS) {
                if (this.value instanceof Long) {
                    queryBuilder.equal(this.property, ((Long) this.value).longValue());
                } else if (this.value instanceof Integer) {
                    queryBuilder.equal(this.property, ((Integer) this.value).intValue());
                } else if (this.value instanceof String) {
                    queryBuilder.equal(this.property, (String) this.value, stringOrder);
                }
            } else if (this.operation == Operation.NOT_EQUALS) {
                if (this.value instanceof Long) {
                    queryBuilder.notEqual(this.property, ((Long) this.value).longValue());
                } else if (this.value instanceof Integer) {
                    queryBuilder.notEqual(this.property, ((Integer) this.value).intValue());
                } else if (this.value instanceof String) {
                    queryBuilder.notEqual(this.property, (String) this.value, stringOrder);
                }
            } else {
                int i = 0;
                if (this.operation == Operation.BETWEEN) {
                    if ((this.values[0] instanceof Long) && (this.values[1] instanceof Long)) {
                        queryBuilder.between(this.property, ((Long) this.values[0]).longValue(), ((Long) this.values[1]).longValue());
                    } else if ((this.values[0] instanceof Integer) && (this.values[1] instanceof Integer)) {
                        queryBuilder.between(this.property, ((Integer) this.values[0]).intValue(), ((Integer) this.values[1]).intValue());
                    } else if ((this.values[0] instanceof Double) && (this.values[1] instanceof Double)) {
                        queryBuilder.between(this.property, ((Double) this.values[0]).doubleValue(), ((Double) this.values[1]).doubleValue());
                    } else if ((this.values[0] instanceof Float) && (this.values[1] instanceof Float)) {
                        queryBuilder.between(this.property, ((Float) this.values[0]).floatValue(), ((Float) this.values[1]).floatValue());
                    }
                } else if (this.operation == Operation.IN) {
                    if (this.values[0] instanceof Long) {
                        long[] jArr = new long[this.values.length];
                        while (i < this.values.length) {
                            jArr[i] = ((Long) this.values[i]).longValue();
                            i++;
                        }
                        queryBuilder.m1951in(this.property, jArr);
                    } else if (this.values[0] instanceof Integer) {
                        int[] iArr = new int[this.values.length];
                        while (i < this.values.length) {
                            iArr[i] = ((Integer) this.values[i]).intValue();
                            i++;
                        }
                        queryBuilder.m1952in(this.property, iArr);
                    }
                } else if (this.operation == Operation.GREATER_THAN) {
                    if (this.value instanceof Long) {
                        queryBuilder.greater(this.property, ((Long) this.value).longValue());
                    } else if (this.value instanceof Integer) {
                        queryBuilder.greater(this.property, ((Integer) this.value).intValue());
                    } else if (this.value instanceof Double) {
                        queryBuilder.greater(this.property, ((Double) this.value).doubleValue());
                    } else if (this.value instanceof Float) {
                        queryBuilder.greater(this.property, ((Float) this.value).floatValue());
                    }
                } else if (this.operation == Operation.LESS_THAN) {
                    if (this.value instanceof Long) {
                        queryBuilder.less(this.property, ((Long) this.value).longValue());
                    } else if (this.value instanceof Integer) {
                        queryBuilder.less(this.property, ((Integer) this.value).intValue());
                    } else if (this.value instanceof Double) {
                        queryBuilder.less(this.property, ((Double) this.value).doubleValue());
                    } else if (this.value instanceof Float) {
                        queryBuilder.less(this.property, ((Float) this.value).floatValue());
                    }
                } else if (this.operation == Operation.IS_NULL) {
                    queryBuilder.isNull(this.property);
                } else if (this.operation == Operation.IS_NOT_NULL) {
                    queryBuilder.notNull(this.property);
                } else if (this.operation == Operation.CONTAINS) {
                    queryBuilder.contains(this.property, (String) this.value, stringOrder);
                } else if (this.operation == Operation.STARTS_WITH) {
                    queryBuilder.startsWith(this.property, (String) this.value, stringOrder);
                } else if (this.operation == Operation.ENDS_WITH) {
                    queryBuilder.endsWith(this.property, (String) this.value, stringOrder);
                } else {
                    throw new UnsupportedOperationException("This operation is not known.");
                }
            }
        }

        private static Object checkValueForType(Property property, @Nullable Object obj) {
            if (obj != null && obj.getClass().isArray()) {
                throw new DbException("Illegal value: found array, but simple object required");
            }
            if (property.type == Date.class) {
                if (obj instanceof Date) {
                    return Long.valueOf(((Date) obj).getTime());
                }
                if (obj instanceof Long) {
                    return obj;
                }
                throw new DbException("Illegal date value: expected java.util.Date or Long for value " + obj);
            }
            if (property.type == Boolean.TYPE || property.type == Boolean.class) {
                if (obj instanceof Boolean) {
                    return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
                }
                if (obj instanceof Number) {
                    int intValue = ((Number) obj).intValue();
                    if (intValue != 0 && intValue != 1) {
                        throw new DbException("Illegal boolean value: numbers must be 0 or 1, but was " + obj);
                    }
                } else if (obj instanceof String) {
                    String str = (String) obj;
                    if ("TRUE".equalsIgnoreCase(str)) {
                        return 1;
                    }
                    if ("FALSE".equalsIgnoreCase(str)) {
                        return 0;
                    }
                    throw new DbException("Illegal boolean value: Strings must be \"TRUE\" or \"FALSE\" (case insensitive), but was " + obj);
                }
            }
            return obj;
        }

        private static Object[] checkValuesForType(Property property, Operation operation, @Nullable Object[] objArr) {
            if (objArr == null) {
                if (operation == Operation.IS_NULL || operation == Operation.IS_NOT_NULL) {
                    return null;
                }
                throw new IllegalArgumentException("This operation requires non-null values.");
            }
            for (int i = 0; i < objArr.length; i++) {
                objArr[i] = checkValueForType(property, objArr[i]);
            }
            return objArr;
        }

        @Override // io.objectbox.query.QueryCondition
        public void setParameterFor(Query query, Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("The new parameter can not be null.");
            }
            if (this.operation == Operation.BETWEEN) {
                throw new UnsupportedOperationException("The BETWEEN condition requires two parameters.");
            }
            if (this.operation == Operation.IN) {
                throw new UnsupportedOperationException("The IN condition does not support changing parameters.");
            }
            if (obj instanceof Long) {
                query.setParameter(this.property, ((Long) obj).longValue());
            } else if (obj instanceof Integer) {
                query.setParameter(this.property, ((Integer) obj).intValue());
            } else if (obj instanceof String) {
                query.setParameter(this.property, (String) obj);
            } else if (obj instanceof Double) {
                query.setParameter(this.property, ((Double) obj).doubleValue());
            } else if (obj instanceof Float) {
                query.setParameter(this.property, ((Float) obj).floatValue());
            } else {
                throw new IllegalArgumentException("Only LONG, INTEGER, DOUBLE, FLOAT or STRING parameters are supported.");
            }
        }

        @Override // io.objectbox.query.QueryCondition
        public void setParameterFor(Query query, Object obj, Object obj2) {
            if (obj == null || obj2 == null) {
                throw new IllegalArgumentException("The new parameters can not be null.");
            }
            if (this.operation != Operation.BETWEEN) {
                throw new UnsupportedOperationException("Only the BETWEEN condition supports two parameters.");
            }
            if ((obj instanceof Long) && (obj2 instanceof Long)) {
                query.setParameters(this.property, ((Long) obj).longValue(), ((Long) obj2).longValue());
            } else if ((obj instanceof Integer) && (obj2 instanceof Integer)) {
                query.setParameters(this.property, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            } else if ((obj instanceof Double) && (obj2 instanceof Double)) {
                query.setParameters(this.property, ((Double) obj).doubleValue(), ((Double) obj2).doubleValue());
            } else if ((obj instanceof Float) && (obj2 instanceof Float)) {
                query.setParameters(this.property, ((Float) obj).floatValue(), ((Float) obj2).floatValue());
            } else {
                throw new IllegalArgumentException("The BETWEEN condition only supports LONG, INTEGER, DOUBLE or FLOAT parameters.");
            }
        }
    }
}
