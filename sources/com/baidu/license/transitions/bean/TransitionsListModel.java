package com.baidu.license.transitions.bean;

import com.baidu.license.sticker.BaseModel;
import com.baidu.license.sticker.BaseResult;
import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitionsListModel extends BaseModel implements Serializable {
    public TransitionResult result;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TransitionResult extends BaseResult implements Serializable {
        public List<TransitionsData> transitionList;

        public List<TransitionsData> getTransitionsDataList() {
            return this.transitionList;
        }

        public void setTransitionsDataList(List<TransitionsData> list) {
            this.transitionList = list;
        }
    }

    public TransitionResult getResult() {
        return this.result;
    }

    public void setResult(TransitionResult transitionResult) {
        this.result = transitionResult;
    }
}
