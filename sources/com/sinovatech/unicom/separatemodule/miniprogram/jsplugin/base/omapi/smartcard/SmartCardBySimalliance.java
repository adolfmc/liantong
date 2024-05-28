package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Hex;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Utils;
import java.io.IOException;
import java.util.NoSuchElementException;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.Session;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
final class SmartCardBySimalliance extends BaseSmartCard implements SEService.CallBack {
    private Channel mChannel;
    private SEService mSEService;
    private Session mSession;
    private boolean mServiceIsConnection = false;
    private Object mLock = new Object();
    private final String TAG = SmartCardBySimalliance.class.getSimpleName();

    private void bindService() throws InterruptedException {
        if (this.mSEService == null) {
            new SEService(Utils.getApp().getApplicationContext(), this);
            LogUtil.m7989d(this.TAG, "start bind SEService");
            if (this.mServiceIsConnection) {
                return;
            }
            synchronized (this.mLock) {
                if (!this.mServiceIsConnection) {
                    LogUtil.m7990d("thread is waiting");
                    this.mLock.wait();
                }
            }
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.BaseSmartCard
    public CardResult execute(String str) {
        try {
            bindService();
            return executeApduCmd(str);
        } catch (InterruptedException e) {
            LogUtil.m7987e(this.TAG, e.getMessage());
            return operFail("执行APDU指令失败，原因:" + e.getMessage());
        } catch (Exception e2) {
            LogUtil.m7987e(this.TAG, e2.getMessage());
            return operFail("执行APDU指令失败，异常原因:" + e2.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.BaseSmartCard
    public CardResult openChannel(String str) {
        try {
            bindService();
            String str2 = this.TAG;
            LogUtil.m7989d(str2, "Command APDU:" + str);
            closeChannelAndSession();
            Object[] openCurrentAvailableChannel = openCurrentAvailableChannel(str);
            String str3 = (String) openCurrentAvailableChannel[0];
            String str4 = (String) openCurrentAvailableChannel[1];
            String str5 = this.TAG;
            LogUtil.m7989d(str5, "resultCode :" + str3 + " resultDesc:" + str4);
            if (str3.equals("0000")) {
                String bytesToHexString = Hex.bytesToHexString(this.mChannel.getSelectResponse());
                String str6 = this.TAG;
                LogUtil.m7989d(str6, "Response APDU：" + bytesToHexString);
                return new CardResult("0000", str4, bytesToHexString);
            }
            return new CardResult("11", str4);
        } catch (IOException e) {
            return new CardResult("11", "开通道失败，IOException异常原因（读卡器或安全元件存在通信问题）:" + e.getMessage());
        } catch (IllegalArgumentException e2) {
            return new CardResult("11", "开通道失败，IllegalArgumentException异常原因（援助的长度不在5到16（含）之间）:" + e2.getMessage());
        } catch (IllegalStateException e3) {
            return new CardResult("11", "开通道失败，IllegalStateException异常原因（安全元件在关闭后使用）:" + e3.getMessage());
        } catch (InterruptedException e4) {
            return new CardResult("11", "开通道失败，InterruptedException异常原因（恢复中断）:" + e4.getMessage());
        } catch (SecurityException e5) {
            return new CardResult("11", "开通道失败，SecurityException异常原因（无法授予调用应用程序访问此会话上的此辅助或默认小程序的权限）:" + e5.getMessage());
        } catch (UnsupportedOperationException e6) {
            return new CardResult("11", "开通道失败，UnsupportedOperationException异常原因（设备不支持给定的P2参数）:" + e6.getMessage());
        } catch (NoSuchElementException e7) {
            return new CardResult("11", "开通道失败，NoSuchElementException异常原因（安全元素上的帮助不可用或无法选择，或者逻辑通道已对非多选小程序打开）:" + e7.getMessage());
        } catch (Exception e8) {
            return new CardResult("11", "开通道失败，异常原因:" + e8.getMessage());
        }
    }

    @Override // org.simalliance.openmobileapi.SEService.CallBack
    public void serviceConnected(SEService sEService) {
        LogUtil.m7989d(this.TAG, "service connected");
        synchronized (this.mLock) {
            if (sEService.isConnected()) {
                LogUtil.m7990d("bind SEService success");
                this.mSEService = sEService;
            }
            LogUtil.m7989d(this.TAG, "Thread notifyAll");
            this.mServiceIsConnection = true;
            this.mLock.notifyAll();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.BaseSmartCard
    public CardResult closeChannel() {
        try {
            closeChannelAndSession();
            CardResult cardResult = new CardResult();
            cardResult.setStatus("0000");
            cardResult.setMsg("Service关闭成功");
            return cardResult;
        } catch (Exception e) {
            e.printStackTrace();
            String str = this.TAG;
            LogUtil.m7987e(str, "Service shutdown error:" + e.getMessage());
            return operFail("Service关闭失败，异常原因:" + e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard.BaseSmartCard
    public CardResult closeService() {
        try {
            closeChannel();
            if (this.mSEService != null && this.mSEService.isConnected()) {
                this.mSEService.shutdown();
                this.mSEService = null;
                this.mServiceIsConnection = false;
                LogUtil.m7985i(this.TAG, "SEService shutdown success");
            }
            CardResult cardResult = new CardResult();
            cardResult.setStatus("0000");
            cardResult.setMsg("SEService关闭成功");
            return cardResult;
        } catch (Exception e) {
            String str = this.TAG;
            LogUtil.m7987e(str, "SEService shutdown error:" + e.getMessage());
            return operFail("SEService关闭失败，异常原因：" + e.getMessage());
        }
    }

    protected synchronized CardResult executeApduCmd(String str) throws Exception {
        if (this.mChannel != null) {
            if (TextUtils.isEmpty(str)) {
                CardResult cardResult = new CardResult();
                cardResult.setMsg("apdu不能为空！");
                cardResult.setStatus("10");
                return cardResult;
            }
            String bytesToHexString = Hex.bytesToHexString(this.mChannel.transmit(Hex.hexStringToBytes(str)));
            String str2 = this.TAG;
            LogUtil.m7989d(str2, "Response APDU：" + bytesToHexString);
            return new CardResult("0000", "发送指令响应成功", bytesToHexString);
        }
        return new CardResult("11", "开通道失败");
    }

    private Object[] openCurrentAvailableChannel(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return new Object[]{"10", "aid不能为空！"};
        }
        Reader currentAvailableReader = getCurrentAvailableReader();
        if (currentAvailableReader == null) {
            LogUtil.m7989d(this.TAG, "判断通道是否存在 selected reader not exist");
            return new Object[]{"11", "开通道失败，选择reader对象不存在"};
        } else if (!currentAvailableReader.isSecureElementPresent()) {
            LogUtil.m7989d(this.TAG, "判断选择的通道是否可用 selected reader can not use");
            return new Object[]{"11", "开通道失败，选择reader对象不可使用"};
        } else {
            this.mSession = currentAvailableReader.openSession();
            byte[] hexStringToBytes = Hex.hexStringToBytes(str);
            String str2 = this.TAG;
            LogUtil.m7985i(str2, "open channel applet：" + str);
            Session session = this.mSession;
            if (session != null) {
                this.mChannel = session.openLogicalChannel(hexStringToBytes);
            }
            if (this.mChannel == null) {
                LogUtil.m7989d(this.TAG, "mChannel == null");
                return new Object[]{"11", "开通道失败"};
            }
            return new Object[]{"0000", "开通道成功"};
        }
    }

    private Reader getCurrentAvailableReader() {
        LogUtil.m7989d(this.TAG, "select reader name:" + getmReaderType().getValue());
        Reader[] readers = this.mSEService.getReaders();
        if (readers == null || readers.length < 1) {
            LogUtil.m7987e(this.TAG, "开通道失败，没有reader对象");
            return null;
        }
        for (Reader reader : readers) {
            LogUtil.m7989d(this.TAG, "avaliable reader name:" + reader.getName());
            if (reader.getName().startsWith(getmReaderType().getValue())) {
                return reader;
            }
        }
        return null;
    }

    public void closeChannelAndSession() {
        try {
            if (this.mChannel != null && !this.mChannel.isClosed()) {
                this.mChannel.close();
                this.mChannel = null;
                LogUtil.m7985i(this.TAG, "channel close success");
            }
        } catch (Exception e) {
            String str = this.TAG;
            LogUtil.m7985i(str, "channel close error:" + e.getMessage());
        }
        try {
            if (this.mSession == null || this.mSession.isClosed()) {
                return;
            }
            this.mSession.close();
            this.mSession = null;
            LogUtil.m7989d(this.TAG, "session close success");
        } catch (Exception e2) {
            String str2 = this.TAG;
            LogUtil.m7987e(str2, "session close error:" + e2.getMessage());
        }
    }
}
