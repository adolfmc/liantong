package cn.finalteam.galleryfinal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p083v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.Toast;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.permission.EasyPermissions;
import cn.finalteam.galleryfinal.utils.ILogger;
import cn.finalteam.galleryfinal.utils.MediaScanner;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import cn.finalteam.galleryfinal.utils.Utils;
import cn.finalteam.toolsfinal.ActivityManager;
import cn.finalteam.toolsfinal.DateUtils;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;
import cn.finalteam.toolsfinal.p093io.FileUtils;
import com.bytedance.applog.tracker.Tracker;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class PhotoBaseActivity extends Activity implements EasyPermissions.PermissionCallbacks {
    protected static String mPhotoTargetFolder;
    private File mCurrentPhotoFile;
    private MediaScanner mMediaScanner;
    protected boolean mTakePhotoAction;
    private Uri mTakePhotoUri;
    private RxPermissions rxPermissions;
    protected int mScreenWidth = 720;
    protected int mScreenHeight = 1280;
    protected Handler mFinishHanlder = new Handler() { // from class: cn.finalteam.galleryfinal.PhotoBaseActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            PhotoBaseActivity.this.finishGalleryFinalPage();
        }
    };

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // cn.finalteam.galleryfinal.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(List<String> list) {
    }

    @Override // cn.finalteam.galleryfinal.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(List<String> list) {
    }

    protected abstract void takeResult(PhotoInfo photoInfo);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("takePhotoUri", this.mTakePhotoUri);
        bundle.putString("photoTargetFolder", mPhotoTargetFolder);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mTakePhotoUri = (Uri) bundle.getParcelable("takePhotoUri");
        mPhotoTargetFolder = bundle.getString("photoTargetFolder");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        ActivityManager.getActivityManager().addActivity(this);
        this.mMediaScanner = new MediaScanner(this);
        DisplayMetrics screenPix = DeviceUtils.getScreenPix(this);
        this.mScreenWidth = screenPix.widthPixels;
        this.mScreenHeight = screenPix.heightPixels;
        this.rxPermissions = new RxPermissions(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MediaScanner mediaScanner = this.mMediaScanner;
        if (mediaScanner != null) {
            mediaScanner.unScanFile();
        }
        ActivityManager.getActivityManager().finishActivity(this);
    }

    public void toast(String str) {
        Toast.makeText(this, str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void takePhotoAction() {
        PermissionDialog.show("拍照为了给您带来更好的服务，需要获取您的相机权限、存储卡权限，用于扫码、拍照、刷脸验证、分享画报、意见反馈、客服聊天、视频通话等功能，对于您授权的信息我们竭尽提供安全保护。");
        this.rxPermissions.request("android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE").subscribe(new Consumer<Boolean>() { // from class: cn.finalteam.galleryfinal.PhotoBaseActivity.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                File file;
                PermissionDialog.dimissDialog();
                if (bool.booleanValue()) {
                    if (StringUtils.isEmpty(PhotoBaseActivity.mPhotoTargetFolder)) {
                        file = GalleryFinal.getCoreConfig().getTakePhotoFolder();
                    } else {
                        file = new File(PhotoBaseActivity.mPhotoTargetFolder);
                    }
                    boolean mkdirs = FileUtils.mkdirs(file);
                    PhotoBaseActivity photoBaseActivity = PhotoBaseActivity.this;
                    photoBaseActivity.mCurrentPhotoFile = new File(file, "IMG" + DateUtils.format(new Date(), "yyyyMMddHHmmss") + ".jpg");
                    if (!mkdirs) {
                        PhotoBaseActivity.this.takePhotoFailure();
                        ILogger.m22038e("create file failure", new Object[0]);
                        return;
                    }
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    if (Build.VERSION.SDK_INT < 24) {
                        intent.putExtra("output", Uri.fromFile(PhotoBaseActivity.this.mCurrentPhotoFile));
                    } else {
                        PhotoBaseActivity photoBaseActivity2 = PhotoBaseActivity.this;
                        intent.putExtra("output", FileProvider.getUriForFile(photoBaseActivity2, "com.sinovatech.unicom.ui.fileprovider", photoBaseActivity2.mCurrentPhotoFile));
                    }
                    PhotoBaseActivity.this.startActivityForResult(intent, 1001);
                    return;
                }
                Toast.makeText(PhotoBaseActivity.this, "未开启摄像头权限", 0).show();
            }
        }, new Consumer<Throwable>() { // from class: cn.finalteam.galleryfinal.PhotoBaseActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                PermissionDialog.dimissDialog();
            }
        });
        if (DeviceUtils.existSDCard()) {
            return;
        }
        String string = getString(C1656R.string.empty_sdcard);
        toast(string);
        if (this.mTakePhotoAction) {
            resultFailure(string, true);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001 && i2 == -1) {
            try {
                if (this.mCurrentPhotoFile == null) {
                    takePhotoFailure();
                    return;
                }
                String path = this.mCurrentPhotoFile.getPath();
                if (!TextUtils.isEmpty(path) && new File(path).exists()) {
                    PhotoInfo photoInfo = new PhotoInfo();
                    photoInfo.setPhotoId(Utils.getRandom(10000, 99999));
                    photoInfo.setPhotoPath(path);
                    updateGallery(path);
                    takeResult(photoInfo);
                    return;
                }
                takePhotoFailure();
            } catch (Exception e) {
                e.printStackTrace();
                takePhotoFailure();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takePhotoFailure() {
        String string = getString(C1656R.string.take_photo_fail);
        if (this.mTakePhotoAction) {
            resultFailure(string, true);
        } else {
            toast(string);
        }
    }

    private void updateGallery(String str) {
        MediaScanner mediaScanner = this.mMediaScanner;
        if (mediaScanner != null) {
            mediaScanner.scanFile(str, "image/jpeg");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resultData(ArrayList<PhotoInfo> arrayList) {
        GalleryFinal.OnHanlderResultCallback callback = GalleryFinal.getCallback();
        int requestCode = GalleryFinal.getRequestCode();
        if (callback != null) {
            if (arrayList != null && arrayList.size() > 0) {
                callback.onHanlderSuccess(requestCode, arrayList);
            } else {
                callback.onHanlderFailure(requestCode, getString(C1656R.string.photo_list_empty));
            }
        }
        finishGalleryFinalPage();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resultFailureDelayed(String str, boolean z) {
        GalleryFinal.OnHanlderResultCallback callback = GalleryFinal.getCallback();
        int requestCode = GalleryFinal.getRequestCode();
        if (callback != null) {
            callback.onHanlderFailure(requestCode, str);
        }
        if (z) {
            this.mFinishHanlder.sendEmptyMessageDelayed(0, 500L);
        } else {
            finishGalleryFinalPage();
        }
    }

    protected void resultFailure(String str, boolean z) {
        GalleryFinal.OnHanlderResultCallback callback = GalleryFinal.getCallback();
        int requestCode = GalleryFinal.getRequestCode();
        if (callback != null) {
            callback.onHanlderFailure(requestCode, str);
        }
        if (z) {
            finishGalleryFinalPage();
        } else {
            finishGalleryFinalPage();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishGalleryFinalPage() {
        ActivityManager.getActivityManager().finishActivity(PhotoEditActivity.class);
        ActivityManager.getActivityManager().finishActivity(PhotoSelectActivity.class);
        Global.mPhotoSelectActivity = null;
        System.gc();
    }

    @Override // android.app.Activity, android.support.p083v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }
}
