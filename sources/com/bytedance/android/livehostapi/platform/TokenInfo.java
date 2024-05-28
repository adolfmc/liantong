package com.bytedance.android.livehostapi.platform;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TokenInfo {
    public String accessToken;
    public long expireAt;
    public String name;
    public String openId;

    public TokenInfo(String str, String str2, String str3, long j) {
        this.name = str;
        this.openId = str2;
        this.accessToken = str3;
        this.expireAt = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String str) {
        this.openId = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public long getExpireAt() {
        return this.expireAt;
    }

    public void setExpireAt(long j) {
        this.expireAt = j;
    }
}
