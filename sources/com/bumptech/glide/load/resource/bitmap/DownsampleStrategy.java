package com.bumptech.glide.load.resource.bitmap;

import com.bumptech.glide.load.Option;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class DownsampleStrategy {
    public static final DownsampleStrategy FIT_CENTER = new FitCenter();
    public static final DownsampleStrategy CENTER_OUTSIDE = new CenterOutside();
    public static final DownsampleStrategy AT_LEAST = new AtLeast();
    public static final DownsampleStrategy AT_MOST = new AtMost();
    public static final DownsampleStrategy CENTER_INSIDE = new CenterInside();
    public static final DownsampleStrategy NONE = new None();
    public static final DownsampleStrategy DEFAULT = CENTER_OUTSIDE;
    public static final Option<DownsampleStrategy> OPTION = Option.memory("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", DEFAULT);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4);

    public abstract float getScaleFactor(int i, int i2, int i3, int i4);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class FitCenter extends DownsampleStrategy {
        FitCenter() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(i3 / i, i4 / i2);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class CenterOutside extends DownsampleStrategy {
        CenterOutside() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.max(i3 / i, i4 / i2);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class AtLeast extends DownsampleStrategy {
        AtLeast() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            int min = Math.min(i2 / i4, i / i3);
            if (min == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(min);
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class AtMost extends DownsampleStrategy {
        AtMost() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            int ceil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            int max = Math.max(1, Integer.highestOneBit(ceil));
            return 1.0f / (max << (max >= ceil ? 0 : 1));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class None extends DownsampleStrategy {
        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return 1.0f;
        }

        None() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class CenterInside extends DownsampleStrategy {
        CenterInside() {
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, FIT_CENTER.getScaleFactor(i, i2, i3, i4));
        }

        @Override // com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
