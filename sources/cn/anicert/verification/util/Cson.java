package cn.anicert.verification.util;

import com.tfd.sdk.LF8bOvWP4;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Cson {
    private Class clazz;
    private String json;
    private Object object;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(55);
    }

    public Cson() {
    }

    public Cson(Object obj) {
        if (obj != null) {
            this.object = obj;
        }
    }

    private native String array2Json(Object[] objArr);

    private native String basic2Json(Object obj);

    private native Object[] getArray(Object obj);

    private native Byte[] getByteArray(Object obj);

    private native Character[] getCharacterArray(Object obj);

    private native Double[] getDoubleArray(Object obj);

    private native Float[] getFloatArray(Object obj);

    private native Integer[] getIntegerArray(Object obj);

    private native Long[] getLongArray(Object obj);

    private native boolean isArray(Class cls);

    private native boolean isByte(Object obj);

    private native boolean isCharacter(Object obj);

    private native boolean isCustomObject(Object obj);

    private native boolean isDouble(Object obj);

    private native boolean isInteger(Object obj);

    private native boolean isLFloat(Object obj);

    private native boolean isList(Object obj);

    private native boolean isLong(Object obj);

    private native boolean isMap(Object obj);

    private native boolean isString(Object obj);

    private native String list2Object(List<Object> list);

    private native String map2Object(Map<Object, Object> map);

    private native String obj2Json(Object obj);

    public native boolean isJsonArray();

    public native boolean isJsonArray(Object obj);

    public native boolean isJsonObject();

    public native boolean isJsonObject(Object obj);

    public native String toJsonString() throws UnHandleJsonException;

    public native String toJsonString(Object obj) throws UnHandleJsonException;
}
