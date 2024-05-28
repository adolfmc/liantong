package com.baidu.p120ar.arplay.core.engine;

import com.baidu.p120ar.arplay.core.message.ARPMessage;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.GestureManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GestureManager {
    private ARPMessage.MessageHandler mMessageHandler;
    private boolean mDisableGesture = false;
    private boolean mDisableClick = false;
    private boolean mDisableDoubleClick = false;
    private boolean mDisableLongPress = false;
    private boolean mDisableSwipe = false;
    private boolean mDisableScroll = false;
    private boolean mDisableTwoFingerScroll = false;
    private boolean mDisablePinch = false;
    private boolean mDisableTwoFingerRotate = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GestureManager() {
        createMessageHandler();
    }

    private void createMessageHandler() {
        this.mMessageHandler = new ARPMessage.MessageHandler() { // from class: com.baidu.ar.arplay.core.engine.GestureManager.1
            @Override // com.baidu.p120ar.arplay.core.message.ARPMessage.MessageHandler
            public void handleMessage(int i, int i2, HashMap<String, Object> hashMap) {
                if (hashMap != null) {
                    if (hashMap.get("disable_all") != null) {
                        if (1 == ((Integer) hashMap.get("disable_all")).intValue()) {
                            GestureManager.this.mDisableGesture = true;
                        } else {
                            GestureManager.this.mDisableGesture = false;
                        }
                    }
                    if (hashMap.get("disable_click") != null) {
                        if (1 == ((Integer) hashMap.get("disable_click")).intValue()) {
                            GestureManager.this.mDisableClick = true;
                        } else {
                            GestureManager.this.mDisableClick = false;
                        }
                    }
                    if (hashMap.get("disable_double_click") != null) {
                        if (1 == ((Integer) hashMap.get("disable_double_click")).intValue()) {
                            GestureManager.this.mDisableDoubleClick = true;
                        } else {
                            GestureManager.this.mDisableDoubleClick = false;
                        }
                    }
                    if (hashMap.get("disable_long_press") != null) {
                        if (1 == ((Integer) hashMap.get("disable_long_press")).intValue()) {
                            GestureManager.this.mDisableLongPress = true;
                        } else {
                            GestureManager.this.mDisableLongPress = false;
                        }
                    }
                    if (hashMap.get("disable_swipe") != null) {
                        if (1 == ((Integer) hashMap.get("disable_swipe")).intValue()) {
                            GestureManager.this.mDisableSwipe = true;
                        } else {
                            GestureManager.this.mDisableSwipe = false;
                        }
                    }
                    if (hashMap.get("disable_scroll") != null) {
                        if (1 == ((Integer) hashMap.get("disable_scroll")).intValue()) {
                            GestureManager.this.mDisableScroll = true;
                        } else {
                            GestureManager.this.mDisableScroll = false;
                        }
                    }
                    if (hashMap.get("disable_two_finger_scroll") != null) {
                        if (1 == ((Integer) hashMap.get("disable_two_finger_scroll")).intValue()) {
                            GestureManager.this.mDisableTwoFingerScroll = true;
                        } else {
                            GestureManager.this.mDisableTwoFingerScroll = false;
                        }
                    }
                    if (hashMap.get("disable_pinch") != null) {
                        if (1 == ((Integer) hashMap.get("disable_pinch")).intValue()) {
                            GestureManager.this.mDisablePinch = true;
                        } else {
                            GestureManager.this.mDisablePinch = false;
                        }
                    }
                    if (hashMap.get("disable_two_finger_rotate") != null) {
                        if (1 == ((Integer) hashMap.get("disable_two_finger_rotate")).intValue()) {
                            GestureManager.this.mDisableTwoFingerRotate = true;
                        } else {
                            GestureManager.this.mDisableTwoFingerRotate = false;
                        }
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onResume() {
        ARPMessage.getInstance().registerMessageHandler(11, this.mMessageHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onPause() {
        ARPMessage.getInstance().removeMessageHandeler(this.mMessageHandler);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        this.mMessageHandler = null;
    }

    public boolean isClickEnable() {
        return (this.mDisableGesture || this.mDisableClick) ? false : true;
    }

    public boolean isDoubleClickEnable() {
        return (this.mDisableGesture || this.mDisableDoubleClick) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isLongPressEnable() {
        return (this.mDisableGesture || this.mDisableLongPress) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isScrollEnable() {
        return (this.mDisableGesture || this.mDisableScroll) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean is2FingerScrollEnable() {
        return (this.mDisableGesture || this.mDisableTwoFingerScroll) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isSwipeEnable() {
        return (this.mDisableGesture || this.mDisableSwipe) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPinchEnable() {
        return (this.mDisableGesture || this.mDisablePinch) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isRotateEnable() {
        return (this.mDisableGesture || this.mDisableTwoFingerRotate) ? false : true;
    }
}
