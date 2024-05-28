package com.baidu.rtc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CommonDefine {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface SLILoginEvent {
        public static final String ENTER_BEGIN = "ENTER_BEGIN";
        public static final String ENTER_FAILED = "ENTER_FAILED";
        public static final String ENTER_TIMEOUT = "ENTER_TIMEOUT";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StreamAction {
        public ConcurrentHashMap<BigInteger, ArrayList<StreamInfo>> streamInfos;
        public StreamActionType type;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum StreamActionType {
        kSubscribe,
        kUnSubscribe
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StreamInfo {
        public boolean active;
        public String description;
        public String feedDisplay;
        public BigInteger feedId;
        public String feedMid;
        public String mid;
        public boolean ready;
        public boolean removed;
        public String type;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class StreamActionQueue {
        private Queue<StreamAction> pendStreamActions = new ConcurrentLinkedQueue();

        public void addAction(StreamActionType streamActionType, ConcurrentHashMap<BigInteger, ArrayList<StreamInfo>> concurrentHashMap) {
            StreamAction streamAction = new StreamAction();
            streamAction.type = streamActionType;
            streamAction.streamInfos = concurrentHashMap;
            this.pendStreamActions.add(streamAction);
        }

        public StreamAction popAction() {
            return this.pendStreamActions.poll();
        }
    }
}
