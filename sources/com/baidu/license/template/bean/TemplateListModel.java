package com.baidu.license.template.bean;

import com.baidu.license.sticker.BaseModel;
import com.baidu.license.sticker.BaseResult;
import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TemplateListModel extends BaseModel implements Serializable {
    public TemplateResult result;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TemplateResult extends BaseResult implements Serializable {
        public List<TemplateData> templateList;

        public List<TemplateData> getTemplateList() {
            return this.templateList;
        }

        public void setTemplateList(List<TemplateData> list) {
            this.templateList = list;
        }
    }

    public TemplateResult getResult() {
        return this.result;
    }

    public void setResult(TemplateResult templateResult) {
        this.result = templateResult;
    }
}
