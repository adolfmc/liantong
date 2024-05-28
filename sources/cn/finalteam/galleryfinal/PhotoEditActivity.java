package cn.finalteam.galleryfinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import cn.finalteam.galleryfinal.adapter.PhotoEditListAdapter;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.model.PhotoTempModel;
import cn.finalteam.galleryfinal.utils.ILogger;
import cn.finalteam.galleryfinal.utils.RecycleViewBitmapUtils;
import cn.finalteam.galleryfinal.utils.Utils;
import cn.finalteam.galleryfinal.widget.FloatingActionButton;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import cn.finalteam.galleryfinal.widget.crop.CropImageActivity;
import cn.finalteam.galleryfinal.widget.crop.CropImageView;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.toolsfinal.ActivityManager;
import cn.finalteam.toolsfinal.StringUtils;
import cn.finalteam.toolsfinal.p093io.FileUtils;
import cn.finalteam.toolsfinal.p093io.FilenameUtils;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoEditActivity extends CropImageActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    static final String CROP_PHOTO_ACTION = "crop_photo_action";
    static final String EDIT_PHOTO_ACTION = "edit_photo_action";
    public static final String SELECT_MAP = "select_map";
    static final String TAKE_PHOTO_ACTION = "take_photo_action";
    public NBSTraceUnit _nbs_trace;
    private boolean mCropPhotoAction;
    private boolean mCropState;
    private Drawable mDefaultDrawable;
    private boolean mEditPhotoAction;
    private File mEditPhotoCacheFile;
    private FloatingActionButton mFabCrop;
    private ImageView mIvBack;
    private ImageView mIvCrop;
    private CropImageView mIvCropPhoto;
    private ImageView mIvPreView;
    private ImageView mIvRotate;
    private PhotoView mIvSourcePhoto;
    private ImageView mIvTakePhoto;
    private LinearLayout mLlGallery;
    private HorizontalListView mLvGallery;
    private PhotoEditListAdapter mPhotoEditListAdapter;
    private ArrayList<PhotoInfo> mPhotoList;
    private LinkedHashMap<Integer, PhotoTempModel> mPhotoTempMap;
    private ProgressDialog mProgressDialog;
    private boolean mRotating;
    private ArrayList<PhotoInfo> mSelectPhotoList;
    private LinearLayout mTitlebar;
    private TextView mTvEmptyView;
    private TextView mTvTitle;
    private final int CROP_SUC = 1;
    private final int CROP_FAIL = 2;
    private final int UPDATE_PATH = 3;
    private int mSelectIndex = 0;
    private Handler mHanlder = new Handler() { // from class: cn.finalteam.galleryfinal.PhotoEditActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                String str = (String) message.obj;
                PhotoInfo photoInfo = (PhotoInfo) PhotoEditActivity.this.mPhotoList.get(PhotoEditActivity.this.mSelectIndex);
                try {
                    for (Map.Entry entry : PhotoEditActivity.this.mPhotoTempMap.entrySet()) {
                        if (((Integer) entry.getKey()).intValue() == photoInfo.getPhotoId()) {
                            PhotoTempModel photoTempModel = (PhotoTempModel) entry.getValue();
                            photoTempModel.setSourcePath(str);
                            photoTempModel.setOrientation(0);
                        }
                    }
                } catch (Exception unused) {
                }
                PhotoEditActivity photoEditActivity = PhotoEditActivity.this;
                photoEditActivity.toast(photoEditActivity.getString(C1656R.string.crop_suc));
                Message obtainMessage = PhotoEditActivity.this.mHanlder.obtainMessage();
                obtainMessage.what = 3;
                obtainMessage.obj = str;
                PhotoEditActivity.this.mHanlder.sendMessage(obtainMessage);
            } else if (message.what == 2) {
                PhotoEditActivity photoEditActivity2 = PhotoEditActivity.this;
                photoEditActivity2.toast(photoEditActivity2.getString(C1656R.string.crop_fail));
            } else if (message.what == 3) {
                if (PhotoEditActivity.this.mPhotoList.get(PhotoEditActivity.this.mSelectIndex) != null) {
                    PhotoInfo photoInfo2 = (PhotoInfo) PhotoEditActivity.this.mPhotoList.get(PhotoEditActivity.this.mSelectIndex);
                    String str2 = (String) message.obj;
                    try {
                        Iterator it = PhotoEditActivity.this.mSelectPhotoList.iterator();
                        while (it.hasNext()) {
                            PhotoInfo photoInfo3 = (PhotoInfo) it.next();
                            if (photoInfo3 != null && photoInfo3.getPhotoId() == photoInfo2.getPhotoId()) {
                                photoInfo3.setPhotoPath(str2);
                            }
                        }
                    } catch (Exception unused2) {
                    }
                    photoInfo2.setPhotoPath(str2);
                    PhotoEditActivity.this.loadImage(photoInfo2);
                    PhotoEditActivity.this.mPhotoEditListAdapter.notifyDataSetChanged();
                }
                if (GalleryFinal.getFunctionConfig().isForceCrop() && !GalleryFinal.getFunctionConfig().isForceCropEdit()) {
                    PhotoEditActivity.this.resultAction();
                }
            }
            PhotoEditActivity.this.corpPageState(false);
            PhotoEditActivity.this.mCropState = false;
            PhotoEditActivity.this.mTvTitle.setText(C1656R.string.photo_edit);
        }
    };

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selectPhotoMap", this.mSelectPhotoList);
        bundle.putSerializable("editPhotoCacheFile", this.mEditPhotoCacheFile);
        bundle.putSerializable("photoTempMap", this.mPhotoTempMap);
        bundle.putInt("selectIndex", this.mSelectIndex);
        bundle.putBoolean("cropState", this.mCropState);
        bundle.putBoolean("rotating", this.mRotating);
        bundle.putBoolean("takePhotoAction", this.mTakePhotoAction);
        bundle.putBoolean("cropPhotoAction", this.mCropPhotoAction);
        bundle.putBoolean("editPhotoAction", this.mEditPhotoAction);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mSelectPhotoList = (ArrayList) getIntent().getSerializableExtra("selectPhotoMap");
        this.mEditPhotoCacheFile = (File) bundle.getSerializable("editPhotoCacheFile");
        this.mPhotoTempMap = new LinkedHashMap<>((HashMap) getIntent().getSerializableExtra("results"));
        this.mSelectIndex = bundle.getInt("selectIndex");
        this.mCropState = bundle.getBoolean("cropState");
        this.mRotating = bundle.getBoolean("rotating");
        this.mTakePhotoAction = bundle.getBoolean("takePhotoAction");
        this.mCropPhotoAction = bundle.getBoolean("cropPhotoAction");
        this.mEditPhotoAction = bundle.getBoolean("editPhotoAction");
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.CropImageActivity, cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        if (GalleryFinal.getFunctionConfig() == null || GalleryFinal.getGalleryTheme() == null) {
            resultFailureDelayed(getString(C1656R.string.please_reopen_gf), true);
        } else {
            setContentView(C1656R.C1660layout.gf_activity_photo_edit);
            GallryUIUtils.setStatusBarColor(this);
            this.mDefaultDrawable = getResources().getDrawable(C1656R.C1658drawable.ic_gf_default_photo);
            this.mSelectPhotoList = (ArrayList) getIntent().getSerializableExtra(SELECT_MAP);
            this.mTakePhotoAction = getIntent().getBooleanExtra(TAKE_PHOTO_ACTION, false);
            this.mCropPhotoAction = getIntent().getBooleanExtra(CROP_PHOTO_ACTION, false);
            this.mEditPhotoAction = getIntent().getBooleanExtra(EDIT_PHOTO_ACTION, false);
            if (getIntent().getBooleanExtra("EmotionMainFragment", false)) {
                findViewById(C1656R.C1659id.iv_delete_photo).setVisibility(0);
                findViewById(C1656R.C1659id.iv_delete_photo).setOnClickListener(new View.OnClickListener() { // from class: cn.finalteam.galleryfinal.PhotoEditActivity.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        ArrayList<PhotoInfo> arrayList = new ArrayList<>();
                        PhotoInfo photoInfo = new PhotoInfo();
                        photoInfo.setPhotoPath("clear");
                        arrayList.add(photoInfo);
                        PhotoEditActivity.this.resultData(arrayList);
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
            if (this.mSelectPhotoList == null) {
                this.mSelectPhotoList = new ArrayList<>();
            }
            this.mPhotoTempMap = new LinkedHashMap<>();
            this.mPhotoList = new ArrayList<>(this.mSelectPhotoList);
            this.mEditPhotoCacheFile = GalleryFinal.getCoreConfig().getEditPhotoCacheFolder();
            if (this.mPhotoList == null) {
                this.mPhotoList = new ArrayList<>();
            }
            Iterator<PhotoInfo> it = this.mPhotoList.iterator();
            while (it.hasNext()) {
                PhotoInfo next = it.next();
                this.mPhotoTempMap.put(Integer.valueOf(next.getPhotoId()), new PhotoTempModel(next.getPhotoPath()));
            }
            findViews();
            setListener();
            this.mPhotoEditListAdapter = new PhotoEditListAdapter(this, this.mPhotoList, this.mScreenWidth);
            this.mLvGallery.setAdapter((ListAdapter) this.mPhotoEditListAdapter);
            try {
                File file = new File(this.mEditPhotoCacheFile, ".nomedia");
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (GalleryFinal.getFunctionConfig().isCamera()) {
                this.mIvTakePhoto.setVisibility(0);
            }
            if (GalleryFinal.getFunctionConfig().isCrop()) {
                this.mIvCrop.setVisibility(0);
            }
            if (GalleryFinal.getFunctionConfig().isRotate()) {
                this.mIvRotate.setVisibility(0);
            }
            if (!GalleryFinal.getFunctionConfig().isMutiSelect()) {
                this.mLlGallery.setVisibility(8);
            }
            initCrop(this.mIvCropPhoto, GalleryFinal.getFunctionConfig().isCropSquare(), GalleryFinal.getFunctionConfig().getCropWidth(), GalleryFinal.getFunctionConfig().getCropHeight());
            if (this.mPhotoList.size() > 0 && !this.mTakePhotoAction) {
                loadImage(this.mPhotoList.get(0));
            }
            if (this.mTakePhotoAction) {
                takePhotoAction();
            }
            if (this.mCropPhotoAction) {
                this.mIvCrop.performClick();
                if (!GalleryFinal.getFunctionConfig().isRotate() && !GalleryFinal.getFunctionConfig().isCamera()) {
                    this.mIvCrop.setVisibility(8);
                }
            } else {
                hasForceCrop();
            }
            if (GalleryFinal.getFunctionConfig().isEnablePreview()) {
                this.mIvPreView.setVisibility(0);
            }
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void setTheme() {
        this.mIvBack.setImageResource(GalleryFinal.getGalleryTheme().getIconBack());
        if (GalleryFinal.getGalleryTheme().getIconBack() == C1656R.C1658drawable.ic_gf_back) {
            this.mIvBack.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvTakePhoto.setImageResource(GalleryFinal.getGalleryTheme().getIconCamera());
        if (GalleryFinal.getGalleryTheme().getIconCamera() == C1656R.C1658drawable.ic_gf_camera) {
            this.mIvTakePhoto.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvCrop.setImageResource(GalleryFinal.getGalleryTheme().getIconCrop());
        if (GalleryFinal.getGalleryTheme().getIconCrop() == C1656R.C1658drawable.ic_gf_crop) {
            this.mIvCrop.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvPreView.setImageResource(GalleryFinal.getGalleryTheme().getIconPreview());
        if (GalleryFinal.getGalleryTheme().getIconPreview() == C1656R.C1658drawable.ic_gf_preview) {
            this.mIvPreView.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvRotate.setImageResource(GalleryFinal.getGalleryTheme().getIconRotate());
        if (GalleryFinal.getGalleryTheme().getIconRotate() == C1656R.C1658drawable.ic_gf_rotate) {
            this.mIvRotate.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        if (GalleryFinal.getGalleryTheme().getEditPhotoBgTexture() != null) {
            this.mIvSourcePhoto.setBackgroundDrawable(GalleryFinal.getGalleryTheme().getEditPhotoBgTexture());
            this.mIvCropPhoto.setBackgroundDrawable(GalleryFinal.getGalleryTheme().getEditPhotoBgTexture());
        }
        this.mFabCrop.setIcon(GalleryFinal.getGalleryTheme().getIconFab());
        this.mTitlebar.setBackgroundColor(GalleryFinal.getGalleryTheme().getTitleBarBgColor());
        this.mTvTitle.setTextColor(GalleryFinal.getGalleryTheme().getTitleBarTextColor());
        this.mFabCrop.setColorPressed(GalleryFinal.getGalleryTheme().getFabPressedColor());
        this.mFabCrop.setColorNormal(GalleryFinal.getGalleryTheme().getFabNornalColor());
    }

    private void findViews() {
        this.mIvTakePhoto = (ImageView) findViewById(C1656R.C1659id.iv_take_photo);
        this.mIvCropPhoto = (CropImageView) findViewById(C1656R.C1659id.iv_crop_photo);
        this.mIvSourcePhoto = (PhotoView) findViewById(C1656R.C1659id.iv_source_photo);
        this.mLvGallery = (HorizontalListView) findViewById(C1656R.C1659id.lv_gallery);
        this.mLlGallery = (LinearLayout) findViewById(C1656R.C1659id.ll_gallery);
        this.mIvBack = (ImageView) findViewById(C1656R.C1659id.iv_back);
        this.mTvEmptyView = (TextView) findViewById(C1656R.C1659id.tv_empty_view);
        this.mFabCrop = (FloatingActionButton) findViewById(C1656R.C1659id.fab_crop);
        this.mIvCrop = (ImageView) findViewById(C1656R.C1659id.iv_crop);
        this.mIvRotate = (ImageView) findViewById(C1656R.C1659id.iv_rotate);
        this.mTvTitle = (TextView) findViewById(C1656R.C1659id.tv_title);
        this.mTitlebar = (LinearLayout) findViewById(C1656R.C1659id.titlebar);
        this.mIvPreView = (ImageView) findViewById(C1656R.C1659id.iv_preview);
    }

    private void setListener() {
        this.mIvTakePhoto.setOnClickListener(this);
        this.mIvBack.setOnClickListener(this);
        this.mLvGallery.setOnItemClickListener(this);
        this.mFabCrop.setOnClickListener(this);
        this.mIvCrop.setOnClickListener(this);
        this.mIvRotate.setOnClickListener(this);
        this.mIvPreView.setOnClickListener(this);
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity
    protected void takeResult(PhotoInfo photoInfo) {
        if (!GalleryFinal.getFunctionConfig().isMutiSelect()) {
            this.mPhotoList.clear();
            this.mSelectPhotoList.clear();
        }
        this.mPhotoList.add(0, photoInfo);
        this.mSelectPhotoList.add(photoInfo);
        this.mPhotoTempMap.put(Integer.valueOf(photoInfo.getPhotoId()), new PhotoTempModel(photoInfo.getPhotoPath()));
        if (!GalleryFinal.getFunctionConfig().isEditPhoto() && this.mTakePhotoAction) {
            resultAction();
            return;
        }
        if (GalleryFinal.getFunctionConfig().isEnablePreview()) {
            this.mIvPreView.setVisibility(0);
        }
        this.mPhotoEditListAdapter.notifyDataSetChanged();
        PhotoSelectActivity photoSelectActivity = (PhotoSelectActivity) ActivityManager.getActivityManager().getActivity(PhotoSelectActivity.class.getName());
        if (photoSelectActivity != null) {
            photoSelectActivity.takeRefreshGallery(photoInfo, true);
        }
        loadImage(photoInfo);
        hasForceCrop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadImage(PhotoInfo photoInfo) {
        this.mTvEmptyView.setVisibility(8);
        this.mIvSourcePhoto.setVisibility(0);
        this.mIvCropPhoto.setVisibility(8);
        String photoPath = photoInfo != null ? photoInfo.getPhotoPath() : "";
        if (GalleryFinal.getFunctionConfig().isCrop()) {
            setSourceUri(Uri.fromFile(new File(photoPath)));
        }
        GalleryFinal.getCoreConfig().getImageLoader().displayImage(this, photoPath, this.mIvSourcePhoto, this.mDefaultDrawable, this.mScreenWidth, this.mScreenHeight);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
        r0.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deleteIndex(int r4, cn.finalteam.galleryfinal.model.PhotoInfo r5) {
        /*
            r3 = this;
            if (r5 == 0) goto L3c
            cn.finalteam.toolsfinal.ActivityManager r0 = cn.finalteam.toolsfinal.ActivityManager.getActivityManager()
            java.lang.Class<cn.finalteam.galleryfinal.PhotoSelectActivity> r1 = cn.finalteam.galleryfinal.PhotoSelectActivity.class
            java.lang.String r1 = r1.getName()
            android.app.Activity r0 = r0.getActivity(r1)
            cn.finalteam.galleryfinal.PhotoSelectActivity r0 = (cn.finalteam.galleryfinal.PhotoSelectActivity) r0
            if (r0 == 0) goto L1b
            int r1 = r5.getPhotoId()
            r0.deleteSelect(r1)
        L1b:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mSelectPhotoList     // Catch: java.lang.Exception -> L3c
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L3c
        L21:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> L3c
            if (r1 == 0) goto L3c
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> L3c
            cn.finalteam.galleryfinal.model.PhotoInfo r1 = (cn.finalteam.galleryfinal.model.PhotoInfo) r1     // Catch: java.lang.Exception -> L3c
            if (r1 == 0) goto L21
            int r1 = r1.getPhotoId()     // Catch: java.lang.Exception -> L3c
            int r2 = r5.getPhotoId()     // Catch: java.lang.Exception -> L3c
            if (r1 != r2) goto L21
            r0.remove()     // Catch: java.lang.Exception -> L3c
        L3c:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r5 = r3.mPhotoList
            int r5 = r5.size()
            r0 = 0
            if (r5 != 0) goto L65
            r3.mSelectIndex = r0
            android.widget.TextView r4 = r3.mTvEmptyView
            int r5 = cn.finalteam.galleryfinal.C1656R.string.no_photo
            r4.setText(r5)
            android.widget.TextView r4 = r3.mTvEmptyView
            r4.setVisibility(r0)
            cn.finalteam.galleryfinal.widget.zoonview.PhotoView r4 = r3.mIvSourcePhoto
            r5 = 8
            r4.setVisibility(r5)
            cn.finalteam.galleryfinal.widget.crop.CropImageView r4 = r3.mIvCropPhoto
            r4.setVisibility(r5)
            android.widget.ImageView r4 = r3.mIvPreView
            r4.setVisibility(r5)
            goto L86
        L65:
            if (r4 != 0) goto L6a
            r3.mSelectIndex = r0
            goto L79
        L6a:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r5 = r3.mPhotoList
            int r5 = r5.size()
            if (r4 != r5) goto L77
            int r4 = r4 + (-1)
            r3.mSelectIndex = r4
            goto L79
        L77:
            r3.mSelectIndex = r4
        L79:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r4 = r3.mPhotoList
            int r5 = r3.mSelectIndex
            java.lang.Object r4 = r4.get(r5)
            cn.finalteam.galleryfinal.model.PhotoInfo r4 = (cn.finalteam.galleryfinal.model.PhotoInfo) r4
            r3.loadImage(r4)
        L86:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.PhotoEditActivity.deleteIndex(int, cn.finalteam.galleryfinal.model.PhotoInfo):void");
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        NBSActionInstrumentation.onItemClickEnter(view, i, this);
        Tracker.onItemClick(adapterView, view, i, j);
        this.mSelectIndex = i;
        loadImage(this.mPhotoList.get(i));
        NBSActionInstrumentation.onItemClickExit();
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.CropImageActivity
    public void setCropSaveSuccess(File file) {
        Message obtainMessage = this.mHanlder.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = file.getAbsolutePath();
        this.mHanlder.sendMessage(obtainMessage);
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.CropImageActivity
    public void setCropSaveException(Throwable th) {
        this.mHanlder.sendEmptyMessage(2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        File file;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == C1656R.C1659id.fab_crop) {
            if (this.mPhotoList.size() == 0) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            } else if (this.mCropState) {
                System.gc();
                PhotoInfo photoInfo = this.mPhotoList.get(this.mSelectIndex);
                try {
                    String extension = FilenameUtils.getExtension(photoInfo.getPhotoPath());
                    if (GalleryFinal.getFunctionConfig().isCropReplaceSource()) {
                        file = new File(photoInfo.getPhotoPath());
                    } else {
                        File file2 = this.mEditPhotoCacheFile;
                        file = new File(file2, Utils.getFileName(photoInfo.getPhotoPath()) + "_crop." + extension);
                    }
                    FileUtils.mkdirs(file.getParentFile());
                    onSaveClicked(file);
                } catch (Exception e) {
                    ILogger.m22037e(e);
                }
            } else {
                resultAction();
            }
        } else if (id == C1656R.C1659id.iv_crop) {
            if (this.mPhotoList.size() > 0) {
                String extension2 = FilenameUtils.getExtension(this.mPhotoList.get(this.mSelectIndex).getPhotoPath());
                if (StringUtils.isEmpty(extension2) || (!extension2.equalsIgnoreCase("png") && !extension2.equalsIgnoreCase("jpg") && !extension2.equalsIgnoreCase("jpeg"))) {
                    toast(getString(C1656R.string.edit_letoff_photo_format));
                } else {
                    if (this.mCropState) {
                        setCropEnabled(false);
                        corpPageState(false);
                        this.mTvTitle.setText(C1656R.string.photo_edit);
                    } else {
                        corpPageState(true);
                        setCropEnabled(true);
                        this.mTvTitle.setText(C1656R.string.photo_crop);
                    }
                    this.mCropState = !this.mCropState;
                }
            }
        } else if (id == C1656R.C1659id.iv_rotate) {
            rotatePhoto();
        } else if (id == C1656R.C1659id.iv_take_photo) {
            if (GalleryFinal.getFunctionConfig().isMutiSelect() && GalleryFinal.getFunctionConfig().getMaxSize() == this.mSelectPhotoList.size()) {
                toast(getString(C1656R.string.select_max_tips));
            } else {
                takePhotoAction();
            }
        } else if (id == C1656R.C1659id.iv_back) {
            if (this.mCropState && ((!this.mCropPhotoAction || GalleryFinal.getFunctionConfig().isRotate() || GalleryFinal.getFunctionConfig().isCamera()) && GalleryFinal.getFunctionConfig().isForceCrop() && GalleryFinal.getFunctionConfig().isForceCropEdit())) {
                this.mIvCrop.performClick();
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            finish();
        } else if (id == C1656R.C1659id.iv_preview) {
            Intent intent = new Intent(this, PhotoPreviewActivity.class);
            intent.putExtra("photo_list", this.mSelectPhotoList);
            startActivity(intent);
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resultAction() {
        resultData(this.mSelectPhotoList);
    }

    private void hasForceCrop() {
        if (GalleryFinal.getFunctionConfig().isForceCrop()) {
            this.mIvCrop.performClick();
            if (GalleryFinal.getFunctionConfig().isForceCropEdit()) {
                return;
            }
            this.mIvCrop.setVisibility(8);
        }
    }

    /* JADX WARN: Type inference failed for: r0v19, types: [cn.finalteam.galleryfinal.PhotoEditActivity$3] */
    private void rotatePhoto() {
        final File file;
        if (this.mPhotoList.size() <= 0 || this.mPhotoList.get(this.mSelectIndex) == null || this.mRotating) {
            return;
        }
        final PhotoInfo photoInfo = this.mPhotoList.get(this.mSelectIndex);
        final String extension = FilenameUtils.getExtension(photoInfo.getPhotoPath());
        if (StringUtils.isEmpty(extension) || (!extension.equalsIgnoreCase("png") && !extension.equalsIgnoreCase("jpg") && !extension.equalsIgnoreCase("jpeg"))) {
            toast(getString(C1656R.string.edit_letoff_photo_format));
            return;
        }
        this.mRotating = true;
        if (photoInfo != null) {
            final PhotoTempModel photoTempModel = this.mPhotoTempMap.get(Integer.valueOf(photoInfo.getPhotoId()));
            final String sourcePath = photoTempModel.getSourcePath();
            if (GalleryFinal.getFunctionConfig().isRotateReplaceSource()) {
                file = new File(sourcePath);
            } else {
                File file2 = this.mEditPhotoCacheFile;
                file = new File(file2, Utils.getFileName(sourcePath) + "_rotate." + extension);
            }
            new AsyncTask<Void, Void, Bitmap>() { // from class: cn.finalteam.galleryfinal.PhotoEditActivity.3
                @Override // android.os.AsyncTask
                protected void onPreExecute() {
                    super.onPreExecute();
                    PhotoEditActivity.this.mTvEmptyView.setVisibility(0);
                    PhotoEditActivity photoEditActivity = PhotoEditActivity.this;
                    photoEditActivity.mProgressDialog = ProgressDialog.show(photoEditActivity, "", photoEditActivity.getString(C1656R.string.waiting), true, false);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Bitmap doInBackground(Void... voidArr) {
                    Bitmap.CompressFormat compressFormat;
                    Bitmap rotateBitmap = Utils.rotateBitmap(sourcePath, GalleryFinal.getFunctionConfig().isRotateReplaceSource() ? 90 : 90 + photoTempModel.getOrientation(), PhotoEditActivity.this.mScreenWidth, PhotoEditActivity.this.mScreenHeight);
                    if (rotateBitmap != null) {
                        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
                            compressFormat = Bitmap.CompressFormat.JPEG;
                        } else {
                            compressFormat = Bitmap.CompressFormat.PNG;
                        }
                        Utils.saveBitmap(rotateBitmap, compressFormat, file);
                    }
                    return rotateBitmap;
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute((AsyncTaskC16513) bitmap);
                    if (PhotoEditActivity.this.mProgressDialog != null) {
                        PhotoEditActivity.this.mProgressDialog.dismiss();
                        PhotoEditActivity.this.mProgressDialog = null;
                    }
                    if (bitmap == null) {
                        PhotoEditActivity.this.mTvEmptyView.setText(C1656R.string.no_photo);
                    } else {
                        bitmap.recycle();
                        PhotoEditActivity.this.mTvEmptyView.setVisibility(8);
                        if (!GalleryFinal.getFunctionConfig().isRotateReplaceSource()) {
                            int orientation = photoTempModel.getOrientation() + 90;
                            if (orientation == 360) {
                                orientation = 0;
                            }
                            photoTempModel.setOrientation(orientation);
                        }
                        Message obtainMessage = PhotoEditActivity.this.mHanlder.obtainMessage();
                        obtainMessage.what = 3;
                        obtainMessage.obj = file.getAbsolutePath();
                        PhotoEditActivity.this.mHanlder.sendMessage(obtainMessage);
                    }
                    PhotoEditActivity.this.loadImage(photoInfo);
                    PhotoEditActivity.this.mRotating = false;
                }
            }.execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void corpPageState(boolean z) {
        if (z) {
            this.mIvSourcePhoto.setVisibility(8);
            this.mIvCropPhoto.setVisibility(0);
            this.mLlGallery.setVisibility(8);
            if (GalleryFinal.getFunctionConfig().isCrop()) {
                this.mIvCrop.setVisibility(0);
            }
            if (GalleryFinal.getFunctionConfig().isRotate()) {
                this.mIvRotate.setVisibility(8);
            }
            if (GalleryFinal.getFunctionConfig().isCamera()) {
                this.mIvTakePhoto.setVisibility(8);
                return;
            }
            return;
        }
        this.mIvSourcePhoto.setVisibility(0);
        this.mIvCropPhoto.setVisibility(8);
        if (GalleryFinal.getFunctionConfig().isCrop()) {
            this.mIvCrop.setVisibility(0);
        }
        if (GalleryFinal.getFunctionConfig().isRotate()) {
            this.mIvRotate.setVisibility(0);
        }
        if (GalleryFinal.getFunctionConfig().isCamera()) {
            this.mIvTakePhoto.setVisibility(8);
        }
        if (GalleryFinal.getFunctionConfig().isMutiSelect()) {
            this.mLlGallery.setVisibility(0);
        }
    }

    @Override // cn.finalteam.galleryfinal.widget.crop.CropImageActivity, cn.finalteam.galleryfinal.widget.crop.MonitoredActivity, cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        RecycleViewBitmapUtils.recycleImageView(this.mIvCropPhoto);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.mCropState && ((!this.mCropPhotoAction || GalleryFinal.getFunctionConfig().isRotate() || GalleryFinal.getFunctionConfig().isCamera()) && GalleryFinal.getFunctionConfig().isForceCrop() && GalleryFinal.getFunctionConfig().isForceCropEdit())) {
            this.mIvCrop.performClick();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }
}
