package com.sinovatech.unicom.separatemodule.audience.view.heart;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.view.View;
import android.view.ViewGroup;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AbstractPathAnimator {
    protected final Config mConfig;
    private final Random mRandom = new Random();

    public abstract void start(View view, ViewGroup viewGroup);

    public AbstractPathAnimator(Config config) {
        this.mConfig = config;
    }

    public float randomRotation() {
        return (this.mRandom.nextFloat() * 28.6f) - 14.3f;
    }

    public Path createPath(AtomicInteger atomicInteger, View view, int i) {
        Random random = this.mRandom;
        int nextInt = random.nextInt(this.mConfig.xRand);
        int nextInt2 = random.nextInt(this.mConfig.xRand);
        int height = view.getHeight() - this.mConfig.initY;
        int intValue = (atomicInteger.intValue() * 15) + (this.mConfig.animLength * i) + random.nextInt(this.mConfig.animLengthRand);
        int i2 = intValue / this.mConfig.bezierFactor;
        int nextInt3 = this.mRandom.nextInt(this.mConfig.xPointFactor);
        int i3 = nextInt + nextInt3;
        int i4 = nextInt3 + nextInt2;
        int i5 = height - intValue;
        int i6 = height - (intValue / 2);
        Path path = new Path();
        path.moveTo(this.mConfig.initX, height);
        float f = height - i2;
        float f2 = i3;
        float f3 = i6;
        path.cubicTo(this.mConfig.initX, f, f2, i6 + i2, f2, f3);
        path.moveTo(f2, f3);
        float f4 = i4;
        path.cubicTo(f2, i6 - i2, f4, i2 + i5, f4, i5);
        return path;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Config {
        public int animDuration;
        public int animLength;
        public int animLengthRand;
        public int bezierFactor;
        public int heartHeight;
        public int heartWidth;
        public int initX;
        public int initY;
        public int xPointFactor;
        public int xRand;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static Config fromTypeArray(TypedArray typedArray) {
            Config config = new Config();
            Resources resources = typedArray.getResources();
            config.initX = (int) typedArray.getDimension(6, resources.getDimensionPixelOffset(2131165949));
            config.initY = (int) typedArray.getDimension(7, resources.getDimensionPixelOffset(2131165950));
            config.xRand = (int) typedArray.getDimension(9, resources.getDimensionPixelOffset(2131165948));
            config.animLength = (int) typedArray.getDimension(0, resources.getDimensionPixelOffset(2131165951));
            config.animLengthRand = (int) typedArray.getDimension(1, resources.getDimensionPixelOffset(2131165952));
            config.bezierFactor = typedArray.getInteger(3, resources.getInteger(2131361804));
            config.xPointFactor = (int) typedArray.getDimension(8, resources.getDimensionPixelOffset(2131165953));
            config.heartWidth = (int) typedArray.getDimension(5, resources.getDimensionPixelOffset(2131165955));
            config.heartHeight = (int) typedArray.getDimension(4, resources.getDimensionPixelOffset(2131165954));
            config.animDuration = typedArray.getInteger(2, resources.getInteger(2131361797));
            return config;
        }
    }
}
