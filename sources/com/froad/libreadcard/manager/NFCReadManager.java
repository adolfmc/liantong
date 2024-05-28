package com.froad.libreadcard.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Build;
import com.eidlink.idocr.sdk.EidLinkSE;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;
import com.eidlink.idocr.sdk.bean.EidlinkResult;
import com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack;
import com.eidlink.idocr.sdk.listener.OnEidInitListener;
import com.eidlink.idocr.sdk.listener.OnGetResultListener;
import com.froad.eid.unify.bean.IDSEConfig;
import com.froad.eid.unify.manager.GlobalBeanManager;
import com.froad.eid.unify.utils.StringUtil;
import com.froad.froadeid.base.libs.core.ReadInfoType;
import com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack;
import com.froad.libreadcard.bankcard.NfcTask;
import com.froad.libreadcard.constants.ReadCardStatus;
import com.froad.libreadcard.constants.ReadCardType;
import com.p189cn.froad.clouddecodingsdk.core.CommoneIDSEFactory;
import com.p189cn.froad.clouddecodingsdk.jni.FFTEIDReadCardJNI;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.FCharUtils;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.IOException;
import java.lang.ref.WeakReference;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NFCReadManager {
    private static final int READER_FLAGS = 15;
    private static final String TAG = "NFCReadManager";
    private static volatile NFCReadManager nfcReadManager;
    private WeakReference<Activity> activityWeakRef;
    private String birth;
    private String cardnum;
    private EidLinkSE eid;
    private String expiry;
    private IsoDep isoDep;
    public NfcAdapter nfcAdapter;
    private ReadCardInfoCallBack readCardInfoCallBack;
    private int sysVersion;
    private ReadCardType readType = ReadCardType.ID_CARD;
    private boolean isImg = true;
    private boolean hasOnNewIntent = false;
    private final String eIDInitResStartStr = "eID初始化";
    private boolean needReadCard = false;
    private OnGetResultListener mListener = new OnGetResultListener() { // from class: com.froad.libreadcard.manager.NFCReadManager.1
        @Override // com.eidlink.idocr.sdk.listener.OnGetResultListener
        public void onFailed(int i, String str) {
            String str2 = "" + i;
            if (StringUtil.isNotEmpty(str) && str.startsWith("FFT_")) {
                str2 = FCharUtils.int2HexStr(i);
                str = str.substring(4);
            }
            TMKeyLog.m16309e(NFCReadManager.TAG, "onFailed>>>readType : " + NFCReadManager.this.readType + " 读卡失败 错误码：" + str2);
            if (NFCReadManager.this.readCardInfoCallBack != null) {
                NFCReadManager.this.readCardInfoCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.REQID, str2, str);
            } else {
                NFCReadManager.this.hasOnNewIntent = false;
                TMKeyLog.m16309e(NFCReadManager.TAG, "onFailed>>>readType : " + NFCReadManager.this.readType + " readCardInfoCallBack==null ");
            }
            GlobalBeanManager.getInstance().setReading(false);
        }

        @Override // com.eidlink.idocr.sdk.listener.OnGetResultListener
        public void onStart() {
            TMKeyLog.m16310d(NFCReadManager.TAG, "onStart");
            if (NFCReadManager.this.readCardInfoCallBack != null) {
                NFCReadManager.this.readCardInfoCallBack.readResult(ReadCardStatus.START, ReadInfoType.REQID, "0", "开始读取证件");
                return;
            }
            TMKeyLog.m16309e(NFCReadManager.TAG, "onStart>>>readType : " + NFCReadManager.this.readType + " readCardInfoCallBack==null ");
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x008b, code lost:
            if ((r1 instanceof org.json.JSONObject) == false) goto L9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x008d, code lost:
            r1 = r1.toString();
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0092, code lost:
            r1 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:7:0x0052, code lost:
            if ((r1 instanceof org.json.JSONObject) == false) goto L9;
         */
        @Override // com.eidlink.idocr.sdk.listener.OnGetResultListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onSuccess(com.eidlink.idocr.sdk.bean.EidlinkResult r6) {
            /*
                r5 = this;
                java.lang.String r0 = r6.getReqId()
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                java.lang.String r2 = "onSuccess>>>readType : "
                r1.append(r2)
                com.froad.libreadcard.manager.NFCReadManager r2 = com.froad.libreadcard.manager.NFCReadManager.this
                com.froad.libreadcard.constants.ReadCardType r2 = com.froad.libreadcard.manager.NFCReadManager.access$000(r2)
                r1.append(r2)
                java.lang.String r2 = "   读卡成功    reqid    "
                r1.append(r2)
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                java.lang.String r2 = "NFCReadManager"
                com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog.m16309e(r2, r1)
                com.froad.libreadcard.manager.NFCReadManager r1 = com.froad.libreadcard.manager.NFCReadManager.this
                r2 = 0
                com.froad.libreadcard.manager.NFCReadManager.access$102(r1, r2)
                com.froad.froadeid.base.libs.core.DataJsonObject r1 = new com.froad.froadeid.base.libs.core.DataJsonObject
                r1.<init>()
                boolean r3 = com.froad.eid.unify.utils.StringUtil.isNotEmpty(r0)
                if (r3 == 0) goto L55
                com.froad.libreadcard.manager.NFCReadManager r6 = com.froad.libreadcard.manager.NFCReadManager.this
                com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack r6 = com.froad.libreadcard.manager.NFCReadManager.access$200(r6)
                if (r6 == 0) goto L9d
                java.lang.String r6 = "reqid"
                r1.put(r6, r0)
                com.froad.libreadcard.manager.NFCReadManager r6 = com.froad.libreadcard.manager.NFCReadManager.this
                com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack r6 = com.froad.libreadcard.manager.NFCReadManager.access$200(r6)
                com.froad.libreadcard.constants.ReadCardStatus r0 = com.froad.libreadcard.constants.ReadCardStatus.SUCCESS
                com.froad.froadeid.base.libs.core.ReadInfoType r3 = com.froad.froadeid.base.libs.core.ReadInfoType.REQID
                boolean r4 = r1 instanceof org.json.JSONObject
                if (r4 != 0) goto L92
                goto L8d
            L55:
                com.froad.libreadcard.manager.NFCReadManager r0 = com.froad.libreadcard.manager.NFCReadManager.this
                com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack r0 = com.froad.libreadcard.manager.NFCReadManager.access$200(r0)
                if (r0 == 0) goto L9d
                java.lang.String r6 = r6.data
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r3 = "读卡成功，加密信息>>>onSuccess>>>encData.length:"
                r0.append(r3)
                int r3 = r6.length()
                r0.append(r3)
                java.lang.String r0 = r0.toString()
                java.lang.String r3 = "NFCReadManager"
                com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog.m16310d(r3, r0)
                java.lang.String r0 = "encIdInfo"
                r1.put(r0, r6)
                com.froad.libreadcard.manager.NFCReadManager r6 = com.froad.libreadcard.manager.NFCReadManager.this
                com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack r6 = com.froad.libreadcard.manager.NFCReadManager.access$200(r6)
                com.froad.libreadcard.constants.ReadCardStatus r0 = com.froad.libreadcard.constants.ReadCardStatus.SUCCESS
                com.froad.froadeid.base.libs.core.ReadInfoType r3 = com.froad.froadeid.base.libs.core.ReadInfoType.ENCIDINFO
                boolean r4 = r1 instanceof org.json.JSONObject
                if (r4 != 0) goto L92
            L8d:
                java.lang.String r1 = r1.toString()
                goto L98
            L92:
                org.json.JSONObject r1 = (org.json.JSONObject) r1
                java.lang.String r1 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r1)
            L98:
                java.lang.String r4 = "0"
                r6.readResult(r0, r3, r4, r1)
            L9d:
                com.froad.eid.unify.manager.GlobalBeanManager r6 = com.froad.eid.unify.manager.GlobalBeanManager.getInstance()
                r6.setReading(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.froad.libreadcard.manager.NFCReadManager.C43411.onSuccess(com.eidlink.idocr.sdk.bean.EidlinkResult):void");
        }
    };
    private MyReaderCallback mReaderCallback = new MyReaderCallback();
    public EidLinkReadCardCallBack eidCallBack = new EidLinkReadCardCallBack() { // from class: com.froad.libreadcard.manager.NFCReadManager.5
        @Override // com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack
        public byte[] transceiveTypeA(byte[] bArr) {
            TMKeyLog.m16310d(NFCReadManager.TAG, "transceiveTypeA>>>bytes:" + NFCReadManager.printHexString(bArr));
            if (NFCReadManager.this.readType == ReadCardType.E_CARD_NFC && NFCReadManager.this.isoDep != null && NFCReadManager.this.isoDep.isConnected()) {
                try {
                    byte[] transceive = NFCReadManager.this.isoDep.transceive(bArr);
                    TMKeyLog.m16310d(NFCReadManager.TAG, "transceiveTypeA>>>transceive:" + NFCReadManager.printHexString(transceive));
                    return transceive;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }

        @Override // com.eidlink.idocr.sdk.listener.EidLinkReadCardCallBack
        public byte[] transceiveTypeB(byte[] bArr) {
            return null;
        }
    };

    /* compiled from: Proguard */
    @TargetApi(19)
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class MyReaderCallback implements NfcAdapter.ReaderCallback {
        public MyReaderCallback() {
        }

        @Override // android.nfc.NfcAdapter.ReaderCallback
        public void onTagDiscovered(Tag tag) {
            ReadCardInfoCallBack readCardInfoCallBack;
            ReadCardStatus readCardStatus;
            ReadInfoType readInfoType;
            String str;
            String str2;
            TMKeyLog.m16307i(NFCReadManager.TAG, "发现目标了>>>Tag:" + tag + ">>>readType:" + NFCReadManager.this.readType + ">>>needReadCard:" + NFCReadManager.this.needReadCard);
            if (NFCReadManager.this.needReadCard) {
                try {
                } catch (IOException e) {
                    e.printStackTrace();
                    readCardInfoCallBack = NFCReadManager.this.readCardInfoCallBack;
                    readCardStatus = ReadCardStatus.FAILED;
                    readInfoType = ReadInfoType.REQID;
                    str = "F3F001";
                    str2 = "NFC初始化异常";
                }
                if (NFCReadManager.this.readType == ReadCardType.E_CARD_NFC) {
                    NFCReadManager.this.isoDep = IsoDep.get(tag);
                    if (NFCReadManager.this.isoDep != null) {
                        TMKeyLog.m16310d(NFCReadManager.TAG, "onTagDiscovered>>>isoDep is not null");
                        if (!NFCReadManager.this.isoDep.isConnected()) {
                            NFCReadManager.this.isoDep.connect();
                        }
                        TMKeyLog.m16310d(NFCReadManager.TAG, "onTagDiscovered>>>isoDep.connect()");
                        EidLinkSE eidLinkSE = NFCReadManager.this.eid;
                        NFCReadManager nFCReadManager = NFCReadManager.this;
                        eidLinkSE.readIDCard(2, nFCReadManager.eidCallBack, nFCReadManager.mListener);
                        return;
                    }
                } else if (NFCReadManager.this.readType == ReadCardType.ID_CARD) {
                    NFCReadManager.this.eid.readIDCard(tag, NFCReadManager.this.mListener);
                    return;
                } else if (NFCReadManager.this.readType == ReadCardType.TRAVEL_CARD) {
                    TMKeyLog.m16310d(NFCReadManager.TAG, "NFCreadCard() called with: cardnum = [" + NFCReadManager.this.cardnum + "] birth = [" + NFCReadManager.this.birth + "] expiry = [" + NFCReadManager.this.expiry + "] isImg = [" + NFCReadManager.this.isImg + "]");
                    NFCReadManager.this.eid.readTravel(tag, NFCReadManager.this.cardnum, NFCReadManager.this.birth, NFCReadManager.this.expiry, NFCReadManager.this.isImg, NFCReadManager.this.mListener);
                    return;
                } else if (NFCReadManager.this.readType != ReadCardType.BANK_CARD) {
                    if (NFCReadManager.this.readCardInfoCallBack == null) {
                        TMKeyLog.m16309e(NFCReadManager.TAG, "readType : " + NFCReadManager.this.readType + " readCardInfoCallBack==null ");
                        return;
                    }
                    readCardInfoCallBack = NFCReadManager.this.readCardInfoCallBack;
                    readCardStatus = ReadCardStatus.FAILED;
                    readInfoType = ReadInfoType.REQID;
                    str = "F30007";
                    str2 = "证件类型设置错误";
                    readCardInfoCallBack.readResult(readCardStatus, readInfoType, str, str2);
                } else {
                    NFCReadManager.this.isoDep = IsoDep.get(tag);
                    if (NFCReadManager.this.isoDep != null) {
                        TMKeyLog.m16310d(NFCReadManager.TAG, "onTagDiscovered>>>isoDep is not null");
                        if (!NFCReadManager.this.isoDep.isConnected()) {
                            NFCReadManager.this.isoDep.connect();
                        }
                        TMKeyLog.m16310d(NFCReadManager.TAG, "onTagDiscovered>>>isoDep.connect()");
                        new NfcTask(NFCReadManager.this.readType, NFCReadManager.this.readCardInfoCallBack).execute(NFCReadManager.this.isoDep);
                        return;
                    }
                }
                readCardInfoCallBack = NFCReadManager.this.readCardInfoCallBack;
                readCardStatus = ReadCardStatus.FAILED;
                readInfoType = ReadInfoType.REQID;
                str = "F3F001";
                str2 = "NFC初始化失败";
                readCardInfoCallBack.readResult(readCardStatus, readInfoType, str, str2);
            }
        }
    }

    private NFCReadManager() {
    }

    private void disableReaderMode(ReadCardType readCardType) {
        TMKeyLog.m16310d(TAG, "disableReaderMode");
        if (this.nfcAdapter != null) {
            disableReaderMode();
        }
    }

    private void enableReaderMode(ReadCardType readCardType) {
        TMKeyLog.m16310d(TAG, "enableReaderMode>>>cardType:" + readCardType);
        if (this.nfcAdapter == null) {
            TMKeyLog.m16310d(TAG, "nfcAdapter is null");
            return;
        }
        TMKeyLog.m16310d(TAG, "enableReaderMode>>>isFFTEnable:" + CommoneIDSEFactory.isFFTEnable());
        if (CommoneIDSEFactory.isFFTEnable() && (readCardType == ReadCardType.ID_CARD || readCardType == ReadCardType.TRAVEL_CARD || readCardType == ReadCardType.E_CARD_NFC)) {
            FFTEIDReadCardJNI.getJNI().queryReadEvn(readCardType, new OnGetResultListener() { // from class: com.froad.libreadcard.manager.NFCReadManager.4
                @Override // com.eidlink.idocr.sdk.listener.OnGetResultListener
                public void onFailed(int i, String str) {
                    String str2 = "" + i;
                    if (StringUtil.isNotEmpty(str) && str.startsWith("FFT_")) {
                        str2 = FCharUtils.int2HexStr(i);
                        str = str.substring(4);
                    }
                    TMKeyLog.m16310d(NFCReadManager.TAG, "onFailed() called with: errCode = [" + str2 + "], errMsg = [" + str + "]");
                    NFCReadManager.this.enableReaderMode();
                }

                @Override // com.eidlink.idocr.sdk.listener.OnGetResultListener
                public void onSuccess(EidlinkResult eidlinkResult) {
                    TMKeyLog.m16310d(NFCReadManager.TAG, "onSuccess() called with: result = [" + eidlinkResult + "]");
                    NFCReadManager.this.enableReaderMode();
                }
            });
        } else {
            enableReaderMode();
        }
    }

    public static NFCReadManager getInstance() {
        if (nfcReadManager == null) {
            synchronized (NFCReadManager.class) {
                if (nfcReadManager == null) {
                    nfcReadManager = new NFCReadManager();
                }
            }
        }
        return nfcReadManager;
    }

    public static String printHexString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase();
        }
        return str;
    }

    public void disableReader() {
        TMKeyLog.m16310d(TAG, "disableReader");
        disableReaderMode(this.readType);
    }

    @TargetApi(19)
    public void disableReaderMode() {
        WeakReference<Activity> weakReference;
        TMKeyLog.m16310d(TAG, "disableReaderMode>>>sysVersion:" + this.sysVersion);
        NfcAdapter nfcAdapter = this.nfcAdapter;
        if (nfcAdapter == null || (weakReference = this.activityWeakRef) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            nfcAdapter.disableReaderMode(weakReference.get());
        } else {
            nfcAdapter.disableForegroundDispatch(weakReference.get());
        }
    }

    @TargetApi(19)
    public void enableReaderMode() {
        TMKeyLog.m16310d(TAG, "enableReaderMode>>>sysVersion:" + this.sysVersion);
        Activity activity = this.activityWeakRef.get();
        int i = Build.VERSION.SDK_INT;
        PendingIntent activity2 = i >= 31 ? PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()).addFlags(536870912), 67108864) : PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()).addFlags(536870912), 0);
        IntentFilter[] intentFilterArr = {new IntentFilter("android.nfc.action.TAG_DISCOVERED"), new IntentFilter("android.nfc.action.TECH_DISCOVERED")};
        String[][] strArr = {new String[]{IsoDep.class.getName()}, new String[]{NfcA.class.getName()}, new String[]{NfcB.class.getName()}, new String[]{NfcF.class.getName()}, new String[]{NfcV.class.getName()}};
        if (this.nfcAdapter != null) {
            TMKeyLog.m16310d(TAG, "enableReaderMode:activityWeakRef.get() " + activity);
            if (i >= 19) {
                this.nfcAdapter.enableReaderMode(activity, this.mReaderCallback, 15, null);
            } else {
                this.nfcAdapter.enableForegroundDispatch(activity, activity2, intentFilterArr, strArr);
            }
        }
    }

    public ReadCardType getReadType() {
        return this.readType;
    }

    public NFCReadManager initEid() {
        IDSEConfig iDSEConfig;
        try {
            iDSEConfig = GlobalBeanManager.getInstance().getIDSEConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.activityWeakRef.get() == null) {
            return this;
        }
        EidlinkInitParams eidFFTParams = iDSEConfig.toEidFFTParams(this.activityWeakRef.get());
        FFTEIDReadCardJNI.getJNI().setDecodeMode(true, GlobalBeanManager.getInstance().isReadPhoto(), false);
        this.eid = CommoneIDSEFactory.geteIDSEReadCard(null, eidFFTParams, new OnEidInitListener() { // from class: com.froad.libreadcard.manager.NFCReadManager.2
            @Override // com.eidlink.idocr.sdk.listener.OnEidInitListener
            public void onFailed(int i) {
                String str = "" + i;
                if (i > 0) {
                    str = FCharUtils.int2HexStr(i);
                }
                TMKeyLog.m16310d(NFCReadManager.TAG, "SDK onFailed() called with: errCode = [" + str + "]");
                if (NFCReadManager.this.readCardInfoCallBack != null) {
                    NFCReadManager.this.readCardInfoCallBack.readResult(ReadCardStatus.FAILED, ReadInfoType.REQID, "F3F001", "eID初始化失败，请检查参数是否正确");
                }
            }

            @Override // com.eidlink.idocr.sdk.listener.OnEidInitListener
            public void onSuccess() {
                TMKeyLog.m16310d(NFCReadManager.TAG, "geteIDSEReadCard SDK onSuccess: ");
                if (NFCReadManager.this.readCardInfoCallBack != null) {
                    NFCReadManager.this.readCardInfoCallBack.readResult(ReadCardStatus.SUCCESS, ReadInfoType.REQID, "000000", "eID初始化成功");
                }
            }
        });
        return this;
    }

    public NFCReadManager initNfc(Context context) {
        this.sysVersion = Build.VERSION.SDK_INT;
        TMKeyLog.m16310d(TAG, "onCreate>>>sysVersion:" + this.sysVersion);
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        return this;
    }

    public void interceptNfc() {
        this.needReadCard = false;
        enableReaderMode();
    }

    public boolean isEnableNFC() {
        NfcAdapter nfcAdapter = this.nfcAdapter;
        return nfcAdapter != null && nfcAdapter.isEnabled();
    }

    public boolean isSupportNFC() {
        return this.nfcAdapter != null;
    }

    public void onNewIntent(Intent intent) {
        ReadCardInfoCallBack readCardInfoCallBack;
        ReadCardStatus readCardStatus;
        ReadInfoType readInfoType;
        String str;
        String str2;
        TMKeyLog.m16310d(TAG, "NFCreadCard: readType " + this.readType + ">>>eid:" + this.eid + ">>>needReadCard:" + this.needReadCard);
        if (this.needReadCard) {
            if (intent == null) {
                this.mListener.onFailed(15929347, "FFT_Intent对象为空");
                return;
            }
            ReadCardType readCardType = this.readType;
            ReadCardType readCardType2 = ReadCardType.BANK_CARD;
            if (readCardType == readCardType2 || this.eid != null) {
                this.hasOnNewIntent = true;
                Tag tag = (Tag) intent.getParcelableExtra("android.nfc.extra.TAG");
                ReadCardType readCardType3 = this.readType;
                try {
                    if (readCardType3 == ReadCardType.E_CARD_NFC) {
                        IsoDep isoDep = IsoDep.get(tag);
                        this.isoDep = isoDep;
                        if (isoDep != null) {
                            TMKeyLog.m16310d(TAG, "onTagDiscovered>>>isoDep is not null");
                            if (!this.isoDep.isConnected()) {
                                this.isoDep.connect();
                            }
                            TMKeyLog.m16310d(TAG, "onTagDiscovered>>>isoDep.connect()");
                            this.eid.readIDCard(2, this.eidCallBack, this.mListener);
                            return;
                        }
                    } else if (readCardType3 == ReadCardType.TRAVEL_CARD) {
                        TMKeyLog.m16310d(TAG, "NFCreadCard() called with: cardnum = [" + this.cardnum + "] birth = [" + this.birth + "] expiry = [" + this.expiry + "] isImg = [" + this.isImg + "]");
                        this.eid.readTravel(tag, this.cardnum, this.birth, this.expiry, this.isImg, this.mListener);
                        return;
                    } else if (readCardType3 != readCardType2) {
                        if (readCardType3 == ReadCardType.ID_CARD) {
                            this.eid.readIDCard(tag, this.mListener);
                            return;
                        }
                        return;
                    } else {
                        IsoDep isoDep2 = IsoDep.get(tag);
                        this.isoDep = isoDep2;
                        if (isoDep2 != null) {
                            TMKeyLog.m16310d(TAG, "onTagDiscovered>>>isoDep is not null");
                            if (!this.isoDep.isConnected()) {
                                this.isoDep.connect();
                            }
                            TMKeyLog.m16310d(TAG, "onTagDiscovered>>>isoDep.connect()");
                            new NfcTask(this.readType, this.readCardInfoCallBack).execute(this.isoDep);
                            return;
                        }
                    }
                    readCardInfoCallBack = this.readCardInfoCallBack;
                    readCardStatus = ReadCardStatus.FAILED;
                    readInfoType = ReadInfoType.REQID;
                    str = "F3F001";
                    str2 = "NFC初始化失败";
                } catch (IOException e) {
                    e.printStackTrace();
                    readCardInfoCallBack = this.readCardInfoCallBack;
                    readCardStatus = ReadCardStatus.FAILED;
                    readInfoType = ReadInfoType.REQID;
                    str = "F3F001";
                    str2 = "NFC初始化异常";
                }
                readCardInfoCallBack.readResult(readCardStatus, readInfoType, str, str2);
            }
        }
    }

    public NFCReadManager setActivity(Activity activity) {
        TMKeyLog.m16310d(TAG, "setActivity() called with: activity = [" + activity + "]");
        this.activityWeakRef = new WeakReference<>(activity);
        return this;
    }

    public NFCReadManager setCallBack(final ReadCardInfoCallBack readCardInfoCallBack) {
        this.readCardInfoCallBack = new ReadCardInfoCallBack() { // from class: com.froad.libreadcard.manager.NFCReadManager.3
            @Override // com.froad.froadeid.base.libs.sdk.ReadCardInfoCallBack
            public void readResult(final ReadCardStatus readCardStatus, final ReadInfoType readInfoType, final String str, final String str2) {
                if (StringUtil.isNotEmpty(str2) && !str2.startsWith("eID初始化")) {
                    NFCReadManager.this.hasOnNewIntent = false;
                }
                if (NFCReadManager.this.activityWeakRef == null || NFCReadManager.this.activityWeakRef.get() == null) {
                    return;
                }
                ((Activity) NFCReadManager.this.activityWeakRef.get()).runOnUiThread(new Runnable() { // from class: com.froad.libreadcard.manager.NFCReadManager.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ReadCardInfoCallBack readCardInfoCallBack2 = readCardInfoCallBack;
                        if (readCardInfoCallBack2 != null) {
                            readCardInfoCallBack2.readResult(readCardStatus, readInfoType, str, str2);
                        }
                    }
                });
            }
        };
        return this;
    }

    public NFCReadManager setCardType(ReadCardType readCardType) {
        this.readType = readCardType;
        this.needReadCard = true;
        TMKeyLog.m16310d(TAG, "setCardType>>>hasOnNewIntent:" + this.hasOnNewIntent);
        if (this.hasOnNewIntent) {
            return this;
        }
        enableReaderMode(readCardType);
        return this;
    }

    public NFCReadManager setTravelInfo(String str, String str2, String str3) {
        this.cardnum = str;
        this.birth = str2;
        this.expiry = str3;
        return this;
    }

    public void stopInterceptNfc() {
        this.needReadCard = true;
        disableReaderMode();
    }
}
