package com.baidu.license.sticker;

import com.google.gson.JsonArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StickerListModel extends BaseModel {
    public StickerListResult result;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StickerListResult extends BaseResult {
        public JsonArray faceInfoList;

        public JsonArray getFaceInfoList() {
            return this.faceInfoList;
        }

        public void setFaceInfoList(JsonArray jsonArray) {
            this.faceInfoList = jsonArray;
        }
    }

    public StickerListResult getResult() {
        return this.result;
    }

    public void setResult(StickerListResult stickerListResult) {
        this.result = stickerListResult;
    }
}
