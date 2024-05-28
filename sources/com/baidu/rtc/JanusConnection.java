package com.baidu.rtc;

import com.baidu.rtc.PeerConnectionClient;
import com.webrtc.AudioTrack;
import com.webrtc.PeerConnection;
import com.webrtc.SurfaceViewRenderer;
import com.webrtc.VideoTrack;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JanusConnection {
    public AudioTrack audioTrack;
    public PeerConnectionClient.PeerConnectionParameters connectionParam;
    public BigInteger handleId;
    public int offerAnswerOwner;
    public boolean offerAnswering;
    public PeerConnectionClient.PCObserver pcObserver;
    public PeerConnection peerConnection;
    public PeerConnectionClient.SDPObserver sdpObserver;
    public boolean type;
    public SurfaceViewRenderer videoRender;
    public VideoTrack videoTrack;
    public int videoview_tag;
    public ConcurrentHashMap<String, VideoTrack> videoTracks = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, AudioTrack> audioTracks = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, String> midSsrcMap = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, SurfaceViewRenderer> videoRenders = new ConcurrentHashMap<>();
    public boolean isPlayerMode = false;
    public ConcurrentHashMap<String, Integer> videoviewTags = new ConcurrentHashMap<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface OfferAnswerOwner {
        public static final int NONE = 0;
        public static final int SHARE_SCREEN_START = 1;
        public static final int SHARE_SCREEN_STOP = 2;
    }
}
