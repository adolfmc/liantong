package com.sinovatech.unicom.p318ui;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import com.blankj.utilcode.util.PathUtils;
import com.fort.andjni.JniLib;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.concurrent.TimeUnit;

/* renamed from: com.sinovatech.unicom.ui.ScreenshotObserver */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ScreenshotObserver extends ContentObserver {
    public static final String TAG = "ScreenshotObserver";
    private ObservableEmitter<Uri> emitter;
    private Handler handler;
    private boolean isShow;
    private long lastClickTime;

    private boolean isFastClick() {
        return JniLib.m15917cZ(this, 353);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        JniLib.m15918cV(this, Boolean.valueOf(z), uri, 352);
    }

    public ScreenshotObserver(Handler handler) {
        super(handler);
        this.handler = handler;
        try {
            Observable.create(new ObservableOnSubscribe<Uri>(this) { // from class: com.sinovatech.unicom.ui.ScreenshotObserver.3
                final /* synthetic */ ScreenshotObserver this$0;

                {
                    JniLib.m15918cV(this, this, 351);
                }

                @Override // io.reactivex.ObservableOnSubscribe
                public void subscribe(ObservableEmitter<Uri> observableEmitter) throws Exception {
                    JniLib.m15918cV(this, observableEmitter, 350);
                }
            }).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer<Uri>(this) { // from class: com.sinovatech.unicom.ui.ScreenshotObserver.1
                final /* synthetic */ ScreenshotObserver this$0;

                {
                    JniLib.m15918cV(this, this, 347);
                }

                @Override // io.reactivex.functions.Consumer
                public void accept(Uri uri) throws Exception {
                    JniLib.m15918cV(this, uri, 346);
                }
            }, new Consumer<Throwable>(this) { // from class: com.sinovatech.unicom.ui.ScreenshotObserver.2
                final /* synthetic */ ScreenshotObserver this$0;

                {
                    JniLib.m15918cV(this, this, 349);
                }

                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    JniLib.m15918cV(this, th, 348);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "监听截屏异常:" + e.getMessage());
        }
    }

    public void screenCapture(Uri uri) {
        Message message;
        Handler handler;
        try {
            String deviceModel = DeviceHelper.getDeviceModel();
            String uri2 = uri.toString();
            MsLogUtil.m7979d(TAG, "截屏地址 = " + uri2);
            this.isShow = false;
            if (!("JEF-AN20,LIO-AN00,YAL-AL00,OCE-AN00,ELS-AN00".contains(deviceModel) && uri2.endsWith("/media")) && uri.toString().startsWith(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString())) {
                Cursor cursor = null;
                try {
                    Cursor query = App.getInstance().getContentResolver().query(uri, new String[]{"_display_name", "_data", "datetaken", "date_added"}, null, null, "date_added DESC");
                    if (query != null && query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex("_data"));
                        String str = PathUtils.getExternalDcimPath() + File.separator + "Screenshots";
                        String str2 = PathUtils.getExternalPicturesPath() + File.separator + "Screenshots";
                        if (!string.startsWith(str) && !string.startsWith(str2)) {
                            MsLogUtil.m7979d(TAG, "图片地址不匹配不做处理:");
                            if (query != null) {
                                query.close();
                            }
                            if (this.isShow) {
                                return;
                            }
                            Message message2 = new Message();
                            message2.what = 4;
                            if (isFastClick()) {
                                this.handler.sendMessage(message2);
                                return;
                            }
                            return;
                        }
                        long j = 0;
                        int columnIndex = query.getColumnIndex("datetaken");
                        if (columnIndex > 0) {
                            j = query.getLong(columnIndex);
                            MsLogUtil.m7979d(TAG, "图片的入库时间:" + j);
                        } else {
                            MsLogUtil.m7979d(TAG, "图片的入库时间:查询失败!");
                        }
                        MsLogUtil.m7979d(TAG, "图片的入库时间和当前时间是否在合理范围内 = " + (System.currentTimeMillis() - j));
                        if (System.currentTimeMillis() - j > 2000) {
                            if (query != null) {
                                query.close();
                            }
                            if (this.isShow) {
                                return;
                            }
                            Message message3 = new Message();
                            message3.what = 4;
                            if (isFastClick()) {
                                this.handler.sendMessage(message3);
                                return;
                            }
                            return;
                        }
                        MsLogUtil.m7979d(TAG, "外存数码相机图片路径 == " + str);
                        MsLogUtil.m7979d(TAG, "外存应用数码相机图片路径 == " + str2);
                        MsLogUtil.m7979d(TAG, "转化的绝对路径 == " + string);
                        if (string.contains("截屏") || string.toLowerCase().contains("screenshot") || string.toLowerCase().contains("screenshots")) {
                            Message message4 = new Message();
                            message4.what = 0;
                            if (Build.VERSION.SDK_INT >= 24) {
                                message4.obj = uri2;
                            } else {
                                message4.obj = string;
                            }
                            MsLogUtil.m7979d(TAG, "原始截屏地址 == " + uri2);
                            if (isFastClick()) {
                                this.handler.sendMessage(message4);
                            }
                        }
                        this.isShow = true;
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    if (0 != 0) {
                        cursor.close();
                    }
                    if (this.isShow) {
                        return;
                    }
                    message = new Message();
                    message.what = 4;
                    if (!isFastClick()) {
                        return;
                    }
                    handler = this.handler;
                }
                if (this.isShow) {
                    return;
                }
                message = new Message();
                message.what = 4;
                if (isFastClick()) {
                    handler = this.handler;
                    handler.sendMessage(message);
                }
            }
        } catch (Exception e2) {
            MsLogUtil.m7977e(TAG, "截屏error = " + e2.getMessage());
        }
    }
}
