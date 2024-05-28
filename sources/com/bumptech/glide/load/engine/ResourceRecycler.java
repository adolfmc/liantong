package com.bumptech.glide.load.engine;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
class ResourceRecycler {
    private final Handler handler = new Handler(Looper.getMainLooper(), new ResourceRecyclerCallback());
    private boolean isRecycling;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void recycle(Resource<?> resource) {
        if (this.isRecycling) {
            this.handler.obtainMessage(1, resource).sendToTarget();
        } else {
            this.isRecycling = true;
            resource.recycle();
            this.isRecycling = false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class ResourceRecyclerCallback implements Handler.Callback {
        static final int RECYCLE_RESOURCE = 1;

        ResourceRecyclerCallback() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                ((Resource) message.obj).recycle();
                return true;
            }
            return false;
        }
    }
}
