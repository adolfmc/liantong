package com.sinovatech.unicom.separatemodule.gamedistribution;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.SpeedCalculator;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.DownloadListener4WithSpeed;
import com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.PushMessageEntity;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.tms.TMSUtil;
import com.sinovatech.unicom.separatemodule.websocket.WebSocketManager;
import com.sinovatech.unicom.separatemodule.websocket.WebSocketNoticDialog;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerGameYuyu {
    private Activity activityContext;
    private List<GameEntity> gameEntityList;
    private NotificationManager mNotificationManager;
    private File parentFile;
    private int boxIndex = -1;
    private Box<GameEntity> yuyueBox = App.getBoxStore().boxFor(GameEntity.class);

    public ManagerGameYuyu(Activity activity) {
        this.activityContext = activity;
        this.mNotificationManager = (NotificationManager) this.activityContext.getSystemService("notification");
    }

    public void queryYuYue() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("pageNo", "1");
            hashMap.put("pageSize", "100");
            App.getAsyncHttpClient().rxPost(URLSet.getGameYuyueURL(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.ManagerGameYuyu.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                    MsLogUtil.m7980d("ManagerGameAutoDownload-queryYuYue(" + URLSet.getGameYuyueURL() + "):" + str);
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.getString("code").equals("0000")) {
                        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("gameInfo");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            if (jSONObject2.optString("downloadState", "").equals("4") && ((GameEntity) ManagerGameYuyu.this.yuyueBox.query().equal(GameEntity_.gamePackageName, jSONObject2.getString("gamePackageName")).build().findUnique()) == null) {
                                GameEntity gameEntity = new GameEntity();
                                gameEntity.setGameId(jSONObject2.getString("gameId"));
                                gameEntity.setGameName(jSONObject2.getString("gameName"));
                                gameEntity.setGamePackageName(jSONObject2.getString("gamePackageName"));
                                gameEntity.setGameIcon(jSONObject2.getString("icon"));
                                gameEntity.setGameUrl(jSONObject2.getString("gameUrl"));
                                gameEntity.setGameSubtitle(jSONObject2.getString("subtitle"));
                                gameEntity.setGameDetailsUrl(jSONObject2.getString("detailsUrl") + "&downcomplete=ok");
                                gameEntity.setGameCentreDetailsUrl(jSONObject2.getString("centreDetailsUrl"));
                                gameEntity.setGameSize(jSONObject2.getString("gameSize"));
                                gameEntity.setGameDownloadStatus("wait");
                                ManagerGameYuyu.this.yuyueBox.put((Box) gameEntity);
                            }
                        }
                    }
                    ManagerGameYuyu.this.prepareData();
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.ManagerGameYuyu.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MsLogUtil.m7978e("ManagerGameAutoDownload-queryYuYue:" + th.getMessage());
                    ManagerGameYuyu.this.prepareData();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("ManagerGameAutoDownload-queryYuYue:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareData() {
        this.gameEntityList = this.yuyueBox.query().equal(GameEntity_.gameDownloadStatus, "wait").build().find();
        List<GameEntity> list = this.gameEntityList;
        if (list != null && list.size() > 0) {
            this.boxIndex = this.gameEntityList.size() - 1;
        }
        prepareDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareDownload() {
        try {
            MsLogUtil.m7980d("ManagerGameAutoDownload-prepareDownload:预约等待数据表中个数 " + this.gameEntityList.size());
            if (this.gameEntityList != null && this.gameEntityList.size() > 0 && SystemServiceUtils.netIsAvailable() && this.boxIndex >= 0) {
                final GameEntity gameEntity = this.gameEntityList.get(this.boxIndex);
                this.boxIndex--;
                if ("wait".equals(gameEntity.getGameDownloadStatus())) {
                    if (!SystemServiceUtils.isWifiActive() && !TMSUtil.checkIsUnicomSimCard(this.activityContext)) {
                        if (isForeground(this.activityContext, MainActivity.class.getName())) {
                            CustomDialogManager.show(this.activityContext, gameEntity.getGameName(), "您预约的游戏已开放下载\n仅限联通用户享受免流量特权", "取消", "继续下载", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.ManagerGameYuyu.3
                                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                                public void onClickOk() {
                                    ManagerGameYuyu.this.downloadGame(gameEntity);
                                }
                            });
                            MsLogUtil.m7980d("ManagerGameAutoDownload-prepareDownload:更新预约状态为已完成 " + gameEntity.getGameName());
                            gameEntity.setGameDownloadStatus(PrefetchCumpLauncher.PrefetchStatus_Complete);
                            this.yuyueBox.put((Box<GameEntity>) gameEntity);
                        }
                    }
                    downloadGame(gameEntity);
                    MsLogUtil.m7980d("ManagerGameAutoDownload-prepareDownload:更新预约状态为已完成 " + gameEntity.getGameName());
                    gameEntity.setGameDownloadStatus(PrefetchCumpLauncher.PrefetchStatus_Complete);
                    this.yuyueBox.put((Box<GameEntity>) gameEntity);
                } else {
                    MsLogUtil.m7980d("ManagerGameAutoDownload-prepareDownload:已经提示过用户下载，继续检查下一个。 " + gameEntity.getGameName());
                    prepareDownload();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("ManagerGameAutoDownload-prepareDownload:" + e.getMessage());
        }
    }

    private boolean isForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks != null && runningTasks.size() > 0 && str.equals(runningTasks.get(0).topActivity.getClassName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadGame(GameEntity gameEntity) {
        try {
            if (!TMSUtil.isPkgInstalled(App.getInstance().getApplicationContext(), gameEntity.getGamePackageName())) {
                this.parentFile = GameDownloadUtils.createDic();
                DownloadTask createDownloadTask = GameDownloadUtils.createDownloadTask(this.parentFile, gameEntity.getGamePackageName(), gameEntity.getGameUrl());
                if (!StatusUtil.isCompleted(createDownloadTask)) {
                    MsLogUtil.m7980d("ManagerGameAutoDownload-downloadGame:开始启动下载 " + gameEntity.getGameName());
                    if (StatusUtil.isSameTaskPendingOrRunning(createDownloadTask)) {
                        createDownloadTask.cancel();
                    }
                    MsLogUtil.m7980d("ManagerGameAutoDownload-downloadGame:准备下载 " + gameEntity.getGameName() + " " + gameEntity.getGameUrl());
                    createDownloadTask.enqueue(new MyDownListener(gameEntity));
                    log(gameEntity, "开始下载_05");
                    return;
                }
                MsLogUtil.m7980d("ManagerGameAutoDownload-downloadGame:已经下载完成了，直接发送消息通知安装 " + gameEntity.getGameName());
                notification(gameEntity, createDownloadTask.getFile().getAbsolutePath());
                return;
            }
            MsLogUtil.m7980d("ManagerGameAutoDownload-downloadGame:已经安装过此游戏，继续执行下一个预约的记录 " + gameEntity.getGameName());
            prepareDownload();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("ManagerGameAutoDownload-downloadGame:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyDownListener extends DownloadListener4WithSpeed {
        private GameEntity gameEntity;

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void blockEnd(@NonNull DownloadTask downloadTask, int i, BlockInfo blockInfo, @NonNull SpeedCalculator speedCalculator) {
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectEnd(@NonNull DownloadTask downloadTask, int i, int i2, @NonNull Map<String, List<String>> map) {
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void connectStart(@NonNull DownloadTask downloadTask, int i, @NonNull Map<String, List<String>> map) {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void infoReady(@NonNull DownloadTask downloadTask, @NonNull BreakpointInfo breakpointInfo, boolean z, @NonNull Listener4SpeedAssistExtend.Listener4SpeedModel listener4SpeedModel) {
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progressBlock(@NonNull DownloadTask downloadTask, int i, long j, @NonNull SpeedCalculator speedCalculator) {
        }

        public MyDownListener(GameEntity gameEntity) {
            this.gameEntity = gameEntity;
        }

        @Override // com.liulishuo.okdownload.DownloadListener
        public void taskStart(@NonNull DownloadTask downloadTask) {
            MsLogUtil.m7980d("ManagerGameAutoDownload-MyDownListener:" + downloadTask.getFilename() + " taskStart");
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void progress(@NonNull DownloadTask downloadTask, long j, @NonNull SpeedCalculator speedCalculator) {
            MsLogUtil.m7980d("ManagerGameAutoDownload-MyDownListener:" + downloadTask.getFilename() + " progress " + j + " " + speedCalculator.speed());
        }

        @Override // com.liulishuo.okdownload.core.listener.assist.Listener4SpeedAssistExtend.Listener4SpeedCallback
        public void taskEnd(@NonNull DownloadTask downloadTask, @NonNull EndCause endCause, @Nullable Exception exc, @NonNull SpeedCalculator speedCalculator) {
            String str = "CANCEL";
            if (endCause != null) {
                try {
                    if (endCause.compareTo(EndCause.SAME_TASK_BUSY) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.PRE_ALLOCATE_FAILED) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.FILE_BUSY) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.CANCELED) == 0) {
                        str = "CANCEL";
                    } else if (endCause.compareTo(EndCause.COMPLETED) == 0) {
                        str = "COMPLETED";
                    } else if (endCause.compareTo(EndCause.ERROR) == 0) {
                        str = "ERROR";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    MsLogUtil.m7978e("ManagerGameAutoDownload-MyDownListener:" + e.getMessage());
                    return;
                }
            }
            String str2 = "";
            if (exc != null) {
                str2 = "" + exc.getMessage();
            }
            MsLogUtil.m7980d("ManagerGameAutoDownload-MyDownListener:" + downloadTask.getFilename() + " " + str + " " + str2);
            if (StatusUtil.isCompleted(downloadTask)) {
                ManagerGameYuyu.this.notification(this.gameEntity, downloadTask.getFile().getAbsolutePath());
                ManagerGameYuyu.this.log(this.gameEntity, "下载完成_05");
                return;
            }
            ManagerGameYuyu.this.prepareDownload();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notification(GameEntity gameEntity, String str) throws Exception {
        MsLogUtil.m7980d("ManagerGameAutoDownload-notification:下载完成，发送消息 " + gameEntity.getGameName() + " " + str);
        PushMessageEntity pushMessageEntity = new PushMessageEntity();
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("");
        pushMessageEntity.setDate(sb.toString());
        pushMessageEntity.setEndTime(System.currentTimeMillis() + "");
        pushMessageEntity.setTitle(gameEntity.getGameName());
        pushMessageEntity.setContent("您预约的\"" + gameEntity.getGameName() + "\"游戏已下载完成，点击安装");
        pushMessageEntity.setMsgType("T1");
        pushMessageEntity.setUrl(gameEntity.getGameDetailsUrl());
        pushMessageEntity.setId(System.currentTimeMillis() + "");
        pushMessageEntity.setUserMobile("0");
        pushMessageEntity.setNewMsgType("");
        PushMsgDao pushMsgDao = new PushMsgDao(App.getInstance());
        if (!pushMsgDao.getPushMessageRecordExist(pushMessageEntity)) {
            pushMsgDao.insertPushMessageRecord(pushMessageEntity);
        }
        WebSocketNoticDialog.show(WebSocketManager.PUSHLOCALGAME, pushMessageEntity, false);
        prepareDownload();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(GameEntity gameEntity, String str) {
        String str2 = this.activityContext.getResources().getString(2131886969).split("@")[1];
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏分发");
        hashMap.put("p5", "36");
        hashMap.put("p32", "Android");
        hashMap.put("p25", gameEntity.getGameName());
        hashMap.put("p26", str);
        hashMap.put("p34", str2);
        App.getAsyncHttpClient().rxPost(URLSet.getGameYuyueLogURL(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.ManagerGameYuyu.4
            @Override // io.reactivex.functions.Consumer
            public void accept(String str3) throws Exception {
                MsLogUtil.m7980d("ManagerGameAutoDownload-log:" + str3);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.gamedistribution.ManagerGameYuyu.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7978e("ManagerGameAutoDownload-log:" + th.getMessage());
            }
        });
    }
}
