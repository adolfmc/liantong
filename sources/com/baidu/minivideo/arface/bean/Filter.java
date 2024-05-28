package com.baidu.minivideo.arface.bean;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Filter extends BaseBeautyItem {
    public static final int DATA_TYPE_DU_SINGLE = 1;
    private int mDataType;
    private File mFile;
    private float mLevel;
    private String mParam;

    public void setDataType(int i) {
        this.mDataType = i;
    }

    public int getDataType() {
        return this.mDataType;
    }

    public String getPath() {
        File file = this.mFile;
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;
    }

    public void setFile(File file) {
        this.mFile = file;
    }

    public File getFile() {
        return this.mFile;
    }

    public void setParam(String str) {
        this.mParam = str;
    }

    public float getLevel() {
        return this.mLevel;
    }

    public void setLevel(float f) {
        this.mLevel = f;
    }

    public String getParam() {
        return this.mParam;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("param:");
        sb.append(this.mParam);
        sb.append(", type:");
        sb.append(this.mDataType);
        sb.append(", level:");
        sb.append(this.mLevel);
        sb.append(", path:");
        File file = this.mFile;
        sb.append(file == null ? "null" : file.getAbsoluteFile());
        return sb.toString();
    }
}
