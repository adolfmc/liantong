package com.baidu.rtc.snapshot;

import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import com.baidu.rtc.utils.FileUtils;
import com.webrtc.EglRenderer;
import com.webrtc.Logging;
import com.webrtc.SurfaceViewRenderer;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class SnapShotHelper implements ISnapShot {
    private static final String TAG = "SnapShotHelper";
    private final Handler mHandler;
    private final SurfaceViewRenderer mRenderer;

    public SnapShotHelper(SurfaceViewRenderer surfaceViewRenderer, Handler handler) {
        this.mRenderer = surfaceViewRenderer;
        this.mHandler = handler;
    }

    @Override // com.baidu.rtc.snapshot.ISnapShot
    public void takeSnapShot(final String str, final SnapShotCallback snapShotCallback) {
        if (this.mRenderer == null || TextUtils.isEmpty(str)) {
            snapShotCallback.onSnapShotTake(false, "render is null");
            return;
        }
        EglRenderer.FrameListener frameListener = new EglRenderer.FrameListener() { // from class: com.baidu.rtc.snapshot.SnapShotHelper.1
            String picFilePath;
            boolean picHasTake = false;

            {
                this.picFilePath = str;
            }

            @Override // com.webrtc.EglRenderer.FrameListener
            public void onFrame(Bitmap bitmap) {
                if (this.picHasTake) {
                    Logging.m5305d("SnapShotHelper", "pic has take already once!");
                    bitmap.recycle();
                    return;
                }
                File file = new File(str);
                if (!file.getParentFile().exists()) {
                    try {
                        file.getParentFile().mkdirs();
                    } catch (Exception e) {
                        SnapShotCallback snapShotCallback2 = snapShotCallback;
                        if (snapShotCallback2 != null) {
                            snapShotCallback2.onSnapShotTake(false, e.getMessage());
                            return;
                        }
                        return;
                    }
                }
                FileUtils.deleteFile(file);
                String saveBitmap2JPG = FileUtils.saveBitmap2JPG(file.getParentFile().getPath(), file.getName(), bitmap, 90);
                bitmap.recycle();
                this.picHasTake = true;
                if (SnapShotHelper.this.mRenderer != null) {
                    SnapShotHelper.this.mRenderer.post(new Runnable() { // from class: com.baidu.rtc.snapshot.SnapShotHelper.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SnapShotHelper.this.mRenderer.removeFrameListener(this);
                        }
                    });
                }
                SnapShotCallback snapShotCallback3 = snapShotCallback;
                if (snapShotCallback3 != null) {
                    if (saveBitmap2JPG != null) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        Logging.m5304e("SnapShotHelper", "take pic " + width + "x" + height + " file:" + saveBitmap2JPG);
                        snapShotCallback.onSnapShotTake(true, str);
                        return;
                    }
                    snapShotCallback3.onSnapShotTake(false, "save bitmap fault");
                }
            }
        };
        SurfaceViewRenderer surfaceViewRenderer = this.mRenderer;
        if (surfaceViewRenderer != null) {
            surfaceViewRenderer.addFrameListener(frameListener, 1.0f);
        }
    }
}
