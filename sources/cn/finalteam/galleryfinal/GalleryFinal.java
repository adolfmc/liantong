package cn.finalteam.galleryfinal;

import android.content.Intent;
import android.widget.Toast;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.utils.ILogger;
import cn.finalteam.galleryfinal.utils.Utils;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;
import cn.finalteam.toolsfinal.p093io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GalleryFinal {
    static final int PERMISSIONS_CODE_GALLERY = 2001;
    static final int TAKE_REQUEST_CODE = 1001;
    private static OnHanlderResultCallback mCallback;
    private static CoreConfig mCoreConfig;
    private static FunctionConfig mCurrentFunctionConfig;
    private static FunctionConfig mGlobalFunctionConfig;
    private static int mRequestCode;
    private static ThemeConfig mThemeConfig;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnHanlderResultCallback {
        void onHanlderFailure(int i, String str);

        void onHanlderSuccess(int i, List<PhotoInfo> list);
    }

    public static void init(CoreConfig coreConfig) {
        mThemeConfig = coreConfig.getThemeConfig();
        mCoreConfig = coreConfig;
        mGlobalFunctionConfig = coreConfig.getFunctionConfig();
    }

    public static FunctionConfig copyGlobalFuncationConfig() {
        FunctionConfig functionConfig = mGlobalFunctionConfig;
        if (functionConfig != null) {
            return functionConfig.m24461clone();
        }
        return null;
    }

    public static CoreConfig getCoreConfig() {
        return mCoreConfig;
    }

    public static FunctionConfig getFunctionConfig() {
        return mCurrentFunctionConfig;
    }

    public static ThemeConfig getGalleryTheme() {
        if (mThemeConfig == null) {
            mThemeConfig = ThemeConfig.DEFAULT;
        }
        return mThemeConfig;
    }

    public static void openGallerySingle(int i, OnHanlderResultCallback onHanlderResultCallback) {
        FunctionConfig copyGlobalFuncationConfig = copyGlobalFuncationConfig();
        if (copyGlobalFuncationConfig != null) {
            openGallerySingle(i, copyGlobalFuncationConfig, onHanlderResultCallback);
            return;
        }
        if (onHanlderResultCallback != null) {
            onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
        }
        ILogger.m22038e("FunctionConfig null", new Object[0]);
    }

    public static void openGallerySingle(int i, FunctionConfig functionConfig, OnHanlderResultCallback onHanlderResultCallback) {
        if (mCoreConfig.getImageLoader() == null) {
            ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig == null && mGlobalFunctionConfig == null) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), C1656R.string.empty_sdcard, 0).show();
        } else {
            functionConfig.mutiSelect = false;
            mRequestCode = i;
            mCallback = onHanlderResultCallback;
            mCurrentFunctionConfig = functionConfig;
            mCoreConfig.getContext().startActivity(new Intent(mCoreConfig.getContext(), PhotoSelectActivity.class));
        }
    }

    public static void openGalleryMuti(int i, int i2, OnHanlderResultCallback onHanlderResultCallback) {
        FunctionConfig copyGlobalFuncationConfig = copyGlobalFuncationConfig();
        if (copyGlobalFuncationConfig != null) {
            copyGlobalFuncationConfig.maxSize = i2;
            openGalleryMuti(i, copyGlobalFuncationConfig, onHanlderResultCallback);
            return;
        }
        if (onHanlderResultCallback != null) {
            onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
        }
        ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
    }

    public static void openGalleryMuti(int i, FunctionConfig functionConfig, OnHanlderResultCallback onHanlderResultCallback) {
        if (mCoreConfig.getImageLoader() == null) {
            ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig == null && mGlobalFunctionConfig == null) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig.getMaxSize() <= 0) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.maxsize_zero_tip));
            }
        } else if (functionConfig.getSelectedList() != null && functionConfig.getSelectedList().size() > functionConfig.getMaxSize()) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.select_max_tips));
            }
        } else if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), C1656R.string.empty_sdcard, 0).show();
        } else {
            mRequestCode = i;
            mCallback = onHanlderResultCallback;
            mCurrentFunctionConfig = functionConfig;
            functionConfig.mutiSelect = true;
            Intent intent = new Intent(mCoreConfig.getContext(), PhotoSelectActivity.class);
            intent.addFlags(268435456);
            mCoreConfig.getContext().startActivity(intent);
        }
    }

    public static void openCamera(int i, OnHanlderResultCallback onHanlderResultCallback) {
        FunctionConfig copyGlobalFuncationConfig = copyGlobalFuncationConfig();
        if (copyGlobalFuncationConfig != null) {
            openCamera(i, copyGlobalFuncationConfig, onHanlderResultCallback);
            return;
        }
        if (onHanlderResultCallback != null) {
            onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
        }
        ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
    }

    public static void openCamera(int i, FunctionConfig functionConfig, OnHanlderResultCallback onHanlderResultCallback) {
        if (mCoreConfig.getImageLoader() == null) {
            ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig == null && mGlobalFunctionConfig == null) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), C1656R.string.empty_sdcard, 0).show();
        } else {
            mRequestCode = i;
            mCallback = onHanlderResultCallback;
            functionConfig.mutiSelect = false;
            mCurrentFunctionConfig = functionConfig;
            Intent intent = new Intent(mCoreConfig.getContext(), PhotoEditActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("take_photo_action", true);
            mCoreConfig.getContext().startActivity(intent);
        }
    }

    public static void openCrop(int i, String str, OnHanlderResultCallback onHanlderResultCallback) {
        FunctionConfig copyGlobalFuncationConfig = copyGlobalFuncationConfig();
        if (copyGlobalFuncationConfig != null) {
            openCrop(i, copyGlobalFuncationConfig, str, onHanlderResultCallback);
            return;
        }
        if (onHanlderResultCallback != null) {
            onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
        }
        ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
    }

    public static void openCrop(int i, FunctionConfig functionConfig, String str, OnHanlderResultCallback onHanlderResultCallback) {
        if (mCoreConfig.getImageLoader() == null) {
            ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig == null && mGlobalFunctionConfig == null) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), C1656R.string.empty_sdcard, 0).show();
        } else if (functionConfig == null || StringUtils.isEmpty(str) || !new File(str).exists()) {
            ILogger.m22039d("config为空或文件不存在", new Object[0]);
        } else {
            mRequestCode = i;
            mCallback = onHanlderResultCallback;
            functionConfig.mutiSelect = false;
            functionConfig.editPhoto = true;
            functionConfig.crop = true;
            mCurrentFunctionConfig = functionConfig;
            ArrayList arrayList = new ArrayList();
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setPhotoPath(str);
            photoInfo.setPhotoId(Utils.getRandom(10000, 99999));
            arrayList.add(photoInfo);
            Intent intent = new Intent(mCoreConfig.getContext(), PhotoEditActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("crop_photo_action", true);
            intent.putExtra(PhotoEditActivity.SELECT_MAP, arrayList);
            mCoreConfig.getContext().startActivity(intent);
        }
    }

    public static void openEdit(int i, String str, OnHanlderResultCallback onHanlderResultCallback) {
        FunctionConfig copyGlobalFuncationConfig = copyGlobalFuncationConfig();
        if (copyGlobalFuncationConfig != null) {
            openEdit(i, copyGlobalFuncationConfig, str, onHanlderResultCallback);
            return;
        }
        if (onHanlderResultCallback != null) {
            onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
        }
        ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
    }

    public static void openEdit(int i, FunctionConfig functionConfig, String str, OnHanlderResultCallback onHanlderResultCallback) {
        if (mCoreConfig.getImageLoader() == null) {
            ILogger.m22038e("Please init GalleryFinal.", new Object[0]);
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (functionConfig == null && mGlobalFunctionConfig == null) {
            if (onHanlderResultCallback != null) {
                onHanlderResultCallback.onHanlderFailure(i, mCoreConfig.getContext().getString(C1656R.string.open_gallery_fail));
            }
        } else if (!DeviceUtils.existSDCard()) {
            Toast.makeText(mCoreConfig.getContext(), C1656R.string.empty_sdcard, 0).show();
        } else if (functionConfig == null || StringUtils.isEmpty(str) || !new File(str).exists()) {
            ILogger.m22039d("config为空或文件不存在", new Object[0]);
        } else {
            mRequestCode = i;
            mCallback = onHanlderResultCallback;
            functionConfig.mutiSelect = false;
            mCurrentFunctionConfig = functionConfig;
            ArrayList arrayList = new ArrayList();
            PhotoInfo photoInfo = new PhotoInfo();
            photoInfo.setPhotoPath(str);
            photoInfo.setPhotoId(Utils.getRandom(10000, 99999));
            arrayList.add(photoInfo);
            Intent intent = new Intent(mCoreConfig.getContext(), PhotoEditActivity.class);
            intent.addFlags(268435456);
            intent.putExtra("edit_photo_action", true);
            intent.putExtra(PhotoEditActivity.SELECT_MAP, arrayList);
            mCoreConfig.getContext().startActivity(intent);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [cn.finalteam.galleryfinal.GalleryFinal$1] */
    public static void cleanCacheFile() {
        if (mCurrentFunctionConfig == null || mCoreConfig.getEditPhotoCacheFolder() == null) {
            return;
        }
        new Thread() { // from class: cn.finalteam.galleryfinal.GalleryFinal.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    FileUtils.deleteDirectory(GalleryFinal.mCoreConfig.getEditPhotoCacheFolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static int getRequestCode() {
        return mRequestCode;
    }

    public static OnHanlderResultCallback getCallback() {
        return mCallback;
    }
}
