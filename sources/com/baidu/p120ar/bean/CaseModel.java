package com.baidu.p120ar.bean;

import android.text.TextUtils;
import com.baidu.p120ar.ARType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.CaseModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CaseModel {
    public String mCaseId;
    public String mCasePath;
    public ARType mCaseType;

    public CaseModel(ARType aRType, String str, String str2) {
        this.mCaseType = aRType;
        this.mCasePath = str;
        this.mCaseId = str2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (super.equals(obj)) {
            return true;
        }
        if (obj instanceof CaseModel) {
            return (!TextUtils.isEmpty(this.mCasePath) && this.mCasePath.equals(((CaseModel) obj).mCasePath)) || (TextUtils.isEmpty(this.mCasePath) && !TextUtils.isEmpty(this.mCaseId) && this.mCaseId.equals(((CaseModel) obj).mCaseId));
        }
        return false;
    }
}
