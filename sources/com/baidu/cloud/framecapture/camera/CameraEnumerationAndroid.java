package com.baidu.cloud.framecapture.camera;

import android.graphics.ImageFormat;
import com.baidu.cloud.framecapture.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CameraEnumerationAndroid {
    static final ArrayList<Size> COMMON_RESOLUTIONS = new ArrayList<>(Arrays.asList(new Size(C0567f.f1819h, 120), new Size(240, C0567f.f1819h), new Size(320, 240), new Size(400, 240), new Size(480, 320), new Size(640, 360), new Size(640, 480), new Size(768, 480), new Size(854, 480), new Size(800, 600), new Size(960, 540), new Size(960, 640), new Size(1024, 576), new Size(1024, 600), new Size(1280, 720), new Size(1280, 1024), new Size(1920, 1080), new Size(1920, 1440), new Size(2560, 1440), new Size(3840, 2160)));
    private static final String TAG = "CameraEnumerationAndroid";

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class CaptureFormat {
        public final FramerateRange framerate;
        public final int height;
        public final int imageFormat = 17;
        public final int width;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class FramerateRange {
            public int max;
            public int min;

            public FramerateRange(int i, int i2) {
                this.min = i;
                this.max = i2;
            }

            public String toString() {
                return "[" + (this.min / 1000.0f) + ":" + (this.max / 1000.0f) + "]";
            }

            public boolean equals(Object obj) {
                if (obj instanceof FramerateRange) {
                    FramerateRange framerateRange = (FramerateRange) obj;
                    return this.min == framerateRange.min && this.max == framerateRange.max;
                }
                return false;
            }

            public int hashCode() {
                return (this.min * 65537) + 1 + this.max;
            }
        }

        public CaptureFormat(int i, int i2, int i3, int i4) {
            this.width = i;
            this.height = i2;
            this.framerate = new FramerateRange(i3, i4);
        }

        public CaptureFormat(int i, int i2, FramerateRange framerateRange) {
            this.width = i;
            this.height = i2;
            this.framerate = framerateRange;
        }

        public int frameSize() {
            return frameSize(this.width, this.height, 17);
        }

        public static int frameSize(int i, int i2, int i3) {
            if (i3 != 17) {
                throw new UnsupportedOperationException("Don't know how to calculate the frame size of non-NV21 image formats.");
            }
            return ((i * i2) * ImageFormat.getBitsPerPixel(i3)) / 8;
        }

        public String toString() {
            return this.width + "x" + this.height + "@" + this.framerate;
        }

        public boolean equals(Object obj) {
            if (obj instanceof CaptureFormat) {
                CaptureFormat captureFormat = (CaptureFormat) obj;
                return this.width == captureFormat.width && this.height == captureFormat.height && this.framerate.equals(captureFormat.framerate);
            }
            return false;
        }

        public int hashCode() {
            return (((this.width * 65497) + this.height) * 251) + 1 + this.framerate.hashCode();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static abstract class ClosestComparator<T> implements Comparator<T> {
        abstract int diff(T t);

        private ClosestComparator() {
        }

        @Override // java.util.Comparator
        public int compare(T t, T t2) {
            return diff(t) - diff(t2);
        }
    }

    public static CaptureFormat.FramerateRange getClosestSupportedFramerateRange(List<CaptureFormat.FramerateRange> list, final int i) {
        return (CaptureFormat.FramerateRange) Collections.min(list, new ClosestComparator<CaptureFormat.FramerateRange>() { // from class: com.baidu.cloud.framecapture.camera.CameraEnumerationAndroid.1
            private static final int MAX_FPS_DIFF_THRESHOLD = 5000;
            private static final int MAX_FPS_HIGH_DIFF_WEIGHT = 3;
            private static final int MAX_FPS_LOW_DIFF_WEIGHT = 1;
            private static final int MIN_FPS_HIGH_VALUE_WEIGHT = 4;
            private static final int MIN_FPS_LOW_VALUE_WEIGHT = 1;
            private static final int MIN_FPS_THRESHOLD = 8000;

            private int progressivePenalty(int i2, int i3, int i4, int i5) {
                if (i2 < i3) {
                    return i2 * i4;
                }
                return ((i2 - i3) * i5) + (i4 * i3);
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.baidu.cloud.framecapture.camera.CameraEnumerationAndroid.ClosestComparator
            public int diff(CaptureFormat.FramerateRange framerateRange) {
                return progressivePenalty(framerateRange.min, 8000, 1, 4) + progressivePenalty(Math.abs((i * 1000) - framerateRange.max), 5000, 1, 3);
            }
        });
    }

    public static Size getClosestSupportedSize(List<Size> list, final int i, final int i2) {
        Size size = new Size(i, i2);
        for (Size size2 : list) {
            if (size2.width == size.height && size2.height == size.width) {
                return size2;
            }
        }
        return (Size) Collections.min(list, new ClosestComparator<Size>() { // from class: com.baidu.cloud.framecapture.camera.CameraEnumerationAndroid.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.baidu.cloud.framecapture.camera.CameraEnumerationAndroid.ClosestComparator
            public int diff(Size size3) {
                return Math.abs(i - size3.width) + Math.abs(i2 - size3.height);
            }
        });
    }
}
