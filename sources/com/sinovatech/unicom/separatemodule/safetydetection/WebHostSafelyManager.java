package com.sinovatech.unicom.separatemodule.safetydetection;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p315ui.utils.HomeTabDebounce;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebHostSafelyManager {
    public static final String Strategy_Black = "Strategy_Black";
    public static final String Strategy_Gray = "Strategy_Gray";
    public static final String Strategy_WhiteOrPass = "Strategy_WhiteOrPass";
    public static final String TAG = "WebHostSafelyManager";
    private static WebHostSafelyManager instance;
    private Dialog blackDialog;
    private Dialog grayDialog;
    private List<String> localWebLoadSafelyHostList = new ArrayList();
    private List<String> localEcstokenSafelyHostList = new ArrayList();
    private Box<SafelyHostEntity> safelyHostEntityBox = App.getBoxStore().boxFor(SafelyHostEntity.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WebHostSafelyListener {
        void allow();

        void refuse();

        void warn(SafelyHostEntity safelyHostEntity);
    }

    public String interceptEcsTokenHost(String str) {
        return Strategy_WhiteOrPass;
    }

    public static synchronized WebHostSafelyManager getInstance() {
        WebHostSafelyManager webHostSafelyManager;
        synchronized (WebHostSafelyManager.class) {
            if (instance == null) {
                synchronized (WebHostSafelyManager.class) {
                    if (instance == null) {
                        instance = new WebHostSafelyManager();
                    }
                }
            }
            webHostSafelyManager = instance;
        }
        return webHostSafelyManager;
    }

    public WebHostSafelyManager() {
        initLoaclWhite();
    }

    private void initLoaclWhite() {
        try {
            JSONObject jSONObject = new JSONObject(FileTools.readFile(App.getInstance().getApplicationContext().getResources().getAssets().open("safelyhost.json")));
            JSONArray jSONArray = jSONObject.getJSONArray("h5loadsafelydomain");
            for (int i = 0; i < jSONArray.length(); i++) {
                this.localWebLoadSafelyHostList.add(jSONArray.getString(i));
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("ecstokensafelydomain");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                this.localEcstokenSafelyHostList.add(jSONArray2.getString(i2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadHostSafetyConfig() {
        try {
            final SafelyHostEntity findFirst = this.safelyHostEntityBox.query().build().findFirst();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appVersion", App.getInstance().getApplicationContext().getString(2131886969));
            if (findFirst != null) {
                jSONObject.put("whiteVersion", findFirst.getWhiteVersion());
                jSONObject.put("blackVersion", findFirst.getBlackVersion());
                jSONObject.put("ecsTokenVersion", findFirst.getEcsTokenVersion());
            } else {
                jSONObject.put("whiteVersion", "");
                jSONObject.put("blackVersion", "");
                jSONObject.put("ecsTokenVersion", "");
                findFirst = new SafelyHostEntity();
            }
            App.getAsyncHttpClient().rxPost(URLSet.getSafelyHostConfig(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Object>() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.3
                @Override // io.reactivex.functions.Function
                public Object apply(String str) throws Exception {
                    try {
                        MsLogUtil.m7979d(WebHostSafelyManager.TAG, "loadHostSafetyConfig " + str);
                        JSONObject jSONObject2 = new JSONObject(str);
                        if ("0000".equals(jSONObject2.getString("code"))) {
                            JSONObject jSONObject3 = jSONObject2.getJSONObject("data");
                            JSONObject optJSONObject = jSONObject3.optJSONObject("whiteDomain");
                            boolean z = true;
                            if (optJSONObject != null) {
                                JSONArray optJSONArray = optJSONObject.optJSONArray("domainNameList");
                                boolean z2 = optJSONObject.optString("domainNameOpenFlag", "0").equals("1");
                                String optString = optJSONObject.optString("whiteVersion", "");
                                if (!optString.equals(findFirst.getWhiteVersion())) {
                                    findFirst.setWhiteHosts(WebHostSafelyManager.this.formatHosts(optJSONArray));
                                }
                                findFirst.setWhiteOpen(z2);
                                findFirst.setWhiteVersion(optString);
                            } else {
                                findFirst.setWhiteHosts("");
                                findFirst.setWhiteOpen(false);
                                findFirst.setWhiteVersion("");
                            }
                            JSONObject optJSONObject2 = jSONObject3.optJSONObject("blackDomain");
                            if (optJSONObject2 != null) {
                                JSONArray optJSONArray2 = optJSONObject2.optJSONArray("domainNameList");
                                boolean z3 = optJSONObject2.optString("domainNameOpenFlag", "0").equals("1");
                                String optString2 = optJSONObject2.optString("blackVersion", "");
                                String optString3 = optJSONObject2.optString("blackdomainNameHint", "");
                                String optString4 = optJSONObject2.optString("blackDomainNameHintUrl", "");
                                String optString5 = optJSONObject2.optString("cancelName", "");
                                String optString6 = optJSONObject2.optString("confirmName", "");
                                if (!optString2.equals(findFirst.getBlackVersion())) {
                                    findFirst.setBlackHosts(WebHostSafelyManager.this.formatHosts(optJSONArray2));
                                }
                                findFirst.setBlackHint(optString3);
                                findFirst.setBlackHintLink(optString4);
                                findFirst.setBlackOpen(z3);
                                findFirst.setBlackVersion(optString2);
                                findFirst.setBlackCancelName(optString5);
                                findFirst.setBlackConfirmName(optString6);
                            } else {
                                findFirst.setBlackHosts("");
                                findFirst.setBlackHint("");
                                findFirst.setBlackHintLink("");
                                findFirst.setBlackOpen(false);
                                findFirst.setBlackVersion("");
                                findFirst.setBlackCancelName("");
                                findFirst.setBlackConfirmName("");
                            }
                            JSONObject optJSONObject3 = jSONObject3.optJSONObject("grayDomin");
                            if (optJSONObject3 != null) {
                                boolean z4 = optJSONObject3.optString("domainNameOpenFlag", "0").equals("1");
                                String optString7 = optJSONObject3.optString("domainNameHint", "");
                                String optString8 = optJSONObject3.optString("domainNamekHintUrl", "");
                                String optString9 = optJSONObject3.optString("domainNameRiskHint", "");
                                String optString10 = optJSONObject3.optString("confirmName", "");
                                String optString11 = optJSONObject3.optString("cancelName", "");
                                findFirst.setGrayHint(optString7);
                                findFirst.setGrayHintLink(optString8);
                                findFirst.setGrayOpen(z4);
                                findFirst.setGrayCancelName(optString11);
                                findFirst.setGrayConfimName(optString10);
                                findFirst.setGrayDialogHint(optString9);
                            } else {
                                findFirst.setGrayHint("");
                                findFirst.setGrayHintLink("");
                                findFirst.setGrayOpen(false);
                                findFirst.setGrayCancelName("");
                                findFirst.setGrayConfimName("");
                                findFirst.setGrayDialogHint("");
                            }
                            JSONObject optJSONObject4 = jSONObject3.optJSONObject("ecsTokenDomain");
                            if (optJSONObject4 != null) {
                                JSONArray optJSONArray3 = optJSONObject4.optJSONArray("domainNameList");
                                if (!optJSONObject4.optString("domainNameOpenFlag", "0").equals("1")) {
                                    z = false;
                                }
                                String optString12 = optJSONObject4.optString("ecsTokenVersion", "");
                                if (!optString12.equals(findFirst.getEcsTokenVersion())) {
                                    findFirst.setEcsTokenHosts(WebHostSafelyManager.this.formatHosts(optJSONArray3));
                                }
                                findFirst.setEcsTokenOpen(z);
                                findFirst.setEcsTokenVersion(optString12);
                            } else {
                                findFirst.setEcsTokenHosts("");
                                findFirst.setEcsTokenOpen(false);
                                findFirst.setEcsTokenVersion("");
                            }
                            findFirst.setHomeWebBaoGuangStatus(jSONObject2.optString("homeWebBaoGuangStatus"));
                            HomeTabDebounce.isOpenHomeTabCaiji = "on".equals(jSONObject2.optString("homeWebBaoGuangStatus"));
                            WebHostSafelyManager.this.safelyHostEntityBox.removeAll();
                            WebHostSafelyManager.this.safelyHostEntityBox.put((Box) findFirst);
                            return "";
                        }
                        MsLogUtil.m7979d(WebHostSafelyManager.TAG, "loadHostSafetyConfig 非0000 清空数据");
                        WebHostSafelyManager.this.safelyHostEntityBox.removeAll();
                        return "";
                    } catch (Exception e) {
                        MsLogUtil.m7979d(WebHostSafelyManager.TAG, "loadHostSafetyConfig 清空数据 " + e.getMessage());
                        WebHostSafelyManager.this.safelyHostEntityBox.removeAll();
                        return "";
                    }
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.1
                @Override // io.reactivex.functions.Consumer
                public void accept(Object obj) throws Exception {
                    MsLogUtil.m7979d(WebHostSafelyManager.TAG, "loadHostSafetyConfig 完成");
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MsLogUtil.m7979d(WebHostSafelyManager.TAG, "loadHostSafetyConfig " + th.getMessage());
                }
            });
        } catch (Exception e) {
            try {
                MsLogUtil.m7979d(TAG, "loadHostSafetyConfig " + e.getMessage());
                this.safelyHostEntityBox.removeAll();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private List<String> parseHosts(String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(",");
            for (int i = 0; i < split.length; i++) {
                if (!TextUtils.isEmpty(split[i])) {
                    arrayList.add(split[i]);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatHosts(JSONArray jSONArray) throws Exception {
        String str = "";
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                String string = jSONArray.getString(i);
                str = i == jSONArray.length() - 1 ? str + string : str + string + ",";
            }
        }
        return str;
    }

    private boolean matchHost(List<String> list, String str) throws Exception {
        if (list != null) {
            try {
                if (TextUtils.isEmpty(str)) {
                    return false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (str.endsWith(list.get(i))) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    private String interceptWebLoadHost(String str) throws Exception {
        String str2;
        try {
            if (TextUtils.isEmpty(str) || !(str.startsWith("http://") || str.startsWith("https://"))) {
                str2 = Strategy_WhiteOrPass;
            } else {
                String host = Uri.parse(str).getHost();
                if (matchHost(this.localWebLoadSafelyHostList, host)) {
                    str2 = Strategy_WhiteOrPass;
                } else {
                    SafelyHostEntity findFirst = this.safelyHostEntityBox.query().build().findFirst();
                    if (findFirst != null) {
                        List<String> parseHosts = parseHosts(findFirst.getWhiteHosts());
                        List<String> parseHosts2 = parseHosts(findFirst.getBlackHosts());
                        if (findFirst.isWhiteOpen() && parseHosts.size() > 0 && matchHost(parseHosts, host)) {
                            str2 = Strategy_WhiteOrPass;
                        } else if (findFirst.isBlackOpen() && parseHosts2.size() > 0 && matchHost(parseHosts2, host)) {
                            str2 = Strategy_Black;
                        } else {
                            str2 = findFirst.isGrayOpen() ? Strategy_Gray : Strategy_WhiteOrPass;
                        }
                    } else {
                        str2 = Strategy_WhiteOrPass;
                    }
                }
            }
            MsLogUtil.m7979d(TAG, "interceptWebLoadHost 链接：" + str + "  结果：" + str2);
            return str2;
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "interceptWebLoadHost " + e.getMessage());
            return Strategy_WhiteOrPass;
        }
    }

    private void showGrayDialogHint(Activity activity, final WebHostSafelyListener webHostSafelyListener) throws Exception {
        if (activity == null || !(activity instanceof Activity) || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Dialog dialog = this.grayDialog;
        if (dialog == null || !dialog.isShowing()) {
            SafelyHostEntity findFirst = this.safelyHostEntityBox.query().build().findFirst();
            this.grayDialog = new Dialog(activity, 2131951911);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            WindowManager.LayoutParams attributes = this.grayDialog.getWindow().getAttributes();
            attributes.width = (int) (displayMetrics.widthPixels * 0.6d);
            attributes.height = -2;
            this.grayDialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493410, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131298435);
            if (findFirst != null && !TextUtils.isEmpty(findFirst.getGrayDialogHint())) {
                textView.setText(findFirst.getGrayDialogHint());
            }
            Button button = (Button) inflate.findViewById(2131298436);
            Button button2 = (Button) inflate.findViewById(2131298434);
            if (!TextUtils.isEmpty(findFirst.getGrayCancelName())) {
                button.setText(findFirst.getGrayCancelName());
            }
            if (!TextUtils.isEmpty(findFirst.getGrayConfimName())) {
                button2.setText(findFirst.getGrayConfimName());
            }
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    WebHostSafelyManager.this.grayDialog.cancel();
                    webHostSafelyListener.allow();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    WebHostSafelyManager.this.grayDialog.cancel();
                    webHostSafelyListener.refuse();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.grayDialog.setContentView(inflate);
            this.grayDialog.setCancelable(false);
            this.grayDialog.setCanceledOnTouchOutside(false);
            this.grayDialog.show();
            this.grayDialog.getWindow().setLayout((int) (displayMetrics.widthPixels * 0.8d), -2);
        }
    }

    private void showBlackDialogHint(final Activity activity, final WebHostSafelyListener webHostSafelyListener) throws Exception {
        if (activity == null || !(activity instanceof Activity) || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Dialog dialog = this.blackDialog;
        if (dialog == null || !dialog.isShowing()) {
            final SafelyHostEntity findFirst = this.safelyHostEntityBox.query().build().findFirst();
            this.blackDialog = new Dialog(activity, 2131951911);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            WindowManager.LayoutParams attributes = this.blackDialog.getWindow().getAttributes();
            attributes.width = (int) (displayMetrics.widthPixels * 0.6d);
            attributes.height = -2;
            this.blackDialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493409, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131298431);
            if (findFirst != null && !TextUtils.isEmpty(findFirst.getBlackHint())) {
                textView.setText(findFirst.getBlackHint());
            }
            Button button = (Button) inflate.findViewById(2131298432);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131298429);
            Button button2 = (Button) inflate.findViewById(2131298433);
            Button button3 = (Button) inflate.findViewById(2131298430);
            if (TextUtils.isEmpty(findFirst.getBlackHintLink())) {
                linearLayout.setVisibility(8);
                button.setVisibility(0);
            } else {
                linearLayout.setVisibility(0);
                button.setVisibility(8);
            }
            if (!TextUtils.isEmpty(findFirst.getBlackCancelName())) {
                button3.setText(findFirst.getBlackCancelName());
            }
            if (!TextUtils.isEmpty(findFirst.getBlackConfirmName())) {
                button.setText(findFirst.getBlackConfirmName());
                button2.setText(findFirst.getBlackConfirmName());
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    WebHostSafelyManager.this.blackDialog.cancel();
                    webHostSafelyListener.refuse();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    WebHostSafelyManager.this.blackDialog.cancel();
                    webHostSafelyListener.refuse();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            button3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.safetydetection.WebHostSafelyManager.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    WebHostSafelyManager.this.blackDialog.cancel();
                    webHostSafelyListener.refuse();
                    IntentManager.gotoWebViewActivity(activity, findFirst.getBlackHintLink(), "", "0", "get");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.blackDialog.setContentView(inflate);
            this.blackDialog.setCancelable(false);
            this.blackDialog.setCanceledOnTouchOutside(false);
            this.blackDialog.show();
            this.blackDialog.getWindow().setLayout((int) (displayMetrics.widthPixels * 0.8d), -2);
        }
    }

    public void shouldInterceptWebOverride(Activity activity, String str, WebHostSafelyListener webHostSafelyListener) {
        try {
            String interceptWebLoadHost = interceptWebLoadHost(str);
            if (Strategy_Black.equals(interceptWebLoadHost)) {
                showBlackDialogHint(activity, webHostSafelyListener);
            } else if (Strategy_Gray.equals(interceptWebLoadHost)) {
                webHostSafelyListener.warn(this.safelyHostEntityBox.query().build().findFirst());
                webHostSafelyListener.allow();
            } else {
                webHostSafelyListener.allow();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "shouldInterceptWebOverride " + e.getMessage());
            webHostSafelyListener.allow();
        }
    }

    public void shouldInterceptWebLanuch(Activity activity, String str, WebHostSafelyListener webHostSafelyListener) {
        try {
            String interceptWebLoadHost = interceptWebLoadHost(str);
            if (Strategy_Black.equals(interceptWebLoadHost)) {
                showBlackDialogHint(activity, webHostSafelyListener);
            } else if (Strategy_Gray.equals(interceptWebLoadHost)) {
                showGrayDialogHint(activity, webHostSafelyListener);
            } else {
                webHostSafelyListener.allow();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "shouldInterceptWebOverride " + e.getMessage());
            webHostSafelyListener.allow();
        }
    }

    public boolean isOpenHomeTabCaiji() {
        try {
            return "on".equals(this.safelyHostEntityBox.query().build().findFirst().getHomeWebBaoGuangStatus());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
