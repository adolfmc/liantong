package com.baidu.arface.utils;

import android.content.res.AssetManager;
import com.baidu.minivideo.arface.utils.FileUtil;
import java.io.File;
import java.io.InputStream;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AssetsReplaceHelper {
    public static final String FACE_CONFIG_NAME = "arsource/res_update_config.json";
    public static final String FACE_UPDATE_CONFIG_FIELD_MASK = "face_masks_data";
    public static final String FACE_UPDATE_CONFIG_FIELD_RES = "face_config_data";
    public static final String MASK_CONFIG_NAME = "arsource/masks_update_config.json";
    private static AssetsReplaceHelper sAssetsReplaceHelper;
    private File cacheDir;

    private AssetsReplaceHelper() {
    }

    public static AssetsReplaceHelper getInstance() {
        if (sAssetsReplaceHelper == null) {
            sAssetsReplaceHelper = new AssetsReplaceHelper();
        }
        return sAssetsReplaceHelper;
    }

    public void setRootDir(File file) {
        this.cacheDir = file;
    }

    public boolean isReplaceResource(AssetManager assetManager, String str, String str2) {
        try {
            InputStream open = assetManager.open(str);
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            return parseUpdateConfigJson(new String(bArr, "utf8"), str2) != parseUpdateConfigJson(FileUtil.readFileText(new File(this.cacheDir, str)), str2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private int parseUpdateConfigJson(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(str2)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(str2);
                if (jSONObject2.has("version")) {
                    return jSONObject2.getInt("version");
                }
                return -1;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
