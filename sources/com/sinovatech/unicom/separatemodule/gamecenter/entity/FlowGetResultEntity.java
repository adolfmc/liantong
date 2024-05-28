package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FlowGetResultEntity {
    private String code;
    private HotGamesEntity.HotGame data;
    private String msg;

    public HotGamesEntity.HotGame getData() {
        return this.data;
    }

    public void setData(HotGamesEntity.HotGame hotGame) {
        this.data = hotGame;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
