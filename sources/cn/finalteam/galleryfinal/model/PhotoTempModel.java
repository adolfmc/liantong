package cn.finalteam.galleryfinal.model;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PhotoTempModel implements Serializable {
    private int orientation;
    private String sourcePath;

    public PhotoTempModel(String str) {
        this.sourcePath = str;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public String getSourcePath() {
        return this.sourcePath;
    }

    public void setSourcePath(String str) {
        this.sourcePath = str;
    }
}
