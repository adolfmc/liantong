package com.sinovatech.unicom.basic.p315ui.share;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerShareConfig;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMenuManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebMoreFunctionView {
    private Activity context;
    private String defaultMenuId;
    private Listener listener;
    private List<WebMenuManager.WebMenuEntity> webMenuEntityList;
    private UserManager userManager = UserManager.getInstance();
    private SharePreferenceUtil preference = App.getSharePreferenceUtil();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$CaptureCallback */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CaptureCallback {
        void captureFinish();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$Listener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface Listener {
        void doCaptureWebview(String str, String str2, String str3, String str4, String str5, String str6, String str7, CaptureCallback captureCallback);

        void onBackHome();

        void onClick();

        void onMenu();

        void onShareCancle(String str);

        void onShareComplete(String str);

        void onShareError(String str, String str2);

        void refresh();

        void tiaozhuan(String str);
    }

    public WebMoreFunctionView(Activity activity, String str, Listener listener) {
        this.context = activity;
        this.defaultMenuId = str;
        this.listener = listener;
    }

    public View getContentView(List<WebMenuManager.WebMenuEntity> list) {
        View inflate = this.context.getLayoutInflater().inflate(2131493451, (ViewGroup) null);
        this.webMenuEntityList = list;
        ListView listView = (ListView) inflate.findViewById(2131299571);
        listView.setAdapter((ListAdapter) new MoreAdapter());
        listView.setOnItemClickListener(new C79211());
        inflate.setFocusableInTouchMode(true);
        inflate.setOnKeyListener(new View.OnKeyListener() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.2
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 0 && i == 82) {
                    WebMoreFunctionView.this.listener.onMenu();
                    return true;
                }
                return true;
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C79211 implements AdapterView.OnItemClickListener {
        C79211() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemClickEnter(view, i, this);
            Tracker.onItemClick(adapterView, view, i, j);
            final WebMenuManager.WebMenuEntity webMenuEntity = (WebMenuManager.WebMenuEntity) WebMoreFunctionView.this.webMenuEntityList.get(i);
            if (webMenuEntity.code.equalsIgnoreCase("shoucang")) {
                if (App.hasLogined()) {
                    if (WebMoreFunctionView.this.context instanceof WebDetailActivity) {
                        String str = RecentCustomManager.ADD;
                        if (webMenuEntity.isCollect) {
                            str = RecentCustomManager.CANCEL;
                        }
                        new RecentCustomManager((WebDetailActivity) WebMoreFunctionView.this.context).deleteOrAddMenu(str, webMenuEntity.getAppId(), webMenuEntity.getProductId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                WebMenuManager.WebMenuEntity webMenuEntity2;
                                String str2 = "失败";
                                String str3 = webMenuEntity.isCollect ? "取消收藏" : "收藏";
                                if (recentStateEntity.isSuccess()) {
                                    str2 = "成功";
                                    webMenuEntity.setCollect(!webMenuEntity2.isCollect);
                                }
                                String str4 = str3 + str2;
                                if (TextUtils.equals("收藏", str3)) {
                                    str4 = recentStateEntity.getRespMsg();
                                }
                                UIUtils.toastCenter(str4);
                            }
                        });
                    }
                } else {
                    WebMoreFunctionView.this.context.startActivity(new Intent(WebMoreFunctionView.this.context, LoginBindActivity.class));
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("wodeshoucang")) {
                if (App.hasLogined()) {
                    WebMoreFunctionView.this.context.startActivity(new Intent(WebMoreFunctionView.this.context, CustomRecentActivity.class));
                } else {
                    WebMoreFunctionView.this.context.startActivity(new Intent(WebMoreFunctionView.this.context, LoginBindActivity.class));
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("cai")) {
                if (App.hasLogined()) {
                    if (!WebMoreFunctionView.this.preference.getBoolean("isCai")) {
                        WebMoreFunctionView.this.dingOrcai("02");
                    } else {
                        UIUtils.toast("已经踩过了！");
                    }
                } else {
                    WebMoreFunctionView.this.context.startActivity(new Intent(WebMoreFunctionView.this.context, LoginBindActivity.class));
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("fenxiang")) {
                try {
                    StatisticsUploadUtils.upload(WebMoreFunctionView.this.context, "S2ndpage1028", "", "fenxiang", "", "", "");
                    JSONObject jSONObject = new JSONObject(webMenuEntity.shareJson);
                    String optString = jSONObject.optString("shareType");
                    String optString2 = jSONObject.optString("shareURL");
                    String optString3 = jSONObject.optString("shareQrcodeURL");
                    String optString4 = jSONObject.optString("templetImg");
                    String optString5 = jSONObject.optString("businessCode");
                    String optString6 = jSONObject.optString("businessName");
                    String optString7 = jSONObject.optString("provider");
                    String str2 = TextUtils.equals("image", optString) ? "longScreenshot" : optString;
                    final C79232 c79232 = new C79232(optString2, optString3, optString4, optString5, optString6, optString7);
                    if (str2.equals("longScreenshot")) {
                        WebMoreFunctionView.this.listener.doCaptureWebview(str2, optString2, optString3, optString4, optString5, optString6, optString7, new CaptureCallback() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.3
                            @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.CaptureCallback
                            public void captureFinish() {
                                ShareManager.showShareDialog(WebMoreFunctionView.this.context, new WebMenuManager.WebMenuEntity("jietufenxiang", "截图分享", WebMenuManager.getDefaultShareContent(), WebMenuManager.getLongshotShareString(), WebMenuManager.getDefaultLongShareJson(), ""), c79232);
                            }
                        });
                    } else {
                        ShareManager.showShareDialog(WebMoreFunctionView.this.context, webMenuEntity, c79232);
                    }
                    ManagerShareConfig.getInstance().getWeworkConfig((AppCompatActivity) WebMoreFunctionView.this.context).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.4
                        @Override // io.reactivex.functions.Consumer
                        public void accept(String str3) throws Exception {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("jietufenxiang")) {
                try {
                    StatisticsUploadUtils.upload(WebMoreFunctionView.this.context, "S2ndpage1028", "", "jietufenxiang", "", "", "");
                    if (TextUtils.isEmpty(webMenuEntity.shareJson) || webMenuEntity.shareJson.equalsIgnoreCase("null")) {
                        webMenuEntity.shareJson = WebMenuManager.getDefaultLongShareJson();
                    }
                    if (TextUtils.isEmpty(webMenuEntity.shareList) || webMenuEntity.shareList.equalsIgnoreCase("null")) {
                        webMenuEntity.shareList = WebMenuManager.getLongshotShareString();
                    }
                    JSONObject jSONObject2 = new JSONObject(webMenuEntity.shareJson);
                    String optString8 = jSONObject2.optString("shareType");
                    String optString9 = jSONObject2.optString("shareQrcodeURL");
                    String optString10 = jSONObject2.optString("templetImg");
                    String optString11 = jSONObject2.optString("shareURL");
                    String optString12 = jSONObject2.optString("businessCode");
                    String optString13 = jSONObject2.optString("businessName");
                    String optString14 = jSONObject2.optString("provider");
                    String str3 = TextUtils.equals("image", optString8) ? "longScreenshot" : optString8;
                    StringBuilder sb = new StringBuilder();
                    sb.append("==");
                    sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                    MsLogUtil.m7979d("截图分享信息", sb.toString());
                    final C79285 c79285 = new C79285(str3, optString11, optString9, optString10, optString12, optString13, optString14);
                    WebMoreFunctionView.this.listener.doCaptureWebview(str3, optString11, optString9, optString10, optString12, optString13, optString14, new CaptureCallback() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.6
                        @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.CaptureCallback
                        public void captureFinish() {
                            ShareManager.showShareDialog(WebMoreFunctionView.this.context, webMenuEntity, c79285);
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("shuaxin")) {
                try {
                    WebMoreFunctionView.this.listener.refresh();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("copyUrl")) {
                try {
                    String optString15 = new JSONObject(webMenuEntity.shareJson).optString("copyUrlContent");
                    ((ClipboardManager) WebMoreFunctionView.this.context.getSystemService("clipboard")).setText(optString15);
                    App.getSharePreferenceUtil().putString("wokoulingcontent", optString15);
                    UIUtils.toast("复制成功");
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("tucao")) {
                String str4 = URLSet.getTucaoURL() + "?title=" + App.webviewTitle;
                PvCurrencyLogUtils.toCaoClickPvLog(str4);
                IntentManager.generateIntentAndGo(WebMoreFunctionView.this.context, str4, webMenuEntity.title, false, "get");
            } else if (webMenuEntity.code.equalsIgnoreCase("shouye")) {
                try {
                    WebMoreFunctionView.this.listener.onBackHome();
                    App.mainTagFromOtherActivity = MainActivity.Fragment_Home;
                    Intent intent = new Intent(WebMoreFunctionView.this.context, MainActivity.class);
                    intent.putExtra("from", "fromWebShareHome");
                    WebMoreFunctionView.this.context.startActivity(intent);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("tiaozhuan") && !TextUtils.isEmpty(webMenuEntity.desc) && URLUtil.isValidUrl(webMenuEntity.desc)) {
                WebMoreFunctionView.this.listener.tiaozhuan(webMenuEntity.desc);
            }
            WebMoreFunctionView.this.listener.onClick();
            NBSActionInstrumentation.onItemClickExit();
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$1$2 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C79232 implements ShareManager.ShareListener {
            final /* synthetic */ String val$businessCode;
            final /* synthetic */ String val$businessName;
            final /* synthetic */ String val$mobileA;
            final /* synthetic */ String val$shareQrcodeURL;
            final /* synthetic */ String val$shareurl;
            final /* synthetic */ String val$templetImg;

            C79232(String str, String str2, String str3, String str4, String str5, String str6) {
                this.val$shareurl = str;
                this.val$shareQrcodeURL = str2;
                this.val$templetImg = str3;
                this.val$businessCode = str4;
                this.val$businessName = str5;
                this.val$mobileA = str6;
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onError(String str, String str2) {
                WebMoreFunctionView.this.listener.onShareError(str, str2);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onComplete(String str) {
                if (str.equals("jietufenxiang")) {
                    WebMoreFunctionView.this.listener.doCaptureWebview("longScreenshot", this.val$shareurl, this.val$shareQrcodeURL, this.val$templetImg, this.val$businessCode, this.val$businessName, this.val$mobileA, new CaptureCallback() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.2.1
                        @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.CaptureCallback
                        public void captureFinish() {
                            ShareManager.showShareDialog(WebMoreFunctionView.this.context, new WebMenuManager.WebMenuEntity("jietufenxiang", "截图分享", WebMenuManager.getDefaultShareContent(), WebMenuManager.getLongshotShareString(), WebMenuManager.getDefaultLongShareJson(), ""), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.2.1.1
                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onComplete(String str2) {
                                    WebMoreFunctionView.this.listener.onShareComplete(str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onCancel(String str2) {
                                    WebMoreFunctionView.this.listener.onShareCancle(str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onError(String str2, String str3) {
                                    WebMoreFunctionView.this.listener.onShareError(str2, str3);
                                }
                            });
                        }
                    });
                } else {
                    WebMoreFunctionView.this.listener.onShareComplete(str);
                }
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onCancel(String str) {
                WebMoreFunctionView.this.listener.onShareCancle(str);
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$1$5 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C79285 implements ShareManager.ShareListener {
            final /* synthetic */ String val$businessCode;
            final /* synthetic */ String val$businessName;
            final /* synthetic */ String val$finalSharetype;
            final /* synthetic */ String val$mobileA;
            final /* synthetic */ String val$shareQrcodeURL;
            final /* synthetic */ String val$shareurl;
            final /* synthetic */ String val$templetImg;

            C79285(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
                this.val$finalSharetype = str;
                this.val$shareurl = str2;
                this.val$shareQrcodeURL = str3;
                this.val$templetImg = str4;
                this.val$businessCode = str5;
                this.val$businessName = str6;
                this.val$mobileA = str7;
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onError(String str, String str2) {
                WebMoreFunctionView.this.listener.onShareError(str, str2);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onComplete(String str) {
                if (str.equals("jietufenxiang")) {
                    WebMoreFunctionView.this.listener.doCaptureWebview(this.val$finalSharetype, this.val$shareurl, this.val$shareQrcodeURL, this.val$templetImg, this.val$businessCode, this.val$businessName, this.val$mobileA, new CaptureCallback() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.5.1
                        @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.CaptureCallback
                        public void captureFinish() {
                            ShareManager.showShareDialog(WebMoreFunctionView.this.context, new WebMenuManager.WebMenuEntity("jietufenxiang", "截图分享", WebMenuManager.getDefaultShareContent(), WebMenuManager.getLongshotShareString(), WebMenuManager.getDefaultLongShareJson(), ""), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView.1.5.1.1
                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onComplete(String str2) {
                                    WebMoreFunctionView.this.listener.onShareComplete(str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onCancel(String str2) {
                                    WebMoreFunctionView.this.listener.onShareCancle(str2);
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onError(String str2, String str3) {
                                    WebMoreFunctionView.this.listener.onShareError(str2, str3);
                                }
                            });
                        }
                    });
                } else {
                    WebMoreFunctionView.this.listener.onShareComplete(str);
                }
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onCancel(String str) {
                WebMoreFunctionView.this.listener.onShareCancle(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.basic.ui.share.WebMoreFunctionView$MoreAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MoreAdapter extends BaseAdapter {
        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        MoreAdapter() {
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return WebMoreFunctionView.this.webMenuEntityList.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = WebMoreFunctionView.this.context.getLayoutInflater().inflate(2131493450, (ViewGroup) null);
            WebMenuManager.WebMenuEntity webMenuEntity = (WebMenuManager.WebMenuEntity) WebMoreFunctionView.this.webMenuEntityList.get(i);
            ImageView imageView = (ImageView) inflate.findViewById(2131299569);
            TextView textView = (TextView) inflate.findViewById(2131299570);
            View findViewById = inflate.findViewById(2131299568);
            if (i == WebMoreFunctionView.this.webMenuEntityList.size() - 1) {
                findViewById.setVisibility(8);
            }
            if (webMenuEntity.code.equalsIgnoreCase("shoucang")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "收藏" : webMenuEntity.title);
                if (webMenuEntity.isCollect) {
                    imageView.setImageResource(2131232607);
                } else {
                    imageView.setImageResource(2131232606);
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("wodeshoucang")) {
                imageView.setImageResource(2131232608);
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "我的收藏" : webMenuEntity.title);
            } else if (webMenuEntity.code.equalsIgnoreCase("cai")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "踩一下" : webMenuEntity.title);
                if (WebMoreFunctionView.this.preference.getBoolean("isCai")) {
                    imageView.setImageResource(2131232611);
                } else {
                    imageView.setImageResource(2131232610);
                }
            } else if (webMenuEntity.code.equalsIgnoreCase("fenxiang")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "分享" : webMenuEntity.title);
                imageView.setImageResource(2131232614);
            } else if (webMenuEntity.code.equalsIgnoreCase("tucao")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "吐槽" : webMenuEntity.title);
                imageView.setImageResource(2131232555);
            } else if (webMenuEntity.code.equalsIgnoreCase("shouye")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "回到首页" : webMenuEntity.title);
                imageView.setImageResource(2131232619);
            } else if (webMenuEntity.code.equalsIgnoreCase("tiaozhuan")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "跳转" : webMenuEntity.title);
                imageView.setImageResource(2131232552);
            } else if (webMenuEntity.code.equalsIgnoreCase("jietufenxiang")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "截图分享" : webMenuEntity.title);
                imageView.setImageResource(2131232615);
            } else if (webMenuEntity.code.equalsIgnoreCase("copyUrl")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "复制链接" : webMenuEntity.title);
                imageView.setImageResource(2131232613);
            } else if (webMenuEntity.code.equalsIgnoreCase("shuaxin")) {
                textView.setText(TextUtils.isEmpty(webMenuEntity.title) ? "刷新" : webMenuEntity.title);
                imageView.setImageResource(2131231962);
            }
            return inflate;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dingOrcai(String str) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("bizNo", this.defaultMenuId);
        requestParams.put("likeType", str);
        requestParams.put("version", this.context.getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        App.getAsyncHttpClient().get(URLSet.getFavPath(), requestParams, (AsyncHttpResponseHandler) null);
        if (str.equals("01")) {
            this.preference.putBoolean("isDing", true);
            UIUtils.toast("感谢您的支持！");
        } else if (str.equals("02")) {
            this.preference.putBoolean("isCai", true);
            UIUtils.toast("我们会继续努力！");
        }
    }
}
