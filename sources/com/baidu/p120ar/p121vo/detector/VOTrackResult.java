package com.baidu.p120ar.p121vo.detector;

import com.baidu.p120ar.slam.TrackModel;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.VOTrackResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOTrackResult {
    private String extendedStatusStr;
    private int mAverageTime;
    private boolean mResult;
    private long mTimeCost;
    private long mTimestamp;
    private boolean mTracked;
    private float processTime;
    private ArrayList<TrackModel> trackModels;
    private int trackQuality;

    public VOTrackResult(long j) {
        this.mTimestamp = j;
    }

    public boolean isResult() {
        return this.mResult;
    }

    public void setResult(boolean z) {
        this.mResult = z;
    }

    public boolean isTracked() {
        return this.mTracked;
    }

    public void setTracked(boolean z) {
        this.mTracked = z;
    }

    public long getTimeCost() {
        return this.mTimeCost;
    }

    public void setTimeCost(long j) {
        this.mTimeCost = j;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public int getTrackQuality() {
        return this.trackQuality;
    }

    public void setTrackQuality(int i) {
        this.trackQuality = i;
    }

    public String getExtendedStatusStr() {
        return this.extendedStatusStr;
    }

    public void setExtendedStatusStr(String str) {
        this.extendedStatusStr = str;
    }

    public float getProcessTime() {
        return this.processTime;
    }

    public void setProcessTime(float f) {
        this.processTime = f;
    }

    public ArrayList<TrackModel> getTrackModels() {
        return this.trackModels;
    }

    public void setTrackModels(ArrayList<TrackModel> arrayList) {
        this.trackModels = arrayList;
    }

    public int getAverageTime() {
        return this.mAverageTime;
    }

    public void setAverageTime(int i) {
        this.mAverageTime = i;
    }
}
