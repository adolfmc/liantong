package com.sinovatech.unicom.common;

import android.app.Activity;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GallaryUtils {
    private final int REQUEST_CODE_GALLERY = 1001;
    private FunctionConfig functionConfig;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IxiangceInterface {
        void getPhotoPath(String str);
    }

    public GallaryUtils(Activity activity) {
        ThemeConfig themeConfig = ThemeConfig.DEFAULT;
        this.functionConfig = new FunctionConfig.Builder().setEnableCamera(true).setEnableEdit(true).setEnableCrop(false).setEnableRotate(false).setCropSquare(false).setEnablePreview(false).build();
        GalleryFinal.init(new CoreConfig.Builder(activity, new GlideImageLoader(), themeConfig).setFunctionConfig(this.functionConfig).setNoAnimcation(true).build());
    }

    public void openXiangce(final IxiangceInterface ixiangceInterface) {
        GalleryFinal.openGallerySingle(1001, this.functionConfig, new GalleryFinal.OnHanlderResultCallback() { // from class: com.sinovatech.unicom.common.GallaryUtils.1
            @Override // cn.finalteam.galleryfinal.GalleryFinal.OnHanlderResultCallback
            public void onHanlderSuccess(int i, List<PhotoInfo> list) {
                if (list != null && list.size() > 0) {
                    ixiangceInterface.getPhotoPath(list.get(0).getPhotoPath());
                } else {
                    ixiangceInterface.getPhotoPath("");
                }
            }

            @Override // cn.finalteam.galleryfinal.GalleryFinal.OnHanlderResultCallback
            public void onHanlderFailure(int i, String str) {
                ixiangceInterface.getPhotoPath("");
            }
        });
    }
}
