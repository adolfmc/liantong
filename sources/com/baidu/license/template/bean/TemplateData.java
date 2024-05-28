package com.baidu.license.template.bean;

import com.baidu.license.INotProguard;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TemplateData implements INotProguard, Serializable {
    public static final int MODE_HORIZONTAL = 1;
    public static final int MODE_VERTICAL = 0;
    public static final int TYPE_DAPIAN = 0;
    public static final int TYPE_KADIAN = 1;
    public String cover;
    public int coverHeight;
    public int coverWidth;
    public String file;
    public String fileSign;
    public String icon;

    /* renamed from: id */
    public String f5000id;
    public int mode;
    public String name;
    public int number;
    public String playUrl;
    public String sign;
    public int status;
    public int type;

    public String getTypeName() {
        return this.type == 1 ? "卡点" : "大片";
    }
}
