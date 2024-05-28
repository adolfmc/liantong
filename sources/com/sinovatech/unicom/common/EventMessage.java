package com.sinovatech.unicom.common;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EventMessage<T> {
    private int code;
    private T data;

    public EventMessage(int i) {
        this.code = i;
    }

    public EventMessage(int i, T t) {
        this.code = i;
        this.data = t;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String toString() {
        return "EventMessage{code=" + this.code + ", data=" + this.data + '}';
    }
}
