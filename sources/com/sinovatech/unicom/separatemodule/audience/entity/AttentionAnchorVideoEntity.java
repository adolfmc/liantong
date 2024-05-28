package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AttentionAnchorVideoEntity {
    private List<AttentionVideoEntity> Anchors;
    private String nextPageNum;
    private int position;

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public List<AttentionVideoEntity> getAnchors() {
        return this.Anchors;
    }

    public void setAnchors(List<AttentionVideoEntity> list) {
        this.Anchors = list;
    }
}
