package com.sinovatech.unicom.separatemodule.login.dongtaimiyao;

import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginParamsEntity {
    private Map<String, String> bodyMap;
    private Map<String, String> headerMap;

    public Map<String, String> getHeaderMap() {
        if (this.headerMap == null) {
            this.headerMap = new HashMap();
        }
        return this.headerMap;
    }

    public void setHeaderMap(Map<String, String> map) {
        this.headerMap = map;
    }

    public Map<String, String> getBodyMap() {
        if (this.bodyMap == null) {
            this.bodyMap = new HashMap();
        }
        return this.bodyMap;
    }

    public void setBodyMap(Map<String, String> map) {
        this.bodyMap = map;
    }
}
