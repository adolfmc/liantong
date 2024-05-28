package com.baidu.p120ar.p121vo;

import com.baidu.p120ar.p121vo.caseconfig.VOConfig;
import com.baidu.p120ar.p121vo.detector.IVOAlgoInteraction;
import com.baidu.p120ar.p121vo.detector.VOAlgoSetting;
import com.baidu.p120ar.p121vo.detector.VOResult;
import com.baidu.p120ar.p121vo.detector.VOTrackResult;
import com.baidu.p120ar.p121vo.interact.DefaultPlaceStrategy;
import com.baidu.p120ar.p121vo.interact.GestureInteractionInfo;
import com.baidu.p120ar.p121vo.interact.IPlaceStrategy;
import com.baidu.p120ar.p121vo.interact.IVOAction;
import com.baidu.p120ar.p121vo.interact.ScreenCoordPlaceStrategy;
import com.baidu.p120ar.p121vo.interact.SpaceCoordPlaceStrategy;
import com.baidu.p120ar.slam.TrackModel;
import com.baidu.p120ar.statistic.StatisticApi;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.VOController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VOController {
    private IVOAction mAction;
    private VOAlgoSetting mAlgoSetting;
    private volatile boolean mIsModelPlaced = false;
    private volatile boolean mIsVOForbidden = false;
    private volatile boolean mIsVOStarted;
    private IPlaceStrategy mPlaceStrategy;

    public VOController(IVOAction iVOAction, VOConfig vOConfig, IVOAlgoInteraction iVOAlgoInteraction, VOAlgoSetting vOAlgoSetting) {
        this.mIsVOStarted = true;
        this.mAction = iVOAction;
        this.mAlgoSetting = vOAlgoSetting;
        if (vOConfig != null) {
            this.mIsVOStarted = vOConfig.isImmediatelyPlaceModel();
        }
        this.mPlaceStrategy = createPlaceStrategy(vOConfig, iVOAlgoInteraction);
    }

    private IPlaceStrategy createPlaceStrategy(VOConfig vOConfig, IVOAlgoInteraction iVOAlgoInteraction) {
        if (vOConfig == null) {
            return new DefaultPlaceStrategy(this.mAction, this.mAlgoSetting.frameWidth, this.mAlgoSetting.frameHeight);
        }
        if (vOConfig.getPlaceType() == 1) {
            SpaceCoordPlaceStrategy spaceCoordPlaceStrategy = new SpaceCoordPlaceStrategy(this.mAction, vOConfig, this.mAlgoSetting.frameWidth, this.mAlgoSetting.frameHeight);
            spaceCoordPlaceStrategy.setAlgoInteraction(iVOAlgoInteraction);
            return spaceCoordPlaceStrategy;
        } else if (vOConfig.getPlaceType() == 0) {
            return new ScreenCoordPlaceStrategy(this.mAction, vOConfig, this.mAlgoSetting.frameWidth, this.mAlgoSetting.frameHeight);
        } else {
            return null;
        }
    }

    public void start() {
        this.mIsVOStarted = true;
    }

    public void setForbidden(boolean z) {
        this.mIsVOForbidden = z;
    }

    public void update(VOResult vOResult, float[] fArr) {
        float[] vORTData;
        if (!this.mIsVOStarted || this.mIsVOForbidden) {
            return;
        }
        VOTrackResult trackResult = vOResult.getTrackResult();
        if (this.mIsModelPlaced) {
            if (trackResult == null || (vORTData = getVORTData(trackResult)) == null || vORTData.length <= 0) {
                return;
            }
            this.mAction.updateRT(vORTData);
            return;
        }
        IPlaceStrategy iPlaceStrategy = this.mPlaceStrategy;
        if (iPlaceStrategy != null) {
            this.mIsModelPlaced = iPlaceStrategy.placeModel(fArr);
            if (this.mIsModelPlaced) {
                this.mAction.enableOffScreenGuide();
                StatisticApi.onEvent("slam_track_on");
            }
        }
    }

    private float[] getVORTData(VOTrackResult vOTrackResult) {
        ArrayList<TrackModel> trackModels = vOTrackResult.getTrackModels();
        if (trackModels != null && !trackModels.isEmpty()) {
            return trackModels.get(0).getPose();
        }
        return new float[0];
    }

    public void resetToOrigin(float f, float f2, float f3) {
        GestureInteractionInfo gestureInteractionInfo = new GestureInteractionInfo();
        gestureInteractionInfo.f4097x = f;
        gestureInteractionInfo.f4098y = f2;
        gestureInteractionInfo.distance = f3;
        gestureInteractionInfo.type = 2;
        handleGestureInteraction(gestureInteractionInfo);
    }

    public void handleGestureInteraction(GestureInteractionInfo gestureInteractionInfo) {
        this.mAction.handleGestureInteraction(gestureInteractionInfo);
    }

    public void release() {
        IVOAction iVOAction = this.mAction;
        if (iVOAction != null) {
            iVOAction.release();
            this.mAction = null;
        }
        this.mPlaceStrategy = null;
    }
}
