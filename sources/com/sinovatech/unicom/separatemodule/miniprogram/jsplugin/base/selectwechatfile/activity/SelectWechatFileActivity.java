package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.provider.DocumentFile;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.adapter.SelectWechatFileAdapter;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity.FileModel;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity.WechatFileModel;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.p317ui.CircularLoading;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUriUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.SelectWechatUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.SpacesItemDecoration;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SelectWechatFileActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE_FOR_DIR = 1000;
    private static final String TAG = "selectWechatFile";
    public static int num;
    public static TextView tv_file_size;
    public NBSTraceUnit _nbs_trace;
    private SelectWechatFileAdapter adapter;
    private ConfigManager configManager;
    private ImageView ivLeftBack;
    private ImageView ivRightCha;
    private Dialog mCircularLoading;
    private RecyclerView mRvMore;
    private TextView tv_sx_title;
    private String type;
    private List<WechatFileModel> wechatFileModels = new ArrayList();
    private List<FileModel> fileModelList = new ArrayList();
    private List<FileModel> allFileModelList = new ArrayList();

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(2131492944);
        num = 0;
        this.configManager = new ConfigManager(this);
        if (this.mCircularLoading == null) {
            this.mCircularLoading = CircularLoading.showLoadDialog(this, "加载中...", true);
        }
        initData();
        PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.1
            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onAllPermissionOk(Permission[] permissionArr) {
                PermissionDialog.dimissDialog();
                if (App.getSharePreferenceUtil().getBoolean("getWechat")) {
                    SelectWechatFileActivity.this.pdShow();
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SelectWechatFileActivity.this.initStart();
                        }
                    }, 1000L);
                    return;
                }
                SelectWechatFileActivity.this.goToResult(null, "用户没有授权访问data目录的权限", "12");
            }

            @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
            public void onPermissionDenied(Permission[] permissionArr) {
                PermissionDialog.dimissDialog();
                ToastUtil.showToast("请开启存储权限,才能使用此功能!");
                SelectWechatFileActivity.this.goToResult(null, "用户没有开启存储权限", "12");
            }
        });
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void initData() {
        try {
            this.type = getIntent().getStringExtra("type");
            this.ivLeftBack = (ImageView) findViewById(2131297419);
            this.ivLeftBack.setOnClickListener(this);
            this.ivRightCha = (ImageView) findViewById(2131297473);
            this.ivRightCha.setOnClickListener(this);
            this.tv_sx_title = (TextView) findViewById(2131299091);
            this.tv_sx_title.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    SelectWechatFileActivity.this.showGetPhotoDialog();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            setTitle();
            tv_file_size = (TextView) findViewById(2131298940);
            tv_file_size.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int i;
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        JSONArray jSONArray = new JSONArray();
                        if (SelectWechatFileActivity.this.wechatFileModels == null || SelectWechatFileActivity.this.wechatFileModels.size() <= 0) {
                            i = 0;
                        } else {
                            i = 0;
                            for (int i2 = 0; i2 < SelectWechatFileActivity.this.wechatFileModels.size(); i2++) {
                                List<FileModel> fileModelList = ((WechatFileModel) SelectWechatFileActivity.this.wechatFileModels.get(i2)).getFileModelList();
                                if (fileModelList != null && fileModelList.size() > 0) {
                                    int i3 = i;
                                    for (int i4 = 0; i4 < fileModelList.size(); i4++) {
                                        if (fileModelList.get(i4).isXuanZhong()) {
                                            i3++;
                                            JSONObject jSONObject = new JSONObject();
                                            jSONObject.put("fileName", fileModelList.get(i4).getFileName());
                                            jSONObject.put("fileSize", fileModelList.get(i4).getFileSize());
                                            if (fileModelList.get(i4).isDataFile()) {
                                                jSONObject.put("filePath", SelectWechatUtils.getRealPathFromURI_API19(SelectWechatFileActivity.this, fileModelList.get(i4).getUri()));
                                            } else {
                                                jSONObject.put("filePath", fileModelList.get(i4).getUri().getPath());
                                            }
                                            jSONArray.put(jSONObject);
                                        }
                                    }
                                    i = i3;
                                }
                            }
                        }
                        if (i != 0 && jSONArray.length() > 0) {
                            SelectWechatFileActivity.this.goToResult(jSONArray, "成功", "0");
                        } else {
                            Toast.makeText(SelectWechatFileActivity.this, "您还未选择任何文件！", 0).show();
                        }
                    } catch (Exception e) {
                        SelectWechatFileActivity.this.goToResult(null, "获取文件地址出现异常," + e.getMessage(), "10");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mRvMore = (RecyclerView) findViewById(2131296483);
            this.mRvMore.setLayoutManager(new LinearLayoutManager(this));
            this.mRvMore.addItemDecoration(new SpacesItemDecoration(14));
            this.adapter = new SelectWechatFileAdapter(this, this.wechatFileModels);
            this.mRvMore.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.4
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    try {
                        if (i == 0) {
                            if (SelectWechatFileActivity.this.adapter.getScrolling()) {
                                GlideApp.with((FragmentActivity) SelectWechatFileActivity.this).resumeRequests();
                            }
                            SelectWechatFileActivity.this.adapter.setScrolling(false);
                            if (SelectWechatFileActivity.this.wechatFileModels != null && SelectWechatFileActivity.this.wechatFileModels.size() > 0) {
                                SelectWechatFileActivity.this.adapter.notifyDataSetChanged();
                            }
                        } else {
                            GlideApp.with((FragmentActivity) SelectWechatFileActivity.this).pauseRequests();
                            SelectWechatFileActivity.this.adapter.setScrolling(true);
                        }
                        super.onScrollStateChanged(recyclerView, i);
                    } catch (Exception e) {
                        Log.e("onScrollStateChanged:", e.getMessage());
                    }
                }
            });
            this.mRvMore.setAdapter(this.adapter);
        } catch (Exception e) {
            Log.e("initData:", e.getMessage());
            pdHide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initStart() {
        this.fileModelList.clear();
        this.allFileModelList.clear();
        try {
            getFiles(DocumentFile.fromTreeUri(this, Uri.parse(FileUriUtils.changeToUri3(FileUriUtils.statusPath))));
        } catch (Exception e) {
            Log.e(TAG, "异常getFiles:" + e.getMessage());
        }
        try {
            getAllFiles("/sdcard/Pictures/WeiXin/");
        } catch (Exception e2) {
            Log.e(TAG, "异常getAllFiles:" + e2.getMessage());
        }
        updateUI();
    }

    private void setTitle() {
        char c;
        String str = this.type;
        int hashCode = str.hashCode();
        if (hashCode == 3143036) {
            if (str.equals("file")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode != 100313435) {
            if (hashCode == 112202875 && str.equals("video")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (str.equals("image")) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                this.tv_sx_title.setText("筛选（图片）");
                return;
            case 1:
                this.tv_sx_title.setText("筛选（视频）");
                return;
            case 2:
                this.tv_sx_title.setText("筛选（文件）");
                return;
            default:
                this.tv_sx_title.setText("筛选（全部）");
                return;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131297419) {
            goToResult(null, "用户点击左侧返回", "11");
        } else if (id == 2131297473) {
            goToResult(null, "用户点击右侧×返回", "11");
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Uri data;
        super.onActivityResult(i, i2, intent);
        if (intent == null || i != 1000 || (data = intent.getData()) == null) {
            return;
        }
        App.getInstance().getContentResolver().takePersistableUriPermission(data, intent.getFlags() & 3);
        this.configManager.setWechatData(true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x00ce, code lost:
        if (r4 == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00d0, code lost:
        r4.stop();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00d5, code lost:
        if (r4 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00d8, code lost:
        r7.setVideoTime(com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.formatTime(r5));
        r7.setNums(com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.isType(r7));
        android.util.Log.d(com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.TAG, "文件属性：" + r7.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0107, code lost:
        if (com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.isAgree(r7, r10.type) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0109, code lost:
        r10.fileModelList.add(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x010e, code lost:
        r10.allFileModelList.add(r7);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void getFiles(android.support.p083v4.provider.DocumentFile r11) {
        /*
            Method dump skipped, instructions count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.getFiles(android.support.v4.provider.DocumentFile):void");
    }

    private void getAllFiles(String str) throws Exception {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    String name = file2.getName();
                    Uri parse = Uri.parse(file2.getPath());
                    Date date = new Date(file2.lastModified());
                    String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
                    String format2 = new SimpleDateFormat(JtDateUtil.dateFormatYM).format(date);
                    FileModel fileModel = new FileModel();
                    fileModel.setFileSize(FileUtils.ShowLongFileSzie(Long.valueOf(file2.length())));
                    fileModel.setCreateTime(format);
                    fileModel.setYearAndMonthStr(format2);
                    fileModel.setUri(parse);
                    fileModel.setXuanZhong(false);
                    fileModel.setFileName(name);
                    fileModel.setDataFile(false);
                    long j = 0;
                    if (FileUtils.isType(fileModel) == 3) {
                        try {
                            MediaPlayer mediaPlayer = new MediaPlayer();
                            mediaPlayer.setDataSource(file2.getPath());
                            mediaPlayer.prepare();
                            j = mediaPlayer.getDuration();
                        } catch (Exception unused) {
                        }
                    }
                    fileModel.setVideoTime(FileUtils.formatTime(j));
                    Log.d(TAG, "文件属性2222：" + fileModel.toString());
                    fileModel.setNums(FileUtils.isType(fileModel));
                    if (FileUtils.isAgree(fileModel, this.type)) {
                        this.fileModelList.add(fileModel);
                    }
                    this.allFileModelList.add(fileModel);
                } else if (file2.isDirectory()) {
                    getAllFiles(file2.getAbsolutePath());
                }
            }
        }
    }

    private void updateUI() {
        try {
            try {
                if (this.fileModelList != null && this.fileModelList.size() > 0) {
                    this.wechatFileModels.clear();
                    List<FileModel> list = (List) this.fileModelList.stream().filter(new Predicate() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.-$$Lambda$SelectWechatFileActivity$PZZW42tixejSLajLd-f5jCVHviU
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return SelectWechatFileActivity.lambda$updateUI$0((FileModel) obj);
                        }
                    }).sorted(Comparator.comparing($$Lambda$6eXb6uyOFk1sdWWKoD9NP8FINko.INSTANCE).reversed()).collect(Collectors.toList());
                    List<String> list2 = (List) ((List) list.stream().map(new Function() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.-$$Lambda$SelectWechatFileActivity$qt3Arcaz4FAwDNph4pVcRSBiYv4
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            String yearAndMonthStr;
                            yearAndMonthStr = ((FileModel) obj).getYearAndMonthStr();
                            return yearAndMonthStr;
                        }
                    }).collect(Collectors.toList())).stream().distinct().collect(Collectors.toList());
                    if (list2 != null && list2.size() > 0 && list != null && list.size() > 0) {
                        for (String str : list2) {
                            WechatFileModel wechatFileModel = new WechatFileModel();
                            ArrayList arrayList = new ArrayList();
                            wechatFileModel.setTime(str);
                            for (FileModel fileModel : list) {
                                if (fileModel.getYearAndMonthStr().equals(str)) {
                                    arrayList.add(fileModel);
                                }
                            }
                            wechatFileModel.setFileModelList(arrayList);
                            this.wechatFileModels.add(wechatFileModel);
                        }
                    }
                    if (this.wechatFileModels != null && this.wechatFileModels.size() > 0) {
                        WechatFileModel wechatFileModel2 = this.wechatFileModels.get(0);
                        if (wechatFileModel2.getTime().equals(new SimpleDateFormat(JtDateUtil.dateFormatYM).format(new Date()))) {
                            List<FileModel> fileModelList = wechatFileModel2.getFileModelList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            boolean z = false;
                            for (int i = 0; i < fileModelList.size(); i++) {
                                if (SelectWechatUtils.isLatestWeek(fileModelList.get(i).getCreateTime())) {
                                    arrayList2.add(fileModelList.get(i));
                                    z = true;
                                } else {
                                    arrayList3.add(fileModelList.get(i));
                                }
                            }
                            this.wechatFileModels.remove(0);
                            if (z) {
                                WechatFileModel wechatFileModel3 = new WechatFileModel();
                                wechatFileModel3.setTime("本周");
                                wechatFileModel3.setFileModelList(arrayList2);
                                WechatFileModel wechatFileModel4 = new WechatFileModel();
                                wechatFileModel4.setTime("本月");
                                wechatFileModel4.setFileModelList(arrayList3);
                                if (arrayList3.size() > 0) {
                                    this.wechatFileModels.add(0, wechatFileModel4);
                                }
                                if (arrayList2.size() > 0) {
                                    this.wechatFileModels.add(0, wechatFileModel3);
                                }
                            } else {
                                WechatFileModel wechatFileModel5 = new WechatFileModel();
                                wechatFileModel5.setTime("本月");
                                wechatFileModel5.setFileModelList(arrayList3);
                                this.wechatFileModels.add(0, wechatFileModel5);
                            }
                        }
                    }
                    if (this.wechatFileModels != null && this.wechatFileModels.size() > 0) {
                        this.adapter.notifyDataSetChanged();
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "异常：" + e.getMessage());
                goToResult(null, "解析数据异常：" + e.getMessage(), "10");
            }
        } finally {
            FileUtils.clearMemoryCache(this);
            pdHide();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$updateUI$0(FileModel fileModel) {
        return fileModel.getYearAndMonthStr() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (this.mCircularLoading == null) {
                this.mCircularLoading = CircularLoading.showLoadDialog(this, "加载中...", true);
            }
            UIUtils.showDialog(this, this.mCircularLoading);
        } catch (Exception unused) {
        }
    }

    private void pdHide() {
        try {
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    SelectWechatFileActivity selectWechatFileActivity = SelectWechatFileActivity.this;
                    CircularLoading.closeDialog(selectWechatFileActivity, selectWechatFileActivity.mCircularLoading);
                }
            }, 1500L);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateQieHuan(String str) {
        try {
            this.type = str;
            num = 0;
            pdShow();
            setTitle();
            updateSizeUI();
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    SelectWechatFileActivity.this.updateUIOther();
                }
            }, 1000L);
        } catch (Exception unused) {
        } catch (Throwable th) {
            pdHide();
            throw th;
        }
        pdHide();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUIOther() {
        try {
            try {
                if (this.allFileModelList != null && this.allFileModelList.size() > 0) {
                    this.wechatFileModels.clear();
                    List<FileModel> list = (List) this.allFileModelList.stream().filter(new Predicate() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.-$$Lambda$SelectWechatFileActivity$xQYFWqMG90vSZSpHNytMfQWepnw
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return SelectWechatFileActivity.lambda$updateUIOther$2(SelectWechatFileActivity.this, (FileModel) obj);
                        }
                    }).sorted(Comparator.comparing($$Lambda$6eXb6uyOFk1sdWWKoD9NP8FINko.INSTANCE).reversed()).collect(Collectors.toList());
                    List<String> list2 = (List) ((List) list.stream().map(new Function() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.-$$Lambda$SelectWechatFileActivity$2aaqJ70j7nmGrP1ezve3S1Vekk4
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            String yearAndMonthStr;
                            yearAndMonthStr = ((FileModel) obj).getYearAndMonthStr();
                            return yearAndMonthStr;
                        }
                    }).collect(Collectors.toList())).stream().distinct().collect(Collectors.toList());
                    if (list2 != null && list2.size() > 0 && list != null && list.size() > 0) {
                        for (String str : list2) {
                            WechatFileModel wechatFileModel = new WechatFileModel();
                            ArrayList arrayList = new ArrayList();
                            wechatFileModel.setTime(str);
                            for (FileModel fileModel : list) {
                                if (fileModel.getYearAndMonthStr().equals(str)) {
                                    arrayList.add(fileModel);
                                }
                            }
                            wechatFileModel.setFileModelList(arrayList);
                            this.wechatFileModels.add(wechatFileModel);
                        }
                    }
                    if (this.wechatFileModels != null && this.wechatFileModels.size() > 0) {
                        WechatFileModel wechatFileModel2 = this.wechatFileModels.get(0);
                        if (wechatFileModel2.getTime().equals(new SimpleDateFormat(JtDateUtil.dateFormatYM).format(new Date()))) {
                            List<FileModel> fileModelList = wechatFileModel2.getFileModelList();
                            ArrayList arrayList2 = new ArrayList();
                            ArrayList arrayList3 = new ArrayList();
                            boolean z = false;
                            for (int i = 0; i < fileModelList.size(); i++) {
                                if (SelectWechatUtils.isLatestWeek(fileModelList.get(i).getCreateTime())) {
                                    arrayList2.add(fileModelList.get(i));
                                    z = true;
                                } else {
                                    arrayList3.add(fileModelList.get(i));
                                }
                            }
                            this.wechatFileModels.remove(0);
                            if (z) {
                                WechatFileModel wechatFileModel3 = new WechatFileModel();
                                wechatFileModel3.setTime("本周");
                                wechatFileModel3.setFileModelList(arrayList2);
                                WechatFileModel wechatFileModel4 = new WechatFileModel();
                                wechatFileModel4.setTime("本月");
                                wechatFileModel4.setFileModelList(arrayList3);
                                if (arrayList3.size() > 0) {
                                    this.wechatFileModels.add(0, wechatFileModel4);
                                }
                                if (arrayList2.size() > 0) {
                                    this.wechatFileModels.add(0, wechatFileModel3);
                                }
                            } else {
                                WechatFileModel wechatFileModel5 = new WechatFileModel();
                                wechatFileModel5.setTime("本月");
                                wechatFileModel5.setFileModelList(arrayList3);
                                this.wechatFileModels.add(0, wechatFileModel5);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "异常：" + e.getMessage());
                goToResult(null, "解析数据异常：" + e.getMessage(), "10");
            }
        } finally {
            this.adapter.notifyDataSetChanged();
            FileUtils.clearMemoryCache(this);
        }
    }

    public static /* synthetic */ boolean lambda$updateUIOther$2(SelectWechatFileActivity selectWechatFileActivity, FileModel fileModel) {
        return fileModel.getYearAndMonthStr() != null && FileUtils.isAgree(fileModel, selectWechatFileActivity.type);
    }

    public void showGetPhotoDialog() {
        final Dialog dialog = new Dialog(this, 2131952236);
        LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(2131493554, (ViewGroup) null);
        linearLayout.findViewById(2131299589).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                SelectWechatFileActivity.this.updateQieHuan("all");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.findViewById(2131299592).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                SelectWechatFileActivity.this.updateQieHuan("image");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.findViewById(2131299591).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                SelectWechatFileActivity.this.updateQieHuan("video");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.findViewById(2131299593).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                SelectWechatFileActivity.this.updateQieHuan("file");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        linearLayout.findViewById(2131299590).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.activity.SelectWechatFileActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        Window window = dialog.getWindow();
        window.setGravity(80);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131952235);
        dialog.show();
    }

    public static void updateSizeUI() {
        if (num < 0) {
            num = 0;
        }
        if (num == 0) {
            tv_file_size.setText("确定0/20");
            tv_file_size.setBackgroundResource(2131232629);
            return;
        }
        TextView textView = tv_file_size;
        textView.setText("确定" + num + "/20");
        tv_file_size.setBackgroundResource(2131232630);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goToResult(JSONArray jSONArray, String str, String str2) {
        Intent intent = new Intent();
        if (jSONArray != null) {
            intent.putExtra("result", !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
        } else {
            intent.putExtra("result", "");
        }
        intent.putExtra("status", str2);
        intent.putExtra("msg", str);
        setResult(-1, intent);
        finish();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            goToResult(null, "用户使用物理键返回", "11");
        }
        return super.onKeyDown(i, keyEvent);
    }
}
