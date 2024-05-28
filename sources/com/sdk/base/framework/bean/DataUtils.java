package com.sdk.base.framework.bean;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DataUtils {
    public Access_process access_process;
    public String apikey;
    public Err_info err_info;
    public int result;
    public String ret_code;
    public String sdk_v;
    public String seq;
    public int setTime;
    public int type;

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Access_process {
        public int ifProtal;
        public Step step1;
        public Step step2;
        public Step step3;
        public Step step4;

        public int getIfProtal() {
            return this.ifProtal;
        }

        public Step getStep1() {
            return this.step1;
        }

        public Step getStep2() {
            return this.step2;
        }

        public Step getStep3() {
            return this.step3;
        }

        public Step getStep4() {
            return this.step4;
        }

        public void setIfProtal(int i) {
            this.ifProtal = i;
        }

        public void setStep1(Step step) {
            this.step1 = step;
        }

        public void setStep2(Step step) {
            this.step2 = step;
        }

        public void setStep3(Step step) {
            this.step3 = step;
        }

        public void setStep4(Step step) {
            this.step4 = step;
        }

        public String toString() {
            return "{ifProtal:" + this.ifProtal + ", step1:" + this.step1 + ", step2:" + this.step2 + ", step3:" + this.step3 + ", step4:" + this.step4 + '}';
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Err_info {
        public String err_code;
        public String err_msg;

        public String getErr_code() {
            return this.err_code;
        }

        public String getErr_msg() {
            return this.err_msg;
        }

        public void setErr_code(String str) {
            this.err_code = str;
        }

        public void setErr_msg(String str) {
            this.err_msg = str;
        }

        public String toString() {
            return "{err_code='" + this.err_code + "', err_msg='" + this.err_msg + "'}";
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Step {
        public long endTime;
        public long startTime;
        public String url;

        public long getEndTime() {
            return this.endTime;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public String getUrl() {
            return this.url;
        }

        public void setEndTime(long j) {
            this.endTime = j;
        }

        public void setStartTime(long j) {
            this.startTime = j;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String toString() {
            return "{url':" + this.url + "', startTime:" + this.startTime + ", endTime:" + this.endTime + '}';
        }
    }

    public Access_process getAccess_process() {
        return this.access_process;
    }

    public String getApikey() {
        return this.apikey;
    }

    public Err_info getErr_info() {
        return this.err_info;
    }

    public int getResult() {
        return this.result;
    }

    public String getRet_code() {
        return this.ret_code;
    }

    public String getSdk_v() {
        return this.sdk_v;
    }

    public String getSeq() {
        return this.seq;
    }

    public int getSetTime() {
        return this.setTime;
    }

    public int getType() {
        return this.type;
    }

    public void setAccess_process(Access_process access_process) {
        this.access_process = access_process;
    }

    public void setApikey(String str) {
        this.apikey = str;
    }

    public void setErr_info(Err_info err_info) {
        this.err_info = err_info;
    }

    public void setResult(int i) {
        this.result = i;
    }

    public void setRet_code(String str) {
        this.ret_code = str;
    }

    public void setSdk_v(String str) {
        this.sdk_v = str;
    }

    public void setSeq(String str) {
        this.seq = str;
    }

    public void setSetTime(int i) {
        this.setTime = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "{result=" + this.result + ", type=" + this.type + ", ret_code='" + this.ret_code + "', apikey='" + this.apikey + "', seq='" + this.seq + "', setTime=" + this.setTime + ", err_info=" + this.err_info + ", sdk_v='" + this.sdk_v + "', access_process=" + this.access_process + '}';
    }
}
