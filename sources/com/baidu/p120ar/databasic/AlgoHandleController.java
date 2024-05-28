package com.baidu.p120ar.databasic;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.databasic.AlgoHandleController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AlgoHandleController {
    private AlgoHandleHandler mHandleHandler;
    private HandlerThread mHandleThread;
    private final List<Long> mCreateHandleList = Collections.synchronizedList(new ArrayList());
    private boolean enableCreate = true;
    private int mType = 0;
    private long mUsingHandle = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.databasic.AlgoHandleController$AlgoHandleHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static final class AlgoHandleHandler extends Handler {
        private static final int MSG_CALLBACK = 1002;
        private static final int MSG_CREATE = 1001;
        private static final int MSG_DESTROY_HANDLE = 1003;
        private static final int MSG_RELEASE = 1004;
        private boolean mRelease;

        public AlgoHandleHandler(Looper looper) {
            super(looper);
            this.mRelease = false;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1004) {
                this.mRelease = true;
            }
            Runnable runnable = (Runnable) message.obj;
            if (runnable != null) {
                runnable.run();
            }
        }

        public void sendMessage(int i, Runnable runnable) {
            if (this.mRelease) {
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = runnable;
            sendMessage(obtain);
        }
    }

    public AlgoHandleController() {
        if (this.mHandleThread == null) {
            this.mHandleThread = new HandlerThread("HandleHandlerThread");
            this.mHandleThread.start();
        }
        if (this.mHandleHandler == null) {
            this.mHandleHandler = new AlgoHandleHandler(this.mHandleThread.getLooper());
        }
    }

    public void sendHandleToRenderer(final long j, final IARRenderer iARRenderer, final String str) {
        AlgoHandleHandler algoHandleHandler;
        HandlerThread handlerThread = this.mHandleThread;
        if (handlerThread == null || !handlerThread.isAlive() || (algoHandleHandler = this.mHandleHandler) == null) {
            return;
        }
        algoHandleHandler.sendMessage(1002, new Runnable() { // from class: com.baidu.ar.databasic.AlgoHandleController.1
            @Override // java.lang.Runnable
            public void run() {
                IARRenderer iARRenderer2 = iARRenderer;
                if (iARRenderer2 != null) {
                    iARRenderer2.setAlgoHandleData(j, str);
                }
            }
        });
    }

    public void setUsingHandle(long j) {
        this.mUsingHandle = j;
    }

    public void release() {
        AlgoHandleHandler algoHandleHandler;
        this.enableCreate = false;
        HandlerThread handlerThread = this.mHandleThread;
        if (handlerThread == null || !handlerThread.isAlive() || (algoHandleHandler = this.mHandleHandler) == null) {
            return;
        }
        algoHandleHandler.sendMessage(1004, new Runnable() { // from class: com.baidu.ar.databasic.AlgoHandleController.2
            @Override // java.lang.Runnable
            public void run() {
                AlgoHandleController.this.executeRelease();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRelease() {
        this.enableCreate = false;
        if (this.mCreateHandleList.size() > 0) {
            try {
                for (Long l : this.mCreateHandleList) {
                    long longValue = l.longValue();
                    if (longValue <= 0 || longValue != this.mUsingHandle) {
                        AlgoHandleAdapter.destroyHandle(longValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ARLog.m20420e("release Exception:" + e.getMessage());
            }
        }
        if (this.mHandleHandler != null) {
            this.mHandleHandler = null;
        }
        HandlerThread handlerThread = this.mHandleThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandleThread = null;
        }
    }

    public long createHandle() {
        AlgoHandleHandler algoHandleHandler;
        if (this.enableCreate) {
            final long createHandle = AlgoHandleAdapter.createHandle();
            HandlerThread handlerThread = this.mHandleThread;
            if (handlerThread != null && handlerThread.isAlive() && (algoHandleHandler = this.mHandleHandler) != null) {
                algoHandleHandler.sendMessage(1001, new Runnable() { // from class: com.baidu.ar.databasic.AlgoHandleController.3
                    @Override // java.lang.Runnable
                    public void run() {
                        AlgoHandleController.this.mCreateHandleList.add(Long.valueOf(createHandle));
                    }
                });
            }
            return createHandle;
        }
        return 0L;
    }

    public int setHandleInput(long j, int i, long j2, int i2, int i3, int i4, boolean z, int i5, boolean z2, ByteBuffer byteBuffer) {
        this.mType = i;
        return AlgoHandleAdapter.setHandleInput(j, i, j2, i2, i3, i4, z, i5, z2, byteBuffer);
    }

    public int setHandleMaskThreshold(long j, float f) {
        return AlgoHandleAdapter.setHandleMaskThreshold(j, f);
    }

    public int setHandleFaceHandle(long j, long j2) {
        return AlgoHandleAdapter.setHandleFaceHandle(j, j2);
    }

    public byte[] getHandleMaskData(long j) {
        return AlgoHandleAdapter.getHandleMaskData(j);
    }

    public int getHandleReserveData(long j, ReserveHandleData reserveHandleData) {
        return AlgoHandleAdapter.getHandleReserveData(j, reserveHandleData);
    }

    public int getHandleType(long j) {
        return AlgoHandleAdapter.getHandleType(j);
    }

    public int increaseHandleReference(long j) {
        return AlgoHandleAdapter.increaseHandleReference(j);
    }

    public int destroyHandle(final long j) {
        AlgoHandleHandler algoHandleHandler;
        HandlerThread handlerThread = this.mHandleThread;
        if (handlerThread == null || !handlerThread.isAlive() || (algoHandleHandler = this.mHandleHandler) == null) {
            return -1;
        }
        algoHandleHandler.sendMessage(1003, new Runnable() { // from class: com.baidu.ar.databasic.AlgoHandleController.4
            @Override // java.lang.Runnable
            public void run() {
                AlgoHandleController.this.executeDestroyHandle(j);
            }
        });
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int executeDestroyHandle(long j) {
        removeHandle(j);
        return AlgoHandleAdapter.destroyHandle(j);
    }

    private void removeHandle(long j) {
        try {
            if (this.mCreateHandleList.contains(Long.valueOf(j))) {
                int indexOf = this.mCreateHandleList.indexOf(Long.valueOf(j));
                if (indexOf >= 0) {
                    this.mCreateHandleList.remove(indexOf);
                    if (indexOf >= 1) {
                        destroyIgnoreHandles(indexOf);
                    }
                } else {
                    ARLog.m20420e("removeHandle cant find:" + j);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ARLog.m20420e("removeHandle Exception:" + e.getMessage());
        }
    }

    private void destroyIgnoreHandles(int i) {
        if (i > 5) {
            ARLog.m20418i("type:" + this.mType + " destroyIgnoreHandles current size:" + i);
            for (int i2 = 0; i2 < i; i2++) {
                long longValue = this.mCreateHandleList.get(0).longValue();
                this.mCreateHandleList.remove(0);
                AlgoHandleAdapter.destroyHandle(longValue);
            }
        }
    }
}
