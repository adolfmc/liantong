package com.baidu.rtc.player;

import com.webrtc.Logging;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PlayTimeStatistician {
    private static final String TAG = "TimeStatistician";
    private long mEndTime;
    private String mRoomName;
    private long mStartTime;
    private Map<PlayStep, Long> mStepsMap = new TreeMap(new Comparator<PlayStep>() { // from class: com.baidu.rtc.player.PlayTimeStatistician.1
        @Override // java.util.Comparator
        public int compare(PlayStep playStep, PlayStep playStep2) {
            if (playStep.value == playStep2.value) {
                return 0;
            }
            return playStep.value > playStep2.value ? 1 : -1;
        }
    });

    public void setRoomName(String str) {
        this.mRoomName = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PlayStep {
        PLAY_LIBRARY_DOWNLOADED(0),
        PLAY_LIBRARY_LOADED(1),
        PLAY_PREPARE_ASYNC(2),
        PLAY_LOCAL_SDP_SET(3),
        PLAY_HTTP_CONNECTION_OPENED(4),
        PLAY_REMOTE_SDP_ACQUIRED(5),
        PLAY_RENDER_VIEW_VISIBLE(6),
        PLAY_ADD_STREAM(7),
        PLAY_ICE_CONNECTED(8),
        PLAY_START(9),
        PLAY_FIRST_FRAME_RENDERED(10),
        PLAY_ERROR(11);
        
        private final int value;

        PlayStep(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlayTimeStatistician(String str) {
        this.mRoomName = str;
        this.mStepsMap.put(PlayStep.PLAY_LIBRARY_DOWNLOADED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_LIBRARY_LOADED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_PREPARE_ASYNC, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_LOCAL_SDP_SET, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_HTTP_CONNECTION_OPENED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_REMOTE_SDP_ACQUIRED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_RENDER_VIEW_VISIBLE, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_ICE_CONNECTED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_ADD_STREAM, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_START, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_FIRST_FRAME_RENDERED, new Long(-1L));
        this.mStepsMap.put(PlayStep.PLAY_ERROR, new Long(-1L));
    }

    public void setStartTime() {
        this.mStartTime = System.currentTimeMillis();
        for (Map.Entry<PlayStep, Long> entry : this.mStepsMap.entrySet()) {
            entry.setValue(new Long(-1L));
        }
    }

    public void updateStepTime(PlayStep playStep) {
        this.mStepsMap.put(playStep, Long.valueOf(System.currentTimeMillis() - this.mStartTime));
        if (playStep == PlayStep.PLAY_ERROR || playStep == PlayStep.PLAY_FIRST_FRAME_RENDERED) {
            this.mEndTime = System.currentTimeMillis();
        }
        Logging.m5305d("TimeStatistician", this.mRoomName + " : Play step " + playStep + " total expend " + this.mStepsMap.get(playStep));
    }

    public long getStepTime(PlayStep playStep) {
        return this.mStepsMap.get(playStep).longValue();
    }

    public long getStepTargetTime(PlayStep playStep, PlayStep playStep2) {
        return this.mStepsMap.get(playStep).longValue() - this.mStepsMap.get(playStep2).longValue();
    }

    public long getRealFirstFrameTime() {
        long longValue = this.mStepsMap.get(PlayStep.PLAY_LIBRARY_DOWNLOADED).longValue() - this.mStepsMap.get(PlayStep.PLAY_RENDER_VIEW_VISIBLE).longValue();
        long longValue2 = this.mStepsMap.get(PlayStep.PLAY_FIRST_FRAME_RENDERED).longValue() - this.mStepsMap.get(PlayStep.PLAY_RENDER_VIEW_VISIBLE).longValue();
        long j = longValue2 - (longValue > 0 ? longValue : 0L);
        Logging.m5305d("TimeStatistician", this.mRoomName + " :count first frame expend { downloadLibsExpend: " + longValue + ", totalExpend:" + longValue2 + ", realFirstFrameTimeExpend: " + j + "}");
        return j;
    }

    public Map<PlayStep, Long> getTimeStepsMap() {
        return this.mStepsMap;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public String toString() {
        return "TimeStatistician{ roomName= " + this.mRoomName + " startTime=" + this.mStartTime + " timeStepsMap=" + this.mStepsMap + " realFirstFrameConsume=" + getRealFirstFrameTime() + " endTime=" + this.mEndTime + '}';
    }
}
