package com.baidu.p120ar.algovo;

import com.baidu.p120ar.algo.ARAlgoJniClient;
import com.baidu.p120ar.algo.FrameType;
import com.baidu.p120ar.slam.TrackModel;
import com.baidu.p120ar.slam.TrackParams;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.algovo.ARVOJniClient */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARVOJniClient {
    private static List<String> sModelIds;
    private static int sTrackerId;

    public static synchronized boolean start(int i, int i2, float[] fArr, float[] fArr2) {
        synchronized (ARVOJniClient.class) {
            if (ARAlgoJniClient.getAlgoInstance().createTrackingSystem(i, i2, fArr, fArr2) == 0) {
                sTrackerId = ARAlgoJniClient.getAlgoInstance().addTrackerVO("");
                return sTrackerId >= 0;
            }
            return false;
        }
    }

    public static synchronized void reset() {
        synchronized (ARVOJniClient.class) {
            if (sTrackerId >= 0) {
                ARAlgoJniClient.getAlgoInstance().removeAllModels(sTrackerId);
            }
        }
    }

    public static synchronized void stop() {
        synchronized (ARVOJniClient.class) {
            ARAlgoJniClient.getAlgoInstance().release();
            sModelIds = null;
        }
    }

    public static int calModelPosition(float[] fArr, float f, float[] fArr2, float[] fArr3) {
        return ARAlgoJniClient.calModelPosition(fArr, f, fArr2, fArr3);
    }

    public static synchronized int insertModel(String str, int i, int i2, float[] fArr, float f) {
        int insertModel;
        synchronized (ARVOJniClient.class) {
            insertModel = ARAlgoJniClient.getAlgoInstance().insertModel(sTrackerId, i, i2, str, f, fArr);
            if (insertModel == 0) {
                if (sModelIds == null) {
                    sModelIds = new ArrayList();
                }
                sModelIds.add(str);
            }
        }
        return insertModel;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
        com.baidu.p120ar.algovo.ARVOJniClient.sModelIds.remove(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int removeModel(java.lang.String r5) {
        /*
            java.lang.Class<com.baidu.ar.algovo.ARVOJniClient> r0 = com.baidu.p120ar.algovo.ARVOJniClient.class
            monitor-enter(r0)
            com.baidu.ar.algo.ARAlgoJniClient r1 = com.baidu.p120ar.algo.ARAlgoJniClient.getAlgoInstance()     // Catch: java.lang.Throwable -> L35
            int r2 = com.baidu.p120ar.algovo.ARVOJniClient.sTrackerId     // Catch: java.lang.Throwable -> L35
            int r1 = r1.removeModel(r2, r5)     // Catch: java.lang.Throwable -> L35
            if (r1 != 0) goto L33
            java.util.List<java.lang.String> r2 = com.baidu.p120ar.algovo.ARVOJniClient.sModelIds     // Catch: java.lang.Throwable -> L35
            if (r2 == 0) goto L33
            r2 = 0
            java.util.List<java.lang.String> r3 = com.baidu.p120ar.algovo.ARVOJniClient.sModelIds     // Catch: java.lang.Throwable -> L35
            int r3 = r3.size()     // Catch: java.lang.Throwable -> L35
        L1a:
            if (r2 >= r3) goto L33
            java.util.List<java.lang.String> r4 = com.baidu.p120ar.algovo.ARVOJniClient.sModelIds     // Catch: java.lang.Throwable -> L35
            java.lang.Object r4 = r4.get(r2)     // Catch: java.lang.Throwable -> L35
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.Throwable -> L35
            boolean r4 = r4.equals(r5)     // Catch: java.lang.Throwable -> L35
            if (r4 == 0) goto L30
            java.util.List<java.lang.String> r5 = com.baidu.p120ar.algovo.ARVOJniClient.sModelIds     // Catch: java.lang.Throwable -> L35
            r5.remove(r2)     // Catch: java.lang.Throwable -> L35
            goto L33
        L30:
            int r2 = r2 + 1
            goto L1a
        L33:
            monitor-exit(r0)
            return r1
        L35:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.algovo.ARVOJniClient.removeModel(java.lang.String):int");
    }

    public static synchronized int removeAllModel() {
        int removeAllModels;
        synchronized (ARVOJniClient.class) {
            removeAllModels = ARAlgoJniClient.getAlgoInstance().removeAllModels(sTrackerId);
            if (removeAllModels == 0 && sModelIds != null) {
                sModelIds.clear();
            }
        }
        return removeAllModels;
    }

    public static synchronized ArrayList<TrackModel> fetchModelPose() {
        ArrayList<TrackModel> arrayList;
        synchronized (ARVOJniClient.class) {
            arrayList = new ArrayList<>();
            if (sModelIds != null) {
                for (String str : sModelIds) {
                    float[] fArr = new float[16];
                    if (ARAlgoJniClient.getAlgoInstance().getModelPose(sTrackerId, str, fArr) == 0) {
                        TrackModel trackModel = new TrackModel();
                        trackModel.setId(str);
                        trackModel.setPose(fArr);
                        arrayList.add(trackModel);
                    }
                }
            }
        }
        return arrayList;
    }

    public static synchronized TrackParams track(ByteBuffer byteBuffer, float[] fArr) {
        TrackParams trackParams;
        synchronized (ARVOJniClient.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int trackFrame = ARAlgoJniClient.getAlgoInstance().trackFrame(byteBuffer, ((float) currentTimeMillis) * 1.0f, fArr, FrameType.TYPE_YUV);
            trackParams = new TrackParams();
            trackParams.processTime = (float) (System.currentTimeMillis() - currentTimeMillis);
            trackParams.trackQuality = trackFrame;
        }
        return trackParams;
    }
}
