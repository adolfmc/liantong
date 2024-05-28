package com.bytedance.android.live.base.api.outer.data;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AnchorInfo {
    String avatar;
    long fansCount;
    String nickname;
    String openId;

    public AnchorInfo(String str, String str2, String str3, long j) {
        this.openId = str;
        this.nickname = str2;
        this.avatar = str3;
        this.fansCount = j;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public long getFansCount() {
        return this.fansCount;
    }

    public void setFansCount(long j) {
        this.fansCount = j;
    }
}
