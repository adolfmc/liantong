package com.baidu.cloud.cloudplayer.videoview;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class MeasureHelper {
    private static final String TAG = "MeasureHelper";
    private int mCurrentAspectRatio = 0;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WeakReference<View> mWeakView;

    public MeasureHelper(View view) {
        this.mWeakView = new WeakReference<>(view);
    }

    public View getView() {
        WeakReference<View> weakReference = this.mWeakView;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setVideoSampleAspectRatio(int i, int i2) {
        this.mVideoSarNum = i;
        this.mVideoSarDen = i2;
    }

    public void setVideoRotation(int i) {
        this.mVideoRotationDegree = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00e2, code lost:
        if (r1 > r9) goto L71;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doMeasure(int r9, int r10) {
        /*
            Method dump skipped, instructions count: 292
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.cloudplayer.videoview.MeasureHelper.doMeasure(int, int):void");
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
    }
}
