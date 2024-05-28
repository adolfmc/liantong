package com.sinovatech.unicom.separatemodule.baidumap.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.p083v4.widget.NestedScrollView;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.utils.PermissionDialog;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.animation.ScaleAnimation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Gradient;
import com.baidu.mapapi.map.HeatMap;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.mahc.custombottomsheetbehavior.BottomSheetBehaviorGoogleMapsLike;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Permissions;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.activity.CitySelectActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.ScreenShotActivity;
import com.sinovatech.unicom.basic.server.CityCodingManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.ScreenShotUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.ScreenshotHandler;
import com.sinovatech.unicom.p318ui.ScreenshotObserver;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.BaiducityCode;
import com.sinovatech.unicom.separatemodule.baidumap.BusinessDataLoader;
import com.sinovatech.unicom.separatemodule.baidumap.BusinessDetailsLoader;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCouponsListAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduDynamicListAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduLiveListAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessDetailsEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BusinessEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.CouponsListEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.FiveGEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.LabelDataEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchTagEntity;
import com.sinovatech.unicom.separatemodule.baidumap.parser.CouponsListParse;
import com.sinovatech.unicom.separatemodule.baidumap.parser.FiveGFunction;
import com.sinovatech.unicom.separatemodule.baidumap.parser.LabelDataParser;
import com.sinovatech.unicom.separatemodule.baidumap.parser.SearchTagFunction;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sunfusheng.marqueeview.MarqueeView;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduMapMainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String PATH = "custom_map_config.json";
    public NBSTraceUnit _nbs_trace;
    private BaiduMap baiduMap;
    private UiSettings baiduMapUiSettings;
    private BottomSheetBehaviorGoogleMapsLike behavior;
    private String businessId;
    private CheckBox checkbox5G;
    private CheckBox checkboxCharging;
    private CheckBox checkboxConstruction5g;
    private Marker currentMarker;
    private int currentRange;
    private String deviceModel;
    private RecyclerView dynamicRV;
    private String ehallId;
    private BitmapDescriptor giftBD;
    private List<FiveGEntity.ExperienceHallBean> hallEhallList;
    private LinearLayout include_top;
    private boolean isLabBG;
    private RecyclerView liveRV;
    private BitmapDescriptor m5Gicon;
    private LinearLayout mActivitycontent;
    private TextView mActivitystate;
    private TextView mActivitytitle;
    private LinearLayout mAreaLayout;
    private ImageButton mBackButton;
    private NestedScrollView mBottomSheet;
    private BusinessEntity mBusinessEntity;
    private RecyclerView mBusinessImage;
    private CheckBox mCheckbox;
    private String mCity;
    private RelativeLayout mCityChoose;
    private TextView mCityText;
    private ImageButton mCollect;
    private CoordinatorLayout mCoordinatorLayout;
    private ImageView mCoupons;
    private BaiduCouponsListAdapter mCouponsListAdapter;
    private RecyclerView mCouponsRV;
    private List<CouponsListEntity> mCoupponsList;
    private BitmapDescriptor mCurrentMarker;
    private ViewTreeObserver.OnGlobalLayoutListener mDeatilLayoutListener;
    private LinearLayout mDetail;
    private RelativeLayout mDetails;
    private LinearLayout mDetailsHeight;
    private LinearLayout mDetailsclose;
    private TextView mDistance;
    private View mDivider;
    private List<BusinessEntity.DynamicListBean> mDynamicList;
    private BaiduDynamicListAdapter mDynamicListAdapter;
    private TextView mEpAddress;
    private ImageView mEpBusinessImg;
    private TextView mEpBusinessTime;
    private TextView mEpName;
    private GeoCoder mGeoCoder;
    private BitmapDescriptor mGiftstore;
    private TextView mGrade;
    private LinearLayout mHeader_layout;
    private List<LabelDataEntity.UnifiedLabelBean> mLabelList;
    private List<LabelDataEntity> mLabelsList;
    private LatLng mLatLng;
    private LinearLayout mLive;
    private ImageView mLiveImage;
    private BaiduLiveListAdapter mLiveListAdapter;
    private TextView mLiveTitle;
    private ImageView mLiveiocn;
    private List<BusinessEntity.LivingListBean> mLivingList;
    private LocationClient mLocClient;
    private HeatMap mMCustomHeatMap;
    private BitmapDescriptor mMarketstore;
    private OverlayOptions mOption;
    private RecyclerView mRecycler_volume;
    private SmartRefreshLayout mRefreshLayout;
    private TextView mRegion;
    private LinearLayout mRemen_zhibo_layout;
    private LinearLayout mReporterror;
    private RxPermissions mRxPermissions;
    private RelativeLayout mSearch;
    private MarqueeView mSearchbanner;
    private RecyclerView mServiceLabel;
    private ImageView mShareiocn;
    private ImageView mStar;
    private RecyclerView mTablabel;
    private RecyclerTablabelView mTablabelAdapter;
    private RecyclerView mTablabels;
    private RecyclerTablabeslView mTablabelsAdapter;
    private String mType;
    private TextView mTypeName;
    private RecyclerView mYingyetingList;
    private BaiduYiyetingListAdapter mYingyetingListAdapter;
    private MapView mapView;
    private HashMap<String, Marker> markerMap;
    private HashMap<String, List<MarkerMap>> markerMapList;
    private Marker markers;
    private int measuredHeight;
    private int measuredWidth;
    private BitmapDescriptor normalBD;

    /* renamed from: pd */
    private CustomePorgressDialog f18496pd;
    private String pitchCity;
    private ImageButton requestLocButton;

    /* renamed from: sp */
    private SharePreferenceUtil f18497sp;
    private ScreenshotHandler ssHandler;
    private ScreenshotObserver ssObserver;
    private TextView top_title;
    private final String TAG = "BaiduMapMainActivity";
    private Activity activityContext = this;
    private float defaultZoom = 14.0f;
    public MyLocationListenner myListener = new MyLocationListenner();
    boolean isFirstLoc = true;
    private LatLng currentLocation = null;
    private String cityName = "";
    private String mapCode = CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE;
    private BusinessDetailsEntity mBusinessDetails = new BusinessDetailsEntity();
    private boolean stade = true;
    private int cityCode = 0;
    private List<FiveGEntity.BaseStationBean> fiveGList = new ArrayList();
    private boolean isChecked = false;
    private boolean isChecked5g = false;
    private boolean isCheckedConstruction5g = false;
    private boolean isCheckedCharging = false;
    private boolean isFiveGLog = true;
    private String detailsWebUrl = "";
    private String mBusinessName = "";
    private String mEpId = "";
    private String mEpName1 = "";
    private String ehall_frontAddress = "";
    private String mPrivienceCode = CityChangeManager.DEFAULT_SELECT_CITY_PROVINCE_CODE;
    private List<BusinessEntity.BusinessBean> mBusinessList = new ArrayList();
    private int IndexLabel = -1;
    private int IndexLabels = 0;
    private boolean isRefresh = true;
    private String titleFlag = "";
    private String labelFlag = "";
    private String labelDetail = "";
    private int beginNum = -1;
    private boolean isLabelsClick = true;
    private boolean isLabelClick = true;
    private String mComplaintUrl = "";
    private boolean isDetailsCoupons = false;
    private boolean isXianxiaremenTitle = false;
    private Overlay mCircle = null;
    private boolean isClickMap = false;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: ARITH  (r15 I:long) = (r15 I:long) + (r0 I:long), expected to be less than 7
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void initControl() {
        /*
            Method dump skipped, instructions count: 1046
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.initControl():void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fa: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r4273 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), expected to be less than 11
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public void updateBusinessDetailsUI() {
        /*
            Method dump skipped, instructions count: 1075
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.updateBusinessDetailsUI():void");
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 75);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C85511 implements OnLoadMoreListener {
        C85511() {
        }

        @Override // com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
        public void onLoadMore(RefreshLayout refreshLayout) {
            BaiduMapMainActivity.this.isRefresh = false;
            BaiduMapMainActivity.this.loadYingyetingData();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class C85662 implements NestedScrollView.OnScrollChangeListener {
        C85662() {
        }

        @Override // android.support.p083v4.widget.NestedScrollView.OnScrollChangeListener
        public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
            if (i2 == nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight()) {
                BaiduMapMainActivity.this.mRefreshLayout.autoLoadMore();
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296473:
                finish();
                break;
            case 2131296494:
                if (App.hasLogined()) {
                    StatisticsUploadUtils.upload(this.activityContext, "FJ0015", "点击地图关注", "按钮", "0", "地图关注", "");
                    Intent intent = new Intent(this.activityContext, BaiduCollectActivity.class);
                    LatLng latLng = this.currentLocation;
                    if (latLng != null) {
                        intent.putExtra("latitude", String.valueOf(latLng.latitude));
                        intent.putExtra("longitude", String.valueOf(this.currentLocation.longitude));
                    }
                    this.activityContext.startActivity(intent);
                    break;
                } else {
                    Intent intent2 = new Intent(this.activityContext, LoginBindActivity.class);
                    intent2.putExtra("directAccess", false);
                    this.activityContext.startActivity(intent2);
                    new AvoidOnResult(this.activityContext).startForResult(intent2, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.3
                        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                        public void onActivityResult(int i, Intent intent3) {
                            if (App.hasLogined()) {
                                Intent intent4 = new Intent(BaiduMapMainActivity.this.activityContext, BaiduCollectActivity.class);
                                if (BaiduMapMainActivity.this.currentLocation != null) {
                                    intent4.putExtra("latitude", String.valueOf(BaiduMapMainActivity.this.currentLocation.latitude));
                                    intent4.putExtra("longitude", String.valueOf(BaiduMapMainActivity.this.currentLocation.longitude));
                                }
                                BaiduMapMainActivity.this.activityContext.startActivity(intent4);
                            }
                        }
                    });
                    break;
                }
            case 2131296495:
            case 2131298481:
                StatisticsUploadUtils.upload(this.activityContext, "FJ0014", "点击地图搜索框", "按钮", "0", "地图搜索框", "");
                Intent intent3 = new Intent(this, BaiduSearchAcitivty.class);
                if (!TextUtils.isEmpty(this.mapCode)) {
                    intent3.putExtra("cityCode", this.mapCode);
                }
                LatLng latLng2 = this.mLatLng;
                if (latLng2 != null) {
                    intent3.putExtra("latitude", String.valueOf(latLng2.latitude));
                    intent3.putExtra("longitude", String.valueOf(this.mLatLng.longitude));
                }
                startActivity(intent3);
                break;
            case 2131296610:
                if (UIUtils.isFastClick()) {
                    this.isChecked = this.mCheckbox.isChecked();
                    if (!this.isChecked) {
                        showBasestation("01");
                        break;
                    } else {
                        showBusiness();
                        break;
                    }
                }
                break;
            case 2131296611:
                if (UIUtils.isFastClick()) {
                    this.isChecked5g = this.checkbox5G.isChecked();
                    if (this.isChecked5g) {
                        showBasestation("01");
                        break;
                    } else {
                        showBusiness();
                        break;
                    }
                }
                break;
            case 2131296612:
                if (UIUtils.isFastClick()) {
                    this.isCheckedCharging = this.checkboxCharging.isChecked();
                    this.beginNum = -1;
                    if (this.isCheckedCharging) {
                        this.mTablabel.setVisibility(8);
                        this.mTablabels.setVisibility(8);
                    } else {
                        this.mTablabel.setVisibility(0);
                        this.mTablabels.setVisibility(0);
                    }
                    loadYingyetingData();
                    break;
                }
                break;
            case 2131296613:
                if (UIUtils.isFastClick()) {
                    this.isCheckedConstruction5g = this.checkboxConstruction5g.isChecked();
                    if (this.isCheckedConstruction5g) {
                        showBasestation("00");
                        break;
                    } else {
                        showBasestation("01");
                        break;
                    }
                }
                break;
            case 2131296652:
                Activity activity = this.activityContext;
                activity.startActivityForResult(new Intent(activity, CitySelectActivity.class), 3335);
                break;
            case 2131296845:
                if (UIUtils.isFastClick()) {
                    this.mDetails.setVisibility(8);
                    if (this.isChecked) {
                        this.mBottomSheet.setVisibility(0);
                        break;
                    }
                }
                break;
            case 2131297663:
                IntentManager.generateIntentAndGo(this.activityContext, this.mBusinessEntity.getLiveFrontAddress(), "直播", false, "get");
                break;
            case 2131298282:
                if (UIUtils.isFastClick()) {
                    StatisticsUploadUtils.upload(this.activityContext, "FJ0017", "点击地图详情报错", this.mBusinessDetails.getTypeIdentifier(), this.mBusinessDetails.getId(), this.mBusinessDetails.getEpName(), this.mBusinessDetails.getEhall_frontAddress());
                    if (!TextUtils.isEmpty(this.mComplaintUrl)) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.mComplaintUrl, "报错", false, "get");
                        break;
                    }
                }
                break;
            case 2131298608:
                if (UIUtils.isFastClick()) {
                    PermissionDialog.show("为了给您带来更好的服务，需要获取您的存储卡权限，用于您使用意见反馈、客服聊天、分享画报等需要上传信息或内容保存的功能，对于您授权的信息我们竭尽提供安全保护。");
                    SoulPermission.getInstance().checkAndRequestPermissions(Permissions.build("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"), new CheckRequestPermissionsListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.4
                        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                        public void onAllPermissionOk(Permission[] permissionArr) {
                            PermissionDialog.dimissDialog();
                            BaiduMapMainActivity.this.baiduMap.snapshot(new BaiduMap.SnapshotReadyCallback() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.4.1
                                @Override // com.baidu.mapapi.map.BaiduMap.SnapshotReadyCallback
                                public void onSnapshotReady(Bitmap bitmap) {
                                    StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0018", "点击地图分享", "按钮", "0", "", "");
                                    ScreenShotUtils.SaveBitmapToFile(BaiduMapMainActivity.this.activityContext, bitmap);
                                    Intent intent4 = new Intent(BaiduMapMainActivity.this.activityContext, ScreenShotActivity.class);
                                    intent4.putExtra("path", ScreenShotUtils.LongWebviewCapturePicture);
                                    intent4.putExtra("qrPaht", "");
                                    intent4.putExtra("form", "map");
                                    BaiduMapMainActivity.this.activityContext.startActivity(intent4);
                                }
                            });
                        }

                        @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
                        public void onPermissionDenied(Permission[] permissionArr) {
                            PermissionDialog.dimissDialog();
                            UIUtils.toast("请开启存储权限");
                        }
                    });
                    break;
                }
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void showBasestation(String str) {
        StatisticsUploadUtils.upload(this.activityContext, "FJ0008", "热力图5g", "按钮", "0", "5g按钮", "");
        this.mLiveiocn.setVisibility(8);
        this.mTablabel.setVisibility(8);
        this.mTablabels.setVisibility(8);
        this.mShareiocn.setVisibility(0);
        this.checkboxCharging.setVisibility(8);
        this.checkboxCharging.setChecked(false);
        MapStatus.Builder builder = new MapStatus.Builder();
        this.defaultZoom = 14.0f;
        builder.zoom(this.defaultZoom);
        this.baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        getCircleOptions(str);
    }

    private void showBusiness() {
        StatisticsUploadUtils.upload(this.activityContext, "FJ0008", "附近营业厅", "按钮", "0", "5g按钮", "");
        this.isFiveGLog = true;
        this.mTablabel.setVisibility(0);
        this.mTablabels.setVisibility(0);
        this.mShareiocn.setVisibility(8);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(this.defaultZoom);
        this.baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        getLabelData();
        getCouponsData();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 3335) {
            return;
        }
        try {
            if (i2 == -1 && intent != null) {
                this.cityName = "";
                this.cityName = intent.getStringExtra("cityName");
                this.pitchCity = "";
                this.pitchCity = this.cityName;
                this.mapCode = "";
                this.mapCode = intent.getStringExtra("mapCode");
                double doubleExtra = intent.getDoubleExtra("latitude", 0.0d);
                double doubleExtra2 = intent.getDoubleExtra("longitude", 0.0d);
                this.mPrivienceCode = intent.getStringExtra("privienceCode");
                this.currentLocation = null;
                this.currentLocation = new LatLng(doubleExtra, doubleExtra2);
                markersLocation();
                ajustMapCenter(this.currentLocation);
                if (!TextUtils.isEmpty(this.cityName) && this.cityName.length() > 3) {
                    if (this.cityName.equals("阿里地区")) {
                        this.cityName = "阿里...";
                    } else {
                        this.cityName = this.cityName.substring(0, 3) + "...";
                    }
                }
                this.mCityText.setText(this.cityName);
                StatisticsUploadUtils.upload(this.activityContext, "FJ0013", "点击切换城市", "按钮", "0", this.cityName, "");
                PermissionDialog.show("地图需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
                this.mRxPermissions.request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.5
                    @Override // io.reactivex.functions.Consumer
                    public void accept(Boolean bool) throws Exception {
                        PermissionDialog.dimissDialog();
                        if (!BaiduMapMainActivity.this.isChecked) {
                            if (!BaiduMapMainActivity.this.isChecked5g || BaiduMapMainActivity.this.isCheckedConstruction5g) {
                                BaiduMapMainActivity.this.getCircleOptions("00");
                                return;
                            } else {
                                BaiduMapMainActivity.this.getCircleOptions("01");
                                return;
                            }
                        }
                        BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                        baiduMapMainActivity.isCheckedCharging = baiduMapMainActivity.checkboxCharging.isChecked();
                        if (BaiduMapMainActivity.this.isCheckedCharging) {
                            BaiduMapMainActivity.this.mTablabel.setVisibility(8);
                            BaiduMapMainActivity.this.mTablabels.setVisibility(8);
                            BaiduMapMainActivity.this.beginNum = -1;
                            BaiduMapMainActivity.this.loadYingyetingData();
                        } else {
                            BaiduMapMainActivity.this.mTablabel.setVisibility(0);
                            BaiduMapMainActivity.this.mTablabels.setVisibility(0);
                            BaiduMapMainActivity.this.getLabelData();
                        }
                        BaiduMapMainActivity.this.getCouponsData();
                    }
                });
                return;
            }
            throw new RuntimeException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getJumpIntent() {
        if (TextUtils.isEmpty(getIntent().getStringExtra("YYT"))) {
            return;
        }
        this.mTablabel.setVisibility(8);
        this.mTablabels.setVisibility(8);
        this.baiduMap.setMaxAndMinZoomLevel(14.0f, 9.0f);
        this.isChecked = false;
        this.mCheckbox.setChecked(false);
        this.isChecked5g = true;
        this.checkbox5G.setChecked(true);
        this.mBottomSheet.setVisibility(8);
        this.mDetails.setVisibility(8);
        this.mLiveiocn.setVisibility(8);
        this.mShareiocn.setVisibility(0);
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(this.defaultZoom);
        this.baiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    private void getRecomeend() {
        App.getAsyncHttpClient().rxGet(URLSet.getStation_search_recomend(), new HashMap(), 3, 2).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new SearchTagFunction()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<SearchTagEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(ArrayList<SearchTagEntity> arrayList) {
                ArrayList arrayList2 = new ArrayList();
                for (int i = 0; i < arrayList.size(); i++) {
                    arrayList2.add(arrayList.get(i).gettABNAME());
                }
                BaiduMapMainActivity.this.mSearchbanner.startWithList(arrayList2);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                th.printStackTrace();
            }
        });
    }

    private void getListMessage() {
        this.behavior = BottomSheetBehaviorGoogleMapsLike.from(this.mBottomSheet);
        this.behavior.setState(3);
        this.mDetails.setVisibility(8);
    }

    private void initBaiduMap() {
        try {
            this.mapView = (MapView) findViewById(2131298034);
            this.markerMap = new HashMap<>();
            this.markerMapList = new HashMap<>();
            this.f18496pd = new CustomePorgressDialog(this.activityContext);
            this.f18496pd.setCancelable(true);
            this.f18496pd.setCanceledOnTouchOutside(false);
            this.normalBD = BitmapDescriptorFactory.fromResource(2131231048);
            this.giftBD = BitmapDescriptorFactory.fromResource(2131231045);
            this.mMarketstore = BitmapDescriptorFactory.fromResource(2131231047);
            this.mGiftstore = BitmapDescriptorFactory.fromResource(2131231046);
            this.m5Gicon = BitmapDescriptorFactory.fromResource(2131231044);
            SDKInitializer.setCoordType(CoordType.GCJ02);
            this.baiduMap = this.mapView.getMap();
            if (!this.isChecked && this.isChecked5g) {
                this.baiduMap.setMaxAndMinZoomLevel(14.0f, 9.0f);
            } else {
                this.baiduMap.setMaxAndMinZoomLevel(21.0f, 5.0f);
            }
            this.mapView.setMapCustomStylePath(getCustomStyleFilePath(this.activityContext, PATH));
            this.mapView.setMapCustomStyleEnable(true);
            this.mapView.showScaleControl(false);
            this.mapView.showZoomControls(true);
            this.baiduMap.setMapType(1);
            this.baiduMap.setMyLocationEnabled(true);
            this.baiduMapUiSettings = this.baiduMap.getUiSettings();
            this.baiduMapUiSettings.setCompassEnabled(false);
            this.baiduMapUiSettings.setOverlookingGesturesEnabled(false);
            this.baiduMapUiSettings.setRotateGesturesEnabled(true);
            this.baiduMapUiSettings.setScrollGesturesEnabled(true);
            this.baiduMapUiSettings.setZoomGesturesEnabled(true);
            this.mLocClient = new LocationClient(this.activityContext);
            this.mLocClient.registerLocationListener(this.myListener);
            LocationClientOption locationClientOption = new LocationClientOption();
            locationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            locationClientOption.setCoorType("gcj02");
            locationClientOption.setScanSpan(0);
            locationClientOption.setOpenGps(true);
            locationClientOption.setPriority(1);
            locationClientOption.setLocationNotify(true);
            locationClientOption.setIgnoreKillProcess(true);
            locationClientOption.SetIgnoreCacheException(false);
            locationClientOption.setWifiCacheTimeOut(600000);
            locationClientOption.setIsNeedAddress(true);
            this.mLocClient.setLocOption(locationClientOption);
            this.baiduMap.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.7
                @Override // com.baidu.mapapi.map.BaiduMap.OnMapLoadedCallback
                public void onMapLoaded() {
                    BaiduMapMainActivity.this.mapView.setZoomControlsPosition(new Point(BaiduMapMainActivity.this.measuredWidth - 140, (BaiduMapMainActivity.this.measuredHeight / 2) - 260));
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(BaiduMapMainActivity.this.requestLocButton.getLayoutParams());
                    layoutParams.setMargins(20, (BaiduMapMainActivity.this.measuredHeight - 260) / 2, 0, 0);
                    BaiduMapMainActivity.this.requestLocButton.setLayoutParams(layoutParams);
                    BaiduMapMainActivity.this.behavior.setAnchorPoint(UIUtils.dip2px(BaiduMapMainActivity.this.activityContext, 160.0f));
                    BaiduMapMainActivity.this.startLocation();
                    BaiduMapMainActivity.this.requestLocButton.setVisibility(0);
                    BaiduMapMainActivity.this.requestLocButton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.7.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            BaiduMapMainActivity.this.startLocation();
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                }
            });
            this.baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.8
                @Override // com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener
                public boolean onMarkerClick(Marker marker) {
                    try {
                        StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0003", "点击地图大头针", ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getTypeIdentifier(), ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getId(), ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getName(), ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getUrl());
                        if (BaiduMapMainActivity.this.currentMarker != null && !marker.getId().equals(BaiduMapMainActivity.this.currentMarker.getId())) {
                            BaiduMapMainActivity.this.currentMarker.setAnimation(BaiduMapMainActivity.this.getScaleAnimation2());
                            BaiduMapMainActivity.this.currentMarker.startAnimation();
                        }
                        marker.setAnimation(BaiduMapMainActivity.this.getScaleAnimation());
                        marker.startAnimation();
                        marker.setToTop();
                        BaiduMapMainActivity.this.currentMarker = marker;
                        BaiduMapMainActivity.this.ajustMapCenter(BaiduMapMainActivity.this.currentMarker.getPosition());
                        if (BaiduMapMainActivity.this.currentMarker != null) {
                            if (TextUtils.isEmpty(((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getDynamicTitle()) || TextUtils.isEmpty(((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getDynamicTopicColor())) {
                                BaiduMapMainActivity.this.baiduMap.hideInfoWindow();
                            } else {
                                BaiduMapMainActivity.this.showInfoWindow(((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getDynamicTitle(), ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getDynamicTopicColor());
                            }
                        }
                        BaiduMapMainActivity.this.businessId = ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getId();
                        BaiduMapMainActivity.this.mType = ((MarkerMap) ((List) BaiduMapMainActivity.this.markerMapList.get(marker.getId())).get(0)).getType();
                        BaiduMapMainActivity.this.loadYingyetingDetails(BaiduMapMainActivity.this.mLatLng, BaiduMapMainActivity.this.businessId, BaiduMapMainActivity.this.mType);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                        UIUtils.toastLong("程序错误【marker点击事件监听：" + e.getMessage() + "】");
                        return true;
                    }
                }
            });
            this.baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.9
                @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
                public void onMapPoiClick(MapPoi mapPoi) {
                }

                @Override // com.baidu.mapapi.map.BaiduMap.OnMapClickListener
                public void onMapClick(LatLng latLng) {
                    BaiduMapMainActivity.this.currentLocation = null;
                    BaiduMapMainActivity.this.currentLocation = latLng;
                    BaiduMapMainActivity.this.isClickMap = true;
                    BaiduMapMainActivity.this.markersLocation();
                    BaiduMapMainActivity.this.mGeoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(BaiduMapMainActivity.this.currentLocation));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    private String getCustomStyleFilePath(Context context, String str) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        String str2;
        ?? r0 = 0;
        r0 = 0;
        r0 = 0;
        r0 = 0;
        try {
            try {
                try {
                    inputStream = context.getAssets().open("customConfigdir/" + str);
                    try {
                        try {
                            byte[] bArr = new byte[inputStream.available()];
                            inputStream.read(bArr);
                            str2 = context.getFilesDir().getAbsolutePath();
                            try {
                                File file = new File(str2 + "/" + str);
                                if (file.exists()) {
                                    file.delete();
                                }
                                file.createNewFile();
                                fileOutputStream = new FileOutputStream(file);
                                try {
                                    fileOutputStream.write(bArr);
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    fileOutputStream.close();
                                } catch (IOException e) {
                                    e = e;
                                    e.printStackTrace();
                                    UIUtils.logE("Copy custom style file failed", e.getMessage());
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    r0 = new StringBuilder();
                                    r0.append(str2);
                                    r0.append("/");
                                    r0.append(str);
                                    return r0.toString();
                                }
                            } catch (IOException e2) {
                                e = e2;
                                fileOutputStream = null;
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = null;
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        str2 = null;
                        fileOutputStream = null;
                    }
                } catch (IOException e4) {
                    e = e4;
                    str2 = null;
                    inputStream = null;
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                    fileOutputStream = null;
                }
                r0 = new StringBuilder();
                r0.append(str2);
                r0.append("/");
                r0.append(str);
                return r0.toString();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e5) {
            e5.printStackTrace();
            UIUtils.logE("Close stream failed", e5.getMessage());
            return r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLocation() {
        PermissionDialog.show("地图需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
        this.mRxPermissions.request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.10
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                PermissionDialog.dimissDialog();
                BaiduMapMainActivity.this.checkboxCharging.setVisibility(8);
                if (bool.booleanValue()) {
                    if (BaiduMapMainActivity.this.markers != null) {
                        BaiduMapMainActivity.this.markers = null;
                    }
                    BaiduMapMainActivity.this.f18496pd.setMessage("正在定位...");
                    BaiduMapMainActivity.this.showDialog();
                    BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                    baiduMapMainActivity.isFirstLoc = true;
                    baiduMapMainActivity.mLocClient.start();
                    return;
                }
                BaiduMapMainActivity.this.dismissDialog();
                UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "定位失败，请在手机-设置里开启定位功能", 1);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.11
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                MsLogUtil.m7978e(th.getMessage());
                BaiduMapMainActivity.this.dismissDialog();
            }
        });
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MyLocationListenner extends BDAbstractLocationListener {
        public MyLocationListenner() {
        }

        @Override // com.baidu.location.BDAbstractLocationListener
        public void onReceiveLocation(BDLocation bDLocation) {
            try {
                if (BaiduMapMainActivity.this.mapView == null) {
                    return;
                }
                if (bDLocation != null && BaiduMapMainActivity.this.mapView != null) {
                    BaiduMapMainActivity.this.mCity = bDLocation.getCity();
                    BaiduMapMainActivity.this.mapCode = bDLocation.getCityCode();
                    int locType = bDLocation.getLocType();
                    if (locType != 61 && locType != 161) {
                        if (BaiduMapMainActivity.this.stade) {
                            BaiduMapMainActivity.this.stade = true;
                            BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                        }
                        throw new RuntimeException();
                    }
                    if (BaiduMapMainActivity.this.isFirstLoc) {
                        BaiduMapMainActivity.this.f18497sp.putString("latitude", String.valueOf(bDLocation.getLatitude()));
                        BaiduMapMainActivity.this.f18497sp.putString("longitude", String.valueOf(bDLocation.getLongitude()));
                        BaiduMapMainActivity.this.isFirstLoc = false;
                        BaiduMapMainActivity.this.pitchCity = "";
                        BaiduMapMainActivity.this.cityName = "";
                        int length = BaiduMapMainActivity.this.mCity.length() - 1;
                        if (!"市".equals(BaiduMapMainActivity.this.mCity.substring(length, BaiduMapMainActivity.this.mCity.length()))) {
                            BaiduMapMainActivity.this.mCityText.setText(BaiduMapMainActivity.this.mCity);
                            BaiduMapMainActivity.this.pitchCity = BaiduMapMainActivity.this.mCity;
                            BaiduMapMainActivity.this.cityName = BaiduMapMainActivity.this.mCity;
                        } else {
                            BaiduMapMainActivity.this.cityName = BaiduMapMainActivity.this.mCity.substring(0, length);
                            BaiduMapMainActivity.this.pitchCity = BaiduMapMainActivity.this.mCity.substring(0, length);
                        }
                        if (!TextUtils.isEmpty(BaiduMapMainActivity.this.cityName) && BaiduMapMainActivity.this.cityName.length() > 3) {
                            if (BaiduMapMainActivity.this.cityName.equals("阿里地区")) {
                                BaiduMapMainActivity.this.cityName = "阿里...";
                            } else {
                                BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                                baiduMapMainActivity.cityName = BaiduMapMainActivity.this.cityName.substring(0, 3) + "...";
                            }
                        }
                        BaiduMapMainActivity.this.mCityText.setText(BaiduMapMainActivity.this.cityName);
                        BaiduMapMainActivity.this.mLatLng = new LatLng(bDLocation.getLatitude(), bDLocation.getLongitude());
                        BaiduMapMainActivity.this.baiduMap.setMyLocationData(new MyLocationData.Builder().accuracy(bDLocation.getRadius()).direction(0.0f).latitude(bDLocation.getLatitude()).longitude(bDLocation.getLongitude()).build());
                        BaiduMapMainActivity.this.baiduMap.setMyLocationConfiguration(new MyLocationConfiguration(MyLocationConfiguration.LocationMode.FOLLOWING, true, BaiduMapMainActivity.this.mCurrentMarker, 0, 0));
                        BaiduMapMainActivity.this.currentLocation = null;
                        BaiduMapMainActivity.this.currentLocation = BaiduMapMainActivity.this.mLatLng;
                        if (BaiduMapMainActivity.this.isChecked || !BaiduMapMainActivity.this.isChecked5g) {
                            if (BaiduMapMainActivity.this.isChecked || !BaiduMapMainActivity.this.isCheckedConstruction5g) {
                                BaiduMapMainActivity.this.getLabelData();
                                BaiduMapMainActivity.this.getCouponsData();
                            } else {
                                BaiduMapMainActivity.this.getCircleOptions("00");
                            }
                        } else {
                            BaiduMapMainActivity.this.getCircleOptions("01");
                        }
                        BaiduMapMainActivity.this.mLocClient.stop();
                        BaiduMapMainActivity.this.mPrivienceCode = CityCodingManager.privienceCode(BaiduMapMainActivity.this.activityContext, BaiduMapMainActivity.this.pitchCity);
                        BaiduMapMainActivity.this.mapCode = CityCodingManager.cityCode(BaiduMapMainActivity.this.activityContext, BaiduMapMainActivity.this.pitchCity);
                        StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0020", "点击附近营业厅入口", "按钮", "0", "原生地图页面", "", "", BaiduMapMainActivity.this.mapCode, BaiduMapMainActivity.this.mPrivienceCode);
                        return;
                    }
                    return;
                }
                throw new RuntimeException();
            } catch (Exception e) {
                StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0020", "点击附近营业厅入口", "按钮", "0", "客户端地图页面", "", "", "", "");
                e.printStackTrace();
                BaiduMapMainActivity.this.dismissDialog();
                BaiduMapMainActivity.this.mLocClient.stop();
                UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "定位失败", 1);
            }
        }
    }

    private void getGeoCoder() {
        this.mGeoCoder = GeoCoder.newInstance();
        this.mGeoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.12
            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
            }

            @Override // com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "定位异常...", 0);
                    return;
                }
                try {
                    BaiduMapMainActivity.this.cityCode = reverseGeoCodeResult.getCityCode();
                    String str = new BaiducityCode().getcityCode(BaiduMapMainActivity.this.cityCode);
                    BaiduMapMainActivity.this.pitchCity = "";
                    BaiduMapMainActivity.this.pitchCity = str;
                    if (TextUtils.isEmpty(str)) {
                        UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "定位异常...", 0);
                        return;
                    }
                    if (str.length() > 3) {
                        if (str.equals("阿里地区")) {
                            str = "阿里...";
                        } else {
                            str = str.substring(0, 3) + "...";
                        }
                    }
                    BaiduMapMainActivity.this.mCityText.setText(str);
                    if (BaiduMapMainActivity.this.isClickMap) {
                        PermissionDialog.show("地图需要您授予中国联通APP位置权限，以开启与位置相关的推荐/搜索/安全保障服务。");
                        BaiduMapMainActivity.this.mRxPermissions.request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.12.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Boolean bool) throws Exception {
                                PermissionDialog.dimissDialog();
                                if (BaiduMapMainActivity.this.isChecked || !BaiduMapMainActivity.this.isChecked5g) {
                                    BaiduMapMainActivity.this.getLabelData();
                                    BaiduMapMainActivity.this.getCouponsData();
                                    return;
                                }
                                BaiduMapMainActivity.this.getCircleOptions("01");
                            }
                        });
                        BaiduMapMainActivity.this.isClickMap = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markersLocation() {
        Marker marker = this.markers;
        if (marker != null) {
            marker.remove();
        }
        this.mOption = new MarkerOptions().position(this.currentLocation).icon(BitmapDescriptorFactory.fromResource(2131231025)).clickable(false);
        this.markers = (Marker) this.baiduMap.addOverlay(this.mOption);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ajustMapCenter(LatLng latLng) {
        try {
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(latLng).zoom(this.defaultZoom);
            this.baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("程序错误【调整地图中心点:" + e.getMessage() + "】");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showInfoWindow(String str, String str2) {
        try {
            LinearLayout linearLayout = (LinearLayout) this.activityContext.getLayoutInflater().inflate(2131493027, (ViewGroup) null);
            TextView textView = (TextView) linearLayout.findViewById(2131298785);
            if (str.length() > 6) {
                textView.setText(str.substring(0, 6) + "...");
            } else {
                textView.setText(str);
            }
            textView.setTextColor(Color.parseColor(str2));
            final InfoWindow infoWindow = new InfoWindow(linearLayout, this.currentMarker.getPosition(), -150);
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.13
                @Override // java.lang.Runnable
                public void run() {
                    BaiduMapMainActivity.this.baiduMap.showInfoWindow(infoWindow);
                }
            }, 500L);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toastLong("程序错误【显示InfoWindow:" + e.getMessage() + "】");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLabelData() {
        try {
            this.mPrivienceCode = CityCodingManager.privienceCode(this.activityContext, this.pitchCity);
            this.mapCode = CityCodingManager.cityCode(this.activityContext, this.pitchCity);
            if (this.currentLocation != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("provinceCode", this.mPrivienceCode);
                hashMap.put("cityCode", this.mapCode);
                hashMap.put("deviceNo", this.deviceModel);
                hashMap.put("longitude", String.valueOf(this.mLatLng.longitude));
                hashMap.put("latitude", String.valueOf(this.mLatLng.latitude));
                hashMap.put("destination_jd", String.valueOf(this.currentLocation.longitude));
                hashMap.put("destination_wd", String.valueOf(this.currentLocation.latitude));
                hashMap.put("version", this.activityContext.getString(2131886969));
                UIUtils.logD("标签结果URL：" + URLSet.getEhallTitleAndLabel());
                UIUtils.logD("标签结果入参：" + hashMap);
                App.getAsyncHttpClient().rxGet(URLSet.getEhallTitleAndLabel(), hashMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, List<LabelDataEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.15
                    @Override // io.reactivex.functions.Function
                    public List apply(String str) throws Exception {
                        UIUtils.logD("标签结果报文：" + str);
                        return LabelDataParser.parseSingleBusiness(new JSONArray(str));
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<LabelDataEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.14
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        BaiduMapMainActivity.this.mTablabel.setVisibility(8);
                        BaiduMapMainActivity.this.mTablabels.setVisibility(8);
                        BaiduMapMainActivity.this.mLabelsList.clear();
                        BaiduMapMainActivity.this.mLabelList.clear();
                        BaiduMapMainActivity.this.titleFlag = "";
                        BaiduMapMainActivity.this.labelFlag = "";
                        BaiduMapMainActivity.this.labelDetail = "";
                        BaiduMapMainActivity.this.isLabBG = false;
                        BaiduMapMainActivity.this.beginNum = -1;
                        if (BaiduMapMainActivity.this.isRefresh) {
                            if (BaiduMapMainActivity.this.mMCustomHeatMap != null) {
                                BaiduMapMainActivity.this.mMCustomHeatMap.removeHeatMap();
                                BaiduMapMainActivity.this.mMCustomHeatMap = null;
                            }
                            BaiduMapMainActivity.this.isFiveGLog = true;
                            BaiduMapMainActivity.this.isChecked = true;
                            BaiduMapMainActivity.this.mCheckbox.setChecked(true);
                            BaiduMapMainActivity.this.isCheckedCharging = false;
                            BaiduMapMainActivity.this.checkboxCharging.setChecked(false);
                            BaiduMapMainActivity.this.isChecked5g = false;
                            BaiduMapMainActivity.this.checkbox5G.setChecked(false);
                            BaiduMapMainActivity.this.isCheckedConstruction5g = false;
                            BaiduMapMainActivity.this.checkboxConstruction5g.setChecked(false);
                            BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                            BaiduMapMainActivity.this.mDetails.setVisibility(8);
                            BaiduMapMainActivity.this.currentMarker = null;
                            BaiduMapMainActivity.this.baiduMap.clear();
                            BaiduMapMainActivity.this.markerMap.clear();
                            BaiduMapMainActivity.this.markerMapList.clear();
                            BaiduMapMainActivity.this.isLabelClick = true;
                            BaiduMapMainActivity.this.isLabelsClick = true;
                            BaiduMapMainActivity.this.mBusinessList.clear();
                        }
                        BaiduMapMainActivity.this.mDynamicList.clear();
                        BaiduMapMainActivity.this.mLivingList.clear();
                        BaiduMapMainActivity.this.f18496pd.setMessage("加载附近网点中...");
                        BaiduMapMainActivity.this.showDialog();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(List<LabelDataEntity> list) {
                        if (list.size() >= 1) {
                            BaiduMapMainActivity.this.mTablabel.setVisibility(0);
                            BaiduMapMainActivity.this.mTablabels.setVisibility(0);
                            BaiduMapMainActivity.this.IndexLabels = 0;
                            BaiduMapMainActivity.this.mLabelsList.addAll(list);
                            BaiduMapMainActivity.this.mTablabelsAdapter.notifyDataSetChanged();
                            BaiduMapMainActivity.this.IndexLabel = 0;
                            BaiduMapMainActivity.this.mLabelList.addAll(list.get(0).getUnifiedLabel());
                            BaiduMapMainActivity.this.mTablabelAdapter.notifyDataSetChanged();
                            if ("1".equals(list.get(0).getChargeFlag())) {
                                BaiduMapMainActivity.this.checkboxCharging.setVisibility(0);
                            } else {
                                BaiduMapMainActivity.this.checkboxCharging.setVisibility(8);
                            }
                        } else if (list.size() < 1) {
                            BaiduMapMainActivity.this.mTablabel.setVisibility(8);
                            BaiduMapMainActivity.this.mTablabels.setVisibility(8);
                            BaiduMapMainActivity.this.titleFlag = "";
                            BaiduMapMainActivity.this.labelFlag = "";
                            BaiduMapMainActivity.this.labelDetail = "";
                            BaiduMapMainActivity.this.isRefresh = true;
                            BaiduMapMainActivity.this.loadYingyetingData();
                        }
                        BaiduMapMainActivity.this.dismissDialog();
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        BaiduMapMainActivity.this.dismissDialog();
                        BaiduMapMainActivity.this.mTablabel.setVisibility(8);
                        BaiduMapMainActivity.this.mTablabels.setVisibility(8);
                        UIUtils.logD("标签结果错误：" + th.getMessage());
                        CustomDialogManager.show(BaiduMapMainActivity.this.activityContext, "温馨提示", "加载附近网点失败 是否重新加载?", true, "取消", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.14.1
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                            public void onClickOk() {
                                BaiduMapMainActivity.this.getLabelData();
                            }
                        });
                    }
                });
            } else {
                UIUtils.showCenterOnlyTextToast(this.activityContext, "您还没有定位当前位置~", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.logD("标签结果错误————：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCouponsData() {
        this.mPrivienceCode = CityCodingManager.privienceCode(this.activityContext, this.pitchCity);
        this.mapCode = CityCodingManager.cityCode(this.activityContext, this.pitchCity);
        HashMap hashMap = new HashMap();
        if (App.hasLogined()) {
            hashMap.put("mobile", UserManager.getInstance().getDefaultPhoneNumber());
        } else {
            hashMap.put("mobile", "");
        }
        hashMap.put("cityCode", this.mapCode);
        hashMap.put("version", this.activityContext.getString(2131886969));
        UIUtils.logD("优惠卷结果URL：" + URLSet.getCouponsList());
        UIUtils.logD("优惠卷结果入参：" + hashMap);
        App.getAsyncHttpClient().rxGet(URLSet.getCouponsList(), hashMap, 0, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, CouponsListEntity>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.17
            @Override // io.reactivex.functions.Function
            public CouponsListEntity apply(String str) throws Exception {
                UIUtils.logD("优惠卷结果报文：" + str);
                return CouponsListParse.parseSingleBusiness(new JSONObject(str));
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CouponsListEntity>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.16
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                BaiduMapMainActivity.this.mCouponsRV.setVisibility(8);
                BaiduMapMainActivity.this.mCoupponsList.clear();
            }

            @Override // io.reactivex.Observer
            public void onNext(final CouponsListEntity couponsListEntity) {
                try {
                    if (!"0000".equals(couponsListEntity.getCode()) || couponsListEntity.getData().size() <= 0) {
                        BaiduMapMainActivity.this.mCouponsRV.setVisibility(8);
                        if (BaiduMapMainActivity.this.isXianxiaremenTitle) {
                            BaiduMapMainActivity.this.isXianxiaremenTitle = true;
                            BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(8);
                        }
                    } else {
                        BaiduMapMainActivity.this.isXianxiaremenTitle = false;
                        BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(0);
                        BaiduMapMainActivity.this.mCouponsRV.setVisibility(0);
                        BaiduMapMainActivity.this.mCoupponsList.add(couponsListEntity);
                        BaiduMapMainActivity.this.mCouponsListAdapter = new BaiduCouponsListAdapter(BaiduMapMainActivity.this.activityContext, couponsListEntity);
                        BaiduMapMainActivity.this.mCouponsRV.setLayoutManager(new LinearLayoutManager(BaiduMapMainActivity.this.activityContext, 0, false));
                        BaiduMapMainActivity.this.mCouponsRV.setAdapter(BaiduMapMainActivity.this.mCouponsListAdapter);
                        BaiduMapMainActivity.this.mCouponsListAdapter.notifyDataSetChanged();
                        BaiduMapMainActivity.this.mCouponsListAdapter.setOnItemListClickListener(new BaiduCouponsListAdapter.OnItemListClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.16.1
                            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCouponsListAdapter.OnItemListClickListener
                            public void onItemListClick(int i, String str, String str2, String str3) {
                                BaiduMapMainActivity.this.isDetailsCoupons = true;
                                Activity activity = BaiduMapMainActivity.this.activityContext;
                                IntentManager.generateIntentAndGo(activity, couponsListEntity.getDetailUrl() + "?couponId=" + str + "&state=" + str2 + "&isFree=" + str3 + "&longitude=" + BaiduMapMainActivity.this.mLatLng.longitude + "&latitude=" + BaiduMapMainActivity.this.mLatLng.latitude + "&cityCode=" + BaiduMapMainActivity.this.mapCode + "&provinceCode=" + BaiduMapMainActivity.this.mPrivienceCode, "优惠卷兑换", false, "get");
                                Activity activity2 = BaiduMapMainActivity.this.activityContext;
                                String couponId = couponsListEntity.getData().get(i).getCouponId();
                                String title = couponsListEntity.getData().get(i).getTitle();
                                StatisticsUploadUtils.upload(activity2, "FJ0019", "点击优惠券", "按钮", couponId, title, couponsListEntity.getDetailUrl() + "?couponId=" + str + "&state=" + str2 + "&isFree=" + str3 + "&longitude=" + BaiduMapMainActivity.this.mLatLng.longitude + "&latitude=" + BaiduMapMainActivity.this.mLatLng.latitude + "&cityCode=" + BaiduMapMainActivity.this.mapCode + "&provinceCode=" + BaiduMapMainActivity.this.mPrivienceCode);
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                UIUtils.logD("优惠卷结果错误：" + th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadYingyetingData() {
        try {
            this.mapView.setZoomControlsPosition(new Point(this.measuredWidth - 140, (this.measuredHeight / 2) - 260));
            this.mPrivienceCode = CityCodingManager.privienceCode(this.activityContext, this.pitchCity);
            this.mapCode = CityCodingManager.cityCode(this.activityContext, this.pitchCity);
            this.isCheckedCharging = this.checkboxCharging.isChecked();
            BusinessDataLoader.loadYingyetingData(this.activityContext, this.mLatLng, this.currentLocation, String.valueOf(this.beginNum + 1), this.deviceModel, this.titleFlag, this.labelFlag, this.labelDetail, this.mapCode, this.mPrivienceCode, this.isCheckedCharging ? "1" : "0", new Observer<BusinessEntity>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.18
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                    if (BaiduMapMainActivity.this.isRefresh) {
                        BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                        BaiduMapMainActivity.this.include_top.setVisibility(8);
                        if (BaiduMapMainActivity.this.mMCustomHeatMap != null) {
                            BaiduMapMainActivity.this.mMCustomHeatMap.removeHeatMap();
                            BaiduMapMainActivity.this.mMCustomHeatMap = null;
                        }
                        BaiduMapMainActivity.this.currentMarker = null;
                        BaiduMapMainActivity.this.baiduMap.clear();
                        BaiduMapMainActivity.this.markerMap.clear();
                        BaiduMapMainActivity.this.markerMapList.clear();
                    }
                    BaiduMapMainActivity.this.mDetails.setVisibility(8);
                    if (!BaiduMapMainActivity.this.isChecked && BaiduMapMainActivity.this.isChecked5g) {
                        BaiduMapMainActivity.this.isFiveGLog = true;
                        BaiduMapMainActivity.this.isChecked = true;
                        BaiduMapMainActivity.this.mCheckbox.setChecked(true);
                        BaiduMapMainActivity.this.isChecked5g = false;
                        BaiduMapMainActivity.this.checkbox5G.setChecked(false);
                    }
                    BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(8);
                    BaiduMapMainActivity.this.mDynamicList.clear();
                    BaiduMapMainActivity.this.mLivingList.clear();
                    BaiduMapMainActivity.this.f18496pd.setMessage("加载附近网点中...");
                    BaiduMapMainActivity.this.showDialog();
                }

                @Override // io.reactivex.Observer
                public void onNext(BusinessEntity businessEntity) {
                    int i;
                    BaiduMapMainActivity.this.mBusinessEntity = businessEntity;
                    BaiduMapMainActivity.this.baiduMap.setMaxAndMinZoomLevel(21.0f, 5.0f);
                    BaiduMapMainActivity.this.stade = false;
                    if ("0".equals(BaiduMapMainActivity.this.mBusinessEntity.getLiveFlag())) {
                        BaiduMapMainActivity.this.mLiveiocn.setVisibility(0);
                    } else {
                        BaiduMapMainActivity.this.mLiveiocn.setVisibility(8);
                    }
                    if (BaiduMapMainActivity.this.mBusinessEntity.getDynamicList().size() > 0) {
                        BaiduMapMainActivity.this.isXianxiaremenTitle = false;
                        i = BaiduMapMainActivity.this.mBusinessEntity.getDynamicList().size() + 0;
                        BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(0);
                        BaiduMapMainActivity.this.dynamicRV.setVisibility(0);
                        BaiduMapMainActivity.this.mDynamicList.clear();
                        BaiduMapMainActivity.this.mDynamicList.addAll(BaiduMapMainActivity.this.mBusinessEntity.getDynamicList());
                        BaiduMapMainActivity.this.mDynamicListAdapter.notifyDataSetChanged();
                    } else {
                        BaiduMapMainActivity.this.dynamicRV.setVisibility(8);
                        if (BaiduMapMainActivity.this.isXianxiaremenTitle) {
                            BaiduMapMainActivity.this.isXianxiaremenTitle = true;
                            BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(8);
                        }
                        i = 0;
                    }
                    if (BaiduMapMainActivity.this.mBusinessEntity.getLivingList().size() > 0) {
                        BaiduMapMainActivity.this.isXianxiaremenTitle = false;
                        i += BaiduMapMainActivity.this.mBusinessEntity.getLivingList().size();
                        BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(0);
                        BaiduMapMainActivity.this.liveRV.setVisibility(0);
                        BaiduMapMainActivity.this.mLivingList.clear();
                        BaiduMapMainActivity.this.mLivingList.addAll(BaiduMapMainActivity.this.mBusinessEntity.getLivingList());
                        BaiduMapMainActivity.this.mLiveListAdapter.notifyDataSetChanged();
                    } else {
                        BaiduMapMainActivity.this.liveRV.setVisibility(8);
                        if (BaiduMapMainActivity.this.isXianxiaremenTitle) {
                            BaiduMapMainActivity.this.isXianxiaremenTitle = true;
                            BaiduMapMainActivity.this.mRemen_zhibo_layout.setVisibility(8);
                        }
                    }
                    if (BaiduMapMainActivity.this.isRefresh) {
                        BaiduMapMainActivity.this.mBusinessList.clear();
                    }
                    BaiduMapMainActivity.this.mBusinessList.addAll(BaiduMapMainActivity.this.mBusinessEntity.getEhallList());
                    if (BaiduMapMainActivity.this.mBusinessList.size() == 0) {
                        BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                        UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "很抱歉，该地市暂无附近网点信息...", 0);
                    } else {
                        if (BaiduMapMainActivity.this.isChecked) {
                            BaiduMapMainActivity.this.mBottomSheet.setVisibility(0);
                            BaiduMapMainActivity.this.include_top.setVisibility(0);
                        }
                        if (!BaiduMapMainActivity.this.isRefresh) {
                            BaiduMapMainActivity.this.mRefreshLayout.finishLoadMore();
                        }
                        BaiduMapMainActivity.this.beginNum++;
                        BaiduMapMainActivity.this.isRefresh = true;
                        BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                        baiduMapMainActivity.updateMapUI(baiduMapMainActivity.mBusinessList);
                        BaiduMapMainActivity baiduMapMainActivity2 = BaiduMapMainActivity.this;
                        baiduMapMainActivity2.updateBusinessUI(baiduMapMainActivity2.mBusinessList, i);
                    }
                    BaiduMapMainActivity.this.dismissDialog();
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                    if (!BaiduMapMainActivity.this.isRefresh) {
                        BaiduMapMainActivity.this.mRefreshLayout.finishLoadMore();
                    }
                    BaiduMapMainActivity.this.isRefresh = true;
                    BaiduMapMainActivity.this.baiduMap.setMaxAndMinZoomLevel(21.0f, 5.0f);
                    BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                    BaiduMapMainActivity.this.mTablabel.setVisibility(8);
                    BaiduMapMainActivity.this.mTablabels.setVisibility(8);
                    th.printStackTrace();
                    BaiduMapMainActivity.this.dismissDialog();
                    UIUtils.logD("列表错误：" + th.getMessage());
                    CustomDialogManager.show(BaiduMapMainActivity.this.activityContext, "温馨提示", "加载附近网点失败 是否重新加载?", true, "取消", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.18.1
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                        public void onClickOk() {
                            BaiduMapMainActivity.this.loadYingyetingData();
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dismissDialog();
            UIUtils.toastLong("程序错误【加载附近营业厅数据:" + e.getMessage() + "】");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissDialog() {
        UIUtils.dismissDialog(this.activityContext, this.f18496pd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog() {
        UIUtils.showDialog(this.activityContext, this.f18496pd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBusinessUI(final List<BusinessEntity.BusinessBean> list, int i) {
        this.mBottomSheet.setVisibility(0);
        if (list.size() > 0) {
            this.mapCode = list.get(0).getCityCode();
            this.top_title.setVisibility(0);
            TextView textView = this.top_title;
            textView.setText("已展示" + (list.size() + i) + "个结果");
        } else {
            this.top_title.setVisibility(8);
        }
        this.mYingyetingListAdapter.notifyDataSetChanged();
        this.mYingyetingListAdapter.setOnItemListClickListener(new BaiduYiyetingListAdapter.OnItemListClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.19
            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduYiyetingListAdapter.OnItemListClickListener
            public void onItemListClick(String str, int i2) {
                String str2;
                BaiduMapMainActivity.this.mYingyetingListAdapter.notifyDataSetChanged();
                BaiduMapMainActivity.this.ehallId = str;
                BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                baiduMapMainActivity.businessId = baiduMapMainActivity.ehallId;
                BaiduMapMainActivity.this.mBusinessName = "";
                BaiduMapMainActivity.this.mBusinessName = ((BusinessEntity.BusinessBean) list.get(i2)).getEpName();
                if (TextUtils.isEmpty(((BusinessEntity.BusinessBean) list.get(i2)).getEhall_frontAddress())) {
                    return;
                }
                try {
                    str2 = Base64.encodeToString(((BusinessEntity.BusinessBean) list.get(i2)).getEhall_frontAddress().getBytes("UTF-8"), 2);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    str2 = "";
                }
                StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0001", "点击详情", ((BusinessEntity.BusinessBean) list.get(i2)).getTypeIdentifier(), ((BusinessEntity.BusinessBean) list.get(i2)).getId(), ((BusinessEntity.BusinessBean) list.get(i2)).getEpName(), str2);
                IntentManager.generateIntentAndGo(BaiduMapMainActivity.this.activityContext, ((BusinessEntity.BusinessBean) list.get(i2)).getEhall_frontAddress(), BaiduMapMainActivity.this.mBusinessName, false, "get");
            }
        });
        this.include_top.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                int state = BaiduMapMainActivity.this.behavior.getState();
                if (state == 3) {
                    BaiduMapMainActivity.this.behavior.setState(5);
                } else if (state == 5) {
                    BaiduMapMainActivity.this.behavior.setState(3);
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMarkerui(MarkerOptions markerOptions, BusinessEntity.BusinessBean businessBean, int i) {
        try {
            Marker marker = (Marker) this.baiduMap.addOverlay(markerOptions);
            ArrayList arrayList = new ArrayList();
            MarkerMap markerMap = new MarkerMap();
            markerMap.setId(businessBean.getId());
            markerMap.setType(businessBean.getBusinessType());
            markerMap.setName(businessBean.getEpName());
            markerMap.setUrl(businessBean.getEhall_frontAddress());
            markerMap.setTypeIdentifier(businessBean.getTypeIdentifier());
            markerMap.setDynamicTitle(businessBean.getDynamicTitle());
            markerMap.setDynamicTopicColor(businessBean.getDynamicTopicColor());
            arrayList.add(markerMap);
            this.markerMapList.put(marker.getId(), arrayList);
            this.markerMap.put(businessBean.getId(), marker);
            if (i == 0) {
                ajustMapCenter(marker.getPosition());
                marker.startAnimation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMapUI(List<BusinessEntity.BusinessBean> list) {
        if (!TextUtils.isEmpty(list.get(0).getEhall_radius())) {
            Overlay overlay = this.mCircle;
            if (overlay != null) {
                overlay.remove();
            }
            this.mCircle = this.baiduMap.addOverlay(new CircleOptions().center(this.currentLocation).radius(Integer.valueOf(list.get(0).getEhall_radius()).intValue()).fillColor(list.get(0).getEhall_color()));
        }
        if (this.markers != null) {
            markersLocation();
        }
        if (!TextUtils.isEmpty(list.get(0).getDistanceUnit())) {
            this.defaultZoom = Float.valueOf(list.get(0).getDistanceUnit()).floatValue();
        }
        for (final int i = 0; i < list.size(); i++) {
            final BusinessEntity.BusinessBean businessBean = list.get(i);
            final MarkerOptions animateType = new MarkerOptions().position(new LatLng(businessBean.getEpWeiDu(), businessBean.getEpJingDu())).flat(false).animateType(MarkerOptions.MarkerAnimateType.grow);
            try {
                if (businessBean.getBusinessType().equals("01")) {
                    Glide.with(App.getInstance()).asBitmap().load(businessBean.getPinEpActImg()).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.21
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            if (businessBean.getCouponList() == null || businessBean.getCouponList().size() == 0) {
                                animateType.icon(BaiduMapMainActivity.this.mMarketstore);
                            } else {
                                animateType.icon(BaiduMapMainActivity.this.mGiftstore);
                            }
                            BaiduMapMainActivity.this.getMarkerui(animateType, businessBean, i);
                        }

                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            animateType.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                            BaiduMapMainActivity.this.getMarkerui(animateType, businessBean, i);
                        }
                    });
                } else {
                    Glide.with(App.getInstance()).asBitmap().load(businessBean.getPinEpActImg()).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.22
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(@Nullable Drawable drawable) {
                            super.onLoadFailed(drawable);
                            if (businessBean.getActList() == null || businessBean.getActList().size() == 0) {
                                animateType.icon(BaiduMapMainActivity.this.normalBD);
                            } else {
                                animateType.icon(BaiduMapMainActivity.this.giftBD);
                            }
                            BaiduMapMainActivity.this.getMarkerui(animateType, businessBean, i);
                        }

                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            animateType.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                            BaiduMapMainActivity.this.getMarkerui(animateType, businessBean, i);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getCircleOptions(final String str) {
        try {
            this.mPrivienceCode = CityCodingManager.privienceCode(this.activityContext, this.pitchCity);
            this.mapView.setZoomControlsPosition(new Point(this.measuredWidth - 140, (this.measuredHeight / 2) - 260));
            if (this.currentLocation != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("proCode", this.mPrivienceCode);
                hashMap.put("ep5GState", str);
                hashMap.put("version", this.activityContext.getString(2131886969));
                UIUtils.logD("5g基站URL：" + URLSet.getFiveGInformationUrl());
                UIUtils.logD("5g基站入参：" + hashMap);
                App.getAsyncHttpClient().rxPost(URLSet.getFiveGInformationUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new FiveGFunction()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<FiveGEntity>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.23
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                        if (BaiduMapMainActivity.this.mMCustomHeatMap != null) {
                            BaiduMapMainActivity.this.mMCustomHeatMap.removeHeatMap();
                            BaiduMapMainActivity.this.mMCustomHeatMap = null;
                        }
                        BaiduMapMainActivity.this.baiduMap.setMaxAndMinZoomLevel(14.0f, 9.0f);
                        BaiduMapMainActivity.this.baiduMap.clear();
                        BaiduMapMainActivity.this.isChecked = false;
                        BaiduMapMainActivity.this.mCheckbox.setChecked(false);
                        BaiduMapMainActivity.this.checkboxCharging.setVisibility(8);
                        if ("01".equals(str)) {
                            BaiduMapMainActivity.this.isChecked5g = true;
                            BaiduMapMainActivity.this.checkbox5G.setChecked(true);
                            BaiduMapMainActivity.this.isCheckedConstruction5g = false;
                            BaiduMapMainActivity.this.checkboxConstruction5g.setChecked(false);
                        } else if ("00".equals(str)) {
                            BaiduMapMainActivity.this.isChecked5g = false;
                            BaiduMapMainActivity.this.checkbox5G.setChecked(false);
                            BaiduMapMainActivity.this.isCheckedConstruction5g = true;
                            BaiduMapMainActivity.this.checkboxConstruction5g.setChecked(true);
                        }
                        BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
                        BaiduMapMainActivity.this.mDetails.setVisibility(8);
                        BaiduMapMainActivity.this.fiveGList.clear();
                        BaiduMapMainActivity.this.f18496pd.setMessage("正在加载...");
                        BaiduMapMainActivity.this.showDialog();
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(FiveGEntity fiveGEntity) {
                        BaiduMapMainActivity.this.fiveGList.addAll(fiveGEntity.getBaseStation());
                        if (BaiduMapMainActivity.this.markers != null) {
                            BaiduMapMainActivity.this.markersLocation();
                        }
                        if (BaiduMapMainActivity.this.fiveGList.size() == 0) {
                            UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, fiveGEntity.getErrorMsg(), 0);
                        } else {
                            ArrayList arrayList = new ArrayList();
                            for (int i = 0; i < BaiduMapMainActivity.this.fiveGList.size(); i++) {
                                arrayList.add(new LatLng(((FiveGEntity.BaseStationBean) BaiduMapMainActivity.this.fiveGList.get(i)).getEpWeiDu(), ((FiveGEntity.BaseStationBean) BaiduMapMainActivity.this.fiveGList.get(i)).getEpJingDu()));
                            }
                            BaiduMapMainActivity.this.mMCustomHeatMap = new HeatMap.Builder().data(arrayList).gradient(new Gradient("00".equals(str) ? new int[]{Color.rgb(129, 211, 248), Color.rgb(129, 211, 248), Color.rgb(129, 211, 248), Color.rgb(129, 211, 248), Color.rgb(129, 211, 248), Color.rgb(129, 211, 248)} : new int[]{Color.rgb(18, 255, 29), Color.rgb(18, 255, 29), Color.rgb(18, 255, 29), Color.rgb(18, 255, 29), Color.rgb(18, 255, 29), Color.rgb(18, 255, 29)}, new float[]{0.1f, 0.2f, 0.4f, 0.7f, 0.9f, 0.95f})).radius(50).build();
                            BaiduMapMainActivity.this.baiduMap.addHeatMap(BaiduMapMainActivity.this.mMCustomHeatMap);
                        }
                        BaiduMapMainActivity.this.hallEhallList = fiveGEntity.getExperienceHall();
                        if (BaiduMapMainActivity.this.hallEhallList.size() != 0) {
                            BaiduMapMainActivity.this.markerMap.clear();
                            BaiduMapMainActivity.this.markerMapList.clear();
                            BaiduMapMainActivity.this.currentMarker = null;
                            for (final int i2 = 0; i2 < BaiduMapMainActivity.this.hallEhallList.size(); i2++) {
                                final MarkerOptions animateType = new MarkerOptions().position(new LatLng(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEpWeiDu(), ((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEpJingDu())).flat(false).animateType(MarkerOptions.MarkerAnimateType.grow);
                                Glide.with(BaiduMapMainActivity.this.activityContext).asBitmap().load(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getPinEpActImg()).into((RequestBuilder<Bitmap>) new SimpleTarget<Bitmap>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.23.1
                                    @Override // com.bumptech.glide.request.target.Target
                                    public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                                        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                                    }

                                    @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
                                    public void onLoadFailed(@Nullable Drawable drawable) {
                                        super.onLoadFailed(drawable);
                                        try {
                                            if (i2 < BaiduMapMainActivity.this.hallEhallList.size()) {
                                                animateType.icon(BaiduMapMainActivity.this.m5Gicon);
                                                Marker marker = (Marker) BaiduMapMainActivity.this.baiduMap.addOverlay(animateType);
                                                ArrayList arrayList2 = new ArrayList();
                                                arrayList2.clear();
                                                MarkerMap markerMap = new MarkerMap();
                                                markerMap.setId(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId());
                                                markerMap.setType("1");
                                                markerMap.setName(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEpName());
                                                markerMap.setUrl(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEhall_frontAddress() + "?mejd=" + String.valueOf(BaiduMapMainActivity.this.currentLocation.longitude) + "&mewd=" + String.valueOf(BaiduMapMainActivity.this.currentLocation.latitude) + "&ehallId=" + ((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId() + "&version=" + BaiduMapMainActivity.this.activityContext.getString(2131886969));
                                                markerMap.setTypeIdentifier("0");
                                                arrayList2.add(markerMap);
                                                BaiduMapMainActivity.this.markerMapList.put(marker.getId(), arrayList2);
                                                BaiduMapMainActivity.this.markerMap.put(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId(), marker);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                                        try {
                                            if (i2 < BaiduMapMainActivity.this.hallEhallList.size()) {
                                                animateType.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                                                Marker marker = (Marker) BaiduMapMainActivity.this.baiduMap.addOverlay(animateType);
                                                ArrayList arrayList2 = new ArrayList();
                                                arrayList2.clear();
                                                MarkerMap markerMap = new MarkerMap();
                                                markerMap.setId(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId());
                                                markerMap.setType("1");
                                                markerMap.setName(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEpName());
                                                markerMap.setUrl(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getEhall_frontAddress() + "?mejd=" + String.valueOf(BaiduMapMainActivity.this.currentLocation.longitude) + "&mewd=" + String.valueOf(BaiduMapMainActivity.this.currentLocation.latitude) + "&ehallId=" + ((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId() + "&version=" + BaiduMapMainActivity.this.activityContext.getString(2131886969));
                                                markerMap.setTypeIdentifier("0");
                                                arrayList2.add(markerMap);
                                                BaiduMapMainActivity.this.markerMapList.put(marker.getId(), arrayList2);
                                                BaiduMapMainActivity.this.markerMap.put(((FiveGEntity.ExperienceHallBean) BaiduMapMainActivity.this.hallEhallList.get(i2)).getId(), marker);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            }
                        }
                        BaiduMapMainActivity.this.dismissDialog();
                        if (BaiduMapMainActivity.this.isFiveGLog) {
                            if (TextUtils.isEmpty(fiveGEntity.getFiveGStationTips())) {
                                UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, "5g覆盖地图仅供参考，具体网络情况需视当时基站状态。", 1);
                            } else {
                                UIUtils.showCenterOnlyTextToast(BaiduMapMainActivity.this.activityContext, fiveGEntity.getFiveGStationTips(), 1);
                            }
                            BaiduMapMainActivity.this.isFiveGLog = false;
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable th) {
                        UIUtils.logD("5g基站错误：" + th.getMessage());
                        BaiduMapMainActivity.this.fiveGList.clear();
                        BaiduMapMainActivity.this.dismissDialog();
                        CustomDialogManager.show(BaiduMapMainActivity.this.activityContext, "温馨提示", "数据加载超时，请重试", true, "取消", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.23.2
                            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                            public void onClickOk() {
                                BaiduMapMainActivity.this.getCircleOptions(str);
                            }
                        });
                    }
                });
            } else {
                UIUtils.showCenterOnlyTextToast(this.activityContext, "您还没有定位当前位置~", 0);
            }
        } catch (Exception e) {
            dismissDialog();
            e.printStackTrace();
            UIUtils.logD("5g基站错误：" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadYingyetingDetails(LatLng latLng, String str, String str2) {
        BusinessDetailsLoader.loadYingyetingDetails(this.activityContext, latLng, str, str2, new Observer<BusinessDetailsEntity>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.24
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
                BaiduMapMainActivity.this.f18496pd.setMessage("努力加载中...");
                BaiduMapMainActivity.this.showDialog();
                BaiduMapMainActivity.this.mBottomSheet.setVisibility(8);
            }

            @Override // io.reactivex.Observer
            public void onNext(BusinessDetailsEntity businessDetailsEntity) {
                BaiduMapMainActivity.this.mBusinessDetails = businessDetailsEntity;
                if (!BaiduMapMainActivity.this.mBusinessDetails.getCode().equals("-1")) {
                    BaiduMapMainActivity.this.mDetails.setVisibility(0);
                    BaiduMapMainActivity.this.updateBusinessDetailsUI();
                } else {
                    BaiduMapMainActivity.this.mDetails.setVisibility(8);
                    Activity activity = BaiduMapMainActivity.this.activityContext;
                    UIUtils.showCenterOnlyTextToast(activity, "" + BaiduMapMainActivity.this.mBusinessDetails.getMsg(), 0);
                }
                BaiduMapMainActivity.this.dismissDialog();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                UIUtils.logD("详情报错：" + th.getMessage());
                th.printStackTrace();
                BaiduMapMainActivity.this.dismissDialog();
                CustomDialogManager.show(BaiduMapMainActivity.this.activityContext, "温馨提示", "加载网点详情失败 是否重新加载?", true, "取消", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.24.1
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                    public void onClickOk() {
                        BaiduMapMainActivity.this.businessId = BaiduMapMainActivity.this.ehallId;
                        BaiduMapMainActivity.this.loadYingyetingDetails(BaiduMapMainActivity.this.mLatLng, BaiduMapMainActivity.this.businessId, BaiduMapMainActivity.this.mType);
                    }
                });
            }
        });
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RecyclerTablabeslView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public RecyclerTablabeslView() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(BaiduMapMainActivity.this.activityContext).inflate(2131493026, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint({"RecyclerView"}) final int i) {
            final MyHolder myHolder = (MyHolder) viewHolder;
            myHolder.mTitle.setText(((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(i)).getTitleDetial());
            if (BaiduMapMainActivity.this.IndexLabels == i) {
                myHolder.bottom.setVisibility(0);
            } else {
                myHolder.bottom.setVisibility(8);
            }
            if (i == 0 && BaiduMapMainActivity.this.isLabelsClick) {
                BaiduMapMainActivity.this.beginNum = -1;
                BaiduMapMainActivity baiduMapMainActivity = BaiduMapMainActivity.this;
                baiduMapMainActivity.titleFlag = ((LabelDataEntity) baiduMapMainActivity.mLabelsList.get(0)).getTitleFlag();
                BaiduMapMainActivity.this.isLabBG = false;
                if (((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(0)).getUnifiedLabel().size() == 0) {
                    BaiduMapMainActivity.this.loadYingyetingData();
                } else {
                    BaiduMapMainActivity.this.mLabelList.clear();
                    BaiduMapMainActivity.this.mLabelList.addAll(((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(i)).getUnifiedLabel());
                    BaiduMapMainActivity.this.mTablabelAdapter.notifyDataSetChanged();
                    BaiduMapMainActivity.this.labelFlag = "";
                    BaiduMapMainActivity.this.labelDetail = "";
                    BaiduMapMainActivity.this.isRefresh = true;
                    BaiduMapMainActivity.this.loadYingyetingData();
                }
            }
            myHolder.mTitle.setTag(Integer.valueOf(i));
            myHolder.title_layout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.RecyclerTablabeslView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0010", "点击顶部标签", "按钮", "0", ((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(i)).getTitleDetial(), "");
                    BaiduMapMainActivity.this.isLabelsClick = false;
                    BaiduMapMainActivity.this.IndexLabels = ((Integer) myHolder.mTitle.getTag()).intValue();
                    int unused = BaiduMapMainActivity.this.IndexLabels;
                    int i2 = i;
                    BaiduMapMainActivity.this.beginNum = -1;
                    BaiduMapMainActivity.this.isLabBG = false;
                    BaiduMapMainActivity.this.titleFlag = ((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(i)).getTitleFlag();
                    if (((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(0)).getUnifiedLabel().size() == 0) {
                        BaiduMapMainActivity.this.loadYingyetingData();
                    } else {
                        BaiduMapMainActivity.this.mLabelList.clear();
                        BaiduMapMainActivity.this.mLabelList.addAll(((LabelDataEntity) BaiduMapMainActivity.this.mLabelsList.get(i)).getUnifiedLabel());
                        BaiduMapMainActivity.this.mTablabelAdapter.notifyDataSetChanged();
                        BaiduMapMainActivity.this.labelFlag = "";
                        BaiduMapMainActivity.this.labelDetail = "";
                        BaiduMapMainActivity.this.isRefresh = true;
                        BaiduMapMainActivity.this.loadYingyetingData();
                    }
                    RecyclerTablabeslView.this.notifyDataSetChanged();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return BaiduMapMainActivity.this.mLabelsList.size();
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class MyHolder extends RecyclerView.ViewHolder {
            private final View bottom;
            private final TextView mTitle;
            private final LinearLayout title_layout;

            public MyHolder(View view) {
                super(view);
                this.mTitle = (TextView) view.findViewById(2131298785);
                this.bottom = view.findViewById(2131296525);
                this.title_layout = (LinearLayout) view.findViewById(2131298794);
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RecyclerTablabelView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<LabelDataEntity.UnifiedLabelBean> list;

        public RecyclerTablabelView(List<LabelDataEntity.UnifiedLabelBean> list) {
            this.list = list;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(BaiduMapMainActivity.this.activityContext).inflate(2131493025, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @SuppressLint({"RecyclerView"}) final int i) {
            final MyHolder myHolder = (MyHolder) viewHolder;
            myHolder.mTitle.setText(this.list.get(i).getServiceLabel());
            if (BaiduMapMainActivity.this.isLabBG) {
                if (BaiduMapMainActivity.this.IndexLabel == i) {
                    myHolder.mTitle.setBackgroundResource(2131231039);
                    myHolder.mTitle.setTextColor(Color.parseColor("#e60028"));
                } else {
                    myHolder.mTitle.setBackgroundResource(2131231038);
                    myHolder.mTitle.setTextColor(Color.parseColor("#666666"));
                }
            } else {
                myHolder.mTitle.setBackgroundResource(2131231038);
                myHolder.mTitle.setTextColor(Color.parseColor("#666666"));
            }
            if (i == 0 && BaiduMapMainActivity.this.isLabelClick) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(myHolder.layout.getLayoutParams());
                layoutParams.setMargins(UIUtils.dip2px(BaiduMapMainActivity.this.activityContext, 15.0f), UIUtils.dip2px(BaiduMapMainActivity.this.activityContext, 7.0f), UIUtils.dip2px(BaiduMapMainActivity.this.activityContext, 7.0f), UIUtils.dip2px(BaiduMapMainActivity.this.activityContext, 7.0f));
                myHolder.layout.setLayoutParams(layoutParams);
            }
            myHolder.mTitle.setTag(Integer.valueOf(i));
            myHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduMapMainActivity.RecyclerTablabelView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    BaiduMapMainActivity.this.isLabelClick = false;
                    StatisticsUploadUtils.upload(BaiduMapMainActivity.this.activityContext, "FJ0009", "点击服务标签", "按钮", "0", ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getServiceLabel(), "");
                    BaiduMapMainActivity.this.beginNum = -1;
                    if (BaiduMapMainActivity.this.isLabBG) {
                        if (BaiduMapMainActivity.this.IndexLabel == i) {
                            BaiduMapMainActivity.this.IndexLabel = BaiduMapMainActivity.this.mLabelList.size();
                            BaiduMapMainActivity.this.titleFlag = "";
                            BaiduMapMainActivity.this.labelFlag = "";
                            BaiduMapMainActivity.this.labelDetail = "";
                        } else {
                            BaiduMapMainActivity.this.IndexLabel = ((Integer) myHolder.mTitle.getTag()).intValue();
                            BaiduMapMainActivity.this.labelFlag = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getLabelFlag();
                            if (!"0".equals(BaiduMapMainActivity.this.labelFlag)) {
                                if ("1".equals(BaiduMapMainActivity.this.labelFlag)) {
                                    BaiduMapMainActivity.this.labelDetail = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getId();
                                }
                            } else {
                                BaiduMapMainActivity.this.labelDetail = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getServiceLabel();
                            }
                        }
                    } else {
                        BaiduMapMainActivity.this.IndexLabel = ((Integer) myHolder.mTitle.getTag()).intValue();
                        BaiduMapMainActivity.this.labelFlag = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getLabelFlag();
                        if (!"0".equals(BaiduMapMainActivity.this.labelFlag)) {
                            if ("1".equals(BaiduMapMainActivity.this.labelFlag)) {
                                BaiduMapMainActivity.this.labelDetail = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getId();
                            }
                        } else {
                            BaiduMapMainActivity.this.labelDetail = ((LabelDataEntity.UnifiedLabelBean) RecyclerTablabelView.this.list.get(i)).getServiceLabel();
                        }
                    }
                    BaiduMapMainActivity.this.isLabBG = true;
                    BaiduMapMainActivity.this.isRefresh = true;
                    BaiduMapMainActivity.this.loadYingyetingData();
                    RecyclerTablabelView.this.notifyDataSetChanged();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.list.size();
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class MyHolder extends RecyclerView.ViewHolder {
            private RelativeLayout layout;
            private final TextView mTitle;

            public MyHolder(View view) {
                super(view);
                this.layout = (RelativeLayout) view.findViewById(2131296493);
                this.mTitle = (TextView) view.findViewById(2131298785);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation getScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.5f);
        scaleAnimation.setDuration(500L);
        return scaleAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animation getScaleAnimation2() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1.0f);
        scaleAnimation.setDuration(500L);
        return scaleAnimation;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MarkerMap {
        private String dynamicTitle;
        private String dynamicTopicColor;

        /* renamed from: id */
        private String f18498id;
        private String name;
        private String type;
        private String typeIdentifier;
        private String url;

        public MarkerMap() {
        }

        public String getId() {
            return this.f18498id;
        }

        public void setId(String str) {
            this.f18498id = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getTypeIdentifier() {
            return this.typeIdentifier;
        }

        public void setTypeIdentifier(String str) {
            this.typeIdentifier = str;
        }

        public String getDynamicTitle() {
            return this.dynamicTitle;
        }

        public void setDynamicTitle(String str) {
            this.dynamicTitle = str;
        }

        public String getDynamicTopicColor() {
            return this.dynamicTopicColor;
        }

        public void setDynamicTopicColor(String str) {
            this.dynamicTopicColor = str;
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.mapView.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.mapView.onPause();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        getContentResolver().unregisterContentObserver(this.ssObserver);
        this.ssHandler.hideScreenshot();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            this.normalBD.recycle();
            this.giftBD.recycle();
            this.mMarketstore.recycle();
            this.mGiftstore.recycle();
            this.mLocClient.stop();
            this.mLocClient.unRegisterLocationListener(this.myListener);
            this.baiduMap.setMyLocationEnabled(false);
            this.baiduMap.clear();
            this.mapView.onDestroy();
            this.mapView = null;
            this.mGeoCoder.destroy();
            this.markerMap.clear();
            this.markerMapList.clear();
            if (this.mMCustomHeatMap != null) {
                this.mMCustomHeatMap.removeHeatMap();
                this.mMCustomHeatMap = null;
            }
            this.mBusinessList.clear();
            this.mLabelList.clear();
            this.mLabelsList.clear();
            this.hallEhallList.clear();
            this.mDynamicList.clear();
            this.mLivingList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        try {
            getContentResolver().unregisterContentObserver(this.ssObserver);
            getContentResolver().registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, this.ssObserver);
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast(e.getMessage());
        }
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        if (this.isDetailsCoupons) {
            getCouponsData();
            this.isDetailsCoupons = false;
        }
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.measuredHeight = UIUtils.getScreenHeight(this.activityContext);
        this.measuredWidth = UIUtils.getScreenWidth(this.activityContext);
    }
}
