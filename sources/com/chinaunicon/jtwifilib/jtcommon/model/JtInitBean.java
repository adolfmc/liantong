package com.chinaunicon.jtwifilib.jtcommon.model;

import com.chinaunicon.jtwifilib.core.global.JtApp;
import java.io.Serializable;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtInitBean implements Serializable {
    private String Location_X;
    private String Location_Y;
    private String appKey;
    private String city;
    private String cityName;
    private Map<String, String> extend;
    private String loid;
    private String password;
    private String province;
    private String provinceName;
    private Map<String, String> theme;
    private String user;
    private String uuid;
    private String version;
    private String workordercode;

    public String getVersion() {
        return this.version;
    }

    public void setVersion() {
        this.version = "1.1.2";
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }

    public void setAppKey() {
        this.appKey = JtApp.getInstance().getAppKey();
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getWorkordercode() {
        return this.workordercode;
    }

    public void setWorkordercode(String str) {
        this.workordercode = str;
    }

    public String getLocation_X() {
        return this.Location_X;
    }

    public void setLocation_X(String str) {
        this.Location_X = str;
    }

    public String getLocation_Y() {
        return this.Location_Y;
    }

    public void setLocation_Y(String str) {
        this.Location_Y = str;
    }

    public String getLoid() {
        return this.loid;
    }

    public void setLoid(String str) {
        this.loid = str;
    }

    public String getProvinceName() {
        return this.provinceName;
    }

    public void setProvinceName(String str) {
        this.provinceName = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public Map<String, String> getExtend() {
        return this.extend;
    }

    public void setExtend(Map<String, String> map) {
        this.extend = map;
    }

    public Map<String, String> getTheme() {
        return this.theme;
    }

    public void setTheme(Map<String, String> map) {
        this.theme = map;
    }
}
