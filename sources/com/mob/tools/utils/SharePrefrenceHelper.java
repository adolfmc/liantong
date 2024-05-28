package com.mob.tools.utils;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.mob.commons.C5873u;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.MobPersistence;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SharePrefrenceHelper implements PublicMemberKeeper {

    /* renamed from: a */
    private Context f15276a;

    /* renamed from: b */
    private volatile MobPersistence f15277b;

    public SharePrefrenceHelper(Context context) {
        if (context != null) {
            this.f15276a = context.getApplicationContext();
        }
    }

    public void open(String str) {
        open(str, 0);
    }

    public void open(String str, int i) {
        String str2 = str + "_" + i;
        this.f15277b = new MobPersistence(this.f15276a, str2);
        m11113a(str2);
    }

    public static boolean isMobSpFileExist(Context context, String str, int i) {
        return C6196a.m11103a(context, str + "_" + i);
    }

    public static boolean isMpfFileExist(Context context, String str, int i) {
        return MobPersistence.m11261a(context, str + "_" + i);
    }

    public void putString(String str, String str2) {
        putString(str, str2, 0L);
    }

    public void putString(String str, String str2, long j) {
        if (this.f15277b != null) {
            try {
                this.f15277b.m11259a(new MobPersistence.C6175e(str, str2, j));
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        try {
            return getStringThrowable(str, str2);
        } catch (MobPersistence.NoValidDataException unused) {
            return str2;
        }
    }

    public String getStringThrowable(String str) throws MobPersistence.NoValidDataException {
        return getStringThrowable(str, "");
    }

    public String getStringThrowable(String str, String str2) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                String str3 = (String) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b(str));
                return TextUtils.isEmpty(str3) ? str2 : str3;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return str2;
    }

    public void putBoolean(String str, Boolean bool) {
        putBoolean(str, bool, 0L);
    }

    public void putBoolean(String str, Boolean bool, long j) {
        if (this.f15277b == null || bool == null) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, Byte.valueOf((byte) (bool.booleanValue() ? 1 : 0)), j));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        try {
            return getBooleanThrowable(str, z);
        } catch (MobPersistence.NoValidDataException unused) {
            return z;
        }
    }

    public boolean getBooleanThrowable(String str) throws MobPersistence.NoValidDataException {
        return getBooleanThrowable(str, false);
    }

    public boolean getBooleanThrowable(String str, boolean z) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Object m11260a = this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b(str));
                if (m11260a != null) {
                    if (m11260a instanceof Boolean) {
                        return ((Boolean) m11260a).booleanValue();
                    }
                    return ((Number) m11260a).byteValue() == 1;
                }
                return z;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return z;
    }

    public void putLong(String str, Long l) {
        putLong(str, l, 0L);
    }

    public void putLong(String str, Long l, long j) {
        if (this.f15277b == null || l == null) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, l, j));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public long getLong(String str) {
        return getLong(str, 0L);
    }

    public long getLong(String str, long j) {
        try {
            return getLongThrowable(str, j);
        } catch (MobPersistence.NoValidDataException unused) {
            return j;
        }
    }

    public long getLongThrowable(String str) throws MobPersistence.NoValidDataException {
        return getLongThrowable(str, 0L);
    }

    public long getLongThrowable(String str, long j) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Long l = (Long) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b(str));
                return l == null ? j : l.longValue();
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return j;
    }

    public void putInt(String str, Integer num) {
        putInt(str, num, 0L);
    }

    public void putInt(String str, Integer num, long j) {
        if (this.f15277b == null || num == null) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, num, j));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public int getInt(String str) {
        return getInt(str, 0);
    }

    public int getInt(String str, int i) {
        try {
            return getIntThrowable(str, i);
        } catch (MobPersistence.NoValidDataException unused) {
            return i;
        }
    }

    public int getIntThrowable(String str) throws MobPersistence.NoValidDataException {
        return getIntThrowable(str, 0);
    }

    public int getIntThrowable(String str, int i) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Integer num = (Integer) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b(str));
                return num == null ? i : num.intValue();
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return i;
    }

    public void putDouble(String str, Double d) {
        putDouble(str, d, 0L);
    }

    public void putDouble(String str, Double d, long j) {
        if (this.f15277b == null || d == null) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, d, j));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public double getDouble(String str) {
        return getDouble(str, 0.0d);
    }

    public double getDouble(String str, double d) {
        try {
            return getDoubleThrowable(str, d);
        } catch (MobPersistence.NoValidDataException unused) {
            return d;
        }
    }

    public double getDoubleThrowable(String str) throws MobPersistence.NoValidDataException {
        return getDoubleThrowable(str, 0.0d);
    }

    public double getDoubleThrowable(String str, double d) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Double d2 = (Double) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b(str));
                return d2 == null ? d : d2.doubleValue();
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return d;
    }

    public void putParcel(String str, Parcelable parcelable) {
        putParcel(str, parcelable, 0L);
    }

    public void putParcel(String str, Parcelable parcelable, long j) {
        if (this.f15277b != null) {
            try {
                this.f15277b.m11259a(new MobPersistence.C6175e(str, parcelable, j) { // from class: com.mob.tools.utils.SharePrefrenceHelper.1
                    @Override // com.mob.tools.utils.MobPersistence.C6175e
                    /* renamed from: c */
                    public Object mo11108c() {
                        Object b = m11166b();
                        if (b != null) {
                            return new MobPersistence.SerializableParcel((Parcelable) b);
                        }
                        return null;
                    }
                });
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    public <T extends Parcelable> T getParcel(String str, Class<T> cls) {
        return (T) getParcel(str, cls, null);
    }

    public <T extends Parcelable> T getParcel(String str, Class<T> cls, T t) {
        try {
            return (T) getParcelThrowable(str, cls, t);
        } catch (MobPersistence.NoValidDataException unused) {
            return t;
        }
    }

    public <T extends Parcelable> T getParcelThrowable(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return (T) getParcelThrowable(str, cls, null);
    }

    public <T> T getParcelThrowable(String str, Class<T> cls, final T t) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                T t2 = (T) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b<T>(str) { // from class: com.mob.tools.utils.SharePrefrenceHelper.2
                    @Override // com.mob.tools.utils.MobPersistence.C6172b
                    /* renamed from: a */
                    public T mo11106a(Object obj) {
                        if (obj != null) {
                            return (T) ((MobPersistence.SerializableParcel) obj).getParcel((Parcelable) t);
                        }
                        return (T) t;
                    }
                });
                return t2 != null ? t2 : t;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return t;
    }

    public <T extends Parcelable> void putParcelMap(String str, Map<String, T> map) {
        putParcelMap(str, map, 0L);
    }

    public <T extends Parcelable> void putParcelMap(String str, Map<String, T> map, long j) {
        if (this.f15277b == null || map == null || map.isEmpty()) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, map, j) { // from class: com.mob.tools.utils.SharePrefrenceHelper.3
                @Override // com.mob.tools.utils.MobPersistence.C6175e
                /* renamed from: c */
                public Object mo11108c() {
                    Map hashMap;
                    Object b = m11166b();
                    if (b != null) {
                        if (b instanceof HashMap) {
                            hashMap = new HashMap();
                        } else if (b instanceof Hashtable) {
                            hashMap = new Hashtable();
                        } else if (b instanceof TreeMap) {
                            hashMap = new TreeMap();
                        } else {
                            hashMap = new HashMap();
                        }
                        for (Map.Entry entry : ((Map) b).entrySet()) {
                            hashMap.put(entry.getKey(), new MobPersistence.SerializableParcel((Parcelable) entry.getValue()));
                        }
                        return hashMap;
                    }
                    return null;
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public <T extends Parcelable> Map<String, T> getParcelMap(String str, Class<T> cls) {
        return getParcelMap(str, cls, null);
    }

    public <T extends Parcelable> Map<String, T> getParcelMap(String str, Class<T> cls, Map<String, T> map) {
        try {
            return getParcelMapThrowable(str, cls, map);
        } catch (MobPersistence.NoValidDataException unused) {
            return map;
        }
    }

    public <T extends Parcelable> Map<String, T> getParcelMapThrowable(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return getParcelMapThrowable(str, cls, null);
    }

    public <T extends Parcelable> Map<String, T> getParcelMapThrowable(String str, Class<T> cls, final Map<String, T> map) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Map<String, T> map2 = (Map) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b<Map<String, T>>(str) { // from class: com.mob.tools.utils.SharePrefrenceHelper.4
                    @Override // com.mob.tools.utils.MobPersistence.C6172b
                    /* renamed from: b */
                    public Map<String, T> mo11106a(Object obj) {
                        Map map3;
                        if (obj != null) {
                            Map map4 = (Map) obj;
                            if (map4 instanceof HashMap) {
                                map3 = (Map<String, T>) new HashMap();
                            } else if (map4 instanceof Hashtable) {
                                map3 = (Map<String, T>) new Hashtable();
                            } else if (map4 instanceof TreeMap) {
                                map3 = (Map<String, T>) new TreeMap();
                            } else {
                                map3 = (Map<String, T>) new HashMap();
                            }
                            for (Map.Entry entry : map4.entrySet()) {
                                map3.put(entry.getKey(), ((MobPersistence.SerializableParcel) entry.getValue()).getParcel(null));
                            }
                            return (Map<String, T>) map3;
                        }
                        return map;
                    }
                });
                return map2 != null ? map2 : map;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return map;
    }

    public <T extends Parcelable> void putParcelList(String str, List<T> list) {
        putParcelList(str, list, 0L);
    }

    public <T extends Parcelable> void putParcelList(String str, List<T> list, long j) {
        if (this.f15277b == null || list == null || list.isEmpty()) {
            return;
        }
        this.f15277b.m11259a(new MobPersistence.C6175e(str, list, j) { // from class: com.mob.tools.utils.SharePrefrenceHelper.5
            @Override // com.mob.tools.utils.MobPersistence.C6175e
            /* renamed from: c */
            public Object mo11108c() {
                List arrayList;
                Object b = m11166b();
                if (b != null) {
                    if (b instanceof ArrayList) {
                        arrayList = new ArrayList();
                    } else if (b instanceof LinkedList) {
                        arrayList = new LinkedList();
                    } else {
                        arrayList = new ArrayList();
                    }
                    for (Parcelable parcelable : (List) b) {
                        arrayList.add(new MobPersistence.SerializableParcel(parcelable));
                    }
                    return arrayList;
                }
                return null;
            }
        });
    }

    public <T extends Parcelable> List<T> getParcelList(String str, Class<T> cls) {
        return getParcelList(str, cls, null);
    }

    public <T extends Parcelable> List<T> getParcelList(String str, Class<T> cls, List<T> list) {
        try {
            return getParcelListThrowable(str, cls, list);
        } catch (MobPersistence.NoValidDataException unused) {
            return list;
        }
    }

    public <T extends Parcelable> List<T> getParcelListThrowable(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return getParcelListThrowable(str, cls, null);
    }

    public <T extends Parcelable> List<T> getParcelListThrowable(String str, Class<T> cls, final List<T> list) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                List<T> list2 = (List) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b<List<T>>(str) { // from class: com.mob.tools.utils.SharePrefrenceHelper.6
                    @Override // com.mob.tools.utils.MobPersistence.C6172b
                    /* renamed from: b */
                    public List<T> mo11106a(Object obj) {
                        ArrayList arrayList;
                        if (obj != null) {
                            List<MobPersistence.SerializableParcel> list3 = (List) obj;
                            if (list3 instanceof ArrayList) {
                                arrayList = new ArrayList();
                            } else if (list3 instanceof LinkedList) {
                                arrayList = new LinkedList();
                            } else {
                                arrayList = new ArrayList();
                            }
                            for (MobPersistence.SerializableParcel serializableParcel : list3) {
                                arrayList.add(serializableParcel.getParcel(null));
                            }
                            return arrayList;
                        }
                        return list;
                    }
                });
                return list2 != null ? list2 : list;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return list;
    }

    public <T extends Parcelable> void putParcelArray(String str, T[] tArr) {
        putParcelArray(str, tArr, 0L);
    }

    public <T extends Parcelable> void putParcelArray(String str, T[] tArr, long j) {
        if (this.f15277b == null || tArr == null || tArr.length <= 0) {
            return;
        }
        try {
            this.f15277b.m11259a(new MobPersistence.C6175e(str, tArr, j) { // from class: com.mob.tools.utils.SharePrefrenceHelper.7
                @Override // com.mob.tools.utils.MobPersistence.C6175e
                /* renamed from: c */
                public Object mo11108c() {
                    Object b = m11166b();
                    if (b != null) {
                        Parcelable[] parcelableArr = (Parcelable[]) b;
                        MobPersistence.SerializableParcel[] serializableParcelArr = new MobPersistence.SerializableParcel[parcelableArr.length];
                        for (int i = 0; i < serializableParcelArr.length; i++) {
                            serializableParcelArr[i] = new MobPersistence.SerializableParcel(parcelableArr[i]);
                        }
                        return serializableParcelArr;
                    }
                    return null;
                }
            });
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    public <T extends Parcelable> T[] getParcelArray(String str, Class<T> cls) {
        return (T[]) getParcelArray(str, cls, null);
    }

    public <T extends Parcelable> T[] getParcelArray(String str, Class<T> cls, T[] tArr) {
        try {
            return (T[]) getParcelArrayThrowable(str, cls, tArr);
        } catch (MobPersistence.NoValidDataException unused) {
            return tArr;
        }
    }

    public <T extends Parcelable> T[] getParcelArrayThrowable(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return (T[]) getParcelArrayThrowable(str, cls, null);
    }

    public <T extends Parcelable> T[] getParcelArrayThrowable(String str, final Class<T> cls, final T[] tArr) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                T[] tArr2 = (T[]) ((Parcelable[]) this.f15277b.m11260a((MobPersistence.C6172b<Object>) new MobPersistence.C6172b<T[]>(str) { // from class: com.mob.tools.utils.SharePrefrenceHelper.8
                    /* JADX WARN: Incorrect return type in method signature: (Ljava/lang/Object;)[TT; */
                    @Override // com.mob.tools.utils.MobPersistence.C6172b
                    /* renamed from: b */
                    public Parcelable[] mo11106a(Object obj) {
                        if (obj != null) {
                            MobPersistence.SerializableParcel[] serializableParcelArr = (MobPersistence.SerializableParcel[]) obj;
                            Parcelable[] parcelableArr = (Parcelable[]) Array.newInstance(cls, serializableParcelArr.length);
                            for (int i = 0; i < parcelableArr.length; i++) {
                                parcelableArr[i] = serializableParcelArr[i].getParcel(null);
                            }
                            return parcelableArr;
                        }
                        return tArr;
                    }
                }));
                return tArr2 != null ? tArr2 : tArr;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return tArr;
    }

    @Deprecated
    public void putObj(String str, Object obj) {
        if (obj == null && this.f15277b != null) {
            remove(str);
        } else if (this.f15277b != null) {
            put(str, obj);
        }
    }

    @Deprecated
    public Object getObj(String str, Object obj) {
        return get(str, obj);
    }

    public void put(String str, Object obj) {
        put(str, obj, 0L);
    }

    public void put(String str, Object obj, long j) {
        if (this.f15277b != null) {
            try {
                this.f15277b.m11259a(new MobPersistence.C6175e(str, obj, j));
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    @Deprecated
    public HashMap<String, Object> getAll() {
        if (this.f15277b != null) {
            try {
                return this.f15277b.m11263a();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return new HashMap<>();
    }

    public Object get(String str) {
        return get(str, null);
    }

    public Object get(String str, Object obj) {
        try {
            return getThrowable(str, obj);
        } catch (MobPersistence.NoValidDataException unused) {
            return obj;
        }
    }

    public Object getThrowable(String str) throws MobPersistence.NoValidDataException {
        return getThrowable(str, null);
    }

    public Object getThrowable(String str, final Object obj) throws MobPersistence.NoValidDataException {
        if (this.f15277b != null) {
            try {
                Object m11260a = this.f15277b.m11260a(new MobPersistence.C6172b<Object>(str) { // from class: com.mob.tools.utils.SharePrefrenceHelper.9
                    @Override // com.mob.tools.utils.MobPersistence.C6172b
                    /* renamed from: a */
                    public Object mo11106a(Object obj2) {
                        if (obj2 != null) {
                            if ((obj2 instanceof String) && SharePrefrenceHelper.this.m11111b((String) obj2)) {
                                try {
                                    return SharePrefrenceHelper.this.m11112a(Base64.decode((String) obj2, 2));
                                } catch (Throwable th) {
                                    NLog mobLog = MobLog.getInstance();
                                    mobLog.m11342d("Expected exc: " + th.getMessage(), new Object[0]);
                                    return obj2;
                                }
                            }
                            return obj2;
                        }
                        return obj;
                    }
                });
                return m11260a != null ? ((m11260a instanceof String) && m11111b((String) m11260a)) ? m11112a(Base64.decode((String) m11260a, 2)) : m11260a : obj;
            } catch (MobPersistence.NoValidDataException e) {
                throw e;
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
        return obj;
    }

    @Deprecated
    public void putAll(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void remove(String str) {
        if (this.f15277b != null) {
            try {
                this.f15277b.m11245a(str);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    public void clear() {
        if (this.f15277b != null) {
            try {
                this.f15277b.m11237b();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public Object m11112a(byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        ObjectInputStream objectInputStream2 = null;
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
        }
        try {
            Object readObject = objectInputStream.readObject();
            C5873u.m12179a(objectInputStream, byteArrayInputStream);
            return readObject;
        } catch (Throwable th3) {
            objectInputStream2 = objectInputStream;
            th = th3;
            C5873u.m12179a(objectInputStream2, byteArrayInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    private void m11113a(String str) {
        HashMap<String, Object> hashMap;
        if (getBoolean("k_m_sp_cpt_dn") || !C6196a.m11103a(this.f15276a, str)) {
            return;
        }
        MobLog.getInstance().m11342d("[MPF][" + str + "]Compat acquire", new Object[0]);
        C6196a c6196a = new C6196a(this.f15276a, str);
        if (this.f15277b != null) {
            hashMap = c6196a.m11105a();
            if (hashMap != null && !hashMap.isEmpty()) {
                putAll(hashMap);
            }
            putBoolean("k_m_sp_cpt_dn", true);
        } else {
            hashMap = null;
        }
        NLog mobLog = MobLog.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("[MPF][");
        sb.append(str);
        sb.append("]Compat done, mv: ");
        sb.append(hashMap != null ? Integer.valueOf(hashMap.size()) : null);
        mobLog.m11342d(sb.toString(), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m11111b(String str) {
        try {
            return Pattern.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$", str);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.utils.SharePrefrenceHelper$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class C6196a {

        /* renamed from: c */
        private static File f15293c;

        /* renamed from: a */
        private File f15294a;

        /* renamed from: b */
        private HashMap<String, Object> f15295b = new HashMap<>();

        public C6196a(Context context, String str) {
            if (context != null) {
                try {
                    this.f15294a = new File(m11104a(context), str);
                    if (!this.f15294a.getParentFile().exists()) {
                        this.f15294a.getParentFile().mkdirs();
                    }
                    if (!this.f15294a.exists()) {
                        this.f15294a.createNewFile();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                    return;
                }
            }
            m11102b();
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
            jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
            	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
            	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
            	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
            */
        /* renamed from: b */
        private void m11102b() {
            /*
                r12 = this;
                java.util.HashMap<java.lang.String, java.lang.Object> r0 = r12.f15295b
                monitor-enter(r0)
                java.io.File r1 = r12.f15294a     // Catch: java.lang.Throwable -> L9c
                if (r1 == 0) goto L9a
                java.io.File r1 = r12.f15294a     // Catch: java.lang.Throwable -> L9c
                boolean r1 = r1.exists()     // Catch: java.lang.Throwable -> L9c
                if (r1 == 0) goto L9a
                r1 = 2
                r2 = 1
                r3 = 0
                r4 = 3
                r5 = 0
                java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L78
                java.io.File r7 = r12.f15294a     // Catch: java.lang.Throwable -> L78
                r6.<init>(r7)     // Catch: java.lang.Throwable -> L78
                java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6d
                java.lang.String r8 = "utf-8"
                r7.<init>(r6, r8)     // Catch: java.lang.Throwable -> L6d
                java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L63
                r8.<init>(r7)     // Catch: java.lang.Throwable -> L63
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5c
                r5.<init>()     // Catch: java.lang.Throwable -> L5c
                java.lang.String r9 = r8.readLine()     // Catch: java.lang.Throwable -> L5c
            L31:
                if (r9 == 0) goto L46
                int r10 = r5.length()     // Catch: java.lang.Throwable -> L5c
                if (r10 <= 0) goto L3e
                java.lang.String r10 = "\n"
                r5.append(r10)     // Catch: java.lang.Throwable -> L5c
            L3e:
                r5.append(r9)     // Catch: java.lang.Throwable -> L5c
                java.lang.String r9 = r8.readLine()     // Catch: java.lang.Throwable -> L5c
                goto L31
            L46:
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L5c
                java.util.HashMap r5 = com.mob.tools.utils.HashonHelper.fromJson(r5)     // Catch: java.lang.Throwable -> L5c
                r12.f15295b = r5     // Catch: java.lang.Throwable -> L5c
                java.io.Closeable[] r4 = new java.io.Closeable[r4]     // Catch: java.lang.Throwable -> L9c
                r4[r3] = r8     // Catch: java.lang.Throwable -> L9c
                r4[r2] = r7     // Catch: java.lang.Throwable -> L9c
                r4[r1] = r6     // Catch: java.lang.Throwable -> L9c
            L58:
                com.mob.commons.C5873u.m12179a(r4)     // Catch: java.lang.Throwable -> L9c
                goto L9a
            L5c:
                r5 = move-exception
                goto L7d
            L5e:
                r8 = move-exception
                r11 = r8
                r8 = r5
                r5 = r11
                goto L8e
            L63:
                r8 = move-exception
                r11 = r8
                r8 = r5
                r5 = r11
                goto L7d
            L68:
                r7 = move-exception
                r8 = r5
                r5 = r7
                r7 = r8
                goto L8e
            L6d:
                r7 = move-exception
                r8 = r5
                r5 = r7
                r7 = r8
                goto L7d
            L72:
                r6 = move-exception
                r7 = r5
                r8 = r7
                r5 = r6
                r6 = r8
                goto L8e
            L78:
                r6 = move-exception
                r7 = r5
                r8 = r7
                r5 = r6
                r6 = r8
            L7d:
                com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L8d
                r9.m11325w(r5)     // Catch: java.lang.Throwable -> L8d
                java.io.Closeable[] r4 = new java.io.Closeable[r4]     // Catch: java.lang.Throwable -> L9c
                r4[r3] = r8     // Catch: java.lang.Throwable -> L9c
                r4[r2] = r7     // Catch: java.lang.Throwable -> L9c
                r4[r1] = r6     // Catch: java.lang.Throwable -> L9c
                goto L58
            L8d:
                r5 = move-exception
            L8e:
                java.io.Closeable[] r4 = new java.io.Closeable[r4]     // Catch: java.lang.Throwable -> L9c
                r4[r3] = r8     // Catch: java.lang.Throwable -> L9c
                r4[r2] = r7     // Catch: java.lang.Throwable -> L9c
                r4[r1] = r6     // Catch: java.lang.Throwable -> L9c
                com.mob.commons.C5873u.m12179a(r4)     // Catch: java.lang.Throwable -> L9c
                throw r5     // Catch: java.lang.Throwable -> L9c
            L9a:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L9c
                return
            L9c:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L9c
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.SharePrefrenceHelper.C6196a.m11102b():void");
        }

        /* renamed from: a */
        private static synchronized File m11104a(Context context) {
            File file;
            synchronized (C6196a.class) {
                if (f15293c == null) {
                    f15293c = new File(context.getFilesDir(), C5731l.m12674a("003Ridfegf"));
                }
                file = f15293c;
            }
            return file;
        }

        /* renamed from: a */
        public static synchronized boolean m11103a(Context context, String str) {
            synchronized (C6196a.class) {
                return new File(m11104a(context), str).exists();
            }
        }

        /* renamed from: a */
        public HashMap<String, Object> m11105a() {
            HashMap<String, Object> hashMap;
            synchronized (this.f15295b) {
                hashMap = new HashMap<>();
                hashMap.putAll(this.f15295b);
            }
            return hashMap;
        }
    }
}
