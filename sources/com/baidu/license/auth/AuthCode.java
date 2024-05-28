package com.baidu.license.auth;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum AuthCode {
    SHOOT_BASE_SEGMENT_RECORD("1200"),
    SHOOT_BASE_FOLLOW("1300"),
    SHOOT_BASE_TOGETHER("1400"),
    SHOOT_BEAUTY_THIN_FACE("1111"),
    SHOOT_BEAUTY_BIG_EYE("1112"),
    SHOOT_BEAUTY_THIN_BOTY("1113"),
    SHOOT_BEAUTY_HEIGHTEN("1114"),
    SHOOT_BEAUTY_EFFECT("1115"),
    SHOOT_BEAUTY_SENIOR("1116"),
    SHOOT_INTERACT_FACE_STICKER("1121"),
    SHOOT_STICKER_GESTURE("1122"),
    SHOOT_STICKER_BACKGROUND("1123"),
    SHOOT_STICKER_SLAM("1124"),
    EDIT_MULTI_VIDEO_MERGE("2100"),
    SMART_ALBUM_PIC2PHOTO(""),
    SMART_ALBUM_POLYMERIZE(""),
    SMART_ALBUM_VIDEO_COVER(""),
    SMART_ALBUM_MERGE_VIDEO("");
    
    public String authCode;

    AuthCode(String str) {
        this.authCode = str;
    }

    public String getAuthCode() {
        return this.authCode;
    }
}
