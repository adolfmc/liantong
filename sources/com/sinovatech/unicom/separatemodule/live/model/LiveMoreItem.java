package com.sinovatech.unicom.separatemodule.live.model;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveMoreItem {
    private List<LiveMoreInfo> bannerList;
    private List<LiveMoreInfo> huifList;
    private List<LiveMoreInfo> liveList;
    private List<LiveMoreInfo> yugList;

    public List<LiveMoreInfo> getBannerList() {
        return this.bannerList;
    }

    public void setBannerList(List<LiveMoreInfo> list) {
        this.bannerList = list;
    }

    public List<LiveMoreInfo> getLiveList() {
        return this.liveList;
    }

    public void setLiveList(List<LiveMoreInfo> list) {
        this.liveList = list;
    }

    public List<LiveMoreInfo> getYugList() {
        return this.yugList;
    }

    public void setYugList(List<LiveMoreInfo> list) {
        this.yugList = list;
    }

    public List<LiveMoreInfo> getHuifList() {
        return this.huifList;
    }

    public void setHuifList(List<LiveMoreInfo> list) {
        this.huifList = list;
    }
}
