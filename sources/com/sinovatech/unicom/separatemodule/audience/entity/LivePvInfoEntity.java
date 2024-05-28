package com.sinovatech.unicom.separatemodule.audience.entity;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LivePvInfoEntity {
    private InfoEntity data;
    private String message;
    private String statusCode;

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class InfoEntity {
        private String prevueTime;

        public String getPrevueTime() {
            return LivePvInfoEntity.switchdate(this.prevueTime) + "  开播";
        }

        public void setPrevueTime(String str) {
            this.prevueTime = str;
        }
    }

    public InfoEntity getData() {
        return this.data;
    }

    public void setData(InfoEntity infoEntity) {
        this.data = infoEntity;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public static String switchdate(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            return new SimpleDateFormat("M月dd日 H:mm").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
            return str;
        }
    }
}
