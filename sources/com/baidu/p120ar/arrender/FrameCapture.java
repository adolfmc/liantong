package com.baidu.p120ar.arrender;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.arplay.core.renderer.IARPRenderer;
import com.baidu.p120ar.lua.LuaMsgBridge;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.utils.ARLog;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.FrameCapture */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FrameCapture {
    private static final String TAG = "FrameCapture";
    private IARPRenderer mARPFilter;
    private LuaMsgListener mCaptureListener;
    private List<String> mCaptureMessages;
    private Handler mControlHandler;
    private LuaMsgBridge mLuaMsgBridge;
    private PixelReadListener mPixelReadListener;
    private PixelReadParams mPixelReadParams;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameCapture(Looper looper, LuaMsgBridge luaMsgBridge, IARPRenderer iARPRenderer) {
        if (looper == null || luaMsgBridge == null || iARPRenderer == null) {
            ARLog.m20419e("FrameCapture", "create FrameCapture error!!! As params NULLLLL!!!");
        }
        this.mControlHandler = new Handler(looper);
        this.mLuaMsgBridge = luaMsgBridge;
        this.mARPFilter = iARPRenderer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setup(int i, int i2) {
        createPixelReader(i, i2);
        createLuaCaptureListener(this.mLuaMsgBridge);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeCaseCaptureListener() {
        PixelReadParams pixelReadParams;
        PixelReadListener pixelReadListener;
        IARPRenderer iARPRenderer = this.mARPFilter;
        if (iARPRenderer == null || (pixelReadParams = this.mPixelReadParams) == null || (pixelReadListener = this.mPixelReadListener) == null) {
            return;
        }
        iARPRenderer.destroyPixelReaderByPreFilterID(pixelReadParams, pixelReadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearCaptureCache() {
        IARPRenderer iARPRenderer = this.mARPFilter;
        if (iARPRenderer != null) {
            iARPRenderer.clearCaptureData();
        }
    }

    private void createPixelReader(int i, int i2) {
        this.mPixelReadParams = new PixelReadParams(PixelType.RGBA);
        this.mPixelReadParams.setOutputWidth(i);
        this.mPixelReadParams.setOutputHeight(i2);
        this.mPixelReadParams.setFrameType(PixelReadParams.FrameType.SINGLE_FRAME);
        this.mPixelReadListener = new PixelReadListener() { // from class: com.baidu.ar.arrender.FrameCapture.1
            @Override // com.baidu.p120ar.arplay.core.pixel.PixelReadListener
            public boolean onPixelRead(FramePixels framePixels) {
                if (framePixels == null || FrameCapture.this.mARPFilter == null) {
                    return false;
                }
                ARLog.m20421d("FrameCapture", "onPixelRead textureID = " + framePixels.getTextureID());
                FrameCapture.this.mARPFilter.setCaptureData(framePixels.getTextureID());
                FrameCapture.this.removeCaptureListenerInThread();
                return true;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeCaptureListenerInThread() {
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.baidu.ar.arrender.FrameCapture.2
                @Override // java.lang.Runnable
                public void run() {
                    FrameCapture.this.removeCaseCaptureListener();
                }
            });
        }
    }

    private void createLuaCaptureListener(LuaMsgBridge luaMsgBridge) {
        this.mCaptureMessages = Arrays.asList("event_name");
        this.mCaptureListener = new LuaMsgListener() { // from class: com.baidu.ar.arrender.FrameCapture.3
            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public List<String> getMsgKeyListened() {
                return FrameCapture.this.mCaptureMessages;
            }

            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public void onLuaMessage(HashMap<String, Object> hashMap) {
                if (FrameCapture.this.mARPFilter == null || FrameCapture.this.mPixelReadParams == null || FrameCapture.this.mPixelReadListener == null) {
                    return;
                }
                String str = (String) hashMap.get("event_name");
                ARLog.m20421d("FrameCapture", "createLuaCaptureListener eventName = " + str);
                if ("capture_frame".equals(str)) {
                    FrameCapture.this.mPixelReadParams.setPreFilterID((String) hashMap.get("filter_id"));
                    if (FrameCapture.this.mPixelReadParams.getPreFilterID().equals("camera") || TextUtils.isEmpty(FrameCapture.this.mPixelReadParams.getPreFilterID())) {
                        if (FrameCapture.this.mARPFilter.isFrontCamera()) {
                            FrameCapture.this.mPixelReadParams.setPixelRotate(PixelRotation.RotateRightFlipHorizontal);
                        } else {
                            FrameCapture.this.mPixelReadParams.setPixelRotate(PixelRotation.RotateRight);
                        }
                    }
                    FrameCapture.this.mARPFilter.createPixelReaderByPreFilterID(FrameCapture.this.mPixelReadParams, FrameCapture.this.mPixelReadListener);
                } else if ("clear_capture".equals(str)) {
                    FrameCapture.this.mARPFilter.clearCaptureData();
                }
            }
        };
        luaMsgBridge.addLuaMsgListener(this.mCaptureListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        LuaMsgListener luaMsgListener;
        Handler handler = this.mControlHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mControlHandler = null;
        }
        LuaMsgBridge luaMsgBridge = this.mLuaMsgBridge;
        if (luaMsgBridge != null && (luaMsgListener = this.mCaptureListener) != null) {
            luaMsgBridge.removeLuaMsgListener(luaMsgListener);
        }
        this.mLuaMsgBridge = null;
        this.mCaptureListener = null;
        this.mCaptureMessages = null;
        this.mPixelReadParams = null;
        this.mPixelReadListener = null;
        this.mARPFilter = null;
    }
}
