package com.megvii.idcardquality.bean;

import android.graphics.Bitmap;
import android.graphics.Point;
import com.megvii.idcard.sdk.C5293a;
import java.util.Arrays;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDCardAttr {
    public static Bitmap centerBitmap;
    public static Bitmap nationalEmblemBitmap;
    public Bitmap IDCardBitmap;
    public C5293a.C5300g[] Shadows;
    public float[] angles;
    public float brightness;
    public C5293a.C5294a[] cards;
    public Point[] cornerPoints;
    public C5293a.C5295b[] faculaes;
    public boolean hasShadow;
    public boolean hasSpecularHighlight;
    public Bitmap headBitmap;
    public float inBound;
    public float isIdcard;
    public float lowQuality = 0.0f;
    public Point[] portraitPoints;
    public int shadowCount;
    public IDCardSide side;
    public int specularHightlightCount;
    public IDCardType type;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum IDCardSide {
        IDCARD_SIDE_FRONT,
        IDCARD_SIDE_BACK
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum IDCardType {
        NORMAL,
        MONGOL
    }

    public String toString() {
        return "IDCardAttr{lowQuality=" + this.lowQuality + ", cornerPoints=" + Arrays.toString(this.cornerPoints) + ", portraitPoints=" + Arrays.toString(this.portraitPoints) + ", angles=" + Arrays.toString(this.angles) + ", hasSpecularHighlight=" + this.hasSpecularHighlight + ", side=" + this.side + ", brightness=" + this.brightness + ", inBound=" + this.inBound + ", isIdcard=" + this.isIdcard + ", shadowCount=" + this.shadowCount + ", specularHightlightCount=" + this.specularHightlightCount + '}';
    }
}
