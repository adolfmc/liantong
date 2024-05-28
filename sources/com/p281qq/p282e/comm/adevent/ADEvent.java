package com.p281qq.p282e.comm.adevent;

import com.p281qq.p282e.comm.util.GDTLogger;

/* renamed from: com.qq.e.comm.adevent.ADEvent */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ADEvent {

    /* renamed from: a */
    private final int f17897a;

    /* renamed from: b */
    private final Object[] f17898b;

    public ADEvent(int i, Object... objArr) {
        this.f17897a = i;
        this.f17898b = objArr;
        if (i < 100) {
            m8281a("EventId 错误" + i);
        }
    }

    /* renamed from: a */
    private void m8281a(String str) {
        GDTLogger.m8234e(str);
    }

    public <T> T getParam(int i, Class<T> cls) {
        Object[] objArr;
        if (cls == null || (objArr = this.f17898b) == null || objArr.length <= i) {
            return null;
        }
        T t = (T) objArr[i];
        if (t == null) {
            GDTLogger.m8234e("ADEvent 参数为空,type:" + this.f17897a);
            return null;
        } else if (cls.isInstance(objArr[i])) {
            return t;
        } else {
            GDTLogger.m8234e("ADEvent" + this.f17897a + " 参数类型错误,期望类型" + cls.getName() + "实际类型 " + t.getClass().getName());
            return null;
        }
    }

    public <T> T getParam(Class<T> cls) {
        return (T) getParam(0, cls);
    }

    public int getType() {
        return this.f17897a;
    }
}
