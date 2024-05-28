package com.mob.tools.utils;

import android.os.Parcelable;
import com.mob.MobSDK;
import com.mob.tools.utils.MobPersistence;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.utils.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6216g {

    /* renamed from: a */
    private static C6216g f15348a;

    /* renamed from: b */
    private SharePrefrenceHelper f15349b;

    private C6216g() {
        if (this.f15349b == null) {
            this.f15349b = new SharePrefrenceHelper(MobSDK.getContext());
            this.f15349b.open("mob_dh", 1);
        }
    }

    /* renamed from: a */
    public static C6216g m11041a() {
        if (f15348a == null) {
            synchronized (C6216g.class) {
                if (f15348a == null) {
                    f15348a = new C6216g();
                }
            }
        }
        return f15348a;
    }

    /* renamed from: a */
    public void m11019a(String str, String str2) {
        this.f15349b.putString(str, str2);
    }

    /* renamed from: a */
    public void m11018a(String str, String str2, long j) {
        this.f15349b.putString(str, str2, j);
    }

    /* renamed from: a */
    public String m11040a(String str) throws MobPersistence.NoValidDataException {
        return this.f15349b.getStringThrowable(str);
    }

    /* renamed from: b */
    public String m11006b(String str, String str2) throws MobPersistence.NoValidDataException {
        return this.f15349b.getStringThrowable(str, str2);
    }

    /* renamed from: a */
    public void m11034a(String str, Boolean bool) {
        this.f15349b.putBoolean(str, bool);
    }

    /* renamed from: a */
    public void m11033a(String str, Boolean bool, long j) {
        this.f15349b.putBoolean(str, bool, j);
    }

    /* renamed from: b */
    public boolean m11010b(String str) throws MobPersistence.NoValidDataException {
        return this.f15349b.getBooleanThrowable(str);
    }

    /* renamed from: a */
    public boolean m11013a(String str, boolean z) throws MobPersistence.NoValidDataException {
        return this.f15349b.getBooleanThrowable(str, z);
    }

    /* renamed from: a */
    public void m11023a(String str, Long l) {
        this.f15349b.putLong(str, l);
    }

    /* renamed from: a */
    public void m11022a(String str, Long l, long j) {
        this.f15349b.putLong(str, l, j);
    }

    /* renamed from: c */
    public long m11005c(String str) {
        return this.f15349b.getLong(str);
    }

    /* renamed from: d */
    public long m11003d(String str) throws MobPersistence.NoValidDataException {
        return this.f15349b.getLongThrowable(str);
    }

    /* renamed from: a */
    public long m11037a(String str, long j) throws MobPersistence.NoValidDataException {
        return this.f15349b.getLongThrowable(str, j);
    }

    /* renamed from: a */
    public void m11025a(String str, Integer num) {
        this.f15349b.putInt(str, num);
    }

    /* renamed from: a */
    public void m11024a(String str, Integer num, long j) {
        this.f15349b.putInt(str, num, j);
    }

    /* renamed from: e */
    public int m11001e(String str) {
        return this.f15349b.getInt(str);
    }

    /* renamed from: a */
    public int m11038a(String str, int i) {
        return this.f15349b.getInt(str, i);
    }

    /* renamed from: b */
    public int m11009b(String str, int i) throws MobPersistence.NoValidDataException {
        return this.f15349b.getIntThrowable(str, i);
    }

    /* renamed from: a */
    public void m11027a(String str, Double d) {
        this.f15349b.putDouble(str, d);
    }

    /* renamed from: a */
    public void m11026a(String str, Double d, long j) {
        this.f15349b.putDouble(str, d, j);
    }

    /* renamed from: f */
    public double m11000f(String str) throws MobPersistence.NoValidDataException {
        return this.f15349b.getDoubleThrowable(str);
    }

    /* renamed from: a */
    public double m11039a(String str, double d) throws MobPersistence.NoValidDataException {
        return this.f15349b.getDoubleThrowable(str, d);
    }

    /* renamed from: a */
    public void m11036a(String str, Parcelable parcelable) {
        this.f15349b.putParcel(str, parcelable);
    }

    /* renamed from: a */
    public void m11035a(String str, Parcelable parcelable, long j) {
        this.f15349b.putParcel(str, parcelable, j);
    }

    /* renamed from: a */
    public <T extends Parcelable> T m11032a(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return (T) this.f15349b.getParcelThrowable(str, cls);
    }

    /* renamed from: a */
    public <T> T m11031a(String str, Class<T> cls, T t) throws MobPersistence.NoValidDataException {
        return (T) this.f15349b.getParcelThrowable(str, cls, t);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11015a(String str, Map<String, T> map) {
        this.f15349b.putParcelMap(str, map);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11014a(String str, Map<String, T> map, long j) {
        this.f15349b.putParcelMap(str, map, j);
    }

    /* renamed from: b */
    public <T extends Parcelable> Map<String, T> m11008b(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return this.f15349b.getParcelMapThrowable(str, cls);
    }

    /* renamed from: a */
    public <T extends Parcelable> Map<String, T> m11029a(String str, Class<T> cls, Map<String, T> map) throws MobPersistence.NoValidDataException {
        return this.f15349b.getParcelMapThrowable(str, cls, map);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11017a(String str, List<T> list) {
        this.f15349b.putParcelList(str, list);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11016a(String str, List<T> list, long j) {
        this.f15349b.putParcelList(str, list, j);
    }

    /* renamed from: c */
    public <T extends Parcelable> List<T> m11004c(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return this.f15349b.getParcelListThrowable(str, cls);
    }

    /* renamed from: a */
    public <T extends Parcelable> List<T> m11030a(String str, Class<T> cls, List<T> list) throws MobPersistence.NoValidDataException {
        return this.f15349b.getParcelListThrowable(str, cls, list);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11012a(String str, T[] tArr) {
        this.f15349b.putParcelArray(str, tArr);
    }

    /* renamed from: a */
    public <T extends Parcelable> void m11011a(String str, T[] tArr, long j) {
        this.f15349b.putParcelArray(str, tArr, j);
    }

    /* renamed from: d */
    public <T extends Parcelable> T[] m11002d(String str, Class<T> cls) throws MobPersistence.NoValidDataException {
        return (T[]) this.f15349b.getParcelArrayThrowable(str, cls);
    }

    /* renamed from: a */
    public <T extends Parcelable> T[] m11028a(String str, Class<T> cls, T[] tArr) throws MobPersistence.NoValidDataException {
        return (T[]) this.f15349b.getParcelArrayThrowable(str, cls, tArr);
    }

    /* renamed from: a */
    public void m11021a(String str, Object obj) {
        this.f15349b.put(str, obj);
    }

    /* renamed from: a */
    public void m11020a(String str, Object obj, long j) {
        this.f15349b.put(str, obj, j);
    }

    /* renamed from: g */
    public Object m10999g(String str) throws MobPersistence.NoValidDataException {
        return this.f15349b.getThrowable(str);
    }

    /* renamed from: b */
    public Object m11007b(String str, Object obj) throws MobPersistence.NoValidDataException {
        return this.f15349b.getThrowable(str, obj);
    }

    /* renamed from: h */
    public void m10998h(String str) {
        this.f15349b.remove(str);
    }
}
