package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

import android.se.omapi.Channel;
import android.se.omapi.Reader;
import android.se.omapi.SEService;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Hex;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.LogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Utils;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;

@RequiresApi(api = 28)
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
final class SmartCardByGoogle extends BaseSmartCard implements SEService.OnConnectedListener {
    private Channel mChannel;
    private SEService mSEService;
    private Session mSession;
    private Object mLock = new Object();
    private boolean mServiceIsConnection = false;
    private final String TAG = SmartCardByGoogle.class.getSimpleName();

    private void bindService() throws InterruptedException {
        if (this.mSEService == null) {
            this.mSEService = new SEService(Utils.getApp().getApplicationContext(), new OMAExecutor(), this);
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
            return operFail("执行APDU指令失败，原因:" + e.getMessage());
        } catch (Exception e2) {
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
            String str3 = (String) openCurrentAvailableChannel[1];
            if (((String) openCurrentAvailableChannel[0]).equals("0000")) {
                String bytesToHexString = Hex.bytesToHexString(this.mChannel.getSelectResponse());
                String str4 = this.TAG;
                LogUtil.m7989d(str4, "Response APDU：" + bytesToHexString);
                return new CardResult("0000", str3, bytesToHexString);
            }
            String str5 = this.TAG;
            LogUtil.m7989d(str5, "OpenChannel Error Desc:" + str3);
            return new CardResult("11", str3);
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
                LogUtil.m7989d(this.TAG, "SEService shutdown success");
            }
            CardResult cardResult = new CardResult();
            cardResult.setStatus("0000");
            cardResult.setMsg("SEService关闭成功");
            return cardResult;
        } catch (Exception e) {
            e.printStackTrace();
            String str = this.TAG;
            LogUtil.m7987e(str, "SEService shutdown error:" + e.getMessage());
            return operFail("SEService关闭失败，异常原因:" + e.getMessage());
        }
    }

    @Override // android.se.omapi.SEService.OnConnectedListener
    public void onConnected() {
        LogUtil.m7985i(this.TAG, "service is connected");
        synchronized (this.mLock) {
            this.mServiceIsConnection = true;
            this.mLock.notifyAll();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class OMAExecutor implements Executor {
        public OMAExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

    private synchronized CardResult executeApduCmd(String str) throws Exception {
        if (this.mChannel != null) {
            if (TextUtils.isEmpty(str)) {
                CardResult cardResult = new CardResult();
                cardResult.setMsg("apdu不能为空！");
                cardResult.setStatus("10");
                return cardResult;
            }
            String str2 = this.TAG;
            LogUtil.m7989d(str2, "Command APDU:" + str);
            String bytesToHexString = Hex.bytesToHexString(this.mChannel.transmit(Hex.hexStringToBytes(str)));
            String str3 = this.TAG;
            LogUtil.m7985i(str3, "Response APDU：" + bytesToHexString);
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
            return new Object[]{"11", "开通道失败，选择reader对象不存在"};
        }
        if (currentAvailableReader.isSecureElementPresent()) {
            this.mSession = currentAvailableReader.openSession();
            byte[] hexStringToBytes = Hex.hexStringToBytes(str);
            String str2 = this.TAG;
            LogUtil.m7985i(str2, "open channel applet：" + str);
            Session session = this.mSession;
            if (session != null) {
                this.mChannel = session.openLogicalChannel(hexStringToBytes);
            }
            return this.mChannel == null ? new Object[]{"11", "开通道失败"} : new Object[]{"0000", "开通道成功"};
        }
        return new Object[]{"11", "开通道失败，选择reader对象不可使用"};
    }

    private Reader getCurrentAvailableReader() {
        LogUtil.m7989d(this.TAG, "select reader name:" + getmReaderType().getValue());
        Reader[] readers = this.mSEService.getReaders();
        if (readers.length < 1) {
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
            if (this.mChannel != null && this.mChannel.isOpen()) {
                this.mChannel.close();
                this.mChannel = null;
                LogUtil.m7985i(this.TAG, "channel close success");
            }
        } catch (Exception e) {
            String str = this.TAG;
            LogUtil.m7987e(str, "channel close error:" + e.getMessage());
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
