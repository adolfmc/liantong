package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.p083v4.content.FileProvider;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.livenesslib.util.SDCardUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.cropimg.ClipImageActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.Locale;
import me.shaohui.advancedluban.Luban;
import me.shaohui.advancedluban.OnCompressListener;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/chooseImage")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChooseImageJSPlugin extends BaseJSPlugin {
    private int maxSize;
    private String sourceType = "";
    private String crop = "no";
    private int compressSize = -999;
    private String loading = "yes";
    private int compressCount = 1;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    static /* synthetic */ int access$1108(ChooseImageJSPlugin chooseImageJSPlugin) {
        int i = chooseImageJSPlugin.compressCount;
        chooseImageJSPlugin.compressCount = i + 1;
        return i;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            this.compressCount = 1;
            this.crop = "no";
            this.sourceType = "";
            this.compressSize = -999;
            if (this.parameterJO != null) {
                this.sourceType = this.parameterJO.optString("sourceType", "");
                this.crop = this.parameterJO.optString("crop", "no");
                this.compressSize = this.parameterJO.optInt("compressSize", -999);
                this.loading = this.parameterJO.optString("loading", "yes");
            }
            chooseImage();
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序异常" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callbackSucces(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("imageType", str);
            jSONObject.put("imageData", str2);
            callbackSuccess(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void chooseImage() throws Exception {
        if ("camera".equals(this.sourceType)) {
            takeCamera();
        } else if ("album".equals(this.sourceType)) {
            takeGallery();
        } else {
            final Dialog dialog = new Dialog(this.activityContext, 2131952236);
            LinearLayout linearLayout = (LinearLayout) this.activityContext.getLayoutInflater().inflate(2131493398, (ViewGroup) null);
            linearLayout.findViewById(2131298197).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            linearLayout.findViewById(2131298199).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        ChooseImageJSPlugin.this.takeCamera();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                        chooseImageJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            linearLayout.findViewById(2131298198).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        ChooseImageJSPlugin.this.takeGallery();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                        chooseImageJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(linearLayout);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = -1;
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setGravity(80);
            window.setAttributes(attributes);
            window.setWindowAnimations(2131952235);
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeGallery() throws Exception {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(this.activityContext).request("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.4
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.PICK");
                    intent.setType("image/*");
                    new AvoidOnResult(ChooseImageJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.4.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            if (i == -1) {
                                try {
                                    if (intent2.getData() != null) {
                                        String filePathWithUri = ImageUtil.getFilePathWithUri(ChooseImageJSPlugin.this.activityContext, intent2.getData());
                                        MsLogUtil.m7980d("从本地相册选择 imageUri：" + intent2.getData());
                                        MsLogUtil.m7980d("从本地相册选择 imagePath：" + filePathWithUri);
                                        if (!filePathWithUri.endsWith(".gif") && !filePathWithUri.endsWith(".GIF")) {
                                            ChooseImageJSPlugin.this.compress(filePathWithUri, 3, ChooseImageJSPlugin.this.compressSize);
                                        }
                                        ChooseImageJSPlugin.this.callbackSucces("gif", ImageUtil.fileToBase64(new File(filePathWithUri)));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                                    chooseImageJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                                    return;
                                }
                            }
                            ChooseImageJSPlugin.this.callbackFail("11", "打开相机或者相册，但是没有拍照或者选取照片，主动取消并返回");
                        }
                    });
                    return;
                }
                ChooseImageJSPlugin.this.callbackFail("13", "用户没有开启存储卡访问权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                chooseImageJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
                PermissionDialog.dimissDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeCamera() throws Exception {
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的相机权限，用于扫码、拍照、刷脸验证、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        new RxPermissions(this.activityContext).request("android.permission.CAMERA").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                final Uri fromFile;
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    final File file = new File(ChooseImageJSPlugin.this.getChooseImageCacheDir(), "output_image.jpg");
                    if (file.exists()) {
                        file.delete();
                    }
                    if (Build.VERSION.SDK_INT >= 24) {
                        fromFile = FileProvider.getUriForFile(ChooseImageJSPlugin.this.activityContext, "com.sinovatech.unicom.ui.fileprovider", file);
                    } else {
                        fromFile = Uri.fromFile(file);
                    }
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    intent.setAction("android.media.action.IMAGE_CAPTURE");
                    intent.putExtra("output", fromFile);
                    new AvoidOnResult(ChooseImageJSPlugin.this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.6.1
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent2) {
                            if (i == -1) {
                                try {
                                    if (file.exists()) {
                                        String filePathWithUri = ImageUtil.getFilePathWithUri(ChooseImageJSPlugin.this.activityContext, fromFile);
                                        MsLogUtil.m7980d("拍照 imageUri：" + fromFile);
                                        MsLogUtil.m7980d("拍照 imagePath：" + filePathWithUri);
                                        ChooseImageJSPlugin.this.compress(filePathWithUri, 3, ChooseImageJSPlugin.this.compressSize);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                                    chooseImageJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                                    return;
                                }
                            }
                            ChooseImageJSPlugin.this.callbackFail("11", "打开相机或者相册，但是没有拍照或者选取照片，主动取消并返回");
                        }
                    });
                    return;
                }
                ChooseImageJSPlugin.this.callbackFail("12", "用户没有开启摄像头权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.7
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                chooseImageJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
                PermissionDialog.dimissDialog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void compress(String str, int i, int i2) throws Exception {
        if (Build.VERSION.SDK_INT <= 28) {
            i2 = 500;
        }
        if (i2 == -999) {
            try {
                if ("yes".equalsIgnoreCase(this.crop)) {
                    MsLogUtil.m7980d("不压缩裁剪");
                    crop(str);
                } else {
                    MsLogUtil.m7980d("不压缩返回给前端");
                    callbackSucces("jpg", ImageUtil.fileToBase64(new File(str)));
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                callbackFail("10", "程序异常" + e.getMessage());
                return;
            }
        }
        final CustomePorgressDialog customePorgressDialog = new CustomePorgressDialog(this.activityContext);
        customePorgressDialog.setMessage("正在压缩...");
        this.maxSize = 500;
        if (i2 >= 0 && i2 <= 50) {
            this.maxSize = 50;
        } else if (i2 > 50 && i2 < 500) {
            this.maxSize = i2;
        }
        Luban.compress(this.activityContext, new File(str)).setMaxSize(this.maxSize).setCompressFormat(Bitmap.CompressFormat.JPEG).putGear(i).launch(new OnCompressListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.8
            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onStart() {
                if ("yes".equalsIgnoreCase(ChooseImageJSPlugin.this.loading)) {
                    customePorgressDialog.show();
                }
                MsLogUtil.m7980d("Luban压缩 onStart");
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onSuccess(File file) {
                try {
                    if (customePorgressDialog.isShowing()) {
                        customePorgressDialog.cancel();
                    }
                    int[] computeSize = ChooseImageJSPlugin.this.computeSize(file);
                    String format = String.format(Locale.CHINA, "Luban onSuccess压缩后参数：%d*%d, %dk", Integer.valueOf(computeSize[0]), Integer.valueOf(computeSize[1]), Long.valueOf(file.length() >> 10));
                    MsLogUtil.m7980d("Luban压缩 onSuccess " + file.getAbsolutePath() + " " + format);
                    if ("yes".equalsIgnoreCase(ChooseImageJSPlugin.this.crop)) {
                        ChooseImageJSPlugin.this.crop(file.getAbsolutePath());
                    } else if (file.length() <= ChooseImageJSPlugin.this.maxSize * 1024 || ChooseImageJSPlugin.this.compressCount >= 2) {
                        ChooseImageJSPlugin.this.callbackSucces("jpg", ImageUtil.fileToBase64(file));
                    } else {
                        ChooseImageJSPlugin.access$1108(ChooseImageJSPlugin.this);
                        ChooseImageJSPlugin.this.compress(file.getPath(), 4, ChooseImageJSPlugin.this.maxSize);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    MsLogUtil.m7980d("Luban压缩 onSuccess发生错误 " + e2.getMessage());
                    ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                    chooseImageJSPlugin.callbackFail("10", "程序异常" + e2.getMessage());
                }
            }

            @Override // me.shaohui.advancedluban.OnCompressListener
            public void onError(Throwable th) {
                if (customePorgressDialog.isShowing()) {
                    customePorgressDialog.cancel();
                }
                MsLogUtil.m7980d("Luban压缩 onError" + th.getMessage());
                ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                chooseImageJSPlugin.callbackFail("10", "程序异常" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void crop(final String str) throws Exception {
        final File file = new File(getChooseImageCacheDir() + File.separator + "output_crop_image.jpg");
        if (file.exists()) {
            file.delete();
        }
        new AvoidOnResult(this.activityContext).startForResult(ClipImageActivity.prepare().aspectX(3).aspectY(3).maxWidth(1280).inputPath(str).outputPath(file.getAbsolutePath()).getIntent(this.activityContext), new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ChooseImageJSPlugin.9
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                try {
                    ChooseImageJSPlugin.this.crop = "no";
                    if (i != -1 || intent == null) {
                        ChooseImageJSPlugin.this.compress(str, 4, ChooseImageJSPlugin.this.compressSize);
                    } else {
                        ChooseImageJSPlugin.this.compress(file.getAbsolutePath(), 4, ChooseImageJSPlugin.this.compressSize);
                    }
                } catch (Exception e) {
                    MsLogUtil.m7980d("裁剪错误" + e.getMessage());
                    ChooseImageJSPlugin chooseImageJSPlugin = ChooseImageJSPlugin.this;
                    chooseImageJSPlugin.callbackFail("10", "程序异常" + e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getChooseImageCacheDir() {
        File file = new File(SDCardUtil.getOwnFileUrl("compressimage"));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] computeSize(File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        return new int[]{options.outWidth, options.outHeight};
    }
}
