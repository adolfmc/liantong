package cn.finalteam.galleryfinal;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.adapter.FolderListAdapter;
import cn.finalteam.galleryfinal.adapter.PhotoListAdapter;
import cn.finalteam.galleryfinal.model.PhotoFolderInfo;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.permission.AfterPermissionGranted;
import cn.finalteam.galleryfinal.permission.EasyPermissions;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import cn.finalteam.galleryfinal.utils.PhotoTools;
import cn.finalteam.galleryfinal.widget.FloatingActionButton;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.StringUtils;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoSelectActivity extends PhotoBaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static String TAKEPHOTO = "TAKEPHOTO";
    public NBSTraceUnit _nbs_trace;
    private List<PhotoFolderInfo> mAllPhotoFolderList;
    private List<PhotoInfo> mCurPhotoList;
    private FloatingActionButton mFabOk;
    private FolderListAdapter mFolderListAdapter;
    private GridView mGvPhotoList;
    private ImageView mIvBack;
    private ImageView mIvClear;
    private ImageView mIvFolderArrow;
    private ImageView mIvPreView;
    private ImageView mIvTakePhoto;
    private LinearLayout mLlFolderPanel;
    private LinearLayout mLlTitle;
    private ListView mLvFolderList;
    private PhotoListAdapter mPhotoListAdapter;
    private RelativeLayout mTitlebar;
    private TextView mTvChooseCount;
    private TextView mTvEmptyView;
    private TextView mTvSubTitle;
    private TextView mTvTitle;
    private final int HANLDER_TAKE_PHOTO_EVENT = 1000;
    private final int HANDLER_REFRESH_LIST_EVENT = 1002;
    private boolean mHasRefreshGallery = false;
    private ArrayList<PhotoInfo> mSelectPhotoList = new ArrayList<>();
    private Handler mHanlder = new Handler() { // from class: cn.finalteam.galleryfinal.PhotoSelectActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1000) {
                PhotoSelectActivity.this.takeRefreshGallery((PhotoInfo) message.obj);
                PhotoSelectActivity.this.refreshSelectCount();
            } else if (message.what == 1002) {
                PhotoSelectActivity.this.refreshSelectCount();
                PhotoSelectActivity.this.mPhotoListAdapter.notifyDataSetChanged();
                PhotoSelectActivity.this.mFolderListAdapter.notifyDataSetChanged();
                if (((PhotoFolderInfo) PhotoSelectActivity.this.mAllPhotoFolderList.get(0)).getPhotoList() == null || ((PhotoFolderInfo) PhotoSelectActivity.this.mAllPhotoFolderList.get(0)).getPhotoList().size() == 0) {
                    PhotoSelectActivity.this.mTvEmptyView.setText(C1656R.string.no_photo);
                }
                PhotoSelectActivity.this.mGvPhotoList.setEnabled(true);
                PhotoSelectActivity.this.mLlTitle.setEnabled(true);
                PhotoSelectActivity.this.mIvTakePhoto.setEnabled(true);
            }
        }
    };

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selectPhotoMap", this.mSelectPhotoList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mSelectPhotoList = (ArrayList) getIntent().getSerializableExtra("selectPhotoMap");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        if (GalleryFinal.getFunctionConfig() == null || GalleryFinal.getGalleryTheme() == null) {
            resultFailureDelayed(getString(C1656R.string.please_reopen_gf), true);
        } else {
            setContentView(C1656R.C1660layout.gf_activity_photo_select);
            GallryUIUtils.setStatusBarColor(this);
            mPhotoTargetFolder = null;
            findViews();
            setListener();
            this.mAllPhotoFolderList = new ArrayList();
            this.mFolderListAdapter = new FolderListAdapter(this, this.mAllPhotoFolderList, GalleryFinal.getFunctionConfig());
            this.mLvFolderList.setAdapter((ListAdapter) this.mFolderListAdapter);
            this.mCurPhotoList = new ArrayList();
            this.mPhotoListAdapter = new PhotoListAdapter(this, this.mCurPhotoList, this.mSelectPhotoList, this.mScreenWidth);
            this.mGvPhotoList.setAdapter((ListAdapter) this.mPhotoListAdapter);
            if (GalleryFinal.getFunctionConfig().isMutiSelect()) {
                this.mTvChooseCount.setVisibility(0);
                this.mFabOk.setVisibility(0);
            }
            this.mGvPhotoList.setEmptyView(this.mTvEmptyView);
            if (GalleryFinal.getFunctionConfig().isCamera()) {
                this.mIvTakePhoto.setVisibility(0);
            } else {
                this.mIvTakePhoto.setVisibility(8);
            }
            refreshSelectCount();
            requestGalleryPermission();
            this.mGvPhotoList.setOnScrollListener(GalleryFinal.getCoreConfig().getPauseOnScrollListener());
        }
        Global.mPhotoSelectActivity = this;
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void setTheme() {
        this.mIvBack.setImageResource(GalleryFinal.getGalleryTheme().getIconBack());
        if (GalleryFinal.getGalleryTheme().getIconBack() == C1656R.C1658drawable.ic_gf_back) {
            this.mIvBack.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvFolderArrow.setImageResource(GalleryFinal.getGalleryTheme().getIconFolderArrow());
        if (GalleryFinal.getGalleryTheme().getIconFolderArrow() == C1656R.C1658drawable.ic_gf_triangle_arrow) {
            this.mIvFolderArrow.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvClear.setImageResource(GalleryFinal.getGalleryTheme().getIconClear());
        if (GalleryFinal.getGalleryTheme().getIconClear() == C1656R.C1658drawable.ic_gf_clear) {
            this.mIvClear.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvPreView.setImageResource(GalleryFinal.getGalleryTheme().getIconPreview());
        if (GalleryFinal.getGalleryTheme().getIconPreview() == C1656R.C1658drawable.ic_gf_preview) {
            this.mIvPreView.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mIvTakePhoto.setImageResource(GalleryFinal.getGalleryTheme().getIconCamera());
        if (GalleryFinal.getGalleryTheme().getIconCamera() == C1656R.C1658drawable.ic_gf_camera) {
            this.mIvTakePhoto.setColorFilter(GalleryFinal.getGalleryTheme().getTitleBarIconColor());
        }
        this.mFabOk.setIcon(GalleryFinal.getGalleryTheme().getIconFab());
        this.mTitlebar.setBackgroundColor(GalleryFinal.getGalleryTheme().getTitleBarBgColor());
        this.mTvSubTitle.setTextColor(GalleryFinal.getGalleryTheme().getTitleBarTextColor());
        this.mTvTitle.setTextColor(GalleryFinal.getGalleryTheme().getTitleBarTextColor());
        this.mTvChooseCount.setTextColor(GalleryFinal.getGalleryTheme().getTitleBarTextColor());
        this.mFabOk.setColorPressed(GalleryFinal.getGalleryTheme().getFabPressedColor());
        this.mFabOk.setColorNormal(GalleryFinal.getGalleryTheme().getFabNornalColor());
    }

    private void findViews() {
        this.mGvPhotoList = (GridView) findViewById(C1656R.C1659id.gv_photo_list);
        this.mLvFolderList = (ListView) findViewById(C1656R.C1659id.lv_folder_list);
        this.mTvSubTitle = (TextView) findViewById(C1656R.C1659id.tv_sub_title);
        this.mLlFolderPanel = (LinearLayout) findViewById(C1656R.C1659id.ll_folder_panel);
        this.mIvTakePhoto = (ImageView) findViewById(C1656R.C1659id.iv_take_photo);
        this.mTvChooseCount = (TextView) findViewById(C1656R.C1659id.tv_choose_count);
        this.mIvBack = (ImageView) findViewById(C1656R.C1659id.iv_back);
        this.mFabOk = (FloatingActionButton) findViewById(C1656R.C1659id.fab_ok);
        this.mTvEmptyView = (TextView) findViewById(C1656R.C1659id.tv_empty_view);
        this.mLlTitle = (LinearLayout) findViewById(C1656R.C1659id.ll_title);
        this.mIvClear = (ImageView) findViewById(C1656R.C1659id.iv_clear);
        this.mTitlebar = (RelativeLayout) findViewById(C1656R.C1659id.titlebar);
        this.mTvTitle = (TextView) findViewById(C1656R.C1659id.tv_title);
        this.mIvFolderArrow = (ImageView) findViewById(C1656R.C1659id.iv_folder_arrow);
        this.mIvPreView = (ImageView) findViewById(C1656R.C1659id.iv_preview);
    }

    private void setListener() {
        this.mLlTitle.setOnClickListener(this);
        this.mIvTakePhoto.setOnClickListener(this);
        this.mIvBack.setOnClickListener(this);
        this.mIvFolderArrow.setOnClickListener(this);
        this.mLvFolderList.setOnItemClickListener(this);
        this.mGvPhotoList.setOnItemClickListener(this);
        this.mFabOk.setOnClickListener(this);
        this.mIvClear.setOnClickListener(this);
        this.mIvPreView.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001a, code lost:
        r0.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void deleteSelect(int r3) {
        /*
            r2 = this;
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r2.mSelectPhotoList     // Catch: java.lang.Exception -> L1d
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L1d
        L6:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> L1d
            if (r1 == 0) goto L1d
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> L1d
            cn.finalteam.galleryfinal.model.PhotoInfo r1 = (cn.finalteam.galleryfinal.model.PhotoInfo) r1     // Catch: java.lang.Exception -> L1d
            if (r1 == 0) goto L6
            int r1 = r1.getPhotoId()     // Catch: java.lang.Exception -> L1d
            if (r1 != r3) goto L6
            r0.remove()     // Catch: java.lang.Exception -> L1d
        L1d:
            r2.refreshAdapter()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.PhotoSelectActivity.deleteSelect(int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAdapter() {
        this.mHanlder.sendEmptyMessageDelayed(1002, 100L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void takeRefreshGallery(PhotoInfo photoInfo, boolean z) {
        if (isFinishing() || photoInfo == null) {
            return;
        }
        Message obtainMessage = this.mHanlder.obtainMessage();
        obtainMessage.obj = photoInfo;
        obtainMessage.what = 1000;
        this.mSelectPhotoList.add(photoInfo);
        this.mHanlder.sendMessageDelayed(obtainMessage, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takeRefreshGallery(final PhotoInfo photoInfo) {
        this.mCurPhotoList.add(0, photoInfo);
        this.mPhotoListAdapter.notifyDataSetChanged();
        if (this.mAllPhotoFolderList.size() == 0) {
            new Handler().postDelayed(new Runnable() { // from class: cn.finalteam.galleryfinal.PhotoSelectActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    PhotoSelectActivity.this.take(photoInfo);
                }
            }, 1000L);
        } else {
            take(photoInfo);
        }
    }

    void take(PhotoInfo photoInfo) {
        List<PhotoFolderInfo> list = this.mAllPhotoFolderList;
        if (list == null || list.size() <= 0) {
            return;
        }
        List<PhotoInfo> photoList = this.mAllPhotoFolderList.get(0).getPhotoList();
        if (photoList == null) {
            photoList = new ArrayList<>();
        }
        photoList.add(0, photoInfo);
        this.mAllPhotoFolderList.get(0).setPhotoList(photoList);
        if (this.mFolderListAdapter.getSelectFolder() != null) {
            PhotoFolderInfo selectFolder = this.mFolderListAdapter.getSelectFolder();
            List<PhotoInfo> photoList2 = selectFolder.getPhotoList();
            if (photoList2 == null) {
                photoList2 = new ArrayList<>();
            }
            photoList2.add(0, photoInfo);
            if (photoList2.size() == 1) {
                selectFolder.setCoverPhoto(photoInfo);
            }
            this.mFolderListAdapter.getSelectFolder().setPhotoList(photoList2);
        } else {
            String parent = new File(photoInfo.getPhotoPath()).getParent();
            for (int i = 1; i < this.mAllPhotoFolderList.size(); i++) {
                PhotoFolderInfo photoFolderInfo = this.mAllPhotoFolderList.get(i);
                if (TextUtils.equals(parent, StringUtils.isEmpty(photoInfo.getPhotoPath()) ? null : new File(photoInfo.getPhotoPath()).getParent())) {
                    List<PhotoInfo> photoList3 = photoFolderInfo.getPhotoList();
                    if (photoList3 == null) {
                        photoList3 = new ArrayList<>();
                    }
                    photoList3.add(0, photoInfo);
                    photoFolderInfo.setPhotoList(photoList3);
                    if (photoList3.size() == 1) {
                        photoFolderInfo.setCoverPhoto(photoInfo);
                    }
                }
            }
        }
        this.mFolderListAdapter.notifyDataSetChanged();
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity
    protected void takeResult(PhotoInfo photoInfo) {
        Message obtainMessage = this.mHanlder.obtainMessage();
        obtainMessage.obj = photoInfo;
        obtainMessage.what = 1000;
        if (!GalleryFinal.getFunctionConfig().isMutiSelect()) {
            this.mSelectPhotoList.clear();
            this.mSelectPhotoList.add(photoInfo);
            if (GalleryFinal.getFunctionConfig().isEditPhoto()) {
                this.mHasRefreshGallery = true;
                toPhotoEdit();
            } else {
                ArrayList<PhotoInfo> arrayList = new ArrayList<>();
                arrayList.add(photoInfo);
                resultData(arrayList);
            }
            this.mHanlder.sendMessageDelayed(obtainMessage, 100L);
            return;
        }
        this.mSelectPhotoList.add(photoInfo);
        this.mHanlder.sendMessageDelayed(obtainMessage, 100L);
    }

    protected void toPhotoEdit() {
        Intent intent = new Intent(this, PhotoEditActivity.class);
        intent.putExtra(PhotoEditActivity.SELECT_MAP, this.mSelectPhotoList);
        startActivity(intent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == C1656R.C1659id.ll_title || id == C1656R.C1659id.iv_folder_arrow) {
            if (this.mLlFolderPanel.getVisibility() == 0) {
                this.mLlFolderPanel.setVisibility(8);
                this.mLlFolderPanel.setAnimation(AnimationUtils.loadAnimation(this, C1656R.anim.gf_flip_horizontal_out));
            } else {
                this.mLlFolderPanel.setAnimation(AnimationUtils.loadAnimation(this, C1656R.anim.gf_flip_horizontal_in));
                this.mLlFolderPanel.setVisibility(0);
            }
        } else if (id == C1656R.C1659id.iv_take_photo) {
            if (GalleryFinal.getFunctionConfig().isMutiSelect() && this.mSelectPhotoList.size() == GalleryFinal.getFunctionConfig().getMaxSize()) {
                toast(getString(C1656R.string.select_max_tips));
                NBSActionInstrumentation.onClickEventExit();
                return;
            } else if (!DeviceUtils.existSDCard()) {
                toast(getString(C1656R.string.empty_sdcard));
                NBSActionInstrumentation.onClickEventExit();
                return;
            } else {
                takePhotoAction();
            }
        } else if (id == C1656R.C1659id.iv_back) {
            if (this.mLlFolderPanel.getVisibility() == 0) {
                this.mLlTitle.performClick();
            } else {
                resultData(null);
                finish();
            }
        } else if (id == C1656R.C1659id.fab_ok) {
            if (this.mSelectPhotoList.size() > 0) {
                if (!GalleryFinal.getFunctionConfig().isEditPhoto()) {
                    resultData(this.mSelectPhotoList);
                } else {
                    toPhotoEdit();
                }
            }
        } else if (id == C1656R.C1659id.iv_clear) {
            this.mSelectPhotoList.clear();
            this.mPhotoListAdapter.notifyDataSetChanged();
            refreshSelectCount();
        } else if (id == C1656R.C1659id.iv_preview) {
            Intent intent = new Intent(this, PhotoPreviewActivity.class);
            intent.putExtra("photo_list", this.mSelectPhotoList);
            startActivity(intent);
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        resultData(null);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        NBSActionInstrumentation.onItemClickEnter(view, i, this);
        Tracker.onItemClick(adapterView, view, i, j);
        if (adapterView.getId() == C1656R.C1659id.lv_folder_list) {
            folderItemClick(i);
        } else {
            photoItemClick(view, i);
        }
        NBSActionInstrumentation.onItemClickExit();
    }

    private void folderItemClick(int i) {
        this.mLlFolderPanel.setVisibility(8);
        this.mCurPhotoList.clear();
        PhotoFolderInfo photoFolderInfo = this.mAllPhotoFolderList.get(i);
        if (photoFolderInfo.getPhotoList() != null) {
            this.mCurPhotoList.addAll(photoFolderInfo.getPhotoList());
        }
        this.mPhotoListAdapter.notifyDataSetChanged();
        if (i == 0) {
            mPhotoTargetFolder = null;
        } else {
            PhotoInfo coverPhoto = photoFolderInfo.getCoverPhoto();
            if (coverPhoto != null && !StringUtils.isEmpty(coverPhoto.getPhotoPath())) {
                mPhotoTargetFolder = new File(coverPhoto.getPhotoPath()).getParent();
            } else {
                mPhotoTargetFolder = null;
            }
        }
        this.mTvSubTitle.setText(photoFolderInfo.getFolderName());
        this.mFolderListAdapter.setSelectFolder(photoFolderInfo);
        this.mFolderListAdapter.notifyDataSetChanged();
        if (this.mCurPhotoList.size() == 0) {
            this.mTvEmptyView.setText(C1656R.string.no_photo);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00bb, code lost:
        r0.remove();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void photoItemClick(android.view.View r4, int r5) {
        /*
            r3 = this;
            java.util.List<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mCurPhotoList
            java.lang.Object r5 = r0.get(r5)
            cn.finalteam.galleryfinal.model.PhotoInfo r5 = (cn.finalteam.galleryfinal.model.PhotoInfo) r5
            java.lang.String r0 = cn.finalteam.galleryfinal.PhotoSelectActivity.TAKEPHOTO
            java.lang.String r1 = r5.getPhotoPath()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L18
            r3.takePhotoAction()
            return
        L18:
            cn.finalteam.galleryfinal.FunctionConfig r0 = cn.finalteam.galleryfinal.GalleryFinal.getFunctionConfig()
            boolean r0 = r0.isMutiSelect()
            if (r0 != 0) goto L66
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r4 = r3.mSelectPhotoList
            r4.clear()
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r4 = r3.mSelectPhotoList
            r4.add(r5)
            java.lang.String r4 = r5.getPhotoPath()
            java.lang.String r4 = cn.finalteam.toolsfinal.p093io.FilenameUtils.getExtension(r4)
            cn.finalteam.galleryfinal.FunctionConfig r0 = cn.finalteam.galleryfinal.GalleryFinal.getFunctionConfig()
            boolean r0 = r0.isEditPhoto()
            if (r0 == 0) goto L5a
            java.lang.String r0 = "png"
            boolean r0 = r4.equalsIgnoreCase(r0)
            if (r0 != 0) goto L56
            java.lang.String r0 = "jpg"
            boolean r0 = r4.equalsIgnoreCase(r0)
            if (r0 != 0) goto L56
            java.lang.String r0 = "jpeg"
            boolean r4 = r4.equalsIgnoreCase(r0)
            if (r4 == 0) goto L5a
        L56:
            r3.toPhotoEdit()
            goto L65
        L5a:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r4.add(r5)
            r3.resultData(r4)
        L65:
            return
        L66:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mSelectPhotoList
            boolean r0 = r0.contains(r5)
            if (r0 != 0) goto L99
            cn.finalteam.galleryfinal.FunctionConfig r0 = cn.finalteam.galleryfinal.GalleryFinal.getFunctionConfig()
            boolean r0 = r0.isMutiSelect()
            if (r0 == 0) goto L92
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mSelectPhotoList
            int r0 = r0.size()
            cn.finalteam.galleryfinal.FunctionConfig r1 = cn.finalteam.galleryfinal.GalleryFinal.getFunctionConfig()
            int r1 = r1.getMaxSize()
            if (r0 != r1) goto L92
            int r4 = cn.finalteam.galleryfinal.C1656R.string.select_max_tips
            java.lang.String r4 = r3.getString(r4)
            r3.toast(r4)
            return
        L92:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mSelectPhotoList
            r0.add(r5)
            r5 = 1
            goto Lbf
        L99:
            java.util.ArrayList<cn.finalteam.galleryfinal.model.PhotoInfo> r0 = r3.mSelectPhotoList     // Catch: java.lang.Exception -> Lbe
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> Lbe
        L9f:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto Lbe
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Exception -> Lbe
            cn.finalteam.galleryfinal.model.PhotoInfo r1 = (cn.finalteam.galleryfinal.model.PhotoInfo) r1     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto L9f
            java.lang.String r1 = r1.getPhotoPath()     // Catch: java.lang.Exception -> Lbe
            java.lang.String r2 = r5.getPhotoPath()     // Catch: java.lang.Exception -> Lbe
            boolean r1 = android.text.TextUtils.equals(r1, r2)     // Catch: java.lang.Exception -> Lbe
            if (r1 == 0) goto L9f
            r0.remove()     // Catch: java.lang.Exception -> Lbe
        Lbe:
            r5 = 0
        Lbf:
            r3.refreshSelectCount()
            java.lang.Object r4 = r4.getTag()
            cn.finalteam.galleryfinal.adapter.PhotoListAdapter$PhotoViewHolder r4 = (cn.finalteam.galleryfinal.adapter.PhotoListAdapter.PhotoViewHolder) r4
            if (r4 == 0) goto Le8
            if (r5 == 0) goto Lda
            android.widget.ImageView r4 = r4.mIvCheck
            cn.finalteam.galleryfinal.ThemeConfig r5 = cn.finalteam.galleryfinal.GalleryFinal.getGalleryTheme()
            int r5 = r5.getCheckSelectedColor()
            r4.setBackgroundColor(r5)
            goto Led
        Lda:
            android.widget.ImageView r4 = r4.mIvCheck
            cn.finalteam.galleryfinal.ThemeConfig r5 = cn.finalteam.galleryfinal.GalleryFinal.getGalleryTheme()
            int r5 = r5.getCheckNornalColor()
            r4.setBackgroundColor(r5)
            goto Led
        Le8:
            cn.finalteam.galleryfinal.adapter.PhotoListAdapter r4 = r3.mPhotoListAdapter
            r4.notifyDataSetChanged()
        Led:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.finalteam.galleryfinal.PhotoSelectActivity.photoItemClick(android.view.View, int):void");
    }

    public void refreshSelectCount() {
        this.mTvChooseCount.setText(getString(C1656R.string.selected, new Object[]{Integer.valueOf(this.mSelectPhotoList.size()), Integer.valueOf(GalleryFinal.getFunctionConfig().getMaxSize())}));
        if (this.mSelectPhotoList.size() > 0 && GalleryFinal.getFunctionConfig().isMutiSelect()) {
            this.mIvClear.setVisibility(0);
        } else {
            this.mIvClear.setVisibility(8);
        }
        if (GalleryFinal.getFunctionConfig().isEnablePreview()) {
            this.mIvPreView.setVisibility(0);
        } else {
            this.mIvPreView.setVisibility(8);
        }
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, cn.finalteam.galleryfinal.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(List<String> list) {
        PermissionDialog.dimissDialog();
        getPhotos();
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, cn.finalteam.galleryfinal.permission.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(List<String> list) {
        PermissionDialog.dimissDialog();
        this.mTvEmptyView.setText(C1656R.string.permissions_denied_tips);
        this.mIvTakePhoto.setVisibility(8);
    }

    @AfterPermissionGranted(2001)
    private void requestGalleryPermission() {
        if (EasyPermissions.hasPermissions(this, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE")) {
            getPhotos();
            return;
        }
        PermissionDialog.show("相册为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
        EasyPermissions.requestPermissions(this, getString(C1656R.string.permissions_tips_gallery), 2001, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [cn.finalteam.galleryfinal.PhotoSelectActivity$3] */
    private void getPhotos() {
        this.mTvEmptyView.setText(C1656R.string.waiting);
        this.mGvPhotoList.setEnabled(false);
        this.mLlTitle.setEnabled(false);
        this.mIvTakePhoto.setEnabled(false);
        new Thread() { // from class: cn.finalteam.galleryfinal.PhotoSelectActivity.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                PhotoSelectActivity.this.mAllPhotoFolderList.clear();
                PhotoSelectActivity photoSelectActivity = PhotoSelectActivity.this;
                List<PhotoFolderInfo> allPhotoFolder = PhotoTools.getAllPhotoFolder(photoSelectActivity, photoSelectActivity.mSelectPhotoList);
                PhotoSelectActivity.this.mAllPhotoFolderList.addAll(allPhotoFolder);
                PhotoSelectActivity.this.mCurPhotoList.clear();
                if (allPhotoFolder.size() > 0 && allPhotoFolder.get(0).getPhotoList() != null) {
                    PhotoInfo photoInfo = new PhotoInfo();
                    photoInfo.setPhotoPath(PhotoSelectActivity.TAKEPHOTO);
                    PhotoSelectActivity.this.mCurPhotoList.add(0, photoInfo);
                    PhotoSelectActivity.this.mCurPhotoList.addAll(allPhotoFolder.get(0).getPhotoList());
                }
                PhotoSelectActivity.this.refreshAdapter();
            }
        }.start();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            try {
                if (this.mLlFolderPanel.getVisibility() == 0) {
                    this.mLlTitle.performClick();
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        if (this.mHasRefreshGallery) {
            this.mHasRefreshGallery = false;
            requestGalleryPermission();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (GalleryFinal.getCoreConfig() == null || GalleryFinal.getCoreConfig().getImageLoader() == null) {
            return;
        }
        GalleryFinal.getCoreConfig().getImageLoader().clearMemoryCache();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        mPhotoTargetFolder = null;
        ArrayList<PhotoInfo> arrayList = this.mSelectPhotoList;
        if (arrayList != null) {
            arrayList.clear();
        }
        System.gc();
    }
}
