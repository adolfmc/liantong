package com.froad.libreadcard.bankcard;

import android.nfc.tech.IsoDep;
import android.os.AsyncTask;
import com.froad.eid.unify.manager.GlobalBeanManager;
import com.froad.froadeid.base.libs.core.ReadInfoType;
import com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack;
import com.froad.libreadcard.constants.ReadCardStatus;
import com.froad.libreadcard.constants.ReadCardType;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.IOException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NfcTask extends AsyncTask<IsoDep, String, String[]> {
    private static final String TAG = "NfcTask";
    private ReadCardInfoCallBack cardCallBack;
    private ReadCardType readCardType;

    public NfcTask(ReadCardType readCardType, ReadCardInfoCallBack readCardInfoCallBack) {
        this.readCardType = readCardType;
        this.cardCallBack = readCardInfoCallBack;
    }

    @Override // android.os.AsyncTask
    public String[] doInBackground(IsoDep... isoDepArr) {
        byte[] pbocCmdReadRecord;
        IsoDep isoDep = isoDepArr[0];
        try {
            if (!isoDep.isConnected()) {
                isoDep.connect();
            }
            PBOCUtil pBOCUtil = new PBOCUtil(isoDep);
            if (pBOCUtil.pbocAppSelect() >= 0 && (pbocCmdReadRecord = pBOCUtil.pbocCmdReadRecord(1, (byte) 8, true)) != null) {
                return pBOCUtil.parseBankCardInfo(pbocCmdReadRecord);
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(String... strArr) {
        TMKeyLog.m16310d(TAG, "onPostExecute");
        if (strArr == null) {
            GlobalBeanManager.getInstance().setReading(false);
            this.cardCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.BANKCARDINFO, "F30001", "银行卡读取失败");
            return;
        }
        String[] strArr2 = (String[]) strArr.clone();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cardNum", strArr2[0]);
            jSONObject.put("ValidityPeriod", strArr2[1]);
            jSONObject.put("bankName", strArr2[2]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalBeanManager.getInstance().setReading(false);
        this.cardCallBack.readResult(ReadCardStatus.SUCCESS, ReadInfoType.BANKCARDINFO, "", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }

    @Override // android.os.AsyncTask
    public void onPreExecute() {
        this.cardCallBack.readResult(ReadCardStatus.START, ReadInfoType.BANKCARDINFO, "", "开始读取");
    }

    @Override // android.os.AsyncTask
    public void onProgressUpdate(String... strArr) {
        TMKeyLog.m16310d(TAG, "onProgressUpdate");
        if (strArr == null) {
            this.cardCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.BANKCARDINFO, "F30002", "银行卡读取失败");
            GlobalBeanManager.getInstance().setReading(false);
            return;
        }
        String[] strArr2 = (String[]) strArr.clone();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("cardNum", strArr2[0]);
            jSONObject.put("ValidityPeriod", strArr2[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalBeanManager.getInstance().setReading(false);
        this.cardCallBack.readResult(ReadCardStatus.SUCCESS, ReadInfoType.BANKCARDINFO, "", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }
}
