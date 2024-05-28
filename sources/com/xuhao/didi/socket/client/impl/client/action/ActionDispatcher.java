package com.xuhao.didi.socket.client.impl.client.action;

import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.action.ISocketActionListener;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ActionDispatcher implements IStateSender, IRegister<ISocketActionListener, IConnectionManager> {
    private volatile ConnectionInfo mConnectionInfo;
    private volatile IConnectionManager mManager;
    private static final DispatchThread HANDLE_THREAD = new DispatchThread();
    private static final LinkedBlockingQueue<ActionBean> ACTION_QUEUE = new LinkedBlockingQueue<>();
    private volatile List<ISocketActionListener> mResponseHandlerList = new ArrayList();
    private ReentrantLock mLock = new ReentrantLock(true);

    static {
        HANDLE_THREAD.start();
    }

    public ActionDispatcher(ConnectionInfo connectionInfo, IConnectionManager iConnectionManager) {
        this.mManager = iConnectionManager;
        this.mConnectionInfo = connectionInfo;
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister
    public IConnectionManager registerReceiver(ISocketActionListener iSocketActionListener) {
        if (iSocketActionListener != null) {
            do {
                try {
                    try {
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    this.mLock.unlock();
                }
            } while (!this.mLock.tryLock(1L, TimeUnit.SECONDS));
            if (!this.mResponseHandlerList.contains(iSocketActionListener)) {
                this.mResponseHandlerList.add(iSocketActionListener);
            }
        }
        return this.mManager;
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher.IRegister
    public IConnectionManager unRegisterReceiver(ISocketActionListener iSocketActionListener) {
        if (iSocketActionListener != null) {
            do {
                try {
                    try {
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    this.mLock.unlock();
                }
            } while (!this.mLock.tryLock(1L, TimeUnit.SECONDS));
            this.mResponseHandlerList.remove(iSocketActionListener);
        }
        return this.mManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void dispatchActionToListener(String str, Serializable serializable, ISocketActionListener iSocketActionListener) {
        char c;
        switch (str.hashCode()) {
            case -1455248519:
                if (str.equals("action_read_complete")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1321574355:
                if (str.equals("action_read_thread_start")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -1245920523:
                if (str.equals("action_connection_failed")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1201839197:
                if (str.equals("action_disconnection")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1121297674:
                if (str.equals("action_write_thread_start")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -749410229:
                if (str.equals("action_connection_success")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -542453077:
                if (str.equals("action_read_thread_shutdown")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 190576450:
                if (str.equals("action_write_thread_shutdown")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1756120480:
                if (str.equals("action_pulse_request")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 2146005698:
                if (str.equals("action_write_complete")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                try {
                    iSocketActionListener.onSocketConnectionSuccess(this.mConnectionInfo, str);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                try {
                    iSocketActionListener.onSocketConnectionFailed(this.mConnectionInfo, str, (Exception) serializable);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 2:
                try {
                    iSocketActionListener.onSocketDisconnection(this.mConnectionInfo, str, (Exception) serializable);
                    return;
                } catch (Exception e3) {
                    e3.printStackTrace();
                    return;
                }
            case 3:
                try {
                    iSocketActionListener.onSocketReadResponse(this.mConnectionInfo, str, (OriginalData) serializable);
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            case 4:
            case 5:
                try {
                    iSocketActionListener.onSocketIOThreadStart(str);
                    return;
                } catch (Exception e5) {
                    e5.printStackTrace();
                    return;
                }
            case 6:
                try {
                    iSocketActionListener.onSocketWriteResponse(this.mConnectionInfo, str, (ISendable) serializable);
                    return;
                } catch (Exception e6) {
                    e6.printStackTrace();
                    return;
                }
            case 7:
            case '\b':
                try {
                    iSocketActionListener.onSocketIOThreadShutdown(str, (Exception) serializable);
                    return;
                } catch (Exception e7) {
                    e7.printStackTrace();
                    return;
                }
            case '\t':
                try {
                    iSocketActionListener.onPulseSend(this.mConnectionInfo, (IPulseSendable) serializable);
                    return;
                } catch (Exception e8) {
                    e8.printStackTrace();
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IStateSender
    public void sendBroadcast(String str, Serializable serializable) {
        OkSocketOptions option = this.mManager.getOption();
        if (option == null) {
            return;
        }
        OkSocketOptions.ThreadModeToken callbackThreadModeToken = option.getCallbackThreadModeToken();
        if (callbackThreadModeToken != null) {
            try {
                callbackThreadModeToken.handleCallbackEvent(new ActionRunnable(new ActionBean(str, serializable, this)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (option.isCallbackInIndependentThread()) {
            ACTION_QUEUE.offer(new ActionBean(str, serializable, this));
        } else if (option.isCallbackInIndependentThread()) {
            SLog.m2258e("ActionDispatcher error action:" + str + " is not dispatch");
        } else {
            do {
                try {
                    try {
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    this.mLock.unlock();
                }
            } while (!this.mLock.tryLock(1L, TimeUnit.SECONDS));
            for (ISocketActionListener iSocketActionListener : new ArrayList(this.mResponseHandlerList)) {
                dispatchActionToListener(str, serializable, iSocketActionListener);
            }
        }
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IStateSender
    public void sendBroadcast(String str) {
        sendBroadcast(str, null);
    }

    public void setConnectionInfo(ConnectionInfo connectionInfo) {
        this.mConnectionInfo = connectionInfo;
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    static class DispatchThread extends AbsLoopThread {
        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void loopFinish(Exception exc) {
        }

        public DispatchThread() {
            super("client_action_dispatch_thread");
        }

        @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
        public void runInLoopThread() throws Exception {
            ActionBean actionBean = (ActionBean) ActionDispatcher.ACTION_QUEUE.take();
            if (actionBean == null || actionBean.mDispatcher == null) {
                return;
            }
            ActionDispatcher actionDispatcher = actionBean.mDispatcher;
            synchronized (actionDispatcher.mResponseHandlerList) {
                for (ISocketActionListener iSocketActionListener : new ArrayList(actionDispatcher.mResponseHandlerList)) {
                    actionDispatcher.dispatchActionToListener(actionBean.mAction, actionBean.arg, iSocketActionListener);
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ActionBean {
        Serializable arg;
        String mAction;
        ActionDispatcher mDispatcher;

        public ActionBean(String str, Serializable serializable, ActionDispatcher actionDispatcher) {
            this.mAction = "";
            this.mAction = str;
            this.arg = serializable;
            this.mDispatcher = actionDispatcher;
        }
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class ActionRunnable implements Runnable {
        private ActionBean mActionBean;

        ActionRunnable(ActionBean actionBean) {
            this.mActionBean = actionBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            ActionBean actionBean = this.mActionBean;
            if (actionBean == null || actionBean.mDispatcher == null) {
                return;
            }
            ActionDispatcher actionDispatcher = this.mActionBean.mDispatcher;
            synchronized (actionDispatcher.mResponseHandlerList) {
                for (ISocketActionListener iSocketActionListener : new ArrayList(actionDispatcher.mResponseHandlerList)) {
                    actionDispatcher.dispatchActionToListener(this.mActionBean.mAction, this.mActionBean.arg, iSocketActionListener);
                }
            }
        }
    }
}
