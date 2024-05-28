package com.baidu.license.sticker;

import com.google.gson.JsonObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StickerOneModel extends BaseModel {
    public StickerOneResult result;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StickerOneResult extends BaseResult {
        public JsonObject faceInfo;

        public JsonObject getFaceInfo() {
            return this.faceInfo;
        }

        public void setFaceInfo(JsonObject jsonObject) {
            this.faceInfo = jsonObject;
        }
    }

    public StickerOneResult getResult() {
        return this.result;
    }

    public void setResult(StickerOneResult stickerOneResult) {
        this.result = stickerOneResult;
    }
}
