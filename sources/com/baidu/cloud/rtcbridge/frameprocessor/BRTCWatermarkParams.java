package com.baidu.cloud.rtcbridge.frameprocessor;

import android.graphics.Color;
import android.graphics.Rect;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCWatermarkParams {
    public static final int STRING_MAX_LENGTH = 50;
    public List<BRTCWatermarkParam> watermarkParamList;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum WatermarkType {
        IMAGE,
        STRING,
        TIME
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class BRTCWatermarkParam {
        public Rect rect;
        public String watermarkResource;
        String watermarkTag;
        public WatermarkType watermarkType;
        public float textSize = 13.0f;
        public boolean autoScaleDensity = true;
        public int textColor = Color.rgb(255, 255, 255);
        public float shadowRadius = 1.0f;
        public float shadowDx = 0.0f;
        public float shadowDy = 1.0f;
        public int shadowColor = -12303292;
        int watermarkTexture = -1;

        public BRTCWatermarkParam(WatermarkType watermarkType, Rect rect, String str) {
            this.watermarkType = watermarkType;
            this.rect = rect;
            this.watermarkResource = str;
        }
    }
}
