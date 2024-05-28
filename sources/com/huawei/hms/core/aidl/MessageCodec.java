package com.huawei.hms.core.aidl;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.huawei.hms.core.aidl.annotation.Packed;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MessageCodec {
    protected static final int VAL_ENTITY = 0;
    protected static final int VAL_LIST = 1;
    protected static final int VAL_NULL = -1;
    protected static final String VAL_TYPE = "_val_type_";

    /* renamed from: a */
    private void m15082a(IMessageEntity iMessageEntity, Field field, Bundle bundle) throws IllegalAccessException {
        Object m15079a = m15079a(field, bundle);
        if (m15079a != null) {
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(iMessageEntity, m15079a);
            field.setAccessible(isAccessible);
        }
    }

    /* renamed from: b */
    private void m15078b(IMessageEntity iMessageEntity, Field field, Bundle bundle) throws IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        field.setAccessible(true);
        writeValue(field.getName(), field.get(iMessageEntity), bundle);
        field.setAccessible(isAccessible);
    }

    public IMessageEntity decode(Bundle bundle, IMessageEntity iMessageEntity) {
        Field[] declaredFields;
        if (bundle == null) {
            return iMessageEntity;
        }
        bundle.setClassLoader(getClass().getClassLoader());
        for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(Packed.class)) {
                    try {
                        m15082a(iMessageEntity, field, bundle);
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                        Log.e("MessageCodec", "decode, set value of the field exception, field name:" + field.getName());
                    }
                }
            }
        }
        return iMessageEntity;
    }

    public Bundle encode(IMessageEntity iMessageEntity, Bundle bundle) {
        Field[] declaredFields;
        for (Class<?> cls = iMessageEntity.getClass(); cls != null; cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (field.isAnnotationPresent(Packed.class)) {
                    try {
                        m15078b(iMessageEntity, field, bundle);
                    } catch (IllegalAccessException | IllegalArgumentException unused) {
                        Log.e("MessageCodec", "encode, get value of the field exception, field name: " + field.getName());
                    }
                }
            }
        }
        return bundle;
    }

    protected List<Object> readList(Type type, Bundle bundle) throws InstantiationException, IllegalAccessException {
        ArrayList arrayList = new ArrayList();
        for (Bundle bundle2 = bundle.getBundle("_next_item_"); bundle2 != null; bundle2 = bundle2.getBundle("_next_item_")) {
            Object obj = bundle2.get("_value_");
            if (!obj.getClass().isPrimitive() && !(obj instanceof String) && !(obj instanceof Serializable)) {
                if (obj instanceof Bundle) {
                    Bundle bundle3 = (Bundle) obj;
                    int i = bundle3.getInt(VAL_TYPE, -1);
                    if (i == 1) {
                        throw new InstantiationException("Nested List can not be supported");
                    }
                    if (i == 0) {
                        arrayList.add(decode(bundle3, (IMessageEntity) ((Class) ((ParameterizedType) type).getActualTypeArguments()[0]).newInstance()));
                    } else {
                        throw new InstantiationException("Unknown type can not be supported");
                    }
                } else {
                    continue;
                }
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    protected void writeList(String str, List list, Bundle bundle) {
        Bundle bundle2 = null;
        for (Object obj : list) {
            if (bundle2 == null) {
                bundle2 = new Bundle();
                bundle.putBundle(str, bundle2);
                bundle2.putInt(VAL_TYPE, 1);
            }
            bundle2 = m15081a("_value_", bundle2, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeValue(String str, Object obj, Bundle bundle) {
        if (obj == null || m15080a(str, obj, bundle)) {
            return;
        }
        if (obj instanceof CharSequence) {
            bundle.putCharSequence(str, (CharSequence) obj);
        } else if (obj instanceof Parcelable) {
            bundle.putParcelable(str, (Parcelable) obj);
        } else if (obj instanceof byte[]) {
            bundle.putByteArray(str, (byte[]) obj);
        } else if (obj instanceof List) {
            writeList(str, (List) obj, bundle);
        } else if (obj instanceof Serializable) {
            bundle.putSerializable(str, (Serializable) obj);
        } else if (obj instanceof IMessageEntity) {
            Bundle encode = encode((IMessageEntity) obj, new Bundle());
            encode.putInt(VAL_TYPE, 0);
            bundle.putBundle(str, encode);
        } else {
            Log.e("MessageCodec", "cannot support type, " + str);
        }
    }

    /* renamed from: a */
    private Object m15079a(Field field, Bundle bundle) {
        String name = field.getName();
        Object obj = bundle.get(name);
        if (obj instanceof Bundle) {
            try {
                Bundle bundle2 = (Bundle) obj;
                int i = bundle2.getInt(VAL_TYPE, -1);
                if (i == 1) {
                    return readList(field.getGenericType(), bundle2);
                }
                if (i == 0) {
                    return decode((Bundle) obj, (IMessageEntity) field.getType().newInstance());
                }
            } catch (Exception unused) {
                Log.e("MessageCodec", "decode, read value of the field exception, field name: " + name);
                return null;
            }
        }
        return obj;
    }

    /* renamed from: a */
    private Bundle m15081a(String str, Bundle bundle, Object obj) {
        Bundle bundle2 = new Bundle();
        writeValue(str, obj, bundle2);
        bundle.putBundle("_next_item_", bundle2);
        return bundle2;
    }

    /* renamed from: a */
    private boolean m15080a(String str, Object obj, Bundle bundle) {
        if (obj instanceof String) {
            bundle.putString(str, (String) obj);
            return true;
        } else if (obj instanceof Integer) {
            bundle.putInt(str, ((Integer) obj).intValue());
            return true;
        } else if (obj instanceof Short) {
            bundle.putShort(str, ((Short) obj).shortValue());
            return true;
        } else if (obj instanceof Long) {
            bundle.putLong(str, ((Long) obj).longValue());
            return true;
        } else if (obj instanceof Float) {
            bundle.putFloat(str, ((Float) obj).floatValue());
            return true;
        } else if (obj instanceof Double) {
            bundle.putDouble(str, ((Double) obj).doubleValue());
            return true;
        } else if (obj instanceof Boolean) {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            return true;
        } else {
            return false;
        }
    }
}
