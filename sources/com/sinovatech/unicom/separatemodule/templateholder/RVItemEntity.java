package com.sinovatech.unicom.separatemodule.templateholder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RVItemEntity {
    public boolean isGengxinXiaoheitiao;
    public boolean isLibaoChangeState;
    public Object realData;
    public boolean refresh;
    public int spanCount;
    public String templateName;
    public int xiaoheiTiaoHight;

    public RVItemEntity(String str, Object obj) {
        this.templateName = str;
        this.realData = obj;
    }

    public RVItemEntity(String str, Object obj, int i) {
        this.templateName = str;
        this.realData = obj;
        this.spanCount = i;
    }
}
