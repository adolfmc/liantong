package com.sinovatech.unicom.basic.p315ui.fuwu.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.entity.MarketingBitsListEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MarketingBitsListEntity {
    private List<MarketingBitsEntity> bottomList;
    private boolean hiddenBottom;
    private boolean hiddenTop;
    private String respCode;
    private String respTime;
    private List<MarketingBitsEntity> topList;
    private String transId;

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    public String getRespTime() {
        return this.respTime;
    }

    public void setRespTime(String str) {
        this.respTime = str;
    }

    public boolean isHiddenTop() {
        return this.hiddenTop;
    }

    public void setHiddenTop(boolean z) {
        this.hiddenTop = z;
    }

    public boolean isHiddenBottom() {
        return this.hiddenBottom;
    }

    public void setHiddenBottom(boolean z) {
        this.hiddenBottom = z;
    }

    public List<MarketingBitsEntity> getTopList() {
        return this.topList;
    }

    public void setTopList(List<MarketingBitsEntity> list) {
        this.topList = list;
    }

    public List<MarketingBitsEntity> getBottomList() {
        return this.bottomList;
    }

    public void setBottomList(List<MarketingBitsEntity> list) {
        this.bottomList = list;
    }
}
