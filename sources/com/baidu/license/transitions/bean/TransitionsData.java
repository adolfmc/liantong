package com.baidu.license.transitions.bean;

import com.baidu.license.INotProguard;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitionsData implements INotProguard, Serializable {
    public String big_icon;
    public String file;

    /* renamed from: id */
    public String f5001id;
    public String name;
    public String sign;
    public String small_icon;
    public String transition_id;

    public String getBigicon() {
        return this.big_icon;
    }

    public String getFile() {
        return this.file;
    }

    public String getId() {
        return this.f5001id;
    }

    public String getName() {
        return this.name;
    }

    public String getSign() {
        return this.sign;
    }

    public String getSmallicon() {
        return this.small_icon;
    }

    public String getTransitionid() {
        return this.transition_id;
    }

    public void setBigicon(String str) {
        this.big_icon = str;
    }

    public void setFile(String str) {
        this.file = str;
    }

    public void setId(String str) {
        this.f5001id = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setSmallicon(String str) {
        this.small_icon = str;
    }

    public void setTransitionid(String str) {
        this.transition_id = str;
    }

    public String toString() {
        return "TransitionsData{id='" + this.f5001id + "', transition_id='" + this.transition_id + "', file='" + this.file + "', big_icon='" + this.big_icon + "', small_icon='" + this.small_icon + "', name='" + this.name + "', sign='" + this.sign + "'}";
    }
}
